package com.bw.movie.myactivity.notice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.BookMovieBean;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 20:30
 */
public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.Holder> {
    private List<BookMovieBean.ResultBean> list;
    private Context context;

    public NoticeAdapter(List<BookMovieBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.notice_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(context).load(list.get(i).imageUrl).into(holder.img);
        holder.tv.setText(list.get(i).name);
        holder.tv2.setText(list.get(i).wantSeeNum+"人想看");
        String s = String.valueOf(list.get(i).releaseTime);
        String format = DateFormatUtil.format(s);
        holder.tv3.setText(format);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv2;
        private final TextView tv;
        private final TextView tv3;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.notice_img);
            tv2 = itemView.findViewById(R.id.notice_time);
            tv = itemView.findViewById(R.id.notice_tv);
            tv3 = itemView.findViewById(R.id.notice_proper);
        }
    }

    public static class DateFormatUtil {
        public static String format(String date){
            if (TextUtils.isEmpty(date))
                return null;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy年MM月dd日mm分ss秒");
                Long aLong = new Long(date);
                return simpleDateFormat.format(aLong);

        }
    }
}
