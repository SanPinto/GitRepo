package com.date.book.login.helper;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

/**
 * Created by sangeetha on 7/6/17.
 */

public class FacebookHelper {

    private static FacebookHelper sInstance;
    private CallbackManager mCallbackManager;
    private OnFaceBookSignedInListener mFBListner;

    private FacebookHelper() {

    }

    public synchronized static FacebookHelper getInstance() {
        if (sInstance == null) {
            sInstance = new FacebookHelper();
        }
        return sInstance;
    }


    public interface OnFaceBookSignedInListener {

        void onLoginSuccess(LoginResult loginResult);

        void onLoginCancelled();

        void onLoginError(FacebookException error);
    }


    public void initFbSdk(OnFaceBookSignedInListener listener) {
        if (mCallbackManager == null) {
            mCallbackManager = CallbackManager.Factory.create();
            mFBListner = listener;
            registerCallBackManager();
        }
    }

    private void registerCallBackManager() {
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (mFBListner != null) {
                    mFBListner.onLoginSuccess(loginResult);
                }
            }

            @Override
            public void onCancel() {
                if (mFBListner != null) {
                    mFBListner.onLoginCancelled();
                }
            }

            @Override
            public void onError(FacebookException error) {
                if (mFBListner != null) {
                    mFBListner.onLoginError(error);
                }
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
