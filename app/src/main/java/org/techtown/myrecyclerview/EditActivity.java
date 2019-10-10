package org.techtown.myrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EditActivity extends AppCompatActivity  {

    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    CheckBox checkBox;
    CheckBox checkBox2;
    CheckBox checkBox3;
    TextView textView;
    ImageView imageView;

    String ski = "SKI & SNOW BOARD";
    String surf = "SURF";
    String camping = "CAMPING";



    EditText editTextRegion;
    TextView textViewCategory;
    EditText editTextnumber;
    EditText editTextrestriction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
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





         editTextRegion = (EditText) findViewById(R.id.region);
         textViewCategory = (TextView) findViewById(R.id.textView);
         editTextnumber = (EditText) findViewById(R.id.number);
         editTextrestriction = (EditText) findViewById(R.id.restriction);




        Intent intent2 = getIntent();
        String Region = intent2.getExtras().getString("region");
        String Category = intent2.getExtras().getString("category");
        String Number = intent2.getExtras().getString("number");
        String Restriction = intent2.getExtras().getString("restriction");

        editTextRegion.setText(Region);
        textViewCategory.setText(Category);
       /* String text12 = textViewCategory.toString();*/


        editTextnumber.setText(Number);
        editTextrestriction.setText(Restriction);

        if(Category.equals(ski)){
            imageView.setImageResource(R.drawable.snowboard111111);
        }
        if(Category.equals(surf)){
            imageView.setImageResource(R.drawable.surf1111);
        }
        if(Category.equals(camping)){
            imageView.setImageResource(R.drawable.camp1);
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {







                String region = editTextRegion.getText().toString();
                String category = textViewCategory.getText().toString();
                String number = editTextnumber.getText().toString();
                String restriction = editTextrestriction.getText().toString();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);


                Intent intent1 = getIntent();
                int position = intent1.getExtras().getInt("position2");

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
                    jsonObject.put("number", number);
                    jsonObject.put("restriction", restriction);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    jsonArray.put(position,jsonObject);
                    //Edittext에 입력을 하면은 제이슨 오브젝트에 넣는다, 그리고 그거를 컨텍스트메뉴에서 보내온 position 위치에 정확하게
                    //끼워 넣어야한다. 그러므로 입력한 데이터들(제이슨오브젝트)의 배열인 제이슨 어레이의 적절한 position 위치에 입력한
                    //제이슨 오브젝트의 데이터를 끼워 넣는다.
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                pref = getSharedPreferences("pref", MODE_PRIVATE); // UI 상태를 저장합니다.
                SharedPreferences.Editor editor = pref.edit();


                editor.putString("post", jsonArray.toString());
                editor.apply();

                Log.d("savelist11", "저장목록 : " + jsonArray);

                startActivity(intent);


            }
        });


    }
}