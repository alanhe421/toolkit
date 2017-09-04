package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.os.Build.VERSION;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.H5GameChargeTask;
import com.qq.reader.common.readertask.protocol.LimitTimeDiscountBuyTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.af;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.module.bookstore.qnative.activity.NativeLimitTimeDiscountBuyActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.tools.util;
import org.json.JSONObject;

public class LimitTimeDiscountBuyCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a bookItem;
    private int checkLimit;
    private long endTime;
    private boolean mIsAttached = false;
    private boolean mIsNeedStatAlg = true;
    private int mPageIndex;
    private long startTime;

    private class a extends s {
        public long a;
        public int b;
        public String c;
        public int d;
        public int e;
        public int f;
        public String g;
        public String h;
        public String i;
        public String j;
        public int k;
        public String l;
        public int m;
        public int n;
        public String o;
        final /* synthetic */ LimitTimeDiscountBuyCard p;

        private a(LimitTimeDiscountBuyCard limitTimeDiscountBuyCard) {
            this.p = limitTimeDiscountBuyCard;
        }

        public void parseData(JSONObject jSONObject) {
            this.a = jSONObject.optLong("bid");
            this.b = jSONObject.optInt("discountType");
            this.c = jSONObject.optString("discountValue");
            this.d = jSONObject.optInt("total", 0);
            this.e = jSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT, 0);
            this.f = jSONObject.optInt("checked", 0);
            this.g = jSONObject.optString("title");
            this.h = jSONObject.optString("cleanIntro");
            this.i = jSONObject.optString("author");
            this.j = jSONObject.optString("categorySName");
            this.l = jSONObject.optString("chapterPriceSum");
            this.m = jSONObject.optInt("form", 0);
            this.k = jSONObject.optInt("lastChapterId", 0);
            this.n = jSONObject.optInt("lprice", 0);
            JSONObject optJSONObject = jSONObject.optJSONObject(s.STATPARAM_KEY);
            if (optJSONObject != null) {
                this.o = optJSONObject.optString(s.ALG);
            }
        }
    }

    public LimitTimeDiscountBuyCard(b bVar, String str) {
        super(bVar, str);
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public void setCheckLimit(int i) {
        this.checkLimit = i;
    }

    public int getResLayoutId() {
        return R.layout.limit_time_discount_buy_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.bookItem = new a();
        this.bookItem.parseData(jSONObject);
        return true;
    }

    private void reloadNetData() {
        Intent intent = new Intent("BROADCAST_ACTION_FORCE_TO_REFRESH");
        if (getEvnetListener() != null && getEvnetListener().getFromActivity() != null) {
            getEvnetListener().getFromActivity().sendBroadcast(intent);
        }
    }

    public void attachView() {
        this.mIsAttached = true;
        if (this.bookItem != null) {
            getRootView().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ LimitTimeDiscountBuyCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.goToDetail(this.a.bookItem);
                }
            });
            c.a(getEvnetListener().getFromActivity()).a(ao.g(this.bookItem.a), (ImageView) ap.a(getRootView(), R.id.img_book_cover), com.qq.reader.common.imageloader.a.a().j());
            TextView textView = (TextView) ap.a(getRootView(), R.id.tv_desc);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_category);
            TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_discount_desc);
            TextView textView4 = (TextView) ap.a(getRootView(), R.id.tv_origin_price);
            TextView textView5 = (TextView) ap.a(getRootView(), R.id.tv_buy_state_desc);
            final TextView textView6 = (TextView) ap.a(getRootView(), R.id.tv_buy);
            ProgressBar progressBar = (ProgressBar) ap.a(getRootView(), R.id.pb_buy_progress);
            LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.ll_progress);
            ((TextView) ap.a(getRootView(), R.id.tv_book_name)).setText(this.bookItem.g);
            textView.setText(this.bookItem.h);
            if (TextUtils.isEmpty(this.bookItem.j)) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(this.bookItem.j);
                textView2.setVisibility(0);
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (this.bookItem.b == 1) {
                stringBuilder.append("限免").append(this.bookItem.c).append("天");
                String stringBuilder2 = stringBuilder.toString();
                CharSequence spannableString = new SpannableString(stringBuilder2);
                spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_3), false), stringBuilder2.indexOf("限免"), stringBuilder2.indexOf("限免") + "限免".length(), 33);
                spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_3), false), stringBuilder2.indexOf("天"), stringBuilder2.indexOf("天") + "天".length(), 33);
                spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_4), false), stringBuilder2.indexOf("限免") + "限免".length(), stringBuilder2.indexOf("天"), 33);
                textView3.setText(spannableString);
            } else if (this.bookItem.b == 2) {
                stringBuilder.append(this.bookItem.c).append("书币");
                textView3.setText(stringBuilder.toString());
            }
            if (this.bookItem.n == 0) {
                textView4.setText(this.bookItem.l + "书币/" + this.bookItem.k + "章");
            } else {
                try {
                    double d = (double) (((float) this.bookItem.n) / 100.0f);
                    textView4.setText(String.format("%.2f", new Object[]{Double.valueOf(d)}) + "元/本");
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                }
            }
            textView4.getPaint().setFlags(16);
            textView4.getPaint().setAntiAlias(true);
            textView5.setVisibility(8);
            progressBar.setVisibility(8);
            linearLayout.setVisibility(8);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.startTime) {
                linearLayout.setVisibility(8);
                textView5.setText("");
                progressBar.setVisibility(8);
                final com.qq.reader.common.utils.af.a aVar = new com.qq.reader.common.utils.af.a();
                aVar.a = String.valueOf(this.bookItem.a);
                aVar.e = this.startTime;
                textView6.setVisibility(8);
                g.a().a(new ReaderIOTask() {
                    public void run() {
                        final boolean b = af.b(aVar);
                        LimitTimeDiscountBuyCard.this.runOnMainThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 b;

                            public void run() {
                                if (b) {
                                    LimitTimeDiscountBuyCard.this.setButtonHasReminder(textView6);
                                } else {
                                    LimitTimeDiscountBuyCard.this.setButtonReminder(textView6);
                                }
                                textView6.setVisibility(0);
                            }
                        });
                    }
                });
            } else if (currentTimeMillis < this.endTime) {
                textView6.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                    final /* synthetic */ LimitTimeDiscountBuyCard b;

                    public void a(View view) {
                        if (com.qq.reader.common.login.c.b()) {
                            this.b.setButtonBuy(textView6);
                            return;
                        }
                        ReaderBaseActivity readerBaseActivity = (ReaderBaseActivity) this.b.getEvnetListener().getFromActivity();
                        readerBaseActivity.setLoginNextTask(new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        this.a.b.setButtonBuy(textView6);
                                        return;
                                    default:
                                        return;
                                }
                            }
                        });
                        readerBaseActivity.startLogin();
                    }
                });
                textView5.setVisibility(0);
                linearLayout.setVisibility(0);
                if (this.bookItem.e >= this.bookItem.d) {
                    progressBar.setVisibility(8);
                    textView5.setVisibility(0);
                    textView5.setText(this.bookItem.e + "人已领");
                    textView6.setText("已领光");
                    textView6.setEnabled(false);
                    textView6.setVisibility(0);
                } else {
                    int i = 0;
                    if (this.bookItem.d > 0) {
                        i = (this.bookItem.e * 100) / this.bookItem.d;
                    }
                    if (i <= 0) {
                        i = 1;
                    }
                    progressBar.setProgress(i);
                    textView5.setVisibility(0);
                    progressBar.setVisibility(0);
                    if (this.bookItem.e <= 0 || this.bookItem.d <= 0) {
                        progressBar.setVisibility(8);
                        textView5.setText("限量" + this.bookItem.d + "本");
                    } else {
                        progressBar.setVisibility(0);
                        textView5.setText("已领" + i + "%");
                    }
                    if (this.bookItem.f == 1) {
                        textView6.setText("已领取");
                        textView6.setEnabled(false);
                    } else {
                        textView6.setText("免费领");
                        textView6.setEnabled(true);
                    }
                    textView6.setVisibility(0);
                }
            } else {
                textView6.setVisibility(8);
            }
            statExposure();
        }
    }

    public void statExposure() {
        int x = ((NativeLimitTimeDiscountBuyActivity) getEvnetListener().getFromActivity()).x();
        if (this.mIsAttached && this.mIsNeedStatAlg && x == this.mPageIndex) {
            CharSequence feedStatString = getFeedStatString();
            if (!TextUtils.isEmpty(feedStatString)) {
                Map hashMap = new HashMap();
                hashMap.put("event_feed_exposure", feedStatString);
                StatisticsManager.a().a("event_feed_exposure", hashMap);
            }
            this.mIsNeedStatAlg = false;
        }
    }

    private String getFeedStatString() {
        if (this.bookItem == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.bookItem.a).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(this.bookItem.o).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
        return stringBuilder.toString();
    }

    private void addToShelf() {
    }

    private void runOnMainThread(Runnable runnable) {
        if (getEvnetListener() != null && getEvnetListener().getFromActivity() != null) {
            getEvnetListener().getFromActivity().runOnUiThread(runnable);
        }
    }

    private void hasGetCheckLimit(final TextView textView) {
        runOnMainThread(new Runnable(this) {
            final /* synthetic */ LimitTimeDiscountBuyCard b;

            public void run() {
                com.qq.reader.view.af.a(ReaderApplication.getApplicationImp(), String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.limit_time_discount_buy_get_check_limit_tip), new Object[]{Integer.valueOf(this.b.checkLimit)}), 0).a();
                textView.setText("免费领");
                textView.setEnabled(true);
            }
        });
    }

    private void hasNoneBookToBuy(final TextView textView) {
        runOnMainThread(new Runnable(this) {
            final /* synthetic */ LimitTimeDiscountBuyCard b;

            public void run() {
                com.qq.reader.view.af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.limit_time_discount_buy_has_none_book), 0).a();
                textView.setText("已抢光");
                textView.setEnabled(false);
            }
        });
    }

    private void anyFailed(final TextView textView) {
        runOnMainThread(new Runnable(this) {
            final /* synthetic */ LimitTimeDiscountBuyCard b;

            public void run() {
                com.qq.reader.view.af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.author_edit_fenda_server_error), 0).a();
                textView.setText("免费领");
                textView.setEnabled(true);
            }
        });
    }

    private void setButtonBuy(final TextView textView) {
        i.a("event_F122", null, ReaderApplication.getApplicationImp());
        textView.setText("领取中...");
        g.a().a(new LimitTimeDiscountBuyTask(String.valueOf(this.bookItem.a), new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ LimitTimeDiscountBuyCard b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    int optInt = new JSONObject(str).optInt("code", -1);
                    if (optInt == 0) {
                        new JSAddToBookShelf(this.b.getEvnetListener().getFromActivity()).addByIdWithCallBack(String.valueOf(this.b.bookItem.a), "true", new com.qq.reader.common.web.js.JSAddToBookShelf.a(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void a() {
                                o.a(this.a.b.getEvnetListener().getFromActivity(), String.valueOf(this.a.b.bookItem.a), null, null, null);
                                textView.setText("已领取");
                                textView.setEnabled(false);
                                this.a.b.reloadNetData();
                            }

                            public void b() {
                                this.a.b.anyFailed(textView);
                            }
                        });
                    } else if (optInt == util.E_NO_TGTKEY) {
                        this.b.hasNoneBookToBuy(textView);
                    } else if (optInt == -1009) {
                        this.b.hasGetCheckLimit(textView);
                    } else {
                        this.b.anyFailed(textView);
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.b.runOnMainThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass8 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        com.qq.reader.view.af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.net_not_available), 0).a();
                        textView.setText("免费领");
                        textView.setEnabled(true);
                    }
                });
            }
        }));
    }

    private void setButtonReminder(final TextView textView) {
        textView.setText("开启提醒");
        textView.setBackgroundResource(R.drawable.limit_time_discount_buy_item_remind_btn_bg);
        textView.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ LimitTimeDiscountBuyCard b;

            class AnonymousClass1 extends ReaderIOTask {
                final /* synthetic */ AnonymousClass3 this$1;
                final /* synthetic */ com.qq.reader.common.utils.af.a val$event;

                AnonymousClass1(AnonymousClass3 anonymousClass3, com.qq.reader.common.utils.af.a aVar) {
                    this.this$1 = anonymousClass3;
                    this.val$event = aVar;
                }

                public void run() {
                    final boolean c = af.c(this.val$event);
                    this.this$1.b.runOnMainThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            if (c) {
                                this.b.this$1.b.setButtonReminder(this.b.this$1.a);
                                com.qq.reader.view.af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.limit_time_discount_buy_reminder_cancel), 0).a();
                            } else {
                                com.qq.reader.view.af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.user_interactive_privacy_update_server_error), 0).a();
                            }
                            this.b.this$1.a.setEnabled(true);
                        }
                    });
                }
            }

            public void a(View view) {
                if (VERSION.SDK_INT < 23) {
                    this.b.openRemind(textView);
                } else if (android.support.v4.content.a.a(this.b.getEvnetListener().getFromActivity(), "android.permission.READ_CALENDAR") != 0) {
                    android.support.v4.app.a.a(this.b.getEvnetListener().getFromActivity(), new String[]{"android.permission.READ_CALENDAR"}, 99);
                    ((NativeLimitTimeDiscountBuyActivity) this.b.getEvnetListener().getFromActivity()).a(new com.qq.reader.module.feed.a.a(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                        }

                        public void b() {
                            this.a.b.openRemind(textView);
                        }
                    });
                } else {
                    this.b.openRemind(textView);
                }
            }
        });
    }

    private void openRemind(final TextView textView) {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(this.startTime));
        i.a("event_F123", hashMap, ReaderApplication.getApplicationImp());
        textView.setEnabled(false);
        final com.qq.reader.common.utils.af.a aVar = new com.qq.reader.common.utils.af.a();
        aVar.a = String.valueOf(this.bookItem.a);
        aVar.e = this.startTime;
        aVar.f = this.endTime;
        aVar.c = String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.limit_time_discount_buy_reminder_title), new Object[]{this.bookItem.g});
        String str = "androidqqreader50://nativepage/LimitTimeDiscountBuy?starttime=" + this.startTime + "&bids=" + this.bookItem.a;
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
        aVar.d = e.df + "?bid=" + this.bookItem.a + "&url=" + str;
        g.a().a(new ReaderIOTask() {
            public void run() {
                final boolean a = af.a(aVar);
                LimitTimeDiscountBuyCard.this.runOnMainThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass9 b;

                    public void run() {
                        if (a) {
                            LimitTimeDiscountBuyCard.this.setButtonHasReminder(textView);
                            com.qq.reader.view.af.a(ReaderApplication.getApplicationImp(), String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.limit_time_discount_buy_reminder_set_successful), new Object[]{Integer.valueOf(3)}), 0).a();
                        } else {
                            com.qq.reader.view.af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.user_interactive_privacy_update_server_error), 0).a();
                        }
                        textView.setEnabled(true);
                    }
                });
            }
        });
    }

    private void setButtonHasReminder(final TextView textView) {
        textView.setText("已开启");
        textView.setBackgroundResource(R.drawable.limit_time_discount_buy_item_remind_checked_btn_bg);
        textView.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ LimitTimeDiscountBuyCard b;

            public void a(View view) {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(this.b.startTime));
                i.a("event_F124", hashMap, ReaderApplication.getApplicationImp());
                textView.setEnabled(false);
                com.qq.reader.common.utils.af.a aVar = new com.qq.reader.common.utils.af.a();
                aVar.a = String.valueOf(this.b.bookItem.a);
                aVar.e = this.b.startTime;
                g.a().a(new AnonymousClass1(this, aVar));
            }
        });
    }

    private void goToDetail(a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("uniteqqreader://nativepage/book/detail?").append("bid=").append(aVar.a).append("&alg=").append(aVar.o).append(H5GameChargeTask.ITEMID).append(aVar.a);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ext_info_id", aVar.a);
            jSONObject.put("itemid", aVar.a);
            jSONObject.put(s.ALG, aVar.o);
            stringBuilder.append("&statInfo=").append(URLEncoder.encode(jSONObject.toString(), "utf-8"));
            com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), stringBuilder.toString(), null);
        } catch (Exception e) {
        }
    }

    public void setPageIndex(int i) {
        this.mPageIndex = i;
    }
}
