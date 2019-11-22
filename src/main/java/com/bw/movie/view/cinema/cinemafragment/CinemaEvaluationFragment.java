package com.bw.movie.view.cinema.cinemafragment;

import android.content.Context;
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
import com.bw.movie.model.bean.CinemaEvaluationBean;
import com.bw.movie.presenter.CinemaEvaluationPresenter;
import com.bw.movie.view.interfaces.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 15:06
 */
public class CinemaEvaluationFragment extends Fragment implements IMainView.CinemaEvaluetion {
    @BindView(R.id.evaluation_recy)
    RecyclerView evaluationRecy;
    Unbinder unbinder;
    private CinemaEvaluationPresenter cinemaEvaluationPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.cinemaevaluation_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        SharedPreferences isLogin = getActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String userId = isLogin.getString("userId", "");
        String sessionId = isLogin.getString("sessionId", "");
        SharedPreferences isL = getActivity().getSharedPreferences("isL", Context.MODE_PRIVATE);
        int cinemaId = isL.getInt("cinemaId", 0);
        cinemaEvaluationPresenter = new CinemaEvaluationPresenter();
        cinemaEvaluationPresenter.attach(this);
        cinemaEvaluationPresenter.getDate(userId, sessionId, cinemaId);
        return inflate;
    }

    @Override
    public void evaluetion(CinemaEvaluationBean cinemaEvaluationBean) {
        List<CinemaEvaluationBean.ResultBean> result = cinemaEvaluationBean.result;
        CinemaEvaluationAdapter cinemaEvaluationAdapter = new CinemaEvaluationAdapter(result, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        evaluationRecy.setLayoutManager(linearLayoutManager);
        evaluationRecy.setAdapter(cinemaEvaluationAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
