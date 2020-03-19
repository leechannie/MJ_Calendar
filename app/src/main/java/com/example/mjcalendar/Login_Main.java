package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mjcalendar.R;

public class Login_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText login_email = (EditText) findViewById(R.id.login_email);
        EditText login_pass = (EditText) findViewById(R.id.login_pass);

        ImageView mark = (ImageView) findViewById(R.id.mark);

        Button login_button = (Button) findViewById(R.id.login_button);
        Button register_button = (Button) findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login_Main.this, com.example.mjcalendar.Login_Register.class);
                Login_Main.this.startActivity(registerIntent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ifIntent = new Intent(Login_Main.this, com.example.mjcalendar.Mypage_main.class);
                Login_Main.this.startActivity(ifIntent);
            }
        });
    }



}
