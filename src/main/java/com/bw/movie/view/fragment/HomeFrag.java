package com.bw.movie.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.homefrag.Homefag;
import com.bw.movie.homefrag.MyAdapter;
import com.bw.movie.homefrag.Otherfag;
import com.bw.movie.homefrag.Thridfag;
import com.bw.movie.model.bean.SearchCinemaBean;
import com.bw.movie.presenter.SearchCinemaPresenter;
import com.bw.movie.view.interfaces.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： 姓名
 * 日期： 2019/10/10 16:42
 */
public class HomeFrag extends Fragment implements IMainView.SearchCinemaView {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.moview_view)
    ViewPager moviewView;
    Unbinder unbinder;
    @BindView(R.id.textdw)
    TextView textdw;
    @BindView(R.id.seach_cinema)
    EditText seachCinema;
    private SearchCinemaPresenter searchCinemaPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.homefrg, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        List<String> list = new ArrayList<>();
        list.add("推荐影院");
        list.add("附近影院");
        list.add("海淀区▼");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Homefag());
        fragmentList.add(new Otherfag());
        fragmentList.add(new Thridfag());
        MyAdapter myAdapter = new MyAdapter(getFragmentManager(), list, fragmentList);
        moviewView.setAdapter(myAdapter);
        tab.setupWithViewPager(moviewView);
        String trim = seachCinema.getText().toString().trim();
        searchCinemaPresenter = new SearchCinemaPresenter();
        searchCinemaPresenter.attach(this);
        searchCinemaPresenter.getDate(trim);

        seachCinema.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = seachCinema.getText().toString().trim();
                searchCinemaPresenter.getDate(trim);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return inflate;
    }

    @Override
    public void searchCinema(SearchCinemaBean searchCinemaBean) {
        if (searchCinemaBean.status.equals("0000")){
            Toast.makeText(getContext(), ""+searchCinemaBean.message, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), ""+searchCinemaBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
