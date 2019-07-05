package indie.aang.paybot.syncManager;


import indie.aang.paybot.data.AppDataManager;
import indie.aang.paybot.ui.base.BaseContract;
import indie.aang.paybot.utilities.Callbacks;
import indie.aang.paybot.utilities.TimeUtils;

public class UploadData {
    String TAG = "Upload data";
    boolean showProgress;
    String token;
    String syncDate;
    private AppDataManager mAppDataManager;
    private Callbacks.SyncCompleteListener onDownloadResourcesListener;
    private BaseContract.View mView;





    public UploadData(BaseContract.View view, AppDataManager appDataManager, Callbacks.SyncCompleteListener listener, boolean showProgress) {

        this.mAppDataManager = appDataManager;
        this.mView = view;
        this.onDownloadResourcesListener = listener;
        this.showProgress = showProgress;
        token = mAppDataManager.getAccessToken();
        syncDate = TimeUtils.getCurrentDateTime();

     }

    public static UploadData newInstance(BaseContract.View view, AppDataManager appDataManager, Callbacks.SyncCompleteListener listener, boolean showProgress) {
        return new UploadData(view, appDataManager, listener, showProgress);
    }

    public AppDataManager getAppDataManager() {
        return mAppDataManager;
    }

    public BaseContract.View getView() {
        return mView;
    }
    // blockingGet() for awaiting of completion





}
