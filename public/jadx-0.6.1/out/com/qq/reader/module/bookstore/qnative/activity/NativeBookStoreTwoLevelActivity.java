package com.qq.reader.module.bookstore.qnative.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BaseWebTabActivity;
import com.qq.reader.activity.ClassifySelectActivity;
import com.qq.reader.activity.CommitCommentActivity;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.CommentDetailSetBanCommentTask;
import com.qq.reader.common.readertask.protocol.CommentDetailSetBestCommentTask;
import com.qq.reader.common.readertask.protocol.CommentDetailSetLockPostTask;
import com.qq.reader.common.readertask.protocol.CommentDetailSetTopTask;
import com.qq.reader.common.readertask.protocol.DelCommentTask;
import com.qq.reader.common.readertask.protocol.IllegalCommentReportTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragment;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentOfClub;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentOfReply;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforClassify;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforClassify_1;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.impl.ag;
import com.qq.reader.module.bookstore.qnative.page.impl.ao;
import com.qq.reader.module.bookstore.qnative.page.impl.v;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.feed.mypreference.MyFeedPreferenceActivity;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.GuideShadowView;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.view.c;
import com.qq.reader.view.k;
import com.qq.reader.view.web.n;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeBookStoreTwoLevelActivity extends BaseWebTabActivity implements e, a, n.a {
    private String A = "0";
    private View B;
    private View C;
    private c D;
    private com.qq.reader.view.linearmenu.a E;
    private ImageView F;
    private final int G = f.SUCCESS;
    private final int H = f.INSUFFICIENT_MEMORY;
    private final int I = f.USER_CANCELLED;
    private final int J = f.LOSS_OF_SERVICE;
    private final int K = 904;
    private final int L = 905;
    private final int M = f.INVALID_DESCRIPTOR;
    private final int N = 907;
    private final int O = 908;
    private final int P = 909;
    private final int Q = 910;
    private final int R = 911;
    private final String S = "置顶";
    private final String T = "取消置顶";
    private final String U = "设为精华书评";
    private final String V = "取消精华书评";
    private final String W = "锁贴";
    private final String X = "解锁";
    private final String Y = "删除";
    private final String Z = "禁言7天";
    private final String aa = "解禁";
    private final String ab = "回复";
    private final String ac = "设为神回复";
    private final String ad = "取消神回复";
    private int ae = 0;
    private int af = 0;
    private int ag = 0;
    private int ah = 0;
    private int ai = 6;
    private int aj = 0;
    private String ak;
    private BroadcastReceiver al = new BroadcastReceiver(this) {
        final /* synthetic */ NativeBookStoreTwoLevelActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (com.qq.reader.common.c.a.cB.equals(action) || com.qq.reader.common.c.a.cC.equals(action)) {
                this.a.c(this.a.r);
            }
        }
    };
    private GuideShadowView am;
    private k an;
    private com.qq.reader.common.emotion.a ao = null;
    private Context k;
    protected String l = "";
    protected b m;
    protected OnClickListener n = null;
    protected n o;
    protected ImageView p = null;
    protected int q = 0;
    protected Bundle r = null;
    protected Button s;
    protected View t = null;
    protected View u = null;
    View v = null;
    private List<d.a> w = new ArrayList();
    private String x = null;
    private d y;
    private boolean z = false;

    protected void onCreate(Bundle bundle) {
        this.k = getApplicationContext();
        this.r = getIntent().getExtras();
        super.onCreate(bundle);
        B();
        if (this.r != null && this.r.containsKey("SHOWCOMMENTACTIVITY")) {
            boolean z = this.r.getBoolean("SHOWCOMMENTACTIVITY");
            this.r.remove("URL_DATA_QURL");
            c(this.r);
            if (z) {
                d(new Bundle(this.r));
            }
        }
        if (this.r != null) {
            this.mStatPageName = this.r.getString("KEY_JUMP_PAGENAME");
            if (this.mStatPageName != null && "bookclubmain".equals(this.mStatPageName)) {
                i.a("event_D201", null, getApplicationContext());
            }
        }
        if (!"1".equals(this.r.getString("fromgene")) || MyFeedPreferenceActivity.a) {
            c(this.r);
        } else {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(com.qq.reader.common.c.a.cB);
            intentFilter.addAction(com.qq.reader.common.c.a.cC);
            registerReceiver(this.al, intentFilter);
            s();
        }
        if (com.qq.reader.module.bookstore.search.f.a(this.r.getString("KEY_ACTIONID"))) {
            Button button = (Button) findViewById(R.id.profile_header_right_button);
            button.setText(getResources().getString(R.string.more_rank));
            button.setVisibility(0);
            button.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.b(this.a, this.a.getResources().getString(R.string.rank_list), null, null);
                }
            });
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.z) {
            this.mHandler.sendEmptyMessageDelayed(1227, 500);
        }
    }

    public void j() {
        String string = this.r.getString("KEY_JUMP_PAGENAME");
        Map hashMap;
        long j;
        ImageView imageView;
        if ("bookclubmain".equals(string) || "discovery_comment_detail".equals(string)) {
            if ("bookclubmain".equals(string)) {
                hashMap = new HashMap();
                j = this.r.getLong("URL_BUILD_PERE_BOOK_ID", 0);
                if (j == 570698) {
                    hashMap.put(s.ORIGIN, "1");
                    i.a("event_E1", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_E1", hashMap);
                } else if (j == 614782) {
                    hashMap.put(s.ORIGIN, "2");
                    i.a("event_E1", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_E1", hashMap);
                } else if (j == 500680) {
                    hashMap.put(s.ORIGIN, "3");
                    i.a("event_E1", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_E1", hashMap);
                } else if (j == 612464) {
                    hashMap.put(s.ORIGIN, "4");
                    i.a("event_E1", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_E1", hashMap);
                }
            }
            imageView = (ImageView) findViewById(R.id.profile_header_right_image);
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.titlebar_icon_newbookcomment_selector);
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.t.getVisibility() != 0) {
                        this.a.d(this.a.r);
                    }
                }
            });
        } else if ("bookclubreply".equals(string) || "selected_comment".equals(string)) {
            if ("bookclubreply".equals(string)) {
                hashMap = new HashMap();
                j = this.r.getLong("URL_BUILD_PERE_BOOK_ID", 0);
                if (j == 570698) {
                    hashMap.put(s.ORIGIN, "1");
                    i.a("event_E3", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_E3", hashMap);
                } else if (j == 614782) {
                    hashMap.put(s.ORIGIN, "2");
                    i.a("event_E3", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_E3", hashMap);
                } else if (j == 500680) {
                    hashMap.put(s.ORIGIN, "3");
                    i.a("event_E3", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_E3", hashMap);
                } else if (j == 612464) {
                    hashMap.put(s.ORIGIN, "4");
                    i.a("event_E3", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_E3", hashMap);
                }
            }
            imageView = (ImageView) findViewById(R.id.profile_header_right_collect);
            if (this.r.getInt("CTYPE") == 9) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.titlebar_icon_share);
                imageView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.m != null && (this.a.m instanceof ao)) {
                            String str;
                            ao aoVar = (ao) this.a.m;
                            String g = com.qq.reader.common.utils.ao.g(aoVar.e);
                            int x = aoVar.x();
                            CharSequence charSequence = this.a.getString(R.string.comment_share_title_prefix) + aoVar.t;
                            if (x == 4) {
                                charSequence = this.a.getString(R.string.comment_share_title_prefix) + com.qq.reader.common.utils.ao.k(aoVar.e);
                                g = com.qq.reader.common.utils.ao.l(aoVar.e);
                            }
                            if (TextUtils.isEmpty(charSequence)) {
                                str = "分享书评";
                            } else {
                                CharSequence charSequence2 = charSequence;
                            }
                            String c = com.qq.reader.common.emotion.b.c(com.qq.reader.common.utils.ao.a(aoVar.s));
                            i.a("event_B137", null, ReaderApplication.getApplicationImp());
                            if (!TextUtils.isEmpty(c) && c.length() > 300) {
                                c = c.substring(0, 300);
                            }
                            new aj(this.a, com.qq.reader.appconfig.e.n + "comment.html?mid=" + aoVar.e + "&ctype=" + aoVar.x() + "&tf=1&cid=" + aoVar.v.a(), g, str, c, null).f_();
                        }
                    }
                });
            }
            ImageView imageView2 = (ImageView) findViewById(R.id.profile_header_right_image);
            String string2 = this.r.getString("PARA_TYPE_COMMENT_UID");
            if (this.m instanceof ao) {
                string2 = ((ao) this.m).u;
            }
            com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
            Object obj = null;
            if (com.qq.reader.common.login.c.b()) {
                obj = c.c();
            }
            imageView2.setVisibility(0);
            imageView2.setImageResource(R.drawable.titlebar_icon_more_selector);
            if (TextUtils.isEmpty(obj)) {
                x();
            } else if (this.ae != 0) {
                imageView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.E != null) {
                            this.a.E.i();
                            this.a.E = null;
                        }
                        this.a.E = new com.qq.reader.view.linearmenu.b(this.a);
                        if (this.a.ah == 1) {
                            this.a.E.a(f.INSUFFICIENT_MEMORY, "取消置顶", null);
                        } else if (this.a.af == 1) {
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("setrightview", true);
                            bundle.putInt("resourceid", R.layout.comment_detail_top_right);
                            this.a.E.a(f.SUCCESS, "置顶", bundle);
                        } else {
                            this.a.E.a(f.SUCCESS, "置顶", null);
                        }
                        if (this.a.ag == 1) {
                            this.a.E.a(f.LOSS_OF_SERVICE, "取消精华书评", null);
                        } else {
                            this.a.E.a(f.USER_CANCELLED, "设为精华书评", null);
                        }
                        if (this.a.ai == 9) {
                            this.a.E.a(905, "解锁", null);
                        } else if (this.a.ai == 6) {
                            this.a.E.a(904, "锁贴", null);
                        }
                        this.a.E.a(f.INVALID_DESCRIPTOR, "删除", null);
                        if (this.a.aj == 1) {
                            this.a.E.a(908, "解禁", null);
                        } else {
                            this.a.E.a(907, "禁言7天", null);
                        }
                        this.a.E.f_();
                        this.a.E.a(new com.qq.reader.view.linearmenu.a.b(this) {
                            final /* synthetic */ AnonymousClass23 a;

                            {
                                this.a = r1;
                            }

                            public boolean a(int i, Bundle bundle) {
                                switch (i) {
                                    case f.SUCCESS /*900*/:
                                        this.a.a.a(true);
                                        break;
                                    case f.INSUFFICIENT_MEMORY /*901*/:
                                        this.a.a.a(false);
                                        break;
                                    case f.USER_CANCELLED /*902*/:
                                        this.a.a.b(true);
                                        break;
                                    case f.LOSS_OF_SERVICE /*903*/:
                                        this.a.a.b(false);
                                        break;
                                    case 904:
                                        this.a.a.c(true);
                                        break;
                                    case 905:
                                        this.a.a.c(false);
                                        break;
                                    case f.INVALID_DESCRIPTOR /*906*/:
                                        this.a.a.z();
                                        break;
                                    case 907:
                                        this.a.a.d(true);
                                        break;
                                    case 908:
                                        this.a.a.d(false);
                                        break;
                                }
                                return false;
                            }
                        });
                    }
                });
            } else if (obj.equalsIgnoreCase(string2)) {
                imageView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.t.getVisibility() != 0) {
                            this.a.y();
                        }
                    }
                });
            } else {
                x();
            }
            List m = this.m.m();
            if (m.size() == 1 && (m.get(0) instanceof MyFavorEmptyCard)) {
                imageView2.setVisibility(8);
                imageView.setVisibility(8);
            }
        } else if ("EndPage".equals(string)) {
            imageView = (ImageView) findViewById(R.id.profile_header_right_image);
            imageView.setVisibility(0);
            imageView.setBackgroundResource(R.drawable.titlebar_icon_blue_change2feed);
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.setResult(1001);
                    this.a.finish();
                }
            });
        } else if (!"classify".equals(string)) {
        } else {
            if (com.qq.reader.common.c.b.f == 0) {
                this.d.setVisibility(8);
                return;
            }
            imageView = (ImageView) findViewById(R.id.profile_header_right_image);
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.titlebar_icon_blue_change2feed);
            this.d.setVisibility(8);
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Intent intent = new Intent();
                    if (this.a.m != null && (this.a.m instanceof ag)) {
                        JSONObject x = ((ag) this.a.m).x();
                        if (x != null) {
                            intent.putExtra("classify_list", x.toString());
                        }
                    }
                    intent.setClass(this.a, ClassifySelectActivity.class);
                    this.a.startActivityForResult(intent, 888);
                }
            });
        }
    }

    private void x() {
        ImageView imageView = (ImageView) findViewById(R.id.profile_header_right_image);
        imageView.setVisibility(0);
        imageView.setImageResource(R.drawable.titlebar_icon_more_selector);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                final com.qq.reader.view.linearmenu.a cVar = new com.qq.reader.view.linearmenu.c(this.a);
                cVar.a(new OnCancelListener(this) {
                    final /* synthetic */ AnonymousClass27 a;

                    {
                        this.a = r1;
                    }

                    public void onCancel(DialogInterface dialogInterface) {
                        this.a.a.getWindow().closeAllPanels();
                    }
                });
                cVar.i();
                cVar.a(2, "广告及垃圾信息", null);
                cVar.a(5, "灌水", null);
                cVar.a(3, "反动", null);
                cVar.a(new com.qq.reader.view.linearmenu.a.b(this) {
                    final /* synthetic */ AnonymousClass27 b;

                    public boolean a(int i, Bundle bundle) {
                        if (this.b.a.m != null && (this.b.a.m instanceof ao)) {
                            ao aoVar = (ao) this.b.a.m;
                            cVar.cancel();
                            final Bundle bundle2 = new Bundle();
                            bundle2.putInt("COMMENT_REPORT_CTYPE", aoVar.x());
                            bundle2.putString("COMMENT_REPORT_BID", String.valueOf(aoVar.e));
                            bundle2.putString("COMMENT_REPORT_COMMENTID", aoVar.v.a());
                            bundle2.putString("COMMENT_REPORT_REPLYID", "0");
                            bundle2.putInt("COMMENT_REPORT_REPORTTYPE", i);
                            bundle2.putString("COMMENT_REPORT_DESC", cVar.b(i));
                            ReaderTask b = this.b.a.b(bundle2);
                            if (com.qq.reader.common.login.c.b()) {
                                g.a().a(b);
                            } else {
                                this.b.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                                    final /* synthetic */ AnonymousClass2 b;

                                    public void a(int i) {
                                        switch (i) {
                                            case 1:
                                                g.a().a(this.b.b.a.b(bundle2));
                                                return;
                                            default:
                                                return;
                                        }
                                    }
                                };
                                this.b.a.startLogin();
                            }
                        }
                        return false;
                    }
                });
                cVar.f_();
            }
        });
    }

    public IllegalCommentReportTask b(Bundle bundle) {
        return new IllegalCommentReportTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    Message obtainMessage = this.a.mHandler.obtainMessage(6000016);
                    obtainMessage.obj = new JSONObject(str).optString(SocialConstants.PARAM_SEND_MSG);
                    this.a.mHandler.sendMessage(obtainMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                exception.printStackTrace();
            }
        }, bundle);
    }

    public boolean k() {
        Fragment d = d();
        if (d == null || !(d instanceof NativePageFragmentforOther)) {
            return false;
        }
        return ((NativePageFragmentforOther) d).isLoading();
    }

    public void finish() {
        if (d() != null) {
            ((BaseFragment) d()).onActivityFinish();
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager == null || getCurrentFocus() == null) {
            super.finish();
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        this.mHandler.sendEmptyMessageDelayed(500009, 200);
    }

    private void d(final Bundle bundle) {
        if (com.qq.reader.common.login.c.b()) {
            e(bundle);
            return;
        }
        setLoginNextTask(new com.qq.reader.common.login.a(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity b;

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.b.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.e(bundle);
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

    private void y() {
        if (com.qq.reader.common.login.c.b()) {
            l();
            return;
        }
        setLoginNextTask(new com.qq.reader.common.login.a(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.l();
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

    private void e(Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, CommitCommentActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1002);
    }

    private void z() {
        String str;
        this.r.getString("PARA_TYPE_COMMENT_UID");
        if (this.m instanceof ao) {
            str = ((ao) this.m).u;
        }
        com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
        str = null;
        if (com.qq.reader.common.login.c.b()) {
            str = c.c();
        }
        if (str != null) {
            showFragmentDialog(703);
        }
    }

    public void l() {
        if (d() instanceof NativePageFragmentOfClub) {
            com.qq.reader.view.linearmenu.b moreMenu = ((NativePageFragmentOfClub) d()).getMoreMenu(null);
            moreMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
                final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                {
                    this.a = r1;
                }

                public boolean a(int i, Bundle bundle) {
                    switch (i) {
                        case 0:
                            String string = this.a.r.getString("PARA_TYPE_COMMENT_UID");
                            if (this.a.m instanceof ao) {
                                string = ((ao) this.a.m).u;
                            }
                            com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
                            String str = null;
                            if (com.qq.reader.common.login.c.b()) {
                                str = c.c();
                            }
                            if (str != null) {
                                if (str.equalsIgnoreCase(string)) {
                                    this.a.showFragmentDialog(703);
                                } else {
                                    af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.del_comment_fail_msg_notauthor), 0).a();
                                }
                            }
                            return true;
                        default:
                            return false;
                    }
                }
            });
            moreMenu.f_();
        }
    }

    private void A() {
        ReaderTask delCommentTask = new DelCommentTask(String.valueOf(this.r.getLong("URL_BUILD_PERE_BOOK_ID")), this.r.getString("COMMENT_ID"), this.r.getInt("CTYPE"));
        delCommentTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    switch (new JSONObject(str).optInt("code")) {
                        case -100:
                            return;
                        case 0:
                            this.a.mHandler.sendEmptyMessage(6000011);
                            return;
                        default:
                            this.a.mHandler.sendEmptyMessage(6000012);
                            return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(6000012);
            }
        });
        g.a().a(delCommentTask);
    }

    private void B() {
        this.C = findViewById(R.id.common_titler);
        ImageView imageView = (ImageView) findViewById(R.id.profile_header_left_back);
        imageView.setVisibility(0);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.C();
            }
        });
        this.l = this.r.getString("LOCAL_STORE_IN_TITLE");
        this.F = (ImageView) findViewById(R.id.iv_top_image);
        if (this.l != null && this.l.length() > 0) {
            this.h.setText(this.l);
        }
        this.p = (ImageView) findViewById(R.id.profile_header_title_sort);
        this.s = (Button) findViewById(R.id.profile_header_right_button);
        this.B = findViewById(R.id.default_progress);
        r();
    }

    private void C() {
        int i;
        if ("bookclubreply".equals(this.r.getString("KEY_JUMP_PAGENAME"))) {
            b bVar = null;
            if (d() != null && (d() instanceof NativePageFragment)) {
                bVar = ((NativePageFragment) d()).mHoldPage;
            }
            if (bVar != null && (bVar instanceof ao)) {
                String string = this.r.getString("COMMENT_ID");
                Intent intent = new Intent();
                intent.putExtra("operation_comment_action", "operation_comment_action_edit");
                intent.putExtra("operation_comment_id", string);
                ao aoVar = (ao) bVar;
                intent.putExtra("operation_comment_action_edit_agree", aoVar.d(string));
                intent.putExtra("operation_comment_action_edit_agreestatus", aoVar.e(string));
                intent.putExtra("operation_comment_action_edit_reply", aoVar.D());
                setResult(-1, intent);
                if (getCurrentFocus() != null) {
                    ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
                }
                i = 1;
                if (i == 0) {
                    setResult(0);
                }
                finish();
            }
        }
        i = 0;
        if (i == 0) {
            setResult(0);
        }
        finish();
    }

    protected void c(Bundle bundle) {
        if (bundle != null) {
            Bundle bundle2 = new Bundle(bundle);
            bundle2.remove("addcomment");
            this.m = com.qq.reader.module.bookstore.qnative.e.a().a(bundle2, this);
            com.qq.reader.common.monitor.debug.c.d("PAGE", "TwoLevel page = " + this.m.toString());
        }
        H();
    }

    protected void a(b bVar) {
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                boolean z;
                if (message.obj == null || !(message.obj instanceof b)) {
                    z = false;
                } else {
                    b bVar = (b) message.obj;
                    boolean b = this.m.b(bVar);
                    this.m.a(bVar);
                    z = b;
                }
                if (this.m != null && this.m.p() == 1002 && this.g.size() > 0 && !r0) {
                    if (d() != null && (d() instanceof NativePageFragment)) {
                        ((NativePageFragment) d()).refresh();
                        t();
                        if (this.m instanceof ao) {
                            this.ae = ((ao) this.m).S;
                            this.af = ((ao) this.m).T;
                            this.ah = ((ao) this.m).V;
                            this.ag = ((ao) this.m).U;
                            this.ai = ((ao) this.m).W;
                            this.aj = ((ao) this.m).X;
                            this.ak = ((ao) this.m).Y;
                            j();
                            break;
                        }
                    }
                } else if (!isFinishing()) {
                    t();
                    a(this.m);
                    m();
                    if (this.m instanceof ao) {
                        this.ae = ((ao) this.m).S;
                        this.af = ((ao) this.m).T;
                        this.ah = ((ao) this.m).V;
                        this.ag = ((ao) this.m).U;
                        this.ai = ((ao) this.m).W;
                        this.aj = ((ao) this.m).X;
                        this.ak = ((ao) this.m).Y;
                    }
                    j();
                    break;
                }
                break;
            case 500004:
                u();
                break;
            case 500006:
                CharSequence charSequence;
                if (this.z) {
                    charSequence = "已取消关注";
                } else {
                    charSequence = "关注成功";
                }
                af.a(this.k, charSequence, 0).a();
                break;
            case 500009:
                super.finish();
                break;
            case 6000011:
                if (E()) {
                    af.a(ReaderApplication.getApplicationImp(), (CharSequence) "书评已删除", 0).a();
                    String string = this.r.getString("COMMENT_ID");
                    Intent intent = new Intent();
                    intent.putExtra("operation_comment_action", "operation_comment_action_del");
                    intent.putExtra("operation_comment_id", string);
                    intent.putExtra("operation_commentcount_action", 2);
                    setResult(-1, intent);
                    finish();
                    break;
                }
                break;
            case 6000012:
                if (E()) {
                    af.a(ReaderApplication.getApplicationImp(), (CharSequence) "评论删除失败", 0).a();
                    break;
                }
                break;
            case 6000013:
                D();
                A();
                break;
            case 6000014:
                if (!isFinishing()) {
                    af.a(getApplicationContext(), ReaderApplication.getApplicationImp().getResources().getString(R.string.comment_send_success), 0).a();
                    JSONObject jSONObject = (JSONObject) message.obj;
                    NativePageFragment nativePageFragment = (NativePageFragment) d();
                    if (nativePageFragment != null && (nativePageFragment.mHoldPage instanceof v)) {
                        ((v) nativePageFragment.mHoldPage).d(jSONObject);
                        nativePageFragment.refresh();
                        break;
                    }
                }
                break;
            case 6000015:
                if (!isFinishing()) {
                    NativePageFragment nativePageFragment2 = (NativePageFragment) d();
                    if (nativePageFragment2 != null && (nativePageFragment2.mHoldPage instanceof v)) {
                        if (message.obj != null && (message.obj instanceof String)) {
                            ((v) nativePageFragment2.mHoldPage).d((String) message.obj);
                        }
                        nativePageFragment2.refresh();
                        break;
                    }
                }
                break;
            case 6000016:
                af.a(getApplicationContext(), (String) message.obj, 0).a();
                q();
                break;
            case 12345001:
                a("已置顶");
                this.ah = 1;
                break;
            case 12345002:
                a("已取消置顶");
                this.ah = 0;
                break;
            case 12345003:
                a("已设为精华书评");
                if (d() != null && (d() instanceof NativePageFragmentOfReply)) {
                    ((NativePageFragmentOfClub) d()).onUpdate();
                }
                this.ag = 1;
                break;
            case 12345004:
                a("已取消精华书评");
                if (d() != null && (d() instanceof NativePageFragmentOfReply)) {
                    ((NativePageFragmentOfClub) d()).onUpdate();
                }
                this.ag = 0;
                break;
            case 12345005:
                a("已锁帖");
                this.ai = 9;
                break;
            case 12345006:
                a("已解锁");
                this.ai = 6;
                break;
            case 12345008:
                a("已禁言");
                this.aj = 1;
                break;
            case 12345009:
                a("已解禁");
                this.aj = 0;
                break;
            case 12345010:
                a("网络异常，请稍后重试");
                break;
            case 12345011:
                a("出错啦，请稍后重试");
                break;
        }
        return true;
    }

    protected void m() {
        if (this.m != null) {
            Class c = this.m.c();
            this.y = this.m.k();
            this.g.clear();
            if (this.o != null) {
                this.o.g();
            }
            if (this.y != null) {
                if (!TextUtils.isEmpty(this.y.i())) {
                    this.h.setText(this.y.i());
                } else if (this.y.h().length() > 0) {
                    this.h.setText(this.y.h());
                }
                this.w = this.y.f();
            }
            HashMap hashMap;
            if (this.w == null || this.w.size() <= 0) {
                hashMap = new HashMap();
                hashMap.put("LOCAL_STORE_HOLD_PAGE", this.m);
                hashMap.put("key_data", this.r);
                this.g.add(new TabInfo(c, "", "", hashMap));
                c();
            } else {
                Bundle bundle;
                int i;
                d.b bVar;
                List g = this.y.g();
                this.x = this.y.d();
                if (NativePageFragmentforClassify.class == c) {
                    hashMap = new HashMap();
                    bundle = new Bundle(this.r);
                    hashMap.put("LOCAL_STORE_HOLD_PAGE", this.m);
                    hashMap.put("key_data", bundle);
                    this.g.add(0, new TabInfo(c, this.r.getString("KEY_ACTIONTAG"), this.r.getString("LOCAL_STORE_IN_TITLE"), hashMap));
                    this.w.clear();
                } else {
                    for (i = 0; i < g.size(); i++) {
                        HashMap hashMap2 = new HashMap();
                        bVar = (d.b) g.get(i);
                        Bundle bundle2 = new Bundle(this.r);
                        if (bVar.c) {
                            hashMap2.put("LOCAL_STORE_HOLD_PAGE", this.m);
                        } else {
                            bundle2.putString("KEY_ACTIONTAG", bVar.b);
                            bundle2.putString("KEY_ACTIONID", this.x);
                        }
                        hashMap2.put("key_data", bundle2);
                        this.g.add(i, new TabInfo(c, bVar.b, bVar.a, hashMap2));
                    }
                    if (g.size() == 0) {
                        hashMap = new HashMap();
                        hashMap.put("LOCAL_STORE_HOLD_PAGE", this.m);
                        hashMap.put("key_data", this.r);
                        this.g.add(new TabInfo(c, "", "", hashMap));
                    }
                }
                if (this.w.size() > 1) {
                    this.p.setVisibility(0);
                    p();
                    for (i = 0; i < this.w.size(); i++) {
                        this.o.a(i, ((d.a) this.w.get(i)).a, null);
                    }
                    this.q = this.y.j();
                    this.o.b(this.q);
                    this.o.a((n.a) this);
                    this.p.setOnClickListener(this.n);
                    this.h.setOnClickListener(this.n);
                } else {
                    this.h.setClickable(false);
                    this.p.setVisibility(8);
                }
                if (g.size() <= 1 || this.g.size() <= 1) {
                    this.e.setVisibility(8);
                    this.d.setVisibility(8);
                } else {
                    this.e.setVisibility(0);
                    this.d.setVisibility(0);
                    this.a.setIndicatorColorResource(R.color.skin_set_common_textcolor);
                    i = this.g.size();
                    int i2 = com.qq.reader.common.c.a.bU / i;
                    int i3 = i2 / 8;
                    if (i == 2 || i == 3) {
                        i3 = (i2 - getResources().getDimensionPixelOffset(R.dimen.common_dp_80)) / 2;
                    }
                    this.a.setLineRightAndLeftPadding(i3, i3);
                }
                c();
                i = 0;
                while (i < g.size()) {
                    bVar = (d.b) g.get(i);
                    if (bVar.c) {
                        this.b.setCurrentItem(i);
                        if (i == 0) {
                            HashMap hashMap3 = ((TabInfo) this.g.get(0)).args;
                            if (hashMap3 != null) {
                                bundle = (Bundle) hashMap3.get("key_data");
                                if (TextUtils.isEmpty(bundle.getString("KEY_ACTIONTAG"))) {
                                    bundle.putString("KEY_ACTIONTAG", bVar.a);
                                }
                                StatisticsManager.a().a(1).a(bundle).a(true);
                            }
                        }
                    } else {
                        i++;
                    }
                }
            }
        }
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        this.d.setLayoutParams(layoutParams);
        this.a.setIndicatorBottomPadding(0);
    }

    protected void a(Bundle bundle) {
        this.a.setIndicatorColorResource(R.color.textcolor_white);
        this.a.setIndicatorHeight(getResources().getDimensionPixelOffset(R.dimen.common_dp_2));
        this.d.setVisibility(8);
        this.a.setOnPageChangeListener(this);
        this.b.setCurrentItem(0);
        this.k = getApplicationContext();
        ((ImageView) findViewById(R.id.profile_header_left_back)).setVisibility(8);
        this.l = getIntent().getExtras().getString("LOCAL_STORE_IN_TITLE");
        if (this.l != null && this.l.length() > 0) {
            this.h.setText(this.l);
        }
    }

    protected String e() {
        return this.x != null ? this.x : "";
    }

    public View n() {
        return this.C;
    }

    public ImageView o() {
        return this.F;
    }

    protected int f() {
        return R.layout.localbookstore_viewpager_layout;
    }

    private void D() {
        try {
            if (this.D == null) {
                this.D = new c(this);
                this.D.a(getResources().getString(R.string.loading_tips));
                this.D.c(true);
            }
            if (!this.D.f()) {
                this.D.f_();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean E() {
        try {
            if (this.D != null && this.D.f()) {
                this.D.cancel();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        Dialog dialog = null;
        switch (i) {
            case 703:
                dialog = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_del).b((int) R.string.bookclub_comment_delete_msg).a((int) R.string.bookclub_reply_delete, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.mHandler.sendEmptyMessage(6000013);
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
        }
        return dialog != null ? dialog : super.createDialog(i, bundle);
    }

    protected void p() {
        if (this.o == null) {
            this.o = new n(this, R.layout.webpage_popup_menu);
            this.o.a(new OnCancelListener(this) {
                final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.p.setImageResource(R.drawable.bookstore_title_arrow_white);
                    if (this.a.am != null) {
                        ((ViewGroup) this.a.getWindow().getDecorView()).removeView(this.a.am);
                    }
                }
            });
            F();
        }
        this.o.b(this.q);
        this.p.setVisibility(0);
        this.p.setImageResource(R.drawable.bookstore_title_arrow_white);
        this.n = new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.G();
            }
        };
    }

    @TargetApi(8)
    private void F() {
        if (this.o != null) {
            this.o.a(new OnShowListener(this) {
                final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    if (!com.qq.reader.appconfig.a.d.n) {
                        if (this.a.am == null) {
                            this.a.am = new GuideShadowView(this.a);
                        }
                        this.a.am.setHighLightRect(this.a.w());
                        ((ViewGroup) this.a.getWindow().getDecorView()).addView(this.a.am);
                    }
                }
            });
        }
    }

    private void G() {
        if (this.o.f()) {
            this.p.setImageResource(R.drawable.bookstore_title_arrow_white);
            this.o.cancel();
            return;
        }
        this.p.setImageResource(R.drawable.bookstore_title_arrow_up_white);
        this.o.c().a((int) R.id.readpage_topbar_popup);
        this.o.a(true);
    }

    public boolean b(int i, Bundle bundle) {
        this.q = i;
        this.o.b(this.q);
        this.b.setCurrentItem(0);
        d.a aVar = (d.a) this.w.get(i);
        this.x = aVar.c;
        this.r.putString("KEY_ACTIONTAG", aVar.a());
        this.r.putString("KEY_ACTIONID", this.x);
        this.g.clear();
        c();
        this.m.a(1001);
        c(this.r);
        StatisticsManager.a().a(1).a(this.r).c();
        if ("rank".equals(this.r.getString("KEY_ACTION"))) {
            if (i == 0) {
                i.a("event_C140", null, ReaderApplication.getApplicationImp());
            } else if (i == 1) {
                i.a("event_C141", null, ReaderApplication.getApplicationImp());
            } else if (i == 2) {
                i.a("event_C142", null, ReaderApplication.getApplicationImp());
            }
        }
        if ("WellChosenBookStore".equalsIgnoreCase(this.r.getString("KEY_JUMP_PAGENAME"))) {
            i.a("event_C149", null, this.k);
        }
        return false;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        HashMap hashMap = ((TabInfo) this.g.get(i)).args;
        if (hashMap != null) {
            StatisticsManager.a().a(1).a((Bundle) hashMap.get("key_data")).c();
        }
    }

    public void doFunction(Bundle bundle) {
        NativePageFragment nativePageFragment;
        if (this.m instanceof com.qq.reader.module.question.a.d) {
            nativePageFragment = (NativePageFragment) d();
            if (nativePageFragment != null && nativePageFragment.isAdded()) {
                nativePageFragment.doFunction(bundle);
                return;
            }
            return;
        }
        if ("go_h5_game".equals(bundle.getString("function_type"))) {
            setLoginNextTask(com.qq.reader.module.game.a.a(bundle, (Activity) this));
            startLogin();
            return;
        }
        int i = bundle.getInt("function_type");
        final boolean z = bundle.getBoolean("need_reload");
        if (i == 3) {
            startLogin();
            setLoginNextTask(new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeBookStoreTwoLevelActivity b;

                public void a(int i) {
                    if (this.b.d() == null) {
                        return;
                    }
                    if (this.b.d() instanceof NativePageFragmentOfReply) {
                        ((NativePageFragmentOfClub) this.b.d()).onUpdate();
                        return;
                    }
                    if (z && (this.b.d() instanceof NativePageFragmentforOther)) {
                        b bVar = ((NativePageFragmentforOther) this.b.d()).mHoldPage;
                        if (!(bVar == null || bVar.j() == null)) {
                            bVar.j().putBoolean("need_reload", true);
                        }
                    }
                    ((NativePageFragment) this.b.d()).refresh();
                }
            });
        }
        if ("detail_2_openvip".equals(bundle.getString("KEY_ACTION"))) {
            new JSPay(this).openVip();
            StatisticsManager.a().a(this.r).a(6).c();
        } else if (i == 5) {
            if (bundle != null && bundle.getString("PARA_TYPE_TOPIC_CONTENT") != null) {
                d(new Bundle(bundle));
            }
        } else if (i == 1) {
            String string = bundle.getString("KEY_CARD_ID");
            if (string != null && d() != null) {
                b bVar = ((NativePageFragment) d()).mHoldPage;
                if (bVar != null) {
                    List<com.qq.reader.module.bookstore.qnative.card.a> m = bVar.m();
                    if (m != null && m.size() >= 1) {
                        for (com.qq.reader.module.bookstore.qnative.card.a cardId : m) {
                            if (string.equals(cardId.getCardId())) {
                                ((NativePageFragment) d()).refresh();
                                return;
                            }
                        }
                    }
                }
            }
        } else {
            nativePageFragment = (NativePageFragment) d();
            if (nativePageFragment != null) {
                nativePageFragment.doFunction(bundle);
            }
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public boolean b() {
        return true;
    }

    public View a(int i) {
        TabInfo tabInfo = (TabInfo) this.g.get(i);
        View inflate = getLayoutInflater().inflate(R.layout.profileaccount_tab_item, null);
        ((TextView) inflate.findViewById(R.id.tab_text)).setText(tabInfo.title);
        if (this.j.size() > i) {
            this.j.set(i, inflate);
        } else {
            while (this.j.size() <= i) {
                this.j.add(null);
            }
            this.j.set(i, inflate);
        }
        return inflate;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 82) {
            return true;
        }
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        Fragment d = d();
        if ((d instanceof BaseFragment) && ((BaseFragment) d).onBackPress()) {
            return true;
        }
        C();
        return true;
    }

    public void q() {
        this.m.a(1000);
        H();
    }

    private void H() {
        boolean z = true;
        if (this.r != null) {
            z = this.r.getBoolean("GO_TWOLEVEL_ACT_USE_CACHE", true);
        }
        if (com.qq.reader.module.bookstore.qnative.d.b().a(this.k, this.m, this.mHandler, z)) {
            m();
            t();
            return;
        }
        s();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.o != null) {
            this.o.a(null);
        }
        com.qq.reader.module.bookstore.qnative.d.b().a(this.m);
        if (this.m != null) {
            this.m.w();
        }
    }

    protected void r() {
        this.v = findViewById(R.id.content_layout);
        this.t = findViewById(R.id.loading_layout);
        this.u = findViewById(R.id.loading_failed_layout);
        this.u.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.q();
            }
        });
    }

    protected void s() {
        v();
        this.v.setVisibility(8);
        this.t.setVisibility(0);
    }

    protected void t() {
        v();
        this.v.setVisibility(0);
        this.t.setVisibility(8);
    }

    protected void u() {
        if (this.v.getVisibility() != 0) {
            this.t.setVisibility(8);
            this.u.setVisibility(0);
        }
    }

    protected void v() {
        this.u.setVisibility(8);
    }

    public k w() {
        if (this.an == null) {
            View view = this.C;
            r1 = new int[4];
            view.getLocationOnScreen(r1);
            r1[2] = r1[0] + view.getWidth();
            r1[3] = view.getHeight() + r1[1];
            this.an = new k();
            this.an.a = new Rect(r1[0], r1[1], r1[2], r1[3]);
            this.an.b = 1;
        }
        return this.an;
    }

    public boolean h() {
        return true;
    }

    public String i() {
        String string = this.r.getString("KEY_JUMP_PAGENAME");
        if (string == null || string.length() == 0) {
            com.qq.reader.module.bookstore.qnative.c.a(this.r.getString("KEY_ACTION"));
        }
        return string;
    }

    private void a(CharSequence charSequence, String str) {
        if (d() != null && (d() instanceof NativePageFragment)) {
            NativePageFragment nativePageFragment = (NativePageFragment) d();
            if (nativePageFragment != null) {
                long j = this.r.getLong("URL_BUILD_PERE_BOOK_ID", 0);
                if (nativePageFragment.mHoldPage instanceof v) {
                    ((v) nativePageFragment.mHoldPage).a(str, charSequence.toString(), String.valueOf(j));
                    nativePageFragment.notifyData();
                } else if (nativePageFragment instanceof NativePageFragmentOfClub) {
                    ((NativePageFragmentOfClub) nativePageFragment).setFailedFakeComment(charSequence, str, String.valueOf(j));
                }
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 888:
                if (i2 == -1) {
                    Object stringExtra = intent.getStringExtra("classify_list");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        try {
                            JSONObject jSONObject = new JSONObject(stringExtra);
                            if (jSONObject != null) {
                                NativePageFragmentforClassify_1 nativePageFragmentforClassify_1 = (NativePageFragmentforClassify_1) d();
                                if (nativePageFragmentforClassify_1 != null) {
                                    nativePageFragmentforClassify_1.setTitleInfo(jSONObject);
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                }
                return;
            case 1002:
                if (i2 == -1) {
                    String string = this.r.getString("PARA_TYPE_BOOK_NAME");
                    if (!intent.getBooleanExtra("DELETE_COMMENT", false)) {
                        this.ao = new com.qq.reader.common.emotion.a(this, this.mHandler, string) {
                            final /* synthetic */ NativeBookStoreTwoLevelActivity a;

                            public void a(String str, String str2) {
                                this.a.a((CharSequence) str, str2);
                            }
                        };
                        this.ao.a(intent);
                    }
                    if (d() != null && (d() instanceof NativePageFragment)) {
                        NativePageFragment nativePageFragment = (NativePageFragment) d();
                        if (nativePageFragment != null && (nativePageFragment.mHoldPage instanceof v)) {
                            nativePageFragment.refresh();
                        }
                    }
                }
                if (intent != null && intent.getBooleanExtra("SHOWCOMMENTACTIVITY", false) && intent.getBooleanExtra("HIDECOMMENTACTIVITYIMMEDIATELY", true)) {
                    setResult(30, intent);
                    finish();
                    return;
                }
                return;
            case 1004:
                ((NativePageFragment) d()).onActivityResult(i, i2, intent);
                return;
            default:
                return;
        }
    }

    private void a(final boolean z) {
        g.a().a(new CommentDetailSetTopTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Message obtain = Message.obtain();
                if (!this.b.b(str)) {
                    obtain.what = 12345011;
                } else if (z) {
                    obtain.what = 12345001;
                } else {
                    obtain.what = 12345002;
                }
                this.b.mHandler.sendMessage(obtain);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Message obtain = Message.obtain();
                obtain.what = 12345010;
                this.b.mHandler.sendMessage(obtain);
            }
        }, this.r.getString("COMMENT_ID"), this.r.getLong("URL_BUILD_PERE_BOOK_ID"), this.r.getInt("CTYPE"), z));
    }

    private void b(final boolean z) {
        g.a().a(new CommentDetailSetBestCommentTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Message obtain = Message.obtain();
                if (!this.b.b(str)) {
                    obtain.what = 12345011;
                } else if (z) {
                    obtain.what = 12345003;
                } else {
                    obtain.what = 12345004;
                }
                this.b.mHandler.sendMessage(obtain);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Message obtain = Message.obtain();
                obtain.what = 12345010;
                this.b.mHandler.sendMessage(obtain);
            }
        }, this.r.getString("COMMENT_ID"), this.r.getLong("URL_BUILD_PERE_BOOK_ID"), this.r.getInt("CTYPE"), z));
    }

    private void c(final boolean z) {
        g.a().a(new CommentDetailSetLockPostTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Message obtain = Message.obtain();
                if (!this.b.b(str)) {
                    obtain.what = 12345011;
                } else if (z) {
                    obtain.what = 12345005;
                } else {
                    obtain.what = 12345006;
                }
                this.b.mHandler.sendMessage(obtain);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Message obtain = Message.obtain();
                obtain.what = 12345010;
                this.b.mHandler.sendMessage(obtain);
            }
        }, this.r.getString("COMMENT_ID"), this.r.getLong("URL_BUILD_PERE_BOOK_ID"), this.r.getInt("CTYPE"), z));
    }

    private void d(final boolean z) {
        g.a().a(new CommentDetailSetBanCommentTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeBookStoreTwoLevelActivity b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Message obtain = Message.obtain();
                if (!this.b.b(str)) {
                    obtain.what = 12345011;
                } else if (z) {
                    obtain.what = 12345008;
                } else {
                    obtain.what = 12345009;
                }
                this.b.mHandler.sendMessage(obtain);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Message obtain = Message.obtain();
                obtain.what = 12345010;
                this.b.mHandler.sendMessage(obtain);
            }
        }, this.ak, this.r.getLong("URL_BUILD_PERE_BOOK_ID"), z));
    }

    private void a(String str) {
        af.a(getApplicationContext(), (CharSequence) str, 0).a();
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("code") && jSONObject.optInt("code") == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
