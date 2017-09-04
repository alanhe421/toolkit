package com.qq.reader.plugin;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.view.af;
import com.qq.reader.view.web.b;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PlugInFontsActivity extends PluginBaseActivity implements g {
    public static HashMap<String, Bitmap> f;
    private static long j = 0;
    ArrayList<a> a;
    ArrayList<l> b = new ArrayList();
    String c = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    GridView d;
    a e;
    af g;
    private ProgressDialog h;
    private String i = "";
    private b k;
    private af l;

    private class a extends BaseAdapter {
        final /* synthetic */ PlugInFontsActivity a;
        private LayoutInflater b;

        private class a {
            TextView a;
            TextView b;
            TextView c;
            TextView d;
            View e;
            TextView f;
            ProgressBar g;
            ImageView h;
            ImageView i;
            Handler j;
            final /* synthetic */ a k;
            private String l;

            private a(a aVar) {
                this.k = aVar;
                this.l = "";
                this.j = new Handler(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void handleMessage(Message message) {
                        switch (message.what) {
                            case 6002:
                                try {
                                    this.a.k.a.e.notifyDataSetChanged();
                                    return;
                                } catch (Exception e) {
                                    f.a("PlugInFontsActivity", "inner_handler " + e.toString());
                                    return;
                                }
                            default:
                                return;
                        }
                    }
                };
            }
        }

        public a(PlugInFontsActivity plugInFontsActivity, Context context) {
            this.a = plugInFontsActivity;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            return this.a.b.size();
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public void a(a aVar, a aVar2) {
            int e = aVar.e();
            String f = aVar.f();
            boolean s = aVar.s();
            aVar2.e.setBackgroundResource(R.drawable.selector_round_blue_button);
            aVar2.d.setTextColor(-1);
            switch (e) {
                case 1:
                case 5:
                    aVar2.d.setVisibility(0);
                    aVar2.d.setText("下载");
                    aVar2.g.setVisibility(8);
                    aVar2.c.setVisibility(8);
                    return;
                case 2:
                case 3:
                    if (s) {
                        aVar2.d.setVisibility(0);
                        aVar2.g.setVisibility(8);
                        aVar2.c.setVisibility(8);
                        aVar2.d.setTextColor(-1);
                        return;
                    }
                    aVar2.d.setVisibility(8);
                    aVar2.g.setVisibility(0);
                    aVar2.g.setMax((int) aVar.c());
                    aVar2.g.setProgress((int) aVar.d());
                    aVar2.c.setVisibility(0);
                    aVar2.c.setText(ao.a(aVar.d(), aVar.c()));
                    return;
                case 8:
                    aVar2.d.setVisibility(0);
                    aVar2.d.setText("重试");
                    aVar2.g.setVisibility(8);
                    aVar2.c.setVisibility(8);
                    return;
                default:
                    aVar2.g.setVisibility(8);
                    aVar2.c.setVisibility(8);
                    aVar2.d.setVisibility(0);
                    if (d.o(this.a.getApplicationContext()).equals(f)) {
                        aVar2.d.setText("使用中");
                        aVar2.e.setBackgroundResource(R.drawable.skin_detail_btn_inuse_bg);
                        return;
                    }
                    aVar2.d.setText("使用");
                    aVar2.e.setBackgroundResource(R.drawable.selector_round_orange_button);
                    return;
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            final l lVar = (l) this.a.b.get(i);
            a aVar2 = (a) this.a.a.get(i);
            if (view == null) {
                view = this.b.inflate(R.layout.plug_in_font_style_item, viewGroup, false);
                a aVar3 = new a();
                aVar3.d = (TextView) view.findViewById(R.id.install_button);
                aVar3.e = view.findViewById(R.id.layout_button);
                aVar3.a = (TextView) view.findViewById(R.id.font_size);
                aVar3.b = (TextView) view.findViewById(R.id.font_pay);
                aVar3.g = (ProgressBar) view.findViewById(R.id.progress);
                aVar3.c = (TextView) view.findViewById(R.id.progress_txt);
                aVar3.h = (ImageView) view.findViewById(R.id.font_img);
                aVar3.i = (ImageView) view.findViewById(R.id.font_style_item_free_tag);
                aVar3.f = (TextView) view.findViewById(R.id.font_name);
                view.setTag(aVar3);
                aVar = aVar3;
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a.setText(lVar.o());
            aVar.b.setText(lVar.v() + this.a.getResources().getString(R.string.charge_gift_bookcoin));
            aVar.f.setText(lVar.l());
            String t = lVar.t();
            if (lVar.a() == 1) {
                aVar.i.setVisibility(8);
                aVar.b.setVisibility(8);
            } else if (t != null) {
                if (t.equals("0")) {
                    aVar.i.setImageResource(R.drawable.skinlist_free_tag);
                    aVar.i.setVisibility(0);
                    aVar.b.setVisibility(8);
                } else if (t.equals("1")) {
                    aVar.i.setVisibility(8);
                    aVar.b.setVisibility(0);
                } else if (t.equals("2")) {
                    aVar.i.setImageResource(R.drawable.skinlist_limited_free_tag);
                    aVar.i.setVisibility(0);
                    aVar.b.setVisibility(8);
                } else {
                    aVar.i.setVisibility(8);
                    aVar.b.setVisibility(0);
                }
            }
            c.a(this.a.getContext()).a(lVar.h(), aVar.h, com.qq.reader.common.imageloader.a.a().j(), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                final /* synthetic */ a b;

                public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    aVar.f.setVisibility(0);
                    aVar.h.setVisibility(4);
                    return false;
                }

                public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    aVar.f.setVisibility(4);
                    aVar.h.setVisibility(0);
                    return false;
                }
            });
            a(aVar2, aVar);
            aVar.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.b(lVar);
                }
            });
            return view;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.plug_in_font_style);
        i();
        a(getIntent().getExtras(), true);
        h();
        this.g = af.a(getApplicationContext(), "", 0);
        ((TextView) findViewById(R.id.profile_header_title)).setText("字体");
        d();
        c();
    }

    private void c() {
        ImageView imageView = (ImageView) findViewById(R.id.font_list_banner);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PlugInFontsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) view.getTag();
                if (aVar == null) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse("http://www.myapp.com/forward/a/590314"));
                    this.a.startActivity(intent);
                } else if (com.qq.reader.qurl.c.a(aVar.h())) {
                    try {
                        com.qq.reader.qurl.c.a(this.a, aVar.h());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    aVar.w().a(new com.qq.reader.module.bookstore.qnative.c.a(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void doFunction(Bundle bundle) {
                        }

                        public Activity getFromActivity() {
                            return this.a.a;
                        }
                    });
                }
            }
        });
        List b = com.qq.reader.cservice.adv.b.a(getApplicationContext()).b("103096");
        if (b == null || b.size() <= 0) {
            imageView.setVisibility(8);
            return;
        }
        com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
        c.a(getContext()).a(aVar.g(), imageView, com.qq.reader.common.imageloader.a.a().j());
        imageView.setTag(aVar);
    }

    private void d() {
        new o(new com.qq.reader.plugin.o.a(this) {
            final /* synthetic */ PlugInFontsActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mHandler.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a(this.a.a.getIntent().getExtras(), false);
                    }
                });
            }

            public void a(Exception exception) {
            }

            public void b() {
            }
        }).a();
    }

    protected void onResume() {
        super.onResume();
        e();
    }

    private void e() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(this);
        }
    }

    protected void onDestroy() {
        if (this.l != null) {
            this.l.b();
        }
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((a) it.next()).v();
        }
    }

    public void a() {
        f();
    }

    public void b() {
        this.mHandler.post(new Runnable(this) {
            final /* synthetic */ PlugInFontsActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.k != null && this.a.k.f()) {
                    this.a.k.cancel();
                }
            }
        });
    }

    private void f() {
        if (this.i.equals(d.o(getApplicationContext()))) {
            setResult(0);
        } else {
            setResult(-1);
        }
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        f();
        return true;
    }

    private void a(Bundle bundle, boolean z) {
        if (bundle != null) {
            this.c = bundle.getString("PLUGIN_TYPE");
            a(k.b().a(this.c));
            this.a = new ArrayList();
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                f fVar = (f) m.c().a(getApplicationContext(), (l) it.next());
                fVar.j();
                this.a.add(fVar);
            }
        }
        if (this.e != null) {
            this.e.notifyDataSetChanged();
        }
        if (!z) {
            k.b();
            e();
        }
        this.i = d.o(getApplicationContext());
    }

    private l g() {
        l lVar = new l(d.B, "2", "系统字体", "android_system_font", "", "默认", "", d.D, "0", "", "1", "android_system_font", "android_system_font");
        lVar.b(4);
        return lVar;
    }

    private void a(ArrayList<l> arrayList) {
        this.b = new ArrayList();
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                this.b.add((l) arrayList.get(i));
            }
        }
        this.b.add(0, g());
    }

    private a b(String str) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.f().equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    public void a(String str) {
        if (this.k != null && this.k.f()) {
            this.k.cancel();
        }
        c(str);
    }

    private void c(String str) {
        a b = b(str);
        if (b != null) {
            switch (b.e()) {
                case 1:
                case 5:
                    com.qq.reader.common.monitor.j.a(15, 3);
                    e(str);
                    b.r();
                    return;
                case 2:
                case 3:
                    b.u();
                    return;
                case 4:
                    if (!d.o(getApplicationContext()).equalsIgnoreCase(str)) {
                        com.qq.reader.common.monitor.j.a(17, 3);
                        d.c(getApplicationContext(), str);
                        Iterator it = this.b.iterator();
                        while (it.hasNext()) {
                            l lVar = (l) it.next();
                            if (str.equals(lVar.i())) {
                                d.d(getApplicationContext(), lVar.l());
                                a(lVar, (int) R.string.font_changed_ok);
                            }
                        }
                        this.e.notifyDataSetChanged();
                        return;
                    }
                    return;
                case 8:
                    b.p();
                    return;
                default:
                    return;
            }
        }
    }

    private void b(l lVar) {
        if (com.qq.reader.common.utils.networkUtil.e.a(ReaderApplication.getApplicationImp())) {
            c(lVar.i());
        } else {
            af.a(ReaderApplication.getApplicationImp(), R.string.net_not_available, 0).a();
        }
    }

    private void i() {
        f = new HashMap();
        this.d = (GridView) findViewById(R.id.font_grid_view);
        this.e = new a(this, getApplicationContext());
        this.d.setAdapter(this.e);
    }

    public void a(String str, boolean z) {
        if (this.e != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (z || currentTimeMillis - j > 1000) {
                j = currentTimeMillis;
                this.e.notifyDataSetChanged();
            }
        }
    }

    private void a(l lVar, int i) {
        if (this.l == null) {
            this.l = af.a(getApplicationContext(), i, 0);
        } else {
            this.l.b(i);
        }
        this.l.a();
    }

    public void a(l lVar, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("id", lVar.i());
        bundle.putString("price", lVar.v());
        int i = 0;
        try {
            i = Integer.valueOf(lVar.v()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (com.qq.reader.common.login.c.c().g(this) < i) {
            bundle.putString("message", "下载本字体，需要支付" + lVar.v() + "书币");
            bundle.putString("chargeButtonName", "余额不足，充值并购买");
            showFragmentDialog(608, bundle);
            return;
        }
        showFragmentDialog(606, bundle);
    }

    public void a(l lVar) {
        startLogin();
        setLoginNextTask(new com.qq.reader.common.login.a(this) {
            final /* synthetic */ PlugInFontsActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.a(this.a.getIntent().getExtras(), false);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void a(l lVar, Bundle bundle) {
    }

    public void a(String str, String str2) {
        this.g.a(str2);
        this.g.a();
    }

    protected Dialog createDialog(int i, final Bundle bundle) {
        Dialog a = new com.qq.reader.view.AlertDialog.a(this).a();
        CharSequence string;
        switch (i) {
            case 606:
                a.setTitle(R.string.paypage_purchase);
                a.a("下载本字体，需要支付" + bundle.getString("price") + "书币");
                a.a(R.string.alert_dialog_buy, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ PlugInFontsActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.a(bundle);
                    }
                });
                a.b(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ PlugInFontsActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 607:
                string = bundle.getString("message");
                a.setTitle(R.string.comment_send_title);
                a.a(string);
                a.a(R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ PlugInFontsActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                a.a(-1, R.drawable.buy_book_dialog_confirm_bg);
                break;
            case 608:
                string = bundle.getString("message");
                CharSequence string2 = bundle.getString("chargeButtonName");
                a.setTitle(R.string.paypage_purchase);
                a.a(string);
                a.a(string2, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ PlugInFontsActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.b(bundle);
                    }
                });
                a.a(-1, R.drawable.buy_book_dialog_confirm_bg);
                break;
            default:
                f.b("PlugInFontsActivity", "Unknown dialog ID : " + i);
                break;
        }
        return a;
    }

    private void d(final String str) {
        g.a().a(new ReaderDBTask() {
            public void run() {
                super.run();
                k.b().a(str, 1);
            }
        });
    }

    private void a(final Bundle bundle) {
        final String string = bundle.getString("id");
        com.qq.reader.cservice.buy.b.b bVar = new com.qq.reader.cservice.buy.b.b();
        bVar.a(string);
        com.qq.reader.cservice.buy.b.c cVar = new com.qq.reader.cservice.buy.b.c(bVar, this);
        cVar.a(new com.qq.reader.cservice.buy.b.a(this) {
            final /* synthetic */ PlugInFontsActivity c;

            public void a(com.qq.reader.cservice.buy.b.b bVar) {
                this.c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.c.k();
                        this.a.c.c(string);
                        this.a.c.d(string);
                    }
                });
            }

            public void b(final com.qq.reader.cservice.buy.b.b bVar) {
                this.c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        this.b.c.k();
                        bundle.putString("message", bVar.b());
                        this.b.c.showFragmentDialog(607, bundle);
                    }
                });
            }

            public void c(final com.qq.reader.cservice.buy.b.b bVar) {
                this.c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        this.b.c.k();
                        bundle.putString("message", bVar.b());
                        bundle.putString("chargeButtonName", "充值并购买");
                        this.b.c.showFragmentDialog(608, bundle);
                    }
                });
            }
        });
        cVar.start();
        j();
    }

    private void b(final Bundle bundle) {
        setChargeNextTask(new com.qq.reader.common.charge.a(this) {
            final /* synthetic */ PlugInFontsActivity b;

            public void a() {
                this.b.a(bundle);
            }

            public void b() {
                bundle.putString("message", this.b.getResources().getString(R.string.paypage_charge));
                this.b.showFragmentDialog(608, bundle);
            }

            public void c() {
            }
        });
        JSPay jSPay = new JSPay(this);
        int i = 0;
        try {
            i = Integer.valueOf(bundle.getString("price")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jSPay.startCharge(this, i);
    }

    private void j() {
        try {
            if (this.h == null || !this.h.isShowing()) {
                this.h = ProgressDialog.show(this, "", "正在购买，请稍候...", true);
                this.h.setCanceledOnTouchOutside(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean k() {
        try {
            if (this.h != null && this.h.isShowing()) {
                this.h.cancel();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void e(String str) {
        switch (Integer.valueOf(str).intValue()) {
            case 2:
                com.qq.reader.common.monitor.j.a(91, 1);
                return;
            case 3:
                com.qq.reader.common.monitor.j.a(92, 1);
                return;
            case 4:
                com.qq.reader.common.monitor.j.a(93, 1);
                return;
            case 5:
                com.qq.reader.common.monitor.j.a(94, 1);
                return;
            case 6:
                com.qq.reader.common.monitor.j.a(95, 1);
                return;
            case 8:
                com.qq.reader.common.monitor.j.a(96, 1);
                return;
            case 9:
                com.qq.reader.common.monitor.j.a(97, 1);
                return;
            case 10:
                com.qq.reader.common.monitor.j.a(98, 1);
                return;
            case 15:
                com.qq.reader.common.monitor.j.a(99, 1);
                return;
            case 16:
                com.qq.reader.common.monitor.j.a(100, 1);
                return;
            case 18:
                com.qq.reader.common.monitor.j.a(101, 1);
                return;
            case 19:
                com.qq.reader.common.monitor.j.a(102, 1);
                return;
            case 20:
                com.qq.reader.common.monitor.j.a(103, 1);
                return;
            case 21:
                com.qq.reader.common.monitor.j.a(104, 1);
                return;
            case 22:
                com.qq.reader.common.monitor.j.a(105, 1);
                return;
            case 23:
                com.qq.reader.common.monitor.j.a(106, 1);
                return;
            case 24:
                com.qq.reader.common.monitor.j.a(107, 1);
                return;
            case 31:
                i.a("event_B109", null, ReaderApplication.getApplicationImp());
                return;
            case 32:
                i.a("event_B111", null, ReaderApplication.getApplicationImp());
                return;
            case 33:
                i.a("event_B115", null, ReaderApplication.getApplicationImp());
                return;
            case 34:
                i.a("event_B110", null, ReaderApplication.getApplicationImp());
                return;
            case 35:
                i.a("event_B114", null, ReaderApplication.getApplicationImp());
                return;
            case 36:
                i.a("event_B113", null, ReaderApplication.getApplicationImp());
                return;
            case 37:
                i.a("event_B112", null, ReaderApplication.getApplicationImp());
                return;
            default:
                return;
        }
    }
}
