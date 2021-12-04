package ru.profitsw2000.firebasefirestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Fragment with input form to add new football club info. Info added to database Firebase.
 */
public class InputFormFragment extends Fragment {

    private String FOOTBALL_CLUB_KEY = "football club"  ;

    private Button addButton    ;
    private EditText clubEdit, cityEdit, countryEdit    ;
    private View rootView   ;
    private DatabaseReference fireBase  ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_input_form, container, false);
        initUI();
        return rootView ;
    }

    private void initUI() {
        addButton = rootView.findViewById(R.id.button)  ;
        clubEdit = rootView.findViewById(R.id.input_club)   ;
        cityEdit = rootView.findViewById(R.id.input_city)   ;
        countryEdit = rootView.findViewById(R.id.input_country) ;
        fireBase = FirebaseDatabase.getInstance().getReference(FOOTBALL_CLUB_KEY)    ;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = fireBase.getKey()   ;
                String club = clubEdit.getText().toString()   ;
                fireBase.push().setValue(club)  ;
            }
        });
    }
}