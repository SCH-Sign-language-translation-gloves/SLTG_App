package com.good.pj_0425;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Timer;
import java.util.TimerTask;

public class AddActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText name_edit = findViewById(R.id.name_edit);
        EditText explan_edit = findViewById(R.id.age_edit);
        Button addBtn = findViewById(R.id.add_btn);

        DAOUser dao = new DAOUser();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ///
                new AlertDialog.Builder(AddActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                        .setMessage("수화를 동작하세요")     // 제목 부분 (직접 작성)
                        .setPositiveButton("Complete", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(getApplicationContext(), "Complete 누름", Toast.LENGTH_SHORT).show(); // 실행할 코드
                            }
                        })
                        .setNegativeButton("Read", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(getApplicationContext(), "Read 누름", Toast.LENGTH_SHORT).show(); // 실행할 코드
                            }
                        })
                        .show();


                ///


//                ProgressDialog oDialog = new ProgressDialog(AddActivity.this,
//                        android.R.style.Theme_DeviceDefault_Light_Dialog);
//                oDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                oDialog.setMessage("수화를 입력하세요");
//
//                oDialog.show();


                ///
                showDialog(1); // 대화상자 호출

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 3초가 지나면 다이얼로그 닫기
                        TimerTask task = new TimerTask(){
                            @Override
                            public void run() {
                                removeDialog(1);
                            }
                        };

                        Timer timer = new Timer();
                        timer.schedule(task, 3000);
                    }
                });
                thread.start();


                ///

                //입력값 변수에 담기
                String name = name_edit.getText().toString(); //이름
                String explan = explan_edit.getText().toString(); // 설명

                User user = new User("", name, explan);


                //데이터베이스 사용자 등록
                dao.add(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();

                        //입력창 초기화
                        name_edit.setText("");
                        explan_edit.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "실패:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }//onClick
        });

        //리스트 버튼
        Button listBtn = findViewById(R.id.list_btn);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }//onCreate
}