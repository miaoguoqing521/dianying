package com.bw.movie.view.movies.buymovie;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.RangeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class Buy_Range_Adapter extends BaseQuickAdapter<RangeBean.ResultBean, BaseViewHolder> {
    public Buy_Range_Adapter(int layoutResId, @Nullable List<RangeBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RangeBean.ResultBean item) {
        helper.setText(R.id.range_name, item.name);
        helper.setText(R.id.range_add, item.address);
        ImageView rangeImg = helper.getView(R.id.range_img);
        Glide.with(mContext).load(item.logo).into(rangeImg);
    }
}
