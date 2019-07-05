package indie.aang.paybot.syncManager;


import org.json.JSONArray;

import indie.aang.paybot.data.AppDataManager;
import indie.aang.paybot.ui.base.BaseContract;
import indie.aang.paybot.utilities.Callbacks;

public class DownloadResources {

    private Callbacks.SyncCompleteListener onDownloadResourcesListener;

    String TAG = "DownloadResourcesHyped";
    private BaseContract.View mView;
    private AppDataManager mAppDataManager;
    boolean showProgress;
    String token;



    public static DownloadResources newInstance(BaseContract.View view, AppDataManager appDataManager, Callbacks.SyncCompleteListener listener, boolean showProgress){
        return new DownloadResources(view, appDataManager, listener, showProgress);
    }


    public static DownloadResources newInstance(BaseContract.View view, AppDataManager appDataManager, Callbacks.SyncCompleteListener listener, boolean showProgress, String _token){
        return new DownloadResources(view, appDataManager, listener, showProgress,_token);

    }

    private DownloadResources(BaseContract.View view, AppDataManager appDataManager, Callbacks.SyncCompleteListener listener, boolean showProgress){

        this.mAppDataManager = appDataManager;
        this.mView = view;
        this.onDownloadResourcesListener = listener;
        this.showProgress = showProgress;
        token = mAppDataManager.getAccessToken();
    }

    private DownloadResources(BaseContract.View view, AppDataManager appDataManager, Callbacks.SyncCompleteListener listener, boolean showProgress, String _token){

        this.mAppDataManager = appDataManager;
        this.mView = view;
        this.onDownloadResourcesListener = listener;
        this.showProgress = showProgress;
        token = _token;
    }


    public AppDataManager getAppDataManager() {
        return mAppDataManager;
    }

    public BaseContract.View getView() {
        return mView;
    }




    public void getUserData(){


    }



}
