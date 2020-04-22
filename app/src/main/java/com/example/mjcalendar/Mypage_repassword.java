package com.example.mjcalendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class Mypage_repassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText send_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_repassword);

        mAuth = FirebaseAuth.getInstance();

        send_email= (EditText) findViewById(R.id.send_email);

        ImageButton delete_send = (ImageButton) findViewById(R.id.delete_send);

        Button cancel_repass = (Button) findViewById(R.id.cancel_repass);
        Button check_repass = (Button) findViewById(R.id.check_repass);
        // 비밀번호 재설정 버튼
        check_repass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        //텍스트 전체 삭제
        delete_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_email.setText("");
            }
        });

        // 비밀번호 재설정 취소
        cancel_repass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(Mypage_repassword.this, Mypage_main.class);
                Mypage_repassword.this.startActivity(cancelIntent);
            }
        });
    }

    //비밀번호 재설정 기능
    private void send() {
        String email = ((EditText) findViewById(R.id.send_email)).getText().toString();

        if (email.length() > 0 ){
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startToast("이메일을 보냈습니다.");
                                Intent intent = new Intent(getApplication(), Mypage_main.class);
                                startActivity(intent);
                            } else {
                                startToast("가입한 이메일을 다시 확인해주세요.");
                            }
                        }
                    });
        } else {
            startToast("이메일을 입력해주세요.");
        }
    }

    // toast 공동 기능
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}