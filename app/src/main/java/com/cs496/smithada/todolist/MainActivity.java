package com.cs496.smithada.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cs496.smithada.todolist.R.id.radioButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mUserTextView;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    //private DataSnapshot mDataSnapshot;
    private ValueEventListener mUserListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, ToDoItem.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_signout) {
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, Auth.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser == null){
            //go do auth
            //start an intent to open the auth view
            Intent intent = new Intent(MainActivity.this, Auth.class);
            startActivity(intent);
        }
        else {
            //Initialize database
            mDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(currentUser.getUid());

            mUserTextView = (TextView) findViewById(R.id.user);
            mUserTextView.setText(currentUser.getUid());

            ValueEventListener userListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    //System.out.println("userId " + user.userId);
                    System.out.println("user " + user.userId);
                    System.out.println("User's List: " + user.toDoItems);
                    System.out.println("typeOf user: " + user.getClass().getName());
                    System.out.println("typeOf user.toDoItems.get(0) " + user.toDoItems.get(0).getClass().getName());

                    System.out.println("typeof user.getToDoItems() " + user.getToDoItems().getClass().getName());
                    System.out.println("Note text: " + user.toDoItems.get(0).toDoText);


                    List<String> list = new ArrayList<String>();
                    List<Map<String,String>> n = new ArrayList<Map<String,String>>();

                    for (int i = 0; i < user.toDoItems.size(); i++){
                        HashMap<String, String> m = new HashMap<String, String>();
                        m.put("item", user.toDoItems.get(i).toDoText);
                        n.add(m);
                    }

                    //display the data
                    final SimpleAdapter postAdapter = new SimpleAdapter(
                            MainActivity.this,
                            n,
                            R.layout.todo_list_item,
                            new String[]{"item"},
                            new int[]{radioButton});
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ListView)findViewById(R.id.todo_list)).setAdapter(postAdapter);
                        }
                    });
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
        }
    }
    // [END on_start_check_user]

    @Override
    public void onStop(){
        super.onStop();

        if (mUserListener != null){
            mDatabase.removeEventListener(mUserListener);
        }
    }

    public void radioButtonClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        System.out.println("Checked: " + ((RadioButton) view).isChecked());
        /*if (((RadioButton) view).isChecked() == true){
            ((RadioButton) view).setChecked(false);
        }*/
        System.out.println(((RadioButton) view).getText());
    }
}
