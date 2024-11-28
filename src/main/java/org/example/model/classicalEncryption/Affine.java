package org.example.model.classicalEncryption;

import java.util.ArrayList;
import java.util.Random;

public class Affine {
    private static final int ALPHABET_SIZE = 26;

    private int[] key = null;
    public Affine() {}
    public int[] genKey(){
        Random random = new Random();
        int a, b;
        // Sinh giá trị cho a đảm bảo nguyên tố cùng nhau với ALPHABET_SIZE
        do {
            a = random.nextInt(ALPHABET_SIZE) + 1;
        } while (!isCoPrime(a));

        b = random.nextInt(ALPHABET_SIZE);

        return new int[]{a, b}; // Trả về mảng chứa cặp khóa {a, b}
    }
    public boolean loadKey(int[] arr){
        if (!isCoPrime(arr[1])) {
            return false;
        }
        if (arr[0]>26 || arr[1]>26) {
            return false;
        }
        this.key = arr;
        return true;
    }

    // Hàm mã hóa Affine Cipher
    public  String encrypt(String plaintext, int a, int b) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                int base = isUpper ? 'A' : 'a';
                int x = c - base;
                int encrypted = (a * x + b) % ALPHABET_SIZE;
                ciphertext.append((char) (encrypted + base));
            } else {
                ciphertext.append(c); // Giữ nguyên ký tự không phải chữ cái
            }
        }
        return ciphertext.toString();
    }

    // Hàm giải mã Affine Cipher
    public  String decrypt(String ciphertext, int a, int b) {
        int aInverse = modInverse(a); // modulo
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                int base = isUpper ? 'A' : 'a';
                int cVal = c - base;
                int decrypted = (aInverse * (cVal - b + ALPHABET_SIZE)) % ALPHABET_SIZE;
                plaintext.append((char) (decrypted + base));
            } else {
                plaintext.append(c); // Giữ nguyên ký tự không phải chữ cái
            }
        }
        return plaintext.toString();
    }

    // Hàm tính nghịch đảo modulo
    private int modInverse(int a) {
        for (int x = 1; x < Affine.ALPHABET_SIZE; x++) {
            if ((a * x) % Affine.ALPHABET_SIZE == 1) {
                return x;
            }
        }
        throw new IllegalArgumentException("Không tồn tại nghịch đảo modulo cho a = " + a + " và m = " + Affine.ALPHABET_SIZE);
    }

    // Kiểm tra 'a' có nguyên tố cùng nhau với 'm' không
    private boolean isCoPrime(int a) {
        return gcd(a, Affine.ALPHABET_SIZE) == 1;
    }

    // Hàm tính GCD (ước số chung lớn nhất) bằng thuật toán Euclid
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Affine affine = new Affine();
        String plaintext = "Khoa Cong Nghe Thong Tin";
        int a = 7, b = 3; // Các tham số mã hóa

        // Mã hóa
        String encrypted = affine.encrypt(plaintext, a, b);
        System.out.println("Văn bản sau mã hóa: " + encrypted);

        // Giải mã
        String decrypted = affine.decrypt(encrypted, a, b);
        System.out.println("Văn bản sau giải mã: " + decrypted);
    }
}
