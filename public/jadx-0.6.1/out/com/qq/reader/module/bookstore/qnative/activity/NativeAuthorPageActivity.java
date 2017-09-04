package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.request.b.g;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.FocusAuthorTask;
import com.qq.reader.common.readertask.protocol.UnFocusAuthorTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.model.TitlerControlModel;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.widget.titler.StateChangeTitler;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class NativeAuthorPageActivity extends NativeBookStoreConfigBaseActivity implements OnClickListener {
    private TextView a;
    private Bundle b;
    private ImageView c;
    private View d;
    private LinearLayout n;
    private String o;
    private String p;
    private String q;
    private boolean r;
    private StateChangeTitler s;
    private Bitmap t = null;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.local_author_page_layout);
        this.b = getIntent().getExtras();
        a();
        b();
        f();
        i.a("event_D191", null, ReaderApplication.getApplicationImp());
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
                        b(this.j);
                        p();
                        j();
                        c();
                        Map hashMap = new HashMap();
                        if (this.j != null && (this.j instanceof com.qq.reader.module.bookstore.qnative.page.impl.b)) {
                            if (((com.qq.reader.module.bookstore.qnative.page.impl.b) this.j).t) {
                                hashMap.put("isOwn", "1");
                            } else {
                                hashMap.put("isOwn", "0");
                            }
                        }
                        i.a("event_D140", hashMap, ReaderApplication.getApplicationImp());
                    }
                } catch (Exception e) {
                    c.e("DetailActivity", e.getMessage());
                }
                return true;
            case 500004:
                this.l = false;
                p();
                d();
                this.s.setBackgroundResource(R.drawable.titler_bg);
                this.c.setVisibility(8);
                return true;
            case 10001508:
                this.r = true;
                a(this.r);
                setResult(-1);
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "关注成功", 0).a();
                return true;
            case 10001509:
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "出错了，请稍后重试", 0).a();
                return true;
            case 10001510:
                this.r = false;
                a(this.r);
                setResult(-1);
                return true;
            case 10001511:
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "出错了，请稍后重试", 0).a();
                return true;
            case 10001512:
                this.r = true;
                a(this.r);
                return true;
            case 10001513:
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "网络异常，请稍后重试", 0).a();
                return true;
            case 10001514:
                a(this.q, false);
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void a(String str, final boolean z) {
        final ImageView imageView = (ImageView) findViewById(R.id.out_frame);
        com.qq.reader.common.imageloader.c.a((Context) this).a(str, new g(this) {
            final /* synthetic */ NativeAuthorPageActivity a;

            {
                this.a = r1;
            }

            public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
            }
        }, new e<String, com.bumptech.glide.load.resource.a.b>(this) {
            final /* synthetic */ NativeAuthorPageActivity c;

            public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                imageView.setBackgroundResource(R.drawable.author_page_header_bg_common);
                return true;
            }

            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                if (bVar instanceof com.bumptech.glide.load.resource.bitmap.j) {
                    com.bumptech.glide.load.resource.bitmap.j jVar2 = (com.bumptech.glide.load.resource.bitmap.j) bVar;
                    try {
                        if (this.c.t == null) {
                            this.c.t = ao.a(jVar2.b(), -1, false);
                        }
                        Drawable bitmapDrawable = new BitmapDrawable(this.c.getResources(), this.c.t);
                        this.c.findViewById(R.id.out_frame_mask).setVisibility(0);
                        if (!z) {
                            this.c.findViewById(R.id.out_frame).setBackgroundDrawable(bitmapDrawable);
                            ((ImageView) this.c.findViewById(R.id.img_author_avatar)).setImageBitmap(ao.b(jVar2.b()));
                        } else if (this.c.findViewById(R.id.out_frame).getBackground() == null) {
                            r1 = new TransitionDrawable(new Drawable[]{new BitmapDrawable(this.c.findViewById(R.id.out_frame).getDrawingCache()), bitmapDrawable});
                            this.c.findViewById(R.id.out_frame).setBackgroundDrawable(r1);
                            r1.startTransition(1200);
                        } else {
                            r1 = new TransitionDrawable(new Drawable[]{this.c.findViewById(R.id.out_frame).getBackground(), bitmapDrawable});
                            this.c.findViewById(R.id.out_frame).setBackgroundDrawable(r1);
                            r1.startTransition(1200);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
    }

    private void a(b bVar) {
        if (bVar != null && (bVar instanceof com.qq.reader.module.bookstore.qnative.page.impl.b)) {
            boolean z;
            String str = ((com.qq.reader.module.bookstore.qnative.page.impl.b) bVar).e;
            if (!(TextUtils.isEmpty(str) || str.equals(this.q))) {
                a(str, true);
            }
            CharSequence charSequence = ((com.qq.reader.module.bookstore.qnative.page.impl.b) bVar).b;
            if (!TextUtils.isEmpty(charSequence)) {
                ((TextView) findViewById(R.id.profile_header_title)).setText(charSequence);
            }
            this.r = ((com.qq.reader.module.bookstore.qnative.page.impl.b) bVar).d;
            a(this.r);
            ImageView imageView = (ImageView) findViewById(R.id.img_focus_shadow);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_focus);
            a c = com.qq.reader.common.login.c.c();
            if (c != null) {
                if (this.o.equals(String.valueOf(c.l(ReaderApplication.getApplicationImp())))) {
                    z = true;
                    if (((com.qq.reader.module.bookstore.qnative.page.impl.b) bVar).s || ((com.qq.reader.module.bookstore.qnative.page.impl.b) bVar).t || r3) {
                        linearLayout.setVisibility(8);
                        imageView.setVisibility(8);
                    }
                    linearLayout.setVisibility(0);
                    imageView.setVisibility(0);
                    return;
                }
            }
            z = false;
            if (((com.qq.reader.module.bookstore.qnative.page.impl.b) bVar).s) {
            }
            linearLayout.setVisibility(8);
            imageView.setVisibility(8);
        }
    }

    protected void a(boolean z, boolean z2) {
        boolean a = d.b().a(getApplicationContext(), this.j, this.mHandler, z);
        if (!z2) {
            if (a) {
                j();
                c();
                return;
            }
            b();
        }
    }

    private void b(b bVar) {
        if (bVar != null && (bVar instanceof com.qq.reader.module.bookstore.qnative.page.impl.b)) {
            final com.qq.reader.module.bookstore.qnative.page.impl.b bVar2 = (com.qq.reader.module.bookstore.qnative.page.impl.b) bVar;
            this.c.setImageResource(R.drawable.titlebar_icon_share_selector);
            this.c.setVisibility(0);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeAuthorPageActivity b;

                public void onClick(View view) {
                    i.a("event_D148", null, ReaderApplication.getApplicationImp());
                    new aj(this.b, com.qq.reader.appconfig.e.cD + "platform=1&tf=1&authorId=" + bVar2.a, bVar2.e, bVar2.b, bVar2.r, null, 11).f_();
                }
            });
        }
    }

    public void a() {
        this.i = (SwipeRefreshLayout) findViewById(R.id.detail_pull_down_list);
        super.a();
        this.a = (TextView) findViewById(R.id.profile_header_title);
        this.f = findViewById(R.id.loading_failed_layout);
        this.s = (StateChangeTitler) findViewById(R.id.titler);
        this.g = (ListView) findViewById(R.id.detail_list);
        this.c = (ImageView) this.s.findViewById(R.id.profile_header_right_image);
        this.d = this.s.findViewById(R.id.profile_header_left_back);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAuthorPageActivity a;

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
        this.s.setConTrollerModel(titlerControlModel);
        this.g.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NativeAuthorPageActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                this.a.s.a(absListView, i, i2, i3);
            }
        });
        this.n = (LinearLayout) findViewById(R.id.ll_focus);
        this.n.setOnClickListener(this);
        this.o = this.b.getString("AUTHORPAGE_KEY_AUTHORID");
        this.p = this.b.getString("AUTHORPAGE_KEY_AUTHOR_NAME");
        this.q = this.b.getString("AUTHORPAGE_KEY_AVATAR_URL");
        if (!TextUtils.isEmpty(this.p)) {
            this.a.setText(this.p);
        }
        if (!TextUtils.isEmpty(this.q)) {
            this.mHandler.sendEmptyMessage(10001514);
        }
    }

    private void f() {
        try {
            this.j = com.qq.reader.module.bookstore.qnative.e.a().a(this.b, this);
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

    public void onDestroy() {
        super.onDestroy();
        if (this.t != null && !this.t.isRecycled()) {
            this.t.recycle();
            this.t = null;
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_focus:
                if (com.qq.reader.common.login.c.b()) {
                    g();
                    return;
                }
                setLoginNextTask(new com.qq.reader.common.login.a(this) {
                    final /* synthetic */ NativeAuthorPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        switch (i) {
                            case 1:
                                this.a.g();
                                return;
                            default:
                                return;
                        }
                    }
                });
                startLogin();
                return;
            default:
                return;
        }
    }

    private void a(boolean z) {
        if ((this.j instanceof com.qq.reader.module.bookstore.qnative.page.impl.b) && ((com.qq.reader.module.bookstore.qnative.page.impl.b) this.j).t) {
            this.n.setVisibility(8);
        }
        i.a("event_D141", null, ReaderApplication.getApplicationImp());
        ImageView imageView = (ImageView) findViewById(R.id.img_focus);
        TextView textView = (TextView) findViewById(R.id.tv_focus);
        if (z) {
            textView.setText("已关注，新书上架第一时间通知你");
            textView.setTextColor(getResources().getColor(R.color.text_color_c103));
            imageView.setImageResource(R.drawable.already_subscribe);
            return;
        }
        textView.setText(getResources().getText(R.string.subscribe_author));
        textView.setTextColor(getResources().getColor(R.color.text_color_c301));
        imageView.setImageResource(R.drawable.subscribe_author);
    }

    private void g() {
        if (this.r) {
            new AlertDialog.a(this).a(getString(R.string.dialog_shortcut_title)).b((CharSequence) "取消关注后，将不再收到作者动态和新书上架消息通知").a((CharSequence) "取消关注", new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeAuthorPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.a.q();
                    i.a("event_D144", null, ReaderApplication.getApplicationImp());
                }
            }).b((CharSequence) "继续关注", new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeAuthorPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).a().show();
            i.a("event_D143", null, ReaderApplication.getApplicationImp());
            return;
        }
        i.a("event_D142", null, ReaderApplication.getApplicationImp());
        i();
    }

    private void i() {
        com.qq.reader.common.readertask.g.a().a(new FocusAuthorTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeAuthorPageActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    int optInt = new JSONObject(str).optInt("code");
                    if (optInt == 0) {
                        this.a.mHandler.sendEmptyMessage(10001508);
                    } else if (optInt == -2) {
                        this.a.mHandler.sendEmptyMessage(10001512);
                    } else {
                        this.a.mHandler.sendEmptyMessage(10001509);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(10001513);
            }
        }, this.o));
    }

    private void q() {
        com.qq.reader.common.readertask.g.a().a(new UnFocusAuthorTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeAuthorPageActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    if (new JSONObject(str).optInt("code") == 0) {
                        this.a.mHandler.sendEmptyMessage(10001510);
                    } else {
                        this.a.mHandler.sendEmptyMessage(10001511);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(10001513);
            }
        }, this.o));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.j != null) {
            this.j.a(i, i, intent, this.mHandler);
        }
    }
}
