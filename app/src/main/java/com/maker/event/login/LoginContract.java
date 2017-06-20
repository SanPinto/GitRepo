package com.maker.event.login;

import android.app.Activity;
import android.content.Intent;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by sangeetha on 6/6/17.
 */

public class LoginContract {

    public interface LoginPresenter {

        void attachView(LoginView view);

        void onCreate();

        void detachView();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        boolean isLoggedIn();

        void login(Activity activity);

        void handleClick(Activity activity);

        void attachActivity(Activity activity);

        void onStart();

        void onStop();


    }


    public interface LoginView {
        void onLoggedIn(FirebaseUser user);

        void onCancelled();

        void onError(Exception error);

        void onLoggedOut();

        void goToBookingScreen();
    }

}
