package com.vptech.fitness.app.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vptech.fitness.app.login.LoginActivity;
import com.vptech.fitness.app.MainActivity;
import com.vptech.fitness.app.R;
import com.vptech.fitness.app.utils.API;
import com.vptech.fitness.app.utils.Authentication;
import com.vptech.fitness.app.utils.ViewHelper;
import org.json.JSONObject;

/**
 * An asynchronous task for posting information from the onboarding flow.
 * @author Aaron Vontell
 */
public class AccountCreationAsyncTask extends AsyncTask<JSONObject, Void, MaterialDialog.Builder> {

    private Context context;

    /**
     * Creates an asynchronous task for posting information from the onboarding flow.
     * @param context The calling activity or context
     */
    public AccountCreationAsyncTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected MaterialDialog.Builder doInBackground(JSONObject... objs) {

        try {

            // First, check that the username is valid and not taken
            JSONObject usernameValidation = API.postValidateUsername(context, objs[0].getString("username"));

            // Show an error if that username is taken
            if (usernameValidation.get("status").equals("failure")) {
                return ViewHelper.createDialog(
                        context, context.getString(R.string.onboard_error_title),
                        usernameValidation.getString("message"),
                        context.getString(R.string.onboard_error_pos), null, null, null);
            }

            // Then create the account
            JSONObject accountResult = API.postAccountCreation(context, objs[0]);
            Authentication.saveOnboardingFinished(context);

            // If there is an error from account creation, we should tell them
            if (accountResult.get("status").equals("failure")) {
                return ViewHelper.createDialog(
                        context, context.getString(R.string.onboard_error_title),
                        accountResult.getString("message"),
                        context.getString(R.string.onboard_error_pos), null, null, null);
            }

            // Now authenticate so we can create the user info. The resulting
            // access token should be saved! postAuthentication will automatically
            // save the access token for us; ain't that nifty?
            JSONObject authResult = API.postAuthentication(context, objs[0].getString("username"), objs[0].getString("password"));

            // If there was an error during authentication, let's tell them
            if (authResult.has("error")) {

                MaterialDialog.SingleButtonCallback callback = new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent loginActivityIntent = new Intent(context, LoginActivity.class);
                        ((Activity) context).finish();
                        context.startActivity(loginActivityIntent);
                    }
                };

                return ViewHelper.createDialog(
                        context, context.getString(R.string.onboard_error_title),
                        context.getString(R.string.onboard_error_auth),
                        context.getString(R.string.onboard_error_pos), null, callback, null);

            }

            // Then create user information
            JSONObject userInfoResult = API.postUserInfo(context, objs[1]);

            // If there was an error during the userInfo portion... tell em
            if (userInfoResult.get("status").equals("failure")) {
                return ViewHelper.createDialog(
                        context, context.getString(R.string.onboard_error_title),
                        userInfoResult.getString("message"),
                        context.getString(R.string.onboard_error_pos), null, null, null);
            }

        }
        catch (Exception e) {

            // Show an error dialog if we don't know what happened
            return ViewHelper.createDialog(
                    context, context.getString(R.string.onboard_error_title),
                    context.getString(R.string.onboard_error_content) + " " + e.toString(),
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