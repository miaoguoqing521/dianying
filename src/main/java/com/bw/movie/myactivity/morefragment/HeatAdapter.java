package com.bw.movie.myactivity.morefragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.ShangMovieBean;
import com.bw.movie.view.movies.DetailsMovie;
import com.bw.movie.view.movies.RoomActivity;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 20:24
 */
public class HeatAdapter extends RecyclerView.Adapter<HeatAdapter.ViewHolder> {
    private List<ShangMovieBean.ResultBean> list;
    private Context context;

    public HeatAdapter(List<ShangMovieBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item1, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv.setText(list.get(i).name);
        Glide.with(context).load(list.get(i).imageUrl).into(viewHolder.img);
        viewHolder.tvv.setText("导演:"+list.get(i).director);
        viewHolder.tvvv.setText("主演:"+list.get(i).starring);
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
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
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv;
        private final TextView tvv;
        private final TextView tvvv;
        private final Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recy2_img);
            tv = itemView.findViewById(R.id.recy2_tv);
            tvv = itemView.findViewById(R.id.recy2_tvv);
            tvvv = itemView.findViewById(R.id.recy2_tvvv);
            btn = itemView.findViewById(R.id.recy2_btn);
        }
    }
}
