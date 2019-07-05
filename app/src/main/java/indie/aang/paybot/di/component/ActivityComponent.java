package indie.aang.paybot.di.component;


import indie.aang.paybot.di.Scope.PerActivity;
import indie.aang.paybot.di.module.ViewModule;
import indie.aang.paybot.ui.login.LoginActivity;
import indie.aang.paybot.ui.main.DashboardActivity;
import indie.aang.paybot.ui.onboarding.OnBoardingActivity;
import indie.aang.paybot.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by AangJnr on 20, September, 2018 @ 2:12 AM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

@PerActivity
@Component(modules = {ViewModule.class}, dependencies = {ApplicationComponent.class})
public interface ActivityComponent {
    void inject(SplashActivity view);
    void inject(LoginActivity view);
    void inject(OnBoardingActivity view);
    void inject(DashboardActivity view);


}