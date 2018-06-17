package com.example.administrator.namotab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    public static final String DEFAULT="N/A";
    private static int out=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences("AppData", Context.MODE_PRIVATE);
        final String Email = sharedPreferences.getString("email",DEFAULT);
        final String  Password = sharedPreferences.getString("password",DEFAULT);

        //Toast.makeText(this,Email +"\n"+Password,Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Email.equals(DEFAULT) && Password.equals(DEFAULT))
                {
                    Intent intent =new Intent(SplashActivity.this,Login.class);
                    startActivity(intent);
                }
                else {
                    String type = "login";
                    BackgroundWorker backgroundWorker=new BackgroundWorker(SplashActivity.this);
                    backgroundWorker.execute(type,Email,Password);
                }
            }
        },out);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
