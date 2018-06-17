package com.example.administrator.namotab;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editProfile extends AppCompatActivity {
    EditText name,email,mob,pass,clg,uni,ads,city,pin,sta,cou;
    Button edit;
    public String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent i =getIntent();
        uid = i.getStringExtra("email");
        name =(EditText)findViewById(R.id.e_fullName);
        email =(EditText) findViewById(R.id.e_email);
        mob=(EditText)findViewById(R.id.e_mno);
        clg=(EditText)findViewById(R.id.e_college);
        uni=(EditText)findViewById(R.id.e_uni);
        pass=(EditText)findViewById(R.id.e_password);
        ads=(EditText)findViewById(R.id.e_address);
        city=(EditText)findViewById(R.id.e_city);
        sta=(EditText)findViewById(R.id.e_state);
        cou=(EditText)findViewById(R.id.e_country);
        pin=(EditText)findViewById(R.id.e_pin);
        edit=(Button) findViewById(R.id.edit_button);

        name.setEnabled(false);
        email.setEnabled(false);
        mob.setEnabled(false);
        clg.setEnabled(false);
        uni.setEnabled(false);
        ads.setEnabled(false);
        city.setEnabled(false);
        sta.setEnabled(false);
        pin.setEnabled(false);
        pass.setEnabled(false);
        cou.setEnabled(false);
        getdata();
    }
    private void getdata(){
        String type = "fatch_data";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,uid);
    }
    public void editButtonClick(View view){
        String Buttonname = edit.getText().toString();
        if(Buttonname.equals("Edit Profile")){
            name.setEnabled(true);
            mob.setEnabled(true);
            clg.setEnabled(true);
            uni.setEnabled(true);
            ads.setEnabled(true);
            pin.setEnabled(true);
            pass.setEnabled(true);
            city.setEnabled(true);
            sta.setEnabled(true);
            cou.setEnabled(true);
            edit.setText("Save");

        }else if(Buttonname.equals("Save")){
            Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();
            edit.setText("EDIT PROFILE");
            String tname=name.getText().toString();
            String tpass=pass.getText().toString();
            String tmno=mob.getText().toString();
            String tclg=clg.getText().toString();
            String tuni=uni.getText().toString();
            String tadrs=ads.getText().toString();
            String tcity=city.getText().toString();
            String tpin=pin.getText().toString();
            String tstate=sta.getText().toString();
            String tcntry=cou.getText().toString();

            if (tname.equals("") || tname.length() == 0
                    || tpass.equals("") || tpass.length() == 0
                    || tmno.equals("") || tmno.length() == 0
                    || tclg.equals("") || tclg.length() == 0
                    || tuni.equals("") || tuni.length() == 0
                    || tadrs.equals("") || tadrs.length() == 0
                    || tcity.equals("") || tcity.length() == 0
                    || tpin.equals("") || tpin.length() == 0
                    || tstate.equals("") || tstate.length() == 0
                    || tcntry.equals("") || tcntry.length() == 0)

                Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show();
            else {
                String type = "edit_profile";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, tname,uid,tpass,tmno,tclg,tuni,tadrs,tcity,tpin,tstate,tcntry);
            }
        }
    }
}
