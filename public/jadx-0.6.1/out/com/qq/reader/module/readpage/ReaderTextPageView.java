package com.qq.reader.module.readpage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.videoplay.NativeVideoPlayerActivity;
import com.qq.reader.module.videoplay.WebViewVideoPlayActivity;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.view.PagePopupWindow;
import com.qq.reader.view.af;
import com.qq.reader.view.animation.AnimationProvider;
import com.qq.reader.view.animation.BezierAnimationProvider;
import com.qq.reader.view.animation.DoublePageAnimationProvider;
import com.qq.reader.view.animation.g;
import com.qq.reader.view.animation.i;
import com.qq.reader.widget.picbrowser.PictureActivity;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.utils.TbsLog;
import format.epub.view.h;
import format.epub.view.p;
import java.util.ArrayList;
import java.util.List;
import tencent.tls.platform.SigType;

public class ReaderTextPageView extends View implements com.qq.reader.module.readpage.p.a {
    public static boolean c = false;
    public static int d = -1;
    private Intent A = null;
    protected j a;
    public AnimationProvider b = null;
    public ReaderPageSwither e = null;
    private b f;
    private i g;
    private a h;
    private y i;
    private p j;
    private aa k;
    private a l;
    private ad m;
    private Context n;
    private Activity o;
    private TextView p = null;
    private RelativeLayout q = null;
    private TextView r;
    private boolean s = false;
    private int t = 0;
    private float u = 0.0f;
    private float v = 0.0f;
    private int w;
    private int x = 0;
    private List<h> y = new ArrayList();
    private PagePopupWindow z = null;

    private class a extends Thread {
        final /* synthetic */ ReaderTextPageView a;
        private boolean b;

        private a(ReaderTextPageView readerTextPageView) {
            this.a = readerTextPageView;
            this.b = false;
        }

        public void a(boolean z) {
            this.b = z;
        }

        public void run() {
            synchronized (IBook.mRemarksList) {
                this.a.p().a();
                if (!(this.b || IBook.mRemarksList.isEmpty())) {
                    this.a.post(new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.g.g();
                            this.a.a.invalidate();
                        }
                    });
                }
            }
        }
    }

    public ReaderTextPageView(Context context, Activity activity, j jVar, a aVar, y yVar) {
        super(context);
        this.n = context;
        this.o = activity;
        this.e = ((ReaderPageActivity) activity).q;
        this.a = jVar;
        j jVar2 = this.a;
        int j = j.j();
        j jVar3 = this.a;
        int k = j.k();
        j jVar4 = this.a;
        setPadding(j, k, j.j(), this.a.l());
        this.g = new i(this.a, 6);
        this.f = new b(context, this.g, aVar);
        this.h = aVar;
        this.a.a(p());
        setDrawingCacheEnabled(false);
        this.j = new p();
        a(this.a.e().b());
        a((com.qq.reader.module.readpage.p.a) this);
        this.q = (RelativeLayout) this.o.findViewById(R.id.online_paypage_progress);
        this.p = (TextView) this.o.findViewById(R.id.online_paypage_btn);
        this.r = (TextView) this.o.findViewById(R.id.online_paypage_progress_text);
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReaderTextPageView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                h o = ((ReaderPageActivity) this.a.o).q.getBookCore().o();
                if (o != null) {
                    o.onClick();
                }
            }
        });
        this.p.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ ReaderTextPageView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                h o = ((ReaderPageActivity) this.a.o).q.getBookCore().o();
                switch (motionEvent.getAction()) {
                    case 0:
                        switch (o.c().p()) {
                            case 1001:
                            case 1003:
                            case 1004:
                            case 1005:
                            case 1006:
                            case 1009:
                                this.a.p.setText(o.c().n());
                                break;
                            default:
                                break;
                        }
                    case 1:
                    case 3:
                        this.a.p.setText(null);
                        break;
                }
                return false;
            }
        });
        this.i = yVar;
    }

    public j getPagePaint() {
        return this.a;
    }

    public boolean c() {
        if (!g()) {
            return false;
        }
        this.b = null;
        G();
        return true;
    }

    public boolean d() {
        if (g()) {
            return false;
        }
        this.b = null;
        F();
        return true;
    }

    public void e() {
        this.b = new com.qq.reader.view.animation.h(this.g, getContext(), this.j);
    }

    public void f() {
        if (g()) {
            this.b = null;
            getAnimationProvider().a(getWidth(), getHeight());
        }
    }

    public boolean g() {
        return getAnimationProvider() instanceof i;
    }

    public boolean h() {
        return this.b != null && (this.b instanceof DoublePageAnimationProvider);
    }

    public void i() {
        if (this.b instanceof DoublePageAnimationProvider) {
            if (this.b.f() || this.b.e()) {
                this.b.i();
            }
            this.b = null;
            setSize(getWidth(), getHeight());
        }
    }

    public void setAnimationProvider(AnimationProvider animationProvider) {
        this.b = animationProvider;
    }

    public AnimationProvider getAnimationProvider() {
        if (this.b == null) {
            switch (d.af(getContext().getApplicationContext())) {
                case 0:
                    this.b = new com.qq.reader.view.animation.d(this.g, getContext(), this.j);
                    break;
                case 1:
                    E();
                    break;
                case 2:
                    G();
                    if (this.a.e != null) {
                        try {
                            this.b.a(ao.a(this.a.e, 10, 10));
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    break;
                case 3:
                    F();
                    break;
                default:
                    String str = Build.DEVICE;
                    if (str != null && str.equalsIgnoreCase("mx2")) {
                        d.q(getContext().getApplicationContext(), 1);
                        E();
                        break;
                    }
                    G();
                    if (this.a.e != null) {
                        this.b.a(ao.a(this.a.e, 10, 10));
                        break;
                    }
                    break;
            }
        }
        return this.b;
    }

    private void E() {
        this.b = new g(this.g, getContext(), this.j);
    }

    private void F() {
        this.b = new i(this.g, getContext(), this.h, this.i, getmAutoReader(), this.j);
    }

    private void G() {
        this.b = new BezierAnimationProvider(this.g, getContext(), this.j);
    }

    public boolean a(com.qq.reader.module.readpage.p.a aVar) {
        return this.j.a(aVar);
    }

    public void a(List<s> list) {
        for (s a : list) {
            this.j.a(a);
        }
    }

    public void setBackgroundColor(int i) {
        this.a.a(i);
    }

    public int getBackgroundColor() {
        return this.a == null ? -1 : this.a.g();
    }

    public void setBackgroundBitmap(Bitmap bitmap) {
        this.a.a(bitmap);
    }

    public void setTextSize(int i) {
        this.a.b((float) i);
        invalidate();
    }

    public void setTextColor(int i) {
        this.a.b(i);
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(b(i), c(i2));
    }

    private int b(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        return mode == Integer.MIN_VALUE ? Math.min(d(size), size) : 0;
    }

    private int c(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int p = this.a.p();
        return mode == Integer.MIN_VALUE ? Math.min(p, size) : p;
    }

    public void j() {
        this.g.f(PageIndex.previous);
        this.g.f(PageIndex.next);
    }

    void k() {
        this.g.g();
    }

    void l() {
        this.a.r();
    }

    public void draw(Canvas canvas) {
        try {
            AnimationProvider animationProvider = getAnimationProvider();
            if (animationProvider.f()) {
                animationProvider.g();
                animationProvider.a(canvas);
                invalidate();
            } else if (animationProvider.e()) {
                animationProvider.a(canvas);
            } else if (!a(canvas, animationProvider)) {
                animationProvider.a(canvas);
            }
            p().a(canvas);
            getTtsModeController().a(canvas);
        } catch (Throwable th) {
            Throwable th2 = th;
            af.a(this.n, "好像出问题了，请再试试", 0).a();
            com.qq.reader.common.monitor.i.a("out_of_memory", false, 0, 0, null, this.n);
            th2.printStackTrace();
            Process.killProcess(Process.myPid());
        }
    }

    protected boolean a(Canvas canvas, AnimationProvider animationProvider) {
        return getmAutoReader().a(canvas, animationProvider.d(), getWidth() - animationProvider.c());
    }

    public b getmAutoReader() {
        return this.f;
    }

    public i getmAutoScrollReader() {
        if (g()) {
            return (i) getAnimationProvider();
        }
        return null;
    }

    public void m() {
    }

    public void n() {
        this.a.h();
        if (this.l != null && this.l.isAlive()) {
            this.l.a(true);
        }
    }

    public void o() {
        if (getAnimationProvider().f() || getAnimationProvider().e()) {
            getAnimationProvider().i();
        }
    }

    public void setRunInBackground(boolean z) {
        this.s = z;
        if (this.s) {
            this.a.e().a().sendEmptyMessage(10000509);
        } else {
            this.a.e().a().sendEmptyMessage(10000508);
        }
    }

    private int d(int i) {
        return (getPaddingLeft() + i) + getPaddingRight();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!this.s) {
            setSize(i, i2);
        }
    }

    public void setSize(int i, int i2) {
        this.a.a(i, i2);
        this.g.a(i, i2);
        getAnimationProvider().a(i, i2);
    }

    public aa p() {
        if (this.k == null) {
            this.k = new aa(this.n, this.o, this, this.g, this.a);
        }
        return this.k;
    }

    public void a(IBook iBook, int i, int i2) {
        p().a(iBook, i, i2);
        this.l = new a();
        this.l.start();
    }

    public void q() {
        p().b();
    }

    public boolean a(int i, int i2) {
        if (p().h()) {
            p().d();
        }
        return this.a.b(i, i2);
    }

    public static void r() {
        d = -1;
    }

    public boolean a(MotionEvent motionEvent) {
        if (d == 3) {
            switch (motionEvent.getAction()) {
                case 1:
                case 3:
                    r();
                    break;
            }
        }
        return true;
    }

    public boolean b(MotionEvent motionEvent) {
        if (d == -1) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.w = 1;
                    this.u = motionEvent.getX();
                    this.v = motionEvent.getY();
                    this.t = a(motionEvent.getX(), motionEvent.getY());
                    if (this.t == 0) {
                        return false;
                    }
                    this.x = this.g.e().a();
                    f.a("ReaderTextPageView", "Action Down->>lastOffsetX : " + this.x);
                    return true;
                case 1:
                    this.w = 3;
                    if (this.t != 0) {
                        if (this.t == 2) {
                            s();
                        } else if (this.t == 1 && this.A != null) {
                            this.o.startActivity(this.A);
                            this.o.overridePendingTransition(17432576, 17432577);
                        }
                        this.t = 0;
                        return true;
                    }
                    break;
                case 2:
                    if (this.t != 0) {
                        float x = motionEvent.getX();
                        int i = ((int) (x - this.u)) + this.x;
                        int y = ((int) (motionEvent.getY() - this.v)) + this.x;
                        f.a("ReaderTextPageView", "Action Move->>disX : " + i);
                        float scaledTouchSlop = (float) ViewConfiguration.get(this.n.getApplicationContext()).getScaledTouchSlop();
                        if (this.t == 3 && this.w == 2) {
                            this.g.e().a(i);
                            this.g.f(PageIndex.current);
                            invalidate();
                            return true;
                        } else if (((float) Math.abs(i)) < 2.0f * scaledTouchSlop && ((float) Math.abs(y)) < 2.0f * scaledTouchSlop) {
                            return true;
                        } else {
                            this.w = 2;
                            if (this.t != 3) {
                                this.t = 0;
                                return true;
                            } else if (((float) Math.abs(i)) < 2.0f * scaledTouchSlop) {
                                return true;
                            } else {
                                this.g.e().a(i);
                                this.g.f(PageIndex.current);
                                invalidate();
                                return true;
                            }
                        }
                    }
                    break;
                case 3:
                    this.w = 3;
                    if (this.t != 0) {
                        this.t = 0;
                        return true;
                    }
                    break;
            }
            return false;
        } else if (d != 3) {
            return true;
        } else {
            a(motionEvent);
            return true;
        }
    }

    public boolean a(View view, float f, float f2) {
        return false;
    }

    public i getmPageCache() {
        return this.g;
    }

    @SuppressLint({"NewApi"})
    public int a(float f, float f2) {
        this.y.clear();
        h binarySearch = this.g.b().binarySearch(f, f2);
        if (binarySearch != null) {
            if (binarySearch.o instanceof p) {
                p pVar = (p) binarySearch.o;
                if (pVar.l != null && pVar.l.trim().length() > 0) {
                    this.y.add(binarySearch);
                    this.z = new PagePopupWindow(this.o, this);
                    PagePopupWindow pagePopupWindow = this.z;
                    int i = this.a.a;
                    j jVar = this.a;
                    i -= j.j();
                    jVar = this.a;
                    i -= j.i();
                    int i2 = this.a.b;
                    j jVar2 = this.a;
                    pagePopupWindow.setParentViewDate(i, (i2 - j.k()) - this.a.l());
                    this.z.setBaseRect((int) binarySearch.a, (int) binarySearch.c, (int) binarySearch.b, (int) binarySearch.d);
                    this.z.setShowStr(pVar.l);
                    return 2;
                } else if (pVar.k) {
                    int[] iArr = new int[]{(int) binarySearch.a, ((int) binarySearch.c) + 8, ((int) binarySearch.b) - ((int) binarySearch.a), (((int) binarySearch.d) - ((int) binarySearch.c)) + 8};
                    this.A = new Intent();
                    this.A.setClass(this.o, PictureActivity.class);
                    this.A.putExtra("EXTRA_IMAGE_VIEW_POSITION", iArr);
                    this.A.putExtra("zipkey", com.qq.reader.common.drm.a.b());
                    this.A.setData(Uri.parse(pVar.h));
                    return 1;
                } else if (pVar.h()) {
                    if (getContext() != null) {
                        String g = pVar.g();
                        if (g == null || g.length() <= 4 || !(g.startsWith("http://") || g.startsWith("https://"))) {
                            this.A = new Intent(getContext(), NativeVideoPlayerActivity.class);
                            this.A.putExtra("zipkey", com.qq.reader.common.drm.a.b());
                            this.A.putExtra("path", pVar.g());
                            this.A.putExtra("id", pVar.a);
                            this.A.putExtra("file_type", 2);
                        } else if (g.endsWith(".mp4") || g.endsWith(".3gp") || g.endsWith(".ts") || g.endsWith(".webm") || g.endsWith(".mkv")) {
                            this.A = new Intent(getContext(), NativeVideoPlayerActivity.class);
                            this.A.putExtra("zipkey", com.qq.reader.common.drm.a.b());
                            this.A.putExtra("path", pVar.g());
                            this.A.putExtra("file_type", 3);
                        } else {
                            this.A = new Intent(getContext(), WebViewVideoPlayActivity.class);
                            this.A.putExtra(SocialConstants.PARAM_URL, pVar.g());
                        }
                        this.A.setFlags(SigType.TLS);
                        com.qq.reader.common.monitor.i.a("event_B191", null, getContext());
                    }
                    return 1;
                }
            } else if (binarySearch.e() == 1) {
                return 3;
            }
        }
        return 0;
    }

    public void s() {
        if (this.z != null) {
            this.z.a(this, null);
        }
    }

    public void t() {
        h o = ((ReaderPageActivity) this.o).q.getBookCore().o();
        this.g.f(o.c().u());
        CharSequence n = o.c().n();
        this.r.setTextColor(getTextColor());
        if (n == null || n.trim().length() <= 0) {
            this.r.setText(this.n.getResources().getString(R.string.paypage_loading));
        } else {
            this.r.setText(n);
        }
        this.e.getmPageContext().a(true);
        this.q.setVisibility(0);
        this.p.setVisibility(4);
    }

    public void u() {
        this.p.setVisibility(4);
    }

    public void v() {
        AnimationProvider animationProvider = getAnimationProvider();
        h o = ((ReaderPageActivity) this.o).q.getBookCore().o();
        int q = o.c().q();
        if (animationProvider.f() || animationProvider.e()) {
            this.g.f(o.c().u());
            return;
        }
        if (q != 1008) {
            this.g.f(PageIndex.current);
        }
        this.e.getmPageContext().a(false);
        this.q.setVisibility(4);
        this.p.setVisibility(0);
    }

    public void w() {
        AnimationProvider animationProvider = getAnimationProvider();
        h o = ((ReaderPageActivity) this.o).q.getBookCore().o();
        if (animationProvider.f() || animationProvider.e()) {
            this.g.f(o.c().u());
            return;
        }
        this.g.f(PageIndex.current);
        this.e.getmPageContext().a(false);
        this.q.setVisibility(4);
        this.p.setVisibility(0);
    }

    private int getTextColor() {
        int L = d.L(this.o);
        int[] N = d.N(this.n);
        d.k = N[0];
        if (d.n) {
            return -8815488;
        }
        if (L >= 8) {
            return N[0];
        }
        TypedArray obtainTypedArray = getContext().getResources().obtainTypedArray(R.array.textStyles);
        L = obtainTypedArray.getColor(L, 0);
        obtainTypedArray.recycle();
        return L;
    }

    public void x() {
        AnimationProvider animationProvider = getAnimationProvider();
        h o = ((ReaderPageActivity) this.o).q.getBookCore().o();
        if (o.c().q() == 1000) {
            if (animationProvider.f() || animationProvider.e()) {
                this.g.f(o.c().u());
            } else {
                this.g.f(PageIndex.current);
            }
        }
        this.e.getmPageContext().a(false);
        this.q.setVisibility(4);
        this.p.setVisibility(4);
    }

    public void y() {
        AnimationProvider animationProvider = getAnimationProvider();
        h o = ((ReaderPageActivity) this.o).q.getBookCore().o();
        int q = o.c().q();
        if (q == 1000 || o.c().u() == PageIndex.current) {
            if (animationProvider.f() || animationProvider.e()) {
                this.g.f(o.c().u());
                return;
            }
            this.g.f(PageIndex.current);
            this.e.getmPageContext().a(false);
            this.q.setVisibility(4);
            this.p.setVisibility(0);
        } else if (q == 1007) {
            this.p.setVisibility(0);
        }
    }

    public void z() {
        AnimationProvider animationProvider = getAnimationProvider();
        h o = ((ReaderPageActivity) this.o).q.getBookCore().o();
        if (animationProvider.f() || animationProvider.e()) {
            this.g.f(o.c().u());
            return;
        }
        this.g.f(PageIndex.current);
        this.e.getmPageContext().a(false);
        this.q.setVisibility(4);
        this.p.setVisibility(0);
    }

    public void A() {
        AnimationProvider animationProvider = getAnimationProvider();
        h o = ((ReaderPageActivity) this.o).q.getBookCore().o();
        if (animationProvider.f() || animationProvider.e()) {
            this.g.f(o.c().u());
            return;
        }
        this.g.f(PageIndex.current);
        this.e.getmPageContext().a(false);
        this.q.setVisibility(4);
        this.p.setVisibility(0);
    }

    public void a() {
        this.e.getmPageContext().a(false);
        this.q.setVisibility(4);
        this.p.setVisibility(4);
    }

    public void b() {
        h o = ((ReaderPageActivity) this.o).q.getBookCore().o();
        if (o.d() || o.g() != TbsLog.TBSLOG_CODE_SDK_INIT) {
            j();
        }
        d b = this.g.b(PageIndex.previous);
        if (!(b == null || b.a() == 0)) {
            b.a(0);
            this.g.f(PageIndex.previous);
        }
        b = this.g.b(PageIndex.current);
        if (!(b == null || b.a() == 0)) {
            b.a(0);
            this.g.f(PageIndex.current);
        }
        b = this.g.b(PageIndex.next);
        if (!(b == null || b.a() == 0)) {
            b.a(0);
            this.g.f(PageIndex.next);
        }
        switch (o.c().p()) {
            case 1000:
                this.e.getmPageContext().a(true);
                this.q.setVisibility(0);
                this.e.g();
                this.e.invalidate();
                return;
            case 1001:
            case 1003:
            case 1004:
            case 1005:
            case 1006:
            case 1009:
                this.p.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public synchronized boolean a(int i) {
        int i2;
        if (i == aa.n) {
            i2 = -aa.r;
        } else {
            i2 = aa.r;
        }
        return ((i) this.b).a((float) i2);
    }

    public ad getTtsModeController() {
        if (this.m == null) {
            this.m = new ad((ReaderPageActivity) this.o, this, this.g);
        }
        return this.m;
    }

    public boolean B() {
        return d == 4;
    }

    public void C() {
        getTtsModeController().a(d);
        d = 4;
    }

    public void D() {
        d = getTtsModeController().a();
    }
}
