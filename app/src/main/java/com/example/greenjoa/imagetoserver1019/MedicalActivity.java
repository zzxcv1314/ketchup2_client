package com.example.greenjoa.imagetoserver1019;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MedicalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical);
    }

    public void call1(View view) {
        Intent intent =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0229047594"));
        startActivity(intent);
    }

    public void call2(View view) {
        Intent intent =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0233201634"));
        startActivity(intent);
    }
}
