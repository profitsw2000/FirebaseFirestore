package ru.profitsw2000.firebasefirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager ;
    FragmentTransaction fragmentTransaction ;
    Navigation navigation   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = new Navigation(getSupportFragmentManager())    ;
        InputFormFragment inputFormFragment = new InputFormFragment()   ;

        navigation.addFragment(inputFormFragment, R.id.input_form, false);
    }
}