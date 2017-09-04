package com.qq.reader.widget.titler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.module.bookstore.qnative.model.TitlerControlModel;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

public class StateChangeTitler extends RelativeLayout {
    int a;
    private View b;
    private TitlerControlModel c;
    private Drawable d;
    private Drawable e;
    private View f;
    private List<b> g;

    public void setConTrollerModel(TitlerControlModel titlerControlModel) {
        this.c = titlerControlModel;
    }

    public StateChangeTitler(Context context) {
        super(context);
        b();
    }

    public StateChangeTitler(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    private void b() {
        this.d = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.titler_bg);
        this.e = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.translucent);
        this.g = new ArrayList();
        this.b = View.inflate(getContext(), R.layout.common_titler, this);
        this.b = this.b.findViewById(R.id.common_titler);
        this.b.setBackgroundDrawable(null);
        this.f = this.b.findViewById(R.id.profile_header_title);
        View findViewById = this.b.findViewById(R.id.profile_header_left_back);
        if (findViewById instanceof b) {
            this.g.add((b) findViewById);
        }
        findViewById = this.b.findViewById(R.id.profile_header_right_image);
        if (findViewById instanceof b) {
            this.g.add((b) findViewById);
        }
        for (b enable : this.g) {
            enable.setEnable(true);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.setCallback(null);
            this.d = null;
        }
        if (this.e != null) {
            this.e.setCallback(null);
            this.e = null;
        }
    }

    public void a(AbsListView absListView, int i, int i2, int i3) {
        if (this.c == null) {
            return;
        }
        if (this.c.mode == TitlerControlModel.POSITION_MODE) {
            if (i > this.c.startPosition) {
                a(this.c.showDuration);
            } else {
                c();
            }
        } else if (this.c.mode == TitlerControlModel.POSITION_Y_MODE) {
            int childCount = absListView.getChildCount();
            if (this.c.startPosition != i || childCount <= 0) {
                if (i > this.c.startPosition) {
                    a(this.c.showDuration);
                }
            } else if (Math.abs(absListView.getChildAt(0).getTop()) > this.c.startY) {
                a(this.c.showDuration);
            } else {
                c();
            }
        }
    }

    public void a() {
        a(0);
    }

    private void a(int i) {
        try {
            if (this.b != null && this.a != 1) {
                this.a = 1;
                Drawable background = this.b.getBackground();
                if ((background instanceof TransitionDrawable) && background != null) {
                    background = ((TransitionDrawable) background).getDrawable(1);
                }
                Bitmap drawingCache = this.b.getDrawingCache();
                if (background != this.d) {
                    background = new TransitionDrawable(new Drawable[]{new BitmapDrawable(drawingCache), this.d});
                    this.b.setBackgroundDrawable(background);
                    d();
                    background.startTransition(i);
                    if (!this.g.isEmpty()) {
                        for (b enable : this.g) {
                            enable.setEnable(false);
                        }
                    }
                }
                if (this.c != null && this.c.withTitle && this.f != null) {
                    this.f.setVisibility(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c() {
        try {
            if (this.a != -1) {
                this.a = -1;
                Drawable background = this.b.getBackground();
                if ((background instanceof TransitionDrawable) && background != null) {
                    background = ((TransitionDrawable) background).getDrawable(1);
                }
                Bitmap drawingCache = this.b.getDrawingCache();
                if (background != this.e) {
                    background = new TransitionDrawable(new Drawable[]{new BitmapDrawable(drawingCache), this.e});
                    this.b.setBackgroundDrawable(background);
                    d();
                    background.startTransition(this.c.hideDuration);
                    if (!this.g.isEmpty()) {
                        for (b enable : this.g) {
                            enable.setEnable(true);
                        }
                    }
                }
                if (this.c.withTitle && this.f != null) {
                    this.f.setVisibility(8);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void d() {
        if (this.b != null && this.c != null && this.c.needImmerseMode && VERSION.SDK_INT >= 19) {
            this.b.setPadding(0, a.ca, 0, 0);
        }
    }

    public void setBackgroundResource(int i) {
        this.d = ReaderApplication.getApplicationImp().getResources().getDrawable(i);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.d = drawable;
    }
}
