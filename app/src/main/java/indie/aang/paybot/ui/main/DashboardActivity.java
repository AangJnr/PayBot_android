package indie.aang.paybot.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import indie.aang.paybot.R;
import indie.aang.paybot.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * A login screen that offers login via email/password.
 */
public class DashboardActivity extends BaseActivity implements DashboardContract.View{

    @Inject
    DashboardPresenter mPresenter;





    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));


        getActivityComponent().inject(this);
        mPresenter.takeView(this);
        mAppDataManager = mPresenter.getAppDataManager();




    }

}