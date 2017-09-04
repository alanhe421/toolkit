package com.qq.reader.module.feed.card.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.card.view.FeedColumnByPassEntranceHeadView.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import format.epub.common.utils.h;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class FeedColumnByPassView extends RelativeLayout {
    private ImageView a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private b e;
    private Context f;
    private boolean g = false;
    private long h;
    private boolean i = false;
    private String j = "";
    private long k;
    private String l = "";
    private String m = "";
    private a n;

    public FeedColumnByPassView(Context context) {
        super(context);
        this.f = context;
        LayoutInflater.from(context).inflate(R.layout.feed_column_bypass_entrance_view, this, true);
        f();
    }

    public FeedColumnByPassView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context;
        LayoutInflater.from(context).inflate(R.layout.feed_column_bypass_entrance_view, this, true);
        f();
    }

    private void f() {
        this.a = (ImageView) findViewById(R.id.entrance_icon);
        this.b = (ImageView) findViewById(R.id.entrance_red_dot);
        this.c = (TextView) findViewById(R.id.entrance_bubble);
        this.d = (TextView) findViewById(R.id.entrance_text);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnByPassView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != null) {
                    String str = this.a.e.f;
                    Map hashMap = new HashMap();
                    if (str.equals("103172")) {
                        hashMap.put(s.ORIGIN, "10857");
                        i.a("event_F18", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        StatisticsManager.a().a("event_F18", hashMap);
                    } else if (str.equals("103180")) {
                        hashMap.put(s.ORIGIN, "2459");
                        i.a("event_F19", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        StatisticsManager.a().a("event_F19", hashMap);
                    } else if (str.equals("103182")) {
                        hashMap.put(s.ORIGIN, "10859");
                        i.a("event_F20", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        StatisticsManager.a().a("event_F20", hashMap);
                    } else if (str.equals("103183")) {
                        hashMap.put(s.ORIGIN, "10854");
                        i.a("event_F21", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        StatisticsManager.a().a("event_F21", hashMap);
                    } else if (str.equals("103185")) {
                        hashMap.put(s.ORIGIN, "10911");
                        i.a("event_F197", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        StatisticsManager.a().a("event_F197", hashMap);
                    } else if (str.equals("103190")) {
                        hashMap.put(s.ORIGIN, "10912");
                        i.a("event_F199", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        StatisticsManager.a().a("event_F199", hashMap);
                    } else if (str.equals("103191")) {
                        hashMap.put(s.ORIGIN, "10912");
                        i.a("event_F199", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        StatisticsManager.a().a("event_F199", hashMap);
                    }
                    if (!(TextUtils.isEmpty(str) || d.J(this.a.f, str))) {
                        d.b(this.a.f, str, true);
                    }
                }
                this.a.a();
                try {
                    c.a(this.a.n.getFromActivity(), this.a.e.d, null);
                } catch (Exception e) {
                }
            }
        });
    }

    public void a(b bVar, a aVar) {
        if (bVar != null) {
            this.n = aVar;
            this.e = bVar;
            if (!TextUtils.isEmpty(bVar.b)) {
                this.d.setText(bVar.b);
            }
            if (!TextUtils.isEmpty(bVar.c)) {
                com.qq.reader.common.imageloader.c.a(getContext()).a(bVar.c, this.a, com.qq.reader.common.imageloader.a.a().j());
            }
            if (!TextUtils.isEmpty(bVar.e)) {
                setTextColor(bVar.e);
            }
        }
    }

    public void a() {
        if (this.b != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.e != null) {
                String str = this.e.f;
                if (!TextUtils.isEmpty(str)) {
                    if (this.g && currentTimeMillis < this.h && !d.J(this.f, str)) {
                        this.b.setVisibility(0);
                    } else if (str.equals("103182") && com.qq.reader.cservice.adv.c.a(false)) {
                        this.b.setVisibility(0);
                    } else {
                        b();
                        c();
                    }
                }
            }
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.setVisibility(8);
        }
    }

    public void c() {
        if (!this.i) {
            d();
        } else if (this.c == null) {
        } else {
            if (System.currentTimeMillis() < this.k) {
                this.c.setVisibility(0);
                setBubbleText(this.j);
                setBubbleColor(this.l);
                setBubbleTextColor(this.m);
                return;
            }
            d();
        }
    }

    public void d() {
        if (this.c != null) {
            this.c.setVisibility(8);
        }
    }

    private Drawable a(Drawable drawable, ColorStateList colorStateList) {
        Drawable mutate = android.support.v4.b.a.a.b(drawable).mutate();
        android.support.v4.b.a.a.a(mutate, colorStateList);
        return mutate;
    }

    private void setBubbleText(String str) {
        if (TextUtils.isEmpty(str)) {
            d();
        } else {
            this.c.setText(str);
        }
    }

    private void setBubbleColor(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c.setBackgroundDrawable(a(this.f.getResources().getDrawable(R.drawable.feed_activity_rectangle), ColorStateList.valueOf(h.a(str))));
        }
    }

    private void setBubbleTextColor(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c.setTextColor(h.a(str));
        }
    }

    private void setTextColor(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d.setTextColor(h.a(str));
        }
    }

    public void e() {
        if (this.e != null) {
            String str = this.e.f;
            List b = com.qq.reader.cservice.adv.b.a(getContext()).b(str);
            this.g = false;
            this.i = false;
            if (b != null && b.size() > 0) {
                for (int i = 0; i < b.size(); i++) {
                    com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(i);
                    Object m = aVar.m();
                    if (!TextUtils.isEmpty(m)) {
                        try {
                            int optInt = new JSONObject(m).optInt("uitype");
                            if (optInt == 11) {
                                this.i = true;
                                this.j = aVar.e();
                                this.k = aVar.j();
                                String i2 = aVar.i();
                                if (!TextUtils.isEmpty(i2) && i2.contains(",")) {
                                    this.l = i2.split(",")[0];
                                    this.m = i2.split(",")[1];
                                }
                            } else if (optInt == 12) {
                                this.g = true;
                                this.h = aVar.j();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
            if (this.g || str.equals("103182")) {
                a();
            } else if (this.i) {
                c();
            } else {
                b();
                d();
            }
        }
    }
}
