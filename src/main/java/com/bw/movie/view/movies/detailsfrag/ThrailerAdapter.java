package com.bw.movie.view.movies.detailsfrag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.QueryMovieBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * 作者： 姓名
 * 日期： 2019/10/22 15:41
 */
public class ThrailerAdapter extends RecyclerView.Adapter<ThrailerAdapter.Holder> {
    private List<QueryMovieBean.ResultBean.ShortFilmListBean> listBeans;
    private Context context;

    public ThrailerAdapter(List<QueryMovieBean.ResultBean.ShortFilmListBean> listBeans, Context context) {
        this.listBeans = listBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.thrailer_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(context).load(listBeans.get(i).imageUrl).into(holder.videp.ivThumb);
        holder.videp.setUp(listBeans.get(i).videoUrl,null);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final JCVideoPlayer videp;

        public Holder(@NonNull View itemView) {
            super(itemView);
            videp = itemView.findViewById(R.id.video);
        }
    }
}
