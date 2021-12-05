package ru.profitsw2000.firebasefirestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Fragment with form used for adding info to database.
 */
public class InputFormFragment extends Fragment {

    private String KEY = "footbal_club" ;

    private View rootView   ;
    private Button addButton    ;
    private EditText clubEdit, cityEdit, countryEdit    ;
    private DatabaseReference databaseReference  ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_input_form, container, false);
        initUI();
        return rootView ;
    }

    /**
     * Method for initialization fragment UI.
     */
    private void initUI(){
        addButton = rootView.findViewById(R.id.button_add_club_info)    ;
        clubEdit = rootView.findViewById(R.id.editClub) ;
        cityEdit = rootView.findViewById(R.id.editCity) ;
        countryEdit = rootView.findViewById(R.id.editCountry)   ;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://fir-firestoreproject-6374c-default-rtdb.europe-west1.firebasedatabase.app/")   ;
        databaseReference = firebaseDatabase.getReference(KEY)  ;

        //click on button ADD NEW CLUB
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get id and club info from form
                String id = databaseReference.getKey();
                String club = clubEdit.getText().toString() ;
                String city = cityEdit.getText().toString() ;
                String country = countryEdit.getText().toString() ;

                //if form is not empty create new instance of FC class and write it to realtime database
                if (!(club.isEmpty()) && !(city.isEmpty()) && !(country.isEmpty())) {
                    FootballClub footballClub = new FootballClub(id, club, city, country)   ;
                    databaseReference.push().setValue(footballClub) ;
                }
                else {
                    Toast.makeText(getContext(), "There is empty fields!!!", Toast.LENGTH_SHORT).show();
                }

                //clear form
                clubEdit.getText().clear();
                cityEdit.getText().clear();
                countryEdit.getText().clear();
            }
        });
    }
}