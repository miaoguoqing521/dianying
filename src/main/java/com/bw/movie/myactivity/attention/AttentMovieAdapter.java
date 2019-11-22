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
import com.bw.movie.model.bean.AttentMovieBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 16:37
 */
public class AttentMovieAdapter extends RecyclerView.Adapter<AttentMovieAdapter.Holder> {
    private List<AttentMovieBean.ResultBean> list;
    private Context context;

    public AttentMovieAdapter(List<AttentMovieBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.attentmovie_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(context).load(list.get(i).imageUrl).into(holder.img);
        holder.tv1.setText(list.get(i).name);
        holder.tv2.setText("导演:"+list.get(i).director);
        holder.tv3.setText("主演:"+list.get(i).starring);
        holder.tv4.setText("评分:"+list.get(i).score+"分");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv1,tv2,tv3,tv4;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.attentmovie_img);
            tv1 = itemView.findViewById(R.id.attentmovie_tv1);
            tv2 = itemView.findViewById(R.id.attentmovie_tv2);
            tv3 = itemView.findViewById(R.id.attentmovie_tv3);
            tv4 = itemView.findViewById(R.id.attentmovie_tv4);
        }
    }
}
