package com.bw.movie.presenter;

import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 13:57
 */
public class SearchPresenter extends BasePresenter<IMainView.ISearch> implements IBaseView {
    public void getDate(String trim) {
        OkHttp instance = OkHttp.getInstance();
        instance.doSearch(new OkHttp.CallSearch() {
            @Override
            public void search(SearchBean searchBean) {
                getView().search(searchBean);
            }
        },trim);
    }
}
