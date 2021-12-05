package ru.profitsw2000.firebasefirestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment to start with application. It contain two buttons to select
 * either to write info to Firebase or read from it.
 */
public class StartFragment extends Fragment {

    private View rootView   ;
    private Navigation navigation   ;
    Button writeButton, readButton  ;
    FragmentManager fragmentManager ;

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
        writeButton = (Button) rootView.findViewById(R.id.button_write) ;

        navigation = new Navigation(getActivity().getSupportFragmentManager())  ;

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputFormFragment inputFormFragment = new InputFormFragment()   ;

                navigation.addFragment(inputFormFragment, R.id.main_frame, true);
            }
        });
    }
}