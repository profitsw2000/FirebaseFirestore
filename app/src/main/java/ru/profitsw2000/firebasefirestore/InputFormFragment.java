package ru.profitsw2000.firebasefirestore;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

/**
 * Fragment with form used for adding info to database.
 */
public class InputFormFragment extends Fragment {

    private String KEY = "footbal_club" ;
    private String STORAGE_KEY = "FC Logos" ;

    private View rootView   ;
    private Button addButton    ;
    private EditText clubEdit, cityEdit, countryEdit    ;
    private ImageView logoImage ;
    private DatabaseReference databaseReference  ;
    private StorageReference storageReference   ;
    private Uri uri ;

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
        logoImage = rootView.findViewById(R.id.imageView)   ;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://fir-firestoreproject-6374c-default-rtdb.europe-west1.firebasedatabase.app/")   ;
        databaseReference = firebaseDatabase.getReference(KEY)  ;
        storageReference = FirebaseStorage.getInstance().getReference(STORAGE_KEY)   ;

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/fir-firestoreproject-6374c.appspot.com/o/FC%20Logos%2F1639332586103fc_logo?alt=media&token=a1fbed4c-e0ca-4ba4-bce4-66dad7b6df78").into(logoImage);

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
                if (!(club.isEmpty()) && !(city.isEmpty()) && !(country.isEmpty()) && (uri != null)) {
                    uploadImage();
                    String fc_logo = uri.toString() ;
                    Log.d("Img", fc_logo)   ;
                    FootballClub footballClub = new FootballClub(id, club, city, country, fc_logo)   ;
                    databaseReference.push().setValue(footballClub) ;
                    Toast.makeText(getContext(), "New club info added to database!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "There is empty fields!!!", Toast.LENGTH_SHORT).show();
                }

                //clear form
                clubEdit.getText().clear();
                cityEdit.getText().clear();
                countryEdit.getText().clear();
                logoImage.setImageResource(R.drawable.ic_launcher_background);
            }
        });

        logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
    }

    private void getImage() {
        Intent intent = new Intent()    ;
        intent.setType("image/*")   ;
        intent.setAction(Intent.ACTION_GET_CONTENT) ;
        activityResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                   if (result.getData() != null){
                       if (result.getResultCode() == RESULT_OK){
                           uri = result.getData().getData()   ;
                           Log.d("Intent_log", "Image URI: " + uri)    ;
                           logoImage.setImageURI(uri);
                       } 
                   }
                }
            });

    private void uploadImage()
    {
        Bitmap bitmap = ((BitmapDrawable) logoImage.getDrawable()).getBitmap()  ;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()   ;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,byteArrayOutputStream)  ;
        byte[] bytes = byteArrayOutputStream.toByteArray()  ;
        final StorageReference myRef = storageReference.child(System.currentTimeMillis() + "fc_logo")   ;
        UploadTask uploadTask = myRef.putBytes(bytes)   ;

        Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return myRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                uri = task.getResult()  ;
            }
        });
    }
}