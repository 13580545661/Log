package com.weacadt.log.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.weacadt.log.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbar);
    }
}
