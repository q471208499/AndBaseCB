package cn.cb.baselibrary.db;

import android.content.Context;

public class DemoSqlManager {
    private static DemoSqlManager instance;
    private static DemoSqlHelper demoSqlHelper;
    private static DemoDbManager demoDbManager;

    private DemoSqlManager() {

    }

    public static void createInstance(Context context, String name, int version) {
        instance = new DemoSqlManager();
        demoSqlHelper = new DemoSqlHelper(context, name, null, version);
        demoDbManager = new DemoDbManager(demoSqlHelper);
    }

    public static DemoSqlManager getInstance() {
        return instance;
    }

    public DemoDbManager getDemoDbManager() {
        return demoDbManager;
    }
}
