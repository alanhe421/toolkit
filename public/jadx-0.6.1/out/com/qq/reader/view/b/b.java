package com.qq.reader.view.b;

import android.app.Activity;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.view.j;
import com.qq.reader.view.l;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Tip */
public class b {
    private boolean a = true;
    private int b;
    private j c;
    private List<l> d = new ArrayList();
    private a e;
    private boolean f;

    /* compiled from: Tip */
    public interface a {
        void a(int i);

        void b(int i);
    }

    public b(Activity activity, int i) {
        this.b = i;
        this.c = new j(activity);
        this.c.a(new 1(this));
        this.d.add(this.c);
    }

    public void a() {
        this.f = true;
        if (this.e != null) {
            this.e.a(this.b);
        }
        try {
            this.c.f_();
        } catch (Exception e) {
            c.e("Tip", e.getMessage());
        } catch (Error e2) {
            c.e("Tip", e2.getMessage());
        }
    }

    public void b() {
        if (this.a) {
            this.a = false;
            try {
                if (this.c.f()) {
                    this.c.dismiss();
                }
            } catch (Exception e) {
                c.e("Tip", e.getMessage());
            }
            for (int i = 0; i < this.d.size(); i++) {
                ((l) this.d.get(i)).dismiss(this.b);
            }
            e();
            if (this.e != null) {
                this.e.b(this.b);
            }
            this.f = false;
        }
    }

    private void e() {
        this.c = null;
        this.d.clear();
    }

    public void a(int i) {
        this.c.e(i);
    }

    public void b(int i) {
        this.c.d(i);
    }

    public void c(int i) {
        this.c.c(i);
    }

    public void d(int i) {
        this.c.b(i);
    }

    public void a(String str) {
        this.c.a(str);
    }

    public void a(boolean z) {
        this.c.f(z);
    }

    public void a(l lVar) {
        this.d.add(lVar);
        if (lVar.getHighLightArea(this.b) != null) {
            this.c.a(lVar.getHighLightArea(this.b));
        }
    }

    public boolean c() {
        return this.a;
    }

    public boolean d() {
        return this.f;
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
