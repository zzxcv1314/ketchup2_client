package com.example.greenjoa.imagetoserver1019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.StringTokenizer;

public class EmotionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emotion);

        TextView emotiontv = (TextView)findViewById(R.id.emotiontv);

        Intent intent = getIntent();
        String emotion = intent.getStringExtra("emotion");

        StringTokenizer st = new StringTokenizer(emotion, "|");
        String a = st.nextToken();
        String b = st.nextToken();
        String c = st.nextToken();
        String d = st.nextToken();
        String e = st.nextToken();
        String f = st.nextToken();
        String g = st.nextToken();
        String h = st.nextToken();

        emotiontv.setText(a + "\n" + b + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n" + g + "\n" + h);
    }
}
