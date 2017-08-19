package com.example.neil.vp_fitness_frontend.utils;

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
     * @throws IOException
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

    public static JSONObject postValidateUsername(Context context, String username) throws IOException, JSONException {

        // Post the account information, and return the results
        JSONObject validationObject = new JSONObject();
        validationObject.put("new_username", username);
        String response = post(context, ROOT_URL + CHECK_USERNAME, validationObject.toString(), JSON, false, false);
        return new JSONObject(response);
    }

    public static JSONObject postAccountCreation(Context context, JSONObject data) throws IOException, JSONException {

        // Post the account information, and return the results
        String response = post(context, ROOT_URL + ACCOUNT_CREATION, data.toString(), JSON, false, false);
        return new JSONObject(response);
    }

    public static JSONObject postUserInfo(Context context, JSONObject data) throws IOException, JSONException {
        String response = post(context, ROOT_URL + INFO_CREATION, data.toString(), JSON, false, true);
        Log.e("USER", response);
        return new JSONObject(response);
    }

    public static JSONObject postAuthentication(Context context, String username, String password) throws IOException, JSONException {
        StringBuilder request = new StringBuilder();
        request.append("username=");
        request.append(username).append("&");
        request.append("password=");
        request.append(password).append("&");
        request.append("grant_type=");
        request.append(Authentication.GRANT_TYPE);
        String data = request.toString();
        String response = post(context, ROOT_URL + AUTHORIZATION, data, WWWE, true, false);

        JSONObject jsonResponse = new JSONObject(response);
        Authentication.saveAccessToken(context, jsonResponse.getString("access_token"));

        return jsonResponse;
    }

}
