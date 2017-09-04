package com.qq.reader.module.feed.card.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import format.epub.common.utils.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedColumnByPassEntranceHeadView extends RelativeLayout {
    private Context a;
    private RelativeLayout b;
    private String c;
    private String d;
    private ArrayList<a> e = new ArrayList();
    private ArrayList<b> f = new ArrayList();
    private ArrayList<FeedColumnByPassView> g = new ArrayList();
    private RelativeLayout h;
    private RelativeLayout i;
    private ImageView j;
    private com.qq.reader.module.bookstore.qnative.c.a k;
    private ImageView l;
    private LinearLayout m;
    private ImageView n;
    private ImageView o;
    private LinearLayout p;
    private ImageView q;
    private ImageView r;
    private ImageView s;

    public static class a {
        public String a;
        public String b;
        public int c;
    }

    public static class b {
        public int a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
    }

    public FeedColumnByPassEntranceHeadView(Context context) {
        super(context);
        this.a = context;
        LayoutInflater.from(context).inflate(R.layout.feed_column_bypass_entrance_card_layout, this, true);
        c();
    }

    public FeedColumnByPassEntranceHeadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        LayoutInflater.from(context).inflate(R.layout.feed_column_bypass_entrance_card_layout, this, true);
        c();
    }

    private void c() {
        this.b = (RelativeLayout) findViewById(R.id.event_container);
        this.h = (RelativeLayout) findViewById(R.id.feed_column_four_entrance_container);
        this.i = (RelativeLayout) findViewById(R.id.feed_column_five_entrance_container);
        this.j = (ImageView) findViewById(R.id.background_iv);
        this.l = (ImageView) findViewById(R.id.event_1_iv);
        this.m = (LinearLayout) findViewById(R.id.event_2_layout);
        this.n = (ImageView) findViewById(R.id.event_21_iv);
        this.o = (ImageView) findViewById(R.id.event_22_iv);
        this.p = (LinearLayout) findViewById(R.id.event_3_layout);
        this.q = (ImageView) findViewById(R.id.event_31_iv);
        this.r = (ImageView) findViewById(R.id.event_32_iv);
        this.s = (ImageView) findViewById(R.id.event_33_iv);
    }

    public boolean a(JSONObject jSONObject, com.qq.reader.module.bookstore.qnative.c.a aVar, boolean z) throws Exception {
        int i = 0;
        if (jSONObject == null) {
            return false;
        }
        this.k = aVar;
        if (jSONObject.optInt("style") != 1) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("bg");
        if (optJSONObject != null) {
            this.c = optJSONObject.optString("image");
            this.d = optJSONObject.optString("color");
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(Constants.FLAG_ACTIVITY_NAME);
        if (optJSONArray != null && optJSONArray.length() > 0) {
            if (this.e.size() > 0) {
                this.e.clear();
            }
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                a aVar2 = new a();
                aVar2.a = optJSONObject2.optString("image");
                aVar2.b = optJSONObject2.optString("qurl");
                aVar2.c = optJSONObject2.optInt("needLogin");
                this.e.add(aVar2);
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray(MessageKey.MSG_CONTENT);
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            if (this.f.size() > 0) {
                this.f.clear();
            }
            while (i < optJSONArray2.length()) {
                JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i);
                b bVar = new b();
                bVar.a = optJSONObject3.optInt("adid");
                bVar.b = optJSONObject3.optString("name");
                bVar.c = optJSONObject3.optString(MessageKey.MSG_ICON);
                bVar.d = optJSONObject3.optString("qurl");
                bVar.e = optJSONObject3.optString("fontcolor");
                bVar.f = optJSONObject3.optString("positionId");
                if (z) {
                    if (bVar.f.equals("103185")) {
                        i.a("event_F196", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    } else if (bVar.f.equals("103191") || bVar.f.equals("103190")) {
                        i.a("event_F198", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    }
                }
                this.f.add(bVar);
                i++;
            }
        }
        return true;
    }

    public void a() {
        if (!TextUtils.isEmpty(this.c) && this.e.size() > 0) {
            a(this.j, this.c);
            this.j.setVisibility(0);
        } else if (TextUtils.isEmpty(this.d) || this.e.size() <= 0) {
            this.j.setVisibility(8);
        } else {
            this.j.setBackgroundColor(h.a(this.d));
            this.j.setVisibility(0);
        }
        d();
        e();
        b();
    }

    private void d() {
        int size = this.e.size();
        if (size == 0) {
            this.j.setVisibility(8);
            this.b.setVisibility(8);
        } else if (size == 1) {
            this.b.setVisibility(0);
            this.l.setVisibility(0);
            this.m.setVisibility(8);
            this.p.setVisibility(8);
            a(this.l, ((a) this.e.get(0)).a);
            this.l.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnByPassEntranceHeadView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "1");
                    hashMap.put("location", "1");
                    i.a("event_F6", hashMap, this.a.getContext());
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("feedEntranceActivity", true);
                    bundle.putString("feedQurl", ((a) this.a.e.get(0)).b);
                    bundle.putInt("feedEntranceActivityNeedlogin", ((a) this.a.e.get(0)).c);
                    this.a.k.doFunction(bundle);
                }
            });
        } else if (size == 2) {
            this.b.setVisibility(0);
            this.l.setVisibility(8);
            this.m.setVisibility(0);
            this.p.setVisibility(8);
            a(this.n, ((a) this.e.get(0)).a);
            this.n.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnByPassEntranceHeadView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "2");
                    hashMap.put("location", "1");
                    i.a("event_F6", hashMap, this.a.getContext());
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("feedEntranceActivity", true);
                    bundle.putString("feedQurl", ((a) this.a.e.get(0)).b);
                    bundle.putInt("feedEntranceActivityNeedlogin", ((a) this.a.e.get(0)).c);
                    this.a.k.doFunction(bundle);
                }
            });
            a(this.o, ((a) this.e.get(1)).a);
            this.o.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnByPassEntranceHeadView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "2");
                    hashMap.put("location", "2");
                    i.a("event_F6", hashMap, this.a.getContext());
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("feedEntranceActivity", true);
                    bundle.putString("feedQurl", ((a) this.a.e.get(1)).b);
                    bundle.putInt("feedEntranceActivityNeedlogin", ((a) this.a.e.get(1)).c);
                    this.a.k.doFunction(bundle);
                }
            });
        } else if (size == 3) {
            this.b.setVisibility(0);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.p.setVisibility(0);
            a(this.q, ((a) this.e.get(0)).a);
            this.q.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnByPassEntranceHeadView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "3");
                    hashMap.put("location", "1");
                    i.a("event_F6", hashMap, this.a.getContext());
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("feedEntranceActivity", true);
                    bundle.putString("feedQurl", ((a) this.a.e.get(0)).b);
                    bundle.putInt("feedEntranceActivityNeedlogin", ((a) this.a.e.get(0)).c);
                    this.a.k.doFunction(bundle);
                }
            });
            a(this.r, ((a) this.e.get(1)).a);
            this.r.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnByPassEntranceHeadView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "3");
                    hashMap.put("location", "2");
                    i.a("event_F6", hashMap, this.a.getContext());
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("feedEntranceActivity", true);
                    bundle.putString("feedQurl", ((a) this.a.e.get(1)).b);
                    bundle.putInt("feedEntranceActivityNeedlogin", ((a) this.a.e.get(1)).c);
                    this.a.k.doFunction(bundle);
                }
            });
            a(this.s, ((a) this.e.get(2)).a);
            this.s.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnByPassEntranceHeadView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "3");
                    hashMap.put("location", "3");
                    i.a("event_F6", hashMap, this.a.getContext());
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("feedEntranceActivity", true);
                    bundle.putString("feedQurl", ((a) this.a.e.get(2)).b);
                    bundle.putInt("feedEntranceActivityNeedlogin", ((a) this.a.e.get(2)).c);
                    this.a.k.doFunction(bundle);
                }
            });
        }
    }

    private void e() {
        int size = this.f.size();
        FeedColumnByPassView feedColumnByPassView;
        FeedColumnByPassView feedColumnByPassView2;
        FeedColumnByPassView feedColumnByPassView3;
        FeedColumnByPassView feedColumnByPassView4;
        if (size == 4) {
            this.h.setVisibility(0);
            this.i.setVisibility(8);
            feedColumnByPassView = (FeedColumnByPassView) this.h.findViewById(R.id.entrance_1);
            feedColumnByPassView2 = (FeedColumnByPassView) this.h.findViewById(R.id.entrance_2);
            feedColumnByPassView3 = (FeedColumnByPassView) this.h.findViewById(R.id.entrance_3);
            feedColumnByPassView4 = (FeedColumnByPassView) this.h.findViewById(R.id.entrance_4);
            if (this.g.size() > 0) {
                this.g.clear();
            }
            this.g.add(feedColumnByPassView);
            this.g.add(feedColumnByPassView2);
            this.g.add(feedColumnByPassView3);
            this.g.add(feedColumnByPassView4);
            feedColumnByPassView.a((b) this.f.get(0), this.k);
            feedColumnByPassView2.a((b) this.f.get(1), this.k);
            feedColumnByPassView3.a((b) this.f.get(2), this.k);
            feedColumnByPassView4.a((b) this.f.get(3), this.k);
        } else if (size == 5) {
            this.h.setVisibility(8);
            this.i.setVisibility(0);
            feedColumnByPassView = (FeedColumnByPassView) this.i.findViewById(R.id.entrance_1);
            feedColumnByPassView2 = (FeedColumnByPassView) this.i.findViewById(R.id.entrance_2);
            feedColumnByPassView3 = (FeedColumnByPassView) this.i.findViewById(R.id.entrance_3);
            feedColumnByPassView4 = (FeedColumnByPassView) this.i.findViewById(R.id.entrance_4);
            FeedColumnByPassView feedColumnByPassView5 = (FeedColumnByPassView) this.i.findViewById(R.id.entrance_5);
            if (this.g.size() > 0) {
                this.g.clear();
            }
            this.g.add(feedColumnByPassView);
            this.g.add(feedColumnByPassView2);
            this.g.add(feedColumnByPassView3);
            this.g.add(feedColumnByPassView4);
            this.g.add(feedColumnByPassView5);
            feedColumnByPassView.a((b) this.f.get(0), this.k);
            feedColumnByPassView2.a((b) this.f.get(1), this.k);
            feedColumnByPassView3.a((b) this.f.get(2), this.k);
            feedColumnByPassView4.a((b) this.f.get(3), this.k);
            feedColumnByPassView5.a((b) this.f.get(4), this.k);
        }
    }

    protected void a(ImageView imageView, String str) {
        c.a(getContext()).a(str, imageView, com.qq.reader.common.imageloader.a.a().j());
    }

    public void b() {
        if (this.g.size() > 0) {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                ((FeedColumnByPassView) it.next()).e();
            }
        }
    }
}
