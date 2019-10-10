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

public class MainActivity extends AppCompatActivity   {



    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



Intent intent = getIntent();
String member = intent.getStringExtra("loginfo");
Log.d("memberinfo","로그인정보"+member);



        ImageView buttonInsert = (ImageView) findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Makeroom.class);
                startActivity(intent);

            }
        });





        ImageView buttonProfile = (ImageView) findViewById(R.id.imageView8);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(intent);

            }
        });


    }


    public void onResume(){
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_main);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Dictionary> dataList = new ArrayList<>();






        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String postarray = pref.getString("post", "");



        try {
            jsonArray = new JSONArray(postarray);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (int i =0;  i <jsonArray.length(); i++)

        {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String region = jsonObject.getString("region");
                String category = jsonObject.getString("category");
                String number = jsonObject.getString("number");
                String restriction = jsonObject.getString("restriction");
                /*String image = jsonObject.getString("image");*/

                dataList.add(new Dictionary(region,category,number,restriction/*,image*/));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }




        CustomAdapter adapter = new CustomAdapter(dataList,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }





}
