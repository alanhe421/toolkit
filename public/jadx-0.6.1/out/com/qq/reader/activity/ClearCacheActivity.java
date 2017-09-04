package com.qq.reader.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.utils.aa;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.o;
import com.tencent.feedback.proguard.R;
import com.tencent.qqpimsecure.JumpQqPimSecureUtil;
import java.util.HashMap;
import java.util.Map;

public class ClearCacheActivity extends ReaderBaseActivity {
    private String a = null;
    private TextView b;
    private Button c;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.setting_clearcache_layout);
        a();
        PackageInfo versionInfo = JumpQqPimSecureUtil.getVersionInfo(this);
        if (versionInfo != null) {
            this.a = versionInfo.packageName;
        } else {
            this.a = "com.qq.reader";
        }
        c();
    }

    private void a() {
        this.b = (TextView) findViewById(R.id.cache_size);
        this.c = (Button) findViewById(R.id.deep_clear_btn);
        CharSequence string = getIntent().getExtras().getString("LOCAL_STORE_IN_TITLE");
        if (!TextUtils.isEmpty(string)) {
            ((TextView) findViewById(R.id.profile_header_title)).setText(string);
        }
        findViewById(R.id.profile_header_left_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ClearCacheActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        if (JumpQqPimSecureUtil.getVersionInfo(this) != null) {
            this.c.setText("深度清理");
        } else {
            this.c.setText("立即升级");
        }
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ClearCacheActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
    }

    private void b() {
        if (this.c == null) {
            return;
        }
        if (JumpQqPimSecureUtil.getVersionInfo(this) != null) {
            this.c.setText("深度清理");
        } else {
            this.c.setText("立即升级");
        }
    }

    private void c() {
        g.a().a(new ReaderIOTask() {
            public void run() {
                super.run();
                Message obtainMessage = ClearCacheActivity.this.mHandler.obtainMessage();
                obtainMessage.what = 10000201;
                try {
                    obtainMessage.obj = new Long(aa.a(true));
                } catch (Exception e) {
                    obtainMessage.obj = new Long(0);
                }
                ClearCacheActivity.this.mHandler.sendMessage(obtainMessage);
            }
        });
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 10000201:
                findViewById(R.id.clearing).setVisibility(8);
                findViewById(R.id.content).setVisibility(0);
                if (message.obj != null && (message.obj instanceof Long)) {
                    Long l = (Long) message.obj;
                    if (l.longValue() >= 0) {
                        if (l.longValue() != 0) {
                            this.b.setText("成功清理" + ab.a(l.longValue()));
                            break;
                        }
                        this.b.setText("暂无缓存清理");
                        break;
                    }
                    this.b.setText("正在计算");
                    break;
                }
        }
        return super.handleMessageImp(message);
    }

    protected void onResume() {
        super.onResume();
        b();
    }

    private void d() {
        Map hashMap = new HashMap();
        PackageInfo versionInfo = JumpQqPimSecureUtil.getVersionInfo(this);
        if (versionInfo == null || !JumpQqPimSecureUtil.isOfficalQqSecure(this)) {
            o.b((Activity) this, "http://qqwx.qq.com/s?aid=index&p=5&c=106166&vt=1&pf=0");
            hashMap.put("type", "download");
        } else {
            if (versionInfo.versionCode >= 1066) {
                JumpQqPimSecureUtil.jumpToQqSecure(this, this.a, String.format("{'dest_view':9502721,'show_id':'%s','show_channel':'%s'}", new Object[]{"0", "10211"}), null);
            } else {
                JumpQqPimSecureUtil.callQqSecureOnBg(this);
            }
            hashMap.put("type", "call");
        }
        i.a("event_Z40", hashMap, this);
    }
}
