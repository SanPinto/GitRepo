package com.event.maker.login.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.event.maker.login.LoginContract;
import com.event.maker.login.helper.FacebookHelper;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by sangeetha on 6/6/17.
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter, FacebookHelper.OnFaceBookSignedInListener, FirebaseAuth.AuthStateListener {
    private static String TAG = "LoginPresenter";
    private FirebaseAuth mFirebaseAuth;
    private LoginContract.LoginView mView;
    private Activity mActivity;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    public void attachView(LoginContract.LoginView view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        FacebookHelper.getInstance().initFbSdk(this);
        //Initialise Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
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
    public boolean isLoggedIn() {
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public void login(Activity activity) {
        FacebookHelper.getInstance().login(activity);
    }


    @Override
    public void handleClick(Activity activity) {
        if (isLoggedIn()) {
            if (mView != null) {
                mView.goToBookingScreen();
            }
        } else {
            login(activity);
        }
    }

    @Override
    public void attachActivity(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onStart() {
        mFirebaseAuth.addAuthStateListener(this);
    }

    @Override
    public void onStop() {
        mFirebaseAuth.removeAuthStateListener(this);
    }


    @Override
    public void onLoginSuccess(LoginResult loginResult) {
        handleFacebookAccessToken(loginResult);
    }

    private void handleFacebookAccessToken(LoginResult loginResult) {
        if (loginResult != null) {
            AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
            mFirebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                if (mView != null) {
                                    mView.onLoggedIn(user);
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                if (mView != null) {
                                    mView.onError(task.getException());
                                }
                            }
                        }
                    });
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

    @Override
    public void onSignedOut() {
        if (mView != null) {
            mView.onLoggedOut();
        }
    }

    private void logout() {
        FacebookHelper.getInstance().logout();
    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
    }

}
