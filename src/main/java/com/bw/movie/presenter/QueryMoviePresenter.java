package com.bw.movie.presenter;

import com.bw.movie.model.bean.AttentionBean;
import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/17 13:53
 */
public class QueryMoviePresenter extends BasePresenter<IMainView.IMovieView> implements IBaseView {

    public void getDate(String userId, String sessionId, int movieId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doMovie(new OkHttp.CallMovie() {
            @Override
            public void movie(QueryMovieBean queryMovieBean) {
                getView().movie(queryMovieBean);
            }
        },userId,sessionId,movieId);
    }

    public void getDetailDate(String userId, String sessionId, int movieId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doAttention(new OkHttp.CallAttention() {
            @Override
            public void attention(AttentionBean attentionBean) {
                getView().attent(attentionBean);
            }
        },userId,sessionId,movieId);
    }
}
