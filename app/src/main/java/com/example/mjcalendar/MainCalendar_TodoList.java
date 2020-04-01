package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainCalendar_TodoList extends AppCompatActivity {
    Button boxAddButton ;
    Button Button4;
    View TodoAddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_todolist);
        ImageButton calendar_navi = (ImageButton) findViewById(R.id.calendar_navi);
//        ImageButton friends_navi = (ImageButton) findViewById(R.id.friends_navi);
//        ImageButton notification_navi = (ImageButton) findViewById(R.id.notification_navi);

        calendar_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainCalendar.class);
                startActivity(intent);
            }
        });
        //도진 테스트 코드
        boxAddButton = (Button) findViewById(R.id.boxAddButton);
        Button4 = (Button)findViewById(R.id.button4);
        TodoAddButton = (View)findViewById(R.id.TodoAddButton);
        TodoAddButton.setVisibility(View.INVISIBLE);
        boxAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                TodoAddButton.setVisibility(View.VISIBLE);
            }
        });

        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // 체크박스 생성해줘야됨.
                TodoAddButton.setVisibility(View.INVISIBLE);
            }
        });
//        boxAddButton = (Button) findViewById(R.id.boxAddButton);
//        boxAddButton.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view){
//
//                                            }
//                                        });
    }

}
