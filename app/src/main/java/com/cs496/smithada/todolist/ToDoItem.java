package com.cs496.smithada.todolist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;
import static com.cs496.smithada.todolist.R.styleable.MenuItem;

public class ToDoItem extends AppCompatActivity {

    //buttons and switches
    private Switch repeatTaskSwitch;
    private EditText todoItemTextField;
    private ToggleButton mondayButton;
    private ToggleButton tuesdayButton;
    private ToggleButton wednesdayButton;
    private ToggleButton thursdayButton;
    private ToggleButton fridayButton;
    private ToggleButton saturdayButton;
    private ToggleButton sundayButton;
    private Switch repeatWeeklySwitch;

    //variables to hold user selected values
    private String mToDoItem;
    private boolean repeatTask = false;
    private boolean monday = false;
    private boolean tuesday = false;
    private boolean wednesday = false;
    private boolean thursday = false;
    private boolean friday= false;
    private boolean saturday = false;
    private boolean sunday = false;
    private boolean repeatWeekly = false;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private ValueEventListener mUserListener;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //enable up action in toolbar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        //get all the view options from the activity view
        repeatTaskSwitch = (Switch) findViewById(R.id.switch_repeat_days);
        todoItemTextField = (EditText) findViewById(R.id.todo_item);
        mondayButton = (ToggleButton) findViewById(R.id.toggle_mon);
        tuesdayButton = (ToggleButton) findViewById(R.id.toggle_tues);
        wednesdayButton = (ToggleButton) findViewById(R.id.toggle_wed);
        thursdayButton = (ToggleButton) findViewById(R.id.toggle_thurs);
        fridayButton = (ToggleButton) findViewById(R.id.toggle_fri);
        saturdayButton = (ToggleButton) findViewById(R.id.toggle_sat);
        sundayButton = (ToggleButton) findViewById(R.id.toggle_sun);
        repeatWeeklySwitch = (Switch) findViewById(R.id.switch_repeat_weekly);

        //add a listener to the switch to toggle other options display
        repeatTaskSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                handleOptionsDisplay(isChecked);

                if (isChecked == true){
                    repeatTask = true;

                    mondayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean monChecked) {
                            //if monday is checked
                            if (monChecked == true){
                                monday = true;
                            }
                            else{
                                monday = false;
                            }
                        }
                    });
                    tuesdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean tuesChecked) {
                            //if tuesday is checked
                            if (tuesChecked == true){
                                tuesday = true;
                            }
                            else{
                                tuesday = false;
                            }
                        }
                    });
                    wednesdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean wedChecked) {
                            //if wed is checked
                            if (wedChecked == true){
                                wednesday = true;
                            }
                            else{
                                wednesday = false;
                            }
                        }
                    });
                    thursdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean thursChecked) {
                            //if thurs is checked
                            if (thursChecked == true){
                                thursday = true;
                            }
                            else{
                                thursday = false;
                            }
                        }
                    });
                    fridayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean friChecked) {
                            //if fri is checked
                            if (friChecked == true){
                                friday = true;
                            }
                            else{
                                friday = false;
                            }
                        }
                    });
                    saturdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean satChecked) {
                            //if sat is checked
                            if (satChecked == true){
                                saturday = true;
                            }
                            else{
                                saturday = false;
                            }
                        }
                    });
                    sundayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean sunChecked) {
                            //if sun is checked
                            if (sunChecked == true){
                                sunday = true;
                            }
                            else{
                                sunday = false;
                            }
                        }
                    });
                    repeatWeeklySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean repeatWeeklyChecked) {
                            //if repeat weekly is checked
                            if (repeatWeeklyChecked == true){
                                repeatWeekly = true;
                            }
                            else{
                                repeatWeekly = false;
                            }
                        }
                    });
                }
                else{
                    repeatTask = false;

                    //in case user made selections, then de-selected repeatTaskSwitch, set all options to false
                    monday = false;
                    tuesday = false;
                    wednesday = false;
                    thursday = false;
                    friday = false;
                    saturday = false;
                    sunday = false;
                    repeatWeekly = false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do_item_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // User chose the "Done" item, store entry in database...
            case R.id.done:

                //System.out.println("This is how many times the swtich is running");

                final FirebaseUser currentUser = mAuth.getCurrentUser();
                //System.out.println(currentUser.getUid());

                //Initialize database
                mDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(currentUser.getUid());

                ValueEventListener userListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        //System.out.println("This is how many times the even listener is running");
                        System.out.println("user returned" + user.userId);

                        //get user entered string
                        mToDoItem = todoItemTextField.getText().toString();

                        System.out.println("Test photo path should be null: " + mCurrentPhotoPath);

                        //make new list object
                        ListItem mListItem = new ListItem(mToDoItem, repeatTask, repeatWeekly, monday, tuesday, wednesday,
                                thursday, friday, saturday, sunday, mCurrentPhotoPath);

                        //add new list item to the user object
                        user.addItem(mListItem);

                        //update user's list in the database
                        mDatabase.child("toDoItems").setValue(user.getToDoItems());
                        //onStop();
                        performDoneAction();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("ERROR " + databaseError.toException());
                    }
                };
                mDatabase.addValueEventListener(userListener);
                // [END user listener]

                // Keep copy of post listener so we can remove it when app stops
                mUserListener = userListener;

            return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    //if switch is checked show other stuff
    private void handleOptionsDisplay(boolean isChecked){
        if (isChecked == true){
            findViewById(R.id.toggle_mon).setVisibility(View.VISIBLE);
            findViewById(R.id.toggle_tues).setVisibility(View.VISIBLE);
            findViewById(R.id.toggle_wed).setVisibility(View.VISIBLE);
            findViewById(R.id.toggle_thurs).setVisibility(View.VISIBLE);
            findViewById(R.id.toggle_fri).setVisibility(View.VISIBLE);
            findViewById(R.id.toggle_sat).setVisibility(View.VISIBLE);
            findViewById(R.id.toggle_sun).setVisibility(View.VISIBLE);
            findViewById(R.id.text_repeat_weekly).setVisibility(View.VISIBLE);
            findViewById(R.id.switch_repeat_weekly).setVisibility(View.VISIBLE);
        }
        else {
            findViewById(R.id.toggle_mon).setVisibility(View.GONE);
            findViewById(R.id.toggle_tues).setVisibility(View.GONE);
            findViewById(R.id.toggle_wed).setVisibility(View.GONE);
            findViewById(R.id.toggle_thurs).setVisibility(View.GONE);
            findViewById(R.id.toggle_fri).setVisibility(View.GONE);
            findViewById(R.id.toggle_sat).setVisibility(View.GONE);
            findViewById(R.id.toggle_sun).setVisibility(View.GONE);
            findViewById(R.id.text_repeat_weekly).setVisibility(View.GONE);
            findViewById(R.id.switch_repeat_weekly).setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop(){
        super.onStop();

        if (mUserListener != null){
            mDatabase.removeEventListener(mUserListener);
        }
        //finish();
    }

    private void performDoneAction(){
        if (mUserListener != null){
            mDatabase.removeEventListener(mUserListener);
        }
        finish();
    }

    public void onImageButtonClick(View view){
        TextView imageButtonText = (TextView) findViewById(R.id.add_image);
        imageButtonText.setText("image added");

        //Now take a picture
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo will go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            }
            catch (IOException ex){
                //Error occured while creating the File
            }

            if (photoFile != null){
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.cs496.smithada.todolist",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    // Display the image thumbnail
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView image = (ImageView) findViewById(R.id.returned_image);

        //get a bitmap and display
        image.setImageBitmap(getbitpam(mCurrentPhotoPath));
    }

    //create the file for the image to be taken
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        //Also save this to the database for later
        mCurrentPhotoPath = image.getAbsolutePath();
        System.out.println("Photo file path: " + mCurrentPhotoPath);
        return image;
    }

    //creates a bitmap of photo taken to be displayed as thumbnail
    public Bitmap getbitpam(String path){
        Bitmap imgthumBitmap=null;
        try
        {

            final int THUMBNAIL_SIZE = 128;

            FileInputStream fis = new FileInputStream(path);
            imgthumBitmap = BitmapFactory.decodeStream(fis);

            imgthumBitmap = Bitmap.createScaledBitmap(imgthumBitmap,
                    THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);

            ByteArrayOutputStream bytearroutstream = new ByteArrayOutputStream();
            imgthumBitmap.compress(Bitmap.CompressFormat.JPEG, 100,bytearroutstream);


        }
        catch(Exception ex) {

        }
        return imgthumBitmap;
    }
}
