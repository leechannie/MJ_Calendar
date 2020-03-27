package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Mypage_repassword extends AppCompatActivity {
    private  EditText now_pass;
    private  EditText new_pass;
    private  EditText new_repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_repassword);

        now_pass = (EditText) findViewById(R.id.now_pass);
        new_pass = (EditText) findViewById(R.id.new_pass);
        new_repass = (EditText) findViewById(R.id.new_repass);

        ImageButton delete_nowpass = (ImageButton) findViewById(R.id.delete_nowpass);
        ImageButton delete_newpass = (ImageButton) findViewById(R.id.delete_newpass);
        ImageButton delete_newrepass = (ImageButton) findViewById(R.id.delete_newrepass);

        Button cancel_repass = (Button) findViewById(R.id.cancel_repass);
        Button check_repass = (Button) findViewById(R.id.check_repass);

        delete_nowpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now_pass.setText("");
            }
        });

        delete_newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_pass.setText("");
            }
        });

        delete_newrepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_repass.setText("");
            }
        });

        cancel_repass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(Mypage_repassword.this,Mypage_main.class);
                Mypage_repassword.this.startActivity(cancelIntent);
            }
        });
    }
}
