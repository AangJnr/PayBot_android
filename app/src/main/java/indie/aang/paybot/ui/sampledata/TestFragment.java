package indie.aang.paybot.ui.sampledata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import indie.aang.paybot.R;
import indie.aang.paybot.ui.ussdService.OverlayShowingService;
import indie.aang.paybot.utilities.AppLogger;
import com.romellfudi.ussdlibrary.SplashLoadingService;
import com.romellfudi.ussdlibrary.USSDApi;
import com.romellfudi.ussdlibrary.USSDController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Use Case for Test Windows
 *
 * @author Romell Domínguez
 * @version 1.1.b 27/09/2018
 * @since 1.0.a
 */
public class TestFragment extends Fragment {

    private TextView result;
    private EditText phone;
    private Button btn1, btn2, btn3, btn4;
    private HashMap<String, HashSet<String>> map;
    private USSDApi ussdApi;



    public static TestFragment newInstance() {

        return new TestFragment();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        map = new HashMap<>();
        map.put("KEY_LOGIN", new HashSet<>(Arrays.asList("Enter pin", "pin")));
        map.put("KEY_RETRY", new HashSet<>(Arrays.asList("Retry", "problem")));
        map.put("KEY_ERROR", new HashSet<>(Arrays.asList("error", "null")));
        map.put("KEY_COMPLETE", new HashSet<>(Arrays.asList("successful", "done")));

        ussdApi = USSDController.getInstance(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_op1, container, false);
        result = (TextView) view.findViewById(R.id.result);
        phone = (EditText) view.findViewById(R.id.phone);
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        setHasOptionsMenu(false);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phone.getText().toString().trim();
                ussdApi = USSDController.getInstance(getActivity());
                result.setText("");
                ussdApi.callUSSDInvoke(phoneNumber, map, new USSDController.CallbackInvoke() {
                    @Override
                    public void responseInvoke(String message) {
                        AppLogger.e("APP", message);
                        result.append("\n-\n" + message);
                        // first option list - select option 1
                        ussdApi.send("1", new USSDController.CallbackMessage() {
                            @Override
                            public void responseMessage(String message) {
                                AppLogger.e("APP", message);
                                result.append("\n-\n" + message);
                                // second option list - select option 1
                                ussdApi.send("1", new USSDController.CallbackMessage() {
                                    @Override
                                    public void responseMessage(String message) {
                                        AppLogger.e("APP", message);
                                        result.append("\n-\n" + message);
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void over(String message) {
                        AppLogger.e("APP", message);
                        result.append("\n-\n" + message);
                    }
                });
            }
        });




        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (USSDController.verifyOverLay(getActivity())) {
                    final Intent svc = new Intent(getActivity(), OverlayShowingService.class);
                    svc.putExtra(OverlayShowingService.EXTRA, "Processing...");

                    getActivity().startService(svc);


                    AppLogger.d("APP", "START OVERLAY DIALOG");

                    String phoneNumber = phone.getText().toString().trim();


                    ussdApi = USSDController.getInstance(getActivity());
                    result.setText("");
                    ussdApi.callUSSDOverlayInvoke(phoneNumber, 1, map,  new USSDController.CallbackInvoke() {
                        @Override
                        public void responseInvoke(String message) {
                            AppLogger.e(">>>>  1st level APP", message);
                            result.append("\n-\n" + message);

                            if(message.toLowerCase().contains("enter pin")){
                                this.over(message);
                                return;
                            }else if(message.toLowerCase().contains("invalid mmi code")){
                                this.over(message);


                                return;
                            }else if(message.toLowerCase().contains("requested to pay")){
                                this.over(message);
                                return;
                            }

                            // first option list - select option 1
                            ussdApi.send("7", new USSDController.CallbackMessage() {
                                @Override
                                public void responseMessage(String message) {
                                    AppLogger.e(">>>>  2nd level APP", message);
                                    result.append("\n-\n" + message);


                                    ussdApi.send("1", new USSDController.CallbackMessage() {
                                        @Override
                                        public void responseMessage(String message) {
                                            AppLogger.e(">>>>  3rd level APP", message);
                                            result.append("\n-\n" + message);

                                            getActivity().stopService(svc);
                                        }
                                    });
                                }
                            });




                        }

                        @Override
                        public void over(String message) {
                            AppLogger.e("APP", message);
                            result.append("\n-\n" + message);
                            getActivity().stopService(svc);
                            AppLogger.e("APP", "STOP OVERLAY DIALOG");
                        }
                    });
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (USSDController.verifyOverLay(getActivity())) {
                    final Intent svc = new Intent(getActivity(), SplashLoadingService.class);
                    getActivity().startService(svc);
                    Log.d("APP", "START SPLASH DIALOG");
                    String phoneNumber = phone.getText().toString().trim();
                    result.setText("");
                    ussdApi.callUSSDOverlayInvoke(phoneNumber, map, new USSDController.CallbackInvoke() {
                        @Override
                        public void responseInvoke(String message) {
                            Log.d("APP", message);
                            result.append("\n-\n" + message);
                            // first option list - select option 1
                            ussdApi.send("1", new USSDController.CallbackMessage() {
                                @Override
                                public void responseMessage(String message) {
                                    Log.d("APP", message);
                                    result.append("\n-\n" + message);
                                    // second option list - select option 1
                                    ussdApi.send("1", new USSDController.CallbackMessage() {
                                        @Override
                                        public void responseMessage(String message) {
                                            Log.d("APP", message);
                                            result.append("\n-\n" + message);
                                            getActivity().stopService(svc);
                                            Log.d("APP", "STOP SPLASH DIALOG");
                                            Log.d("APP", "successful");
                                        }
                                    });
                                }
                            });
                        }

                        @Override
                        public void over(String message) {
                            Log.d("APP", message);
                            result.append("\n-\n" + message);
                            getActivity().stopService(svc);
                            Log.d("APP", "STOP OVERLAY DIALOG");
                        }
                    });
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USSDController.verifyAccesibilityAccess(getActivity());
            }
        });

        return view;
    }

}

