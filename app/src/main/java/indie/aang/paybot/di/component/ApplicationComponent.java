package indie.aang.paybot.di.component;


import android.app.Application;
import android.content.Context;

 import indie.aang.paybot.PayBot;
import indie.aang.paybot.data.AppDataManager;
import indie.aang.paybot.di.Scope.ApplicationContext;
import indie.aang.paybot.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AangJnr on 20, September, 2018 @ 2:12 AM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(PayBot app);


    @ApplicationContext
    Context getContext();

    Application getApplication();

    AppDataManager getAppDataManager();

}