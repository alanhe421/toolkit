package com.nhaarman.listviewanimations.itemmanipulation.dragdrop;

import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;

/* compiled from: HoverDrawable */
class e extends BitmapDrawable {
    private float a;
    private float b;
    private float c;

    e(View view, float f) {
        super(view.getResources(), a.a(view));
        this.a = (float) view.getTop();
        this.b = f;
        setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    void a(MotionEvent motionEvent) {
        a((int) (((this.a - this.b) + motionEvent.getY()) + this.c));
    }

    void a(float f) {
        this.c += this.a - f;
        this.a = f;
    }

    boolean a() {
        return this.a > ((float) getBounds().top);
    }

    int b() {
        return (int) (((float) getBounds().top) - this.a);
    }

    int c() {
        return getBounds().top;
    }

    void a(int i) {
        setBounds(getBounds().left, i, getBounds().left + getIntrinsicWidth(), getIntrinsicHeight() + i);
    }

    void b(int i) {
        if (a()) {
            i = -i;
        }
        this.a += (float) i;
        this.b += (float) i;
    }
}
