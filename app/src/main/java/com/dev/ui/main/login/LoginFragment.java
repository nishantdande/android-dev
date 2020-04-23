package com.dev.ui.main.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewpager.widget.ViewPager;

import com.dev.R;
import com.dev.data.network.model.LoginResponse;
import com.dev.di.component.ActivityComponent;
import com.dev.ui.base.BaseActivity;
import com.dev.ui.base.BaseFragment;
import com.dev.ui.main.MainActivity;
import com.dev.utils.AppLogger;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.username)
    TextInputLayout username;

    @BindView(R.id.password)
    TextInputLayout password;

    @BindView(R.id.login)
    AppCompatButton login;

    @BindView(R.id.changeTheme)
    SwitchCompat changeTheme;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mPresenter.getTheme();
        }
        return view;
    }


    @Override
    protected void setUp(View view) {

    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @OnClick(R.id.login)
    public void login(){
        mPresenter.onServerLoginClick(getText(username), getText(password));
    }

    @OnClick(R.id.parentLayout)
    void parentLayoutClick(){
        hideKeyboard();
    }

    @Override
    public void onSuccess(LoginResponse loginResponse) {
        AppLogger.d(loginResponse.getAccessToken());
    }

    @Override
    public void updateThemeUi(String theme) {
        if (theme.equals("ThemeYellow"))
            changeTheme.setChecked(true);
        else
            changeTheme.setChecked(false);
    }

    @OnCheckedChanged(R.id.changeTheme)
    void changeTheme(boolean value){
        if(!changeTheme.isPressed()) {
            return;
        }
        ((BaseActivity)getActivity()).changeTheme(value);
    }

    private String getText(TextInputLayout textInputLayout){
        return textInputLayout.getEditText().getText().toString();
    }
}
