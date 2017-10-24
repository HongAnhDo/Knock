package com.example.hongdo.knock.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hongdo.knock.R;
import com.example.hongdo.knock.adapters.RecyclerViewAdapter;
import com.example.hongdo.knock.databases.Test;
import com.example.hongdo.knock.models.EventDay;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import ru.noties.scrollable.ScrollableLayout;

public class CalendarActivity extends AppCompatActivity {


    private CompactCalendarView compactcalendarView;
    private ImageButton btnPreMonth;
    private ImageButton btnNextMonth;
    private Button btnCreateEvent;
    private Toolbar toolbar;
    private TextView tv;
    private RecyclerView bookings_listview;
    private ScrollableLayout scrollableLayout;
    private RelativeLayout headCalendar;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<EventDay> eventDayList;
    private ArrayList<EventDay> eventDay;

    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    Calendar c;
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorStausBar));

        setUpView();
        setUpEvent();

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setUpView() {
        tv = (TextView) findViewById(R.id.tv_dateSelect);
        bookings_listview = (RecyclerView) findViewById(R.id.bookings_listview);
        btnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
        compactcalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        btnNextMonth = (ImageButton) findViewById(R.id.ibtn_nextMonth);
        btnPreMonth = (ImageButton) findViewById(R.id.ibtn_prevMonth);
        headCalendar = (RelativeLayout) findViewById(R.id.headCalendar);
        scrollableLayout = (ScrollableLayout) findViewById(R.id.scrollable_layout);

    }




    private void setUpEvent() {
        eventDay = new ArrayList<EventDay>();
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        tv.setText(dateFormatForMonth.format(c.getTime()));
        eventDayList = Test.getListEventDay();
        date = new Date();

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        btnPreMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousMonth();
            }
        });

        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextMonth();
            }
        });


        String stDateToday = dateFormatForDay.format(new Date());

        for (EventDay e : eventDayList) {
            Date dateEV = e.getDateEvent();
            long timeInMillis = e.getDateEvent().getTime();
            String stDateEV = dateFormatForDay.format(dateEV);
            if (stDateToday.equals(stDateEV)) {
                eventDay.add(e);
            }


        }

        bookings_listview.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(eventDay);
        bookings_listview.setAdapter(recyclerViewAdapter);

        for (EventDay e : eventDayList) {
            long timeInMillis = e.getDateEvent().getTime();

            Event event = new Event(Color.RED, timeInMillis, e);
            compactcalendarView.addEvent(event);

        }

        compactcalendarView.setUseThreeLetterAbbreviation(true);
        compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                eventDay.clear();

                String stDateClick = dateFormatForDay.format(dateClicked);

                for (EventDay e : eventDayList) {
                    Date dateEV = e.getDateEvent();
                    long timeInMillis = e.getDateEvent().getTime();
                    String stDateEV = dateFormatForDay.format(dateEV);
                    Log.d("HAD: ", stDateEV + ":" +stDateClick );
                    if (stDateClick.equals(stDateEV)) {
                        eventDay.add(e);
                    }


                }

                recyclerViewAdapter.deleteData(eventDay);
                bookings_listview.setAdapter(recyclerViewAdapter);


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                tv.setText(dateFormatForMonth.format(compactcalendarView.getFirstDayOfCurrentMonth()));
            }

        });


        scrollableLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (checkIntersectToolbar(bookings_listview)) {
                    tv.setText(dateFormatForDay.format(date));
                    btnNextMonth.setVisibility(View.GONE);
                    btnPreMonth.setVisibility(View.GONE);

                } else {
                    tv.setText(dateFormatForMonth.format(compactcalendarView.getFirstDayOfCurrentMonth()));
                    btnNextMonth.setVisibility(View.VISIBLE);
                    btnPreMonth.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    public void showNextMonth() {
        compactcalendarView.showNextMonth();

    }

    public void showPreviousMonth() {
        compactcalendarView.showPreviousMonth();

    }

    private boolean checkIntersectToolbar(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int top = location[1];
        headCalendar.getLocationOnScreen(location);
        int tbBottom = location[1] + headCalendar.getHeight();
        Log.d("HAD", "lenght: " + top + ": " + tbBottom);
        if (top <= tbBottom)
            return true;
        return false;
    }



}

