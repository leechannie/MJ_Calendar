package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Mypage_rename extends AppCompatActivity {
    private EditText rename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_rename);

        rename = (EditText) findViewById(R.id.rename);

        ImageButton delete_rename = (ImageButton) findViewById(R.id.delete_rename);

        Button check_rename = (Button) findViewById(R.id.check_rename);
        Button cancel_rename = (Button) findViewById(R.id.cancel_rename);

        delete_rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rename.setText("");
            }
        });

        cancel_rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(Mypage_rename.this,Mypage_main.class);
                Mypage_rename.this.startActivity(cancelIntent);
            }
        });
    }
}
