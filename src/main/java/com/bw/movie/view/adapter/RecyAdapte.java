package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.JiMoviewBean;
import com.bw.movie.view.movies.RoomActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/10 20:10
 */
public class RecyAdapte extends RecyclerView.Adapter<RecyAdapte.ViewHolds> {
    private List<JiMoviewBean.ResultBean> list;
    private Context context;

    public RecyAdapte(List<JiMoviewBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolds onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item1, null);
        ViewHolds viewHolds = new ViewHolds(inflate);
        return viewHolds;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolds viewHolds, final int i) {
        viewHolds.tv.setText(list.get(i).name);
        viewHolds.img.setImageURI(list.get(i).imageUrl);
        long releaseTime = list.get(i).releaseTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
        String format = simpleDateFormat.format(releaseTime);
        viewHolds.tvv.setText(""+format);
        viewHolds.tvvv.setText(list.get(i).wantSeeNum+"人想看");
        viewHolds.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RoomActivity.class);
                SharedPreferences isP = context.getSharedPreferences("isP", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = isP.edit();
                edit.putInt("movieId",list.get(i).movieId);
                edit.commit();
                context.startActivity(intent);
            }
        });
        viewHolds.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItem.setOn(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolds extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView tv;
        private final TextView tvv;
        private final TextView tvvv;
        private final Button btn;

        public ViewHolds(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recy2_img);
            tv = itemView.findViewById(R.id.recy2_tv);
            tvv = itemView.findViewById(R.id.recy2_tvv);
            tvvv = itemView.findViewById(R.id.recy2_tvvv);
            btn = itemView.findViewById(R.id.recy2_btn);
        }
    }
    public interface setOnItem{
        void setOn(int position);
    }
    private setOnItem setOnItem;

    public void setSetOnItem(RecyAdapte.setOnItem setOnItem) {
        this.setOnItem = setOnItem;
    }
}
