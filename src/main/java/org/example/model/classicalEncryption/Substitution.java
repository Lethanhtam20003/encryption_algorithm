package org.example.model.classicalEncryption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Substitution {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String key;
    public Substitution() {}
    public String genKey(){
        ArrayList<Character> chars = new ArrayList<>();
        for (char c : alphabet.toCharArray()) {
            chars.add(c);
        }
//        ho tro xao tron cac phan tu trong mot mang char
        Collections.shuffle(chars, new Random());
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }
    public boolean loadKey(String key){
        if(checkKey(key)){
            this.key = key;
            return true;
        }
        return false;
    }
    public String encrypt(String plantext){
        ArrayList<Integer> listIndex = listIndexCharUppercase(plantext);
        StringBuilder res = new StringBuilder();
        plantext = plantext.toUpperCase();
        for(char c : plantext.toCharArray()){
            int index = alphabet.indexOf(c);
            if(index != -1){
                res.append(key.charAt(index));
            }else
                res.append(c);
        }
        return uppercaseStringByIndex(listIndex,res.toString());
    }
    public String decrypt(String plantext){
        ArrayList<Integer> listIndex = listIndexCharUppercase(plantext);
        StringBuilder res = new StringBuilder();
        plantext = plantext.toUpperCase();
        for(char c : plantext.toCharArray()){
            int index = key.indexOf(c);
            if(index != -1){
                res.append(alphabet.charAt(index));
            }else
                res.append(c);
        }
        return uppercaseStringByIndex(listIndex,res.toString());
    }

    private boolean checkKey(String key) {
        // key ko dc rong, co cung do dai voi bang chu cai
        if(key == null || key.length() != 26){
            return false;
        }
//        moi mot ky tu ben key doi phai co ben bang chu cai
        for(char c : key.toCharArray()){
            if(alphabet.indexOf(c) != -1){
                return true;
            }
        }
        return true;
    }
    public ArrayList<Integer> listIndexCharUppercase(String plantext){
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < plantext.length(); i++){
            char c = plantext.charAt(i);
            if(Character.isUpperCase(c)){
                res.add(i);
            }
        }
        return res;
    }
    public String uppercaseStringByIndex(ArrayList<Integer> list,String plantext){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < plantext.length(); i++){
            char c = plantext.charAt(i);
            if(list.contains(i)){
                res.append(Character.toUpperCase(c));
            }else
                res.append(Character.toLowerCase(c));
        }
        return res.toString();
    }
    public static void main(String[] args) {
        Substitution sub = new Substitution();
        String key = sub.genKey();
        sub.loadKey(key);
        System.out.println("key:"+key);

        System.out.println("ma hoa 'hai con cho':"+sub.encrypt("Hai Co-=n cho"));
        System.out.println("giai ma:"+sub.decrypt(sub.encrypt("Hai Con-= cho")));
    }
}
