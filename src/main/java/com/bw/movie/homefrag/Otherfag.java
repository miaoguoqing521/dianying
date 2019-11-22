package com.bw.movie.homefrag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.model.bean.NearCinema;
import com.bw.movie.presenter.NearPresenter;
import com.bw.movie.view.interfaces.IMainView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/11 15:51
 */
public class Otherfag extends Fragment implements IMainView.INearView {
    @BindView(R.id.near_xrecy)
    XRecyclerView nearRecy;
    Unbinder unbinder;
    private int page=1;
    private NearPresenter nearPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.nearcinema, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nearRecy.setLayoutManager(linearLayoutManager);
        nearPresenter = new NearPresenter();
        nearPresenter.attach(this);
        nearPresenter.getDate(page);
        return inflate;
    }

    @Override
    public void nearcinema(NearCinema nearCinema) {
        if (nearCinema!=null){
            final List<NearCinema.ResultBean> result = nearCinema.result;
            nearRecy.setPullRefreshEnabled(true);
            nearRecy.setLoadingMoreEnabled(true);
            nearRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    page=1;
                    nearPresenter.getDate(page);
                    nearRecy.refreshComplete();

                }

                @Override
                public void onLoadMore() {
                    page++;
                    nearPresenter.getDate(page);
                    nearRecy.loadMoreComplete();
                }
            });

            NearAdapter nearAdapter = new NearAdapter(result,getActivity());
            nearRecy.setAdapter(nearAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
