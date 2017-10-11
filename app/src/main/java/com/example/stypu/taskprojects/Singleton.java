package com.example.stypu.taskprojects;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by stypu on 05.10.2017.
 */

class Singleton {
    private static  Singleton ourInstance;
    private static List<ToDo> toDos = new LinkedList<>();
    private Singleton() {
    }
    public static Singleton getInstance()
    {
        if(ourInstance==null)
        {
            ourInstance = new Singleton();
            toDos.add(new ToDo("Pozmywac",new Date(2017, 10, 21)) );
            toDos.add(new ToDo("Posprzatac", new Date(2017,11,22)));
            toDos.add(new ToDo("Zrobic zakupy", new Date()));
        }
        return ourInstance;
    }

    public List<ToDo> getToDos() {
        return toDos;
    }

    public void setTasks(List<ToDo> todoss) {
        this.toDos = todoss;
    }

    public static void remove(int i)
    {
        toDos.remove(i);
    }

}
