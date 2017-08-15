package com.example.neil.vp_fitness_frontend.utils;

import com.example.neil.vp_fitness_frontend.Authentication;

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
    private static final String INFO_CREATION = "/apis/users/info";
    private static final String AUTHORIZATION = "/oauth/token";

    /** Configurations for OkHttp and other connection elements */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
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
     * @param url The URL to post to
     * @param json The JSON object to obtain
     * @param beared True if this should include a client bearer
     * @param authed True if this should include an authenticated header
     * @return The response from the server, as a string
     * @throws IOException
     */
    private static String post(String url, String json, boolean beared, boolean authed) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder request = new Request.Builder()
                .url(url)
                .post(body);

        if (beared) {
            request.addHeader("Basic", Authentication.PRIVATE_KEY_HEADER);
        }
        if (authed) {
            request.addHeader("Authorization", Authentication.getAccessToken());
        }

        Response response = client.newCall(request.build()).execute();
        return response.body().string();
    }


    // PRAGMA - Actual API-specific methods ------------------------------------

    public static JSONObject postAccountCreation(JSONObject data) throws IOException, JSONException {

        // Post the account information, and return the results
        String response = post(ROOT_URL + ACCOUNT_CREATION, data.toString(), false, false);
        return new JSONObject(response);
    }

    public static JSONObject postUserInfo(JSONObject data) throws IOException, JSONException {
        String response = post(ROOT_URL + INFO_CREATION, data.toString(), false, true);
        return new JSONObject(response);
    }

    public static JSONObject postAuthentication(String username, String password) throws IOException, JSONException {
        JSONObject authObject = new JSONObject();
        authObject
                .put("username", username)
                .put("password", password)
                .put("grant_type", Authentication.GRANT_TYPE);
        String response = post(ROOT_URL + AUTHORIZATION, authObject.toString(), true, false);
        JSONObject jsonResponse = new JSONObject(response);
        Authentication.saveAccessToken(jsonResponse.getString("access_token"));

        return jsonResponse;
    }

}
