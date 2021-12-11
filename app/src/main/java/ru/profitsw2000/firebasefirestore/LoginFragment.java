package ru.profitsw2000.firebasefirestore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Fragment with registration/signin form.
 */
public class LoginFragment extends Fragment {

    private View rootView   ;
    private Button regButton, signinButton  ;
    private EditText edEmail, edPassword    ;
    private FirebaseAuth firebaseAuth   ;
    private Navigation navigation   ;

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
        signinButton = rootView.findViewById(R.id.button_signin)    ;
        regButton = rootView.findViewById(R.id.button_register)    ;
        edEmail = rootView.findViewById(R.id.editEmail) ;
        edPassword = rootView.findViewById(R.id.editPassword)   ;
        firebaseAuth = FirebaseAuth.getInstance()   ;
        navigation = new Navigation(getActivity().getSupportFragmentManager())  ;

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(edEmail.getText().toString().isEmpty()) && !(edPassword.getText().toString().isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(edEmail.getText().toString(), edPassword.getText().toString()).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "User log in succesfully!", Toast.LENGTH_LONG).show();
                                        StartFragment startFragment = new StartFragment()   ;
                                        navigation.addFragment(startFragment,R.id.main_frame,false);
                                    }
                                    else {
                                        edEmail.getText().clear();
                                        edPassword.getText().clear();
                                        Toast.makeText(getContext(), "Account login failed!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(getContext(), "Error! Empty fields.", Toast.LENGTH_LONG).show();
                }
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(edEmail.getText().toString().isEmpty()) && !(edPassword.getText().toString().isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(edEmail.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "User was registered succesfully!", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getContext(), "User registration failed!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Failure", e.getLocalizedMessage());
                        }
                    })  ;
                }
                else {
                    Toast.makeText(getContext(), "Error! Empty fields.", Toast.LENGTH_LONG).show();
                }
                edEmail.getText().clear();
                edPassword.getText().clear();
            }
        });
    }
}