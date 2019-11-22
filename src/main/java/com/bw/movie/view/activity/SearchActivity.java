package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.presenter.SearchPresenter;
import com.bw.movie.view.adapter.SearchAdapter;
import com.bw.movie.view.interfaces.IMainView;
import com.bw.movie.view.movies.DetailsMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements IMainView.ISearch {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.seach_view)
    EditText seachView;
    @BindView(R.id.search_recycler)
    RecyclerView searchRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final SearchPresenter searchPresenter = new SearchPresenter();
        searchPresenter.attach(this);
        String trim = seachView.getText().toString().trim();
        searchPresenter.getDate(trim);
        seachView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim1 = seachView.getText().toString().trim();
                searchPresenter.getDate(trim1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void search(SearchBean searchBean) {
        final List<SearchBean.ResultBean> result = searchBean.result;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecycler.setLayoutManager(linearLayoutManager);
        SearchAdapter searchAdapter = new SearchAdapter(result, this);
        searchRecycler.setAdapter(searchAdapter);
        searchAdapter.setSetOnItemCilck(new SearchAdapter.setOnItemCilck() {
            @Override
            public void onItemClick(int position) {
                int movieId = result.get(position).movieId;
                Intent intent = new Intent(SearchActivity.this, DetailsMovie.class);

                startActivity(intent);
            }
        });
    }
}
