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
import com.bw.movie.model.bean.ShangMovieBean;
import com.bw.movie.presenter.HeatPreseneter;
import com.bw.movie.view.interfaces.IMoreView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 16:48
 */
public class HeatFragment extends Fragment implements IMoreView.HeatView {//正在热映
    @BindView(R.id.hear_recy)
    RecyclerView hearRecy;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.heat_frag, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        HeatPreseneter heatPreseneter = new HeatPreseneter();
        heatPreseneter.attach(this);
        heatPreseneter.getDate();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hearRecy.setLayoutManager(linearLayoutManager);
        return inflate;
    }

    @Override
    public void suc(ShangMovieBean shangMovieBean) {
        List<ShangMovieBean.ResultBean> result = shangMovieBean.result;
        HeatAdapter heatAdapter=new HeatAdapter(result,getActivity());
        hearRecy.setAdapter(heatAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
