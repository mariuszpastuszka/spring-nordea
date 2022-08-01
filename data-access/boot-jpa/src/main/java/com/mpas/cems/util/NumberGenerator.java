
package com.mpas.cems.util;

import java.util.Random;


public final class NumberGenerator {
    private static final Random RAND = new Random();
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";

    public static String getBadgeNumber() {
        final var sb = new StringBuilder();
        sb.append(randomUppercase()).append(randomUppercase());
        for (int i = 0; i < 6; ++i) {
            sb.append(randomDigit());
        }
        return sb.toString();
    }

    public static String getPassword(){
        final var sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            sb.append(randomCharacter());
        }
        return sb.toString();
    }

    public static String getCaseNumber() {
        final var sb = new StringBuilder();
        sb.append(randomUppercase()).append(randomUppercase());
        for (int i = 0; i < 8; ++i) {
            sb.append(randomDigit());
        }
        return sb.toString();
    }

    public static String getEvidenceNumber(){
        final var sb = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            sb.append(randomUppercase());
        }
        for (int i = 0; i < 16; ++i) {
            sb.append(randomDigit());
        }
        return sb.toString();
    }

    private static Character randomUppercase() {
        return UPPER.charAt(RAND.nextInt(UPPER.length() - 1));
    }

    private static Character randomDigit() {
        return DIGITS.charAt(RAND.nextInt(DIGITS.length() - 1));
    }

    private static Character randomCharacter() {
        final var all = UPPER.concat(UPPER.toLowerCase()).concat(DIGITS);
        return all.charAt(RAND.nextInt(all.length() - 1));
    }

    private NumberGenerator() {
        // prevent initialization fo this class
    }
}
