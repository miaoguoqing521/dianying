package com.bw.movie.presenter;

import com.bw.movie.model.bean.ReMovieBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMoreView;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 19:56
 */
public class HotPresenter extends BasePresenter<IMoreView.HotView> implements IBaseView {
    public void getDate() {
        OkHttp instance = OkHttp.getInstance();
        instance.doRe(new OkHttp.BackRe() {
            @Override
            public void Re(ReMovieBean reMovieBean) {
                getView().re(reMovieBean);
            }
        });
    }
}
