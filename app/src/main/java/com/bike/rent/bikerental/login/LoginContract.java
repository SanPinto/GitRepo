package com.bike.rent.bikerental.login;

import android.content.Intent;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

/**
 * Created by sangeetha on 6/6/17.
 */

public class LoginContract {

    public interface LoginPresenter {

        void attachView(LoginView view);

        void detachView();

        void onActivityResult(int requestCode, int resultCode, Intent data);


    }

    public interface RequiredPresenterOps {

        void onLoginSuccess(LoginResult loginResult);

        void onLoginCancelled();

        void onLoginError(FacebookException error);


    }


    public interface LoginView {
        void onLoggedIn(LoginResult loginResult);

        void onCancelled();

        void onError(FacebookException error);
    }

    public interface LoginInteractor {
        void initFbSdk();

        void onActivityResult(int requestCode, int resultCode, Intent data);

    }
}
