package com.yuvrajpatel.rxjava_application.DataSource;

import com.yuvrajpatel.rxjava_application.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Task> createTask(){

        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new Task("Pay Hydro bills", true, 3));
        taskList.add(new Task("Walk the dog", false, 2));
        taskList.add(new Task("Buy Grocery", true, 1));
        taskList.add(new Task("Play Cricket", false, 0));
        taskList.add(new Task("Make Dinner", true, 5));

        return taskList;
    }
}
