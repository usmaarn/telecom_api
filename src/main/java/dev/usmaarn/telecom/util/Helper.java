package dev.usmaarn.telecom.util;

import java.security.SecureRandom;

public class Helper {
    public static String generateRandomString(int length){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();

        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            int index = random.nextInt(CHARACTERS.length());
            builder.append(CHARACTERS.charAt(index));
        }
        return builder.toString();
    }
}
