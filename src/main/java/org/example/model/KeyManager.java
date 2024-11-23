package org.example.model;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class KeyManager {
    private static KeyManager instance;
    public KeyManager() {}
    public static KeyManager getInstance() {
        if(instance == null) {
            instance = new KeyManager();
        }
        return instance;
    }
    public String SecretKeyToString(SecretKey keyTest) {
        return Base64.getEncoder().encodeToString(keyTest.getEncoded());
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


}
