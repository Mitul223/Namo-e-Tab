package com.example.administrator.namotab;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class faculty_profile extends AppCompatActivity {

    ListView lv;
    public String result = "";
    public String uid, pass;
    TextView jdata;
    ArrayList<String> mid = new ArrayList<>();
    ArrayList<String> mtitle = new ArrayList<>();
    ArrayList<String> mdesc = new ArrayList<>();
    ArrayList<String> mfield = new ArrayList<>();
    ArrayList<String> mtype = new ArrayList<>();
    ArrayList<String> email = new ArrayList<>();
    ArrayList<String> link = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);
        jdata =(TextView)findViewById(R.id.jdata);
        Intent i = getIntent();
        uid = i.getStringExtra("username");
        pass = i.getStringExtra("password");
        SharedPreferences sharedPreferences = getSharedPreferences("AppData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", uid);
        editor.putString("password", pass);
        editor.apply();
        lv = (ListView) findViewById(R.id.matlist);
        StrictMode.enableDefaults(); //STRICT MODE ENABLED
        //getData();
         refresh();
        button=(FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(faculty_profile.this,upload_material.class);
                intent.putExtra("email",uid);
                startActivity(intent);

            }
        });
    }

    public void refresh(){
        String type = "fatch_fac_material";
        BackgroundWorker backgroundWorker = new BackgroundWorker(faculty_profile.this);
        backgroundWorker.execute(type, uid);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.fac_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    public void getData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.show();
//        InputStream isr = null;
//        try {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httppost = new HttpPost("https://hebephrenic-interio.000webhostapp.com/fatch_material.php"); //YOUR PHP SCRIPT ADDRESS
//            HttpResponse response = httpclient.execute(httppost);
////            Toast.makeText(this,"I'm Here",Toast.LENGTH_LONG).show();
//            HttpEntity entity = response.getEntity();
//            isr = entity.getContent();
//        } catch (Exception e) {
//            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
//        }
//        //convert response to string
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            isr.close();
//
//            result = sb.toString();
//        } catch (Exception e) {
//            // Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
//        }
//        String type = "fatch_fac_material";
//        BackgroundWorker backgroundWorker = new BackgroundWorker(faculty_profile.this);
//        backgroundWorker.execute(type, uid);
        //parse json data
        try {
            result = jdata.getText().toString();
            JSONArray jArray = new JSONArray(result);
            mid.clear();
            mtitle.clear();
            email.clear();
            mdesc.clear();
            mfield.clear();
            mtype.clear();
            link.clear();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);
                mid.add(json.getString("mid"));
                mtitle.add(json.getString("mtitle"));
                email.add(json.getString("eid"));
                mdesc.add(json.getString("mdescription"));
                mfield.add(json.getString("mfield"));
                mtype.add(json.getString("mtype"));
                link.add(json.getString("mlink"));
            }

            if (!(result.equals(""))) { //simple_list_item_1
                final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listforadminactivity, R.id.list_item_string, mtitle);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ProgressDialog dialog1 = new ProgressDialog(faculty_profile.this);
                        dialog1.setMessage("Please Wait...");
                        dialog1.show();
                        //Toast.makeText(Reviwer.this,link.get(position),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(faculty_profile.this, materialViewer.class);
                        intent.putExtra("pdfurl", link.get(position));
                        startActivity(intent);
                        dialog1.dismiss();
                    }
                });
//                lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(faculty_profile.this);
//                        builder.setTitle("Material Request");
//                        builder.setIcon(R.drawable.avatar);
//                        // Toast.makeText(getApplicationContext(),mid.get(position).toString(),Toast.LENGTH_LONG).show();
//                        builder.setMessage("Title : " + mtitle.get(position) + "\nDescription : " + mdesc.get(position) + "\nEmail : " + email.get(position) + "\nMaterial Type : " + mtype.get(position) + "\nMaterial Field : " + mfield.get(position))
//                                .setCancelable(true)
//                                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                    }
//                                })
//                                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                    }
//                                });
//                        android.app.AlertDialog alert = builder.create();
//                        alert.show();
//                        return true;
//                    }
//                });
            }

        } catch (Exception e) {
            Log.e("log_tag", "Error Parsing Data " + e.toString());
        }
        dialog.dismiss();
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

        switch(item.getItemId()){
            case R.id.editprof:
                Intent intent = new Intent(faculty_profile.this,editProfile.class);
                intent.putExtra("email",uid);
                startActivity(intent);
                return true;
            case R.id.upgrade:
                CreateAlertDialog();
                return true;
            case R.id.logout:
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(faculty_profile.this);
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
                                Intent intent1=new Intent(faculty_profile.this,Login.class);
                                startActivity(intent1);
                                finish();
                            }
                        });
                android.app.AlertDialog alert = builder.create();
                alert.show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void CreateAlertDialog() {

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

        LinearLayout ll_alert_layout = new LinearLayout(this);
        ll_alert_layout.setOrientation(LinearLayout.VERTICAL);
        final EditText ed_input = new EditText(this);
        ll_alert_layout.addView(ed_input);

        alertbox.setTitle("Branch Name");
        alertbox.setMessage("Please enter your branch name");

        //setting linear layout to alert dialog

        alertbox.setView(ll_alert_layout);

        alertbox.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        // will automatically dismiss the dialog and will do nothing

                    }
                });


        alertbox.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        String input_text = ed_input.getText().toString();
                        String type = "updatefield";
                        BackgroundWorker backgroundWorker=new BackgroundWorker(faculty_profile.this);
                        backgroundWorker.execute(type,uid,input_text);
                    }
                });
        alertbox.show();
    }
}
