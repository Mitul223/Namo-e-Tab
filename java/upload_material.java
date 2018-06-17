package com.example.administrator.namotab;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class upload_material extends AppCompatActivity {
    private Button button;
    public String filename,mtitle,mtype,mdec,mfield,memail;
    EditText title,dics,field,type;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_material);
        button=(Button) findViewById(R.id.button);
        title = (EditText)findViewById(R.id.txtmtitle);
        dics = (EditText)findViewById(R.id.txtdesc);
        field = (EditText)findViewById(R.id.txtfield);
        type = (EditText)findViewById(R.id.txttype);
        username = (TextView)findViewById(R.id.username);
        Intent intent = getIntent();
        String getemail = intent.getStringExtra("email").toString();
        username.setText(getemail);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                return;
            }
        }
        enable_button();
    }

    private void enable_button() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode==100&&(grantResults[0]==PackageManager.PERMISSION_GRANTED)){
            enable_button();
        }else {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
        }
    }


    private void checkValidation() {

        // Get all edittext texts
         mtitle = title.getText().toString();
         mtype = type.getText().toString();
         mdec = dics.getText().toString();
         mfield = field.getText().toString();
        memail = username.getText().toString();
        if (mtitle.equals("") || mtitle.length() == 0
                || mtype.equals("") || mtype.length() == 0
                || mdec.equals("") || mdec.length() == 0
                || mfield.equals("") || mfield.length() == 0)

            Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show();

            // Else do signup or do your stuff
        else {
            new MaterialFilePicker()
                    .withActivity(upload_material.this)
                    .withRequestCode(10)
                    .start();
        }

    }
    ProgressDialog progress;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data){
        if (requestCode==10 && resultCode== RESULT_OK){
            progress = new ProgressDialog(upload_material.this);
            progress.setTitle("Uploding...");
            progress.setMessage("Please Wait...");
            progress.show();;


            Thread t =new Thread(new Runnable() {
                @Override
                public void run() {
                    File f =new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                    String content_type = getMimeType(f.getPath());
                    String file_path = f.getAbsolutePath();
                    filename = file_path.substring(file_path.lastIndexOf("/")+1);
                    OkHttpClient client = new OkHttpClient();
                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);

                    RequestBody request_b1ody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("type",content_type)
                            .addFormDataPart("uploaded_file",file_path.substring(file_path.lastIndexOf("/")+1),file_body)
                            .build();
                    Request request = new Request.Builder()
//                            .url("http://www.jobs.cteguj.in/gmca/savefile.php")
                            .url("https://hebephrenic-interio.000webhostapp.com/savefile.php")
                            .post(request_b1ody)
                            .build();
                    try {
                        Response response = client.newCall(request).execute();
                        if (!response.isSuccessful())
                        {
                            throw new IOException("Error :"+response);
                        }
                        progress.dismiss();
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            progress.dismiss();
            Toast.makeText(upload_material.this,filename,Toast.LENGTH_LONG).show();
            String type = "upload_material";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, mtitle, mtype, mdec, mfield, filename,memail);
        }
    }
    private String getMimeType(String path) {
        String extemsion = MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extemsion);
    }
}