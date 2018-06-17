package com.example.greenjoa.imagetoserver1019;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class LoginActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 2001;
    static final int REQUEST_TAKE_ALBUM = 2002;
    static final int REQUEST_IMAGE_CROP = 2003;

    //ImageButton btn_capture;
    Button btn_capture;
    //Button btn_album;
    //ImageView iv_view;

    String mCurrentPhotoPath; //현재 사용중인 사진의 경로 (디바이스 내 파일 경로)
    Uri photoURI, albumURI;  //각각의 파일이 갖고 있는 경로
    int serverResponseCode = 0;

    boolean isAlbum = false;  //crop시 사진을 직접 찍은 것인지, 앨범에서 가져온 것인지 확인하는 플래그
    String upLoadServerUri ="http://ketchup2.azurewebsites.net/UploadToServer.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btn_capture = (Button) findViewById(R.id.btn_capture);
        //iv_view = (ImageView) findViewById(R.id.iv_view);

        btn_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ATask aTask = new ATask();
                aTask.execute();
            }
        });
    }

    public class ATask extends AsyncTask<Integer, Integer, String>{
        protected void onPreExecute(){
            Log.i("ATask","onPreExecute");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            Log.i("ATask","doInbackground");
            captureCamera();
           // cancel(true);
            return null;
        }

        protected void onPostExecute(String result){
          //  ImageView imageView1 = (ImageView)findViewById(R.id.iv_view);
            //imageView1.setImageResource(R.drawable.loading);


            Log.i("ATask","onpostexecute");

            super.onPostExecute(result);
        }

        protected void onCancelled(){
            Log.i("ATask","oncancelled");
            super.onCancelled();
        }

        protected void onProgressUpdate(Integer... values){
            Log.i("ATask","onProgressupdate");
            super.onProgressUpdate(values);
        }
    }


    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("Loading..");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                for (int i = 0; i < 5; i++) {
                    //asyncDialog.setProgress(i * 30);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();
            super.onPostExecute(result);
        }
    }




    public class BTask extends AsyncTask<Integer, Integer, String>{

        protected void onPreExecute(){
            Log.i("BTask","onPreExecute");

            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Integer... integers) {
            Log.i("BTask","doinbackground");

            try {
                URL url = null;
                url = new URL("http://ketchup2.azurewebsites.net/UploadToServer.php");

                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                int c;

                while((c=in.read()) != -1){
                    buffer.append((char)c);
                }

                Log.i("유진","여기까지 옴3");
                //Log.i("참고", buffer.toString());

                if(buffer.substring(0,7).equals("notuser")){

                    runOnUiThread(new Runnable() {
                        public void run() {

                            AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();     //닫기
                                }
                            });
                            alert.setMessage("You are not user.");
                            alert.show();



                            //Toast.makeText(LoginActivity.this, "You are not user.", Toast.LENGTH_SHORT).show();
                        }
                    });

                 //   return "notuser";
                }

                else{
                    Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                    intent.putExtra("name", (CharSequence) buffer);
                    intent.putExtra("mCurrentPhotoPath", (CharSequence) mCurrentPhotoPath);
                    startActivity(intent);
                }


                Log.i("유진","여기까지 옴4");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String result){

            Log.i("BTask","onPostexecute");

            super.onPostExecute(result);
        }

        protected void onCancelled(String result){
            Log.i("BTask","oncancelled");
            super.onCancelled(result);
        }

        protected void onProgressUpdate(Integer... values){
            Log.i("BTask","onProgressupdate");
            super.onProgressUpdate(values);
        }
    }

    public void captureCamera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try{
                Log.i("유진","createimagefile");
                photoFile = createImageFile();
            }catch (IOException ex){
                //ERROR occurred while creating the File
            }

            if(photoFile != null){
                Log.i("유진","createimagefile2");
                photoURI = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                //startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CROP);
            }
        }
    }

    private File createImageFile() throws IOException{

        Log.i("유진","createimagefile3");
        //Create an image file name
        String imageFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";

        //String imageFileName = "testimage" + ".jpg";
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/pathvalue/"+ imageFileName);

        //Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = storageDir.getAbsolutePath();


        Log.i("mCurrentPhotoPath", mCurrentPhotoPath);
        return storageDir;
    }

    public void getAlbum(){
        //앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_TAKE_ALBUM);
    }


    public void galleryAddPic(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.i("onActivityResult", "CALL");
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQUEST_TAKE_PHOTO:
                isAlbum = false;
                //cropImage();
                break;
            case REQUEST_TAKE_ALBUM:
                isAlbum = true;
                File albumFile = null;
                try{
                    albumFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(albumURI != null){
                    albumURI = Uri.fromFile(albumFile);
                }
                photoURI = data.getData();
                //cropImage();
                break;

            case REQUEST_IMAGE_CROP:
                Log.i("cropImage", "Call4");
                galleryAddPic();
                //업로드

              //  CheckTypesTask task = new CheckTypesTask();
              //  task.execute();

                new Thread(new Runnable(){
                    @Override
                    public void run() {

                        uploadFile(mCurrentPhotoPath);
                    }
                }).start();

                break;
        }
    }


    public int uploadFile(String sourceFileUri){

        Log.i("cropImage", "Call6");
        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

           // dialog.dismiss();

            Log.e("uploadFile", "Source File not exist :" +sourceFileUri);

            runOnUiThread(new Runnable() {
                public void run() {
                    //messageText.setText("Source File not exist :" +uploadFilePath + "" + uploadFileName);
                }
            });

            return 0;

        }
        else
        {
            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);

                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){


                    final HttpURLConnection finalConn = conn;
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(LoginActivity.this, "File Upload Complete.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

               // dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                       // messageText.setText("MalformedURLException Exception : check script url.");
                        Toast.makeText(LoginActivity.this, "MalformedURLException",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

              //  dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                       // messageText.setText("Got Exception : see logcat ");
                        Toast.makeText(LoginActivity.this, "Got Exception : see logcat ",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upload file Exception", "Exception : " + e.getMessage(), e);
            }
          //  dialog.dismiss();
            new BTask().execute();
            return serverResponseCode;

        } // End else block


    }
}

