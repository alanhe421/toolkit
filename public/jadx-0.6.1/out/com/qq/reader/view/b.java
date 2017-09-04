package com.qq.reader.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;

/* compiled from: BasePopupWindow */
public class b extends PopupWindow {
    private ImageView a;
    private a b;
    private EditText c = null;

    /* compiled from: BasePopupWindow */
    private class a extends FrameLayout {
        final /* synthetic */ b a;

        public a(b bVar, Context context) {
            this.a = bVar;
            super(context);
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            boolean z = true;
            if (motionEvent.getAction() == 0) {
                this.a.a();
            }
            try {
                z = super.dispatchTouchEvent(motionEvent);
            } catch (Exception e) {
            }
            return z;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z = true;
            try {
                z = super.onTouchEvent(motionEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return z;
        }
    }

    public b(View view, int i, int i2) {
        super(view, i, i2);
        a(view, i, i2);
    }

    private void a(View view, int i, int i2) {
        this.b = new a(this, view.getContext());
        this.a = new ImageView(view.getContext());
        this.a.setImageDrawable(new ColorDrawable(Color.parseColor("#77000000")));
        this.b.addView(view, new LayoutParams(i, i2));
        this.b.addView(this.a, new LayoutParams(i, i2));
        setContentView(this.b);
        if (d.n) {
            this.a.setVisibility(0);
        } else {
            this.a.setVisibility(8);
        }
    }

    public void setHeight(int i) {
        super.setHeight(i);
    }

    public void setWidth(int i) {
        super.setWidth(i);
    }

    public void a(EditText editText) {
        this.c = editText;
    }

    private void a() {
        if (this.c != null) {
            ((InputMethodManager) ReaderApplication.getApplicationImp().getSystemService("input_method")).hideSoftInputFromWindow(this.c.getWindowToken(), 0);
        }
    }
}
