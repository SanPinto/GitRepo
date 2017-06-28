package com.event.maker.login.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.event.maker.R;
import com.event.maker.login.SignUpContract;
import com.event.maker.login.utils.ApplicationUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by sangeetha on 28/6/17.
 */

public class SignUpPresenterImpl implements SignUpContract.SignUpPresenter {
    private SignUpContract.SignUpView mView;
    private FirebaseAuth mFirebaseAuth;
    private static String TAG = SignUpPresenterImpl.class.getName();
    private FragmentActivity mActivity;

    @Override
    public void attachView(SignUpContract.SignUpView view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        //Initialise Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void handleLogin() {
        if (isLoggedIn()) {
            if (mView != null) {
                mView.goToBookingScreen();
            }
        } else {
            if (mView != null) {
                mView.initLoginScreen();
            }
        }
    }

    @Override
    public void signUp() {
        String username = mView.getUserName();
        String password = mView.getPassWord();
        if (isValidUserName(username) && isValidPassWord(password)) {
            createAccount(username, password);
        }
    }

    @Override
    public void attachActivity(FragmentActivity activity) {
        mActivity = activity;
    }

    private void createAccount(String username, String password) {
        mFirebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            mView.onSignUpFailed();
                        } else {
                            mView.onUserSignedIn();
                        }

                    }
                });
    }


    private boolean isLoggedIn() {
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            return true;
        }
        return false;
    }


    private boolean isValidPassWord(String password) {
        if (ApplicationUtils.isEmpty(password)) {
            mView.showPaswordEmptyError(R.string.password_empty);
            return false;
        } else if (!ApplicationUtils.isValidPassword(password)) {
            mView.showInvalidPasswordError(R.string.invalid_password);
            return false;
        }
        return true;
    }


    private boolean isValidUserName(String userName) {
        if (ApplicationUtils.isEmpty(userName)) {
            mView.showUserNameEmptyError(R.string.username_empty);
            return false;
        } else if (!ApplicationUtils.isValidEmail(userName)) {
            mView.showInvalidUserNameError(R.string.invalid_username);
            return false;
        }
        return true;
    }

}
