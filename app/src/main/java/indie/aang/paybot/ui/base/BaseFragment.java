package indie.aang.paybot.ui.base;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import indie.aang.paybot.R;
import indie.aang.paybot.data.AppDataManager;
import indie.aang.paybot.utilities.AppConstants;
import indie.aang.paybot.utilities.CommonUtils;
import indie.aang.paybot.utilities.OnFragmentInteractionListener;

import butterknife.Unbinder;

/**
 * Created by AangJnr on 18, September, 2018 @ 8:19 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */


public abstract class BaseFragment extends Fragment implements BaseContract.FragmentView {
    public static boolean IS_VERTICAL = false;
    public String TAG;
    private BaseActivity mActivity;
    public static int SELECTED_NAV_ITEM;
    public InflateOptionsMenuListener inflateOptionsMenuListener;
    public OnFragmentInteractionListener fragmentInteractionListener;
    public Unbinder UNBINDER;
    public View ROOT_VIEW;

    int limit = 20;
    int currentTotalItemsCount = 0;
    int currentPage;
    int totalPages;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        TAG = getClass().getSimpleName();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setUp(view);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            fragmentInteractionListener = (OnFragmentInteractionListener) context;
            fragmentInteractionListener.toggleBottomNavigation(false, SELECTED_NAV_ITEM);
            //fragmentInteractionListener.showDrawerMenu( R.id.menu_home, R.id.menu_performance, R.id.menu_my_communities, R.id.bottom_nav_finances, R.id.bottom_nav_orders, R.id.bottom_nav_farmers);
        }


        if (context instanceof InflateOptionsMenuListener) {
            inflateOptionsMenuListener = (InflateOptionsMenuListener) context;
            inflateOptionsMenuListener.onLoadToolbarMenu(R.menu.toolbar_menu);
        }


        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }



    }

     public void showLoading() {
         if (mActivity.mProgressDialog != null) {
             setLoadingMessage("Please wait...");
             mActivity.mProgressDialog.show();
         }

    }

     public void hideLoading() {
        if (mActivity.mProgressDialog != null && mActivity.mProgressDialog.isShowing()) {
            mActivity.mProgressDialog.cancel();
        }
    }

    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

     public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }


    @Override
    public void onDetach() {
        //mActivity = null;

        fragmentInteractionListener = null;
        inflateOptionsMenuListener = null;
        super.onDetach();
    }



    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }



    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public AppDataManager getAppDataManager(){
        return mActivity.mAppDataManager;
    }



    public void setUnBinder(Unbinder unBinder) {
        UNBINDER = unBinder;
    }

    //protected abstract void setUp(View view);

    @Override
    public void onDestroy() {
        if (UNBINDER != null) {
            UNBINDER.unbind();
        }
        super.onDestroy();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }


    public void showDialog(Boolean cancelable, String title, String message, DialogInterface.OnClickListener onPositiveButtonClickListener, String positiveText, DialogInterface.OnClickListener onNegativeButtonClickListener, String negativeText, int icon_drawable) {
        CommonUtils.showAlertDialog(mActivity.mAlertDialogBuilder, cancelable, title, message, onPositiveButtonClickListener, positiveText, onNegativeButtonClickListener,
                negativeText, icon_drawable);


    }


     public void setLoadingMessage(String message) {
        if(mActivity.mProgressDialog != null)
            mActivity.mProgressDialog.setMessage(message);
    }


    public void showLoading(String title, String message, boolean indeterminate, int icon, boolean cancelableOnTouchOutside) {
        hideLoading();
        mActivity.mProgressDialog = CommonUtils.showLoadingDialog(mActivity.mProgressDialog, title, message, indeterminate, icon, cancelableOnTouchOutside);

    }


    public  void getCurrentLocation(LocationListener locationListener) {

        boolean GpsStatus;
        LocationManager locationManager;

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (GpsStatus) {

            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setSpeedRequired(false);
            criteria.setCostAllowed(true);
            criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
            criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);


            // This is the Best And IMPORTANT part
            final Looper looper = null;

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                locationManager.requestSingleUpdate(criteria, locationListener, looper);

            }
        } else {
            hideLoading();
            showDialog(true, "Enable Location", "Please enable GPS in settings", (d, w)->{


                Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(viewIntent, AppConstants.LOCATION_SETTINGS);


            }, "ENABLE", (d, w)-> d.dismiss(), "CANCEL", 0 );
            showMessage("Please Enable GPS First");


        }
    }




    public interface InflateOptionsMenuListener{
        void onLoadToolbarMenu(int layoutId);
    }

    public OnFragmentInteractionListener getFragmentInteractionListener() {
        return fragmentInteractionListener;
    }

    public void makeCall(String name, String phoneNumber) {

        showDialog(true, "Call " + name + "?", "Call charges may apply.", (dialog, which) -> {

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }, "CALL", (dialog, which) -> dialog.cancel(), "CANCEL", 0);

    }

}