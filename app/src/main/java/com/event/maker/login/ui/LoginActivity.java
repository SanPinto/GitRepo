package com.event.maker.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.event.maker.R;
import com.event.maker.login.LoginContract;
import com.event.maker.login.event.EventActivity;
import com.event.maker.login.presenter.LoginPresenterImpl;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    private LoginPresenterImpl mPresenter;
    private Button mLoginButton;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenterImpl();
        mPresenter.attachView(this);
        mPresenter.onCreate();
        mPresenter.attachActivity(this);
        initViews();
        setLoginButton();
        initClickListner();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPresenter != null) {
            mPresenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initViews() {
        mLoginButton = (Button) findViewById(R.id.fb_login_button);
    }

    @Override
    public void onLoggedIn(FirebaseUser user) {
        Toast.makeText(this, "You succefully Logged into Facebook", Toast.LENGTH_SHORT).show();
        if (mLoginButton != null) {
            mLoginButton.setText(getResources().getString(R.string.logout));
        }
        goToBookingScreen();
    }

    @Override
    public void onCancelled() {
        Toast.makeText(this, "Your Facebook Login Cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Exception error) {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoggedOut() {
        if (mLoginButton != null) {
            mLoginButton.setText(getResources().getString(R.string.login));
        }
    }

    @Override
    public void goToBookingScreen() {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
        finish();
    }

    private void setLoginButton() {
        if (mLoginButton != null && mPresenter != null) {
            if (mPresenter.isLoggedIn()) {
                mLoginButton.setText(getResources().getString(R.string.go_to_home));
            } else {
                mLoginButton.setText(getResources().getString(R.string.login));
            }
        }
    }

    private void initClickListner() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClick();
            }
        });
    }

    private void handleClick() {
        if (mPresenter != null) {
            mPresenter.handleClick(this);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }
}
