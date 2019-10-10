package org.techtown.myrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Handler mHandler = new Handler();
    ImageSwitcher mSwitcher;
    boolean mRunning;
    Thread mThread;
    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    ArrayList aa2 = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ImageView dust = (ImageView) findViewById(R.id.imageView8);
        dust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Main4Activity.class);


                startActivity(intent);


            }
        });



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

                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
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

aa2.add(ID);


                }


                EditText id = (EditText) findViewById(R.id.editText5);
                String ID2 = id.getText().toString();
                EditText pw = (EditText) findViewById(R.id.editText4);
                String PW2 = pw.getText().toString();


                SharedPreferences pref24 = getSharedPreferences("IDinfo", MODE_PRIVATE); // UI 상태를 저장합니다.
                SharedPreferences.Editor editor24 = pref24.edit();

                editor24.putString("ID",ID2); //로그인 페이지에서 입력한 아이디값을 저장한다.
                editor24.apply();



                if(aa2.contains(ID2)) {

                    if(PW2.equals(PW)){

                        Toast.makeText(getApplicationContext(), "입장합니다! ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        startActivity(intent);

                    }


                    else {

                        Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다 ", Toast.LENGTH_LONG).show();
                    }


                }

                if(!aa2.contains(ID2)){
                    Toast.makeText(getApplicationContext(), "아이디가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                }





            }
        });

 /////////////////////////////////////////////////////////////////////////////////////////여기까지 로그인

        ImageView button8 = (ImageView) findViewById(R.id.imageView6);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });





        mSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {


            @Override
            public View makeView() {

                ImageView view = new ImageView(getApplicationContext());
                view.setBackgroundColor(Color.WHITE);
                view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                view.setLayoutParams(
                        new ImageSwitcher.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));

                return view;
            }
        });


        mSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mSwitcher.setOutAnimation(this, android.R.anim.fade_out);


                startAnimation();
    }






















    private void startAnimation() {
        mSwitcher.setVisibility(View.VISIBLE);
        mThread = new ImageThread();
        mThread.start();
    }
    /*private void stopAnimation(){
        mRunning = false;
        try{
            mThread.join();
        }catch (InterruptedException ex){

        }
        mSwitcher.setVisibility(View.INVISIBLE);
}*/

    class ImageThread extends Thread {
        int duration = 1500;
        final int images[] = {R.drawable.z2,
                R.drawable.z3,
                R.drawable.z4,
                R.drawable.z5,
                R.drawable.z6,

        };

        int cur = 0;

        public void run() {
            mRunning = true;
            while (mRunning) {
                synchronized (this) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mSwitcher.setImageResource(images[cur]);
                        }
                    });
                }

                cur++;

                if (cur == images.length) {
                    cur = 0;
                }

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                }


            }
        }

    }
}