package cn.cb.andbase;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.cb.andbase.activity.BarBtnActivity;
import cn.cb.andbase.activity.CrashActivity;
import cn.cb.andbase.activity.DialogActivity;
import cn.cb.andbase.activity.LoadingActivity;
import cn.cb.andbase.activity.LocalActivity;
import cn.cb.andbase.activity.MainActivity;
import cn.cb.andbase.activity.RecyclerActivity;
import cn.cb.andbase.activity.TestBrowserActivity;
import cn.cb.andbase.activity.UpdateActivity;
import cn.cb.andbase.adapter.MyMenuAdapter;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.activity.SplashBaseActivity;
import cn.cb.baselibrary.widget.MyDividerItemDecoration;

public class NavigationMenuActivity extends BaseActivity {
    private List<String> list = new ArrayList<>();
    private MyMenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        bindView();
        setMenuList();
    }

    private void bindView() {
        RecyclerView recyclerView = findViewById(R.id.nav_menu_recycler);
        menuAdapter = new MyMenuAdapter(this, list, clickListener);
        recyclerView.addItemDecoration(new MyDividerItemDecoration());
        recyclerView.setAdapter(menuAdapter);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.item_btn) {
                Class cls = null;
                int index = (int) v.getTag();
                if (index == 0) {
                    cls = RecyclerActivity.class;
                } else if (index == 1) {
                    cls = SplashBaseActivity.class;
                } else if (index == 2) {
                    cls = UpdateActivity.class;
                } else if (index == 3) {
                    cls = TestBrowserActivity.class;
                } else if (index == 4) {
                    cls = MainActivity.class;
                } else if (index == 5) {
                    cls = LoadingActivity.class;
                } else if (index == 6) {
                    cls = CrashActivity.class;
                } else if (index == 7) {
                    cls = LocalActivity.class;
                    return;
                } else if (index == 8) {
                    cls = DialogActivity.class;
                } else if (index == 9) {
                    cls = BarBtnActivity.class;
                }
                Intent intent = new Intent();
                intent.setClass(NavigationMenuActivity.this, cls);
                intent.putExtra(Intent.EXTRA_TITLE, list.get(index));
                startActivity(intent);
            }
        }
    };

    private void setMenuList() {
        List<String> result = new ArrayList<>();
        result.add("万能recycler边框");
        result.add("splash");
        result.add("update");
        result.add("Web");
        result.add("Main");
        result.add("Loading");
        result.add("Crash Activity");
        result.add("中文 & English");
        result.add("Dialog");
        result.add("标题栏按钮");
        list.addAll(result);
        menuAdapter.notifyDataSetChanged();
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