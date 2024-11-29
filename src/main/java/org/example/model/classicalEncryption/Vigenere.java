package org.example.model.classicalEncryption;

import java.util.Random;
import java.util.Scanner;

public class Vigenere{
    private String key;
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public  String genKey(int num) {
        StringBuilder newKey = new StringBuilder();
        Random rand = new Random();
        for(int i = 0; i < num; i++) {
            char a = alphabet.charAt(rand.nextInt(alphabet.length()));
            newKey.append(a);
        }
        return newKey.toString(); // Lấy độ dài phù hợp
    }


    public  boolean loadKey(String key) {
        if(key == null || key.isEmpty()){
            return false;
        }
        for(int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if(!Character.isLetter(ch)) {
                return false;
            }

        }
        this.key = key;
        return true;
    }


    // Hàm mã hóa Vigenère
    public  String encrypt(String text) {
        if(text.length()<key.length()){
            throw new IllegalArgumentException("Text length should be greater than key length");
        }
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int x = alphabet.indexOf(text.charAt(i));
            int k = alphabet.indexOf(key.charAt(i%key.length()));
            if (x == -1) { // Nếu ký tự không thuộc alphabet, giữ nguyên
                cipherText.append(text.charAt(i));
            } else {
                char c = alphabet.charAt((x + k) % alphabet.length());
                cipherText.append(c);
            }
        }
        return cipherText.toString();
    }

    // Hàm giải mã Vigenère
    public  String decrypt(String cipherText) {
        if(cipherText.length()<key.length()){
            throw new IllegalArgumentException("Text length should be greater than key length");
        }
        StringBuilder originalText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            int y = alphabet.indexOf(cipherText.charAt(i));
            int k = alphabet.indexOf(key.charAt(i%key.length()));
            if (y == -1) { // Nếu ký tự không thuộc alphabet, giữ nguyên
                originalText.append(cipherText.charAt(i));
            } else {
                char c = alphabet.charAt((y - k + alphabet.length()) % alphabet.length());
                originalText.append(c);
            }
        }
        return originalText.toString();
    }

    public static void main(String[] args) {
        Vigenere vigenere = new Vigenere();
        String key = vigenere.genKey(10);
        System.out.println("key: "+ key);
        vigenere.loadKey("CIPHER");
        String cipherText = vigenere.encrypt("this crypto system is not secure");
        System.out.println("cipherText: "+ cipherText);
        String decryptedText = vigenere.decrypt(cipherText);
        System.out.println("decryptedText: "+ decryptedText);
    }

    public String getKey() {
        return this.key;
    }
}
