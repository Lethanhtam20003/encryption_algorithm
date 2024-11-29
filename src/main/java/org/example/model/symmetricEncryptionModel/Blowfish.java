package org.example.model.symmetricEncryptionModel;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Blowfish {
    private SecretKey key;
    private Cipher cipher;
    private final String[] listAlgorithms = {"Blowfish/ECB/PKCS5Padding","Blowfish/CBC/NoPadding","Blowfish/CFB/PKCS5Padding",
                                        "Blowfish/OFB/PKCS5Padding","Blowfish/CTR/NoPadding"};
    private String algorithm = listAlgorithms[0];
    private final int[] listKeySize = {32,128,256,448};
    private int keySize = listKeySize[1];
    private IvParameterSpec ivSpec;
    public SecretKey genKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
        keyGen.init(128);
        return keyGen.generateKey();
    }
    public boolean loadKey(SecretKey key) {
        if(!checkKey(key))
            return false;
        this.key = key;
        return true;
    }
    public IvParameterSpec genIv(){
        byte[] iv = new byte[8];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
    public boolean loadIv(IvParameterSpec ivSpec) {
        if(ivSpec == null)
            return false;
        this.ivSpec = ivSpec;
        return true;
    }

    private boolean checkKey(SecretKey key) {
        if(key == null){
            System.out.println("Key is null");
            return false;
        }
        byte[] keyBytes = key.getEncoded();
        try {
            // Kiểm tra kích thước khóa
            int keyLength =  keyBytes.length* 8; // Đổi sang bit
            if (keyLength < 32 || keyLength > 448) {
                System.out.println("Kích thước khóa không hợp lệ: " + keyLength + " bit");
                return false;
            }

            // Thử tạo SecretKeySpec và khởi tạo Cipher
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "Blowfish");
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

    // Hàm mã hóa
    public String encryptStr(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        if(algorithm.equals(listAlgorithms[0]))
            cipher.init(Cipher.ENCRYPT_MODE, key);
        else
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Hàm giải mã
    public String decryptStr(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        if(algorithm.equals(listAlgorithms[0]))
            cipher.init(Cipher.DECRYPT_MODE, key);
        else
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] originalData = cipher.doFinal(decodedData);
        return new String(originalData);
    }
    // Hàm mã hóa nội dung tệp
    public boolean encryptFile(String inputFilePath, String outputFilePath) throws Exception {
        cipher = Cipher.getInstance(algorithm);
        if(algorithm.equals(listAlgorithms[0]))
            cipher.init(Cipher.ENCRYPT_MODE, key);
        else
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        try (BufferedInputStream bis =new BufferedInputStream( new FileInputStream(inputFilePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFilePath))) {
            if(!algorithm.equals(listAlgorithms[0]))
                bos.write(this.ivSpec.getIV());
            try(CipherInputStream cis = new CipherInputStream(bis,cipher)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = cis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
            }
            bos.flush();
        }
        return true;
    }

    // Hàm giải mã nội dung tệp
    public boolean decryptFile(String inputFilePath, String outputFilePath) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        try (BufferedInputStream bis =new BufferedInputStream( new FileInputStream(inputFilePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFilePath))) {
            if(algorithm.equals(listAlgorithms[0]))
                cipher.init(Cipher.ENCRYPT_MODE, key);
            else{
                byte[] iv = new byte[8];
                bis.read(iv);
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            }
            try(CipherOutputStream cos = new CipherOutputStream(bos, cipher)){
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
        Blowfish blowfish = new Blowfish();
        SecretKey key = blowfish.genKey();
        System.out.println("key:"+Base64.getEncoder().encodeToString(key.getEncoded()));
        System.out.println("key load:"+blowfish.loadKey(key));
        IvParameterSpec ivspec = blowfish.genIv();
        blowfish.loadIv(ivspec);
//        String plaintext = "hello";
//        String encrypted = blowfish.encryptStr(plaintext);
//        System.out.println(encrypted);
//        String decrypted = blowfish.decryptStr(encrypted);
//        System.out.println(decrypted);

    }
}
