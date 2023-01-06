package sirs.group35.ala.util;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;
import java.util.Enumeration;


public class Signer {

    private String keyStorePath;

    private String keyStorePassword;

    private String keyStoreType;

    public Signer(String keyStorePath, String keyStorePassword, String keyStoreType) {
        this.keyStorePath = keyStorePath;
        this.keyStorePassword = keyStorePassword;
        this.keyStoreType = keyStoreType;
    }

    private KeyStore getKeyStore() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        FileInputStream inputStream = new FileInputStream(keyStorePath);
        keyStore.load(inputStream, keyStorePassword.toCharArray());
        return keyStore;
    }

    private PrivateKey getPrivateKey() throws Exception {
        KeyStore keyStore = getKeyStore();
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            System.out.println(alias);
        }
        PrivateKey privateKey = (PrivateKey) keyStore.getKey("spring", keyStorePassword.toCharArray());
        return privateKey;
    }

    public byte[] signDocument(byte[] timestamp, byte[] file) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(timestamp);
        md.update(file);
        System.out.println("TIMESTAMP: " + timestamp);
        System.out.println("FILE: " + file);

        byte[] hash = md.digest();
        System.out.println("HASH: " + Base64.getEncoder().encodeToString(hash));
        System.out.println("HASH SIZE: " + hash.length);

        Signature signature = Signature.getInstance("SHA256withRSA");
        PrivateKey privateKey = getPrivateKey();
        signature.initSign(privateKey);
        signature.update(hash);
        byte[] signedHash = signature.sign();
        System.out.println("SIGNED HASH: " + signedHash + "\n\n\n\n");

        return signedHash;
    }

}