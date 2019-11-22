package com.bw.movie.presenter;

import com.bw.movie.model.bean.RecommendedCinemaBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/11 16:21
 */
public class RecommendPresenter extends BasePresenter<IMainView.IRecommend> implements IBaseView {
    public void getDate() {
        OkHttp instance = OkHttp.getInstance();
        instance.doRecommend(new OkHttp.BackRecommend() {
            @Override
            public void Recommedd(RecommendedCinemaBean recommendedCinemaBean) {
                getView().reco(recommendedCinemaBean);
            }
        });
    }
}
