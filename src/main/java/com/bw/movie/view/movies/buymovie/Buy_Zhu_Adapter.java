package com.bw.movie.view.movies.buymovie;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.MovieIdCinemaBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/24 18:57
 */
public class Buy_Zhu_Adapter extends BaseQuickAdapter<MovieIdCinemaBean.ResultBean, BaseViewHolder> {
    public Buy_Zhu_Adapter(int layoutResId, @Nullable List<MovieIdCinemaBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieIdCinemaBean.ResultBean item) {
        helper.setText(R.id.range_name, item.name);
        helper.setText(R.id.range_add, item.address);
        ImageView rangeImg = helper.getView(R.id.range_img);
        Glide.with(mContext).load(item.logo).into(rangeImg);
    }
}
