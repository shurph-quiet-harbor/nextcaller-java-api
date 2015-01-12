package com.nextcaller.integration.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class VersionProvider {

    private static final Logger logger = LoggerFactory.getLogger(VersionProvider.class);

    private static final VersionProvider INSTANCE = new VersionProvider();
    private String version;

    private VersionProvider() {
        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("pom-attributes");
            version = rb.getString("application.version");
        } catch (MissingResourceException e) {
            logger.warn("Resource bundle 'primefaces-extensions' was not found or error while reading current version.");
        }
    }

    public static String getVersion() {
        return INSTANCE.version;
    }

}
