package indie.aang.paybot.utilities;

import android.text.Html;
import android.text.Spanned;

import androidx.annotation.NonNull;

import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ordgen on 8/25/17.
 */

public class TextUtilsHelper {
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public static <T, F> List<F> pluck(String fieldName, Class<F> fieldType, List<T> list, Class<T> listType)
            throws NoSuchFieldException, IllegalAccessException {
        Field f = listType.getField(fieldName);
        ArrayList<F> result = new ArrayList<F>();
        for (T element : list) {
            result.add(fieldType.cast(f.get(element)));
        }
        return result;
    }

    public static String getFormattedDateString(Calendar calendar) {
        String m = (calendar.get(Calendar.MONTH) + 1) < 10 ? ("0" + (calendar.get(Calendar.MONTH) + 1)) : String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String d = calendar.get(Calendar.DATE) < 10 ? ("0" + calendar.get(Calendar.DATE)) : String.valueOf(calendar.get(Calendar.DATE));
        return  calendar.get(Calendar.YEAR) + "-" + m + "-" + d;
    }

    public static String getFormattedDateString(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString("dd") + "-" + dateTime.toString("MM") + "-" + dateTime.toString("yy");
    }

    public static boolean isValidEmailAddress(String emailAddress) {
        String emailRegEx;
        Pattern pattern;
        // Regex for a valid email address
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.find();
    }


    public static boolean isCowtribeEmail(String email) {
        //TODO: Replace this with your own logic
        return CommonUtils.isEmailValid(email) && email.contains("@cowtribe.com");
    }

    public static boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 6;
    }


    public static String toTitleCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

    public static String convertArrayListToString(ArrayList<String> list, @NonNull String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i ++) {
            sb.append(list.get(i));

            if (i + 1 != list.size()) {
                sb.append(delimiter);
            }
        }

        return sb.toString();
    }

    public static ArrayList<String> convertToArray(@NonNull String string, @NonNull String delimiter) {
        return new ArrayList<String>(Arrays.asList(string.split(delimiter)));
    }
}
