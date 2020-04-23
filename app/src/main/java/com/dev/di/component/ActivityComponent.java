
package com.dev.di.component;

import com.dev.di.PerActivity;
import com.dev.di.module.ActivityModule;
import com.dev.ui.main.MainActivity;
import com.dev.ui.main.login.LoginFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginFragment fragment);

    void inject(MainActivity activity);


}