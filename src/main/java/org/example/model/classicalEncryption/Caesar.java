package org.example.model.classicalEncryption;

public class Caesar {
    private int key;
   private String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String encryptString(String plantext){
        char[] arr = plantext.toUpperCase().toCharArray();
        StringBuilder res= new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            if(alphabet.indexOf(arr[i])!=-1){
                int index = (alphabet.indexOf(arr[i])+key)%26;
                res.append(alphabet.charAt(index));
            }else
                res.append(arr[i]);
        }
        return res.toString();
    }
    public String decryptString(String plantext){
        char[] arr = plantext.toUpperCase().toCharArray();
        StringBuilder res= new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            if(alphabet.indexOf(arr[i])!=-1){
                int index = (alphabet.indexOf(arr[i])-key)%26;
                res.append(alphabet.charAt(index));
            }else
                res.append(arr[i]);
        }
        return res.toString();
    }


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }


    public static void main(String[] args) {
        Caesar caesar = new Caesar();
        caesar.setKey(5);
        System.out.println(caesar.encryptString("helo= 1jjd"));
        System.out.println(caesar.decryptString(caesar.encryptString("helo= 1jjd")));
    }
}
