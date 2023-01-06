package sirs.group35.ala.util;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sirs.group35.ala.model.FileDB;


public class Auditor {

    private String publicKey;

    public Auditor(String publicKey) {
        this.publicKey = publicKey;
    }

    public boolean validateDocument(FileDB file, String base64PublicKey) throws Exception {
        // Decode the base64-encoded public key
        byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        
        // Get the public key from the key specification
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
    
        // Decrypt the signed hash with the public key
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedHash = cipher.doFinal(Base64.getDecoder().decode(file.getSignedHash()));
    
        // Hash the file content and timestamp with SHA-256
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(file.getData());
        messageDigest.update(ByteBuffer.allocate(Long.BYTES).putLong(file.getTimestamp()).array());
        byte[] generatedHash = messageDigest.digest();
    
        // Compare the decrypted hash and the generated hash
        return Arrays.equals(decryptedHash, generatedHash);
    }
    
    
}
