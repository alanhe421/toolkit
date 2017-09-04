package com.qq.reader.cservice.alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.j;
import com.tencent.feedback.proguard.R;
import tencent.tls.platform.SigType;

public class NewUserAlarmActivity extends Activity {
    private Button a;
    private Button b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.new_user_alarm);
        j.a(53, 0);
        this.a = (Button) findViewById(R.id.new_user_alarm_sureButton);
        this.b = (Button) findViewById(R.id.new_user_alarm_cancelButton);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewUserAlarmActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewUserAlarmActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void a() {
        j.a(54, 0);
        String str = "http://qqreader.3g.qq.com/book/book.c?id=123730&" + e.b(getApplicationContext());
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.setFlags(SigType.TLS);
        intent.putExtra("com.qq.reader.MainActivity.gift", true);
        intent.putExtra("com.qq.reader.WebContent", str);
        startActivity(intent);
        finish();
    }
}
