package indie.aang.paybot.ui.sampledata;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import indie.aang.paybot.R;
import indie.aang.paybot.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * A login screen that offers login via email/password.
 */
public class BoilerPlateActivity extends BaseActivity implements BoilerPlateContract.View {

    @Inject
    BoilerPlatePresenter mPresenter;




    public static Intent getStartIntent(Context context) {
        return new Intent(context, BoilerPlateActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUnBinder(ButterKnife.bind(this));

        //getActivityComponent().inject(this);
        mPresenter.takeView(this);
        mAppDataManager = mPresenter.getAppDataManager();




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