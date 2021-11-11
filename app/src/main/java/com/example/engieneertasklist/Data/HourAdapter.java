package com.example.engieneertasklist.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.engieneertasklist.R;
import com.example.engieneertasklist.Utils.CalendarUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HourAdapter extends ArrayAdapter<HourEvent> {

    public HourAdapter(@NonNull Context context, List<HourEvent> hourEvents) {
        super(context, 0,hourEvents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HourEvent event = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_cell,parent,false);
        }

        setHour(convertView, event.time);
        setEvents(convertView, event.events);

        return  convertView;
    }

    private void setEvents(View convertView, ArrayList<Event> events) {
        TextView event1 = convertView.findViewById(R.id.event1);
        TextView event2 = convertView.findViewById(R.id.event2);
        TextView event3 = convertView.findViewById(R.id.event3);

        switch (events.size())
        {
            case 0:
                HideEvent(event1);
                HideEvent(event2);
                HideEvent(event3);
                break;
            case 1:
                SetEvent(event1,events.get(0));
                HideEvent(event2);
                HideEvent(event3);
                break;
            case 2:
                SetEvent(event1,events.get(0));
                SetEvent(event2,events.get(1));
                HideEvent(event3);
                break;
            case 3:
                SetEvent(event1,events.get(0));
                SetEvent(event2,events.get(1));
                SetEvent(event3,events.get(2));
                break;
            default:
                SetEvent(event1,events.get(0));
                SetEvent(event2,events.get(1));
                event3.setVisibility(View.VISIBLE);
                String eventsNotShown = String.valueOf(events.size() - 2);
                eventsNotShown += " More Events..";
                event3.setText(eventsNotShown);
                break;
        }
    }

    private void SetEvent(TextView textView, Event event) {
        textView.setText(event.getName());
        textView.setVisibility(View.VISIBLE);
    }

    private void HideEvent(TextView tv)
    {
        tv.setVisibility(View.INVISIBLE);
    }

    private void setHour(View convertView, LocalTime time) {
        TextView timeTV = convertView.findViewById(R.id.timeTV);
        timeTV.setText(CalendarUtils.formattedShortTime(time));
        
    }
}