package com.bike.rent.bikerental.login.presenter;

import android.content.Intent;

import com.bike.rent.bikerental.login.LoginContract;
import com.bike.rent.bikerental.login.helper.FacebookHelper;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

/**
 * Created by sangeetha on 6/6/17.
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter, FacebookHelper.OnFaceBookSignedInListener {
    private LoginContract.LoginView mView;

    public LoginPresenterImpl() {
        FacebookHelper.getInstance().initFbSdk(this);
    }

    @Override
    public void attachView(LoginContract.LoginView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            FacebookHelper.getInstance().onActivityResult(requestCode, resultCode, data);
        }



    @Override
    public void onLoginSuccess(LoginResult loginResult) {
        if (mView != null) {
            mView.onLoggedIn(loginResult);
        }
    }

    @Override
    public void onLoginCancelled() {
        if (mView != null) {
            mView.onCancelled();
        }

    }

    @Override
    public void onLoginError(FacebookException error) {
        if (mView != null) {
            mView.onError(error);
        }
    }
}
