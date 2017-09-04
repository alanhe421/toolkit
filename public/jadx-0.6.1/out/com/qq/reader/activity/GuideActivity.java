package com.qq.reader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.k;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.fragment.GuidFragment;
import com.qq.reader.module.bookstore.qnative.fragment.GuidFragmentBase;
import com.qq.reader.module.bookstore.qnative.fragment.GuidFragment_5;
import com.qq.reader.module.bookstore.qnative.fragment.UpgradeGuidFragment;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.feed.data.impl.g;
import com.qq.reader.view.UnTouchViewPager;
import com.qq.reader.view.i;
import com.tencent.feedback.proguard.R;
import java.lang.reflect.Field;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GuideActivity extends ReaderBaseActivity implements e {
    private UnTouchViewPager a;
    private a b;
    private ImageView c;
    private JSONObject d;
    private boolean e = false;

    class a extends com.qq.reader.module.bookstore.qweb.a {
        final /* synthetic */ GuideActivity a;
        private b[] b;

        public a(GuideActivity guideActivity, k kVar, b[] bVarArr) {
            this.a = guideActivity;
            super(kVar);
            this.b = bVarArr;
        }

        public int a() {
            return this.b.length;
        }

        public BaseFragment d(int i) {
            return c(i);
        }

        private GuidFragmentBase c(int i) {
            GuidFragmentBase guidFragmentBase;
            InstantiationException e;
            IllegalAccessException e2;
            try {
                guidFragmentBase = (GuidFragmentBase) this.b[i].b.newInstance();
                try {
                    guidFragmentBase.setPosition(i);
                    guidFragmentBase.setGuideImgResId(this.b[i].c);
                    guidFragmentBase.setGuideFragmentCount(this.b.length);
                } catch (InstantiationException e3) {
                    e = e3;
                    e.printStackTrace();
                    return guidFragmentBase;
                } catch (IllegalAccessException e4) {
                    e2 = e4;
                    e2.printStackTrace();
                    return guidFragmentBase;
                }
            } catch (InstantiationException e5) {
                InstantiationException instantiationException = e5;
                guidFragmentBase = null;
                e = instantiationException;
                e.printStackTrace();
                return guidFragmentBase;
            } catch (IllegalAccessException e6) {
                IllegalAccessException illegalAccessException = e6;
                guidFragmentBase = null;
                e2 = illegalAccessException;
                e2.printStackTrace();
                return guidFragmentBase;
            }
            return guidFragmentBase;
        }
    }

    private class b {
        final /* synthetic */ GuideActivity a;
        private Class b;
        private int c;

        public b(GuideActivity guideActivity, Class cls, int i) {
            this.a = guideActivity;
            this.b = cls;
            this.c = i;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b[] b = b();
        if (b == null || b.length <= 0) {
            a();
            return;
        }
        a(b);
        setSwipeBackEnable(false);
        getWindow().setFlags(1024, 1024);
    }

    public boolean needSetImmerseMode() {
        return false;
    }

    private void a(b[] bVarArr) {
        setContentView(R.layout.guidelayout);
        this.a = (UnTouchViewPager) findViewById(R.id.viewpager);
        this.a.setOffscreenPageLimit(4);
        try {
            Field declaredField = ViewPager.class.getDeclaredField("m");
            declaredField.setAccessible(true);
            declaredField.set(this.a, new i(this.a.getContext(), null));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
        this.c = (ImageView) findViewById(R.id.guide_img);
        this.b = new a(this, getSupportFragmentManager(), bVarArr);
        this.a.setAdapter(this.b);
        this.a.setOnPageChangeListener(this);
    }

    private b[] b() {
        if (d.j(ReaderApplication.getApplicationImp())) {
            return new b[]{new b(this, GuidFragment_5.class, R.drawable.guideimg_5)};
        } else if (com.qq.reader.common.c.a.V || com.qq.reader.common.c.a.T) {
            return new b[]{new b(this, GuidFragment.class, R.drawable.guideimg_1), new b(this, GuidFragment_5.class, R.drawable.guideimg_5)};
        } else if (com.qq.reader.common.c.a.U) {
            return new b[]{new b(this, UpgradeGuidFragment.class, R.drawable.guideimg_1)};
        } else {
            return new b[]{new b(this, GuidFragment.class, R.drawable.guideimg_1), new b(this, GuidFragment_5.class, R.drawable.guideimg_5)};
        }
    }

    public void a(int i, boolean z) {
        this.a.setCurrentItem(i, z);
    }

    public void a() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        if (getIntent().getExtras() != null) {
            intent.putExtras(getIntent().getExtras());
        }
        startActivity(intent);
        new Handler().post(new Runnable(this) {
            final /* synthetic */ GuideActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.finish();
            }
        });
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        GuidFragmentBase guidFragmentBase = (GuidFragmentBase) this.b.e(i);
        if (guidFragmentBase != null) {
            guidFragmentBase.onDataNotify();
        }
    }

    @Deprecated
    private void c() {
        c.a("guide", "saveUserLikeItem " + this.d);
        if (this.d != null) {
            String[] strArr;
            JSONObject jSONObject = new JSONObject();
            JSONObject optJSONObject = this.d.optJSONObject("datamap");
            c.a("guide", "datamap " + optJSONObject);
            JSONArray jSONArray = new JSONArray();
            switch (d.aS(this)) {
                case 1:
                    strArr = com.qq.reader.appconfig.d.e;
                    break;
                case 2:
                    strArr = com.qq.reader.appconfig.d.f;
                    break;
                default:
                    strArr = com.qq.reader.appconfig.d.e;
                    break;
            }
            int i = 0;
            while (strArr != null && i < strArr.length) {
                String str = strArr[i];
                if (TextUtils.isEmpty(str)) {
                    c.a("guide", "key is null " + i);
                } else {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject(str);
                    if (optJSONObject2 != null) {
                        jSONArray.put(optJSONObject2);
                    } else {
                        c.a("guide", "value is null  key : " + str);
                    }
                }
                i++;
            }
            if (jSONArray != null && strArr != null) {
                try {
                    jSONObject.put("infos", jSONArray);
                    jSONObject.put("showtime", "1970010100");
                    g gVar = new g();
                    gVar.b(jSONObject.toString());
                    com.qq.reader.module.feed.loader.c.b().a(gVar);
                    com.qq.reader.module.feed.loader.d.b().a(gVar);
                    c.a("guid", jSONObject.toString());
                    d.z(ReaderApplication.getApplicationImp().getApplicationContext(), "1970010100");
                } catch (JSONException e) {
                    c.a("guide", "error " + e);
                    e.printStackTrace();
                }
            }
        }
    }
}
