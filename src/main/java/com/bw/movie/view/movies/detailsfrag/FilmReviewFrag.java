package com.bw.movie.view.movies.detailsfrag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.QueryCommentBean;
import com.bw.movie.presenter.QueryCommentPresenter;
import com.bw.movie.view.interfaces.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/17 14:58
 */
public class FilmReviewFrag extends Fragment implements IMainView.QueryCommentView {
    @BindView(R.id.film_recy)
    RecyclerView filmRecy;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.filmreviewfrag, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        SharedPreferences isLogin = getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String userId = isLogin.getString("userId", "");
        String sessionId = isLogin.getString("sessionId", "");
        SharedPreferences sp = getContext().getSharedPreferences("isP", Context.MODE_PRIVATE);
        int movieId = sp.getInt("movieId", 0);
        QueryCommentPresenter queryCommentPresenter = new QueryCommentPresenter();
        queryCommentPresenter.attach(this);
        queryCommentPresenter.getDate(userId,sessionId,movieId);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        filmRecy.setLayoutManager(linearLayoutManager);
        return inflate;
    }

    @Override
    public void queryComment(QueryCommentBean queryCommentBean) {
        List<QueryCommentBean.ResultBean> result = queryCommentBean.result;
        FilmAdapter filmAdapter = new FilmAdapter(result, getActivity());
        filmRecy.setAdapter(filmAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
