package com.qq.reader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.request.b.g;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.model.TitlerControlModel;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.n;
import com.qq.reader.widget.titler.StateChangeTitler;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class UserCenterNewActivity extends NativeBookStoreConfigBaseActivity implements OnClickListener {
    private TextView a;
    private Bundle b;
    private View c;
    private SwipeRefreshLayout d;
    private View n;
    private String o;
    private String p;
    private String q;
    private StateChangeTitler r;
    private ImageView s;
    private Bitmap t = null;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.user_center_page_new_layout);
        this.b = getIntent().getExtras();
        a();
        b();
        f();
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                try {
                    if (message.obj != null) {
                        this.j.a((b) message.obj);
                    }
                    if (this.j != null && this.j.p() == 1002) {
                        if (this.i != null) {
                            this.l = true;
                            this.i.setRefreshing(false);
                        }
                        a(this.j);
                        p();
                        j();
                        c();
                    }
                } catch (Exception e) {
                    c.e("DetailActivity", e.getMessage());
                }
                return true;
            case 500004:
                this.l = false;
                p();
                d();
                this.r.setVisibility(0);
                this.r.setBackgroundResource(R.drawable.titler_bg);
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void a(String str, final boolean z) {
        com.qq.reader.common.imageloader.c.a((Context) this).a(str, new g(this) {
            final /* synthetic */ UserCenterNewActivity b;

            public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                if (obj instanceof j) {
                    j jVar = (j) obj;
                    try {
                        if (this.b.t == null) {
                            this.b.t = ao.a(jVar.b(), -1, false);
                        }
                        Drawable bitmapDrawable = new BitmapDrawable(this.b.getResources(), this.b.t);
                        this.b.findViewById(R.id.out_frame_mask).setVisibility(0);
                        if (!z) {
                            this.b.findViewById(R.id.out_frame).setBackgroundDrawable(bitmapDrawable);
                        } else if (this.b.findViewById(R.id.out_frame).getBackground() == null) {
                            r1 = new TransitionDrawable(new Drawable[]{new BitmapDrawable(this.b.findViewById(R.id.out_frame).getDrawingCache()), bitmapDrawable});
                            this.b.findViewById(R.id.out_frame).setBackgroundDrawable(r1);
                            r1.startTransition(1200);
                        } else {
                            r1 = new TransitionDrawable(new Drawable[]{this.b.findViewById(R.id.out_frame).getBackground(), bitmapDrawable});
                            this.b.findViewById(R.id.out_frame).setBackgroundDrawable(r1);
                            r1.startTransition(1200);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, a.a().a((int) R.drawable.user_center_default_bg));
    }

    private void a(b bVar) {
        if (bVar != null && (bVar instanceof n)) {
            String str = ((n) bVar).c;
            if (!(TextUtils.isEmpty(str) || str.equals(this.q))) {
                a(str, true);
            }
            CharSequence charSequence = ((n) bVar).b;
            if (!TextUtils.isEmpty(charSequence) && this.a != null && TextUtils.isEmpty(this.a.getText())) {
                if (charSequence.length() > 7) {
                    charSequence = charSequence.substring(0, 7) + "...";
                }
                this.a.setText(charSequence);
            }
        }
    }

    public void e_() {
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ UserCenterNewActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j.a(1001);
                this.a.a(false, true);
                this.a.d.setRefreshing(false);
            }
        }, 1000);
    }

    public void a() {
        super.a();
        this.a = (TextView) findViewById(R.id.profile_header_title);
        this.f = findViewById(R.id.loading_failed_layout);
        this.r = (StateChangeTitler) findViewById(R.id.titler);
        this.g = (ListView) findViewById(R.id.user_center_list);
        this.c = this.r.findViewById(R.id.profile_header_left_back);
        this.d = (SwipeRefreshLayout) findViewById(R.id.detail_pull_down_list);
        this.d.setOnRefreshListener(new SwipeRefreshLayout.b(this) {
            final /* synthetic */ UserCenterNewActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.e_();
            }
        });
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterNewActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        TitlerControlModel titlerControlModel = new TitlerControlModel();
        titlerControlModel.mode = TitlerControlModel.POSITION_Y_MODE;
        titlerControlModel.startPosition = 0;
        titlerControlModel.startY = ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_page_header_icon_margin_top);
        this.r.setConTrollerModel(titlerControlModel);
        this.g.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ UserCenterNewActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                this.a.r.a(absListView, i, i2, i3);
            }
        });
        this.n = findViewById(R.id.out_frame);
        this.o = this.b.getString("userId");
        if (this.b.containsKey("userNickName")) {
            this.p = this.b.getString("userNickName");
        }
        if (this.b.containsKey("userIconUrl")) {
            this.q = this.b.getString("userIconUrl");
        }
        if (!TextUtils.isEmpty(this.p)) {
            if (this.p.length() > 7) {
                this.p = this.p.substring(0, 7) + "...";
            }
            ((TextView) findViewById(R.id.profile_header_title)).setText(this.p);
        }
        this.s = (ImageView) findViewById(R.id.fake_user_icon);
        if (!TextUtils.isEmpty(this.q)) {
            a(this.q, false);
        }
    }

    private void f() {
        try {
            this.j = e.a().a(this.b, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.j != null) {
            if (this.h == null) {
                this.h = new f(this);
            }
            this.h.a(this.j);
            this.g.setAdapter(this.h);
            a(true, false);
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_header_left_back:
                finish();
                return;
            default:
                return;
        }
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.t != null && !this.t.isRecycled()) {
            this.t.recycle();
            this.t = null;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.j != null) {
            this.j.a(i, i2, intent, this.mHandler);
        }
        if (i == 1 && this.j != null && (this.j instanceof n)) {
            if ((((n) this.j).d == 1) && i2 == -1) {
                a(false, true);
            }
        }
    }

    protected void onResume() {
        super.onResume();
        Map hashMap = new HashMap();
        if (this.j instanceof n) {
            hashMap.put("isOwn", String.valueOf(((n) this.j).d));
        }
        i.a("event_D160", hashMap, ReaderApplication.getApplicationImp());
    }
}
