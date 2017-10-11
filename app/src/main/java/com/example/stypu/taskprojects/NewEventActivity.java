package com.example.stypu.taskprojects;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewEventActivity extends AppCompatActivity {


    public static final String TASK_CREATED = "com.vertiavo.zadanie2.NewTaskActivity.TASK_CREATED";


    private List<ToDo> todos = Singleton.getInstance().getToDos();

    private EditText name;

    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        name = (EditText) findViewById(R.id.insert_name);
        date =(EditText) findViewById(R.id.insert_date);

        name.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void SaveEvent(View view)
    {
        String eventName = name.getText().toString();
        String datee = date.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(datee + ":00");

        } catch (ParseException e) {
            // TODO Auto-generated tcatch block
            e.printStackTrace();
            date.setError("Date format is invalid");
            return ;
        }
       if(validateName()) {
           todos.add(new ToDo(eventName, convertedDate));
           boolean created = true;

           CheckBox cb = findViewById(R.id.addNotification);

           if(cb.isChecked() == true) {
               AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

               Notification.Builder builder = new Notification.Builder(this);
               builder.setContentTitle("Reminder");
               builder.setContentText("Event: " + eventName);
               builder.setSmallIcon(R.drawable.ic_android);

               Intent remindIntent = new Intent(this, NotificationPublisher.class);
               remindIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
               remindIntent.putExtra(NotificationPublisher.NOTIFICATION, builder.build());

               PendingIntent intentt = PendingIntent.getBroadcast(this, 0, remindIntent, PendingIntent.FLAG_UPDATE_CURRENT);


               alarm.setExact(AlarmManager.RTC_WAKEUP, convertedDate.getTime(), intentt);
           }
           Intent intent = new Intent(this, MainActivity.class);
           intent.putExtra(TASK_CREATED, created);
           startActivity(intent);
       }
       }

    public boolean validateName(){
        if (name.getText().toString().length() == 0) {
            name.setError("Name is required!");
            return false;
        } else return true;
    }

}
