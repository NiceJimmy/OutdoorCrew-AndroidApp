package org.techtown.myrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RoomActivity2 extends AppCompatActivity {
Uri uri;
int position;
String uri1;
    ImageView mImageView;

    JSONObject jsonObject = new JSONObject();
    JSONArray jArray = new JSONArray();

    private static int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);





        mImageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("psn12");


        ImageView list = (ImageView) findViewById(R.id.imageView6);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
            }
        });
        ImageView pro = (ImageView) findViewById(R.id.imageView39);
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(intent2);
            }
        });


        ImageView buttonInsert = (ImageView) findViewById(R.id.imageView26);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                /*intent.putExtra("position4",position);*/

                startActivity(intent);

                SharedPreferences pref32 = getSharedPreferences("dia",MODE_PRIVATE); // UI 상태를 저장합니다.
                SharedPreferences.Editor editor2 = pref32.edit();

                editor2.putString("dda", String.valueOf(position));
                editor2.apply();





            }
        });


        ImageView buttonInsert2 = (ImageView) findViewById(R.id.imageView27);
        buttonInsert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);


                startActivity(intent);


            }
        });






/////////////////////////////////////////////////////////////////////////////////////

        EditText content = (EditText) findViewById(R.id.editText9);
        EditText content2 = (EditText) findViewById(R.id.editText10);

        EditText content4 = (EditText) findViewById(R.id.editText12);


        SharedPreferences pref31 = getSharedPreferences("aaa", MODE_PRIVATE);
        String homearray = pref31.getString("home", "");


        try {
            jArray = new JSONArray(homearray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i =0;  i <jArray.length(); i++)
        {

            try {

                  JSONObject  jsonObject = jArray.getJSONObject(i);
                String Content1 = jsonObject.getString("a1");
                String Content22 = jsonObject.getString("a2");

                String Content44 = jsonObject.getString("a4");
                String uri1 = jsonObject.getString("a5");

                if (position == i) {
                    content.setText(Content1);
                    content2.setText(Content22);
                    content4.setText(Content44);
                    uri = Uri.parse(uri1);
                                }

                } catch(JSONException e){
                    e.printStackTrace();
                }

        }




        Glide.with(RoomActivity2.this)
                .load(uri)
                .apply(new RequestOptions().centerCrop())
                .transition(new DrawableTransitionOptions().crossFade())
                .into(mImageView);

        Log.d("savelist44","로드목록 : "+homearray);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////





// 아잉디 판별 시작
      /*  SharedPreferences pref = getSharedPreferences("IDinfo", MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
        final String ID2 = pref.getString("ID", "");

        SharedPreferences pref224 = getSharedPreferences("IDinfo22", MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
        final String ID224 = pref224.getString("ID22", "");
*/

/*
Log.d("aagg","아이디 두개 : "+ID2+ID224);
*/

            Button savebutton = (Button) findViewById(R.id.button6);
            savebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
         /*           if(ID2.equals(ID224)){
         */               EditText content = (EditText) findViewById(R.id.editText9);
                    EditText content2 = (EditText) findViewById(R.id.editText10);

                    EditText content4 = (EditText) findViewById(R.id.editText12);
                    mImageView = (ImageView) findViewById(R.id.imageView);


                    String content1 = content.getText().toString();
                    String content22 = content2.getText().toString();

                    String content44 = content4.getText().toString();


                    try {
                        jsonObject.put("a1", content1);
                        jsonObject.put("a2", content22);

                        jsonObject.put("a4", content44);
                        jsonObject.put("a5", String.valueOf(uri));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        jArray.put(position, jsonObject); // 방목록의 어댑터에서 인텐트로 가져온 포지션값에 해당하는 제이슨 어레이의 위치에 데이터를 덮어씌우는 것이다.
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    SharedPreferences pref31 = getSharedPreferences("aaa", MODE_PRIVATE); // UI 상태를 저장합니다.
                    SharedPreferences.Editor editor2 = pref31.edit();

                    editor2.putString("home", jArray.toString());
                    editor2.apply();


                    Toast.makeText(getApplicationContext(), "저장이 완료되었습니다!", Toast.LENGTH_LONG).show();

                    Log.d("savelist44", "저장목록 : " + jArray.toString());


                    finish();
                    /*else {
                        Toast.makeText(getApplicationContext(), "방장만 수정할 수 있습니다.", Toast.LENGTH_LONG).show();
                    }*/
                }
            });













        ImageView imageView1 = (ImageView) findViewById(R.id.imageView25);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImagefromGallery();
            }
        });





        ImageView dust = (ImageView) findViewById(R.id.imageView8);
        dust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Main4Activity.class);


                startActivity(intent);


            }
        });

    }












    public void loadImagefromGallery() {
        //Intent 생성
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
        intent.setType("image/*"); //이미지만 보이게
        //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //이미지 선택작업을 후의 결과 처리
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //이미지를 하나 골랐을때
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                //data에서 절대경로로 이미지를 가져옴
                uri = data.getData();

                Glide.with(RoomActivity2.this)
                        .load(uri)
                        .apply(new RequestOptions().centerCrop())
                        .transition(new DrawableTransitionOptions().crossFade())
                        .into(mImageView);

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }




}

