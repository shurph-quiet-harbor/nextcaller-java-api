package com.nextcaller.integration.util;

import com.nextcaller.integration.exceptions.ValidateException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ValidateUtil {

    public static final int DEFAULT_PHONE_LENGTH = 10;
    public static final int DEFAULT_PROFILE_ID_LENGTH = 30;
    
    private static final String USERNAME_PROFILE_ID_REGEX = "^[a-zA-Z0-9]*$";
    private static final int ZIP_CODE_LENGTH = 5;
    private static final int EXTENDED_ZIP_CODE_LENGTH = 4;
    private static final List<String> MANDATORY_ADDRES_NAME_FIELDS =
            Arrays.asList("first_name", "last_name", "address");
    private static final List<String> ALLOWED_ADDRES_NAME_FIELDS =
            Arrays.asList(
                    "first_name", "last_name", "address", "city", "state",
                    "zip_code", "middle_name", "apt_suite", "extended_zip");

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
     * @param addressData number
     * @return true if phone number is valid
     * @throws ValidateException
     */
    public static boolean validateAddressName(Map<String, String> addressData) throws ValidateException {
        if (addressData == null || addressData.isEmpty()) {
            throw new ValidateException("Invalid address data. Address data cannot be blank.");
        }
        for (Map.Entry<String, String> entry : addressData.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            if (!ALLOWED_ADDRES_NAME_FIELDS.contains(key)) {
                throw new ValidateException(String.format(
                        "Invalid address, name field: %s. Allowed fields: %s.", key, PrepareUrlUtil.join(ALLOWED_ADDRES_NAME_FIELDS, ", ")));
            }
            if (value == null || value.isEmpty()) {
                throw new ValidateException(String.format(
                        "Invalid address, name value \"%s\" for key %s.", value, key));
            }
            if (key.equals("zip_code") && (!value.matches("\\d+") || value.length() != ZIP_CODE_LENGTH)) {
                throw new ValidateException(String.format("Invalid zip code: %s", value));
            }
            if (key.equals("extended_zip") && (!value.matches("\\d+") || value.length() != EXTENDED_ZIP_CODE_LENGTH)) {
                throw new ValidateException(String.format("Invalid zip code: %s", value));
            }
        }
        for (String field: MANDATORY_ADDRES_NAME_FIELDS) {
            if (!addressData.containsKey(field)) {
                throw new ValidateException(String.format(
                        "Not all mandatory fields are supplied: %s.", PrepareUrlUtil.join(MANDATORY_ADDRES_NAME_FIELDS, ", ")));
            }
        }
        final String city = addressData.getOrDefault("city", "");
        final String state = addressData.getOrDefault("state", "");
        final String zip_code = addressData.getOrDefault("zip_code", "");
        if (!(!city.isEmpty() && !state.isEmpty() || !zip_code.isEmpty())) {
            throw new ValidateException("Either pair of city and state fields or zip_code field should be supplied");
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
