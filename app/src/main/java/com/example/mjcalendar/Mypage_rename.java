package com.example.mjcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Mypage_rename extends AppCompatActivity {
    private EditText rename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_rename);

        rename = (EditText) findViewById(R.id.rename);

        ImageButton delete_rename = (ImageButton) findViewById(R.id.delete_rename);

        Button check_rename = (Button) findViewById(R.id.check_rename);
        check_rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_rename();
            }
        });

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
                Intent cancelIntent = new Intent(Mypage_rename.this, Mypage_main.class);
                Mypage_rename.this.startActivity(cancelIntent);
            }
        });
    }

    //이름 재설정
    private void set_rename() {
        String new_name = rename.getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference washingtonRef = db.collection("users").document(user.getUid());
        if (new_name.length() > 0 ) {
            washingtonRef
                    .update("name", new_name)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            startToast("이름변경 성공");
                            Intent intent = new Intent(getApplication(), Mypage_main.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            startToast("이름변경 실패");
                        }
                    });
        }else {
            startToast("변경할 이름을 입력해주세요.");
        }
    }

    // toast 공동 기능
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
