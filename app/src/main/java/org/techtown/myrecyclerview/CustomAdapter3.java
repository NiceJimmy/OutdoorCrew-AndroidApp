package org.techtown.myrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.ViewHolder> {


    private final List<Dictionary3> mDataList;

    Context context;


    public CustomAdapter3(List<Dictionary3> dataList, Context context) {
        mDataList = dataList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dust, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Dictionary3 item = mDataList.get(position);
        holder.textView_title.setText(String.valueOf(mDataList.get(position).getTitle()));
        holder.textView_release.setText(String.valueOf(mDataList.get(position).getRelease()));
        holder.texView_director.setText(String.valueOf(mDataList.get(position).getDirector()));
        //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.

        Glide.with(holder.itemView.getContext())
                .load(mDataList.get(position).getImg_url())
                .into(holder.imageView_img);




    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_img;
        private TextView textView_title, textView_release, texView_director;
private Button button;

        public ViewHolder(final View itemView) {
            super(itemView);

            imageView_img = (ImageView) itemView.findViewById(R.id.imageView1);
            textView_title = (TextView) itemView.findViewById(R.id.city1);
            textView_release = (TextView) itemView.findViewById(R.id.dust2);
            texView_director = (TextView) itemView.findViewById(R.id.dust1);

        }
    }
}