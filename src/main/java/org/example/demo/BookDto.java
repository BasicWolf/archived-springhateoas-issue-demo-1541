package org.example.demo;

import org.springframework.hateoas.RepresentationModel;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class BookDto extends RepresentationModel<BookDto> {
    public PublicKey publicKey;
    public String name;

    public BookDto(String name) {
        this.publicKey = randomPublicKey();
        this.name = name;
    }

    private PublicKey randomPublicKey() {
        try {
            var keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();
            return keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
