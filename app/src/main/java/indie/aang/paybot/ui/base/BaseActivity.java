package indie.aang.paybot.ui.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import indie.aang.paybot.BuildConfig;
import indie.aang.paybot.PayBot;
import indie.aang.paybot.R;
import indie.aang.paybot.data.AppDataManager;
import indie.aang.paybot.di.component.ActivityComponent;
import indie.aang.paybot.di.component.DaggerActivityComponent;
import indie.aang.paybot.di.module.ViewModule;
import indie.aang.paybot.utilities.AppLogger;
import indie.aang.paybot.utilities.BounceInterpolator;
import indie.aang.paybot.utilities.Callbacks;
import indie.aang.paybot.utilities.CommonUtils;
import indie.aang.paybot.utilities.CustomToast;
import indie.aang.paybot.utilities.ImageUtil;
import indie.aang.paybot.utilities.KeyboardUtils;
import indie.aang.paybot.utilities.NetworkUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import java.io.File;
import javax.inject.Inject;
import butterknife.Unbinder;
import timber.log.Timber;
import static indie.aang.paybot.utilities.AppConstants.REQUEST_IMAGE_CAPTURE;
import static indie.aang.paybot.utilities.AppConstants.ROOT_DIR;


/**
 * Created by AangJnr on 18, September, 2018 @ 8:21 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */


public abstract class BaseActivity extends AppCompatActivity
        implements BaseContract.View, Callbacks.FragmentCallback {
    static Callbacks.ImageCaptureListener imageCaptureListener;
    public Uri mCapturedImageUri;


    private Unbinder mUnBinder;
    public String TAG;

    @Inject
    public AppDataManager mAppDataManager;

    @Inject
    public AlertDialog.Builder mAlertDialogBuilder;

    @Inject
    ProgressDialog mProgressDialog;


    ActivityComponent activityComponent;

    public static String DEVICE_ID;

    public static Gson gson = new Gson();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        overridePendingTransition(R.anim.right_slide, R.anim.slide_out_left);


        DEVICE_ID = CommonUtils.getDeviceId(this);





    }


    public static Gson getGson() {
        return gson;
    }

    @Override
    public void toggleFullScreen(Boolean hideNavBar, Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = getWindow().getDecorView().getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(flags);
            window.setStatusBarColor(Color.WHITE);
        }
    }



    public void hideStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = getWindow().getDecorView().getSystemUiVisibility();
            window.getDecorView().setSystemUiVisibility(flags);
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(mProgressDialog);
    }


    @Override
    public void setLoadingMessage(String message) {
     runOnUiThread(() -> {
    if(mProgressDialog != null && mProgressDialog.isShowing())
    mProgressDialog.setMessage(message);

        });
    }




    @Override
    public void showLoading(String title, String message, boolean indeterminate, int icon, boolean cancelableOnTouchOutside) {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(mProgressDialog, title, message, indeterminate, icon, cancelableOnTouchOutside);

    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.unknown_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {

        runOnUiThread(() -> {

    if (message != null) {
        CustomToast.makeToast(BaseActivity.this, message, Toast.LENGTH_LONG).show();
    } else {
        CustomToast.makeToast(BaseActivity.this, getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
    }

        });
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            KeyboardUtils.hideSoftInput(this);
        }
    }


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }



    public ActivityComponent getActivityComponent() {
       if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .viewModule(new ViewModule(this))
                    .applicationComponent(PayBot.getAppContext(this).getComponent())
                    .build();
        }

        return activityComponent;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;

        //return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {




        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openLoginActivityOnTokenExpire() {



    }

    public String getStringResources(int resource) {
        return getString(resource);
    }




    @Override
    public void showDialog(Boolean cancelable, String title, String message, DialogInterface.OnClickListener onPositiveButtonClickListener, String positiveText, DialogInterface.OnClickListener onNegativeButtonClickListener, String negativeText, int icon_drawable) {
        CommonUtils.showAlertDialog(mAlertDialogBuilder, cancelable, title, message, onPositiveButtonClickListener, positiveText, onNegativeButtonClickListener,
                negativeText, icon_drawable);

    }







    public AppDataManager getAppDataManager() {
        return mAppDataManager;
    }


    public void setBackListener(@Nullable View view){
        onBackPressed();
     }

    protected boolean hasPermissions(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {

            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {


                }
                return false;
            }

        }
        return true;
    }

    protected File createTemporaryFile(String part, String ext) throws Exception {
        File dir = new File(ROOT_DIR + File.separator + ".temp/");
        if (!dir.exists()) Timber.i("Is DIR created?  %s", dir.mkdirs());
        AppLogger.i("Destination path is %s", dir);


        return File.createTempFile(part, ext, dir);
    }



    public void onBackClicked(View v) {
        try {
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if(getAppDataManager().getBooleanValue("home")) {
           // goHome();
            getAppDataManager().setBooleanValue("home", false);
        }
        else
        super.onBackPressed();
    }

    public void openActivity(Intent intent){
        startActivity(intent);
    }

    @Override
    public void finishActivity(){
        finish();
    }

  /*  public void goHome(){

        Intent intent = new Intent(this, DrawerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    public void goHome(@Nullable View v){

        Intent intent = new Intent(this, DrawerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
*/
    public void makeCall(String name, String phoneNumber) {

        showDialog(true, "Call " + name + "?", "Call charges may apply.", (dialog, which) -> {

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }, "CALL", (dialog, which) -> dialog.cancel(), "CANCEL", 0);

    }





    public static void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public static void runLayoutAnimation(final ListView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);
        recyclerView.setLayoutAnimation(controller);
        //recyclerView.getAdapter().notifyAll();
        recyclerView.scheduleLayoutAnimation();
    }





  /*
    public void logout(@Nullable View v){

        showDialog(true, "Logging out!", "Are you sure you want to logout?\nThis will delete all your data and clear any un synced information.",
                (dialog, which) -> {

                    showLoading("Logging out", "Please wait...", true, 0, false);

                    Completable.fromAction(() -> getAppDataManager().setUserAsLoggedOut()).subscribeOn(Schedulers.io())

                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    getAppDataManager().getCompositeDisposable().add(d);

                                }

                                @Override
                                public void onComplete() {

                                    hideLoading();

                                    new Handler().postDelayed(() -> {
                                        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                    },1000);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                    showMessage("An error occurred logging you out. Please try again.");
                                    hideLoading();
                                }
                            });
                   *//* LinearLayout container = new LinearLayout(this);
                    container.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    params.setMargins(16, 0, 16, 0);

                    final EditText editText = new EditText(this);
                    editText.setLayoutParams(params);
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                    editText.setLines(1);
                    editText.setMaxLines(1);
                    editText.setHint("Enter your email");
                    container.addView(editText, params);



                    //Show enter password dialog

                    final AlertDialog dialogBuilder = new AlertDialog.Builder(this, R.style.AppDialog).create();
                    dialogBuilder.setTitle("Confirm logout");
                    dialogBuilder.setMessage("Please enter your email to confirm logout");
                    dialogBuilder.setButton(Dialog.BUTTON_POSITIVE, "LOGOUT", (dialog1, which1) -> {

                        dialog1.dismiss();

                        if(editText.getText().toString().equals(getAppDataManager().getUserEmail())){

                            showLoading("Logging out", "Please wait...", true, 0, false);

                            Completable.fromAction(() -> getAppDataManager().setUserAsLoggedOut()).subscribeOn(Schedulers.io())

                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                            getAppDataManager().getCompositeDisposable().add(d);

                                        }

                                        @Override
                                        public void onComplete() {

                                            hideLoading();

                                            new Handler().postDelayed(() -> {
                                                Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);
                                            },1000);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            e.printStackTrace();
                                            showMessage("An error occurred logging you out. Please try again.");
                                        }
                                    });
                        }else
                        showMessage("Incorrect email provided! Please try again.");





                    });
                    dialogBuilder.setButton(Dialog.BUTTON_NEGATIVE, "CANCEL", (dialog1, which1) -> dialog1.dismiss());




                    dialogBuilder.setView(container);
                    dialogBuilder.show();
*//*


                }, "YES, LOGOUT", (dialog, which) -> dialog.dismiss(), "NO, CANCEL", R.drawable.ic_power_settings_new_black_24dp);










    }

  */

    protected void logOut(){
        //Todo show dialog to confirm logout
        CommonUtils.showAlertDialog(mAlertDialogBuilder, true, getString(R.string.log_out), getString(R.string.log_out_rational),
                (dialogInterface, i) ->   {
                    mAppDataManager.setUserAsLoggedOut();
                    dialogInterface.dismiss();
                }, getString(R.string.yes), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }, getString(R.string.no), 0);
    }



    public void startCameraIntent() {
        Intent takePictureIntent;

        if (!hasPermissions(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
        } else {

            takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                mCapturedImageUri = getDestinationUri();

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageUri);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            } catch (Exception e) {
                e.printStackTrace();
                AppLogger.e(TAG, e.getMessage());
            }
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                AppLogger.d(TAG, "Starting camera intent");
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private Uri getDestinationUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        //String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss'.jpg'", Locale.UK).format(new Date());
        String destinationFileName = "image";
        if (getImage != null) {
            outputFileUri = FileProvider.getUriForFile(
                    this, BuildConfig.APPLICATION_ID,
                    new File(getImage.getPath(), destinationFileName));
        }
        return outputFileUri;
    }



    private void saveUri(@NonNull Uri uri) {
        mCapturedImageUri = uri;

        Bitmap bitmap;
        try {
            bitmap = ImageUtil.handleSamplingAndRotationBitmap(this, mCapturedImageUri);
            if(imageCaptureListener != null)
                imageCaptureListener.onImageCaptureComplete(ImageUtil.bitmapToBase64(bitmap));
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Failed to load");
            AppLogger.d(TAG,  e);
        }finally{
         }

    }

    public void triggerBounceAnimation(View view){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.bounce);
         anim.setInterpolator(new BounceInterpolator(0.2, 10));
        view.startAnimation(anim);
    }





}