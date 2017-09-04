package com.liveshow.view;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.TextView;
import com.liveshow.b.f;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.utils.o;
import com.qq.reader.liveshow.b.d;
import com.qq.reader.liveshow.utils.k;
import com.tencent.feedback.proguard.R;

public class ReaderLiveLibCheckerActivity extends ReaderBaseActivity {
    f a;
    private TextView b;
    private StringBuilder c;
    private int d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(128, 128);
        k.a(this);
        setContentView(R.layout.livecheckeractivity);
        disableUseAnimation();
        this.b = (TextView) findViewById(R.id.toast);
        Intent intent = getIntent();
        if (intent != null) {
            this.d = intent.getIntExtra("room", 0);
        }
        this.a = new f();
        this.c = new StringBuilder();
        this.a.a(this, new d(this) {
            final /* synthetic */ ReaderLiveLibCheckerActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.c.setLength(0);
                this.a.c.append("正在下载访谈插件…").append(i).append("%");
                this.a.b.setText(this.a.c.toString());
            }

            public void a(boolean z) {
                if (z) {
                    this.a.b.setText("加载成功");
                    o.b(this.a, this.a.d);
                    this.a.finish();
                    return;
                }
                this.a.b.setText("访谈插件加载失败");
            }

            public void a() {
                this.a.b.setText("正在下载访谈插件…");
            }
        });
    }

    public void finish() {
        super.finish();
        try {
            if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                overridePendingTransition(0, 0);
            }
        } catch (Exception e) {
        }
    }
}
