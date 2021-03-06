package com.pps.ppls;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.pps.ppls.model.DaoMaster;
import com.pps.ppls.model.DaoSession;

/**
 * Created by roba on 3/11/2015.
 */
public class PPLSApplication extends Application {

    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "logoped", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}