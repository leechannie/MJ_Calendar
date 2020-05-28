package com.example.mjcalendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.net.URL;


public class Mypage_main extends AppCompatActivity {
    ImageView mypage_ima;
    private static final int REQUEST_CODE = 0;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        setContentView(R.layout.activity_mypage_main);

        TextView mypage_name = (TextView) findViewById(R.id.mypage_name);
        show_name();
        TextView mypage_email = (TextView) findViewById(R.id.mypage_email);
        show_id();

        mypage_ima = (ImageView) findViewById(R.id.mypage_ima);
        mypage_ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, REQUEST_CODE);
            }
        });
            show_image();


        ImageButton rename_button = (ImageButton) findViewById(R.id.rename_button);
        ImageButton repass_button = (ImageButton) findViewById(R.id.repass_button);

        Button logout_button = (Button) findViewById(R.id.logout_button);
        Button delete_button = (Button) findViewById(R.id.delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_member();
            }
        });

        rename_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mypage_rename.class);
                startActivity(intent);

            }
        });

        repass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mypage_repassword.class);
                startActivity(intent);
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login_Main.class);
                startActivity(intent);
                startToast("로그아웃");
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(getApplicationContext(), Login_Main.class);
            startActivity(intent);
        }

    }

    // toast 띄우기
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //아이디 보여주기
    private void show_id() {
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
    private void show_name() {
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
                        mypage_name.setText((String) document.getData().get("name"));
                    } else {
                        // Log.d(TAG, "No such document");
                    }
                } else {
                    startToast("이름 가져오기에 실패");
                }
            }
        });
    }

    //갤러리에서 사진 골라, 프로필 사진 업로드
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri imgage = data.getData();
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    //mypage_ima.setImageBitmap(img);
                    //startToast("사진선택 성공");
                } catch (Exception e) {
                    startToast("사진 선택 실패");
                }

                //Firebase storage에 사진 저장
                StorageReference riversRef = mStorageRef.child("users/" + user.getEmail() + ".jpg");

                riversRef.putFile(imgage)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                //다운로드 url 생성
                                Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String photoStringLink = uri.toString();
                                        //파이어베이스 database에 사진 url 넣기
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();

                                        DocumentReference washingtonRef = db.collection("users").document(user.getUid());
                                        if (photoStringLink.length() > 0) {
                                            washingtonRef
                                                    .update("image", photoStringLink)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            startToast("프로필 사진 저장 성공");
                                                            show_image();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            startToast("프로필 사진 저장 실패");
                                                        }
                                                    });
                                        } else {
                                            startToast("프로필 사진을 선택해주세요.");
                                        }

                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                            }
                        });

            } else if (resultCode == RESULT_CANCELED) {
                startToast("사진 선택 취소");
            }
        }
    }
    // 이미지 보여주기
    private void show_image(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("users").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if ((String) document.getData().get("image") != ""){
                        if (document.exists()) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            String image_url = new String((String) document.getData().get("image"));
                            Uri url = Uri.parse(image_url);
                            try {
                                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image_url).getContent());
                                mypage_ima.setImageBitmap(bitmap);
                            } catch (Exception e) {
                                startToast("실패" + e);
                            }
                        }else {
                            // Log.d(TAG, "No such document");
                        }
                    } else {
                        // Log.d(TAG, "No such document");
                    }
                } else {
                    startToast("이미지 가져오기에 실패");
                }
            }
        });
    }

    // 회원탈퇴
    private void delete_member() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Firebase Authentication 삭제
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Firebase Authentication 삭제
                        } else {
                            startToast("탈퇴를 다시 시도해주세요.");
                        }
                    }
                });

        // Firebase Database 삭제
        db.collection("users").document(user.getUid())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Firebase Database 삭제
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("탈퇴를 다시 시도해주세요.");
                    }
                });

        // Firebase Storage 삭제
        StorageReference riversRef = mStorageRef.child("users/" + user.getEmail() + ".jpg");
        riversRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Firebase Storage 삭제
                startToast("탈퇴 되었습니다. 감사합니다.");
                Intent intent = new Intent(getApplicationContext(),Login_Main.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
             // storage는 꼭 없을 수도 있음 그래서 실패할 수 있음.
                startToast("탈퇴 되었습니다. 감사합니다.");
                Intent intent = new Intent(getApplicationContext(),Login_Main.class);
                startActivity(intent);
            }
        });
    }

    //뒤로 갈 때 새로운 창으로 띄우기
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainCalendar.class);
        startActivity(intent);
    }

}

