package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.okhttp.App;
import com.bw.movie.model.utils.EncryptUtil;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.utils.SendAuth;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.view.interfaces.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IMainView.ILoginView {

    @BindView(R.id.login_account)
    EditText loginAccount;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_wjpossword)
    LinearLayout loginWjpossword;
    @BindView(R.id.tz_registered)
    TextView tzRegistered;
    @BindView(R.id.but_login)
    LinearLayout butLogin;
    @BindView(R.id.view1_longn)
    View view1Longn;
    @BindView(R.id.view2_longn)
    View view2Longn;
    @BindView(R.id.but_wechat)
    LinearLayout butWechat;
    @BindView(R.id.tv_togather)
    TextView tvTogather;
    private SharedPreferences isLoogin;
    private SharedPreferences.Editor edit;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        isLoogin = getSharedPreferences("isLoogin", MODE_PRIVATE);
        String loginAccounts = isLoogin.getString("loginAccount", "");
        loginAccount.setText(loginAccounts);
        loginPresenter = new LoginPresenter();
        loginPresenter.attach(this);
    }

    @OnClick({R.id.tv_togather, R.id.tz_registered, R.id.but_login, R.id.but_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_togather:
                break;
            case R.id.tz_registered:
                Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.but_login:
                String email = loginAccount.getText().toString().trim();
                String pwd = loginPassword.getText().toString().trim();
                // TODO: 2019/10/10 加密密码
                String encrypt = EncryptUtil.encrypt(pwd);
                edit = isLoogin.edit();
                if (TextUtils.isEmpty(email)&&TextUtils.isEmpty(encrypt)){
                    Toast.makeText(this, "请输入邮箱或密码", Toast.LENGTH_SHORT).show();
                }else {
                    edit.putString("loginAccount",email)
                         .putString("loginPassword",encrypt);
                    edit.commit();

                    loginPresenter.getDate(email,encrypt);
                }
                break;
            case R.id.but_wechat:
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_login";
                App.api.sendReq(req);
                break;
        }
    }

    @Override
    public void success(LoginBean loginBean) {
        if (loginBean.status.equals("0000")){
            Toast.makeText(this, ""+loginBean.message, Toast.LENGTH_SHORT).show();
            LoginBean.ResultBean result = loginBean.result;
            int userId = result.userId;
            String sessionId = result.sessionId;
            String nickName = result.userInfo.nickName;
            String headPic = result.userInfo.headPic;
            SharedPreferences isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
            SharedPreferences.Editor edit = isLogin.edit();
            edit.putString("userId",String.valueOf(userId));
            edit.putString("sessionId",sessionId);
            edit.putString("nickName",nickName);
            edit.putString("headPic",headPic);
            edit.commit();
            Intent intents = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intents);
        }else {
            Toast.makeText(this, ""+loginBean.message, Toast.LENGTH_SHORT).show();
        }
    }
}
