package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.model.bean.CinemaBean;
import com.bw.movie.model.bean.MovieIdCinemaBean;
import com.bw.movie.model.bean.PriceMovieQueryCinemaBean;
import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.model.bean.RangeBean;
import com.bw.movie.model.bean.SchduBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/24 09:41
 */
public class MovieIdCinemaPresenter extends BasePresenter<IMainView.IRangeView> implements IBaseView {

    private OkHttp instance = new OkHttp();

    public void getPrice(int movieId) {
        instance.doPriceCinema(new OkHttp.CallpriceCinemaBean() {
            @Override
            public void priceCinemaBean(PriceMovieQueryCinemaBean priceMovieQueryCinemaBean) {
                getView().priceMovie(priceMovieQueryCinemaBean);
            }
        },movieId);
    }
    //查询区域
    public void getDate() {
        instance.doCinema(new OkHttp.CallcinemaBean() {
            @Override
            public void cinemas(CinemaBean cinemaBean) {
                Log.i("qqq1",""+cinemaBean);
                getView().cinema(cinemaBean);
            }
        });
    }
    //排期
    public void getSchu() {
        instance.doSchdu(new OkHttp.Callschdu() {
            @Override
            public void schdu(SchduBean schduBean) {
                getView().schdu(schduBean);
            }
        });
    }

    public void getrangedata(int movieId, int regionId) {
        instance = OkHttp.getInstance();
        instance.doRange(new OkHttp.CallRange() {
            @Override
            public void range(RangeBean rangeBean) {
                getView().range(rangeBean);
            }
        },movieId,regionId);
    }


    public void getzhudata(int movieId, String toString) {
        instance.doMovieIdCinema(new OkHttp.CallmovieIdCinema() {
            @Override
            public void movieIdCinema(MovieIdCinemaBean movieIdCinemaBean) {
                getView().movieIdCinema(movieIdCinemaBean);
            }
        },movieId,toString);
    }
}
