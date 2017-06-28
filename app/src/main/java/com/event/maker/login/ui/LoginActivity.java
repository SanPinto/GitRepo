package com.event.maker.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.event.maker.R;
import com.event.maker.login.LoginContract;
import com.event.maker.login.event.EventActivity;
import com.event.maker.login.presenter.LoginPresenterImpl;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends FragmentActivity implements LoginContract.LoginView, View.OnClickListener {

    private LoginPresenterImpl mPresenter;
    private Button mLoginButton;
    private EditText mEmailEditText, mPasswordEditText;
    private Button mRegisterBtn, mSignInBtn, mResetPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mPresenter = new LoginPresenterImpl();
        mPresenter.attachView(this);
        mPresenter.onCreate();
        mPresenter.attachActivity(this);
        initViews();
        mPresenter.handleLogin();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPresenter != null) {
            mPresenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initViews() {
        mLoginButton = (Button) findViewById(R.id.fb_login_button);
        mEmailEditText = (EditText) findViewById(R.id.email);
        mPasswordEditText = (EditText) findViewById(R.id.password);
        mRegisterBtn = (Button) findViewById(R.id.sign_up_button);
        mSignInBtn = (Button) findViewById(R.id.sign_in_button);
        mResetPwd = (Button) findViewById(R.id.reset_password);
    }

    @Override
    public void onLoggedIn(FirebaseUser user) {
        Toast.makeText(this, "You succefully Logged into Facebook", Toast.LENGTH_SHORT).show();
        if (mLoginButton != null) {
            mLoginButton.setText(getResources().getString(R.string.logout));
        }
        goToBookingScreen();
    }

    @Override
    public void onCancelled() {
        Toast.makeText(this, "Your Facebook Login Cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Exception error) {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoggedOut() {
        if (mLoginButton != null) {
            mLoginButton.setText(getResources().getString(R.string.login));
        }
    }

    @Override
    public void goToBookingScreen() {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initLoginScreen() {
        setClickListeners();
    }

    @Override
    public String getUserName() {
        return mEmailEditText.getText().toString();
    }

    @Override
    public void showUserNameEmptyError(int resId) {
        mEmailEditText.setError(getString(resId));
    }

    @Override
    public void showInvalidUserNameError(int resId) {
        mEmailEditText.setError(getString(resId));
    }

    @Override
    public String getPassWord() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    public void showPaswordEmptyError(int resId) {
        mPasswordEditText.setError(getString(resId));
    }

    @Override
    public void showInvalidPasswordError(int resId) {
        mPasswordEditText.setError(getString(resId));
    }

    private void setClickListeners() {
        mLoginButton.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mResetPwd.setOnClickListener(this);
        mSignInBtn.setOnClickListener(this);
    }

    private void setLoginButton() {
        if (mLoginButton != null && mPresenter != null) {
            if (mPresenter.isLoggedIn()) {
                mLoginButton.setText(getResources().getString(R.string.go_to_home));
            } else {
                mLoginButton.setText(getResources().getString(R.string.login));
            }
        }
    }


    private void loginUsingFB() {
        if (mPresenter != null) {
            mPresenter.handleClick(this);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_button:
                signUp();
                break;
            case R.id.sign_in_button:
                mPresenter.signIn();
                break;
            case R.id.reset_password:
                break;
            case R.id.fb_login_button:
                loginUsingFB();
                break;
        }

    }

    private void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
