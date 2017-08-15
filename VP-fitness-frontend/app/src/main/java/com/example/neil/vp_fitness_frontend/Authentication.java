package com.example.neil.vp_fitness_frontend;

/**
 * A class used to authenticate the user and hold user authentication info
 * @author Aaron Vontell
 */
public class Authentication {

    public static final String PRIVATE_KEY_HEADER =
            "Basic dnBmaXR3ZWJhcHA6SkJVWTlWRTY5MjQzQllDOTAyNDM4N0hHVlkzQVFGSw==";
    public static final String GRANT_TYPE = "password";
    public static final String ACCESS_TOKEN_KEY = "VP_ACCESS_TOKEN";

    public static String getAccessToken() {
        throw new RuntimeException("'getAccessToken' is not yet implemented");
    }

    public static void saveAccessToken(String accessToken) {
        throw new RuntimeException("'saveAccessToken' is not yet implemented");
    }

}
