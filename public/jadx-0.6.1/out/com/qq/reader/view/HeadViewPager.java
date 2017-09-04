package com.qq.reader.view;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.cservice.adv.b;
import com.tencent.android.tpush.common.Constants;
import com.tencent.util.WeakReferenceHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadViewPager extends ViewPager implements Callback {
    public Map<String, Integer> a = new HashMap();
    WeakReferenceHandler b = new WeakReferenceHandler(this);
    private PagerAdapter c;
    private boolean d;
    private PagerAdapter e;

    private class a extends PagerAdapter {
        final /* synthetic */ HeadViewPager a;
        private PagerAdapter b;

        public a(HeadViewPager headViewPager, PagerAdapter pagerAdapter) {
            this.a = headViewPager;
            this.b = pagerAdapter;
        }

        public int a() {
            return this.b.a() > 2 ? Constants.ERRORCODE_UNKNOWN : this.b.a();
        }

        public Object a(ViewGroup viewGroup, int i) {
            return this.b.a(viewGroup, i % this.b.a());
        }

        public int a(Object obj) {
            return this.b.a(obj);
        }

        public void a(ViewGroup viewGroup, int i, Object obj) {
            this.b.a(viewGroup, i % this.b.a(), obj);
        }

        public boolean a(View view, Object obj) {
            return this.b.a(view, obj);
        }
    }

    public HeadViewPager(Context context) {
        super(context);
        a(context);
    }

    public HeadViewPager(Context context, AttributeSet attributeSet) {
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

    public void setAdapter(PagerAdapter pagerAdapter) {
        this.e = pagerAdapter == null ? null : new a(this, pagerAdapter);
        super.setAdapter(this.e);
        this.c = pagerAdapter;
    }

    public PagerAdapter getAdapter() {
        return this.c;
    }

    public PagerAdapter getMyPagerAdapter() {
        return this.e;
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, true);
    }

    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(i, z);
    }

    public void a(long j) {
        k();
        this.b.sendEmptyMessageDelayed(0, j);
    }

    public void j() {
        k();
        this.b.sendEmptyMessageDelayed(0, 5000);
    }

    public void k() {
        this.b.removeMessages(0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
            this.d = true;
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.d = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean handleMessage(Message message) {
        if (!(getAdapter() == null || getAdapter().a() <= 1 || this.d)) {
            int currentItem = getCurrentItem();
            try {
                List b = b.a(getContext()).b("103170");
                if (b != null && b.size() > currentItem) {
                    int intValue;
                    com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(currentItem);
                    if (this.a.get(aVar.h()) != null) {
                        intValue = ((Integer) this.a.get(aVar.h())).intValue();
                    } else {
                        intValue = 0;
                    }
                    this.a.put(aVar.h(), Integer.valueOf(intValue + 1));
                }
            } catch (Exception e) {
            }
            if (currentItem + 1 < getMyPagerAdapter().a()) {
                setCurrentItem(currentItem + 1, true);
            } else {
                setCurrentItem(0, true);
            }
        }
        return true;
    }
}
