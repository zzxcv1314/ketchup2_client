package com.example.greenjoa.imagetoserver1019;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class RequestActivity extends AppCompatActivity {
    Button closePopupBtn, closePopupBtn2;
    Button showPopupBtn1, showPopupBtn2;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;
    int ans =1;
    int bns=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

//        showPopupBtn1 = (ImageButton) findViewById(R.id.showPopupBtn1);
//        showPopupBtn2 = (ImageButton) findViewById(R.id.showPopupBtn2);
        showPopupBtn1 = (Button) findViewById(R.id.showPopupBtn1);
        showPopupBtn2 = (Button) findViewById(R.id.showPopupBtn2);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);


        showPopupBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ans==1) {
                    //instantiate the popup.xml layout file
                    LayoutInflater layoutInflater = (LayoutInflater) RequestActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.popup, null);

                    closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);

                    //instantiate popup window
                    popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    //display the popup window
                    popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                    //close the popup window on button click
                    closePopupBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ans=2;
                            popupWindow.dismiss();
                        }
                    });
                }
                else if(ans==2){
                    //instantiate the popup.xml layout file
                    LayoutInflater layoutInflater = (LayoutInflater) RequestActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.no, null);

                    closePopupBtn2 = (Button) customView.findViewById(R.id.closePopupBtn2);

                    //instantiate popup window
                    popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    //display the popup window
                    popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                    //close the popup window on button click
                    closePopupBtn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ans=2;
                            popupWindow.dismiss();
                        }
                    });
                }
            }
        });

        showPopupBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bns==1) {
                    //instantiate the popup.xml layout file
                    LayoutInflater layoutInflater = (LayoutInflater) RequestActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.popup, null);

                    closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);

                    //instantiate popup window
                    popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    //display the popup window
                    popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                    //close the popup window on button click
                    closePopupBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bns=2;
                            popupWindow.dismiss();
                        }
                    });
                }
                else if(bns==2){
                    //instantiate the popup.xml layout file
                    LayoutInflater layoutInflater = (LayoutInflater) RequestActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.no, null);

                    closePopupBtn2 = (Button) customView.findViewById(R.id.closePopupBtn2);

                    //instantiate popup window
                    popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    //display the popup window
                    popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                    //close the popup window on button click
                    closePopupBtn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bns=2;
                            popupWindow.dismiss();
                        }
                    });
                }
            }
        });
    }
}
