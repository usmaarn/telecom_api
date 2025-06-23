package com.telzz.sub.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class HashService {
    public String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            return byteArrayToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String byteArrayToHex(final byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public boolean verifyHash(String input, String expectedHash) {
        String inputHash = sha256(input);
        return inputHash.equalsIgnoreCase(expectedHash); // Case-insensitive match
    }
}
