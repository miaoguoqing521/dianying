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
import com.bw.movie.model.bean.ReMovieBean;
import com.bw.movie.view.movies.DetailsMovie;
import com.bw.movie.view.movies.RoomActivity;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 20:41
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolds> {
    private List<ReMovieBean.ResultBean> list;
    private Context context;

    public HotAdapter(List<ReMovieBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolds onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item1, null);
        ViewHolds viewHolds = new ViewHolds(inflate);
        return viewHolds;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolds viewHolds, final int i) {
        viewHolds.tv.setText(list.get(i).name);
        Glide.with(context).load(list.get(i).imageUrl).into(viewHolds.img);
        viewHolds.tvv.setText("导演:"+list.get(i).director);
        viewHolds.tvvv.setText("主演:"+list.get(i).starring);
        viewHolds.btn.setOnClickListener(new View.OnClickListener() {
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
        viewHolds.itemView.setOnClickListener(new View.OnClickListener() {
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

    class ViewHolds extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv;
        private final TextView tvv;
        private final TextView tvvv;
        private final Button btn;

        public ViewHolds(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recy2_img);
            tv = itemView.findViewById(R.id.recy2_tv);
            tvv = itemView.findViewById(R.id.recy2_tvv);
            tvvv = itemView.findViewById(R.id.recy2_tvvv);
            btn = itemView.findViewById(R.id.recy2_btn);
        }
    }
}
