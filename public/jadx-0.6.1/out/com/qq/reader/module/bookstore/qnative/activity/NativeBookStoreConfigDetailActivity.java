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
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import com.qq.reader.activity.CommitCommentActivity;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.activity.WebBookRewardActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.download.task.m;
import com.qq.reader.common.download.task.n;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.readertask.protocol.RentBookQueryTask;
import com.qq.reader.common.readertask.protocol.RentBookTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.JSBookDir;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.cservice.buy.a.b;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.download.book.e;
import com.qq.reader.cservice.download.book.f;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.readpage.AddLimitFreeBook2DBTask;
import com.qq.reader.module.readpage.q;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.view.web.h;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tesla.soload.SoLoadCore;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeBookStoreConfigDetailActivity extends NativeBookStoreConfigBaseActivity implements b, f, a {
    private TextView A;
    private Bundle B;
    private e C = null;
    private int D = -1;
    private int E = 3;
    private boolean F = false;
    private View G;
    private SwipeRefreshLayout H;
    private ProgressBar I;
    private ProgressBar J;
    private ViewGroup K;
    private LinearLayout L;
    private LinearLayout M;
    private ImageView N;
    private ImageView O;
    private ImageView P;
    private boolean Q = true;
    private boolean R = true;
    private final com.qq.reader.common.charge.voucher.a.a S = new com.qq.reader.common.charge.voucher.a.a();
    private int T = 0;
    private int U = 0;
    private String V = "";
    private boolean W = false;
    private boolean X = false;
    private int Y = 0;
    private Bitmap Z;
    int a = 0;
    private BroadcastReceiver aa = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreConfigDetailActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            c.a(this.a.o).a(intent.getStringExtra("message"), new g(this) {
                final /* synthetic */ AnonymousClass43 a;

                {
                    this.a = r1;
                }

                public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                    if (obj instanceof j) {
                        j jVar = (j) obj;
                        try {
                            if (this.a.a.Z == null) {
                                this.a.a.Z = ao.a(jVar.b(), -1);
                            }
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.a.a.getResources(), this.a.a.Z);
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
    };
    private Bundle ab;
    private m ac = new m(this) {
        final /* synthetic */ NativeBookStoreConfigDetailActivity a;

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
                if (this.a.u == null || downloadBookTask.getId() == this.a.u.d()) {
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
    private h ad;
    boolean b = false;
    boolean c = false;
    private final String d = "LBStoreConfigDetailActivity";
    private final long n = 500;
    private Context o;
    private TextView p;
    private ImageView q;
    private ImageView r;
    private View s = null;
    private TextView t = null;
    private com.qq.reader.module.bookstore.qnative.b.a u = null;
    private TextView v = null;
    private TextView w = null;
    private TextView x = null;
    private ProgressDialog y;
    private com.qq.reader.view.c z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.o = this;
        setContentView(R.layout.localbookstore_detail_layout);
        if (!"Meizu_M040".equals(com.qq.reader.common.c.a.E)) {
            getWindow().addFlags(SigType.WLOGIN_PF);
        }
        this.u = new com.qq.reader.module.bookstore.qnative.b.a(this, this.mHandler);
        this.B = getIntent().getExtras();
        if (this.B != null) {
            if (this.B.containsKey("PARA_TYPE_IS_BOOK_FINISH")) {
                this.V = this.B.getString("PARA_TYPE_IS_BOOK_FINISH", "");
            }
            if (this.B.containsKey("PARA_TYPE_IS_FROM_END_PAGE_WRITER")) {
                this.W = this.B.getBoolean("PARA_TYPE_IS_FROM_END_PAGE_WRITER", false);
            }
            if (this.B.containsKey("PARA_TYPE_IS_FROM_END_PAGE_READER")) {
                this.X = this.B.getBoolean("PARA_TYPE_IS_FROM_END_PAGE_READER", false);
            }
        }
        StatisticsManager.a().b("DetailPage").a(this.B).c();
        a();
        b();
        q();
        this.F = false;
        this.C = (e) l.d(1001);
        this.C.a(TaskStateEnum.values(), this.ac);
        i.a("event_DetailPage", null, this.o);
        d.a((Context) this).a(this.aa, new IntentFilter("detail.loadimg"));
        setStatPageName("bookdetailpage");
        com.qq.reader.activity.a.a.a().a(this);
    }

    protected void onResume() {
        super.onResume();
        t();
    }

    protected void onPause() {
        super.onPause();
        d.a((Context) this).a(this.aa);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.C.b(TaskStateEnum.values(), this.ac);
        if (!(this.Z == null || this.Z.isRecycled())) {
            this.Z.recycle();
            this.Z = null;
        }
        this.V = "";
        this.W = false;
        this.X = false;
        com.qq.reader.activity.a.a.a().b(this);
    }

    protected void onStop() {
        super.onStop();
    }

    @SuppressLint({"NewApi"})
    public void a() {
        super.a();
        this.K = (ViewGroup) findViewById(R.id.ll_download);
        this.L = (LinearLayout) findViewById(R.id.ll_read);
        this.M = (LinearLayout) findViewById(R.id.ll_add2shelf);
        this.N = (ImageView) findViewById(R.id.img_download_icon);
        this.O = (ImageView) findViewById(R.id.img_read_icon);
        this.P = (ImageView) findViewById(R.id.img_add_shelf_icon);
        this.A = (TextView) findViewById(R.id.tv_download_voucher_tips);
        this.J = (ProgressBar) findViewById(R.id.progress_read_download_percent);
        this.I = (ProgressBar) findViewById(R.id.progress_download_percent);
        this.H = (SwipeRefreshLayout) findViewById(R.id.detail_pull_down_list);
        this.H.setOnRefreshListener(new SwipeRefreshLayout.b(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.e_();
            }
        });
        this.G = findViewById(R.id.common_titler);
        this.p = (TextView) this.G.findViewById(R.id.profile_header_title);
        this.p.setText(r());
        this.q = (ImageView) findViewById(R.id.profile_header_left_back);
        this.r = (ImageView) findViewById(R.id.profile_header_right_image);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

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
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String str = "";
                new aj(this.a, String.valueOf(this.a.u.d()), this.a.u.k()).f_();
                i.a("event_B132", null, this.a.o);
            }
        });
        s();
        this.g = (ListView) findViewById(R.id.detail_list);
        this.g.setOverScrollMode(2);
        this.g.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i <= 0) {
                    View childAt = absListView.getChildAt(0);
                    if (childAt != null && childAt.findViewById(R.id.colcard3_bookinfo) != null) {
                        int color;
                        int color2;
                        double min = Math.min(((double) Math.abs(childAt.getTop())) / ((double) childAt.findViewById(R.id.colcard3_bookinfo).getHeight()), 1.0d);
                        if (min == 1.0d && this.a.p.getAlpha() == 0.0f) {
                            ao.a(this.a.p, 1.0f);
                            color = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_startcolor);
                            color2 = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_endcolor);
                            ObjectAnimator ofObject = ObjectAnimator.ofObject(this.a.G, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(color), Integer.valueOf(color2)});
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
                                ObjectAnimator ofObject2 = ObjectAnimator.ofObject(this.a.G, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(color2), Integer.valueOf(color)});
                                ofObject2.setDuration(200);
                                ofObject2.start();
                            } else {
                                this.a.G.setBackgroundColor(color);
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
            this.j = com.qq.reader.module.bookstore.qnative.e.a().a(this.B, this);
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
                if (y()) {
                    com.qq.reader.cservice.download.book.g gVar = (com.qq.reader.cservice.download.book.g) message.obj;
                    if (this.u != null) {
                        if (gVar.a() == 5) {
                            com.qq.reader.module.bookchapter.online.i iVar = new com.qq.reader.module.bookchapter.online.i(this.u.d() + "");
                            iVar.b(gVar.a());
                            iVar.a(gVar.b());
                            new AddLimitFreeBook2DBTask(iVar).execute();
                        }
                        this.u.a(gVar.c());
                        this.u.a(true);
                        if (this.u.d() == Long.parseLong(gVar.e())) {
                            v();
                            break;
                        }
                    }
                }
                break;
            case 1204:
            case 21001:
                if (y()) {
                    af.a(getApplicationContext(), getString(R.string.download_faile), 0).a();
                    break;
                }
                break;
            case 1205:
                if (y()) {
                    showFragmentDialog(606);
                    break;
                }
                break;
            case 1217:
                a(String.valueOf(this.u.d()), this.u.c(), String.valueOf(message.obj));
                break;
            case 1218:
                i.a("event_B144", null, ReaderApplication.getApplicationImp());
                if (B()) {
                    if (this.u.o()) {
                        v();
                    } else {
                        cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                        try {
                            if (this.u != null) {
                                this.u.a(cVar.b());
                                if (this.u.d() == Long.parseLong(cVar.c())) {
                                    if (this.Y != 100) {
                                        v();
                                    } else {
                                        af.a(ReaderApplication.getApplicationContext(), (CharSequence) "购买成功", 0).a();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    this.mHandler.sendEmptyMessage(500007);
                    break;
                }
                break;
            case 1219:
                if (B()) {
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
                Mark e2 = com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.u.d()));
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
            case 8001:
                if (!(message == null || message.obj == null)) {
                    int intValue = ((Integer) message.obj).intValue();
                    if (message.arg1 == 1) {
                        a(intValue, message.arg1);
                        break;
                    }
                }
                break;
            case 8003:
                if (!(message == null || message.obj == null)) {
                    long longValue = ((Long) message.obj).longValue();
                    if (this.u != null && this.u.d() == longValue) {
                        u();
                    }
                }
                return true;
            case 400008:
                if (this.b) {
                    this.b = false;
                    i();
                }
                if (this.c) {
                    this.c = false;
                    C();
                    break;
                }
                break;
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
                } catch (Exception e3) {
                    com.qq.reader.common.monitor.f.a("DetailActivity", e3.getMessage());
                }
                return true;
            case 500004:
                this.l = false;
                p();
                d();
                this.G.setBackgroundResource(R.drawable.titler_bg);
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
                if (!isFinishing() && this.D < this.E) {
                    m();
                }
                return true;
            case 8000011:
                c((AlertDialog) message.obj);
                return true;
            case 8000012:
                d((AlertDialog) message.obj);
                return true;
        }
        return super.handleMessageImp(message);
    }

    private String r() {
        return "书籍详情";
    }

    private void s() {
        this.t = (TextView) findViewById(R.id.detail_bottom_notify_on_board);
        this.s = findViewById(R.id.detail_bottom_btns);
        this.s.setVisibility(8);
        this.v = (TextView) findViewById(R.id.detail_bottom_btns_read);
        this.v.setClickable(false);
        this.L.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.V)) {
                    Map hashMap = new HashMap();
                    hashMap.put("finish", this.a.V);
                    if (this.a.W) {
                        i.a("event_B207", hashMap, this.a);
                    } else if (this.a.X) {
                        i.a("event_B208", hashMap, this.a);
                    }
                }
                if (this.a.ab != null) {
                    Intent intent = new Intent();
                    intent.putExtras(this.a.ab);
                    com.qq.reader.b.a(intent, this.a);
                } else {
                    Mark e = com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.a.u.d()));
                    if (e != null && (e instanceof DownloadMark)) {
                        com.qq.reader.common.download.task.g downloadTask = ((DownloadMark) e).getDownloadTask();
                        if (downloadTask != null) {
                            if (downloadTask.getState() == TaskStateEnum.Paused || downloadTask.getState() == TaskStateEnum.Failed) {
                                if (downloadTask.getIsOnlyDownLoadIcon()) {
                                    downloadTask.setIsOnlyDownLoadIcon(false);
                                }
                                if (this.a.C != null) {
                                    this.a.C.e(downloadTask);
                                }
                            } else if ((downloadTask.getState() == TaskStateEnum.DeactivePrepared || downloadTask.getState() == TaskStateEnum.DeactiveStarted) && this.a.C != null) {
                                this.a.C.c(downloadTask);
                            }
                            this.a.mHandler.sendMessage(this.a.mHandler.obtainMessage(1237));
                            return;
                        }
                    }
                    this.a.Q = false;
                    this.a.mLoginNextTask = this.a.u.t();
                }
                try {
                    long j = this.a.B.getLong("URL_BUILD_PERE_BOOK_ID", 0);
                    String string = this.a.B.getString(s.STATPARAM_KEY);
                    com.qq.reader.common.monitor.debug.c.d("LBStoreConfigDetailActivity", "上传书籍来源,值为 : " + string);
                    if (TextUtils.isEmpty(string) && this.a.u != null) {
                        string = this.a.u.c();
                    }
                    if (!TextUtils.isEmpty(string)) {
                        com.qq.reader.common.monitor.a.b.a(new com.qq.reader.common.monitor.a.a(j + "", string));
                    }
                    StatisticsManager.a().e("" + j).d(string).a(2).c();
                    i.a("event_Bookonline", null, this.a.o);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        this.w = (TextView) findViewById(R.id.detail_bottom_btns_download);
        this.w.setClickable(false);
        this.K.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.V)) {
                    Map hashMap = new HashMap();
                    hashMap.put("finish", this.a.V);
                    if (this.a.W) {
                        i.a("event_B209", hashMap, this.a);
                    } else if (this.a.X) {
                        i.a("event_B210", hashMap, this.a);
                    }
                }
                if (!this.a.a(false)) {
                    i.a("event_C10", null, this.a.o);
                    StatisticsManager.a().a("event_C10", null);
                    this.a.Q = true;
                    this.a.mLoginNextTask = this.a.u.s();
                    try {
                        this.a.B.getLong("URL_BUILD_PERE_BOOK_ID", 0);
                        this.a.B.getString(s.STATPARAM_KEY);
                        StatisticsManager.a().a(3).a(this.a.B).c();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.x = (TextView) findViewById(R.id.tv_detail_bottom_btns_add2shelf);
        this.M.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.V)) {
                    Map hashMap = new HashMap();
                    hashMap.put("finish", this.a.V);
                    if (this.a.W) {
                        i.a("event_B205", hashMap, this.a);
                    } else if (this.a.X) {
                        i.a("event_B206", hashMap, this.a);
                    }
                }
                this.a.w();
                try {
                    long j = this.a.B.getLong("URL_BUILD_PERE_BOOK_ID", 0);
                    StatisticsManager.a().e("" + j).d(this.a.B.getString(s.STATPARAM_KEY)).a(4).c();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void t() {
        try {
            if (this.j != null) {
                JSONObject x = ((com.qq.reader.module.bookstore.qnative.page.impl.f) this.j).x();
                if (this.u != null) {
                    this.u.a(x);
                    new o().parseData(x);
                    u();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void u() {
        boolean z = true;
        int i = 0;
        if (this.u != null) {
            this.t.setVisibility(8);
            long d = this.u.d();
            if (d > 0) {
                boolean z2;
                if (ao.m(this.u.d())) {
                    e(false);
                    d(false);
                    this.s.setVisibility(0);
                    this.v.setVisibility(0);
                    this.M.setVisibility(0);
                    c(true);
                    this.x.setText(R.string.bookinfo_add2bookshelf_ok);
                    Mark mark = null;
                    if (d != 0) {
                        mark = com.qq.reader.common.db.handle.i.c().e(String.valueOf(d));
                    }
                    this.ab = null;
                    int b = this.u.b();
                    com.qq.reader.appconfig.a.d.bF(ReaderApplication.getApplicationImp());
                    if (b == 0) {
                        this.v.setText(R.string.webpage_bookinfo_readonline_free);
                    } else {
                        this.v.setText(R.string.webpage_bookinfo_readonline_pay);
                    }
                    if ("19200".equals(this.u.l())) {
                        this.N.setImageResource(R.drawable.detail_bottom_subscription_icon);
                    } else {
                        this.N.setImageResource(R.drawable.detail_bottom_download_icon);
                    }
                    if (mark != null) {
                        c(false);
                        this.x.setText(R.string.bookinfo_add2bookshelf_already);
                        if (mark instanceof DownloadMark) {
                            b(true);
                            if ("19200".equals(this.u.l())) {
                                this.w.setText(R.string.webpage_bookinfo_subscription_free);
                            } else {
                                this.w.setText(R.string.webpage_bookinfo_download_free);
                            }
                        } else {
                            if (!new File(mark.getId()).exists() || 4 == mark.getType()) {
                                b(true);
                                if ("19200".equals(this.u.l())) {
                                    this.w.setText(R.string.webpage_bookinfo_subscription_free);
                                    z2 = true;
                                } else {
                                    this.w.setText(R.string.webpage_bookinfo_download_free);
                                    z2 = true;
                                }
                            } else {
                                this.ab = new Bundle();
                                this.ab.putString("filepath", mark.getId());
                                this.ab.putString("filename", mark.getBookName());
                                this.ab.putString("fileauthor", mark.getAuthor());
                                this.ab.putInt("fileencode", mark.getEncoding());
                                this.v.setText(R.string.webpage_bookinfo_read);
                                if (mark.getBookName().endsWith("trial")) {
                                    b(true);
                                    if ("19200".equals(this.u.l())) {
                                        this.w.setText(R.string.webpage_bookinfo_subscription_free);
                                        z2 = true;
                                    } else {
                                        this.w.setText(R.string.webpage_bookinfo_download_free);
                                        z2 = true;
                                    }
                                } else {
                                    b(false);
                                    if ("19200".equals(this.u.l())) {
                                        this.w.setText(R.string.webpage_bookinfo_subscription_ok);
                                        z2 = true;
                                    } else {
                                        this.w.setText(R.string.webpage_bookinfo_download_ok);
                                        z2 = false;
                                    }
                                }
                            }
                            z = z2;
                        }
                    } else {
                        this.x.setText(R.string.bookinfo_add2bookshelf_ok);
                        c(true);
                        if ("19200".equals(this.u.l())) {
                            this.w.setText(R.string.webpage_bookinfo_subscription_free);
                        } else {
                            this.w.setText(R.string.webpage_bookinfo_download_free);
                        }
                        this.u.a();
                        this.w.setVisibility(0);
                    }
                } else {
                    this.t.setVisibility(0);
                    this.r.setVisibility(8);
                    if (this.u.m()) {
                        this.t.setText("上架提醒已开启");
                        this.t.setBackgroundResource(R.color.bookclub_textlightgray);
                        this.t.setClickable(false);
                    } else {
                        this.t.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                i.a("event_C132", null, this.a.getContext());
                                this.a.a(this.a.u.d());
                            }
                        });
                    }
                }
                if (z) {
                    z2 = ((com.qq.reader.module.bookstore.qnative.page.impl.f) this.j).a;
                    TextView textView = this.A;
                    if (!z2) {
                        i = 4;
                    }
                    textView.setVisibility(i);
                    return;
                }
                this.A.setVisibility(4);
            }
        }
    }

    public void a(long j) {
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                    final int optInt = jSONObject.optInt("code");
                    if (optInt == -9) {
                        this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        this.a.a.o();
                                        return;
                                    default:
                                        return;
                                }
                            }
                        };
                        this.a.startLogin();
                    }
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void run() {
                            if (optInt == 0) {
                                this.b.a.t.setText("上架提醒已开启");
                                this.b.a.t.setBackgroundResource(R.color.bookclub_textlightgray);
                            }
                        }
                    });
                } catch (Exception e) {
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        });
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.appconfig.e.h + "outbook/order?").append("bid=").append(j);
        readerProtocolJSONTask.setUrl(stringBuffer.toString());
        com.qq.reader.common.readertask.g.a().a(readerProtocolJSONTask);
    }

    private void a(int i, int i2) {
        if (i2 == 0) {
            d(true);
            this.v.setText(i + "%");
            return;
        }
        e(true);
        this.w.setText(i + "%");
    }

    private void a(DownloadBookTask downloadBookTask, boolean z) {
        if (this.C != null) {
            this.C.c(downloadBookTask);
        }
        if (z) {
            d(false);
            this.v.setText("继续下载");
            return;
        }
        e(false);
        this.w.setText("继续下载");
    }

    private void b(DownloadBookTask downloadBookTask, boolean z) {
        if (this.C != null) {
            this.C.e(downloadBookTask);
        }
        if (z) {
            d(false);
            this.v.setText("暂停");
            return;
        }
        e(false);
        this.w.setText("暂停");
    }

    private void v() {
        this.u.u();
        this.mLoginNextTask = this.u.w();
    }

    private void w() {
        if (!E()) {
            return;
        }
        if (this.u.p()) {
            this.u.v();
            this.mLoginNextTask = this.u.w();
            if (com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.u.d())) == null) {
                this.x.setText(R.string.bookinfo_add2bookshelf_ok);
                c(true);
                return;
            }
            this.x.setText(R.string.bookinfo_add2bookshelf_already);
            c(false);
            return;
        }
        af.a(this.o, this.o.getResources().getString(R.string.bookinfo_client_needupdate, new Object[]{getString(R.string.app_name)}), 0).a();
    }

    private void x() {
        this.u.b(false);
        w();
    }

    private void a(String str, String str2, String str3) {
        com.qq.reader.cservice.download.book.g gVar = new com.qq.reader.cservice.download.book.g(str);
        gVar.e(str2);
        gVar.f(str3);
        com.qq.reader.cservice.download.book.h hVar = new com.qq.reader.cservice.download.book.h(getApplicationContext(), gVar);
        hVar.a(this);
        z();
        hVar.start();
    }

    private boolean y() {
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

    private void z() {
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

    private void A() {
        try {
            if (this.y == null || !this.y.isShowing()) {
                this.y = ProgressDialog.show(this, "", "正在购买，请稍候...", true);
                this.y.setCanceledOnTouchOutside(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean B() {
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
                        this.Y = 100;
                    } else {
                        this.Y = 0;
                    }
                }
                a(alertDialog, (Activity) this);
                break;
            case 607:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity a;

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
                    final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.b = true;
                        this.a.f();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.a(-1, (int) R.drawable.buy_book_dialog_confirm_bg);
                break;
            case ErrorCode.STATIC_TBS_INSTALL_SMART_INSTALL_TBS_FINAL_EXCEPTION /*610*/:
                b(alertDialog, (Activity) this);
                break;
        }
        return alertDialog != null ? alertDialog : super.createDialog(i, bundle);
    }

    public void g() {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.a.S.a(aVar);
                Message obtain = Message.obtain();
                obtain.what = 400008;
                this.a.mHandler.sendMessage(obtain);
            }

            public void a() {
            }
        }, String.valueOf(this.u.d()), 0));
    }

    public void a(final AlertDialog alertDialog) {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity b;

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.b.S.a(aVar);
                Message obtainMessage = this.b.mHandler.obtainMessage(8000011);
                obtainMessage.obj = alertDialog;
                this.b.mHandler.sendMessage(obtainMessage);
            }

            public void a() {
            }
        }, String.valueOf(this.u.d()), 0));
    }

    public void b(final AlertDialog alertDialog) {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity b;

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.b.S.a(aVar);
                Message obtainMessage = this.b.mHandler.obtainMessage(8000012);
                obtainMessage.obj = alertDialog;
                this.b.mHandler.sendMessage(obtainMessage);
            }

            public void a() {
            }
        }, String.valueOf(this.u.d()), 0));
    }

    public void c(final AlertDialog alertDialog) {
        if (alertDialog != null && alertDialog.isShowing()) {
            TextView textView = (TextView) alertDialog.findViewById(R.id.tv_user_balance);
            if (textView != null) {
                textView.setText(this.S.b());
            }
            if (this.S.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.S.g);
            }
            ProgressBar progressBar = (ProgressBar) alertDialog.findViewById(R.id.pb_user_balance);
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
            Button c = alertDialog.c(-1);
            Button c2 = alertDialog.c(-2);
            if (c != null && c2 != null) {
                int e = (this.u.e() * this.u.g()) / 100;
                int f = this.u.f();
                int i = this.u.i();
                if (i <= 0 || i >= e) {
                    i = e;
                }
                if (f <= 0 || i >= f) {
                    f = i;
                }
                i = this.S.a();
                if (i < 0 || i >= f) {
                    c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeBookStoreConfigDetailActivity b;

                        public void onClick(View view) {
                            alertDialog.c();
                            this.b.i();
                        }
                    });
                    f = R.string.alert_dialog_buy_confirm;
                    if ("19200".equals(this.u.l())) {
                        f = R.string.alert_dialog_subscription_confirm;
                    }
                    c.setText(getString(f));
                    c2.setVisibility(0);
                    c2.setText(getString(R.string.alert_dialog_cancel));
                    c2.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeBookStoreConfigDetailActivity b;

                        public void onClick(View view) {
                            alertDialog.c();
                        }
                    });
                    return;
                }
                this.a = f;
                if (!ao.s(this.S.e)) {
                    View findViewById;
                    if (getResources().getConfiguration().orientation == 2) {
                        findViewById = alertDialog.findViewById(R.id.activity_info_land);
                        textView = (TextView) alertDialog.findViewById(R.id.activity_text_land);
                    } else {
                        findViewById = alertDialog.findViewById(R.id.activity_info);
                        textView = (TextView) alertDialog.findViewById(R.id.activity_text);
                    }
                    findViewById.setVisibility(0);
                    textView.setText(this.S.e);
                    textView.setVisibility(0);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "4");
                    i.a("event_A202", hashMap, ReaderApplication.getApplicationImp());
                }
                c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity b;

                    public void onClick(View view) {
                        alertDialog.c();
                        this.b.b = true;
                        this.b.f();
                    }
                });
                f = R.string.alert_dialog_buy_balance_insufficient;
                if ("19200".equals(this.u.l())) {
                    f = R.string.alert_dialog_subscription_balance_insufficient;
                }
                c.setText(getString(f));
                c2.setVisibility(8);
            }
        }
    }

    private void a(final AlertDialog alertDialog, Activity activity) {
        int i;
        CharSequence charSequence;
        View inflate = LayoutInflater.from(activity).inflate(R.layout.book_buy_view_new, null);
        TextView textView = (TextView) inflate.findViewById(R.id.book_discount_msg);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_origin_price);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.pb_user_balance);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_price);
        String k = this.u.k();
        int e = this.u.e();
        int g = this.u.g();
        String h = this.u.h();
        int i2 = this.u.i();
        Object j = this.u.j();
        int f = this.u.f();
        if (g < 100 || i2 > 0 || f > 0) {
            g = (g * e) / 100;
            if (i2 <= 0 || i2 >= g) {
                i2 = g;
            } else {
                h = j;
            }
            Object obj = null;
            if (f <= 0 || ((i2 <= 0 || f >= i2) && i2 != 0)) {
                String str = h;
                i = i2;
            } else {
                obj = 1;
                i = f;
            }
            if (TextUtils.isEmpty(j)) {
                textView.setVisibility(8);
            } else {
                textView.setText("(" + j + ")");
                textView.setVisibility(0);
            }
            textView3.setText(String.valueOf(i));
            if (obj != null) {
                textView2.setVisibility(8);
            } else {
                Object obj2 = String.valueOf(e) + "书币";
                CharSequence spannableString = new SpannableString(obj2);
                spannableString.setSpan(new StrikethroughSpan(), 0, obj2.length(), 33);
                textView2.setText(spannableString);
            }
        } else {
            textView.setVisibility(8);
            textView2.setVisibility(8);
            textView3.setText(String.valueOf(e));
            i = e;
        }
        textView = (TextView) inflate.findViewById(R.id.book_name);
        textView.setText(String.format(getResources().getString(R.string.buy_book_name), new Object[]{k}));
        int a = this.S.a();
        String b = this.S.b();
        if (a < 0) {
            progressBar.setVisibility(0);
            charSequence = "";
        } else {
            progressBar.setVisibility(8);
            Object obj3 = b;
        }
        textView = (TextView) inflate.findViewById(R.id.tv_user_balance);
        textView.setText(charSequence);
        if (this.S.d > 0) {
            com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            }, new OnDismissListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            }, this.S.g);
        }
        alertDialog.a(inflate);
        if ("19200".equals(this.u.l())) {
            alertDialog.setTitle(getString(R.string.alert_dialog_subscription));
        } else {
            alertDialog.setTitle(getString(R.string.alert_dialog_buy));
        }
        int i3;
        if (a < 0 || a >= i) {
            i3 = R.string.alert_dialog_buy_confirm;
            if ("19200".equals(this.u.l())) {
                i3 = R.string.alert_dialog_subscription_confirm;
            }
            alertDialog.a(i3, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                    this.b.i();
                }
            });
            alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                }
            });
        } else {
            this.a = i;
            i3 = R.string.alert_dialog_buy_balance_insufficient;
            if ("19200".equals(this.u.l())) {
                i3 = R.string.alert_dialog_subscription_balance_insufficient;
            }
            alertDialog.a(i3, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                    this.b.b = true;
                    this.b.f();
                }
            });
        }
        alertDialog.a(-1, (int) R.drawable.selector_orange_button);
        alertDialog.a(-2, (int) R.drawable.selector_white_button);
        a(alertDialog);
    }

    public void d(final AlertDialog alertDialog) {
        if (alertDialog != null && alertDialog.isShowing()) {
            TextView textView = (TextView) alertDialog.findViewById(R.id.tv_user_balance);
            if (textView != null) {
                textView.setText(this.S.b());
            }
            if (this.S.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.S.g);
            }
            ProgressBar progressBar = (ProgressBar) alertDialog.findViewById(R.id.pb_user_balance);
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
            final Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, "2");
            Button c = alertDialog.c(-1);
            Button c2 = alertDialog.c(-2);
            if (c != null && c2 != null) {
                int i = this.T;
                int a = this.S.a();
                if (a < 0 || a >= i) {
                    c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeBookStoreConfigDetailActivity c;

                        public void onClick(View view) {
                            alertDialog.c();
                            this.c.C();
                            i.a("event_C224", hashMap, ReaderApplication.getApplicationImp());
                        }
                    });
                    c.setText(getString(R.string.alert_dialog_rent_confirm));
                    c2.setVisibility(0);
                    c2.setText(getString(R.string.alert_dialog_cancel));
                    c2.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeBookStoreConfigDetailActivity c;

                        public void onClick(View view) {
                            alertDialog.c();
                            i.a("event_C225", hashMap, ReaderApplication.getApplicationImp());
                        }
                    });
                    return;
                }
                this.a = i;
                c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity c;

                    public void onClick(View view) {
                        alertDialog.c();
                        this.c.c = true;
                        this.c.f();
                        i.a("event_C227", hashMap, ReaderApplication.getApplicationImp());
                    }
                });
                c.setText(getString(R.string.alert_dialog_buy_balance_insufficient));
                c2.setVisibility(8);
                i.a("event_C226", hashMap, ReaderApplication.getApplicationImp());
            }
        }
    }

    private void b(final AlertDialog alertDialog, Activity activity) {
        final Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, "2");
        i.a("event_C223", hashMap, ReaderApplication.getApplicationImp());
        View inflate = LayoutInflater.from(activity).inflate(R.layout.book_rent_view, null);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.pb_user_balance);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_rent_price);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_rent_day);
        String k = this.u.k();
        int i = this.T;
        int i2 = this.U;
        textView.setText(String.valueOf(i));
        textView2.setText(String.valueOf(i2));
        textView = (TextView) inflate.findViewById(R.id.book_name);
        textView.setText(String.format(getResources().getString(R.string.buy_book_name), new Object[]{k}));
        int a = this.S.a();
        CharSequence b = this.S.b();
        if (a < 0) {
            b = "";
            progressBar.setVisibility(0);
        } else {
            progressBar.setVisibility(8);
        }
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_user_balance);
        textView3.setText(b);
        if (this.S.d > 0) {
            com.qq.reader.common.charge.voucher.b.a(this, textView3, new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            }, new OnDismissListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            }, this.S.g);
        }
        alertDialog.a(inflate);
        alertDialog.setTitle(getString(R.string.alert_dialog_rent));
        if (a < 0 || a >= i) {
            alertDialog.a((int) R.string.alert_dialog_rent_confirm, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                    this.c.C();
                    i.a("event_C224", hashMap, ReaderApplication.getApplicationImp());
                }
            });
            alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                    i.a("event_C225", hashMap, ReaderApplication.getApplicationImp());
                }
            });
        } else {
            this.a = i;
            alertDialog.a((int) R.string.alert_dialog_buy_balance_insufficient, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                    this.c.c = true;
                    this.c.f();
                    i.a("event_C227", hashMap, ReaderApplication.getApplicationImp());
                }
            });
            i.a("event_C226", hashMap, ReaderApplication.getApplicationImp());
        }
        alertDialog.a(-1, (int) R.drawable.buy_book_dialog_confirm_bg);
        b(alertDialog);
    }

    private void C() {
        com.qq.reader.common.readertask.g.a().a(new RentBookTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    com.qq.reader.common.monitor.f.a("RentBookTask", str);
                    int optInt = jSONObject.optInt("code", 0);
                    final String optString = jSONObject.optString(SocialConstants.PARAM_SEND_MSG, "");
                    if (optInt == 0) {
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass31 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                af.a(this.a.a, (CharSequence) "租书成功", 0).a();
                                this.a.a.x();
                            }
                        });
                        this.a.mHandler.sendEmptyMessageDelayed(500007, 2000);
                        return;
                    }
                    this.a.mHandler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass31 b;

                        public void run() {
                            af.a(this.b.a, optString, 0).a();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    com.qq.reader.common.monitor.f.a("RentBookTask", e.getMessage());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass31 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        af.a(this.a.a, (CharSequence) "网络链接失败！", 0).a();
                    }
                });
            }
        }, this.u.d()));
    }

    public void doFunction(Bundle bundle) {
        String string = bundle.getString("KEY_ACTION");
        Intent intent = new Intent();
        if ("detail_2_topic_main".equals(string)) {
            com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if ("detail_2_chapter".equals(string)) {
            try {
                if (E()) {
                    new JSBookDir(this).dir(((com.qq.reader.module.bookstore.qnative.page.impl.f) this.j).x().optJSONObject("book").toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                com.qq.reader.common.monitor.debug.c.a("LBStoreConfigDetailActivity", "error " + e);
            }
        } else if ("detail_2_topic".equals(string)) {
            this.D = bundle.getInt("DETAIL_COMMENT_INDEX_COUNT", this.E);
            if (com.qq.reader.common.login.c.b()) {
                F();
                return;
            }
            setLoginNextTask(new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass32 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.F();
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
                string = "comment.html?bid=" + String.valueOf(((com.qq.reader.module.bookstore.qnative.page.impl.f) this.j).x().optJSONObject("book").optInt("id"));
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
                StatisticsManager.a().a(this.B).a(6).c();
                return;
            }
            this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            com.qq.reader.common.readertask.g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                                final /* synthetic */ AnonymousClass33 a;

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
                                                        StatisticsManager.a().a(this.a.a.a.B).a(6).c();
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
        } else if ("rent_book".equals(string)) {
            this.T = bundle.getInt("rent_price");
            this.U = bundle.getInt("rent_days");
            G();
        } else if (!"buy_one_price".equals(string)) {
        } else {
            if (com.qq.reader.common.login.c.b()) {
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("onlybuy", true);
                showFragmentDialog(606, bundle2);
                return;
            }
            setLoginNextTask(new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass35 a;

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
        if (D()) {
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

    private boolean D() {
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.getInt("com.qq.reader.WebContent.from", 0) != 1) {
            return false;
        }
        return true;
    }

    public void h_() {
        t();
        r.a().a(0, String.valueOf(this.u.d()), this.u.k());
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
                new com.qq.reader.common.emotion.a(this, this.mHandler, ((com.qq.reader.module.bookstore.qnative.page.impl.f) this.j).x().optJSONObject("book").optString("title", null)) {
                    final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                    public void a(String str, String str2) {
                    }
                }.a(intent);
            }
        } else if (i == Constants.ERRORCODE_UNKNOWN) {
            o();
        }
        super.onActivityResult(i, i2, intent);
    }

    private boolean E() {
        if (this.u == null || this.u.x()) {
            return true;
        }
        Dialog a = new AlertDialog.a(this).a((CharSequence) "提示").b((CharSequence) "本书需要升级到最新版本才能享受精排版阅读体验").a((CharSequence) "升级", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

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

    private void F() {
        Intent intent = new Intent();
        intent.setClass(this, CommitCommentActivity.class);
        intent.putExtras(this.B);
        startActivityForResult(intent, 1002);
    }

    public void e_() {
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.H.setRefreshing(false);
            }
        }, 1000);
    }

    private boolean a(boolean z) {
        boolean equals;
        DownloadBookTask a = this.C.a(this.u.d());
        if (a != null && a.getState() == TaskStateEnum.Paused) {
            equals = "trial".equals(a.getBookFormat());
            if (z == equals) {
                if (a.getIsOnlyDownLoadIcon()) {
                    a.setIsOnlyDownLoadIcon(false);
                }
                b(a, equals);
                return true;
            }
        }
        if (a != null && (a.getState() == TaskStateEnum.Started || a.getState() == TaskStateEnum.DeactiveStarted)) {
            equals = "trial".equals(a.getBookFormat());
            if (z == equals) {
                a(a, equals);
                return true;
            }
        }
        return false;
    }

    private void G() {
        if (com.qq.reader.common.login.c.b()) {
            showFragmentDialog(ErrorCode.STATIC_TBS_INSTALL_SMART_INSTALL_TBS_FINAL_EXCEPTION);
            return;
        }
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        com.qq.reader.common.readertask.g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                            final /* synthetic */ AnonymousClass40 a;

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
                        com.qq.reader.common.readertask.g.a().a(new RentBookQueryTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                            final /* synthetic */ AnonymousClass40 a;

                            {
                                this.a = r1;
                            }

                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                try {
                                    com.qq.reader.common.monitor.f.a("RentBookQueryTask", str);
                                    JSONObject jSONObject = new JSONObject(str);
                                    boolean optBoolean = jSONObject.optBoolean("isRentBook", false);
                                    boolean optBoolean2 = jSONObject.optBoolean("isRend", false);
                                    jSONObject = jSONObject.optJSONObject("detailmsg");
                                    if (jSONObject.optInt("price", 0) != 0) {
                                        this.a.a.T = jSONObject.optInt("price", 0);
                                    }
                                    if (jSONObject.optInt("day", 0) != 0) {
                                        this.a.a.U = jSONObject.optInt("day", 0);
                                    }
                                    if (optBoolean && !optBoolean2) {
                                        this.a.a.mHandler.postDelayed(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass2 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                this.a.a.a.showFragmentDialog(ErrorCode.STATIC_TBS_INSTALL_SMART_INSTALL_TBS_FINAL_EXCEPTION);
                                            }
                                        }, 200);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                            }
                        }, this.a.u.d()));
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }

    private void a(String str) {
        if (com.qq.reader.common.login.c.b()) {
            b(str);
            return;
        }
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ NativeBookStoreConfigDetailActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        com.qq.reader.common.readertask.g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                            final /* synthetic */ AnonymousClass41 a;

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
        if (this.ad == null) {
            this.ad = new h(this);
            this.ad.a((Activity) this);
            this.ad.a(new h.a(this) {
                final /* synthetic */ NativeBookStoreConfigDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(String str, boolean z) {
                    try {
                        com.qq.reader.common.monitor.f.d("readerpage", "OnDialogClose " + str);
                        this.a.ad.dismiss();
                        this.a.ad = null;
                        if (z) {
                            this.a.o();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        this.ad.a(str2);
        this.ad.f_();
    }

    private void b(boolean z) {
        this.K.setEnabled(z);
        this.N.setEnabled(z);
        this.w.setEnabled(z);
    }

    private void c(boolean z) {
        this.M.setEnabled(z);
        this.P.setEnabled(z);
        this.x.setEnabled(z);
    }

    private void d(boolean z) {
        if (z) {
            this.J.setVisibility(0);
            this.O.setVisibility(8);
            return;
        }
        this.J.setVisibility(8);
        this.O.setVisibility(0);
    }

    private void e(boolean z) {
        if (z) {
            this.I.setVisibility(0);
            this.N.setVisibility(8);
            return;
        }
        this.I.setVisibility(8);
        this.N.setVisibility(0);
    }

    public void i() {
        int a = this.S.a();
        if (a > 0) {
            if (this.a <= 0 || a >= this.a) {
                com.qq.reader.cservice.buy.a.d dVar = new com.qq.reader.cservice.buy.a.d(this.o, String.valueOf(this.u.d()));
                dVar.a(this.u.c());
                dVar.a((b) this);
                dVar.start();
                A();
            }
        }
    }
}
