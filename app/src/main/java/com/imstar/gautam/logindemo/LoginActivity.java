package com.imstar.gautam.logindemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.imstar.gautam.logindemo.Helper.Helper;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG=LoginActivity.class.getSimpleName();
    private EditText emailInput;
    private EditText passwordInput;
    private TextView signupText;
    private TextView loginError;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        mAuth=((FirebaseApplication)getApplication()).getFirebaseAuth();
        ((FirebaseApplication)getApplication()).chheckUserLogin(LoginActivity.this);

        loginError=findViewById(R.id.logn_error);
        emailInput=findViewById(R.id.email);
        passwordInput=findViewById(R.id.password);
        signupText=findViewById(R.id.register);

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signupIntent);
            }
        });

        final Button loginButton=findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail=emailInput.getText().toString();
                String enteredPassword=passwordInput.getText().toString();

                if(TextUtils.isEmpty(enteredEmail) || TextUtils.isEmpty(enteredPassword)){
                    Helper.displayMessageToast(LoginActivity.this, "Log in fields must be filled");
                    return;
                }
                if(!Helper.isValidEmail(enteredEmail)){
                    Helper.displayMessageToast(LoginActivity.this, "invalidate email entered");
                    return;
                }
                ((FirebaseApplication)getApplication()).loginAUser(LoginActivity.class, enteredEmail, enteredPassword, loginError);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if ((FirebaseApplication)getApplication()).mAuthListener !=null{

        }
    }
}
