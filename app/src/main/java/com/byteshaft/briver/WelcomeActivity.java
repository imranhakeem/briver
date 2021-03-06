package com.byteshaft.briver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byteshaft.briver.utils.AppGlobals;

public class WelcomeActivity extends Activity implements View.OnClickListener {

    ImageView ivWelcomeLogoMain;
    LinearLayout llWelcomeLogin;
    Button btnLogin;
    Button btnRegister;
    TextView tvForgotPassword;
    EditText etLoginEmail;
    EditText etLoginPassword;
    String sLoginEmail;
    String sLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivWelcomeLogoMain = (ImageView) findViewById(R.id.iv_welcome_logo_main);
        llWelcomeLogin = (LinearLayout) findViewById(R.id.ll_welcome_login);
        etLoginEmail = (EditText) findViewById(R.id.et_login_email);
        etLoginPassword = (EditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
        tvForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        tvForgotPassword.setOnClickListener(this);
        llWelcomeLogin.setVisibility(View.GONE);

        final Animation animMainLogoFading = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.anim_welcome_logo_fading);
        final Animation animMainLogoTransition = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.anim_welcome_logo_transition);
        final Animation animLayoutLoginFadeIn = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.anim_login_layout);
        final Animation animMainLogoFadeIn = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.anim_welcome_logo_fade_in);
        final Animation animMainLogoFadeOut = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.anim_welcome_logo_fade_out);

        ivWelcomeLogoMain.startAnimation(animMainLogoFading);

        etLoginEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("Email", " Focus Got");
                    if (!etLoginPassword.hasFocus()) {
                        ivWelcomeLogoMain.startAnimation(animMainLogoFadeOut);
                    }
                } else {
                    Log.i("Email", " Focus Lost");
                    if (!etLoginPassword.hasFocus()) {
                        ivWelcomeLogoMain.startAnimation(animMainLogoFadeIn);
                    }
                }
            }
        });

        etLoginPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("Password", " Focus Got");
                    if (!etLoginEmail.hasFocus()) {
                        ivWelcomeLogoMain.startAnimation(animMainLogoFadeOut);
                        Log.i("Password", " Focus Got");
                    }
                } else {
                    Log.i("Password", " Focus Lost");
                    if (!etLoginEmail.hasFocus()) {
                        ivWelcomeLogoMain.startAnimation(animMainLogoFadeIn);
                    }
                }
            }
        });

        animMainLogoTransition.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llWelcomeLogin.setVisibility(View.VISIBLE);
                llWelcomeLogin.startAnimation(animLayoutLoginFadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animMainLogoFading.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivWelcomeLogoMain.startAnimation(animMainLogoTransition);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_login:

                sLoginEmail = etLoginEmail.getText().toString();
                sLoginPassword = etLoginPassword.getText().toString();

                if (validateLoginInput()) {
                    Log.i("Validate: ", "Yes");
                } else {
                    Log.i("Validate: ", "No");
                }

                break;

            case R.id.btn_register:

        Intent i = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_enter_register_activity
                , R.anim.anim_exit_login_activity);

                break;

            case R.id.tv_forgot_password:

                break;
            default:

                break;
        }
    }


    public boolean validateLoginInput() {
        boolean valid = true;
        if (sLoginEmail.trim().isEmpty()) {
            etLoginEmail.setError("Empty");
            valid = false;
        } else if (!sLoginEmail.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(sLoginEmail).matches()) {
            etLoginEmail.setError("Invalid E-Mail");
            valid = false;
        } else {
            etLoginEmail.setError(null);
        }

        if (sLoginPassword.trim().isEmpty() || sLoginPassword.length() < 4) {
            etLoginPassword.setError("Minimum 4 Characters");
            valid = false;
        } else {
            etLoginPassword.setError(null);
        }
        return valid;
    }

    public void onLoginSuccess() {
        AppGlobals.setLoggedIn(true);
//        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void onLoginFailed(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

}
