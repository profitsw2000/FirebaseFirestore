package ru.profitsw2000.firebasefirestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment to start with application. It contain two buttons to select
 * either to write info to Firebase or read from it.
 */
public class StartFragment extends Fragment {

    private View rootView   ;
    private Navigation navigation   ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_start, container, false);
        initUI()    ;
        return rootView ;
    }

    private void initUI() {

    }
}