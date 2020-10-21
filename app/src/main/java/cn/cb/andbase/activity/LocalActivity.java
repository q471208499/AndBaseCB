package cn.cb.andbase.activity;

import android.os.Bundle;
import android.view.View;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.utils.CBAppUtils;

public class LocalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        initBarView();

        findViewById(R.id.chinese).setOnClickListener(clickListener);
        findViewById(R.id.english).setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.chinese) {
                CBAppUtils.setLocalLanguageZh(LocalActivity.this);
            } else if (v.getId() == R.id.english) {
                CBAppUtils.setLocalLanguageEn(LocalActivity.this);
            }

        }
    };
}