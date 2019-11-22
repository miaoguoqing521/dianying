package com.bw.movie.myactivity.attention;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.AttentCinemaBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 19:47
 */
public class AttentCinemaAdapter extends RecyclerView.Adapter<AttentCinemaAdapter.Holder> {
    private List<AttentCinemaBean.ResultBean> list;
    private Context context;

    public AttentCinemaAdapter(List<AttentCinemaBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.attentcinema_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tv.setText(list.get(i).name);
        Glide.with(context).load(list.get(i).logo).into(holder.img);
        holder.tvv.setText(list.get(i).address);
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
            img = itemView.findViewById(R.id.attentcinema_img);
            tv = itemView.findViewById(R.id.attentcinema_tv);
            tvv = itemView.findViewById(R.id.attentcinema_tvv);
        }
    }
}
