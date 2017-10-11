package com.example.stypu.taskprojects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by stypu on 05.10.2017.
 */

public class ListViewAdapter extends BaseAdapter {

    private List<ToDo> todos;
    private final  LayoutInflater inflater;

    public ListViewAdapter(Context context, List<ToDo> todos) {
        super();
        this.todos = todos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int i) {
        return todos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.list_item_todos, viewGroup, false);

        TextView toDoNameView = rowView.findViewById(R.id.list_item_name);
        TextView toDoDateView = rowView.findViewById(R.id.list_item_date);

        ToDo toDo = todos.get(i);
        toDoNameView.setText(toDo.getName());

        Date date = toDo.getDate();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        toDoDateView.setText(format.format(date.getTime()));

        return rowView;
    }
}
