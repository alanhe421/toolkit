package com.qq.reader.module.feed.swipe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.tencent.feedback.proguard.R;

public class FeedSwipeCompatibleLayout extends RelativeLayout {
    public static int a = R.id.concept_rootview;

    public FeedSwipeCompatibleLayout(Context context, View view, int i) {
        super(context);
        a(context, view, i);
    }

    private void a(Context context, View view, int i) {
        view.setId(a);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        addView(View.inflate(context, i, null), layoutParams);
        addView(view, layoutParams);
    }
}
