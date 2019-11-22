package com.bw.movie.presenter;

import com.bw.movie.model.bean.BookMovieBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IAttent;
import com.bw.movie.view.interfaces.IBaseView;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 20:26
 */
public class NoticePresenter extends BasePresenter<IAttent.INoticeView> implements IBaseView {
    public void getDate(String userId, String sessionId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doBookMovie(new OkHttp.CallBookMovie() {
            @Override
            public void bookmovie(BookMovieBean bookMovieBean) {
                getView().succ(bookMovieBean);
            }
        },userId,sessionId);
    }
}
