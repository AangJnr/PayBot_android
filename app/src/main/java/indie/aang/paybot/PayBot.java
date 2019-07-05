package indie.aang.paybot;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import indie.aang.paybot.di.component.ApplicationComponent;
import indie.aang.paybot.di.component.DaggerApplicationComponent;
import indie.aang.paybot.di.module.ApplicationModule;
import indie.aang.paybot.utilities.AppLogger;

import static indie.aang.paybot.utilities.FileUtils.createNoMediaFile;


public class PayBot extends Application {

    private ApplicationComponent mApplicationComponent;


    public static  PayBot getAppContext(Context context) {
        return (PayBot) context.getApplicationContext();
    }


    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNoMediaFile();

        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);

        //Initialize application logging mechanism
        AppLogger.init(this);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


    }

}
