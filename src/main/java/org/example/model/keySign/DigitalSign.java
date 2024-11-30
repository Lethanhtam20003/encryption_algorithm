package org.example.model.keySign;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DigitalSign {
    KeyPair keyPair;
    SecureRandom random;
    Signature signature;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    private String[] listDigitalSignatureAlgorithm = {"DSA","RSA",};
    private String digitalSignatureAlgorithm = listDigitalSignatureAlgorithm[0];
    private String[] listRandomNumberGenerationAlgorithm = {"SHA1PRNG","NativePRNG","NativePRNGBlocking","NativePRNGNonBlocking"};
    private String randomNumberGenerationAlgorithm = listRandomNumberGenerationAlgorithm[0];
    private String algorithmProvider = "SUN";

    public DigitalSign()  {
        KeyPairGenerator keyGen= null;
        try {
            keyGen = KeyPairGenerator.getInstance(digitalSignatureAlgorithm,algorithmProvider);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        try {
            random = SecureRandom.getInstance(randomNumberGenerationAlgorithm,algorithmProvider);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        keyGen.initialize(1024,random);
        keyPair = keyGen.genKeyPair();
        try {
            signature = Signature.getInstance(digitalSignatureAlgorithm,algorithmProvider);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }
    public PublicKey genPublicKey(){
        if(keyPair!=null){
            return keyPair.getPublic();
        }
        return null;
    }
    public PrivateKey genPrivateKey(){
        if(keyPair!=null){
            return keyPair.getPrivate();
        }
        return null;
    }
    public boolean LoadPublicKey(String key){
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            KeyFactory keyFactory = KeyFactory.getInstance(digitalSignatureAlgorithm);
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean LoadPrivateKey(String key){
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            KeyFactory keyFactory = KeyFactory.getInstance(digitalSignatureAlgorithm);
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String sign(String mes) throws SignatureException, InvalidKeyException {
        byte[] data = mes.getBytes();
        signature.initSign(privateKey);
        signature.update(data);
        byte[] sign = signature.sign();
        return Base64.getEncoder().encodeToString(sign);
    }
    public String signFile(String src) throws InvalidKeyException, IOException, SignatureException {
        signature.initSign(privateKey);
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src))){
            byte[] buf = new byte[1024];
            int len;
            while((len = bis.read(buf))!= -1){
                signature.update(buf,0,len);
            }
        }
        byte[] sign = signature.sign();
        return Base64.getEncoder().encodeToString(sign);
    }
    public boolean verify(String mes,String sign) throws InvalidKeyException, SignatureException {
        signature.initVerify(publicKey);
        byte[] data = mes.getBytes();
        byte[] signValue = Base64.getDecoder().decode(sign);
        signature.update(data);
        return signature.verify(signValue);
    }
    public boolean verifyFile(String src,String sign) throws InvalidKeyException, IOException, SignatureException {
        signature.initVerify(publicKey);
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src))){
            byte[] buf = new byte[1024];
            int len;
            while((len = bis.read(buf))!= -1){
                signature.update(buf,0,len);
            }
        }
        byte[] signValue = Base64.getDecoder().decode(sign);
        return signature.verify(signValue);
    }
    public String keyToString(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
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

    public String getAlgorithmProvider() {
        return algorithmProvider;
    }

    public void setAlgorithmProvider(String algorithmProvider) {
        this.algorithmProvider = algorithmProvider;
    }

    public String getRandomNumberGenerationAlgorithm() {
        return randomNumberGenerationAlgorithm;
    }

    public void setRandomNumberGenerationAlgorithm(String randomNumberGenerationAlgorithm) {
        this.randomNumberGenerationAlgorithm = randomNumberGenerationAlgorithm;
    }

    public String[] getListRandomNumberGenerationAlgorithm() {
        return listRandomNumberGenerationAlgorithm;
    }

    public void setListRandomNumberGenerationAlgorithm(String[] listRandomNumberGenerationAlgorithm) {
        this.listRandomNumberGenerationAlgorithm = listRandomNumberGenerationAlgorithm;
    }

    public String getDigitalSignatureAlgorithm() {
        return digitalSignatureAlgorithm;
    }

    public void setDigitalSignatureAlgorithm(String digitalSignatureAlgorithm) {
        this.digitalSignatureAlgorithm = digitalSignatureAlgorithm;
    }

    public String[] getListDigitalSignatureAlgorithm() {
        return listDigitalSignatureAlgorithm;
    }

    public void setListDigitalSignatureAlgorithm(String[] listDigitalSignatureAlgorithm) {
        this.listDigitalSignatureAlgorithm = listDigitalSignatureAlgorithm;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException, IOException {
        DigitalSign ds = new DigitalSign( );
        ds.genPublicKey();
        ds.genPrivateKey();
//        String sign = ds.sign("heloo");
//        System.out.println(sign);
//        boolean verify = ds.verify("heloo",sign);
//        System.out.println(verify);
        String src = "D:\\storage\\resource\\local-1.txt";
        String src2 = "D:\\storage\\resource\\local-2.txt";
        String sign = ds.signFile(src);
        System.out.println(sign);
        boolean verify = ds.verifyFile(src2,sign);
        System.out.println(verify);
    }
}
