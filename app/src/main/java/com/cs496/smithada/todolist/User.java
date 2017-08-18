package com.cs496.smithada.todolist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by smithada on 8/15/17.
 */

public class User {

    public String userId;
    public List<ListItem> toDoItems = new ArrayList<ListItem>();

    public User() {
        //default constructor
    }

    public User(String userId){
        this.userId = userId;
        this.toDoItems = null;
    }

    public void addItem(ListItem item){
        toDoItems.add(item);
    }

    /*public String getUserId(){
        return userId;
    }
    */
    public List<ListItem> getToDoItems(){
        return toDoItems;
    }
}
