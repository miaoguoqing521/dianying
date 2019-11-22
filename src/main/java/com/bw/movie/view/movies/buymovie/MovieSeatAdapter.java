package com.bw.movie.view.movies.buymovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bw.movie.R;
import com.bw.movie.model.bean.FileSeatBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/25 10:37
 */
public class MovieSeatAdapter extends BaseQuickAdapter<FileSeatBean.ResultBean, BaseViewHolder> {
    private int status;

    public MovieSeatAdapter(int layoutResId, @Nullable List<FileSeatBean.ResultBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, final FileSeatBean.ResultBean item) {
        final CheckBox che_box = helper.getView(R.id.che_box);
        String row = item.row;
        String seat1 = item.seat;
        String seat = row + "-" + seat1;
        SharedPreferences sp = mContext.getSharedPreferences("isZz", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("seat",seat);
        edit.commit();

        int status = item.status;
        if (status == 1) {
            che_box.setChecked(false);
        } else if (status == 2) {
            che_box.setChecked(true);
            che_box.setBackgroundColor(R.drawable.myy_shape);
        }
        che_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che_box.isChecked()) {
                    item.setStatus(3);
                    callBack.getBack(item.row + "排" + item.seat + "座");
                } else {
                    item.setStatus(1);
                    callBack.getBack("取消选座");
                }
            }
        });
    }

    public interface CallBack {
        void getBack(String s);
    }

    CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
