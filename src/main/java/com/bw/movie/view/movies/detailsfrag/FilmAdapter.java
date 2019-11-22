package com.bw.movie.view.movies.detailsfrag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.QueryCommentBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/23 15:02
 */
public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.Holder> {
    private List<QueryCommentBean.ResultBean> list;
    private Context context;

    public FilmAdapter(List<QueryCommentBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.film_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.name.setText(list.get(i).commentUserName);
        holder.num_text.setText(list.get(i).greatNum+"");
        holder.cotent.setText(list.get(i).commentContent);
        holder.score.setText(list.get(i).score+"分");
        holder.replynum.setText("等"+list.get(i).replyNum+"人回复");
        String s = String.valueOf(list.get(i).commentTime);
        String format = DateFormatUtil.format(s);
        holder.time.setText(format);
        RatingBar rating = holder.rating;
        rating.setRating((float) list.get(i).score);
        Glide.with(context).load(list.get(i).commentHeadPic).into(holder.img);
        holder.num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final ImageView img,num;
        private final TextView name;
        private final RatingBar rating;
        private final TextView score,cotent,time,num_text,replynum;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.film_img);
            name = itemView.findViewById(R.id.film_name);
            rating = itemView.findViewById(R.id.filmReview_ratingBar);
            score = itemView.findViewById(R.id.film_score);
             num = itemView.findViewById(R.id.film_num);
             cotent = itemView.findViewById(R.id.film_content);
             time = itemView.findViewById(R.id.film_time);
             num_text = itemView.findViewById(R.id.film_num_text);
             replynum = itemView.findViewById(R.id.film_replyNum);
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
