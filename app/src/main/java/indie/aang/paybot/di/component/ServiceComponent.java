package indie.aang.paybot.di.component;


import indie.aang.paybot.di.Scope.PerService;
import indie.aang.paybot.di.module.ServiceModule;

import dagger.Component;

/**
 * Created by AangJnr on 20, September, 2018 @ 2:20 AM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

   // void inject(SyncService service);

}

