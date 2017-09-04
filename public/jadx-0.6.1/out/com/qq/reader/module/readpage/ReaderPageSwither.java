package com.qq.reader.module.readpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.utils.ao;
import com.qq.reader.readengine.fileparse.e;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.view.af;
import com.qq.reader.view.animation.AnimationProvider;
import com.qq.reader.view.animation.AnimationProvider.Mode;
import com.qq.reader.view.animation.i;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.theme.SkinnableBitmapDrawable;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

public class ReaderPageSwither extends FrameLayout implements OnLongClickListener, a, com.qq.reader.module.readpage.h.a, com.qq.reader.module.readpage.s.a, y, com.qq.reader.view.ColorPickerView.b, com.qq.reader.view.ColorPickerView.c, com.qq.reader.view.a.a {
    private int A = 0;
    private volatile a B;
    private volatile boolean C = false;
    private com.qq.reader.view.a D;
    private f a;
    private Context b;
    private d c;
    private e d;
    private af e;
    private int f;
    private com.qq.reader.readengine.kernel.b g;
    private j h;
    private u i;
    private final int j = 13;
    private volatile int k = 0;
    private Timer l = null;
    private TimerTask m = null;
    private af n;
    private volatile boolean o = false;
    private int p = 0;
    private int q = 0;
    private b r;
    private c s;
    private float t = 0.0f;
    private float u = 0.0f;
    private float v = 0.0f;
    private float w = 0.0f;
    private VelocityTracker x;
    private boolean y;
    private int z = 0;

    public interface d {
        void a(double d);

        void a(int i);

        void a(String str);

        void a(boolean z);
    }

    private class a implements Runnable {
        final /* synthetic */ ReaderPageSwither a;

        private a(ReaderPageSwither readerPageSwither) {
            this.a = readerPageSwither;
        }

        public void run() {
            if (this.a.C) {
                this.a.performLongClick();
                this.a.C = false;
            }
        }
    }

    public interface b {
        boolean a(View view, MotionEvent motionEvent);
    }

    public interface c {
        boolean a(int i);
    }

    public interface f {
        View a(ReaderPageSwither readerPageSwither);
    }

    public com.qq.reader.readengine.kernel.b getBookCore() {
        return this.g;
    }

    public b getAutoReader() {
        return getTopPage().getmAutoReader();
    }

    public i getAutoScrollReader() {
        return getTopPage().getmAutoScrollReader();
    }

    public j getmPageContext() {
        return this.h;
    }

    public u getPageLayers() {
        return this.i;
    }

    public ReaderPageSwither(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        c();
    }

    public ReaderPageSwither(Context context) {
        super(context);
        this.b = context;
        c();
    }

    public ReaderPageSwither(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
        c();
    }

    public void c() {
        String path;
        setDrawingCacheQuality(524288);
        Intent intent = ((Activity) this.b).getIntent();
        Uri uri = null;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            try {
                Uri uri2;
                if (extras.containsKey("android.intent.extra.STREAM")) {
                    uri2 = (Uri) extras.get("android.intent.extra.STREAM");
                } else {
                    uri2 = null;
                }
                uri = uri2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ("android.intent.action.VIEW".equals(intent.getAction()) || uri != null) {
            if (uri == null) {
                uri = intent.getData();
            }
            path = uri.getPath();
        } else {
            try {
                path = intent.getExtras().getString("filepath");
            } catch (Exception e2) {
                path = "";
            }
        }
        this.g = com.qq.reader.readengine.kernel.c.a(path);
        this.g.o().a((com.qq.reader.module.readpage.h.a) this);
        this.i = new u();
        this.h = v.a(this.b, this.g);
        this.h.a(this.i);
        setFocusable(true);
        setClickable(true);
        this.f = ViewConfiguration.get(this.b.getApplicationContext()).getScaledTouchSlop();
        setOnLongClickListener(this);
        if (!com.qq.reader.appconfig.b.a()) {
            I();
        }
        setDrawingCacheEnabled(false);
    }

    public void d() {
        ViewGroup viewGroup = (ViewGroup) ((Activity) this.b).findViewById(R.id.root);
        this.i.a(new t(this.b));
        this.i.a(new com.qq.reader.module.readpage.a.a(this.b));
        this.i.a(new com.qq.reader.module.readpage.a.b(this.b));
        for (com.qq.reader.readengine.kernel.d.a aVar : this.i.b()) {
            viewGroup.addView(aVar.c());
            aVar.a((com.qq.reader.module.readpage.s.a) this);
            aVar.a((Activity) this.b);
            aVar.a(((ReaderPageActivity) this.b).getHandler());
            this.g.b().a(aVar);
        }
    }

    public void a(s sVar) {
        g();
        invalidate();
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (getChildCount() >= 2) {
            throw new IllegalStateException("Can't add more than 2 views to a ViewSwitcher");
        }
        super.addView(view, i, layoutParams);
        view.setVisibility(0);
    }

    public boolean a(int i, int i2) {
        return getTopPage().a(i, i2);
    }

    private View E() {
        View a = this.a.a(this);
        LayoutParams layoutParams = (FrameLayout.LayoutParams) a.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(-1, -1);
        }
        addView(a, 0, layoutParams);
        return a;
    }

    public void setFactory(f fVar) {
        this.a = fVar;
        E();
    }

    public void setText(e eVar) throws UnsupportedEncodingException {
        if (eVar instanceof com.qq.reader.readengine.fileparse.d) {
            setText(eVar, true);
        }
    }

    public void setText(e eVar, boolean z) throws UnsupportedEncodingException {
        setText(eVar, z, true);
    }

    public void setText(e eVar, boolean z, boolean z2) throws UnsupportedEncodingException {
        setInput(eVar);
        this.g.a(true, z, z2);
        s();
        invalidate();
    }

    public void setInput(e eVar) {
        this.g.a(eVar);
    }

    public void e() {
        if (getTopPage().g()) {
            getAutoScrollReader().j();
        }
        r();
        invalidate();
    }

    public void f() {
        r();
    }

    public void g() {
        getTopPage().k();
    }

    public void a(PageIndex pageIndex) {
        getTopPage().getmPageCache().f(pageIndex);
    }

    public void h() {
        getTopPage().l();
    }

    public void i() {
        if (!k()) {
            if (this.g instanceof com.qq.reader.readengine.kernel.a.c) {
                this.k = 1;
            } else {
                this.k = com.qq.reader.appconfig.a.d.C(this.b);
            }
            boolean a = a(true);
            if (!a) {
                b(true);
            }
            this.i.a(a);
        }
    }

    public boolean a(boolean z) {
        switch (this.k) {
            case 1:
                boolean A;
                getAutoReader().b((int) com.qq.reader.appconfig.a.d.D(this.b));
                this.g.b(2);
                if (z) {
                    A = A();
                } else {
                    A = true;
                }
                if (A) {
                    getTopPage().c();
                    getAutoReader().a(this.k);
                    l();
                    F();
                    this.p = 2;
                    break;
                }
                return false;
            case 2:
                getAutoReader().b((int) com.qq.reader.appconfig.a.d.E(this.b));
                this.g.b(1);
                getTopPage().d();
                getTopPage().getmAutoScrollReader().j();
                getAutoReader().a(this.k);
                F();
                this.p = 2;
                getTopPage().invalidate();
                break;
        }
        ((ReaderPageActivity) this.b).getHandler().removeMessages(1244);
        ((ReaderPageActivity) this.b).getHandler().sendEmptyMessageDelayed(1244, 1800000);
        com.qq.reader.common.monitor.debug.c.e("AUTO", "send msg");
        return true;
    }

    private void F() {
        try {
            ReaderPageActivity readerPageActivity = (ReaderPageActivity) this.b;
            z.a(ao.a(this.b.getApplicationContext(), true), readerPageActivity.getHandler(), readerPageActivity);
        } catch (Exception e) {
        }
    }

    public int getScrollingTextLineNum() {
        if (getTopPage().g()) {
            return getAutoScrollReader().q();
        }
        return 0;
    }

    public void b(boolean z) {
        if (k()) {
            if (!(this.g instanceof com.qq.reader.readengine.kernel.a.c)) {
                com.qq.reader.appconfig.a.d.g(this.b, this.k);
            }
            getAutoReader().d();
            this.p = 0;
            this.o = false;
            try {
                ReaderPageActivity readerPageActivity = (ReaderPageActivity) this.b;
                z.a(ao.a(this.b.getApplicationContext(), false), readerPageActivity.getHandler(), readerPageActivity);
            } catch (Exception e) {
            }
            a(z, this.k);
            getTopPage().b();
            ((ReaderPageActivity) this.b).getHandler().removeMessages(1244);
            com.qq.reader.common.monitor.debug.c.e("AUTO", "remove msg");
        }
    }

    public void a(boolean z, int i) {
        if (i == 2) {
            j();
        } else {
            c(z);
        }
        getTopPage().f();
        setViewMode(com.qq.reader.appconfig.a.d.B(getApplicationContext()));
        this.i.a(false);
        ((ReaderPageActivity) this.b).getHandler().removeMessages(1244);
        com.qq.reader.common.monitor.debug.c.e("AUTO", "remove msg");
    }

    public void j() {
        if (getAutoScrollReader() != null) {
            com.qq.reader.appconfig.a.d.b(this.b, getAutoReader().g());
            getAutoScrollReader().m();
            getTopPage().invalidate();
        }
    }

    public void c(boolean z) {
        if (this.e != null) {
            this.e.b();
        }
        com.qq.reader.appconfig.a.d.a(this.b, getAutoReader().g());
        if (this.m != null) {
            this.m.cancel();
        }
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
        if (com.qq.reader.appconfig.a.d.af(getContext().getApplicationContext()) == 3) {
            getTopPage().d();
        }
        if (z) {
            invalidate();
        }
    }

    public boolean k() {
        return getAutoReader().c();
    }

    public void l() {
        this.m = new TimerTask(this) {
            final /* synthetic */ ReaderPageSwither a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        float e = this.a.a.getAutoReader().e();
                        float a = this.a.a.getAutoReader().f() ? 0.0f : this.a.a.getAutoSpeed();
                        if (this.a.a.getAutoSpeed() + e >= ((float) this.a.a.getHeight())) {
                            this.a.a.getTopPage().getmPageCache().g();
                            int e2 = this.a.a.g.e();
                            this.a.a.s();
                            switch (e2) {
                                case 0:
                                case 1:
                                    this.a.a.getAutoReader().a(0.0f);
                                    break;
                                case 2:
                                    this.a.a.b(true);
                                    break;
                                case 3:
                                case 5:
                                    switch (this.a.a.b(e2)) {
                                        case 0:
                                        case 2:
                                        case 4:
                                            this.a.a.b(true);
                                            break;
                                        case 1:
                                            this.a.a.getAutoReader().a(0.0f);
                                            break;
                                        default:
                                            break;
                                    }
                                case 4:
                                    this.a.a.b(true);
                                    this.a.a.p();
                                    break;
                                default:
                                    break;
                            }
                        }
                        this.a.a.getAutoReader().a(a + e);
                        this.a.a.getTopPage().postInvalidate();
                    }
                });
            }
        };
        if (this.l == null) {
            this.l = new Timer();
        }
        this.l.schedule(this.m, 0, 70);
    }

    public boolean d(boolean z) {
        if (!k()) {
            return false;
        }
        getAutoReader().b(true);
        if (z) {
            f(this.g instanceof com.qq.reader.readengine.kernel.a.c).a(getAutoReader().g(), this.k);
        }
        ((ReaderPageActivity) this.b).getHandler().removeMessages(1244);
        com.qq.reader.common.monitor.debug.c.e("AUTO", "remove msg");
        return true;
    }

    public void m() {
        getAutoReader().b(false);
        postInvalidate();
    }

    public void n() {
        if (k()) {
            g(true);
        } else {
            b(true, (int) http.Internal_Server_Error);
        }
        IBook.mSearchList.clear();
    }

    public void o() {
        if (k()) {
            g(false);
        } else {
            c(true, http.Internal_Server_Error);
        }
    }

    private float getAutoSpeed() {
        return getAutoReader().h();
    }

    public void p() {
        b(false, (int) http.Internal_Server_Error);
    }

    public void q() {
        b(true, (int) http.Internal_Server_Error);
    }

    public void b(boolean z, int i) {
        if (this.g.a()) {
            AnimationProvider animationProvider = getTopPage().getAnimationProvider();
            int width = getWidth();
            int height = getHeight();
            int b = animationProvider.b(this.g);
            animationProvider.a(PageIndex.next);
            int i2;
            switch (b) {
                case 0:
                case 1:
                    if (z) {
                        animationProvider.b((float) ((width * 4) / 5), (float) ((height * 5) / 6));
                        animationProvider.a((width * 4) / 5, (height * 5) / 6, -width, height, Mode.AutoScrollingForward, i);
                    } else {
                        b = (int) this.t;
                        i2 = (int) this.u;
                        width = -width;
                        if (this.u < ((float) (height / 3))) {
                            height = 0;
                        }
                        animationProvider.a(b, i2, width, height, Mode.AutoScrollingForward, i);
                    }
                    invalidate();
                    r();
                    return;
                case 2:
                    getTopPage().m();
                    return;
                case 3:
                case 4:
                case 5:
                    switch (b(b)) {
                        case 0:
                            e(true);
                            return;
                        case 1:
                            if (z) {
                                animationProvider.b((float) ((width * 4) / 5), (float) ((height * 5) / 6));
                                animationProvider.a((width * 4) / 5, (height * 5) / 6, -width, height, Mode.AutoScrollingForward, i);
                            } else {
                                b = (int) this.t;
                                i2 = (int) this.u;
                                width = -width;
                                if (this.u < ((float) (height / 3))) {
                                    height = 0;
                                }
                                animationProvider.a(b, i2, width, height, Mode.AutoScrollingForward, i);
                            }
                            invalidate();
                            r();
                            return;
                        case 2:
                            getTopPage().m();
                            b = (int) this.t;
                            i2 = (int) this.u;
                            width = -width;
                            if (this.u < ((float) (height / 3))) {
                                height = 0;
                            }
                            animationProvider.a(b, i2, width, height, Mode.AutoScrollingForward, i);
                            invalidate();
                            r();
                            return;
                        case 3:
                            if (this.d != null) {
                                this.d.u();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    protected int b(int i) {
        if (this.d != null) {
            return this.d.a(i, true);
        }
        return 0;
    }

    public ReaderTextPageView getTopPage() {
        return (ReaderTextPageView) getChildAt(0);
    }

    public i getPageCache() {
        return ((ReaderTextPageView) getChildAt(0)).getmPageCache();
    }

    protected void r() {
        s();
    }

    public void s() {
        if (this.c != null) {
            this.c.a(this.g.i());
            this.c.a(this.g.g().doubleValue());
            this.c.a(!this.h.t());
        }
    }

    public void t() {
        c(false, http.Internal_Server_Error);
    }

    private void G() {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        com.qq.reader.common.c.a.bU = displayMetrics.widthPixels;
        com.qq.reader.common.c.a.bT = displayMetrics.heightPixels;
    }

    public void c(boolean z, int i) {
        if (this.g.a()) {
            AnimationProvider animationProvider = getTopPage().getAnimationProvider();
            G();
            int i2 = com.qq.reader.common.c.a.bU;
            int i3 = com.qq.reader.common.c.a.bT;
            int a = animationProvider.a(this.g);
            animationProvider.a(PageIndex.previous);
            switch (a) {
                case 0:
                case 1:
                    if (z) {
                        animationProvider.b((float) (getWidth() / 4), (float) (getHeight() / 2));
                    }
                    animationProvider.a((int) this.t, (int) this.u, -i2, i3, Mode.AutoScrollingForward, i);
                    invalidate();
                    u();
                    return;
                case 2:
                    getTopPage().m();
                    return;
                case 3:
                case 4:
                    switch (c(a)) {
                        case 0:
                            e(false);
                            return;
                        case 1:
                            if (z) {
                                animationProvider.b((float) (getWidth() / 4), (float) (getHeight() / 2));
                            }
                            animationProvider.a((int) this.t, (int) this.u, -i2, i3, Mode.AutoScrollingForward, i);
                            invalidate();
                            u();
                            return;
                        case 2:
                            getTopPage().m();
                            a = (int) this.t;
                            int i4 = (int) this.u;
                            i2 = -i2;
                            if (this.u < ((float) (i3 / 3))) {
                                i3 = 0;
                            }
                            animationProvider.a(a, i4, i2, i3, Mode.AutoScrollingForward, i);
                            invalidate();
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    protected int c(int i) {
        if (this.d != null) {
            return this.d.d(i);
        }
        return 0;
    }

    protected void u() {
        s();
    }

    public void e(boolean z) {
    }

    public void v() {
        if (this.n != null) {
            this.n.b();
            this.n = null;
        }
    }

    public Context getApplicationContext() {
        if (getContext() instanceof Activity) {
            return ((Activity) getContext()).getApplicationContext();
        }
        return getContext();
    }

    public void setTextSize(float f) {
        this.h.b(f);
        invalidate();
    }

    public void setViewMode(int i) {
        g();
        this.g.b(i);
        if (getTopPage().h()) {
            a(PageIndex.current_left, PageIndex.current_right);
        }
        invalidate();
        r();
    }

    public void a(float f) {
        this.g.c(f);
        g();
        if (getTopPage().h()) {
            a(PageIndex.current_left, PageIndex.current_right);
        }
        invalidate();
        r();
    }

    public void setTextColor(int i) {
        this.h.b(i);
        g();
        if (getTopPage().h()) {
            a(PageIndex.current_left, PageIndex.current_right);
        }
        invalidate();
    }

    public void setTitleColor(int i) {
        this.h.c(i);
        invalidate();
    }

    public void setPageHeaderColor(int i) {
        this.h.d(i);
        invalidate();
    }

    public void setBackgroundColor(int i) {
        if (getChildCount() > 0) {
            getTopPage().setBackgroundColor(i);
            g();
        }
        getTopPage().getAnimationProvider().a(i);
        if (getTopPage().h()) {
            a(PageIndex.current_left, PageIndex.current_right);
        }
        invalidate();
    }

    public int getBackgroundColor() {
        return getTopPage() == null ? -1 : getTopPage().getBackgroundColor();
    }

    public void setBackgroundResource(int i) {
        if (getChildCount() > 0) {
            getTopPage().setBackgroundResource(i);
            getTopPage().getAnimationProvider().a(f(i));
            g();
            if (getTopPage().h()) {
                a(PageIndex.current_left, PageIndex.current_right);
            }
        }
    }

    private int f(int i) {
        return ao.a(getContext().getResources().getDrawable(i));
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (getChildCount() > 0) {
            if (drawable instanceof BitmapDrawable) {
                getTopPage().setBackgroundBitmap(((BitmapDrawable) drawable).getBitmap());
                getTopPage().getAnimationProvider().a(ao.a(drawable));
            } else if (drawable instanceof SkinnableBitmapDrawable) {
                getTopPage().setBackgroundBitmap(((SkinnableBitmapDrawable) drawable).getBitmap());
                getTopPage().getAnimationProvider().a(ao.a(drawable));
            } else if (drawable instanceof ColorDrawable) {
                int a = ao.a(drawable);
                getTopPage().setBackgroundColor(a);
                getTopPage().getAnimationProvider().a(a);
            } else {
                getTopPage().setBackgroundDrawable(drawable);
                getTopPage().getAnimationProvider().a(ao.a(drawable));
            }
            g();
            if (getTopPage().h()) {
                a(PageIndex.current_left, PageIndex.current_right);
            }
        }
    }

    public void setOnAreaClickListener(b bVar) {
        this.r = bVar;
    }

    public void setOnMiddleTouchListener(c cVar) {
        this.s = cVar;
    }

    public boolean w() {
        return this.y;
    }

    public void setBlockTouch(boolean z) {
        this.y = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        getTopPage().p().f();
        z.a(((ReaderPageActivity) this.b).getHandler());
        IBook.mSearchList.clear();
        if (getBookCore().o().a(motionEvent, getTopPage().getAnimationProvider())) {
            return true;
        }
        if (getTopPage().B()) {
            return getTopPage().getTtsModeController().a(motionEvent);
        }
        if (this.y) {
            return true;
        }
        if (this.p != 2) {
            boolean[] b = getTopPage().p().b(motionEvent);
            if (b[0]) {
                return b[1];
            }
            if (getTopPage().p().c(motionEvent) && motionEvent.getAction() != 0) {
                return true;
            }
            if (getTopPage().b(motionEvent) && motionEvent.getAction() != 0) {
                if (!this.C) {
                    return true;
                }
                this.C = false;
                return true;
            }
        }
        if (this.x == null) {
            this.x = VelocityTracker.obtain();
        }
        this.x.addMovement(motionEvent);
        AnimationProvider animationProvider = getTopPage().getAnimationProvider();
        if (!this.g.a()) {
            return false;
        }
        int i;
        int i2;
        float y;
        switch (motionEvent.getAction()) {
            case 0:
                this.t = motionEvent.getX();
                this.u = motionEvent.getY();
                this.v = this.t;
                this.w = this.u;
                this.o = true;
                if (a(motionEvent)) {
                }
                switch (this.p) {
                    case 0:
                        animationProvider.b(this.v, this.w);
                        break;
                    case 2:
                        if (getAutoReader().a(this.t, this.u)) {
                            d(false);
                            break;
                        }
                        break;
                }
                H();
                return true;
            case 1:
                if (this.C) {
                    this.C = false;
                    if (this.B != null) {
                        removeCallbacks(this.B);
                        this.B = null;
                    }
                }
                if (a(motionEvent)) {
                    if (this.x != null) {
                        this.x.recycle();
                        this.x = null;
                    }
                    s();
                    invalidate();
                    return c(motionEvent);
                }
                switch (this.p) {
                    case 1:
                        VelocityTracker velocityTracker = this.x;
                        velocityTracker.computeCurrentVelocity(1000);
                        int xVelocity = (int) velocityTracker.getXVelocity();
                        G();
                        i = com.qq.reader.common.c.a.bU;
                        i2 = com.qq.reader.common.c.a.bT;
                        if (xVelocity > http.Internal_Server_Error) {
                            animationProvider.a((int) motionEvent.getX(), (int) motionEvent.getY(), i, i2, Mode.AutoScrollingForward, http.Internal_Server_Error);
                            s();
                        } else if (xVelocity < -500) {
                            animationProvider.a((int) motionEvent.getX(), (int) motionEvent.getY(), -i, i2, Mode.AutoScrollingForward, http.Internal_Server_Error);
                            s();
                        } else {
                            boolean z;
                            if (getBookCore().o().f() != TbsLog.TBSLOG_CODE_SDK_INIT) {
                                z = true;
                            } else {
                                z = false;
                            }
                            a(motionEvent.getX(), z);
                        }
                        this.p = 0;
                        invalidate();
                        if (this.x == null) {
                            return true;
                        }
                        this.x.recycle();
                        this.x = null;
                        return true;
                    case 2:
                        if (this.o) {
                            d(true);
                            return true;
                        }
                        m();
                        return true;
                    case 3:
                        if (y()) {
                            m();
                            this.p = 2;
                            return true;
                        }
                        y = motionEvent.getY();
                        if (y - this.w >= ((float) (this.f * 2))) {
                            g(true);
                        } else if (this.w - y >= ((float) (this.f * 2))) {
                            g(false);
                        }
                        this.p = 2;
                        return true;
                    default:
                        return c(motionEvent);
                }
            case 2:
                float x = motionEvent.getX();
                float y2 = motionEvent.getY();
                i = (int) (x - this.v);
                i2 = (int) (y2 - this.w);
                if (Math.abs(i) >= this.f || Math.abs(i2) >= this.f) {
                    this.o = false;
                    if (this.C) {
                        this.C = false;
                        if (this.B != null) {
                            removeCallbacks(this.B);
                            this.B = null;
                        }
                    }
                }
                if (a(motionEvent)) {
                    s();
                    getTopPage().invalidate();
                    return true;
                }
                int i3;
                switch (this.p) {
                    case 1:
                        i = (int) (x - this.t);
                        i = (int) (y2 - this.u);
                        this.t = x;
                        this.u = y2;
                        animationProvider.b((int) x, (int) y2);
                        invalidate();
                        return true;
                    case 2:
                        if (Math.abs(i) >= this.f || Math.abs(i2) >= this.f) {
                            this.p = 3;
                            this.o = false;
                            return true;
                        }
                    case 3:
                        break;
                    case 4:
                        if (ab.b(this, x, y2)) {
                            i3 = (int) ((y2 - this.u) / 2.0f);
                            this.u = y2;
                            if (this.s == null) {
                                return true;
                            }
                            this.s.a(-i3);
                            return true;
                        }
                        this.p = 0;
                        return true;
                    default:
                        if (Math.abs(i) < this.f && Math.abs(i2) < this.f) {
                            return true;
                        }
                        this.o = false;
                        if (!com.qq.reader.appconfig.a.d.ah(this.b.getApplicationContext()) || !ab.b(this, x, y2) || Math.abs(i2) < this.f || Math.abs(i) >= this.f * 2 || Math.abs(i) >= Math.abs(i2) || (i != 0 && Math.atan(Math.abs(((double) i2) / ((double) i))) < 1.3089969389957472d)) {
                            PageIndex a = animationProvider.a(x, y2);
                            if (a == PageIndex.previous) {
                                i = animationProvider.a(this.g);
                                switch (i) {
                                    case 0:
                                    case 1:
                                        getTopPage().o();
                                        animationProvider.b((int) x, (int) y2);
                                        invalidate();
                                        this.p = 1;
                                        this.q = 2;
                                        return true;
                                    case 2:
                                        getTopPage().m();
                                        return true;
                                    case 3:
                                    case 4:
                                        switch (c(i)) {
                                            case 0:
                                                e(false);
                                                return true;
                                            case 1:
                                                getTopPage().o();
                                                animationProvider.b((int) x, (int) y2);
                                                invalidate();
                                                this.p = 1;
                                                this.q = 2;
                                                return true;
                                            case 2:
                                                getTopPage().m();
                                                getTopPage().o();
                                                animationProvider.b((int) x, (int) y2);
                                                invalidate();
                                                this.p = 1;
                                                this.q = 2;
                                                return true;
                                            default:
                                                return true;
                                        }
                                    default:
                                        return true;
                                }
                            } else if (a == PageIndex.next) {
                                i = animationProvider.b(this.g);
                                switch (i) {
                                    case 0:
                                    case 1:
                                        getTopPage().o();
                                        animationProvider.b((int) x, (int) y2);
                                        invalidate();
                                        this.p = 1;
                                        this.q = 1;
                                        return true;
                                    case 2:
                                        getTopPage().m();
                                        return true;
                                    case 3:
                                    case 4:
                                    case 5:
                                        switch (b(i)) {
                                            case 0:
                                                e(true);
                                                this.p = 1;
                                                this.q = 1;
                                                return true;
                                            case 1:
                                                getTopPage().o();
                                                animationProvider.b((int) x, (int) y2);
                                                invalidate();
                                                this.p = 1;
                                                this.q = 1;
                                                return true;
                                            case 2:
                                                getTopPage().m();
                                                getTopPage().o();
                                                animationProvider.b((int) x, (int) y2);
                                                invalidate();
                                                this.p = 1;
                                                this.q = 1;
                                                return true;
                                            case 3:
                                                if (this.d == null) {
                                                    return true;
                                                }
                                                this.d.u();
                                                return true;
                                            default:
                                                return true;
                                        }
                                    default:
                                        return true;
                                }
                            } else {
                                animationProvider.b(x, y2);
                                return true;
                            }
                        }
                        this.p = 4;
                        return true;
                }
                if (!y()) {
                    return true;
                }
                i3 = (int) (y2 - this.u);
                this.u = y2;
                y = ((float) i3) + getAutoReader().e();
                if (y > ((float) getHeight())) {
                    y = (float) getHeight();
                }
                if (y < 0.0f) {
                    y = 0.0f;
                }
                getAutoReader().a(y);
                return true;
            case 3:
                this.p = 0;
                this.o = false;
                if (!this.C) {
                    return true;
                }
                this.C = false;
                if (this.B == null) {
                    return true;
                }
                removeCallbacks(this.B);
                this.B = null;
                return true;
            default:
                return true;
        }
    }

    private boolean c(MotionEvent motionEvent) {
        if (!this.o) {
            return false;
        }
        b(motionEvent);
        this.o = false;
        this.p = 0;
        return true;
    }

    public void x() {
        this.p = 0;
    }

    public boolean a(MotionEvent motionEvent) {
        return getAutoScrollReader() != null && getAutoScrollReader().a(motionEvent, this.x);
    }

    public boolean y() {
        return getAutoReader().f();
    }

    private void a(float f, boolean z) {
        Object obj;
        int f2;
        int i;
        AnimationProvider animationProvider = getTopPage().getAnimationProvider();
        G();
        int i2 = com.qq.reader.common.c.a.bU;
        int i3 = com.qq.reader.common.c.a.bT;
        if (this.q == 1) {
            if (z) {
                obj = null;
            } else {
                Object obj2;
                obj2 = this.t > ((float) ((i2 * 3) / 4)) ? 1 : null;
                if (obj2 != null) {
                    f2 = this.g.f();
                    if (f2 == 3) {
                        c(f2);
                    }
                }
                obj = obj2;
            }
            int i4 = (int) this.t;
            f2 = (int) this.u;
            int i5 = -i2;
            if (this.w < ((float) (i3 / 3))) {
                i = 0;
            } else {
                i = i3;
            }
            animationProvider.a(i4, f2, i5, i, obj != null ? Mode.AutoScrollingBackward : Mode.AutoScrollingForward, http.Internal_Server_Error);
        }
        if (this.q == 2) {
            if (z) {
                obj = null;
            } else {
                if (this.t < ((float) (i2 / 4))) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (obj2 != null) {
                    f2 = this.g.e();
                    if (f2 == 3) {
                        b(f2);
                    }
                }
                obj = obj2;
            }
            i4 = (int) this.t;
            f2 = (int) this.u;
            i5 = -i2;
            if (this.w < ((float) (i3 / 3))) {
                i = 0;
            } else {
                i = i3;
            }
            animationProvider.a(i4, f2, i5, i, obj != null ? Mode.AutoScrollingBackward : Mode.AutoScrollingForward, http.Internal_Server_Error);
        }
        s();
    }

    public boolean b(MotionEvent motionEvent) {
        if (this.r == null) {
            return false;
        }
        this.r.a(this, motionEvent);
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        int i;
        if (y > 0.0f) {
            this.A = 0;
            i = this.z + 1;
            this.z = i;
            if (i > 2) {
                this.z = 0;
                n();
            }
        } else if (y >= 0.0f) {
            return false;
        } else {
            this.z = 0;
            i = this.A + 1;
            this.A = i;
            if (i > 2) {
                this.A = 0;
                o();
            }
        }
        return true;
    }

    public d getPageChangeListener() {
        return this.c;
    }

    public void setPageChangeListener(d dVar) {
        this.c = dVar;
    }

    public e getTurnPageListener() {
        return this.d;
    }

    public void setTurnPageListener(e eVar) {
        this.d = eVar;
    }

    public void a(int i, boolean z) {
        if (z) {
            setTextColor(i);
            setTitleColor(i);
            h o = this.g.o();
            if (o != null) {
                o.a(i);
            }
            if (this.c != null) {
                this.c.a(i);
                return;
            }
            return;
        }
        setBackgroundColor(i);
    }

    public void b(int i, int i2) {
        com.qq.reader.appconfig.a.d.k = i;
        com.qq.reader.appconfig.a.d.l = i2;
        com.qq.reader.appconfig.a.d.j(getContext().getApplicationContext(), 8);
        com.qq.reader.appconfig.a.d.a(getContext().getApplicationContext(), i, i2);
        d(8);
    }

    public void d(int i) {
        int i2;
        com.qq.reader.appconfig.a.d.p = i;
        int[] N = com.qq.reader.appconfig.a.d.N(this.b);
        com.qq.reader.appconfig.a.d.k = N[0];
        com.qq.reader.appconfig.a.d.l = N[1];
        com.qq.reader.appconfig.a.d.m = com.qq.reader.appconfig.a.d.O(this.b);
        if (com.qq.reader.appconfig.a.d.n) {
            setTextColor(-8815488);
            setTitleColor(-8355712);
            setBackgroundColor(-14540252);
            i2 = -10066330;
            setPageHeaderColor(-10066330);
        } else if (i < 8) {
            Resources resources = getContext().getResources();
            TypedArray obtainTypedArray = resources.obtainTypedArray(R.array.bkStyles);
            TypedArray obtainTypedArray2 = resources.obtainTypedArray(R.array.textStyles);
            TypedArray obtainTypedArray3 = resources.obtainTypedArray(R.array.infoStyles);
            TypedArray obtainTypedArray4 = resources.obtainTypedArray(R.array.titleStyles);
            if (i == 0 || i == 1 || i == 8 || i == 3) {
                setBackgroundColor(obtainTypedArray.getColor(i, -1));
            } else {
                try {
                    setBackgroundDrawable(obtainTypedArray.getDrawable(i));
                } catch (OutOfMemoryError e) {
                }
            }
            int color = obtainTypedArray2.getColor(i, 0);
            i2 = obtainTypedArray3.getColor(i, 0);
            int color2 = obtainTypedArray4.getColor(i, 0);
            setTextColor(color);
            setTitleColor(color2);
            setPageHeaderColor(i2);
            obtainTypedArray.recycle();
            obtainTypedArray2.recycle();
            obtainTypedArray3.recycle();
            obtainTypedArray4.recycle();
        } else {
            i2 = N[0];
            setTextColor(N[0]);
            setTitleColor(N[0]);
            setPageHeaderColor(i2);
            setBackgroundColor(N[1]);
        }
        h o = this.g.o();
        if (o != null) {
            o.a(i2);
        }
        if (this.c != null) {
            this.c.a(i2);
        }
    }

    private void H() {
        this.C = true;
        if (this.B == null) {
            this.B = new a();
        }
        postDelayed(this.B, (long) ViewConfiguration.getLongPressTimeout());
    }

    public boolean onLongClick(View view) {
        if (this.p == 2) {
            return false;
        }
        if (ReaderTextPageView.d != -1) {
            return true;
        }
        if (getTopPage().p().a(view, this.v, this.w)) {
            return true;
        }
        if (getTopPage().a(view, this.v, this.w)) {
            return true;
        }
        return false;
    }

    public void a(boolean z, PageIndex pageIndex, PageIndex pageIndex2) {
        if (z) {
            getTopPage().k();
        }
        getTopPage().getmPageCache().a(pageIndex);
        switch (this.g.e()) {
            case 0:
            case 1:
                getTopPage().getmPageCache().a(pageIndex2);
                return;
            default:
                getTopPage().getmPageCache().c(pageIndex2);
                return;
        }
    }

    public void a(PageIndex pageIndex, PageIndex pageIndex2) {
        a(true, pageIndex, pageIndex2);
        this.g.f();
    }

    public com.qq.reader.view.a f(boolean z) {
        if (this.D == null) {
            this.D = new com.qq.reader.view.a((Activity) this.b, z);
            this.D.a((com.qq.reader.view.a.a) this);
            this.D.a(((ReaderPageActivity) this.b).getHandler());
        }
        return this.D;
    }

    public float g(boolean z) {
        return (float) getAutoReader().c(z);
    }

    public void e(int i) {
        if (this.k != i) {
            a(true, this.k);
            this.k = i;
            a(true);
        }
    }

    public void z() {
        m();
    }

    private void I() {
        try {
            View.class.getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{new Integer(1), null});
        } catch (Exception e) {
        }
    }

    public void a() {
        b(true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean A() {
        /*
        r3 = this;
        r0 = 1;
        r1 = 0;
        r2 = r3.g;
        r2 = r2.e();
        switch(r2) {
            case 0: goto L_0x000c;
            case 1: goto L_0x000c;
            case 2: goto L_0x0023;
            case 3: goto L_0x0012;
            case 4: goto L_0x0012;
            case 5: goto L_0x0012;
            default: goto L_0x000b;
        };
    L_0x000b:
        r0 = r1;
    L_0x000c:
        if (r0 == 0) goto L_0x0011;
    L_0x000e:
        r3.s();
    L_0x0011:
        return r0;
    L_0x0012:
        r2 = r3.b(r2);
        switch(r2) {
            case 0: goto L_0x001a;
            case 1: goto L_0x000c;
            case 2: goto L_0x001a;
            case 3: goto L_0x0019;
            case 4: goto L_0x001a;
            default: goto L_0x0019;
        };
    L_0x0019:
        goto L_0x000b;
    L_0x001a:
        r0 = r3.getTopPage();
        r0.m();
        r0 = r1;
        goto L_0x000c;
    L_0x0023:
        r0 = r3.getTopPage();
        r0.m();
        r0 = r1;
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.readpage.ReaderPageSwither.A():boolean");
    }

    public void invalidate() {
        if (com.qq.reader.appconfig.b.a()) {
            getTopPage().invalidate();
        } else {
            super.invalidate();
        }
    }

    public void b() {
        r();
    }

    public boolean b(float f) {
        int b;
        if (f > 0.0f) {
            b = this.g.b(Math.abs(f));
        } else {
            b = this.g.a(Math.abs(f));
        }
        switch (b) {
            case 0:
            case 1:
            case 6:
                return true;
            case 3:
            case 4:
            case 5:
                if (f > 0.0f) {
                    b = c(b);
                } else {
                    b = b(b);
                }
                switch (b) {
                    case 0:
                        getTopPage().m();
                        return false;
                    case 1:
                        if (f > 0.0f) {
                            this.g.b(Math.abs(f));
                        } else {
                            this.g.a(Math.abs(f));
                        }
                        return true;
                    case 2:
                        AnimationProvider animationProvider = getTopPage().getAnimationProvider();
                        b = getWidth();
                        int height = getHeight();
                        g();
                        getPageCache().a(PageIndex.current, 2);
                        if (f <= 0.0f) {
                            animationProvider.b((float) ((b * 4) / 5), (float) (height / 2));
                            animationProvider.a(b, 0, -b, height, Mode.ForceScrolling, http.Internal_Server_Error);
                            return false;
                        }
                        animationProvider.b((float) (b / 5), (float) (height / 2));
                        animationProvider.a(-b, 0, b, height, Mode.ForceScrolling, http.Internal_Server_Error);
                        return false;
                    default:
                        return false;
                }
            default:
                return false;
        }
    }

    public boolean B() {
        h o = getBookCore().o();
        if (!o.d() || o.f() == 1008) {
            return true;
        }
        return false;
    }

    public void C() {
        getTopPage().C();
    }

    public void D() {
        getTopPage().D();
    }

    public void a(int i) {
        ReadOnlineResult t = this.g.o().c().t();
        if (t != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(t.z());
            stringBuilder.append("书币");
            stringBuilder.append(" + ");
            stringBuilder.append(t.A() + i);
            stringBuilder.append("书券");
            int B = t.B();
            if (B > 0) {
                stringBuilder.append(" + ").append(B).append("抵扣券");
            }
            t.k(t.A() + i);
            t.j(stringBuilder.toString());
            getTopPage().getmPageCache().f(PageIndex.current);
            getTopPage().invalidate();
        }
    }

    public void a(Message message) {
        this.i.a().sendMessage(message);
    }
}
