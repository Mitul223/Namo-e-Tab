package com.example.administrator.namotab;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class admin_profile_list extends AppCompatActivity {

    public String result = "";
    ListView lv;
    TextView useremail;
    String uid,pass;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> beenthere = new ArrayList<>();
    ArrayList<String> email = new ArrayList<>();
    ArrayList<String> branch = new ArrayList<>();
    ArrayList<String> contact = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i =getIntent();
        uid = i.getStringExtra("username");
        pass = i.getStringExtra("password");
        SharedPreferences sharedPreferences =getSharedPreferences("AppData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",uid);
        editor.putString("password",pass);
        editor.commit();
        useremail = (TextView)findViewById(R.id.userid);
        setContentView(R.layout.admin_profile_list);
        StrictMode.enableDefaults(); //STRICT MODE ENABLED
        lv=(ListView)findViewById(R.id.listmember);
        getData();
        refresh();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void refresh(){
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    public void getData() {

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.show();
        InputStream isr = null;

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://hebephrenic-interio.000webhostapp.com/reviewer_requestdata.php"); //YOUR PHP SCRIPT ADDRESS
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
            //nameView.setText("Couldnt connect to database");
        }
        //convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        //parse json data
        try {
                beenthere.clear();
                email.clear();
                branch.clear();
                contact.clear();
            JSONArray jArray = new JSONArray(result);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);
                beenthere.add(json.getString("fullname"));
                email.add(json.getString("email"));
                branch.add(json.getString("branch"));
                contact.add(json.getString("mno"));
            }

            if (!(result.equals(""))) { //simple_list_item_1
                final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listforadminactivity,R.id.list_item_string, beenthere);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(admin_profile_list.this);
                        builder.setTitle("Request For Become a Reviwer");
                        builder.setIcon(R.drawable.avatar);
                        builder.setMessage("Name : "+beenthere.get(position)+"\nBranch : "+branch.get(position)+"\nEmail : "+email.get(position)+"\nContact : "+contact.get(position))
                                .setCancelable(true)
                                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String type = "reviwer_request_accept";
                                        BackgroundWorker backgroundWorker=new BackgroundWorker(admin_profile_list.this);
                                        backgroundWorker.execute(type,email.get(position).toString(),"Deny");
                                    }
                                })
                                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String type = "reviwer_request_accept";
                                        BackgroundWorker backgroundWorker=new BackgroundWorker(admin_profile_list.this);
                                        backgroundWorker.execute(type,email.get(position).toString(),"Allow");
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
            }

        } catch (Exception e) {
            Log.e("log_tag", "Error Parsing Data " + e.toString());
        }
        dialog.dismiss();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("admin_profile_list Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.faculty_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.editprof:
                Intent intent = new Intent(admin_profile_list.this, editProfile.class);
                intent.putExtra("email", uid);
                startActivity(intent);
                return true;
            case R.id.logout:
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(admin_profile_list.this);
                builder.setTitle("Warning!");
                builder.setIcon(R.drawable.avatar);
                builder.setMessage("Are you sure you want to logout?")
                        .setCancelable(false)
                        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences sharedPreferences =getSharedPreferences("AppData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email","N/A");
                                editor.putString("password","N/A");
                                editor.commit();
                                Intent intent1 = new Intent(admin_profile_list.this, Login.class);
                                startActivity(intent1);
                                finish();
                            }
                        });
                android.app.AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        //super.onBackPressed();
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(admin_profile_list.this);
        builder.setTitle("Warning!");
        builder.setIcon(R.drawable.avatar);
        builder.setMessage("Are you sure you want to Quit?")
                .setCancelable(false)
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Quit!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
}

