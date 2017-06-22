package com.cyy.mvpsimple;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.fire.DBCallback;
import com.fire.DBHelper;
import com.fire.table.DBBase;

import butterknife.ButterKnife;

/**
 * Created by study on 17/6/22.
 *
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);


        DBBase.Config config = DBBase.builcConfig()
                .setName("message.db")
                .setVersion(1)
                .setDebug(true)
                .setCallback(new DBCallback() {
                    @Override
                    public void dbCreate() {

                    }

                    @Override
                    public void dbUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {

                    }
                });

        DBBase.getBase().init(this,config).creatTables(new Class[]{Message.class});
    }
}
