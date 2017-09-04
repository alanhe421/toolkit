package com.qq.reader.module.comic.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.request.b.g;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.SwipeRefreshLayout.b;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.view.EmptyView;
import com.qrcomic.a.h;
import com.qrcomic.downloader.d;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.manager.QRComicManager;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class NativeBookStoreComicDetailActivity extends NativeBookStoreConfigBaseActivity implements a {
    private boolean A = true;
    private h B;
    private Activity a;
    private TextView b;
    private ImageView c;
    private ImageView d;
    private View n = null;
    private TextView o = null;
    private Bundle p;
    private View q;
    private ViewGroup r;
    private LinearLayout s;
    private LinearLayout t;
    private ImageView u;
    private com.qq.reader.module.comic.entity.a v;
    private Bitmap w;
    private int x;
    private boolean y = true;
    private boolean z = true;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.localbookstore_comic_detail_layout);
        this.a = this;
        if (!"Meizu_M040".equals(com.qq.reader.common.c.a.E)) {
            getWindow().addFlags(SigType.WLOGIN_PF);
        }
        this.p = getIntent().getExtras();
        a();
        b();
        r();
        com.qq.reader.activity.a.a.a().a(this);
    }

    protected void onResume() {
        super.onResume();
        b(!s());
        if (!this.y && this.A) {
            o();
        }
        this.y = false;
        this.A = true;
        Object obj = "5";
        if (!(this.p == null || TextUtils.isEmpty(this.p.getString("KEY_COMIC_ORIGIN")))) {
            obj = this.p.getString("KEY_COMIC_ORIGIN");
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, obj);
        i.a("event_F247", hashMap, ReaderApplication.getApplicationImp());
    }

    protected void a() {
        super.a();
        this.v = new com.qq.reader.module.comic.entity.a();
        this.v.a = getIntent().getExtras().getString("KEY_COMIC_ID");
        this.v.b = getIntent().getExtras().getString("KEY_COMIC_NAME");
        this.r = (ViewGroup) findViewById(R.id.ll_download);
        this.s = (LinearLayout) findViewById(R.id.ll_read);
        this.t = (LinearLayout) findViewById(R.id.ll_add2shelf);
        this.u = (ImageView) findViewById(R.id.img_add_shelf_icon);
        this.i = (SwipeRefreshLayout) findViewById(R.id.detail_pull_down_list);
        this.i.setOnRefreshListener(new b(this) {
            final /* synthetic */ NativeBookStoreComicDetailActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.e_();
            }
        });
        this.q = findViewById(R.id.common_titler);
        this.q.setBackgroundColor(getResources().getColor(R.color.skin_set_bookdetail_title_bar_startcolor));
        this.b = (TextView) this.q.findViewById(R.id.profile_header_title);
        this.b.setText(g());
        this.c = (ImageView) findViewById(R.id.profile_header_left_back);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.setResult(0);
                this.a.finish();
            }
        });
        this.d = (ImageView) findViewById(R.id.out_frame);
        i();
        this.g = (ListView) findViewById(R.id.detail_list);
        this.g.setOverScrollMode(2);
        this.g.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NativeBookStoreComicDetailActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                int color = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_startcolor);
                int color2 = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_endcolor);
                if (i == 0) {
                    View childAt = absListView.getChildAt(0);
                    if (childAt != null) {
                        this.a.d.setVisibility(0);
                        if (Math.min(((double) Math.abs(childAt.getTop())) / ((double) childAt.getHeight()), 1.0d) == 1.0d) {
                            this.a.q.setBackgroundColor(color2);
                            ao.a(this.a.b, 1.0f);
                            this.a.c.setImageResource(R.drawable.titlebar_icon_back_selector);
                            return;
                        }
                        this.a.q.setBackgroundColor(color);
                        ao.a(this.a.b, 0.0f);
                        if (this.a.x != 0) {
                            this.a.c.setImageResource(this.a.x);
                        } else {
                            this.a.c.setImageResource(R.drawable.titlebar_icon_back_selector_black);
                        }
                    }
                } else if ((this.a.q.getBackground() instanceof ColorDrawable) && ((ColorDrawable) this.a.q.getBackground()).getColor() == color) {
                    this.a.f();
                } else if (this.a.d.isShown()) {
                    this.a.d.setVisibility(8);
                }
            }
        });
        this.e = findViewById(R.id.loading_layout);
        LayoutParams layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.detail_listview_margin_bottom);
        this.i.setLayoutParams(layoutParams);
        disableUseAnimation();
    }

    private void f() {
        this.q.setBackgroundColor(getResources().getColor(R.color.skin_set_bookdetail_title_bar_endcolor));
        ao.a(this.b, 1.0f);
        this.c.setImageResource(R.drawable.titlebar_icon_back_selector);
        if (this.d.isShown()) {
            this.d.setVisibility(8);
        }
    }

    private String g() {
        return "书籍详情";
    }

    private void i() {
        boolean z;
        this.n = findViewById(R.id.detail_bottom_btns);
        this.n.setVisibility(8);
        ((TextView) findViewById(R.id.detail_bottom_btns_read)).setClickable(false);
        this.s.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Object g = this.a.q();
                if (TextUtils.isEmpty(g)) {
                    com.qq.reader.module.comic.a.a().a(this.a.a, this.a.v.a);
                } else {
                    com.qq.reader.module.comic.a.a().a(this.a.a, this.a.v.a, g, 0, "", true, 0);
                }
                i.a("event_F253", null, ReaderApplication.getApplicationImp());
            }
        });
        ((TextView) findViewById(R.id.detail_bottom_btns_download)).setClickable(false);
        this.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                ComicShelfInfo a = ComicShelfInfo.a(this.a.t());
                if (a == null) {
                    a = new ComicShelfInfo();
                    a.a(this.a.v.a);
                }
                if (c.b()) {
                    o.a(this.a.a, a, 0, null, false);
                } else {
                    this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void a(int i) {
                            switch (i) {
                                case 1:
                                    o.a(this.b.a.a, a, 0, null, false);
                                    return;
                                default:
                                    return;
                            }
                        }
                    };
                    this.a.startLogin();
                }
                i.a("event_F252", null, ReaderApplication.getApplicationImp());
            }
        });
        this.o = (TextView) findViewById(R.id.tv_detail_bottom_btns_add2shelf);
        this.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                final com.qq.reader.module.comic.entity.h j = this.a.t();
                if (j != null) {
                    if (c.b()) {
                        com.qq.reader.module.comic.e.b.a(ReaderApplication.getApplicationContext(), j);
                        this.a.b(false);
                        i.a("event_F254", null, ReaderApplication.getApplicationImp());
                        return;
                    }
                    this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                        final /* synthetic */ AnonymousClass7 b;

                        public void a(int i) {
                            switch (i) {
                                case 1:
                                    com.qq.reader.module.comic.e.b.a(ReaderApplication.getApplicationContext(), j);
                                    this.b.a.b(false);
                                    return;
                                default:
                                    return;
                            }
                        }
                    };
                    this.a.startLogin();
                }
            }
        });
        if (s()) {
            z = false;
        } else {
            z = true;
        }
        b(z);
    }

    private String q() {
        com.qq.reader.module.comic.entity.h t = t();
        if (t != null) {
            return t.h();
        }
        return "";
    }

    private void r() {
        try {
            this.j = e.a().a(this.p, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.j != null) {
            if (this.h == null) {
                this.h = new f(this.a);
            }
            this.h.a(this.j);
            this.g.setAdapter(this.h);
            a(true, false);
            this.n.setVisibility(4);
        }
    }

    private boolean s() {
        return com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.v.a)) != null;
    }

    private void b(boolean z) {
        if (z) {
            this.o.setText(R.string.bookinfo_add2bookshelf_ok);
        } else {
            this.o.setText(R.string.bookinfo_add2bookshelf_already);
        }
        this.t.setEnabled(z);
        this.u.setEnabled(z);
        this.o.setEnabled(z);
    }

    private com.qq.reader.module.comic.entity.h t() {
        return ((com.qq.reader.module.comic.d.c) this.j).x();
    }

    public void a(String str, final boolean z) {
        if (z) {
            this.d.setImageResource(R.drawable.detail_new_top_bg);
            this.x = R.drawable.titlebar_icon_back_selector_black;
        } else {
            this.d.setImageResource(R.drawable.gradient_vertical_black_to_white);
            this.x = R.drawable.titlebar_icon_back_selector;
        }
        this.c.setImageResource(this.x);
        com.qq.reader.common.imageloader.c.a(this).a(str, new g(this) {
            final /* synthetic */ NativeBookStoreComicDetailActivity b;

            public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                if (obj instanceof j) {
                    Bitmap b = ((j) obj).b();
                    try {
                        if (z) {
                            this.b.a(b);
                        } else {
                            this.b.d.setBackgroundDrawable(new BitmapDrawable(b));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void a(Bitmap bitmap) {
        if (this.w == null) {
            this.w = ao.a(bitmap, -1);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), this.w);
        if (this.d.getBackground() == null) {
            Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{new BitmapDrawable(this.d.getDrawingCache()), bitmapDrawable});
            this.d.setBackgroundDrawable(transitionDrawable);
            transitionDrawable.startTransition(1200);
            return;
        }
        transitionDrawable = new TransitionDrawable(new Drawable[]{this.d.getBackground(), bitmapDrawable});
        this.d.setBackgroundDrawable(transitionDrawable);
        transitionDrawable.startTransition(1200);
    }

    public Activity getFromActivity() {
        return this;
    }

    protected void c() {
        super.c();
        this.n.setVisibility(0);
    }

    public void a(String str, int i) {
        this.g.setVisibility(8);
        d();
        if (this.f instanceof EmptyView) {
            ((EmptyView) this.f).a(0);
            ((EmptyView) this.f).a((CharSequence) str);
            ((EmptyView) this.f).b(i);
            this.f.setClickable(false);
        }
        f();
    }

    public void p() {
        super.p();
        if (this.j instanceof com.qq.reader.module.comic.d.c) {
            int i = ((com.qq.reader.module.comic.d.c) this.j).a;
            if (i == 1004) {
                a(getResources().getString(R.string.comic_book_off_shelf_tips), (int) R.drawable.empty10);
            } else if (i != 0) {
                a(((com.qq.reader.module.comic.d.c) this.j).b, (int) R.drawable.empty10);
            } else if (this.z) {
                this.z = false;
                v();
            }
        }
        u();
    }

    private void u() {
        com.qq.reader.module.comic.entity.h t = t();
        if (t != null) {
            r.a().a(3, t.a(), t.b());
        }
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
        if ("detail_2_openvip".equals(bundle.getString("KEY_ACTION"))) {
            final String string = bundle.getString("URL_DATA_QURL");
            if (c.b()) {
                a(string);
                return;
            }
            this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeBookStoreComicDetailActivity b;

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.b.o();
                            this.b.a(string);
                            return;
                        default:
                            return;
                    }
                }
            };
            startLogin();
        }
    }

    private void a(String str) {
        if (com.qq.reader.qurl.c.a(str)) {
            try {
                com.qq.reader.qurl.c.a(this, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(boolean z) {
        this.A = z;
    }

    private void v() {
        if (com.qrcomic.util.f.c(this) && com.qq.reader.module.comic.a.a().a(this)) {
            this.B = com.qrcomic.manager.b.a().b();
            final QRComicManager qRComicManager = new QRComicManager(this.B);
            Object q = q();
            final ArrayList arrayList = new ArrayList();
            arrayList.add(new QRComicBuyReqInfo(this.v.a));
            com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                public String getTaskName() {
                    return "preloadUserBuyInfo";
                }

                public void run() {
                    super.run();
                    qRComicManager.a(arrayList, null, false);
                }
            });
            if (!TextUtils.isEmpty(q)) {
                qRComicManager.a(this.v.a, q, false, false, new QRComicManager.c(this) {
                    final /* synthetic */ NativeBookStoreComicDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(com.qrcomic.entity.a aVar, int i) {
                    }

                    public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                        if (list != null && list.size() >= 1) {
                            LinkedList linkedList = new LinkedList();
                            ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) list.get(0);
                            comicSectionPicInfo.preloadLocation = "detailPage";
                            linkedList.add(comicSectionPicInfo);
                            d.b().a(linkedList, new com.qrcomic.downloader.j(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void a(ComicSectionPicInfo comicSectionPicInfo, long j, long j2) {
                                }

                                public void a(ComicSectionPicInfo comicSectionPicInfo, int i, String str) {
                                }

                                public void a(ComicSectionPicInfo comicSectionPicInfo, String str) {
                                }
                            }, true);
                        }
                    }
                }, false);
            }
        }
    }

    public void startActivity(Intent intent) {
        disableUseAnimation();
        super.startActivity(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            if (!(com.qrcomic.manager.b.a().b() == null || com.qrcomic.manager.b.a().b().h == null || com.qrcomic.manager.b.a().b().i == null)) {
                com.qrcomic.manager.b.a().b().h.c();
                com.qrcomic.manager.b.a().b().i.c();
                d.b().f();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.qq.reader.activity.a.a.a().b(this);
    }
}
