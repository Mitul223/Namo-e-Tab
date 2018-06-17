package com.example.administrator.namotab;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static com.example.administrator.namotab.R.drawable.text_selector;

public class Login extends AppCompatActivity {

    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
  //  public ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //dialog = new ProgressDialog(this);
        emailid = (EditText)findViewById(R.id.login_emailid);
        password = (EditText)findViewById(R.id.login_password);
        //loginButton = (Button)findViewById(R.id.loginBtn);
        forgotPassword = (TextView)findViewById(R.id.forgot_password);
        signUp = (TextView)findViewById(R.id.createAccount);
        show_hide_password = (CheckBox)findViewById(R.id.show_hide_password);

        //loginLayout = (LinearLayout)findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);

        // Setting text selector over textviews
//        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(text_selector);
//        try {
//            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
//
//            forgotPassword.setTextColor(csl);
//            show_hide_password.setTextColor(csl);
//            signUp.setTextColor(csl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        show_hide_password
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    public void btnLoginClick(View view){
        checkValidation();}

    public void signupClick(View view){
        Intent intent =new Intent(this,Registration.class);
        startActivity(intent);
    }
    public void forgotpwdClick(View view){
        Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
    }

    private void checkValidation() {
        // Get email id and password
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
           // loginLayout.startAnimation(shakeAnimation);
            Toast.makeText(this,
                    "Both fields are compulsory",Toast.LENGTH_LONG).show();

        }
        // Check if email id is valid or not
        else if (!m.find())
            Toast.makeText(this,
                    "Your Email Id is Invalid.",Toast.LENGTH_LONG).show();
            // Else do login and do your stuff
        else {
//            dialog.setMessage("Please wait...");
//            dialog.show();
            String type = "login";
            String email = emailid.getText().toString();
            String pwd = password.getText().toString();
            BackgroundWorker backgroundWorker=new BackgroundWorker(this);
            backgroundWorker.execute(type,email,pwd);
//            dialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning!");
        builder.setIcon(R.mipmap.ic_question2);
        builder.setMessage("Are you sure!\nYou want to quit?")
                .setCancelable(true)
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
