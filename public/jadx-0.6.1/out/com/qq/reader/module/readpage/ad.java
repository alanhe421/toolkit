package com.qq.reader.module.readpage;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.appconfig.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.plugin.tts.model.d;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.view.MarkView;
import com.tencent.qalsdk.im_open.http;
import format.epub.view.ZLTextElementAreaArrayList;
import format.epub.view.h;

/* compiled from: TtsModeController */
public class ad {
    private String a = ad.class.getName();
    private ReaderPageActivity b;
    private ReaderTextPageView c;
    private MarkView d;
    private i e;
    private int f = -1;
    private boolean g;

    public ad(ReaderPageActivity readerPageActivity, ReaderTextPageView readerTextPageView, i iVar) {
        this.b = readerPageActivity;
        this.c = readerTextPageView;
        this.e = iVar;
        this.d = new MarkView(this.b, false, false, 0);
    }

    public boolean a(MotionEvent motionEvent) {
        if (!this.g) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.b.N().i();
                return true;
            default:
                return true;
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (this.g) {
            switch (i) {
                case 24:
                case 25:
                    return true;
            }
        }
        return false;
    }

    public void a(int i) {
        this.f = i;
        this.g = true;
        b();
    }

    public int a() {
        this.g = false;
        d();
        c();
        if (this.f == 0) {
            this.f = -1;
        }
        return this.f;
    }

    public void a(d dVar) {
        c(dVar);
        this.c.invalidate();
    }

    public void b(d dVar) {
        if (dVar != null) {
            try {
                if (dVar.b()) {
                    d();
                    dVar.a(false);
                    this.b.q.b(false, (int) http.Internal_Server_Error);
                    this.b.q.getBookCore().a(dVar);
                }
            } catch (Exception e) {
                f.a("TTS", "TTS BUG : " + e.toString());
            }
        }
    }

    public void a(Canvas canvas) {
        j pagePaint = this.c.getPagePaint();
        float k = (float) j.k();
        float o = (float) (pagePaint.o() - pagePaint.l());
        float j = (float) j.j();
        float i = (float) (pagePaint.a - j.i());
        this.d.a(canvas, k, o, j, i, pagePaint.h.descent());
    }

    private void b() {
        if (this.b != null) {
            if (2 != a.d.B(this.b.getApplicationContext())) {
                this.b.q.setViewMode(2);
            }
            this.b.q.getTopPage().e();
        }
    }

    private void c() {
        if (this.b != null) {
            int B = a.d.B(this.b.getApplicationContext());
            if (2 != B) {
                this.b.q.setViewMode(B);
            }
            ReaderTextPageView topPage = this.b.q.getTopPage();
            if (!topPage.h()) {
                topPage.b = null;
                topPage.setSize(topPage.getWidth(), topPage.getHeight());
            }
        }
    }

    private void c(d dVar) {
        float f;
        float f2;
        float f3;
        float f4 = 0.0f;
        g d = dVar.d();
        g e = dVar.e();
        ZLTextElementAreaArrayList b = this.e.b();
        if (b.size() > 0) {
            h hVar;
            int i = 0;
            while (i < b.size()) {
                hVar = (h) b.get(i);
                if (hVar.d().b(d) >= 0) {
                    f = hVar.a;
                    f2 = hVar.c;
                    break;
                }
                i++;
            }
            f2 = 0.0f;
            f = 0.0f;
            float f5 = 0.0f;
            while (i < b.size()) {
                hVar = (h) b.get(i);
                if (hVar.d().b(e) >= 0) {
                    break;
                }
                f5 = hVar.b;
                f4 = hVar.d;
                i++;
            }
            hVar = (h) b.get(b.size() - 1);
            f3 = f;
            f = f4;
            f4 = f5;
        } else {
            f = 0.0f;
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.d.a(f3, f2, f4, f, b);
    }

    private void d() {
        this.d.b();
        this.c.invalidate();
    }
}
