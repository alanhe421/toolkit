package com.qq.reader.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.fragment.RankWebFragment;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.view.b.b;
import com.qq.reader.view.web.n;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RankWebActivity extends BaseWebTabActivity implements e, com.qq.reader.view.web.n.a {
    private String[] A = null;
    private ArrayList<a> B = new ArrayList();
    private String k;
    private String l = "";
    private int m = -1;
    private int n = -1;
    private ImageView o = null;
    private n p;
    private OnClickListener q = null;
    private RankWebFragment r = new RankWebFragment();
    private b s;
    private boolean t;
    private String u;
    private String v;
    private String[] w;
    private String[] x;
    private String[] y;
    private String[] z = null;

    class a {
        String a;
        String b;
        String[] c;
        String[] d;
        final /* synthetic */ RankWebActivity e;

        a(RankWebActivity rankWebActivity) {
            this.e = rankWebActivity;
        }
    }

    protected void a(Bundle bundle) {
    }

    protected String e() {
        return "";
    }

    protected void onCreate(Bundle bundle) {
        this.k = getIntent().getExtras().getString("com.qq.reader.WebContent") + "&up=" + d.aS(this);
        this.t = getIntent().getExtras().getBoolean("need_tip", false);
        HashMap hashMap = new HashMap();
        hashMap.put("book_url", this.k);
        this.g.add(new TabInfo(this.r, "", "", hashMap));
        super.onCreate(bundle);
        this.a.setIndicatorColorResource(R.color.textcolor_white);
        this.a.setIndicatorHeight(getResources().getDimensionPixelOffset(R.dimen.common_dp_2));
        this.a.setVisibility(8);
        this.a.setOnPageChangeListener(this);
        this.b.setOffscreenPageLimit(3);
        this.o = (ImageView) findViewById(R.id.profile_header_title_sort);
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        this.d.setLayoutParams(layoutParams);
        this.a.setIndicatorBottomPadding(0);
    }

    private void j() {
        if (this.p == null) {
            this.p = new n(this, R.layout.webpage_popup_menu);
            this.p.a(new OnCancelListener(this) {
                final /* synthetic */ RankWebActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.o.setBackgroundResource(R.drawable.bookstore_title_arrow);
                }
            });
        }
        this.p.b(this.n);
        this.o.setVisibility(0);
        this.o.setBackgroundResource(R.drawable.bookstore_title_arrow);
        this.q = new OnClickListener(this) {
            final /* synthetic */ RankWebActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.k();
            }
        };
    }

    private void k() {
        if (this.p.f()) {
            this.o.setBackgroundResource(R.drawable.bookstore_title_arrow);
            this.p.cancel();
            return;
        }
        this.o.setBackgroundResource(R.drawable.bookstore_title_arrow_up);
        this.p.f_();
    }

    public void doPageAction(String str) {
        if (TextUtils.isEmpty(this.l)) {
            this.l = str;
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.u = jSONObject.optString("actioncode");
                if ("1002".equals(this.u)) {
                    b(jSONObject);
                } else if ("1001".equals(this.u)) {
                    a(jSONObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                f.d("zxc", "error " + e);
            }
        }
    }

    private void a(JSONObject jSONObject) {
        int i;
        int i2;
        int i3 = 0;
        JSONObject optJSONObject = jSONObject.optJSONObject("pageinfo");
        JSONArray optJSONArray = optJSONObject.optJSONArray("rankList");
        this.v = optJSONObject.optString("showRankId");
        if (optJSONArray != null) {
            i = 0;
            for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                a aVar = new a(this);
                aVar.a = optJSONObject2.optString("rankId");
                aVar.b = optJSONObject2.optString("rankName");
                aVar.d = optJSONObject2.optString("seqNames").split(",");
                aVar.c = optJSONObject2.optString("seqs").split(",");
                if (aVar.a != null && aVar.a.equals(this.v)) {
                    i = i2;
                }
                this.B.add(aVar);
            }
        } else {
            i = 0;
        }
        this.n = i;
        this.g.clear();
        a aVar2 = (a) this.B.get(i);
        if (aVar2.c != null) {
            int i4;
            for (i4 = 0; i4 < aVar2.c.length; i4++) {
                HashMap hashMap = new HashMap();
                String str = "";
                hashMap.put("book_url", a(a(this.k, "rid", "" + aVar2.a), "seq", aVar2.c[i4]));
                this.g.add(new TabInfo(RankWebFragment.class, "", aVar2.d[i4], hashMap));
            }
            c();
            this.a.setVisibility(0);
            if (aVar2.d.length <= 1) {
                this.a.setVisibility(8);
            }
            this.a.setIndicatorColorResource(R.color.textcolor_white);
            this.h.setText(((a) this.B.get(i)).b);
            i = this.g.size();
            i4 = com.qq.reader.common.c.a.bU / i;
            i2 = i4 / 8;
            if (i == 2 || i == 3) {
                i2 = (i4 - getResources().getDimensionPixelOffset(R.dimen.common_dp_80)) / 2;
            }
            this.a.setLineRightAndLeftPadding(i2, i2);
            this.o.setBackgroundResource(R.drawable.bookstore_title_arrow);
            j();
            this.p.g();
            while (i3 < this.B.size()) {
                aVar2 = (a) this.B.get(i3);
                this.p.a(Integer.parseInt(aVar2.a), aVar2.b, null);
                i3++;
            }
            this.p.a((com.qq.reader.view.web.n.a) this);
            this.o.setOnClickListener(this.q);
            this.h.setOnClickListener(this.q);
        }
    }

    private void b(JSONObject jSONObject) {
        int i;
        int i2 = 0;
        JSONObject optJSONObject = jSONObject.optJSONObject("pageinfo");
        this.v = optJSONObject.optString("showCategory");
        JSONArray optJSONArray = optJSONObject.optJSONArray("categoryList");
        this.w = new String[optJSONArray.length()];
        this.x = new String[optJSONArray.length()];
        this.y = new String[optJSONArray.length()];
        for (i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
            this.w[i] = optJSONObject2.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
            this.x[i] = optJSONObject2.optString("categoryName");
            this.y[i] = optJSONObject2.optString("categoryLevel");
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("sortList");
        this.A = new String[optJSONArray2.length()];
        this.z = new String[optJSONArray2.length()];
        for (i = 0; i < optJSONArray2.length(); i++) {
            JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i);
            this.z[i] = optJSONObject3.optString("sort");
            this.A[i] = optJSONObject3.optString("sortName");
        }
        if (this.z != null) {
            this.b.setOffscreenPageLimit(this.z.length);
            this.g.clear();
            for (i = 0; i < this.w.length; i++) {
                if (this.w[i].equals(this.v)) {
                    this.n = i;
                    break;
                }
            }
            for (i = 0; i < this.z.length; i++) {
                HashMap hashMap = new HashMap();
                String str = "";
                hashMap.put("book_url", a(a(a(this.k, "cid", this.w[this.n]), "cLevel", this.y[this.n]), "sort", this.z[i]));
                this.g.add(new TabInfo(RankWebFragment.class, "", this.A[i], hashMap));
            }
            c();
            this.a.setVisibility(0);
            this.a.setIndicatorColorResource(R.color.textcolor_white);
            this.h.setText(this.x[this.n]);
            int size = this.g.size();
            int i3 = com.qq.reader.common.c.a.bU / size;
            i = i3 / 8;
            if (size == 2 || size == 3) {
                i = (i3 - getResources().getDimensionPixelOffset(R.dimen.common_dp_80)) / 2;
            }
            this.a.setLineRightAndLeftPadding(i, i);
            this.o.setBackgroundResource(R.drawable.bookstore_title_arrow);
        }
        j();
        this.p.g();
        while (i2 < this.x.length) {
            this.p.a(Integer.parseInt(this.w[i2]), this.x[i2], null);
            i2++;
        }
        this.p.a((com.qq.reader.view.web.n.a) this);
        this.o.setOnClickListener(this.q);
        this.h.setOnClickListener(this.q);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
    }

    public void onPageScrollStateChanged(int i) {
    }

    public boolean b(int i, Bundle bundle) {
        return true;
    }

    protected void onPause() {
        if (this.s != null && this.s.c()) {
            this.s.b();
        }
        super.onPause();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if ((i != 4 && i != 82) || this.s == null || !this.s.d()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.s.b();
        return true;
    }

    public static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return str;
        }
        return str.replaceAll("(" + str2 + "=[^&]*)", str2 + "=" + str3);
    }

    public boolean b() {
        return true;
    }

    public View a(int i) {
        TabInfo tabInfo = (TabInfo) this.g.get(i);
        View inflate = getLayoutInflater().inflate(R.layout.profileaccount_tab_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab_text);
        textView.setText(tabInfo.title);
        textView.setTextColor(getResources().getColor(R.color.textcolor_white));
        if (this.j.size() > i) {
            this.j.set(i, inflate);
        } else {
            while (this.j.size() <= i) {
                this.j.add(null);
            }
            this.j.set(i, inflate);
        }
        if (b()) {
            if (this.b.getCurrentItem() == i) {
                textView.setTextColor(getResources().getColor(R.color.textcolor_green));
            } else {
                textView.setTextColor(getResources().getColor(R.color.textcolor_black));
            }
        }
        return inflate;
    }
}
