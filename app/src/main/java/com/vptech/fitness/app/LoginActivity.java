package com.vptech.fitness.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.vptech.fitness.app.onboarding.LoginAsyncTask;
import com.vptech.fitness.app.utils.ViewHelper;

/**
 * An activity that allows the user to login into their VP fitness account
 * @author Aaron Vontell
 */
public class LoginActivity extends AppCompatActivity {

    private MaterialEditText usernameView;
    private MaterialEditText passwordView;
    private LinearLayout loginButton;
    private LinearLayout registerButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Bind and prepare each view
        this.usernameView = findViewById(R.id.login_username_view);
        this.passwordView = findViewById(R.id.login_password_view);
        this.loginButton = findViewById(R.id.login_button_view);
        this.registerButton = findViewById(R.id.login_no_account_view);
        this.context = this;

        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // We know that the username and password need to be of a certain length, so we can
                // do some pre-validation
                String username = usernameView.getEditableText().toString();
                String password = passwordView.getEditableText().toString();

                if (username.length() < 3 || password.length() < 7) {
                    ViewHelper.createDialog(
                            context, context.getString(R.string.login_error_title),
                            context.getString(R.string.login_error),
                            context.getString(R.string.onboard_error_pos), null, null, null).build().show();
                } else {
                    new LoginAsyncTask(context, username, password).execute();
                }

            }
        });

        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivityIntent = new Intent(context, OnboardingActivity.class);
                ((Activity) context).finish();
                context.startActivity(registerActivityIntent);
            }
        });

    }
}
