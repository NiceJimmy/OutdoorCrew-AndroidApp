package org.techtown.myrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity2 extends AppCompatActivity {
    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 pref = getSharedPreferences("pref", MODE_PRIVATE);
                String memberlist = pref.getString("memberlist", "");

                try {
                    jsonArray = new JSONArray(memberlist);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                String ID = null;
                String PW = null;
                for (int i = 0; i < jsonArray.length(); i++)

                {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ID = jsonObject.getString("ID");
                        PW = jsonObject.getString("PW");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


                EditText id = (EditText) findViewById(R.id.editText5);
                String ID2 = id.getText().toString();
                EditText pw = (EditText) findViewById(R.id.editText4);
                String PW2 = pw.getText().toString();



                if(ID2.equals(ID)) {

                    if(PW2.equals(PW)){

                        Toast.makeText(getApplicationContext(), "입장합니다! ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("loginfo",id.getText());
                        startActivity(intent);







                    }


                    else {

                        Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다 ", Toast.LENGTH_LONG).show();
                    }


                }

                if(!ID2.equals(ID)){
                    Toast.makeText(getApplicationContext(), "아이디가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                }





            }
        });
    }





}
