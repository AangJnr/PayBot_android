package indie.aang.paybot.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;

import indie.aang.paybot.R;
import indie.aang.paybot.ui.ussdService.OverlayShowingService;
import com.romellfudi.ussdlibrary.USSDApi;
import com.romellfudi.ussdlibrary.USSDController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class UssdService {

    private String TAG = "USSDService";
    private static Callbacks.UssdListener ussdListener;


    private String MOMO_PIN;
    private Intent svc;
    private int SIM_SLOT = 0;
    private boolean isInitiated = false;
    private HashMap<String, HashSet<String>> map;
    private Context mContext;
    AlertDialog.Builder builder;
    USSDApi ussdApi;



    private UssdService(Context context){
        this.mContext = context;
        map = new HashMap<>();
        map.put("KEY_LOGIN", new HashSet<>(Arrays.asList("Enter pin", "pin")));
        map.put("KEY_RETRY", new HashSet<>(Arrays.asList("Retry", "problem")));
        map.put("KEY_ERROR", new HashSet<>(Arrays.asList("error", "null")));
        map.put("KEY_COMPLETE", new HashSet<>(Arrays.asList("successful", "done")));
        builder = new AlertDialog.Builder(mContext, R.style.AppDialog);
        ussdApi = USSDController.getInstance(new ContextThemeWrapper(context, R.style.AppAlertDialog));


    }

    public static UssdService getInstance(Context context){
        return new UssdService(context);
    }

    public static void setUssdListener(Callbacks.UssdListener listener){
        ussdListener = listener;


    }


    public static void clearUssdListener(){
        ussdListener = null;
    }


    public void showEnterPinDialog(String code){
        LinearLayout container = new LinearLayout(mContext);
        container.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 0, 16, 0);
        final EditText editText = new EditText(mContext);
        editText.setLayoutParams(params);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText.setLines(1);
        editText.setMaxLines(1);
        editText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4)});
        editText.setHint("Enter pin");
        container.addView(editText, params);


        final AlertDialog dialogBuilder = new AlertDialog.Builder(mContext, R.style.AppDialog).create();
        dialogBuilder.setTitle("Issue Payment");
        dialogBuilder.setMessage("Please enter your mobile money pin to issue payment");
        dialogBuilder.setButton(Dialog.BUTTON_POSITIVE, "Authorize", (dialog1, which1) -> {

            KeyboardUtils.hideSoftInput((Activity) mContext);

            MOMO_PIN = editText.getText().toString().trim();

            if (MOMO_PIN.isEmpty() || MOMO_PIN.length() != 4) {
               showMessage("Please enter the correct pin");
                return;
            }
            dialog1.dismiss();

            startUssdService(code);

        });
        dialogBuilder.setButton(Dialog.BUTTON_NEGATIVE, "CANCEL", (dialog1, which1) -> dialog1.dismiss());

        dialogBuilder.setView(container);
        dialogBuilder.show();

        KeyboardUtils.showSoftInput(editText, mContext);

    }


     void startUssdService(String code){
        isInitiated = false;

        //USSDApi ussdApi = USSDController.getInstance(mContext);
        svc = new Intent(mContext, OverlayShowingService.class);


        if (USSDController.verifyOverLay(mContext) && USSDController.verifyAccesibilityAccess(mContext)) {
            svc.putExtra(OverlayShowingService.EXTRA, "Processing...");
            mContext.startService(svc);


            AppLogger.e("APP", "START OVERLAY DIALOG");


            ussdApi.callUSSDOverlayInvoke(code, SIM_SLOT, map,  new USSDController.CallbackInvoke() {
                @Override
                public void responseInvoke(String message) {
                    AppLogger.e(">>>>  1st level APP", message);
                }

                @Override
                public void over(String message) {
                    KeyboardUtils.hideSoftInput((Activity) mContext);

                    AppLogger.e("APP >>> Over ", message);
                    AppLogger.e("APP", "STOP OVERLAY DIALOG");

                    if(message.toLowerCase().startsWith("you have requested to pay "))
                        issuePaymentThroughUssd();
                    else {
                        mContext.stopService(svc);
                        CommonUtils.showAlertDialog(builder, true, "Payment unsuccessful", message,
                                (d1, w) -> d1.dismiss(), "OK", null, "", 0);

                    }
                }
            });
        }

    }



    void issuePaymentThroughUssd(){
        String momoServiceCode = "*170"+ Uri.encode("#");

        ussdApi.callUSSDOverlayInvoke(momoServiceCode, SIM_SLOT, map,  new USSDController.CallbackInvoke() {
            @Override
            public void responseInvoke(String message) {
                AppLogger.e(">>>>  1st level APP", "\n"+ message);

                if(message.toLowerCase().contains("enter pin")){
                    ussdApi.send(MOMO_PIN, m -> {
                        AppLogger.e(">>>> PIN entered, Next steps ...", m);

                        ussdApi.send("1", m2 -> {
                            AppLogger.e(">>>>  Select to approve, entered 1 ", m2);

                               /* ussdApi.send("1", m3 -> {
                                    AppLogger.e(">>>>  Approved payment! >> entered 1", m3);
                                });*/

                        });

                    });
                    return;

                }else if(message.toLowerCase().contains("invalid mmi code") || message.toLowerCase().contains("incorrect mobile money pin")
                        || message.toLowerCase().contains("transaction failed") || message.toLowerCase().contains("you have no pending approvals")){
                    this.over(message);
                    return;

                } else if(message.contains("1) Yes\n2) No\n0) Back")){
                    ussdApi.send("1", m3 -> {
                        AppLogger.e(">>>>  Approve payments confirmation >> entered 1", m3);
                    });
                    return;
                }


                if(!isInitiated)
                    // first option list - select option 1
                    ussdApi.send("6", m1 -> {
                        isInitiated = true;
                        AppLogger.e(">>>> Entered option 7", m1);


                        ussdApi.send("3", m2 -> {
                            AppLogger.e(">>>> Entered option 3", m2);
                        });
                    });

            }

            @Override
            public void over(String overMessage) {
                KeyboardUtils.hideSoftInput((Activity) mContext);

                AppLogger.e("OVER", overMessage);
                mContext.stopService(svc);
                AppLogger.e("OVER", "STOP OVERLAY DIALOG");


                String dialogMessage;

                if(overMessage.toLowerCase().contains("you have no pending approvals") || overMessage.toLowerCase().contains("transaction failed"))
                    dialogMessage =  overMessage +"\nPlease ensure you have enough funds in your wallet.";
                else
                    dialogMessage = overMessage;

                CommonUtils.showAlertDialog(new AlertDialog.Builder(mContext, R.style.AppAlertDialog), true, "Message", dialogMessage,
                        (d, w) -> {
                            d.dismiss();

                            if(overMessage.toLowerCase().contains("you have approved the debit transaction of")){

                                String newMessage = overMessage.replace(" ", "").trim();

                                try{

                                    String[] values = newMessage.split(".");
                                    String transactionId = values[2].replace("TransactionID", "").trim();

                                    AppLogger.e(TAG, "THE TRANSACTION ID IS >>>>> " + transactionId);


                                    if(ussdListener != null) {
                                        ussdListener.onUssdPaymentSuccess(transactionId);
                                        AppLogger.e(TAG, ">>>>> USSD LISTENER IS NOT NULL ");


                                    }else
                                        AppLogger.e(TAG, ">>>>> USSD LISTENER IS NULL ");


                                }catch (Exception e){
                                    e.printStackTrace();

                                    if(ussdListener != null) {
                                        ussdListener.onUssdPaymentSuccess(UUID.randomUUID().toString());
                                        AppLogger.e(TAG, ">>>>> USSD LISTENER IS NOT NULL ");

                                    }
                                }finally {
                                    clearUssdListener();

                                }
                                AppLogger.e("OVER", "PAYMENT SESSION COMPLETE!");
                            }
                        }, "OK", null, "", 0);

                isInitiated = false;
            }
        });


    }



    public void showSelectSimDialog(String code){


        builder.setTitle("Choose an option");
        builder.setSingleChoiceItems(new String[]{ "SIM 1", "SIM 2"}, -1, (dialogInterface, i) -> {

            dialogInterface.dismiss();

            SIM_SLOT = i;

            showEnterPinDialog(code);

        });

        AlertDialog mDialog = builder.create();
        mDialog.show();
    }


    public void showMessage(String message) {
        CustomToast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }


}
