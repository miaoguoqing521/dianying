package com.bw.movie.view.movies.buymovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.model.bean.MovieCinemaDateBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/25 10:29
 */
public class Room_Recycler_Adapter extends BaseQuickAdapter<MovieCinemaDateBean.ResultBean, BaseViewHolder> {
    public Room_Recycler_Adapter(int layoutResId, @Nullable List<MovieCinemaDateBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieCinemaDateBean.ResultBean item) {
        helper.setText(R.id.recycler_type, item.screeningHall);
        helper.setText(R.id.recycler_Begintime, item.beginTime+"-");
        helper.setText(R.id.recycler_endtime, item.endTime);
        int id = item.id;
        int hallId = item.hallId;
        SharedPreferences sp = mContext.getSharedPreferences("hallId", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("hallId",hallId);
        edit.putInt("id",id);
        edit.commit();
    }

}
