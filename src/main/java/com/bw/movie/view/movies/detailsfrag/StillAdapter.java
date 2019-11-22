package com.bw.movie.view.movies.detailsfrag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.QueryMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/11/18 15:57
 */
public class StillAdapter extends RecyclerView.Adapter<StillAdapter.Holder> {
    private List<String> list;
    private Context context;

    public StillAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.still_itm, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.sim.setImageURI(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sim;

        public Holder(@NonNull View itemView) {
            super(itemView);
            sim = itemView.findViewById(R.id.still_sim);
        }
    }
}
