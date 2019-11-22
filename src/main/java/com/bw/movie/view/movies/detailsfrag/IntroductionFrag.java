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
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
 * 日期： 2019/10/17 14:56
 */
public class IntroductionFrag extends Fragment implements IMainView.IMovieView {
    @BindView(R.id.introduct_content)
    TextView introductContent;
    @BindView(R.id.cast)
    TextView cast;
    @BindView(R.id.introduct_recy)
    RecyclerView introductRecy;
    @BindView(R.id.actor)
    TextView actor;
    @BindView(R.id.actor_recy)
    RecyclerView actorRecy;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.introductionfrag, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        SharedPreferences isLogin = getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        SharedPreferences sp = getContext().getSharedPreferences("isP", Context.MODE_PRIVATE);
        int movieId = sp.getInt("movieId", 0);
        final String userId = isLogin.getString("userId", "");
        final String sessionId = isLogin.getString("sessionId", "");
        MoviePresenter moviePresenter = new MoviePresenter();
        moviePresenter.attach(this);
        moviePresenter.getDate(movieId,userId,sessionId);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        introductRecy.setLayoutManager(linearLayoutManager);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        actorRecy.setLayoutManager(staggeredGridLayoutManager);
        return inflate;
    }


    @Override
    public void movie(QueryMovieBean queryMovieBean) {
        QueryMovieBean.ResultBean result = queryMovieBean.result;
        introductContent.setText(result.summary);
        cast.setText("("+result.movieDirector.size()+")");
        actor.setText("("+result.commentNum+")");
        List<QueryMovieBean.ResultBean.MovieDirectorBean> director = result.movieDirector;
        IntroductAdapter introductAdapter=new IntroductAdapter(director,getContext());
        introductRecy.setAdapter(introductAdapter);

        List<QueryMovieBean.ResultBean.MovieActorBean> actor = result.movieActor;
        ActorAdapter actorAdapter=new ActorAdapter(actor,getActivity());
        actorRecy.setAdapter(actorAdapter);
    }

    @Override
    public void attent(AttentionBean attentionBean) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
