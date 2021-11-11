package com.example.engieneertasklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.engieneertasklist.Data.Event;
import com.example.engieneertasklist.Data.HourAdapter;
import com.example.engieneertasklist.Data.HourEvent;
import com.example.engieneertasklist.Utils.CalendarUtils;

import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.engieneertasklist.Utils.CalendarUtils.selectedDate;

public class DailyCalendarActivity extends AppCompatActivity {

    private TextView monthDayText;
    private TextView dayOfWeekTV;
    public ListView hourListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calendar);
        initWidgets();
    }

    private void initWidgets() {
        monthDayText = findViewById(R.id.monthDayTV);
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV);
        hourListView = findViewById(R.id.hourListView);
    }

    public void previousDayAction(View view) {
        CalendarUtils.selectedDate =  CalendarUtils.selectedDate.minusDays(1);
        setDayView();
    }

    public void nextDayAction(View view) {
        CalendarUtils.selectedDate =  CalendarUtils.selectedDate.plusDays(1);
        setDayView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDayView();
    }

    private void setDayView() {
        monthDayText.setText(CalendarUtils.monthDayFromDate(selectedDate));
        String dayOfWeek = selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        dayOfWeekTV.setText(dayOfWeek);
        setHourAdapter();
    }

    private void setHourAdapter() {
        HourAdapter hourAdapter = new HourAdapter(getApplicationContext(),hourEventList());
        hourListView.setAdapter(hourAdapter);
    }

    private ArrayList<HourEvent> hourEventList() {
        ArrayList<HourEvent> list = new ArrayList<>();

        for(int hour = 0 ; hour < 24 ; hour++)
        {
            LocalTime time = LocalTime.of(hour, 0);
            ArrayList<Event> events = Event.eventsFromDateAndTime(selectedDate,time);
            HourEvent hourEvent = new HourEvent(time,events);
            list.add(hourEvent);
        }
        return  list;
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));

    }
}