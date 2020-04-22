package com.example.mjcalendar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.example.mjcalendar.R.layout.activity_manager_academic;


public class Manager_academic extends AppCompatActivity {
    Switch sw_auto_update;
    TextView tv_info;
    TextView tv_update_log;
    Spinner spinner_updates;
    Button btn_update_now;
    Button btn_load_update;
    Button btn_mju_home;
    Toolbar toolbar;

    private String htmlPageUrl = "www.mju.ac.kr/mjukr/262/subview.do";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( activity_manager_academic );


        sw_auto_update = (Switch) findViewById( R.id.switch_auto_update );
        tv_info = (TextView) findViewById( R.id.text_information );
        tv_update_log = (TextView) findViewById( R.id.update_log );
        spinner_updates = (Spinner) findViewById( R.id.spinner_updates );
        btn_update_now = (Button) findViewById( R.id.btn_update );
        btn_load_update = (Button) findViewById( R.id.btn_load_update );
        btn_mju_home = (Button) findViewById( R.id.btn_mju_homepage );

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //스위치 온 오프 기능 정의
        sw_auto_update.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //auto update 상태
                } else {
                    //manual update 상태
                }
            }
        });

        // 스크롤 가능한 텍스트 뷰로 설정
        tv_update_log.setMovementMethod( new ScrollingMovementMethod() );

        btn_update_now.setOnClickListener( new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("크롤링 중");
                try {
                    Document doc = Jsoup.connect( htmlPageUrl ).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private class
}
