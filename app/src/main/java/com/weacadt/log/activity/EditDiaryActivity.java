package com.weacadt.log.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.weacadt.log.R;
import com.weacadt.log.application.BaseApplication;
import com.weacadt.log.data.DiaryItem;
import com.weacadt.log.database.DaoSession;
import com.weacadt.log.database.DiaryItemDao;

import java.util.ArrayList;
import java.util.List;

public class EditDiaryActivity extends AppCompatActivity implements View.OnClickListener {

    private DaoSession daoSession;
    private DiaryItemDao diaryItemDao;
    private DiaryItem diaryItem;
    private List<DiaryItem> list = new ArrayList();

    private Toolbar toolbar;
    private EditText editText;
    private TextInputEditText textInputEditText;
    private FloatingActionButton fab_add;
    private FloatingActionButton fab_back;
    private FloatingActionButton fab_delete;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);
        initData();
    }

    private void initData() {
        daoSession = ((BaseApplication)getApplication()).getDaoSession();
        diaryItemDao = daoSession.getDiaryItemDao();

        editText = findViewById(R.id.et_edit_diary);
        textInputEditText = findViewById(R.id.tiet_edit_diary_content);
        fab_add = findViewById(R.id.edit_diary_fab_add);
        fab_back = findViewById(R.id.edit_diary_fab_back);
        fab_delete = findViewById(R.id.edit_diary_fab_delete);
        toolbar = findViewById(R.id.toolbar_main);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);

        list = diaryItemDao.queryBuilder().where(DiaryItemDao.Properties.Id.eq(id)).list();
        diaryItem = list.get(0);

        editText.setText(diaryItem.getTitle());
        textInputEditText.setText(diaryItem.getContent());

        mYear = diaryItem.getYear();
        mMonth = diaryItem.getMonth();
        mDay = diaryItem.getDayOfMonth();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mYear + "年" + mMonth + "月" + mDay + "日");
        fab_add.setOnClickListener(this);
        fab_back.setOnClickListener(this);


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
                DatePickerDialog dialog = new DatePickerDialog(EditDiaryActivity.this, 0, listener, mYear, mMonth -1, mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            getSupportActionBar().setTitle(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
            mYear = year;
            mMonth = month + 1;
            mDay = dayOfMonth;
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_diary_fab_add:
                diaryItem.setTitle(editText.getText().toString());
                diaryItem.setContent(textInputEditText.getText().toString());

                diaryItem.setYear(mYear);
                diaryItem.setMonth(mMonth);
                diaryItem.setDayOfMonth(mDay);

                diaryItemDao.update(diaryItem);
                setResult(4);
                finish();
            case R.id.edit_diary_fab_back:
                finish();
        }
    }
}
