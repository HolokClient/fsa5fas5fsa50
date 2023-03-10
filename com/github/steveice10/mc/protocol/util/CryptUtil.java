/*
 * Decompiled with CFR 0.152.
 */
package com.github.steveice10.mc.protocol.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptUtil {
    private CryptUtil() {
    }

    public static SecretKey generateSharedKey() {
        try {
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(128);
            return gen.generateKey();
        }
        catch (NoSuchAlgorithmException e) {
            throw new Error("Failed to generate shared key.", e);
        }
    }

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024);
            return gen.generateKeyPair();
        }
        catch (NoSuchAlgorithmException e) {
            throw new Error("Failed to generate key pair.", e);
        }
    }

    public static PublicKey decodePublicKey(byte[] bytes) throws IOException {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
        }
        catch (GeneralSecurityException e) {
            throw new IOException("Could not decrypt public key.", e);
        }
    }

    public static SecretKey decryptSharedKey(PrivateKey privateKey, byte[] sharedKey) {
        return new SecretKeySpec(CryptUtil.decryptData(privateKey, sharedKey), "AES");
    }

    public static byte[] encryptData(Key key, byte[] data) {
        return CryptUtil.runEncryption(1, key, data);
    }

    public static byte[] decryptData(Key key, byte[] data) {
        return CryptUtil.runEncryption(2, key, data);
    }

    private static byte[] runEncryption(int mode, Key key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(mode, key);
            return cipher.doFinal(data);
        }
        catch (GeneralSecurityException e) {
            throw new Error("Failed to run encryption.", e);
        }
    }

    public static byte[] getServerIdHash(String serverId, PublicKey publicKey, SecretKey secretKey) {
        try {
            return CryptUtil.encrypt("SHA-1", serverId.getBytes("ISO_8859_1"), secretKey.getEncoded(), publicKey.getEncoded());
        }
        catch (UnsupportedEncodingException e) {
            throw new Error("Failed to generate server id hash.", e);
        }
    }

    private static byte[] encrypt(String encryption, byte[] ... data) {
        try {
            MessageDigest digest = MessageDigest.getInstance(encryption);
            byte[][] byArray = data;
            int n = data.length;
            int n2 = 0;
            while (n2 < n) {
                byte[] array = byArray[n2];
                digest.update(array);
                ++n2;
            }
            return digest.digest();
        }
        catch (NoSuchAlgorithmException e) {
            throw new Error("Failed to encrypt data.", e);
        }
    }
}

