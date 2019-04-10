package com.weacadt.log.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.weacadt.log.R;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;
public class CalendarActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_calendar);
        calendarView = findViewById(R.id.cal);
        textView = findViewById(R.id.tv);
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(CalendarActivity.this,"您的生日是"+year+"年"+month+"月"+dayOfMonth+"日", Toast.LENGTH_LONG).show();
            }
        });
    }
}