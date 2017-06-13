package com.date.book.login;

import android.app.Activity;
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

        boolean isLoggedIn();

        void login(Activity activity);

        void handleClick(Activity activity);

        ;


    }


    public interface LoginView {
        void onLoggedIn(LoginResult loginResult);

        void onCancelled();

        void onError(FacebookException error);

        void onLoggedOut();

        void goToBookingScreen();
    }

}
