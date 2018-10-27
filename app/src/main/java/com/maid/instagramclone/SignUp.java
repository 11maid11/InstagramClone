package com.maid.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName;
    private EditText edtKickSpd;
    private EditText edtKickPwr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSubmit);
        edtName = findViewById(R.id.edtName);
        edtKickPwr = findViewById(R.id.edtKickPwr);
        edtKickSpd = findViewById(R.id.edtkickSpd);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name",edtName.getText().toString());
        kickBoxer.put("punch_power",Integer.parseInt(edtKickSpd.getText().toString()));
        kickBoxer.put("punch_speed",Integer.parseInt(edtKickPwr.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(SignUp.this, kickBoxer.get("name") +"Saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
