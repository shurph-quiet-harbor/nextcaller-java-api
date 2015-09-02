package com.nextcaller.integration.util;

public class CleanUtil {
    /**
     * Clean phone string
     *
     * @param phone phone string with garbage characters
     * @return cleaned phone string
     */
    public static String cleanPhone(String phone) {
        return phone.replaceAll("[^0-9]", "");
    }
}
