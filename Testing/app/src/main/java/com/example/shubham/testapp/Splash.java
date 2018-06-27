package com.example.shubham.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class Splash extends AppCompatActivity {
    private Thread mSplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Splash sPlashScreen = this;

        mSplashThread = new Thread(){
        public void run(){
            try{
                synchronized (this){
                    wait(1500);
                }
            } catch (InterruptedException e) {
            }
            finish();
            Intent intent = new Intent();
            intent.setClass(sPlashScreen,MainActivity.class);
            startActivity(intent);
        }
        };
        mSplashThread.start();
    }
    public boolean onTouchEvent(MotionEvent evt) {
        if (evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (mSplashThread) {
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}
