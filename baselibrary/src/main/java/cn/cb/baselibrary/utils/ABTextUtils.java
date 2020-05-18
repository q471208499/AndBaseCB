package cn.cb.baselibrary.utils;

import android.text.TextUtils;
import android.widget.TextView;

public class ABTextUtils {

    public static boolean isEmpty(TextView... textViews) {
        if (textViews == null) {
            return true;
        }
        for (TextView textView : textViews) {
            if (textView == null) {
                return true;
            }
            String s = textView.getText().toString();
            if (TextUtils.isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(TextView... textViews) {
        return !isEmpty(textViews);
    }

}
