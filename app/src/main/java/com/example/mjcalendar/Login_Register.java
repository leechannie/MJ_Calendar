package com.example.mjcalendar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText register_name = (EditText) findViewById(R.id.register_name);
        EditText register_email = (EditText) findViewById(R.id.register_email);
        EditText register_pass = (EditText) findViewById(R.id.register_pass);
        EditText register_repass = (EditText) findViewById(R.id.register_repass);

        ImageView register_ima = (ImageView) findViewById(R.id.register_ima);

        Button register = (Button) findViewById(R.id.register);
    }
}
