package com.qq.reader.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.module.readpage.ReaderTextPageView;
import com.qq.reader.module.readpage.l;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class PagePopupWindow extends View {
    int A;
    int B;
    int C = 1;
    View D;
    private TextView E = null;
    private ScrollView F = null;
    private ImageView G = null;
    private ImageView H = null;
    private Drawable I;
    private Drawable J;
    private ArrayList<Float> K = new ArrayList();
    private View L = null;
    private final int M = 0;
    private final int N = 1;
    Activity a = null;
    PopupWindow b = null;
    String c = "";
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    l l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int t;
    int u;
    final int v = 25;
    final int w = 20;
    int x;
    int y;
    int z;

    public PagePopupWindow(Activity activity, View view) {
        super(activity);
        this.a = activity;
        b();
    }

    public void setShowStr(String str) {
        this.c = str;
    }

    public void setParentViewDate(int i, int i2) {
        this.f = i;
        this.g = i2;
        this.i = (this.g / 2) - 20;
    }

    public void setBaseRect(int i, int i2, int i3, int i4) {
        this.m = i;
        this.n = i2;
        this.o = i3;
        this.p = i4;
        this.x = (this.m + this.o) / 2;
    }

    private void b() {
        WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
        this.d = windowManager.getDefaultDisplay().getWidth();
        this.e = windowManager.getDefaultDisplay().getHeight();
        this.h = this.d;
        this.D = LayoutInflater.from(this.a.getApplicationContext()).inflate(R.layout.page_popup_container, null);
        this.F = (ScrollView) this.D.findViewById(R.id.scroll);
        this.G = (ImageView) this.D.findViewById(R.id.popup_note_uparrow);
        this.H = (ImageView) this.D.findViewById(R.id.popup_note_downarrow);
        this.F.setScrollContainer(true);
        this.F.setFocusable(true);
        this.E = (TextView) this.D.findViewById(R.id.note_content);
        this.E.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PagePopupWindow a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.l != null) {
                    this.a.a();
                    this.a.l.a(this.a.c);
                    this.a.l = null;
                }
            }
        });
        this.b = new PopupWindow(this.D);
        this.b.setTouchable(true);
        this.D.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PagePopupWindow a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                ReaderTextPageView.r();
                this.a.a();
                this.a.l = null;
            }
        });
    }

    private void a(boolean z) {
        if (!z) {
            this.E.post(new Runnable(this) {
                final /* synthetic */ PagePopupWindow a;

                {
                    this.a = r1;
                }

                public void run() {
                    int width = this.a.F.getWidth();
                    int height = this.a.F.getHeight();
                    this.a.q = width;
                    if (height >= this.a.i) {
                        this.a.r = this.a.i;
                    } else {
                        this.a.r = height;
                    }
                    if (this.a.q < this.a.d && (this.a.m + this.a.o) + this.a.q > this.a.d * 2) {
                        this.a.s = this.a.d - this.a.q;
                    } else if (this.a.q >= this.a.d || (this.a.m + this.a.o) + this.a.q > this.a.d * 2) {
                        this.a.s = 0;
                    } else {
                        this.a.s = ((this.a.m + this.a.o) / 2) - (this.a.q / 2);
                    }
                    if (this.a.s < 0) {
                        this.a.s = 0;
                    }
                    if (this.a.C == 1) {
                        this.a.t = this.a.n - this.a.r;
                        this.a.u = this.a.e - this.a.n;
                    } else {
                        this.a.t = this.a.p + (this.a.p - this.a.n);
                    }
                    this.a.A = this.a.m + (((this.a.o - this.a.m) - this.a.y) / 2);
                    if (this.a.C == 1) {
                        this.a.B = this.a.n - this.a.z;
                    } else {
                        this.a.B = this.a.t;
                        this.a.u = (this.a.e - this.a.t) - this.a.r;
                    }
                    this.a.c();
                    this.a.d();
                }
            });
        }
    }

    private void c() {
        if (d.ao(ReaderApplication.getApplicationImp())) {
            this.t -= a.ca;
            this.u += a.ca;
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.s, this.t, 0, this.u);
        this.F.setLayoutParams(layoutParams);
    }

    private void d() {
        if (d.ao(ReaderApplication.getApplicationImp())) {
            this.B -= a.ca;
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.A, this.B, 0, 0);
        this.L.setLayoutParams(layoutParams);
    }

    public void a(View view, l lVar) {
        int i;
        this.l = lVar;
        e();
        switch (this.C) {
            case 0:
                this.G.setVisibility(0);
                this.H.setVisibility(8);
                i = R.drawable.arrow_up;
                this.L = this.G;
                break;
            default:
                this.G.setVisibility(8);
                this.H.setVisibility(0);
                i = R.drawable.arrow_down;
                this.L = this.H;
                break;
        }
        this.I = this.a.getApplicationContext().getResources().getDrawable(i);
        this.J = this.a.getApplicationContext().getResources().getDrawable(R.drawable.reading_note_bg);
        this.z = this.I.getIntrinsicHeight();
        this.y = this.I.getIntrinsicWidth();
        this.k = this.h;
        this.j = this.i - this.z;
        this.E.setText(this.c);
        this.F.scrollTo(0, 0);
        this.b.setHeight(this.e);
        this.b.setWidth(this.d);
        this.b.showAtLocation(view, 0, 0, 0);
        a(false);
    }

    public void a() {
        this.b.dismiss();
        this.a = null;
    }

    private void e() {
        if (this.n * 2 >= this.g) {
            this.C = 1;
        } else {
            this.C = 0;
        }
    }
}
