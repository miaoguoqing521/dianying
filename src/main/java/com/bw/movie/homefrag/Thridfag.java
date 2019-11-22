package com.bw.movie.homefrag;

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
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.QueryRegionBean;
import com.bw.movie.model.bean.RegionCinemaBean;
import com.bw.movie.presenter.RegionCinemaPresenter;
import com.bw.movie.view.cinema.CinemaDetails;
import com.bw.movie.view.interfaces.IMainView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/11 15:52
 */
public class Thridfag extends Fragment implements IMainView.IRegionCinema {
    @BindView(R.id.thrid_recy1)
    RecyclerView thridRecy1;
    @BindView(R.id.thrid_recy2)
    RecyclerView thridRecy2;
    Unbinder unbinder;
    private RegionCinemaPresenter regionCinemaPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.thrid_activity, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        regionCinemaPresenter = new RegionCinemaPresenter();
        regionCinemaPresenter.attach(this);
        regionCinemaPresenter.getDatee();
        return inflate;
    }


    @Override
    public void region(QueryRegionBean queryRegionBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        thridRecy1.setLayoutManager(linearLayoutManager);
        final List<QueryRegionBean.ResultBean> result = queryRegionBean.result;
        ThridAdapter thridAdapter = new ThridAdapter(result, getActivity());
        thridAdapter.setSetOnItemCilck(new ThridAdapter.setOnItemCilck() {
            @Override
            public void onItemClick(int position) {
                int regionId = result.get(position).regionId;
                regionCinemaPresenter.getDate(regionId);
            }
        });
        thridRecy1.setAdapter(thridAdapter);
    }

    @Override
    public void regioncinema(RegionCinemaBean regionCinemaBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        thridRecy2.setLayoutManager(linearLayoutManager);
        if (regionCinemaBean!=null){
            final List<RegionCinemaBean.ResultBean> result1 = regionCinemaBean.result;
            ThridChildAdapter thridChildAdapter = new ThridChildAdapter(result1, getActivity());
            thridChildAdapter.setChildItemClick(new ThridChildAdapter.childItemClick() {
                @Override
                public void childItem(int position) {
                    int chinemaId = result1.get(position).id;
                    SharedPreferences isL = getActivity().getSharedPreferences("isL", Context.MODE_PRIVATE);
                    isL.edit().putInt("cinemaId",chinemaId).commit();
                    EventBus.getDefault().postSticky(result1);
                    startActivity(new Intent(getActivity(), CinemaDetails.class));
                }
            });
            thridRecy2.setAdapter(thridChildAdapter);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
