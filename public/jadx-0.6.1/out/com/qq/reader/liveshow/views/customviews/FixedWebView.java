package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import com.qq.reader.liveshow.utils.e;
import java.util.Map;

public class FixedWebView extends WebView {
    private boolean a = true;
    private Paint b;
    private Paint c;
    private float d = 0.0f;
    private int e;
    private int f;

    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ Map b;
        final /* synthetic */ FixedWebView c;

        public void run() {
            this.c.loadUrl(this.a, this.b);
        }
    }

    public FixedWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        a();
    }

    public FixedWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        a();
    }

    public FixedWebView(Context context) {
        super(context);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        a();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.a) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            int scrollY = getScrollY();
            scrollTo(getScrollX(), getScrollY() + 1);
            scrollTo(getScrollX(), scrollY);
        }
        return super.onTouchEvent(motionEvent);
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

    public void a(final String str) {
        post(new Runnable(this) {
            final /* synthetic */ FixedWebView b;

            public void run() {
                if (str != null && str.length() != 0) {
                    this.b.loadUrl(str);
                }
            }
        });
    }

    public void reload() {
        String url = getUrl();
        if (url != null && url.length() != 0) {
            loadUrl(url);
        }
    }

    private void a() {
        this.b = new Paint();
        this.b.setColor(-1);
        this.b.setAntiAlias(true);
        this.b.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        this.c = new Paint();
        this.c.setXfermode(null);
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(e.a);
        }
    }

    public void setRadius(float f) {
        this.d = f;
    }

    public void draw(Canvas canvas) {
        if (this.d > 0.0f) {
            this.e = getScrollX();
            this.f = getScrollY();
            Bitmap createBitmap = Bitmap.createBitmap(this.e + getWidth(), this.f + getHeight(), Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            super.draw(canvas2);
            a(canvas2);
            d(canvas2);
            b(canvas2);
            c(canvas2);
            canvas.drawBitmap(createBitmap, 0.0f, 0.0f, this.c);
            createBitmap.recycle();
            return;
        }
        super.draw(canvas);
    }

    private void a(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) this.e, this.d);
        path.lineTo((float) this.e, (float) this.f);
        path.lineTo(this.d, (float) this.f);
        path.arcTo(new RectF((float) this.e, (float) this.f, ((float) this.e) + (this.d * 2.0f), ((float) this.f) + (this.d * 2.0f)), -90.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.b);
    }

    private void b(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) this.e, ((float) (this.f + getHeight())) - this.d);
        path.lineTo((float) this.e, (float) (this.f + getHeight()));
        path.lineTo(((float) this.e) + this.d, (float) (this.f + getHeight()));
        path.arcTo(new RectF((float) this.e, ((float) (this.f + getHeight())) - (this.d * 2.0f), ((float) this.e) + (this.d * 2.0f), (float) (this.f + getHeight())), 90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.b);
    }

    private void c(Canvas canvas) {
        Path path = new Path();
        path.moveTo(((float) (this.e + getWidth())) - this.d, (float) (this.f + getHeight()));
        path.lineTo((float) (this.e + getWidth()), (float) (this.f + getHeight()));
        path.lineTo((float) (this.e + getWidth()), ((float) (this.f + getHeight())) - this.d);
        path.arcTo(new RectF(((float) (this.e + getWidth())) - (this.d * 2.0f), ((float) (this.f + getHeight())) - (this.d * 2.0f), (float) (this.e + getWidth()), (float) (this.f + getHeight())), 0.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.b);
    }

    private void d(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) (this.e + getWidth()), ((float) this.f) + this.d);
        path.lineTo((float) (this.e + getWidth()), (float) this.f);
        path.lineTo(((float) (this.e + getWidth())) - this.d, (float) this.f);
        path.arcTo(new RectF(((float) (this.e + getWidth())) - (this.d * 2.0f), (float) this.f, (float) (this.e + getWidth()), ((float) this.f) + (this.d * 2.0f)), -90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.b);
    }

    public void setTouchable(boolean z) {
        this.a = z;
    }
}
