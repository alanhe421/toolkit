package com.qq.reader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.k;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.c;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentAutoMoreforOther;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.item.r;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.impl.i;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.feed.card.view.HallOfFameTabItemView;
import com.qq.reader.view.LinearListView;
import com.qq.reader.view.LinearListView.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class HallOfFameActivity extends NativeBookStoreConfigBaseActivity implements com.qq.reader.module.bookstore.qnative.c.a {
    protected Bundle a = null;
    b b = new b(this) {
        final /* synthetic */ HallOfFameActivity a;

        {
            this.a = r1;
        }

        public void a(LinearListView linearListView, View view, int i, long j) {
            this.a.q.setCurrentItem(i, false);
            ((HallOfFameTabItemView) this.a.s.b(this.a.t).getTag()).b();
            this.a.t = i;
            ((HallOfFameTabItemView) this.a.s.b(i).getTag()).a();
        }
    };
    private Context c;
    private String d = "";
    private int n = 0;
    private TextView o = null;
    private a p;
    private WebAdViewPager q;
    private int r;
    private LinearListView s;
    private int t = 0;
    private BaseAdapter u = new BaseAdapter(this) {
        final /* synthetic */ HallOfFameActivity a;

        {
            this.a = r1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.a.getLayoutInflater().inflate(R.layout.localstore_card_author_left, viewGroup, false);
            }
            HallOfFameTabItemView hallOfFameTabItemView = new HallOfFameTabItemView(this.a.c, null, view);
            hallOfFameTabItemView.setTabItemData((r) ((i) this.a.j).x().get(i));
            view.setTag(hallOfFameTabItemView);
            return view;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public Object getItem(int i) {
            return ((i) this.a.j).x().get(i);
        }

        public int getCount() {
            this.a.r = ((i) this.a.j).x().size();
            return this.a.r;
        }
    };

    private class a extends com.qq.reader.module.bookstore.qweb.a {
        final /* synthetic */ HallOfFameActivity a;

        public a(HallOfFameActivity hallOfFameActivity, k kVar) {
            this.a = hallOfFameActivity;
            super(kVar);
        }

        public int a() {
            return this.a.r;
        }

        public BaseFragment d(int i) {
            return c(i);
        }

        public int a(Object obj) {
            return super.a(obj);
        }

        private BaseFragment c(int i) {
            InstantiationException e;
            IllegalAccessException e2;
            r rVar = (r) ((i) this.a.j).x().get(i);
            if (rVar == null) {
                return null;
            }
            Class cls;
            if (rVar.a().equalsIgnoreCase("more")) {
                cls = NativePageFragmentAutoMoreforOther.class;
            } else {
                cls = NativePageFragmentforOther.class;
            }
            BaseFragment baseFragment;
            try {
                baseFragment = (NativePageFragmentforOther) cls.newInstance();
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY_ACTIONID", rVar.a());
                    bundle.putString("KEY_ACTIONTAG", this.a.i());
                    bundle.putString("KEY_JUMP_PAGENAME", "HallOfFamePage");
                    HashMap hashMap = new HashMap();
                    hashMap.put("key_data", bundle);
                    baseFragment.setHashArguments(hashMap);
                    return baseFragment;
                } catch (InstantiationException e3) {
                    e = e3;
                } catch (IllegalAccessException e4) {
                    e2 = e4;
                    e2.printStackTrace();
                    return baseFragment;
                }
            } catch (InstantiationException e5) {
                e = e5;
                baseFragment = null;
                e.printStackTrace();
                return baseFragment;
            } catch (IllegalAccessException e6) {
                e2 = e6;
                baseFragment = null;
                e2.printStackTrace();
                return baseFragment;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = getApplicationContext();
        setContentView(R.layout.localbookstore_halloffame_layout);
        this.a = getIntent().getExtras();
        this.d = this.a.getString("LOCAL_STORE_IN_TITLE");
        this.n = this.a.getInt("CURRENT_ITEM");
        this.t = this.n;
        a();
        g();
        this.p = new a(this, getSupportFragmentManager());
        this.q = (WebAdViewPager) findViewById(R.id.haffoffame_author_list_author);
        this.q.setShouldIntercept(new com.qq.reader.module.bookstore.qweb.WebAdViewPager.a(this) {
            final /* synthetic */ HallOfFameActivity a;

            {
                this.a = r1;
            }

            public boolean a() {
                return false;
            }

            public void b() {
            }
        });
    }

    protected void onResume() {
        super.onResume();
        f();
        StatisticsManager.a().b();
    }

    private void f() {
        if (this.k > 0) {
            long currentTimeMillis = System.currentTimeMillis() - this.k;
            if (currentTimeMillis >= 172800000) {
                n();
            } else if (currentTimeMillis >= 1800000) {
                m();
            }
        }
    }

    public void a() {
        super.a();
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HallOfFameActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.o = (TextView) findViewById(R.id.profile_header_title);
        this.o.setText(this.d);
        this.s = (LinearListView) findViewById(R.id.haffoffame_tab_list);
        this.s.setOnItemClickListener(this.b);
        if (this.f != null) {
            this.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HallOfFameActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.g();
                }
            });
        }
    }

    private void g() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("KEY_JUMP_PAGENAME", "HallOfFamePage");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(s.ORIGIN, "102437");
                bundle.putString(s.STATPARAM_KEY, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.j = e.a().a(bundle, this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a(false, false);
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this;
    }

    public void startActivity(Intent intent) {
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        super.startActivity(intent);
    }

    private String i() {
        int aS = d.aS(this);
        if (aS == 0) {
            aS = 3;
        }
        return String.valueOf(aS);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                super.handleMessageImp(message);
                this.s.setAdapter(this.u);
                this.q.setAdapter(this.p);
                this.q.setOffscreenPageLimit(2);
                ((HallOfFameTabItemView) this.s.b(this.n).getTag()).a();
                this.q.setCurrentItem(this.n);
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void b() {
        l();
        this.s.setVisibility(8);
        this.e.setVisibility(0);
    }

    protected void c() {
        l();
        this.s.setVisibility(0);
        this.e.setVisibility(8);
    }

    protected void d() {
        if (this.s.getVisibility() != 0 && this.r <= 0) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
        } else if (this.i != null) {
            this.i.setRefreshing(false);
        }
    }
}
