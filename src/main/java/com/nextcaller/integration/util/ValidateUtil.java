package com.nextcaller.integration.util;

import com.nextcaller.integration.exceptions.ValidateException;

public class ValidateUtil {

    public static final int DEFAULT_PHONE_LENGTH = 10;

    public static boolean validatePhone(String phone) throws ValidateException {

        if (phone == null || phone.isEmpty()) {
            throw new ValidateException("Invalid phone number: " + phone + ". Phone cannot be blank.");
        } else if (phone.length() != DEFAULT_PHONE_LENGTH) {
            throw new ValidateException("Invalid phone number: " + phone + ". Phone should has length "
                    + DEFAULT_PHONE_LENGTH + ".");
        } else if (!phone.matches("\\d+")) {
            throw new ValidateException("Invalid phone number: " + phone + ". Phone should consists of only digits.");
        }

        return true;
    }

}
