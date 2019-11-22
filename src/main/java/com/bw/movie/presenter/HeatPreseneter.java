package com.bw.movie.presenter;

import com.bw.movie.model.bean.ShangMovieBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMoreView;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 19:55
 */
public class HeatPreseneter extends BasePresenter<IMoreView.HeatView> implements IBaseView {
    public void getDate() {
        OkHttp instance = OkHttp.getInstance();
        instance.doShang(new OkHttp.BackShang() {
            @Override
            public void shang(ShangMovieBean shangMovieBean) {
                getView().suc(shangMovieBean);
            }
        });
    }
}
