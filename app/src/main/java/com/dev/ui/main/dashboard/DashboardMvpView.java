package com.dev.ui.main.dashboard;


import com.dev.data.network.model.LoginResponse;
import com.dev.data.network.model.Story;
import com.dev.ui.base.MvpView;

import java.util.ArrayList;

public interface DashboardMvpView extends MvpView {

    void onSuccess(ArrayList<Story> stories);

}
