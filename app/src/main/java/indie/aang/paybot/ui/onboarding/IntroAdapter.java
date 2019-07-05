package indie.aang.paybot.ui.onboarding;

import android.graphics.Color;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import indie.aang.paybot.R;

public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position){

            case 0:
                return IntroFragment.newInstance("Hi there!", "This is PayBot\n PayBot is meant to facilitate the way you make payments using any USSD service on any network. It does the heavy lifting for us!", R.drawable.ic_logo_no_background, position);

            case 1:
                return IntroFragment.newInstance("No internet connection required!", "PayBot overlays over USSD service. You don't need internet connectivity to make payments", R.drawable.ic_logo_no_background, position);

            case 2:
                return IntroFragment.newInstance("Secure", "PayBot is secure. We do not save your password/pins neither do we share your information with any third parties.", R.drawable.ic_logo_no_background, position);

                default:
                    return IntroFragment.newInstance("Hi there!", "This is PayBot\n PayBot is meant to facilitate the way you make payments using any USSD service on any network. It does the heavy lifting for us!", R.drawable.ic_logo_no_background, position);
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

}