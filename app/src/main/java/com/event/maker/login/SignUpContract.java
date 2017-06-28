package com.event.maker.login;

import android.support.v4.app.FragmentActivity;

import com.event.maker.login.ui.SignUpActivity;

/**
 * Created by sangeetha on 28/6/17.
 */

public class SignUpContract {

    public interface SignUpPresenter {

        void attachView(SignUpView view);

        void onCreate();

        void handleLogin();

        void signUp();

        void attachActivity(FragmentActivity activity);
    }

    public interface SignUpView {
        void goToBookingScreen();

        void initLoginScreen();

        String getUserName();

        String getPassWord();

        void showPaswordEmptyError(int resId);

        void showInvalidPasswordError(int resId);

        void showUserNameEmptyError(int resId);

        void showInvalidUserNameError(int resId);

        void onSignUpFailed();

        void onUserSignedIn();
    }
}
