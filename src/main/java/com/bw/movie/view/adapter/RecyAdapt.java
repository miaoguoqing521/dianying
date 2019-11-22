package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.ReMovieBean;
import com.bw.movie.view.movies.DetailsMovie;
import com.bw.movie.view.movies.RoomActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/11 09:37
 */
public class RecyAdapt extends RecyclerView.Adapter<RecyAdapt.Holder> {
    private List<ReMovieBean.ResultBean> list;
    private Context context;

    public RecyAdapt(List<ReMovieBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item2, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.tv.setText(list.get(i).name);
        holder.img.setImageURI(list.get(i).imageUrl);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RoomActivity.class);
                SharedPreferences isP = context.getSharedPreferences("isP", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = isP.edit();
                edit.putInt("movieId",list.get(i).movieId);
                edit.commit();
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsMovie.class);
                intent.putExtra("movieId",list.get(i).movieId);
                intent.putExtra("name",list.get(i).name);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView tv;
        private final Button btn;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recy3_img);
            tv = itemView.findViewById(R.id.recy3_tv);
            btn = itemView.findViewById(R.id.recy3_btn);
        }
    }
}
