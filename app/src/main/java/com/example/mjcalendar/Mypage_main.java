package com.example.mjcalendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class Mypage_main extends AppCompatActivity {
    ImageView mypage_ima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_main);

        TextView mypage_name = (TextView) findViewById(R.id.mypage_name);
        TextView mypage_email = (TextView) findViewById(R.id.mypage_email);

        mypage_ima = (ImageView) findViewById(R.id.mypage_ima);
        ImageButton rename_button = (ImageButton) findViewById(R.id.rename_button);
        ImageButton repass_button = (ImageButton) findViewById(R.id.repass_button);

        Button logout_button = (Button) findViewById(R.id.logout_button);
        Button delete_button = (Button) findViewById(R.id.delete_button);

        rename_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mypage_rename.class);
                startActivity(intent);

            }
        });

        repass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mypage_repassword.class);
                startActivity(intent);
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login_Main.class);
                startActivity(intent);
            }
        });


    }

}