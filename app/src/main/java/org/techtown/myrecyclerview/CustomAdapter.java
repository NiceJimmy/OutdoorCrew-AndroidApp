package org.techtown.myrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static org.techtown.myrecyclerview.Makeroom.arrayset;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements View.OnClickListener {


    private final List<Dictionary> mDataList;


    JSONArray jsonArray = new JSONArray();
    JSONArray jArray = new JSONArray();
    JSONArray jsonArray2 = new JSONArray();
    JSONArray jsonArray3 = new JSONArray();
    JSONObject jsonObject2 = new JSONObject();
String arrayset1 = arrayset;
    ImageView imageView;
    Context context;

    String aa;
    String position4;

    String Region;
    String Category;
    String Number;
    String Restriction;



    JSONObject jsonObject = new JSONObject();

    public CustomAdapter(List<Dictionary> dataList,Context context) {
        mDataList = dataList;
        this.context = context;

    } //데이타를 외부에서 들고 온다.


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        Dictionary item = mDataList.get(position);

        holder.region.setText(item.getRegion());
        holder.category.setText(item.getCategory());
        holder.number.setText(item.getNumber());
        holder.restriction.setText(item.getRestriction());

        /*holder.image.setImageDrawable(mCtx.getResources().getDrawable(item.getDrawableId()));*/
        Region = holder.region.getText().toString();
        Category = holder.category.getText().toString();
        Number = holder.number.getText().toString();
        Restriction = holder.restriction.getText().toString();




        if (Category.equals("SKI & SNOW BOARD")) {
            holder.image.setImageResource(R.drawable.snowboard111111);
        }

        if(Category.equals("CAMPING")){
            holder.image.setImageResource(R.drawable.camp1);
        }

        if(Category.equals("SURF")){
            holder.image.setImageResource(R.drawable.surf1111);
        }

       /* Intent intent = new Intent(holder.getContext(), RoomActivity2.class);

        intent.putExtra("position14", position);
*/



    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView region;
        TextView category;
        TextView number;
        TextView restriction;
        ImageView image;

        public ViewHolder(final View itemView) {
            super(itemView);
            region = itemView.findViewById(R.id.region);
            category = itemView.findViewById(R.id.category);
            number = itemView.findViewById(R.id.number);
            restriction = itemView.findViewById(R.id.restriction);
            image = itemView.findViewById(R.id.imageView2);


            itemView.setOnCreateContextMenuListener(this);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  int position = getAdapterPosition();


                  Log.d("posit1","클릭시 위치값: "+position);



                    SharedPreferences pref = context.getSharedPreferences("IDinfo", context.MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
                   String ID22 = pref.getString("ID", ""); // 로그인 페이지에서 저장한 아이디값을 로드한다.
                    String ID134 = number.getText().toString();
                    SharedPreferences pref252 = context.getSharedPreferences("pref556", context.MODE_PRIVATE);
                    String memberlist56 = pref252.getString("memberlist556", "");


                    SharedPreferences pref244 = context.getSharedPreferences("IDinfo22",context.MODE_PRIVATE); // UI 상태를 저장합니다.
                    SharedPreferences.Editor editor244 = pref244.edit();

                    editor244.putString("ID22",ID134); //로그인


                   /* try {
                        jsonArray3 = new JSONArray(memberlist56);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    String ID = null;
                    String position221=null;
                    for (int i = 0; i < jsonArray3.length(); i++)

                    {
                        try {
                            JSONObject jsonObject = jsonArray3.getJSONObject(i);
                            ID = jsonObject.getString("ID556");
                            position221 = jsonObject.getString("position221");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

String po = String.valueOf(position);
                    if (po.equals(position221)){
                   *//*     if(ID.equals(ID22)){*/

                            Toast.makeText(context.getApplicationContext(), " 입장합니다. ", Toast.LENGTH_LONG).show();
                            ///////////////////////////////////////////////////////////////////////////////////////
                            Intent intent = new Intent(itemView.getContext(), RoomActivity2.class);

                            intent.putExtra("psn12", position);


                            itemView.getContext().startActivity(intent);
                     /*   }*/
               /*     }
                    if( po.equals(position221)){
                        Toast.makeText(context.getApplicationContext(), " 가입하셔야 입장 가능합니다. ", Toast.LENGTH_LONG).show();
                    }

Log.d("popo88","가입할때po : "+memberlist56);
                    Log.d("popo88","눌럿을때po : "+po);



*/
//////////////////////////////////////////////////////////////////////////////////








                }
            });




        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "삭제하기");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "편집하기");
          /*  MenuItem Enter = menu.add(Menu.NONE, 1003, 3, "참여하기");*/
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
          /*  Enter.setOnMenuItemClickListener(onEditMenu);*/

        }


        private  final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {



            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               switch (item.getItemId()){
                   case 1001:
                       int position = getAdapterPosition();








                      SharedPreferences pref2 = context.getSharedPreferences("pref", context.MODE_PRIVATE);
                       SharedPreferences.Editor editor = pref2.edit();
                       String postarray = pref2.getString("post", "");

                       try {
                           jsonArray = new JSONArray(postarray);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }





                       SharedPreferences pref556 = context.getSharedPreferences("IDinfo", context.MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
                       String ID29 = pref556.getString("ID", ""); // 로그인 페이지에서 저장한 아이디값을 로드한다.

                       String ID99 = number.getText().toString();



                       if(ID99.equals(ID29)) {


                           jsonArray.remove(position);
                           editor.putString("post", jsonArray.toString());
                           editor.apply();


                           mDataList.remove(position);
                           notifyItemRemoved(position);
                           notifyItemRangeChanged(position, mDataList.size());
                       }


                       else{
                           Toast.makeText(context.getApplicationContext(), "방장만 삭제할 수 있습니다.", Toast.LENGTH_LONG).show();

                       }



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                       SharedPreferences pref3 = context.getSharedPreferences("aaa", context.MODE_PRIVATE);
                       SharedPreferences.Editor editor2 = pref3.edit();

                       String homearray = pref3.getString("home","");
                       try {
                           jArray = new JSONArray(homearray);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }








                           jArray.remove(position);


                           editor2.putString("home", jArray.toString());
                           editor2.apply();
                           Log.d("savelist44","삭제현황 : "+homearray);









////////////////////////////////////////////////////////////////////////////////////////////////////////

/*

                       SharedPreferences pref4 = context.getSharedPreferences(String.valueOf(position), context.MODE_PRIVATE);
                       SharedPreferences.Editor editor4 = pref4.edit();
                       String postarray1 = pref2.getString("content", "");

                       try {
                           jsonArray = new JSONArray(postarray1);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
*/



                      /* editor4.clear();
                       editor4.putString("content", jsonArray.toString());
                       editor4.apply();

*/

                       /*mDataList.remove(position);
                       notifyItemRemoved(position);
                       notifyItemRangeChanged(position,mDataList.size());
*/





///////////////////////////// 사진첩까지 삭제할 기능 로드//////////////////////////////////////////////////

/*



                       SharedPreferences pref7 = context.getSharedPreferences("aaa", context.MODE_PRIVATE);
                       SharedPreferences.Editor editor27 = pref7.edit();
                       String postarray2 = pref7.getString("content", "");


                       try {
                           jsonArray2 = new JSONArray(postarray2);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }


                       for (int i = 0; i < jsonArray2.length(); i++)

                       {
                           try {
                               JSONObject jsonObject = jsonArray2.getJSONObject(i);
                              position4 = jsonObject.getString("position");
                              aa = String.valueOf(i);



                               if(position4.equals(String.valueOf(position))) {


                                   jsonArray3.put(jsonObject);

                                   Log.d("sss3","저장값"+jsonArray3.length());
for(int ss=0; ss<jsonArray3.length(); ss++) {

    jsonArray2.remove(Integer.parseInt(aa));

    position = position -1;
}




                               }



                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }



                       editor27.putString("content", jsonArray2.toString());
                       editor27.apply();


*/



                      break;
                   case 1002:

                       SharedPreferences pref22 = context.getSharedPreferences("IDinfo", context.MODE_PRIVATE); // 저장한 데이터를 불러오는 부분이다.(로드)
                       String ID3 = pref22.getString("ID", ""); // 로그인 페이지에서 저장한 아이디값을 로드한다.

                       String ID22 = number.getText().toString();



                       if(ID22.equals(ID3)) {

                           int position2 = getAdapterPosition();
                           Intent intent4 = new Intent(itemView.getContext(),EditActivity.class);

                           intent4.putExtra("region", mDataList.get(position2).getRegion());
                           intent4.putExtra("category",mDataList.get(position2).getCategory());
                           intent4.putExtra("number", mDataList.get(position2).getNumber());
                           intent4.putExtra("restriction", mDataList.get(position2).getRestriction());

                           intent4.putExtra("position2", position2);



                           itemView.getContext().startActivity(intent4);


                       }else{
                           Toast.makeText(context.getApplicationContext(), "방장만 편집할 수 있습니다.", Toast.LENGTH_LONG).show();

                       }











                       break;

                   case 1003:



                   /*   int position221 = getAdapterPosition();

                       Intent intent = new Intent(itemView.getContext(),Pop2Activity.class);
                       intent.putExtra("position221",position221);
                       itemView.getContext().startActivity(intent);*/










                       break;


               }

                return true;
            }
        };



    }




}
   /* @Override
    public void onCreateContextMenu(ContextMenu menu, this, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 메뉴 추가U

        MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
        MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
        Edit.setOnMenuItemClickListener(onEditMenu);
        Delete.setOnMenuItemClickListener(onEditMenu);

    }






    // 4. 캔텍스트 메뉴 클릭시 동작을 설정
    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {






        @Override
        public boolean onMenuItemClick(MenuItem item) {


            switch (item.getItemId()) {
                case 1001:

                    Intent intent = new Intent(mContext.getApplicationContext(), Makeroom.class);
                    mContext.startActivity(intent);




                    break;

                case 1002:

                    mDataList.remove(0);
                    notifyItemRemoved(0);
                    notifyItemRangeChanged(0, mDataList.size());

                    break;

            }
            return true;
        }
    };





    public CustomAdapter(Context context, ArrayList<Dictionary> mDataList) {
        this.mDataList = mDataList;
        this.mContext = context;




    }


}



*/












   /* public static ArrayList<Dictionary> mList;
    private Context mContext;






    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener { // 1. 리스너 추가

        protected TextView mRegion;
        protected TextView mCategory;
        protected TextView mNumber;
        protected TextView mRestriction;

       *//* protected ImageView mPictures;*//*




        public CustomViewHolder(final View view) {
            super(view);

            this.mRegion = (TextView) view.findViewById(R.id.region);
            this.mCategory = (TextView) view.findViewById(R.id.category);
            this.mNumber = (TextView) view.findViewById(R.id.number);
            this.mRestriction = (TextView) view.findViewById(R.id.restriction);
      *//*      this.mPictures = (ImageView) view.findViewById(R.id.imageView2);*//*









                  view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(view.getContext(), RoomActivity2.class);


                            view.getContext().startActivity(intent);




                        }
            });
            view.setOnCreateContextMenuListener(this);

        }







        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 메뉴 추가U

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);

        }






        // 4. 캔텍스트 메뉴 클릭시 동작을 설정
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {






            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {
                    case 1001:

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextRegion = (EditText) view.findViewById(R.id.edittext_dialog_region);
                        final EditText editTextCategory = (EditText) view.findViewById(R.id.edittext_dialog_category);
                        final EditText editTextNumber = (EditText) view.findViewById(R.id.edittext_dialog_number);
                        final EditText editTextRestriction = (EditText) view.findViewById(R.id.edittext_dialog_restriction);


                        editTextRegion.setText(mList.get(getAdapterPosition()).getRegion());
                        editTextCategory.setText(mList.get(getAdapterPosition()).getCategory());
                        editTextNumber.setText(mList.get(getAdapterPosition()).getNumber());
                        editTextRestriction.setText(mList.get(getAdapterPosition()).getRestriction());


                        final AlertDialog dialog = builder.create();

                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                String strRegion = editTextRegion.getText().toString();
                                String strCategory = editTextCategory.getText().toString();
                                String strNumber = editTextNumber.getText().toString();
                                String strRestriction = editTextRestriction.getText().toString();



                                Dictionary dict = new Dictionary(strRegion, strCategory, strNumber, strRestriction);

                                mList.set(getAdapterPosition(), dict);
                                notifyItemChanged(getAdapterPosition());

                                dialog.dismiss();





                            }
                        });

                        dialog.show();

                        break;

                    case 1002:

                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());

                        break;

                }
                return true;
            }
        };


    }


    public CustomAdapter(Context context, ArrayList<Dictionary> list) {
        this.mList = list;
        this.mContext = context;




    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {


        viewholder.mRegion.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mCategory.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mRestriction.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.mRegion.setGravity(Gravity.CENTER);
        viewholder.mCategory.setGravity(Gravity.CENTER);
        viewholder.mNumber.setGravity(Gravity.CENTER);
        viewholder.mRestriction.setGravity(Gravity.CENTER);

        viewholder.mRegion.setText(mList.get(position).getRegion());
        viewholder.mCategory.setText(mList.get(position).getCategory());
        viewholder.mNumber.setText(mList.get(position).getNumber());
        viewholder.mRestriction.setText(mList.get(position).getRestriction());

      *//*  viewholder.mPictures.setImageResource(mList.get(position).drawableId);*//*



    }


    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }



    public interface ItemClickListener {
        void onItemClick(View view, int position);





    }

*/




