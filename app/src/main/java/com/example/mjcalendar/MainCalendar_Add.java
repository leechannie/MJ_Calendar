package com.example.mjcalendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainCalendar_Add extends AppCompatActivity {
    int Start_y=0, Start_m=0, Start_d=0, Start_h=0, Start_mi=0;
    int End_y=0, End_m=0, End_d=0, End_h=0, End_mi=0;

    EditText List_name;
    String shared = "file";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_add);

        Button color_change = (Button) findViewById(R.id.color_change);
        ImageButton cancel = (ImageButton) findViewById(R.id.cancel);
        ImageButton save = (ImageButton) findViewById(R.id.save);


        ImageButton time = (ImageButton) findViewById(R.id.time);
        Button time_detail_button = (Button) findViewById(R.id.time_detail);
        ImageButton clock = (ImageButton) findViewById(R.id.clock);
        Button clock_detail = (Button) findViewById(R.id.clock_detail);
        ImageButton add = (ImageButton) findViewById(R.id.add);
        Button add_detail = (Button) findViewById(R.id.add_detail);
         List_name = (EditText) findViewById(R.id.List_name);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCalendar_Add.this, MainCalendar_Fragment.class);
                intent.putExtra("sendData",List_name.getText().toString());// 이 메서드를 통해 데이터를 전달합니다.
                startActivity(intent);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_OnClickHandler(v);
            }
        });
        time_detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_OnClickHandler(v);
            }
        });

        color_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color_OnClickHandler(v);
            }
        });

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock_OnClickHandler(v);
            }
        });
        clock_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock_OnClickHandler(v);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_OnClickHandler(v);
            }
        });
        add_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_OnClickHandler(v);
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("key","");
        List_name.setText(value);



    }
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences  sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        String value = List_name.getText().toString();
        editor.putString("key", value);
        editor.commit();
    }



//    void show_time() {
//        final List<String> ListItems = new ArrayList<>();
//        ListItems.add("없음");
//        ListItems.add("이벤트 당일 (오전 9시)");
//        ListItems.add("1일전 (오전 9시)");
//        ListItems.add("2일전 (오전 9시)");
//        ListItems.add("1주전");
//        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("                            알림");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int pos) {
//                String selectedText = items[pos].toString();
//                Toast.makeText(MainCalendar_Add.this, selectedText, Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.show();
//    }
//
//    void show_clock() {
//        final List<String> ListItems = new ArrayList<>();
//        ListItems.add("없음");
//        ListItems.add("매일");
//        ListItems.add("매주 월요일");
//        ListItems.add("매월 세번째 월요일");
//        ListItems.add("매년 3월 16일");
//        ListItems.add("주중 매일 (월-금)");
//        ListItems.add("사용자화");
//        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("                            반복");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int pos) {
//                String selectedText = items[pos].toString();
//                Toast.makeText(MainCalendar_Add.this, selectedText, Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.show();
//    }
public void color_OnClickHandler(View view) {
    View dialogView = getLayoutInflater().inflate(R.layout.activity_calendar_color_fragment, null);


    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(dialogView);

    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int pos)
        {
            ;

            Toast.makeText(getApplicationContext(),"하이", Toast.LENGTH_LONG).show();
        }
    });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();
}

    public void time_OnClickHandler(View view) {
        View dialogView = getLayoutInflater().inflate(R.layout.activity_calendar_time_fragment, null);

        ImageButton Start_date_imagebutton = dialogView.findViewById(R.id.Start_date_imagebutton);
        Start_date_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_showDate();
            }
        });
        Button Start_date_button = dialogView.findViewById(R.id.Start_date_button);
        Start_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_showDate();
            }
        });


        ImageButton Start_time_imagebutton = dialogView.findViewById(R.id.Start_time_imagebutton);
        Start_time_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_showTime();
            }
        });

        Button Start_time_button = dialogView.findViewById(R.id.Start_time_button);
        Start_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_showTime();
            }
        });



        Button button2 = dialogView.findViewById(R.id.Checkbutton1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), Start_y+"."+Start_m+"."+Start_d+"\n"+Start_h+":" + Start_mi, Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton End_date_imagebutton = dialogView.findViewById(R.id.End_date_imagebutton);
        End_date_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                End_showDate();
            }
        });

        Button End_date_button = dialogView.findViewById(R.id.End_date_button);
        End_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                End_showDate();
            }
        });


        ImageButton End_time_imagebutton = dialogView.findViewById(R.id.End_time_imagebutton);
        End_time_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                End_showTime();
            }
        });

        Button End_time_button = dialogView.findViewById(R.id.End_time_button);
        End_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                End_showTime();
            }
        });



        Button button3 = dialogView.findViewById(R.id.Checkbutton2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), End_y+"."+End_m+"."+End_d+"\n"+End_h+":" + End_mi, Toast.LENGTH_SHORT).show();
            }
        });




        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int pos)
            {
                ;

                Toast.makeText(getApplicationContext(),"하이", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void clock_OnClickHandler(View view) {
        View dialogView = getLayoutInflater().inflate(R.layout.activity_calendar_alarm_fragment, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int pos)
            {
                ;

                Toast.makeText(getApplicationContext(),"하이", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void add_OnClickHandler(View view) {
        View dialogView = getLayoutInflater().inflate(R.layout.activity_calendar_add_fragment, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int pos)
            {
                ;

                Toast.makeText(getApplicationContext(),"하이", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
