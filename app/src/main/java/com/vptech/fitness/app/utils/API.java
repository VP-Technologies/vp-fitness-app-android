package com.vptech.fitness.app.utils;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A class for making connections to the server and sending / receiving
 * information. Note that this API will use the Authentication static class
 * to make authenticated requests if it needs to.
 * @author Aaron Vontell
 */
public class API {

    /** Endpoints */
    private static final String ROOT_URL = "https://vptech-fitness.herokuapp.com";
    private static final String ACCOUNT_CREATION = "/api/users";
    private static final String CHECK_USERNAME = "/api/validate";
    private static final String INFO_CREATION = "/apis/users/info";
    private static final String AUTHORIZATION = "/oauth/token";

    /** Configurations for OkHttp and other connection elements */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType WWWE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    /** A singleton HTTP client helper */
    private static OkHttpClient client = null;

    /**
     * Let's do some fancy "singleton" mechanisms for these connections
     * @return an OkHttpClient for use
     */
    private static OkHttpClient getOkHttpClient() {
        if(client == null) {
            client = new OkHttpClient();
        }
        return client;
    }

    /**
     * Posts the given json data to the given URL
     * @param context A calling context
     * @param url The URL to post to
     * @param data The JSON object to obtain
     * @param type The format that this data is being sent in
     * @param beared True if this should include a client bearer
     * @param authed True if this should include an authenticated header
     * @return The response from the server, as a string
     * @throws IOException An exception which may occur during the post response
     */
    private static String post(Context context, String url, String data, MediaType type, boolean beared, boolean authed) throws IOException {
        RequestBody body = RequestBody.create(type, data);
        Request.Builder request = new Request.Builder()
                .url(url)
                .post(body);

        if (beared) {
            request.addHeader("Authorization", Authentication.PRIVATE_KEY_HEADER);
        }

        if (authed) {
            Log.e("Auth", Authentication.getAccessToken(context));
            request.addHeader("Authorization", "Bearer " + Authentication.getAccessToken(context));
        }

        Response response = getOkHttpClient().newCall(request.build()).execute();
        return response.body().string();
    }

    // PRAGMA - Actual API-specific methods ------------------------------------

    /**
     * Makes an HTTP POST request with a username to determine if a user already
     * exists on the server.
     * @param context the context making the request
     * @param username the username being checked against
     * @return a JSONObject with a response as defined by the server API
     * @throws IOException An exception that can occur during the POST process
     * @throws JSONException An exception that can occur during the POST process
     */
    public static JSONObject postValidateUsername(Context context, String username) throws IOException, JSONException {
        JSONObject validationObject = new JSONObject();
        validationObject.put("new_username", username);
        String response = post(context, ROOT_URL + CHECK_USERNAME, validationObject.toString(), JSON, false, false);
        return new JSONObject(response);
    }

    /**
     * Makes an HTTP POST request with user data for account creation
     * @param context the context making the request
     * @param data the data being inserted as a user into the database
     * @return a JSONObject with a response as defined by the server API
     * @throws IOException An exception that can occur during the POST process
     * @throws JSONException An exception that can occur during the POST process
     */
    public static JSONObject postAccountCreation(Context context, JSONObject data) throws IOException, JSONException {
        String response = post(context, ROOT_URL + ACCOUNT_CREATION, data.toString(), JSON, false, false);
        return new JSONObject(response);
    }

    /**
     * Makes an HTTP POST request with user info for account modification
     * @param context the context making the request
     * @param data the data being inserted for a user into the database
     * @return a JSONObject with a response as defined by the server API
     * @throws IOException An exception that can occur during the POST process
     * @throws JSONException An exception that can occur during the POST process
     */
    public static JSONObject postUserInfo(Context context, JSONObject data) throws IOException, JSONException {
        String response = post(context, ROOT_URL + INFO_CREATION, data.toString(), JSON, false, true);
        return new JSONObject(response);
    }

    /**
     * Makes an HTTP POST request with a username and password for logging in.
     * The received access token is then saved for later use.
     * @param context the context making the request
     * @param username the desired username to log into
     * @param password the password for this user
     * @return a JSONObject with a response as defined by the server API
     * @throws IOException An exception that can occur during the POST process
     * @throws JSONException An exception that can occur during the POST process
     */
    public static JSONObject postAuthentication(Context context, String username, String password) throws IOException, JSONException {
        String data = "username=" +
                username + "&" +
                "password=" +
                password + "&" +
                "grant_type=" +
                Authentication.GRANT_TYPE;
        String response = post(context, ROOT_URL + AUTHORIZATION, data, WWWE, true, false);

        JSONObject jsonResponse = new JSONObject(response);
        Authentication.saveAccessToken(context, jsonResponse.getString("access_token"));

        return jsonResponse;
    }

}
