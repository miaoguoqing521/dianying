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
 * 日期： 2019/10/22 14:52
 */
public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.Holder> {
    private List<QueryMovieBean.ResultBean.MovieActorBean> list;
    private Context context;

    public ActorAdapter(List<QueryMovieBean.ResultBean.MovieActorBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.actor_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(context).load(list.get(i).photo).into(holder.img);
        holder.tv.setText(list.get(i).name);
        holder.tvv.setText(list.get(i).role);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv;
        private final TextView tvv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.actor_img);
            tv = itemView.findViewById(R.id.actor_tv);
            tvv = itemView.findViewById(R.id.actor_tvv);
        }
    }
}
