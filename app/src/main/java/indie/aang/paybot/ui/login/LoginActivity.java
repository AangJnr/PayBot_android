package indie.aang.paybot.ui.login;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import indie.aang.paybot.R;
import indie.aang.paybot.ui.base.BaseActivity;
import indie.aang.paybot.utilities.AppLogger;
import com.karan.churi.PermissionManager.PermissionManager;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static android.os.Build.VERSION_CODES.M;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginPresenter mPresenter;
    PermissionManager permissionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toggleFullScreen(false, getWindow());

        setContentView(R.layout.activity_login);
        setUnBinder(ButterKnife.bind(this));


        getActivityComponent().inject(this);
        mPresenter.takeView(this);
        mAppDataManager = mPresenter.getAppDataManager();


        

      /*  if(BuildConfig.DEBUG){
            mEmailView.setText("camara.admin@cowtribe.com");
            mPasswordView.setText("hacker101");
        }*/


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestForPermissions();
        }




    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.dropView();
        super.onDestroy();
    }

    @Override
    public void openLoginActivityOnTokenExpire() {

    }

    @RequiresApi(M)
    void requestForPermissions(){


        permissionManager = new PermissionManager() {
            @Override
            public void ifCancelledAndCanRequest(Activity activity) {

                AppLogger.i("****** Can request");

                showDialog(false, getString(R.string.permissions_needed),
                        getString(R.string.permissions_needed_rationale), (dialogInterface, i) ->
                                permissionManager.checkAndRequestPermissions(LoginActivity.this),
                        getString(R.string.grant_permissions), (dialogInterface, i) -> supportFinishAfterTransition(),
                        getString(R.string.quit), 0);
            }

            @Override
            public void ifCancelledAndCannotRequest(Activity activity) {
                AppLogger.i("****** Cannot request");


                showDialog(false, getString(R.string.permissions_not_provided),
                        "Please provide the permission in Settings.", null,
                        "", (dialogInterface, i) -> supportFinishAfterTransition(), getString(R.string.quit), 0);

            }

        };

        new Handler().postDelayed(() -> showDialog(false, getString(R.string.hello),
                getString(R.string.provide_all_permissions_rationale), (dialogInterface, i) -> {
                    permissionManager.checkAndRequestPermissions(LoginActivity.this);
                }, getString(R.string.grant_permissions), (dialogInterface, i) -> finishAfterTransition(),
                getString(R.string.quit), 0), 500);

    }



    @Override
    public void finishActivity() {
        super.finishActivity();
    }
}