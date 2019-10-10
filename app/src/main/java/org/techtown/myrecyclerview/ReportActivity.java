package org.techtown.myrecyclerview;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        Button save1 = (Button) findViewById(R.id.button7);
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    public void onPause(){
        super.onPause();

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE); // UI 상태를 저장합니다.
        SharedPreferences.Editor editor = pref.edit(); // Editor를 불러옵니다.

        EditText text2 = (EditText)findViewById(R.id.editText3);
        EditText text3 = (EditText)findViewById(R.id.editText6);
        EditText text4 = (EditText)findViewById(R.id.editText7);
        EditText text5 = (EditText)findViewById(R.id.editText8);


        // 저장할 값들을 입력합니다.
        editor.putString("text2", text2.getText().toString());
        editor.putString("text3", text3.getText().toString());
        editor.putString("text4", text4.getText().toString());
        editor.putString("text5", text5.getText().toString());



        editor.commit(); // 저장합니다.



    }








    public void onStop(){ // 어플리케이션이 화면에서 사라질때
        super.onStop();



    }


    public void onResume(){
        super.onResume();

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        EditText text2 = (EditText)findViewById(R.id.editText3);
        EditText text3 = (EditText)findViewById(R.id.editText6);
        EditText text4 = (EditText)findViewById(R.id.editText7);
        EditText text5 = (EditText)findViewById(R.id.editText8);


        String Text2 = pref.getString("text2","");
        String Text3 = pref.getString("text3","");
        String Text4 = pref.getString("text4","");
        String Text5 = pref.getString("text5","");


        text2.setText(Text2);
        text3.setText(Text3);
        text4.setText(Text4);
        text5.setText(Text5);

    }



}
