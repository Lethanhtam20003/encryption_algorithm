package org.example.model.MaHoaDoiXungModel;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {
    private SecretKey key;
    private IvParameterSpec iv;
    private int keySize; //key size list: 128,192,256
    public int[] listKeySize = {128,192,256};

//    AES/CBC/NoPadding (128)
//    AES/CBC/PKCS5Padding (128)
//    AES/ECB/NoPadding (128)
//    AES/ECB/PKCS5Padding (128)
    private final String algorithm = "AES/CBC/PKCS5Padding";

    public AES() {
        this.keySize = listKeySize[0];
        this.iv = genIv();
    }

    public SecretKey genKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keySize);
        return keyGen.generateKey();
    }
    public void loadKey(SecretKey key) {
        this.key = key;
    }
    public IvParameterSpec genIv() {
        byte[] ivBytes = new byte[16];
        new SecureRandom().nextBytes(ivBytes);
        return  new IvParameterSpec(ivBytes);
    }
    public void loadIv(IvParameterSpec iv) {
        this.iv = iv;
    }

    public byte[] encryptStr(String plainText) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(plainText.getBytes());
    }
    public String decryptStr(byte[] cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText);
    }
    public String encryptBase64(String plainText) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return Base64.getEncoder().encodeToString(encryptStr(plainText));
    }
    public String decryptBase64(byte[] cipherText) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return decryptStr(Base64.getDecoder().decode(cipherText));
    }
    public boolean encryptFile(String pathIn,String pathOut) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        try(
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathIn));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathOut))){
            bos.write(iv.getIV()); // ghi iv vao dau tep
            try(CipherInputStream cis = new CipherInputStream(bis, cipher)) {
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
    public boolean decryptFile(String pathIn,String pathOut) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(algorithm);

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathIn));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathOut))) {
            byte[] ivBytes = new byte[16];
            bis.read(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE,key,iv);
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

    public SecretKey stringToSecretKey(String str) {
        if(!(str.matches("^[A-Za-z0-9+/]*={0,2}$") && (str.length() % 4 == 0)))
            return null;
        byte[] encoded = Base64.getDecoder().decode(str);
        if( encoded.length == 16 || encoded.length == 24 || encoded.length == 32) {
            return new SecretKeySpec(Base64.getDecoder().decode(str), "AES");
        }
        return null;
    }

    public String getAlgorithm() {
        return algorithm;
    }
    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }


    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        AES aes = new AES();
        aes.loadKey(aes.genKey());
        String plaintext = "plaintext";
        System.out.println(aes.encryptBase64(plaintext));
    }
}
