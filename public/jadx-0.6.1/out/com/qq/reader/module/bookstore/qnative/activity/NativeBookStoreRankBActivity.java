package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.activity.BaseWebTabActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentForLeftTab;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.view.GuideShadowView;
import com.qq.reader.view.k;
import com.qq.reader.view.web.n;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class NativeBookStoreRankBActivity extends BaseWebTabActivity implements a, n.a {
    protected Bundle k = null;
    private String[] l = new String[]{"boy", "pub", "girl", "comics", "audio"};
    private TextView m;
    private Button n;
    private View o;
    private n p;
    private GuideShadowView q;
    private HashMap<String, d> r = new HashMap();
    private String s;
    private boolean t = true;
    private String[] u = new String[]{"男生", "出版", "女生", "动漫", "音频"};
    private String[] v = new String[]{"1", "3", "2", "4", "5"};
    private k w;

    public void doFunction(Bundle bundle) {
    }

    protected void onCreate(Bundle bundle) {
        this.k = getIntent().getExtras();
        this.s = this.k.getString("URL_BUILD_PERE_RANK");
        super.onCreate(bundle);
        j();
    }

    public void j() {
        this.m = (TextView) findViewById(R.id.profile_header_title);
        this.o = findViewById(R.id.common_titler);
        this.n = (Button) findViewById(R.id.profile_header_right_button);
        findViewById(R.id.common_tab__line).setVisibility(8);
        this.n.setBackgroundResource(R.drawable.rankboard_title_right_filter);
        Drawable drawable = getResources().getDrawable(R.drawable.rankboard_filter_btn_selector);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.n.setCompoundDrawables(null, null, drawable, null);
        this.n.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_2));
        this.n.setGravity(17);
        this.n.setMaxLines(1);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreRankBActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.n();
            }
        });
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreRankBActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.b.setOffscreenPageLimit(this.l.length);
        this.b.setShouldIntercept(new WebAdViewPager.a(this) {
            final /* synthetic */ NativeBookStoreRankBActivity a;

            {
                this.a = r1;
            }

            public boolean a() {
                return this.a.t;
            }

            public void b() {
                this.a.t = true;
            }
        });
        this.t = true;
        int intValue = Integer.valueOf(this.s).intValue();
        if (intValue <= this.g.size()) {
            this.b.setCurrentItem(b(intValue));
            if (intValue == 4) {
                i.a("event_F346", null, getContext());
            } else if (intValue == 5) {
                i.a("event_B293", null, getContext());
            }
        } else {
            this.b.setCurrentItem(0);
        }
        this.a.a();
        this.a.setOnPageChaneListenerForTitle(new e(this) {
            final /* synthetic */ NativeBookStoreRankBActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                this.a.a.setTextSizeAndColor(i, (int) this.a.getResources().getDimension(R.dimen.text_size_class_5), this.a.getResources().getColor(R.color.text_color_c104));
                this.a.l();
                if (!TextUtils.isEmpty(this.a.l[i])) {
                    if (this.a.r == null || this.a.r.get(this.a.l[i]) == null) {
                        this.a.n.setVisibility(4);
                    } else {
                        d dVar = (d) this.a.r.get(this.a.l[i]);
                        if (dVar.f() == null || dVar.f().size() <= 1) {
                            this.a.n.setVisibility(4);
                        } else {
                            this.a.n.setVisibility(0);
                            this.a.a(true, dVar);
                        }
                    }
                    Map hashMap = new HashMap();
                    hashMap.put("pre", this.a.l[i]);
                    i.a("event_F315", hashMap, this.a.getContext());
                    if (this.a.l[3].equals(this.a.l[i])) {
                        i.a("event_F346", null, this.a.getContext());
                    } else if (this.a.l[4].equals(this.a.l[i])) {
                        i.a("event_B293", null, this.a.getContext());
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        findViewById(R.id.common_titler).setBackgroundResource(R.drawable.rank_page_top_title_bg);
        this.a.setUnderlineHeight(0);
        this.a.setBackgroundResource(R.drawable.rank_page_top_title_bg);
        this.a.setTextColorResource(R.color.text_color_c104_p60);
        this.a.setTextSize((int) getResources().getDimension(R.dimen.text_size_class_3));
        this.a.setIndicatorColorResource(R.color.translucent);
        this.a.setTextSizeAndColor(b(intValue), (int) getResources().getDimension(R.dimen.text_size_class_5), getResources().getColor(R.color.text_color_c104));
    }

    private int b(int i) {
        switch (i) {
            case 2:
                return 2;
            case 3:
                return 1;
            case 4:
                return 3;
            case 5:
                return 4;
            default:
                return 0;
        }
    }

    private void n() {
        if (this.p != null) {
            Drawable drawable;
            if (this.p.f()) {
                this.p.cancel();
                drawable = getResources().getDrawable(R.drawable.rankboard_filter_nor);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                this.n.setCompoundDrawables(null, null, drawable, null);
                return;
            }
            this.p.c().a((int) R.id.readpage_topbar_popup);
            this.p.a(true);
            drawable = getResources().getDrawable(R.drawable.rankboard_filter_sel);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.n.setCompoundDrawables(null, null, drawable, null);
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public boolean b(int i, Bundle bundle) {
        return false;
    }

    public boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 1002:
                d dVar = (d) message.obj;
                if (dVar == null) {
                    this.n.setVisibility(4);
                    return true;
                }
                a(message.arg1 == 1, dVar);
                l();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void a(Bundle bundle) {
        for (int i = 0; i < this.u.length; i++) {
            HashMap hashMap = new HashMap();
            hashMap.put("URL_BUILD_PERE_RANK", this.k.getString("URL_BUILD_PERE_RANK"));
            hashMap.put("KEY_JUMP_PAGENAME", this.k.getString("KEY_JUMP_PAGENAME"));
            hashMap.put("KEY_ACTIONID", this.k.getString("KEY_ACTIONID"));
            hashMap.put("URL_BUILD_PERE_RANK", this.v[i]);
            this.g.add(i, new TabInfo(NativePageFragmentForLeftTab.class, "", this.u[i], hashMap));
        }
    }

    public void a(boolean z, d dVar) {
        if (this.p == null) {
            LayoutParams layoutParams = (LayoutParams) this.n.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = (int) getResources().getDimension(R.dimen.common_dp_20);
                layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.activity_left_right_margin);
                this.n.setLayoutParams(layoutParams);
                this.n.setPadding((int) getResources().getDimension(R.dimen.common_dp_6), 0, (int) getResources().getDimension(R.dimen.common_dp_6), 0);
            }
            this.p = new n(this, R.layout.webpage_popup_menu);
            this.p.a(new OnCancelListener(this) {
                final /* synthetic */ NativeBookStoreRankBActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    Drawable drawable = this.a.getResources().getDrawable(R.drawable.rankboard_filter_nor);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    this.a.n.setCompoundDrawables(null, null, drawable, null);
                    if (this.a.q != null) {
                        ((ViewGroup) this.a.getWindow().getDecorView()).removeView(this.a.q);
                    }
                }
            });
            this.p.a(new n.a(this) {
                final /* synthetic */ NativeBookStoreRankBActivity a;

                {
                    this.a = r1;
                }

                public boolean b(int i, Bundle bundle) {
                    this.a.p.b(i);
                    this.a.m();
                    return false;
                }
            });
            this.p.a(new OnShowListener(this) {
                final /* synthetic */ NativeBookStoreRankBActivity a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    if (!com.qq.reader.appconfig.a.d.n) {
                        if (this.a.q == null) {
                            this.a.q = new GuideShadowView(this.a);
                        }
                        this.a.q.setHighLightRect(this.a.k());
                        ((ViewGroup) this.a.getWindow().getDecorView()).addView(this.a.q);
                    }
                }
            });
        }
        if (this.r.get(dVar.b()) != null) {
            this.r.remove(dVar.b());
        }
        if (dVar != null) {
            this.r.put(dVar.b(), dVar);
            if (!o().equals(dVar.b())) {
                return;
            }
            if (dVar.f().size() > 1) {
                this.p.g();
                int i = 0;
                while (i < dVar.f().size()) {
                    this.p.a(i, a(((d.a) dVar.f().get(i)).a), null);
                    if (z && ((d.a) dVar.f().get(i)).b) {
                        this.p.b(i);
                        this.n.setText(a(((d.a) dVar.f().get(i)).a));
                    }
                    i++;
                }
                this.n.setVisibility(0);
                return;
            }
            this.n.setVisibility(4);
        }
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str) || str.indexOf("-") < 0) {
            return null;
        }
        return str.subSequence(str.indexOf("-") + 1, str.length()).toString();
    }

    public k k() {
        if (this.w == null) {
            View view = this.o;
            int[] iArr = new int[2];
            r2 = new int[2];
            view.getLocationOnScreen(iArr);
            r2[0] = iArr[0] + view.getWidth();
            r2[1] = view.getHeight() + iArr[1];
            this.w = new k();
            this.w.a = new Rect(iArr[0], iArr[1], r2[0], r2[1]);
            this.w.b = 1;
        }
        return this.w;
    }

    public void l() {
        if (this.n != null) {
            String o = o();
            if (this.r != null && this.r.get(o) != null) {
                this.n.setText(a(((d) this.r.get(o)).h()));
            }
        }
    }

    private String o() {
        if (this.b == null || this.b.getCurrentItem() >= this.l.length) {
            return this.l[0];
        }
        return this.l[this.b.getCurrentItem()];
    }

    protected String e() {
        return "排行榜";
    }

    public void m() {
        if (d() != null) {
            NativePageFragmentForLeftTab nativePageFragmentForLeftTab = (NativePageFragmentForLeftTab) d();
            String o = o();
            int h = this.p.h();
            if (this.r != null && this.r.get(o) != null) {
                this.n.setText(a(((d.a) ((d) this.r.get(o)).f().get(h)).a));
                String str = ((d.a) ((d) this.r.get(o)).f().get(h)).c;
                nativePageFragmentForLeftTab.loadRankDetailPageData(str, o);
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, str);
                hashMap.put("type", ((d.a) ((d) this.r.get(o)).f().get(h)).d);
                i.a("event_F316", hashMap, getContext());
            }
        }
    }
}
