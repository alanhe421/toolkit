package com.qq.reader.view;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.tencent.util.WeakReferenceHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AdvLoopVerticalViewPager extends VerticalViewPager implements Callback {
    public Map<String, Integer> a = new HashMap();
    private boolean b;
    private WeakReferenceHandler c = new WeakReferenceHandler(this);

    public AdvLoopVerticalViewPager(Context context) {
        super(context);
        a(context);
    }

    public AdvLoopVerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        try {
            Field declaredField = VerticalViewPager.class.getDeclaredField("m");
            declaredField.setAccessible(true);
            declaredField.set(this, new i(context, null));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, true);
    }

    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(i, z);
    }

    public void a() {
        b();
        this.c.sendEmptyMessageDelayed(0, 5000);
    }

    public void b() {
        this.c.removeMessages(0);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            a();
        } else {
            b();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.b = true;
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.b = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean handleMessage(Message message) {
        if (!(getAdapter() == null || getAdapter().a() <= 1 || this.b)) {
            int currentItem = getCurrentItem();
            if (currentItem + 1 < getAdapter().a()) {
                setCurrentItem(currentItem + 1, true);
            } else {
                setCurrentItem(0, true);
            }
        }
        return true;
    }
}
