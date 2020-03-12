package com.example.mjcalendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainCalendar_Time_Fragment extends AppCompatActivity {

    int Start_y=0, Start_m=0, Start_d=0, Start_h=0, Start_mi=0;
    int End_y=0, End_m=0, End_d=0, End_h=0, End_mi=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_time_fragment);


        ImageButton Start_date_imagebutton = findViewById(R.id.Start_date_imagebutton);
        Start_date_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_showDate();
            }
        });

        Button Start_date_button = findViewById(R.id.Start_date_button);
        Start_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_showDate();
            }
        });


        ImageButton Start_time_imagebutton = findViewById(R.id.Start_time_imagebutton);
        Start_time_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_showTime();
            }
        });

        Button Start_time_button = findViewById(R.id.Start_time_button);
        Start_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_showTime();
            }
        });



        Button button2 = findViewById(R.id.Checkbutton1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), Start_y+"."+Start_m+"."+Start_d+"\n"+Start_h+":" + Start_mi, Toast.LENGTH_SHORT).show();
            }
        });


        ImageButton End_date_imagebutton = findViewById(R.id.End_date_imagebutton);
        End_date_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                End_showDate();
            }
        });

        Button End_date_button = findViewById(R.id.End_date_button);
        End_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                End_showDate();
            }
        });


        ImageButton End_time_imagebutton = findViewById(R.id.End_time_imagebutton);
        End_time_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                End_showTime();
            }
        });

        Button End_time_button = findViewById(R.id.End_time_button);
        End_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                End_showTime();
            }
        });



        Button button3 = findViewById(R.id.Checkbutton2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), End_y+"."+End_m+"."+End_d+"\n"+End_h+":" + End_mi, Toast.LENGTH_SHORT).show();
            }
        });
    }
    void Start_showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Start_y = year;
                Start_m = month+1;
                Start_d = dayOfMonth;

            }
        },2019, 1, 11);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }

    void Start_showTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Start_h = hourOfDay;
                Start_mi = minute;

            }
        }, 21, 12, true);

        timePickerDialog.setMessage("메시지");
        timePickerDialog.show();
    }

    void End_showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                End_y = year;
                End_m = month+1;
                End_d = dayOfMonth;

            }
        },2019, 1, 11);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }

    void End_showTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                End_h = hourOfDay;
                End_mi = minute;

            }
        }, 21, 12, true);

        timePickerDialog.setMessage("메시지");
        timePickerDialog.show();
    }
}
