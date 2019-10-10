package org.techtown.myrecyclerview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    Handler mHandler = new Handler();
    ImageSwitcher mSwitcher,mSwitcher2;
    boolean mRunning;
    Thread mThread;

ArrayList a2 = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                String memberlist = pref.getString("memberlist", "");

                try {
                    jsonArray = new JSONArray(memberlist);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                String ID = null;
                for (int i = 0; i < jsonArray.length(); i++)

                {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ID = jsonObject.getString("ID");
                        String PW = jsonObject.getString("PW");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    a2.add(ID);


                }

                EditText et_id = (EditText) findViewById(R.id.et_id);
                String ID2 = et_id.getText().toString();
                EditText et_pw = (EditText) findViewById(R.id.et_pw);
                String PW = et_pw.getText().toString();
                EditText et_pw2 = (EditText) findViewById(R.id.et_pw2);
                String PW2 = et_pw2.getText().toString();


                if (a2.contains(ID2)) {


                    Toast.makeText(getApplicationContext(), "ID가 이미 존재합니다! ", Toast.LENGTH_LONG).show();


                }


                if (!a2.contains(ID2)) {

                    if (PW.equals(PW2)) {

                        Toast.makeText(getApplicationContext(), "회원이 되신것을 축하드립니다! ", Toast.LENGTH_LONG).show();

                        save();
                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다 ", Toast.LENGTH_LONG).show();
                    }


                }


            }
        });



    }


    public void save() {


        EditText et_id = (EditText) findViewById(R.id.et_id);
        EditText et_pw = (EditText) findViewById(R.id.et_pw);

        String ID = et_id.getText().toString();
        String PW = et_pw.getText().toString();


        try {
            jsonObject.put("ID", ID);
            jsonObject.put("PW", PW);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        jsonArray.put(jsonObject);


        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE); // UI 상태를 저장합니다.
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("memberlist", jsonArray.toString());
        editor.apply();

        Log.d("저장", "값" + jsonArray);


    }




}








