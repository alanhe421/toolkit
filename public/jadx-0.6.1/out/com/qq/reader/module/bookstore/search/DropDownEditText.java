package com.qq.reader.module.bookstore.search;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.Selection;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;

public class DropDownEditText extends EditText {
    com.qq.reader.view.b a;
    ListView b;
    BaseAdapter c;
    private int d = 10;
    private int e = getResources().getColor(R.color.common_divider);
    private int f = getResources().getDimensionPixelOffset(R.dimen.common_divider_height_thin);
    private int g = 17170443;
    private int h = -1;
    private int i;
    private e j = new e();
    private DataSetObserver k;
    private Runnable l = new Runnable(this) {
        final /* synthetic */ DropDownEditText a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.a != null) {
                try {
                    this.a.b();
                    com.qq.reader.common.monitor.debug.c.d("POPWINDOW", "resetBottomHeight topOffset = " + this.a.i + " mDropDownHeight = " + this.a.h);
                    this.a.a.update(0, this.a.i, -1, this.a.h);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Handler m = new Handler(Looper.getMainLooper());
    private long n;
    private OnGlobalLayoutListener o = new OnGlobalLayoutListener(this) {
        final /* synthetic */ DropDownEditText a;

        {
            this.a = r1;
        }

        public void onGlobalLayout() {
            long currentTimeMillis = System.currentTimeMillis();
            com.qq.reader.common.monitor.debug.c.d("POPWINDOW", "onGlobalLayout");
            if (currentTimeMillis - this.a.n > 16) {
                this.a.m.removeCallbacks(this.a.l);
                this.a.m.postDelayed(this.a.l, 16);
            }
            this.a.n = currentTimeMillis;
        }
    };
    private c p;

    public interface a {
        CharSequence a(int i);
    }

    public interface b {
        void a(ListView listView);
    }

    public interface c {
        void a();

        void b();
    }

    private static class d extends DataSetObserver {
        private final WeakReference<DropDownEditText> a;
        private final Runnable b;

        private d(DropDownEditText dropDownEditText) {
            this.b = new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    DropDownEditText dropDownEditText = (DropDownEditText) this.a.a.get();
                    if (dropDownEditText != null) {
                        ListAdapter listAdapter = dropDownEditText.c;
                        if (listAdapter != null) {
                            dropDownEditText.a(listAdapter.getCount());
                        }
                    }
                }
            };
            this.a = new WeakReference(dropDownEditText);
        }

        public void onChanged() {
            DropDownEditText dropDownEditText = (DropDownEditText) this.a.get();
            if (dropDownEditText != null && dropDownEditText.c != null) {
                dropDownEditText.post(this.b);
            }
        }
    }

    private class e implements OnItemClickListener {
        final /* synthetic */ DropDownEditText a;
        private OnItemClickListener b;

        private e(DropDownEditText dropDownEditText) {
            this.a = dropDownEditText;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.b != null) {
                this.b.onItemClick(adapterView, view, i, j);
            }
            CharSequence a = ((a) this.a.c).a(i);
            if (a != null) {
                if (!a.toString().equals(this.a.getText().toString())) {
                    this.a.clearComposingText();
                    this.a.setText(a);
                }
                Spannable text = this.a.getText();
                Selection.setSelection(text, text.length());
                this.a.c();
            }
        }
    }

    public DropDownEditText(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.b = new ListView(context);
        this.b.setBackgroundResource(this.g);
        this.b.setDivider(new ColorDrawable(this.e));
        this.b.setDividerHeight(this.f);
        this.b.setOnItemClickListener(this.j);
        this.a = new com.qq.reader.view.b(this.b, -1, this.h);
        this.a.setBackgroundDrawable(new ColorDrawable(0));
        this.a.setTouchable(true);
        this.a.setOutsideTouchable(false);
        this.a.setSoftInputMode(16);
        this.a.a(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (VERSION.SDK_INT >= 24) {
            e();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (VERSION.SDK_INT >= 24) {
            f();
        }
    }

    public DropDownEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DropDownEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void a() {
        try {
            if (getWindowVisibility() != 8 && !d()) {
                if (VERSION.SDK_INT >= 24) {
                    b();
                }
                this.a.setInputMethodMode(1);
                if (VERSION.SDK_INT >= 24) {
                    this.a.setHeight(this.h);
                    this.a.showAtLocation(((Activity) getContext()).getWindow().getDecorView(), 0, 0, this.i);
                } else {
                    this.a.showAsDropDown(this, 0, this.d);
                }
                if (this.p != null) {
                    this.p.b();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        View rootView = getRootView();
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        int[] iArr = new int[2];
        getLocationInWindow(iArr);
        int height = (iArr[1] + getHeight()) + this.d;
        this.h = rect.bottom - height;
        this.i = height;
        this.a.setHeight(this.h);
    }

    public void c() {
        try {
            if (this.a.isShowing()) {
                this.a.dismiss();
                if (this.p != null) {
                    this.p.a();
                }
            }
        } catch (Exception e) {
        }
    }

    private void e() {
        ((ViewGroup) getRootView()).getChildAt(0).getViewTreeObserver().addOnGlobalLayoutListener(this.o);
    }

    private void f() {
        ((ViewGroup) getRootView()).getChildAt(0).getViewTreeObserver().removeGlobalOnLayoutListener(this.o);
    }

    public void setDropDownVerticalOffset(int i) {
        this.d = i;
    }

    public void setDropDownBackgroundResource(int i) {
        this.g = i;
        this.b.setBackgroundResource(this.g);
    }

    public void setThreshold(int i) {
    }

    public void setOnDismissListener(c cVar) {
        this.p = cVar;
    }

    public boolean d() {
        return this.a.isShowing();
    }

    public <T extends BaseAdapter & a & b> void setAdapter(T t) {
        if (this.k == null) {
            this.k = new d();
        } else if (this.c != null) {
            this.c.unregisterDataSetObserver(this.k);
        }
        this.c = t;
        if (this.c != null) {
            t.registerDataSetObserver(this.k);
        }
        this.b.setAdapter(this.c);
        ((b) t).a(this.b);
    }

    private void a(int i) {
        if (i == 0) {
            c();
        } else {
            a();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.j.b = onItemClickListener;
    }
}
