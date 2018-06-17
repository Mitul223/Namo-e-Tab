package com.example.administrator.namotab;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {


    EditText fullname, email, mno, pass, cpass, clgnm, uninm, adres, city, state, contry, pin;
    //Button btnSignup;
    CheckBox chkTermCondition;
  //  Button buttonLoadImage;
    TextView alreadyuser;
    private static int RESULT_LOAD_IMAGE = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        chkTermCondition = (CheckBox) findViewById(R.id.terms_conditions);
        alreadyuser=(TextView)findViewById(R.id.already_user);
        fullname = (EditText) findViewById(R.id.fullName);
        email = (EditText) findViewById(R.id.userEmailId);
        mno = (EditText) findViewById(R.id.mobileNumber);
        pass = (EditText) findViewById(R.id.password);
        cpass = (EditText) findViewById(R.id.confirmPassword);
        clgnm = (EditText) findViewById(R.id.college);
        uninm = (EditText) findViewById(R.id.university);
        adres = (EditText) findViewById(R.id.location);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        contry = (EditText) findViewById(R.id.country);
        pin = (EditText) findViewById(R.id.pin);

//        buttonLoadImage = (Button) findViewById(R.id.btnSelectPhoto);
//        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                Intent i = new Intent(
//                        Intent.ACTION_PICK,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                startActivityForResult(i, RESULT_LOAD_IMAGE);
//            }
//        });
        alreadyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void signupClick(View view) {
        checkValidation();
    }

    private void checkValidation() {

        // Get all edittext texts
        String fullnm = fullname.getText().toString();
        String femail = email.getText().toString();
        String fmno = mno.getText().toString();
        String fpwd = pass.getText().toString();
        String fcpwd = cpass.getText().toString();
        String fclgnm = clgnm.getText().toString();
        String funinm = uninm.getText().toString();
        String fadres = adres.getText().toString();
        String fcity = city.getText().toString();
        String fstate = state.getText().toString();
        String fcountry = contry.getText().toString();
        String fpin = pin.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(femail);

        // Check if all strings are null or not
        if (fullnm.equals("") || fullnm.length() == 0
                || femail.equals("") || femail.length() == 0
                || fmno.equals("") || fmno.length() == 0
                || fpwd.equals("") || fpwd.length() == 0
                || fclgnm.equals("") || fclgnm.length() == 0
                || funinm.equals("") || funinm.length() == 0
                || fadres.equals("") || fadres.length() == 0
                || fcity.equals("") || fcity.length() == 0
                || fstate.equals("") || fstate.length() == 0
                || fcountry.equals("") || fcountry.length() == 0
                || fpin.equals("") || fpin.length() == 0)

            Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show();


            // Check if email id valid or not
        else if (!m.find())

            Toast.makeText(this, "Your Email Id is Invalid.", Toast.LENGTH_LONG).show();


            // Check if both password should be equal
        else if (!fpwd.equals(fcpwd))

            Toast.makeText(this, "Both password doesn't match.", Toast.LENGTH_LONG).show();

            // Make sure user should check Terms and Conditions checkbox
        else if (!chkTermCondition.isChecked())

            Toast.makeText(this, "Please select Terms and Conditions.", Toast.LENGTH_LONG).show();

            // Else do signup or do your stuff
        else {
            String type = "fac_reg";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, fullnm, femail, fmno, fpwd, fclgnm, funinm, fadres, fcity, fstate, fcountry, fpin);
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Registration Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
