package org.example.model.classicalEncryption;

import java.util.Arrays;
import java.util.Random;

public class Hill {
    private static final int ALPHABET_SIZE = 26; // Số ký tự trong bảng chữ cái
    private int[][] key;
    public int[][] genKey(int n){
        Random rand = new Random();
        int[][] key = new int[n][n];
        do{
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    key[i][j] = rand.nextInt(ALPHABET_SIZE);
                }
            }
            System.out.println(1);
        }while (determinant(key, n) == 0 || modInverse(determinant(key, n), ALPHABET_SIZE) == -1);
        return key;
    }
    public boolean loadKey(int[][] key){
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                if(key[i][j] < 0 || key[i][j] >= ALPHABET_SIZE){
                    return false;
                }
            }
        }
        this.key = key;
        return true;
    }
    // Mã hóa Hill Cipher
    public String encrypt(String text, int[][] keyMatrix) {
        int n = keyMatrix.length; // Kích thước ma trận
        text = preprocessText(text, n); // Đảm bảo chiều dài văn bản chia hết cho n
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < text.length(); i += n) {
            int[] plainVector = new int[n];
            for (int j = 0; j < n; j++) {
                plainVector[j] = text.charAt(i + j) - 'A';
            }

            int[] cipherVector = matrixMultiplyMod(plainVector, keyMatrix, ALPHABET_SIZE);

            for (int value : cipherVector) {
                cipherText.append((char) (value + 'A'));
            }
        }

        return cipherText.toString();
    }

    // Giải mã Hill Cipher
    public String decrypt(String cipherText, int[][] keyMatrix) {
        int[][] inverseKeyMatrix = invertMatrixMod(keyMatrix, ALPHABET_SIZE); // Ma trận nghịch đảo
        return encrypt(cipherText, inverseKeyMatrix); // Mã hóa với ma trận nghịch đảo để giải mã
    }

    // Tính tích ma trận với modulo
    private int[] matrixMultiplyMod(int[] vector, int[][] matrix, int mod) {
        int n = matrix.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= mod; // Lấy phần dư
        }
        return result;
    }

    // Tính ma trận nghịch đảo theo modulo
    private int[][] invertMatrixMod(int[][] matrix, int mod) {
        int n = matrix.length;
        int det = determinant(matrix, n);
        int detInverse = modInverse(det, mod);

        if (detInverse == -1) {
            throw new IllegalArgumentException("Matrix is not invertible modulo " + mod);
        }

        int[][] adjMatrix = adjoint(matrix);
        int[][] inverseMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = (adjMatrix[i][j] * detInverse % mod + mod) % mod;
            }
        }

        return inverseMatrix;
    }

    // Tính định thức của ma trận
    private int determinant(int[][] matrix, int n) {
        if (n == 1) return matrix[0][0];

        int det = 0;
        int[][] subMatrix = new int[n][n];
        for (int x = 0; x < n; x++) {
            int subI = 0;
            for (int i = 1; i < n; i++) {
                int subJ = 0;
                for (int j = 0; j < n; j++) {
                    if (j == x) continue;
                    subMatrix[subI][subJ++] = matrix[i][j];
                }
                subI++;
            }
            det += matrix[0][x] * determinant(subMatrix, n - 1) * (x % 2 == 0 ? 1 : -1);
        }
        return det % ALPHABET_SIZE;
    }

    // Tính ma trận adjoint
    private int[][] adjoint(int[][] matrix) {
        int n = matrix.length;
        int[][] adjMatrix = new int[n][n];

        if (n == 1) {
            adjMatrix[0][0] = 1;
            return adjMatrix;
        }

        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sign = ((i + j) % 2 == 0) ? 1 : -1;
                getCofactor(matrix, temp, i, j, n);
                adjMatrix[j][i] = sign * determinant(temp, n - 1);
            }
        }
        return adjMatrix;
    }

    // Tính cofactor
    private void getCofactor(int[][] matrix, int[][] temp, int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    // Tính nghịch đảo modulo
    private int modInverse(int a, int mod) {
        a %= mod;
        for (int x = 1; x < mod; x++) {
            if ((a * x) % mod == 1) return x;
        }
        return -1; // Không có nghịch đảo
    }

    // Xử lý văn bản: thêm ký tự để đủ chia hết cho n
    private String preprocessText(String text, int n) {
        while (text.length() % n != 0) {
            text += 'X'; // Thêm ký tự đệm
        }
        return text.toUpperCase();
    }

    // Chạy thử
    public static void main(String[] args) {
        Hill hill= new Hill();
        int[][] key = hill.genKey(2);
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                System.out.print(key[i][j] + " ");
            }
            System.out.println();
        }
        hill.loadKey(key);
        String plaintext = "helloworlddsadasdasdasdsad";
        System.out.println("plaintext: " + plaintext);
        String encrypt = hill.encrypt(plaintext, key);
        System.out.println("encrypted: " + encrypt);
        String decrypt = hill.decrypt(encrypt, key);
        System.out.println("decrypted: " + decrypt);
    }
}
