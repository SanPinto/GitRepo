package com.event.maker.login.presenter;

import com.event.maker.R;
import com.event.maker.login.LoginContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sangeetha on 21/6/17.
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({Pattern.class})
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterImplTest {

    private LoginPresenterImpl mPresenter;
    @Mock
    private LoginContract.LoginView mView;

//    @Mock
//    private static final Pattern EMAIL_ADDRESS = Pattern.compile(
//            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                    "\\@" +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                    "(" +
//                    "\\." +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                    ")+"
//    );

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new LoginPresenterImpl();
        mPresenter.attachView(mView);
//        PowerMockito.mockStatic(Pattern.class);
//        Mockito.when(EMAIL_ADDRESS);
//        when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                CharSequence str = (CharSequence) invocation.getArguments()[0];
//                return (str == null || str.length() == 0);
//            }
//        });

//        when(EMAIL_ADDRESS.matcher(any(CharSequence.class)).matches()).thenAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                return true;
//            }
//        });
    }

    @Test
    public void ValidateSignUP() throws Exception {
        when(mView.getUserName()).thenReturn("");


    }

    @Test
    public void shouldShowErrorMsgWhenUserNameIsEmpty(String userName) throws Exception {
        when(mView.getUserName()).thenReturn("");
        mPresenter.isEmpty("");
        verify(mView).showUserNameEmptyError(R.string.username_empty);
    }

//TODO validating this method,
    @Test
    public void shouldShowInvalidusernameWhenEmailIsInvalid() throws Exception {
       when(mView.getUserName()).thenReturn("hello");
        verify(mView).showInvalidUserNameError(R.string.invalid_username);

    }

    @Test
    public void showShowErrorMsgWhenPasswordIsEmpty() throws Exception {
        when(mView.getUserName()).thenReturn("anisha@gmail.com");
        when(mView.getPassWord()).thenReturn("");
        mPresenter.signIn();
        verify(mView).showPaswordEmptyError(R.string.password_empty);

    }

    @Test
    public void showInvalidPasswordMsgWhenPasswordIsInvalid() throws Exception {
        when(mView.getPassWord()).thenReturn("1234");
        mPresenter.signIn();
        verify(mView).showInvalidPasswordError(R.string.invalid_password);

    }
}