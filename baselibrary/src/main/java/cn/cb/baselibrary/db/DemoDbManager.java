package cn.cb.baselibrary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.cb.baselibrary.utils.LogHelper;

public class DemoDbManager {
    private static DemoDbManager instance;
    private static DemoOpenHelper demoOpenHelper;
    private static DemoSql demoSql;

    private DemoDbManager() {
    }

    public static void createInstance(Context context, String name, int version) {
        instance = new DemoDbManager();
        demoOpenHelper = new DemoOpenHelper(context, name, null, version);
        demoSql = new DemoSql(demoOpenHelper);
    }

    public static DemoDbManager getInstance() {
        return instance;
    }

    public DemoSql getDemoSql() {
        return demoSql;
    }


    public static class DemoOpenHelper extends SQLiteOpenHelper {
        private final String TAG = getClass().getSimpleName();

        public DemoOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlScribeRecord = "CREATE TABLE \"SCRIBE_RECORD\" (\n" +//抄表记录
                    "  \"ID\" INTEGER NOT NULL DEFAULT 0 PRIMARY KEY AUTOINCREMENT,\n" +//主键
                    "  \"METERADDRESS\" TEXT NOT NULL,\n" +//水表编号
                    "  \"COLLECTOR_NUMBER\" TEXT,\n" +//手持机编号
                    "  \"CUMULATIVE_USAGE\" INTEGER DEFAULT 0,\n" +//累计流量
                    "  \"ON_OFF_VALVE\" INTEGER DEFAULT 0,\n" +//开关阀
                    "  \"ANGLE_ALERT\" INTEGER DEFAULT 0,\n" +//角度报警
                    "  \"HIGH_TEMP_ALERT\" INTEGER DEFAULT 0,\n" +//高温报警
                    "  \"MAGNETIC_ATTACK_ALERT\" INTEGER DEFAULT 0,\n" +//磁攻击
                    "  \"OVERCURRENT_ALERT\" INTEGER DEFAULT 0,\n" +//过流报警
                    "  \"LOW_VOLTAGE_ALERT\" INTEGER DEFAULT 0,\n" +//电压低
                    "  \"ABNORMAL_VALVE_ALERT\" INTEGER DEFAULT 0,\n" +//阀门异常
                    "  \"STORAGE_ALERT\" INTEGER DEFAULT 0,\n" +//存储
                    "  \"SENSOR_ALERT\" INTEGER DEFAULT 0,\n" +//传感器
                    "  \"BATTERY_VOLTAGE\" INTEGER DEFAULT 0,\n" +//电池电压
                    "  \"TEMP\" INTEGER DEFAULT 0,\n" +//温度
                    "  \"ANGLE\" INTEGER DEFAULT 0,\n" +//角度
                    "  \"STATUS\" INTEGER DEFAULT 0,\n" +//状态0未上传；状态1已上传
                    //"  \"DAILY_SETTLEMENT\" TEXT,\n" +//日冻结时间
                    "  \"CREATE_TIME\" TEXT\n" +//创建时间
                    ")";
            String sqlBook = "CREATE TABLE \"BOOK\" (\n" +
                    "  \"ID\" INTEGER NOT NULL DEFAULT 0 PRIMARY KEY AUTOINCREMENT,\n" +
                    "  \"NAME\" TEXT,\n" +
                    "  \"METERADDRESS\" TEXT NOT NULL,\n" +
                    "  \"ADDRESS\" TEXT,\n" +
                    "  \"CREATE_TIME\" TEXT\n" +//创建时间
                    ")";
            LogHelper.i(TAG, "onCreate: " + sqlScribeRecord);
            db.execSQL(sqlScribeRecord);
            LogHelper.i(TAG, "onCreate: " + sqlBook);
            db.execSQL(sqlBook);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            List<String> list = new ArrayList<>();
            switch (oldVersion) {
                case 1:
                    list.add("ALTER TABLE SCRIBE_RECORD ADD TEMP INTEGER DEFAULT 0");
                    list.add("ALTER TABLE SCRIBE_RECORD ADD ANGLE INTEGER DEFAULT 0");
            }
            for (String s : list) {
                LogHelper.i(TAG, "onUpgrade: " + s);
                db.execSQL(s);
            }
        }
    }
}


