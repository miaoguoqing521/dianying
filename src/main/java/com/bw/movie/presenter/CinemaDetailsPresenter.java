package com.bw.movie.presenter;

import com.bw.movie.model.bean.QueryCinemaDetails;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/11/19 18:58
 */
public class CinemaDetailsPresenter extends BasePresenter<IMainView.DetaulsView> implements IBaseView {

    public void getDate(String userId, String sessionId, int cinemaId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doDetails(new OkHttp.CallDetails() {
            @Override
            public void details(QueryCinemaDetails queryCinemaDetails) {
                getView().details(queryCinemaDetails);
            }
        },userId,sessionId,cinemaId);
    }
}
