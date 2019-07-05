package indie.aang.paybot.utilities;


import android.net.Uri;

import androidx.fragment.app.Fragment;

public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);


    void toggleBottomNavigation(boolean shouldShow, int selectedItem);

    void showDrawerMenu(int... menusToShow);

    void hideDrawerMenu(int... menusToHide);



    void loadFragment(Fragment fragment);

}



