package com.event.maker.login.presenter;

import com.event.maker.login.SignUpContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


/**
 * Created by sangeetha on 28/6/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpPresenterImplTest {

    private SignUpPresenterImpl mPresenter;
    @Mock
    private SignUpContract.SignUpView mView;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new SignUpPresenterImpl();
        mPresenter.attachView(mView);

    }

    @Test
    public void validateSingIn() throws Exception {
        when(mView.getUserName()).thenReturn("");

    }
}