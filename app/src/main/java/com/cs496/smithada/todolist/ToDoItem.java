package com.cs496.smithada.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.ToggleButton;

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

        //get the first switch in this view
        repeatTask = (Switch) findViewById(R.id.switch_repeat_days);

        //add a listener to the switch to toggle other options display
        repeatTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

                handleOptionsDisplay(isChecked);
            };
        });
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
