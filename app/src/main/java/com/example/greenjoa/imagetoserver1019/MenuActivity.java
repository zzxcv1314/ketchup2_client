package com.example.greenjoa.imagetoserver1019;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MenuActivity extends AppCompatActivity {
    private static int ONE_MINUTE = 5626;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        new AlarmHATT(getApplicationContext()).Alarm();

        TextView tv = (TextView) findViewById(R.id.tv);
        //ImageButton btn1 = (ImageButton) findViewById(R.id.btn1);
        //ImageButton btn2 = (ImageButton) findViewById(R.id.btn2);
        //ImageButton btn3 = (ImageButton) findViewById(R.id.btn3);
        //ImageButton btn4 = (ImageButton) findViewById(R.id.btn4);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);

        Intent intent = getIntent();
        String username = intent.getStringExtra("name");

        tv.setText(username);



            btn1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), RequestActivity.class);
                    startActivity(intent1);
                }
            });


            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = new Intent(getApplicationContext(), InfoActivity.class);
                    startActivity(intent2);
                }
            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent intent3 = new Intent(getApplicationContext(), MedicalActivity.class);
                    Intent intent3 = new Intent(getApplicationContext(), ProgramActivity.class);
                    startActivity(intent3);
                }
            });


            btn4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
//                Intent intent4 = new Intent(getApplicationContext(),MedicalActivity.class);
//                startActivity(intent4);

                    Intent intent = getIntent();
                    String imagepath = intent.getStringExtra("mCurrentPhotoPath");

                    Intent intent4 = new Intent(getApplicationContext(), GuessActivity.class);
                    intent4.putExtra("imagepath", (CharSequence) imagepath);
                    startActivity(intent4);

                }
            });

        }

    public class AlarmHATT {
        private Context context;

        public AlarmHATT(Context context) {
            this.context = context;
        }

        public void Alarm() {
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(MenuActivity.this, BroadcastD.class);

            PendingIntent sender = PendingIntent.getBroadcast(MenuActivity.this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기

            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 18, 15, 0);

            //알람 예약
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }
    }
}