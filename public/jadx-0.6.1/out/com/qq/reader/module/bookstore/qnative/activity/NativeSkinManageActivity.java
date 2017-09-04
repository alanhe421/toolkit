package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.mypreference.StickyGridHeadersGridView;
import com.qq.reader.module.feed.mypreference.e;
import com.qq.reader.module.readpage.q;
import com.qq.reader.plugin.w;
import com.qq.reader.plugin.x;
import com.qq.reader.view.AlertDialog;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NativeSkinManageActivity extends ReaderBaseActivity implements com.qq.reader.plugin.w.b {
    private StickyGridHeadersGridView a;
    private View b;
    private View c;
    private View d;
    private a e;
    private Toast f;

    private class a extends BaseAdapter implements e {
        final /* synthetic */ NativeSkinManageActivity a;
        private ArrayList<x> b;

        public a(NativeSkinManageActivity nativeSkinManageActivity, ArrayList<x> arrayList) {
            this.a = nativeSkinManageActivity;
            this.b = arrayList;
        }

        public void a(ArrayList<x> arrayList) {
            this.b = arrayList;
        }

        public x a(String str) {
            if (!(str == null || this.b == null)) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    x xVar = (x) it.next();
                    if (str.equals(xVar.i())) {
                        return xVar;
                    }
                }
            }
            return null;
        }

        public int getCount() {
            if (this.b != null) {
                return this.b.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            if (this.b == null || i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(ReaderApplication.getApplicationImp(), R.layout.theme_list_grid_ui, null);
                view.setLayoutParams(new LayoutParams(-1, -1));
                b bVar = new b();
                bVar.a = (ImageView) view.findViewById(R.id.img_theme_pic);
                bVar.b = (TextView) view.findViewById(R.id.tv_theme_name);
                bVar.c = (TextView) view.findViewById(R.id.tv_theme_size);
                bVar.d = (TextView) view.findViewById(R.id.tv_percent);
                bVar.e = (TextView) view.findViewById(R.id.tv_state);
                bVar.g = view.findViewById(R.id.ll_btn);
                bVar.f = (ProgressBar) view.findViewById(R.id.pb_percent);
                bVar.h = (ImageView) view.findViewById(R.id.free_tag);
                bVar.i = (ImageView) view.findViewById(R.id.img_delete);
                view.setTag(bVar);
            }
            b bVar2 = (b) view.getTag();
            if (!(bVar2 == null || this.b == null || i >= this.b.size())) {
                final x xVar = (x) this.b.get(i);
                if (xVar != null) {
                    bVar2.b.setText(xVar.l());
                    bVar2.c.setText(xVar.o());
                    if (xVar.u()) {
                        bVar2.h.setVisibility(0);
                    } else {
                        bVar2.h.setVisibility(8);
                    }
                    if (Constants.DEFAULT_UIN.equals(xVar.i())) {
                        bVar2.a.setImageResource(R.drawable.default_skin_img);
                    } else {
                        c.a(this.a.getContext()).a(xVar.h(), bVar2.a, com.qq.reader.common.imageloader.a.a().a((int) R.drawable.comment_pic_default_bg));
                    }
                    bVar2.a.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ a b;

                        public void onClick(View view) {
                            if (com.qq.reader.common.login.c.b()) {
                                if (xVar == null || !Constants.DEFAULT_UIN.equals(xVar.i())) {
                                    Map hashMap = new HashMap();
                                    hashMap.put(s.ORIGIN, xVar.i());
                                    i.a("event_A170", hashMap, ReaderApplication.getApplicationImp());
                                } else {
                                    i.a("event_A169", null, ReaderApplication.getApplicationImp());
                                }
                                o.b(this.b.a, xVar.i(), null);
                                return;
                            }
                            this.b.a.mHandler.sendEmptyMessage(10000406);
                        }
                    });
                    this.a.a(xVar, bVar2);
                    bVar2.i.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ a b;

                        public void onClick(View view) {
                            i.a("event_B185", null, ReaderApplication.getApplicationImp());
                            this.b.a.c(xVar);
                        }
                    });
                    bVar2.g.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ a b;

                        public void onClick(View view) {
                            if (com.qq.reader.common.login.c.b()) {
                                switch (xVar.d()) {
                                    case 0:
                                    case 1:
                                    case 6:
                                        w.b().a(xVar);
                                        if (xVar != null) {
                                            Map hashMap = new HashMap();
                                            hashMap.put(s.ORIGIN, xVar.i());
                                            i.a("event_A164", hashMap, ReaderApplication.getApplicationImp());
                                        }
                                        this.b.a.e.notifyDataSetChanged();
                                        return;
                                    case 2:
                                    case 3:
                                        w.b().a(xVar);
                                        this.b.a.e.notifyDataSetChanged();
                                        return;
                                    case 4:
                                        this.b.a.b(xVar);
                                        return;
                                    case 7:
                                        if (ao.a(xVar)) {
                                            w.b().e(Constants.DEFAULT_UIN);
                                            ao.v(Constants.DEFAULT_UIN);
                                            this.b.a.e.notifyDataSetChanged();
                                        }
                                        w.b().a(xVar);
                                        return;
                                    default:
                                        return;
                                }
                            }
                            this.b.a.mHandler.sendEmptyMessage(10000406);
                        }
                    });
                }
            }
            return view;
        }

        public int a(int i) {
            return 0;
        }

        public int a() {
            return 0;
        }

        public View a(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }

    public static class b {
        public ImageView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public TextView e;
        public ProgressBar f;
        public View g;
        public ImageView h;
        public ImageView i;
    }

    private void a(x xVar) {
        if (xVar == null || Constants.DEFAULT_UIN.equals(xVar.i())) {
            d.j(ReaderApplication.getApplicationImp(), 0);
            i.a("event_A163", null, ReaderApplication.getApplicationImp());
            return;
        }
        d.j(ReaderApplication.getApplicationImp(), 7);
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, xVar.i());
        i.a("event_A186", hashMap, ReaderApplication.getApplicationImp());
    }

    private void b(x xVar) {
        w.b().b(xVar, (Activity) this);
    }

    public boolean handleMessage(Message message) {
        String str;
        switch (message.what) {
            case 10000401:
                a((ArrayList) message.obj);
                return true;
            case 10000402:
                c();
                return true;
            case 10000403:
                ArrayList e = w.b().e();
                if (e.size() > 0) {
                    a(e);
                } else {
                    a();
                }
                w.b().g();
                return true;
            case 10000404:
                b();
                if (!(this.e == null || this.a == null)) {
                    int firstVisiblePosition = this.a.getFirstVisiblePosition();
                    this.e.notifyDataSetChanged();
                    this.a.setSelection(firstVisiblePosition);
                }
                try {
                    str = (String) message.obj;
                    x a = this.e.a(str);
                    if (a == null || 4 != a.d() || str.equals(d.bS(ReaderApplication.getApplicationImp()))) {
                        if (a != null && 4 == a.d() && str.equals(d.bS(ReaderApplication.getApplicationImp()))) {
                            a(a);
                        }
                        return true;
                    }
                    b(a);
                    return true;
                } catch (Exception e2) {
                }
                break;
            case 10000405:
                try {
                    str = (String) message.obj;
                    if (this.f == null) {
                        this.f = Toast.makeText(this, str, 0);
                    } else {
                        this.f.setText(str);
                    }
                    this.f.show();
                } catch (Exception e3) {
                    com.qq.reader.common.monitor.debug.c.e(getClass().getSimpleName(), e3.toString());
                }
                return true;
            case 10000406:
                startLogin();
                return true;
            case 10000407:
                return true;
            case 10000408:
                try {
                    showFragmentDialog(ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE, (Bundle) message.obj);
                } catch (Exception e32) {
                    com.qq.reader.common.monitor.debug.c.c(getClass().getSimpleName(), e32.toString(), true);
                }
                return true;
            default:
                return false;
        }
    }

    private void a(ArrayList<x> arrayList) {
        if (arrayList != null) {
            this.e.a((ArrayList) arrayList);
            ((BaseAdapter) this.a.getAdapter()).notifyDataSetChanged();
            b();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.localbookstore_skin_manage_layout);
        e();
        ArrayList e = w.b().e();
        if (e.size() > 0) {
            a(e);
        } else {
            a();
        }
        w.b().g();
    }

    protected void onResume() {
        super.onResume();
        w.b().a((com.qq.reader.plugin.w.b) this);
        if (this.e != null) {
            int firstVisiblePosition = this.a.getFirstVisiblePosition();
            this.e.notifyDataSetChanged();
            this.a.setSelection(firstVisiblePosition);
        }
    }

    protected void onPause() {
        super.onPause();
        w.b().b((com.qq.reader.plugin.w.b) this);
    }

    protected void a() {
        d();
        this.d.setVisibility(8);
        this.b.setVisibility(0);
    }

    protected void b() {
        d();
        this.d.setVisibility(0);
        this.b.setVisibility(8);
    }

    protected void c() {
        this.b.setVisibility(8);
        if (this.d.getVisibility() != 0) {
            this.c.setVisibility(0);
        }
    }

    public void excuteOnSwitchAccount(Context context) {
        super.excuteOnSwitchAccount(context);
        w.b().a((com.qq.reader.plugin.w.b) this);
        this.mHandler.sendEmptyMessage(10000403);
    }

    protected void d() {
        this.c.setVisibility(8);
    }

    private void e() {
        this.a = (StickyGridHeadersGridView) findViewById(R.id.gridview);
        this.b = findViewById(R.id.loading_layout);
        this.c = findViewById(R.id.loading_failed_layout);
        this.d = this.a;
        this.e = new a(this, new ArrayList());
        this.a.setAdapter(this.e);
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeSkinManageActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        ((TextView) findViewById(R.id.profile_header_title)).setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.my_theme));
    }

    public String A() {
        return "skin_all";
    }

    public Handler B() {
        return getHandler();
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        switch (i) {
            case ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE /*320*/:
                AlertDialog alertDialog = (AlertDialog) q.a(this, i, null);
                if (bundle == null || alertDialog == null) {
                    return null;
                }
                CharSequence string = bundle.getString("message");
                CharSequence string2 = bundle.getString("buttonok");
                final String string3 = bundle.getString("qurl");
                CharSequence string4 = bundle.getString("id");
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                    return null;
                }
                final Map hashMap = new HashMap();
                if (!TextUtils.isEmpty(string4)) {
                    hashMap.put(s.ORIGIN, string4);
                }
                alertDialog.a(string);
                alertDialog.a(-1, (int) R.drawable.buy_book_dialog_confirm_bg);
                alertDialog.a(string2, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeSkinManageActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        try {
                            com.qq.reader.qurl.c.a(this.c, string3);
                            i.a("event_A167", hashMap, ReaderApplication.getApplicationImp());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                alertDialog.b((CharSequence) "取消", new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeSkinManageActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        i.a("event_A168", hashMap, ReaderApplication.getApplicationImp());
                    }
                });
                i.a("event_A166", hashMap, ReaderApplication.getApplicationImp());
                return alertDialog;
            default:
                return super.createDialog(i, bundle);
        }
    }

    private void c(final x xVar) {
        new com.qq.reader.view.AlertDialog.a(this).a((int) R.string.dialog_shortcut_title).b((int) R.string.skin_delete_dialog_note).a((int) R.string.confirm, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ NativeSkinManageActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                w.b().a(xVar, this.b);
                dialogInterface.dismiss();
            }
        }).b((int) R.string.cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ NativeSkinManageActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).a().show();
    }

    private void a(x xVar, b bVar) {
        if (bVar != null && xVar != null) {
            int d = xVar.d();
            bVar.g.setEnabled(true);
            bVar.g.setBackgroundResource(R.drawable.skin_btn_download_bg);
            bVar.f.setVisibility(8);
            bVar.e.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.localstore_textcolor_white));
            bVar.i.setVisibility(8);
            CharSequence charSequence = null;
            CharSequence string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_download);
            switch (d) {
                case 0:
                case 1:
                case 6:
                    String trim = xVar.o().trim();
                    if (trim != null && trim.length() > 0) {
                        if (w.b().d(xVar) > 0) {
                            string = "继续下载";
                            bVar.f.setVisibility(0);
                            bVar.f.setProgress(w.b().d(xVar));
                        } else {
                            string = "下载";
                        }
                        bVar.d.setVisibility(8);
                        bVar.e.setVisibility(0);
                        bVar.e.setText(string);
                        return;
                    }
                case 2:
                case 3:
                    bVar.f.setVisibility(0);
                    charSequence = w.b().c(xVar);
                    string = "";
                    break;
                case 4:
                    if (!xVar.i().equals(d.bS(ReaderApplication.getApplicationImp()))) {
                        bVar.g.setBackgroundResource(R.drawable.selector_round_orange_button);
                        string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_switch);
                        if (!Constants.DEFAULT_UIN.equals(xVar.i())) {
                            bVar.i.setVisibility(0);
                            break;
                        }
                    }
                    string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_used);
                    bVar.g.setEnabled(false);
                    bVar.g.setBackgroundResource(R.drawable.selector_round_orange_button);
                    bVar.e.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c104));
                    break;
                    break;
                case 7:
                    string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_update);
                    break;
            }
            if (TextUtils.isEmpty(charSequence)) {
                bVar.d.setVisibility(8);
                bVar.d.setText("");
                bVar.f.setProgress(0);
            } else {
                bVar.d.setVisibility(0);
                if ("正在安装...".equals(charSequence)) {
                    bVar.d.setText("正在切换...");
                    bVar.f.setVisibility(8);
                } else {
                    bVar.d.setText(charSequence);
                    bVar.f.setProgress(w.b().d(xVar));
                }
            }
            if (TextUtils.isEmpty(string)) {
                bVar.e.setText("");
                bVar.e.setVisibility(8);
                return;
            }
            bVar.e.setText(string);
            bVar.e.setVisibility(0);
        }
    }
}
