package org.techtown.myrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder> implements View.OnClickListener {
    Intent position2;
    int popo2;
    int po;

    private final List<Dictionary2> mDataList;
     Context context;
    JSONArray jsonArray = new JSONArray();
    JSONObject jsonObject = new JSONObject();
    public CustomAdapter2(List<Dictionary2> dataList, Context context){
    mDataList = dataList;
    this.context = context;



} //데이타를 외부에서 들고 온다.

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//리스트의 한 셀에 보여질 뷰를 생성, 반환값은 holder에 뷰를 붙인 형태
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Dictionary2 item = mDataList.get(position);


        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());


    holder.posit.setText(item.getPosit());





        try {
            holder.image.setImageURI(Uri.parse(item.getImage()));
            Glide.with(holder.itemView.getContext())
                    .load(item.getImage())
                    .into(holder.image);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onClick(View v) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
         TextView title;
        TextView contents;
        TextView posit;
         ImageView image;
        public ViewHolder(View itemView) {
           super(itemView);
           title = itemView.findViewById(R.id.title);
           contents = itemView.findViewById(R.id.contents);
           image = itemView.findViewById(R.id.imageView3);

           posit = itemView.findViewById(R.id.posit);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "삭제하기");
          /*  MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "편집하기");*/
            Edit.setOnMenuItemClickListener(onEditMenu);
       /*     Delete.setOnMenuItemClickListener(onEditMenu);*/
        }


        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            private Intent intent;

            public void setIntent(Intent intent) {
                this.intent = intent;
                intent.getExtras().getInt("position3");

                position2=intent;

            }

            public Intent getIntent() {
                return intent;

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemClick(final MenuItem item) {

                switch (item.getItemId()){
                    case 1001:
                        int position = getAdapterPosition();





                        SharedPreferences pref2 = context.getSharedPreferences("aaa", context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref2.edit();
                        String postarray1 = pref2.getString("content", "");

                        try {
                            jsonArray = new JSONArray(postarray1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                         String popo = posit.getText().toString();
                         popo2 = Integer.parseInt(popo);

                      /*  SharedPreferences pref29 = context.getSharedPreferences("popo998", context.MODE_PRIVATE); // UI 상태를 저장합니다.
                        SharedPreferences.Editor editor9 = pref29.edit();

                        editor9.putInt("popo225",popo2);
                        editor9.apply();*/




//////////////////////////////////////////////////////////////////////////////////////////////////////
                        SharedPreferences pref = context.getSharedPreferences("IDinfo", context.MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
                        String ID2 = pref.getString("ID", ""); // 로그인 페이지에서 저장한 아이디값을 로드한다.

                        String ID = title.getText().toString();



//////////////////////////////////////////////////////////////////////////////////////////////////////로드한 아이디가 올린게시물과 일치하면 지운다.
if(ID.equals(ID2)) {
    Intent intent = new Intent(itemView.getContext(),PopActivity.class);
    itemView.getContext().startActivity(intent);
    jsonArray.remove(popo2);
    mDataList.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(popo2,mDataList.size());
}else{
    Toast.makeText(context.getApplicationContext(), "본인이 올린 게시물이 아닙니다~!", Toast.LENGTH_LONG).show();

}



                        editor.putString("content", jsonArray.toString());
                        editor.apply();

                        Log.d("popot123","삭제테스트"+popo2);
                        Log.d("contents1","삭제테스트"+position);










                        break;
                   /* case 1002:




                        int position3 = getAdapterPosition();
                        Intent intent4 = new Intent(itemView.getContext(),EditContents.class);

                        intent4.putExtra("title55", mDataList.get(position3).getTitle());
                        intent4.putExtra("content55",mDataList.get(position3).getContents());
                        intent4.putExtra("image55", mDataList.get(position3).getImage());


                        intent4.putExtra("edit55", position3);
                        intent4.putExtra("tvnum", popo2);


                        itemView.getContext().startActivity(intent4);



                        break;*/


                }

                return true;
            }
        };


    }

}
