package com.example.engieneertasklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engieneertasklist.Data.Event;
import com.example.engieneertasklist.Data.EventAdapter;
import com.example.engieneertasklist.Utils.CalendarUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import static com.example.engieneertasklist.Utils.CalendarUtils.daysInMonthArray;
import static com.example.engieneertasklist.Utils.CalendarUtils.daysInWeekArray;
import static com.example.engieneertasklist.Utils.CalendarUtils.monthYearFromDate;

public class WeekViewActivity extends AppCompatActivity  implements CalendarAdapter.OnItemListener{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    public ListView eventListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate =  CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate =  CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate( CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray( CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }



    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

}