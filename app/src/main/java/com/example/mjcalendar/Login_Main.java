package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mjcalendar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.os.Process.myPid;

public class Login_Main extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText login_email;
    private EditText login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        login_email = (EditText) findViewById(R.id.login_email);
        login_pass = (EditText) findViewById(R.id.login_pass);

        ImageView mark = (ImageView) findViewById(R.id.mark);

        Button login_button = (Button) findViewById(R.id.login_button);
        Button register_button = (Button) findViewById(R.id.register_button);

        ImageButton delete_loginid = (ImageButton) findViewById(R.id.delete_loginid) ;
        ImageButton delete_loginpass = (ImageButton) findViewById(R.id.delete_loginpass) ;

        // 로그인 버튼
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // 회원가입 페이지로 이동
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login_Register.class);
                startActivity(intent);
            }
        });
        // 텍스트 전체 삭제 버튼
        delete_loginid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_email.setText("");
            }
        });
        delete_loginpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_pass.setText("");
            }
        });

}
    // 로그인 기능 구현
    private void loginUser() {
        String email = login_email.getText().toString();
        String password = login_pass.getText().toString();

        if (email.length() > 0 && password.length() > 0) { //세 개의 입력칸이 모두 값이 입력될때
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인");
                                Intent intent = new Intent(getApplication(), MainCalendar.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                               if(task.getException() != null){
                                   startToast("이메일 혹은 비밀번호를 다시 확인해주세요.");
                               }
                            }

                            // ...
                        }
                    });
        } else {
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }
    }

    // toast 공동 기능
    private void startToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

}
