package org.example.model.asymmetricEncryptionModel;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {
    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private final int[] listKeySize = {512,1024,2048,3072,4096};
    private int keySize = listKeySize[2];
    public RSA(){
        try {
            genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public void genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        keyPair = keyGen.generateKeyPair();
    }
    public PublicKey genPublicKey() {
        return keyPair.getPublic();
    }
    public PrivateKey genPrivateKey() {
        return keyPair.getPrivate();
    }
    public boolean loadPublicKey(String publicKey)   {

        try {
            // Giải mã chuỗi Base64 để lấy byte[]
            byte[] keyBytes = Base64.getDecoder().decode(publicKey);

            // Sử dụng KeyFactory để tạo public key từ byte[]
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            try {
                this.publicKey = keyFactory.generatePublic(keySpec);
            }catch (InvalidKeySpecException e){
                return false;
            }

            return true; // Thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
    }
    public boolean loadPrivateKey(String privateKey)   {
        try {
            // Giải mã chuỗi Base64 để lấy byte[]
            byte[] keyBytes = Base64.getDecoder().decode(privateKey);

            // Sử dụng KeyFactory để chuyển đổi byte[] thành PrivateKey
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            try {
                this.privateKey = keyFactory.generatePrivate(keySpec);
            } catch (InvalidKeySpecException e) {
                return false;
            }


            return true; // Thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
    }
    public String encryptBase64(String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return Base64.getEncoder().encodeToString(encrypt(data));
    }
    public byte[] encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    public byte[] decryptBase64(String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return Base64.getDecoder().decode(data);
    }

    public String decrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data), StandardCharsets.UTF_8);
    }
    public String keyToString(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static void main(String[] args) throws Exception {
        RSA lap = new RSA();
        PublicKey publicKey = lap.genPublicKey();
        PrivateKey privateKey = lap.genPrivateKey();
        String pbkStr = lap.keyToString(publicKey);
        String pvkStr = lap.keyToString(privateKey);
        System.out.println("public key:"+pbkStr);
        System.out.println("private Key:"+pvkStr);
        boolean g1 = lap.loadPublicKey(pbkStr);
        boolean g2 = lap.loadPrivateKey(pvkStr);
        System.out.println("load key"+g1+g2 );
        System.out.println(Base64.getEncoder().encodeToString(lap.encrypt("test")));
        System.out.println(lap.decrypt(lap.encrypt("test")));

    }


    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public int[] getListKeySize() {
        return listKeySize;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }
}
