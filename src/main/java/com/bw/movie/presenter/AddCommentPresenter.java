package com.bw.movie.presenter;

import com.bw.movie.model.bean.AddCommentBean;
import com.bw.movie.model.okhttp.OkHttp;
import com.bw.movie.view.interfaces.IBaseView;
import com.bw.movie.view.interfaces.IMainView;

/**
 * 作者： 姓名
 * 日期： 2019/10/23 14:11
 */
public class AddCommentPresenter extends BasePresenter<IMainView.AddComentView> implements IBaseView {
    public void getDate(String userId, String sessionId, int movieId, String trim, double rating) {
        OkHttp instance = OkHttp.getInstance();
        instance.doCallAddComment(new OkHttp.CallAdd() {
            @Override
            public void addcomment(AddCommentBean addCommentBean) {
                getView().addComent(addCommentBean);
            }
        },userId,sessionId,movieId,trim,rating);
    }
}
