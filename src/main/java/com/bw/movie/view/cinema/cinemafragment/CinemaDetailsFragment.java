package com.bw.movie.view.cinema.cinemafragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.QueryCinemaDetails;
import com.bw.movie.presenter.CinemaDetailsPresenter;
import com.bw.movie.view.interfaces.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 15:06
 */
public class CinemaDetailsFragment extends Fragment implements IMainView.DetaulsView{
    @BindView(R.id.aaa)
    ImageView aaa;
    @BindView(R.id.frug_loction)
    TextView frugLoction;
    @BindView(R.id.frug_phone)
    TextView frugPhone;
    @BindView(R.id.frug_metro)
    TextView frugMetro;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.cinemadetails_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        SharedPreferences isLogin = getActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String userId = isLogin.getString("userId", "");
        String sessionId = isLogin.getString("sessionId", "");
        SharedPreferences isL = getActivity().getSharedPreferences("isL", Context.MODE_PRIVATE);
        int cinemaId = isL.getInt("cinemaId", 0);
        CinemaDetailsPresenter cinemaDetailsPresenter = new CinemaDetailsPresenter();
        cinemaDetailsPresenter.attach(this);
        cinemaDetailsPresenter.getDate(userId, sessionId, cinemaId);
        return inflate;
    }
    @Override
    public void details(QueryCinemaDetails queryCinemaDetails) {
        final QueryCinemaDetails.ResultBean result = queryCinemaDetails.result;
        aaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frugLoction.setText(result.address);
            }
        });
        frugPhone.setText(result.phone);
        frugMetro.setText(result.vehicleRoute);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
