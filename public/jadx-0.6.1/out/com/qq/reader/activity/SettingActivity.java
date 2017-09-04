package com.qq.reader.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.tencent.feedback.proguard.R;

public class SettingActivity extends ReaderBaseActivity implements OnClickListener {
    private Button a;
    private LinearLayout b;
    private TextView c;
    private TextView d;
    private boolean e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.settinglayout);
        a();
        getWindow().setFlags(1024, 1024);
    }

    private void a() {
        this.a = (Button) findViewById(R.id.gobackbutton);
        this.b = (LinearLayout) findViewById(R.id.notification_switch);
        this.c = (TextView) findViewById(R.id.notification_switch_close);
        this.d = (TextView) findViewById(R.id.notification_switch_open);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.e = d.aC(this);
        a(this.e);
        this.b.setBackgroundResource(R.drawable.reader_dialog_setting_radiogroup_bg);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gobackbutton:
                finish();
                return;
            case R.id.notification_switch:
                this.e = !this.e;
                a(this.e);
                d.y((Context) this, this.e);
                return;
            default:
                return;
        }
    }

    private void a(boolean z) {
        this.c.setSelected(!this.e);
        this.d.setSelected(this.e);
    }
}
