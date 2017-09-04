package com.qq.reader.view.web;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

public class ListenToInputMethodView extends RelativeLayout {
    private a a = null;
    private boolean b = true;
    private int c = 0;

    public interface a {
        boolean a(KeyEvent keyEvent);
    }

    public ListenToInputMethodView(Context context) {
        super(context);
    }

    public ListenToInputMethodView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ListenToInputMethodView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setKeyImeListener(a aVar) {
        this.a = aVar;
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        boolean dispatchKeyEventPreIme = super.dispatchKeyEventPreIme(keyEvent);
        if (this.a != null) {
            return this.a.a(keyEvent);
        }
        return dispatchKeyEventPreIme;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
