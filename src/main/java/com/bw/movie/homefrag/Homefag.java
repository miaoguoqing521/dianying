package com.bw.movie.homefrag;



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
import com.bw.movie.model.bean.RecommendedCinemaBean;
import com.bw.movie.presenter.RecommendPresenter;
import com.bw.movie.view.interfaces.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.security.AccessController.getContext;

/**
 * 作者： 姓名
 * 日期： 2019/10/11 15:51
 */
public class Homefag extends Fragment implements IMainView.IRecommend {
    @BindView(R.id.home_recy)
    RecyclerView homeRecy;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.homefag, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecy.setLayoutManager(linearLayoutManager);
        RecommendPresenter recommendPresenter = new RecommendPresenter();
        recommendPresenter.attach(this);
        recommendPresenter.getDate();
        return inflate;
    }
    @Override
    public void reco(RecommendedCinemaBean recommendedCinemaBean) {
        if (recommendedCinemaBean!=null){
            List<RecommendedCinemaBean.ResultBean> result = recommendedCinemaBean.result;
            RecommendAdapter recommendAdapter = new RecommendAdapter(result,getActivity());
            homeRecy.setAdapter(recommendAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
