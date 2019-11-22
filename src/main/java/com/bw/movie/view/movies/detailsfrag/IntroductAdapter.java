package com.bw.movie.view.movies.detailsfrag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.QueryMovieBean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/22 14:34
 */
public class IntroductAdapter extends RecyclerView.Adapter<IntroductAdapter.Holder> {
    private List<QueryMovieBean.ResultBean.MovieDirectorBean> list;
    private Context context;

    public IntroductAdapter(List<QueryMovieBean.ResultBean.MovieDirectorBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.introduct_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(context).load(list.get(i).photo).into(holder.img);
        holder.tv.setText(list.get(i).name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.introduct_img);
            tv = itemView.findViewById(R.id.introduct_tv);
        }
    }
}
