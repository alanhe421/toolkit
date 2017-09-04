package com.qq.reader.module.audio.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.login.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.module.audio.b.b;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.view.af;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioPanicGiftsActivity extends ReaderBaseActivity {
    private static final String m = (e.a + "nativepage/listenTime/getDayListenInfo");
    private static final String n = (e.a + "nativepage/listenTime/getTodayGift");
    private TextView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private Button i;
    private View j;
    private View k;
    private View l;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.audio_panic_gifts_activity);
        b();
        c();
        d();
        h();
    }

    private void b() {
        this.a = (TextView) findViewById(R.id.profile_header_title);
        this.b = (ImageView) findViewById(R.id.profile_header_left_back);
        this.i = (Button) findViewById(R.id.panic_gifts_go);
        this.c = (ImageView) findViewById(R.id.panic_gifts_header);
        this.f = (TextView) findViewById(R.id.panic_gifs_status);
        this.g = (TextView) findViewById(R.id.panic_gifs_status_subtip);
        this.e = (TextView) findViewById(R.id.panic_gifs_time);
        this.h = (TextView) findViewById(R.id.panic_gifts_bottom_tips);
        this.d = (ImageView) findViewById(R.id.panic_gifts_info_bg);
        this.j = findViewById(R.id.loading_layout);
        this.k = findViewById(R.id.loading_failed_layout);
        this.l = findViewById(R.id.content_rl);
    }

    private void c() {
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioPanicGiftsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.i.setOnClickListener(new c(this) {
            final /* synthetic */ AudioPanicGiftsActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                if (this.a.a()) {
                    this.a.i();
                } else {
                    this.a.mHandler.obtainMessage(4, null).sendToTarget();
                }
            }
        });
        if (this.k != null) {
            this.k.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioPanicGiftsActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.h();
                }
            });
        }
        if (!com.qq.reader.common.utils.networkUtil.e.a(getApplicationContext())) {
            g();
        }
    }

    private void d() {
        this.a.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_panicgift_title));
    }

    private void e() {
        this.j.setVisibility(0);
        this.k.setVisibility(8);
        this.l.setVisibility(4);
    }

    private void f() {
        this.j.setVisibility(8);
        this.k.setVisibility(8);
        this.l.setVisibility(0);
    }

    private void g() {
        this.j.setVisibility(8);
        this.k.setVisibility(0);
        this.l.setVisibility(8);
    }

    private void h() {
        e();
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ AudioPanicGiftsActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Object cVar;
                JSONException e;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    cVar = new com.qq.reader.module.audio.b.c();
                    if (jSONObject != null) {
                        try {
                            cVar.a = jSONObject.optInt("todayListenTime");
                            cVar.b = jSONObject.optInt("weekGetTimes");
                            cVar.c = jSONObject.optInt("weekGetMaxTimes");
                            cVar.d = jSONObject.optInt("todayGetStatus");
                            cVar.f = jSONObject.optInt("getAmount");
                            cVar.i = jSONObject.optInt("dayAllGetMaxTickets");
                            cVar.e = jSONObject.optString(MessageKey.MSG_ICON);
                            cVar.g = jSONObject.optBoolean("isLogin", true);
                            cVar.h = jSONObject.optInt("listenTimeToGift", 10);
                            cVar.j = jSONObject.optInt("dayPerGetMaxTickets", 10);
                        } catch (JSONException e2) {
                            e = e2;
                            e.printStackTrace();
                            this.a.mHandler.obtainMessage(0, cVar).sendToTarget();
                        }
                    }
                } catch (JSONException e3) {
                    JSONException jSONException = e3;
                    cVar = null;
                    e = jSONException;
                    e.printStackTrace();
                    this.a.mHandler.obtainMessage(0, cVar).sendToTarget();
                }
                this.a.mHandler.obtainMessage(0, cVar).sendToTarget();
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.obtainMessage(2, null).sendToTarget();
            }
        });
        readerProtocolJSONTask.setUrl(m);
        g.a().a(readerProtocolJSONTask);
    }

    private void i() {
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ AudioPanicGiftsActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Object bVar;
                JSONException e;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    bVar = new b();
                    if (jSONObject != null) {
                        try {
                            bVar.a = jSONObject.optInt("giftCode");
                            bVar.b = jSONObject.optInt("getAmount");
                        } catch (JSONException e2) {
                            e = e2;
                            e.printStackTrace();
                            this.a.mHandler.obtainMessage(1, bVar).sendToTarget();
                        }
                    }
                } catch (JSONException e3) {
                    JSONException jSONException = e3;
                    bVar = null;
                    e = jSONException;
                    e.printStackTrace();
                    this.a.mHandler.obtainMessage(1, bVar).sendToTarget();
                }
                this.a.mHandler.obtainMessage(1, bVar).sendToTarget();
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.obtainMessage(3, null).sendToTarget();
            }
        });
        readerProtocolJSONTask.setUrl(n);
        g.a().a(readerProtocolJSONTask);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 0:
                a(message.obj);
                return true;
            case 1:
                b(message.obj);
                return true;
            case 2:
                g();
                return true;
            case 3:
                j();
                return true;
            case 4:
                this.mLoginNextTask = new a(this) {
                    final /* synthetic */ AudioPanicGiftsActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        this.a.h();
                    }
                };
                setLoginNextTask(this.mLoginNextTask);
                startLogin();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void a(Object obj) {
        if (obj != null) {
            f();
            com.qq.reader.module.audio.b.c cVar = (com.qq.reader.module.audio.b.c) obj;
            String a = a((int) R.string.audio_panicgift_listentime);
            CharSequence format = String.format(a((int) R.string.audio_panicgift_buttom_tips), new Object[]{Integer.valueOf(cVar.h), Integer.valueOf(cVar.i), Integer.valueOf(cVar.c)});
            this.e.setText(String.format(a, new Object[]{Integer.valueOf(cVar.a)}));
            this.h.setText(format);
            if (a()) {
                com.qq.reader.common.imageloader.c.a((Context) this).a(cVar.e, this.c, com.qq.reader.common.imageloader.a.a().f());
            }
            int i = cVar.d;
            int i2 = cVar.f;
            int i3 = cVar.h;
            if (i == 0) {
                boolean z = cVar.a >= i3;
                this.i.setVisibility(0);
                this.i.setEnabled(z);
                this.f.setText(String.format(a((int) R.string.audio_panicgift_chance), new Object[]{Integer.valueOf(cVar.j)}));
                this.g.setText("");
                if (z) {
                    this.i.setText(a((int) R.string.audio_panicgift_chance_available));
                    return;
                }
                i2 = i3 - cVar.a;
                this.i.setText(String.format(a((int) R.string.audio_panicgift_chance_unavailable), new Object[]{Integer.valueOf(i2)}));
                return;
            } else if (i == 1) {
                this.i.setVisibility(4);
                if (i2 == 0) {
                    r0 = a((int) R.string.audio_panicgift_obtain_failed);
                    r1 = a((int) R.string.audio_panicgift_obtain_failed_tip);
                    this.f.setText(r0);
                    this.g.setText(r1);
                    return;
                }
                a = a((int) R.string.audio_panicgift_obtain_success);
                format = a((int) R.string.audio_panicgift_obtain_success_tip);
                this.f.setText(String.format(a, new Object[]{Integer.valueOf(cVar.f)}));
                this.g.setText(format);
                return;
            } else if (i == 2) {
                r0 = String.format(a((int) R.string.audio_panicgift_reachmax), new Object[]{Integer.valueOf(cVar.c)});
                r1 = a((int) R.string.audio_panicgift_reachmax_tip);
                this.f.setText(r0);
                this.g.setText(r1);
                this.i.setVisibility(4);
                return;
            } else {
                return;
            }
        }
        g();
    }

    private void b(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            return;
        }
        if (bVar.a == 0) {
            this.i.setVisibility(4);
            String a = a((int) R.string.audio_panicgift_obtain_success);
            CharSequence a2 = a((int) R.string.audio_panicgift_obtain_success_tip);
            this.f.setText(String.format(a, new Object[]{Integer.valueOf(bVar.b)}));
            this.g.setText(a2);
            return;
        }
        this.i.setVisibility(4);
        CharSequence a3 = a((int) R.string.audio_panicgift_obtain_failed);
        a2 = a((int) R.string.audio_panicgift_obtain_failed_tip);
        this.f.setText(a3);
        this.g.setText(a2);
        this.i.setVisibility(4);
    }

    private void j() {
        if (com.qq.reader.common.utils.networkUtil.e.a(getApplicationContext())) {
            af.a(getApplicationContext(), (CharSequence) "出错了,请稍后重试", 0).a();
        } else {
            af.a(getApplicationContext(), (CharSequence) "网络异常,请稍后重试", 0).a();
        }
    }

    private String a(int i) {
        return ReaderApplication.getApplicationImp().getResources().getString(i);
    }

    public boolean a() {
        return com.qq.reader.common.login.c.b();
    }

    public void startLogin() {
        startLogin(7);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    protected void onResume() {
        super.onResume();
        i.a("event_B307", null, ReaderApplication.getApplicationImp());
    }
}
