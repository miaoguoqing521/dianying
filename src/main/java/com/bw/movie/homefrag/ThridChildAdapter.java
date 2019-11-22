package com.bw.movie.homefrag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.RegionCinemaBean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/19 09:39
 */
public class ThridChildAdapter extends RecyclerView.Adapter<ThridChildAdapter.Holder> {
    private List<RegionCinemaBean.ResultBean> list;
    private Context context;

    public ThridChildAdapter(List<RegionCinemaBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.thridchilditem, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.tv.setText(list.get(i).name);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childItemClick.childItem(i);
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
            tv = itemView.findViewById(R.id.thridchild_tv);
        }
    }
    public interface childItemClick{
        void childItem(int position);
    }
    private childItemClick childItemClick;

    public void setChildItemClick(ThridChildAdapter.childItemClick childItemClick) {
        this.childItemClick = childItemClick;
    }
}
