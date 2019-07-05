package indie.aang.paybot.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import indie.aang.paybot.R;


public final class ViewUtils {

    private ViewUtils() {
        // This utility class is not publicly instantiable
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void markFormAsComplete(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, R.color.textColorTertiaryLightInverted), PorterDuff.Mode.SRC_ATOP);
        }
    }




    public static View getPlaceHolderView(View emptyView, String mainText, String subText, int imageResource){


        ImageView placeHolderImage = emptyView.findViewById(R.id.place_holder_image);
        TextView placeHolderMainText = emptyView.findViewById(R.id.place_holder_main_text);
        TextView placeHolderSubText = emptyView.findViewById(R.id.place_holder_sub_text);
        placeHolderImage.setImageResource(imageResource);
        placeHolderMainText.setText(mainText);
        placeHolderSubText.setText(subText);

        return emptyView;
    }

    public static View getPlaceHolderView(Context context, String mainText, String subText, @DrawableRes int drawable){

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View emptyView = layoutInflaterAndroid.inflate(R.layout.content_place_holder, null);

        ImageView placeHolderImage = emptyView.findViewById(R.id.place_holder_image);
        TextView placeHolderMainText = emptyView.findViewById(R.id.place_holder_main_text);
        TextView placeHolderSubText = emptyView.findViewById(R.id.place_holder_sub_text);

        placeHolderImage.setImageResource(drawable);
        placeHolderMainText.setText(mainText);
        placeHolderSubText.setText(subText);

        return emptyView;
    }










}