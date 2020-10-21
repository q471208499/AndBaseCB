package cn.cb.andbase.activity;

import android.os.Bundle;
import android.view.View;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;

public class DialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        //initBarView();
        getSupportActionBar().hide();
        findViewById(R.id.dialog_show).setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showFinishDialog();
        }
    };
}