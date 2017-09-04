package com.qq.reader.module.bookstore.search.card;

import android.view.View;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import org.json.JSONObject;

/* compiled from: ExchangeContoller */
public class a {
    public long a = -1;
    public String b;
    private final long c = 3000;
    private boolean d;
    private AnimatorSet e;
    private ReaderProtocolJSONTask f;
    private JSONObject g;

    public JSONObject a() {
        return this.g;
    }

    public void b() {
        this.d = true;
    }

    public boolean c() {
        return this.d;
    }

    public void a(String str) {
        if (str != null && str.length() != 0) {
            if (this.g == null || !str.equals(this.b)) {
                if (this.f != null) {
                    g.a().b(this.f);
                }
                this.a = System.currentTimeMillis();
                this.b = str;
                this.f = new ReaderProtocolJSONTask(new c(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        try {
                            this.a.g = new JSONObject(str);
                            com.qq.reader.common.monitor.debug.c.e("ExchangeContoller", "back json = " + str);
                            this.a.f = null;
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.e("ExchangeContoller", "e.printStackTrace()");
                            this.a.d();
                        }
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        this.a.d();
                    }
                });
                this.f.setUrl(str);
                g.a().a(this.f);
                return;
            }
            com.qq.reader.common.monitor.debug.c.a("ExchangeContoller", str + " is already recieved.");
        }
    }

    public void a(View view, AnimatorListenerAdapter animatorListenerAdapter, AnimatorListenerAdapter animatorListenerAdapter2) {
        view.setAnimation(null);
        e();
        if (this.e == null) {
            this.e = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotationX", new float[]{180.0f, 90.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "rotationX", new float[]{90.0f, 0.0f});
            ofFloat.addListener(animatorListenerAdapter);
            ofFloat2.addListener(animatorListenerAdapter2);
            this.e.playSequentially(new Animator[]{ofFloat, ofFloat2});
            this.e.setDuration(300);
        }
        this.e.end();
        this.e.start();
    }

    public boolean a(long j) {
        if (this.a == -1 || j - this.a <= 3000) {
            d();
            return false;
        }
        com.qq.reader.common.monitor.debug.c.e(getClass().getSimpleName(), " change = true");
        return true;
    }

    public void d() {
        this.g = null;
        if (this.f != null) {
            g.a().b(this.f);
        }
        this.f = null;
        e();
    }

    private void e() {
        this.a = -1;
        this.d = false;
    }
}
