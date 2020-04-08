package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainCalendar_Fragment extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_fragment);

        final TextView everyday= findViewById(R.id.everyday);
        ImageButton add_button =  findViewById(R.id.add_button);
        View add = getLayoutInflater().inflate(R.layout.activity_calendar_add, null);
        final EditText List_name = (EditText) add.findViewById(R.id.List_name);

        everyday.setText(List_name.getText());




    }
}
