package org.example.model.MaHoaDoiXungModel;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class DES {
    private SecretKey key;
    private int keySize = 56;
    private final int ivSize = 8;
//    DES/CBC/NoPadding (56)
//    DES/CBC/PKCS5Padding (56)
//    DES/ECB/NoPadding (56)
//    DES/ECB/PKCS5Padding (56)
private String[] listAlorithm = {"DES/ECB/PKCS5Padding", "DES/CBC/PKCS5Padding", "DES/CFB8/NoPadding", "DES/OFB/NoPadding", "DES/CTR/NoPadding"};
    private String algorithm = listAlorithm[0];
    private final IvParameterSpec iv;

    public DES() {
        iv = genIv();
    }

    //main
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        DES des = new DES();
        SecretKey key = des.GenKey();
        SecretKey k2 = des.stringToSecretKey(Base64.getEncoder().encodeToString(key.getEncoded()));
        des.loadKey(k2);

        String plainText = "hello world";
        byte[] cipherText = des.encryptStr(plainText);
        System.out.println(Base64.getEncoder().encodeToString(cipherText));
        System.out.println(des.decryptString(cipherText));

        byte[] cipherTextBase64 = des.encryptBase64(plainText);
        System.out.println(Base64.getEncoder().encodeToString(cipherTextBase64));
        System.out.println(des.decryptBase64(cipherTextBase64));

//        String fileIn = "D:\\storage\\taiLieu/21130598_VuHoangMinhTu.docx";
//        String fileOut = "D:\\storage\\taiLieu/cipherFile";
//        String fileDe = "D:\\storage\\taiLieu/3.docx";
//        des.encryptFile(fileIn,fileOut);
//        des.decryptFile(fileOut,fileDe);
        /**
         //         * test string to secrect key
         //         *
         //         */
//        System.out.println(des.SecretKeyToString(des.stringToSecretKey("reU7Uv5KJYk=")));
//        System.out.println(des.checkKey(des.stringToSecretKey("reU7Uv5KJYk=")));




    }

    public SecretKey GenKey() throws NoSuchAlgorithmException {
        KeyGenerator genKey = KeyGenerator.getInstance("DES");
        genKey.init(keySize);
        return genKey.generateKey();
    }

    public void loadKey(SecretKey key) {
        this.key = key;
    }

    public IvParameterSpec genIv() {
        byte[] ivBytes = new byte[ivSize];  // DES sử dụng IV dài 8 byte
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(ivBytes);  // Sinh ra mảng byte ngẫu nhiên
        return new IvParameterSpec(ivBytes);
    }

    public byte[] encryptStr(String plaintext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(algorithm);
        if (algorithm.equals(listAlorithm[0])) {
            System.out.println(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        }
        return cipher.doFinal(plaintext.getBytes());

    }

    public String decryptString(byte[] ciphertext) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(algorithm);
        if (algorithm.equals("DES/ECB/PKCS5Padding")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
        }
        return new String(cipher.doFinal(ciphertext), StandardCharsets.UTF_8);
    }

    public byte[] encryptBase64(String str) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        return Base64.getEncoder().encode(encryptStr(str));
    }

    public String decryptBase64(byte[] ciphertext) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        return decryptString(Base64.getDecoder().decode(ciphertext));
    }

    public boolean encryptFile(String pathIn, String pathOut) throws IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(algorithm);
        if (algorithm.equals("DES/ECB/PKCS5Padding")) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathIn));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathOut));

        CipherInputStream cis = new CipherInputStream(bis, cipher);
        byte[] read = new byte[1024];
        int length;
        while((length = cis.read(read))!= -1){
            bos.write(read, 0, length);
        }
        read = cipher.doFinal();
        if(read!=null){
            bos.write(read);
        }
        bos.flush();
        bos.close();
        cis.close();
        return true;
    }

    public boolean decryptFile(String pathIn, String pathOut) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(algorithm);
        if (algorithm.equals("DES/ECB/PKCS5Padding")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathIn));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathOut));

        CipherOutputStream cos = new CipherOutputStream(bos, cipher);
        byte[] read = new byte[1024];
        int length;
        while((length = bis.read(read))!=-1){
            cos.write(read, 0, length);
        }
        read = cipher.doFinal();
        if(read!=null){
            bos.write(read);
        }
        bos.flush();
        bos.close();
        cos.close();
        cos.close();
        return true;
    }

    public SecretKey stringToSecretKey(String str){
        if(!(str.matches("^[A-Za-z0-9+/]*={0,2}$") && (str.length() % 4 == 0)))
            return null;
        if(Base64.getDecoder().decode(str).length == 8) {
            byte[] encoded = Base64.getDecoder().decode(str);
            return new SecretKeySpec(encoded, "DES");
        }
        return null;
    }

    //    getter setter
    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }

    public String[] getListAlorithm() {
        return listAlorithm;
    }

    public void setListAlorithm(String[] listAlorithm) {
        this.listAlorithm = listAlorithm;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

}
