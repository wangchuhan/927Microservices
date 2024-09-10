package com.scu927.config;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author Chuhan
 * @date 2024/9/11
 */

public class KeyGeneratorUtil {

    public static void generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);  // 生成2048位的RSA密钥
        KeyPair keyPair = keyPairGen.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();


        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        System.out.println("Private Key: " + privateKeyString);
        System.out.println("Public Key: " + publicKeyString);
    }



    public static void main(String[] args) throws Exception {
        // create a 256byte HmacSHA256 private key
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256);  // 256 bits for HS256
        SecretKey secretKey = keyGen.generateKey();

        // transfer private key to Base64  String
        String base64EncodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Base64 Encoded Secret Key: " + base64EncodedKey);
    }
}