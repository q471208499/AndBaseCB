package cn.cb.andbase.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;

public class TextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initBarView();

        TextView textView = findViewById(R.id.text_type_1);
        textView.setSelected(true);

        TextView textView2 = findViewById(R.id.text_type_2);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);//设置跑马灯显示效果
        textView2.setSingleLine(true);//设置单行显示
        textView2.setHorizontallyScrolling(true);//设置水平滚动效果
        textView2.setMarqueeRepeatLimit(-1);//设置滚动次数，-1为无限滚动，1为滚动1次
        textView2.setSelected(true);
    }
}