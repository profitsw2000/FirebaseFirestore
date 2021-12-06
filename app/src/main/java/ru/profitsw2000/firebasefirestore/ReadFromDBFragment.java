package ru.profitsw2000.firebasefirestore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A frament, that started when user pressed read button in main fragment.
 * This fragment contain listView with data from realtime database.
 */
public class ReadFromDBFragment extends Fragment {

    private View rootView   ;
    private ListView listView   ;
    private FootballClubAdapter footballClubAdapter ;
    private List<FootballClub> list ;
    private DatabaseReference databaseReference ;
    private String KEY = "footbal_club" ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_read_from_d_b, container, false);
        initUI();
        getDataFromDB();
        return rootView ;
    }

    private void initUI() {
        listView = (ListView) rootView.findViewById(R.id.list_from_database)    ;
        list = new ArrayList<>()    ;
        footballClubAdapter = new FootballClubAdapter(getContext(), list)   ;
        listView.setAdapter(footballClubAdapter);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://fir-firestoreproject-6374c-default-rtdb.europe-west1.firebasedatabase.app/")   ;
        databaseReference = firebaseDatabase.getReference(KEY)  ;

    }

    private void getDataFromDB() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list.size() > 0) list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    FootballClub footballClub = dataSnapshot.getValue(FootballClub.class)   ;
                    assert footballClub != null ;
                    list.add(footballClub)  ;
                }
                footballClubAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener) ;
    }
}