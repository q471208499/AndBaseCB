package cn.cb.andbase.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;

public class FloatWindowActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);
        initBarView();
        //TextView textView = findViewById(R.id.float_text);
        TextView textView = new TextView(this);
        textView.setText("ddddddd");
        textView.setTextColor(Color.RED);
        textView.setBackground(getDrawable(R.color.colorBlue));
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setPadding(18, 8, 18, 8);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(100, 30);
        textView.setLayoutParams(layoutParams);
        setFloatWindowViewDefault(textView);
    }
}