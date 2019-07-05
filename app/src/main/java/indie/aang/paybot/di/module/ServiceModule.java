package indie.aang.paybot.di.module;


import android.app.Service;

import dagger.Module;

/**
 * Created by AangJnr on 20, September, 2018 @ 2:19 AM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
}