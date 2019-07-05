package indie.aang.paybot.ui.main;


import indie.aang.paybot.data.AppDataManager;
import indie.aang.paybot.ui.base.BasePresenter;
import indie.aang.paybot.utilities.AppLogger;
import indie.aang.paybot.utilities.Callbacks;

import javax.inject.Inject;

/**
 * Created by AangJnr on 18, September, 2018 @ 9:06 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

public class DashboardPresenter extends BasePresenter<DashboardContract.View> implements DashboardContract.Presenter,
        Callbacks.SyncCompleteListener {

    AppDataManager mAppDataManager;



    @Inject
    public DashboardPresenter(AppDataManager appDataManager) {
        super(appDataManager);
        this.mAppDataManager = appDataManager;
    }



    @Override
    public void onSuccess(String message) {
        AppLogger.i(TAG, "**** ON SUCCESS");


        getView().hideLoading();
        getView().showMessage(message);

    }

    @Override
    public void onError(Throwable throwable) {
        AppLogger.i(TAG, "**** ON ERROR");

        getView().hideLoading();
        getView().showMessage(throwable.getMessage());


        throwable.printStackTrace();

        if(throwable.getMessage().contains("Please login again") || throwable.getMessage().contains("Token is invalid") || throwable.getMessage().contains("401")) {
            getView().openLoginActivityOnTokenExpire();
        }
    }
}
