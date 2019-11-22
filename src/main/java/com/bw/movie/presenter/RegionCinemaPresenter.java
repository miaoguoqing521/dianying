package com.bw.movie.presenter;

import com.bw.movie.model.bean.QueryRegionBean;
import com.bw.movie.model.bean.RegionCinemaBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/19 08:58
 */
public class RegionCinemaPresenter extends BasePresenter<IMainView.IRegionCinema> implements IBaseView {
    public void getDate(int regionId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doRegionCinema(new OkHttp.CallRegionCinema() {
            @Override
            public void regioncinema(RegionCinemaBean regionCinemaBean) {
                getView().regioncinema(regionCinemaBean);
            }
        },regionId);
    }

    public void getDatee() {
        OkHttp instance = OkHttp.getInstance();
        instance.doRegion(new OkHttp.CallRegion() {
            @Override
            public void queryregion(QueryRegionBean regionBean) {
                getView().region(regionBean);
            }
        });
    }
}
