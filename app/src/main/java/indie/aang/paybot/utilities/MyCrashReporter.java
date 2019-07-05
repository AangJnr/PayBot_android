package indie.aang.paybot.utilities;


import com.balsikandar.crashreporter.CrashReporter;

/**
 * Created by AangJnr on 15, November, 2018 @ 1:11 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */


public final class MyCrashReporter {


    private MyCrashReporter() {
        throw new AssertionError("No instances.");
    }

    public static void log(int priority, String tag, String message) {
        // TODO add log entry to circular buffer.


    }

    public static void logWarning(Throwable t) {
        // TODO report non-fatal warning.
        CrashReporter.logException((Exception) t);
    }

    public static void logError(Throwable t) {
        // TODO report non-fatal error.
        CrashReporter.logException((Exception) t);
    }


}