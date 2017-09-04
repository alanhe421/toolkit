package com.qq.reader.plugin;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.activity.PluginBrigeActivity;
import com.qq.reader.b;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.view.AlertDialog.a;
import com.qq.reader.view.af;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import format.archive.ArchiveFileBrowser;
import format.epub.common.utils.f;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import tencent.tls.platform.SigType;

public class PlugInDefaultActivity extends PluginBaseActivity implements g {
    a a;
    String b = "";
    String c = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    String d;
    String e;
    TextView f;
    TextView g;
    TextView h;
    ProgressBar i;
    Button j;
    ImageView k;
    View l;
    k m;
    l n;
    af o;
    Handler p = new 9(this);
    private SoftReference<Bitmap> s;
    private final int t = ErrorCode.INFO_CAN_NOT_LOAD_TBS;
    private Intent u;
    private Runnable v = new 6(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.plug_in_new_default);
        this.m = k.b();
        this.f = (TextView) findViewById(R.id.profile_header_title);
        this.g = (TextView) findViewById(R.id.puglin_info);
        this.k = (ImageView) findViewById(R.id.plugin_image);
        this.l = findViewById(R.id.plugin_install_info);
        this.h = (TextView) findViewById(R.id.plugin_install_info_text);
        this.i = (ProgressBar) findViewById(R.id.plugin_install_info_progress);
        this.j = (Button) findViewById(R.id.profile_header_right_button);
        this.j.setOnClickListener(new 1(this));
        h();
        this.o = af.a(getApplicationContext(), (CharSequence) "", 0);
        this.u = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.c = extras.getString("PLUGIN_TYPE");
            ArrayList a = this.m.a(this.c);
            if (a.size() > 0) {
                this.n = (l) a.get(0);
            }
            if (this.n == null) {
                finish();
            } else {
                this.a = b(this.n);
            }
        }
    }

    public void a() {
        finish();
    }

    protected void onResume() {
        super.onResume();
        if (this.a != null) {
            b();
            f();
            this.a.a((g) this);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.a == null) {
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.a == null) {
        }
    }

    private a b(l lVar) {
        return m.c().a(getApplicationContext(), lVar);
    }

    private void b() {
        this.a.j();
        this.b = this.n.i();
        this.d = this.n.l();
        this.e = this.n.n();
        this.f.setText(this.n.l());
        this.g.setText(this.n.n());
        if (this.n.r().length() > 0) {
            if (this.s == null || this.s.get() == null) {
                this.s = new SoftReference(ao.j(this.n.s()));
            }
            if (this.s.get() != null) {
                this.k.setImageBitmap((Bitmap) this.s.get());
            } else if (!this.a.m()) {
                this.a.a(true);
                ReaderTask readerDownloadTask = new ReaderDownloadTask(getApplicationContext(), this.n.s(), this.n.h());
                readerDownloadTask.setListener(new 3(this));
                g.a().a(readerDownloadTask);
            }
        }
        f();
        ((RelativeLayout) findViewById(R.id.plugin_install_info)).setOnClickListener(new 4(this));
    }

    private void c() {
        if (this.a != null) {
            switch (this.a.e()) {
                case 1:
                    if ("1".equals(this.c)) {
                        j.a(12, 3);
                    } else if (!("3".equals(this.c) || "4".equals(this.c))) {
                        if (Constants.VIA_SHARE_TYPE_INFO.equals(this.c)) {
                            j.a(41, 3);
                        } else if ("5".equals(this.c)) {
                            j.a(19, 3);
                        } else if ("7".equals(this.c)) {
                            j.a(36, 3);
                        } else if ("8".equals(this.c)) {
                            j.a(39, 3);
                        }
                    }
                    this.a.r();
                    return;
                case 2:
                case 3:
                    this.a.u();
                    return;
                case 4:
                    if (this.n.i().equals("30")) {
                        try {
                            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.qq.qcloud");
                            if (launchIntentForPackage != null) {
                                launchIntentForPackage.addFlags(SigType.TLS);
                                getApplicationContext().startActivity(launchIntentForPackage);
                                j.a(43, 3);
                                return;
                            }
                            this.a.p();
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            this.a.p();
                            return;
                        }
                    }
                    if (this.n.i().equals("1")) {
                        j.a(13, 3);
                    } else if (this.n.i().equals(Constants.VIA_REPORT_TYPE_MAKE_FRIEND)) {
                        j.a(20, 3);
                    } else if (this.n.i().equals("29")) {
                        j.a(37, 3);
                    } else if (this.n.i().equals("25")) {
                        j.a(42, 3);
                    }
                    showDialog(ErrorCode.INFO_CAN_NOT_LOAD_TBS);
                    return;
                case 5:
                    if ("1".equals(this.c)) {
                        j.a(12, 3);
                    } else if (!("3".equals(this.c) || "4".equals(this.c))) {
                        if ("8".equals(this.c)) {
                            j.a(39, 3);
                        } else if ("5".equals(this.c)) {
                            j.a(19, 3);
                        } else if ("7".equals(this.c)) {
                            j.a(36, 3);
                        } else if (Constants.VIA_SHARE_TYPE_INFO.equals(this.c)) {
                            j.a(41, 3);
                        }
                    }
                    this.a.r();
                    return;
                case 6:
                    f.a(new 5(this), this, "正在安装，请稍候...");
                    return;
                case 7:
                    this.a.k();
                    this.a.r();
                    return;
                case 8:
                    this.a.p();
                    return;
                default:
                    return;
            }
        }
    }

    private void d() {
        Bundle extras = this.u.getExtras();
        String string = extras.getString("filename");
        String string2 = extras.getString("filepath");
        if (string != null && string2 != null && string.length() != 0 && string2.length() != 0) {
            if (this.c.equalsIgnoreCase(Constants.VIA_SHARE_TYPE_INFO)) {
                this.mHandler.postDelayed(this.v, 200);
            } else {
                new a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.exit).b("安装完成，打开《" + string + "》?").a((int) R.string.alert_dialog_ok, new 8(this)).b((int) R.string.alert_dialog_cancel, new 7(this)).a().show();
            }
        }
    }

    private void e() {
        Bundle extras = this.u.getExtras();
        String string = extras.getString("filename");
        String string2 = extras.getString("filepath");
        if (string != null && string2 != null && string.length() != 0 && string2.length() != 0) {
            if (this.c.equalsIgnoreCase("1")) {
                this.u.setClass(this, PluginBrigeActivity.class);
                this.u.putExtra("pluginname", "pdf");
                this.u.putExtra("uri", string2);
                startActivity(this.u);
                i.a("event_B55", null, this);
            } else if (this.c.equalsIgnoreCase("5")) {
                this.u.setClass(this, ArchiveFileBrowser.class);
                startActivity(this.u);
                i.a("event_B55", null, this);
            } else if (this.c.equalsIgnoreCase(Constants.VIA_SHARE_TYPE_INFO)) {
                b.a(this.u, this);
                i.a("event_B55", null, this);
            }
        }
    }

    private void f() {
        int e = this.a.e();
        boolean s = this.a.s();
        this.j.setVisibility(8);
        this.l.setBackgroundResource(R.drawable.selector_round_blue_button);
        CharSequence charSequence;
        switch (e) {
            case 1:
            case 5:
            case 6:
                if (e == 6) {
                    this.h.setText("安装");
                    this.l.setBackgroundResource(R.drawable.selector_orange_button);
                    return;
                } else if (n.a(this.b)) {
                    this.h.setText("安装");
                    this.l.setBackgroundResource(R.drawable.selector_orange_button);
                    return;
                } else {
                    String trim = this.n.o().trim();
                    if (trim == null || trim.length() <= 0) {
                        this.h.setText("安装");
                        this.l.setBackgroundResource(R.drawable.selector_orange_button);
                        return;
                    }
                    trim = ao.a(this.a.d(), this.a.c());
                    if (this.a.d() > 0) {
                        charSequence = "继续下载  " + trim;
                    } else {
                        charSequence = "下载 (" + this.n.o() + ") ";
                    }
                    this.h.setText(charSequence);
                    if (this.a.c() != 0) {
                        this.i.setProgress((int) ((this.a.d() * 100) / this.a.c()));
                        return;
                    }
                    return;
                }
            case 2:
            case 3:
                if (s) {
                    this.h.setText("下载 (" + this.n.o() + ")");
                    this.i.setVisibility(8);
                    return;
                }
                this.i.setVisibility(0);
                charSequence = ao.a(this.a.d(), this.a.c());
                if (charSequence.equalsIgnoreCase("正在安装...")) {
                    this.h.setText(charSequence);
                    this.i.setVisibility(8);
                    return;
                }
                this.h.setText("正在下载..." + charSequence);
                if (this.a.c() != 0) {
                    this.i.setProgress((int) ((this.a.d() * 100) / this.a.c()));
                    return;
                }
                return;
            case 7:
                this.j.setText("更新");
                this.j.setVisibility(0);
                this.h.setText("卸载");
                this.h.setVisibility(0);
                this.i.setVisibility(8);
                return;
            case 8:
                this.h.setText("重试");
                this.i.setVisibility(8);
                return;
            default:
                if (this.n.i().equals("30")) {
                    this.h.setText("打开");
                } else {
                    this.h.setText("卸载");
                }
                this.h.setVisibility(0);
                this.i.setVisibility(8);
                return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                finish();
                break;
        }
        return false;
    }

    public void a(String str, boolean z) {
        f();
        if (this.a.e() == 4) {
            d();
        }
    }

    public void a(l lVar, String str) {
    }

    public void a(l lVar) {
        startLogin();
    }

    public void a(l lVar, Bundle bundle) {
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.s == null || this.s.get() == null || ((Bitmap) this.s.get()).isRecycled())) {
            ((Bitmap) this.s.get()).recycle();
        }
        if (this.a != null) {
            this.a.v();
        }
        this.mHandler.removeCallbacks(this.v);
    }

    public void a(String str, String str2) {
        this.o.a((CharSequence) str2);
        this.o.a();
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case ErrorCode.INFO_CAN_NOT_LOAD_TBS /*405*/:
                if (this.a != null) {
                    return new a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.exit).b("确认卸载" + this.a.b().l() + "?").a((int) R.string.alert_dialog_ok, new 2(this)).b((int) R.string.alert_dialog_cancel, new 10(this)).a();
                }
                break;
        }
        return super.onCreateDialog(i);
    }
}
