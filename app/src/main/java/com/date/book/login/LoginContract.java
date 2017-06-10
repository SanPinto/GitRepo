package com.date.book.login;

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



    public interface LoginView {
        void onLoggedIn(LoginResult loginResult);

        void onCancelled();

        void onError(FacebookException error);
    }

}
