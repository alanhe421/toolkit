package com.qq.reader.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.fragment.a.a;
import java.util.ArrayList;
import java.util.Iterator;

public class ClassifyGrid extends LinearLayout {
    private DataSetObserver a;
    private a b = null;
    private ArrayList<LinearLayout> c = new ArrayList();

    public ClassifyGrid(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public ClassifyGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ClassifyGrid(Context context) {
        super(context);
        a();
    }

    private void a() {
        setOrientation(1);
    }

    public a getAdapter() {
        return this.b;
    }

    public void setAdapter(a aVar) {
        this.b = aVar;
        if (this.b != null) {
            if (this.a != null) {
                this.b.unregisterDataSetObserver(this.a);
            }
            this.a = new DataSetObserver(this) {
                final /* synthetic */ ClassifyGrid a;

                {
                    this.a = r1;
                }

                public void onChanged() {
                    this.a.b();
                }
            };
            this.b.registerDataSetObserver(this.a);
        }
        b();
    }

    private void b() {
        c.a("grid", "getview");
        if (this.b != null) {
            c();
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 19;
            float f = 0.0f;
            float b = this.b.b();
            int i = 0;
            View view = null;
            while (i < this.b.getCount()) {
                View view2;
                View view3 = this.b.getView(i, null, this);
                float b2 = this.b.b(i);
                f += b2;
                c.a("grid", "width " + f + "  childwidth " + b2 + " wholewidth " + b);
                if (view == null || f > b) {
                    View linearLayout = new LinearLayout(getContext());
                    linearLayout.setOrientation(0);
                    linearLayout.setLayoutParams(layoutParams);
                    this.c.add(linearLayout);
                    addView(linearLayout);
                    float f2 = b2;
                    view2 = linearLayout;
                    f = f2;
                } else {
                    view2 = view;
                }
                view2.addView(view3);
                i++;
                view = view2;
            }
        }
    }

    private void c() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((LinearLayout) it.next()).removeAllViews();
        }
        removeAllViews();
        this.c.clear();
    }
}
