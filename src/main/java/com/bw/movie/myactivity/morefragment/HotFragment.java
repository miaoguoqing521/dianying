package com.bw.movie.myactivity.morefragment;

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
import com.bw.movie.model.bean.ReMovieBean;
import com.bw.movie.presenter.HotPresenter;
import com.bw.movie.view.interfaces.IMoreView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 16:47
 */
public class HotFragment extends Fragment implements IMoreView.HotView {
    @BindView(R.id.hot_recy)
    RecyclerView hotRecy;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.hot_frag, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hotRecy.setLayoutManager(linearLayoutManager);
        HotPresenter hotPresenter = new HotPresenter();
        hotPresenter.attach(this);
        hotPresenter.getDate();
        return inflate;
    }

    @Override
    public void re(ReMovieBean reMovieBean) {
        List<ReMovieBean.ResultBean> result = reMovieBean.result;
        HotAdapter hotAdapter=new HotAdapter(result,getActivity());
        hotRecy.setAdapter(hotAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
