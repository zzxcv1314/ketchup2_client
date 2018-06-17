package com.example.greenjoa.imagetoserver1019;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DietActivity extends AppCompatActivity {

    private TextView result, today;
    private Spinner spinner;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdfNow = new SimpleDateFormat("d");
    SimpleDateFormat sdfNow2 = new SimpleDateFormat("E MMM dd", new Locale("en","US"));
    SimpleDateFormat sdfNow3 = new SimpleDateFormat("E", new Locale("en","US"));
    SimpleDateFormat sdfNow4 = new SimpleDateFormat("MMM", new Locale("en", "US"));

    String formatDate = sdfNow.format(date);
    String formatDate2 = sdfNow2.format(date);
    String formatDate3 = sdfNow3.format(date);
    String formatDate4 = sdfNow4.format(date);

    Calendar calendar = new GregorianCalendar(Locale.KOREA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);

        result = (TextView) findViewById(R.id.result);
        today = (TextView) findViewById(R.id.today);
        spinner = (Spinner)findViewById(R.id.spinner);

        if(formatDate.equals("1")){
            calendar.add(Calendar.MONTH, 1);
            SimpleDateFormat fm = new SimpleDateFormat("MMM", new Locale("en", "US"));
            formatDate4 = fm.format(calendar.getTime());
            Log.i("tag",formatDate4);
        }

//        formatDate4 = "Nov";
//        formatDate3 = "Mon";
//        formatDate = "20";

        getWebsite(formatDate);
        today.setText(formatDate3 + " " + formatDate4 + " " + formatDate);

        switch(formatDate3){
            case "Mon":
                List<String> spinner_items1 = new ArrayList<>();
                ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_items1);

                spinner_items1.add("▼SELECT");
                spinner_items1.add("Monday");
                spinner_items1.add("Tuesday");
                spinner_items1.add("Wednesday");
                spinner_items1.add("Thursday");
                spinner_items1.add("Friday");

                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter1);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                        switch(position){
                            case 1:
                                getWebsite(formatDate);
                                today.setText(formatDate3 + " " + formatDate4 + " " + formatDate);
                                break;
                            case 2:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+1));
                                today.setText("Tue "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+1));
                                break;
                            case 3:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+2));
                                today.setText("Wed "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+2));
                                break;
                            case 4:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+3));
                                today.setText("Thu "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+3));
                                break;
                            case 5:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+4));
                                today.setText("Fri "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+4));
                                break;
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        getWebsite(formatDate);
                    }
                });
                break;
            case "Tue":
                List<String> spinner_items2 = new ArrayList<>();
                ArrayAdapter<String> adapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_items2);

                spinner_items2.add("▼SELECT");
                spinner_items2.add("Tuesday");
                spinner_items2.add("Wednesday");
                spinner_items2.add("Thursday");
                spinner_items2.add("Friday");

                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter2);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                        switch(position){
                            case 1:
                                getWebsite(formatDate);
                                today.setText(formatDate3 + " " + formatDate4 + " " + formatDate);
                                break;
                            case 2:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+1));
                                today.setText("Wed "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+1));
                                break;
                            case 3:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+2));
                                today.setText("Thu "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+2));
                                break;
                            case 4:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+3));
                                today.setText("Fri "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+3));
                                break;
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        getWebsite(formatDate);
                    }
                });
                break;
            case "Wed":
                List<String> spinner_items3 = new ArrayList<>();
                ArrayAdapter<String> adapter3=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_items3);

                spinner_items3.add("▼SELECT");
                spinner_items3.add("Wednesday");
                spinner_items3.add("Thursday");
                spinner_items3.add("Friday");

                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter3);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                        switch(position){
                            case 1:
                                getWebsite(formatDate);
                                today.setText(formatDate3 + " " + formatDate4 + " " + formatDate);
                                break;
                            case 2:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+1));
                                today.setText("Thu "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+1));
                                break;
                            case 3:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+2));
                                today.setText("Fri "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+2));
                                break;
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        getWebsite(formatDate);
                    }
                });
                break;
            case "Thu":
                List<String> spinner_items4 = new ArrayList<>();
                ArrayAdapter<String> adapter4=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_items4);

                spinner_items4.add("▼SELECT");
                spinner_items4.add("Thursday");
                spinner_items4.add("Friday");

                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter4);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                        switch(position){
                            case 1:
                                getWebsite(formatDate);
                                today.setText(formatDate3 + " " + formatDate4 + " " + formatDate);
                                break;
                            case 2:
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)+1));
                                today.setText("Fri "+ formatDate4 + " " + String.valueOf(Integer.parseInt(formatDate)+1));
                                break;
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        getWebsite(formatDate);
                    }
                });
                break;
            case "Fri":
                List<String> spinner_items5 = new ArrayList<>();
                ArrayAdapter<String> adapter5=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_items5);

                spinner_items5.add("▼SELECT");
                spinner_items5.add("Friday");

                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter5);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                        switch(position){
                            case 1:
                                getWebsite(formatDate);
                                today.setText(formatDate3 + " " + formatDate4 + " " + formatDate);
                                break;
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        getWebsite(formatDate);
                    }
                });
                break;
            case "Sat":
                List<String> spinner_items6 = new ArrayList<>();
                ArrayAdapter<String> adapter6=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_items6);

                spinner_items6.add("▼SELECT");
                //spinner_items6.add("Saturday");

                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter6);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        result.setText(" ");
                        today.setText(formatDate3 + " " + formatDate4 + " " + formatDate);
                    }
                });

                break;
            case "Sun":
                List<String> spinner_items7 = new ArrayList<>();
                ArrayAdapter<String> adapter7=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_items7);

                spinner_items7.add("▼SELECT");
                //spinner_items6.add("Saturday");

                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter7);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        result.setText(" ");
                        today.setText(formatDate3 + " " + formatDate4 + " " + formatDate);
                    }
                });
                break;
        }

/*
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //String str = adapterView.getItemAtPosition(position).toString();
//                if(position!=0){
//                    Toast.makeText(getApplicationContext(), "요일"+(position)+" 클릭되었습니다", Toast.LENGTH_SHORT).show();
//                }

                switch(position){
                    case 1:
                        Toast.makeText(getApplicationContext(), String.valueOf(Integer.parseInt(formatDate)-4), Toast.LENGTH_SHORT).show();
                        if(Integer.parseInt(formatDate)-4 > 0){
                            if(formatDate3=="Fri")
                                getWebsite(String.valueOf(Integer.parseInt(formatDate)-4));
                        }

                        //Toast.makeText(getApplicationContext(), "Monday", Toast.LENGTH_SHORT).show();
                        //getWebsite("1");
                        today.setText("월요일");
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "Tuesday", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "Wednesday", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), "Thursday", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), "Friday", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(getApplicationContext(), "Saturday", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                getWebsite(formatDate);
            }
        });
  */

        // ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.number, android.R.layout.simple_spinner_item);
        //spinner.setAdapter(adapter);

        // getWebsite(formatDate);
        // today.setText(formatDate2);
    }

    private void getWebsite(final String a) {
        new Thread(new Runnable() {

            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("http://www.seoulnoin.or.kr/senior/life2.asp#carte").get();

                    Elements links = doc.select("table.calendar_view"); //dl

//                    for (Element link : links) {
//                        if (link.getElementsByTag("td").text().equals(a)) {
//                            for (int i = 0; i < 5; i++)
//                                builder.append(link.select("p").get(i).text()).append("\n");
//                        }
//                    }
                    for (Element link : links) {
                        Elements link2 = link.select("td");
                        for(Element link3 : link2){
                            if (link3.getElementsByTag("b").text().equals(a)) {
                                for (int i = 0; i < 6; i++) {
                                    builder.append(link3.select("p").get(i).text()).append("\n");
                                }
                            }
                        }
                    }

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    }
