package com.bw.movie.presenter;

import com.bw.movie.model.bean.AttentMovieBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IAttent;
import com.bw.movie.view.interfaces.IBaseView;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 16:35
 */
public class AttentMoviePresenter extends BasePresenter<IAttent.IAttentMovie> implements IBaseView {

    public void getDate(String userId, String sessionId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doAttentMovie(new OkHttp.CallAttentMovie() {
            @Override
            public void attentMovie(AttentMovieBean attentMovieBean) {
                getView().succe(attentMovieBean);
            }
        },userId,sessionId);
    }
}
