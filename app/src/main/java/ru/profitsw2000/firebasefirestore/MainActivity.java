package ru.profitsw2000.firebasefirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public Navigation navigation   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = new Navigation(getSupportFragmentManager())    ;

        StartFragment startFragment = new StartFragment()   ;
        navigation.addFragment(startFragment, R.id.main_frame, false);
    }
}