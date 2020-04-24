package com.dev.ui.main.dashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.DevApplication;
import com.dev.R;
import com.dev.data.network.model.LoginResponse;
import com.dev.data.network.model.Story;
import com.dev.di.component.ActivityComponent;
import com.dev.ui.base.BaseActivity;
import com.dev.ui.base.BaseFragment;
import com.dev.utils.AppLogger;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class DashboardFragment extends BaseFragment implements DashboardMvpView, DashboardAdapter.Callback {

    @Inject
    DashboardMvpPresenter<DashboardMvpView> mPresenter;

    @Inject
    DashboardAdapter mDashboardAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    int mPageCount =0;

    public static DashboardFragment newInstance() {
        Bundle args = new Bundle();
        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }


    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDashboardAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    mPresenter.getTopStories(++mPageCount);
                }
            }
        });
        mDashboardAdapter.setCallback(this);
        mPresenter.getTopStories(mPageCount);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }


    @Override
    public void onSuccess(ArrayList<Story> stories) {
        mDashboardAdapter.addItems(stories);
    }


    @Override
    public void onStoryClick(Story story) {
        mPresenter.storeStory(story);
        getBaseActivity().switchScreen(2);
    }

    @Override
    public void onRetryClick() {
        mPresenter.getTopStories(mPageCount);
    }
}
