package com.qq.reader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.common.db.handle.r.a;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.utils.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.view.linearmenu.b;
import com.qq.reader.view.web.g;
import com.tencent.feedback.proguard.R;
import tencent.tls.platform.SigType;

public class OnlineHistoryActivity extends ReaderBaseActivity implements OnItemClickListener, OnItemLongClickListener {
    Context a;
    protected final int b = f.USER_CANCELLED;
    private View c;
    private ListView d;
    private g e;
    private View f;
    private a g = null;
    private TextView h = null;
    private Button i = null;
    private com.qq.reader.view.linearmenu.a j;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.onlinehistory);
        this.i = (Button) findViewById(R.id.profile_header_right_button);
        this.i.setVisibility(0);
        this.i.setText("清空");
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OnlineHistoryActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e.b()) {
                    this.a.e.notifyDataSetChanged();
                    this.a.b();
                }
            }
        });
        this.a = getApplicationContext();
        this.d = (ListView) findViewById(R.id.online_history_list);
        this.f = findViewById(R.id.online_no_history_view);
        this.e = new g(this);
        this.e.a();
        this.d.setAdapter(this.e);
        this.d.setOnItemClickListener(this);
        this.d.setOnItemLongClickListener(this);
        this.d.setOnCreateContextMenuListener(this);
        this.c = findViewById(R.id.profile_header_left_back);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OnlineHistoryActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.setResult(0);
                this.a.finish();
            }
        });
        ((TextView) findViewById(R.id.profile_header_title)).setText(getResources().getString(R.string.profile_history));
        c();
    }

    protected void onResume() {
        super.onResume();
        b();
    }

    private void b() {
        if (this.e.getCount() > 0) {
            this.f.setVisibility(8);
            this.d.setVisibility(0);
            this.i.setVisibility(0);
            return;
        }
        this.f.setVisibility(0);
        this.d.setVisibility(8);
        this.i.setVisibility(8);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        a aVar = (a) this.e.getItem(i);
        if (aVar.b == 0) {
            long longValue;
            String str = aVar.a;
            Intent intent = new Intent();
            try {
                longValue = Long.valueOf(str).longValue();
            } catch (Exception e) {
                longValue = 0;
            }
            if (longValue > 0) {
                intent.setClass(this, NativeBookStoreConfigDetailActivity.class);
                intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
                intent.putExtra("URL_BUILD_PERE_BOOK_ID", longValue);
                startActivity(intent);
            }
        } else if (aVar.b == 2) {
            o.b((Activity) this, aVar.a, "", null, null);
        } else if (aVar.b == 3) {
            o.l(this, aVar.a, null);
        } else {
            Intent intent2 = new Intent();
            intent2.setClass(this, WebBrowserForContents.class);
            intent2.putExtra("com.qq.reader.WebContent", aVar.a);
            intent2.putExtra("com.qq.reader.WebContent_collect", true);
            intent2.putExtra("com.qq.reader.WebContent_share", true);
            intent2.addFlags(SigType.TLS);
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent2);
        }
    }

    private void c() {
        if (this.e.getCount() == 0) {
            this.f.setVisibility(0);
            this.d.setVisibility(8);
            return;
        }
        this.f.setVisibility(8);
        this.d.setVisibility(0);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        setResult(0);
        finish();
        return true;
    }

    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
        try {
            if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        } catch (Exception e) {
        }
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        try {
            if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        } catch (Exception e) {
        }
    }

    public void finish() {
        super.finish();
        try {
            if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        } catch (Exception e) {
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.g = (a) this.e.getItem(i);
        if (this.g != null) {
            a().f_();
        }
        return true;
    }

    public com.qq.reader.view.linearmenu.a a() {
        if (this.j != null) {
            return this.j;
        }
        this.j = new b(this);
        this.j.a(0, getResources().getString(R.string.historylist_menu_del), null);
        this.j.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ OnlineHistoryActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                switch (i) {
                    case 0:
                        this.a.a(this.a.g, false);
                        return true;
                    default:
                        return false;
                }
            }
        });
        return this.j;
    }

    private void a(a aVar, boolean z) {
        this.e.a(aVar);
        this.e.notifyDataSetChanged();
        if (this.e.getCount() == 0) {
            this.f.setVisibility(0);
            this.d.setVisibility(8);
        }
    }
}
