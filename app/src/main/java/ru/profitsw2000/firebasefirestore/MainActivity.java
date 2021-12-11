package ru.profitsw2000.firebasefirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public Navigation navigation   ;
    private FirebaseAuth firebaseAuth   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = new Navigation(getSupportFragmentManager())    ;
        firebaseAuth = FirebaseAuth.getInstance()   ;

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser()   ;
        if (firebaseUser != null) {
            StartFragment startFragment = new StartFragment()   ;
            navigation.addFragment(startFragment, R.id.main_frame, false);
            Toast.makeText(this, "Not null", Toast.LENGTH_LONG).show();
        } else {
            LoginFragment loginFragment = new LoginFragment()   ;
            navigation.addFragment(loginFragment, R.id.main_frame, false);
            Toast.makeText(this, "Null", Toast.LENGTH_LONG).show();
        }
    }
}