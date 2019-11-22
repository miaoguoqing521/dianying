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
import com.bw.movie.model.bean.AttentionBean;
import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.presenter.MoviePresenter;
import com.bw.movie.view.interfaces.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/17 14:57
 */
public class TrailerFrag extends Fragment implements IMainView.IMovieView {
    @BindView(R.id.trailer_recy)
    RecyclerView trailerRecy;
    Unbinder unbinder;
    private ThrailerAdapter thrailerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.trailer, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        trailerRecy.setLayoutManager(linearLayoutManager);

        SharedPreferences isLogin = getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        SharedPreferences sp = getContext().getSharedPreferences("isP", Context.MODE_PRIVATE);
        int movieId = sp.getInt("movieId", 0);
        final String userId = isLogin.getString("userId", "");
        final String sessionId = isLogin.getString("sessionId", "");
        MoviePresenter moviePresenter = new MoviePresenter();
        moviePresenter.attach(this);
        moviePresenter.getDate(movieId,userId,sessionId);
        return inflate;
    }

    @Override
    public void movie(QueryMovieBean queryMovieBean) {
        QueryMovieBean.ResultBean result = queryMovieBean.result;
        List<QueryMovieBean.ResultBean.ShortFilmListBean> shortFilmList = result.shortFilmList;
        thrailerAdapter = new ThrailerAdapter(shortFilmList, getActivity());
        trailerRecy.setAdapter(thrailerAdapter);

    }

    @Override
    public void attent(AttentionBean attentionBean) {

    }

    @Override
    public void onPause() {
        super.onPause();
        thrailerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
