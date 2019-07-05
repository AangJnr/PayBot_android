package indie.aang.paybot.ui.ussdService;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import indie.aang.paybot.R;

public class OverlayShowingService extends Service {

    private View  view;

    private WindowManager wm;

    public final static String EXTRA = "TITTLE";

    private String tittle = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra(EXTRA))
            tittle = intent.getStringExtra(EXTRA);
        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }


        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.service_processing_payment_layout, null);

       /* overlayedButton = new Button(this);
        overlayedButton.setText(tittle);
        overlayedButton.setBackgroundColor(0xFFFFFFFF);
        overlayedButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);*/


        WindowManager.LayoutParams params =
                new WindowManager.LayoutParams
                        (WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, LAYOUT_FLAG
                                , WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER;
        wm.addView(view, params);


        View cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener((v)-> {onDestroy();});


        new Handler().postDelayed(() -> {
          cancel.setVisibility(View.VISIBLE);
        }, 5000);


        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new Handler().postDelayed(() -> {
            if (view != null) {
                wm.removeView(view);
                view = null;
            }
        }, 500);
    }
}