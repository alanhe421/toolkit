package com.qq.reader.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.AlertDialog.a;
import com.tencent.feedback.proguard.R;
import com.tencent.tesla.soload.SoLoadCore;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: VoteRecommendDialog */
public class av extends as {
    public av(Activity activity, long j, int i, String str, boolean z) {
        super(activity, j, i, str, z);
        g();
        k();
    }

    protected String j() {
        return "1";
    }

    public void f_() {
        super.f_();
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, E);
        i.a("event_D102", hashMap, ReaderApplication.getApplicationImp());
    }

    protected void a(String str) {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("results");
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("rTicketWeek");
                optJSONObject = optJSONObject.optJSONObject("rBookRank");
                int optInt2 = optJSONObject.optInt("num");
                int optInt3 = optJSONObject.optInt("pos");
                int optInt4 = optJSONObject.optInt("frontnum");
                int optInt5 = optJSONObject.optInt("nextnum");
                if (optInt3 == 1) {
                    optInt5 = optInt2 - optInt5;
                } else {
                    optInt5 = (optInt4 - optInt2) + 1;
                }
                if (optInt5 < 0) {
                    optInt5 = 0;
                }
                this.z.setText(String.valueOf(optInt));
                this.B.setText(String.valueOf(optInt3));
                this.C.setText(a(optInt3, optInt5));
            }
        } catch (Exception e) {
            c.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    protected CharSequence a(int i, int i2) {
        if (i <= 1) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder(a("只差", 14, Color.parseColor("#999999")));
            spannableStringBuilder.append(a(String.valueOf(i2), 20, Color.parseColor("#ff5959")));
            spannableStringBuilder.append(a("票就被后1名赶上了", 14, Color.parseColor("#999999")));
            return spannableStringBuilder;
        }
        spannableStringBuilder = new SpannableStringBuilder(a("只差", 14, Color.parseColor("#999999")));
        spannableStringBuilder.append(a(String.valueOf(i2), 20, Color.parseColor("#ff5959")));
        spannableStringBuilder.append(a("票即可超越前1名", 14, Color.parseColor("#999999")));
        return spannableStringBuilder;
    }

    protected void l() {
        this.o.setText("投推荐票");
        this.q.setText("1张");
        this.s.setText("2张");
        this.u.setText("3张");
        this.w.setText(BookShelfFragment.CATEGORY_ALL);
        this.y.setText(ReaderApplication.getApplicationImp().getString(R.string.vote_dialog_recommend_ticket_count_title));
        this.A.setText(ReaderApplication.getApplicationImp().getString(R.string.vote_dialog_recommend_ticket_rank_title));
    }

    protected void m() {
        this.b = this.a.d(this.e.getApplicationContext());
        this.p.setText("（今日剩余:" + this.b + "张）");
        if (this.b < 1) {
            a(0, "VOTE_TYPE_RECOMMEND");
        } else if (this.b < 2) {
            a(1, "VOTE_TYPE_RECOMMEND");
        } else if (this.b < 3) {
            a(2, "VOTE_TYPE_RECOMMEND");
        } else if (this.b < 5) {
            a(3, "VOTE_TYPE_RECOMMEND");
        } else {
            a(4, "VOTE_TYPE_RECOMMEND");
        }
    }

    protected void b(int i) {
        if (i == R.id.button_one) {
            this.c = 1;
        } else if (i == R.id.button_two) {
            this.c = 2;
        } else if (i == R.id.button_three) {
            this.c = 3;
        } else if (i == R.id.button_all) {
            this.c = this.a.d(this.e.getApplicationContext());
        }
        c(1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void b(java.lang.String r5) {
        /*
        r4 = this;
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x003d }
        r0.<init>(r5);	 Catch:{ JSONException -> 0x003d }
        r1 = "code";
        r0 = r0.optInt(r1);	 Catch:{ JSONException -> 0x003d }
        if (r0 != 0) goto L_0x0035;
    L_0x000e:
        r0 = r4.e;	 Catch:{ JSONException -> 0x003d }
        r1 = 2131297533; // 0x7f0904fd float:1.8213014E38 double:1.053000892E-314;
        r0 = r0.getString(r1);	 Catch:{ JSONException -> 0x003d }
        r4.c(r0);	 Catch:{ JSONException -> 0x003d }
        r0 = r4.a;	 Catch:{ JSONException -> 0x003d }
        r1 = r4.e;	 Catch:{ JSONException -> 0x003d }
        r1 = r1.getApplicationContext();	 Catch:{ JSONException -> 0x003d }
        r2 = r4.b;	 Catch:{ JSONException -> 0x003d }
        r3 = r4.c;	 Catch:{ JSONException -> 0x003d }
        r2 = r2 - r3;
        r0.b(r1, r2);	 Catch:{ JSONException -> 0x003d }
    L_0x002a:
        r0 = r4.i;
        r1 = new com.qq.reader.view.av$1;
        r1.<init>(r4);
        r0.post(r1);
    L_0x0034:
        return;
    L_0x0035:
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r0 != r1) goto L_0x004c;
    L_0x0039:
        r4.n();	 Catch:{ JSONException -> 0x003d }
        goto L_0x002a;
    L_0x003d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x005d }
        r0 = r4.i;
        r1 = new com.qq.reader.view.av$1;
        r1.<init>(r4);
        r0.post(r1);
        goto L_0x0034;
    L_0x004c:
        r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r0 != r1) goto L_0x0069;
    L_0x0050:
        r0 = r4.e;	 Catch:{ JSONException -> 0x003d }
        r1 = 2131297534; // 0x7f0904fe float:1.8213016E38 double:1.0530008926E-314;
        r0 = r0.getString(r1);	 Catch:{ JSONException -> 0x003d }
        r4.c(r0);	 Catch:{ JSONException -> 0x003d }
        goto L_0x002a;
    L_0x005d:
        r0 = move-exception;
        r1 = r4.i;
        r2 = new com.qq.reader.view.av$1;
        r2.<init>(r4);
        r1.post(r2);
        throw r0;
    L_0x0069:
        r1 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        if (r0 != r1) goto L_0x007a;
    L_0x006d:
        r0 = r4.e;	 Catch:{ JSONException -> 0x003d }
        r1 = 2131297530; // 0x7f0904fa float:1.8213008E38 double:1.0530008906E-314;
        r0 = r0.getString(r1);	 Catch:{ JSONException -> 0x003d }
        r4.c(r0);	 Catch:{ JSONException -> 0x003d }
        goto L_0x002a;
    L_0x007a:
        r0 = r4.e;	 Catch:{ JSONException -> 0x003d }
        r1 = 2131297529; // 0x7f0904f9 float:1.8213005E38 double:1.05300089E-314;
        r0 = r0.getString(r1);	 Catch:{ JSONException -> 0x003d }
        r4.c(r0);	 Catch:{ JSONException -> 0x003d }
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.view.av.b(java.lang.String):void");
    }

    protected void n() {
        if (d("VOTE_TYPE_RECOMMEND")) {
            c(this.e.getString(R.string.vote_toast_no_ticket));
        } else {
            this.i.post(new Runnable(this) {
                final /* synthetic */ av a;

                {
                    this.a = r1;
                }

                public void run() {
                    a b = new a(this.a.e).c(R.drawable.alert_dialog_icon).a("投票失败").b("票数不足，投票失败");
                    b.a("如何获得推荐票", new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent();
                            intent.setClass(this.a.a.e, WebBrowserForContents.class);
                            intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
                            intent.putExtra("com.qq.reader.WebContent", "help/help7.2.html?tf=1&fp=1");
                            com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                            this.a.a.e.startActivity(intent);
                        }
                    }).b(R.string.alert_dialog_cancel, new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.cancel();
                        }
                    });
                    if (!this.a.e.isFinishing()) {
                        b.b();
                    }
                }
            });
        }
    }
}
