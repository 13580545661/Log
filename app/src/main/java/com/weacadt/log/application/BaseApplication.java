package com.weacadt.log.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.weacadt.log.database.DaoMaster;
import com.weacadt.log.database.DaoSession;

public class BaseApplication extends Application {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase();
    }

    private void initDatabase() {
        helper = new DaoMaster.DevOpenHelper(this, "log.db");
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
