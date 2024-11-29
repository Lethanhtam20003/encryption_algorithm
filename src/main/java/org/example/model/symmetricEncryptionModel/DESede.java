package org.example.model.symmetricEncryptionModel;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class DESede {
    private SecretKey key;
    private Cipher cipher;
    private final String[] listAlgorithms = {"DESede/ECB/PKCS5Padding", "DESede/CBC/PKCS5Padding", "DESede/CFB/PKCS5Padding",
            "DESede/OFB/PKCS5Padding", "DESede/CTR/NoPadding"};
    private String algorithm = listAlgorithms[0]; // Default to ECB
    private final int[] listKeySize = {112, 168,192}; // DESede key size: 112 or 168 bits
    private int keySize = listKeySize[0]; // Default to 168-bit key size
    private IvParameterSpec ivSpec;

    // Hàm tạo khóa
    public SecretKey genKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
        keyGen.init(keySize); // Chọn chiều dài khóa 168 bit
        return keyGen.generateKey();
    }

    // Hàm tải khóa
    public boolean loadKey(SecretKey key) {
        if (checkKey(key)){
            System.out.println("Chiều dài khóa: " + (key.getEncoded().length * 8) + " bit");
            this.key = key;
            return true;
        }
        return false;
    }

    // Kiểm tra khóa
    private boolean checkKey(SecretKey key) {
        if (key == null) {
            System.out.println("Key is null");
            return false;
        }
        byte[] keyBytes = key.getEncoded();
        try {
            // Kiểm tra kích thước khóa
            int keyLength = keyBytes.length * 8; // Đổi sang bit
            if (keyLength != 112 && keyLength != 168 && keyLength != 192 && keyLength != 256) {
                return false;
            }

            // Thử tạo SecretKeySpec và khởi tạo Cipher
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Nếu không có ngoại lệ, khóa hợp lệ
            return true;
        } catch (Exception e) {
            // In lỗi nếu có vấn đề xảy ra
            System.out.println("Khóa không hợp lệ: " + e.getMessage());
            return false;
        }
    }
    // Hàm tạo IV ngẫu nhiên
    public IvParameterSpec genIv() {
        byte[] iv = new byte[8]; // IV cho DESede là 8 byte
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    // Hàm tải IV
    public boolean loadIv(IvParameterSpec ivSpec) {
        if (ivSpec == null)
            return false;
        this.ivSpec = ivSpec;
        return true;
    }
    // Hàm mã hóa chuỗi
    public String encryptStr(String data) throws Exception {
        cipher = Cipher.getInstance(algorithm);
        if (algorithm.equals(listAlgorithms[0])) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        }
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Hàm giải mã chuỗi
    public String decryptStr(String encryptedData) throws Exception {
        cipher = Cipher.getInstance(algorithm);
        if (algorithm.equals(listAlgorithms[0])) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        }
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] originalData = cipher.doFinal(decodedData);
        return new String(originalData);
    }

    // Hàm mã hóa tệp
    public boolean encryptFile(String inputFilePath, String outputFilePath) throws Exception {
        cipher = Cipher.getInstance(algorithm);
        if (algorithm.equals(listAlgorithms[0])) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFilePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFilePath))) {
            if (!algorithm.equals(listAlgorithms[0])) {
                bos.write(this.ivSpec.getIV());
            }
            try (CipherInputStream cis = new CipherInputStream(bis, cipher)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = cis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
            }catch (IOException e) {
                System.out.println("Lỗi khi mã hóa tệp: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
            bos.flush();
        }
        return true;
    }

    // Hàm giải mã tệp
    public boolean decryptFile(String inputFilePath, String outputFilePath) throws Exception {
        cipher = Cipher.getInstance(algorithm);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFilePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFilePath))) {
            if (algorithm.equals(listAlgorithms[0])) {
                cipher.init(Cipher.DECRYPT_MODE, key);
            } else {
                byte[] iv = new byte[8];
                bis.read(iv); // Đọc IV từ đầu tệp
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            }
            try (CipherOutputStream cos = new CipherOutputStream(bos, cipher)) {
                byte[] read = new byte[1024];
                int length;
                while ((length = bis.read(read)) != -1) {
                    cos.write(read, 0, length);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        DESede desede = new DESede();
        SecretKey key = desede.genKey();
        System.out.println("Key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
        System.out.println("Key load: " + desede.loadKey(key));
        IvParameterSpec ivspec = desede.genIv();
        desede.loadIv(ivspec);

        String plaintext = "hello";
        String encrypted = desede.encryptStr(plaintext);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = desede.decryptStr(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
