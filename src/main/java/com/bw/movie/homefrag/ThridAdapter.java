package com.bw.movie.homefrag;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.QueryRegionBean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/19 09:11
 */
public class ThridAdapter extends RecyclerView.Adapter<ThridAdapter.Holder> {
    private List<QueryRegionBean.ResultBean> list;
    private Context context;

    public ThridAdapter(List<QueryRegionBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.thriditem, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.tv.setText(list.get(i).regionName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

        private final TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.thrid_tv);
        }
    }
    public interface setOnItemCilck{
        void onItemClick(int position);
    }
    private setOnItemCilck setOnItemCilck;

    public void setSetOnItemCilck(ThridAdapter.setOnItemCilck setOnItemCilck) {
        this.setOnItemCilck = setOnItemCilck;
    }
}
