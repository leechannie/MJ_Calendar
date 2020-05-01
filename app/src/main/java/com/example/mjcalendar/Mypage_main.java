package com.example.mjcalendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;

public class Mypage_main extends AppCompatActivity {
    ImageView mypage_ima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_main);

        TextView mypage_name = (TextView) findViewById(R.id.mypage_name);
        show_name();
        TextView mypage_email = (TextView) findViewById(R.id.mypage_email);
        show_id();

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
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login_Main.class);
                startActivity(intent);
                startToast("로그아웃");
            }
      });

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent intent = new Intent(getApplicationContext(),Login_Main.class);
            startActivity(intent);
        }

    }
    // toast 띄우기
    private void startToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    //아이디 보여주기
    private void show_id(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView mypage_email = (TextView) findViewById(R.id.mypage_email);
        if (user != null) {
            // Name, email address, and profile photo Url
            String email = user.getEmail();
            mypage_email.setText(email);
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            String uid = user.getUid();
        }
    }

    //이름 보여주기
    private  void show_name(){
        final TextView mypage_name = (TextView) findViewById(R.id.mypage_name);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("users").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        mypage_name.setText((String)document.getData().get("name"));
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                       // Log.d(TAG, "No such document");
                    }
                } else {
                    startToast("이름 가져오기에 실패");
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}