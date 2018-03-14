package sdu.cs58.krichapol.animalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    //Explicit ประกาศตัวแปร
    Button btn1, btn2, btn3, btn4;
    ImageView questionImageView;
    ImageButton volumnImageButton;
    MediaPlayer mediaPlayer;// เล่นไฟล์เสียง
    int questionCount = 1;// เก็บจำนวนข้อคำถาม
    ArrayList<Integer> qID = new ArrayList<Integer>();// เป็น Array ในการสุ่มคำถาม
    String answer;// เก็บคำตอบ
    int score = 0;// รวมคะแนน

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Initial view ผูก Element กับตัวแปรบน Java
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        questionImageView = findViewById(R.id.imvQuestion);
        volumnImageButton = findViewById(R.id.imbVolumn);

        // นับจำนวนคำถาม
        for (int i=1; i <= questionCount; i++) {
            qID.add(i);
        }
        Collections.shuffle(qID); // กำหนดให้ Random คำถาม
        setQuestion(qID.remove(0));
    } // end OnCreate Method

    private void setQuestion(int qID) { // method นี้ใช้กำหนดข้อคำถามและเฉลยในแต่ละข้อ
        if (qID == 1) {
            answer = "นก";
            questionImageView.setImageResource(R.drawable.bird);
            mediaPlayer = MediaPlayer.create(this, R.raw.bird);

            ArrayList<String> choice = new ArrayList<String>();
            choice.add("นก");
            choice.add("ช้าง");
            choice.add("หมู");
            choice.add("วัว");
            Collections.shuffle(choice);
            btn1.setText(choice.remove(0));
            btn2.setText(choice.remove(0));
            btn3.setText(choice.remove(0));
            btn4.setText(choice.remove(0));
        }

    } // end setQuestion Method

    public void choiceAns(View view){ // method นี้ใช้ตรวจคำตอบ

        Button button = (Button) view;
        String buttonString = button.getText().toString();
        if (buttonString.equals(answer)){
            score++;
        }

        if (qID.isEmpty()) { // ถ้าทำครบทุกข้อ qID จะเป็นค่าว่าง
            dialogboxScore(); // เรียก dialogboxScore() Method เพื่อแสดงคะแนน
        } else { // ถ้าทำไม่ครบทุกข้อ
            setQuestion(qID.remove(0)); // เรียก setQuestion(int qID) Method แสดงคำถามข้อต่อไป
        }

    }// end choiceAns Method

    private void dialogboxScore() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("สรุปคะแนน");
        builder.setMessage("ได้คะแนน " + score + " คะแนน")
            .setCancelable(false)
            .setPositiveButton("ออกจากเกม", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            })
            .setNegativeButton("เล่นอีกครั้ง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }// end dialogboxScore Method

    public void playSound(View view) {
        mediaPlayer.start();
    } // end playSound Method

} // end Class
