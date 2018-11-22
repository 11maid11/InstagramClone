package com.maid.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity implements View.OnClickListener{
    EditText edtUsr,edtPass;
    Button btnLog,btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log in");
        edtPass = findViewById(R.id.edtLpassword);
        edtUsr = findViewById(R.id.edtLUsername);
        btnLog = findViewById(R.id.btnLogin);
        btnSign = findViewById(R.id.btnSignUp);
        edtPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnLog);
                }
                return false;
            }
        });

        btnSign.setOnClickListener(this);
        btnLog.setOnClickListener(this);
        if(ParseUser.getCurrentUser() !=null){
//            transit();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnSignUp:
                Intent intent = new Intent(this,SignUp.class);
                startActivity(intent);
                break;
            case R.id.btnLogin:
                ParseUser.logInInBackground(edtUsr.getText().toString(), edtPass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                            if(user !=null && e == null){
                                Toast.makeText(Login.this,user.getUsername()+"is logged in ",Toast.LENGTH_SHORT).show();
                                transit();

                            }else{
                                Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                    }
                });

                break;

        }
    }
    public void hide(View v){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
    public void transit(){
        Intent intent = new Intent(this,SocialMedia.class);
        startActivity(intent);
    }
}
