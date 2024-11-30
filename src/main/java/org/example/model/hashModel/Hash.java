package org.example.model.hashModel;

import java.io.*;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    private final String[] listAlgorithm = { "MD5", "SHA-1", "SHA-256", "SHA-384"};
    private String algorithm = listAlgorithm[0];
    public String hash(String data) throws  NoSuchAlgorithmException {
//        khoi tao MessageDigiest voi thuat toan MD5
        MessageDigest md = MessageDigest.getInstance(algorithm);
//        chuyen chuoi dua vao thang mang byte
        byte[] bytes = data.getBytes();
//        bam
        byte[] digest= md.digest(bytes);
        BigInteger number = new BigInteger(1, digest);
//        chuyen ket qua sang hexa
        return number.toString(16);
    }

    public String hashFile(String src) throws NoSuchAlgorithmException, IOException {
//        khoi tao MessageDigiest voi thuat toan MD5
        MessageDigest md = MessageDigest.getInstance(algorithm);
//        Kiem tra co ton tai file khong
        File file = new File(src);
        if(!file.exists()) return null;
//        doc file va tinh tan mang bam
        try (DigestInputStream di = new DigestInputStream(new BufferedInputStream(new FileInputStream(file)), md)) {
            byte[] buff = new byte[1024];
            while (di.read(buff) != -1) {
                // Đọc file, digest sẽ tự động cập nhật
            }
        }
//        nhan kiet qua sau khi bam
        byte[] digest = md.digest();
        BigInteger number = new BigInteger(1, digest);
// Chuyển đổi sang chuỗi Hexadecimal với độ dài cố định
        return String.format("%032x", number);
    }

    public String[] getListAlgorithm() {
        return listAlgorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public static void main(String[] args) throws Exception {
        Hash hash = new Hash();
        System.out.println(hash.hash("heloo").equals("948fab37aeaa563cfa7f009d836ead8d"));
        System.out.println(hash.hashFile("D:\\storage\\taiLieu\\logo trường\\logo.png").equals("a32f183353abc91feeb9cdd95bf5bd48"));

    }
}
