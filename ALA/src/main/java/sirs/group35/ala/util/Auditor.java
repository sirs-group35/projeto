package sirs.group35.ala.util;

import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


import sirs.group35.ala.model.FileDB;


public class Auditor {

    private String base64PublicKey;

    public Auditor(String base64PublicKey) {
        this.base64PublicKey = base64PublicKey;
    }

    public boolean validateDocument(FileDB file) throws Exception {
        // Decode the base64-encoded public key
        byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        
        // Get the public key from the key specification
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
    
        // Verify the signature with the public key
        Signature signature = Signature.getInstance("SHA256withRSA");
    
        // Hash the file content and timestamp with SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(ByteBuffer.allocate(Long.BYTES).putLong(file.getTimestamp()).array());
        md.update(file.getData());
        byte[] generatedHash = md.digest();

        // Compare the decrypted hash and the generated hash
        signature.initVerify(publicKey);
        signature.update(generatedHash);
        return signature.verify(Base64.getDecoder().decode(file.getSignedHash()));
    }
    
    
}

// oQY5W/x+viLBAvgJm+RmF4Vi71C+eYLPKG1fxccSVK0=

// N+QED14m8ukhkWf/yH9oN3jTsiHnMQYP4eVKs4n7lWWEQ61fVhjRCQsG6bKqXbM6ix7PQxONjfSFU2Kb2NOBAlm3AX0cv7+CBv/Hym+nwjp7e+2TCPVgln74GA7WRn711TOHLSPE3MYYfH6XWMMXm+f9Fw7l44wc2D3m47ZU+9p57Xc1SZuhSc68xz4n0n2uMpEFn7PwfxPv80GjExp+qI4dbHWPP/oPXTfKgKjSRYmpyRCA/Xa/UjtjgItwxvX7jCZyIW18T0CJb/NUkhMNFsshh7Gw3OnbtRBv20dNxXMWySlKuqN1nPHqaCD1CxNEhTzATOf/hV+VlhTkh5dhVw==


