package com.cs496.smithada.todolist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ManageUserDetails extends AppCompatActivity {

    //used for nav drawer
    private TextView userName;

    //private FirebaseAuth mAuth;
    //private Context context = getApplicationContext();
    private CharSequence text = "User update successful";
    private CharSequence error = "Error updating user";
    private int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_details);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){

            //must declar a final varible to be accessed deeper in scope
            final FirebaseUser localUser = user;

            Button button = (Button) findViewById(R.id.update_name);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    EditText editText = (EditText) findViewById(R.id.name_text);

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(editText.getText().toString())
                            //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                            .build();

                    localUser.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(ManageUserDetails.this, MainActivity.class);
                                        startActivity(intent);
                                        //Toast.makeText(context, text, duration).show();
                                    }
                                }
                            });
                }
            });
        }




                // [START initialize_auth]
        //mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        /*
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            //must declar a final varible to be accessed deeper in scope
            final FirebaseUser localUser = user;

            String name = user.getDisplayName();
            if (name == null){
                Button button = (Button) findViewById(R.id.update_name);
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        EditText editText = (EditText) findViewById(R.id.name_text);

                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(editText.getText().toString())
                                //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                                .build();

                        localUser.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            Toast.makeText(context, text, duration).show();
                                        }
                                    }
                                });
                    }
                });
            }
        }
        else {
            Toast.makeText(context, error, duration).show();
        }

        Intent intent = new Intent(ManageUserDetails.this, MainActivity.class);
        startActivity(intent);*/
    }
}
