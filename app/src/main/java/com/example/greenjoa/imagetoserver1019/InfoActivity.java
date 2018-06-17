package com.example.greenjoa.imagetoserver1019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        Button menu = (Button)findViewById(R.id.menu);
        Button shuttle = (Button)findViewById(R.id.shuttle);



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),DietActivity.class);
                startActivity(intent1);
            }
        });

        shuttle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),ShuttleActivity.class);
                startActivity(intent2);
            }
        });
    }
}
