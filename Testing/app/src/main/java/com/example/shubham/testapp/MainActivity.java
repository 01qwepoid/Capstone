package com.example.shubham.testapp;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    Button bloginpage;


    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bloginpage = (Button) findViewById(R.id.butloginpage);
    }


    public void scanBar(View v){
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE","PRODUCT_MODE");
            startActivityForResult(intent,0 );
        }catch(ActivityNotFoundException e) {
            showDialog(MainActivity.this,"No scanner found!","Download a scanner code activity?","Yes","No").show();
        }

    }

    private void scanQR(View v){
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE","PRODUCT_MODE");
            startActivityForResult(intent,0 );
        }catch(ActivityNotFoundException e) {
            showDialog(MainActivity.this,"No scanner found!","Download a scanner code activity?","Yes","No").show();
        }
    }

    private  static AlertDialog showDialog(final AppCompatActivity act,
             CharSequence title, CharSequence message,CharSequence buttonYes,
             CharSequence buttonNo){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:"+"com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    act.startActivity(intent);
                }catch (ActivityNotFoundException e){
                }
            }
        }).setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
            }
        });
        return downloadDialog.show();
    }

     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode==0){
            if(resultCode==RESULT_OK){
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast.makeText(this,"Content:"+contents+" Format:" +format,Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void showDialog() {
    }

    public void launchLoginPage(View view) {
        Log.d(LOG_TAG,"Button Clicked");
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void launchuserhome(View view) {
        Intent intent = new Intent(this, UserHome.class);
        startActivity(intent);
    }

    public void launchmerchanthome(View view) {
        Intent intent = new Intent(this, MerchantHome.class);
        startActivity(intent);
    }
}
