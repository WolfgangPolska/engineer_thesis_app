package com.example.engieneertasklist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.engieneertasklist.Data.Event;
import com.example.engieneertasklist.Utils.CalendarUtils;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date" + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Date" + CalendarUtils.formattedTime(time));
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventNameTV);
    }

    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate,time);
        Event.eventsList.add(newEvent);
        finish();
    }
}