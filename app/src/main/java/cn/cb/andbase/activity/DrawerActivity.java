package cn.cb.andbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;

public class DrawerActivity extends BaseActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_bar_home);
        NavigationView navigationView = findViewById(R.id.nav_view);
        addMenuItem(navigationView);

        TextView title = navigationView.getHeaderView(0).findViewById(R.id.nav_header_title);
        TextView subtitle = navigationView.getHeaderView(0).findViewById(R.id.nav_header_subtitle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.open();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addMenuItem(NavigationView navigationView) {
        navigationView.getMenu().add(100, 200, 0, "菜单1");
        navigationView.getMenu().add(100, 201, 1, "菜单2");
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(i).setOnMenuItemClickListener(menuItemClickListener);
        }
    }

    private MenuItem.OnMenuItemClickListener menuItemClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Class cls = null;
            String title = "";
            switch (item.getItemId()) {
                case 200:
                    cls = LoadingActivity.class;
                    title = "菜单1";
                    break;
                case 201:
                    // TODO 切换fragment
                    return false;
            }
            Intent intent = new Intent(DrawerActivity.this, cls);
            intent.putExtra(Intent.EXTRA_TITLE, title);
            startActivity(intent);
            drawerLayout.close();
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.drawer, menu);
        return true;*/
        return super.onCreateOptionsMenu(menu);
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isOpen()) {
                drawerLayout.close();
            } else {
                showFinishDialog();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}