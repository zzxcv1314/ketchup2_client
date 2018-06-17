package com.example.greenjoa.imagetoserver1019;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

public class AgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.age);

        TextView agetv = (TextView)findViewById(R.id.agetv);

        Intent intent = getIntent();
        String age = intent.getStringExtra("age");

//        java.util.StringTokenizer strToken = new java.util.StringTokenizer(age,"/b");
//        while (strToken.hasMoreTokens()){
//            String token = strToken.nextToken();
//            agetv.setText("\n" + token);
//            //System.out.println("\n" + token);
//        }


        agetv.setText(age);
    }


}
