package org.techtown.myrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Makeroom extends AppCompatActivity {

    JSONObject jsonObject = new JSONObject();
  JSONArray jsonArray = new JSONArray();

    CheckBox checkBox;
    CheckBox checkBox2;
    CheckBox checkBox3;
    TextView textView;
    ImageView imageView;

    String ski="SKI & SNOW BOARD";
    String surf="SURF";
    String camping="CAMPING";

    Uri uri;
    private int REQUEST_TEST = 1;



static String arrayset;
EditText editTextnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeroom);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        textView = findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView23);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(ski);
                imageView.setImageResource(R.drawable.snowboard111111);
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(surf);
                imageView.setImageResource(R.drawable.surf1111);
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(camping);
                imageView.setImageResource(R.drawable.camp1);
            }
        });
        editTextnumber = (EditText) findViewById(R.id.number);
        SharedPreferences pref = getSharedPreferences("IDinfo", MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
        final String ID2 = pref.getString("ID", ""); // 로그인 페이지에서 저장한 아이디값을 로드한다.

        editTextnumber.setText(ID2);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText editTextRegion = (EditText) findViewById(R.id.region);
                TextView textViewCategory = (TextView) findViewById(R.id.textView);


                EditText editTextrestriction = (EditText) findViewById(R.id.restriction);


                String region = editTextRegion.getText().toString();
                String category = textViewCategory.getText().toString();
                String number = editTextnumber.getText().toString();
                String restriction = editTextrestriction.getText().toString();




                Intent intent = new Intent(getApplicationContext(), MainActivity.class);




                      SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    String postarray = pref.getString("post", "");
                    ArrayList<Dictionary> dataList = new ArrayList<>();

                    try {
                        jsonArray = new JSONArray(postarray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    for (int i = 0; i < jsonArray.length(); i++)

                    {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String region1 = jsonObject.getString("region");
                            String category1 = jsonObject.getString("category");
                            String number1 = jsonObject.getString("number");
                            String restriction1 = jsonObject.getString("restriction");
                            /* int drawableId1 = jsonObject.getInt("drawableId");*/


                            dataList.add(new Dictionary(region1, category1, number1, restriction1/*, drawableId1*/));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                ///////////////////////////////////////////////////////////////////////////////////////저장하기


                try {
                    jsonObject.put("region", region);
                    jsonObject.put("category", category);
                    jsonObject.put("number",number);
                    jsonObject.put("restriction",restriction);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);


                 pref = getSharedPreferences("pref", MODE_PRIVATE); // UI 상태를 저장합니다.
                SharedPreferences.Editor editor = pref.edit();



                editor.putString("post", jsonArray.toString());
                editor.apply();

                Log.d("savelist11","저장목록 : "+jsonArray);

         finish();




            }
        });


    }

    /*private void changeView(int index) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); //  레이아웃 인플레이터 객체 생성

        FrameLayout frame = (FrameLayout) findViewById(R.id.frame) ; //프레임레이아웃 객체 생성
       *//* if (frame.getChildCount() > 0) {  //getChildCount ???
            // FrameLayout에서 뷰 삭제.
            frame.removeViewAt(0);
        }*//*

        // XML에 작성된 레이아웃을 View 객체로 변환.
        View view = null ;


        if(index==0){
            view = inflater.inflate(R.layout.roomimg1,frame, false) ;

        }
        if(index==1){
            view = inflater.inflate(R.layout.roomimg2, frame, false) ;

        }
        if(index==2){
            view = inflater.inflate(R.layout.roomimg3, frame, false) ;

        }

        // FrameLayout에 뷰 추가.
        if (view != null) {
            frame.addView(view) ;
        }
    }*/

}



/*
    public static ArrayList<Dictionary> mArrayList=new ArrayList<>();



EditText editTextRegion;
EditText editTextCategory;
EditText editTextNumber;
EditText editTextRestriction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeroom);




        Button buttonSubmit = (Button) findViewById(R.id.button_dialog_submit2);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);

                View view = LayoutInflater.from(Makeroom.this).inflate(R.layout.item_list, null, false);

                editTextRegion = (EditText) view. findViewById(R.id.edittext_dialog_region);
                editTextCategory = (EditText)view. findViewById(R.id.edittext_dialog_category);
                editTextNumber = (EditText)view. findViewById(R.id.edittext_dialog_number);
                editTextRestriction = (EditText)view. findViewById(R.id.edittext_dialog_restriction);


                intent2.putExtra("region", editTextRegion.getText().toString());
                intent2.putExtra("category", editTextCategory.getText().toString());
                intent2.putExtra("number", editTextNumber.getText().toString());
                intent2.putExtra("restriction", editTextRestriction.getText().toString());

                startActivity(intent2);






            }
        });




    }
}
*/
