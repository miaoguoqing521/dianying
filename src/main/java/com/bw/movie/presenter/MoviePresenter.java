package com.bw.movie.presenter;

import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/22 14:13
 */
public class MoviePresenter extends BasePresenter<IMainView.IMovieView> implements IBaseView {
    public void getDate(int movieId,String userId, String sessionId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doMovie(new OkHttp.CallMovie() {
            @Override
            public void movie(QueryMovieBean queryMovieBean) {
                getView().movie(queryMovieBean);
            }
        },userId,sessionId,movieId);

    }
}
