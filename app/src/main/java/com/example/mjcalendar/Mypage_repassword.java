package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Mypage_repassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_repassword);

        EditText now_pass = (EditText) findViewById(R.id.now_pass);
        EditText new_pass = (EditText) findViewById(R.id.new_pass);
        EditText new_repass = (EditText) findViewById(R.id.new_repass);

        ImageButton delete_newpass = (ImageButton) findViewById(R.id.delete_newpass);
        ImageButton delete_nowpass = (ImageButton) findViewById(R.id.delete_nowpass);
        ImageButton delete_newrepass = (ImageButton) findViewById(R.id.delete_newrepass);

        Button cancel_repass = (Button) findViewById(R.id.cancel_repass);
        Button check_repass = (Button) findViewById(R.id.check_repass);

        cancel_repass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(Mypage_repassword.this,Mypage_main.class);
                Mypage_repassword.this.startActivity(cancelIntent);
            }
        });
    }
}
