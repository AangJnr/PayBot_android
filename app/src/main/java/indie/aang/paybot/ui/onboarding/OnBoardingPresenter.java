package indie.aang.paybot.ui.onboarding;


import javax.inject.Inject;

import indie.aang.paybot.data.AppDataManager;
import indie.aang.paybot.ui.base.BasePresenter;

/**
 * Created by AangJnr on 18, September, 2018 @ 9:06 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

public class OnBoardingPresenter extends BasePresenter<OnBoardingContract.View> implements OnBoardingContract.Presenter {

    AppDataManager mAppDataManager;

    @Inject
    public OnBoardingPresenter(AppDataManager appDataManager) {
        super(appDataManager);
        this.mAppDataManager = appDataManager;



        }





}
