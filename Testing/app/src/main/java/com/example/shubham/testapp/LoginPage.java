package com.example.shubham.testapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    Button bLogin;
    EditText eUsername, ePass;

    TextView tAttempt;
    int counter = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        bLogin = (Button) findViewById(R.id.buttonlogin);
        eUsername = (EditText) findViewById(R.id.etusername);
        ePass = (EditText) findViewById(R.id.etpassword);
        tAttempt = (TextView)findViewById(R.id.tvattemptno);
        tAttempt.setVisibility(View.GONE);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eUsername.getText().toString().equals("admin") &&
                        ePass.getText().toString().equals("admin")){
                            Toast.makeText(getApplicationContext(),"Redirecting...",
                            Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"Wrong Credentials",
                            Toast.LENGTH_SHORT).show();

                            tAttempt.setVisibility(View.VISIBLE);
                            tAttempt.setBackgroundColor(Color.RED);
                            counter--;
                            tAttempt.setText(Integer.toString(counter));

                            if(counter==0){
                                bLogin.setEnabled(false);
                            }
                        }
                }
        });
    }
}
