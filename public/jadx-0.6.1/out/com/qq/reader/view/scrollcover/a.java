package com.qq.reader.view.scrollcover;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.tencent.smtt.sdk.WebView;

/* compiled from: FancyCoverFlowAdapter */
public abstract class a extends BaseAdapter {
    public abstract View a(int i, View view, ViewGroup viewGroup);

    public final View getView(int i, View view, ViewGroup viewGroup) {
        FancyCoverFlow fancyCoverFlow = (FancyCoverFlow) viewGroup;
        View view2 = null;
        if (view != null) {
            view = (FancyCoverFlowItemWrapper) view;
            view2 = view.getChildAt(0);
            view.removeAllViews();
        } else {
            view = new FancyCoverFlowItemWrapper(viewGroup.getContext());
        }
        view2 = a(i, view2, viewGroup);
        if (view2 == null) {
            throw new NullPointerException("getCoverFlowItem() was expected to return a view, but null was returned.");
        }
        boolean a = fancyCoverFlow.a();
        view.a(a);
        if (a) {
            view.a(fancyCoverFlow.getReflectionGap());
            view.a(fancyCoverFlow.getReflectionRatio());
        }
        view.addView(view2);
        view.setLayoutParams(view2.getLayoutParams());
        view.setBackgroundColor(WebView.NIGHT_MODE_COLOR);
        view.b(a());
        return view;
    }

    public int a() {
        return 0;
    }
}
