package com.example.administrator.namotab;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by Mitul on 2/8/2018.
 */

public class BackgroundWorker extends AsyncTask<String,String,String> {

    Context context;
    BackgroundWorker(Context ctx){
        context=ctx;
    }
    AlertDialog alertDialog;
    public String result="";
    String emailforlogin="";
    String fpassword="";
    String type="";
    public JSONArray jarray;
    public ProgressDialog dialog;
    public boolean execute_status=false;
    @Override
    protected String doInBackground(String ... voids) {

        type=voids[0];

//        String login_url = "http://www.jobs.cteguj.in/gmca/fac_login.php";
//        String edit_url ="http://www.jobs.cteguj.in/gmca/update.php";
//        String register_url = "http://www.jobs.cteguj.in/gmca/fac_reg.php";
//        String fatch_data = "http://www.jobs.cteguj.in/gmca/select.php";
//        String upload_detail="http://www.jobs.cteguj.in/gmca/uploadfile.php";
//        String branch_url="http://www.jobs.cteguj.in/gmca/update_branch.php";
//        String reviewer_url ="http://www.jobs.cteguj.in/gmca/reviewer_requestdata.php";
//        String acept_request ="http://www.jobs.cteguj.in/gmca/update_type.php";

        String login_url = "https://hebephrenic-interio.000webhostapp.com/fac_login.php";
        String edit_url ="https://hebephrenic-interio.000webhostapp.com/update.php";
        String register_url = "https://hebephrenic-interio.000webhostapp.com/fac_reg.php";
        String fatch_data = "https://hebephrenic-interio.000webhostapp.com/select.php";
        String upload_detail="https://hebephrenic-interio.000webhostapp.com/uploadfile.php";
        String branch_url="https://hebephrenic-interio.000webhostapp.com/update_branch.php";
        String reviewer_url ="https://hebephrenic-interio.000webhostapp.com/reviewer_requestdata.php";
        String acept_request ="https://hebephrenic-interio.000webhostapp.com/update_type.php";
        String material_request ="https://hebephrenic-interio.000webhostapp.com/update_material.php";
        String fac_material_url = "https://hebephrenic-interio.000webhostapp.com/fatch_fac_material.php";

        if(type.equals("login")){
            try {
                String email=voids[1];
                String password=voids[2];
                emailforlogin=email;
                fpassword = password;
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("fac_email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(type.equals("fac_reg")){

            try {
                String fnm=voids[1];
                String femail=voids[2];
                String fmno=voids[3];
                String fpass=voids[4];
                String fclg=voids[5];
                String funi=voids[6];
                String fadrs=voids[7];
                String fcity=voids[8];
                String fstate=voids[9];
                String fcntry=voids[10];
                String fpin=voids[11];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(fnm,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(femail,"UTF-8")+"&"+
                        URLEncoder.encode("mno","UTF-8")+"="+URLEncoder.encode(fmno,"UTF-8")+"&"+
                        URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(fpass,"UTF-8")+"&"+
                        URLEncoder.encode("clgnm","UTF-8")+"="+URLEncoder.encode(fclg,"UTF-8")+"&"+
                        URLEncoder.encode("uninm","UTF-8")+"="+URLEncoder.encode(funi,"UTF-8")+"&"+
                        URLEncoder.encode("add","UTF-8")+"="+URLEncoder.encode(fadrs,"UTF-8")+"&"+
                        URLEncoder.encode("ct","UTF-8")+"="+URLEncoder.encode(fcity,"UTF-8")+"&"+
                        URLEncoder.encode("stnm","UTF-8")+"="+URLEncoder.encode(fstate,"UTF-8")+"&"+
                        URLEncoder.encode("cntnm","UTF-8")+"="+URLEncoder.encode(fcntry,"UTF-8")+"&"+
                        URLEncoder.encode("pincode","UTF-8")+"="+URLEncoder.encode(fpin,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("edit_profile")){

            try {
                String fnm=voids[1];
                String femail=voids[2];
                String fpass=voids[3];
                String fmno=voids[4];
                String fclg=voids[5];
                String funi=voids[6];
                String fadrs=voids[7];
                String fcity=voids[8];
                String fstate=voids[9];
                String fcntry=voids[10];
                String fpin=voids[11];

                URL url = new URL(edit_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("fullname","UTF-8")+"="+URLEncoder.encode(fnm,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(femail,"UTF-8")+"&"+
                        URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(fpass,"UTF-8")+"&"+
                        URLEncoder.encode("mno","UTF-8")+"="+URLEncoder.encode(fmno,"UTF-8")+"&"+
                        URLEncoder.encode("clgnm","UTF-8")+"="+URLEncoder.encode(fclg,"UTF-8")+"&"+
                        URLEncoder.encode("uninm","UTF-8")+"="+URLEncoder.encode(funi,"UTF-8")+"&"+
                        URLEncoder.encode("adres","UTF-8")+"="+URLEncoder.encode(fadrs,"UTF-8")+"&"+
                        URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(fcity,"UTF-8")+"&"+
                        URLEncoder.encode("state","UTF-8")+"="+URLEncoder.encode(fstate,"UTF-8")+"&"+
                        URLEncoder.encode("country","UTF-8")+"="+URLEncoder.encode(fcntry,"UTF-8")+"&"+
                        URLEncoder.encode("pin","UTF-8")+"="+URLEncoder.encode(fpin,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("fatch_data")){

            try {
                String fnm=voids[1];
                URL url = new URL(fatch_data);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(fnm,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("upload_material")) {

            try {
                String title = voids[1];
                String mtype = voids[2];
                String decs = voids[3];
                String fileld = voids[4];
                String link = voids[5];
                String email = voids[6];

                URL url = new URL(upload_detail);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8") + "&" +
                        URLEncoder.encode("desc", "UTF-8") + "=" + URLEncoder.encode(decs, "UTF-8") + "&" +
                        URLEncoder.encode("field", "UTF-8") + "=" + URLEncoder.encode(fileld, "UTF-8") + "&" +
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(mtype, "UTF-8") + "&" +
                        URLEncoder.encode("link", "UTF-8") + "=" + URLEncoder.encode(link, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("updatefield")){

            try {
                String email=voids[1];
                String branch=voids[2];


                URL url = new URL(branch_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("reviewer_request")){

            try {
                execute_status=true;
                URL url = new URL(reviewer_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("reviwer_request_accept")){
            try {
                String email=voids[1];
                String status=voids[2];
                emailforlogin=email;
                URL url = new URL(acept_request);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+ "&" +
                        URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("material_request_accept")){
            try {
                String email=voids[1];
                String status=voids[2];
                //emailforlogin=email;
                URL url = new URL(material_request);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+ "&" +
                        URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } if(type.equals("fatch_fac_material")){
            try {
                String email=voids[1];
                emailforlogin=email;
                URL url = new URL(fac_material_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Please Wait...");
        dialog.show();
    }
      @Override
      protected void onPostExecute(String result) {

          if(result.equals("Registered")){
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Registration Successful!");
              builder.setIcon(R.drawable.avatar);
              builder.setMessage("Login With your E-mail and Password.")
                      .setCancelable(false)
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                              Intent intent = new Intent(context.getApplicationContext(),Login.class);
                              context.startActivity(intent);
                              ((Activity)context).finish();
                          }
                      });
              AlertDialog alert = builder.create();
              alert.show();
          }else if(result.equals("NotRegistered")){
//              alertDialog.setMessage(result);
//              alertDialog.show();
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Warning!");
              builder.setIcon(R.drawable.avatar);
              builder.setMessage("Already Registered with this Email.\nTry with other email or login with current one.")
                      .setCancelable(false)
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {

                          }
                      });
              AlertDialog alert = builder.create();
              alert.show();
          }else if(result.equals("Adminlogin")){
                  Intent intent = new Intent(context.getApplicationContext(),admin_profile_list.class);
                  intent.putExtra("username",emailforlogin);
                  intent.putExtra("password",fpassword);
                  context.startActivity(intent);
                  ((Activity)context).finish(); //Access main activity
          } else if(result.equals("Facultylogin")){
              Intent intent = new Intent(context.getApplicationContext(),faculty_profile.class);
              intent.putExtra("username",emailforlogin);
              intent.putExtra("password",fpassword);
              context.startActivity(intent);
              ((Activity)context).finish(); //Access main activity
          }else if(result.equals("Reviewerlogin")){
              Intent intent = new Intent(context.getApplicationContext(),Reviwer.class);
              intent.putExtra("username",emailforlogin);
              intent.putExtra("password",fpassword);
              context.startActivity(intent);
              ((Activity)context).finish(); //Access main activity
          }else if(result.equals("notlogin")){
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Warning!");
              builder.setIcon(R.drawable.avatar);
              builder.setMessage("Email or Password is not valid.")
                      .setCancelable(false)
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                          }
                      });
              AlertDialog alert = builder.create();
              alert.show();
          }else if(result.equals("updated")){
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Message!");
              builder.setIcon(R.drawable.avatar);
              builder.setMessage("Profile Updated.")
                      .setCancelable(false)
                      .setPositiveButton("Great!", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                              ((Activity)context).onBackPressed();
                          }
                      });
              AlertDialog alert = builder.create();
              alert.show();
          }else if(result.equals("notupdated")) {
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Not Update!");
              builder.setIcon(R.drawable.avatar);
              builder.setMessage("Profile faces some Problem.")
                      .setCancelable(false)
                      .setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                              ((Activity)context).onBackPressed();
                          }
                      });
              AlertDialog alert = builder.create();
              alert.show();
          }else if(result.equals("MaterialUploaded")){
//              alertDialog.setMessage(result);
//              alertDialog.show();
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Congratulations!");
              builder.setIcon(R.drawable.avatar);
              builder.setMessage("Your material has uploaded succsessfully.")
                      .setCancelable(false)
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                          }
                      });
              AlertDialog alert = builder.create();
              alert.show();
          }else if(result.equals("branchUpdated")){
//              alertDialog.setMessage(result);
//              alertDialog.show();
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Congratulations!");
              builder.setIcon(R.drawable.avatar);
              builder.setMessage("Your Request is sent to the Admin.")
                      .setCancelable(false)
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                          }
                      });
              AlertDialog alert = builder.create();
              alert.show();
          }else if(result.equals("branchNotUpdated")){
//              alertDialog.setMessage(result);
//              alertDialog.show();
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Congratulations!");
              builder.setIcon(R.drawable.avatar);
              builder.setMessage("Your Profile Suffering from some Error!mi")
                      .setCancelable(false)
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                          }
                      });
              AlertDialog alert = builder.create();
              alert.show();
              } else if(result.equals("Rejected")){
              ((Activity)context).recreate();
          }else if(result.equals("Not Rejected")){

          }
            else if(execute_status) {
              try {
                  JSONObject responce = new JSONObject(result);
                  Toast.makeText(context,responce.getString("fname"),Toast.LENGTH_SHORT).show();
              } catch (JSONException e) {
                  e.printStackTrace();
              }
            execute_status=false;
          }else if(result.equals("RequestAcepted")){

          }
          else {
              try {
                  JSONObject responce = new JSONObject(result);
                  String myName = responce.getString("fname");
                  String myEmail = responce.getString("femail");
                  String mypass = responce.getString("fpwd");
                  String mymob = responce.getString("fmno");
                  String myclgnm = responce.getString("fclg");
                  String myuninm = responce.getString("funi");
                  String myadrs = responce.getString("faddrs");
                  String mycity = responce.getString("fcity");
                  String mypin = responce.getString("fpin");
                  String mystate = responce.getString("fstate");
                  String mycntry = responce.getString("fcntry");

                  EditText fname =(EditText) ((Activity)context).findViewById(R.id.e_fullName);
                  EditText femail =(EditText) ((Activity)context).findViewById(R.id.e_email);
                  EditText fpwd =(EditText) ((Activity)context).findViewById(R.id.e_password);
                  EditText fmno =(EditText) ((Activity)context).findViewById(R.id.e_mno);
                  EditText fclg =(EditText) ((Activity)context).findViewById(R.id.e_college);
                  EditText funi =(EditText) ((Activity)context).findViewById(R.id.e_uni);
                  EditText faddrs =(EditText) ((Activity)context).findViewById(R.id.e_address);
                  EditText fcity =(EditText) ((Activity)context).findViewById(R.id.e_city);
                  EditText fpin =(EditText) ((Activity)context).findViewById(R.id.e_pin);
                  EditText fstate =(EditText) ((Activity)context).findViewById(R.id.e_state);
                  EditText fcntry =(EditText) ((Activity)context).findViewById(R.id.e_country);

                  fname.setText(myName);
                  femail.setText(myEmail);
                  fpwd.setText(mypass);
                  fmno.setText(mymob);
                  fclg.setText(myclgnm);
                  funi.setText(myuninm);
                  faddrs.setText(myadrs);
                  fcity.setText(mycity);
                  fpin.setText(mypin);
                  fstate.setText(mystate);
                  fcntry.setText(mycntry);
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
          if (type.equals("fatch_fac_material")){
              TextView jdata =(TextView) ((Activity)context).findViewById(R.id.jdata);
              jdata.setText(result);
          }
          dialog.dismiss();
      }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
    private void getStudents(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                Toast.makeText(context,json.getString("fname"),Toast.LENGTH_SHORT).show();
               // students.add(json.getString(Config.TAG_USERNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, students));
    }
}
