package com.event.maker.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.event.maker.R;
import com.event.maker.login.SignUpContract;
import com.event.maker.login.event.EventActivity;
import com.event.maker.login.presenter.SignUpPresenterImpl;

/**
 * Created by sangeetha on 28/6/17.
 */

public class SignUpActivity extends FragmentActivity implements SignUpContract.SignUpView, View.OnClickListener {

    private SignUpPresenterImpl mPresenter;
    private EditText mEmailEditText, mPasswordEditText;
    private Button mRegisterBtn, mSignInBtn;
    private ProgressBar mLoader;
    private LinearLayout mContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);
        mPresenter = new SignUpPresenterImpl();
        mPresenter.attachView(this);
        mPresenter.onCreate();
        mPresenter.attachActivity(this);
        initViews();
        mPresenter.handleLogin();
    }


    private void initViews() {
        mEmailEditText = (EditText) findViewById(R.id.email);
        mPasswordEditText = (EditText) findViewById(R.id.password);
        mRegisterBtn = (Button) findViewById(R.id.sign_up_button);
        mSignInBtn = (Button) findViewById(R.id.sign_in_button);
        mLoader = (ProgressBar) findViewById(R.id.progress_bar);
        mContainer = (LinearLayout) findViewById(R.id.container);
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
    public void onSignUpFailed() {
        Snackbar.make(mContainer, getString(R.string.registration_failed), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUserSignedIn() {
        goToBookingScreen();
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
        mRegisterBtn.setOnClickListener(this);
        mSignInBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_button:
                signUp();
                break;
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.reset_password:
                resetPassword();
                break;
        }

    }

    private void resetPassword() {

    }

    private void signIn() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUp() {
        showLoader();
        mPresenter.signUp();
    }


    private void showLoader() {
        mLoader.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        mLoader.setVisibility(View.GONE);
    }
}
