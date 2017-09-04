package com.qq.reader.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.k;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.PagerSlidingTabStrip;
import com.qq.reader.common.widget.PagerSlidingTabStrip.c;
import com.qq.reader.module.bookstore.qweb.WebAdViewPager;
import com.qq.reader.module.bookstore.qweb.channel.ColumnActivity;
import com.qq.reader.module.bookstore.qweb.channel.ColumnWebEntity;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.bookstore.qweb.fragment.BookStoreFragment;
import com.qq.reader.view.web.n;
import com.tencent.android.tpush.common.Constants;
import com.tencent.av.sdk.AVError;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class BookStoreActivity extends ReaderBaseActivity implements com.qq.reader.common.web.js.JsAdEvent.a, com.qq.reader.view.web.n.a {
    public boolean a = true;
    BroadcastReceiver b = new BroadcastReceiver(this) {
        final /* synthetic */ BookStoreActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (this.a.r == null) {
                this.a.r = new com.qq.reader.module.bookstore.qweb.channel.a();
            }
            this.a.r;
            com.qq.reader.module.bookstore.qweb.channel.a.a(this.a.getApplicationContext());
            this.a.b(intent.getIntExtra("userType", 0));
            this.a.mHandler.sendEmptyMessageDelayed(1223, 500);
            this.a.a();
        }
    };
    protected final int c = 304;
    private PagerSlidingTabStrip d;
    private WebAdViewPager e;
    private ImageView f;
    private ImageView g;
    private b h;
    private a i;
    private ArrayList<ColumnWebEntity> j = new ArrayList();
    private TextView k = null;
    private n l = null;
    private ImageView m = null;
    private View n;
    private int[] o = new int[]{0, 1, 2, 3};
    private String[] p = new String[]{ReaderApplication.getApplicationImp().getResources().getString(R.string.bookstore_title_set0), ReaderApplication.getApplicationImp().getResources().getString(R.string.bookstore_title_set1), ReaderApplication.getApplicationImp().getResources().getString(R.string.bookstore_title_set2), ReaderApplication.getApplicationImp().getResources().getString(R.string.bookstore_title_set3)};
    private GestureDetector q;
    private com.qq.reader.module.bookstore.qweb.channel.a r;
    private com.qq.reader.view.linearmenu.b s;
    private int t = -1;

    private class a extends AsyncTask<Void, Integer, ArrayList<ColumnWebEntity>> {
        final /* synthetic */ BookStoreActivity a;

        private a(BookStoreActivity bookStoreActivity) {
            this.a = bookStoreActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((ArrayList) obj);
        }

        protected void a(ArrayList<ColumnWebEntity> arrayList) {
            int i = 1;
            int i2 = 0;
            if (arrayList != null) {
                int i3;
                int currentItem = this.a.e.getCurrentItem();
                if (this.a.j != null && this.a.j.size() == arrayList.size()) {
                    i3 = 0;
                    for (int i4 = 0; i4 < this.a.j.size(); i4++) {
                        if (((ColumnWebEntity) this.a.j.get(i4)).getTitleid() != ((ColumnWebEntity) arrayList.get(i4)).getTitleid()) {
                            i3 = 1;
                        }
                    }
                    i = i3;
                }
                if (i != 0) {
                    if (currentItem < this.a.j.size() && currentItem >= 0 && this.a.j != null) {
                        ColumnWebEntity columnWebEntity = (ColumnWebEntity) this.a.j.get(currentItem);
                        for (i3 = 0; i3 < arrayList.size(); i3++) {
                            if (columnWebEntity.getTitleid() == ((ColumnWebEntity) arrayList.get(i3)).getTitleid()) {
                                i2 = i3;
                                break;
                            }
                        }
                    }
                    this.a.j = arrayList;
                    this.a.a(i2);
                }
            }
        }

        protected ArrayList<ColumnWebEntity> a(Void... voidArr) {
            ArrayList<ColumnWebEntity> j = this.a.g();
            j.q = j.size();
            for (int i = 0; i < j.size(); i++) {
                ColumnWebEntity columnWebEntity = (ColumnWebEntity) j.get(i);
                String[] b = this.a.d(columnWebEntity.getTitleid());
                columnWebEntity.setSuffixUrl(b != null ? b[d.aS(this.a)] : null);
            }
            return j;
        }
    }

    private class b extends com.qq.reader.module.bookstore.qweb.a implements c {
        final /* synthetic */ BookStoreActivity a;

        public b(BookStoreActivity bookStoreActivity, k kVar) {
            this.a = bookStoreActivity;
            super(kVar);
        }

        public View c(int i) {
            ColumnWebEntity columnWebEntity = (ColumnWebEntity) this.a.j.get(i);
            View inflate = this.a.getLayoutInflater().inflate(R.layout.bookstore_tab_item, null);
            ((TextView) inflate.findViewById(R.id.tab_text)).setText(columnWebEntity.getTitleName());
            return inflate;
        }

        public int a() {
            return this.a.j == null ? 0 : this.a.j.size();
        }

        public BaseFragment d(int i) {
            return f(i);
        }

        public int a(Object obj) {
            return super.a(obj);
        }

        private BaseFragment f(int i) {
            BaseFragment baseFragment;
            InstantiationException e;
            IllegalAccessException e2;
            ColumnWebEntity columnWebEntity = (ColumnWebEntity) this.a.j.get(i);
            if (columnWebEntity == null) {
                return null;
            }
            try {
                baseFragment = (BaseFragment) BookStoreFragment.class.newInstance();
                try {
                    Bundle bundle = new Bundle();
                    bundle.putInt("titleindex", columnWebEntity.getTilteIndex().intValue());
                    bundle.putInt("titleid", columnWebEntity.getTitleid());
                    bundle.putString("titlename", columnWebEntity.getTitleName());
                    if (TextUtils.isEmpty(columnWebEntity.getSuffixUrl())) {
                        bundle.putString("book_url", columnWebEntity.getLinkUrl());
                    } else {
                        bundle.putString("book_url", columnWebEntity.getSuffixUrl());
                    }
                    baseFragment.setArguments(bundle);
                    return baseFragment;
                } catch (InstantiationException e3) {
                    e = e3;
                    e.printStackTrace();
                    return baseFragment;
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
        this.q = new GestureDetector(this, new OnGestureListener(this) {
            final /* synthetic */ BookStoreActivity a;

            {
                this.a = r1;
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            public void onShowPress(MotionEvent motionEvent) {
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            public void onLongPress(MotionEvent motionEvent) {
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (f < 0.0f && this.a.e.getCurrentItem() == this.a.h.a() - 1) {
                    this.a.d();
                }
                return false;
            }

            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }
        });
        setContentView(R.layout.bookstore_layout);
        this.d = (PagerSlidingTabStrip) findViewById(R.id.bookstore_tabs);
        this.e = (WebAdViewPager) findViewById(R.id.bookstore_page);
        this.e.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ BookStoreActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.q.onTouchEvent(motionEvent);
                return false;
            }
        });
        this.e.setShouldIntercept(new com.qq.reader.module.bookstore.qweb.WebAdViewPager.a(this) {
            final /* synthetic */ BookStoreActivity a;

            {
                this.a = r1;
            }

            public boolean a() {
                return this.a.a;
            }

            public void b() {
                this.a.a = true;
            }
        });
        this.a = true;
        this.f = (ImageView) findViewById(R.id.bookstore_more);
        this.g = (ImageView) findViewById(R.id.bookstore_more_new);
        this.n = findViewById(R.id.bookstore_search);
        this.h = new b(this, getSupportFragmentManager());
        this.e.setOffscreenPageLimit(1);
        this.e.setAdapter(this.h);
        this.d.setViewPager(this.e);
        this.d.setShouldExpand(true);
        this.d.setBackgroundResource(R.color.bookstore_tab_bg);
        this.d.setIndicatorColorResource(R.color.textcolor_white);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.common_dp_4);
        this.d.setLineRightAndLeftPadding(dimensionPixelOffset, dimensionPixelOffset);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookStoreActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.c(this.a, "");
            }
        });
        if (2048 != (d.al(this) & 2048)) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookStoreActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
        final e d = this.h.d();
        this.d.setOnPageChangeListener(new e(this) {
            final /* synthetic */ BookStoreActivity b;

            public void onPageSelected(int i) {
                d.onPageSelected(i);
                this.b.a(((ColumnWebEntity) this.b.j.get(i)).getTitleid(), ((ColumnWebEntity) this.b.j.get(i)).getWebid());
            }

            public void onPageScrolled(int i, float f, int i2) {
                d.onPageScrolled(i, f, i2);
            }

            public void onPageScrollStateChanged(int i) {
                d.onPageScrollStateChanged(i);
            }
        });
        e();
        setIsShowNightMask(false);
        a();
    }

    private void a(int i, int i2) {
        if (i2 == 0) {
            switch (i) {
                case 10001:
                    j.a(22, 2);
                    return;
                case Constants.CODE_LOGIC_REGISTER_IN_PROCESS /*10002*/:
                    j.a(23, 2);
                    return;
                case 10003:
                    j.a(24, 2);
                    return;
                case 10004:
                    j.a(25, 2);
                    return;
                case AVError.AV_ERR_SERVER_CONNECT_ROOM_FAIL /*10005*/:
                    j.a(26, 2);
                    return;
                default:
                    f.b("BookStoreActivity", "do no have the title : titleid = " + i);
                    return;
            }
        }
        switch (i2) {
            case 301126:
                j.a(27, 2);
                return;
            case 301129:
                j.a(28, 2);
                return;
            case 301130:
                j.a(32, 2);
                return;
            case 301131:
                j.a(29, 2);
                return;
            case 301132:
                j.a(30, 2);
                return;
            case 301133:
                j.a(31, 2);
                return;
            case 301134:
                j.a(34, 2);
                return;
            case 301135:
                j.a(33, 2);
                return;
            case 301136:
                j.a(35, 2);
                return;
            case 301137:
                j.a(36, 2);
                return;
            case 301138:
                j.a(37, 2);
                return;
            case 301140:
                j.a(38, 2);
                return;
            case 301141:
                j.a(40, 2);
                return;
            case 301142:
                j.a(41, 2);
                return;
            case 301143:
                j.a(39, 2);
                return;
            case 301144:
                j.a(42, 2);
                return;
            default:
                f.b("BookStoreActivity", "do no have the title : webId = " + i2);
                return;
        }
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(this.b, new IntentFilter("com.qq.reader.selectpreference.mainactivity"));
    }

    private void d() {
        if (this.j != null && this.j.size() != 0) {
            d.t((Context) this, d.al(this) | 2048);
            this.g.setVisibility(8);
            com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            Intent intent = new Intent(getApplicationContext(), ColumnActivity.class);
            intent.putExtra(com.qq.reader.module.bookstore.qweb.channel.a.b, this.e != null ? ((ColumnWebEntity) this.j.get(this.e.getCurrentItem())).getTitleid() : 0);
            startActivityForResult(intent, 1011);
        }
    }

    protected void onPause() {
        if (this.l != null) {
            this.l.dismiss();
        }
        int currentItem = (this.e == null || this.j == null) ? 0 : this.e.getCurrentItem();
        this.t = currentItem;
        unregisterReceiver(this.b);
        super.onPause();
    }

    public void a() {
        this.i = new a();
        this.i.execute(new Void[0]);
    }

    protected boolean handleMessageImp(Message message) {
        return super.handleMessageImp(message);
    }

    private boolean b(int i) {
        if (this.l.h() != i) {
            this.l.b(i);
            d.F(ReaderApplication.getApplicationImp().getApplicationContext(), i);
            this.k.setText(c(i));
            this.e.setCurrentItem(0, false);
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                ColumnWebEntity columnWebEntity = (ColumnWebEntity) this.j.get(i2);
                String[] d = d(columnWebEntity.getTitleid());
                columnWebEntity.setSuffixUrl(d != null ? d[i] : null);
            }
            b();
            Intent intent = new Intent();
            intent.setAction("com.qq.reader.refreshclassify");
            sendBroadcast(intent);
        }
        return false;
    }

    private void e() {
        if (this.l == null) {
            findViewById(R.id.bookstore_logo_area).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookStoreActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.f();
                }
            });
            this.m = (ImageView) findViewById(R.id.recommendSettingTipIV);
            this.k = (TextView) findViewById(R.id.recommendSettingTV);
            this.k.setText(c(d.aS(getApplicationContext())));
            this.l = new n(this, R.layout.webpage_popup_menu);
            this.l.c(true);
            for (int i = 0; i < this.o.length; i++) {
                this.l.a(this.o[i], this.p[i], null);
            }
            this.l.a(new com.qq.reader.view.web.n.a(this) {
                final /* synthetic */ BookStoreActivity a;

                {
                    this.a = r1;
                }

                public boolean b(int i, Bundle bundle) {
                    this.a.l.cancel();
                    return this.a.b(i);
                }
            });
            this.l.a(new OnCancelListener(this) {
                final /* synthetic */ BookStoreActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.m.setBackgroundResource(R.drawable.bookstore_title_arrow);
                }
            });
        }
        Window i2 = this.l.i();
        LayoutParams attributes = i2.getAttributes();
        attributes.gravity = 51;
        attributes.width = getResources().getDimensionPixelOffset(R.dimen.common_dp_140);
        attributes.x = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        i2.setAttributes(attributes);
    }

    private String c(int i) {
        int i2 = 0;
        if (i < 0) {
            i = 0;
        }
        int i3 = 0;
        while (i2 < this.o.length) {
            if (this.o[i2] == i) {
                i3 = i2;
            }
            i2++;
        }
        return this.p[i3];
    }

    private String[] d(int i) {
        switch (i) {
            case 10001:
                return getResources().getStringArray(R.array.recommned_url_10001);
            case Constants.CODE_LOGIC_REGISTER_IN_PROCESS /*10002*/:
                return getResources().getStringArray(R.array.recommned_url_10002);
            case 10003:
                return getResources().getStringArray(R.array.recommned_url_10003);
            case AVError.AV_ERR_SERVER_CONNECT_ROOM_FAIL /*10005*/:
                return getResources().getStringArray(R.array.recommned_url_10005);
            default:
                return null;
        }
    }

    private void f() {
        if (this.l.f()) {
            this.m.setBackgroundResource(R.drawable.bookstore_title_arrow);
            this.l.cancel();
            return;
        }
        this.m.setBackgroundResource(R.drawable.bookstore_title_arrow_up);
        this.l.b(d.aS(getApplicationContext()));
        this.l.f_();
    }

    protected void onStop() {
        if (this.l != null) {
            this.l.dismiss();
        }
        this.a = true;
        super.onStop();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("adapter", this.h.b());
        bundle.putInt("sel", this.e.getCurrentItem());
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        this.h.a(bundle.getParcelable("adapter"), null);
        this.t = bundle.getInt("sel");
        MainActivity.b = true;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void b() {
        this.e.setAdapter(this.h);
        this.h.c();
        this.d.a();
    }

    public void a(final int i) {
        this.e.setAdapter(this.h);
        this.h.c();
        this.d.a();
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ BookStoreActivity b;

            public void run() {
                this.b.e.setCurrentItem(i, false);
            }
        }, 300);
    }

    private ArrayList<ColumnWebEntity> g() {
        return com.qq.reader.module.bookstore.qweb.channel.d.a().a(Boolean.valueOf(true));
    }

    public boolean b(int i, Bundle bundle) {
        return false;
    }

    public com.qq.reader.view.linearmenu.a c() {
        this.s = new com.qq.reader.view.linearmenu.b(this);
        this.s.a(0, "刷新", null);
        this.s.a(1, getString(R.string.online_history), null);
        this.s.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ BookStoreActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.s.cancel();
                return this.a.a(i, bundle);
            }
        });
        this.s.a(new OnCancelListener(this) {
            final /* synthetic */ BookStoreActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getWindow().closeAllPanels();
            }
        });
        return this.s;
    }

    protected boolean a(int i, Bundle bundle) {
        BaseFragment e = this.h.e(this.e.getCurrentItem());
        if (e == null) {
            return false;
        }
        switch (i) {
            case 0:
                if (e instanceof BookStoreFragment) {
                    ((BookStoreFragment) e).refresh();
                }
                j.a(1, 2);
                return true;
            case 1:
                if (e instanceof BookStoreFragment) {
                    ((BookStoreFragment) e).onCreateHisDialog();
                }
                j.a(2, 2);
                return true;
            default:
                return false;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        BaseFragment e = this.h.e(this.e.getCurrentItem());
        if (i == 4) {
            if (!(e == null || !(e instanceof BookStoreFragment) || ((BookStoreFragment) e).stop())) {
                j.a(3, 2);
                ((MainActivity) getParent()).a("bookstand_tab");
            }
            return true;
        } else if (i != 82) {
            return super.onKeyDown(i, keyEvent);
        } else {
            c().f_();
            j.a(0, 2);
            return true;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.h.e(this.e.getCurrentItem()) != null) {
            this.h.e(this.e.getCurrentItem()).onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
        if (i == 1011 && intent != null) {
            this.t = intent.getIntExtra(com.qq.reader.module.bookstore.qweb.channel.a.c, -1);
            a();
        }
    }

    public void setTouched(boolean z) {
        this.a = !z;
    }
}
