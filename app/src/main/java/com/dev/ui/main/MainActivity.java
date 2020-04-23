package com.dev.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.dev.R;
import com.dev.data.prefs.PreferencesHelper;
import com.dev.ui.base.BaseActivity;
import com.dev.utils.AppLogger;
import com.dev.utils.CommonUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.feed_view_pager)
    ViewPager2 mViewPager;

    @Inject
    MainPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(CommonUtils.initPref());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(MainActivity.this);

        mPresenter.getTheme();
        setUp();
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        mPagerAdapter.setCount(3);
        mViewPager.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setUserInputEnabled(false);
    }

    @Override
    protected void updateTheme(boolean value) {
        mPresenter.setTheme(value);
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void navigateScreen(int value) {
        mViewPager.setCurrentItem(value);
    }

    @Override
    public void setTheme(String theme) {
        if (theme != null && theme.equals("ThemeYellow")) {
            setTheme(R.style.ThemeYellow);
        }
        else{
            setTheme(R.style.AppTheme);
        }
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() > 0)
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
        else
            super.onBackPressed();
    }
}
