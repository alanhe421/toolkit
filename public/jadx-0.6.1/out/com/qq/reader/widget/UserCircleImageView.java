package com.qq.reader.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.qq.reader.ReaderApplication;
import com.qq.reader.c.b;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

public class UserCircleImageView extends ImageView implements OnTouchListener {
    private static final ScaleType a = ScaleType.CENTER_CROP;
    private static final Config b = Config.ARGB_8888;
    private final RectF c;
    private final RectF d;
    private final Matrix e;
    private final Paint f;
    private final Paint g;
    private final Paint h;
    private int i;
    private int j;
    private int k;
    private Bitmap l;
    private BitmapShader m;
    private int n;
    private int o;
    private float p;
    private float q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private OnTouchListener v;

    public UserCircleImageView(Context context) {
        super(context);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        this.i = 0;
        this.j = 0;
        this.k = 0;
    }

    public UserCircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UserCircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        this.i = 0;
        this.j = 0;
        this.k = 0;
        super.setScaleType(a);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.CircleImage, i, 0);
        this.j = obtainStyledAttributes.getDimensionPixelSize(0, ao.a(0.5f));
        this.i = obtainStyledAttributes.getColor(1, ReaderApplication.getApplicationImp().getResources().getColor(R.color.avatar_circle_mask_color));
        this.k = obtainStyledAttributes.getColor(2, Color.parseColor("#7F333333"));
        obtainStyledAttributes.recycle();
        super.setOnTouchListener(this);
        this.r = true;
        if (this.s) {
            a();
            this.s = false;
        }
    }

    public ScaleType getScaleType() {
        return a;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }

    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.p, this.f);
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.q, this.g);
            if (this.t) {
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.p, this.h);
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a();
    }

    public int getBorderColor() {
        return this.i;
    }

    public void setBorderColor(int i) {
        if (i != this.i) {
            this.i = i;
            this.g.setColor(this.i);
            invalidate();
        }
    }

    public int getBorderWidth() {
        return this.j;
    }

    public void setBorderWidth(int i) {
        if (i != this.j) {
            this.j = i;
            a();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.l = bitmap;
        a();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.l = a(drawable);
        a();
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        this.l = a(getDrawable());
        a();
    }

    private Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap;
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(1, 1, b);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), b);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void a() {
        if (!this.r) {
            this.s = true;
        } else if (this.l != null) {
            this.m = new BitmapShader(this.l, TileMode.CLAMP, TileMode.CLAMP);
            this.f.setAntiAlias(true);
            this.f.setShader(this.m);
            this.g.setStyle(Style.STROKE);
            this.g.setAntiAlias(true);
            this.g.setColor(this.i);
            this.g.setStrokeWidth((float) this.j);
            this.o = this.l.getHeight();
            this.n = this.l.getWidth();
            this.d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.q = Math.min((this.d.height() - ((float) this.j)) / 2.0f, (this.d.width() - ((float) this.j)) / 2.0f);
            this.c.set((float) this.j, (float) this.j, this.d.width() - ((float) this.j), this.d.height() - ((float) this.j));
            this.p = Math.min(this.c.height() / 2.0f, this.c.width() / 2.0f);
            this.h.setColor(this.k);
            this.h.setStyle(Style.FILL);
            this.h.setAntiAlias(true);
            b();
            invalidate();
        }
    }

    private void b() {
        float height;
        float width;
        float f = 0.0f;
        this.e.set(null);
        if (((float) this.n) * this.c.height() > this.c.width() * ((float) this.o)) {
            height = this.c.height() / ((float) this.o);
            width = (this.c.width() - (((float) this.n) * height)) * 0.5f;
        } else {
            height = this.c.width() / ((float) this.n);
            width = 0.0f;
            f = (this.c.height() - (((float) this.o) * height)) * 0.5f;
        }
        this.e.setScale(height, height);
        this.e.postTranslate((float) (((int) (width + 0.5f)) + this.j), (float) (((int) (f + 0.5f)) + this.j));
        this.m.setLocalMatrix(this.e);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean onTouch;
        if (this.v != null) {
            onTouch = this.v.onTouch(view, motionEvent);
        } else {
            onTouch = false;
        }
        if (this.u) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.t = true;
                    invalidate();
                    break;
                case 1:
                case 3:
                    this.t = false;
                    invalidate();
                    break;
            }
        }
        return onTouch;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.v = onTouchListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        if (onClickListener == null) {
            this.u = false;
        } else {
            this.u = true;
        }
    }
}
