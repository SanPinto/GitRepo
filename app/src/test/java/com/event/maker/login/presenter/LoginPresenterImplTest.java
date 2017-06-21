package com.event.maker.login.presenter;

import android.text.TextUtils;

import com.event.maker.R;
import com.event.maker.login.LoginContract;
import com.event.maker.login.utils.ApplicationUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sangeetha on 21/6/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class, ApplicationUtils.class})

public class LoginPresenterImplTest {

    private LoginPresenterImpl mPresenter;
    @Mock
    private LoginContract.LoginView mView;


    @Before
    public void setUp() throws Exception {
        mPresenter = new LoginPresenterImpl();
        mPresenter.attachView(mView);
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.mockStatic(ApplicationUtils.class);

    }

    @Test
    public void shouldShowErrorMsgWhenUserNameIsEmpty() throws Exception {
        when(mView.getUserName()).thenReturn("");
        mPresenter.signUp();
        verify(mView).showUserNameError(R.string.username_empty);
    }


    @Test
    public void shouldShowInvalidusernameWhenEmailIsInvalid() throws Exception {
        when(mView.getUserName()).thenReturn("hello");
        mPresenter.signUp();
        verify(mView).showInvalidUserName(R.string.invalid_username);

    }
}