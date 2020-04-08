package com.example.mjcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;


/*
 Manager User의 Setting option을 접근하기 위한 page
 */
public class Manager_setting extends Activity {
    Button btn_logout;
    Button btn_academic;
    Button btn_usermanage;
    Button btn_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_manager_setting );





    }
}
