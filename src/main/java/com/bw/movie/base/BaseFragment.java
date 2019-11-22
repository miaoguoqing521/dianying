package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * 作者： 姓名
 * 日期： 2019/11/19 09:35
 */
public abstract class BaseFragment extends Fragment {
    private boolean isVisible;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isVisible=true;
        loader();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        loader();
    }

    private void loader() {
        if (getUserVisibleHint()&&isVisible){
            initDate();
        }else {
            Log.e("MMM","不加载");
        }
    }

    protected abstract void initDate();
}
