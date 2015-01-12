package com.nextcaller.integration.util;

import com.nextcaller.integration.exceptions.ValidateException;

public class ValidateUtil {

    public static final int DEFAULT_PHONE_LENGTH = 10;
    public static final int DEFAULT_PROFILE_ID_LENGTH = 30;
    
    private static final String USERNAME_PROFILE_ID_REGEX = "^[a-zA-Z0-9]*$";

    /**
     * @param phone phone number
     * @return true if phone number is valid
     * @throws ValidateException
     */
    public static boolean validatePhone(String phone) throws ValidateException {
        if (phone == null || phone.isEmpty()) {
            throw new ValidateException("Invalid phone number. Phone cannot be blank.");
        } else if (phone.length() != DEFAULT_PHONE_LENGTH) {
            throw new ValidateException("Invalid phone number: " + phone + ". Phone should has length "
                    + DEFAULT_PHONE_LENGTH + ".");
        } else if (!phone.matches("\\d+")) {
            throw new ValidateException("Invalid phone number: " + phone + ". Phone should consists of only digits.");
        }

        return true;
    }

    /**
     * @param profileId Profile identifier
     * @return true if phone number is valid
     * @throws ValidateException
     */
    public static boolean validateProfileId(String profileId) throws ValidateException {
        if (profileId == null || profileId.isEmpty()) {
            throw new ValidateException("Invalid profile id. Profile id cannot be blank.");
        } else if (profileId.length() != DEFAULT_PROFILE_ID_LENGTH) {
            throw new ValidateException("Invalid profile id: " + profileId + ". Profile id should has length "
                    + DEFAULT_PROFILE_ID_LENGTH + ".");
        } else if (!profileId.matches(USERNAME_PROFILE_ID_REGEX)) {
            throw new ValidateException("Invalid profile id: " + profileId + ". Profile id should consists of only digits and letters.");
        }
        
        return true;
    }

    /**
     * @param username Platform username
     * @return true if phone number is valid
     * @throws ValidateException
     */
    public static boolean validatePlatformUsername(String username) throws ValidateException {
        if (username == null || username.isEmpty()) {
            throw new ValidateException("Invalid Platform Username. Platform Username cannot be blank.");
        } else if (!username.matches(USERNAME_PROFILE_ID_REGEX)) {
            throw new ValidateException("Invalid Platform Username: " + username + ". Platform Username should consists of only digits and letters.");
        }

        return true;
    }

}
