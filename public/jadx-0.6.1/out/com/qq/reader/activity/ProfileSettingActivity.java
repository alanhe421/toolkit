package com.qq.reader.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.utils.aa;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.cservice.adv.c;
import com.qq.reader.cservice.protocol.UserProtocolRedPointManger;
import com.qq.reader.plugin.PlugInListActivity;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.tesla.soload.SoLoadCore;
import java.util.ArrayList;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class ProfileSettingActivity extends ReaderBaseActivity implements OnItemClickListener {
    private Context a;
    private ListView b = null;
    private a c = null;
    private ImageView d = null;
    private TextView e = null;
    private Dialog f = null;
    private long g = -1;

    public class a extends BaseAdapter {
        final /* synthetic */ ProfileSettingActivity a;
        private Context b;
        private List<b> c = new ArrayList();

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(ProfileSettingActivity profileSettingActivity, Context context, List<b> list) {
            this.a = profileSettingActivity;
            this.b = context;
            this.c.addAll(list);
        }

        public int getCount() {
            return this.c.size();
        }

        public b a(int i) {
            return (b) this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemViewType(int i) {
            return a(i).b();
        }

        public int getViewTypeCount() {
            return 2;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.b.getSystemService("layout_inflater");
                switch (getItemViewType(i)) {
                    case 0:
                        view = layoutInflater.inflate(R.layout.profile_setting_item, viewGroup, false);
                        break;
                    case 1:
                        view = layoutInflater.inflate(R.layout.profile_setting_group_divider, viewGroup, false);
                        break;
                }
            }
            if (getItemViewType(i) == 0) {
                b a = a(i);
                ((TextView) ap.a(view, R.id.profile_setting_list_item_text)).setText(a.c());
                View a2 = ap.a(view, R.id.profile_setting_list_redtip);
                TextView textView = (TextView) ap.a(view, R.id.profile_setting_list_tip_text);
                if (!a.a()) {
                    a2.setVisibility(8);
                }
                boolean z;
                switch (a.d()) {
                    case 10:
                        if (d.t) {
                            a2.setVisibility(0);
                        } else {
                            a2.setVisibility(8);
                        }
                        textView.setText("");
                        break;
                    case 11:
                        if (c.a((Object) "TYPE_SKIN_LIST_UPDATE")) {
                            a2.setVisibility(0);
                        } else {
                            a2.setVisibility(8);
                        }
                        textView.setText("");
                        break;
                    case 12:
                        if (com.qq.reader.appconfig.a.c.a != 0 && d.a) {
                            a2.setVisibility(0);
                            if (com.qq.reader.appconfig.a.c.c == null) {
                                textView.setText("新版本");
                                break;
                            }
                            String[] split = com.qq.reader.appconfig.a.c.c.split("_");
                            if (split.length < 2) {
                                textView.setText("新版本");
                                break;
                            }
                            int i2;
                            String[] split2 = split[1].split("\\.");
                            try {
                                if (split2.length < 3) {
                                    i2 = 1;
                                } else if (Integer.valueOf(split2[2]).intValue() == 0) {
                                    i2 = 1;
                                } else {
                                    z = false;
                                }
                            } catch (NumberFormatException e) {
                                i2 = 1;
                            }
                            if (split2.length < 3) {
                                if (split2.length != 2) {
                                    textView.setText("新版本");
                                    break;
                                }
                                textView.setText("新版V" + split2[0] + "." + split2[1]);
                                break;
                            } else if (i2 == 0) {
                                textView.setText("新版V" + split2[0] + "." + split2[1] + "." + split2[2]);
                                break;
                            } else {
                                textView.setText("新版V" + split2[0] + "." + split2[1]);
                                break;
                            }
                        }
                        a2.setVisibility(8);
                        textView.setText("");
                        break;
                        break;
                    case 16:
                        z = d.f(this.b);
                        if (UserProtocolRedPointManger.a(this.b.getApplicationContext()).d() && z) {
                            a2.setVisibility(0);
                        } else {
                            a2.setVisibility(8);
                        }
                        textView.setText("");
                        break;
                    case 17:
                        if (this.a.g >= 0) {
                            if (this.a.g != 0) {
                                textView.setText(ab.a(this.a.g));
                                break;
                            }
                            textView.setText("");
                            break;
                        }
                        textView.setText("正在计算");
                        break;
                }
            }
            return view;
        }
    }

    private class b {
        final /* synthetic */ ProfileSettingActivity a;
        private int b;
        private String c;
        private int d;
        private boolean e;

        public b(ProfileSettingActivity profileSettingActivity, int i) {
            this(profileSettingActivity, i, null, -1, false);
        }

        public b(ProfileSettingActivity profileSettingActivity, int i, String str, int i2) {
            this(profileSettingActivity, i, str, i2, false);
        }

        public b(ProfileSettingActivity profileSettingActivity, int i, String str, int i2, boolean z) {
            this.a = profileSettingActivity;
            this.b = i;
            this.d = i2;
            this.c = str;
            this.e = z;
        }

        public boolean a() {
            return this.e;
        }

        public int b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public int d() {
            return this.d;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = getApplicationContext();
        setContentView(R.layout.profile_setting_layout);
        this.b = (ListView) findViewById(R.id.profile_setting_list);
        View inflate = getLayoutInflater().inflate(R.layout.profile_setting_footer, null);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ProfileSettingActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (com.qq.reader.common.login.c.b()) {
                    this.a.a(1000, "退出当前账号?");
                }
            }
        });
        if (com.qq.reader.common.login.c.b()) {
            this.b.addFooterView(inflate);
        }
        this.c = new a(this, this.a, a());
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(this);
        this.d = (ImageView) findViewById(R.id.profile_header_left_back);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ProfileSettingActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.e = (TextView) findViewById(R.id.profile_header_title);
        this.e.setText("设置");
        g.a().a(new ReaderIOTask() {
            public void run() {
                super.run();
                Message obtainMessage = ProfileSettingActivity.this.mHandler.obtainMessage();
                obtainMessage.what = 10000200;
                try {
                    obtainMessage.obj = Long.valueOf(aa.a(false));
                } catch (Exception e) {
                    obtainMessage.obj = Long.valueOf(0);
                }
                ProfileSettingActivity.this.mHandler.sendMessage(obtainMessage);
            }
        });
    }

    private List<b> a() {
        List<b> arrayList = new ArrayList();
        arrayList.add(new b(this, 1));
        arrayList.add(new b(this, 0, getString(R.string.my_plugin), 10, true));
        arrayList.add(new b(this, 0, getString(R.string.my_theme), 11));
        arrayList.add(new b(this, 0, getString(R.string.redeemcode), 19));
        arrayList.add(new b(this, 1));
        arrayList.add(new b(this, 0, getString(R.string.app_update), 12, true));
        arrayList.add(new b(this, 0, getString(R.string.star_reader), 13));
        arrayList.add(new b(this, 0, getString(R.string.help_center), 14));
        arrayList.add(new b(this, 0, getString(R.string.feedback), 15));
        arrayList.add(new b(this, 0, String.format(getString(R.string.about), new Object[]{getResources().getString(R.string.app_name)}), 16, true));
        arrayList.add(new b(this, 1));
        arrayList.add(new b(this, 0, getString(R.string.clear_cache), 17));
        if (!com.qq.reader.b.a.a.b()) {
            arrayList.add(new b(this, 0, getString(R.string.app_recommend), 18));
        }
        return arrayList;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 6116:
                d.t = false;
                this.c.notifyDataSetInvalidated();
                break;
            case 10000200:
                this.g = ((Long) message.obj).longValue();
                this.c.notifyDataSetChanged();
                return true;
            case 10000201:
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "缓存已清除", 0).a();
                this.g = ((Long) message.obj).longValue();
                this.c.notifyDataSetChanged();
                return true;
        }
        return super.handleMessageImp(message);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.b.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.c.getCount()) {
            switch (this.c.a(i).d()) {
                case 10:
                    b();
                    j.a(10, 3);
                    i.a("event_D11", null, this.a);
                    return;
                case 11:
                    h();
                    i.a("event_A162", null, this.a);
                    return;
                case 12:
                    if (com.qq.reader.common.protocol.c.a(this.a) && d.a) {
                        d.a = false;
                    }
                    c();
                    j.a(55, 3);
                    i.a("event_D56", null, this.a);
                    this.c.notifyDataSetChanged();
                    return;
                case 13:
                    f();
                    j.a(1, 3);
                    return;
                case 14:
                    j();
                    j.a(56, 3);
                    i.a("event_D57", null, this.a);
                    return;
                case 15:
                    g();
                    j.a(2, 3);
                    return;
                case 16:
                    i();
                    j.a(58, 3);
                    i.a("event_D59", null, this.a);
                    return;
                case 17:
                    this.g = 0;
                    this.c.notifyDataSetChanged();
                    o.n(this, null);
                    i.a("event_Z39", null, this.a);
                    return;
                case 18:
                    d();
                    return;
                case 19:
                    e();
                    return;
                default:
                    return;
            }
        }
    }

    private void b() {
        if (d.t) {
            getHandler().sendEmptyMessage(6116);
        }
        Intent intent = new Intent();
        intent.setClass(this.a, PlugInListActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void c() {
        checkUpdate(false, false);
    }

    private void d() {
        Intent intent = new Intent();
        intent.setClass(this.a, WebBrowserForContents.class);
        intent.putExtra("com.qq.reader.WebContent", "/applist.html?");
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
        j.a(44, 3);
    }

    private void e() {
        Intent intent = new Intent();
        intent.setClass(this.a, WebBrowserForContents.class);
        intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
        intent.putExtra("com.qq.reader.WebContent", "http://cdk.book.qq.com/");
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void f() {
        Uri parse = Uri.parse("market://details?id=" + "com.qq.reader");
        if (parse == null) {
            g();
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(parse);
        if (a(intent)) {
            startActivity(intent);
        } else {
            g();
        }
    }

    private boolean a(Intent intent) {
        return getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private void g() {
        o.m(this, null);
    }

    private void h() {
        c.a((Object) "TYPE_SKIN_LIST_UPDATE", false);
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
        o.g((Activity) this, null);
    }

    private void i() {
        d.b((Context) this, false);
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
        Intent intent = new Intent();
        intent.setClass(this.a, UserProtocolActivity.class);
        intent.putExtra("com.qq.reader.WebContent", e.cZ);
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        startActivity(intent);
    }

    private void j() {
        Intent intent = new Intent();
        intent.setClass(this.a, WebBrowserForContents.class);
        intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
        intent.putExtra("com.qq.reader.WebContent", "helpIndex.html");
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void a(int i, String str) {
        switch (i) {
            case 1000:
                this.f = new com.qq.reader.view.AlertDialog.a(this).c(17301543).a((CharSequence) "提示").b((CharSequence) str).a((int) R.string.dialog_exit_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ProfileSettingActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        j.a(0, 3);
                        i.a("event_D1", null, ReaderApplication.getApplicationImp());
                        String c = com.qq.reader.common.login.c.c().c();
                        com.qq.reader.common.login.c.a();
                        Intent intent = new Intent();
                        intent.setAction("com.qq.reader.loginout");
                        intent.putExtra("userId", c);
                        this.a.a.sendBroadcast(intent);
                        this.a.finish();
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ ProfileSettingActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                this.f.setCanceledOnTouchOutside(true);
                break;
        }
        if (this.f != null) {
            this.f.show();
        }
    }
}
