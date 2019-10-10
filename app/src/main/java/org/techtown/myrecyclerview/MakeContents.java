package org.techtown.myrecyclerview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MakeContents extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 0;
    JSONObject jsonObject = new JSONObject();
    JSONObject jsonObject2;
    JSONObject jsonObject3 = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    JSONArray jsonArray2;
    JSONArray jsonArray3 = new JSONArray();
    //제이슨 어레이와 제이슨 오브젝트를 맨위에 대표로 선언
    ImageView mImageView;

    EditText editTextRegion;
    private static int PICK_IMAGE_REQUEST = 1;

    static final String TAG = "MainActivity";
    Uri uri;

    String aa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_contents);

        SharedPreferences pref = getSharedPreferences("IDinfo", MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
        String ID2 = pref.getString("ID", ""); // 로그인 페이지에서 저장한 아이디값을 로드한다.





        LottieAnimationView lottie = (LottieAnimationView) findViewById(R.id.lottie);
        lottie.playAnimation();
        lottie.loop(true);
        lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });// 이미지를 로드하는 메소드이다. 이 메소드는 하단에 모두 구현되어 있다.


        mImageView = (ImageView) findViewById(R.id.imageView); // 이미지뷰를 선언한다.


        Intent intent = getIntent(); // 겟 인텐트를 선언한다.
        final int position = intent.getExtras().getInt("position5"); // 위치값(position)을 인텐트로 받아온다. RoomActivity2에서 위치값을 보내면
        //사진첩 리사이클러뷰가 구현되어있는 Main2Activity에서 받는다. 여기서 추가버튼인 플러스 버튼을 클릭하면 그 파지션값을 다시 Makecontent로 보낸다.
        //이제 Makecontent에서 position 값을 활용하게 된다. (처음에 방을 들어갈때 방별로 다른 사진첩 내용을 출력해주기 위함이다.)

        editTextRegion = (EditText) findViewById(R.id.editText1);
        editTextRegion.setText(ID2);
        Button button = (Button) findViewById(R.id.button10); // 사진첩에 올릴 게시물을 작성하고 이 버튼을 누르면 리사이클러뷰에 사진아이템들이 추가된다.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText editTextCategory = (EditText) findViewById(R.id.editText2);



                String title = editTextRegion.getText().toString();
                String contents = editTextCategory.getText().toString();

                // 게시물에 올릴 내용을 편집하고 스트링값으로 변환한다.


                SharedPreferences pref = getSharedPreferences("aaa", MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
                String postarray = pref.getString("content", ""); //postarry라는 이름으로 스트링화시켜서 저장한 제이슨어레이를 불러와서 스트링화 시킨다.
                ArrayList<Dictionary2> dataList2 = new ArrayList<>();//딕셔너리에서 조합한 어레이리스트객체를 생성한다.


                try {
                    jsonArray = new JSONArray(postarray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                for (int i = 0; i < jsonArray.length(); i++)

                {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String Title = jsonObject.getString("title");
                        String Contents = jsonObject.getString("contents");
                        String Image = jsonObject.getString("image");
                        String position3 = jsonObject.getString("position");
                        String posit = jsonObject.getString("length");
                         aa = String.valueOf(i);

                        if (position3.equals(String.valueOf(position))) {
                            dataList2.add(new Dictionary2(Title, Contents, Image, aa));


                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                ///////////////////////////////////////////////////////////////////////////////////////저장하기


                try {
                    jsonObject.put("title", title); // 제이슨오브젝트에 입력한 스트링값을 삽입한다.
                    jsonObject.put("contents", contents);
                    jsonObject.put("image", uri);
                    jsonObject.put("position", position);

                    jsonObject.put("length",jsonArray.length());
                    // 맨 위에 공통적으로 uri 를 선언하고 이미지로드 메소드를 실행하면 uri 변수에 이미지 값이 대입이 된다.
                    // 그리고 그 uri값을  제이슨 오브젝트에 집어넣었다.


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                    jsonArray.put(jsonObject); // 그 제이슨 오브젝트[{"title": 제목 ,"content" : 내용, "image" : uri 값 },{...}]을 하나하나 제이슨어레이에 집어넣는다.

                // 그러면 저 제이슨 오브젝트의 그룹인 제이슨 어레이가 형성이 된다.






                SharedPreferences pref2 = getSharedPreferences("aaa", MODE_PRIVATE); // UI 상태를 저장합니다.
                SharedPreferences.Editor editor2 = pref2.edit();

                editor2.putString("content", jsonArray.toString()); //제이슨어레이를 스트링화시켜서 저장을 한다. 이제 위에 불러오는 부분을 보면 된다.
                editor2.apply();


                Log.d("popot123", "저장목록:" + jsonArray.toString());




///////////////////////////////////////////////////////////////////////////////////////////////////////////

               /* SharedPreferences pref33 = getSharedPreferences("aaa", MODE_PRIVATE);
                String indexarray = pref33.getString("indexValue", "");



                try {
                    jsonArray2 = new JSONArray(indexarray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int s=0; s<jsonArray2.length(); s++){

                    try {
                        JSONObject jsonObject2 = jsonArray2.getJSONObject(s);
                        aa = jsonObject2.getString("index");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
*/

/*
int aa2 = Integer.parseInt(aa);
int aa3 = aa2+1;

String aa4 = String.valueOf(aa3);

                try {
                    jsonObject3.put("index",aa4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray3.put(jsonObject3);

                SharedPreferences pref26 = getSharedPreferences("indexValue", MODE_PRIVATE); // UI 상태를 저장합니다.
                SharedPreferences.Editor editor26 = pref26.edit();

                editor26.putString("index2", jsonArray3.toString()); //제이슨어레이를 스트링화시켜서 저장을 한다. 이제 위에 불러오는 부분을 보면 된다.
                editor26.apply();

                Log.d("popot113", "저장목록:" + jsonArray3.toString());

*/




/////////////////////////////////////////////////////////////////////////////////////////////////////////


                finish();


            }
        });


    }


    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData return the content URI for the selected Image
                    uri = data.getData();

                    Glide.with(MakeContents.this)
                            .load(uri)
                            .apply(new RequestOptions().centerCrop())
                            .transition(new DrawableTransitionOptions().crossFade())
                            .into(mImageView);


                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    mImageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;

            }
    }


}
