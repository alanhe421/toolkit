package com.qq.reader.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.GetBookUserScoreTask;
import com.qq.reader.common.readertask.protocol.UploadBookUserScoreTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.utils.t;
import com.qq.reader.common.widget.ReaderRatingBar;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.readengine.model.IBook;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.sdk.WebView;
import com.tencent.util.WeakReferenceHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReaderEndPager implements Callback, OnClickListener, com.qq.reader.common.widget.ReaderRatingBar.a {
    private int[] A = new int[]{R.id.tv_book_name_1, R.id.tv_book_name_2, R.id.tv_book_name_3};
    private int[] B = new int[]{R.id.tv_author_1, R.id.tv_author_2, R.id.tv_author_3};
    private int[] C = new int[]{R.id.ll_book_1, R.id.ll_book_2, R.id.ll_book_3};
    private int D = 0;
    private int E;
    private String F;
    private IBook G;
    private long H = 0;
    private at I;
    private String J;
    private String K;
    private int L = 16;
    private int M = 11;
    private boolean N;
    b a = null;
    t b;
    int[] c = new int[]{R.id.book_1, R.id.book_2, R.id.book_3};
    long d;
    String e;
    String f;
    int g;
    boolean h;
    int i;
    long j;
    long k;
    boolean l;
    boolean m;
    String n;
    private Activity o;
    private View p;
    private WeakReferenceHandler q;
    private WeakReferenceHandler r = new WeakReferenceHandler(Looper.getMainLooper(), this);
    private float s;
    private View t;
    private View u;
    private d v;
    private d w;
    private ArrayList<c> x = new ArrayList();
    private ArrayList<c> y = new ArrayList();
    private int[] z = new int[]{R.id.img_book_cover_1, R.id.img_book_cover_2, R.id.img_book_cover_3};

    private class EndPageNetTask extends ReaderProtocolJSONTask {
        public EndPageNetTask(com.qq.reader.common.readertask.ordinal.c cVar) {
            super(cVar);
            this.mUrl = e.h + "readover?bid=" + ReaderEndPager.this.d;
        }
    }

    private class a {
        public ArrayList<c> a;
        public ArrayList<c> b;
        final /* synthetic */ ReaderEndPager c;

        private a(ReaderEndPager readerEndPager) {
            this.c = readerEndPager;
            this.a = null;
            this.b = null;
        }
    }

    private class b {
        String a;
        String b;
        String c;
        String d;
        String e;
        final /* synthetic */ ReaderEndPager f;

        private b(ReaderEndPager readerEndPager) {
            this.f = readerEndPager;
        }
    }

    private class c {
        long a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        final /* synthetic */ ReaderEndPager i;

        private c(ReaderEndPager readerEndPager) {
            this.i = readerEndPager;
        }
    }

    private class d {
        TextView a;
        TextView b;
        View c;
        View d;
        View e;
        TextView f;
        TextView g;
        TextView h;
        TextView i;
        LinearLayout j;
        TextView k;
        View l;
        LinearLayout m;
        StrokeLinearLayout n;
        TextView o;
        View p;
        View q;
        TextView r;
        ReaderRatingBar s;
        TextView t;
        final /* synthetic */ ReaderEndPager u;

        private d(ReaderEndPager readerEndPager) {
            this.u = readerEndPager;
        }
    }

    public View a() {
        return this.p;
    }

    public ReaderEndPager(Activity activity) {
        this.o = activity;
        this.q = (WeakReferenceHandler) ((ReaderBaseActivity) activity).getHandler();
        this.p = LayoutInflater.from(this.o).inflate(R.layout.readerendpage, null);
        this.t = this.p.findViewById(R.id.endpage_layout_vertical);
        this.u = this.p.findViewById(R.id.endpage_layout_horizontal);
        this.w = new d();
        this.v = new d();
        a(this.t, this.v);
        a(this.u, this.w);
        if (this.o.getResources().getConfiguration().orientation == 1) {
            this.t.setVisibility(0);
            this.u.setVisibility(8);
        } else {
            this.t.setVisibility(8);
            this.u.setVisibility(0);
        }
        this.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReaderEndPager a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                ((ReaderPageActivity) this.a.o).openOptionsMenu();
            }
        });
        this.u.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReaderEndPager a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                ((ReaderPageActivity) this.a.o).openOptionsMenu();
            }
        });
        this.b = new t(this.o, true);
        this.b.a(R.id.readerendpage_root);
    }

    private void a(View view, d dVar) {
        dVar.a = (TextView) view.findViewById(R.id.tag1);
        dVar.b = (TextView) view.findViewById(R.id.tag2);
        dVar.c = view.findViewById(R.id.interact);
        dVar.d = view.findViewById(R.id.share_layout);
        dVar.e = view.findViewById(R.id.bookclub);
        dVar.f = (TextView) view.findViewById(R.id.button_reward);
        dVar.g = (TextView) view.findViewById(R.id.button_recommend);
        dVar.h = (TextView) view.findViewById(R.id.button_monthticket);
        dVar.i = (TextView) view.findViewById(R.id.change_books);
        dVar.j = (LinearLayout) view.findViewById(R.id.share_achievement_btn);
        dVar.k = (TextView) view.findViewById(R.id.author_change_books);
        dVar.l = view.findViewById(R.id.adview);
        dVar.m = (LinearLayout) view.findViewById(R.id.books_layout);
        dVar.n = (StrokeLinearLayout) view.findViewById(R.id.reward_layout);
        dVar.n.setRadius(this.o.getResources().getDimensionPixelSize(R.dimen.endpagebutton_radius));
        dVar.n.setStrokeWidth(1.0f);
        dVar.p = view.findViewById(R.id.divider_1);
        dVar.q = view.findViewById(R.id.divider_2);
        dVar.o = (TextView) view.findViewById(R.id.count);
        dVar.r = (TextView) view.findViewById(R.id.guessyoulike);
        dVar.c.setOnClickListener(this);
        dVar.e.setOnClickListener(this);
        dVar.f.setOnClickListener(this);
        dVar.g.setOnClickListener(this);
        dVar.h.setOnClickListener(this);
        dVar.i.setOnClickListener(this);
        dVar.j.setOnClickListener(this);
        dVar.s = (ReaderRatingBar) view.findViewById(R.id.endpage_ratingbar);
        dVar.s.setOnRatingBarDelayChangedListener(this);
        dVar.s.setRatingChangedDelaytime(1000);
        dVar.t = (TextView) view.findViewById(R.id.endpage_rating_text);
        dVar.s.setRatingText(dVar.t, view.getContext().getResources().getStringArray(R.array.endpage_rating_score_intro));
    }

    public void onClick(View view) {
        Message obtain;
        switch (view.getId()) {
            case R.id.right_arrow:
            case R.id.adview:
                a(new com.qq.reader.common.c.c.a(this) {
                    final /* synthetic */ ReaderEndPager a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        com.qq.reader.common.monitor.debug.c.a("endpage", "go to " + this.a.a.d);
                        try {
                            com.qq.reader.qurl.c.a(this.a.o, this.a.a.d);
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, this.a.a.e.equals("8") ? "0" : "1");
                            i.a("event_C257", hashMap, ReaderApplication.getApplicationImp());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            case R.id.interact:
                if (this.I == null) {
                    if (TextUtils.isEmpty(this.n) && this.G != null) {
                        this.n = this.G.getAuthorIcon();
                    }
                    this.I = new at(this.o, this.d, this.n);
                }
                if (!this.I.f()) {
                    at.b = "3";
                    if (this.m) {
                        as.E = "5";
                    } else {
                        as.E = "4";
                    }
                    this.I.f_();
                }
                i.a("event_B147", null, ReaderApplication.getApplicationImp());
                return;
            case R.id.bookclub:
                a(new com.qq.reader.common.c.c.a(this) {
                    final /* synthetic */ ReaderEndPager a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        com.qq.reader.appconfig.a.d.a(this.a.o, this.a.d, this.a.g);
                        this.a.v.o.setVisibility(8);
                        this.a.w.o.setVisibility(8);
                        com.qq.reader.common.monitor.debug.c.a("bookclub", "bookid  " + this.a.d);
                        i.a("event_C56", null, ReaderApplication.getApplicationImp());
                        i.a("event_B116", null, ReaderApplication.getApplicationImp());
                        o.a(this.a.o, Long.valueOf(this.a.d), null, 0, new JumpActivityParameter());
                    }
                });
                return;
            case R.id.button_reward:
                if (this.m) {
                    as.E = "5";
                } else {
                    as.E = "4";
                }
                i.a("event_B21", null, ReaderApplication.getApplicationImp());
                obtain = Message.obtain();
                obtain.what = 1231;
                obtain.arg1 = 1;
                obtain.arg2 = 1;
                this.q.sendMessage(obtain);
                return;
            case R.id.button_recommend:
                if (this.m) {
                    as.E = "5";
                } else {
                    as.E = "4";
                }
                i.a("event_B22", null, ReaderApplication.getApplicationImp());
                obtain = Message.obtain();
                obtain.what = 1231;
                obtain.arg1 = 2;
                obtain.arg2 = 1;
                this.q.sendMessage(obtain);
                return;
            case R.id.button_monthticket:
                if (this.m) {
                    as.E = "5";
                } else {
                    as.E = "4";
                }
                i.a("event_B23", null, ReaderApplication.getApplicationImp());
                obtain = Message.obtain();
                obtain.what = 1231;
                obtain.arg1 = 3;
                obtain.arg2 = 1;
                this.q.sendMessage(obtain);
                return;
            case R.id.share_achievement_btn:
                i.a("event_B145", null, ReaderApplication.getApplicationImp());
                String a = com.qq.reader.common.login.c.c().a();
                String str = e.ae + "bid=" + this.d + "&qqid=" + this.e;
                String format = String.format(this.o.getString(R.string.endpage_share_summary), new Object[]{this.J, this.K});
                new aj(this.o, str, ao.g(this.d), String.format(this.o.getString(R.string.endpage_share_title), new Object[]{a}), format, "").f_();
                return;
            case R.id.author_change_books:
                a(new com.qq.reader.common.c.c.a(this) {
                    final /* synthetic */ ReaderEndPager a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        Intent intent = new Intent(this.a.o, NativeBookStoreTwoLevelActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("KEY_ACTION", "sameauthorbooks");
                        bundle.putString("KEY_CARD_ID", "authorRec");
                        bundle.putInt("com.qq.reader.WebContent", 1);
                        bundle.putString("KEY_ACTIONID", String.valueOf(this.a.d));
                        bundle.putString("LOCAL_STORE_IN_TITLE", "全部作品");
                        bundle.putInt("function_type", 0);
                        intent.putExtras(bundle);
                        this.a.o.startActivity(intent);
                    }
                });
                return;
            case R.id.change_books:
                i.a("event_B117", null, ReaderApplication.getApplicationImp());
                f();
                return;
            default:
                return;
        }
    }

    private void a(com.qq.reader.common.c.c.a aVar) {
        if (((ReaderPageActivity) this.o).h()) {
            ((ReaderPageActivity) this.o).a(aVar);
        } else {
            aVar.a();
        }
    }

    public boolean handleMessage(Message message) {
        float f = 0.0f;
        switch (message.what) {
            case 0:
                if (message.obj instanceof Float) {
                    f = ((Float) message.obj).floatValue();
                } else if (message.obj instanceof Integer) {
                    f = (float) ((Integer) message.obj).intValue();
                }
                a(f);
                return true;
            case 10000501:
                if (!this.o.isFinishing()) {
                    if (message.obj instanceof Float) {
                        f = ((Float) message.obj).floatValue();
                    } else if (message.obj instanceof Integer) {
                        f = (float) ((Integer) message.obj).intValue();
                    }
                    this.v.s.setRating(f);
                    this.w.s.setRating(f);
                }
                return true;
            case 10000502:
                if (!this.o.isFinishing()) {
                    af.a(this.o, message.obj.toString(), 0).a();
                    this.v.s.setRating(this.s);
                    this.w.s.setRating(this.s);
                }
                return true;
            case 10000511:
                if (!(this.o == null || this.o.isFinishing())) {
                    a aVar = (a) message.obj;
                    Collection collection = aVar.b;
                    Collection collection2 = aVar.a;
                    if (collection != null) {
                        this.x.clear();
                        this.x.addAll(collection);
                    }
                    if (collection2 != null) {
                        this.y.clear();
                        this.y.addAll(collection2);
                    }
                    j();
                }
                return true;
            default:
                return false;
        }
    }

    public boolean a(RatingBar ratingBar, final float f) {
        if (com.qq.reader.common.login.c.b()) {
            return false;
        }
        ((ReaderBaseActivity) this.o).setLoginNextTask(new com.qq.reader.common.login.a(this) {
            final /* synthetic */ ReaderEndPager b;

            public void a(int i) {
                Message obtainMessage = this.b.r.obtainMessage();
                switch (i) {
                    case 1:
                        obtainMessage.what = 0;
                        obtainMessage.obj = Float.valueOf(f);
                        break;
                    case 2:
                    case 3:
                        obtainMessage.what = 10000501;
                        obtainMessage.obj = Float.valueOf(0.0f);
                        break;
                }
                this.b.r.sendMessage(obtainMessage);
            }
        });
        ((ReaderBaseActivity) this.o).startLogin();
        return true;
    }

    public void b(RatingBar ratingBar, float f) {
        com.qq.reader.common.monitor.debug.c.e("onRatingBarDelay", f + "");
        a(f);
    }

    private void a(final float f) {
        if (f >= 1.0f) {
            i.a("event_A179", null, ReaderApplication.getApplicationImp());
            g.a().a(new UploadBookUserScoreTask(this.d, f, new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderEndPager b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    Message obtainMessage = this.b.r.obtainMessage();
                    obtainMessage.what = 10000502;
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        obtainMessage.obj = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                        obtainMessage.arg1 = jSONObject.optInt("code", -1);
                        if (obtainMessage.arg1 >= 0) {
                            this.b.s = f;
                        }
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.e("CommitCommentActivity", e.getMessage());
                        obtainMessage.obj = ReaderApplication.getApplicationImp().getResources().getString(R.string.login_net_exception);
                        obtainMessage.arg1 = -1;
                    }
                    this.b.r.sendMessage(obtainMessage);
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    Message obtainMessage = this.b.r.obtainMessage();
                    obtainMessage.what = 10000502;
                    obtainMessage.obj = ReaderApplication.getApplicationImp().getResources().getString(R.string.login_net_exception);
                    obtainMessage.arg1 = -1;
                    this.b.r.sendMessage(obtainMessage);
                }
            }));
        }
    }

    public void b() {
        com.qq.reader.common.monitor.debug.c.a("endpage", "start ");
        g.a().a(new EndPageNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ ReaderEndPager a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                com.qq.reader.common.monitor.debug.c.a("endpage", str);
                if (!TextUtils.isEmpty(str)) {
                    try {
                        this.a.a(new JSONObject(str));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.common.monitor.debug.c.a("endpage", "error " + exception);
            }
        }));
    }

    public void c() {
        g.a().a(new GetBookUserScoreTask(this.d, new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ ReaderEndPager a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                com.qq.reader.common.monitor.debug.c.a("endpage", str);
                Message obtainMessage = this.a.r.obtainMessage();
                obtainMessage.what = 10000501;
                obtainMessage.obj = Float.valueOf(0.0f);
                try {
                    JSONObject optJSONObject = new JSONObject(str).optJSONObject("scoreInfo");
                    if (optJSONObject != null) {
                        obtainMessage.obj = Float.valueOf(this.a.s = (float) optJSONObject.optDouble("score", 0.0d));
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("endpage", e.getMessage());
                }
                this.a.r.sendMessage(obtainMessage);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.common.monitor.debug.c.e("endpage", exception.getMessage());
            }
        }));
    }

    public void d() {
        if (com.qq.reader.appconfig.a.d.n) {
            this.b.b();
        } else {
            this.b.a();
        }
    }

    public void e() {
        f();
    }

    private void f() {
        if (this.x != null && this.x.size() > 0) {
            d dVar;
            View view;
            Map hashMap = new HashMap();
            if (this.m) {
                hashMap.put("finish", "1");
            } else {
                hashMap.put("finish", "0");
            }
            i.a("event_B202", hashMap, this.o);
            View view2;
            if (this.u.getVisibility() == 0) {
                view2 = this.u;
                dVar = this.w;
                view = view2;
            } else if (this.t.getVisibility() == 0) {
                view2 = this.t;
                dVar = this.v;
                view = view2;
            } else {
                dVar = null;
                view = null;
            }
            for (int i = 0; i < 3; i++) {
                int i2;
                if (this.E < this.x.size()) {
                    i2 = this.E;
                } else {
                    i2 = 0;
                }
                this.E = i2;
                final c cVar = (c) this.x.get(i2);
                View findViewById = view.findViewById(this.c[i]);
                TextView textView = (TextView) findViewById.findViewById(R.id.bookname);
                TextView textView2 = (TextView) findViewById.findViewById(R.id.authorname);
                TextView textView3 = (TextView) findViewById.findViewById(R.id.info);
                com.qq.reader.common.imageloader.c.a(this.o).a(cVar.b, (ImageView) findViewById.findViewById(R.id.bookcover), com.qq.reader.common.imageloader.a.a().j());
                textView.setText(cVar.c);
                if (this.N) {
                    textView2.setText(cVar.h + DLConstants.DEPENDENCY_PACKAGE_DIV + cVar.d);
                } else {
                    try {
                        textView2.setText(cVar.g);
                    } catch (Exception e) {
                        return;
                    }
                }
                if (textView3 != null) {
                    textView3.setText(cVar.e);
                }
                if (this.D != 0) {
                    textView2.setTextColor(this.D);
                }
                textView.setTextColor(dVar.a.getTextColors());
                int i3 = com.qq.reader.appconfig.a.d.i(this.o, this.d);
                if (this.g > i3) {
                    dVar.o.setText("" + a(this.g - i3));
                    dVar.o.setVisibility(0);
                } else {
                    dVar.o.setVisibility(8);
                }
                findViewById.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ReaderEndPager b;

                    public void onClick(View view) {
                        this.b.a(new com.qq.reader.common.c.c.a(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void a() {
                                Intent intent = new Intent();
                                intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
                                intent.putExtra("URL_BUILD_PERE_BOOK_ID", cVar.a);
                                intent.putExtra(s.STATPARAM_KEY, cVar.f);
                                if (this.a.b.m) {
                                    intent.putExtra("PARA_TYPE_IS_BOOK_FINISH", "1");
                                } else {
                                    intent.putExtra("PARA_TYPE_IS_BOOK_FINISH", "0");
                                }
                                intent.putExtra("PARA_TYPE_IS_FROM_END_PAGE_READER", true);
                                intent.setClass(this.a.b.o, NativeBookStoreConfigDetailActivity.class);
                                this.a.b.o.startActivity(intent);
                                i.a("event_B118", null, ReaderApplication.getApplicationImp());
                                Map hashMap = new HashMap();
                                if (this.a.b.m) {
                                    hashMap.put("finish", "1");
                                } else {
                                    hashMap.put("finish", "0");
                                }
                                i.a("event_B204", hashMap, this.a.b.o);
                            }
                        });
                    }
                });
                this.E++;
            }
        }
    }

    private void g() {
        com.qq.reader.common.monitor.debug.c.e("endpage", this.y.size() + "");
        if (this.u.getVisibility() != 0) {
            View view = this.t;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.author_books);
            linearLayout.removeAllViews();
            if (this.y == null || this.y.size() <= 0) {
                view.findViewById(R.id.author_relate_book_area).setVisibility(8);
                return;
            }
            view.findViewById(R.id.author_relate_book_area).setVisibility(0);
            Map hashMap = new HashMap();
            if (this.m) {
                hashMap.put("finish", "1");
            } else {
                hashMap.put("finish", "0");
            }
            i.a("event_B201", hashMap, this.o);
            View a;
            if (this.y.size() == 1) {
                a = a((c) this.y.get(0));
                a.setBackgroundResource(R.color.translucent);
                linearLayout.addView(a);
            } else if (this.y.size() > 1) {
                a = a(this.y);
                a.setBackgroundResource(R.color.translucent);
                linearLayout.addView(a);
            }
            this.F = ((c) this.y.get(0)).d;
            if (this.y.size() > 3) {
                view.findViewById(R.id.author_change_books).setVisibility(0);
                view.findViewById(R.id.author_change_books).setOnClickListener(this);
                return;
            }
            view.findViewById(R.id.author_change_books).setVisibility(8);
        }
    }

    private void h() {
        if (!this.N) {
            if (this.a == null) {
                this.v.l.setVisibility(8);
                return;
            }
            this.v.l.setVisibility(0);
            ((TextView) this.v.l.findViewById(R.id.adv_txt)).setText(this.a.b);
            ((TextView) this.v.l.findViewById(R.id.bookshelf_advheader_lefticon)).setText(this.a.a);
            this.v.l.setOnClickListener(this);
            i.a("event_C256", null, ReaderApplication.getApplicationImp());
        }
    }

    private void a(JSONObject jSONObject) {
        int i = 0;
        com.qq.reader.common.monitor.debug.c.a("endpage", jSONObject.toString());
        JSONObject optJSONObject = jSONObject.optJSONObject("commentinfo");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("relRec");
        JSONObject optJSONObject3 = jSONObject.optJSONObject("authorRec");
        this.d = (long) optJSONObject.optInt("bid");
        this.e = jSONObject.optString("qqid");
        this.g = optJSONObject.optInt("commentcount");
        this.i = jSONObject.optInt("userCommentCount", -1);
        this.j = jSONObject.optLong("userBeginReadTime", -1);
        this.k = jSONObject.optLong("userTotalReadTime", -1);
        this.h = jSONObject.optBoolean("userScored", false);
        optJSONObject = jSONObject.optJSONObject("ad");
        if (optJSONObject != null) {
            this.a = new b();
            this.a.a = optJSONObject.optJSONObject(ComicStoreExclusiveItemCard.NET_AD_ATTR_EXTINFO).optString("label");
            this.a.b = optJSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_DES);
            this.a.c = optJSONObject.optString("image");
            this.a.d = optJSONObject.optString("link_url");
            this.a.e = optJSONObject.optString("type");
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = optJSONObject2.optJSONArray("bookList");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject4 = optJSONArray.optJSONObject(i2);
                c cVar = new c();
                cVar.d = optJSONObject4.optString("author");
                cVar.c = optJSONObject4.optString("title");
                cVar.a = optJSONObject4.optLong("bid");
                cVar.b = ao.g(cVar.a);
                cVar.f = optJSONObject4.optString(s.STATPARAM_KEY);
                cVar.e = optJSONObject4.optString("intro");
                cVar.h = optJSONObject4.optString("categoryName");
                cVar.g = optJSONObject4.optJSONObject("ext").optString("read_percent") + "%读过";
                arrayList.add(cVar);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (optJSONObject3 != null) {
            optJSONArray = optJSONObject3.optJSONArray("bookList");
            while (optJSONArray != null && i < optJSONArray.length()) {
                optJSONObject3 = optJSONArray.optJSONObject(i);
                c cVar2 = new c();
                cVar2.d = optJSONObject3.optString("author");
                cVar2.c = optJSONObject3.optString("title");
                cVar2.a = optJSONObject3.optLong("bid");
                cVar2.b = ao.g(cVar2.a);
                cVar2.f = optJSONObject3.optString(s.STATPARAM_KEY);
                cVar2.e = optJSONObject3.optString("intro");
                arrayList2.add(cVar2);
                i++;
            }
        }
        a aVar = new a();
        aVar.b = arrayList;
        aVar.a = arrayList2;
        Message obtain = Message.obtain();
        obtain.what = 10000511;
        obtain.obj = aVar;
        if (this.r != null) {
            this.r.sendMessage(obtain);
        }
    }

    public void a(IBook iBook) {
        if (iBook != null) {
            this.d = iBook.getBookNetId();
            this.f = iBook.getBookShortName();
            this.n = iBook.getAuthorIcon();
            this.G = iBook;
        }
    }

    public void a(boolean z) {
        this.m = z;
        if (z) {
            this.w.s.setVisibility(0);
            this.w.t.setVisibility(0);
            this.v.s.setVisibility(0);
            this.v.t.setVisibility(0);
            this.w.a.setText(this.o.getResources().getString(R.string.endpage_tag_1_1));
            this.w.b.setText(this.o.getResources().getString(R.string.endpage_tag_1_2));
            this.v.a.setText(this.o.getResources().getString(R.string.endpage_tag_1_1));
            this.v.b.setText(this.o.getResources().getString(R.string.endpage_tag_1_2));
            this.w.c.setVisibility(0);
            this.w.d.setVisibility(0);
            this.v.c.setVisibility(0);
            this.v.d.setVisibility(0);
            ((TextView) this.v.e.findViewById(R.id.bookclub_text)).setText(R.string.endpage_go_comment2);
            ((TextView) this.w.e.findViewById(R.id.bookclub_text)).setText(R.string.endpage_go_comment2);
            i();
            this.w.n.setVisibility(8);
            this.v.n.setVisibility(8);
        } else {
            this.w.a.setText(this.o.getResources().getString(R.string.endpage_tag_2_1));
            this.w.b.setText(this.o.getResources().getString(R.string.endpage_tag_2_2));
            this.v.a.setText(this.o.getResources().getString(R.string.endpage_tag_2_1));
            this.v.b.setText(this.o.getResources().getString(R.string.endpage_tag_2_2));
        }
        c();
    }

    private void i() {
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        if (this.N) {
            linearLayout2 = (LinearLayout) this.u.findViewById(R.id.reading_achievement_layout);
            this.L = 13;
            this.M = 10;
            linearLayout = linearLayout2;
        } else {
            linearLayout2 = (LinearLayout) this.t.findViewById(R.id.reading_achievement_layout);
            this.L = 16;
            this.M = 11;
            linearLayout = linearLayout2;
        }
        if (this.k <= 600000 || !this.m) {
            linearLayout.setVisibility(8);
            return;
        }
        i.a("event_B153", null, ReaderApplication.getApplicationImp());
        linearLayout.setVisibility(0);
        if (!this.N) {
            final ImageView imageView = (ImageView) linearLayout.findViewById(R.id.user_icon);
            try {
                com.qq.reader.common.imageloader.c.a(this.o).a(com.qq.reader.common.login.c.c().b(), imageView, com.qq.reader.common.imageloader.a.a().b(), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
                    final /* synthetic */ ReaderEndPager b;

                    public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                        return false;
                    }

                    public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                        imageView.setBackgroundResource(R.drawable.achievement_icon_bg);
                        return false;
                    }
                });
            } catch (Exception e) {
            }
        }
        TextView textView = (TextView) linearLayout.findViewById(R.id.start_read_time);
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.total_read_time);
        TextView textView3 = (TextView) linearLayout.findViewById(R.id.user_comment);
        int i = (int) (this.k / 3600000);
        int i2 = ((int) (this.k / BuglyBroadcastRecevier.UPLOADLIMITED)) % 60;
        this.J = new SimpleDateFormat("yyyy年MM月", Locale.CHINA).format(Long.valueOf(this.j));
        if (i > 0) {
            this.K = i + "小时" + i2 + "分钟";
        } else {
            this.K = i2 + "分钟";
        }
        textView.setText(a(this.J, "开始阅读"));
        textView2.setText(a(this.K, "累计时长"));
        if (this.i <= 0) {
            textView3.setVisibility(8);
            linearLayout.findViewById(R.id.achieve_divider).setVisibility(8);
            return;
        }
        textView3.setText(a(this.i + "条", "发表书评"));
    }

    private SpannableString a(String str, String str2) {
        SpannableString spannableString = new SpannableString(str + "\n" + str2);
        spannableString.setSpan(new AbsoluteSizeSpan(this.L, true), 0, str.length() + 1, 17);
        spannableString.setSpan(new AbsoluteSizeSpan(this.M, true), str.length() + 1, spannableString.length(), 34);
        spannableString.setSpan(new ForegroundColorSpan(WebView.NIGHT_MODE_COLOR), 0, str.length(), 17);
        spannableString.setSpan(new ForegroundColorSpan(-6710887), str.length() + 1, spannableString.length(), 34);
        return spannableString;
    }

    public static String a(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i < Constants.ERRORCODE_UNKNOWN) {
            stringBuffer.append(i);
            stringBuffer.append("");
        } else if (i < Constants.ERRORCODE_UNKNOWN || i >= 1000000) {
            stringBuffer.append((i + 5000) / Constants.ERRORCODE_UNKNOWN);
            stringBuffer.append("万");
        } else {
            stringBuffer.append(i / Constants.ERRORCODE_UNKNOWN);
            stringBuffer.append(".");
            stringBuffer.append(((i + http.Internal_Server_Error) % Constants.ERRORCODE_UNKNOWN) / 1000);
            stringBuffer.append("万");
        }
        return stringBuffer.toString();
    }

    public void a(Configuration configuration) {
        com.qq.reader.common.monitor.debug.c.a("endpage", "onConfigurationChanged " + configuration.orientation);
        if (configuration.orientation == 2) {
            this.t.setVisibility(8);
            this.u.setVisibility(0);
            this.N = true;
            i();
        } else {
            this.t.setVisibility(0);
            this.u.setVisibility(8);
            this.N = false;
            i();
        }
        if (this.I != null) {
            if (this.I.f()) {
                this.I.dismiss();
            }
            this.I = null;
        }
        j();
    }

    private void j() {
        if (System.currentTimeMillis() - this.H > 1000) {
            this.H = System.currentTimeMillis();
            g();
            f();
            h();
            k();
        }
    }

    private void k() {
        if (!this.m) {
            if (this.h) {
                this.w.s.setVisibility(8);
                this.w.t.setVisibility(8);
                this.v.s.setVisibility(8);
                this.v.t.setVisibility(8);
                this.w.c.setVisibility(8);
                this.w.d.setVisibility(0);
                this.v.c.setVisibility(8);
                this.v.d.setVisibility(0);
                this.w.e.setVisibility(0);
                this.v.e.setVisibility(0);
                ((TextView) this.v.e.findViewById(R.id.bookclub_text)).setText(R.string.endpage_go_comment);
                ((TextView) this.w.e.findViewById(R.id.bookclub_text)).setText(R.string.endpage_go_comment);
                return;
            }
            this.w.s.setVisibility(0);
            this.w.t.setVisibility(0);
            this.v.s.setVisibility(0);
            this.v.t.setVisibility(0);
            ((TextView) this.v.e.findViewById(R.id.bookclub_text)).setText(R.string.endpage_go_comment2);
            this.w.d.setVisibility(8);
            this.v.d.setVisibility(8);
        }
    }

    private View a(final c cVar) {
        View inflate = ((LayoutInflater) ReaderApplication.getApplicationImp().getSystemService("layout_inflater")).inflate(R.layout.localstore_detail_listcard_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.book_name);
        TextView textView2 = (TextView) inflate.findViewById(R.id.book_author);
        TextView textView3 = (TextView) inflate.findViewById(R.id.book_info);
        TextView textView4 = (TextView) inflate.findViewById(R.id.book_popularity);
        com.qq.reader.common.imageloader.c.a(this.o).a(cVar.b, (ImageView) inflate.findViewById(R.id.book_cover), com.qq.reader.common.imageloader.a.a().j());
        textView.setText(cVar.c);
        textView2.setText(cVar.d);
        textView3.setText(cVar.e);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReaderEndPager b;

            public void onClick(View view) {
                this.b.a(new com.qq.reader.common.c.c.a(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        Intent intent = new Intent();
                        intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
                        intent.putExtra("URL_BUILD_PERE_BOOK_ID", cVar.a);
                        intent.putExtra(s.STATPARAM_KEY, cVar.f);
                        if (this.a.b.m) {
                            intent.putExtra("PARA_TYPE_IS_BOOK_FINISH", "1");
                        } else {
                            intent.putExtra("PARA_TYPE_IS_BOOK_FINISH", "0");
                        }
                        intent.putExtra("PARA_TYPE_IS_FROM_END_PAGE_WRITER", true);
                        intent.setClass(this.a.b.o, NativeBookStoreConfigDetailActivity.class);
                        this.a.b.o.startActivity(intent);
                        i.a("event_B146", null, ReaderApplication.getApplicationImp());
                        Map hashMap = new HashMap();
                        if (this.a.b.m) {
                            hashMap.put("finish", "1");
                        } else {
                            hashMap.put("finish", "0");
                        }
                        i.a("event_B203", hashMap, this.a.b.o);
                    }
                });
            }
        });
        return inflate;
    }

    private View a(List<c> list) {
        View inflate = ((LayoutInflater) ReaderApplication.getApplicationImp().getSystemService("layout_inflater")).inflate(R.layout.endpage_card_horizon_booklist, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_show_all);
        textView.setText("查看全部作品");
        if (this.l) {
            textView.setVisibility(0);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ReaderEndPager a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            });
        } else {
            textView.setVisibility(8);
        }
        int i = 0;
        while (i < list.size() && i < this.z.length) {
            final c cVar = (c) list.get(i);
            ImageView imageView = (ImageView) inflate.findViewById(this.z[i]);
            TextView textView2 = (TextView) inflate.findViewById(this.A[i]);
            ((TextView) inflate.findViewById(this.B[i])).setVisibility(8);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(this.C[i]);
            com.qq.reader.common.imageloader.c.a(this.o).a(cVar.b, imageView, com.qq.reader.common.imageloader.a.a().j());
            textView2.setText(cVar.c);
            linearLayout.setVisibility(0);
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ReaderEndPager b;

                public void onClick(View view) {
                    com.qq.reader.common.c.c.a anonymousClass1 = new com.qq.reader.common.c.c.a(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            Intent intent = new Intent();
                            intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
                            intent.putExtra("URL_BUILD_PERE_BOOK_ID", cVar.a);
                            intent.putExtra(s.STATPARAM_KEY, cVar.f);
                            if (this.a.b.m) {
                                intent.putExtra("PARA_TYPE_IS_BOOK_FINISH", "1");
                            } else {
                                intent.putExtra("PARA_TYPE_IS_BOOK_FINISH", "0");
                            }
                            intent.putExtra("PARA_TYPE_IS_FROM_END_PAGE_WRITER", true);
                            intent.setClass(this.a.b.o, NativeBookStoreConfigDetailActivity.class);
                            this.a.b.o.startActivity(intent);
                            i.a("event_B146", null, ReaderApplication.getApplicationImp());
                            Map hashMap = new HashMap();
                            if (this.a.b.m) {
                                hashMap.put("finish", "1");
                            } else {
                                hashMap.put("finish", "0");
                            }
                            i.a("event_B203", hashMap, this.a.b.o);
                        }
                    };
                    if (((ReaderPageActivity) this.b.o).h()) {
                        ((ReaderPageActivity) this.b.o).a(anonymousClass1);
                    } else {
                        anonymousClass1.a();
                    }
                }
            });
            i++;
        }
        if (list.size() == 2) {
            View findViewById = inflate.findViewById(R.id.ll_book_3);
            findViewById.setVisibility(4);
            findViewById.setClickable(false);
        }
        return inflate;
    }
}
