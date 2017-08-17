package com.cs496.smithada.todolist;

public class ListItem {

    public String toDoText;
    public boolean repeats;
    public boolean repeatsWeekly;
    public boolean mon;
    public boolean tues;
    public boolean wed;
    public boolean thurs;
    public boolean fri;
    public boolean sat;
    public boolean sun;
    public boolean done;

    public ListItem(){
        //default constructor
    }

    public ListItem(String toDoText, boolean repeats, boolean repeatsWeekly, boolean mon, boolean tues,
                    boolean wed, boolean thurs, boolean fri, boolean sat, boolean sun){
        this.toDoText = toDoText;
        this.repeats = repeats;
        this.repeatsWeekly = repeatsWeekly;
        this.mon = mon;
        this.tues = tues;
        this.wed = wed;
        this.thurs = thurs;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.done = false;
    }
}
