package com.bw.movie.presenter;

import com.bw.movie.model.bean.CinemaEvaluationBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 16:40
 */
public class CinemaEvaluationPresenter extends BasePresenter<IMainView.CinemaEvaluetion> implements IBaseView {
    public void getDate(String userId, String sessionId, int cinemaId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doEvaluetion(new OkHttp.CallEvalue() {
            @Override
            public void evaluetion(CinemaEvaluationBean cinemaEvaluationBean) {
                getView().evaluetion(cinemaEvaluationBean);
            }
        },userId,sessionId,cinemaId);
    }
}
