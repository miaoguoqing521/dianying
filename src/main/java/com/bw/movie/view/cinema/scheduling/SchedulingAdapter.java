package com.bw.movie.view.cinema.scheduling;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaSchedulingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/11/21 10:30
 */
public class SchedulingAdapter extends RecyclerView.Adapter<SchedulingAdapter.Holder> {
    private List<CinemaSchedulingBean.ResultBean> list;
    private Context context;

    public SchedulingAdapter(List<CinemaSchedulingBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.scheduling_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.name.setText(list.get(i).name);
        holder.doctor.setText("导演:"+list.get(i).director);
        holder.zhuyan.setText("主演:"+list.get(i).starring);
        holder.pingfen.setText("评分:"+list.get(i).score+"分");
        holder.sim.setImageURI(list.get(i).imageUrl);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduClick.scheClick(i);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduClick.scheClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final Button btn;
        private final TextView zhuyan;
        private final SimpleDraweeView sim;
        private final TextView pingfen;
        private final TextView name;
        private final TextView doctor;

        public Holder(@NonNull View itemView) {
            super(itemView);
            doctor = itemView.findViewById(R.id.schedu_doctor);
            name = itemView.findViewById(R.id.schedu_name);
            pingfen = itemView.findViewById(R.id.schedu_pingfen);
            sim = itemView.findViewById(R.id.schedu_sim);
            zhuyan = itemView.findViewById(R.id.schedu_zhuyan);
            btn = itemView.findViewById(R.id.shceduling_btn);
        }
    }
    public interface ScheduClick{
        void scheClick(int position);
    }
    private ScheduClick scheduClick;

    public void setScheduClick(ScheduClick scheduClick) {
        this.scheduClick = scheduClick;
    }
}
