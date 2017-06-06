package com.bike.rent.bikerental.login.presenter;

import android.content.Intent;

import com.bike.rent.bikerental.login.LoginContract;
import com.bike.rent.bikerental.login.interactor.LoginInteractorImpl;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

/**
 * Created by sangeetha on 6/6/17.
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter, LoginContract.RequiredPresenterOps {
    private LoginContract.LoginView mView;
    private LoginInteractorImpl mInteractor;

    public LoginPresenterImpl() {
        mInteractor = new LoginInteractorImpl(this);
        mInteractor.initFbSdk();
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
        if (mInteractor != null) {
            mInteractor.onActivityResult(requestCode, resultCode, data);
        }
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
