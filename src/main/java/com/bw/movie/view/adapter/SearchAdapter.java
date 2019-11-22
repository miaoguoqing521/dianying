package com.bw.movie.view.adapter;

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
import com.bw.movie.homefrag.ThridAdapter;
import com.bw.movie.model.bean.SearchBean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 14:01
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder> {
    private List<SearchBean.ResultBean> list;
    private Context context;

    public SearchAdapter(List<SearchBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.search_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.name.setText(list.get(i).name);
        holder.daoyan.setText("导演:"+list.get(i).director);
        holder.pingfen.setText("评分:"+list.get(i).score+"分");
        holder.zhuyan.setText("主演:"+list.get(i).starring);
        Glide.with(context).load(list.get(i).imageUrl).into(holder.img);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItemCilck.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView daoyan;
        private final TextView name;
        private final TextView pingfen;
        private final TextView zhuyan;
        private final TextView btn;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.search_img);
            daoyan = itemView.findViewById(R.id.search_daoyan);
            name = itemView.findViewById(R.id.search_name);
            pingfen = itemView.findViewById(R.id.search_pingfen);
            zhuyan = itemView.findViewById(R.id.search_zhuyan);
            btn = itemView.findViewById(R.id.search_btn);
        }
    }
    public interface setOnItemCilck{
        void onItemClick(int position);
    }
    private setOnItemCilck setOnItemCilck;

    public void setSetOnItemCilck(SearchAdapter.setOnItemCilck setOnItemCilck) {
        this.setOnItemCilck = setOnItemCilck;
    }
}
