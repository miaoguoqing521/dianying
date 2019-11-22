package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.utils.EncryptUtil;
import com.bw.movie.model.bean.ReginBean;
import com.bw.movie.model.bean.SendEmail;
import com.bw.movie.presenter.ReginPresenter;
import com.bw.movie.view.interfaces.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity implements IMainView.IReginView {

    @BindView(R.id.name_regin)
    EditText nameRegin;
    @BindView(R.id.email_regin)
    EditText emailRegin;
    @BindView(R.id.set_regin)
    EditText setRegin;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.regin_togather)
    TextView reginTogather;
    @BindView(R.id.regin_wjpossword)
    LinearLayout reginWjpossword;
    @BindView(R.id.regin_registered)
    TextView reginRegistered;
    @BindView(R.id.but_regin)
    LinearLayout butRegin;
    private int anInt = 120;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 11) {
                reginTogather.setText("重新获取(" + anInt + "" + "s)");
                anInt--;
                if (anInt >= 0) {
                    handler.sendEmptyMessageDelayed(11, 1000);
                } else {
                    anInt = 120;
                    verification.setText("获取验证码");
                }
            }
        }
    };
    private ReginPresenter reginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        reginPresenter = new ReginPresenter();
        reginPresenter.attach(this);
    }

    @Override
    public void send(SendEmail sendEmail) {
        Toast.makeText(this, "" + sendEmail.message, Toast.LENGTH_SHORT).show();
        if (sendEmail.status.equals("0000")) {
            Toast.makeText(this, "" + sendEmail.message, Toast.LENGTH_SHORT).show();
        }
        handler.sendEmptyMessageDelayed(11, 1000);
    }

    @Override
    public void regin(ReginBean reginBean) {
        if (reginBean.status.equals("0000")) {
            Toast.makeText(this, "" + reginBean.message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
            startActivity(intent);
        } else if (reginBean.status.equals("1001")) {
            Toast.makeText(this, "" + reginBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.regin_wjpossword, R.id.regin_registered, R.id.but_regin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regin_wjpossword:
                if (anInt==120){
                    String trim = emailRegin.getText().toString().trim();
                    reginPresenter.getDate(trim);
                }
                break;
            case R.id.regin_registered:
                Intent intents = new Intent(Main2Activity.this,LoginActivity.class);
                startActivity(intents);
                break;
            case R.id.but_regin:
                String name = nameRegin.getText().toString().trim();
                String encrypt = EncryptUtil.encrypt(setRegin.getText().toString().trim());
                String s = emailRegin.getText().toString().trim();
                String s1 = verification.getText().toString().trim();
                reginPresenter.getDates(name,encrypt,s,s1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(11);
    }
}
