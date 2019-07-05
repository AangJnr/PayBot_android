package indie.aang.paybot.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import indie.aang.paybot.R;
import indie.aang.paybot.data.DataManager;
import indie.aang.paybot.ui.base.BaseActivity;
import indie.aang.paybot.ui.main.DashboardActivity;

/**
 * A login screen that offers login via email/password.
 */


public class OnBoardingActivity extends BaseActivity implements OnBoardingContract.View {

    @Inject
    OnBoardingPresenter mPresenter;

    @BindView(R.id.view_pager)
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toggleFullScreen(true, getWindow());
        setContentView(R.layout.activity_onboarding);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        mPresenter.takeView(this);
        mAppDataManager = mPresenter.getAppDataManager();


        viewPager.setAdapter(new IntroAdapter(getSupportFragmentManager()));












    }


    @Override
    public void openLoginActivityOnTokenExpire() {
    }







    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.dropView();
        super.onDestroy();
    }

}