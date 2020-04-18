package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


/*
 Manager User의 Setting option을 접근하기 위한 page
 */
public class Manager_setting extends AppCompatActivity {
    Button btn_logout;
    Button btn_academic;
    Button btn_usermanage;
    Button btn_notice;
//    ImageButton btn_back;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_manager_setting );

        btn_logout = (Button) findViewById( R.id.btn_logout );
        btn_academic = (Button) findViewById( R.id.btn_academic );
        btn_usermanage = (Button) findViewById( R.id.btn_usermanage );
        btn_notice = (Button) findViewById( R.id.btn_notice );
//        btn_back = (ImageButton) findViewById( R.id.btn_back);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle( "관리자용" );
        setSupportActionBar(toolbar);

        btn_logout.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그아웃 이벤트 처리
            }
        });

        btn_academic.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //페이지 넘기기
                Intent intent = new Intent(getApplicationContext(), Manager_academic.class);
                startActivity(intent);
            }
        });

    }
}
