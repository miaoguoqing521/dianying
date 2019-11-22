package com.bw.movie.presenter;

import com.bw.movie.model.bean.NearCinema;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/12 08:52
 */
public class NearPresenter extends BasePresenter<IMainView.INearView> implements IBaseView {
    public void getDate(int page) {
        OkHttp instance = OkHttp.getInstance();
        instance.doNear(new OkHttp.BackNear() {
            @Override
            public void Near(NearCinema nearCinema) {
                getView().nearcinema(nearCinema);
            }
        },page);
    }
}
