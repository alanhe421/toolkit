package com.qq.reader.module.bookshelf;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.c;
import com.qq.reader.module.bookstore.qnative.c.b;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AbsBaseTabBookShelf */
public abstract class a implements OnScrollListener, OnItemLongClickListener, com.qq.reader.module.bookstore.qnative.c.a {
    protected int a = -1;
    protected Activity b;
    protected ListView c;
    protected View d;
    protected FrameLayout e;
    protected View f = null;
    protected Handler g;
    protected boolean h = false;
    public View i;
    protected boolean j = false;
    protected RelativeLayout k = null;
    protected OnClickListener l = new OnClickListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.a();
        }
    };
    int m = 0;
    private a n;

    /* compiled from: AbsBaseTabBookShelf */
    public interface a {
        void a();

        void b();
    }

    public abstract void a();

    public abstract void b();

    public abstract void c();

    public a(int i, Activity activity, Handler handler, int i2, int i3) {
        this.a = i;
        this.b = activity;
        this.g = handler;
        this.i = LayoutInflater.from(this.b).inflate(i2, null, false);
        this.c = (ListView) this.i.findViewById(i3);
        this.c.setOnScrollListener(this);
        this.c.setOnItemClickListener(new b(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.a(adapterView, view, i, j);
            }
        });
        this.c.setOnItemLongClickListener(this);
    }

    public void a(a aVar) {
        this.n = aVar;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public ListView d() {
        return this.c;
    }

    public Rect e() {
        if (this.k == null) {
            return null;
        }
        Rect rect = new Rect();
        this.k.getHitRect(rect);
        return rect;
    }

    private void j() {
        this.g.sendEmptyMessage(300007);
    }

    private String k() {
        return "102438";
    }

    public void f() {
        g.a().a(new AbsBaseTabBookShelf$6(this));
    }

    protected void a(com.qq.reader.cservice.adv.a aVar) {
        final Map hashMap = new HashMap();
        hashMap.put("advId", aVar.d() + "");
        if (this.d == null) {
            this.d = LayoutInflater.from(this.b.getApplicationContext()).inflate(R.layout.bookshelf_advheader_layout, null);
            this.e.addView(this.d);
            this.d.setTag(aVar);
            this.d.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean onLongClick(View view) {
                    this.a.j();
                    return true;
                }
            });
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (view.getTag() != null && (view.getTag() instanceof com.qq.reader.cservice.adv.a)) {
                        com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) view.getTag();
                        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(s.ORIGIN, aVar.f());
                            aVar.w().a().putString(s.STATPARAM_KEY, jSONObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (com.qq.reader.qurl.c.a(aVar.h())) {
                            try {
                                com.qq.reader.qurl.c.a(this.b.b, aVar.h());
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            aVar.w().a(this.b);
                        }
                        i.a("event_A141", hashMap, this.b.b.getApplicationContext());
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(10856));
                        StatisticsManager.a().a("event_A141", hashMap);
                        int aS = d.aS(ReaderApplication.getApplicationImp());
                        if (aS == 0) {
                            aS = 3;
                        }
                        Map hashMap2 = new HashMap();
                        hashMap2.put("sex", String.valueOf(aS));
                        i.a("event_C109", hashMap2, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_C109", hashMap2);
                    }
                }
            });
        }
        this.d.setTag(aVar);
        ((TextView) this.d.findViewById(R.id.bookshelf_advheader_title)).setText(aVar.e());
        if (this.n != null) {
            this.n.a();
        }
        i.a("event_A158", hashMap, this.b.getApplicationContext());
    }

    public void g() {
        i();
        if (this.n != null) {
            this.n.b();
        }
    }

    public void h() {
        if (this.k != null) {
            a(true);
        }
    }

    public void i() {
        if (this.k != null) {
            this.k.setVisibility(8);
            this.c.removeHeaderView(this.k);
        }
        if (this.d != null) {
            com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) this.d.getTag();
            aVar.a(0);
            com.qq.reader.cservice.adv.b.a(this.b.getApplicationContext()).d(aVar);
            this.d.setVisibility(8);
            com.qq.reader.cservice.adv.b.a(this.b.getApplicationContext()).d();
        }
        j.a(122, 0);
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this.b;
    }

    public void a(AdapterView<?> adapterView, View view, int i, long j) {
    }
}
