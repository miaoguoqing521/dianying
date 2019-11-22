package com.bw.movie.view.movies.buymovie;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.PriceMovieQueryCinemaBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/24 18:54
 */
public class Buy_Price_Adapter extends BaseQuickAdapter<PriceMovieQueryCinemaBean.ResultBean, BaseViewHolder> {

    public Buy_Price_Adapter(int layoutResId, @Nullable List<PriceMovieQueryCinemaBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PriceMovieQueryCinemaBean.ResultBean item) {
        helper.setText(R.id.range_name, item.name);
        helper.setText(R.id.range_add, item.address);
        ImageView rangeImg = helper.getView(R.id.range_img);
        Glide.with(mContext).load(item.logo).into(rangeImg);
    }
}
