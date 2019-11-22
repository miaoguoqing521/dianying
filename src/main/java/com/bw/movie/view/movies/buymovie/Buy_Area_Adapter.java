package com.bw.movie.view.movies.buymovie;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stx.xhb.xbanner.OnDoubleClickListener;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/24 10:52
 */
public class Buy_Area_Adapter extends BaseQuickAdapter<CinemaBean.ResultBean, BaseViewHolder> {
    public Buy_Area_Adapter(int layoutResId, @Nullable List<CinemaBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CinemaBean.ResultBean item) {
        helper.setText(R.id.pop_name, item.regionName);
        TextView popName = helper.getView(R.id.pop_name);
        popName.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                onClickRegion.region(item.regionId,item.regionName);
            }
        });
    }


    public interface OnClickRegion {
        public void region(int regionId, String regionName);
    }

    public OnClickRegion onClickRegion;

    public void setOnClickRegion(OnClickRegion onClickRegion) {
        this.onClickRegion = onClickRegion;
    }
}
