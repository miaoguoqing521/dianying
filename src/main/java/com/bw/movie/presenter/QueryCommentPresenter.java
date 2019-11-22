package com.bw.movie.presenter;

import com.bw.movie.model.bean.QueryCommentBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/23 14:57
 */
public class QueryCommentPresenter extends BasePresenter<IMainView.QueryCommentView> implements IBaseView {
    public void getDate(String userId, String sessionId, int movieId) {
        OkHttp instance = OkHttp.getInstance();
        instance.doQueryComment(new OkHttp.CallQueryComment() {
            @Override
            public void queryComment(QueryCommentBean queryCommentBean) {
                getView().queryComment(queryCommentBean);
            }
        },userId,sessionId,movieId);
    }
}
