package ru.profitsw2000.firebasefirestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment with registration/signin form.
 */
public class LoginFragment extends Fragment {

    private View rootView   ;
    Button regButton, signinButton  ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initUI()    ;
        return rootView ;
    }

    private void initUI() {
        signinButton = rootView.findViewById(R.id.editEmail)    ;
        regButton = rootView.findViewById(R.id.editPassword)    ;

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}