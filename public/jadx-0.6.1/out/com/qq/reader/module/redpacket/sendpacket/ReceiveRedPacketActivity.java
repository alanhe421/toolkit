package com.qq.reader.module.redpacket.sendpacket;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.GetRedpacketInfoTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.view.af;
import com.qq.reader.view.m;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ReceiveRedPacketActivity extends ReaderBaseActivity implements OnClickListener {
    private static final String a = ReceiveRedPacketActivity.class.getSimpleName();
    private long b;
    private long c;
    private RedPacket d;
    private ReaderProtocolJSONTask e;
    private TextView f;
    private TextView g;
    private TextView h;
    private Button i;
    private TextView j;
    private View k;
    private TextView l;

    protected boolean isLayoutFillWindow() {
        return false;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 0:
                progressCancel();
                af.a(this, "网络异常，请稍后重试", 1).a();
                finish();
                break;
            case 1:
                progressCancel();
                c();
                break;
            case 2:
                f();
                af.a(this, "网络异常，请稍后重试", 1).a();
                break;
            case 3:
                if (this.d.p() != 0) {
                    e();
                    break;
                }
                o.h(this, a.h + this.d.q() + "&timi=" + d.z(this) + "&skey=" + c.c().a(this), null);
                if (this.d.o() != 1) {
                    new JSAddToBookShelf(this).addById(String.valueOf(this.d.e()), "false");
                }
                finish();
                break;
            case 1001:
                a();
                break;
        }
        return super.handleMessageImp(message);
    }

    public void a(String str, Drawable drawable) {
        if (!isFinishing()) {
            if (this.mProgressDialog == null) {
                if (str == null) {
                    str = "";
                }
                this.mProgressDialog = new m(this);
                this.mProgressDialog.a(str);
                this.mProgressDialog.a(drawable);
                this.mProgressDialog.a(new OnKeyListener(this) {
                    final /* synthetic */ ReceiveRedPacketActivity a;

                    {
                        this.a = r1;
                    }

                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        switch (i) {
                            case 4:
                                this.a.progressCancel();
                                this.a.finish();
                                break;
                        }
                        return false;
                    }
                });
            }
            this.mProgressDialog.f_();
        }
    }

    private void c() {
        if (this.d != null) {
            if (this.d.p() != 3) {
                View findViewById = findViewById(R.id.content_view);
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ReceiveRedPacketActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.finish();
                    }
                });
            }
            ImageView imageView = (ImageView) findViewById(R.id.user_icon);
            this.f = (TextView) findViewById(R.id.redpacket_statue);
            this.i = (Button) findViewById(R.id.open_redpacket);
            this.g = (TextView) findViewById(R.id.redpacket_help);
            this.h = (TextView) findViewById(R.id.lookluck);
            this.j = (TextView) findViewById(R.id.redpacket_message);
            this.k = findViewById(R.id.open_loading);
            findViewById(R.id.close_btn).setOnClickListener(this);
            this.i.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                final /* synthetic */ ReceiveRedPacketActivity a;

                {
                    this.a = r1;
                }

                public void a(View view) {
                    if (c.b()) {
                        this.a.a();
                    } else {
                        this.a.a(1001, null);
                    }
                }
            });
            this.g.setOnClickListener(this);
            this.h.setOnClickListener(this);
            this.j.setText(this.d.i());
            this.l = (TextView) findViewById(R.id.username);
            this.l.setText(this.d.l());
            if (this.d.o() == 1) {
                imageView.setImageResource(R.drawable.redpacket_offical);
                this.l.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.offical_icon), null);
            } else {
                com.qq.reader.common.imageloader.c.a(this).a(this.d.m(), imageView, com.qq.reader.common.imageloader.a.a().e());
                d();
            }
            e();
        }
    }

    private void d() {
        ImageView imageView = (ImageView) findViewById(R.id.vip_icon);
        if (this.d.s() == 1 && this.d.t() == 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.bookvip_month);
        } else if (this.d.s() == 2 && this.d.t() == 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.bookvip_year);
        }
        switch (this.d.t()) {
            case 0:
                if (this.d.n() >= 0) {
                    this.l.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(ao.c(this.d.n())), null);
                    return;
                }
                return;
            case 1:
                imageView.setVisibility(0);
                imageView.setImageResource(ao.e(this.d.n()));
                this.l.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.author_flag_icon), null);
                return;
            default:
                return;
        }
    }

    private void e() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.d.j() + "");
        switch (this.d.p()) {
            case -1:
                f();
                af.a(this, "出错啦，请稍后重试", 1).a();
                return;
            case 0:
                f();
                return;
            case 1:
                this.k.setVisibility(8);
                this.i.setVisibility(8);
                this.f.setVisibility(0);
                this.f.setText(R.string.redpacket_received_all);
                i.a("event_D222", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 2:
                this.k.setVisibility(8);
                this.i.setVisibility(8);
                this.f.setVisibility(0);
                this.f.setText(R.string.redpacket_expired_tip);
                i.a("event_D223", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 3:
                if (!TextUtils.isEmpty(this.d.q())) {
                    o.h(this, a.h + this.d.q() + "&timi=" + d.z(this) + "&skey=" + c.c().a(this), null);
                    finish();
                    return;
                }
                return;
            case 4:
                this.k.setVisibility(8);
                this.i.setVisibility(8);
                this.f.setVisibility(0);
                this.g.setVisibility(0);
                this.g.setOnClickListener(this);
                this.f.setText(R.string.redpacket_no_recommend_ticket);
                this.g.setTag(a.i);
                this.g.setText(R.string.redpacket_how_get_recommend_ticket);
                i.a("event_D220", null, ReaderApplication.getApplicationImp());
                return;
            case 5:
                this.k.setVisibility(8);
                this.i.setVisibility(8);
                this.f.setVisibility(0);
                this.g.setVisibility(0);
                this.g.setOnClickListener(this);
                this.f.setText(R.string.redpacket_no_month_ticket);
                this.g.setTag(a.j);
                this.g.setText(R.string.redpacket_how_get_month_ticket);
                i.a("event_D221", null, ReaderApplication.getApplicationImp());
                return;
            case 6:
                this.k.setVisibility(8);
                this.i.setVisibility(8);
                this.f.setVisibility(0);
                this.g.setVisibility(0);
                this.g.setOnClickListener(this);
                this.f.setText(R.string.redpacket_used_max_month_ticket);
                this.g.setTag(a.k);
                this.g.setText(R.string.redpacket_use_month_ticket_limit);
                return;
            case 7:
                this.k.setVisibility(8);
                this.i.setVisibility(8);
                this.f.setVisibility(0);
                this.f.setText(R.string.redpacket_received_max_tip);
                return;
            case 8:
                f();
                af.a(this, "操作频繁，请稍后再试", 1).a();
                return;
            case 9:
                f();
                af.a(this, "已被拉黑，无法领红包", 1).a();
                return;
            case 10:
                f();
                af.a(this, "本书暂不支持领红包", 1).a();
                return;
            default:
                f();
                af.a(this, this.d.r(), 1).a();
                return;
        }
    }

    private void f() {
        this.k.setVisibility(8);
        this.j.setText(this.d.i());
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        switch (this.d.j()) {
            case 0:
                this.i.setBackgroundResource(R.drawable.open_redpacket_normal_bg);
                return;
            case 1:
                this.i.setBackgroundResource(R.drawable.open_redpacket_month_bg);
                return;
            case 2:
                this.i.setBackgroundResource(R.drawable.open_redpacket_recommend_bg);
                return;
            case 4:
                this.i.setBackgroundResource(R.drawable.open_redpacket_normal_bg);
                return;
            default:
                return;
        }
    }

    private void g() {
        this.e = new GetRedpacketInfoTask(a.d + "rid=" + this.b + "&rtype=" + this.c, new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ ReceiveRedPacketActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                com.qq.reader.common.monitor.debug.c.a(ReceiveRedPacketActivity.a, "str = " + str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    this.a.d = new RedPacket();
                    if (jSONObject.optInt("code") == 0) {
                        this.a.d.b(jSONObject.optInt("redPacketType"));
                        this.a.d.b(jSONObject.optString("redpacketMessage"));
                        this.a.d.f(jSONObject.optString("detailUrl"));
                        this.a.d.f(jSONObject.optInt("rstatus"));
                        this.a.d.e(jSONObject.optInt("yw"));
                        jSONObject = jSONObject.optJSONObject("ownerInfo");
                        if (jSONObject != null) {
                            this.a.d.c(jSONObject.optString("name"));
                            this.a.d.d(jSONObject.optString(MessageKey.MSG_ICON));
                            this.a.d.d(jSONObject.optInt("level"));
                            this.a.d.g(jSONObject.optInt("vipStatus", 0));
                            this.a.d.h(jSONObject.optInt("type"));
                        }
                        this.a.mHandler.sendEmptyMessage(1);
                        return;
                    }
                    this.a.mHandler.sendEmptyMessage(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.a.mHandler.sendEmptyMessage(0);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(0);
            }
        });
        g.a().a(this.e);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.receive_redpacket_layout);
        setSwipeBackEnable(false);
        disableUseAnimation();
        if (getIntent() != null) {
            this.b = getIntent().getLongExtra("rid", 0);
            this.c = (long) getIntent().getIntExtra("rtype", 0);
        }
        a("正在加载...", getResources().getDrawable(R.drawable.red_packet_progress_loading_40x40));
        g();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
                finish();
                return;
            case R.id.redpacket_help:
                String str = (String) this.g.getTag();
                if (!TextUtils.isEmpty(str)) {
                    o.h(this, str, null);
                    return;
                }
                return;
            case R.id.lookluck:
                if (!TextUtils.isEmpty(this.d.q())) {
                    o.h(this, a.h + this.d.q() + "&timi=" + d.z(this) + "&skey=" + c.c().a(this), null);
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a() {
        this.i.setBackgroundResource(R.drawable.hb_loading_bg);
        this.k.setVisibility(0);
        g.a().a(new OpenRedPacketTask(this.b, this.c, new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ ReceiveRedPacketActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                com.qq.reader.common.monitor.debug.c.a(ReceiveRedPacketActivity.a, "str = " + str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optInt("code") == 0) {
                        this.a.d.f(jSONObject.optInt("rstatus"));
                        this.a.d.f(jSONObject.optString("detailUrl"));
                        this.a.d.c(jSONObject.optLong("bid"));
                        this.a.d.g(jSONObject.optString("rtoastMsg", "网络异常，请稍后重试"));
                        this.a.mHandler.sendEmptyMessage(3);
                        return;
                    }
                    this.a.mHandler.sendEmptyMessage(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.a.mHandler.sendEmptyMessage(2);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(2);
            }
        }));
    }

    private void a(final int i, final Object obj) {
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ ReceiveRedPacketActivity c;

            public void a(int i) {
                switch (i) {
                    case 1:
                        Message obtain = Message.obtain();
                        obtain.what = i;
                        obtain.obj = obj;
                        this.c.mHandler.sendMessage(obtain);
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }
}
