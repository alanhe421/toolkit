package com.qq.reader.module.redpacket.sendpacket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.e.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.VoteTypeQueryTask;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class RedpacketTypeSelectActivity extends ReaderBaseActivity implements OnClickListener {
    private static final String c = RedpacketTypeSelectActivity.class.getSimpleName();
    protected View a = null;
    protected View b = null;
    private String d;
    private String e;
    private int f;
    private boolean g = false;

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 0:
                a();
                break;
            case 1:
                progressCancel();
                c();
                break;
        }
        return super.handleMessageImp(message);
    }

    protected void a() {
        this.a.setVisibility(8);
        this.b.setVisibility(0);
    }

    private void c() {
        this.a.setVisibility(8);
        findViewById(R.id.content_view).setVisibility(0);
        if (this.g) {
            findViewById(R.id.month_voet_redpacket).setOnClickListener(this);
        } else {
            findViewById(R.id.month_voet_redpacket).setVisibility(8);
        }
        findViewById(R.id.recommend_voet_redpacket).setOnClickListener(this);
        findViewById(R.id.oridinary_voet_redpacket).setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.redpacket_type_select_layout);
        findViewById(R.id.content_view).setVisibility(8);
        this.a = findViewById(R.id.loading_layout);
        ((ProgressBar) findViewById(R.id.default_progress)).setIndeterminateDrawable(getResources().getDrawable(R.drawable.red_packet_progress_loading));
        this.b = findViewById(R.id.loading_failed_layout);
        if (this.b != null) {
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RedpacketTypeSelectActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b();
                    this.a.e();
                }
            });
        }
        b();
        this.d = getIntent().getStringExtra("bid");
        this.e = getIntent().getStringExtra("bookname");
        TextView textView = (TextView) findViewById(R.id.profile_header_title);
        findViewById(R.id.common_titler).setBackgroundResource(R.color.redpacket_title_bg);
        textView.setText(getResources().getString(R.string.redpacket_type_select_title));
        findViewById(R.id.profile_header_left_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedpacketTypeSelectActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        Button button = (Button) findViewById(R.id.profile_header_right_button);
        button.setVisibility(0);
        button.setText("帮助");
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedpacketTypeSelectActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.h(this.a, a.l, null);
            }
        });
        e();
        i.a("event_D217", null, ReaderApplication.getApplicationImp());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.month_voet_redpacket:
                this.f = 1;
                break;
            case R.id.recommend_voet_redpacket:
                this.f = 2;
                break;
            case R.id.oridinary_voet_redpacket:
                this.f = 0;
                break;
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.f + "");
        i.a("event_D218", hashMap, ReaderApplication.getApplicationImp());
        d();
    }

    private void d() {
        Intent intent = new Intent();
        intent.putExtra("bid", this.d);
        intent.putExtra("bookname", this.e);
        intent.putExtra("type", this.f);
        intent.setClass(this, SendRedpacketActivity.class);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra("send_packet_success", false)) {
            setResult(i2, intent);
            finish();
        }
    }

    private void e() {
        g.a().a(new VoteTypeQueryTask(Long.valueOf(this.d).longValue(), new c(this) {
            final /* synthetic */ RedpacketTypeSelectActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int optInt = jSONObject.optInt("code");
                    if (optInt == 0) {
                        this.a.g = jSONObject.getJSONObject("results").getJSONObject("TMR").optBoolean("third", false);
                    } else if (optInt == 8000) {
                        this.a.g = false;
                    }
                    this.a.mHandler.sendEmptyMessage(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(0);
            }
        }));
    }

    protected void b() {
        this.b.setVisibility(8);
        this.a.setVisibility(0);
    }
}
