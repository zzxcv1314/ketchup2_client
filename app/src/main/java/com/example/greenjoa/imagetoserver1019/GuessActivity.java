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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GuessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess);

        TextView tv = (TextView)findViewById(R.id.tv);
        ImageView iv = (ImageView)findViewById(R.id.iv);
        Button age = (Button) findViewById(R.id.age);
        Button emotion = (Button) findViewById(R.id.emotion);

        Intent intent4 = getIntent();
        String imagepath = intent4.getStringExtra("imagepath");

        //tv.setText(imagepath);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);


        Bitmap bitmap = BitmapFactory.decodeFile(imagepath);//경로를 통해 비트맵으로 전환
       // resizeBitmap(bitmap);
        iv.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기


        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {

                    public void run() {
                        try {
                            URL url = null;
                            url = new URL("https://loginwithface.azurewebsites.net/faceRecogAge.php");

                            HttpURLConnection http = (HttpURLConnection)url.openConnection();
                            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                            StringBuffer buffer = new StringBuffer();
                            int c;

                            while((c=in.read()) != -1){
                                buffer.append((char)c);
                            }

                            Intent intent = new Intent(getApplicationContext(),AgeActivity.class);
                            intent.putExtra("age", (CharSequence) buffer);
                            startActivity(intent);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                }).start();
            }
        });

        emotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {

                    public void run() {
                        try {
                            URL url = null;
                            url = new URL("https://loginwithface.azurewebsites.net/faceRecog.php");

                            HttpURLConnection http = (HttpURLConnection)url.openConnection();
                            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                            StringBuffer buffer = new StringBuffer();
                            int c;

                            while((c=in.read()) != -1){
                                buffer.append((char)c);
                            }

                            Intent intent = new Intent(getApplicationContext(),EmotionActivity.class);
                            intent.putExtra("emotion", (CharSequence) buffer);
                            startActivity(intent);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                }).start();
            }
        });

    }


    public Bitmap rotate(Bitmap src, float degree) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }


    public int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    public void calling(View view) {
        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"));
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
