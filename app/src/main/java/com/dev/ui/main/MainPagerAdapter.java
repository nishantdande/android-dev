package com.dev.ui.main;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dev.ui.main.dashboard.DashboardFragment;
import com.dev.ui.main.details.DetailsFragment;
import com.dev.ui.main.login.LoginFragment;

public class MainPagerAdapter extends FragmentStateAdapter {

    private int mTabCount;

    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    public void setCount(int count) {
        mTabCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return LoginFragment.newInstance();
            case 1:
                return DashboardFragment.newInstance();
            case 2:
                return DetailsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return mTabCount;
    }
}
