package com.example.mjcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    }
}
