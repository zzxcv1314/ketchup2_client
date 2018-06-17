package com.example.greenjoa.imagetoserver1019;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.app.Activity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class ProgramActivity extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    //private static final String TAG_ID = "id";
    private static final String TAG_FNAME = "first_name";
    private static final String TAG_LNAME = "last_name";
    //private static final String TAG_EMAIL = "email";
    //private static final String TAG_buttonon = "buttonon";
    //private static final String TAG_buttonoff = "buttonoff";
    private static final String TAG_info = "info";
    private static final String TAG_times = "times";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program);


        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://ketchup2.azurewebsites.net/PHP_connection.php");

      //  task.execute("https://ketchup2.azurewebsites.net/pro.php"); //도메인

    }




    protected void showList() {

        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i =0; i<peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                //String id = c.getString(TAG_ID);
                String first_name = c.getString(TAG_FNAME);
                String last_name = c.getString(TAG_LNAME);
                //String email = c.getString(TAG_EMAIL);
                //String buttonon = c.getString(TAG_buttonon);
                //String buttonoff = c.getString(TAG_buttonoff);
                String info = c.getString(TAG_info);
                String times = c.getString(TAG_times);


                HashMap<String, String> persons = new HashMap<String, String>();

                //persons.put(TAG_ID, id);
                persons.put(TAG_FNAME, first_name);
                persons.put(TAG_LNAME, last_name);
                //persons.put(TAG_EMAIL, email);
                //persons.put(TAG_buttonon, buttonon);
                //persons.put(TAG_buttonoff, buttonoff);
                persons.put(TAG_info, info);
                persons.put(TAG_times, times);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ProgramActivity.this, personList, R.layout.list_item,
                    new String[]{TAG_FNAME, TAG_LNAME, TAG_info, TAG_times},
                    new int[]{R.id.id, R.id.name, R.id.info,R.id.times}
            );

            list.setAdapter(adapter);
        }

        catch (JSONException e) {
            e.printStackTrace();;
        }
    }



    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json= bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                }

                catch (Exception e) {
                    return  null;
                }
            }



            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }


}

