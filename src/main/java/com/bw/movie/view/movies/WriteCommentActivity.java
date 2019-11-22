package com.bw.movie.view.movies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.AddCommentBean;
import com.bw.movie.model.bean.QueryMovieBean;
import com.bw.movie.presenter.AddCommentPresenter;
import com.bw.movie.view.interfaces.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteCommentActivity extends AppCompatActivity implements IMainView.AddComentView {

    @BindView(R.id.write_back)
    ImageView writeBack;
    @BindView(R.id.write_name)
    TextView writeName;
    @BindView(R.id.write_rating)
    RatingBar writeRating;
    @BindView(R.id.write_text)
    EditText writeText;
    @BindView(R.id.write_btn)
    Button writeBtn;
    private AddCommentPresenter addCommentPresenter;
    private String userId;
    private String sessionId;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        ButterKnife.bind(this);
        addCommentPresenter = new AddCommentPresenter();
        SharedPreferences isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
        userId = isLogin.getString("userId", "");
        sessionId = isLogin.getString("sessionId", "");
        SharedPreferences sp = getSharedPreferences("isP", MODE_PRIVATE);
        movieId = sp.getInt("movieId", 0);

        writeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addCommentPresenter.attach(this);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = writeText.getText().toString().trim();
                double rating = writeRating.getRating();
                addCommentPresenter.getDate(userId, sessionId, movieId,trim,rating);
                finish();
            }
        });

    }

    @Override
    public void addComent(AddCommentBean addCommentBean) {

            Toast.makeText(this, ""+addCommentBean.message, Toast.LENGTH_SHORT).show();

    }
}
