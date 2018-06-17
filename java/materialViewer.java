package com.example.administrator.namotab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class materialViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_viewer);
        Intent i = getIntent();
        String pdfurl = i.getStringExtra("pdfurl");

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
       // webView.getSettings().setPluginsEnabled(true);
        webView.loadUrl("https://docs.google.com/viewer?url="+pdfurl);
    }
}
