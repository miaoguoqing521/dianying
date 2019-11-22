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
import com.bw.movie.model.bean.JiMoviewBean;
import com.bw.movie.presenter.BePresenter;
import com.bw.movie.view.interfaces.IMoreView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 16:48
 */

public class BeFragment extends Fragment implements IMoreView.BeView {//即将上映
    @BindView(R.id.be_recy)
    RecyclerView beRecy;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.be_frag, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        BePresenter bePresenter = new BePresenter();
        bePresenter.attach(this);
        bePresenter.getDate();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        beRecy.setLayoutManager(linearLayoutManager);
        return inflate;
    }

    @Override
    public void ji(JiMoviewBean jiMoviewBean) {
        List<JiMoviewBean.ResultBean> result = jiMoviewBean.result;
        BeAdapter beAdapter = new BeAdapter(result, getActivity());
        beRecy.setAdapter(beAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
