package com.qq.reader.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.VoteRewardCommentTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.t;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VoteRewardDialog */
public class aw extends as {
    int[] G = new int[]{R.id.rl_item_1, R.id.rl_item_2, R.id.rl_item_3, R.id.rl_item_4, R.id.rl_item_5, R.id.rl_item_6};
    String H = null;
    String I = null;
    private ArrayList<a> J = new ArrayList();
    private String K;
    private String L;
    private String M;
    private String N;

    /* compiled from: VoteRewardDialog */
    private class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public int e;
        public String f;
        final /* synthetic */ aw g;
        private String h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;

        private a(aw awVar) {
            this.g = awVar;
            this.h = "money";
            this.i = "mticket";
            this.j = "name";
            this.k = "imgurl";
            this.l = "applyType";
            this.m = "comment";
        }

        public void a(JSONObject jSONObject) {
            try {
                this.a = jSONObject.optString(this.h);
                this.b = jSONObject.optString(this.i);
                this.c = jSONObject.optString(this.j);
                this.d = jSONObject.optString(this.k);
                this.e = jSONObject.optInt(this.l);
                this.f = jSONObject.optString(this.m);
            } catch (Exception e) {
                c.e("VoteRewardDialog", e.getMessage());
            }
        }

        public boolean a() {
            return this.e != 4;
        }
    }

    /* compiled from: VoteRewardDialog */
    class b extends BaseDialog {
        Activity a;
        TextView b;
        EditText c;
        TextView d;
        TextView e;
        ImageView i;
        TextView j;
        ImageView k;
        t l;
        LinearLayout m;
        final /* synthetic */ aw n;
        private int o;
        private int p;

        public b(final aw awVar, Activity activity, int i, int i2) {
            this.n = awVar;
            this.p = i;
            this.o = i2;
            this.a = activity;
            if (this.f == null) {
                a(this.a, null, R.layout.vote_comment_layout, 1, true);
            }
            this.f.setCanceledOnTouchOutside(false);
            this.b = (TextView) this.f.findViewById(R.id.extra_info);
            this.c = (EditText) this.f.findViewById(R.id.vote_comment);
            this.d = (TextView) this.f.findViewById(R.id.btn_commit);
            this.e = (TextView) this.f.findViewById(R.id.btn_cancel);
            this.i = (ImageView) this.f.findViewById(R.id.img_avatar);
            this.k = (ImageView) this.f.findViewById(R.id.img_fans_level);
            this.j = (TextView) this.f.findViewById(R.id.tv_fan_add_number);
            this.m = (LinearLayout) this.f.findViewById(R.id.ll_fans_add_number);
            com.qq.reader.common.imageloader.c.a(e()).a(com.qq.reader.common.login.c.c().b(), this.i, com.qq.reader.common.imageloader.a.a().b());
            this.k.setImageResource(ao.c(this.o));
            this.j.setText("+" + this.p);
            if (!ao.s(awVar.I)) {
                this.b.setVisibility(0);
                this.b.setText(awVar.I);
            }
            if (!TextUtils.isEmpty(awVar.L)) {
                this.c.setHint(awVar.L);
            }
            this.c.setOnFocusChangeListener(new OnFocusChangeListener(this) {
                final /* synthetic */ b b;

                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        this.b.f.getWindow().setSoftInputMode(5);
                    }
                }
            });
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    String obj = this.b.c.getText().toString();
                    if (ao.s(obj) || !ao.s(obj.trim())) {
                        String trim = obj.trim();
                        if (ao.s(trim)) {
                            trim = this.b.c.getHint().toString().trim();
                        }
                        if (trim.length() < 5) {
                            this.b.n.c("内容过短，再说点什么吧");
                            return;
                        } else if (trim.length() > 1000) {
                            this.b.n.c("已达评论字数上限");
                            return;
                        } else {
                            g.a().a(new VoteRewardCommentTask(this.b.n.l, this.b.n.H, trim, new com.qq.reader.common.readertask.ordinal.c(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                    f.a("str", str);
                                    this.a.b.n.c("发表成功");
                                    ((InputMethodManager) this.a.b.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.b.f.getCurrentFocus().getWindowToken(), 0);
                                    this.a.b.n.i.postDelayed(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            this.a.a.b.cancel();
                                        }
                                    }, 200);
                                }

                                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                    this.a.b.n.c("网络异常，请稍后重试");
                                }
                            }));
                            return;
                        }
                    }
                    this.b.n.c("内容过短，再说点什么吧");
                }
            });
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    ((InputMethodManager) this.b.a.getSystemService("input_method")).hideSoftInputFromWindow(this.b.f.getCurrentFocus().getWindowToken(), 0);
                    this.b.n.i.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.cancel();
                        }
                    }, 200);
                }
            });
            this.l = new t(this.a, true);
            this.l.c(true);
        }

        public void cancel() {
            super.cancel();
            this.l.b(true);
        }

        public void dismiss() {
            super.dismiss();
            this.l.b(true);
        }

        public void d() {
            super.d();
            this.l.b(true);
        }

        public void f_() {
            super.f_();
            if (this.m != null) {
                ObjectAnimator g = g();
                g.setTarget(this.m);
                g.setDuration(1000);
                g.setRepeatCount(2);
                g.start();
            }
        }

        private ObjectAnimator g() {
            PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f});
            return ObjectAnimator.ofPropertyValuesHolder(new Object(), new PropertyValuesHolder[]{ofFloat});
        }
    }

    public aw(Activity activity, long j, int i, String str, boolean z) {
        super(activity, j, i, str, z);
        g();
        p();
        k();
    }

    public void f_() {
        super.f_();
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, E);
        i.a("event_D104", hashMap, ReaderApplication.getApplicationImp());
        i.a("event_C241", null, ReaderApplication.getApplicationImp());
    }

    protected String j() {
        return "3";
    }

    protected void i() {
        super.i();
    }

    private void p() {
        if (this.J == null || this.J.size() == 0) {
            this.J = new ArrayList();
            a aVar = new a();
            aVar.a = "100";
            a aVar2 = new a();
            aVar2.a = "388";
            a aVar3 = new a();
            aVar3.a = "588";
            a aVar4 = new a();
            aVar4.a = "999";
            a aVar5 = new a();
            aVar5.a = "1888";
            a aVar6 = new a();
            aVar6.a = "10000";
            aVar6.b = "赠送1张月票";
            this.J.add(aVar);
            this.J.add(aVar2);
            this.J.add(aVar3);
            this.J.add(aVar4);
            this.J.add(aVar5);
            this.J.add(aVar6);
            View f = f(false);
            a(f);
            this.D.removeAllViews();
            this.D.addView(f);
        }
    }

    protected void a(String str) {
        boolean z;
        Exception exception;
        boolean z2;
        View f;
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("results");
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("rewardWeek");
                JSONArray optJSONArray = optJSONObject.optJSONArray("rewardConf");
                optJSONObject = optJSONObject.optJSONObject("rewardRank");
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
                if (optJSONArray == null || optJSONArray.length() < 6) {
                    z2 = false;
                    if (z2) {
                        f = f(false);
                        a(f);
                    } else {
                        f = f(true);
                        b(f);
                    }
                    this.D.removeAllViews();
                    this.D.addView(f);
                }
                try {
                    if (this.J == null) {
                        this.J = new ArrayList();
                    } else {
                        this.J.clear();
                    }
                    optInt = 0;
                    z2 = true;
                    while (optJSONArray != null) {
                        try {
                            if (optInt >= optJSONArray.length()) {
                                break;
                            }
                            JSONObject jSONObject = optJSONArray.getJSONObject(optInt);
                            if (jSONObject != null) {
                                a aVar = new a();
                                aVar.a(jSONObject);
                                if (!aVar.a()) {
                                    z2 = false;
                                }
                                this.J.add(aVar);
                            }
                            optInt++;
                        } catch (Exception e) {
                            Exception exception2 = e;
                            z = z2;
                            exception = exception2;
                        }
                    }
                } catch (Exception e2) {
                    exception = e2;
                    z = true;
                    c.e(getClass().getSimpleName(), exception.getMessage());
                    z2 = z;
                    if (z2) {
                        f = f(true);
                        b(f);
                    } else {
                        f = f(false);
                        a(f);
                    }
                    this.D.removeAllViews();
                    this.D.addView(f);
                }
                if (z2) {
                    f = f(false);
                    a(f);
                } else {
                    f = f(true);
                    b(f);
                }
                this.D.removeAllViews();
                this.D.addView(f);
            }
        } catch (Exception e3) {
            exception = e3;
            z = false;
            c.e(getClass().getSimpleName(), exception.getMessage());
            z2 = z;
            if (z2) {
                f = f(false);
                a(f);
            } else {
                f = f(true);
                b(f);
            }
            this.D.removeAllViews();
            this.D.addView(f);
        }
    }

    private void a(View view, final a aVar, int i) throws Exception {
        if (aVar != null && i < this.G.length) {
            View findViewById = view.findViewById(this.G[i]);
            int[] iArr = new int[]{R.id.tv_sales_info_1, R.id.tv_sales_info_2, R.id.tv_sales_info_3, R.id.tv_sales_info_4, R.id.tv_sales_info_5, R.id.tv_sales_info_6};
            TextView textView = (TextView) findViewById.findViewById(new int[]{R.id.tv_number_1, R.id.tv_number_2, R.id.tv_number_3, R.id.tv_number_4, R.id.tv_number_5, R.id.tv_number_6}[i]);
            if (!(textView == null || TextUtils.isEmpty(aVar.a))) {
                textView.setText(a(Long.parseLong(aVar.a)));
            }
            textView = (TextView) findViewById.findViewById(iArr[i]);
            if (!(textView == null || TextUtils.isEmpty(aVar.b))) {
                textView.setText(aVar.b);
            }
            findViewById.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ aw b;

                public void onClick(View view) {
                    try {
                        this.b.dismiss();
                        if (com.qq.reader.common.login.c.b()) {
                            this.b.a(aVar);
                        } else {
                            this.b.b(aVar);
                        }
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, aVar.a);
                        i.a("event_C242", hashMap, ReaderApplication.getApplicationImp());
                    } catch (Exception e) {
                        c.e(getClass().getSimpleName(), e.getMessage());
                    }
                }
            });
        }
    }

    private void a(View view) {
        int i = 0;
        try {
            int size;
            if (this.J != null) {
                size = this.J.size();
            } else {
                size = 0;
            }
            while (i < size && i < this.G.length) {
                a(view, (a) this.J.get(i), i);
                i++;
            }
        } catch (Exception e) {
            c.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    private void b(View view, final a aVar, int i) throws Exception {
        if (aVar != null && i < this.G.length) {
            View findViewById = view.findViewById(this.G[i]);
            int[] iArr = new int[]{R.id.tv_sales_info_1, R.id.tv_sales_info_2, R.id.tv_sales_info_3, R.id.tv_sales_info_4, R.id.tv_sales_info_5, R.id.tv_sales_info_6};
            int[] iArr2 = new int[]{R.id.tv_gift_name_1, R.id.tv_gift_name_2, R.id.tv_gift_name_3, R.id.tv_gift_name_4, R.id.tv_gift_name_5, R.id.tv_gift_name_6};
            int[] iArr3 = new int[]{R.id.img_gift_1, R.id.img_gift_2, R.id.img_gift_3, R.id.img_gift_4, R.id.img_gift_5, R.id.img_gift_6};
            TextView textView = (TextView) findViewById.findViewById(new int[]{R.id.tv_number_1, R.id.tv_number_2, R.id.tv_number_3, R.id.tv_number_4, R.id.tv_number_5, R.id.tv_number_6}[i]);
            if (!(textView == null || TextUtils.isEmpty(aVar.a))) {
                textView.setText(a(Long.parseLong(aVar.a)) + ReaderApplication.getApplicationImp().getString(R.string.coin_name));
            }
            textView = (TextView) findViewById.findViewById(iArr[i]);
            if (!(textView == null || TextUtils.isEmpty(aVar.b))) {
                textView.setText(aVar.b);
            }
            textView = (TextView) findViewById.findViewById(iArr2[i]);
            if (!(textView == null || TextUtils.isEmpty(aVar.c))) {
                textView.setText(aVar.c);
            }
            ImageView imageView = (ImageView) findViewById.findViewById(iArr3[i]);
            if (!(imageView == null || TextUtils.isEmpty(aVar.d))) {
                com.qq.reader.common.imageloader.c.a(e()).a(aVar.d, imageView, com.qq.reader.common.imageloader.a.a().j());
            }
            findViewById.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ aw b;

                public void onClick(View view) {
                    try {
                        this.b.dismiss();
                        if (com.qq.reader.common.login.c.b()) {
                            this.b.a(aVar);
                        } else {
                            this.b.b(aVar);
                        }
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, aVar.a);
                        i.a("event_C242", hashMap, ReaderApplication.getApplicationImp());
                    } catch (Exception e) {
                        c.e(getClass().getSimpleName(), e.getMessage());
                    }
                }
            });
        }
    }

    private void a(a aVar) {
        this.K = aVar.b;
        this.L = aVar.f;
        this.c = Integer.parseInt(aVar.a);
        c(0);
    }

    private void b(final a aVar) {
        ((ReaderBaseActivity) this.e).setLoginNextTask(new com.qq.reader.common.login.a(this) {
            final /* synthetic */ aw b;

            public void a(int i) {
                this.b.a(aVar);
            }
        });
        ((ReaderBaseActivity) this.e).startLogin();
    }

    private void b(View view) {
        int i = 0;
        try {
            int size;
            if (this.J != null) {
                size = this.J.size();
            } else {
                size = 0;
            }
            while (i < size && i < this.G.length) {
                b(view, (a) this.J.get(i), i);
                i++;
            }
        } catch (Exception e) {
            c.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    protected CharSequence a(int i, int i2) {
        if (i <= 1) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder(a("只差", 14, Color.parseColor("#999999")));
            spannableStringBuilder.append(a(String.valueOf(i2), 20, Color.parseColor("#ff5959")));
            spannableStringBuilder.append(a("书币就被后1名赶上了", 14, Color.parseColor("#999999")));
            return spannableStringBuilder;
        }
        spannableStringBuilder = new SpannableStringBuilder(a("只差", 14, Color.parseColor("#999999")));
        spannableStringBuilder.append(a(String.valueOf(i2), 20, Color.parseColor("#ff5959")));
        spannableStringBuilder.append(a("书币即可超越前1名", 14, Color.parseColor("#999999")));
        return spannableStringBuilder;
    }

    protected void l() {
        this.y.setText(ReaderApplication.getApplicationImp().getString(R.string.vote_dialog_reward_ticket_count_title));
        this.A.setText(ReaderApplication.getApplicationImp().getString(R.string.vote_dialog_reward_ticket_rank_title));
    }

    protected void m() {
        this.b = this.a.g(this.e.getApplicationContext());
    }

    protected void b(int i) {
    }

    protected void b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code");
            if (optInt == 0) {
                this.H = jSONObject.optString("commentId");
                this.I = jSONObject.optString("resMsg");
                b(jSONObject.optInt("fansnum"), jSONObject.optInt("fanLevel"));
                this.a.e(this.e.getApplicationContext(), this.b - this.c);
                this.i.post(new Runnable(this) {
                    final /* synthetic */ aw a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.cancel();
                    }
                });
            } else if (optInt == 1) {
                this.M = jSONObject.optString("booktitle");
                this.N = jSONObject.optString("balance");
                n();
            } else {
                c(this.e.getApplicationContext().getString(R.string.vote_toast_error));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void b(final int i, final int i2) {
        this.i.post(new Runnable(this) {
            final /* synthetic */ aw c;

            public void run() {
                if (!this.c.e.isFinishing()) {
                    new b(this.c, this.c.e, i, i2).f_();
                }
            }
        });
    }

    protected void n() {
        this.i.post(new Runnable(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void run() {
                View inflate = View.inflate(ReaderApplication.getApplicationImp(), R.layout.vote_reward_need_charge_dialog_content_view, null);
                TextView textView = (TextView) inflate.findViewById(R.id.tv_bookname);
                if (textView != null) {
                    textView.setText("《" + this.a.M + "》");
                }
                textView = (TextView) inflate.findViewById(R.id.tv_reward_count);
                if (textView != null) {
                    CharSequence spannableStringBuilder = new SpannableStringBuilder(this.a.a("打赏：" + this.a.c + ReaderApplication.getApplicationImp().getString(R.string.coin_name), 13, Color.parseColor("#333333")));
                    if (!TextUtils.isEmpty(this.a.K)) {
                        spannableStringBuilder.append(this.a.a("（" + this.a.K + "）", 13, Color.parseColor("#e65051")));
                    }
                    textView.setText(spannableStringBuilder);
                }
                textView = (TextView) inflate.findViewById(R.id.tv_user_balance);
                if (textView != null) {
                    textView.setText("余额：" + this.a.N + ReaderApplication.getApplicationImp().getString(R.string.coin_name));
                }
                com.qq.reader.view.AlertDialog.a a = new com.qq.reader.view.AlertDialog.a(this.a.e).c(R.drawable.alert_dialog_icon).a(inflate).a("打赏失败");
                a.a("余额不足，充值并打赏", new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        i.a("event_C244", null, ReaderApplication.getApplicationImp());
                        if (com.qq.reader.common.login.c.b()) {
                            ((ReaderBaseActivity) this.a.a.e).setChargeNextTask(new com.qq.reader.common.charge.a(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void a() {
                                    this.a.a.a.c(0);
                                }

                                public void b() {
                                    this.a.a.a.n();
                                }

                                public void c() {
                                    this.a.a.a.n();
                                }
                            });
                            new JSPay(this.a.a.e).startCharge(this.a.a.e, 0);
                        }
                    }
                });
                if (!this.a.e.isFinishing()) {
                    AlertDialog a2 = a.a();
                    a2.a(-1, R.drawable.buy_book_dialog_confirm_bg);
                    a2.show();
                    i.a("event_C243", null, ReaderApplication.getApplicationImp());
                }
            }
        });
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1230:
                a(false);
                return true;
            default:
                return false;
        }
    }

    private View f(boolean z) {
        int i;
        Object obj = 1;
        if (d.Y(ReaderApplication.getApplicationImp()) != 1) {
            obj = null;
        }
        if (obj != null) {
            if (z) {
                i = R.layout.vote_reward_option_config_portrait;
            } else {
                i = R.layout.vote_reward_option_default_portrait;
            }
        } else if (z) {
            i = R.layout.vote_reward_option_config_landscape;
        } else {
            i = R.layout.vote_reward_option_default_landscape;
        }
        if (i < 0) {
            return null;
        }
        return View.inflate(ReaderApplication.getApplicationImp(), i, null);
    }

    private String a(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        if (j <= 0) {
            stringBuilder.append(0);
        } else if (j < 100000) {
            stringBuilder.append(j);
        } else if (j < 1000000) {
            stringBuilder.append((500 + j) / TracerConfig.LOG_FLUSH_DURATION);
            r2 = ((500 + j) % TracerConfig.LOG_FLUSH_DURATION) / 1000;
            if (r2 != 0) {
                stringBuilder.append(".");
                stringBuilder.append(r2);
            }
            stringBuilder.append("万");
        } else if (j < 99995000) {
            stringBuilder.append(((int) (5000 + j)) / Constants.ERRORCODE_UNKNOWN);
            stringBuilder.append("万");
        } else {
            stringBuilder.append((5000000 + j) / 100000000);
            r2 = ((5000000 + j) % 100000000) / 10000000;
            if (r2 != 0) {
                stringBuilder.append(".");
                stringBuilder.append(r2);
            }
            stringBuilder.append("亿");
        }
        return stringBuilder.toString();
    }
}
