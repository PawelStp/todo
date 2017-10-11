package com.example.stypu.taskprojects;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;
import java.util.List;

import static com.example.stypu.taskprojects.NewEventActivity.TASK_CREATED;

public class MainActivity extends AppCompatActivity {

    private ListView mList;
    private List<ToDo> toDos = Singleton.getInstance().getToDos();
    private Context context;
    private Snackbar Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intext = getIntent();

        if(getIntent().getBooleanExtra(TASK_CREATED,false)==true){
            Message = Snackbar.make(findViewById(R.id.main), "Added Event.", Snackbar.LENGTH_LONG);
            Message.show();
        };
        mList = (ListView) findViewById(R.id.main_list);

        final ListViewAdapter ListViewAdapter = new ListViewAdapter(this, toDos);
        mList.setAdapter(ListViewAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                toDos.remove(i);
                ListViewAdapter.notifyDataSetChanged();
                context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Event has been removed", Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        addNewTask();
        Intent intent = new Intent(this, NewEventActivity.class);
        startActivity(intent);
        return true;
    }
}
