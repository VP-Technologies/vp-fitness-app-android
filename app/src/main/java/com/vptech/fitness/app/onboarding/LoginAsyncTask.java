package com.vptech.fitness.app.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vptech.fitness.app.MainActivity;
import com.vptech.fitness.app.R;
import com.vptech.fitness.app.utils.API;
import com.vptech.fitness.app.utils.Authentication;
import com.vptech.fitness.app.utils.ViewHelper;

import org.json.JSONObject;

/**
 * An asynchronous task that can be used to authenticate the user and save their credentials
 * @author Aaron Vontell
 */
public class LoginAsyncTask extends AsyncTask<Void, Void, MaterialDialog.Builder> {

    private Context context;
    private String username;
    private String password;

    /**
     * Creates an asynchronous task for posting information to the server for logging in
     * @param context The calling activity or context
     */
    public LoginAsyncTask(Context context, String username, String password) {
        super();
        this.context = context;
        this.username = username;
        this.password = password;
    }

    @Override
    protected MaterialDialog.Builder doInBackground(Void ... voids) {

        try {

            // Authenticate the user. The resulting access token should be saved!
            // postAuthentication will automatically save the access token for us; ain't that nifty?
            JSONObject authResult = API.postAuthentication(context, username, password);

            // If there was an error during authentication, let's tell them
            if (authResult.has("error")) {

                return ViewHelper.createDialog(
                        context, context.getString(R.string.login_error_title),
                        context.getString(R.string.login_error),
                        context.getString(R.string.onboard_error_pos), null, null, null);

            }

        }
        catch (Exception e) {

            // Show an error dialog if we don't know what happened
            return ViewHelper.createDialog(
                    context, context.getString(R.string.login_error_title),
                    context.getString(R.string.login_error),
                    context.getString(R.string.onboard_error_pos), null, null, null);

        }

        // If we made it all the way through, then hooray! We can load up the
        // main screen!
        return null;

    }

    @Override
    protected void onPostExecute(MaterialDialog.Builder builder) {
        if (builder != null) {
            builder.build().show();
        } else {
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            ((Activity) context).finish();
            context.startActivity(mainActivityIntent);
        }

    }
}