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
import com.bw.movie.model.bean.AttentCinemaBean;
import com.bw.movie.presenter.AttentCinemaPresenter;
import com.bw.movie.view.interfaces.IAttent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 14:34
 */
public class AttentionCinemaActivity extends Fragment implements IAttent.IAttentCinema {
    @BindView(R.id.attentcinema_recy)
    RecyclerView attentcinemaRecy;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.attentcinema_activity, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        AttentCinemaPresenter attentCinemaPresenter = new AttentCinemaPresenter();
        attentCinemaPresenter.attach(this);
        SharedPreferences isLogin = getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String userId = isLogin.getString("userId", "");
        String sessionId = isLogin.getString("sessionId", "");
        attentCinemaPresenter.getDate(userId,sessionId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        attentcinemaRecy.setLayoutManager(linearLayoutManager);
        return inflate;
    }

    @Override
    public void success(AttentCinemaBean attentCinemaBean) {
        List<AttentCinemaBean.ResultBean> result = attentCinemaBean.result;
        AttentCinemaAdapter attentCinemaAdapter = new AttentCinemaAdapter(result, getActivity());
        attentcinemaRecy.setAdapter(attentCinemaAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
