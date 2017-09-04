package com.qq.reader.module.feed.card;

import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.h;
import java.util.ArrayList;

public abstract class FeedMultiClickBaseCard extends FeedBaseCard implements h {
    float currentX = 0.0f;
    float currentY = 0.0f;
    private long downStart;
    float downX = 0.0f;
    float downY = 0.0f;
    private View lastPressed;
    private int mClickIndex;
    private int sMoveEdge = 10;

    protected abstract void doClick(View view);

    protected abstract ArrayList<View> getViews();

    public FeedMultiClickBaseCard(b bVar, String str) {
        super(bVar, str);
    }

    private View getPressedView(MotionEvent motionEvent, ArrayList<View> arrayList) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        RectF rectF = new RectF();
        int[] iArr = new int[2];
        for (int i = 0; i < arrayList.size(); i++) {
            View view = (View) arrayList.get(i);
            view.getLocationOnScreen(iArr);
            rectF.set((float) iArr[0], (float) iArr[1], (float) (view.getWidth() + iArr[0]), (float) (view.getHeight() + iArr[1]));
            if (rectF.contains(rawX, rawY)) {
                this.mClickIndex = i;
                return view;
            }
        }
        return null;
    }

    public void onDown(MotionEvent motionEvent) {
        try {
            this.lastPressed = getPressedView(motionEvent, getViews());
            this.downX = motionEvent.getRawX();
            this.downY = motionEvent.getRawY();
            this.currentX = this.downX;
            this.currentY = this.downY;
            if (this.lastPressed != null) {
                this.downStart = System.currentTimeMillis();
                this.lastPressed.postDelayed(new Runnable(this) {
                    final /* synthetic */ FeedMultiClickBaseCard a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (!this.a.isMoveOut() && this.a.lastPressed != null) {
                            this.a.lastPressed.setSelected(true);
                        }
                    }
                }, (long) ViewConfiguration.getTapTimeout());
            }
        } catch (Exception e) {
        }
    }

    public void onMove(MotionEvent motionEvent) {
        if (motionEvent != null) {
            this.currentX = motionEvent.getRawX();
            this.currentY = motionEvent.getRawY();
            if (this.lastPressed != null && outOffset(motionEvent)) {
                this.lastPressed.setSelected(false);
                this.lastPressed = null;
            }
        }
    }

    private boolean outOffset(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (Math.abs(rawX - this.downX) > ((float) this.sMoveEdge) || Math.abs(rawY - this.downY) > ((float) this.sMoveEdge)) {
            return true;
        }
        return false;
    }

    public void onUpOrCancel(MotionEvent motionEvent) {
        if (this.lastPressed != null) {
            boolean z;
            if (System.currentTimeMillis() - this.downStart > 500 || motionEvent == null) {
                z = false;
            } else {
                doClick(this.lastPressed);
                z = true;
            }
            if (!z) {
                this.lastPressed.setSelected(false);
            }
            this.lastPressed = null;
        }
    }

    private boolean isMoveOut() {
        return Math.abs(this.downX - this.currentX) > ((float) this.sMoveEdge) || Math.abs(this.downY - this.currentY) > ((float) this.sMoveEdge);
    }

    public int getClickIndex() {
        return this.mClickIndex;
    }

    public View getLastPressedView() {
        return this.lastPressed;
    }
}
