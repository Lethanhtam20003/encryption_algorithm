package org.example.model.MaHoaDoiXungModel;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DES {
    SecretKey key;
    private int keySize = 56;
//    DES/CBC/NoPadding (56)
//    DES/CBC/PKCS5Padding (56)
//    DES/ECB/NoPadding (56)
//    DES/ECB/PKCS5Padding (56)
    private final String algorithm = "DES";
    public DES() {}
    public SecretKey GenKey() throws NoSuchAlgorithmException {
        KeyGenerator genKey = KeyGenerator.getInstance(algorithm);
        genKey.init(keySize);
        return genKey.generateKey();
    }
    public void loadKey(SecretKey key) throws NoSuchAlgorithmException {
        this.key = key;
    }
    public byte[] encryptStr(String plaintext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] data = plaintext.getBytes();
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        return cipher.doFinal(data);
    }
    public String decryptString(byte[] ciphertext) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE,key);

        return new String(cipher.doFinal(ciphertext), StandardCharsets.UTF_8);
    }
    public byte[] encryptBase64(String str) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return Base64.getEncoder().encode(encryptStr(str));
    }
    public String decryptBase64(byte[] ciphertext) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return decryptString(Base64.getDecoder().decode(ciphertext));
    }
    public boolean encryptFile(String pathIn,String pathOut) throws IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE,key);

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
    public boolean decryptFile(String pathIn,String pathOut) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE,key);

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
    public String SecretKeyToString(SecretKey key) throws NoSuchAlgorithmException {
        byte[] encoded = key.getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }
    public SecretKey stringToSecretKey(String str){
        if(Base64.getDecoder().decode(str).length == 8) {
            byte[] encoded = Base64.getDecoder().decode(str);
            return new SecretKeySpec(encoded, algorithm);
        }
        return null;
    }
    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }
    public boolean checkKey(SecretKey key) {
        // check null
        if(key == null){
            return false;
        }
        // check size
        if(8 != key.getEncoded().length){
            return false;
        }
        return  true;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        DES des = new DES();
        SecretKey key = des.GenKey();
        des.loadKey(key);

//        String plainText = "hello world";
//        byte[] cipherText = des.encryptStr(plainText);
//        System.out.println(des.SecretKeyToString(key));
//        System.out.println(Base64.getEncoder().encodeToString(cipherText));
//        System.out.println(new String(des.decryptString(cipherText)));
//
//        byte[] cipherTextBase64 = des.encryptBase64(plainText);
//        System.out.println(Base64.getEncoder().encodeToString(cipherTextBase64));
//        System.out.println(new String(des.decryptBase64(cipherTextBase64)));

        String fileIn = "D:\\storage\\taiLieu/21130598_VuHoangMinhTu.docx";
        String fileOut = "D:\\storage\\taiLieu/cipherFile";
        String fileDe = "D:\\storage\\taiLieu/3.docx";
        des.encryptFile(fileIn,fileOut);
        des.decryptFile(fileOut,fileDe);
        /**
//         * test string to secrect key
//         *
//         */
//        System.out.println(des.SecretKeyToString(des.stringToSecretKey("reU7Uv5KJYk=")));
//        System.out.println(des.checkKey(des.stringToSecretKey("reU7Uv5KJYk=")));




    }


}
