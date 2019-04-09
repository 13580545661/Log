package com.weacadt.log.activity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.weacadt.log.R;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddDiaryActivity extends AppCompatActivity implements View.OnClickListener {
    private Button tvShowDialog;
    private Calendar cal;
    private int mYear, mMonth, mDay;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        //获取当前日期
        getDate();
        getSupportActionBar().setTitle(mYear + "年" + (mMonth + 1) + "月" + mDay + "日");


    }

    /**
     * 获取当前日期
     */
    private void getDate() {
        cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy", "year" + mYear);
        mMonth = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        mDay = cal.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_add_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ab_set_date:
                DatePickerDialog dialog = new DatePickerDialog(AddDiaryActivity.this, 0, listener, mYear, mMonth, mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    OnDateSetListener listener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            getSupportActionBar().setTitle(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
        }
    };

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddDiaryActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_diary_fab_add:break;
        }
    }
}
