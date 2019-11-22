package com.bw.movie.myactivity.attention;

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
import com.bw.movie.model.bean.AttentMovieBean;
import com.bw.movie.presenter.AttentMoviePresenter;
import com.bw.movie.view.interfaces.IAttent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 14:33
 */
public class AttentionActivity extends Fragment implements IAttent.IAttentMovie{
    @BindView(R.id.attent_recy)
    RecyclerView attentRecy;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.attention_activity, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        attentRecy.setLayoutManager(linearLayoutManager);
        AttentMoviePresenter attentMoviePresenter = new AttentMoviePresenter();
        attentMoviePresenter.attach(this);
        SharedPreferences isLog = getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String userId = isLog.getString("userId", "");
        String sessionId = isLog.getString("sessionId", "");
        attentMoviePresenter.getDate(userId,sessionId);
        return inflate;
    }
    @Override
    public void succe(AttentMovieBean attentMovieBean) {
        List<AttentMovieBean.ResultBean> result = attentMovieBean.result;
        AttentMovieAdapter attentMovieAdapter = new AttentMovieAdapter(result, getActivity());
        attentRecy.setAdapter(attentMovieAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
