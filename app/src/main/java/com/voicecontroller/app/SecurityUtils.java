package com.voicecontroller.app;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class SecurityUtils {
    private static final String PASSWORD = "bluetooth_voice_controller_secure_2024";
    private static final String ALGORITHM = "AES";
    
    public static String encrypt(String data) throws Exception {
        byte[] key = generateKey();
        SecretKeySpec secretKey = new SecretKeySpec(key, 0, key.length, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.encodeToString(encryptedData, Base64.DEFAULT);
    }
    
    public static String decrypt(String encryptedData) throws Exception {
        byte[] key = generateKey();
        SecretKeySpec secretKey = new SecretKeySpec(key, 0, key.length, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.decode(encryptedData, Base64.DEFAULT);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
    
    private static byte[] generateKey() throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(PASSWORD.getBytes());
    }
}
