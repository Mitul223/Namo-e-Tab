package com.example.administrator.namotab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText mno,pass;
    Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mno=(EditText)findViewById(R.id.etmno);
        pass=(EditText)findViewById(R.id.etpass);

        sign=(Button)findViewById(R.id.btn_sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
            }
        });
    }

    public void onLogin(View v){
//        String mnumber = mno.getText().toString();
//        String password=pass.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type);
    }
}
