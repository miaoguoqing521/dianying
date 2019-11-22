package com.bw.movie.presenter;

import com.bw.movie.model.bean.SearchCinemaBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/11/13 18:59
 */
public class SearchCinemaPresenter extends BasePresenter<IMainView.SearchCinemaView> implements IBaseView {

    public void getDate(String trim) {
        OkHttp instance = OkHttp.getInstance();
        instance.doSearchCinema(new OkHttp.CallSearchCinema() {
            @Override
            public void searchcinema(SearchCinemaBean searchCinemaBean) {
                getView().searchCinema(searchCinemaBean);
            }
        },trim);
    }
}
