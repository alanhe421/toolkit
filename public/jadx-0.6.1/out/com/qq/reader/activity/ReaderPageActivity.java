package com.qq.reader.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.iflytek.speech.UtilityConfig;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.NewChapterViewActivity.TabViewBookInfo;
import com.qq.reader.common.db.handle.o;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.download.task.m;
import com.qq.reader.common.download.task.n;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.h;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineFile;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.readertask.protocol.QueryBookIntroTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.readertask.protocol.RentBookQueryTask;
import com.qq.reader.common.readertask.protocol.RentBookTask;
import com.qq.reader.common.readertask.protocol.VoteTypeQueryTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ac;
import com.qq.reader.common.utils.aj;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.t;
import com.qq.reader.common.web.js.JSDownLoad;
import com.qq.reader.common.web.js.JSLogin;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.download.book.f;
import com.qq.reader.cservice.onlineread.OnlineChapterDownloadTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.cservice.onlineread.d;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.framework.mark.UserMark;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreEndPageActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qweb.fragment.ReaderPagerChapterFragment;
import com.qq.reader.module.readpage.AddLimitFreeBook2DBTask;
import com.qq.reader.module.readpage.PageFooter;
import com.qq.reader.module.readpage.ReaderPageSwither;
import com.qq.reader.module.readpage.ReaderPageSwither$e;
import com.qq.reader.module.readpage.ReaderTextPageView;
import com.qq.reader.module.readpage.h$c;
import com.qq.reader.module.readpage.r;
import com.qq.reader.module.readpage.voteview.RoundBorderDrawable;
import com.qq.reader.module.readpage.w;
import com.qq.reader.module.readpage.x;
import com.qq.reader.plugin.PlugInDefaultActivity;
import com.qq.reader.plugin.PlugInFontsActivity;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.model.BookTxt;
import com.qq.reader.readengine.model.BookUmd;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.AudioFloatingWindowView;
import com.qq.reader.view.BookmarkView;
import com.qq.reader.view.FlipContainerLayout;
import com.qq.reader.view.HelpScrollLayout;
import com.qq.reader.view.PageAdvertismentView;
import com.qq.reader.view.PageHeader;
import com.qq.reader.view.ReaderEndPager;
import com.qq.reader.view.ab;
import com.qq.reader.view.ad;
import com.qq.reader.view.ae;
import com.qq.reader.view.ae$b;
import com.qq.reader.view.af;
import com.qq.reader.view.ai;
import com.qq.reader.view.an;
import com.qq.reader.view.an$b;
import com.qq.reader.view.animation.AnimationProvider;
import com.qq.reader.view.animation.AnimationProvider.Mode;
import com.qq.reader.view.ap;
import com.qq.reader.view.aq;
import com.qq.reader.view.ar;
import com.qq.reader.view.ar$a;
import com.qq.reader.view.as;
import com.qq.reader.view.at;
import com.qq.reader.view.ay;
import com.qq.reader.view.p;
import com.qq.reader.view.q;
import com.qq.reader.view.web.e;
import com.qq.reader.view.web.k;
import com.qq.reader.view.web.l;
import com.qq.reader.view.y;
import com.qq.reader.view.z;
import com.qq.reader.widget.ReaderWidget;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import format.epub.common.book.BookEPub;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class ReaderPageActivity extends ReaderBaseActivity implements ServiceConnection, SensorEventListener, com.qq.reader.common.drm.a.a, com.qq.reader.common.utils.e.a, com.qq.reader.cservice.buy.a.b, f, ReaderPageSwither$e, h$c, ae$b, an$b, ar$a, com.qq.reader.view.b.b.a, e, com.qq.reader.view.y.a {
    boolean A = false;
    private final String B = "ReaderPage";
    private com.qq.reader.cservice.onlineread.e C;
    private int D = -1;
    private ab E;
    private PageFooter F;
    private volatile com.qq.reader.readengine.fileparse.e G;
    private HelpScrollLayout H;
    private ImageView I;
    private IBook J;
    private BookmarkView K;
    private int L;
    private int M;
    private PageHeader N;
    private boolean O = true;
    private boolean P = false;
    private boolean Q = false;
    private ad R;
    private com.qq.reader.view.ay.a S;
    private ay T;
    private an U;
    private ai V;
    private p W;
    private Mark X = null;
    private com.qq.reader.view.e Y;
    private com.qq.reader.view.c Z;
    public final int a = 0;
    private int aA = 0;
    private int aB = 0;
    private boolean aC;
    private int aD = -1;
    private final int aE = 10001;
    private final int aF = Constants.CODE_LOGIC_REGISTER_IN_PROCESS;
    private FlipContainerLayout aG;
    private ReaderEndPager aH;
    private ImageView aI;
    private boolean aJ;
    private boolean aK = true;
    private BroadcastReceiver aL;
    private com.qq.reader.module.readpage.c aM;
    private com.qq.reader.cservice.download.book.e aN = null;
    private k aO = null;
    private final com.qq.reader.common.charge.voucher.a.a aP = new com.qq.reader.common.charge.voucher.a.a();
    private int aQ;
    private boolean aR;
    private String aS;
    private r aT;
    private AlertDialog aU;
    private boolean aV;
    private ImageView aW;
    private f aX;
    private l aY;
    private com.qq.reader.module.rookie.presenter.a.a aZ;
    private com.qq.reader.view.c aa;
    private ProgressDialog ab;
    private com.qq.reader.cservice.onlineread.c ac;
    private q ad;
    private z ae;
    private int af = 0;
    private BroadcastReceiver ag;
    private BroadcastReceiver ah;
    private IntentFilter ai;
    private int aj = 0;
    private int ak = 0;
    private final int al = 0;
    private final int am = 1;
    private final int an = 2;
    private boolean ao = false;
    private boolean ap = false;
    private PageAdvertismentView aq;
    private Timer ar;
    private Timer as;
    private int at;
    private int au;
    private int av;
    private aq aw;
    private com.qq.reader.view.b.b ax;
    private g ay;
    private com.qq.reader.module.bookchapter.a.a az;
    public final int b = 1;
    private boolean ba = false;
    private com.qq.reader.common.c.c.a bb;
    private long bc = 0;
    private o bd = null;
    private m be = new m(this) {
        final /* synthetic */ ReaderPageActivity a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            int i = 0;
            TaskStateEnum c = nVar.c();
            TaskStateEnum a = nVar.a();
            DownloadBookTask downloadBookTask = (DownloadBookTask) nVar.d();
            if (downloadBookTask == null) {
                return;
            }
            if (this.a.X == null || downloadBookTask.getId() == this.a.X.getBookId()) {
                boolean equals = "trial".equals(downloadBookTask.getBookFormat());
                if (c == TaskStateEnum.Started) {
                    int currentSize;
                    if (downloadBookTask.getSize() > 0) {
                        currentSize = (int) ((((float) downloadBookTask.getCurrentSize()) * 100.0f) / ((float) downloadBookTask.getSize()));
                    } else {
                        currentSize = 0;
                    }
                    if (this.a.X != null && (this.a.X instanceof DownloadMark)) {
                        Message obtain = Message.obtain();
                        obtain.what = 8001;
                        obtain.arg1 = 1;
                        obtain.obj = Integer.valueOf(currentSize);
                        this.a.mHandler.sendMessage(obtain);
                    }
                } else if (c == TaskStateEnum.Installing && a != TaskStateEnum.Installing) {
                    Message obtain2 = Message.obtain();
                    obtain2.what = 8003;
                    if (equals) {
                        i = 1;
                    }
                    obtain2.arg1 = i;
                    obtain2.obj = Long.valueOf(downloadBookTask.getId());
                    this.a.mHandler.sendMessage(obtain2);
                } else if ((c == TaskStateEnum.Failed && a != TaskStateEnum.Failed) || ((c == TaskStateEnum.DeactivePrepared && a != TaskStateEnum.DeactivePrepared) || (c == TaskStateEnum.DeactiveStarted && a != TaskStateEnum.DeactiveStarted))) {
                    Message obtain3 = Message.obtain();
                    obtain3.what = 8018;
                    obtain3.arg1 = 1;
                    this.a.mHandler.sendMessage(obtain3);
                }
            }
        }
    };
    private boolean bf = false;
    private af bg = null;
    private boolean bh = false;
    private com.qq.reader.view.g bi;
    private ae bj;
    private boolean bk;
    private y bl;
    private boolean bm = false;
    private BroadcastReceiver bn = new BroadcastReceiver(this) {
        final /* synthetic */ ReaderPageActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("BROADCAST_ACTION_TTS_START".equals(action)) {
                if (this.a.au()) {
                    this.a.n(1);
                } else {
                    this.a.n(0);
                }
                this.a.av();
            } else if ("BROADCAST_ACTION_TTS_PAUSE".equals(action)) {
                com.qq.reader.plugin.tts.n.f().o();
            } else if ("BROADCAST_ACTION_TTS_RESUME".equals(action)) {
                com.qq.reader.plugin.tts.n.f().p();
            } else if ("BROADCAST_ACTION_TTS_STOP".equals(action)) {
                this.a.O();
            } else if ("BROADCAST_ACTION_TTS_SWITCH_TTS".equals(action)) {
                this.a.h(intent.getIntExtra("BROADCAST_EXTRA_TTS_TYPE", 1));
            } else if ("BROADCAST_ACTION_TTS_SWITCH_CHAPTER".equals(action)) {
                this.a.a(intent);
            } else if (com.qq.reader.plugin.audiobook.core.e.E.equals(action) && this.a.C != null && this.a.C.g() != null) {
                this.a.C.g().c(intent.getBooleanExtra("auto_pay", false));
            }
        }
    };
    private BroadcastReceiver bo = new BroadcastReceiver(this) {
        final /* synthetic */ ReaderPageActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.qq.reader.chapter.DownloadSucess".equalsIgnoreCase(action)) {
                this.a.getHandler().obtainMessage(21007).sendToTarget();
            } else if ("com.qq.reader.chapter.DownloadFailed".equalsIgnoreCase(action)) {
                this.a.getHandler().obtainMessage(21008).sendToTarget();
            }
        }
    };
    private com.qq.reader.view.web.c bp;
    private Mark[] bq = null;
    private ar br;
    private com.qq.reader.view.c bs;
    private com.qq.reader.readengine.model.IBook.a bt;
    public final int c = 2;
    public final int d = 3;
    public final int e = 4;
    public final int f = 5;
    public final int g = 6;
    public final int h = 7;
    public final int i = 8;
    public final int j = 9;
    public final int k = 10;
    public final int l = 11;
    public final int m = 12;
    @Deprecated
    public final int n = 13;
    public final int o = 14;
    public boolean p = false;
    public ReaderPageSwither q = null;
    volatile boolean r = false;
    Sensor s = null;
    public String t = "CLOUD_SYN_TASK_RESULT_BOOKID";
    public String u = "CLOUD_SYN_TASK_RESULT_CHAPTERID";
    public String v = "CLOUD_SYN_TASK_RESULT_OFFSET";
    at w;
    as x;
    int y = 0;
    boolean z = false;

    private class a extends TimerTask {
        final /* synthetic */ ReaderPageActivity a;

        private a(ReaderPageActivity readerPageActivity) {
            this.a = readerPageActivity;
        }

        public void run() {
            Message obtainMessage = this.a.mHandler.obtainMessage();
            obtainMessage.what = 1209;
            this.a.mHandler.sendMessage(obtainMessage);
        }
    }

    private class b {
        boolean a;
        boolean b;
        final /* synthetic */ ReaderPageActivity c;

        private b(ReaderPageActivity readerPageActivity) {
            this.c = readerPageActivity;
        }
    }

    private class c extends TimerTask {
        final /* synthetic */ ReaderPageActivity a;

        private c(ReaderPageActivity readerPageActivity) {
            this.a = readerPageActivity;
        }

        public void run() {
            List b = com.qq.reader.cservice.adv.b.a(this.a.getApplicationContext()).b("100101");
            if (b != null && b.size() > 0) {
                com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
                if (aVar != null) {
                    aVar.a(0);
                    com.qq.reader.cservice.adv.b.a(this.a.getApplicationContext()).d(aVar);
                    Message obtainMessage = this.a.mHandler.obtainMessage();
                    obtainMessage.obj = aVar;
                    obtainMessage.what = 1208;
                    this.a.mHandler.sendMessage(obtainMessage);
                    this.a.as.schedule(new a(), com.tencent.qalsdk.base.a.ap);
                }
            }
        }
    }

    private void aN() {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1439)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1461)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r6 = this;
        r1 = 1;
        r2 = 0;
        r0 = r6.getContext();
        r0 = r0.getApplicationContext();
        r0 = com.qq.reader.appconfig.a.d.Y(r0);
        if (r0 != 0) goto L_0x0059;
    L_0x0010:
        r0 = r1;
    L_0x0011:
        r3 = r6.getContext();
        r3 = r3.getApplicationContext();
        r3 = com.qq.reader.appconfig.a.d.ag(r3);
        r4 = r6.q;
        r4 = r4.getTopPage();
        r4 = r4.getWidth();
        r5 = r6.q;
        r5 = r5.getTopPage();
        r5 = r5.getHeight();
        if (r0 == 0) goto L_0x0044;
    L_0x0033:
        if (r3 == 0) goto L_0x0044;
    L_0x0035:
        r0 = r6.q;
        r0 = r0.getTopPage();
        r0 = r0.getPagePaint();
        r3 = r4 / 2;
        r0.a(r3, r5);
    L_0x0044:
        r0 = r6.G;
        if (r0 != 0) goto L_0x015d;
    L_0x0048:
        r6.a(r2);
        r6.t();
        r0 = r6.X;
        if (r0 == 0) goto L_0x005b;
    L_0x0052:
        r0 = r6.X;
        r0 = r0 instanceof com.qq.reader.framework.mark.DownloadMark;
        if (r0 == 0) goto L_0x005b;
    L_0x0058:
        return;
    L_0x0059:
        r0 = r2;
        goto L_0x0011;
    L_0x005b:
        r0 = r6.J;
        if (r0 == 0) goto L_0x0076;
    L_0x005f:
        r0 = r6.J;
        r0 = r0.getBookNetId();
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0076;
    L_0x006b:
        r0 = r6.aH;
        if (r0 == 0) goto L_0x0076;
    L_0x006f:
        r0 = r6.aH;
        r1 = r6.J;
        r0.a(r1);
    L_0x0076:
        r0 = r6.O;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 == 0) goto L_0x011e;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x007a:
        r0 = r6.Q;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 == 0) goto L_0x0096;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x007e:
        r0 = 400; // 0x190 float:5.6E-43 double:1.976E-321;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.showFragmentDialog(r0);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x0083:
        r0 = com.qq.reader.common.monitor.h.a();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0.h();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r6.O;
        if (r0 != 0) goto L_0x0058;
    L_0x008e:
        r0 = com.qq.reader.common.monitor.h.a();
        r0.f();
        goto L_0x0058;
    L_0x0096:
        r0 = r6.aR;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 != 0) goto L_0x00d9;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x009a:
        r0 = r6.P;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 != 0) goto L_0x00d9;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x009e:
        r0 = r6.q;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r1 = r6.G;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0.setText(r1);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r6.X;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 == 0) goto L_0x00ba;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x00a9:
        r0 = r6.X;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r0.getStarPointStr();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 != 0) goto L_0x00ba;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x00b1:
        r0 = r6.q;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r0.getBookCore();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0.p();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x00ba:
        r0 = r6.q;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r0.getTopPage();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r0.getmPageCache();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0.g();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r6.q;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r0.getTopPage();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r0.h();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 == 0) goto L_0x00d6;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x00d3:
        r6.aO();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x00d6:
        r0 = 1;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.P = r0;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x00d9:
        r0 = 1;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.P = r0;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r6.J;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.a(r0);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        goto L_0x0083;
    L_0x00e2:
        r0 = move-exception;
        r1 = "onWindowFocusChanged";	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r2 = "error";	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        com.qq.reader.common.monitor.f.a(r1, r2, r0);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        com.qq.reader.common.monitor.b.a(r0);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = 0;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.O = r0;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = 1005; // 0x3ed float:1.408E-42 double:4.965E-321;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.D = r0;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = new android.os.Bundle;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0.<init>();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r1 = "read_error_type";	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r2 = r6.D;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0.putInt(r1, r2);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r1 = "ReaderPage";	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r2 = "DIALOG_READ_FAILED222222";	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        com.qq.reader.common.monitor.f.a(r1, r2);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r1 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.showFragmentDialog(r1, r0);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = r6.O;
        if (r0 != 0) goto L_0x0058;
    L_0x0115:
        r0 = com.qq.reader.common.monitor.h.a();
        r0.f();
        goto L_0x0058;
    L_0x011e:
        r0 = com.qq.reader.common.utils.am.a();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 != 0) goto L_0x0128;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x0124:
        r0 = 1006; // 0x3ee float:1.41E-42 double:4.97E-321;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.D = r0;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x0128:
        r0 = r6.D;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r1 = 1008; // 0x3f0 float:1.413E-42 double:4.98E-321;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        if (r0 != r1) goto L_0x0140;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
    L_0x012e:
        r6.bj();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        goto L_0x0083;
    L_0x0133:
        r0 = move-exception;
        r1 = r6.O;
        if (r1 != 0) goto L_0x013f;
    L_0x0138:
        r1 = com.qq.reader.common.monitor.h.a();
        r1.f();
    L_0x013f:
        throw r0;
    L_0x0140:
        r0 = "ReaderPage";	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r1 = "DIALOG_READ_FAILED3333333";	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        com.qq.reader.common.monitor.f.a(r0, r1);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0 = new android.os.Bundle;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0.<init>();	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r1 = "read_error_type";	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r2 = r6.D;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r0.putInt(r1, r2);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r1 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        r6.showFragmentDialog(r1, r0);	 Catch:{ Exception -> 0x00e2, all -> 0x0133 }
        goto L_0x0083;
    L_0x015d:
        r0 = r6.G;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0058;
    L_0x0165:
        r0 = r6.p;
        if (r0 == 0) goto L_0x0058;
    L_0x0169:
        r0 = r6.q;
        r0 = r0.getBookCore();
        r0 = r0.o();
        r0 = r0.f();
        r3 = 999; // 0x3e7 float:1.4E-42 double:4.936E-321;
        if (r0 != r3) goto L_0x0058;
    L_0x017b:
        r0 = r6.q;
        r0 = r0.getBookCore();
        r0 = r0.h();
        r3 = r6.aB;
        if (r3 != r1) goto L_0x018d;
    L_0x0189:
        r1 = r6.a(r0, r0);
    L_0x018d:
        if (r1 == 0) goto L_0x019b;
    L_0x018f:
        r6.a(r0, r2, r2, r2);
        r0 = r6.q;
        r0 = r0.getBookCore();
        r0.p();
    L_0x019b:
        r6.p = r2;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.ReaderPageActivity.aN():void");
    }

    public void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        setIsShowNightMask(false);
        setSwipeBackEnable(false);
        this.aN = (com.qq.reader.cservice.download.book.e) com.qq.reader.common.download.task.l.d(1001);
        this.aN.a(TaskStateEnum.values(), this.be);
        this.bd = o.c();
        try {
            W();
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a("readerpage", "readerpage getBookTypeFromIntent exception : " + e.toString());
        }
        h.a().a(this.aA);
        h.a().g();
        this.ap = false;
        d.a = true;
        this.at = com.qq.reader.appconfig.a.d.ao(getApplicationContext()) ? 1024 : 0;
        U();
        if (com.qq.reader.appconfig.a.d.aq(getApplicationContext())) {
            i = 1;
        } else {
            i = 0;
        }
        this.au = i;
        m(com.qq.reader.appconfig.a.d.Y(getApplicationContext()));
        com.qq.reader.common.c.a.a(false);
        this.av = com.qq.reader.appconfig.a.d.af(getApplicationContext());
        a();
        e();
        this.q.setOnAreaClickListener(new com.qq.reader.module.readpage.ReaderPageSwither.b(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public boolean a(View view, MotionEvent motionEvent) {
                if (!this.a.O) {
                    return false;
                }
                if (this.a.q.w()) {
                    return false;
                }
                boolean h = this.a.q.getTopPage().h();
                if (((ReaderPageSwither) view).a((int) (h ? motionEvent.getX() - ((float) (this.a.q.getTopPage().getWidth() / 2)) : motionEvent.getX()), (int) motionEvent.getY())) {
                    if (h) {
                        this.a.q.a(PageIndex.current_left, PageIndex.current_right);
                    }
                    return true;
                } else if (this.a.aJ) {
                    return false;
                } else {
                    switch (com.qq.reader.module.readpage.ab.a(view, motionEvent.getX(), motionEvent.getY())) {
                        case 0:
                            this.a.i();
                            break;
                        case 1:
                            if (!com.qq.reader.appconfig.a.d.an(this.a.getApplicationContext())) {
                                this.a.j();
                                break;
                            }
                            this.a.q.q();
                            break;
                        case 2:
                            this.a.openOptionsMenu();
                            i.a("event_B2", null, this.a);
                            break;
                    }
                    return false;
                }
            }
        });
        this.q.setOnMiddleTouchListener(new com.qq.reader.module.readpage.ReaderPageSwither.c(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i) {
                if (!com.qq.reader.appconfig.a.d.aa(this.a.getApplicationContext())) {
                    ao.a(this.a, i);
                }
                return false;
            }
        });
        this.ag = new BroadcastReceiver(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                    this.a.F.a(intent.getIntExtra("level", 0), intent.getIntExtra("scale", 100));
                }
            }
        };
        V();
        this.ai = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        this.q.getBookCore().a(this.mHandler);
        this.q.getBookCore().o().a(this);
        this.aM = new com.qq.reader.module.readpage.c(getApplicationContext(), this.mHandler, 1);
        setStatPageName("readerpage");
        if (com.qq.reader.appconfig.b.a()) {
            getWindow().setFlags(SigType.WLOGIN_PF, SigType.WLOGIN_PF);
        }
        if (com.qq.reader.module.rookie.presenter.a.a().c) {
            this.mHandler.post(new Runnable(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (com.qq.reader.module.rookie.presenter.a.a().c && this.a.aZ == null) {
                        this.a.aZ = new com.qq.reader.module.rookie.presenter.a.a(this) {
                            final /* synthetic */ AnonymousClass71 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z) {
                                this.a.a.bs();
                                if (z) {
                                    this.a.a.aI();
                                    return;
                                }
                                try {
                                    if (this.a.a.q.getBookCore().o().c().p() != 1009) {
                                        return;
                                    }
                                    if (!com.qq.reader.module.rookie.presenter.a.a().c) {
                                        af.a(ReaderApplication.getApplicationImp(), (CharSequence) "新用户才能领取本福利", 0).a();
                                    } else if (this.a.a.isOnResume) {
                                        boolean b;
                                        if (this.a.a.C.g() != null) {
                                            b = this.a.a.q.getBookCore().o().c().b();
                                        } else {
                                            b = false;
                                        }
                                        com.qq.reader.module.rookie.a.b a = com.qq.reader.module.rookie.presenter.a.a().a("p2", this.a.a.J.getBookNetId(), b);
                                        if (a == null) {
                                            a = com.qq.reader.module.rookie.presenter.a.a().a("p3", this.a.a.J.getBookNetId(), b);
                                        }
                                        if (a == null) {
                                            a = com.qq.reader.module.rookie.presenter.a.a().a("p5", this.a.a.J.getBookNetId(), b);
                                        }
                                        if (a == null) {
                                            af.a(ReaderApplication.getApplicationImp(), (CharSequence) "暂无新用户可领福利", 0).a();
                                        }
                                    }
                                } catch (Exception e) {
                                    com.qq.reader.common.monitor.debug.c.e("ReaderPage giftUpdate", e.getMessage());
                                }
                            }
                        };
                        com.qq.reader.module.rookie.presenter.a.a().a(this.a.aZ);
                    }
                }
            });
        }
        com.qq.reader.common.utils.e.a().a(this);
        onAudioFloatingStateChange(2, com.qq.reader.common.utils.e.a().c(), com.qq.reader.common.utils.e.a().b(), com.qq.reader.common.utils.e.a().d());
    }

    public void openOptionsMenu() {
        Configuration configuration = getResources().getConfiguration();
        if ((configuration.screenLayout & 15) > 3) {
            int i = configuration.screenLayout;
            configuration.screenLayout = 3;
            super.openOptionsMenu();
            configuration.screenLayout = i;
            return;
        }
        super.openOptionsMenu();
    }

    public boolean needSetImmerseMode() {
        return false;
    }

    private void b(boolean z) {
        if (!com.qq.reader.appconfig.a.d.ao(getApplicationContext())) {
            return;
        }
        if (com.qq.reader.appconfig.a.d.aq(getApplicationContext())) {
            aj.c(this, z);
        } else {
            aj.d(this, z);
        }
    }

    private void U() {
        if (!com.qq.reader.appconfig.a.d.ao(getApplicationContext())) {
            aj.d(this);
        } else if (com.qq.reader.appconfig.a.d.aq(getApplicationContext())) {
            aj.c(this, true);
        } else {
            aj.d(this, true);
        }
    }

    private void V() {
        if (com.qq.reader.common.c.a.H) {
            com.qq.reader.common.c.a.H = false;
            this.H = (HelpScrollLayout) findViewById(R.id.helpScrollView);
            this.H.a(new com.qq.reader.view.HelpScrollLayout.a(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                }

                public void a(View view, float f, float f2) {
                    this.a.a(view, f, f2);
                }
            });
            this.I = (ImageView) findViewById(R.id.helpScrollView_imgview);
            this.I.setImageResource(R.drawable.read_info);
            this.H.setVisibility(0);
        }
    }

    private void W() {
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("android.intent.action.VIEW".equals(action) || "android.intent.action.SEND".equals(action)) {
            Uri data = intent.getData();
            if (data == null) {
                data = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
            }
            action = data.getPath();
            if (!(action == null || action.equals(""))) {
                action = action.toLowerCase();
                if (action.endsWith(".teb") || action.endsWith(".epub") || action.endsWith(".trial") || action.endsWith(".qteb")) {
                    this.aA = 1;
                } else if (action.endsWith(".umd")) {
                    this.aA = 4;
                } else {
                    this.aA = 2;
                }
            }
        } else {
            Bundle extras = getIntent().getExtras();
            if (extras.getBoolean("com.qq.reader.fromonline")) {
                this.aA = 2;
            } else {
                action = extras.getString("filepath").toLowerCase();
                if (action.endsWith(".teb") || action.endsWith(".epub") || action.endsWith(".trial") || action.endsWith(".qteb")) {
                    this.aA = 1;
                } else if (action.endsWith(".umd")) {
                    this.aA = 4;
                } else {
                    this.aA = 2;
                }
            }
        }
        if (this.aA == 1) {
            if (com.qq.reader.appconfig.a.d.B(getApplicationContext()) == 1) {
                com.qq.reader.appconfig.a.d.q(getApplicationContext(), 2);
            }
            if (com.qq.reader.appconfig.a.d.Y(getApplicationContext()) == 0) {
                com.qq.reader.appconfig.a.d.l(getContext(), 1);
            }
        }
    }

    public void a() {
        ao.b = com.qq.reader.appconfig.a.d.o(getApplicationContext());
        this.aG = new FlipContainerLayout(this);
        final View inflate = getLayoutInflater().inflate(R.layout.readerpage, null);
        int i = getResources().getDisplayMetrics().widthPixels;
        if (getResources().getConfiguration().orientation == 2 && com.qq.reader.appconfig.a.d.ao(getApplicationContext()) && !com.qq.reader.appconfig.a.d.aq(getApplicationContext())) {
            i += ao.f((Activity) this);
        }
        this.aG.setIsSideFlip(false);
        this.aG.setRightViewFilpEnable(true);
        this.aG.setRightSize(i);
        this.aG.setCurrentView(new com.qq.reader.view.FlipContainerLayout.b(this) {
            final /* synthetic */ ReaderPageActivity b;

            public boolean a() {
                return true;
            }

            public boolean b() {
                return this.b.aK;
            }

            public View c() {
                return inflate;
            }
        });
        this.aG.setFlipListener(new com.qq.reader.view.FlipLayout.a(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public void a(float f) {
            }

            public void b(float f) {
            }

            public void a() {
            }

            public void b() {
                this.a.aH.b();
            }

            public void c(float f) {
            }

            public void c() {
            }

            public void d() {
                this.a.aK = true;
                this.a.aP();
                if (this.a.aT != null) {
                    com.qq.reader.module.readpage.h o = this.a.q.getBookCore().o();
                    if (o.c().p() == 1009) {
                        this.a.aT.a(this.a.J != null ? this.a.J.getBookNetId() : 0, o.c().b());
                        com.qq.reader.module.rookie.presenter.a.a().b(this.a);
                    }
                }
            }

            public void e() {
                if (this.a.aT != null) {
                    this.a.aT.a();
                }
                this.a.ba = true;
            }

            public void f() {
                this.a.ba = false;
            }
        });
        this.aG.setOnTapListener(new com.qq.reader.view.FlipLayout.b(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public boolean a() {
                return !this.a.aK;
            }
        });
        getWindow().getDecorView().post(new Runnable(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.getHandler().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass126 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.aH = new ReaderEndPager(this.a.a);
                        this.a.a.aG.setRightView(this.a.a.aH.a());
                        this.a.a.aH.d();
                    }
                });
            }
        });
        setContentView(this.aG);
        this.q = (ReaderPageSwither) findViewById(R.id.pagetext);
        this.q.d();
        this.q.setFactory(new w(getApplicationContext(), this));
        this.q.setViewMode(com.qq.reader.appconfig.a.d.B(getApplicationContext()));
        this.q.setTurnPageListener(this);
        this.N = (PageHeader) findViewById(R.id.page_header);
        this.F = (PageFooter) findViewById(R.id.pagefooter);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.F.a(getApplicationContext(), displayMetrics.widthPixels, displayMetrics.heightPixels, this.q.getBookCore(), this.N, this.mHandler);
        this.q.setPageChangeListener(this.F);
        this.q.getmPageContext().a(new com.qq.reader.module.readpage.o(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                if (i == 1000) {
                    this.a.X();
                    com.qq.reader.common.monitor.debug.c.a("reader", " EVENT_ACTIVE ");
                    return true;
                }
                if (i == 1001) {
                    com.qq.reader.common.monitor.debug.c.a("reader", " EVENT_ACTIVETEXT_APPEAR ");
                    try {
                        if (this.a.aJ) {
                            return false;
                        }
                        int i2 = (int) bundle.getFloat("x");
                        int i3 = (int) bundle.getFloat("y");
                        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                        layoutParams.gravity = 51;
                        layoutParams.leftMargin = i2;
                        layoutParams.topMargin = i3;
                        this.a.addContentView(this.a.aI, layoutParams);
                        Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                        alphaAnimation.setDuration(500);
                        alphaAnimation.setRepeatCount(-1);
                        alphaAnimation.setRepeatMode(2);
                        this.a.aI.setAnimation(alphaAnimation);
                        alphaAnimation.start();
                        this.a.aJ = true;
                        com.qq.reader.appconfig.a.d.aN(this.a.getApplicationContext());
                    } catch (Throwable th) {
                    }
                } else if (i == 1002) {
                    com.qq.reader.common.monitor.debug.c.a("reader", " EVENT_PAGE_PAINT_CONTEXT_ONCLICK ");
                    this.a.a(bundle);
                }
                return false;
            }
        });
        this.aq = (PageAdvertismentView) findViewById(R.id.push_msg);
        this.aq.a(this);
        if (com.qq.reader.common.c.a.b) {
            this.aI = new ImageView(this);
            this.aI.setImageResource(R.drawable.finger);
        }
    }

    private void a(Bundle bundle) {
        if (bundle != null) {
            if ("READER_PAGE_AUTHOR_WORDS_ICON_ONCLICK".equals(bundle.getString("PARA_READER_PAGE_CONTEXT_ONCLICK_EVENT_TYPE"))) {
                String string = bundle.getString("AUTHORPAGE_KEY_AUTHORID");
                String string2 = bundle.getString("AUTHORPAGE_KEY_AUTHOR_NAME");
                String string3 = bundle.getString("AUTHORPAGE_KEY_AVATAR_URL");
                try {
                    com.qq.reader.qurl.c.a(this, String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{string, string2, string3}), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, "5");
                i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
            }
        }
    }

    private void X() {
        this.aD = 10001;
        i.a("event_B119", null, getApplicationContext());
        f(true);
    }

    private void Y() {
        this.aD = Constants.CODE_LOGIC_REGISTER_IN_PROCESS;
        f(false);
    }

    public void a(boolean z) {
        Map map = null;
        if (!this.P || z) {
            Uri uri;
            Intent intent = getIntent();
            String action = intent.getAction();
            i.a("event_readbook", null, getApplicationContext());
            StatisticsManager.a().a("event_readbook", null);
            Bundle extras = intent.getExtras();
            if (extras != null) {
                try {
                    if (extras.containsKey("android.intent.extra.STREAM")) {
                        uri = (Uri) extras.get("android.intent.extra.STREAM");
                    } else {
                        uri = null;
                    }
                    map = uri;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String path;
            if ("android.intent.action.VIEW".equals(action) || map != null || "android.intent.action.SEND".equals(action)) {
                String a;
                if (map == null) {
                    uri = intent.getData();
                    if (uri == null) {
                        uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                    }
                } else {
                    Object obj = map;
                }
                path = uri.getPath();
                if (uri.toString().startsWith("content://media")) {
                    a = ao.a(uri);
                } else {
                    a = path;
                }
                if (a.startsWith("/root")) {
                    path = a.substring(5, a.length());
                } else {
                    path = a;
                }
                int intExtra = intent.getIntExtra("fileencrypt", 2);
                String stringExtra = intent.getStringExtra("fileid");
                if (!(path == null || path.equals(""))) {
                    a(path, path.substring(path.lastIndexOf("/") + 1, path.length()), "", -1, intExtra, stringExtra);
                }
                this.ak = 2;
            } else if (extras == null) {
                this.D = 1009;
                this.O = false;
            } else if (extras.getBoolean("com.qq.reader.fromonline")) {
                this.ak = 1;
                this.aS = extras.getString("com.qq.reader.fromonline_addfrom");
                path = extras.getString("filepath");
                Parcelable parcelable = extras.getParcelable("com.qq.reader.OnlineTag");
                boolean z2 = extras.getBoolean("com.qq.reader.OnlineTag.web.chapter");
                if (parcelable == null) {
                    parcelable = v.b().a(path);
                }
                if (parcelable == null || !(parcelable instanceof OnlineTag)) {
                    this.D = 1008;
                    this.O = false;
                    return;
                }
                this.af = 1;
                OnlineTag onlineTag = (OnlineTag) parcelable;
                a(onlineTag.k());
                a(onlineTag, z2);
                if (this.O) {
                    if (this.q.getBookCore().o().f() == 1008 && extras.getString("com.qq.reader.inheadpage") == null) {
                        this.q.getBookCore().p();
                    }
                    this.q.getTopPage().a(this.J, 1, this.af);
                } else if (this.D != 1008) {
                }
            } else {
                b(extras);
                this.ak = 0;
            }
        }
    }

    protected void a(final OnlineTag onlineTag, boolean z) {
        OnlineTag onlineTag2;
        this.aB = 1;
        Map hashMap = new HashMap();
        hashMap.put("bid", onlineTag.k());
        i.a("event_readBookonline", hashMap, getApplicationContext());
        StatisticsManager.a().a("event_readBookonline", hashMap);
        if (this.q.getBookCore().l() == 2) {
            this.N.setVisibility(8);
        }
        BroadcastReceiver b = b();
        android.support.v4.content.d.a((Context) this).a(b, new IntentFilter("com.qq.reader.chapter.updatecount"));
        android.support.v4.content.d.a((Context) this).a(b, new IntentFilter("com.qq.reader.chapter.updatefilelist"));
        android.support.v4.content.d.a((Context) this).a(Z(), new IntentFilter(com.qq.reader.common.c.a.cf));
        if (z) {
            onlineTag2 = onlineTag;
        } else {
            OnlineTag a = v.b().a(onlineTag.k());
            if (a == null) {
                a = onlineTag;
            }
            ao.a(a);
            onlineTag2 = a;
        }
        if (onlineTag.g() == 0) {
            i.a("event_online_chapterid_check", false, 0, 0, null, ReaderApplication.getApplicationImp().getApplicationContext());
            onlineTag.c(1);
        }
        this.C = d(onlineTag2);
        this.C.d(onlineTag2);
        e(onlineTag2);
        j.a(onlineTag2.k(), onlineTag2.g());
        this.X = com.qq.reader.common.db.handle.i.c().b(onlineTag.k(), true);
        File b2 = this.C.b(-12);
        if (b2 == null) {
            this.D = 1001;
            this.O = false;
        } else if (!b2.exists() || b2.length() <= 0) {
            if (z) {
                this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                    final /* synthetic */ ReaderPageActivity b;

                    public void a(int i) {
                        switch (i) {
                            case 1:
                                this.b.c(onlineTag);
                                return;
                            case 2:
                                this.b.y();
                                return;
                            case 3:
                                this.b.y();
                                return;
                            default:
                                return;
                        }
                    }
                };
            }
            if (onlineTag2.s() <= this.J.getFileCount() || onlineTag2.w() != 1) {
                if (this.X != null && this.X.getSynBook() == 1) {
                    onlineTag2.a(false);
                }
                a(onlineTag2, PageIndex.current);
            } else {
                a(null, onlineTag2.s(), PageIndex.current, 1004);
                a(false, true);
                this.P = true;
            }
        } else {
            this.O = a(onlineTag2);
            if (this.O) {
                this.F.setmFootInfo(j(onlineTag2.g()));
                a(this.C.g());
            }
        }
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("book_activate");
            if (stringExtra != null && stringExtra.length() > 0) {
                i.a("event_A148", null, ReaderApplication.getApplicationImp());
                Message obtain = Message.obtain();
                obtain.what = 1229;
                obtain.obj = stringExtra;
                this.mHandler.sendMessageDelayed(obtain, 500);
                return;
            }
        }
        this.mHandler.sendEmptyMessageDelayed(1221, 500);
        this.mHandler.sendEmptyMessageDelayed(1222, 500);
    }

    public BroadcastReceiver b() {
        if (this.ah == null) {
            this.ah = new BroadcastReceiver(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    String stringExtra;
                    if ("com.qq.reader.chapter.updatecount".equals(action)) {
                        int intExtra = intent.getIntExtra("book_max_chapter", -1);
                        stringExtra = intent.getStringExtra("book_id");
                        Message obtain = Message.obtain();
                        obtain.what = 100002;
                        obtain.arg1 = intExtra;
                        obtain.obj = stringExtra;
                        this.a.getHandler().sendMessage(obtain);
                    } else if ("com.qq.reader.chapter.updatefilelist".equals(action)) {
                        action = intent.getStringExtra("book_id");
                        if (this.a.C != null) {
                            stringExtra = this.a.C.g().k();
                            if (action != null && action.equals(stringExtra)) {
                                for (ReadOnlineFile chapterId : (List) intent.getExtras().getSerializable("chapter_file_list")) {
                                    this.a.J.getMulitFile().findNewFile(chapterId.getChapterId());
                                }
                            }
                        }
                    }
                }
            };
        }
        return this.ah;
    }

    private BroadcastReceiver Z() {
        if (this.aL == null) {
            this.aL = new BroadcastReceiver(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    try {
                        HashMap hashMap = (HashMap) intent.getSerializableExtra("errorbookmap");
                        Object obj = null;
                        if (this.a.C != null) {
                            OnlineTag g = this.a.C.g();
                            if (g != null) {
                                obj = g.k();
                            }
                        }
                        if (obj != null && hashMap != null && hashMap.containsKey(obj)) {
                            com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this, ((Integer) hashMap.get(obj)).intValue()));
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            };
        }
        return this.aL;
    }

    public int a(OnlineTag onlineTag, int i) {
        this.C = d(onlineTag);
        if (i == -12) {
            this.C.d(onlineTag);
        }
        this.C.g().b(true);
        File b = this.C.b(i);
        if (b == null) {
            return 0;
        }
        if (!b.exists() || b.length() <= 0) {
            PageIndex pageIndex = PageIndex.current;
            if (i == -11) {
                pageIndex = PageIndex.previous;
            } else if (i == -10) {
                pageIndex = PageIndex.next;
            }
            if (onlineTag.s() <= this.J.getFileCount() || onlineTag.w() != 1) {
                a(onlineTag, pageIndex);
            } else {
                b(false, true);
                if (this.q.getTopPage().B()) {
                    this.q.getBookCore().c(1);
                }
            }
            return 2;
        }
        boolean z;
        if (i == -11 || (i != -10 && onlineTag.i() == b.length())) {
            z = false;
        } else {
            z = true;
        }
        OnlineTag z2 = onlineTag.z();
        z2.c(onlineTag.s());
        if (onlineTag.a()) {
            z2.a(0);
        } else {
            z2.a(onlineTag.i());
        }
        boolean a = a(z2);
        if (!onlineTag.a()) {
            z2.a(true);
            onlineTag.a(true);
        }
        if (!a) {
            return 0;
        }
        try {
            onlineTag.c(z2.g());
            onlineTag.b(z2.h());
            if (i == -10) {
                OnlineTag z3 = onlineTag.z();
                z3.a(0);
                this.C.b(z3);
            }
            a = (i == -12 || i == -10 || i == -11) ? false : true;
            if (a) {
                this.C.g().c(z2.g());
                this.C.g().b(z2.h());
                this.q.g();
            }
            this.F.setmFootInfo(j(onlineTag.g()));
            this.q.setText((com.qq.reader.readengine.fileparse.d) this.G, z, a);
            if (this.q.getTopPage().h()) {
                i(i);
                this.q.getBookCore().f();
            }
            if (this.q.getTopPage().B()) {
                this.q.getBookCore().r();
            }
            this.q.getTopPage().postInvalidate();
            ao.c(onlineTag);
            return 1;
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a("readOnlineBook", "UnsupportedEncodingException", e);
            return 0;
        }
    }

    private void a(OnlineTag onlineTag, PageIndex pageIndex) {
        this.bc = System.currentTimeMillis();
        if (onlineTag.a()) {
            this.C.g().a(0);
        } else {
            this.C.g().a(onlineTag.i());
            this.C.g().a(false);
        }
        a(null, onlineTag.s(), pageIndex, 1000);
        this.P = true;
        this.C.e();
    }

    private void i(int i) {
        com.qq.reader.module.readpage.i iVar = this.q.getTopPage().getmPageCache();
        if (i == -12) {
            iVar.f(PageIndex.current_left);
            iVar.f(PageIndex.current_right);
            this.q.a(false, PageIndex.current_left, PageIndex.current_right);
        } else if (i == -11) {
            iVar.f(PageIndex.previous_left);
            iVar.f(PageIndex.previous_right);
            this.q.a(false, PageIndex.previous_left, PageIndex.previous_right);
        } else if (i == -10) {
            iVar.f(PageIndex.next_left);
            iVar.f(PageIndex.next_right);
            this.q.a(false, PageIndex.next_left, PageIndex.next_right);
        }
    }

    private com.qq.reader.cservice.onlineread.e d(OnlineTag onlineTag) {
        if (this.C == null) {
            this.C = new com.qq.reader.cservice.onlineread.e(getApplicationContext(), onlineTag);
            this.C.a(c());
        }
        return this.C;
    }

    public com.qq.reader.cservice.onlineread.c c() {
        final Context applicationContext = getApplicationContext();
        if (this.ac == null) {
            this.ac = new com.qq.reader.cservice.onlineread.c(this) {
                final /* synthetic */ ReaderPageActivity b;

                public void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult, OnlineChapterDownloadTask onlineChapterDownloadTask) {
                    Message message = new Message();
                    message.what = 1111;
                    message.obj = readOnlineResult;
                    this.b.getHandler().sendMessage(message);
                }

                public void a(OnlineTag onlineTag, OnlineChapterDownloadTask onlineChapterDownloadTask) {
                    Message.obtain(this.b.getHandler(), 1110, onlineTag).sendToTarget();
                }

                public void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult) {
                    Message obtainMessage = this.b.getHandler().obtainMessage();
                    obtainMessage.what = 1207;
                    obtainMessage.arg1 = readOnlineResult.s();
                    obtainMessage.obj = readOnlineResult;
                    this.b.getHandler().sendMessage(obtainMessage);
                }

                public void b(OnlineTag onlineTag, ReadOnlineResult readOnlineResult) {
                    int i = 1;
                    int g = onlineTag.g();
                    int s = onlineTag.s();
                    Message message = new Message();
                    message.what = 1112;
                    message.arg1 = readOnlineResult.s();
                    message.obj = readOnlineResult;
                    if (g == s) {
                        i = 3;
                    } else if (Math.abs(s - g) > 1) {
                        i = 6;
                    } else if (g >= s) {
                        i = 2;
                    }
                    message.arg2 = i;
                    this.b.getHandler().sendMessage(message);
                }

                public void a() {
                    com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
                    if (c != null && !c.j(applicationContext)) {
                        c.a(applicationContext, true);
                    }
                }

                public void a(OnlineTag onlineTag) {
                    Message obtain = Message.obtain();
                    obtain.what = 100002;
                    obtain.arg1 = onlineTag.n();
                    obtain.obj = onlineTag.k();
                    this.b.getHandler().sendMessage(obtain);
                }

                public void a(List<ReadOnlineFile> list) {
                    for (ReadOnlineFile chapterId : list) {
                        this.b.J.getMulitFile().findNewFile(chapterId.getChapterId());
                    }
                }
            };
        }
        return this.ac;
    }

    private void e(final OnlineTag onlineTag) {
        this.J = new BookTxt(onlineTag, 4);
        this.J.setEncodingStr(com.qq.reader.common.utils.c.b.a(this.J.getEncoding()));
        this.J.setReadType(1);
        this.J.createMulitFile(onlineTag.n());
        com.qq.reader.common.readertask.g.a().a(new ReaderIOTask() {
            public void run() {
                ReaderPageActivity.this.J.initFileList(onlineTag);
                Message obtain = Message.obtain();
                obtain.what = 1214;
                obtain.obj = onlineTag;
                ReaderPageActivity.this.getHandler().sendMessage(obtain);
                if (com.qq.reader.common.db.handle.h.b().b(onlineTag.k())) {
                    com.qq.reader.common.db.handle.h.b().a(onlineTag.k(), onlineTag.g());
                } else if (!com.qq.reader.common.login.c.c().j(ReaderPageActivity.this.getApplicationContext()) && com.qq.reader.common.db.handle.h.b().d(onlineTag.k())) {
                    com.qq.reader.common.db.handle.h.b().a(onlineTag.k(), onlineTag.g());
                }
            }
        });
        com.qq.reader.common.db.handle.c.a(onlineTag.k(), this.J.getAuthorId(), getHandler());
        if (this.G != null) {
            this.G.s();
            this.G = null;
        }
        this.G = new com.qq.reader.readengine.fileparse.f(this.J);
        this.q.setInput(this.G);
        bo();
    }

    private void a(String str, int i, PageIndex pageIndex, int i2) {
        ReadOnlineResult readOnlineResult = null;
        OnlineTag g = this.C.g();
        g.c(i);
        g.g(i);
        try {
            ((com.qq.reader.readengine.fileparse.f) this.G).a(str, i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        be();
        String str2 = "";
        if (i2 == 1001) {
            int i3 = i2;
            int i4 = i;
            a(i3, i4, "", 0, getString(R.string.paypage_getchapter), "", pageIndex, "", null);
        } else if (i2 == 1000) {
            com.qq.reader.module.readpage.h o = this.q.getBookCore().o();
            if (o != null) {
                readOnlineResult = o.c().t();
            }
            if (this.aC) {
                r5 = getString(R.string.paypage_buying);
            } else {
                r5 = getString(R.string.paypage_loading);
                if (readOnlineResult != null) {
                    readOnlineResult.f("");
                }
            }
            a(i2, i, "", 0, r5, "", pageIndex, "", readOnlineResult);
        } else if (i2 == 1004) {
            String string;
            String str3;
            str2 = "";
            String str4 = "";
            if (g.w() == 1) {
                r5 = getString(R.string.paypage_comment);
                string = getResources().getString(R.string.paypage_title_endpage);
                str3 = str2;
            } else {
                if (com.qq.reader.cservice.bookfollow.c.a(g.k())) {
                    str4 = getString(R.string.paypage_recommend);
                    str2 = getResources().getString(R.string.paypage_tip_ordered);
                } else {
                    str4 = getResources().getString(R.string.paypage_ordernew);
                }
                string = getString(R.string.paypage_title_wait);
                str3 = str2;
                r5 = str4;
            }
            a(1004, g.s(), "", -1, r5, str3, pageIndex, string, null);
        }
    }

    protected boolean a(OnlineTag onlineTag) {
        if (onlineTag == null) {
            try {
                this.D = 1008;
                return false;
            } catch (Throwable e) {
                com.qq.reader.common.monitor.f.a("ReadFile:", "read failed!", e);
                e.printStackTrace();
                this.D = 1003;
                com.qq.reader.common.monitor.b.a(e);
                return false;
            } catch (Throwable e2) {
                com.qq.reader.common.monitor.f.a("ReadFile:", "read failed!", e2);
                e2.printStackTrace();
                this.D = 1004;
                com.qq.reader.common.monitor.b.a(e2);
                return false;
            }
        }
        String str;
        boolean h;
        String str2 = "";
        File a = v.b().a(onlineTag);
        if (a == null || !a.exists()) {
            str = str2;
        } else {
            str = com.qq.reader.cservice.onlineread.b.a(getApplicationContext(), onlineTag);
        }
        ((com.qq.reader.readengine.fileparse.f) this.G).a(str, onlineTag.g());
        if (!onlineTag.r() || a.length() <= onlineTag.i()) {
            h = ((com.qq.reader.readengine.fileparse.d) this.G).h();
        } else {
            com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
            gVar.a(onlineTag.g(), onlineTag.i());
            if (onlineTag.a()) {
                h = ((com.qq.reader.readengine.fileparse.d) this.G).a(gVar, false);
            } else {
                h = ((com.qq.reader.readengine.fileparse.d) this.G).a(gVar, true);
            }
        }
        if (h) {
            a(TbsLog.TBSLOG_CODE_SDK_INIT, 0, "", 0, "", "", PageIndex.current, "", null);
            com.qq.reader.common.db.handle.b.a().a(onlineTag.k(), onlineTag.g(), false, this.mHandler);
            return true;
        }
        this.D = 1002;
        return false;
    }

    protected void a(String str, String str2, String str3, int i, int i2, String str4) {
        try {
            String substring;
            int encoding;
            long j;
            long j2;
            Map hashMap;
            com.qq.reader.readengine.kernel.g gVar;
            com.qq.reader.module.bookchapter.online.i a;
            String str5 = "";
            if (str.contains("/storage/")) {
                if (!str.startsWith("/storage/")) {
                    String str6 = str;
                    substring = str6.substring(str.indexOf("/storage/"), str.length());
                    this.X = com.qq.reader.common.db.handle.i.c().b(substring, true);
                    if (this.X == null) {
                        encoding = this.X.getEncoding();
                    } else {
                        if (getIntent().getBooleanExtra("detailpage_trial_read", false) && com.qq.reader.readengine.model.a.n(substring)) {
                            substring = substring.substring(0, substring.lastIndexOf(".")) + ".trial";
                            this.X = com.qq.reader.common.db.handle.i.c().b(substring, true);
                        }
                        encoding = i;
                    }
                    if (-1 == encoding) {
                        encoding = com.qq.reader.readengine.model.a.g(substring);
                    }
                    this.af = 1;
                    if (100 == encoding) {
                        this.J = new BookUmd(str2, substring, str3);
                        this.G = new com.qq.reader.readengine.fileparse.h((BookUmd) this.J);
                        ab();
                    } else if (101 != encoding) {
                        j = 0;
                        if (this.X != null) {
                            j = this.X.getBookId();
                        }
                        this.G = new com.qq.reader.readengine.kernel.a.a(substring, j);
                        this.J = this.G.t();
                        this.af = 2;
                        if (this.X != null && (this.X instanceof DownloadMark) && this.J == null) {
                            ad();
                            return;
                        }
                    } else {
                        j2 = 0;
                        if (this.X != null) {
                            j2 = this.X.getBookId();
                        }
                        this.J = new BookTxt(str2, substring, str3, encoding, "", j2);
                        ab();
                        this.G = new com.qq.reader.readengine.fileparse.g((BookTxt) this.J);
                    }
                    if (this.X != null && this.X.getBookId() > 0) {
                        hashMap = new HashMap();
                        hashMap.put("bid", String.valueOf(this.X.getBookId()));
                        i.a("event_readBookonline", hashMap, getApplicationContext());
                        StatisticsManager.a().a("event_readBookonline", hashMap);
                    }
                    this.J.setEncrypted_flag(i2);
                    this.J.setBookLocalId(str4);
                    this.J.setReadType(0);
                    this.q.setInput(this.G);
                    if (this.G != null) {
                        if (this.G instanceof com.qq.reader.readengine.fileparse.d) {
                            if (this.J.getLength() == 0) {
                                this.aR = true;
                            } else if (this.X == null) {
                                if (this.J.getLength() <= 0) {
                                    j = this.X.getStartPoint();
                                    gVar = new com.qq.reader.readengine.kernel.g();
                                    if (j >= this.J.getLength()) {
                                        gVar.a(j);
                                    } else {
                                        this.Q = true;
                                        gVar.a(0);
                                    }
                                    a(gVar, true, false, true);
                                    if (this.X.getStarPointStr() == null) {
                                        this.q.getBookCore().p();
                                    }
                                } else {
                                    this.O = false;
                                }
                            } else if (((com.qq.reader.readengine.fileparse.d) this.G).i()) {
                                this.O = false;
                            } else {
                                this.O = true;
                            }
                            if (this.X != null) {
                                com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                                    public void run() {
                                        super.run();
                                        ReaderPageActivity.this.a(ReaderPageActivity.this.X);
                                    }
                                });
                                if (this.J.getBookNetId() > 0) {
                                    a(String.valueOf(this.J.getBookNetId()));
                                }
                            }
                        } else if (this.G.t() == null) {
                            a = com.qq.reader.common.db.handle.h.b().a(this.J.getBookNetId() + "");
                            if (a != null || a.c() > System.currentTimeMillis()) {
                                c(substring, str2, str3, encoding, i2, str4);
                            } else if (com.qq.reader.common.login.c.b()) {
                                aa();
                                if (!this.Z.f()) {
                                    this.Z.f_();
                                }
                                com.qq.reader.cservice.download.book.h hVar = new com.qq.reader.cservice.download.book.h(getApplicationContext(), new com.qq.reader.cservice.download.book.g(str4));
                                if (this.aX == null) {
                                    final String str7 = substring;
                                    final String str8 = str2;
                                    final String str9 = str3;
                                    final int i3 = encoding;
                                    final int i4 = i2;
                                    final String str10 = str4;
                                    this.aX = new f(this) {
                                        final /* synthetic */ ReaderPageActivity g;

                                        public void a(com.qq.reader.cservice.download.book.g gVar) {
                                            switch (gVar.a()) {
                                                case 0:
                                                case 2:
                                                case 6:
                                                    com.qq.reader.common.db.handle.h.b().a(this.g.J.getBookNetId() + "", 0);
                                                    this.g.b(str7, str8, str9, i3, i4, str10);
                                                    break;
                                                case 1:
                                                case 4:
                                                    this.g.b(str7, str8, str9, i3, i4, str10);
                                                    break;
                                                case 5:
                                                    com.qq.reader.module.bookchapter.online.i iVar = new com.qq.reader.module.bookchapter.online.i(this.g.J.getBookNetId() + "");
                                                    iVar.b(gVar.a());
                                                    iVar.a(gVar.b());
                                                    com.qq.reader.common.db.handle.h.b().a(iVar);
                                                    this.g.b(str7, str8, str9, i3, i4, str10);
                                                    break;
                                            }
                                            this.g.aX = null;
                                        }

                                        public void b(com.qq.reader.cservice.download.book.g gVar) {
                                            this.g.f(3);
                                            this.g.aX = null;
                                            this.g.aX = null;
                                        }

                                        public void c(com.qq.reader.cservice.download.book.g gVar) {
                                            if (gVar.a() == 1) {
                                                this.g.b(str7, str8, str9, i3, i4, str10);
                                            } else {
                                                this.g.aB = 2;
                                                this.g.mHandler.sendMessage(this.g.mHandler.obtainMessage(1246));
                                            }
                                            this.g.aX = null;
                                        }
                                    };
                                }
                                hVar.a(this.aX);
                                hVar.start();
                                return;
                            } else {
                                d(substring, str2, str3, encoding, i2, str4);
                                return;
                            }
                        } else if (this.X == null || !(this.X instanceof DownloadMark)) {
                            this.O = false;
                        }
                        this.q.getTopPage().a(this.J, 0, this.af);
                        be();
                    }
                }
            }
            substring = str;
            this.X = com.qq.reader.common.db.handle.i.c().b(substring, true);
            if (this.X == null) {
                substring = substring.substring(0, substring.lastIndexOf(".")) + ".trial";
                this.X = com.qq.reader.common.db.handle.i.c().b(substring, true);
                encoding = i;
            } else {
                encoding = this.X.getEncoding();
            }
            if (-1 == encoding) {
                encoding = com.qq.reader.readengine.model.a.g(substring);
            }
            this.af = 1;
            if (100 == encoding) {
                this.J = new BookUmd(str2, substring, str3);
                this.G = new com.qq.reader.readengine.fileparse.h((BookUmd) this.J);
                ab();
            } else if (101 != encoding) {
                j2 = 0;
                if (this.X != null) {
                    j2 = this.X.getBookId();
                }
                this.J = new BookTxt(str2, substring, str3, encoding, "", j2);
                ab();
                this.G = new com.qq.reader.readengine.fileparse.g((BookTxt) this.J);
            } else {
                j = 0;
                if (this.X != null) {
                    j = this.X.getBookId();
                }
                this.G = new com.qq.reader.readengine.kernel.a.a(substring, j);
                this.J = this.G.t();
                this.af = 2;
                ad();
                return;
            }
            hashMap = new HashMap();
            hashMap.put("bid", String.valueOf(this.X.getBookId()));
            i.a("event_readBookonline", hashMap, getApplicationContext());
            StatisticsManager.a().a("event_readBookonline", hashMap);
            this.J.setEncrypted_flag(i2);
            this.J.setBookLocalId(str4);
            this.J.setReadType(0);
            this.q.setInput(this.G);
            if (this.G != null) {
                if (this.G instanceof com.qq.reader.readengine.fileparse.d) {
                    if (this.J.getLength() == 0) {
                        this.aR = true;
                    } else if (this.X == null) {
                        if (((com.qq.reader.readengine.fileparse.d) this.G).i()) {
                            this.O = false;
                        } else {
                            this.O = true;
                        }
                    } else if (this.J.getLength() <= 0) {
                        this.O = false;
                    } else {
                        j = this.X.getStartPoint();
                        gVar = new com.qq.reader.readengine.kernel.g();
                        if (j >= this.J.getLength()) {
                            this.Q = true;
                            gVar.a(0);
                        } else {
                            gVar.a(j);
                        }
                        a(gVar, true, false, true);
                        if (this.X.getStarPointStr() == null) {
                            this.q.getBookCore().p();
                        }
                    }
                    if (this.X != null) {
                        com.qq.reader.common.readertask.g.a().a(/* anonymous class already generated */);
                        if (this.J.getBookNetId() > 0) {
                            a(String.valueOf(this.J.getBookNetId()));
                        }
                    }
                } else if (this.G.t() == null) {
                    a = com.qq.reader.common.db.handle.h.b().a(this.J.getBookNetId() + "");
                    if (a != null) {
                    }
                    c(substring, str2, str3, encoding, i2, str4);
                } else {
                    this.O = false;
                }
                this.q.getTopPage().a(this.J, 0, this.af);
                be();
            }
        } catch (FileNotFoundException e) {
            this.O = false;
            e.printStackTrace();
            com.qq.reader.common.monitor.f.a("ReadFile:", e.toString());
        } catch (Exception e2) {
            this.O = false;
            e2.printStackTrace();
            com.qq.reader.common.monitor.f.a("ReadFile:", e2.toString());
        }
    }

    private void b(String str, String str2, String str3, int i, int i2, String str4) {
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final int i3 = i;
        final int i4 = i2;
        final String str8 = str4;
        this.mHandler.post(new Runnable(this) {
            final /* synthetic */ ReaderPageActivity g;

            public void run() {
                this.g.c(str5, str6, str7, i3, i4, str8);
                this.g.q.getTopPage().a(this.g.J, 0, this.g.af);
                this.g.be();
            }
        });
    }

    private void c(String str, String str2, String str3, int i, int i2, String str4) {
        if (this.J.getEncrypted_flag() == 0) {
            com.qq.reader.common.drm.a aVar = new com.qq.reader.common.drm.a(this, this.J.getBookPath());
            int a = aVar.a();
            if (a == 0) {
                ad();
                return;
            } else if (a == 1 || a == -2) {
                aVar.a((com.qq.reader.common.drm.a.a) this);
                aa();
                if (!this.Z.f()) {
                    this.Z.f_();
                }
                aVar.b(String.valueOf(this.J.getBookNetId()), com.qq.reader.readengine.model.a.k(this.X.getId()));
                return;
            } else if (a == -3) {
                Message obtainMessage = this.mHandler.obtainMessage();
                obtainMessage.what = 1123;
                this.mHandler.sendMessage(obtainMessage);
                return;
            } else if (a == 2) {
                d(str, str2, str3, i, i2, str4);
                return;
            } else {
                return;
            }
        }
        ad();
    }

    private void aa() {
        if (this.Z == null) {
            this.Z = new com.qq.reader.view.c(this);
            this.Z.c(true);
            this.Z.a(getResources().getString(R.string.read_page_requesting_permission_check));
        }
    }

    private void d(String str, String str2, String str3, int i, int i2, String str4) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1124;
        this.mHandler.sendMessage(obtainMessage);
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final int i3 = i;
        final int i4 = i2;
        final String str8 = str4;
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ ReaderPageActivity g;

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.g.a(str5, str6, str7, i3, i4, str8);
                        return;
                    case 2:
                        this.g.y();
                        return;
                    case 3:
                        this.g.y();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    private void ab() {
        if (this.X != null) {
            this.J.mTruePageBytes = this.X.getTurePageBytes();
            this.J.mCurBufferPageIndex = this.X.getTurePageCurIndex();
            this.J.mTruePageFont = this.X.getTurePageFont();
            this.J.mTurePageCmd = 100;
        }
    }

    private int a(List<com.qq.reader.readengine.model.b> list) {
        int abs = Math.abs(this.J.getBookPath().hashCode());
        if (list == null) {
            return 0;
        }
        int i = 0;
        for (com.qq.reader.readengine.model.b bVar : list) {
            int i2;
            try {
                if (bVar.r() == this.af) {
                    if ((bVar.g().equals("0") || bVar.g().length() <= 0) && (bVar.h().equals("0") || bVar.h().length() <= 0)) {
                        if (this.aB == 0) {
                            long bookNetId;
                            long a = a(bVar.m(), (int) bVar.n());
                            long a2 = a(bVar.o(), (int) bVar.p());
                            if (this.J.getBookNetId() != 0) {
                                bookNetId = this.J.getBookNetId();
                            } else {
                                bookNetId = (long) abs;
                            }
                            bVar.b(a + "");
                            bVar.c(a2 + "");
                            bVar.d(bookNetId);
                            i += com.qq.reader.common.db.handle.s.a().a(bookNetId, (long) bVar.m(), bVar.n(), (long) bVar.o(), bVar.p(), a + "", a2 + "", bVar.f());
                        } else {
                            bVar.b(bVar.n() + "");
                            bVar.c(bVar.p() + "");
                            bVar.d(this.J.getBookNetId());
                            i += com.qq.reader.common.db.handle.s.a().a(this.J.getBookNetId(), (long) bVar.m(), bVar.n(), (long) bVar.o(), bVar.p(), bVar.n() + "", bVar.p() + "", bVar.f());
                        }
                    }
                    i2 = i;
                    i = i2;
                }
            } catch (Exception e) {
                i2 = i;
            }
        }
        return i;
    }

    private int ac() {
        int abs = Math.abs(this.J.getBookPath().hashCode());
        if (this.aB != 0 || this.bq == null) {
            return 0;
        }
        int i = 0;
        for (com.qq.reader.readengine.model.b bVar : IBook.mRemarksList) {
            int i2;
            try {
                if (bVar.m() == 0 && bVar.n() == 0 && bVar.o() == 0 && bVar.p() == 0 && bVar.g().length() > 0 && bVar.h().length() > 0) {
                    long bookNetId;
                    String str = null;
                    int[] a = a(Long.parseLong(bVar.g()), false);
                    int[] a2 = a(Long.parseLong(bVar.h()), false);
                    if (this.J.getBookNetId() != 0) {
                        bookNetId = this.J.getBookNetId();
                    } else {
                        bookNetId = (long) abs;
                    }
                    int i3 = a[0];
                    long j = (long) a[1];
                    int i4 = a2[0];
                    long j2 = (long) a2[1];
                    int i5 = i3 - 1;
                    if (i5 >= 0 && i5 < this.bq.length) {
                        str = this.bq[i5].getDescriptionStr();
                    }
                    bVar.e(str);
                    bVar.d(bookNetId);
                    bVar.a(i3);
                    bVar.e(j);
                    bVar.b(i4);
                    bVar.f(j2);
                    i += com.qq.reader.common.db.handle.s.a().a(bookNetId, (long) i3, j, (long) i4, j2, bVar.g(), bVar.h(), bVar.f());
                }
                i2 = i;
            } catch (Exception e) {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void ad() {
        /*
        r12 = this;
        r0 = 1;
        r12.O = r0;
        r10 = 0;
        r0 = r12.X;
        if (r0 == 0) goto L_0x0150;
    L_0x0008:
        r0 = r12.X;
        r0 = r0 instanceof com.qq.reader.framework.mark.DownloadMark;
        if (r0 == 0) goto L_0x0150;
    L_0x000e:
        r0 = r12.aN;
        r1 = r12.X;
        r2 = r1.getBookId();
        r11 = r0.a(r2);
        if (r11 == 0) goto L_0x002b;
    L_0x001c:
        r0 = com.qq.reader.activity.ReaderPageActivity.AnonymousClass48.a;
        r1 = r11.getState();
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x0093;
            case 2: goto L_0x0093;
            case 3: goto L_0x00b9;
            case 4: goto L_0x00b9;
            case 5: goto L_0x00e1;
            case 6: goto L_0x00e1;
            case 7: goto L_0x0110;
            case 8: goto L_0x0110;
            default: goto L_0x002b;
        };
    L_0x002b:
        r0 = r10;
    L_0x002c:
        if (r0 == 0) goto L_0x008f;
    L_0x002e:
        r0 = r12.q;
        r0 = r0.getBookCore();
        r0 = r0.d();
        if (r0 != 0) goto L_0x0041;
    L_0x003a:
        r0 = r12.q;
        r1 = r12.G;
        r0.setInput(r1);
    L_0x0041:
        r0 = r12.X;
        if (r0 != 0) goto L_0x0060;
    L_0x0045:
        r1 = new com.qq.reader.framework.mark.LocalMark;
        r0 = r12.J;
        r2 = r0.getBookName();
        r0 = r12.J;
        r3 = r0.getBookPath();
        r0 = r12.J;
        r4 = r0.getLength();
        r6 = 1;
        r7 = 0;
        r1.<init>(r2, r3, r4, r6, r7);
        r12.X = r1;
    L_0x0060:
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = -1;
        r3 = "";
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = r12.getResources();
        r5 = 2131297047; // 0x7f090317 float:1.8212028E38 double:1.053000652E-314;
        r5 = r0.getString(r5);
        r6 = "";
        r7 = com.qq.reader.readengine.kernel.PageIndex.current;
        r0 = r12.X;
        r8 = r0.getBookShortName();
        r9 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r0 = com.qq.reader.common.readertask.g.a();
        r1 = new com.qq.reader.activity.ReaderPageActivity$20;
        r1.<init>();
        r0.a(r1);
    L_0x008f:
        r12.ae();
        return;
    L_0x0093:
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = -1;
        r3 = "";
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = r12.getResources();
        r5 = 2131296713; // 0x7f0901c9 float:1.821135E38 double:1.053000487E-314;
        r5 = r0.getString(r5);
        r6 = "";
        r7 = com.qq.reader.readengine.kernel.PageIndex.current;
        r0 = r12.X;
        r8 = r0.getBookShortName();
        r9 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r0 = r10;
        goto L_0x002c;
    L_0x00b9:
        r0 = 0;
        r11.setIsOnlyDownLoadIcon(r0);
        r0 = r12.aN;
        r0.c(r11);
        r1 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
        r2 = -1;
        r3 = "";
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = 2131297058; // 0x7f090322 float:1.821205E38 double:1.0530006574E-314;
        r5 = r12.getString(r0);
        r6 = "";
        r7 = com.qq.reader.readengine.kernel.PageIndex.current;
        r8 = "";
        r9 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r0 = r10;
        goto L_0x002c;
    L_0x00e1:
        r0 = 0;
        r11.setIsOnlyDownLoadIcon(r0);
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = -1;
        r3 = "";
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = r12.getResources();
        r5 = 2131296713; // 0x7f0901c9 float:1.821135E38 double:1.053000487E-314;
        r5 = r0.getString(r5);
        r6 = "";
        r7 = com.qq.reader.readengine.kernel.PageIndex.current;
        r0 = r12.X;
        r8 = r0.getBookShortName();
        r9 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r0 = r12.aN;
        r0.e(r11);
        r0 = r10;
        goto L_0x002c;
    L_0x0110:
        r0 = new java.io.File;
        r1 = r11.getFilePath();
        r0.<init>(r1);
        r0 = r0.exists();
        if (r0 == 0) goto L_0x0122;
    L_0x011f:
        r0 = 1;
        goto L_0x002c;
    L_0x0122:
        r0 = 0;
        r11.setIsOnlyDownLoadIcon(r0);
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = -1;
        r3 = "";
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = r12.getResources();
        r5 = 2131296713; // 0x7f0901c9 float:1.821135E38 double:1.053000487E-314;
        r5 = r0.getString(r5);
        r6 = "";
        r7 = com.qq.reader.readengine.kernel.PageIndex.current;
        r0 = r12.X;
        r8 = r0.getBookShortName();
        r9 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r0 = r12.aN;
        r0.f(r11);
        goto L_0x002b;
    L_0x0150:
        r0 = 1;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.ReaderPageActivity.ad():void");
    }

    private void ae() {
        if (this.X != null && !TextUtils.isEmpty(String.valueOf(this.X.getBookId()))) {
            OnlineTag onlineTag = new OnlineTag(String.valueOf(this.X.getBookId()), "", 0);
            if (this.az == null) {
                this.az = new com.qq.reader.module.bookchapter.a.a(getApplicationContext(), onlineTag);
            }
            this.az.a(this.mHandler);
            this.az.a(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    if (this.a.az != null && this.a.az.c() != null) {
                        this.a.X.setLimitFreeEndTime(this.a.az.c().x());
                        this.a.X.setDiscount(this.a.az.c().s());
                        if (this.a.az.c().t()) {
                            this.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass54 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    af.a(this.a.a, this.a.a.getResources().getString(R.string.price_info_limitfree) + "，" + ao.i(this.a.a.az.c().x()), 0).a();
                                }
                            });
                        }
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            });
        }
    }

    private void b(Bundle bundle) {
        this.aB = 0;
        String string = bundle.getString("filepath");
        String string2 = bundle.getString("filename");
        String string3 = bundle.getString("fileauthor");
        if (string3 == null) {
            string3 = "匿名";
        }
        a(string, string2, string3, bundle.getInt("fileencode", -1), bundle.getInt("fileencrypt", 2), bundle.getString("fileid"));
    }

    protected an d() {
        if (this.U == null) {
            this.U = new an(this);
            this.U.a(this);
            this.U.a(new com.qq.reader.view.an.c(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.af().f_();
                    this.a.U.cancel();
                    i.a("event_B13", null, this.a);
                }
            });
        }
        return this.U;
    }

    protected void e() {
        Context applicationContext = getApplicationContext();
        com.qq.reader.appconfig.a.d.n = com.qq.reader.appconfig.a.d.P(applicationContext);
        this.q.d(com.qq.reader.appconfig.a.d.L(applicationContext));
        this.q.setTextSize(com.qq.reader.appconfig.a.d.I(applicationContext));
        com.qq.reader.appconfig.a.d.u = com.qq.reader.appconfig.a.d.S(applicationContext);
        com.qq.reader.appconfig.a.d.v = com.qq.reader.appconfig.a.d.U(applicationContext);
        com.qq.reader.appconfig.a.d.x = com.qq.reader.appconfig.a.d.Y(applicationContext);
        com.qq.reader.readengine.a.a.b();
    }

    protected ay f() {
        if (this.T == null) {
            this.T = new ay(this);
            this.S = new com.qq.reader.view.ay.a(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a(float f) {
                    if (this.a.J != null) {
                        this.a.J.mTurePageCmd = 102;
                    }
                    this.a.q.a(f);
                }

                public void a() {
                    this.a.mHandler.sendEmptyMessage(1201);
                }

                public void a(int i) {
                    Message message = new Message();
                    message.what = 1216;
                    message.arg1 = i;
                    this.a.mHandler.sendMessage(message);
                }
            };
            this.T.a(this.S);
        } else {
            this.T.a((Activity) this);
        }
        return this.T;
    }

    private com.qq.reader.view.e af() {
        if (this.Y == null) {
            this.Y = new com.qq.reader.view.e(this, this.q);
        }
        return this.Y;
    }

    public void a(final String str, String str2, final x xVar) {
        this.ad = new q(this);
        this.ad.a(str2);
        this.ad.a(new com.qq.reader.view.q.a(this) {
            final /* synthetic */ ReaderPageActivity c;

            public void a(String str) {
                xVar.a(str, str);
            }

            public void a() {
                ReaderTextPageView.r();
            }
        });
        this.ad.f_();
    }

    public void a(String str, final x xVar) {
        this.ad = new q(this);
        this.ad.a(str);
        this.ad.a(new com.qq.reader.view.q.a(this) {
            final /* synthetic */ ReaderPageActivity b;

            public void a(String str) {
                xVar.a(str);
            }

            public void a() {
            }
        });
        this.ad.f_();
    }

    public boolean onSearchRequested() {
        c(1);
        return true;
    }

    private ai ag() {
        if (this.V == null) {
            this.V = new ai(this, R.string.jump_text_local_percent, this.aB);
            this.V.a(new com.qq.reader.view.ai.a(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a(double d) {
                    if (this.a.aG.getCurrentState() != 101) {
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass59 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.aG.h();
                            }
                        });
                    }
                    long m = this.a.q.getBookCore().m();
                    long j = (long) (((double) m) * d);
                    com.qq.reader.readengine.kernel.g a = this.a.q.getBookCore().a(d);
                    com.qq.reader.readengine.kernel.g h = this.a.q.getBookCore().h();
                    AnimationProvider animationProvider = this.a.q.getTopPage().getAnimationProvider();
                    if (!h.equals(a)) {
                        if (com.qq.reader.appconfig.a.d.Y(this.a.getApplicationContext()) == 0 && com.qq.reader.appconfig.a.d.ag(this.a.getApplicationContext())) {
                            try {
                                this.a.q.getBookCore().a(a, true, false, false);
                                this.a.q.f();
                                if (j >= m && a.e() == j) {
                                    this.a.q.getBookCore().f();
                                }
                                com.qq.reader.module.readpage.i iVar = this.a.q.getTopPage().getmPageCache();
                                iVar.g();
                                iVar.a(PageIndex.previous_left);
                                iVar.a(PageIndex.current_left);
                                iVar.a(PageIndex.next_left);
                                switch (this.a.q.getBookCore().e()) {
                                    case 0:
                                    case 1:
                                        iVar.a(PageIndex.previous_right);
                                        iVar.a(PageIndex.current_right);
                                        iVar.a(PageIndex.next_right);
                                        break;
                                    default:
                                        iVar.c(PageIndex.previous_right);
                                        iVar.c(PageIndex.current_right);
                                        iVar.c(PageIndex.next_right);
                                        break;
                                }
                                this.a.q.getBookCore().f();
                            } catch (Exception e) {
                                com.qq.reader.common.monitor.f.a("ReaderPage onTurnSeekBar", "exp : " + e.getMessage());
                            }
                        } else {
                            boolean z = true;
                            if (this.a.aB == 1) {
                                z = this.a.a(h, a);
                            }
                            if (z) {
                                if (this.a.J != null) {
                                    this.a.J.mTurePageCmd = 101;
                                }
                                this.a.a(a, false, true, false);
                                if (this.a.b(h, a)) {
                                    if (d >= 1.0d && a.e() == j) {
                                        this.a.q.getBookCore().f();
                                        this.a.q.s();
                                    } else if (!this.a.q.getBookCore().n()) {
                                        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                                        gVar.a(m);
                                        this.a.a(gVar, false, true, false);
                                        this.a.q.getBookCore().f();
                                        this.a.q.s();
                                    }
                                }
                            }
                        }
                        int width = this.a.q.getWidth();
                        int height = this.a.q.getHeight();
                        if (h.b(a) < 0) {
                            animationProvider.b((float) ((width * 4) / 5), (float) (height / 2));
                            animationProvider.a(width, 0, -width, height, Mode.ForceScrolling, http.Internal_Server_Error);
                        } else if (h.b(a) > 0) {
                            animationProvider.b((float) (width / 5), (float) (height / 2));
                            animationProvider.a(-width, 0, width, height, Mode.ForceScrolling, http.Internal_Server_Error);
                        }
                        this.a.q.getTopPage().invalidate();
                        b bVar = new b();
                        this.a.a(bVar);
                        this.a.V.a(bVar.a, bVar.b);
                    }
                }

                public void a() {
                    if (this.a.aB == 1) {
                        com.qq.reader.readengine.kernel.g h = this.a.q.getBookCore().h();
                        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                        if (this.a.q.getBookCore().o().f() != 1000) {
                            if (this.a.J.getCurIndex() >= this.a.J.getFileCount()) {
                                this.a.b("已到最后一章");
                                return;
                            }
                            gVar.a(this.a.J.getCurIndex() + 1, 0);
                            if (this.a.a(h, gVar)) {
                                this.a.a(gVar, false, false, false);
                            } else {
                                this.a.q.g();
                                this.a.q.getTopPage().invalidate();
                            }
                            b bVar = new b();
                            this.a.a(bVar);
                            this.a.V.a(bVar.a, bVar.b);
                            this.a.V.g();
                        }
                    }
                }

                public void b() {
                    if (this.a.aB == 1) {
                        com.qq.reader.readengine.kernel.g h = this.a.q.getBookCore().h();
                        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                        if (this.a.q.getBookCore().o().f() != 1000) {
                            if (this.a.J.getCurIndex() <= 1) {
                                this.a.b("已到第一章");
                                return;
                            }
                            gVar.a(this.a.J.getCurIndex() - 1, 0);
                            if (this.a.a(h, gVar)) {
                                this.a.a(gVar, false, false, false);
                            } else {
                                this.a.q.g();
                                this.a.q.getTopPage().invalidate();
                            }
                            b bVar = new b();
                            this.a.a(bVar);
                            this.a.V.a(bVar.a, bVar.b);
                            this.a.V.g();
                        }
                    }
                }

                public String b(double d) {
                    if (this.a.aB == 1) {
                        return this.a.j(this.a.q.getBookCore().a(d).f());
                    } else if (!(this.a.J instanceof BookTxt) && !(this.a.J instanceof BookEPub) && !(this.a.J instanceof BookUmd)) {
                        return null;
                    } else {
                        return this.a.a(this.a.q.getBookCore().a(d).e());
                    }
                }

                public Double c() {
                    return this.a.q.getBookCore().g();
                }

                public String d() {
                    if (this.a.aB == 1) {
                        return this.a.j(this.a.J.getCurIndex());
                    } else if (!(this.a.J instanceof BookTxt) && !(this.a.J instanceof BookEPub) && !(this.a.J instanceof BookUmd)) {
                        return null;
                    } else {
                        return this.a.a(this.a.q.getBookCore().h().e());
                    }
                }
            });
        }
        return this.V;
    }

    public void onAudioFloatingStateChange(int i, long j, boolean z, String str) {
        AudioFloatingWindowView audioFloatingWindowView;
        if (i == 2) {
            audioFloatingWindowView = (AudioFloatingWindowView) findViewById(R.id.img_audio_floating);
            if (audioFloatingWindowView != null) {
                ao.a(2, this, audioFloatingWindowView, j, z, str);
            }
        } else if (i == 1) {
            audioFloatingWindowView = (AudioFloatingWindowView) findViewById(R.id.img_tts_floating);
            if (audioFloatingWindowView != null && this.C != null && this.C.g() != null) {
                ao.a(1, this, audioFloatingWindowView, Long.parseLong(this.C.g().k()), this.C.g(), z, new OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.aE();
                    }
                });
            }
        }
    }

    private void a(b bVar) {
        bVar.a = false;
        bVar.b = false;
        if (!(this.J.getMulitFile() == null || this.J.getMulitFile().isFirstFile())) {
            bVar.a = true;
        }
        if (this.J.getMulitFile() != null && !this.J.getMulitFile().isLastFile()) {
            bVar.b = true;
        }
    }

    private String j(int i) {
        OnlineChapter onlineChapter = (OnlineChapter) this.J.getMulitFile().getChapterInfo(i);
        if (onlineChapter != null) {
            return onlineChapter.getChapterName();
        }
        return "第" + i + "章";
    }

    protected void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        this.q.getTopPage().setRunInBackground(false);
        this.r = true;
        ao.a((Activity) this);
        ao.b((Activity) this);
        ai();
        StatisticsManager.a().a("event_reader", null);
        this.bm = false;
        this.mHandler.sendEmptyMessageDelayed(1243, 100);
        com.qq.reader.tinker.b.b().a();
        android.support.v4.content.d.a((Context) this).a(this.bn);
    }

    private boolean ah() {
        if (this.bj == null || !this.bj.f()) {
            return true;
        }
        return this.bj.g();
    }

    private void ai() {
        String string;
        int a;
        this.F.a();
        if (com.qq.reader.appconfig.a.d.n) {
            ao.a((Activity) this, false);
        }
        if (this.C != null) {
            this.C.a(c());
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            string = extras.getString("filename");
        } else {
            string = null;
        }
        if (string == null || !com.qq.reader.readengine.model.a.l(string)) {
            setRequestedOrientation(com.qq.reader.appconfig.a.d.x);
        } else {
            m(1);
        }
        registerReceiver(this.ag, this.ai);
        try {
            com.qq.reader.appconfig.a.a(getApplicationContext());
            a = ao.a(getApplicationContext(), false);
            if (a < 0) {
                ao.a(getWindow(), true);
            } else {
                try {
                    com.qq.reader.module.readpage.z.a(a, getHandler(), this);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            new com.qq.reader.common.monitor.k(getApplicationContext()).a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.ar = new Timer();
        this.ar.schedule(new c(), 600000);
        this.as = new Timer();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        a = instance.get(11);
        if (((a >= 19 && a < 24) || (a >= 0 && a <= 6)) && !com.qq.reader.appconfig.a.d.n && com.qq.reader.appconfig.a.d.ar(getApplicationContext())) {
            try {
                SensorManager sensorManager = (SensorManager) getSystemService("sensor");
                this.s = sensorManager.getDefaultSensor(5);
                sensorManager.registerListener(this, this.s, 1);
            } catch (Exception e2) {
            }
            com.qq.reader.appconfig.a.d.t(getApplicationContext(), false);
        }
        this.aM.a();
        h.a().d();
        android.support.v4.content.d.a((Context) this).a(this.bo, new IntentFilter("com.qq.reader.chapter.DownloadSucess"));
        android.support.v4.content.d.a((Context) this).a(this.bo, new IntentFilter("com.qq.reader.chapter.DownloadFailed"));
        if (this.aB == 1) {
            com.qq.reader.common.readertask.g.a().a(new ReaderDBTask() {
                public void run() {
                    OnlineTag g = ReaderPageActivity.this.C.g();
                    if (g != null) {
                        ReaderPageActivity.this.a(g.k());
                    }
                }
            });
        }
    }

    private void aj() {
        Context applicationContext = getApplicationContext();
        int i = Calendar.getInstance().get(6);
        int be = com.qq.reader.appconfig.a.d.be(applicationContext);
        if (i < be) {
            com.qq.reader.appconfig.a.d.bd(applicationContext);
        } else if (i != be) {
            com.qq.reader.appconfig.a.d.bd(applicationContext);
            if (this.aB == 1) {
                i.a("event_report_reader_online_count", true, 0, 0, null, false, true, applicationContext);
                return;
            }
            i.a("event_report_reader_all_count", true, 0, 0, null, false, true, getApplicationContext());
        }
    }

    protected void onPause() {
        this.q.getTopPage().setRunInBackground(true);
        h.a().i();
        h.a().e();
        this.r = false;
        this.q.d(true);
        this.q.v();
        if (ao.a((Context) this, false) < 0) {
            ao.a(getWindow(), false);
        }
        try {
            com.qq.reader.module.readpage.z.b(getHandler(), this);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onPause();
        com.qq.reader.appconfig.a.d.n(getApplicationContext(), com.qq.reader.appconfig.a.d.s);
        com.qq.reader.appconfig.a.d.o(getApplicationContext(), com.qq.reader.appconfig.a.d.q);
        if (com.qq.reader.appconfig.a.d.bz(this) && Build.MODEL.contains("LT29i")) {
            ao.f((Context) this, 1);
        }
        this.F.b();
        long readingTime = (long) this.F.getReadingTime();
        if (readingTime > 5000) {
            com.qq.reader.common.c.a.W = ao.n() + readingTime;
            if (this.J != null) {
                StatisticsManager.a().a("readTime", Long.valueOf(readingTime)).a("bid", Long.valueOf(this.J.getBookNetId())).a(101).c();
            } else {
                StatisticsManager.a().a("readTime", Long.valueOf(readingTime)).a("bid", Integer.valueOf(0)).a(101).c();
            }
        }
        g();
        aX();
        try {
            unregisterReceiver(this.ag);
        } catch (Exception e) {
            if (e != null) {
                com.qq.reader.common.monitor.debug.c.a("unregister", e.toString());
            }
        }
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessage(10009);
        }
        if (this.ar != null) {
            this.ar.cancel();
        }
        if (this.as != null) {
            this.as.cancel();
        }
        if (this.aq.getVisibility() == 0) {
            this.aq.setVisibility(4);
        }
        if (com.qq.reader.appconfig.a.d.n) {
            ao.a((Activity) this, true);
        }
        if (this.s != null) {
            ((SensorManager) getSystemService("sensor")).unregisterListener(this, this.s);
            this.s = null;
        }
        if (getIntent().getBooleanExtra("widget", false) && this.bk) {
            this.bk = false;
        }
        if (isFinishing()) {
            com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                public void run() {
                    com.qq.reader.common.utils.ad.b(ReaderPageActivity.this.getApplicationContext());
                }
            });
        } else {
            com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                public void run() {
                    com.qq.reader.common.utils.ad.a(ReaderPageActivity.this.getIntent(), ReaderPageActivity.this.getApplicationContext());
                }
            });
        }
        android.support.v4.content.d.a((Context) this).a(this.bo);
        new com.qq.reader.common.monitor.k(getApplicationContext()).a();
        if (this.ax != null && this.ax.c()) {
            this.ax.b();
        }
    }

    protected void g() {
        if (this.J != null) {
            if (this.aB == 0) {
                if (this.X != null && com.qq.reader.readengine.model.a.k(this.X.getId())) {
                    if (com.qq.reader.common.db.handle.i.c().b(com.qq.reader.readengine.model.a.p(this.X.getId()), false) != null) {
                        return;
                    }
                }
                Object a = this.q.getBookCore().a(1);
                if (a != null) {
                    a.setHasNewContent(false);
                    if (this.X != null && com.qq.reader.readengine.model.a.l(this.X.getId())) {
                        a.setAuthor(this.X.getAuthor());
                        a.setBookName(this.X.getBookName());
                    }
                    if (!(this.az == null || this.az.c() == null)) {
                        a.setLimitFreeEndTime(this.az.c().x());
                        a.setDiscount(this.az.c().s());
                    }
                    com.qq.reader.common.db.handle.i.c().a((Mark) a, true);
                    if (this.X != null && !com.qq.reader.readengine.model.a.k(this.X.getId())) {
                        c(a);
                    }
                }
            } else if (this.aB == 1 && this.C != null && this.G != null && (this.G instanceof com.qq.reader.readengine.fileparse.d)) {
                Object g = this.C.g();
                com.qq.reader.readengine.kernel.g h = this.q.getBookCore().h();
                if (h != null) {
                    g.c(h.f());
                    g.a(h.g());
                    OnlineChapter onlineChapter = (OnlineChapter) this.J.getMulitFile().getChapterInfo(h.f());
                    if (onlineChapter != null) {
                        g.b(onlineChapter.getChapterName());
                    }
                    g.b(System.currentTimeMillis());
                    v.b().b((OnlineTag) g);
                    ao.a(ReaderApplication.getApplicationImp(), (OnlineTag) g);
                    getIntent().putExtra("com.qq.reader.OnlineTag", g);
                    if (this.ao || com.qq.reader.common.db.handle.i.c().b(g.k(), true) != null) {
                        DownloadBookTask downloadBookTask = null;
                        if (g.q() == 0) {
                            downloadBookTask = ((com.qq.reader.cservice.download.book.e) com.qq.reader.common.download.task.l.b(1001)).a(Long.parseLong(g.k()));
                        }
                        if (downloadBookTask == null) {
                            Mark a2 = this.q.getBookCore().a(4);
                            if (a2 != null) {
                                a2.setHasNewContent(false);
                                if (this.ao) {
                                    int w = g.w();
                                    a2.setFinished(w);
                                    if (w == 0) {
                                        a2.setLastUpdateTime(g.C());
                                        a2.setLastUpdateChapter(g.D());
                                    }
                                }
                                a2.setId(g.k());
                                a2.setBookId(Long.valueOf(g.k()).longValue());
                                a2.setCoverUrl(g.u());
                                a2.setLastReadChapterName(g.h());
                                if (this.ay != null && this.ay.e().t()) {
                                    a2.setLimitFreeEndTime(this.ay.e().x());
                                    a2.setDiscount(this.ay.e().s());
                                }
                                if (a2 != null) {
                                    com.qq.reader.common.db.handle.j.a().a(new com.qq.reader.common.monitor.a.a(g.k(), com.qq.reader.common.monitor.a.b.a(g.k())));
                                    com.qq.reader.common.db.handle.i.c().a(a2, true);
                                    if (this.ao) {
                                        com.qq.reader.appconfig.a.d.h(this.q.getApplicationContext(), String.valueOf(a2.getBookId()));
                                    }
                                }
                                c(g);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void onDestroy() {
        com.qq.reader.common.utils.e.a().b(this);
        com.qq.reader.module.bookchapter.c.a().a(true);
        com.qq.reader.module.bookchapter.c.b();
        com.qq.reader.readengine.b.c.b();
        this.q.getTopPage().q();
        IBook.mSearchList.clear();
        if (this.ay != null) {
            this.ay.d();
        }
        if (this.C != null) {
            this.C.a(null);
            this.C.f();
        }
        if (this.aB == 1) {
            try {
                if (this.ah != null) {
                    android.support.v4.content.d.a((Context) this).a(this.ah);
                }
                if (this.aL != null) {
                    android.support.v4.content.d.a((Context) this).a(this.aL);
                }
            } catch (Throwable th) {
            }
        }
        if (this.G != null) {
            this.G.e();
            this.G.s();
        }
        if (this.q != null) {
            this.q.getTopPage().getmPageCache().f();
            this.q.getTopPage().p().e();
            this.q.getTopPage().p().d();
            this.q.getTopPage().n();
        }
        if (this.bg != null) {
            this.bg.b();
            this.bg = null;
        }
        if (this.q.getTopPage().B()) {
            ba();
        }
        com.qq.reader.plugin.c.e();
        com.qq.reader.appconfig.a.d.l(getApplicationContext(), com.qq.reader.appconfig.a.d.x);
        d.a = false;
        com.qq.reader.module.bookchapter.c.a().g();
        this.mHandler.removeCallbacksAndMessages(null);
        aQ();
        this.aN.b(TaskStateEnum.values(), this.be);
        super.onDestroy();
        System.gc();
        h.a().j();
        if (this.aZ != null) {
            com.qq.reader.module.rookie.presenter.a.a().b(this.aZ);
        }
        if (this.aT != null) {
            this.aT.b();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            this.mHandler.sendEmptyMessage(1115);
        }
        super.onWindowFocusChanged(z);
        this.ap = true;
        if (z) {
            b(true);
        } else {
            b(false);
        }
    }

    private void c(boolean z) {
        if (!z) {
            if (this.q.k()) {
                this.q.b(true);
                af.a((Context) this, (CharSequence) "自动阅读已关闭", 0).a();
            }
            if (this.q.getTopPage().B()) {
                ba();
            }
            if (ReaderTextPageView.d == 1) {
                ReaderTextPageView.r();
                this.q.getTopPage().p().e();
                this.q.getTopPage().p().d();
            } else if (ReaderTextPageView.d == 2) {
                ReaderTextPageView.r();
                this.q.getTopPage().p().e();
            } else if (this.aB != 1) {
                ak();
                finish();
            } else if (aJ()) {
                showFragmentDialog(304);
                j.a(5, 2);
            } else {
                ak();
                finish();
            }
        }
    }

    private void ak() {
        if (this.C != null) {
            OnlineTag g = this.C.g();
            if (g != null) {
                Object k = g.k();
                if (!TextUtils.isEmpty(k) && k.equals(com.qq.reader.common.c.a.dg)) {
                    com.qq.reader.common.c.a.dg = "";
                }
            }
        }
    }

    private boolean k(int i) {
        if (this.aG == null || this.aG.getCurrentState() == 101) {
            return false;
        }
        if (i == 82) {
            return true;
        }
        this.aK = true;
        this.aG.h();
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.ax != null && this.ax.c()) {
            this.ax.b();
            return true;
        } else if (k(i)) {
            i.a("event_B119", null, ReaderApplication.getApplicationImp());
            return true;
        } else if (this.q.getBookCore().o().a(i, keyEvent)) {
            return true;
        } else {
            if (this.q.getTopPage().getTtsModeController().a(i, keyEvent)) {
                return super.onKeyDown(i, keyEvent);
            }
            boolean d = d(i == 82);
            if (ReaderTextPageView.d != 1 || this.q == null) {
                switch (i) {
                    case 4:
                        i.a("event_B3", null, this);
                        c(d);
                        return true;
                    case 24:
                    case Opcodes.IGET_CHAR /*87*/:
                        if (com.qq.reader.appconfig.a.d.am(getApplicationContext())) {
                            l();
                            return true;
                        }
                        break;
                    case 25:
                    case Opcodes.IGET_SHORT /*88*/:
                        if (com.qq.reader.appconfig.a.d.am(getApplicationContext())) {
                            k();
                            return true;
                        }
                        break;
                    case Opcodes.IGET /*82*/:
                        if (this.ax != null && this.ax.d()) {
                            return true;
                        }
                        if (ReaderTextPageView.d == 1) {
                            ReaderTextPageView.r();
                            this.q.getTopPage().p().e();
                            this.q.getTopPage().p().d();
                        } else if (ReaderTextPageView.d == 2) {
                            ReaderTextPageView.r();
                            this.q.getTopPage().p().e();
                        }
                        if (this.q.getTopPage().B() || this.q.d(true)) {
                            return true;
                        }
                        return super.onKeyDown(i, keyEvent);
                }
                return super.onKeyDown(i, keyEvent);
            }
            this.q.getTopPage().p().e();
            this.q.getTopPage().p().d();
            ReaderTextPageView.r();
            this.q.invalidate();
            return true;
        }
    }

    public boolean h() {
        if (this.aB != 1 || !this.ba || !aJ()) {
            return false;
        }
        i.a("event_B368", null, this);
        showFragmentDialog(ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01);
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 24:
            case 25:
                return true;
            default:
                return super.onKeyUp(i, keyEvent);
        }
    }

    public synchronized void i() {
        this.q.p();
    }

    public synchronized void j() {
        this.q.t();
    }

    public void k() {
        boolean z = true;
        this.q.n();
        if (!this.aK) {
            if (this.aB == 1 && this.C != null) {
                OnlineTag g = this.C.g();
                if (g == null) {
                    z = false;
                } else if (g.w() != 1) {
                    z = false;
                }
            }
            this.aH.a(z);
            this.aG.g();
            com.qq.reader.common.monitor.d.a(this.J.getBookNetId(), z);
        }
    }

    public void l() {
        this.q.o();
    }

    private boolean a(com.qq.reader.readengine.kernel.g gVar, com.qq.reader.readengine.kernel.g gVar2) {
        int f = gVar.f();
        int f2 = gVar2.f();
        this.bf = true;
        if (this.aB != 1) {
            return false;
        }
        this.q.h();
        boolean checkExist = this.J.getMulitFile().checkExist(f2);
        if (f2 == f && !checkExist) {
            return false;
        }
        if (checkExist) {
            a(TbsLog.TBSLOG_CODE_SDK_INIT, 0, "", 0, "", "", PageIndex.current, "", null);
            return true;
        }
        this.q.getBookCore().p();
        a(null, f2, PageIndex.current, 1001);
        return false;
    }

    private void a(com.qq.reader.readengine.kernel.g gVar, boolean z, boolean z2, boolean z3) {
        try {
            Object obj;
            OnlineTag g;
            if (this.aB == 1) {
                if (gVar.f() != this.J.getCurIndex()) {
                    obj = null;
                } else if (!((com.qq.reader.readengine.fileparse.d) this.G).a(gVar.f())) {
                    obj = null;
                }
                this.q.h();
                this.q.g();
                if (obj == null) {
                    this.q.getBookCore().a(gVar, z2, z3, z);
                } else {
                    this.q.getBookCore().p();
                    g = this.C.g();
                    g.a(gVar.g());
                    g.g(gVar.f());
                    if (gVar.h()) {
                        g.a(false);
                    }
                    a(g, g.s());
                }
                this.q.e();
            }
            int i = 1;
            this.q.h();
            this.q.g();
            if (obj == null) {
                this.q.getBookCore().p();
                g = this.C.g();
                g.a(gVar.g());
                g.g(gVar.f());
                if (gVar.h()) {
                    g.a(false);
                }
                a(g, g.s());
            } else {
                this.q.getBookCore().a(gVar, z2, z3, z);
            }
            this.q.e();
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a("ReaderPage", "jumpWithPoint", e);
        }
    }

    private boolean b(com.qq.reader.readengine.kernel.g gVar, com.qq.reader.readengine.kernel.g gVar2) {
        if (gVar.f() != gVar2.f()) {
            return false;
        }
        return true;
    }

    private BookmarkView al() {
        if (this.K == null) {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            if (getResources().getConfiguration().orientation == 2) {
                this.L = (width * 60) / 100;
                this.M = height / 3;
                height = width;
            } else {
                this.L = (width * 80) / 100;
                this.M = height / 4;
            }
            this.K = new BookmarkView(this, this.L, this.M, width, height);
            View view = this.K;
            int imgWidth = this.K.getImgWidth();
            if (this.K.getImgHeight() <= height) {
                height = this.K.getImgHeight();
            }
            addContentView(view, new LinearLayout.LayoutParams(imgWidth, height));
        }
        return this.K;
    }

    public void m() {
        i.a("event_B2", null, ReaderApplication.getApplicationImp());
        if (this.bg != null) {
            this.bg.b();
        }
        if (this.q != null) {
            this.q.x();
        }
        p().f_();
        n().f_();
        o().f_();
        if (com.qq.reader.appconfig.a.d.cf(this)) {
            new com.qq.reader.view.b.a(this).f_();
            com.qq.reader.appconfig.a.d.P((Context) this, false);
        }
    }

    private void am() {
        if (this.ax != null && this.ax.c()) {
            this.ax.b();
        }
        this.ax = ap.a(1, this);
        this.ax.a(this.ae);
        this.ax.a((com.qq.reader.view.b.b.a) this);
        this.ax.a();
    }

    private void an() {
        if (this.ax != null && this.ax.c()) {
            this.ax.b();
        }
        this.ax = ap.a(0, this);
        this.ax.a(this.W);
        this.ax.a((com.qq.reader.view.b.b.a) this);
        this.ax.a();
    }

    private void ao() {
        if (this.ax != null && this.ax.c()) {
            this.ax.b();
        }
        this.ax = ap.a(2, this);
        this.ax.a(this.ae);
        this.ax.a((com.qq.reader.view.b.b.a) this);
        this.ax.a();
    }

    public void a(int i) {
        switch (i) {
            case 0:
                ap();
                n().f_();
                return;
            case 1:
                ap();
                o().g(true);
                o().f_();
                return;
            case 2:
                ap();
                o().g(true);
                o().f_();
                return;
            default:
                return;
        }
    }

    public void b(int i) {
        o().g(false);
        aq();
    }

    private void ap() {
        if (this.K != null && this.K.getVisibility() == 0) {
            this.K.b();
            this.q.invalidate();
        }
        if (this.q != null) {
            this.q.getTopPage().p().e();
            this.q.getTopPage().p().d();
        }
    }

    private void aq() {
        getWindow().closeAllPanels();
        p().dismiss();
    }

    public p n() {
        if (this.W == null) {
            this.W = new p(this);
            this.W.a(new com.qq.reader.view.p.a(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.ar();
                }
            });
        }
        return this.W;
    }

    private void ar() {
        ao.b((Activity) this);
        this.q.d(com.qq.reader.appconfig.a.d.L(getApplicationContext()));
        this.aH.d();
        ao.a((Activity) this, !com.qq.reader.appconfig.a.d.n);
        if (this.E != null) {
            this.E.cancel();
        }
        if (this.q.getmPageContext().s()) {
            this.q.g();
            this.q.getTopPage().invalidate();
        }
        if (!(this.q == null || this.q.getmPageContext() == null)) {
            this.q.getmPageContext().w();
        }
        as();
    }

    private void as() {
        aD().a(com.qq.reader.appconfig.a.d.n);
        o().f(com.qq.reader.appconfig.a.d.n);
        aF().a(com.qq.reader.appconfig.a.d.n);
        p().a(com.qq.reader.appconfig.a.d.n);
        ag().a(com.qq.reader.appconfig.a.d.n);
        af().a(com.qq.reader.appconfig.a.d.n);
        if (this.q.getBookCore() != null) {
            this.q.f(this.q.getBookCore() instanceof com.qq.reader.readengine.kernel.a.c).a(com.qq.reader.appconfig.a.d.n);
        }
        if (this.aW == null) {
            return;
        }
        if (com.qq.reader.appconfig.a.d.n) {
            this.aW.setImageResource(R.drawable.redpacket_top_night);
        } else {
            this.aW.setImageResource(R.drawable.redpacket_top);
        }
    }

    private void b(String str) {
        if (this.bg == null) {
            this.bg = af.a(getApplicationContext(), (CharSequence) str, 1500);
        }
        this.bg.a((CharSequence) str);
        this.bg.a();
    }

    private void at() {
        if (this.E != null) {
            this.E.cancel();
        }
        if (au()) {
            n(1);
        } else {
            n(0);
        }
        av();
    }

    private boolean au() {
        if (com.qq.reader.appconfig.a.d.ay(this).contains("baidu")) {
            return true;
        }
        return false;
    }

    private void av() {
        File file = new File(com.qq.reader.common.c.a.aX + "bdttsplugin.zip");
        if (!file.exists()) {
            com.qq.reader.common.readertask.g.a().a(new ReaderDownloadTask(getContext(), file.getPath(), "http://wfqqreader.3g.qq.com/bd/20170308/bdttsplugin.zip"));
        }
    }

    private void aw() {
        if (this.aB != 1 || this.q.getBookCore().o().f() == TbsLog.TBSLOG_CODE_SDK_INIT) {
            Mark a = this.q.getBookCore().a(this.aB == 0 ? 0 : 7);
            if (a == null) {
                b("添加书签失败");
                return;
            }
            if (this.aB == 0) {
                int[] a2 = a(a.getStartPoint(), false);
                ((UserMark) a).setChapterId(a2[0]);
                ((UserMark) a).setChapterOffset((long) a2[1]);
            }
            if (com.qq.reader.common.db.handle.i.c().b((UserMark) a)) {
                com.qq.reader.common.db.handle.i.c().c((UserMark) a);
                b("书签已取消");
            } else {
                com.qq.reader.common.db.handle.i.c().a((UserMark) a);
                al().a();
                this.E.cancel();
            }
            i.a("event_B6", null, this);
            return;
        }
        b("当前页面不支持添加书签");
    }

    private void ax() {
        this.E.cancel();
        i.a("event_B18", null, this);
        c(false);
    }

    private void ay() {
        this.E.cancel();
        c(1);
    }

    private void az() {
        this.E.cancel();
        c(10);
    }

    private void aA() {
        long aB = aB();
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
        intent.putExtra("URL_BUILD_PERE_BOOK_ID", aB);
        intent.setClass(this, NativeBookStoreConfigDetailActivity.class);
        startActivity(intent);
    }

    private long aB() {
        try {
            if (this.aB != 0) {
                return Long.parseLong(this.C.g().k());
            }
            DownloadBookTask a = o.c().a(this.J.getBookPath());
            if (a != null) {
                return a.getId();
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private void aC() {
        String str = "";
        str = "";
        str = "";
        if (this.aB != 0) {
            str = ao.k(this.C.g().b());
            this.C.g().o();
            a(this.C.g().k(), str);
        } else if (this.X != null && (this.X instanceof LocalMark) && this.X.getBookId() <= 0) {
            com.qq.reader.cservice.b.a.a(this, this.X.getBookShortName());
        } else if (this.X != null && this.X.getBookId() > 0) {
            str = ao.k(this.X.getBookName());
            this.X.getAuthor();
            a(String.valueOf(this.X.getBookId()), str);
        }
    }

    private aq aD() {
        Mark a;
        if (this.aw == null) {
            this.aw = new aq(this, 7, (int) getResources().getDimension(R.dimen.read_page_top_bar_width), 1);
            this.aw.d(false);
            aD().a(com.qq.reader.appconfig.a.d.n);
            a = this.q.getBookCore().a(this.aB == 0 ? 0 : 7);
            if (a == null || !com.qq.reader.common.db.handle.i.c().b((UserMark) a)) {
                this.aw.a(getString(R.string.readpage_topbar_bookmark), R.drawable.readpage_topbar_bookmark, R.drawable.readpage_topbar_bookmark_night, 1000, false);
            } else {
                this.aw.a(getString(R.string.readpage_topbar_bookmark_cancel), R.drawable.readpage_topbar_cancel_bookmark, R.drawable.readpage_topbar_cancel_bookmark_night, 1000, false);
            }
            if (this.X != null) {
                a = com.qq.reader.common.db.handle.i.c().b(this.X.getBookId() + "", true);
                if (a != null) {
                    if (a.getPrivateProperty() == 1) {
                        this.aw.a(getString(R.string.readpage_topbar_read_private), R.drawable.readpage_topbar_read_private, R.drawable.readpage_topbar_read_private_night, 1004, false);
                    } else if (a.getPrivateProperty() == 0) {
                        this.aw.a(getString(R.string.readpage_topbar_read_private), R.drawable.readpage_topbar_read_private, R.drawable.readpage_topbar_read_private_night, 1004, true);
                    }
                }
            }
            this.aw.a(getString(R.string.readpage_topbar_search), R.drawable.readpage_topbar_search, R.drawable.readpage_topbar_search_night, 1001, false);
            if (this.aB == 1 || (this.X != null && this.X.getBookId() > 0)) {
                this.aw.a(getString(R.string.readpage_topbar_detail), R.drawable.readpage_topbar_detail, R.drawable.readpage_topbar_detail_night, 1002, false);
            }
            if (this.aB == 1 || (this.X != null && this.X.getBookId() > 0)) {
                this.aw.a(getString(R.string.readpage_topbar_share), R.drawable.readpage_topbar_share, R.drawable.readpage_topbar_share_night, 1003, false);
            }
            this.aw.a(new com.qq.reader.view.a.a(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public boolean a(int i) {
                    if (this.a.E != null) {
                        this.a.E.cancel();
                    }
                    switch (i) {
                        case 1000:
                            this.a.aw();
                            break;
                        case 1001:
                            this.a.ay();
                            break;
                        case 1002:
                            this.a.aA();
                            i.a("event_B45", null, this.a);
                            break;
                        case 1003:
                            i.a("event_B42", null, this.a);
                            this.a.aC();
                            break;
                        case 1004:
                            Object hashMap = new HashMap();
                            if (this.a.X.getPrivateProperty() == 1) {
                                com.qq.reader.module.bookshelf.d.b(this.a, this.a.X.getBookId(), new com.qq.reader.common.readertask.ordinal.c(this) {
                                    final /* synthetic */ AnonymousClass64 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                        this.a.a.X.setPrivateProperty(0);
                                        com.qq.reader.common.readertask.g.a().a(new ReaderPageActivity$33$1$1(this));
                                    }

                                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                        this.a.a.mHandler.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                af.a(this.a.a.a.getApplicationContext(), (int) R.string.toast_catgory_error, 1).a();
                                            }
                                        });
                                    }
                                }, new Runnable(this) {
                                    final /* synthetic */ AnonymousClass64 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        if (this.a.a.aB == 1) {
                                            this.a.a.F();
                                        }
                                    }
                                });
                                hashMap.put("readPrivate", "1");
                            } else {
                                com.qq.reader.module.bookshelf.d.a(this.a, this.a.X.getBookId(), new com.qq.reader.common.readertask.ordinal.c(this) {
                                    final /* synthetic */ AnonymousClass64 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                        this.a.a.X.setPrivateProperty(1);
                                        com.qq.reader.common.readertask.g.a().a(new ReaderPageActivity$33$3$1(this));
                                    }

                                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                        af.a(this.a.a.getApplicationContext(), (int) R.string.toast_catgory_error, 1).a();
                                    }
                                }, new Runnable(this) {
                                    final /* synthetic */ AnonymousClass64 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        if (this.a.a.aB == 1) {
                                            this.a.a.F();
                                        }
                                    }
                                });
                                hashMap.put("readPrivate", "0");
                            }
                            i.a("event_B184", hashMap, this.a);
                            break;
                    }
                    return false;
                }
            });
        } else {
            Mark a2 = this.q.getBookCore().a(this.aB == 0 ? 0 : 7);
            if (this.aB == 0 && a2 != null) {
                int[] a3 = a(a2.getStartPoint(), false);
                ((UserMark) a2).setChapterId(a3[0]);
                ((UserMark) a2).setChapterOffset((long) a3[1]);
            }
            if (a2 == null || !com.qq.reader.common.db.handle.i.c().b((UserMark) a2)) {
                this.aw.b(getString(R.string.readpage_topbar_bookmark), R.drawable.readpage_topbar_bookmark, R.drawable.readpage_topbar_bookmark_night, 1000, false);
            } else {
                this.aw.b(getString(R.string.readpage_topbar_bookmark_cancel), R.drawable.readpage_topbar_cancel_bookmark, R.drawable.readpage_topbar_cancel_bookmark_night, 1000, false);
            }
            if (this.X != null) {
                a = com.qq.reader.common.db.handle.i.c().b(this.X.getBookId() + "", true);
                if (a == null) {
                    this.aw.b(1004);
                } else if (a.getPrivateProperty() == 1) {
                    this.aw.b(getString(R.string.readpage_topbar_read_private), R.drawable.readpage_topbar_read_private, R.drawable.readpage_topbar_read_private_night, 1004, false);
                } else if (a.getPrivateProperty() == 0) {
                    this.aw.b(getString(R.string.readpage_topbar_read_private), R.drawable.readpage_topbar_read_private, R.drawable.readpage_topbar_read_private_night, 1004, true);
                }
            }
            this.aw.g();
        }
        return this.aw;
    }

    public z o() {
        if (this.ae == null) {
            this.ae = new z(this, this.aB, this.q.getBookCore().a(), this.X);
            this.ae.a(new com.qq.reader.view.z.a(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a(int i, View view) {
                    switch (i) {
                        case 1000:
                            try {
                                this.a.ax();
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        case 1003:
                            if (this.a.aD().f()) {
                                this.a.aD().cancel();
                                return;
                            }
                            this.a.aD().f_();
                            i.a("event_B183", null, this.a);
                            return;
                        case 1004:
                            this.a.az();
                            return;
                        case 1008:
                            this.a.Q();
                            this.a.E.cancel();
                            i.a("event_B81", null, ReaderApplication.getApplicationImp());
                            j.a(80, 1);
                            return;
                        case 1009:
                            if (this.a.E != null) {
                                this.a.E.cancel();
                            }
                            try {
                                if (this.a.J != null && this.a.aB == 1 && this.a.ay != null) {
                                    com.qq.reader.module.bookchapter.online.f e2 = this.a.ay.e();
                                    int i2 = 0;
                                    if (e2 == null || e2.y() == null || e2.y().o() <= 0 || this.a.C == null) {
                                        i2 = 1;
                                    } else {
                                        String valueOf = String.valueOf(e2.y().o());
                                        if (this.a.q == null || this.a.q.getBookCore() == null) {
                                            i2 = 1;
                                        } else {
                                            com.qq.reader.readengine.kernel.g h = this.a.q.getBookCore().h();
                                            if (h != null) {
                                                int f = h.f();
                                                Bundle bundle = new Bundle();
                                                if (f <= e2.y().p()) {
                                                    bundle.putInt("from", 1);
                                                    bundle.putString("AUDIO_PLAY_RELATED_BOOK_BID", e2.y().d());
                                                    bundle.putParcelable("related_onlinetag", this.a.C.g());
                                                } else {
                                                    bundle.putInt("from", 1);
                                                    bundle.putString("AUDIO_PLAY_RELATED_BOOK_BID", e2.y().d());
                                                    Parcelable g = this.a.C.g();
                                                    if (g != null) {
                                                        g.g(1);
                                                        g.c(1);
                                                    }
                                                    bundle.putParcelable("related_onlinetag", g);
                                                }
                                                com.qq.reader.common.utils.o.a(this.a, valueOf, f, bundle, null);
                                                i.a("event_C230", null, this.a.getApplicationContext());
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                    if (i2 != 0) {
                                        if (this.a.q.getBookCore().o().c().p() == TbsLog.TBSLOG_CODE_SDK_INIT) {
                                            if (this.a.au()) {
                                                com.qq.reader.plugin.tts.n.f().a(this.a);
                                            } else {
                                                com.qq.reader.plugin.tts.n.f().a(this.a);
                                            }
                                            this.a.av();
                                            if (com.qq.reader.plugin.tts.n.f().q()) {
                                                i.a("event_B268", null, ReaderApplication.getApplicationImp());
                                                com.qq.reader.common.utils.o.a(this.a, this.a.C.g(), null);
                                            } else {
                                                com.qq.reader.plugin.tts.n.f().a(this.a.mHandler);
                                                com.qq.reader.plugin.tts.n.f().b();
                                                return;
                                            }
                                        } else if (this.a.C == null || this.a.q.getBookCore().o().c().p() == 1008) {
                                            this.a.b("当前页面不支持人声朗读");
                                            return;
                                        } else {
                                            i2 = new g(this.a, this.a.C.g()).b();
                                            if (i2 == 2) {
                                                this.a.b("请先购买本章");
                                                return;
                                            } else if (i2 == 1) {
                                                this.a.b("请先购买本书");
                                                return;
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                    this.a.aE();
                                    return;
                                } else if (this.a.J != null && this.a.aB == 0) {
                                    this.a.at();
                                    return;
                                } else {
                                    return;
                                }
                            } catch (Exception e3) {
                                com.qq.reader.common.monitor.debug.c.e("Error", e3.getMessage());
                                return;
                            }
                        default:
                            return;
                    }
                }
            });
            if (this.aB == 1 && this.ay != null) {
                com.qq.reader.module.bookchapter.online.f e = this.ay.e();
                if (e != null && e.y().o() > 0) {
                    this.ae.a(true);
                    this.bh = true;
                }
            }
            this.ae.a(new OnShowListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    aj.b(this.a, false);
                }
            });
        }
        if (this.bh) {
            i.a("event_C229", null, getApplicationContext());
        }
        return this.ae;
    }

    private void aE() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("BROADCAST_ACTION_TTS_START");
        intentFilter.addAction("BROADCAST_ACTION_TTS_PAUSE");
        intentFilter.addAction("BROADCAST_ACTION_TTS_RESUME");
        intentFilter.addAction("BROADCAST_ACTION_TTS_STOP");
        intentFilter.addAction("BROADCAST_ACTION_TTS_SWITCH_TTS");
        intentFilter.addAction("BROADCAST_ACTION_TTS_SWITCH_CHAPTER");
        intentFilter.addAction(com.qq.reader.plugin.audiobook.core.e.E);
        android.support.v4.content.d.a((Context) this).a(this.bn, intentFilter);
    }

    public ab p() {
        this.p = false;
        n();
        if (this.E == null) {
            this.E = new ab(this, 4);
            if (this.q.getBookCore().a()) {
                if (this.aB == 0) {
                    this.E.a(0, "目录", R.drawable.menu_icon_catalog, R.drawable.menu_icon_catalog_night, false);
                } else if (this.aB == 1) {
                    this.E.a(11, "目录", R.drawable.menu_icon_catalog, R.drawable.menu_icon_catalog_night, false);
                }
                this.E.a(2, "进度", R.drawable.menu_icon_jump, R.drawable.menu_icon_jump_night, false, 0);
                this.E.a(9, "设置", R.drawable.menu_icon_setting, R.drawable.menu_icon_setting_night, false);
                if (this.aB == 1 || (this.X != null && this.X.getBookId() > 0)) {
                    int i;
                    if (this.ay == null || this.ay.e() == null) {
                        i = 0;
                    } else {
                        i = this.ay.e().y().H();
                    }
                    this.E.a(14, "评论", R.drawable.readerpage_comment_icon_selector, R.drawable.readerpage_comment_icon_selector_night, false, i);
                } else {
                    this.E.i();
                }
            } else {
                this.E.a(4, "返回", R.drawable.readpage_topbar_back, R.drawable.readpage_topbar_back_night, false);
                this.E.i();
            }
            this.E.a(new com.qq.reader.view.ab.b(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public boolean a(int i) {
                    return this.a.c(i);
                }
            });
            this.E.a(new com.qq.reader.view.ab.c(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public com.qq.reader.view.k a(int i) {
                    return null;
                }
            });
            this.E.a(new OnCancelListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.ax != null && this.a.ax.d()) {
                        this.a.ax.b();
                    }
                    if (this.a.aD().f()) {
                        this.a.aD().cancel();
                    }
                    if (this.a.o().f()) {
                        this.a.o().cancel();
                    }
                    if (this.a.n().f()) {
                        this.a.n().cancel();
                    }
                    this.a.getWindow().closeAllPanels();
                }
            });
        } else {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                this.E.a(12, "竖屏", R.drawable.menu_icon_landscape, false);
            } else {
                this.E.a(12, "横屏", R.drawable.menu_icon_landscape, false);
            }
            if (this.aB == 1 || (this.X != null && this.X.getBookId() > 0)) {
                int i2;
                if (this.ay == null || this.ay.e() == null) {
                    i2 = 0;
                } else {
                    i2 = this.ay.e().y().H();
                }
                this.E.a(14, "评论", R.drawable.readerpage_comment_icon_selector, false, i2);
            }
            this.E.h();
        }
        if (!(this.E == null || this.C == null)) {
            OnlineTag g = this.C.g();
            if (g != null) {
                ac.a(this, this.E.g(), g.k(), g.b());
            }
        }
        return this.E;
    }

    private com.qq.reader.view.g aF() {
        if (this.bi == null) {
            int i = (this.X == null || !(com.qq.reader.readengine.model.a.l(this.X.getBookName()) || this.X.getBookName().toLowerCase().endsWith(".epub"))) ? 0 : 3;
            this.bi = new com.qq.reader.view.g(this, i);
            this.bi.a(new com.qq.reader.view.g.b(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    ao.b(this.a);
                }

                public void b() {
                    com.qq.reader.appconfig.a.d.n = !com.qq.reader.appconfig.a.d.n;
                    com.qq.reader.appconfig.a.d.i(this.a, com.qq.reader.appconfig.a.d.n);
                    this.a.ar();
                    if (com.qq.reader.appconfig.a.d.n) {
                        i.a("event_B41", null, this.a);
                    } else {
                        i.a("event_B41", null, this.a);
                    }
                }
            });
            this.bi.a(new com.qq.reader.view.g.a(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a(float f) {
                    com.qq.reader.common.monitor.f.d(APMidasPayAPI.ENV_TEST, "zoom == " + f);
                    if (this.a.J != null) {
                        this.a.J.mTurePageCmd = 102;
                    }
                    this.a.q.a(f);
                    this.a.aH.e();
                    i.a("event_B33", null, this.a);
                }

                public void a() {
                    this.a.mHandler.sendEmptyMessage(1201);
                }
            });
            this.bi.a(new com.qq.reader.view.g.c(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    if (i == 9) {
                        this.a.e(8);
                        this.a.af().f_();
                        this.a.bi.dismiss();
                        i.a("event_B13", null, this.a.getContext());
                        return;
                    }
                    this.a.e(i);
                    Map hashMap = new HashMap();
                    hashMap.put("background_color", i + "");
                    i.a("event_N16", hashMap, this.a);
                }
            });
            this.bi.a(new com.qq.reader.view.g.d(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(int i) {
                    switch (i) {
                        case 1:
                            this.a.aG();
                            return;
                        case 2:
                            this.a.at();
                            i.a("event_B73", null, this.a);
                            return;
                        case 3:
                            this.a.q();
                            i.a("event_B16", null, this.a);
                            return;
                        case 4:
                            if (this.a.G != null && (this.a.G instanceof com.qq.reader.readengine.kernel.a.a)) {
                                this.a.w().g();
                            }
                            this.a.w().a(true);
                            i.a("event_B15", null, this.a);
                            return;
                        default:
                            return;
                    }
                }
            });
        }
        return this.bi;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!this.ap) {
            return false;
        }
        if (this.K != null && this.K.getVisibility() == 0) {
            this.K.b();
            this.q.invalidate();
        }
        if (this.q != null) {
            this.q.getTopPage().p().e();
            this.q.getTopPage().p().d();
        }
        if (com.qq.reader.common.c.a.A) {
            this.mHandler.sendEmptyMessageDelayed(1215, 200);
        } else {
            m();
        }
        i.a("event_B1", null, this);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return c(menuItem.getItemId());
    }

    protected boolean c(int i) {
        switch (i) {
            case 0:
            case 11:
                if (this.W != null && this.W.f()) {
                    this.W.cancel();
                }
                Intent intent = new Intent();
                intent.setClass(this, NewChapterViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("resultBook", new TabViewBookInfo(this.aB, this.J.getBookNetId(), this.J.getBookPath(), this.J.getBookName(), this.J.getEncoding(), this.J.getFileCount(), false));
                if (this.aB == 1) {
                    bundle.putParcelable("resultOnlinetag", this.C.g());
                    bundle.putLong("bookFileLength", this.G.v());
                }
                if (this.G != null) {
                    bundle.putLong("resultMarkP", this.q.getBookCore().h().e());
                } else {
                    bundle.putLong("resultMarkP", -1);
                }
                intent.putExtras(bundle);
                this.bk = true;
                startActivityForResult(intent, 0);
                i.a("event_B5", null, this);
                return true;
            case 1:
                i.a("event_B32", null, this);
                StatisticsManager.a().a("event_B32", null);
                if (this.bj == null) {
                    this.bj = new ae(this);
                    this.bj.a(this.G);
                    this.bj.a(this);
                }
                this.bj.f_();
                return true;
            case 2:
                ag().a(this.q.getBookCore().g().doubleValue() * 100.0d);
                if (this.aB == 1) {
                    ag().b(this.J.getFileCount());
                }
                b bVar = new b();
                a(bVar);
                this.V.a(bVar.a, bVar.b);
                i.a("event_B9", null, this);
                return true;
            case 4:
                finish();
                return true;
            case 5:
                d().f_();
                i.a("event_B12", null, this);
                return true;
            case 6:
                f().f_();
                i.a("event_B33", null, this);
                return true;
            case 7:
                aH().f_();
                return true;
            case 8:
                finish();
                return true;
            case 9:
                aF().f_();
                i.a("event_B39", null, this);
                return false;
            case 10:
                i.a("event_B34", null, this);
                if (this.C == null) {
                    a(this.X);
                    return false;
                } else if (b(this.C.g())) {
                    return false;
                } else {
                    af.a(getApplicationContext(), (int) R.string.online_download_error, 0).a();
                    return false;
                }
            case 12:
                aG();
                return true;
            case 14:
                com.qq.reader.common.utils.o.a((Activity) this, Long.valueOf(this.J.getBookNetId()), this.J.getBookName(), 0, new JumpActivityParameter());
                i.a("event_B44", null, this);
                return true;
            default:
                return false;
        }
    }

    private void aG() {
        if (this.J != null) {
            this.J.mTurePageCmd = 102;
        }
        this.p = true;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            int i = 0;
        } else {
            boolean z = true;
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1114;
        if (i == 0) {
            obtainMessage.arg1 = 1;
            com.qq.reader.appconfig.a.d.l(getContext(), 1);
        } else {
            obtainMessage.arg1 = 0;
            com.qq.reader.appconfig.a.d.l(getContext(), 0);
        }
        this.mHandler.sendMessage(obtainMessage);
        i.a("event_B10", null, this);
    }

    private y aH() {
        if (this.bl == null) {
            this.bl = new y(this);
            this.bl.a((com.qq.reader.view.y.a) this);
        }
        return this.bl;
    }

    public void q() {
        CharSequence charSequence;
        if (this.q.getTopPage().h() && this.q.getTopPage().h()) {
            charSequence = "双翻页模式下无法开启自动阅读模式";
            if (this.bg == null) {
                this.bg = af.a(getApplicationContext(), charSequence, 1500);
            } else {
                this.bg.a(charSequence);
            }
            this.bg.a();
        } else if (this.q.getBookCore().o().c().p() != TbsLog.TBSLOG_CODE_SDK_INIT) {
            charSequence = "当前页面下无法开启自动阅读模式";
            if (this.bg == null) {
                this.bg = af.a(getApplicationContext(), charSequence, 1500);
            } else {
                this.bg.a(charSequence);
            }
            this.bg.a();
        } else {
            this.q.i();
        }
    }

    public void r() {
        this.q.b(true);
    }

    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            this.q.g();
            this.q.getTopPage().invalidate();
            final OnlineTag onlineTag;
            if (i == 0) {
                if (this.aB == 1) {
                    com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                        public void run() {
                            ReaderPageActivity.this.J.initFileList(ReaderPageActivity.this.C.g());
                        }
                    });
                }
                if (-1 == i2 && intent != null) {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        if (this.aB != 0) {
                            onlineTag = (OnlineTag) extras.getParcelable("resultOnlinetag");
                            if (onlineTag != null) {
                                this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                                    final /* synthetic */ ReaderPageActivity b;

                                    public void a(int i) {
                                        switch (i) {
                                            case 1:
                                                onlineTag.a(false);
                                                com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                                                gVar.a(true);
                                                gVar.a(onlineTag.s(), onlineTag.i());
                                                this.b.a(gVar, false, true, false);
                                                return;
                                            default:
                                                return;
                                        }
                                    }
                                };
                                onlineTag.a(false);
                                com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                                gVar.a(onlineTag.s(), onlineTag.i());
                                gVar.a(true);
                                a(gVar, false, true, false);
                                this.q.getBookCore().p();
                            }
                        } else if (this.X == null || this.X.getBookId() <= 0 || !com.qq.reader.readengine.model.a.k(this.X.getId()) || extras.getBoolean(ReaderPagerChapterFragment.RESULT_BOOKMARK_FREE, true)) {
                            long j = extras.getLong(ReaderPagerChapterFragment.RESULT_BOOKMARK_POINT);
                            com.qq.reader.readengine.kernel.g gVar2 = new com.qq.reader.readengine.kernel.g();
                            gVar2.a(j);
                            if (this.J != null) {
                                this.J.mTurePageCmd = 101;
                            }
                            a(gVar2, false, false, true);
                            this.q.getBookCore().p();
                            if (this.q.getTopPage().h()) {
                                this.q.getTopPage().i();
                            }
                        } else {
                            a(this.X);
                            return;
                        }
                        if (this.aG.getCurrentState() != 101) {
                            this.mHandler.post(new Runnable(this) {
                                final /* synthetic */ ReaderPageActivity a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.aG.h();
                                }
                            });
                        }
                    }
                }
            } else if (10 == i) {
                if (i2 == -1) {
                    this.mHandler.sendEmptyMessage(1200);
                }
            } else if (1000 == i) {
                n(0);
            } else if (100 == i) {
                if (intent != null) {
                    String stringExtra = intent.getStringExtra("selected_role");
                    if (stringExtra != null && stringExtra.length() > 0 && !com.qq.reader.appconfig.a.d.ay(getApplicationContext()).equalsIgnoreCase(stringExtra) && com.qq.reader.plugin.tts.n.f().a(stringExtra)) {
                        com.qq.reader.appconfig.a.d.o(getApplicationContext(), stringExtra);
                        com.qq.reader.plugin.tts.n.f().m();
                    }
                }
            } else if (i == 20001) {
                if (i2 == 0) {
                    if (this.x != null && this.x.f()) {
                        this.x.o().sendEmptyMessage(1230);
                    }
                    if (this.z || this.A) {
                        S();
                    }
                    if (com.qq.reader.module.rookie.presenter.a.a().c) {
                        com.qq.reader.module.rookie.presenter.a.a().a(6, true);
                    }
                    if (this.aB == 1) {
                        r0 = this.q.getBookCore().o().c();
                        if (r0 != null && r0.p() == 1009) {
                            if (r0.a()) {
                                r5 = getString(R.string.paypage_pay_chapter);
                            } else {
                                r5 = getString(R.string.paypage_pay_all);
                            }
                            r6 = getString(R.string.paypage_tip_needpurchase);
                            if (this.ay != null && this.ay.e().n()) {
                                r5 = getString(R.string.paypage_pay_all_subscription);
                                r6 = getString(R.string.paypage_tip_needpurchase_subscription);
                            }
                            a(1003, r0.o(), r0.l(), r0.m(), r5, r6, r0.u(), "", r0.t());
                            r0 = Message.obtain();
                            r0.what = 1212;
                            r0.obj = this.q.getBookCore().o().c();
                            this.mHandler.sendMessageDelayed(r0, 150);
                        }
                    }
                }
            } else if (i == 20002) {
                if (i2 == 0) {
                    if (this.aB == 1) {
                        r0 = this.q.getBookCore().o().c();
                        if (r0.a()) {
                            r5 = getString(R.string.paypage_pay_chapter);
                        } else {
                            r5 = getString(R.string.paypage_pay_all);
                        }
                        r6 = getString(R.string.paypage_tip_needpurchase);
                        if (this.ay != null && this.ay.e().n()) {
                            r5 = getString(R.string.paypage_pay_all_subscription);
                            r6 = getString(R.string.paypage_tip_needpurchase_subscription);
                        }
                        a(1003, r0.o(), r0.l(), r0.m(), r5, r6, r0.u(), "", r0.t());
                        r0 = Message.obtain();
                        r0.what = 1212;
                        r0.obj = this.q.getBookCore().o().c();
                        r0.arg1 = 100;
                        this.mHandler.sendMessageDelayed(r0, 150);
                    }
                } else if (i2 == 5) {
                    new JSLogin(this).toLogin();
                }
            } else if (i == 1000) {
                this.aK = true;
                if (i2 == 1001) {
                    R();
                }
            } else if (i == 1002) {
                this.aK = true;
                if (this.C != null) {
                    onlineTag = this.C.g();
                    com.qq.reader.common.db.handle.b.a().a(onlineTag.k(), onlineTag.g(), false, this.mHandler);
                    Message obtain = Message.obtain();
                    obtain.arg1 = onlineTag.g();
                    this.q.a(obtain);
                }
            }
        } catch (Throwable e) {
            com.qq.reader.common.monitor.b.a(e);
        }
    }

    private void aI() {
        if (this.aB == 1) {
            String str;
            com.qq.reader.module.readpage.h.b c = this.q.getBookCore().o().c();
            String string = c.a() ? getString(R.string.paypage_pay_chapter) : getString(R.string.paypage_pay_all);
            String string2 = getString(R.string.paypage_tip_needpurchase);
            if (this.ay == null || !this.ay.e().n()) {
                str = string;
            } else {
                string = getString(R.string.paypage_pay_all_subscription);
                string2 = getString(R.string.paypage_tip_needpurchase_subscription);
                str = string;
            }
            a(1003, c.o(), c.l(), c.m(), str, string2, c.u(), "", c.t());
            Message obtain = Message.obtain();
            obtain.what = 1212;
            obtain.obj = this.q.getBookCore().o().c();
            obtain.arg1 = 100;
            this.mHandler.sendMessageDelayed(obtain, 150);
        }
    }

    private boolean a(View view, float f, float f2) {
        boolean z = false;
        if (this.H == null || this.H.getVisibility() != 0) {
            return false;
        }
        switch (com.qq.reader.module.readpage.ab.a(view, f, f2)) {
            case 2:
                z = true;
                break;
        }
        return d(z);
    }

    private boolean d(boolean z) {
        if (this.H == null || this.H.getVisibility() != 0) {
            return false;
        }
        if (z) {
            openOptionsMenu();
        }
        this.I.setImageBitmap(null);
        this.I = null;
        this.H.setVisibility(8);
        this.H.b();
        this.H.removeAllViews();
        ((ViewGroup) getWindow().getDecorView().getRootView()).removeView(this.H);
        this.H = null;
        return true;
    }

    protected boolean s() {
        try {
            if (this.q.getBookCore().o().f() == 1000) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private boolean aJ() {
        OnlineTag g = this.C.g();
        if (g != null && aK() && this.G != null && com.qq.reader.common.db.handle.i.c().b(g.k(), true) == null) {
            if ((g.q() == 0 ? ((com.qq.reader.cservice.download.book.e) com.qq.reader.common.download.task.l.b(1001)).a(Long.parseLong(g.k())) : null) == null) {
                return true;
            }
        }
        return false;
    }

    private boolean aK() {
        if (this.C == null) {
            return false;
        }
        OnlineTag g = this.C.g();
        if (g == null) {
            return false;
        }
        int g2 = g.g();
        if (g2 > 0 && g2 > g.n()) {
            g.n();
        }
        return true;
    }

    private void aL() {
        if (this.C != null) {
            OnlineTag g = this.C.g();
            if (g != null && g.q() == 0) {
                com.qq.reader.common.imageloader.c.a((Context) this).c(g.u());
            }
            if (g != null) {
                StatisticsManager.a().e(g.k()).d(com.qq.reader.common.monitor.l.a(g.k())).a(8).c();
            }
        }
    }

    private void c(String str) {
        com.qq.reader.cservice.download.book.g gVar = new com.qq.reader.cservice.download.book.g(str);
        gVar.e(com.qq.reader.common.monitor.a.b.a(str));
        if (this.aB == 0) {
            gVar.f("qteb");
        }
        com.qq.reader.cservice.download.book.h hVar = new com.qq.reader.cservice.download.book.h(getApplicationContext(), gVar);
        hVar.a(this);
        aV();
        hVar.start();
    }

    public boolean b(OnlineTag onlineTag) {
        int i = 0;
        if (onlineTag.q() != 0) {
            return false;
        }
        if (onlineTag.p() != null && onlineTag.p().length() != 0) {
            try {
                long parseLong = Long.parseLong(onlineTag.k());
                DownloadBookTask downloadBookTask = new DownloadBookTask(parseLong, onlineTag.b(), onlineTag.o(), onlineTag.p(), onlineTag.u(), onlineTag.n(), onlineTag.B(), onlineTag.A(), -1);
                downloadBookTask.setNetChannel(com.qq.reader.common.monitor.a.b.a(onlineTag.k()));
                JSDownLoad.downLoadBook(downloadBookTask, (com.qq.reader.cservice.download.book.e) com.qq.reader.common.download.task.l.b(1001), this, true);
                com.qq.reader.common.db.handle.i.c().c(parseLong, this.J.getBookPath());
                return true;
            } catch (NumberFormatException e) {
                com.qq.reader.common.monitor.f.a("ONLINE", "downLoadWholeBook NumberFormatException : " + e.toString());
                return false;
            }
        } else if (com.qq.reader.common.login.c.b()) {
            if (this.ay == null) {
                this.ay = new g(getApplicationContext(), onlineTag.z());
            }
            this.ay.a(this.mHandler);
            int c = this.ay.c();
            if (c != -2) {
                this.ay.d();
            }
            switch (c) {
                case -2:
                    aV();
                    return true;
                case 1:
                    c(onlineTag.k());
                    return true;
                case 2:
                    if (this.ay != null && this.ay.e().n()) {
                        i = 1;
                    }
                    if (i != 0) {
                        c(onlineTag.k());
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("com.qq.reader.OnlineTag", onlineTag);
                        intent.setClass(getApplicationContext(), ChapterBatDownloadActivity.class);
                        startActivity(intent);
                        i.a("event_B57", null, getApplicationContext());
                        StatisticsManager.a().a("event_B57", null);
                        j.a(56, 1);
                    }
                    return true;
                case 3:
                    return false;
                default:
                    return false;
            }
        } else {
            final OnlineTag onlineTag2 = onlineTag;
            this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                final /* synthetic */ ReaderPageActivity b;

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.b.b(onlineTag2);
                            return;
                        default:
                            return;
                    }
                }
            };
            startLogin();
            return true;
        }
    }

    public boolean a(final Mark mark) {
        com.qq.reader.common.monitor.debug.c.e("ReaderPage", "downLoadHardCoverBook->");
        if (!TextUtils.isEmpty(mark.getDownloadUrl())) {
            long bookId = mark.getBookId();
            DownloadBookTask downloadBookTask = new DownloadBookTask(bookId, mark.getBookShortName(), mark.getAuthor(), mark.getDownloadUrl(), mark.getImageURI(), 0, "qteb", 1, -1);
            downloadBookTask.setNetChannel(com.qq.reader.common.monitor.a.b.a(String.valueOf(bookId)));
            JSDownLoad.downLoadBook(downloadBookTask, (com.qq.reader.cservice.download.book.e) com.qq.reader.common.download.task.l.b(1001), this, true);
            com.qq.reader.common.db.handle.i.c().c(bookId, this.J.getBookPath());
            return true;
        } else if (com.qq.reader.common.login.c.b()) {
            OnlineTag onlineTag = new OnlineTag(String.valueOf(mark.getBookId()), "", 0);
            if (this.az == null) {
                this.az = new com.qq.reader.module.bookchapter.a.a(getApplicationContext(), onlineTag);
            }
            this.az.a(this.mHandler);
            int a = this.az.a();
            if (a != -2) {
                this.az.b();
            }
            switch (a) {
                case -2:
                    aV();
                    return true;
                case 1:
                    c(onlineTag.k());
                    return true;
                default:
                    return false;
            }
        } else if (this.bm) {
            return true;
        } else {
            this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                final /* synthetic */ ReaderPageActivity b;

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.b.a(mark);
                            return;
                        default:
                            return;
                    }
                }
            };
            startLogin();
            this.bm = true;
            return true;
        }
    }

    public void c(final OnlineTag onlineTag) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ ReaderPageActivity b;

            public void run() {
                if (this.b.C != null) {
                    onlineTag.g(onlineTag.g());
                    this.b.C.g().b(true);
                    this.b.a(onlineTag, onlineTag.g());
                    return;
                }
                this.b.a(onlineTag, -12);
            }
        });
    }

    protected Dialog createDialog(final int i, Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) com.qq.reader.module.readpage.q.a(this, i, null);
        String str = "";
        switch (i) {
            case 300:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.aR();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!this.a.aS()) {
                            this.a.finish();
                        }
                    }
                });
                return alertDialog;
            case 301:
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.startLogin();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!this.a.aS()) {
                            this.a.finish();
                        }
                    }
                });
                return alertDialog;
            case 302:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.a.aB == 1 && this.a.C != null) {
                            Intent intent = new Intent();
                            intent.setClass(this.a, WebBrowserForContents.class);
                            intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.a(this.a, Long.parseLong(this.a.C.g().k())));
                            intent.setFlags(SigType.WLOGIN_QRPUSH);
                            this.a.startActivity(intent);
                        }
                        this.a.finish();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!this.a.aS()) {
                            this.a.finish();
                        }
                    }
                });
                return alertDialog;
            case 303:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        com.qq.reader.common.login.c.a();
                        this.a.startLogin();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!this.a.aS()) {
                            this.a.finish();
                        }
                    }
                });
                return alertDialog;
            case 304:
            case 305:
            case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01 /*321*/:
                alertDialog.a((CharSequence) "加入书架", new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.ao = true;
                        this.b.aL();
                        i.a("event_C15", null, this.b.getApplicationContext());
                        if (i == ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01) {
                            i.a("event_B369", null, this.b.getApplicationContext());
                        }
                        if (!TextUtils.isEmpty(this.b.aS)) {
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, this.b.aS);
                            i.a("event_A156", hashMap, this.b.getApplicationContext());
                        }
                        if (!this.b.a(i, dialogInterface)) {
                            if (i == 305) {
                                this.b.bh();
                            } else {
                                this.b.finish();
                            }
                        }
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        j.a(15, 2);
                        i.a("event_C16", null, this.b.getApplicationContext());
                        StatisticsManager.a().a("event_C16", null);
                        if (!this.b.a(i, dialogInterface)) {
                            if (i == 305) {
                                this.b.bh();
                            } else {
                                this.b.finish();
                            }
                        }
                    }
                });
                i.a("event_C6", null, ReaderApplication.getApplicationImp());
                return alertDialog;
            case 306:
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.startLogin();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!this.a.aS()) {
                            if (this.a.G == null || !(this.a.G instanceof com.qq.reader.readengine.fileparse.d)) {
                                this.a.finish();
                            }
                        }
                    }
                });
                return alertDialog;
            case 307:
                alertDialog.a((int) R.string.dialog_drm_font_download_positive_button, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        final com.qq.reader.plugin.c cVar = new com.qq.reader.plugin.c(this.a.getApplicationContext(), this.a.mHandler);
                        if (com.qq.reader.common.login.c.b()) {
                            cVar.c();
                        } else {
                            this.a.setLoginNextTask(new com.qq.reader.common.login.a(this) {
                                final /* synthetic */ AnonymousClass115 b;

                                public void a(int i) {
                                    cVar.c();
                                }
                            });
                            this.a.startLogin();
                        }
                        com.qq.reader.appconfig.a.d.F(this.a.getApplicationContext(), true);
                    }
                });
                return alertDialog;
            case 400:
                alertDialog.b((int) R.string.dialog_change_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.Q = false;
                        com.qq.reader.common.db.handle.i.c().c(this.a.X.getId());
                        com.qq.reader.common.db.handle.i.c().c(this.a.X.getBookId(), this.a.X.getId());
                        com.qq.reader.common.db.handle.i.c().c(this.a.X.getId(), false);
                        format.epub.common.a.a.b(this.a.X.getId());
                        try {
                            this.a.q.setText(this.a.G);
                            this.a.P = true;
                            if (this.a.q.getTopPage().h()) {
                                this.a.aO();
                            }
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.f.a("DIALOG_BOOK_CHANGE", "setText:", e);
                        }
                    }
                });
                return alertDialog;
            case http.Internal_Server_Error /*500*/:
                CharSequence a = com.qq.reader.common.exception.b.a(bundle.getInt("read_error_type"));
                if (a == null) {
                    a = getString(R.string.dialog_readfailed_msg);
                }
                com.qq.reader.common.exception.c cVar = new com.qq.reader.common.exception.c();
                cVar.a((String) a).a(System.currentTimeMillis()).b("bookstand_open").c(com.qq.reader.appconfig.a.d.R(getApplicationContext())).d("").e("");
                com.qq.reader.common.exception.d.a(getApplicationContext()).a(cVar);
                this.D = -1;
                alertDialog.a(a);
                alertDialog.a((int) R.string.dialog_readfailed_but, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.y();
                    }
                });
                return alertDialog;
            case 501:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.dialog_change_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.y();
                    }
                });
                return alertDialog;
            case ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE /*600*/:
                alertDialog.a((int) R.string.dialog_readfailed_but, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.finish();
                    }
                });
                return alertDialog;
            case 601:
                alertDialog.a((int) R.string.readerpage_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.finish();
                    }
                });
                return alertDialog;
            case 602:
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.aU();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.finish();
                    }
                });
                return alertDialog;
            case 603:
                alertDialog.a((int) R.string.readerpage_changeaccount, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass110 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        this.a.a.a(true);
                                        return;
                                    case 2:
                                    case 3:
                                        this.a.a.y();
                                        return;
                                    default:
                                        return;
                                }
                            }
                        };
                        this.a.startLogin();
                    }
                });
                return alertDialog;
            case 604:
                alertDialog.a((int) R.string.login_string, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass111 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        this.a.a.a(true);
                                        return;
                                    case 2:
                                    case 3:
                                        this.a.a.y();
                                        return;
                                    default:
                                        return;
                                }
                            }
                        };
                        this.a.startLogin();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.a.G == null || !(this.a.G instanceof com.qq.reader.readengine.fileparse.d)) {
                            this.a.finish();
                        }
                    }
                });
                return alertDialog;
            case 605:
                final long j = bundle.getLong(this.t);
                final int i2 = bundle.getInt(this.u);
                final int i3 = bundle.getInt(this.v);
                alertDialog.a(bundle.getString("message"));
                alertDialog.setTitle("进度跳转");
                alertDialog.a((int) R.string.alert_dialog_cloud_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity d;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.d.aB == 1) {
                            OnlineTag a;
                            if (this.d.C == null) {
                                a = v.b().a(String.valueOf(j));
                            } else {
                                a = this.d.C.g().z();
                            }
                            if (a != null) {
                                a.c(i2);
                                a.a((long) i3);
                                a.a(false);
                                this.d.c(a);
                            }
                            com.qq.reader.common.monitor.debug.c.a(Constants.LogTag, "TYPE_ONLINE  chapter id = " + i2 + " /  offset = " + i3);
                        } else if (this.d.aB == 0) {
                            long a2 = this.d.a(i2, i3);
                            com.qq.reader.common.monitor.debug.c.a(Constants.LogTag, "TYPE_LOCAL  chapter id = " + i2 + " /  offset = " + i3);
                            com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                            gVar.a(a2);
                            if (this.d.J != null) {
                                this.d.J.mTurePageCmd = 102;
                            }
                            this.d.a(gVar, false, true, false);
                        }
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                return alertDialog;
            case 606:
                a(alertDialog, (Activity) this);
                return alertDialog;
            case 607:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                return alertDialog;
            case 608:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.charge, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.z = true;
                        this.a.A();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                return alertDialog;
            case ErrorCode.STATIC_TBS_INSTALL_SMART_INSTALL_TBS_FINAL_EXCEPTION /*610*/:
                b(alertDialog, (Activity) this);
                return alertDialog;
            case 701:
                if (bundle == null) {
                    str = "0";
                } else {
                    str = bundle.getString("fileid");
                }
                return new com.qq.reader.view.AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.readerpage_download_complete_title).b((int) R.string.readerpage_download_complete_msg).a((int) R.string.readerpage_download_complete_positive, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.finish();
                        this.b.d(str);
                    }
                }).a(new OnCancelListener(this) {
                    final /* synthetic */ ReaderPageActivity b;

                    public void onCancel(DialogInterface dialogInterface) {
                        this.b.finish();
                        this.b.d(str);
                    }
                }).a();
            default:
                return alertDialog;
        }
    }

    private boolean a(int i, DialogInterface dialogInterface) {
        if (i != ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01) {
            return false;
        }
        dialogInterface.dismiss();
        if (this.bb != null) {
            this.bb.a();
        }
        this.bb = null;
        return true;
    }

    public void a(com.qq.reader.common.c.c.a aVar) {
        this.bb = aVar;
    }

    private void d(String str) {
        Mark e = com.qq.reader.common.db.handle.i.c().e(str);
        if (e != null && new File(e.getId()).exists() && 4 != e.getType()) {
            Bundle bundle = new Bundle();
            bundle.putString("filepath", e.getId());
            bundle.putString("filename", e.getBookName());
            bundle.putString("fileauthor", e.getAuthor());
            bundle.putInt("fileencode", e.getEncoding());
            Intent intent = new Intent();
            intent.putExtras(bundle);
            com.qq.reader.b.a(intent, this);
        }
    }

    protected void onFragmentDialgoCancel(DialogInterface dialogInterface) {
        y();
    }

    protected boolean handleMessageImp(Message message) {
        boolean z = false;
        boolean z2 = true;
        OnlineTag onlineTag;
        int s;
        PageIndex u;
        int g;
        ReadOnlineResult t;
        int e;
        String i;
        String string;
        String str;
        Bundle bundle;
        Intent intent;
        Bundle bundle2;
        int i2;
        com.qq.reader.cservice.buy.a.c cVar;
        int d;
        Bundle bundle3;
        final com.qq.reader.module.bookchapter.online.f fVar;
        Map hashMap;
        switch (message.what) {
            case 1110:
                if (!s()) {
                    return true;
                }
                boolean a;
                onlineTag = (OnlineTag) message.obj;
                PageIndex u2 = this.q.getBookCore().o().c().u();
                a(TbsLog.TBSLOG_CODE_SDK_INIT, z, "", z, "", "", u2, "", null);
                try {
                    if (this.aB == 1) {
                        a = a(onlineTag);
                        if (a) {
                            this.F.setmFootInfo(j(onlineTag.g()));
                            this.q.setText(this.G, onlineTag.r(), false);
                            this.P = true;
                            this.O = true;
                            if (this.q.getTopPage().h()) {
                                this.q.a(PageIndex.current_left, PageIndex.current_right);
                            }
                            this.q.getTopPage().invalidate();
                            if (this.q.getTopPage().B()) {
                                this.q.getBookCore().r();
                            }
                            ao.c(onlineTag);
                        }
                    } else {
                        a = true;
                    }
                } catch (Exception e2) {
                    com.qq.reader.common.monitor.f.a("handleMessageImp", "readonline failed!", e2);
                    a = z;
                }
                if (a) {
                    this.C.g().c(onlineTag.g());
                    this.C.g().b(onlineTag.h());
                    if (this.aM.b() != null) {
                        return true;
                    }
                    a(this.C.g());
                    return true;
                }
                this.bf = true;
                a(1001, onlineTag.g(), "", -1, getResources().getString(R.string.paypage_reget), "获取章节失败！", u2, "", null);
                return true;
            case 1111:
                if (!s()) {
                    return true;
                }
                r();
                ReadOnlineResult readOnlineResult = (ReadOnlineResult) message.obj;
                s = this.C.g().s();
                u = this.q.getBookCore().o().c().u();
                com.qq.reader.module.readpage.h o = this.q.getBookCore().o();
                g = o.g();
                t = o.c().t();
                if (t != null) {
                    e = t.e();
                }
                if (e == 0 || g != 1003) {
                    a(1001, s, "", -1, getString(R.string.paypage_reget), readOnlineResult.u(), u, "", null);
                } else {
                    t.f("");
                    a(1001, s, "", -1, getString(R.string.paypage_reget), readOnlineResult.u(), u, "", t);
                }
                if (readOnlineResult == null || readOnlineResult.s() != -1) {
                    if (!this.q.getTopPage().B()) {
                        return true;
                    }
                    this.q.getBookCore().c(3);
                    return true;
                } else if (com.qq.reader.common.login.c.b()) {
                    new Bundle().putString("message", readOnlineResult.u());
                    if (!com.qq.reader.common.login.c.a((Activity) this, Boolean.valueOf(true))) {
                        com.qq.reader.common.login.c.a();
                        a(1005, s, "", -1, aM(), readOnlineResult.u(), PageIndex.next, "", readOnlineResult);
                        return true;
                    } else if (!this.q.getTopPage().B()) {
                        return true;
                    } else {
                        this.q.getBookCore().c(3);
                        return true;
                    }
                } else {
                    ao.x();
                    a(1005, s, "", -1, aM(), getString(R.string.paypage_tip_needlogin), PageIndex.next, "", readOnlineResult);
                    return true;
                }
            case 1112:
                if (s()) {
                    r();
                    t = (ReadOnlineResult) message.obj;
                    s = this.C.g().s();
                    String t2 = t.t();
                    this.C.g().b(t2);
                    u = this.q.getBookCore().o().c().u();
                    if (!com.qq.reader.common.login.c.b()) {
                        a(1005, s, "", -1, aM(), getResources().getString(R.string.paypage_tip_needlogin), u, t2, null);
                        ao.x();
                        break;
                    }
                    new Bundle().putString("message", t.u());
                    if (message.arg1 == -5) {
                        a(1006, s, "", -1, getString(R.string.paypage_openvip), t.u(), u, t2, t);
                    } else {
                        String str2 = t.w() + "&" + com.qq.reader.appconfig.e.b(getApplicationContext());
                        boolean h = t.h();
                        i = t.i();
                        if (!TextUtils.isEmpty(i)) {
                            b(i);
                        }
                        this.aQ = t.l();
                        if (this.ay == null || !this.ay.e().n()) {
                            z2 = z;
                        }
                        i = getString(R.string.paypage_charge);
                        string = getString(R.string.paypage_tip_needpurchase);
                        if (z2) {
                            i = h ? getString(R.string.paypage_pay_all_subscription) : getString(R.string.paypage_charge_subscription);
                            string = getString(R.string.paypage_tip_needpurchase_subscription);
                            str = i;
                        } else if (h) {
                            str = bp() ? getString(R.string.paypage_pay_chapter) : getString(R.string.paypage_pay_all);
                        } else {
                            str = i;
                        }
                        if (h) {
                            a(1003, s, str2, message.arg2, str, string, u, t2, t);
                        } else {
                            a(1009, s, str2, message.arg2, str, string, u, t2, t);
                        }
                    }
                    ao.a(t, message.arg1);
                    break;
                }
                return true;
            case 1114:
                if (message.arg1 == 0 || message.arg1 == 1) {
                    m(message.arg1);
                    break;
                }
            case 1115:
                aN();
                aj();
                break;
            case 1116:
                try {
                    if (this.Z != null && this.Z.f()) {
                        this.Z.cancel();
                    }
                } catch (Exception e3) {
                }
                if (this.q.getTopPage().h()) {
                    this.q.a(PageIndex.current_left, PageIndex.current_right);
                }
                this.q.g();
                this.q.s();
                this.q.getTopPage().invalidate();
                break;
            case 1117:
                try {
                    if (this.Z != null && this.Z.f()) {
                        this.Z.cancel();
                    }
                } catch (Exception e4) {
                }
                this.O = z;
                bundle = new Bundle();
                this.D = 1010;
                bundle.putInt("read_error_type", this.D);
                com.qq.reader.common.monitor.f.a("ReaderPage", "DIALOG_READ_FAILED111111");
                showFragmentDialog(http.Internal_Server_Error, bundle);
                break;
            case 1119:
                this.q.getTopPage().invalidate();
                break;
            case 1120:
                showFragmentDialog(601);
                return true;
            case 1121:
                showFragmentDialog(ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE);
                return true;
            case 1122:
                showFragmentDialog(603);
                return true;
            case 1123:
                showFragmentDialog(602);
                return true;
            case 1124:
                showFragmentDialog(604);
                return true;
            case 1127:
                if (this.Z != null && this.Z.f()) {
                    this.Z.cancel();
                }
                a(TbsLog.TBSLOG_CODE_SDK_INIT, z, "", z, "", "", PageIndex.current, "", null);
                if (this.q.getTopPage().h()) {
                    this.q.a(PageIndex.current_left, PageIndex.current_right);
                }
                if (this.X != null) {
                    a(this.X);
                }
                this.q.g();
                this.q.s();
                this.q.getTopPage().invalidate();
                if (this.J.getBookNetId() > 0) {
                    bk();
                    this.mHandler.sendEmptyMessageDelayed(1228, 800);
                    this.aH.a(this.J);
                    break;
                }
                break;
            case 1200:
                if (this.J != null) {
                    this.J.mTurePageCmd = 102;
                }
                this.q.getBookCore().j();
                this.q.g();
                this.q.s();
                this.q.getTopPage().invalidate();
                break;
            case 1201:
                i.a("event_B14", null, this);
                intent = new Intent();
                intent.setClass(getApplicationContext(), PlugInFontsActivity.class);
                bundle2 = new Bundle();
                bundle2.putInt("fromActivity", 10);
                bundle2.putString("PLUGIN_TYPE", "2");
                intent.putExtras(bundle2);
                startActivityForResult(intent, 10);
                break;
            case 1202:
            case 1211:
            case 65554:
                return true;
            case 1203:
                if (aW()) {
                    com.qq.reader.cservice.download.book.g gVar = (com.qq.reader.cservice.download.book.g) message.obj;
                    if (this.C == null) {
                        if (this.aB == 0) {
                            this.X.setDownloadUrl(gVar.c());
                            a(this.X);
                            break;
                        }
                    }
                    OnlineTag g2 = this.C.g();
                    g2.f(gVar.c());
                    if (this.ay != null && this.ay.e().n()) {
                        e = 1;
                    }
                    if (e == 0) {
                        if (g2.k() == gVar.e()) {
                            try {
                                if (gVar.a() == 5) {
                                    com.qq.reader.module.bookchapter.online.i iVar = new com.qq.reader.module.bookchapter.online.i(g2.k());
                                    iVar.b(gVar.a());
                                    iVar.a(gVar.b());
                                    new AddLimitFreeBook2DBTask(iVar).execute();
                                }
                                DownloadBookTask downloadBookTask = new DownloadBookTask(Long.parseLong(g2.k()), g2.b(), g2.o(), g2.p(), g2.u(), g2.n(), g2.B(), g2.A(), -1);
                                downloadBookTask.setNetChannel(com.qq.reader.common.monitor.a.b.a(g2.k()));
                                JSDownLoad.downLoadBook(downloadBookTask, (com.qq.reader.cservice.download.book.e) com.qq.reader.common.download.task.l.b(1001), this, true);
                                break;
                            } catch (NumberFormatException e5) {
                                com.qq.reader.common.monitor.f.a("ONLINE", "downLoadWholeBook NumberFormatException : " + e5.toString());
                                break;
                            }
                        }
                    }
                    intent = new Intent();
                    g2.m(this.ay.e().o());
                    g2.e(true);
                    intent.putExtra("com.qq.reader.OnlineTag", g2);
                    intent.setClass(getApplicationContext(), ChapterBatDownloadActivity.class);
                    startActivity(intent);
                    i.a("event_B57", null, getApplicationContext());
                    StatisticsManager.a().a("event_B57", null);
                    j.a(56, 1);
                    break;
                }
                return true;
                break;
            case 1204:
                if (aW()) {
                    af.a(getApplicationContext(), getString(R.string.download_faile), (int) z).a();
                    break;
                }
                return true;
            case 1205:
                if (aW()) {
                    showFragmentDialog(606);
                    break;
                }
                return true;
            case 1206:
                format.epub.common.utils.f.a(message.arg1);
                break;
            case 1207:
                if (s()) {
                    r();
                    i2 = message.arg1;
                    if (i2 == -3) {
                        this.C.g().h(1);
                        this.q.e(true);
                        g(true);
                    } else if (i2 == -11) {
                        g(z);
                    }
                    if (i2 != -3) {
                        z = true;
                    }
                    a(z, true);
                    break;
                }
                return true;
            case 1208:
                try {
                    this.aq.a((com.qq.reader.cservice.adv.a) message.obj);
                    this.aq.setVisibility(0);
                    break;
                } catch (Exception e6) {
                    break;
                }
            case 1209:
                if (this.aq.getVisibility() == 0) {
                    this.aq.setVisibility(4);
                    break;
                }
                break;
            case 1210:
                this.q.g();
                this.q.getTopPage().invalidate();
                break;
            case 1212:
                com.qq.reader.module.readpage.h.b bVar = (com.qq.reader.module.readpage.h.b) message.obj;
                if (!(bVar == null || this.C == null)) {
                    boolean a2;
                    t = bVar.t();
                    if (t != null) {
                        a2 = t.a();
                    } else {
                        a2 = z;
                    }
                    com.qq.reader.module.readpage.h.b c = this.q.getBookCore().o().c();
                    s = c.o();
                    if (message.arg1 != 100) {
                        if (message.arg1 != 101) {
                            if (!a2) {
                                com.qq.reader.cservice.buy.a.d dVar = new com.qq.reader.cservice.buy.a.d(getApplicationContext(), this.C.g().k());
                                dVar.a(1);
                                dVar.a((com.qq.reader.cservice.buy.a.b) this);
                                dVar.start();
                                str = getString(R.string.paypage_buying);
                                a(1000, s, "", z, str, "", PageIndex.current, "", t);
                                c.b(str);
                                i.a("event_B126", null, ReaderApplication.getApplicationImp());
                                break;
                            }
                            onlineTag = this.C.g();
                            onlineTag.d(true);
                            onlineTag.g(s);
                            this.aC = true;
                            a(onlineTag, s);
                            c.b(getString(R.string.paypage_buying));
                            this.C.g().d(z);
                            this.aC = z;
                            i.a("event_B125", null, ReaderApplication.getApplicationImp());
                            break;
                        }
                        onlineTag = this.C.g();
                        onlineTag.g(s);
                        this.aC = z;
                        c.b(getString(R.string.paypage_loading));
                        a(onlineTag, s);
                        break;
                    }
                    onlineTag = this.C.g();
                    onlineTag.g(s);
                    this.aC = true;
                    a(onlineTag, s);
                    break;
                }
            case 1213:
                if ((this.ax == null || !this.ax.d()) && !isFinishing() && !com.qq.reader.common.c.a.H && this.I == null) {
                    p();
                    n();
                    an();
                    break;
                }
            case 1214:
                f((OnlineTag) message.obj);
                this.q.s();
                break;
            case 1215:
                m();
                break;
            case 1216:
                if (this.J != null) {
                    this.J.mTurePageCmd = 102;
                }
                this.q.getBookCore().k();
                this.q.g();
                this.q.s();
                this.q.getTopPage().invalidate();
                break;
            case 1218:
                i.a("event_B144", null, ReaderApplication.getApplicationImp());
                cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                if (cVar.e() == 1 || bg()) {
                    a(cVar);
                    if (this.aB == 1 && this.q.getBookCore().o().f() != TbsLog.TBSLOG_CODE_SDK_INIT) {
                        a(this.C.g(), this.C.g().s());
                        break;
                    }
                }
                return true;
                break;
            case 1219:
                cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                if (cVar.e() == 1) {
                    s = this.C.g().s();
                    if (this.ay == null || !this.ay.e().n()) {
                        z2 = z;
                    }
                    str = z2 ? getString(R.string.paypage_charge_subscription) : getString(R.string.paypage_charge);
                    if (z2) {
                        string = getString(R.string.paypage_tip_needpurchase_subscription);
                    } else {
                        string = getString(R.string.paypage_tip_needpurchase);
                    }
                    u = this.q.getBookCore().o().c().u();
                    t = this.q.getBookCore().o().c().t();
                    if (cVar.d() != -6) {
                        if (t != null) {
                            t.f("");
                        }
                        a(1001, s, "", -1, getString(R.string.paypage_repay), "购买失败", u, "", t);
                        break;
                    }
                    a(1009, s, "", z, str, string, u, "", t);
                    break;
                } else if (bg()) {
                    d = cVar.d();
                    bundle3 = new Bundle();
                    bundle3.putString("message", cVar.a());
                    if (d != -2) {
                        if (d != -6) {
                            showFragmentDialog(607, bundle3);
                            break;
                        }
                        showFragmentDialog(608, bundle3);
                        break;
                    } else if (!com.qq.reader.common.login.c.a((Activity) this, Boolean.valueOf(true))) {
                        com.qq.reader.common.login.c.a();
                        bundle3.putString("message", "支付出现问题，请重试");
                        showFragmentDialog(607, bundle3);
                        break;
                    }
                } else {
                    return true;
                }
                break;
            case 1221:
                if ((this.ax == null || !this.ax.d()) && !isFinishing() && this.aB == 1 && this.C.g().g() >= 10 && ao.f((Context) this) && !com.qq.reader.appconfig.a.d.bo(this) && !com.qq.reader.common.c.a.H && this.I == null) {
                    p();
                    o();
                    am();
                    com.qq.reader.appconfig.a.d.bn(this);
                    break;
                }
            case 1222:
                if ((this.ax == null || !this.ax.d()) && !isFinishing() && this.aB == 1 && this.C.g().g() <= 5 && !com.qq.reader.appconfig.a.d.bq(this) && !com.qq.reader.common.c.a.H && this.I == null) {
                    p();
                    o();
                    ao();
                    com.qq.reader.appconfig.a.d.bp(this);
                    break;
                }
            case 1224:
                bi();
                this.O = true;
                a(true);
                break;
            case 1225:
                bi();
                this.D = -1;
                bundle = new Bundle();
                bundle.putInt("read_error_type", this.D);
                showFragmentDialog(http.Internal_Server_Error, bundle);
                break;
            case 1228:
                com.qq.reader.plugin.c cVar2 = new com.qq.reader.plugin.c(getApplicationContext(), this.mHandler);
                if (cVar2.b()) {
                    if (com.qq.reader.appconfig.a.d.by(getApplicationContext())) {
                        if (com.qq.reader.common.login.c.b()) {
                            cVar2.c();
                            break;
                        }
                    }
                    showFragmentDialog(307);
                    break;
                }
                break;
            case 1229:
                if (message.obj != null) {
                    try {
                        i = (String) message.obj;
                        this.aO = new k(this, 0);
                        this.aO.a(i, this.mHandler);
                        break;
                    } catch (Exception e7) {
                        break;
                    }
                }
                break;
            case 1231:
                boolean z3;
                if (this.bt == null) {
                    bo();
                }
                if (message.arg2 == 0) {
                    z3 = true;
                } else {
                    z3 = z;
                }
                if (this.x != null && this.x.f()) {
                    this.x.cancel();
                }
                if (message.arg1 != 1) {
                    if (message.arg1 != 2) {
                        if (this.bt != null && !this.bt.c()) {
                            b(getString(R.string.not_support_month));
                            break;
                        }
                        this.x = com.qq.reader.common.utils.o.a(message.arg1, (Activity) this, this.J.getBookNetId(), this.J.getCurIndex(), this.J.getAuthorIcon(), z3);
                        break;
                    } else if (this.bt != null && !this.bt.b()) {
                        b(getString(R.string.not_support_recommend));
                        break;
                    } else {
                        this.x = com.qq.reader.common.utils.o.a(message.arg1, (Activity) this, this.J.getBookNetId(), this.J.getCurIndex(), this.J.getAuthorIcon(), z3);
                        break;
                    }
                } else if (this.bt != null && !this.bt.a()) {
                    b(getString(R.string.not_support_reward));
                    break;
                } else {
                    this.x = com.qq.reader.common.utils.o.a(message.arg1, (Activity) this, this.J.getBookNetId(), this.J.getCurIndex(), this.J.getAuthorIcon(), z3);
                    break;
                }
                break;
            case 1232:
                message = Message.obtain();
                message.what = 102;
                this.q.a(message);
                break;
            case 1233:
                f(true);
                break;
            case 1234:
                if (this.J == null) {
                    StatisticsManager.a().a("readTime", Integer.valueOf(message.arg1)).a("bid", Integer.valueOf(z)).a(101).c();
                    break;
                }
                StatisticsManager.a().a("readTime", Integer.valueOf(message.arg1)).a("bid", Long.valueOf(this.J.getBookNetId())).a(101).c();
                break;
            case 1235:
                bi();
                finish();
                break;
            case 1236:
                i2 = message.arg1;
                if (this.aB == 1 && this.N != null) {
                    if (i2 != 2) {
                        this.N.setVisibility(z);
                        break;
                    }
                    this.N.setVisibility(8);
                    break;
                }
            case 1240:
                X();
                break;
            case 1243:
                ah();
                return true;
            case 1244:
                if (!this.q.k()) {
                    return true;
                }
                bq().show();
                this.q.d(z);
                this.q.v();
                return true;
            case 1245:
                com.qq.reader.module.readpage.z.a(getHandler(), this);
                return true;
            case 1246:
                if (this.Z != null && this.Z.f()) {
                    this.Z.cancel();
                }
                onlineTag = new OnlineTag(String.valueOf(this.X.getBookId()), "", 0);
                if (this.az == null) {
                    this.az = new com.qq.reader.module.bookchapter.a.a(getApplicationContext(), onlineTag);
                }
                this.az.a(this.mHandler);
                this.az.a(new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        this.a.showFragmentDialog(606);
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    }
                });
                return true;
            case APPluginErrorCode.ERROR_APP_SYSTEM /*2000*/:
                if (this.aB != 0 || !com.qq.reader.readengine.model.a.k(this.X.getId())) {
                    com.qq.reader.cservice.cloud.f fVar2 = (com.qq.reader.cservice.cloud.f) message.obj;
                    bundle2 = new Bundle();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(getString(R.string.colud_info2));
                    stringBuffer.append(fVar2.g());
                    stringBuffer.append(getString(R.string.colud_info3));
                    bundle2.putString("message", stringBuffer.toString());
                    bundle2.putLong(this.t, fVar2.c());
                    bundle2.putInt(this.u, (int) fVar2.g());
                    bundle2.putInt(this.v, fVar2.f());
                    showFragmentDialog(605, bundle2);
                    break;
                }
                return true;
            case 2001:
                if (!(a(IBook.mRemarksList) == 0 && ac() == 0)) {
                    this.q.g();
                    this.q.getTopPage().invalidate();
                }
                if (com.qq.reader.common.login.c.b() && !(((message.obj instanceof Mark) && ((Mark) message.obj).getBookId() == 0) || com.qq.reader.common.utils.m.a((Context) this))) {
                    b(message.obj);
                    break;
                }
            case 8001:
                if (!(message == null || message.obj == null || this.X == null)) {
                    i2 = ((Integer) message.obj).intValue();
                    a(1000, -1, "", Constants.ERRORCODE_UNKNOWN, String.format(getResources().getString(R.string.downloading_with_percent), new Object[]{Integer.valueOf(i2)}) + "%", "", PageIndex.current, this.X.getBookShortName(), null);
                    this.q.getTopPage().t();
                    break;
                }
            case 8003:
                long longValue = ((Long) message.obj).longValue();
                if (this.X != null && this.X.getBookId() == longValue && com.qq.reader.readengine.model.a.l(this.X.getId())) {
                    if (message.arg1 != 1 && !(this.X instanceof DownloadMark)) {
                        bundle3 = new Bundle();
                        bundle3.putString("fileid", String.valueOf(longValue));
                        showFragmentDialog(701, bundle3);
                        break;
                    }
                    a(true);
                    this.E = null;
                    this.q.getTopPage().t();
                    break;
                }
                break;
            case 8018:
                a(1001, -1, "", Constants.ERRORCODE_UNKNOWN, getString(R.string.paypage_reget), "", PageIndex.current, "", null);
                break;
            case 10009:
                intent = new Intent();
                intent.setAction(ReaderWidget.d);
                sendBroadcast(intent);
                break;
            case 21000:
                if (this.C == null) {
                    return true;
                }
                if (message.arg1 != 3 && this.aB == 1) {
                    com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                        public void run() {
                            ReaderPageActivity.this.J.initFileList(ReaderPageActivity.this.C.g(), true);
                            ReaderPageActivity.this.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass84 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    ReaderPageActivity.this.be();
                                    ReaderPageActivity.this.q.g();
                                    ReaderPageActivity.this.q.getTopPage().invalidate();
                                }
                            });
                        }
                    });
                    if (!(this.ay == null || this.ay.e() == null)) {
                        if (com.qq.reader.common.db.handle.i.c().f(this.ay.e().g()) != null && this.ay.e().t()) {
                            com.qq.reader.common.db.handle.i.c().m();
                            af.a((Context) this, getResources().getString(R.string.price_info_limitfree) + "，" + ao.i(this.ay.e().x()), (int) z).a();
                        }
                        i = this.ay.e().y().I();
                        final String J = this.ay.e().y().J();
                        if (this.J != null) {
                            this.J.setAuthorId(J);
                        }
                        if (!TextUtils.isEmpty(i)) {
                            com.qq.reader.common.imageloader.c.a((Context) this).a(i, new com.bumptech.glide.request.b.g(this) {
                                final /* synthetic */ ReaderPageActivity b;

                                public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                                    if (obj instanceof com.bumptech.glide.load.resource.bitmap.j) {
                                        com.bumptech.glide.load.resource.bitmap.j jVar = (com.bumptech.glide.load.resource.bitmap.j) obj;
                                        if (jVar.b() != null) {
                                            Drawable roundBorderDrawable = new RoundBorderDrawable(jVar.b(), ao.a(15.0f), 266724837, 1, true);
                                            if (this.b.J != null) {
                                                this.b.J.setAuthorIconDrawable(roundBorderDrawable);
                                                this.b.J.setAuthorId(J);
                                                this.b.q.g();
                                                this.b.q.getTopPage().invalidate();
                                            }
                                        }
                                    }
                                }
                            }, com.qq.reader.common.imageloader.a.a().i());
                        }
                    }
                    if (this.aV) {
                        fVar = (com.qq.reader.module.bookchapter.online.f) message.obj;
                        com.qq.reader.common.readertask.g.a().a(new ReaderDBTask() {
                            public void run() {
                                super.run();
                                if (com.qq.reader.common.db.handle.h.b().c(String.valueOf(ReaderPageActivity.this.J.getBookNetId()))) {
                                    long t = fVar.y().t();
                                    if (t <= 0 || t < System.currentTimeMillis()) {
                                        OnlineTag g = ReaderPageActivity.this.C.g();
                                        if (g != null) {
                                            com.qq.reader.common.db.handle.h.b().a(g.k(), g.g());
                                        }
                                    }
                                }
                            }
                        });
                    }
                    this.aV = true;
                }
                fVar = (com.qq.reader.module.bookchapter.online.f) message.obj;
                a(fVar.y());
                try {
                    d = fVar.y().j();
                    if (this.C.g().n() < d) {
                        this.C.g().d(d);
                    }
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
                d = fVar.j();
                if (d == 1) {
                    if (this.aa != null && this.aa.f()) {
                        c(fVar.g());
                        break;
                    }
                    return true;
                } else if (aW()) {
                    if (d != 2) {
                        if (d == 3) {
                            af.a((Context) this, (int) R.string.online_download_error, (int) z).a();
                            break;
                        }
                    }
                    intent = new Intent();
                    intent.putExtra("com.qq.reader.OnlineTag", this.C.g());
                    intent.setClass(getApplicationContext(), ChapterBatDownloadActivity.class);
                    startActivity(intent);
                    break;
                } else {
                    return true;
                }
                break;
            case 21001:
                if (aW()) {
                    af.a(getApplicationContext(), getString(R.string.download_faile), (int) z).a();
                    break;
                }
                return true;
            case 21007:
                af.a((Context) this, (CharSequence) "章节下载成功", (int) z).a();
                break;
            case 21008:
                af.a((Context) this, (CharSequence) "章节下载失败", (int) z).a();
                break;
            case 21012:
                fVar = (com.qq.reader.module.bookchapter.online.f) message.obj;
                if (fVar.j() == 1) {
                    if (this.aa != null && this.aa.f()) {
                        c(fVar.g());
                        break;
                    }
                    return true;
                } else if (!aW()) {
                    return true;
                }
                break;
            case 21013:
                if (aW()) {
                    af.a(getApplicationContext(), getString(R.string.download_faile), (int) z).a();
                    break;
                }
                return true;
            case 21016:
                OnlineChapter onlineChapter = (OnlineChapter) message.obj;
                if (!(this.J == null || this.aB != 1 || onlineChapter == null)) {
                    if (this.J.getBookNetId() == onlineChapter.getBookId()) {
                        this.q.getBookCore().b().b(onlineChapter.getChapterId());
                    }
                    if (message.arg1 == 1) {
                        if (onlineChapter.getRedPacketAmount() != 0) {
                            this.J.setHasRedpacket(true);
                            h(true);
                        } else {
                            this.J.setHasRedpacket(z);
                            h(z);
                        }
                    }
                }
                if (!(this.q.getTopPage().getAnimationProvider().f() || this.mHandler == null)) {
                    this.mHandler.post(new Runnable(this) {
                        final /* synthetic */ ReaderPageActivity a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.q.g();
                            this.a.q.getTopPage().invalidate();
                        }
                    });
                    break;
                }
            case 65541:
                if (this.aO != null) {
                    this.aO.f_();
                    break;
                }
                break;
            case 65545:
                br();
                return true;
            case 65552:
                try {
                    com.qq.reader.module.rookie.a.b bVar2 = (com.qq.reader.module.rookie.a.b) message.obj;
                    if (bVar2 == null) {
                        return true;
                    }
                    if ((this.aB == 1 && this.q.getBookCore().o().c().p() != 1009) || this.aY == null || isFinishing()) {
                        return true;
                    }
                    this.aY.f_();
                    OnlineTag g3 = this.C.g();
                    if (g3 != null) {
                        try {
                            e = Integer.parseInt(g3.k());
                        } catch (NumberFormatException e9) {
                            e9.printStackTrace();
                        }
                    }
                    com.qq.reader.module.rookie.presenter.a.a().a(bVar2.a, "p2", (long) e);
                    hashMap = new HashMap();
                    hashMap.put("id", bVar2.a + "");
                    i.a("event_A266", hashMap, ReaderApplication.getApplicationImp());
                    return true;
                } catch (Exception e10) {
                    e10.printStackTrace();
                    return true;
                }
            case 100002:
                d = message.arg1;
                i = (String) message.obj;
                if (this.C != null) {
                    OnlineTag g4 = this.C.g();
                    g = g4.n();
                    String k = g4.k();
                    if (i != null && i.equals(k) && g < d) {
                        new com.qq.reader.cservice.bookfollow.b(getApplicationContext()).a(k, g);
                        this.C.g().d(d);
                        this.J.getMulitFile().findNewFile(d);
                        break;
                    }
                }
                break;
            case 200001:
                switch (message.arg1) {
                    case 21001:
                        b("请安装最新版讯飞语记");
                        bd();
                        ba();
                        break;
                    case 22002:
                        b("你选的这个声音还没有下载呢。");
                        break;
                    default:
                        b("语音播放出错，错误码：" + message.arg1);
                        ba();
                        break;
                }
            case 200003:
                this.q.getTopPage().getTtsModeController().a((com.qq.reader.plugin.tts.model.d) message.obj);
                break;
            case 200004:
                this.q.getTopPage().getTtsModeController().b((com.qq.reader.plugin.tts.model.d) message.obj);
                break;
            case 200007:
                this.q.getTopPage().getTtsModeController().b((com.qq.reader.plugin.tts.model.d) message.obj);
                this.q.getTopPage().getTtsModeController().a((com.qq.reader.plugin.tts.model.d) message.obj);
                if (!(this.C == null || this.C.g() == null)) {
                    ao.h(this.C.g().g());
                    break;
                }
            case 200008:
                b("请安装最新版讯飞语记");
                ba();
                bd();
                break;
            case 200009:
                ba();
                break;
            case 200012:
                if (message.arg1 != 3 && message.arg1 == 4) {
                    break;
                }
            case 200013:
                ba();
                try {
                    intent = new Intent();
                    intent.setAction("com.iflytek.vflynote.loading");
                    intent.putExtra("delay_close", 1500);
                    startActivityForResult(intent, 1000);
                    break;
                } catch (Exception e11) {
                    af.a((Context) this, (CharSequence) "请先启动讯飞语记", (int) z).a();
                    break;
                }
            case 400008:
                if (this.z) {
                    this.z = z;
                    T();
                }
                if (this.A) {
                    this.A = z;
                    bn();
                    break;
                }
                break;
            case 500110:
                com.qq.reader.module.readpage.voteview.a.a aVar = (com.qq.reader.module.readpage.voteview.a.a) message.obj;
                if (aVar == null) {
                    return true;
                }
                hashMap = new HashMap();
                if (!aVar.f) {
                    hashMap.put(s.ORIGIN, "4");
                    i.a("event_C286", hashMap, ReaderApplication.getApplicationImp());
                }
                com.qq.reader.common.utils.o.a((Activity) this, aVar.f, aVar.a, aVar.e, aVar.b, aVar.d);
                return true;
            case 8000011:
                b((AlertDialog) message.obj);
                return true;
            case 8000012:
                c((AlertDialog) message.obj);
                return true;
        }
        return super.handleMessageImp(message);
    }

    private void a(com.qq.reader.module.bookchapter.online.d dVar) {
        if (dVar != null) {
            List M = dVar.M();
            if (M == null || M.size() <= 0) {
                o().b(4);
            } else {
                o().b(0);
            }
        }
    }

    private String aM() {
        if (!com.qq.reader.module.rookie.presenter.a.a().b) {
            return getResources().getString(R.string.paypage_needlogin);
        }
        i.a("event_A270", null, ReaderApplication.getApplicationImp());
        return getResources().getString(R.string.rookie_paypage_needlogin);
    }

    public void t() {
        int i = 1;
        if (!this.q.getTopPage().h()) {
            if (this.aB == 0) {
                i = 2;
            } else if (this.aB == 1) {
                i = 2;
            }
        }
        this.F.setType(i);
    }

    private void a(IBook iBook) {
        Message obtain = Message.obtain();
        obtain.what = 103;
        Bundle bundle = new Bundle();
        bundle.putString("VGMSG_BOOKID", "" + iBook.getBookNetId());
        bundle.putString("VGMSG_CHAPTERID", "");
        obtain.setData(bundle);
        this.q.a(obtain);
    }

    private void aO() {
        this.q.getTopPage().setSize(this.q.getTopPage().getWidth(), this.q.getTopPage().getHeight());
        if (this.G instanceof com.qq.reader.readengine.fileparse.d) {
            this.q.a(PageIndex.current_left, PageIndex.current_right);
        }
    }

    private boolean l(int i) {
        if (this.aB == 1) {
            switch (i) {
                case 3:
                case 4:
                case 5:
                    OnlineTag g = this.C.g();
                    if (g.g() >= g.n()) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public void u() {
        Y();
    }

    public int d(int i) {
        if (this.aB != 1) {
            return 0;
        }
        this.bf = false;
        switch (i) {
            case 3:
                if (this.C == null) {
                    return 0;
                }
                int a = a(this.C.g(), -11);
                if (a != 0) {
                    a(TbsLog.TBSLOG_CODE_SDK_INIT, 0, "", 0, "", "", PageIndex.previous, "", null);
                } else if (2 == a) {
                }
                return a;
            case 4:
                OnlineTag g = this.C.g();
                g.b(false);
                g.g(g.g() - 1);
                a(g, PageIndex.previous);
                return 2;
            default:
                return 0;
        }
    }

    public int a(int i, boolean z) {
        boolean z2 = true;
        if (l(i)) {
            return 3;
        }
        if (this.aB == 1) {
            this.bf = false;
            OnlineTag g = this.C.g();
            switch (i) {
                case 3:
                    if (this.C == null) {
                        return 0;
                    }
                    if (g != null) {
                        g.a(true);
                    }
                    int a = a(g, -10);
                    if (a == 0 && g.q() == 0) {
                        g(true);
                        return a;
                    } else if (2 != a) {
                        return a;
                    } else {
                        e(z);
                        return a;
                    }
                case 4:
                    if (g != null) {
                        g.a(true);
                    }
                    g.g(g.g() + 1);
                    g.b(true);
                    a(g, PageIndex.next);
                    e(z);
                    return 2;
                case 5:
                    if (g.w() == 1) {
                        z2 = false;
                    }
                    b(z2, false);
                    return 0;
                default:
                    return 0;
            }
        } else if (this.aB != 0) {
            return 0;
        } else {
            DownloadBookTask a2 = o.c().a(this.J.getBookPath());
            if (i != 3) {
                return i == 1 ? 0 : 0;
            } else {
                if (this.q.getTopPage().B()) {
                    this.q.getBookCore().c(1);
                    return 0;
                } else if (a2 == null) {
                    return 0;
                } else {
                    if (this.X == null || this.X.getBookId() <= 0) {
                        z2 = false;
                    } else {
                        z2 = com.qq.reader.readengine.model.a.k(this.X.getId());
                    }
                    if (z2) {
                        a(this.X);
                        return 4;
                    }
                    f(false);
                    return 0;
                }
            }
        }
    }

    private void e(boolean z) {
    }

    private void a(boolean z, boolean z2) {
        b(z, z2);
    }

    private void b(boolean z, boolean z2) {
        a(this.J.getBookNetId(), this.J.getBookShortName(), z, z2);
    }

    private void a(long j, String str, boolean z, boolean z2) {
        f(false);
    }

    public void v() {
        if (this.bp != null) {
            this.bp.cancel();
        }
    }

    private void f(boolean z) {
        boolean z2 = false;
        com.qq.reader.common.monitor.debug.c.a("showactiveview", "isSingleChapter " + z);
        if (this.aK) {
            this.aK = false;
            try {
                if (this.q != null && this.q.getBookCore().a()) {
                    boolean z3;
                    OnlineTag onlineTag;
                    if (this.aB != 1 || this.C == null) {
                        onlineTag = null;
                        z3 = true;
                    } else {
                        OnlineTag g = this.C.g();
                        OnlineTag onlineTag2;
                        if (g != null) {
                            if (g.w() == 1) {
                                z2 = true;
                            }
                            onlineTag2 = g;
                            z3 = z2;
                            onlineTag = onlineTag2;
                        } else {
                            onlineTag2 = g;
                            z3 = false;
                            onlineTag = onlineTag2;
                        }
                    }
                    Intent intent = new Intent(this, NativeBookStoreEndPageActivity.class);
                    Bundle bundle = new Bundle();
                    if (z) {
                        bundle.putString("KEY_JUMP_PAGENAME", "bookclubchapter");
                        bundle.putInt("CTYPE", 0);
                        bundle.putString("LOCAL_STORE_IN_TITLE", this.J.getBookShortName());
                        bundle.putString("PARA_TYPE_BOOK_TITLE", this.J.getBookShortName());
                        bundle.putLong("URL_BUILD_PERE_BOOK_ID", this.J.getBookNetId());
                        if (this.aB == 1 && r0 != null) {
                            bundle.putInt("URL_BUILD_PERE_CHAPTER_ID", this.C.g().g());
                        }
                        bundle.putBoolean("LOCAL_STORE_KEY_IS_FINISH", z3);
                        bundle.putInt("function_type", 0);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 1002);
                        return;
                    }
                    this.q.D();
                    this.aH.a(z3);
                    this.aG.g();
                    com.qq.reader.common.monitor.d.a(this.J.getBookNetId(), z3);
                    ba();
                }
            } catch (Exception e) {
            }
        }
    }

    private void aP() {
    }

    private void aQ() {
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        this.bk = true;
    }

    public void e(int i) {
        com.qq.reader.appconfig.a.d.n = false;
        this.bi.g();
        com.qq.reader.appconfig.a.d.i(getApplicationContext(), com.qq.reader.appconfig.a.d.n);
        ao.a((Activity) this, true);
        ao.b((Activity) this);
        as();
        if (!(this.q == null || this.q.getmPageContext() == null)) {
            this.q.getmPageContext().w();
        }
        this.q.d(i);
        com.qq.reader.appconfig.a.d.j(getContext().getApplicationContext(), i);
        com.qq.reader.common.db.handle.c.a(String.valueOf(this.J.getBookNetId()), getHandler());
        if (this.q.getmPageContext().s()) {
            this.q.g();
            this.q.getTopPage().invalidate();
        }
    }

    private void m(int i) {
        com.qq.reader.appconfig.a.d.x = i;
        setRequestedOrientation(i);
    }

    private void aR() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), VIPBrowser.class);
        intent.putExtra("com.qq.reader.webbrowser.url", com.qq.reader.appconfig.e.a((Context) this));
        intent.putExtra("com.qq.reader.webbrowser.title", R.string.dialog_vip);
        startActivity(intent);
    }

    private boolean aS() {
        return this.G != null;
    }

    private void aT() {
        if (this.Y != null && this.Y.f()) {
            this.Y.cancel();
        }
        if (this.K != null && this.K.getVisibility() == 0) {
            this.K.b();
        }
    }

    public void onConfigurationChanged(final Configuration configuration) {
        aT();
        try {
            this.K = null;
            this.Y = null;
            this.q.b(true);
            if (this.w != null) {
                if (this.w.f()) {
                    this.w.dismiss();
                }
                this.w = null;
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a("onConfigurationChanged", "error", e);
            this.O = false;
            this.D = 1009;
            Bundle bundle = new Bundle();
            bundle.putInt("read_error_type", this.D);
            showFragmentDialog(http.Internal_Server_Error, bundle);
        }
        int i = getResources().getDisplayMetrics().widthPixels;
        if (configuration.orientation == 2 && com.qq.reader.appconfig.a.d.ao(getApplicationContext()) && !com.qq.reader.appconfig.a.d.aq(getApplicationContext())) {
            i += ao.f((Activity) this);
        }
        this.aG.setRightSize(i);
        getWindow().getDecorView().post(new Runnable(this) {
            final /* synthetic */ ReaderPageActivity b;

            public void run() {
                this.b.getHandler().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass124 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.b.aH.a(configuration);
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.a(ReaderPageActivity.class.getSimpleName(), e.getMessage());
                        }
                    }
                });
            }
        });
        if (this.aG.getCurrentState() != 101) {
            this.aG.g();
        }
        super.onConfigurationChanged(configuration);
    }

    public ad w() {
        if (this.R == null) {
            this.R = new ad(this, this.C, this.ay);
            this.R.a(new com.qq.reader.view.r(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public t a() {
                    return this.a.R.c();
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    super.onDismiss(dialogInterface);
                    boolean ao = com.qq.reader.appconfig.a.d.ao(this.a.getApplicationContext());
                    boolean aq = com.qq.reader.appconfig.a.d.aq(this.a.getApplicationContext());
                    if (ao && aq) {
                        this.a.mHandler.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass125 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.q.d(com.qq.reader.appconfig.a.d.L(this.a.a.getApplicationContext()));
                            }
                        }, 500);
                    } else if (ao && !aq) {
                        this.a.q.d(com.qq.reader.appconfig.a.d.L(this.a.getApplicationContext()));
                    }
                    int af = com.qq.reader.appconfig.a.d.af(this.a.getApplicationContext());
                    if (af != this.a.av) {
                        this.a.av = af;
                        if (this.a.J != null) {
                            this.a.J.mTurePageCmd = 102;
                        }
                        this.a.q.setViewMode(com.qq.reader.appconfig.a.d.B(this.a.getApplicationContext()));
                    }
                }
            });
            this.R.a(new com.qq.reader.view.ad.a(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    Message message = new Message();
                    message.what = 1216;
                    message.arg1 = i;
                    this.a.mHandler.sendMessage(message);
                }
            });
        }
        return this.R;
    }

    public void f(int i) {
        com.qq.reader.common.monitor.f.a("ReadPage", "onIdentifyError code:" + i);
        this.mHandler.post(new Runnable(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    if (this.a.Z != null && this.a.Z.f()) {
                        this.a.Z.cancel();
                    }
                } catch (Exception e) {
                }
            }
        });
        Message obtainMessage = this.mHandler.obtainMessage();
        switch (i) {
            case -3:
                obtainMessage.what = 1123;
                break;
            case -2:
                obtainMessage.what = 1122;
                break;
            case -1:
                obtainMessage.what = 1121;
                break;
            case 2:
                obtainMessage.what = 1124;
                break;
            case 3:
                obtainMessage.what = 1120;
                break;
        }
        this.mHandler.sendMessage(obtainMessage);
    }

    public void x() {
        this.q.getBookCore().a(this.X);
    }

    private void aU() {
        String bookLocalId = this.J.getBookLocalId();
        if (bookLocalId != null) {
            Intent intent = new Intent();
            intent.setClass(this, WebBrowserForContents.class);
            intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.a((Context) this, Long.parseLong(bookLocalId)));
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            startActivity(intent);
        }
        finish();
    }

    public void a(com.qq.reader.readengine.b.d dVar) {
        long e = dVar.e();
        com.qq.reader.readengine.model.b bVar = new com.qq.reader.readengine.model.b(dVar.b(), dVar.c(), this.J.getBookNetId(), dVar.h(), dVar.b(), dVar.h(), dVar.c(), dVar.a());
        bVar.a(this.aB == 1);
        if (IBook.mSearchList.size() > 0) {
            IBook.mSearchList.clear();
        }
        IBook.mSearchList.add(bVar);
        if (this.bj != null) {
            this.bj.cancel();
        }
        this.q.g();
        this.q.getTopPage().invalidate();
        com.qq.reader.readengine.kernel.g gVar;
        if (this.aB == 0) {
            gVar = new com.qq.reader.readengine.kernel.g();
            gVar.a(e);
            if (this.J != null) {
                this.J.mTurePageCmd = 101;
            }
            a(gVar, false, true, false);
        } else {
            gVar = new com.qq.reader.readengine.kernel.g();
            gVar.a(dVar.h(), dVar.e());
            gVar.a(true);
            a(gVar, false, true, false);
        }
        if (this.q.getTopPage().h()) {
            this.q.getTopPage().i();
        }
    }

    public boolean y() {
        if (this.G != null && this.q.getBookCore().a()) {
            return false;
        }
        finish();
        return true;
    }

    public void a(int i, String str) {
        OnlineTag g;
        if (i != 1 || str == null || str.trim().length() <= 0) {
            if (i == 2) {
                g = this.C.g();
                if (g != null) {
                    g.c(d.a);
                }
            }
        } else if (this.C != null) {
            g = this.C.g();
            g.f(str);
            v.b().b(g);
            b(g);
        } else {
            a(this.X);
        }
        this.mHandler.sendEmptyMessage(1202);
    }

    private void a(int i, int i2, String str, int i3, String str2, String str3, PageIndex pageIndex, String str4, ReadOnlineResult readOnlineResult) {
        boolean z = false;
        com.qq.reader.module.readpage.h o = this.q.getBookCore().o();
        o.c().d(str3);
        o.c().b(str2);
        o.c().a(readOnlineResult);
        switch (i) {
            case TbsLog.TBSLOG_CODE_SDK_INIT /*999*/:
                o.b(i);
                o.b();
                return;
            case 1000:
            case 1001:
                if (i3 == Constants.ERRORCODE_UNKNOWN) {
                    o.c().a(pageIndex);
                    if (str4 != null && str4.length() > 0) {
                        o.c().c(str4);
                    }
                } else {
                    OnlineChapter onlineChapter = (OnlineChapter) this.J.getMulitFile().getChapterInfo(i2);
                    o.c().a(pageIndex);
                    if (i2 != 0) {
                        o.c().b(i2);
                    }
                    if (str4 != null && str4.length() > 0) {
                        o.c().c(str4);
                    } else if (onlineChapter != null) {
                        o.c().c(onlineChapter.getChapterName());
                    } else {
                        o.c().c("第" + i2 + "章");
                    }
                }
                o.b(i);
                return;
            case 1003:
            case 1009:
                o.c().a(str);
                o.c().a(i3);
                if (str4 != null && str4.length() > 0) {
                    o.c().c(str4);
                }
                o.b(i);
                if (this.q.getTopPage().B()) {
                    this.q.getBookCore().c(4);
                    return;
                }
                return;
            case 1004:
                if (this.aB != 1 || this.C == null) {
                    z = true;
                } else {
                    OnlineTag g = this.C.g();
                    if (g != null && g.w() == 1) {
                        z = true;
                    }
                }
                com.qq.reader.common.monitor.d.a(this.J.getBookNetId(), z);
                o.e();
                o.c().a(pageIndex);
                o.c().b(i2);
                o.c().c(str4);
                o.b(i);
                if (this.q.getTopPage().B()) {
                    this.q.getBookCore().c(1);
                    return;
                }
                return;
            case 1005:
                o.c().a(pageIndex);
                o.b(i);
                if (this.q.getTopPage().B()) {
                    this.q.getBookCore().c(2);
                    return;
                }
                return;
            case 1006:
                o.c().a(pageIndex);
                o.b(i);
                if (this.q.getTopPage().B()) {
                    this.q.getBookCore().c(5);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void z() {
        this.mHandler.sendEmptyMessage(1211);
    }

    public void a(com.qq.reader.cservice.buy.a.c cVar) {
        int i = 0;
        if (this.C != null) {
            if (this.ay != null) {
                int c = this.ay.c();
                if (this.ay != null && this.ay.e().n()) {
                    i = 1;
                }
                if (c == 2 && r0 == 0) {
                    return;
                }
            }
            OnlineTag g = this.C.g();
            g.f(cVar.b());
            v.b().b(g);
            b(g);
        } else if (this.aB == 0) {
            a(this.X);
        } else if (this.aB == 2) {
            this.aB = 0;
            com.qq.reader.common.readertask.g.a().a(new ReaderDBTask() {
                public void run() {
                    super.run();
                    com.qq.reader.common.db.handle.h.b().a(ReaderPageActivity.this.J.getBookNetId() + "", 0);
                    ReaderPageActivity.this.mHandler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass92 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            ReaderPageActivity.this.a(true);
                        }
                    });
                }
            });
        }
    }

    public void setDestUrl(String str) {
    }

    public void reload() {
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

    private void aV() {
        if (this.aa == null) {
            this.aa = new com.qq.reader.view.c(this);
            this.aa.c(true);
            this.aa.a(getResources().getString(R.string.get_book_music_feed_loading));
            this.aa.a(new OnCancelListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.ay != null) {
                        this.a.ay.d();
                    }
                }
            });
        }
        if (!this.aa.f()) {
            this.aa.f_();
        }
    }

    private boolean aW() {
        try {
            if (this.aa != null && this.aa.f()) {
                this.aa.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        if (sensor.getType() != 5) {
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 5 && this.ap) {
            if (!(sensorEvent.values[0] >= 33.333332f || com.qq.reader.appconfig.a.d.n || this.s == null)) {
                this.mHandler.sendEmptyMessage(1213);
            }
            ((SensorManager) getSystemService("sensor")).unregisterListener(this, this.s);
            this.s = null;
        }
    }

    private String a(long j) {
        String str = "";
        if (this.bq == null || this.bq.length <= 0 || this.bq[0].getDescriptionStr().equalsIgnoreCase("首页")) {
            return str;
        }
        String descriptionStr;
        int i = 1;
        while (i < this.bq.length) {
            if (j < this.bq[i].getStartPoint()) {
                descriptionStr = this.bq[i - 1].getDescriptionStr();
                break;
            } else if (j == this.bq[i].getStartPoint()) {
                descriptionStr = this.bq[i].getDescriptionStr();
                break;
            } else {
                i++;
            }
        }
        descriptionStr = str;
        if (!descriptionStr.equalsIgnoreCase("") || j <= this.bq[this.bq.length - 1].getStartPoint()) {
            return descriptionStr;
        }
        return this.bq[this.bq.length - 1].getDescriptionStr();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(final java.lang.Object r6) {
        /*
        r5 = this;
        r0 = 1;
        monitor-enter(r5);
        r1 = r5.aB;	 Catch:{ all -> 0x0056 }
        if (r1 != r0) goto L_0x0017;
    L_0x0006:
        r0 = r5.mHandler;	 Catch:{ all -> 0x0056 }
        r1 = r5.mHandler;	 Catch:{ all -> 0x0056 }
        r2 = 2001; // 0x7d1 float:2.804E-42 double:9.886E-321;
        r1 = r1.obtainMessage(r2, r6);	 Catch:{ all -> 0x0056 }
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r0.sendMessageDelayed(r1, r2);	 Catch:{ all -> 0x0056 }
    L_0x0015:
        monitor-exit(r5);
        return;
    L_0x0017:
        r1 = r5.aB;	 Catch:{ all -> 0x0056 }
        if (r1 != 0) goto L_0x0015;
    L_0x001b:
        r1 = com.qq.reader.common.db.handle.i.c();	 Catch:{ all -> 0x0056 }
        r2 = r5.J;	 Catch:{ all -> 0x0056 }
        r2 = r2.getBookPath();	 Catch:{ all -> 0x0056 }
        r1 = r1.a(r2);	 Catch:{ all -> 0x0056 }
        r5.bq = r1;	 Catch:{ all -> 0x0056 }
        r1 = r5.bq;	 Catch:{ all -> 0x0056 }
        if (r1 == 0) goto L_0x0059;
    L_0x002f:
        r0 = com.qq.reader.module.bookchapter.c.a();	 Catch:{ all -> 0x0056 }
        r1 = r5.bq;	 Catch:{ all -> 0x0056 }
        r0.a(r1);	 Catch:{ all -> 0x0056 }
        r0 = r5.J;	 Catch:{ all -> 0x0056 }
        if (r0 == 0) goto L_0x0043;
    L_0x003c:
        r0 = r5.J;	 Catch:{ all -> 0x0056 }
        r1 = r5.bq;	 Catch:{ all -> 0x0056 }
        r0.setChapterMarks(r1);	 Catch:{ all -> 0x0056 }
    L_0x0043:
        r0 = r5.aM;	 Catch:{ all -> 0x0056 }
        r1 = r5.bq;	 Catch:{ all -> 0x0056 }
        r0.a(r1);	 Catch:{ all -> 0x0056 }
        r0 = r5.mHandler;	 Catch:{ all -> 0x0056 }
        r1 = 2001; // 0x7d1 float:2.804E-42 double:9.886E-321;
        r0 = r0.obtainMessage(r1, r6);	 Catch:{ all -> 0x0056 }
        r0.sendToTarget();	 Catch:{ all -> 0x0056 }
        goto L_0x0015;
    L_0x0056:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0059:
        r1 = com.qq.reader.module.bookchapter.c.a();	 Catch:{ all -> 0x0056 }
        r2 = new com.qq.reader.activity.ReaderPageActivity$130;	 Catch:{ all -> 0x0056 }
        r2.<init>(r5, r6);	 Catch:{ all -> 0x0056 }
        r1.b(r2);	 Catch:{ all -> 0x0056 }
        r1 = 0;
        r2 = r5.aB;	 Catch:{ all -> 0x0056 }
        if (r2 != 0) goto L_0x00a1;
    L_0x006a:
        r2 = r5.J;	 Catch:{ all -> 0x0056 }
        r2 = r2.getBookPath();	 Catch:{ all -> 0x0056 }
        if (r2 == 0) goto L_0x00a1;
    L_0x0072:
        r3 = "/Download/Books/";
        r2 = r2.indexOf(r3);	 Catch:{ all -> 0x0056 }
        r3 = -1;
        if (r2 == r3) goto L_0x00a1;
    L_0x007c:
        r1 = com.qq.reader.module.bookchapter.c.a();	 Catch:{ all -> 0x0056 }
        r1 = r1.c();	 Catch:{ all -> 0x0056 }
        if (r1 != 0) goto L_0x0015;
    L_0x0086:
        r1 = com.qq.reader.module.bookchapter.c.a();	 Catch:{ all -> 0x0056 }
        r2 = r5.J;	 Catch:{ all -> 0x0056 }
        r2 = r2.getEncoding();	 Catch:{ all -> 0x0056 }
        r3 = r5.J;	 Catch:{ all -> 0x0056 }
        r3 = r3.getBookPath();	 Catch:{ all -> 0x0056 }
        r4 = r5.J;	 Catch:{ all -> 0x0056 }
        r4 = r4.getBookName();	 Catch:{ all -> 0x0056 }
        r1.a(r2, r3, r4, r0);	 Catch:{ all -> 0x0056 }
        goto L_0x0015;
    L_0x00a1:
        r0 = r1;
        goto L_0x007c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.ReaderPageActivity.a(java.lang.Object):void");
    }

    private void b(Object obj) {
        try {
            this.aM.a(this.q.getBookCore(), this.J, obj);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("ReaderPage", e.toString());
        }
    }

    private void c(final Object obj) {
        com.qq.reader.common.readertask.g.a().a(new ReaderDBTask() {
            public void run() {
                super.run();
                try {
                    ReaderPageActivity.this.aM.c(ReaderPageActivity.this.q.getBookCore(), ReaderPageActivity.this.J, obj);
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("ReaderPage", e.toString());
                }
            }
        });
    }

    public int[] a(long j, boolean z) {
        int[] iArr = new int[]{1, -1};
        return this.aM.a(this.q.getBookCore(), this.J, j, z);
    }

    private long a(int i, int i2) {
        return this.aM.a(this.q.getBookCore(), this.J, i, i2);
    }

    private void aX() {
        if (!com.qq.reader.common.login.c.b()) {
            return;
        }
        if (this.bq == null && this.aB == 0) {
            com.qq.reader.common.monitor.f.b("CLOUD", "doCloudSynCommitBook mChapterMarks == null");
        } else if (this.aM.b() == null) {
            com.qq.reader.common.monitor.f.b("CLOUD", "doCloudSynCommitBook theFirstcloudTask == null");
        } else {
            com.qq.reader.framework.a.b b = com.qq.reader.common.db.handle.s.a().b(com.qq.reader.appconfig.a.d.R(getApplicationContext()), this.J.getBookNetId());
            if (b != null && b.g() != 0) {
                b.b(this.af);
                List arrayList = new ArrayList();
                arrayList.add(b);
                com.qq.reader.cservice.cloud.e.a().a(arrayList);
            }
        }
    }

    public void synCloudNoteDone(com.qq.reader.framework.a.b bVar) {
        if (bVar != null && this.J != null && bVar.a() == this.J.getBookNetId()) {
            if (this.aB != 1 && (this.aB != 0 || this.bq == null)) {
                return;
            }
            if (bVar.e() == null || bVar.e().size() <= 0) {
                IBook.mRemarksList.clear();
                this.q.g();
                this.q.getTopPage().invalidate();
                return;
            }
            a(bVar.e());
            IBook.mRemarksList.clear();
            for (com.qq.reader.readengine.model.b bVar2 : bVar.e()) {
                if (bVar2.r() == this.af && (this.aB != 1 || bVar2.m() == this.J.getCurIndex())) {
                    IBook.mRemarksList.add(bVar2);
                }
            }
            this.q.g();
            this.q.getTopPage().invalidate();
        }
    }

    public void A() {
        if (this.y <= 0) {
            ReadOnlineResult t = this.q.getBookCore().o().c().t();
            if (t != null) {
                int g = t.g();
                this.y = g;
                int j = t.j();
                if (!(g == 0 || j == 0 || g == j)) {
                    this.y = j;
                }
            } else {
                return;
            }
        }
        new JSPay(this).startCharge(this, this.y);
    }

    public void B() {
        this.mHandler.obtainMessage(1212, this.q.getBookCore().o().c()).sendToTarget();
    }

    public void C() {
        boolean z = false;
        if (this.X == null || !(this.X instanceof DownloadMark)) {
            boolean a;
            com.qq.reader.module.readpage.h.b c = this.q.getBookCore().o().c();
            ReadOnlineResult t = c.t();
            if (t != null) {
                a = t.a();
                z = t.d();
            } else {
                a = false;
            }
            if (a || r0) {
                B();
                return;
            } else if (this.C != null) {
                int o = c.o();
                OnlineTag g = this.C.g();
                g.g(o);
                a(g, o);
                return;
            } else {
                return;
            }
        }
        com.qq.reader.common.download.task.g c2 = this.bd.c(this.X.getBookId());
        if (c2 != null) {
            this.aN.e(c2);
        }
    }

    public void D() {
        OnlineTag g = this.C.g();
        long parseLong = Long.parseLong(this.C.g().k());
        this.C.g().b();
        if (g.w() == 1) {
            b(false, false);
            return;
        }
        if (!com.qq.reader.cservice.bookfollow.c.a(parseLong + "")) {
            com.qq.reader.cservice.bookfollow.c.b(parseLong + "");
        }
        b(true, false);
    }

    public void E() {
        if (com.qq.reader.module.rookie.presenter.a.a().b) {
            i.a("event_A271", null, ReaderApplication.getApplicationImp());
        }
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.F();
                        return;
                    case 2:
                        this.a.y();
                        return;
                    case 3:
                        this.a.y();
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }

    public void F() {
        this.bf = true;
        C();
    }

    public void G() {
        new JSPay(this).openVip();
        j.a(77, 1);
        i.a("event_B78", null, getApplicationContext());
        StatisticsManager.a().a("event_B78", null);
    }

    public void H() {
        i.a("event_B122", null, ReaderApplication.getApplicationImp());
        this.mLoginNextTask = ao.a(this.aQ, (ReaderBaseActivity) this, null);
    }

    public void I() {
        if (com.qq.reader.common.login.c.b()) {
            showFragmentDialog(ErrorCode.STATIC_TBS_INSTALL_SMART_INSTALL_TBS_FINAL_EXCEPTION);
            return;
        }
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        com.qq.reader.common.readertask.g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                            final /* synthetic */ AnonymousClass132 a;

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
                            final /* synthetic */ AnonymousClass132 a;

                            {
                                this.a = r1;
                            }

                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                try {
                                    com.qq.reader.common.monitor.f.a("RentBookQueryTask", str);
                                    JSONObject jSONObject = new JSONObject(str);
                                    boolean optBoolean = jSONObject.optBoolean("isRentBook", false);
                                    boolean optBoolean2 = jSONObject.optBoolean("isRend", false);
                                    if (optBoolean && !optBoolean2) {
                                        this.a.a.mHandler.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass2 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                this.a.a.a.showFragmentDialog(ErrorCode.STATIC_TBS_INSTALL_SMART_INSTALL_TBS_FINAL_EXCEPTION);
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                            }
                        }, this.a.J.getBookNetId()));
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }

    public boolean J() {
        if (this.aB != 1) {
            return this.X == null || !(this.X instanceof DownloadMark);
        } else {
            this.bf = true;
            a(1001, this.C.g().g(), "", -1, getResources().getString(R.string.paypage_getchapter), "", PageIndex.current, "", null);
            return true;
        }
    }

    public void g(int i) {
        switch (i) {
            case 1000:
                this.q.a(PageIndex.current);
                this.q.getTopPage().invalidate();
                return;
            case 1001:
                com.qq.reader.common.monitor.f.a("QQReader", "doBatBuy");
                G();
                return;
            case 1002:
                H();
                return;
            case 1003:
                I();
                return;
            case 1004:
                aZ();
                return;
            case 1005:
                aY();
                return;
            default:
                return;
        }
    }

    private void aY() {
        if (this.ay == null) {
            return;
        }
        if (this.ay.e().i() == -1) {
            this.ay.a(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    if (this.a.mHandler != null) {
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass133 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.a.aW()) {
                                    this.a.a.showFragmentDialog(606);
                                }
                            }
                        });
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    if (this.a.mHandler != null) {
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass133 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.a.aW()) {
                                    this.a.a.b("获取书籍资源失败");
                                }
                            }
                        });
                    }
                }
            });
            aV();
            return;
        }
        showFragmentDialog(606);
    }

    private void aZ() {
        try {
            com.qq.reader.common.charge.voucher.a.a E = this.q.getBookCore().o().c().t().E();
            if (E != null) {
                this.aP.a(E);
            }
            com.qq.reader.common.charge.voucher.b.a().a((Activity) this, new OnDismissListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            }, this.aP.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void K() {
        com.qq.reader.module.readpage.h o = this.q.getBookCore().o();
        if (o.c().p() == 1008) {
            h(false);
        } else if (this.J != null) {
            if (this.J.hasRedpacket()) {
                h(true);
            }
            this.J.setHeadPageBitmap(null);
        }
        switch (o.c().p()) {
            case TbsLog.TBSLOG_CODE_SDK_INIT /*999*/:
                this.q.getTopPage().x();
                if (this.aT != null) {
                    this.aT.a();
                    break;
                }
                break;
            case 1000:
                this.q.getTopPage().t();
                if (this.aT != null) {
                    this.aT.a();
                    break;
                }
                break;
            case 1001:
                this.q.getTopPage().v();
                break;
            case 1003:
            case 1009:
                this.q.getTopPage().w();
                j.a(85, 1);
                i.a("event_B86", null, getApplicationContext());
                StatisticsManager.a().a("event_B86", null);
                boolean b = o.c().b();
                if (b) {
                    j.a(84, 1);
                    i.a("event_B85", null, getApplicationContext());
                    StatisticsManager.a().a("event_B85", null);
                }
                if ((this.ax == null || !this.ax.d()) && o.c().p() == 1009) {
                    if (this.aT == null) {
                        this.aT = new r(this, this.q.getBookCore().o().a());
                    }
                    this.aT.a(this.J != null ? this.J.getBookNetId() : 0, b);
                    bs();
                    com.qq.reader.module.rookie.presenter.a.a().b((Activity) this);
                    break;
                }
            case 1004:
                this.q.getTopPage().y();
                break;
            case 1005:
                this.q.getTopPage().z();
                break;
            case 1006:
                this.q.getTopPage().A();
                break;
            case 1008:
                com.qq.reader.common.imageloader.c.a((Context) this).a(ao.f(this.J.getBookNetId()), new com.bumptech.glide.request.b.g(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                        if (obj instanceof com.bumptech.glide.load.resource.bitmap.j) {
                            com.bumptech.glide.load.resource.bitmap.j jVar = (com.bumptech.glide.load.resource.bitmap.j) obj;
                            if (this.a.J != null && this.a.J.getHeadPageBitmap() == null) {
                                this.a.J.setHeadPageBitmap(jVar.b());
                                if (this.a.q.getBookCore().b().m()) {
                                    this.a.mHandler.post(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass3 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            this.a.a.q.g();
                                            this.a.a.q.getTopPage().invalidate();
                                        }
                                    });
                                }
                                this.a.q.g();
                                this.a.q.getTopPage().invalidate();
                            }
                        }
                    }
                }, com.qq.reader.common.imageloader.a.a().g());
                this.q.getTopPage().u();
                break;
        }
        be();
        this.q.getTopPage().invalidate();
    }

    private void g(boolean z) {
        String string;
        OnlineTag g = this.C.g();
        int g2 = g.g();
        this.J.getFileCount();
        if (z) {
            string = getString(R.string.paypage_title_endpage);
        } else {
            com.qq.reader.cservice.bookfollow.c.b(g.k());
            string = getString(R.string.paypage_title_wait);
        }
        a(1004, g2, "", -1, getString(R.string.paypage_recommend), "", PageIndex.next, string, null);
    }

    public void finish() {
        Intent intent = getIntent();
        if (!intent.getBooleanExtra("is_from_splashactivity", false)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                this.aj = extras.getInt("readfrom", 11000);
            } else {
                this.aj = 11000;
            }
            switch (this.aj) {
                case 11002:
                    backRootActivity();
                    break;
                default:
                    break;
            }
        }
        backRootActivity();
        j.e++;
        j.n = true;
        super.finish();
    }

    public void L() {
        q();
    }

    public void M() {
        at();
    }

    public ar N() {
        if (this.br == null) {
            this.br = new ar(this);
            this.br.a(this);
        }
        return this.br;
    }

    private void ba() {
        this.q.D();
        if (this.br != null) {
            this.br.cancel();
            this.br.g();
        }
        com.qq.reader.plugin.tts.n.f().g();
        this.q.getBookCore().u();
        bc();
        com.qq.reader.common.c.a.X = null;
    }

    private void n(int i) {
        if (this.q.getBookCore().o().c().p() == TbsLog.TBSLOG_CODE_SDK_INIT) {
            if (this.J != null) {
                if (!com.qq.reader.common.db.handle.e.a().a((Context) this, this.J.getBookNetId())) {
                    b("版权限制，本书不支持人声朗读");
                    return;
                }
            }
            try {
                if (!(this.J == null || this.J.getBookName() == null)) {
                    com.qq.reader.common.c.a.X = this.J.getBookName();
                }
                bb();
                this.q.C();
                this.q.getBookCore().t();
                if (i == 0) {
                    com.qq.reader.plugin.tts.n.f().a((Activity) this);
                } else {
                    com.qq.reader.plugin.tts.n.f().a((Context) this);
                }
                com.qq.reader.plugin.tts.n.f().a(this.mHandler);
                com.qq.reader.plugin.tts.n.f().a(this.q.getBookCore().s());
                com.qq.reader.plugin.tts.n.f().n();
            } catch (Exception e) {
                com.qq.reader.appconfig.a.d.o((Context) this, "");
                b("人声朗读启动出错，请退出重试，或跟客服联系");
            }
        } else if (this.C != null) {
            int b = new g(this, this.C.g()).b();
            if (b == 2) {
                b("请先购买本章");
            } else if (b == 1) {
                b("请先购买本书");
            }
        } else {
            b("当前页面不支持人声朗读");
        }
    }

    private void bb() {
        com.qq.reader.plugin.tts.o.a(this, this);
    }

    private void bc() {
        com.qq.reader.plugin.tts.o.a(this);
    }

    public void O() {
        ba();
    }

    public void h(int i) {
        ba();
        if (i == 1) {
            n(1);
        } else {
            this.mHandler.postDelayed(new Runnable(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.n(0);
                }
            }, 500);
        }
    }

    public void P() {
        Intent intent = new Intent();
        intent.setAction(UtilityConfig.SETTINGS_ACTION_TTS);
        intent.setPackage(UtilityConfig.COMPONENT_PKG);
        Bundle bundle = new Bundle();
        bundle.putString("abc", com.qq.reader.appconfig.a.d.ay(getApplicationContext()));
        intent.putExtras(bundle);
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void bd() {
        Intent intent = new Intent();
        intent.setClass(this, PlugInDefaultActivity.class);
        intent.putExtra("PLUGIN_TYPE", "7");
        startActivity(intent);
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public void a(String str) {
        if (com.qq.reader.common.monitor.a.b.a(str).length() == 0) {
            com.qq.reader.common.monitor.a.a b = com.qq.reader.common.db.handle.j.a().b(str);
            if (b != null) {
                com.qq.reader.common.monitor.a.b.a(new com.qq.reader.common.monitor.a.a(str, b.b()));
            }
        }
    }

    private void be() {
        com.qq.reader.module.readpage.h o = this.q.getBookCore().o();
        int i = TbsLog.TBSLOG_CODE_SDK_INIT;
        if (o != null) {
            i = o.c().p();
        }
        if (i == 1000 || i == 1008 || i == 1001 || i == 1005 || i == 1003 || i == 1004) {
            this.F.setmFootInfo("");
        } else if (this.J != null && this.J.getReadType() == 1) {
            this.F.setmFootInfo(j(this.C.g().g()));
        } else if (this.J != null && this.J.getReadType() == 0) {
            String bookShortName = this.J.getBookShortName();
            if (bookShortName == null || bookShortName.trim().length() == 0) {
                bookShortName = ao.l(this.J.getBookPath());
            }
            if (bookShortName == null) {
                bookShortName = "";
            }
            this.F.setmFootInfo(bookShortName);
        }
    }

    private void f(OnlineTag onlineTag) {
        if (this.ay == null) {
            this.ay = new g(getApplicationContext(), onlineTag.z());
        }
        this.ay.a(this.mHandler);
        if (com.qq.reader.common.utils.m.a((Context) this)) {
            this.ay.b(true);
        } else {
            this.ay.a(true);
        }
    }

    public void b(com.qq.reader.cservice.buy.a.c cVar) {
        if (cVar != null) {
            Message obtain = Message.obtain();
            obtain.what = 1218;
            obtain.obj = cVar;
            this.mHandler.sendMessage(obtain);
        }
    }

    public void c(com.qq.reader.cservice.buy.a.c cVar) {
        Message obtain = Message.obtain();
        obtain.what = 1219;
        obtain.obj = cVar;
        this.mHandler.sendMessage(obtain);
    }

    private void bf() {
        if (this.ab == null || !this.ab.isShowing()) {
            this.ab = ProgressDialog.show(this, "", "正在购买，请稍候...", true);
            this.ab.setCanceledOnTouchOutside(false);
        }
    }

    private boolean bg() {
        try {
            if (this.ab != null && this.ab.isShowing()) {
                this.ab.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void Q() {
        if (this.w == null) {
            this.w = new at(this, this.J.getBookNetId(), this.J.getAuthorIcon());
        }
        if (!this.w.f()) {
            at.b = "2";
            as.E = "2";
            this.w.f_();
        }
    }

    public void R() {
        if (this.aB != 1) {
            bh();
        } else if (aJ()) {
            showFragmentDialog(305);
        } else {
            bh();
        }
    }

    private void bh() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        startActivity(intent);
        finish();
    }

    public void a(String str, String str2) {
        getShareDialog().a(str, str2);
        getShareDialog().f_();
        Map hashMap = new HashMap();
        hashMap.put("from", "1");
        i.a("event_M88", hashMap, ReaderApplication.getApplicationImp());
    }

    private boolean bi() {
        try {
            if (this.bs != null && this.bs.f()) {
                this.bs.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void bj() {
        Bundle extras = getIntent().getExtras();
        final String string = extras.getString("filepath");
        final int i = extras.getInt("book_chapterid");
        final int i2 = extras.getInt("book_chapter_offset");
        if (string != null && string.trim().length() > 0) {
            ReaderTask queryBookIntroTask = new QueryBookIntroTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderPageActivity d;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String optString = jSONObject.optString("downloadinfo");
                        int optInt = jSONObject.optInt("lastupdatechapterid");
                        int optInt2 = jSONObject.optInt("drm");
                        int optInt3 = jSONObject.optInt("isfinished");
                        String optString2 = jSONObject.optString("title");
                        String optString3 = jSONObject.optString("author");
                        String optString4 = jSONObject.optString("fileformat");
                        String optString5 = jSONObject.optString("downloadUrl");
                        if (optString == null || optString.indexOf("txt") == -1) {
                            com.qq.reader.framework.mark.a.a(Long.valueOf(string).longValue(), optString2, optString3, optString);
                            this.d.mHandler.sendEmptyMessage(1235);
                            return;
                        }
                        OnlineTag onlineTag = new OnlineTag(string, "", 0);
                        onlineTag.a(optString2).e(optString3).f(optString5).c(i).a((long) i2).b("").d(optInt).h(ao.g(Long.parseLong(string))).k(optString4).i(optInt2).h(optInt3);
                        onlineTag.a(false);
                        v.b().b(onlineTag);
                        this.d.getIntent().putExtra("com.qq.reader.OnlineTag", onlineTag);
                        this.d.getIntent().putExtra("com.qq.reader.OnlineTag.web.chapter", true);
                        Message obtain = Message.obtain();
                        obtain.what = 1224;
                        obtain.obj = onlineTag;
                        this.d.mHandler.sendMessage(obtain);
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.d.mHandler.sendEmptyMessage(1225);
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.d.mHandler.sendEmptyMessage(1225);
                }
            }, string);
            if (this.bs == null) {
                this.bs = new com.qq.reader.view.c(this);
                this.bs.c(true);
            }
            this.bs.a("正在拉取书籍信息...");
            this.bs.f_();
            com.qq.reader.common.readertask.g.a().a(queryBookIntroTask);
        }
    }

    private void bk() {
        if (this.X != null && com.qq.reader.readengine.model.a.n(this.X.getId())) {
            com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                public void run() {
                    String o = com.qq.reader.readengine.model.a.o(ReaderPageActivity.this.X.getId());
                    if (new File(o).exists()) {
                        com.qq.reader.common.db.handle.i.c().c(o, false);
                        format.epub.common.a.a.b(o);
                        ao.a(new File(o));
                        ao.a(new File(com.qq.reader.common.drm.a.a(o)));
                        ao.a(new File(com.qq.reader.common.drm.a.b(o)));
                    }
                }
            });
        }
    }

    private void a(AlertDialog alertDialog, Activity activity) {
        String b;
        int p;
        String w;
        int i;
        String str;
        int i2;
        Object obj;
        int i3;
        View inflate = LayoutInflater.from(activity).inflate(R.layout.book_buy_view_new, null);
        TextView textView = (TextView) inflate.findViewById(R.id.book_discount_msg);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_origin_price);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_price);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.pb_user_balance);
        String str2 = "";
        str2 = "";
        str2 = "";
        int i4 = R.string.alert_dialog_buy;
        Object obj2 = null;
        int s;
        String r;
        int v;
        int i5;
        if (this.aB == 1) {
            OnlineTag g = this.C.g();
            b = g.b();
            g.k();
            p = this.ay.e().p();
            s = this.ay.e().s();
            r = this.ay.e().r();
            v = this.ay.e().v();
            w = this.ay.e().w();
            if (this.ay.e().n()) {
                i4 = R.string.alert_dialog_subscription;
                obj2 = 1;
            }
            i = v;
            str = r;
            i2 = p;
            p = i4;
            i4 = this.ay.e().q();
            i5 = s;
            obj = obj2;
            i3 = i5;
        } else if (this.X != null) {
            this.X.getId();
            b = this.X.getBookShortName();
            p = this.az.c().p();
            s = this.az.c().s();
            r = this.az.c().r();
            v = this.az.c().v();
            w = this.az.c().w();
            if (this.az.c().n()) {
                i4 = R.string.alert_dialog_subscription;
                obj2 = 1;
            }
            i = v;
            str = r;
            i2 = p;
            p = i4;
            i4 = this.az.c().q();
            i5 = s;
            obj = obj2;
            i3 = i5;
        } else {
            return;
        }
        if (i3 < 100 || i > 0 || i4 > 0) {
            Object obj3;
            i3 = (i3 * i2) / 100;
            if (i <= 0 || i >= i3) {
                i = i3;
            } else {
                str = w;
            }
            obj2 = null;
            if (i4 <= 0 || ((i <= 0 || i4 >= i) && i != 0)) {
                i4 = i;
                String str3 = str;
            } else {
                obj2 = 1;
                obj3 = w;
            }
            if (TextUtils.isEmpty(obj3)) {
                textView.setVisibility(8);
            } else {
                textView.setText("(" + obj3 + ")");
                textView.setVisibility(0);
            }
            textView3.setText(String.valueOf(i4));
            if (obj2 != null) {
                textView2.setVisibility(8);
            } else {
                Object obj4 = String.valueOf(i2) + "书币";
                CharSequence spannableString = new SpannableString(obj4);
                spannableString.setSpan(new StrikethroughSpan(), 0, obj4.length(), 33);
                textView2.setText(spannableString);
            }
        } else {
            textView.setVisibility(8);
            textView2.setVisibility(8);
            textView3.setText(String.valueOf(i2));
            i4 = i2;
        }
        textView = (TextView) inflate.findViewById(R.id.book_name);
        textView.setText(String.format(getString(R.string.buy_book_name), new Object[]{b}));
        int bm = bm();
        CharSequence bl = bl();
        textView = (TextView) inflate.findViewById(R.id.tv_user_balance);
        if (bm < 0) {
            progressBar.setVisibility(0);
            bl = "";
        } else {
            progressBar.setVisibility(8);
        }
        textView.setText(bl);
        if (this.aP.d > 0) {
            com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            }, new OnDismissListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            }, this.aP.g);
        }
        alertDialog.a(inflate);
        alertDialog.setTitle(getString(p));
        int i6;
        final AlertDialog alertDialog2;
        if (bm < 0 || bm >= i4) {
            i6 = R.string.alert_dialog_buy_confirm;
            if (obj != null) {
                i6 = R.string.alert_dialog_subscription_confirm;
            }
            alertDialog2 = alertDialog;
            alertDialog.a(i6, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog2.c();
                    this.b.T();
                }
            });
            alertDialog2 = alertDialog;
            alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog2.c();
                    if (this.b.aB == 2) {
                        this.b.finish();
                    }
                }
            });
        } else {
            this.y = i4;
            i6 = R.string.alert_dialog_buy_balance_insufficient;
            if (obj != null) {
                i6 = R.string.alert_dialog_subscription_balance_insufficient;
            }
            alertDialog2 = alertDialog;
            alertDialog.a(i6, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog2.c();
                    this.b.z = true;
                    this.b.A();
                }
            });
        }
        alertDialog.a(-1, (int) R.drawable.selector_orange_button);
        alertDialog.a(-2, (int) R.drawable.selector_white_button);
        a(alertDialog);
    }

    private String bl() {
        return this.aP.b();
    }

    private int bm() {
        return this.aP.a();
    }

    public void a(final AlertDialog alertDialog) {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new com.qq.reader.common.readertask.protocol.QueryUserBalanceTask.a(this) {
            final /* synthetic */ ReaderPageActivity b;

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.b.aP.a(aVar);
                Message obtainMessage = this.b.mHandler.obtainMessage(8000011);
                obtainMessage.obj = alertDialog;
                this.b.mHandler.sendMessage(obtainMessage);
            }

            public void a() {
            }
        }, String.valueOf(this.J.getBookNetId()), 0));
    }

    public void b(final AlertDialog alertDialog) {
        int i = 1;
        if (alertDialog != null && alertDialog.isShowing()) {
            TextView textView = (TextView) alertDialog.findViewById(R.id.tv_user_balance);
            if (textView != null) {
                textView.setText(bl());
            }
            if (this.aP.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    class AnonymousClass1 extends ReaderShortTask {
                        final /* synthetic */ AnonymousClass39 this$1;
                        final /* synthetic */ int val$newChapterId;

                        AnonymousClass1(AnonymousClass39 anonymousClass39, int i) {
                            this.this$1 = anonymousClass39;
                            this.val$newChapterId = i;
                        }

                        public void run() {
                            this.this$1.a.J.initFileList(this.this$1.a.C.g(), true);
                            this.this$1.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.this$1.a.q.g();
                                    this.a.this$1.a.q.getTopPage().invalidate();
                                    com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                                    gVar.a(this.a.val$newChapterId, 0);
                                    this.a.this$1.a.a(gVar, false, true, false);
                                }
                            });
                        }
                    }

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.aP.g);
            }
            ProgressBar progressBar = (ProgressBar) alertDialog.findViewById(R.id.pb_user_balance);
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
            Button c = alertDialog.c(-1);
            Button c2 = alertDialog.c(-2);
            if (c != null && c2 != null) {
                int s;
                int v;
                int i2;
                if (this.aB == 1) {
                    this.C.g();
                    int p = this.ay.e().p();
                    s = this.ay.e().s();
                    v = this.ay.e().v();
                    if (this.ay.e().n()) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    i = i2;
                    i2 = v;
                    v = s;
                    s = p;
                } else {
                    s = this.az.c().p();
                    v = this.az.c().s();
                    i2 = this.az.c().v();
                    if (!this.az.c().n()) {
                        i = 0;
                    }
                }
                v = (v * s) / 100;
                if (i2 <= 0 || i2 >= v) {
                    i2 = v;
                }
                v = bm();
                if (v < 0 || v >= i2) {
                    c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ReaderPageActivity b;

                        public void onClick(View view) {
                            alertDialog.c();
                            this.b.T();
                        }
                    });
                    i2 = R.string.alert_dialog_buy_confirm;
                    if (i != 0) {
                        i2 = R.string.alert_dialog_subscription_confirm;
                    }
                    c.setText(getString(i2));
                    c2.setVisibility(0);
                    c2.setText(getString(R.string.alert_dialog_cancel));
                    c2.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ReaderPageActivity b;

                        public void onClick(View view) {
                            alertDialog.c();
                            if (this.b.aB == 2) {
                                this.b.finish();
                            }
                        }
                    });
                    return;
                }
                this.y = i2;
                if (!ao.s(this.aP.e)) {
                    View findViewById;
                    if (getResources().getConfiguration().orientation == 2) {
                        findViewById = alertDialog.findViewById(R.id.activity_info_land);
                        textView = (TextView) alertDialog.findViewById(R.id.activity_text_land);
                    } else {
                        findViewById = alertDialog.findViewById(R.id.activity_info);
                        textView = (TextView) alertDialog.findViewById(R.id.activity_text);
                    }
                    findViewById.setVisibility(0);
                    textView.setText(this.aP.e);
                    textView.setVisibility(0);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "4");
                    i.a("event_A202", hashMap, getApplicationContext());
                }
                c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity b;

                    public void onClick(View view) {
                        alertDialog.c();
                        this.b.z = true;
                        this.b.A();
                    }
                });
                i2 = R.string.alert_dialog_buy_balance_insufficient;
                if (i != 0) {
                    i2 = R.string.alert_dialog_subscription_balance_insufficient;
                }
                c.setText(getString(i2));
                c2.setVisibility(8);
            }
        }
    }

    public void c(final AlertDialog alertDialog) {
        if (alertDialog != null && alertDialog.isShowing()) {
            TextView textView = (TextView) alertDialog.findViewById(R.id.tv_user_balance);
            if (textView != null) {
                textView.setText(bl());
            }
            if (this.aP.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ ReaderPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.aP.g);
            }
            ProgressBar progressBar = (ProgressBar) alertDialog.findViewById(R.id.pb_user_balance);
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
            final Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, "1");
            Button c = alertDialog.c(-1);
            Button c2 = alertDialog.c(-2);
            if (c != null && c2 != null) {
                int i = this.q.getBookCore().o().c().i();
                int bm = bm();
                if (bm < 0 || bm >= i) {
                    c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ReaderPageActivity c;

                        public void onClick(View view) {
                            alertDialog.c();
                            this.c.bn();
                            i.a("event_C224", hashMap, ReaderApplication.getApplicationImp());
                        }
                    });
                    c.setText(getString(R.string.alert_dialog_rent_confirm));
                    c2.setVisibility(0);
                    c2.setText(getString(R.string.alert_dialog_cancel));
                    c2.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ReaderPageActivity c;

                        public void onClick(View view) {
                            alertDialog.c();
                            i.a("event_C225", hashMap, ReaderApplication.getApplicationImp());
                        }
                    });
                    return;
                }
                this.y = i;
                c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ReaderPageActivity c;

                    public void onClick(View view) {
                        alertDialog.c();
                        this.c.A = true;
                        this.c.A();
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
        hashMap.put(s.ORIGIN, "1");
        i.a("event_C223", hashMap, ReaderApplication.getApplicationImp());
        View inflate = LayoutInflater.from(activity).inflate(R.layout.book_rent_view, null);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.pb_user_balance);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_rent_price);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_rent_day);
        String bookName = this.J.getBookName();
        int i = this.q.getBookCore().o().c().i();
        int j = this.q.getBookCore().o().c().j();
        textView.setText(String.valueOf(i));
        textView2.setText(String.valueOf(j));
        textView = (TextView) inflate.findViewById(R.id.book_name);
        textView.setText(String.format(getString(R.string.buy_book_name), new Object[]{bookName}));
        int a = this.aP.a();
        CharSequence bl = bl();
        if (a < 0) {
            bl = "";
            progressBar.setVisibility(0);
        } else {
            progressBar.setVisibility(8);
        }
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_user_balance);
        textView3.setText(bl);
        if (this.aP.d > 0) {
            com.qq.reader.common.charge.voucher.b.a(this, textView3, new OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            }, new OnDismissListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            }, this.aP.g);
        }
        alertDialog.a(inflate);
        alertDialog.setTitle(getString(R.string.alert_dialog_rent));
        if (a < 0 || a >= i) {
            alertDialog.a((int) R.string.alert_dialog_rent_confirm, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                    this.c.bn();
                    i.a("event_C224", hashMap, ReaderApplication.getApplicationImp());
                }
            });
            alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                    i.a("event_C225", hashMap, ReaderApplication.getApplicationImp());
                }
            });
        } else {
            this.y = i;
            alertDialog.a((int) R.string.alert_dialog_buy_balance_insufficient, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.c();
                    this.c.A = true;
                    this.c.A();
                    i.a("event_C227", hashMap, ReaderApplication.getApplicationImp());
                }
            });
            i.a("event_C226", hashMap, ReaderApplication.getApplicationImp());
        }
        alertDialog.a(-1, (int) R.drawable.buy_book_dialog_confirm_bg);
        d(alertDialog);
    }

    public void d(final AlertDialog alertDialog) {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new com.qq.reader.common.readertask.protocol.QueryUserBalanceTask.a(this) {
            final /* synthetic */ ReaderPageActivity b;

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.b.aP.a(aVar);
                Message obtainMessage = this.b.mHandler.obtainMessage(8000012);
                obtainMessage.obj = alertDialog;
                this.b.mHandler.sendMessage(obtainMessage);
            }

            public void a() {
            }
        }, String.valueOf(this.J.getBookNetId()), 0));
    }

    public void S() {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new com.qq.reader.common.readertask.protocol.QueryUserBalanceTask.a(this) {
            final /* synthetic */ ReaderPageActivity a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.a.aP.a(aVar);
                Message obtain = Message.obtain();
                obtain.what = 400008;
                this.a.mHandler.sendMessage(obtain);
            }

            public void a() {
            }
        }, String.valueOf(this.J.getBookNetId()), 0));
    }

    public void T() {
        int bm = bm();
        if (bm > 0) {
            if (this.y <= 0 || bm >= this.y) {
                Object obj = null;
                if (this.C != null) {
                    obj = this.C.g().k();
                } else if (this.X != null) {
                    obj = String.valueOf(this.X.getBookId());
                }
                if (!TextUtils.isEmpty(obj)) {
                    com.qq.reader.cservice.buy.a.d dVar = new com.qq.reader.cservice.buy.a.d(getApplicationContext(), obj);
                    dVar.a((com.qq.reader.cservice.buy.a.b) this);
                    dVar.start();
                    bf();
                }
            }
        }
    }

    private void bn() {
        com.qq.reader.common.readertask.g.a().a(new RentBookTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ ReaderPageActivity a;

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
                            final /* synthetic */ AnonymousClass37 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                af.a(this.a.a, (CharSequence) "租书成功", 0).a();
                                this.a.a.ao = true;
                                this.a.a.aL();
                                this.a.a.g();
                            }
                        });
                        Message obtain = Message.obtain();
                        obtain.what = 1212;
                        obtain.obj = this.a.q.getBookCore().o().c();
                        obtain.arg1 = 101;
                        this.a.mHandler.sendMessageDelayed(obtain, 150);
                        return;
                    }
                    this.a.mHandler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass37 b;

                        public void run() {
                            af.a(this.b.a, optString, 0).a();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass37 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        af.a(this.a.a, (CharSequence) "网络链接失败！", 0).a();
                    }
                });
            }
        }, this.J.getBookNetId()));
    }

    private void bo() {
        this.bt = this.J.getBookVoteInfo();
        if (this.bt == null) {
            this.J.setBookVoteInfo(true, true, true);
            this.bt = this.J.getBookVoteInfo();
            com.qq.reader.common.readertask.g.a().a(new VoteTypeQueryTask(this.J.getBookNetId(), new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    com.qq.reader.common.monitor.f.a("str", str);
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("code");
                        if (optInt == 0) {
                            jSONObject = jSONObject.getJSONObject("results").getJSONObject("TMR");
                            this.a.J.setBookVoteInfo(jSONObject.optBoolean("first", false), jSONObject.optBoolean("second", false), jSONObject.optBoolean("third", false));
                            this.a.bt = this.a.J.getBookVoteInfo();
                        } else if (optInt == 8000) {
                            this.a.J.setBookVoteInfo(false, false, false);
                            this.a.bt = this.a.J.getBookVoteInfo();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            }));
        }
    }

    private boolean bp() {
        if (this.C == null || this.C.g() == null) {
            return false;
        }
        OnlineTag g = this.C.g();
        int i = -1;
        if (this.ay != null) {
            i = this.ay.c();
        } else if (g != null) {
            g gVar = new g(ReaderApplication.getApplicationImp(), g.z());
            if (gVar != null) {
                i = gVar.c();
            }
        }
        if (g == null || r0 != 2) {
            return false;
        }
        return true;
    }

    public void excuteOnSwitchAccount(Context context) {
        super.excuteOnSwitchAccount(context);
        if (this.q.getBookCore().o().f() == 1005) {
            C();
        }
    }

    private AlertDialog bq() {
        if (this.aU == null) {
            this.aU = new com.qq.reader.view.AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.continue_read_dialog_title).b((int) R.string.continue_read_dialog_tips).a((int) R.string.continue_read_dialog_agree, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!(this.a.isFinishing() || this.a.aU.isShowing())) {
                        this.a.aU.dismiss();
                    }
                    this.a.q.b(true);
                    this.a.q.v();
                    this.a.q.m();
                }
            }).b((int) R.string.continue_read_dialog_disagree, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!(this.a.isFinishing() || this.a.aU.isShowing())) {
                        this.a.aU.dismiss();
                    }
                    this.a.q.m();
                    this.a.mHandler.removeMessages(1244);
                    this.a.mHandler.sendEmptyMessageDelayed(1244, 1800000);
                    com.qq.reader.common.monitor.debug.c.e("AUTO", "send msg");
                }
            }).a(new OnCancelListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (!(this.a.isFinishing() || this.a.aU.isShowing())) {
                        this.a.aU.dismiss();
                    }
                    this.a.q.b(true);
                    this.a.q.v();
                    this.a.q.m();
                }
            }).a();
        }
        return this.aU;
    }

    private void h(boolean z) {
        if (this.aW == null) {
            this.aW = (ImageView) findViewById(R.id.red_package);
            this.aW.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ReaderPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    com.qq.reader.common.utils.o.a(this.a, this.a.J.getBookName(), this.a.J.getBookNetId(), 0, true, null);
                    i.a("event_D208", null, ReaderApplication.getApplicationImp());
                }
            });
        }
        if (!z || this.q.getBookCore().b().m()) {
            this.aW.setVisibility(8);
            return;
        }
        this.aW.setVisibility(0);
        if (com.qq.reader.appconfig.a.d.n) {
            this.aW.setImageResource(R.drawable.redpacket_top_night);
        } else {
            this.aW.setImageResource(R.drawable.redpacket_top);
        }
        i.a("event_D207", null, ReaderApplication.getApplicationImp());
    }

    private void br() {
        boolean z = false;
        if (!com.qq.reader.module.rookie.presenter.a.a().f() && !com.qq.reader.module.rookie.presenter.a.a().e()) {
            try {
                if (this.C.g() != null) {
                    z = this.q.getBookCore().o().c().b();
                }
                com.qq.reader.module.rookie.a.b a = com.qq.reader.module.rookie.presenter.a.a().a("p2", this.J.getBookNetId(), z);
                if (a != null) {
                    this.aY = l.a(this, a, new OnDismissListener(this) {
                        final /* synthetic */ ReaderPageActivity a;

                        {
                            this.a = r1;
                        }

                        public void onDismiss(DialogInterface dialogInterface) {
                            com.qq.reader.cservice.adv.a.c(ReaderApplication.getApplicationImp(), "CHANNEL_ADV_CLOSE_TIME");
                        }
                    }, 2);
                    if (this.aY != null && this.mHandler != null) {
                        this.aY.a(a, this.mHandler, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void bs() {
        if (com.qq.reader.module.rookie.presenter.a.a().c && !com.qq.reader.module.rookie.presenter.a.a().d() && !com.qq.reader.cservice.adv.b.a && !com.qq.reader.cservice.adv.b.b && this.mHandler != null && this.aB == 1) {
            try {
                if (this.q.getBookCore().o().c().p() == 1009) {
                    this.mHandler.removeMessages(65545);
                    this.mHandler.sendEmptyMessageDelayed(65545, 100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(Intent intent) {
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (this.aB != 0) {
                    final OnlineTag onlineTag = (OnlineTag) extras.getParcelable("resultOnlinetag");
                    if (onlineTag != null) {
                        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ ReaderPageActivity b;

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        onlineTag.a(false);
                                        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                                        gVar.a(true);
                                        gVar.a(onlineTag.s(), onlineTag.i());
                                        this.b.a(gVar, false, true, false);
                                        return;
                                    default:
                                        return;
                                }
                            }
                        };
                        onlineTag.a(false);
                        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                        gVar.a(onlineTag.s(), onlineTag.i());
                        gVar.a(true);
                        a(gVar, false, true, false);
                        this.q.getBookCore().p();
                    }
                } else if (this.X == null || this.X.getBookId() <= 0 || !com.qq.reader.readengine.model.a.k(this.X.getId()) || extras.getBoolean(ReaderPagerChapterFragment.RESULT_BOOKMARK_FREE, true)) {
                    long j = extras.getLong(ReaderPagerChapterFragment.RESULT_BOOKMARK_POINT);
                    com.qq.reader.readengine.kernel.g gVar2 = new com.qq.reader.readengine.kernel.g();
                    gVar2.a(j);
                    if (this.J != null) {
                        this.J.mTurePageCmd = 101;
                    }
                    a(gVar2, false, false, true);
                    this.q.getBookCore().p();
                    if (this.q.getTopPage().h()) {
                        this.q.getTopPage().i();
                    }
                } else {
                    a(this.X);
                    return;
                }
                if (this.aG.getCurrentState() != 101) {
                    this.mHandler.post(new Runnable(this) {
                        final /* synthetic */ ReaderPageActivity a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.aG.h();
                        }
                    });
                }
            }
        }
    }
}
