package com.example.mjcalendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainCalendar_Modify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_modify);

        Button color_change = (Button) findViewById(R.id.color_change);
        ImageButton cancel = (ImageButton) findViewById(R.id.cancel);
        ImageButton time = (ImageButton) findViewById(R.id.time);
        Button time_detail_button = (Button) findViewById(R.id.time_detail);
        ImageButton clock = (ImageButton) findViewById(R.id.clock);
        Button clock_detail = (Button) findViewById(R.id.clock_detail);
        ImageButton add = (ImageButton) findViewById(R.id.add);
        Button add_detail = (Button) findViewById(R.id.add_detail);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
}
