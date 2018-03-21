package sdu.cs58.krichapol.animalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Explicit ประกาศตัวแปร
    EditText nameEditText;
    Button startButton;
    String nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial view ผูก Element กับตัวแปรบน Java
        nameEditText = findViewById(R.id.edtName);
        startButton = findViewById(R.id.btnStart);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ดึงข้อมูลที่ User ป้อนในหน้า UI มาเก็บในตัวแปรชนิด String
                nameString = nameEditText.getText().toString().trim();

                //ตรวจสอบค่าว่างในตัวแปร nameString
                if (nameString.length() == 0) {
                    Toast.makeText(getApplicationContext(), "กรุณาใส่ชื่อด้วยนะจ๊ะ", Toast.LENGTH_SHORT).show();
                } else {
                //กรณีที่ไม่มีค่าว่าง เปิดหน้า Game
                    Intent startIntent = new Intent(MainActivity.this, GameActivity.class);
                    startIntent.putExtra("Name", nameString);
                    startActivity(startIntent);
                }
            }// end onClick

        }); // end setOnClickListener

    }// end onCreate Method

}// end Class
