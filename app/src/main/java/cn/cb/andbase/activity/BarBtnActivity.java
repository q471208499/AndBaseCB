package cn.cb.andbase.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;
import es.dmoral.toasty.MyToast;

public class BarBtnActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_btn);
        initBarView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add(Menu.NONE, Menu.NONE, Menu.FIRST, "更新");
        //menuItem.setIcon(R.mipmap.ic_search); 使用图标
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        /*MenuItem menuItem2 = menu.add(Menu.NONE, Menu.NONE, Menu.FIRST, "搜索");
        menuItem2.setIcon(android.R.drawable.ic_menu_search);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == Menu.NONE) {
            MyToast.show(item.getTitle());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}