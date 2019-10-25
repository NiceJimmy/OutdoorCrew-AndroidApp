package org.techtown.myrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    JSONObject jsonObject2 = new JSONObject();
    JSONArray jsonArray2 = new JSONArray();
int position;
String position4;
String aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*Intent intent = getIntent();
        position = intent.getExtras().getInt("position4");*/
        SharedPreferences pref = getSharedPreferences("dia", MODE_PRIVATE);
        String posit = pref.getString("dda", "");
        position = Integer.parseInt(posit);


        ImageView list = (ImageView) findViewById(R.id.imageView6);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
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







    public void onResume(){
        super.onResume();



        RecyclerView recyclerView = findViewById(R.id.recyclerview_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Dictionary2> dataList = new ArrayList<>();







        SharedPreferences pref = getSharedPreferences("aaa", MODE_PRIVATE);
        String postarray = pref.getString("content", "");


        try {
            jsonArray = new JSONArray(postarray);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    for (int i = 0; i < jsonArray.length(); i++)

    {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String ID = jsonObject.getString("title");
            String PW = jsonObject.getString("contents");
            String image = jsonObject.getString("image");
             position4 = jsonObject.getString("position");
             String posit = jsonObject.getString("length");
              aa = String.valueOf(i);




            if(position4.equals(String.valueOf(position))) {  //이프문 안에 스트링값은 잘 받아오고 있다.
                dataList.add(new Dictionary2(ID, PW, image, aa));

                //방삭제시 삭제할 사진의 인덱스값보내는 작업

          }

                        Log.d("loadlist12","로드항목:"+dataList.toString());



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


        /*if(position4.equals(String.valueOf(position))) {





        }*/






      /*  try {
            jsonArray2 = new JSONArray(postarray);
        } catch (JSONException e) {
            e.printStackTrace();
        }




          String countarray = jsonArray3.toString();



            Log.d("count11","어래이수 : "+countarray);
*/











        CustomAdapter2 adapter2 = new CustomAdapter2(dataList,getApplicationContext());
        recyclerView.setAdapter(adapter2);


        ImageView buttonInsert = (ImageView) findViewById(R.id.imageView4);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MakeContents.class);

                intent.putExtra("position5",position);


                startActivity(intent);

            }
        });
    }







}
