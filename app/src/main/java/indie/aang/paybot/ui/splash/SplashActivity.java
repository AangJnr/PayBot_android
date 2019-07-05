package indie.aang.paybot.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import indie.aang.paybot.data.DataManager;
import indie.aang.paybot.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import indie.aang.paybot.ui.login.LoginActivity;
import indie.aang.paybot.ui.main.DashboardActivity;
import indie.aang.paybot.ui.onboarding.OnBoardingActivity;

/**
 * A login screen that offers login via email/password.
 */


public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toggleFullScreen(true, getWindow());
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        mPresenter.takeView(this);
        mAppDataManager = mPresenter.getAppDataManager();



        //Todo check if user is already on-boarded or is a new user
        if(mAppDataManager.getUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN.getType())
            //Todo go to main dashboard
             startActivity(new Intent(this, DashboardActivity.class));
        else

            //Todo check if user is on boarded or not

            if(mAppDataManager.getBooleanValue("onboarded"))
                startActivity(new Intent(this, LoginActivity.class));
             else
                startActivity(new Intent(this, OnBoardingActivity.class));


        finish();



    }

    @Override
    public void startAnimations() {
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




}