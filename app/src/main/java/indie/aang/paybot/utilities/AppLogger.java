package indie.aang.paybot.utilities;


import android.app.Application;
import android.util.Log;

import com.balsikandar.crashreporter.CrashReporter;
import indie.aang.paybot.BuildConfig;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

import timber.log.Timber;

import static android.util.Log.INFO;

public class AppLogger {

    public static void init(Application applicationContext) {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {

            String crashReporterPath = AppConstants.ROOT_DIR + File.separator + "crashReports";
            CrashReporter.initialize(applicationContext, crashReporterPath);
            //Todo Initialize Crashytics here
            //Fabric.with(applicationContext, new Crashlytics());

            }
    }

    public static void d(String s, Object... objects) {
        Timber.d(s, objects);
    }

    public static void d(Throwable throwable, String s, Object... objects) {
        Timber.d(throwable, s, objects);
    }

    public static void i(String s, Object... objects) {
        Timber.i(s, objects);
    }

    public static void i(Throwable throwable, String s, Object... objects) {
        Timber.i(throwable, s, objects);
    }

    public static void w(String s, Object... objects) {
        Timber.w(s, objects);
    }

    public static void w(Throwable throwable, String s, Object... objects) {
        Timber.w(throwable, s, objects);
    }

    public static void e(String s, Object... objects) {
        Timber.e(s, objects);
    }

    public static void e(Throwable throwable, String s, Object... objects) {
        Timber.e(throwable, s, objects);
    }

    public static void d( String s) {
        Timber.d(s);
    }

    public static void i(String tag, String message) {
        Timber.i("%s%s  -> %s", " ******** ",tag, message);
    }

    public static void i(String message) {
        Timber.i( "%s -> %s", " ******** ", message);
    }

    public static void e(String tag, String message) {
        Timber.e("%s%s  -> %s", " ******** ",tag, message);
    }

    public static void dump(String tag, String message) {
       // Timber.e("%s%s  -> %s", " ******** ",tag, message);
        System.out.println(String.format("%s%s  -> %s", " ******** ",tag, message));
    }

    private static final class CrashReportingTree extends Timber.Tree {
        @Override
        protected boolean isLoggable(@Nullable String tag, int priority) {
            return priority >= INFO;
        }
        @Override
        protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {

            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            MyCrashReporter.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    MyCrashReporter.logError(t);
                } else if (priority == Log.WARN) {
                    MyCrashReporter.logWarning(t);
                }
            }
        }
    }

}