package ru.profitsw2000.firebasefirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Logging"  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://fir-firestoreproject-6374c-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference mDbRef = mDatabase.getReference("Donor/Name");
        mDbRef.setValue("Parinitha");
    }
}