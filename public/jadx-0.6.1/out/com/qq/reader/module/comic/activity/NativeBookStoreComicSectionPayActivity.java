package com.qq.reader.module.comic.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.charge.voucher.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.module.comic.e.e;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.module.comic.task.SectionPayTask;
import com.qq.reader.module.comic.task.SectionPayTask.a;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.af;
import com.qrcomic.entity.k;
import com.qrcomic.entity.o;
import com.qrcomic.util.h;
import com.tencent.feedback.proguard.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NativeBookStoreComicSectionPayActivity extends ReaderBaseActivity implements OnClickListener {
    private static final String a = NativeBookStoreComicSectionPayActivity.class.getName();
    private a A = new a(this) {
        final /* synthetic */ NativeBookStoreComicSectionPayActivity a;

        {
            this.a = r1;
        }

        public void a(o<k> oVar) {
            if (this.a.l != null) {
                this.a.l.obtainMessage(5, oVar).sendToTarget();
            }
        }
    };
    private EmptyView B;
    private Bundle b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private ImageView h;
    private TextView i;
    private TextView j;
    private View k;
    private com.qrcomic.util.k l;
    private ComicShelfInfo m;
    private final int n = 1;
    private final int o = 2;
    private final int p = 3;
    private final int q = 4;
    private final int r = 5;
    private final com.qq.reader.common.charge.voucher.a.a s = new com.qq.reader.common.charge.voucher.a.a();
    private a t;
    private Callback u;
    private String v;
    private String w;
    private AlertDialog x;
    private int y;
    private boolean z = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.qq.reader.module.comic.a.a().a(this);
        setContentView(R.layout.comic_section_pay);
        disableUseAnimation();
        this.b = getIntent().getExtras();
        this.v = this.b.getString("key_comic_id");
        this.w = this.b.getString("key_section_id");
        this.y = this.b.getInt("key_show_pay_page_orientation");
        this.m = (ComicShelfInfo) getIntent().getParcelableExtra("key_pay_comic_shelf_info");
        b();
        a((Context) this);
        a(this.b);
        d(this.b);
    }

    private void a(Bundle bundle) {
        CharSequence string = bundle.getString("key_comic_title");
        if (string != null) {
            this.c.setText(string);
        }
        if (bundle.getInt("key_buy_type") == 2) {
            this.g.setText(bundle.getString("key_comic_title", ""));
        } else {
            this.g.setText(bundle.getString("key_section_title", ""));
        }
    }

    private void b() {
        this.u = new Callback(this) {
            final /* synthetic */ NativeBookStoreComicSectionPayActivity a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 3) {
                    this.a.k.setVisibility(8);
                    if (message.obj instanceof com.qq.reader.common.charge.voucher.a.a) {
                        this.a.s.a((com.qq.reader.common.charge.voucher.a.a) message.obj);
                        this.a.e.setText("余额：" + this.a.s.b());
                        if (this.a.s.d > 0) {
                            b.a(this.a, this.a.e, new OnClickListener(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(View view) {
                                }
                            }, new OnDismissListener(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void onDismiss(DialogInterface dialogInterface) {
                                }
                            }, this.a.s.g);
                        }
                        this.a.d();
                        c.d(NativeBookStoreComicSectionPayActivity.a, "用户余额加载完成");
                    } else {
                        af.a(this.a, "查询用户信息失败", 0).a();
                    }
                } else if (i == 4) {
                    this.a.k.setVisibility(8);
                    af.a(this.a, "查询用户信息失败", 0).a();
                    c.d(NativeBookStoreComicSectionPayActivity.a, "用户余额加载失败");
                    this.a.B.setVisibility(0);
                } else if (i == 1) {
                    if (message.obj instanceof a) {
                        this.a.t = (a) message.obj;
                        if (this.a.t.g) {
                            this.a.a(4);
                            c.d(NativeBookStoreComicSectionPayActivity.a, "漫画的可见状态和引擎不一样。。。。。");
                        } else {
                            this.a.d.setText(this.a.c());
                            if (this.a.b.getInt("key_buy_type") != this.a.t.e) {
                                this.a.a(3);
                            } else {
                                this.a.b.putInt("key_buy_type", this.a.t.e);
                                this.a.e(this.a.b);
                                this.a.d();
                            }
                        }
                    } else {
                        af.a(this.a, "查询话别信息失败", 0).a();
                    }
                } else if (i == 2) {
                    af.a(this.a, "查询话别信息失败", 0).a();
                    this.a.B.setVisibility(0);
                } else if (i == 5) {
                    this.a.j.setEnabled(true);
                    if (this.a.j.getTag() instanceof String) {
                        this.a.j.setText((String) this.a.j.getTag());
                    }
                    if (message.obj instanceof o) {
                        o oVar = (o) message.obj;
                        if (oVar.c == 0) {
                            if (oVar.e instanceof k) {
                                k kVar = (k) oVar.e;
                                if (this.a.t.e == 1) {
                                    this.a.b.putInt("key_buy_type", 1);
                                    this.a.b.putSerializable("sectionIdList", (Serializable) kVar.d);
                                }
                                if (this.a.f.isSelected()) {
                                    this.a.b.putInt("auto_buy_new", 0);
                                } else {
                                    this.a.b.putInt("auto_buy_new", 1);
                                }
                                this.a.b.putString("key_pay_error_msg", "");
                                this.a.b.putInt("section_cost", kVar.b);
                                this.a.a(2);
                                if (this.a.m != null) {
                                    e.a(this.a, this.a.m, false);
                                }
                            } else {
                                this.a.b.putString("key_pay_error_msg", oVar.d);
                                af.a(this.a, "购买失败，请重试", 0).a();
                            }
                        } else if (oVar.c == 1005) {
                            if (this.a.x == null || !this.a.x.isShowing()) {
                                r0 = oVar.d;
                                if (TextUtils.isEmpty(r0)) {
                                    r0 = this.a.getResources().getString(R.string.buy_section_error_area);
                                }
                                this.a.x = new AlertDialog.a(this.a).a("提示").b(r0).a(false).a("我知道啦", new DialogInterface.OnClickListener(this) {
                                    final /* synthetic */ AnonymousClass2 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (!this.a.a.isFinishing() && this.a.a.x != null && this.a.a.x.isShowing()) {
                                            this.a.a.x.dismiss();
                                            this.a.a.x = null;
                                        }
                                    }
                                }).a();
                            }
                            this.a.x.show();
                        } else if (oVar.c == 1006) {
                            if (this.a.x == null || !this.a.x.isShowing()) {
                                r0 = oVar.d;
                                if (TextUtils.isEmpty(r0)) {
                                    r0 = this.a.getResources().getString(R.string.pay_fail_by_permission);
                                }
                                if (TextUtils.isEmpty(r0)) {
                                    r0 = this.a.getResources().getString(R.string.pay_fail_by_permission);
                                }
                                this.a.x = new AlertDialog.a(this.a).a("提示").b(r0).a(false).a("我知道啦", new DialogInterface.OnClickListener(this) {
                                    final /* synthetic */ AnonymousClass2 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (!this.a.a.isFinishing() && this.a.a.x != null && this.a.a.x.isShowing()) {
                                            this.a.a.x.dismiss();
                                            this.a.a.x = null;
                                        }
                                    }
                                }).a();
                            }
                            this.a.x.show();
                        } else {
                            af.a(this.a, "购买失败，请重试", 0).a();
                        }
                    } else {
                        this.a.b.putString("key_pay_error_msg", "请稍后重试");
                        af.a(this.a, "购买失败，请重试", 0).a();
                    }
                }
                return true;
            }
        };
        this.l = new com.qrcomic.util.k(this.u);
    }

    private void a(int i) {
        b(i);
    }

    private Spannable c() {
        Spannable spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append("价格：");
        if (this.t != null) {
            if (this.t.d == this.t.c) {
                spannableStringBuilder.append(String.valueOf(this.t.c));
                spannableStringBuilder.append("书币");
            } else {
                Object obj = String.valueOf(this.t.c) + "书币";
                Object obj2 = " " + String.valueOf(this.t.a()) + "书币";
                Object obj3 = "（" + this.t.f + "）";
                spannableStringBuilder.append(obj);
                spannableStringBuilder.append(obj2);
                spannableStringBuilder.append(obj3);
                int length = obj.length() + 3;
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_color_c103)), 3, length, 33);
                spannableStringBuilder.setSpan(new StrikethroughSpan(), 3, length, 33);
                if (!TextUtils.isEmpty(this.t.f)) {
                    length += obj2.length();
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_color_c701)), length, obj3.length() + length, 33);
                }
            }
        }
        return spannableStringBuilder;
    }

    private void d() {
        if (this.s.a && this.t != null) {
            if (this.s.a() >= this.t.a()) {
                this.j.setBackgroundResource(R.drawable.selector_round_blue_button);
                if (this.b.getInt("key_buy_type") == 2) {
                    this.j.setText(getResources().getString(R.string.comic_buy_all_book));
                } else {
                    this.j.setText(getResources().getString(R.string.comic_buy));
                }
                i.a("event_F257", null, ReaderApplication.getApplicationContext());
                return;
            }
            this.j.setBackgroundResource(R.drawable.selector_round_orange_button);
            this.j.setText(getString(R.string.comic_charge));
            i.a("event_F259", null, ReaderApplication.getApplicationContext());
        }
    }

    private void b(Bundle bundle) {
        this.k.setVisibility(0);
        c.d(a, "开始获取用户余额");
        g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeBookStoreComicSectionPayActivity a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                c.d(NativeBookStoreComicSectionPayActivity.a, " 用户余额 成功 " + aVar);
                if (this.a.l != null) {
                    this.a.l.obtainMessage(3, aVar).sendToTarget();
                }
            }

            public void a() {
                c.d(NativeBookStoreComicSectionPayActivity.a, " 用户余额 失败");
                this.a.s.c();
                if (this.a.l != null) {
                    this.a.l.sendEmptyMessage(4);
                }
            }
        }, this.v, 1));
    }

    private void c(final Bundle bundle) {
        c.d(a, "开始获取书籍信息");
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreComicSectionPayActivity b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                c.d(NativeBookStoreComicSectionPayActivity.a, " 书籍信息 成功 " + str);
                o oVar = (o) new Gson().fromJson(str, new TypeToken<o<a>>(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }
                }.getType());
                if (com.qrcomic.util.g.a) {
                    ((a) oVar.e).e = bundle.getInt("key_buy_type");
                }
                if (oVar.c == 0) {
                    if (this.b.l != null) {
                        this.b.l.obtainMessage(1, oVar.e).sendToTarget();
                    }
                } else if (this.b.l != null) {
                    this.b.l.obtainMessage(2).sendToTarget();
                    c.d(NativeBookStoreComicSectionPayActivity.a, " 书籍信息 失败 message is " + oVar.d);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                c.d(NativeBookStoreComicSectionPayActivity.a, " 书籍信息 失败" + exception);
                if (this.b.l != null) {
                    this.b.l.sendEmptyMessage(2);
                }
            }
        });
        String str = com.qrcomic.a.i.a(this.v, this.w) + "&dt=1";
        c.d(a, " 访问链接：" + str);
        readerProtocolJSONTask.setUrl(str);
        g.a().a(readerProtocolJSONTask);
    }

    private void d(Bundle bundle) {
        bundle.putInt("auto_buy_new", bundle.getInt("comicExtraCode"));
        this.B.setVisibility(8);
        com.qq.reader.common.imageloader.c.a(this).a(com.qrcomic.a.i.a(this.v, this.w, com.qq.reader.common.c.a.bU, com.qq.reader.common.c.a.bT), this.h, com.qq.reader.common.imageloader.a.a().j());
        if (com.qq.reader.common.login.c.b()) {
            b(bundle);
            c(bundle);
        }
    }

    private void a(Context context) {
        this.c = (TextView) findViewById(R.id.profile_header_title);
        this.g = (TextView) findViewById(R.id.title);
        findViewById(R.id.profile_header_left_back).setOnClickListener(this);
        this.h = (ImageView) findViewById(R.id.section_cover_image);
        this.k = findViewById(R.id.default_progress);
        this.B = (EmptyView) findViewById(R.id.empty_view);
        this.B.b((OnClickListener) this);
        this.d = (TextView) findViewById(R.id.price);
        this.e = (TextView) findViewById(R.id.balance);
        this.f = (TextView) findViewById(R.id.autopay);
        this.f.setOnClickListener(this);
        this.j = (TextView) findViewById(R.id.btn_buy_logined);
        this.j.setOnClickListener(this);
        this.i = (TextView) findViewById(R.id.btn_buy_unlogin);
        this.i.setOnClickListener(this);
        e(this.b);
        e();
    }

    private void e() {
        if (com.qq.reader.common.login.c.b()) {
            this.i.setVisibility(8);
            this.d.setVisibility(0);
            this.e.setVisibility(0);
            this.j.setVisibility(0);
            this.k.setVisibility(0);
            return;
        }
        this.i.setVisibility(0);
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        this.j.setVisibility(8);
        this.k.setVisibility(8);
    }

    private void e(Bundle bundle) {
        if (bundle.getInt("key_buy_type") == 2) {
            this.f.setVisibility(4);
            this.f.setClickable(false);
            this.f.setFocusableInTouchMode(false);
        } else if (com.qq.reader.common.login.c.b()) {
            this.f.setClickable(true);
            this.f.setFocusableInTouchMode(true);
            this.f.setVisibility(0);
        }
        if (h.a.a(this.v, com.qrcomic.manager.b.a().b().a())) {
            this.f.setSelected(true);
        } else {
            this.f.setSelected(false);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        boolean z = true;
        int id = view.getId();
        if (id == R.id.btn_buy_logined) {
            String charSequence = this.j.getText().toString();
            if (getString(R.string.comic_buy).equals(charSequence)) {
                f();
            } else if (getString(R.string.comic_buy_all_book).equals(charSequence)) {
                f();
            } else if (getString(R.string.comic_charge).equals(charSequence)) {
                i.a("event_F260", null, ReaderApplication.getApplicationContext());
                setChargeNextTask(new com.qq.reader.common.charge.a(this) {
                    final /* synthetic */ NativeBookStoreComicSectionPayActivity a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.f();
                    }

                    public void b() {
                        af.a(this.a, "充值失败", 0).a();
                    }

                    public void c() {
                        af.a(this.a, "充值取消", 0).a();
                    }
                });
                com.qq.reader.common.utils.o.a(this, this.t.c);
            }
        } else if (id == R.id.btn_buy_unlogin) {
            this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeBookStoreComicSectionPayActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    this.a.e();
                    if (i == 1) {
                        this.a.b(this.a.b);
                        this.a.c(this.a.b);
                    }
                }
            };
            startLogin();
        } else if (id == R.id.autopay) {
            boolean z2;
            String str = this.v;
            String a = com.qrcomic.manager.b.a().b().a();
            if (this.f.isSelected()) {
                z2 = false;
            } else {
                z2 = true;
            }
            h.a.a(str, a, z2);
            TextView textView = this.f;
            if (this.f.isSelected()) {
                z = false;
            }
            textView.setSelected(z);
        } else if (id == R.id.profile_header_left_back) {
            a(0);
        } else if (id == R.id.empty_page_reload) {
            d(this.b);
        }
    }

    private void f() {
        if (this.t != null) {
            i.a("event_F258", null, ReaderApplication.getApplicationContext());
            this.j.setEnabled(false);
            this.j.setTag(this.j.getText().toString());
            this.j.setText("正在购买…");
            List arrayList = new ArrayList();
            arrayList.add(this.t.b);
            g.a().a(new SectionPayTask(this.t.a, arrayList, this.t.e, this.t.a(), this.A));
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        a(0);
        return true;
    }

    public void finish() {
        b(0);
    }

    private void b(int i) {
        Intent intent = new Intent();
        this.b.putInt("key_pay_ressult", i);
        List arrayList = new ArrayList();
        arrayList.add(this.w);
        this.b.putSerializable("sectionIdList", (Serializable) arrayList);
        intent.putExtras(this.b);
        setResult(i, intent);
        super.finish();
        overridePendingTransition(0, g());
    }

    private int g() {
        if (this.y == 1) {
            return R.anim.slide_out_right;
        }
        return R.anim.slide_out_bottom;
    }

    protected void onStart() {
        super.onStart();
        this.l.postDelayed(new Runnable(this) {
            final /* synthetic */ NativeBookStoreComicSectionPayActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.z = true;
                this.a.setSwipeBackEnable(true);
            }
        }, 200);
    }

    protected boolean isLayoutFillWindow() {
        return this.z;
    }
}
