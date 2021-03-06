package com.example.mjcalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainCalendar extends Activity {
    ImageButton my_info_button;

    LinearLayout schedule;

    /**
     * 연/월 텍스트뷰
     */


    /**
     * 그리드뷰 어댑터
     */
    private GridAdapter gridAdapter;

    /**
     * 일 저장 할 리스트
     */
    private ArrayList<String> dayList;

    /**
     * 그리드뷰
     */
    private GridView gridView;

    /**
     * 캘린더 변수
     */
    private Calendar mCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);
        ImageButton todo_navi = (ImageButton) findViewById(R.id.todo_navi);
//        ImageButton friends_navi = (ImageButton) findViewById(R.id.friends_navi);
//        ImageButton notification_navi = (ImageButton) findViewById(R.id.notification_navi);

        my_info_button = (ImageButton) findViewById(R.id.my_info);
        ImageButton search_button = (ImageButton) findViewById(R.id.search);


        show_image();
        my_info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mypage_main.class);
                startActivity(intent);
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainCalendar_Search.class);
                startActivity(intent);
            }
        });

        todo_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainCalendar_TodoList.class);
                startActivity(intent);
            }
        });






//        my_info_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                color_OnClickHandler(v);
//            }
//        });





//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View view = inflater.inflate(R.layout.activity_calendar_fragment, schedule, true);
//
//                ImageButton add_button = (ImageButton) schedule.findViewById(R.id.add_button);
//                Button modify_button = (Button) schedule.findViewById(R.id.CalendarList);
//
//
//                add_button.setOnClickListener(new View.OnClickListener() {
//
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), MainCalendar_Add.class);
//                        startActivity(intent);
//
//                    }
//                });
//
//                modify_button.setOnClickListener(new View.OnClickListener() {
//
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), MainCalendar_Modify.class);
//                        startActivity(intent);
//
//                    }
//                });
//
//
//            }
//        });


        final Button button1 = (Button) findViewById(R.id.button);
        gridView = (GridView)findViewById(R.id.gridview);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
                color_OnClickHandler(view);


            }
        });

        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        button1.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));


//        tvDate.setText(4 + "/" + curMonthFormat.format(date)); //수정할때 사용
        //gridview 요일 표시
        dayList = new ArrayList<String>();
//        dayList.add("일");
//        dayList.add("월");
//        dayList.add("화");
//        dayList.add("수");
//        dayList.add("목");
//        dayList.add("금");
//        dayList.add("토");

        mCal = Calendar.getInstance();



        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);

//        mCal.set(Integer.parseInt(curYearFormat.format(date)), 5 - 1, 1); //수정할때 사용
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setText(curYearFormat.format(date) + "/" + 5);
                dayList = new ArrayList<String>();


                mCal.set(Integer.parseInt(curYearFormat.format(date)), 5 - 1, 1);

//        mCal.set(Integer.parseInt(curYearFormat.format(date)), 5 - 1, 1); //수정할때 사용
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {
                    dayList.add("");
                }
                setCalendarDate(mCal.get(Calendar.MONTH) + 1);

                gridAdapter = new GridAdapter(getApplicationContext(), dayList);
                gridView.setAdapter(gridAdapter);



            }
        });

        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

    }

    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    private void setCalendarDate(int month) {
        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }

    }

    /**
     * 그리드뷰 어댑터
     *
     */

    private class GridAdapter extends BaseAdapter {

        private final List<String> list;

        private final LayoutInflater inflater;

        /**
         * 생성자
         *
         * @param context
         * @param list
         */
        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));

            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.color_000000));

            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvItemGridView;
    }

    public void color_OnClickHandler(View view) {
        View dialogView = getLayoutInflater().inflate(R.layout.activity_calendar_fragment, null);


        ImageButton add_button = (ImageButton) dialogView.findViewById(R.id.add_button);
        Button modify_button = (Button) dialogView.findViewById(R.id.CalendarList);
        TextView everyday = (TextView) dialogView.findViewById(R.id.everyday);

        everyday.setText("흠");



        final View add = getLayoutInflater().inflate(R.layout.activity_calendar_add, null);
        EditText List_name = (EditText) add.findViewById(R.id.List_name);
        ImageButton save = (ImageButton) add.findViewById(R.id.save);


//        modify_button.setText(List_name.getText().toString());
//        modify_button.setText(receiveStr);



        modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainCalendar_Modify.class);
                startActivity(intent);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainCalendar_Add.class);
                startActivity(intent);
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int pos)
            {
                ;

                Toast.makeText(getApplicationContext(),"하이", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
                                my_info_button.setImageBitmap(bitmap);
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

    // toast 띄우기
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //다시 로그인 창으로 못 돌아가게 하기 위해 뒤로가면 앱 종료
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainCalendar.this);
        builder.setMessage("정말로 종료하시겠습니까?");
        builder.setTitle("종료 알림창")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        ActivityCompat.finishAffinity(MainCalendar.this);
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("종료 알림창");
        alert.show();
    }
}
//package com.example.mjcalendar;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//
//public class MainCalendar extends AppCompatActivity {
//    LinearLayout schedule;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar);
//
//
//        ImageButton todo_navi = (ImageButton) findViewById(R.id.todo_navi);
//        ImageButton friends_navi = (ImageButton) findViewById(R.id.friends_navi);
//        ImageButton notification_navi = (ImageButton) findViewById(R.id.notification_navi);
//
//        ImageButton my_info_button = (ImageButton) findViewById(R.id.my_info);
//        ImageButton search_button = (ImageButton) findViewById(R.id.search);
//        Button button = (Button) findViewById(R.id.button);
//        Button select = (Button) findViewById(R.id.select);
//        schedule = (LinearLayout) findViewById(R.id.schedule);
//
//
//
////        todo_navi.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                Intent intent = new Intent(getApplicationContext(), MainCalendar.class);
////                startActivity(intent);
////            }
////        });
////
////        friends_navi.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                Intent intent = new Intent(getApplicationContext(), MainCalendar.class);
////                startActivity(intent);
////            }
////        });
////
////        notification_navi.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                Intent intent = new Intent(getApplicationContext(), MainCalendar.class);
////                startActivity(intent);
////            }
////        });
//
//
//        select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), MainCalendar_Modify.class);
//                startActivity(intent);
//            }
//        });
//
//        my_info_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  Intent intent = new Intent(getApplicationContext(),MainCalendar_Search.class);
//                //  startActivity(intent);
//            }
//        });
//
//        search_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), MainCalendar_Search.class);
//                startActivity(intent);
//            }
//        });
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View view = inflater.inflate(R.layout.activity_calendar_fragment, schedule, true);
//
//                ImageButton add_button = (ImageButton) schedule.findViewById(R.id.add_button);
//                Button modify_button = (Button) schedule.findViewById(R.id.CalendarList);
//
//
//                add_button.setOnClickListener(new View.OnClickListener() {
//
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), MainCalendar_Add.class);
//                        startActivity(intent);
//
//                    }
//                });
//
//                modify_button.setOnClickListener(new View.OnClickListener() {
//
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), MainCalendar_Modify.class);
//                        startActivity(intent);
//
//                    }
//                });
//
//
//            }
//        });
//
//
//    }
//
//
//}
