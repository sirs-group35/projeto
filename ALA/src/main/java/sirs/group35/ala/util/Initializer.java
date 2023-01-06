package sirs.group35.ala.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    private String keyStorePath;
    private String keyStorePassword;
    private String keyStoreType;

    public Initializer(
      @Value("${server.ssl.key-store}") String keyStorePath,
      @Value("${server.ssl.key-store-password}") String keyStorePassword,
      @Value("${server.ssl.key-store-type}") String keyStoreType) {
 
        this.keyStorePath = keyStorePath;
        this.keyStorePassword = keyStorePassword;
        this.keyStoreType = keyStoreType;
    }

    public Signer initSigner() {
        return new Signer(
          this.keyStorePath, this.keyStorePassword, this.keyStoreType);
    }
}