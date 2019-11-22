package com.bw.movie.view.cinema.cinemafragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaEvaluationBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 16:43
 */
public class CinemaEvaluationAdapter extends RecyclerView.Adapter<CinemaEvaluationAdapter.Holder> {
    private List<CinemaEvaluationBean.ResultBean> list;
    private Context context;

    public CinemaEvaluationAdapter(List<CinemaEvaluationBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cinemaevaluation_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(context).load(list.get(i).commentHeadPic).apply(RequestOptions.circleCropTransform())
                .into(holder.system_img);
        holder.system_name.setText(list.get(i).commentUserName);
        holder.system_text.setText(list.get(i).commentContent);
        String time = String.valueOf(list.get(i).commentTime);
        String format = DateFormatUtil.format(time);
        holder.system_time.setText(format);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final ImageView system_img;
        private final TextView system_name;
        private final TextView system_time;
        private final TextView system_text;

        public Holder(@NonNull View itemView) {
            super(itemView);
            system_img = itemView.findViewById(R.id.system_img);
            system_name = itemView.findViewById(R.id.system_name);
            system_time = itemView.findViewById(R.id.system_time);
            system_text = itemView.findViewById(R.id.system_text);
        }
    }
    public static class DateFormatUtil {
        public static String format(String date) {
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日mm分ss秒");
            Long time = new Long(date);
            return format.format(time);
        }
    }
}
