package com.qq.reader.module.bookstore.qnative.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.content.d;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.request.b.g;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.AudioBookDownloadActivity;
import com.qq.reader.activity.CommitCommentActivity;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.activity.WebBookRewardActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.download.task.m;
import com.qq.reader.common.download.task.n;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.e;
import com.qq.reader.common.utils.e.a;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSBookDir;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.cservice.buy.a.b;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.download.book.f;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.readpage.AddLimitFreeBook2DBTask;
import com.qq.reader.module.readpage.q;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.AudioFloatingWindowView;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.view.web.h;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.tesla.soload.SoLoadCore;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeAudioBookDetailActivity extends NativeBookStoreConfigBaseActivity implements a, b, f, com.qq.reader.module.bookstore.qnative.c.a {
    private Bundle A;
    private int B = -1;
    private int C = 3;
    private boolean D = false;
    private View E;
    private SwipeRefreshLayout F;
    private ProgressBar G;
    private ProgressBar H;
    private ViewGroup I;
    private LinearLayout J;
    private LinearLayout K;
    private ImageView L;
    private ImageView M;
    private ImageView N;
    private final com.qq.reader.common.charge.voucher.a.a O = new com.qq.reader.common.charge.voucher.a.a();
    private int P = 0;
    private int Q = 0;
    private String R = "";
    private boolean S = false;
    private boolean T = false;
    private int U = 0;
    private boolean V = false;
    private boolean W = false;
    private Bitmap X;
    private BroadcastReceiver Y = new BroadcastReceiver(this) {
        final /* synthetic */ NativeAudioBookDetailActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if ("BROADCAST_OFF_MARKET".equals(intent.getAction())) {
                this.a.u();
                return;
            }
            String stringExtra = intent.getStringExtra("message");
            if (intent.getLongExtra("cpid", 0) == 2000000804) {
                c.a(this.a.o).a(stringExtra, new g(this) {
                    final /* synthetic */ AnonymousClass28 a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                        if (obj instanceof j) {
                            j jVar = (j) obj;
                            try {
                                Drawable transitionDrawable;
                                if (this.a.a.findViewById(R.id.out_frame).getBackground() == null) {
                                    transitionDrawable = new TransitionDrawable(new Drawable[]{jVar, jVar});
                                    this.a.a.findViewById(R.id.out_frame).setBackgroundDrawable(transitionDrawable);
                                    transitionDrawable.startTransition(1200);
                                } else {
                                    transitionDrawable = new TransitionDrawable(new Drawable[]{this.a.a.findViewById(R.id.out_frame).getBackground(), jVar});
                                    this.a.a.findViewById(R.id.out_frame).setBackgroundDrawable(transitionDrawable);
                                    transitionDrawable.startTransition(1200);
                                }
                                this.a.a.q.setImageResource(R.drawable.titlebar_icon_back_selector_black);
                                this.a.a.r.setImageResource(R.drawable.titlebar_icon_share_selector_black);
                                com.qq.reader.common.monitor.debug.c.e("Detail", "black arrow2 " + this.a.a.toString());
                                ((ImageView) this.a.a.findViewById(R.id.out_frame)).setImageResource(R.drawable.audio_detail_new_top_bg);
                                ((ImageView) this.a.a.findViewById(R.id.out_frame)).setLayoutParams(new LayoutParams(-1, -2));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
                c.a(this.a.o).a(stringExtra, new g(this) {
                    final /* synthetic */ AnonymousClass28 a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                        if (obj instanceof j) {
                            j jVar = (j) obj;
                            try {
                                if (this.a.a.X == null) {
                                    this.a.a.X = ao.a(jVar.b(), -1);
                                }
                                BitmapDrawable bitmapDrawable = new BitmapDrawable(this.a.a.getResources(), this.a.a.X);
                                Drawable transitionDrawable;
                                if (this.a.a.findViewById(R.id.out_frame).getBackground() == null) {
                                    transitionDrawable = new TransitionDrawable(new Drawable[]{new BitmapDrawable(this.a.a.findViewById(R.id.out_frame).getDrawingCache()), bitmapDrawable});
                                    this.a.a.findViewById(R.id.out_frame).setBackgroundDrawable(transitionDrawable);
                                    transitionDrawable.startTransition(1200);
                                } else {
                                    transitionDrawable = new TransitionDrawable(new Drawable[]{this.a.a.findViewById(R.id.out_frame).getBackground(), bitmapDrawable});
                                    this.a.a.findViewById(R.id.out_frame).setBackgroundDrawable(transitionDrawable);
                                    transitionDrawable.startTransition(1200);
                                }
                                this.a.a.q.setImageResource(R.drawable.titlebar_icon_back_selector_black);
                                this.a.a.r.setImageResource(R.drawable.titlebar_icon_share_selector_black);
                                com.qq.reader.common.monitor.debug.c.e("Detail", "black arrow2 " + this.a.a.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }
    };
    private m Z = new m(this) {
        final /* synthetic */ NativeAudioBookDetailActivity a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            DownloadBookTask downloadBookTask;
            TaskStateEnum c = nVar.c();
            TaskStateEnum a = nVar.a();
            if (c == TaskStateEnum.Started || c == TaskStateEnum.DeactiveStarted) {
                downloadBookTask = (DownloadBookTask) nVar.d();
                if (downloadBookTask == null) {
                    return;
                }
                if (this.a.u == null || downloadBookTask.getId() == this.a.u.g()) {
                    Message obtain = Message.obtain();
                    obtain.what = 8001;
                    boolean equals = "trial".equals(downloadBookTask.getBookFormat());
                    if (!equals) {
                        int currentSize;
                        if (downloadBookTask.getSize() > 0) {
                            currentSize = (int) ((((float) downloadBookTask.getCurrentSize()) * 100.0f) / ((float) downloadBookTask.getSize()));
                        } else {
                            currentSize = 0;
                        }
                        obtain.arg1 = equals ? 0 : 1;
                        obtain.obj = Integer.valueOf(currentSize);
                        this.a.mHandler.sendMessage(obtain);
                    } else {
                        return;
                    }
                }
                return;
            }
            if (c == TaskStateEnum.Installing && a != TaskStateEnum.Installing) {
                downloadBookTask = (DownloadBookTask) nVar.d();
                Message obtain2 = Message.obtain();
                obtain2.what = 8003;
                obtain2.obj = Long.valueOf(downloadBookTask.getId());
                this.a.mHandler.sendMessageDelayed(obtain2, 500);
            }
        }
    };
    int a = 0;
    private h aa;
    boolean b = false;
    boolean c = false;
    private final String d = "NativeAudioBookDetailActivity";
    private final long n = 500;
    private Context o;
    private TextView p;
    private ImageView q;
    private ImageView r;
    private View s = null;
    private TextView t = null;
    private com.qq.reader.module.audio.a.b u = null;
    private TextView v = null;
    private TextView w = null;
    private TextView x = null;
    private ProgressDialog y;
    private com.qq.reader.view.c z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.o = this;
        setContentView(R.layout.localbookstore_audio_detail_layout);
        if (VERSION.SDK_INT > 10 && !"Meizu_M040".equals(com.qq.reader.common.c.a.E)) {
            getWindow().addFlags(SigType.WLOGIN_PF);
        }
        this.u = new com.qq.reader.module.audio.a.b(this, this.mHandler);
        this.A = getIntent().getExtras();
        if (this.A != null) {
            if (this.A.containsKey("PARA_TYPE_IS_BOOK_FINISH")) {
                this.R = this.A.getString("PARA_TYPE_IS_BOOK_FINISH", "");
            }
            if (this.A.containsKey("PARA_TYPE_IS_FROM_END_PAGE_WRITER")) {
                this.S = this.A.getBoolean("PARA_TYPE_IS_FROM_END_PAGE_WRITER", false);
            }
            if (this.A.containsKey("PARA_TYPE_IS_FROM_END_PAGE_READER")) {
                this.T = this.A.getBoolean("PARA_TYPE_IS_FROM_END_PAGE_READER", false);
            }
        }
        StatisticsManager.a().b("DetailPage").a(this.A).c();
        a();
        b();
        q();
        this.D = false;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("BROADCAST_OFF_MARKET");
        intentFilter.addAction("detail.loadimg");
        d.a((Context) this).a(this.Y, intentFilter);
        e.a().a(this);
        onAudioFloatingStateChange(2, e.a().c(), e.a().b(), e.a().d());
        i.a("event_B250", null, ReaderApplication.getApplicationImp());
        com.qq.reader.activity.a.a.a().a(this);
    }

    protected void onResume() {
        super.onResume();
        t();
    }

    protected void onPause() {
        super.onPause();
        d.a((Context) this).a(this.Y);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.X == null || this.X.isRecycled())) {
            this.X.recycle();
            this.X = null;
        }
        this.R = "";
        this.S = false;
        this.T = false;
        e.a().b(this);
        com.qq.reader.activity.a.a.a().b(this);
    }

    protected void onStop() {
        super.onStop();
    }

    @SuppressLint({"NewApi"})
    public void a() {
        super.a();
        this.I = (ViewGroup) findViewById(R.id.ll_download);
        this.J = (LinearLayout) findViewById(R.id.ll_read);
        this.K = (LinearLayout) findViewById(R.id.ll_add2shelf);
        this.L = (ImageView) findViewById(R.id.img_download_icon);
        this.M = (ImageView) findViewById(R.id.img_read_icon);
        this.N = (ImageView) findViewById(R.id.img_add_shelf_icon);
        this.M.setImageResource(R.drawable.audio_detail_bottom_read_icon);
        this.H = (ProgressBar) findViewById(R.id.progress_read_download_percent);
        this.G = (ProgressBar) findViewById(R.id.progress_download_percent);
        this.F = (SwipeRefreshLayout) findViewById(R.id.detail_pull_down_list);
        this.F.setOnRefreshListener(new SwipeRefreshLayout.b(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.e_();
            }
        });
        this.E = findViewById(R.id.common_titler);
        this.p = (TextView) this.E.findViewById(R.id.profile_header_title);
        this.p.setText(r());
        this.q = (ImageView) findViewById(R.id.profile_header_left_back);
        this.r = (ImageView) findViewById(R.id.profile_header_right_image);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.setResult(0);
                this.a.finish();
            }
        });
        this.r.setVisibility(0);
        this.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String str = "";
                aj ajVar = new aj(this.a, String.valueOf(this.a.u.g()), this.a.u.n());
                ajVar.a(true);
                ajVar.f_();
                i.a("event_B251", null, this.a.o);
            }
        });
        s();
        this.g = (ListView) findViewById(R.id.detail_list);
        if (VERSION.SDK_INT > 8) {
            this.g.setOverScrollMode(2);
        }
        this.g.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (!this.a.V && VERSION.SDK_INT > 10 && i <= 0) {
                    View childAt = absListView.getChildAt(0);
                    if (childAt != null && childAt.findViewById(R.id.colcard3_bookinfo) != null) {
                        int color;
                        int color2;
                        double min = Math.min(((double) Math.abs(childAt.getTop())) / ((double) ao.a(100.0f)), 1.0d);
                        if (min == 1.0d && this.a.p.getAlpha() == 0.0f) {
                            ao.a(this.a.p, 1.0f);
                            color = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_startcolor);
                            color2 = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_endcolor);
                            ObjectAnimator ofObject = ObjectAnimator.ofObject(this.a.E, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(color), Integer.valueOf(color2)});
                            ofObject.setDuration(200);
                            ofObject.start();
                            this.a.q.setImageResource(R.drawable.titlebar_icon_back_selector);
                            this.a.r.setImageResource(R.drawable.titlebar_icon_share_selector);
                        }
                        if (min < 1.0d && this.a.p.getAlpha() > 0.0f) {
                            ao.a(this.a.p, 0.0f);
                            color = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_startcolor);
                            color2 = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_endcolor);
                            if (min > 0.10000000149011612d) {
                                ObjectAnimator ofObject2 = ObjectAnimator.ofObject(this.a.E, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(color2), Integer.valueOf(color)});
                                ofObject2.setDuration(200);
                                ofObject2.start();
                            } else {
                                this.a.E.setBackgroundColor(color);
                            }
                            this.a.q.setImageResource(R.drawable.titlebar_icon_back_selector_black);
                            this.a.r.setImageResource(R.drawable.titlebar_icon_share_selector_black);
                            com.qq.reader.common.monitor.debug.c.e("Detail", "black arrow1 " + this.a.toString());
                        }
                    }
                }
            }
        });
        this.e = findViewById(R.id.loading_layout);
        this.i = (SwipeRefreshLayout) findViewById(R.id.detail_pull_down_list);
        LayoutParams layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.detail_listview_margin_bottom);
        this.i.setLayoutParams(layoutParams);
    }

    private void q() {
        try {
            this.j = com.qq.reader.module.bookstore.qnative.e.a().a(this.A, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.j != null) {
            if (this.h == null) {
                this.h = new com.qq.reader.module.bookstore.qnative.a.f(this.o);
            }
            this.h.a(this.j);
            this.g.setAdapter(this.h);
            a(true, false);
            this.s.setVisibility(4);
        }
    }

    protected boolean handleMessageImp(Message message) {
        com.qq.reader.cservice.buy.a.c cVar;
        switch (message.what) {
            case 1203:
                if (B()) {
                    com.qq.reader.cservice.download.book.g gVar = (com.qq.reader.cservice.download.book.g) message.obj;
                    if (this.u != null) {
                        if (gVar.a() == 5) {
                            com.qq.reader.module.bookchapter.online.i iVar = new com.qq.reader.module.bookchapter.online.i(this.u.g() + "");
                            iVar.b(gVar.a());
                            iVar.a(gVar.b());
                            new AddLimitFreeBook2DBTask(iVar).execute();
                        }
                        this.u.a(gVar.c());
                        this.u.a(true);
                        if (this.u.g() == Long.parseLong(gVar.e())) {
                            z();
                            break;
                        }
                    }
                }
                break;
            case 1204:
            case 21001:
                if (B()) {
                    af.a(getApplicationContext(), getString(R.string.download_faile), 0).a();
                    break;
                }
                break;
            case 1205:
                if (B()) {
                    showFragmentDialog(606);
                    break;
                }
                break;
            case 1217:
                a(String.valueOf(this.u.g()), this.u.f(), String.valueOf(message.obj));
                break;
            case 1218:
                i.a("event_B144", null, ReaderApplication.getApplicationImp());
                if (E()) {
                    cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                    try {
                        if (this.u != null) {
                            if (this.u.g() == Long.parseLong(cVar.c())) {
                                af.a(ReaderApplication.getApplicationContext(), (CharSequence) "购买成功", 0).a();
                            }
                            A();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.mHandler.sendEmptyMessage(500007);
                    break;
                }
                break;
            case 1219:
                if (E()) {
                    cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                    int d = cVar.d();
                    Bundle bundle = new Bundle();
                    bundle.putString("message", cVar.a());
                    if (d != -2) {
                        if (d != -6) {
                            showFragmentDialog(607, bundle);
                            break;
                        }
                        showFragmentDialog(608, bundle);
                        break;
                    } else if (!com.qq.reader.common.login.c.a((Activity) this, Boolean.valueOf(true))) {
                        com.qq.reader.common.login.c.a();
                        bundle.putString("message", "支付出现问题，请重试");
                        showFragmentDialog(607, bundle);
                        break;
                    }
                }
                break;
            case 1237:
                Intent intent = new Intent();
                Bundle bundle2 = new Bundle();
                Mark e2 = com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.u.g()));
                if (e2 != null) {
                    bundle2.putString("filepath", e2.getId());
                    bundle2.putString("filename", e2.getBookName());
                    bundle2.putString("fileauthor", e2.getAuthor());
                    bundle2.putBoolean("detailpage_trial_read", true);
                    bundle2.putString("fileid", String.valueOf(e2.getBookId()));
                    intent.putExtras(bundle2);
                    com.qq.reader.b.a(intent, this);
                }
                return true;
            case 8003:
                if (!(message == null || message.obj == null)) {
                    long longValue = ((Long) message.obj).longValue();
                    if (this.u != null && this.u.g() == longValue) {
                        y();
                    }
                }
                return true;
            case 400008:
                if (this.b) {
                    this.b = false;
                    i();
                    break;
                }
                break;
            case 500000:
            case 500001:
                try {
                    if (message.obj != null) {
                        this.j.a((com.qq.reader.module.bookstore.qnative.page.b) message.obj);
                    }
                    c();
                    if (this.i != null) {
                        this.l = true;
                        this.i.setRefreshing(false);
                    }
                    p();
                    j();
                    if (this.W) {
                        this.W = false;
                        v();
                    }
                } catch (Exception e3) {
                    com.qq.reader.common.monitor.f.a("DetailActivity", e3.getMessage());
                }
                return true;
            case 500004:
                this.l = false;
                p();
                d();
                this.E.setBackgroundResource(R.drawable.titler_bg);
                this.r.setVisibility(8);
                ao.a(this.p, 1.0f);
                return true;
            case 500007:
                o();
                break;
            case 500008:
                m();
                break;
            case 6000014:
                af.a(getApplicationContext(), ReaderApplication.getApplicationImp().getResources().getString(R.string.comment_send_success), 0).a();
                if (!isFinishing() && this.B < this.C) {
                    m();
                }
                return true;
            case 8000011:
                b((AlertDialog) message.obj);
                return true;
        }
        return super.handleMessageImp(message);
    }

    private String r() {
        return "音频详情";
    }

    private void s() {
        this.t = (TextView) findViewById(R.id.detail_bottom_notify_on_board);
        this.s = findViewById(R.id.detail_bottom_btns);
        this.s.setVisibility(8);
        this.v = (TextView) findViewById(R.id.detail_bottom_btns_read);
        this.v.setText(R.string.text_free_listen);
        this.v.setClickable(false);
        this.J.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_B256", null, this.a.o);
                o.c(this.a, String.valueOf(this.a.u.g()), this.a.u.n(), this.a.A, null);
            }
        });
        this.w = (TextView) findViewById(R.id.detail_bottom_btns_download);
        this.w.setClickable(false);
        this.I.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_B255", null, this.a.o);
                if (com.qq.reader.common.login.c.b()) {
                    this.a.v();
                    return;
                }
                this.a.setLoginNextTask(new com.qq.reader.common.login.a(this) {
                    final /* synthetic */ AnonymousClass30 a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        switch (i) {
                            case 1:
                                this.a.a.w();
                                return;
                            default:
                                return;
                        }
                    }
                });
                this.a.startLogin();
            }
        });
        this.x = (TextView) findViewById(R.id.tv_detail_bottom_btns_add2shelf);
        this.K.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_B257", null, this.a.o);
                if (!TextUtils.isEmpty(this.a.R)) {
                    Map hashMap = new HashMap();
                    hashMap.put("finish", this.a.R);
                    if (this.a.S) {
                        i.a("event_B205", hashMap, this.a);
                    } else if (this.a.T) {
                        i.a("event_B206", hashMap, this.a);
                    }
                }
                this.a.A();
                try {
                    long j = this.a.A.getLong("URL_BUILD_PERE_BOOK_ID", 0);
                    StatisticsManager.a().e("" + j).d(this.a.A.getString(s.STATPARAM_KEY)).a(4).c();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void t() {
        try {
            if (this.j != null) {
                JSONObject x = ((com.qq.reader.module.bookstore.qnative.page.impl.a) this.j).x();
                if (this.u != null) {
                    this.u.a(x);
                    y();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void u() {
        findViewById(R.id.layout_off_market).setVisibility(0);
        this.E.setBackgroundColor(getResources().getColor(R.color.skin_set_bookdetail_title_bar_endcolor));
        this.q.setImageResource(R.drawable.titlebar_icon_back_selector);
        this.p.setText(r());
        this.V = true;
    }

    private void v() {
        if (this.u != null) {
            int c = this.u.c();
            if (c == 1 || c == 3) {
                x();
            } else if (c != 2) {
            } else {
                if (this.u.t() || this.u.b()) {
                    x();
                } else {
                    showFragmentDialog(606);
                }
            }
        }
    }

    private void w() {
        this.W = true;
        o();
    }

    private void x() {
        Intent intent = new Intent();
        Parcelable onlineTag = new OnlineTag(String.valueOf(this.u.g()), "", System.currentTimeMillis());
        onlineTag.c(1);
        onlineTag.j(2);
        onlineTag.k("mp3");
        onlineTag.a(this.u.n());
        onlineTag.e(this.u.o());
        intent.putExtra("com.qq.reader.OnlineTag", onlineTag);
        intent.setClass(this, AudioBookDownloadActivity.class);
        startActivity(intent);
    }

    private void y() {
        if (this.u != null) {
            this.t.setVisibility(8);
            long g = this.u.g();
            if (g > 0) {
                this.s.setVisibility(0);
                this.v.setVisibility(0);
                this.K.setVisibility(0);
                a(true);
                this.x.setText(R.string.bookinfo_add2bookshelf_ok);
                Mark mark = null;
                if (g != 0) {
                    mark = com.qq.reader.common.db.handle.i.c().e(String.valueOf(g));
                }
                if (this.u.c() == 2 && this.u.p() == 2000000804) {
                    this.w.setText(R.string.subscribe_whole_album);
                }
                if (this.u.t() || this.u.h() == 0 || this.u.b()) {
                    this.v.setText(R.string.text_play);
                } else {
                    this.v.setText(R.string.text_free_listen);
                }
                this.w.setText(R.string.webpage_bookinfo_download_free);
                if (mark != null) {
                    a(false);
                    this.x.setText(R.string.bookinfo_add2bookshelf_already);
                    return;
                }
                this.x.setText(R.string.bookinfo_add2bookshelf_ok);
                a(true);
            }
        }
    }

    private void z() {
        this.u.u();
        this.mLoginNextTask = this.u.w();
    }

    private void A() {
        this.u.v();
        this.mLoginNextTask = this.u.w();
        if (com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.u.g())) == null) {
            this.x.setText(R.string.bookinfo_add2bookshelf_ok);
            a(true);
            return;
        }
        this.x.setText(R.string.bookinfo_add2bookshelf_already);
        a(false);
    }

    private void a(String str, String str2, String str3) {
        com.qq.reader.cservice.download.book.g gVar = new com.qq.reader.cservice.download.book.g(str);
        gVar.e(str2);
        gVar.f(str3);
        com.qq.reader.cservice.download.book.h hVar = new com.qq.reader.cservice.download.book.h(getApplicationContext(), gVar);
        hVar.a(this);
        C();
        hVar.start();
    }

    private boolean B() {
        try {
            if (this.z != null && this.z.f()) {
                this.z.cancel();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void a(com.qq.reader.cservice.download.book.g gVar) {
        this.mHandler.obtainMessage(1203, gVar).sendToTarget();
    }

    public void b(com.qq.reader.cservice.download.book.g gVar) {
        this.mHandler.obtainMessage(1204, gVar).sendToTarget();
    }

    public void c(com.qq.reader.cservice.download.book.g gVar) {
        this.mHandler.obtainMessage(1205, gVar).sendToTarget();
    }

    private void C() {
        try {
            if (this.z == null) {
                this.z = new com.qq.reader.view.c(this);
                this.z.c(true);
                this.z.a(getResources().getString(R.string.get_book_music_feed_loading));
            }
            if (!this.z.f()) {
                this.z.f_();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void D() {
        try {
            if (this.y == null || !this.y.isShowing()) {
                this.y = ProgressDialog.show(this, "", "正在购买，请稍候...", true);
                this.y.setCanceledOnTouchOutside(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean E() {
        try {
            if (this.y != null && this.y.isShowing()) {
                this.y.cancel();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void f() {
        new JSPay(this).startCharge(this, this.a);
    }

    public void b(com.qq.reader.cservice.buy.a.c cVar) {
        Message obtain = Message.obtain();
        obtain.what = 1218;
        obtain.obj = cVar;
        this.mHandler.sendMessage(obtain);
    }

    public void c(com.qq.reader.cservice.buy.a.c cVar) {
        Message obtain = Message.obtain();
        obtain.what = 1219;
        obtain.obj = cVar;
        this.mHandler.sendMessage(obtain);
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) q.a(this, i, null);
        String str = "";
        switch (i) {
            case 606:
                if (bundle != null) {
                    if (bundle.getBoolean("onlybuy", false)) {
                        this.U = 100;
                    } else {
                        this.U = 0;
                    }
                }
                a(alertDialog, (Activity) this);
                break;
            case 607:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 608:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.charge, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.b = true;
                        this.a.f();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.a(-1, (int) R.drawable.buy_book_dialog_confirm_bg);
                break;
        }
        return alertDialog != null ? alertDialog : super.createDialog(i, bundle);
    }

    public void g() {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.a.O.a(aVar);
                Message obtain = Message.obtain();
                obtain.what = 400008;
                this.a.mHandler.sendMessage(obtain);
            }

            public void a() {
            }
        }, String.valueOf(this.u.g()), 2));
    }

    public void a(final AlertDialog alertDialog) {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeAudioBookDetailActivity b;

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.b.O.a(aVar);
                Message obtainMessage = this.b.mHandler.obtainMessage(8000011);
                obtainMessage.obj = alertDialog;
                this.b.mHandler.sendMessage(obtainMessage);
            }

            public void a() {
            }
        }, String.valueOf(this.u.g()), 2));
    }

    public void b(final AlertDialog alertDialog) {
        if (alertDialog != null && alertDialog.isShowing()) {
            TextView textView = (TextView) alertDialog.findViewById(R.id.tv_user_balance);
            if (textView != null) {
                textView.setText(this.O.b());
            }
            if (this.O.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ NativeAudioBookDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.O.g);
            }
            ProgressBar progressBar = (ProgressBar) alertDialog.findViewById(R.id.pb_user_balance);
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
            Button c = alertDialog.c(-1);
            Button c2 = alertDialog.c(-2);
            if (c != null && c2 != null) {
                int h = (this.u.h() * this.u.j()) / 100;
                if (this.u.p() == 2000000804 && this.u.a() > 0) {
                    h = this.u.a();
                }
                int i = this.u.i();
                int l = this.u.l();
                if (l > 0 && l < r0) {
                    h = l;
                }
                if (i > 0 && r0 < i) {
                    h = i;
                }
                i = this.O.a();
                if (i < 0 || i >= h) {
                    c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeAudioBookDetailActivity b;

                        public void onClick(View view) {
                            alertDialog.c();
                            this.b.i();
                        }
                    });
                    h = R.string.alert_dialog_buy_confirm;
                    if ("19200".equals(this.u.q())) {
                        h = R.string.alert_dialog_subscription_confirm;
                    }
                    c.setText(getString(h));
                    c2.setVisibility(0);
                    c2.setText(getString(R.string.alert_dialog_cancel));
                    c2.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeAudioBookDetailActivity b;

                        public void onClick(View view) {
                            alertDialog.c();
                        }
                    });
                    return;
                }
                this.a = h;
                if (!ao.s(this.O.e)) {
                    View findViewById;
                    if (getResources().getConfiguration().orientation == 2) {
                        findViewById = alertDialog.findViewById(R.id.activity_info_land);
                        textView = (TextView) alertDialog.findViewById(R.id.activity_text_land);
                    } else {
                        findViewById = alertDialog.findViewById(R.id.activity_info);
                        textView = (TextView) alertDialog.findViewById(R.id.activity_text);
                    }
                    findViewById.setVisibility(0);
                    textView.setText(this.O.e);
                    textView.setVisibility(0);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "4");
                    i.a("event_A202", hashMap, ReaderApplication.getApplicationImp());
                }
                c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookDetailActivity b;

                    public void onClick(View view) {
                        alertDialog.c();
                        this.b.b = true;
                        this.b.f();
                    }
                });
                h = R.string.alert_dialog_buy_balance_insufficient;
                if ("19200".equals(this.u.q())) {
                    h = R.string.alert_dialog_subscription_balance_insufficient;
                }
                c.setText(getString(h));
                c2.setVisibility(8);
            }
        }
    }

    private void a(AlertDialog alertDialog, Activity activity) {
        int i;
        CharSequence charSequence;
        View inflate = LayoutInflater.from(activity).inflate(R.layout.book_buy_view_new, null);
        TextView textView = (TextView) inflate.findViewById(R.id.book_discount_msg);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_origin_price);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.pb_user_balance);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_price);
        TextView textView4 = (TextView) inflate.findViewById(R.id.textViewDownloadType);
        textView4.setVisibility(0);
        textView4.setText("");
        String n = this.u.n();
        int h = this.u.h();
        int j = this.u.j();
        String k = this.u.k();
        int l = this.u.l();
        Object m = this.u.m();
        int i2 = this.u.i();
        if (j < 100 || l > 0 || i2 > 0 || (this.u.p() == 2000000804 && this.u.a() > 0 && h > this.u.a())) {
            if (this.u.p() != 2000000804) {
                j = (j * h) / 100;
            } else if (this.u.a() > 0) {
                j = this.u.a();
            } else {
                j = h;
            }
            if (l <= 0 || l >= j) {
                l = j;
            } else {
                k = m;
            }
            Object obj = null;
            if (i2 <= 0 || ((l <= 0 || i2 >= l) && l != 0)) {
                String str = k;
                i = l;
            } else {
                obj = 1;
                i = i2;
            }
            if (TextUtils.isEmpty(m)) {
                textView.setVisibility(8);
            } else {
                textView.setText("(" + m + ")");
                textView.setVisibility(0);
            }
            textView3.setText(String.valueOf(i));
            if (obj != null) {
                textView2.setVisibility(8);
            } else {
                Object obj2 = String.valueOf(h) + "书币";
                CharSequence spannableString = new SpannableString(obj2);
                spannableString.setSpan(new StrikethroughSpan(), 0, obj2.length(), 33);
                textView2.setText(spannableString);
            }
        } else {
            textView.setVisibility(8);
            textView2.setVisibility(8);
            textView3.setText(String.valueOf(h));
            i = h;
        }
        ((TextView) inflate.findViewById(R.id.book_name)).setText(String.format(getResources().getString(R.string.buy_book_name), new Object[]{n}));
        if (this.u.p() == 2000000804) {
            if (h > 0 && i > 0) {
                ((TextView) inflate.findViewById(R.id.limitText)).setVisibility(0);
            }
            if (this.u.c() == 2) {
                charSequence = "预计更新" + this.u.d() + "集，已更新至第" + this.u.e() + "集";
                textView = (TextView) inflate.findViewById(R.id.preText);
                textView.setText(charSequence);
                textView.setVisibility(0);
            }
        }
        int a = this.O.a();
        String b = this.O.b();
        if (a < 0) {
            progressBar.setVisibility(0);
            charSequence = "";
        } else {
            progressBar.setVisibility(8);
            Object obj3 = b;
        }
        textView = (TextView) inflate.findViewById(R.id.tv_user_balance);
        textView.setText(charSequence);
        if (this.O.d > 0) {
            com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                final /* synthetic */ NativeAudioBookDetailActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            }, new OnDismissListener(this) {
                final /* synthetic */ NativeAudioBookDetailActivity a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            }, this.O.g);
        }
        CharSequence string = getString(R.string.alert_dialog_audio_buy);
        if (this.u.p() == 2000000804 && this.u.c() == 2) {
            string = getString(R.string.alert_dialog_audio_subscribe);
        }
        alertDialog.a(inflate);
        alertDialog.setTitle(string);
        int i3;
        final AlertDialog alertDialog2;
        if (a < 0 || a >= i) {
            i3 = R.string.alert_dialog_buy_confirm;
            if ("19200".equals(this.u.q())) {
                i3 = R.string.alert_dialog_subscription_confirm;
            }
            alertDialog2 = alertDialog;
            alertDialog.a(i3, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeAudioBookDetailActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog2.c();
                    this.b.i();
                }
            });
            alertDialog2 = alertDialog;
            alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeAudioBookDetailActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog2.c();
                }
            });
        } else {
            this.a = i;
            i3 = R.string.alert_dialog_buy_balance_insufficient;
            if ("19200".equals(this.u.q())) {
                i3 = R.string.alert_dialog_subscription_balance_insufficient;
            }
            alertDialog2 = alertDialog;
            alertDialog.a(i3, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeAudioBookDetailActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog2.c();
                    this.b.b = true;
                    this.b.f();
                }
            });
        }
        alertDialog.a(-1, (int) R.drawable.selector_orange_button);
        alertDialog.a(-2, (int) R.drawable.selector_white_button);
        a(alertDialog);
    }

    public void doFunction(Bundle bundle) {
        String string = bundle.getString("KEY_ACTION");
        Intent intent = new Intent();
        if ("detail_2_topic_main".equals(string)) {
            com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if ("detail_2_chapter".equals(string)) {
            try {
                if (G()) {
                    new JSBookDir(this).dir(((com.qq.reader.module.bookstore.qnative.page.impl.a) this.j).x().optJSONObject("audio").toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                com.qq.reader.common.monitor.debug.c.a("NativeAudioBookDetailActivity", "error " + e);
            }
        } else if ("detail_2_topic".equals(string)) {
            this.B = bundle.getInt("DETAIL_COMMENT_INDEX_COUNT", this.C);
            if (com.qq.reader.common.login.c.b()) {
                H();
                return;
            }
            setLoginNextTask(new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeAudioBookDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass17 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.H();
                                }
                            });
                            return;
                        default:
                            return;
                    }
                }
            });
            startLogin();
        } else if ("detail_2_comment".equals(string)) {
            try {
                string = "comment.html?bid=" + ((com.qq.reader.module.bookstore.qnative.page.impl.a) this.j).x().optJSONObject("audio").optString("adid");
                intent.setClass(this, WebBrowserForContents.class);
                intent.putExtra("com.qq.reader.WebContent", string);
                com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(intent);
            } catch (Exception e2) {
            }
        } else if ("detail_2_reward".equals(string)) {
            long j = bundle.getLong("URL_BUILD_PERE_BOOK_ID");
            r0 = bundle.getInt("PARA_TYPE_REWARD_TAB_INDEX");
            String string2 = bundle.getString("PARA_TYPE_REWARD_EXTRA_URL_PARAMS");
            String string3 = bundle.getString("PARA_TYPE_BOOK_TITLE");
            intent.setClass(this.o, WebBookRewardActivity.class);
            intent.putExtra("URL_BUILD_PERE_BOOK_ID", j);
            intent.putExtra("PARA_TYPE_REWARD_TAB_INDEX", r0);
            intent.putExtra("PARA_TYPE_REWARD_EXTRA_URL_PARAMS", string2);
            intent.putExtra("PARA_TYPE_BOOK_TITLE", string3);
            com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent);
        } else if ("detail_2_open_package_vip".equals(string)) {
            r0 = bundle.getInt("package_id", 0);
            if (r0 > 0) {
                a(String.valueOf(r0));
            }
        } else if ("detail_2_openvip".equals(string)) {
            if (com.qq.reader.common.login.c.b()) {
                new JSPay(this).openVip();
                StatisticsManager.a().a(this.A).a(6).c();
                return;
            }
            this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeAudioBookDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            com.qq.reader.common.readertask.g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                                final /* synthetic */ AnonymousClass18 a;

                                {
                                    this.a = r1;
                                }

                                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                    try {
                                        JSONObject jSONObject = new JSONObject(str);
                                        if (jSONObject != null) {
                                            com.qq.reader.common.login.b.a.a(com.qq.reader.common.login.c.c(), jSONObject);
                                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                                final /* synthetic */ AnonymousClass1 a;

                                                {
                                                    this.a = r1;
                                                }

                                                public void run() {
                                                    com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
                                                    if (c != null) {
                                                        if (c.j(this.a.a.a)) {
                                                            this.a.a.a.o();
                                                            return;
                                                        }
                                                        new JSPay(this.a.a.a).openVip();
                                                        StatisticsManager.a().a(this.a.a.a.A).a(6).c();
                                                    }
                                                }
                                            });
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                }
                            }));
                            return;
                        default:
                            return;
                    }
                }
            };
            startLogin();
        } else if (!"buy_one_price".equals(string)) {
        } else {
            if (com.qq.reader.common.login.c.b()) {
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("onlybuy", true);
                showFragmentDialog(606, bundle2);
                return;
            }
            setLoginNextTask(new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeAudioBookDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass19 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.o();
                                }
                            });
                            return;
                        default:
                            return;
                    }
                }
            });
            startLogin();
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return super.onKeyDown(i, keyEvent);
        }
        if (F()) {
            Intent intent = new Intent();
            intent.setClass(this.o, MainActivity.class);
            intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
            startActivity(intent);
            return true;
        }
        setResult(0);
        finish();
        return true;
    }

    private boolean F() {
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.getInt("com.qq.reader.WebContent.from", 0) != 1) {
            return false;
        }
        return true;
    }

    public void h_() {
        t();
        r.a().a(2, String.valueOf(this.u.g()), this.u.n());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 20001) {
            if (i2 == 0) {
                this.mHandler.sendEmptyMessageDelayed(500007, 2000);
                g();
            } else if (i2 == 5) {
                startLogin();
            }
        } else if (i == 20002) {
            if (i2 == 0 || i2 == 20003) {
                this.mHandler.sendEmptyMessageDelayed(500007, 2000);
            } else if (i2 == 5) {
                startLogin();
            }
        } else if (i == 1002) {
            if (i2 == -1) {
                new com.qq.reader.common.emotion.a(this, this.mHandler, ((com.qq.reader.module.bookstore.qnative.page.impl.a) this.j).x().optJSONObject("audio").optString("audioName", null)) {
                    final /* synthetic */ NativeAudioBookDetailActivity a;

                    public void a(String str, String str2) {
                    }
                }.a(intent);
            }
        } else if (i == Constants.ERRORCODE_UNKNOWN) {
            o();
        }
        super.onActivityResult(i, i2, intent);
    }

    private boolean G() {
        if (this.u == null || this.u.x()) {
            return true;
        }
        Dialog a = new AlertDialog.a(this).a((CharSequence) "提示").b((CharSequence) "本书需要升级到最新版本才能享受精排版阅读体验").a((CharSequence) "升级", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.checkUpdate(false, false);
            }
        }).a();
        a.setCanceledOnTouchOutside(false);
        a.show();
        return false;
    }

    private void H() {
        Intent intent = new Intent();
        intent.setClass(this, CommitCommentActivity.class);
        intent.putExtras(this.A);
        startActivityForResult(intent, 1002);
    }

    public void e_() {
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.F.setRefreshing(false);
            }
        }, 1000);
    }

    private void a(String str) {
        if (com.qq.reader.common.login.c.b()) {
            b(str);
            return;
        }
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ NativeAudioBookDetailActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        com.qq.reader.common.readertask.g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                            final /* synthetic */ AnonymousClass25 a;

                            {
                                this.a = r1;
                            }

                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                try {
                                    JSONObject jSONObject = new JSONObject(str);
                                    if (jSONObject != null) {
                                        com.qq.reader.common.login.b.a.a(com.qq.reader.common.login.c.c(), jSONObject);
                                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                if (com.qq.reader.common.login.c.c() != null) {
                                                    this.a.a.a.o();
                                                }
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                            }
                        }));
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }

    private void b(String str) {
        String str2 = "buyPack.html?pid=" + str;
        if (this.aa == null) {
            this.aa = new h(this);
            this.aa.a((Activity) this);
            this.aa.a(new h.a(this) {
                final /* synthetic */ NativeAudioBookDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(String str, boolean z) {
                    try {
                        com.qq.reader.common.monitor.f.d("readerpage", "OnDialogClose " + str);
                        this.a.aa.dismiss();
                        this.a.aa = null;
                        if (z) {
                            this.a.o();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        this.aa.a(str2);
        this.aa.f_();
    }

    private void a(boolean z) {
        this.K.setEnabled(z);
        this.N.setEnabled(z);
        this.x.setEnabled(z);
    }

    public void i() {
        int a = this.O.a();
        if (a > 0) {
            if (this.a <= 0 || a >= this.a) {
                com.qq.reader.cservice.buy.a.a aVar = new com.qq.reader.cservice.buy.a.a(this.o, String.valueOf(this.u.g()));
                aVar.a(this.u.f());
                aVar.a((b) this);
                aVar.start();
                D();
            }
        }
    }

    public void onAudioFloatingStateChange(int i, long j, boolean z, String str) {
        AudioFloatingWindowView audioFloatingWindowView = (AudioFloatingWindowView) findViewById(R.id.img_audio_floating);
        if (audioFloatingWindowView != null) {
            ao.a(2, this, audioFloatingWindowView, j, z, str);
        }
    }
}
