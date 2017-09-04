package com.qq.reader.plugin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.plugin.audiobook.MusicActivity;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.b;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PlugInListActivity extends PluginBaseActivity implements OnItemClickListener {
    public static HashMap<String, Bitmap> e;
    public static boolean g = false;
    ListView a;
    a b;
    ArrayList<l> c;
    ArrayList<Boolean> d = new ArrayList();
    public final int f = 0;
    protected transient boolean h = false;
    af i;
    Handler j = new Handler(this) {
        final /* synthetic */ PlugInListActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 6107:
                case 6114:
                    this.a.h = false;
                    this.a.g();
                    PlugInListActivity.g = false;
                    return;
                case 6108:
                    this.a.g();
                    this.a.e();
                    this.a.j();
                    this.a.b.notifyDataSetChanged();
                    PlugInListActivity.g = false;
                    return;
                default:
                    return;
            }
        }
    };
    private Context k;
    private b l;
    private ProgressDialog m;
    private final int n = 501;
    private o o;

    public class a extends BaseAdapter {
        final /* synthetic */ PlugInListActivity a;
        private LayoutInflater b;

        private class a {
            Handler a;
            final /* synthetic */ a b;
            private ImageView c;
            private TextView d;
            private TextView e;
            private ImageView f;
            private boolean g;
            private String h;

            private a(a aVar) {
                this.b = aVar;
                this.g = false;
                this.h = "";
                this.a = new Handler(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void handleMessage(Message message) {
                        switch (message.what) {
                            case 6002:
                                try {
                                    this.a.b.a.b.notifyDataSetChanged();
                                    return;
                                } catch (Exception e) {
                                    f.a("PlugInListActivity", "inner_handler " + e.toString());
                                    return;
                                }
                            default:
                                return;
                        }
                    }
                };
            }

            private boolean a() {
                return this.g;
            }

            private void a(String str, String str2, Context context) {
                this.g = true;
                this.h = str2;
                ReaderTask readerDownloadTask = new ReaderDownloadTask(context, this.h, str);
                readerDownloadTask.setListener(new com.qq.reader.common.readertask.ordinal.b(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            Message obtain = Message.obtain();
                            obtain.what = 6002;
                            this.a.a.sendMessage(obtain);
                        }
                        this.a.g = false;
                    }
                });
                g.a().a(readerDownloadTask);
            }
        }

        public a(PlugInListActivity plugInListActivity, Context context) {
            this.a = plugInListActivity;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            return this.a.c.size();
        }

        public Object getItem(int i) {
            return this.a.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            l lVar;
            a fVar;
            l lVar2 = (l) this.a.c.get(i);
            Boolean bool = (Boolean) this.a.d.get(i);
            if (view == null) {
                view = this.b.inflate(R.layout.plug_in_list_item, null);
                aVar = new a();
                aVar.c = (ImageView) view.findViewById(R.id.plugin_icon);
                aVar.d = (TextView) view.findViewById(R.id.plugin_name);
                aVar.e = (TextView) view.findViewById(R.id.plugin_installed);
                aVar.f = (ImageView) view.findViewById(R.id.plugin_new_icon);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            CharSequence l = lVar2.l();
            String r = lVar2.r();
            if (new File(r).exists()) {
                if (PlugInListActivity.e.get(r) == null || PlugInListActivity.e.get(r) == null) {
                    Bitmap j = ao.j(r);
                    if (j != null) {
                        PlugInListActivity.e.put(r, j);
                        aVar.c.setBackgroundDrawable(new BitmapDrawable((Bitmap) PlugInListActivity.e.get(r)));
                    }
                } else {
                    aVar.c.setBackgroundDrawable(new BitmapDrawable((Bitmap) PlugInListActivity.e.get(r)));
                }
            } else if (!aVar.a()) {
                aVar.a(lVar2.g(), r, this.a.getApplicationContext());
            }
            ArrayList a = k.b().a(lVar2.i());
            if (a.size() > 0) {
                lVar = (l) a.get(0);
            } else {
                lVar = null;
            }
            if (lVar != null) {
                String k = lVar.k();
                h b = k.b();
                if ("2".equals(k)) {
                    fVar = new f(this.a.getApplicationContext(), lVar, b);
                } else if ("4".equals(k) || "5".equals(k)) {
                    fVar = new i(this.a.getApplicationContext(), lVar, b);
                } else if (Constants.VIA_SHARE_TYPE_INFO.equals(k) || "8".equals(k)) {
                    fVar = new d(this.a.getApplicationContext(), lVar, b);
                } else if ("7".equals(k)) {
                    fVar = new d(this.a.getApplicationContext(), lVar, b);
                } else {
                    fVar = new b(this.a.getApplicationContext(), lVar, b);
                }
            } else {
                fVar = null;
            }
            if (fVar == null || !fVar.l()) {
                aVar.e.setVisibility(4);
            } else {
                aVar.e.setVisibility(0);
            }
            aVar.d.setText(l);
            if (bool.booleanValue()) {
                aVar.f.setVisibility(0);
            } else {
                aVar.f.setVisibility(4);
            }
            return view;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.plug_in_list);
        this.k = getApplicationContext();
        ((TextView) findViewById(R.id.profile_header_title)).setText("我的插件");
        e = new HashMap();
        e();
        this.a = (ListView) findViewById(R.id.plug_in_list);
        this.b = new a(this, getApplicationContext());
        this.a.setAdapter(this.b);
        this.a.setOnItemClickListener(this);
        this.i = af.a(getApplicationContext(), "请停止所有插件下载，再执行更新操作", 0);
        h();
        String s = d.s(this.k);
        String r = d.r(this.k);
        if (!s.equals("PLUGIN_DEFAULT_SERIES") && !s.equals(r)) {
            d.f(this.k, r);
            showDialog(501);
        }
    }

    protected void onResume() {
        super.onResume();
        j();
        this.b.notifyDataSetChanged();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!this.isReady2Show) {
            return false;
        }
        c();
        return true;
    }

    public void onWindowFocusChanged(boolean z) {
        this.isReady2Show = true;
        super.onWindowFocusChanged(z);
    }

    public void a() {
        finish();
    }

    protected boolean a(int i, Bundle bundle) {
        switch (i) {
            case 0:
                i();
                return true;
            default:
                return false;
        }
    }

    private void i() {
        int d;
        Iterator it = k.b().e().iterator();
        while (it.hasNext()) {
            d = ((l) it.next()).d();
            if (d != 2) {
                if (d == 3) {
                }
            }
            Object obj = null;
        }
        d = 1;
        if (obj != null) {
            b();
        } else {
            this.i.a();
        }
    }

    public void b() {
        g = true;
        this.o = new o(new com.qq.reader.plugin.o.a(this) {
            final /* synthetic */ PlugInListActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.j.sendEmptyMessage(6108);
            }

            public void a(Exception exception) {
                this.a.a(exception);
            }

            public void b() {
                this.a.j.sendEmptyMessage(6107);
            }
        });
        this.o.a();
        f();
    }

    public void c() {
        d().f_();
    }

    public com.qq.reader.view.linearmenu.a d() {
        this.l = new b(this);
        this.l.a(0, "更新", null);
        this.l.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ PlugInListActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                return this.a.a(i, bundle);
            }
        });
        this.l.a(new OnCancelListener(this) {
            final /* synthetic */ PlugInListActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getWindow().closeAllPanels();
            }
        });
        return this.l;
    }

    public void e() {
        k b = k.b();
        this.c = b.d();
        if (this.c.size() == 0) {
            File file = new File(com.qq.reader.common.c.a.p + "plugin.db");
            if (file.exists()) {
                file.delete();
            }
            b.a(this.k);
            this.c = b.d();
        }
        for (int i = 0; i < this.c.size(); i++) {
            l lVar = (l) this.c.get(i);
            f.b("PlugInListActivity", "pdata = " + lVar.l());
            if (lVar.i().equals("3")) {
                this.c.remove(i);
            }
        }
    }

    private void j() {
        if (this.c != null) {
            this.d.clear();
            ArrayList e = k.b().e();
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                l lVar = (l) it.next();
                if (!lVar.i().equals("3")) {
                    boolean z;
                    String i = lVar.i();
                    Iterator it2 = e.iterator();
                    while (it2.hasNext()) {
                        lVar = (l) it2.next();
                        if (lVar.k().equals(i) && m.c().a(this.k, lVar).o()) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                    this.d.add(Boolean.valueOf(z));
                }
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        l lVar = (l) this.c.get(i);
        String i2 = lVar.i();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (i2.equals("4")) {
            ArrayList a = k.b().a(lVar.i());
            if (a.size() > 0) {
                i iVar = new i(getApplicationContext(), (l) a.get(0), k.b());
                if (iVar == null || !iVar.l()) {
                    intent.setClass(this, PlugInDefaultActivity.class);
                } else {
                    intent.setClass(this, MusicActivity.class);
                }
            }
        } else if (i2.equals("2")) {
            j.a(14, 3);
            bundle.putInt("fromActivity", 11);
            intent.setClass(this, PlugInFontsActivity.class);
        } else {
            if ("1".equals(i2)) {
                j.a(11, 3);
            } else if (!"3".equals(i2)) {
                if (Constants.VIA_SHARE_TYPE_INFO.equals(i2)) {
                    j.a(40, 3);
                } else if ("7".equals(i2)) {
                    j.a(35, 3);
                } else if ("5".equals(i2)) {
                    j.a(18, 3);
                } else if ("8".equals(i2)) {
                    j.a(38, 3);
                }
            }
            intent.setClass(this, PlugInDefaultActivity.class);
        }
        bundle.putString("PLUGIN_TYPE", lVar.i());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void a(Exception exception) {
        this.j.sendEmptyMessage(6114);
    }

    public void f() {
        if (this.m == null) {
            this.m = ProgressDialog.show(this, null, getResources().getString(R.string.progress_plugin_get_message), true);
            this.m.setCancelable(true);
            this.m.setOnKeyListener(new OnKeyListener(this) {
                final /* synthetic */ PlugInListActivity a;

                {
                    this.a = r1;
                }

                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    switch (i) {
                        case 4:
                            this.a.progressCancel();
                            break;
                    }
                    return false;
                }
            });
            return;
        }
        this.m.show();
    }

    public void progressCancel() {
        if (this.m != null && this.m.isShowing()) {
            this.m.setMessage(getResources().getString(R.string.progress_plugin_cancel_message));
            this.h = true;
            if (this.o != null) {
                this.o.b();
            }
        }
    }

    public void g() {
        if (this.m != null && this.m.isShowing()) {
            this.m.cancel();
            this.m = null;
        }
    }

    protected void onDestroy() {
        try {
            e.clear();
        } catch (Exception e) {
            f.a("PlugInListActivity", "onDestroy " + e.toString());
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        a();
        return true;
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 501:
                return new com.qq.reader.view.AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a("更新提示").b("我的插件内容有更新，是否刷新？").a(R.string.alert_dialog_ok, new OnClickListener(this) {
                    final /* synthetic */ PlugInListActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.i();
                    }
                }).b(R.string.alert_dialog_cancel, new OnClickListener(this) {
                    final /* synthetic */ PlugInListActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
            default:
                return super.onCreateDialog(i);
        }
    }
}
