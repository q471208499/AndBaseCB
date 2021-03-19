package cn.cb.andbase;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cb.andbase.activity.BarBtnActivity;
import cn.cb.andbase.activity.CrashActivity;
import cn.cb.andbase.activity.DialogActivity;
import cn.cb.andbase.activity.DrawerActivity;
import cn.cb.andbase.activity.FloatWindowActivity;
import cn.cb.andbase.activity.LoadingActivity;
import cn.cb.andbase.activity.LocalActivity;
import cn.cb.andbase.activity.MainActivity;
import cn.cb.andbase.activity.RecyclerActivity;
import cn.cb.andbase.activity.RecyclerViewMaxItemActivity;
import cn.cb.andbase.activity.TestBrowserActivity;
import cn.cb.andbase.activity.TextActivity;
import cn.cb.andbase.activity.UpdateActivity;
import cn.cb.andbase.adapter.MyMenuAdapter;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.activity.SplashBaseActivity;
import cn.cb.baselibrary.utils.ViewUtils;
import cn.cb.baselibrary.widget.MyDividerItemDecoration;

public class NavigationMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        bindView();
    }

    private void bindView() {
        RecyclerView recyclerView = findViewById(R.id.nav_menu_recycler);
        MyMenuAdapter menuAdapter = new MyMenuAdapter(this, initData(), clickListener);
        recyclerView.addItemDecoration(new MyDividerItemDecoration());
        recyclerView.setAdapter(menuAdapter);
        ViewUtils.setOutline(recyclerView);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.item_btn) {
                Map<String, Object> map = (Map<String, Object>) v.getTag();
                Class cls = (Class) map.get("cls");
                String title = (String) map.get("name");
                Intent intent = new Intent();
                intent.setClass(NavigationMenuActivity.this, cls);
                intent.putExtra(Intent.EXTRA_TITLE, title);
                startActivity(intent);
            }
        }
    };

    private List<Map<String, Object>> initData() {
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map0 = new HashMap<>();
        map0.put("name", "万能recycler边框");
        map0.put("cls", RecyclerActivity.class);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "splash");
        map1.put("cls", SplashBaseActivity.class);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "update");
        map2.put("cls", UpdateActivity.class);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "Web");
        map3.put("cls", TestBrowserActivity.class);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("name", "Main");
        map4.put("cls", MainActivity.class);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("name", "Loading");
        map5.put("cls", LoadingActivity.class);

        Map<String, Object> map6 = new HashMap<>();
        map6.put("name", "Crash Activity");
        map6.put("cls", CrashActivity.class);

        Map<String, Object> map7 = new HashMap<>();
        map7.put("name", "中文 & English");
        map7.put("cls", LocalActivity.class);

        Map<String, Object> map8 = new HashMap<>();
        map8.put("name", "Dialog");
        map8.put("cls", DialogActivity.class);

        Map<String, Object> map9 = new HashMap<>();
        map9.put("name", "标题栏按钮");
        map9.put("cls", BarBtnActivity.class);

        Map<String, Object> map10 = new HashMap<>();
        map10.put("name", "recycler max item");
        map10.put("cls", RecyclerViewMaxItemActivity.class);

        Map<String, Object> map11 = new HashMap<>();
        map11.put("name", "app全局悬浮");
        map11.put("cls", FloatWindowActivity.class);

        Map<String, Object> map12 = new HashMap<>();
        map12.put("name", "抽屉");
        map12.put("cls", DrawerActivity.class);

        Map<String, Object> map13 = new HashMap<>();
        map13.put("name", "网络请求");
        map13.put("cls", DrawerActivity.class);

        Map<String, Object> map14 = new HashMap<>();
        map14.put("name", "文本滚动");
        map14.put("cls", TextActivity.class);

        maps.add(map0);
        maps.add(map1);
        maps.add(map2);
        maps.add(map3);
        maps.add(map4);
        maps.add(map5);
        maps.add(map6);
        maps.add(map7);
        maps.add(map8);
        maps.add(map9);
        maps.add(map10);
        maps.add(map11);
        maps.add(map12);
        maps.add(map14);
        return maps;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showFinishDialog();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}