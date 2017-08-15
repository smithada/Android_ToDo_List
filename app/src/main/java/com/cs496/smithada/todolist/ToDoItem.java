package com.cs496.smithada.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.ToggleButton;
import android.view.MenuItem;

import static com.cs496.smithada.todolist.R.styleable.MenuItem;

public class ToDoItem extends AppCompatActivity {

    private Switch repeatTask;
    private EditText todoItem;
    private ToggleButton monday;
    private ToggleButton tuesday;
    private ToggleButton wednesday;
    private ToggleButton thursday;
    private ToggleButton friday;
    private ToggleButton saturday;
    private ToggleButton sunday;
    private Switch repeatWeekly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //get all the view options from the activity view
        repeatTask = (Switch) findViewById(R.id.switch_repeat_days);
        todoItem = (EditText) findViewById(R.id.todo_item);
        monday = (ToggleButton) findViewById(R.id.toggle_mon);
        tuesday = (ToggleButton) findViewById(R.id.toggle_tues);
        wednesday = (ToggleButton) findViewById(R.id.toggle_wed);
        thursday = (ToggleButton) findViewById(R.id.toggle_thurs);
        friday = (ToggleButton) findViewById(R.id.toggle_fri);
        saturday = (ToggleButton) findViewById(R.id.toggle_sat);
        sunday = (ToggleButton) findViewById(R.id.toggle_sun);
        repeatWeekly = (Switch) findViewById(R.id.switch_repeat_weekly);

        //add a listener to the switch to toggle other options display
        repeatTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                handleOptionsDisplay(isChecked);

                if (isChecked == true){
                    monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            //if monday is checked
                        }
                    });
                    tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            //if tuesday is checked
                        }
                    });
                    wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            //if wed is checked
                        }
                    });
                    thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            //if thurs is checked
                        }
                    });
                    friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            //if fri is checked
                        }
                    });
                    saturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            //if sat is checked
                        }
                    });
                    sunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            //if sun is checked
                        }
                    });
                    repeatWeekly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            //if repeat weekly is checked
                        }
                    });
                }
            };
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
            case R.id.done:
                // User chose the "Settings" item, show the app settings UI...
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
}
