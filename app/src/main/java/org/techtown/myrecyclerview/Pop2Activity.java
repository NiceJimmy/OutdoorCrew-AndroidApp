package org.techtown.myrecyclerview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Pop2Activity extends Activity { // 팝업액티비티에서 이부분 매우매우 중요하다.
JSONObject jsonObject = new JSONObject();
JSONArray jsonArray = new JSONArray();
String ID2;
int position22;
String position221;
    TextView txtText;
    ArrayList a2 = new ArrayList();
    ArrayList a3 = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pop2);




        txtText = (TextView)findViewById(R.id.txtText);

        SharedPreferences pref = getSharedPreferences("IDinfo", MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
        ID2 = pref.getString("ID", ""); // 로그인 페이지에서 저장한 아이디값을 로드한다.
    }

    public void mOnYES(View v){



        SharedPreferences pref = getSharedPreferences("pref556", MODE_PRIVATE);
        String memberlist56 = pref.getString("memberlist556", "");

        try {
            jsonArray = new JSONArray(memberlist56);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String ID = null;
        for (int i = 0; i < jsonArray.length(); i++)

        {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ID = jsonObject.getString("ID556");
                position221 = jsonObject.getString("position221");


            } catch (JSONException e) {
                e.printStackTrace();
            }
            a2.add(ID);
            a3.add(position221);

        }

        if (a2.contains(ID2)){
            Toast.makeText(getApplicationContext(), "이미 가입한 소모임입니다. ", Toast.LENGTH_LONG).show();


            finish();



        }
        if (!a2.contains(ID2)){
            Toast.makeText(getApplicationContext(), " 이 소모임에 가입하였습니다. ", Toast.LENGTH_LONG).show();
            save();
           finish();



        }


    }
    public void mOnNO(View v){

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }




    public void save() {

        Intent intent = getIntent();
        position22 = intent.getExtras().getInt("position221");
        position221 = String.valueOf(position22);

        try {
            jsonObject.put("ID556", ID2);
            jsonObject.put("position", position221);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        jsonArray.put(jsonObject);


        SharedPreferences pref556 = getSharedPreferences("pref556", MODE_PRIVATE); // UI 상태를 저장합니다.
        SharedPreferences.Editor editor556 = pref556.edit();
        editor556.putString("memberlist556", jsonArray.toString());
        editor556.apply();




    }



}
