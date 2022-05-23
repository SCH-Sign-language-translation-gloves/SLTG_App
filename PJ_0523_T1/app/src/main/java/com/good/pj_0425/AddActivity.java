package com.good.pj_0425;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.remnant.arduinoiot_ble.MainActivity;
import com.remnant.arduinoiot_ble.R;

public class AddActivity extends AppCompatActivity {

    //다른 클래스에서 변수를 가져오기 위한 것
//    public static Context context_main; // context 변수 선언
//    public int var; // 다른 Activity에서 접근할 변수
//    int var2 = ((MainActivity)MainActivity.context_main).var;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //다른 클래스에서 변수를 가져오기 위한 것
//        context_main = this;
        //

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText name_edit = findViewById(R.id.name_edit);
        EditText cont_edit = findViewById(R.id.cont_edit);
        Button addBtn = findViewById(R.id.add_btn);

        DAOUser dao = new DAOUser();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //입력값 변수에 담기
                String name = name_edit.getText().toString(); //이름
                String cont = cont_edit.getText().toString(); // 내용

                User user = new User("", name, cont);

                //Complete를 누를 때까지 센서값 받아오기

                new AlertDialog.Builder(AddActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                        .setMessage("수화를 동작하세요")     // 제목 부분 (직접 작성)
                        .setPositiveButton("Complete", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                            //0523_고쳐야할 부분 : Complete를 누를 때까지 센서값 받아오기
//                            if(int i=0){}

//                            btGatt.readCharacteristic(ValueCharacteristic_read);
                            public void onClick(DialogInterface dialog, int which){
//                                Log.d("Complete ", dialog);
                                Log.d("Complete", dialog + " ,  " + which);
                                Toast.makeText(getApplicationContext(), "Complete 누름", Toast.LENGTH_SHORT).show(); // 실행할 코드
                            }
                        })

//                        .setNegativeButton("Read", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
//                            public void onClick(DialogInterface dialog, int which){
//                                Toast.makeText(getApplicationContext(), "Read 누름", Toast.LENGTH_SHORT).show(); // 실행할 코드
//                            }
//                        })
                        .show();

                //

                //데이터베이스 사용자 등록
                dao.add(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();

                        //입력창 초기화
                        name_edit.setText("");
                        cont_edit.setText("");
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