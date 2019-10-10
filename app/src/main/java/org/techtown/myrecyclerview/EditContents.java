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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EditContents extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 0;
    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    ImageView mImageView;
    EditText edittitle;
    EditText editcontents;


    private static int PICK_IMAGE_REQUEST = 1;

    static final String TAG = "MainActivity";
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contents);




        ImageView camera = (ImageView) findViewById(R.id.imageView28);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });


        Intent intent = getIntent();
        final int position = intent.getExtras().getInt("edit55");
        final int popo2 = intent.getExtras().getInt("tvnum");

        String title2 = intent.getExtras().getString("title55");
        String content2 = intent.getExtras().getString("content55");
        String image2 = intent.getExtras().getString("image55");
        Log.d("imagesend","이미지값:"+image2);

        mImageView = (ImageView) findViewById(R.id.imageView);
        edittitle = (EditText)findViewById(R.id.editText1);
        editcontents = (EditText)findViewById(R.id.editText2);

        edittitle.setText(title2);
        editcontents.setText(content2);

       /* mImageView.setImageURI(Uri.parse(image2));*/
uri = Uri.parse(image2);
        Glide.with(EditContents.this)
                .load(uri)
                .apply(new RequestOptions().centerCrop())
                .transition(new DrawableTransitionOptions().crossFade())
                .into(mImageView);


        Button button = (Button) findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String title = edittitle.getText().toString();
                String contents = editcontents.getText().toString();
                /* String uri1 = uri.toString();*/





                SharedPreferences pref = getSharedPreferences("aaa", MODE_PRIVATE);
                String postarray = pref.getString("content", "");
                ArrayList<Dictionary2> dataList = new ArrayList<>();

                try {
                    jsonArray = new JSONArray(postarray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                for (int i =0;  i <jsonArray.length(); i++)

                {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String ID = jsonObject.getString("title");
                        String PW = jsonObject.getString("contents");
                        String image = jsonObject.getString("image");
                        String posit = jsonObject.getString("length");
                        String position = jsonObject.getString("position");

                        String aa = String.valueOf(i);
                        if (position.equals(String.valueOf(popo2))) {
                        dataList.add(new Dictionary2( ID,PW,image,aa));}
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }




                ///////////////////////////////////////////////////////////////////////////////////////저장하기


                try {
                    jsonObject.put("title",title);
                    jsonObject.put("contents",contents);
                    jsonObject.put("image",uri);
                    jsonObject.put("length",jsonArray.length());
                    jsonObject.put("position",popo2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    jsonArray.put(popo2,jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                SharedPreferences pref2 = getSharedPreferences("aaa", MODE_PRIVATE); // UI 상태를 저장합니다.
                SharedPreferences.Editor editor = pref2.edit();

                editor.putString("content",jsonArray.toString());
                editor.apply();

/*
Intent intent2 = new Intent(getApplicationContext(), CustomAdapter2.class);

    intent2.putExtra("position3", position);
*/


                finish();


            }
        });





    }


  /*  public void loadImagefromGallery() {
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

                Glide.with(MakeContents.this)
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

    }*/


    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    //data.getData return the content URI for the selected Image
                    uri = data.getData();

                    Glide.with(EditContents.this)
                            .load(uri)
                            .apply(new RequestOptions().centerCrop())
                            .transition(new DrawableTransitionOptions().crossFade())
                            .into(mImageView);


                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
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
