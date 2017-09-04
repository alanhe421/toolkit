package com.qq.reader.view;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.tencent.util.WeakReferenceHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AdvViewPager extends ViewPager implements Callback {
    public Map<String, Integer> a = new HashMap();
    private boolean b;
    private WeakReferenceHandler c = new WeakReferenceHandler(this);

    public AdvViewPager(Context context) {
        super(context);
        a(context);
    }

    public AdvViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("m");
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

    public void j() {
        k();
        this.c.sendEmptyMessageDelayed(0, 5000);
    }

    public void k() {
        this.c.removeMessages(0);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            j();
        } else {
            k();
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
