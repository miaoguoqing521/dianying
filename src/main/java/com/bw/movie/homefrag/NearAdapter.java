package com.bw.movie.homefrag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.NearCinema;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/12 08:57
 */
public class NearAdapter extends RecyclerView.Adapter<NearAdapter.Holder> {
    private List<NearCinema.ResultBean> list;
    private Context context;

    public NearAdapter(List<NearCinema.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.nearitem, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tv.setText(list.get(i).name);
        holder.tvv.setText(list.get(i).address);
        holder.tvvv.setText(list.get(i).distance+"m");
        Glide.with(context).load(list.get(i).logo).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv;
        private final TextView tvv;
        private final TextView tvvv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.near_img);
            tv = itemView.findViewById(R.id.near_tv);
            tvv = itemView.findViewById(R.id.near_tvv);
            tvvv = itemView.findViewById(R.id.near_tvvv);
        }
    }
}
