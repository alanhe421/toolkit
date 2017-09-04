package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.c.b;
import com.tencent.feedback.proguard.R;

public class EmptyView extends LinearLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private View f;
    private Context g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private boolean l;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EmptyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = false;
        this.i = true;
        this.j = 0;
        this.k = 4;
        this.l = false;
        this.g = context;
        a();
        a(attributeSet);
        setGravity(17);
        setOnClickListener(null);
        if (getBackground() == null) {
            setBackgroundResource(R.color.common_backgraound_white);
        }
    }

    private void a() {
        this.f = View.inflate(this.g, R.layout.empty_page_container, this);
        this.a = (TextView) findViewById(R.id.empty_page_content);
        this.b = (TextView) findViewById(R.id.empty_page_content_title);
        this.c = (TextView) findViewById(R.id.empty_page_button);
        this.d = (TextView) findViewById(R.id.empty_page_reload);
        this.e = (ImageView) findViewById(R.id.empty_page_icon);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.g.getTheme().obtainStyledAttributes(attributeSet, b.EmptyPage, 0, 0);
        try {
            int i = obtainStyledAttributes.getInt(6, -1);
            CharSequence string = obtainStyledAttributes.getString(3);
            CharSequence string2 = obtainStyledAttributes.getString(2);
            CharSequence string3 = obtainStyledAttributes.getString(0);
            CharSequence string4 = obtainStyledAttributes.getString(1);
            Drawable drawable = obtainStyledAttributes.getDrawable(4);
            boolean z = obtainStyledAttributes.getBoolean(5, false);
            if (i != -1) {
                a(i);
            }
            a(string);
            b(string2);
            d(string3);
            c(string4);
            if (drawable == null) {
                this.e.setVisibility(8);
            } else {
                this.e.setVisibility(0);
                a(drawable);
            }
            a(z);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public EmptyView a(int i) {
        switch (i) {
            case 0:
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                this.b.setVisibility(8);
                break;
            case 1:
                this.b.setVisibility(0);
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                break;
            case 2:
                this.d.setVisibility(0);
                this.b.setVisibility(8);
                this.c.setVisibility(8);
                break;
            case 3:
                this.c.setVisibility(0);
                this.d.setVisibility(8);
                this.b.setVisibility(8);
                break;
            case 4:
                this.c.setVisibility(0);
                this.b.setVisibility(0);
                this.d.setVisibility(8);
                break;
            case 5:
                this.b.setVisibility(8);
                this.c.setVisibility(8);
                this.d.setVisibility(0);
                this.d.setTextColor(getResources().getColor(R.color.text_color_c401));
                break;
            default:
                this.a.setVisibility(0);
                this.e.setVisibility(0);
                break;
        }
        return this;
    }

    public EmptyView b(int i) {
        this.e.setVisibility(0);
        this.e.setImageResource(i);
        return this;
    }

    public EmptyView a(Drawable drawable) {
        this.e.setVisibility(0);
        this.e.setImageDrawable(drawable);
        return this;
    }

    public EmptyView a(CharSequence charSequence) {
        this.a.setText(charSequence);
        return this;
    }

    public EmptyView b(CharSequence charSequence) {
        this.b.setText(charSequence);
        return this;
    }

    public EmptyView c(CharSequence charSequence) {
        this.c.setText(charSequence);
        return this;
    }

    public EmptyView d(CharSequence charSequence) {
        this.d.setText(charSequence);
        return this;
    }

    public EmptyView a(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
        return this;
    }

    public EmptyView b(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
        return this;
    }

    public EmptyView a(boolean z) {
        this.h = z;
        return this;
    }

    protected void onMeasure(int i, int i2) {
        if (!isShown()) {
            i2 = MeasureSpec.makeMeasureSpec(0, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.h && this.i) {
            b();
            if (this.l) {
                return;
            }
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        if (this.l) {
            this.l = false;
            requestLayout();
            return;
        }
        super.onDraw(canvas);
    }

    private void b() {
        if (isShown()) {
            this.i = false;
            int top = getTop();
            View view = (View) getParent();
            while (!(view instanceof ListView) && this.k > 0) {
                top += view.getTop();
                view = (View) view.getParent();
                this.k--;
            }
            if (view instanceof ListView) {
                int measuredHeight = view.getMeasuredHeight() - top;
                if (measuredHeight >= getMeasuredHeight()) {
                    getLayoutParams().height = measuredHeight;
                    this.f.getLayoutParams().height = measuredHeight;
                    setMinimumHeight(measuredHeight);
                    this.l = true;
                }
            }
        }
    }
}
