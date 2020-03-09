package com.example.mjcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainCalendar extends AppCompatActivity {
    LinearLayout schedule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        ImageButton my_info_button = (ImageButton) findViewById(R.id.my_info);
        ImageButton search_button = (ImageButton) findViewById(R.id.search);
        Button button = (Button) findViewById(R.id.button);
        schedule = (LinearLayout) findViewById(R.id.schedule);

        my_info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(getApplicationContext(),MainCalendar_Search.class);
                //  startActivity(intent);
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainCalendar_Search.class);
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.activity_calendar_fragment, schedule, true);

                ImageButton add_button = (ImageButton) schedule.findViewById(R.id.add_button);
                Button modify_button = (Button) schedule.findViewById(R.id.CalendarList);


                add_button.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainCalendar_Add.class);
                        startActivity(intent);

                    }
                });

                modify_button.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainCalendar_Modify.class);
                        startActivity(intent);

                    }
                });


            }
        });


    }


}
