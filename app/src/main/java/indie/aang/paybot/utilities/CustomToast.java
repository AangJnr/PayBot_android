package indie.aang.paybot.utilities;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import indie.aang.paybot.R;


/**
 * Created by AangJnr on 25, September, 2018 @ 11:05 AM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */


public class CustomToast extends Toast {

    static Toast toast;
    Context context;

    public CustomToast(Context context) {
        super(context);
    }


    public static Toast makeToast(Context c, String message, int duration) {

        LayoutInflater inflater = LayoutInflater.from(c);
        View layout = inflater.inflate(R.layout.custom_toast, null, false);
        // set a message
        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(message);
        // Toast...
        toast = new Toast(c);
       /* toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,
                (int) (ScreenUtils.getScreenHeight(c) / 3));*/
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.setDuration(duration);
        toast.setView(layout);

        return toast;

    }


}