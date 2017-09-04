package com.qq.reader.module.readpage.voteview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.qq.reader.view.as;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VoteViewGroup extends LinearLayout implements Callback, OnClickListener {
    public static int b = (((((((((((ImagesView.c + q) + r) + m) + o) + p) + s) + t) + u) + w) + x) + y);
    private static int m = ao.a(92.0f);
    private static int n = ao.a(150.0f);
    private static int o = ao.a(8.0f);
    private static int p = ao.a(12.0f);
    private static int q = 0;
    private static int r = ao.a(25.0f);
    private static int s = (d.o ? ao.a(14.0f) : 0);
    private static int t = 0;
    private static int u;
    private static float v = 14.0f;
    private static int w = 0;
    private static int x = 0;
    private static int y = 0;
    private static float z = 16.0f;
    private int A = Color.parseColor("#5986b3");
    private String B;
    private Handler C = new WeakReferenceHandler(this);
    private Handler D;
    private ViewType E = ViewType.REWARD;
    private int F = 1;
    private int G = 8;
    private boolean H;
    private String I;
    private String J;
    private int K = 1;
    private int L = 0;
    private int M;
    private boolean N;
    private boolean O;
    private boolean P;
    public TextView a;
    private final String c = getClass().getSimpleName();
    private Context d;
    private Map<String, Bitmap> e;
    private a f;
    private Runnable g;
    private Button h;
    private int i = 80;
    private int j = 40;
    private ImagesView k;
    private TextView l;

    public interface a {
        void a(View view);
    }

    public interface b {
        void a(com.qq.reader.module.readpage.voteview.a.a aVar);
    }

    public enum ViewType {
        REWARD,
        RECOMMENT,
        MONTHTICKET
    }

    static {
        int a;
        if (d.o) {
            a = ao.a(12.5f);
        } else {
            a = 0;
        }
        u = a;
    }

    public VoteViewGroup(Context context) {
        super(context);
        a(context);
    }

    public VoteViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public VoteViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        setOrientation(1);
        this.d = context;
        setLayoutParams(new LayoutParams(-1, -2));
        e();
    }

    private void a() {
        this.h = new Button(this.d);
        this.h.setId(R.id.vote_button);
        this.h.setBackgroundResource(R.drawable.vote_icon_reward);
        this.h.setOnClickListener(this);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(n, m);
        layoutParams.setMargins(0, o, 0, p);
        layoutParams.gravity = 17;
        addView(this.h, layoutParams);
    }

    private void b() {
        this.k = new ImagesView(this.d);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, q, 0, r);
        addView(this.k, layoutParams);
        this.k.setIconOnlickListener(new b(this) {
            final /* synthetic */ VoteViewGroup a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.module.readpage.voteview.a.a aVar) {
                if (this.a.D != null) {
                    Message obtainMessage = this.a.D.obtainMessage(500110);
                    obtainMessage.obj = aVar;
                    this.a.D.sendMessage(obtainMessage);
                }
            }
        });
    }

    private void c() {
        this.a = new TextView(this.d);
        this.a.setIncludeFontPadding(false);
        this.a.setTextSize(1, v);
        this.a.setTextColor(getResources().getColor(R.color.bookclub_textblack));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(0, t, 0, u);
        layoutParams.gravity = 17;
        addView(this.a, layoutParams);
    }

    private void d() {
        this.l = new TextView(this.d);
        this.l.setId(R.id.discuss_button);
        this.l.setBackgroundResource(R.drawable.translucent);
        this.l.setOnClickListener(this);
        this.B = this.d.getResources().getString(R.string.chapter_comment_tip);
        this.l.setText(String.format(this.B, new Object[]{Integer.valueOf(0)}));
        this.l.setClickable(true);
        this.l.setTextSize(1, z);
        this.l.setTextColor(getResources().getColorStateList(R.color.vote_discuss_textcolor_selector));
        this.l.setIncludeFontPadding(false);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (this.H) {
            x = ao.a(50.0f);
        } else {
            x = ao.a(25.0f);
        }
        layoutParams.setMargins(0, x, 0, y);
        layoutParams.gravity = 17;
        addView(this.l, layoutParams);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, (int) getRealHeight());
    }

    public float getRealHeight() {
        if (this.H) {
            return (float) ((w + x) + y);
        }
        return ((((((((((this.k.getRealMeasuredHeight() + ((float) q)) + ((float) r)) + ((float) m)) + ((float) o)) + ((float) p)) + ((float) s)) + ((float) t)) + ((float) u)) + ((float) w)) + ((float) x)) + ((float) y);
    }

    public Handler getVoteViewGroupHander() {
        return this.C;
    }

    public void setBaseHander(Handler handler) {
        this.D = handler;
    }

    public void setInvalidateObserver(a aVar) {
        this.f = aVar;
    }

    public void setViewType(ViewType viewType) {
        this.E = viewType;
        f.b("TAG", "setViewType");
        a(this.E);
    }

    public void setViewGroupPadding(int i, int i2, int i3, int i4) {
        setPadding(i, i2, i3, i4);
        requestLayout();
    }

    public void setOnlyDiscussButton(boolean z) {
        this.H = z;
        removeAllViews();
        e();
    }

    private void a(String str, String str2) {
        if (str != null && !"".equals(str)) {
            this.I = str;
            this.J = str2;
            g.a().a(new GetVoteUserIconsTask(new com.qq.reader.module.readpage.voteview.net.a(this.d, this.C, this.E, str), str, str2, com.qq.reader.module.readpage.voteview.net.b.b(this.E, str), this.E));
        }
    }

    public void onClick(View view) {
        Message obtainMessage;
        switch (view.getId()) {
            case R.id.discuss_button:
                obtainMessage = this.D.obtainMessage();
                obtainMessage.what = 1233;
                this.D.sendMessage(obtainMessage);
                return;
            case R.id.vote_button:
                obtainMessage = this.D.obtainMessage();
                obtainMessage.what = 1231;
                obtainMessage.arg1 = this.F;
                obtainMessage.arg2 = 0;
                this.D.sendMessage(obtainMessage);
                HashMap hashMap = new HashMap();
                switch (this.E) {
                    case REWARD:
                        as.E = "3";
                        i.a("event_D101", null, ReaderApplication.getApplicationImp());
                        return;
                    case RECOMMENT:
                        as.E = "3";
                        i.a("event_D96", null, ReaderApplication.getApplicationImp());
                        return;
                    case MONTHTICKET:
                        as.E = "3";
                        i.a("event_D98", null, ReaderApplication.getApplicationImp());
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public boolean handleMessage(Message message) {
        Bundle data;
        switch (message.what) {
            case 100:
                f();
                break;
            case 101:
                if (this.M != message.arg1) {
                    i.a("event_D94", null, ReaderApplication.getApplicationImp());
                    if (this.N) {
                        this.N = false;
                        i.a("event_D100", null, ReaderApplication.getApplicationImp());
                    }
                    if (this.O) {
                        this.O = false;
                        i.a("event_D95", null, ReaderApplication.getApplicationImp());
                    }
                    if (this.P) {
                        this.P = false;
                        i.a("event_D97", null, ReaderApplication.getApplicationImp());
                    }
                }
                this.M = message.arg1;
                break;
            case 102:
                if (this.k != null) {
                    String b = this.k.b();
                    if (!"".equals(b)) {
                        a(b, true);
                        break;
                    }
                }
                break;
            case 103:
                data = message.getData();
                if (data != null) {
                    a(data.getString("VGMSG_BOOKID"), data.getString("VGMSG_CHAPTERID"));
                    break;
                }
                break;
            case 105:
                data = message.getData();
                if (data != null) {
                    a(data.getString("data"), false);
                    break;
                }
                break;
            case 106:
                a(com.qq.reader.module.readpage.voteview.net.b.a(this.E, this.I), true);
                break;
        }
        return true;
    }

    private void e() {
        if (this.H) {
            d();
        } else {
            if (com.qq.reader.common.c.b.j == 0) {
                n = ao.a(140.0f);
                m = ao.a(85.0f);
            } else if (com.qq.reader.common.c.b.j == 1) {
                int a = ao.a(85.0f);
                m = a;
                n = a;
            } else if (com.qq.reader.common.c.b.j == 2) {
                n = ao.a(80.0f);
                m = ao.a(30.0f);
            }
            a();
            if (d.o) {
                c();
            }
            b();
        }
        requestLayout();
    }

    private void f() {
        if (this.k != null) {
            if (this.g != null) {
                this.C.removeCallbacks(this.g);
            }
            this.g = new Runnable(this) {
                final /* synthetic */ VoteViewGroup a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.k.invalidate();
                    if (this.a.f != null) {
                        f.b("TAG", "doUpdate");
                        this.a.f.a(this.a);
                    }
                }
            };
            this.C.postDelayed(this.g, 200);
            f.b(this.c, "call doUpdate");
        }
    }

    private void a(ViewType viewType) {
        a(com.qq.reader.module.readpage.voteview.net.b.a(viewType, this.I), true);
    }

    private void a(String str, boolean z) {
        if (this.I != null) {
            if (str != null && !"".equals(str)) {
                JSONArray jSONArray;
                try {
                    jSONArray = new JSONArray(str);
                } catch (JSONException e) {
                    f.a(this.c, e.getMessage());
                    jSONArray = null;
                }
                int length = jSONArray == null ? 0 : jSONArray.length();
                if (this.k.a != null) {
                    this.k.a.clear();
                }
                this.e = new HashMap(length);
                for (int i = 0; i < length; i++) {
                    com.qq.reader.module.readpage.voteview.a.a aVar = new com.qq.reader.module.readpage.voteview.a.a();
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    aVar.a = optJSONObject.optString("uid");
                    aVar.b = optJSONObject.optString(MessageKey.MSG_ICON);
                    aVar.c = optJSONObject.optString("time");
                    aVar.e = optJSONObject.optString("authorid");
                    aVar.f = optJSONObject.optInt("isManito", 0) == 1;
                    if (!(aVar.b == null || "".equals(aVar.b))) {
                        this.e.put(aVar.b, this.k.b);
                        this.k.a.add(aVar);
                    }
                }
                this.k.setDrawables(this.e);
                this.k.setViewType(this.E);
                this.k.setBid(this.I);
                this.k.a();
                if (this.k != null) {
                    this.k.invalidate();
                }
                g();
            } else if (!z) {
            } else {
                if (this.L < this.K) {
                    com.qq.reader.module.readpage.voteview.net.b.b("", this.E, this.I);
                    a(this.I, this.J);
                    this.L++;
                    return;
                }
                this.L = 0;
            }
        }
    }

    private void g() {
        com.qq.reader.module.feed.mypreference.f.d c = com.qq.reader.module.feed.mypreference.f.a().b().b(n).a(m).a().c(4).c();
        String c2;
        switch (this.E) {
            case REWARD:
                this.F = 1;
                if (com.qq.reader.common.c.b.j == 0) {
                    this.h.setBackgroundResource(R.drawable.vote_icon_reward);
                } else if (com.qq.reader.common.c.b.j == 1) {
                    c.b().d(this.i).c();
                    this.h.setBackgroundDrawable(c.a("赏", this.A));
                } else if (com.qq.reader.common.c.b.j == 2) {
                    c.b().d(this.j).a(n).b(m).c();
                    this.h.setBackgroundDrawable(c.a("求打赏", this.A, this.G));
                }
                if (this.a != null) {
                    c2 = com.qq.reader.module.readpage.voteview.net.b.c(ViewType.REWARD, this.I);
                    if (c2 != null) {
                        this.a.setText(a(c2 + "人打赏", c2.length()));
                    }
                }
                this.N = true;
                break;
            case RECOMMENT:
                this.F = 2;
                if (com.qq.reader.common.c.b.j == 0) {
                    this.h.setBackgroundResource(R.drawable.vote_icon_recommend);
                } else if (com.qq.reader.common.c.b.j == 1) {
                    c.b().d(this.i).c();
                    this.h.setBackgroundDrawable(c.a("荐", this.A));
                } else if (com.qq.reader.common.c.b.j == 2) {
                    c.b().d(this.j).a(n).b(m).c();
                    this.h.setBackgroundDrawable(c.a("求推荐", this.A, this.G));
                }
                if (this.a != null) {
                    c2 = com.qq.reader.module.readpage.voteview.net.b.c(ViewType.RECOMMENT, this.I);
                    if (c2 != null) {
                        this.a.setText(a(c2 + "人推荐", c2.length()));
                    }
                }
                this.O = true;
                break;
            case MONTHTICKET:
                this.F = 3;
                if (com.qq.reader.common.c.b.j == 0) {
                    this.h.setBackgroundResource(R.drawable.vote_icon_monthticket);
                } else if (com.qq.reader.common.c.b.j == 1) {
                    c.b().d(this.i).c();
                    this.h.setBackgroundDrawable(c.a("月", this.A));
                } else if (com.qq.reader.common.c.b.j == 2) {
                    c.b().d(this.j).a(n).b(m).c();
                    this.h.setBackgroundDrawable(c.a("求月票", this.A, this.G));
                }
                if (this.a != null) {
                    c2 = com.qq.reader.module.readpage.voteview.net.b.c(ViewType.MONTHTICKET, this.I);
                    if (c2 != null) {
                        this.a.setText(a(c2 + "人投月票", c2.length()));
                    }
                }
                this.P = true;
                break;
            default:
                this.F = 1;
                if (com.qq.reader.common.c.b.j == 0) {
                    this.h.setBackgroundResource(R.drawable.vote_icon_reward);
                } else if (com.qq.reader.common.c.b.j == 1) {
                    c.b().d(this.i).c();
                    this.h.setBackgroundDrawable(c.a("赏", this.A));
                } else if (com.qq.reader.common.c.b.j == 2) {
                    c.b().d(this.j).a(n).b(m).c();
                    this.h.setBackgroundDrawable(c.a("求打赏", this.A, this.G));
                }
                if (this.a != null) {
                    c2 = com.qq.reader.module.readpage.voteview.net.b.c(ViewType.REWARD, this.I);
                    if (c2 != null) {
                        this.a.setText(a(c2 + "人打赏", c2.length()));
                    }
                }
                this.N = true;
                break;
        }
        invalidate();
        Message obtainMessage = this.C.obtainMessage();
        obtainMessage.what = 100;
        this.C.sendMessage(obtainMessage);
    }

    private SpannableStringBuilder a(String str, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.A), 0, i, 33);
        return spannableStringBuilder;
    }
}
