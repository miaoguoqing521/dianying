package com.bw.movie.presenter;

import com.bw.movie.model.bean.AttentCinemaBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IAttent;
import com.bw.movie.view.interfaces.IBaseView;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 19:41
 */
public class AttentCinemaPresenter extends BasePresenter<IAttent.IAttentCinema> implements IBaseView {
    public void getDate(String userId, String sessionId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doAttentCinema(new OkHttp.CallAttentCinema() {
            @Override
            public void attentCinema(AttentCinemaBean attentCinemaBean) {
                getView().success(attentCinemaBean);
            }
        },userId,sessionId);
    }
}
