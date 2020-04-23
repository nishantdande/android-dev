package com.dev.data.prefs;

import com.dev.data.network.model.Story;

public interface PreferencesHelper {

    void setTheme(String theme);

    String  getTheme();

    String getAccessToken();

    void setAccessToken(String accessToken);

     void setStory(Story story);

    Story getStory();

    void clearStory();

}
