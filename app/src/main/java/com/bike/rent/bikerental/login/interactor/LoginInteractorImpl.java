package com.bike.rent.bikerental.login.interactor;

import android.content.Intent;

import com.bike.rent.bikerental.login.LoginContract;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

/**
 * Created by sangeetha on 6/6/17.
 */

public class LoginInteractorImpl implements LoginContract.LoginInteractor {
    private CallbackManager mCallbackManager;
    private LoginContract.RequiredPresenterOps mPresenter;

    public LoginInteractorImpl(LoginContract.RequiredPresenterOps requiredPresenterOps) {
        mPresenter = requiredPresenterOps;
    }

    @Override
    public void initFbSdk() {
        if (mCallbackManager == null) {
            mCallbackManager = CallbackManager.Factory.create();
            registerCallBackManager();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void registerCallBackManager() {
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (mPresenter != null) {
                    mPresenter.onLoginSuccess(loginResult);
                }
            }

            @Override
            public void onCancel() {
                if (mPresenter != null) {
                    mPresenter.onLoginCancelled();
                }
            }

            @Override
            public void onError(FacebookException error) {
                if (mPresenter != null) {
                    mPresenter.onLoginError(error);
                }
            }
        });
    }
}
