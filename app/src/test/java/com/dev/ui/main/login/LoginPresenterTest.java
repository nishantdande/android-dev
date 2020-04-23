package com.dev.ui.main.login;

import android.util.Patterns;

import com.dev.R;
import com.dev.data.DataManager;
import com.dev.data.network.model.LoginRequest;
import com.dev.data.network.model.LoginResponse;
import com.dev.utils.CommonUtils;
import com.dev.utils.rx.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    LoginMvpView mMockLoginMvpView;
    @Mock
    DataManager mMockDataManager;

    private LoginPresenter<LoginMvpView> mLoginPresenter;
    private TestScheduler mTestScheduler;

    private static final String EMAIL_ADDRESS = "test@worldofplay.in";
    private static final String PASSWORD = "Worldofplay@2020";
    private static final String TOKEN = "VwvYXBpXC9";
    private static final String INVALID_EMAIL = "testworldofplay.in";
    private static final String INVALID_PASSWORD = "worldofplay2020";
    private static final String EMPTY_FIELD = "";

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mLoginPresenter = new LoginPresenter<>(
            mMockDataManager,
            testSchedulerProvider,
            compositeDisposable);
        mLoginPresenter.onAttach(mMockLoginMvpView);
    }

    @Test
    public void testLoginEmptyEmail() {

        mLoginPresenter.onServerLoginClick(EMPTY_FIELD, PASSWORD);

        mTestScheduler.triggerActions();

        verify(mMockLoginMvpView).onError(R.string.empty_email);
    }

    @Test
    public void testLoginInvalidEmail() {
        assertFalse(INVALID_EMAIL.matches(CommonUtils.EMAIL_PATTERN));
    }

    @Test
    public void testLoginValidEmail() {
        assertTrue(EMAIL_ADDRESS.matches(CommonUtils.EMAIL_PATTERN));
    }

    @Test
    public void testLoginEmptyPassword() {

        mLoginPresenter.onServerLoginClick(EMAIL_ADDRESS, EMPTY_FIELD);

        mTestScheduler.triggerActions();

        verify(mMockLoginMvpView).onError(R.string.empty_password);
    }

    @Test
    public void testLoginValidPassword() {
        assertTrue(PASSWORD.matches(CommonUtils.PASSWORD_PATTERN));
    }

    @Test
    public void testLoginInvalidPassword() {
        assertFalse(INVALID_PASSWORD.matches(CommonUtils.PASSWORD_PATTERN));
    }

    @Test
    public void testServerLoginSuccess() {

        LoginResponse loginResponse = new LoginResponse();

        doReturn(Single.create(e -> {
            e.onSuccess(loginResponse);
        })).when(mMockDataManager)
                .doServerLoginApiCall(new LoginRequest
                        .ServerLoginRequest(EMAIL_ADDRESS, PASSWORD));

        mLoginPresenter.onServerLoginClick(EMAIL_ADDRESS, PASSWORD);

        mTestScheduler.triggerActions();

        verify(mMockLoginMvpView).showLoading();
        verify(mMockLoginMvpView).onSuccess(loginResponse);
        verify(mMockLoginMvpView).hideLoading();

//        verify(mMockLoginMvpView).openDetailFragment();
    }


    @After
    public void tearDown() throws Exception {
        mLoginPresenter.onDetach();
    }

}