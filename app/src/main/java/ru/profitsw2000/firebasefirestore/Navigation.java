package ru.profitsw2000.firebasefirestore;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/*
Class used for starting new fragment.
 */
public class Navigation {

    private final FragmentManager fragmentManager   ;

    public Navigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void addFragment(Fragment fragment, int container, boolean useBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()    ;
        fragmentTransaction.replace(container, fragment)    ;
        if (useBackStack) {
            fragmentTransaction.addToBackStack(null)    ;
        }
        fragmentTransaction.commit()    ;
    }
}
