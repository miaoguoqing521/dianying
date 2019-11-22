package com.bw.movie.presenter;

import com.bw.movie.model.bean.FileSeatBean;
import com.bw.movie.model.bean.MovieCinemaDateBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/25 10:11
 */
public class DatePresenter extends BasePresenter<IMainView.DateView> implements IBaseView {
    public void getDate(int movieId, int cinemaId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doCinemaDate(new OkHttp.CallCinemaMovieDate() {
            @Override
            public void cinemaDate(MovieCinemaDateBean movieCinemaDateBean) {
                getView().cinemaDate(movieCinemaDateBean);
            }
        },movieId,cinemaId);
    }

    public void getseatdata(int hallId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doFileSeat(new OkHttp.Callseat() {
            @Override
            public void fileseat(FileSeatBean fileSeatBean) {
                getView().fileSeat(fileSeatBean);
            }
        },hallId);
    }
}
