package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import com.qq.reader.common.monitor.f;
import java.util.Map;

public class FixedWebView extends WebView {
    private a a;

    public /* bridge */ /* synthetic */ Object getAccessibilityNodeProvider() {
        return super.getAccessibilityNodeProvider();
    }

    public FixedWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
    }

    public FixedWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
    }

    public FixedWebView(Context context) {
        super(context);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int scrollY = getScrollY();
            scrollTo(getScrollX(), getScrollY() + 1);
            scrollTo(getScrollX(), scrollY);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void a(String str) {
        if (str != null && str.length() != 0) {
            try {
                super.loadUrl(str);
            } catch (Exception e) {
                f.a("Javascript", "error", e);
            }
        }
    }

    @Deprecated
    public void loadUrl(String str, Map<String, String> map) {
        if (str != null && str.length() != 0) {
            super.loadUrl(str, map);
        }
    }

    @Deprecated
    public void loadUrl(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    super.loadUrl(str);
                }
            } catch (Exception e) {
            }
        }
    }

    public void b(String str) {
        post(new 1(this, str));
    }

    public void reload() {
        String url = getUrl();
        if (url != null && url.length() != 0) {
            loadUrl(url);
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.a != null) {
            this.a.a(this, i, i2, i3, i4);
        }
    }

    public a getOnScrollChangedCallback() {
        return this.a;
    }

    public void setOnScrollChangedListener(a aVar) {
        this.a = aVar;
    }
}
