package com.bw.movie.view.cinema.scheduling;

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

import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaSchedulingBean;
import com.bw.movie.presenter.ScheduleTingPresenter;
import com.bw.movie.view.interfaces.IMainView;
import com.bw.movie.view.movies.BuyMovieActivity;
import com.bw.movie.view.movies.RoomActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 21:10
 */
public class ScheduleTabFragment extends Fragment implements IMainView.SchedulingView {

    Unbinder unbinder;
    @BindView(R.id.schedu_xrecy)
    RecyclerView scheduXrecy;
    private ScheduleTingPresenter scheduleTingPresenter;
    private int cinemaId;
    private SchedulingAdapter schedulingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.scheduletabfragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        Bundle bundle = getArguments();
        cinemaId = bundle.getInt("did");
        scheduleTingPresenter = new ScheduleTingPresenter();
        scheduleTingPresenter.attach(this);
        scheduleTingPresenter.getDate(cinemaId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        scheduXrecy.setLayoutManager(linearLayoutManager);
        return inflate;
    }


    @Override
    public void scheduling(CinemaSchedulingBean cinemaSchedulingBean) {
        final List<CinemaSchedulingBean.ResultBean> result = cinemaSchedulingBean.result;
       if (result!=null){
           schedulingAdapter = new SchedulingAdapter(result, getActivity());
           scheduXrecy.setAdapter(schedulingAdapter);
           schedulingAdapter.setScheduClick(new SchedulingAdapter.ScheduClick() {
               @Override
               public void scheClick(int position) {
                   Intent proom = new Intent(getActivity(), RoomActivity.class);
                   EventBus.getDefault().postSticky(result);
                   int movieId = result.get(position).movieId;
                   SharedPreferences isP = getActivity().getSharedPreferences("isP", Context.MODE_PRIVATE);
                   SharedPreferences isJ = getActivity().getSharedPreferences("isJ", Context.MODE_PRIVATE);
                   SharedPreferences isV = getActivity().getSharedPreferences("isV", Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor1 = isV.edit().putString("videos", result.get(position).trailerUrl);
                   SharedPreferences.Editor editor = isJ.edit().putString("name", result.get(position).name);
                   editor1.commit();
                   editor.commit();
                   proom.putExtra("cinemaId", cinemaId);
                   isP.getInt("movieId", movieId);
                   startActivity(proom);
               }
           });
       }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
