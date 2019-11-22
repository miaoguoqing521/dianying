package com.bw.movie.presenter;

import com.bw.movie.model.bean.CinemaSchedulingBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/11/21 10:26
 */
public class ScheduleTingPresenter extends BasePresenter<IMainView.SchedulingView> implements IBaseView {
    public void getDate(int cinemaId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doScheduling(new OkHttp.CallScheduling() {
            @Override
            public void scheduling(CinemaSchedulingBean cinemaSchedulingBean) {
                getView().scheduling(cinemaSchedulingBean);
            }
        },cinemaId);
    }
}
