package com.dev.ui.main.details;

import com.dev.data.network.model.Story;
import com.dev.ui.base.MvpView;

public interface DetailsMvpView extends MvpView {

    void setStory(Story story);
}
