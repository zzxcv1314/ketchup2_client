package com.example.greenjoa.imagetoserver1019;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        //intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        //intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                convert();
            }
        };
        timer.schedule(timerTask, 3000);
    }

    public void convert()
    {
        Intent select = new Intent(this, LoginActivity.class);
        startActivity(select);
    }

}
