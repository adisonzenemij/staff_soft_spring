package com.apps.staff_software_spring.util;

import java.util.Random;

public class RandomUtil {
    public static String randomName(Random random) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int nameLength = 5 + random.nextInt(10); // Random length between 5 and 14
        StringBuilder sb = new StringBuilder(nameLength);

        for (int i = 0; i < nameLength; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        return sb.toString();
    }
}
