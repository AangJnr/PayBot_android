package indie.aang.paybot.ui.login;



 import indie.aang.paybot.data.AppDataManager;
import indie.aang.paybot.data.DataManager;
 import indie.aang.paybot.syncManager.DownloadResources;
import indie.aang.paybot.ui.base.BasePresenter;
 import indie.aang.paybot.utilities.AppConstants;
import indie.aang.paybot.utilities.AppLogger;
import indie.aang.paybot.utilities.Callbacks;

import javax.inject.Inject;


import static indie.aang.paybot.ui.base.BaseActivity.getGson;

/**
 * Created by AangJnr on 18, September, 2018 @ 9:06 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter, Callbacks.SyncCompleteListener {


    AppDataManager mAppDataManager;


    @Inject
    public LoginPresenter(AppDataManager appDataManager) {
        super(appDataManager);
        this.mAppDataManager = appDataManager;


    }



    @Override
    public void onSuccess(String message) {
        AppLogger.i(TAG, "**** ON SUCCESS");

        getView().hideLoading();
        getView().showMessage(message);

        getAppDataManager().setUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN);


    }

    @Override
    public void onError(Throwable throwable) {
        AppLogger.i(TAG, "**** ON ERROR");
        getView().hideLoading();
        getView().showMessage(throwable.getMessage());
        throwable.printStackTrace();

    }



}
