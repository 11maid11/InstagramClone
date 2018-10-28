package com.maid.instagramclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText userLogin, passLogin, userSignup, passSignup;
    private Button logIn, signIn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        userLogin = findViewById(R.id.edtUserLogin);
        passLogin = findViewById(R.id.edtPassLogin);
        userSignup = findViewById(R.id.edtUserSignup);
        passSignup = findViewById(R.id.edtPassSignup);

        logIn = findViewById(R.id.btnLogIn);
        signIn = findViewById(R.id.btnSignUp);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(userLogin.getText().toString(), passLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user !=null && e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,"Logged in",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }else{
                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage() ,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();

                        }
                    }
                });
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(userSignup.getText().toString());
                appUser.setPassword(passSignup.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,"Signed up",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }else{
                            FancyToast.makeText(SignUpLoginActivity.this,"Error occured",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();

                        }
                    }
                });
            }
        });
    }

}
