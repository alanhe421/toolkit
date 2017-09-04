package com.qq.reader.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.networkUtil.a;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import tencent.tls.platform.SigType;

public class CheckNetWorkActivity extends ReaderBaseActivity implements Callback {
    TextView a;
    ScrollView b;
    StringBuilder c = new StringBuilder();
    String d = "";
    Button e;
    View f;
    boolean g = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.checknetwork_layout);
        this.d = getString(R.string.title_check_net);
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CheckNetWorkActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.e = (Button) findViewById(R.id.profile_header_right_button);
        this.e.setVisibility(0);
        this.e.setText(getString(R.string.send_check_result));
        this.e.setTextColor(getResources().getColor(R.color.commit_comment_btn_bg_color));
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CheckNetWorkActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.g) {
                    try {
                        this.a.a();
                        i.a("event_A241", null, ReaderApplication.getApplicationImp());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.f = findViewById(R.id.loading_layout);
        ((TextView) findViewById(R.id.profile_header_title)).setText(this.d);
        this.b = (ScrollView) findViewById(R.id.scroll_check_info);
        this.a = (TextView) findViewById(R.id.check_info);
        a.a().a(this.mHandler);
    }

    protected void onDestroy() {
        super.onDestroy();
        a.a().b();
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case -10001:
                b();
                this.e.setTextColor(getResources().getColor(R.color.common_titler_title_textcolor));
                this.f.setVisibility(8);
                af.a(ReaderApplication.getApplicationImp(), getString(R.string.check_result_notice), 0).a();
                this.g = true;
                i.a("event_A239", null, ReaderApplication.getApplicationImp());
                break;
            case -10000:
                this.c.append(message.obj);
                this.c.append("\n");
                this.a.setText(this.c.toString());
                this.mHandler.post(new Runnable(this) {
                    final /* synthetic */ CheckNetWorkActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.fullScroll(Opcodes.INT_TO_FLOAT);
                    }
                });
                break;
        }
        return false;
    }

    private void a() {
        if (!ao.a((Activity) this, "800057679", this.a.getText().toString())) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/*");
            intent.setFlags(SigType.TLS);
            intent.putExtra("android.intent.extra.SUBJECT", this.d);
            intent.putExtra("android.intent.extra.TEXT", this.a.getText());
            startActivity(Intent.createChooser(intent, this.d));
        }
    }

    private void b() {
        try {
            ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, this.a.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
