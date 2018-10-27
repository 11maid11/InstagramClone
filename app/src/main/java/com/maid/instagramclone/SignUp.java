package com.maid.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName;
    private EditText edtKickSpd;
    private EditText edtKickPwr;
    private String data;
    private TextView txtGetData;
    private Button getAllData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllData = findViewById(R.id.btnGetAll);
        btnSave = findViewById(R.id.btnSubmit);
        edtName = findViewById(R.id.edtName);
        edtKickPwr = findViewById(R.id.edtKickPwr);
        edtKickSpd = findViewById(R.id.edtkickSpd);
        txtGetData = findViewById(R.id.txtGetData);
        btnSave.setOnClickListener(this);
        getAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = "";
                final ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null ){
                            if(objects.size()>0){
                                FancyToast.makeText(SignUp.this,"Success",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                                for (int i =0;i<objects.size();i++){
                                    data+= objects.get(i).get("name") + ";" + objects.get(i).get("punch_power");
                                }
                            }
                        }else{
                            FancyToast.makeText(SignUp.this,"Error occured",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }
                        txtGetData.setText(data);
                    }
                });
            }
        });
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("dnVzzlN4Ay", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e == null){
                            txtGetData.setText(object.get("name") + " " +object.get("punch_power"));
                        }
                    }
                });

            }
            });
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
