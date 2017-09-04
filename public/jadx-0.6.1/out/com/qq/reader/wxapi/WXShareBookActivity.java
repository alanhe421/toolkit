package com.qq.reader.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookshelf.h;
import com.qq.reader.module.bookshelf.h.a;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class WXShareBookActivity extends Activity implements a {
    public static String a = "";
    private Context b;
    private ImageButton c;
    private String d;
    private String e;
    private Handler f;
    private ListView g;
    private h h;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.wx_share_bookstand);
        this.f = new Handler(this) {
            final /* synthetic */ WXShareBookActivity a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.a(message);
            }
        };
        this.b = getApplicationContext();
        Bundle extras = getIntent().getExtras();
        this.e = extras.getString("WXTRANSACTION");
        this.d = extras.getString("WXSENDTARGETNAME");
        this.g = (ListView) findViewById(R.id.booklist);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.h = new h(this, false);
        this.h.b(displayMetrics.heightPixels);
        this.h.a(displayMetrics.widthPixels);
        this.c = (ImageButton) findViewById(R.id.wx_share_title_image_left);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WXShareBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        a();
    }

    protected void onPause() {
        super.onPause();
        finish();
    }

    private void a() {
        if (this.h.c() > 0) {
            this.h.a();
        }
        List<Mark> g = i.c().g();
        List arrayList = new ArrayList();
        for (Mark mark : g) {
            if (mark.getType() != 3 && (!mark.getBookName().endsWith(".teb") || ao.a(mark))) {
                arrayList.add(mark);
            }
        }
        if (arrayList != null && arrayList.size() > 0) {
            Mark[] markArr = new Mark[arrayList.size()];
            arrayList.toArray(markArr);
            this.h.a(markArr);
            this.h.a(this);
            this.g.setAdapter(this.h);
        }
    }

    protected void onResume() {
        super.onResume();
        this.e = getIntent().getExtras().getString("WXTRANSACTION");
        if (a.equals(this.e)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            startActivity(intent);
            finish();
            return;
        }
        a = this.e;
    }

    protected boolean a(Message message) {
        int i = message.what;
        return false;
    }

    public void onClickBook(int i) {
        Object item = this.h.getItem(i);
        if (item != null && (item instanceof Mark)) {
            Mark mark = (Mark) item;
            if (mark != null) {
                WXApiManager.getInstance(getApplicationContext()).sendBookToWX(this.b, mark, this.e);
                j.a(75, 0);
                finish();
            }
        }
    }
}
