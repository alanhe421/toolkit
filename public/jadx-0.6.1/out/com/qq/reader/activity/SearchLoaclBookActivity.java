package com.qq.reader.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.utils.am;
import com.qq.reader.common.utils.ao;
import com.qq.reader.filebrowser.FileSearch;
import com.qq.reader.filebrowser.view.a;
import com.qq.reader.filebrowser.view.b;
import com.qq.reader.filebrowser.view.c;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.view.GuideShadowView;
import com.qq.reader.view.af;
import com.qq.reader.view.f;
import com.qq.reader.view.k;
import com.qq.reader.view.web.n;
import com.tencent.feedback.proguard.R;
import format.archive.FileItem;
import format.epub.common.book.BookEPub;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SearchLoaclBookActivity extends ReaderBaseActivity implements c {
    private static final int[] x = new int[]{0, 1, 2, 3, 4, 5};
    private static final String[] y = new String[]{"全部格式", "Txt", "EPub", "Pdf", "Office", "其他"};
    private int A = 0;
    private com.qq.reader.view.c B;
    private StringBuffer C = new StringBuffer("放入书架");
    private n D;
    private GuideShadowView E;
    private k F;
    Context a;
    OnItemClickListener b = new OnItemClickListener(this) {
        final /* synthetic */ SearchLoaclBookActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            File b;
            ((a) this.a.e.get(i)).d();
            a aVar = (a) this.a.e.get(i);
            if (aVar.c() == 1) {
                b = aVar.b();
            } else {
                b = new File(aVar.a());
            }
            if (b != null) {
                this.a.a(b, true);
            }
        }
    };
    private ListView c;
    private TextView d;
    private ArrayList<a> e = new ArrayList();
    private ArrayList<a> f = new ArrayList();
    private b g;
    private Handler h;
    private int i;
    private List<a> j = new ArrayList();
    private ImageView k;
    private View l;
    private TextView m;
    private TextView n;
    private TextView o;
    private RelativeLayout p;
    private ImageView q;
    private int r;
    private LocalBookActivity.b s;
    private FileSearch t;
    private OnCancelListener u;
    private f v;
    private TextView w;
    private int z = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = this;
        setContentView(R.layout.localbook_layout);
        this.i = (int) getResources().getDimension(R.dimen.common_list_item_height);
        this.q = (ImageView) findViewById(R.id.profile_header_title_sort);
        this.l = findViewById(R.id.local_book_bottom);
        ((TextView) findViewById(R.id.local_book_uplevel)).setVisibility(8);
        this.k = (ImageView) findViewById(R.id.profile_header_left_back);
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchLoaclBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.setResult(this.a.A);
                this.a.finish();
            }
        });
        this.m = (TextView) findViewById(R.id.local_book_bottom_select_all);
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchLoaclBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int count;
                int i;
                a aVar;
                if ("全选".equals(this.a.m.getText())) {
                    this.a.m.setText("取消");
                    count = this.a.g.getCount();
                    for (i = 0; i < count; i++) {
                        aVar = (a) this.a.g.getItem(i);
                        if (aVar != null && aVar.e() == 0) {
                            aVar.b(1);
                            this.a.j.add(aVar);
                        }
                    }
                    this.a.n.setText(this.a.a(this.a.j.size()));
                    this.a.g.notifyDataSetChanged();
                    j.a(85, 0);
                    return;
                }
                this.a.i();
                count = this.a.g.getCount();
                for (i = 0; i < count; i++) {
                    aVar = (a) this.a.g.getItem(i);
                    if (aVar != null && aVar.e() == 1) {
                        aVar.b(0);
                    }
                }
                this.a.g.notifyDataSetChanged();
            }
        });
        this.n = (TextView) findViewById(R.id.local_book_bottom_import);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchLoaclBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e();
                g.a().a(new AnonymousClass1(this));
            }
        });
        this.o = (TextView) findViewById(R.id.profile_header_title);
        this.o.setText(y[0]);
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchLoaclBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.j().a(true);
                j.a(49, 0);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.profile_header_title_sort);
        imageView.setImageResource(R.drawable.bookstore_title_arrow_white);
        imageView.setVisibility(0);
        this.p = (RelativeLayout) findViewById(R.id.common_titler);
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchLoaclBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.j().a(true);
                j.a(49, 0);
            }
        });
        this.c = (ListView) findViewById(R.id.filelist);
        this.c.setPadding(0, 0, 0, this.i);
        this.d = (TextView) findViewById(R.id.info);
        this.c.setOnItemClickListener(this.b);
        this.h = new Handler(this) {
            final /* synthetic */ SearchLoaclBookActivity a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.handleMessageImp(message);
            }
        };
        this.g = new b(this, this.e);
        this.d.setText(b());
        this.g.a((c) this);
        this.c.setAdapter(this.g);
        String string = getIntent().getExtras().getString("filepath");
        if (string != null && string.length() > 0) {
            a();
            a(string);
        }
    }

    private void e() {
        if (this.B == null) {
            this.B = new com.qq.reader.view.c(this);
            this.B.c(true);
            this.B.a("正在导入...");
        }
        if (!this.B.f()) {
            this.B.f_();
        }
    }

    private void f() {
        if (this.B != null) {
            this.B.cancel();
        }
    }

    protected void a() {
        this.s = new LocalBookActivity.b(this) {
            final /* synthetic */ SearchLoaclBookActivity a;

            {
                this.a = r1;
            }

            public void a(final List<File> list) {
                ((Activity) this.a.a).runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 b;

                    public void run() {
                        if (!this.b.a.t.a() && list.size() != 0) {
                            for (File file : list) {
                                if (ao.a(file.getName(), this.b.a.a.getApplicationContext(), this.b.a.z)) {
                                    a a = this.b.a.a(this.b.a.e, file);
                                    if (a != null) {
                                        this.b.a.f.add(a);
                                        if (a.e() == 0) {
                                            this.b.a.r = this.b.a.r + 1;
                                        }
                                    }
                                } else {
                                    this.b.a.a(this.b.a.f, file);
                                }
                            }
                            this.b.a.g();
                        }
                    }
                });
            }

            public void a(int i) {
                this.a.h.sendEmptyMessage(i);
            }
        };
        this.t = new FileSearch();
        this.u = new OnCancelListener(this) {
            final /* synthetic */ SearchLoaclBookActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.t.a(true);
            }
        };
    }

    private void g() {
        this.g.notifyDataSetChanged();
        this.c.setSelection(this.g.getCount() - 1);
        this.d.setText(b());
        String string = this.a.getResources().getString(R.string.local_import_book_scan_book);
        this.w.setText(String.format(string, new Object[]{Integer.valueOf(this.g.getCount())}));
    }

    protected void a(String str) {
        this.t.a(false);
        this.v = new f(this);
        this.v.c(true);
        this.v.b(false);
        this.w = this.v.g();
        this.v.a(this.u);
        this.v.a("扫描");
        this.v.f_();
        if (str != null && str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        this.t.a(str, com.qq.reader.common.c.a.bm, this.s);
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

    private void a(File file, boolean z) {
        if (!am.a() || !file.exists()) {
            af.a(this.a.getApplicationContext(), (CharSequence) "SDCard无法访问", 0).a();
        } else if (com.qq.reader.readengine.model.a.b(com.qq.reader.readengine.model.a.a(file))) {
            i();
            String absolutePath = file.getAbsolutePath();
            String name = file.getName();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("filepath", absolutePath);
            bundle.putString("filename", name);
            intent.putExtras(bundle);
            com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            com.qq.reader.b.a(intent, this.a);
        } else if (file.isFile()) {
            BookShelfFragment.resetScrollType = (byte) 1;
        }
    }

    public boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 1002:
                i();
                this.g.notifyDataSetChanged();
                f();
                break;
            case 1003:
                if (this.e.size() == 0) {
                    af.a(getApplicationContext(), (CharSequence) "什么也没有扫描到", 1).a();
                } else {
                    Collections.sort(this.e);
                    this.g.notifyDataSetChanged();
                }
                this.v.cancel();
                break;
            case 1004:
                this.v.cancel();
                af.a((Activity) this.a, (int) R.string.dialog_searchfailed_msg, (int) APPluginErrorCode.ERROR_APP_TENPAY).a();
                break;
            case 1005:
                g();
                break;
        }
        return true;
    }

    public String b() {
        return getResources().getString(R.string.serch_result) + "  " + this.g.getCount() + "本";
    }

    private int a(File file) {
        Object obj;
        String path = file.getPath();
        Iterator it = LocalBookActivity.d.iterator();
        while (it.hasNext()) {
            if (path.equals((String) it.next())) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj != null) {
            return 3;
        }
        return com.qq.reader.readengine.model.a.b(com.qq.reader.readengine.model.a.a(file)) ? 4 : 0;
    }

    protected a a(List<a> list, File file) {
        int a = a(file);
        String a2 = com.qq.reader.readengine.model.a.a(file);
        return a(a, file, list, ao.b(file.length()), a2);
    }

    protected a a(int i, File file, List<a> list, String str, String str2) {
        String name = file.getName();
        if (name.startsWith(".")) {
            return null;
        }
        if (file.isFile()) {
            name = name.substring(0, name.lastIndexOf("."));
        }
        a aVar = new a(name, i, str, str2);
        if (file instanceof FileItem) {
            aVar.a(1);
            aVar.a(file);
        }
        aVar.a(file.getAbsolutePath());
        list.add(aVar);
        return aVar;
    }

    private void h() {
        for (a aVar : this.j) {
            if (aVar != null) {
                String a = aVar.a();
                String substring = a.substring(a.lastIndexOf("/") + 1, a.length());
                Mark localMark = new LocalMark(substring, a, 0, 1, false);
                localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
                localMark.setPercentStr("0.0%").setAuthor("匿名");
                if (substring != null && substring.toLowerCase().endsWith(".epub")) {
                    try {
                        BookEPub createBookForFile = BookEPub.createBookForFile(a, 0);
                        if (createBookForFile != null) {
                            createBookForFile.readMetaInfo();
                            substring = createBookForFile.getBookName();
                            if (substring != null && substring.trim().length() > 0) {
                                localMark.setBookName(substring);
                            }
                            List authors = createBookForFile.authors();
                            localMark.setAuthor(authors.size() > 0 ? ((format.epub.common.book.a) authors.get(0)).a : "").setEncoding(101);
                            format.epub.common.a.a.a(createBookForFile);
                        }
                    } catch (Error e) {
                    }
                }
                if (i.c().a(localMark, true)) {
                    this.A = 1;
                    aVar.b(3);
                }
            }
        }
    }

    private void i() {
        for (int i = 0; i < this.j.size(); i++) {
            a aVar = (a) this.j.get(i);
            if (aVar != null && aVar.e() == 1) {
                aVar.b(0);
            }
        }
        this.j.clear();
        this.l.setVisibility(8);
        this.c.setPadding(0, 0, 0, 0);
        this.m.setText("全选");
        this.n.setText("放入书架(1)");
    }

    private String a(int i) {
        this.C.setLength(4);
        this.C.append("(");
        this.C.append(i);
        this.C.append(")");
        return this.C.toString();
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

    public void a(a aVar, boolean z) {
        if (z) {
            this.l.setVisibility(0);
            this.c.setPadding(0, 0, 0, this.i);
            this.j.add(aVar);
            if (this.j.size() == this.r) {
                this.m.setText("取消");
            }
            this.n.setText(a(this.j.size()));
            return;
        }
        this.j.remove(aVar);
        if (this.j.size() == 0) {
            i();
            return;
        }
        this.m.setText("全选");
        this.n.setText(a(this.j.size()));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            setResult(this.A);
        }
        return super.onKeyDown(i, keyEvent);
    }

    private n j() {
        if (this.D == null) {
            this.D = new n(this, R.layout.webpage_popup_menu);
            this.D.c().a((int) R.id.readpage_topbar_popup);
            this.D.a(new OnCancelListener(this) {
                final /* synthetic */ SearchLoaclBookActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.q.setImageResource(R.drawable.bookstore_title_arrow_white);
                    if (this.a.E != null) {
                        ((ViewGroup) this.a.getWindow().getDecorView()).removeView(this.a.E);
                    }
                }
            });
            k();
            for (int i = 0; i < x.length; i++) {
                this.D.a(x[i], y[i], null);
            }
            this.D.a(new n.a(this) {
                final /* synthetic */ SearchLoaclBookActivity a;

                class AnonymousClass1 extends ReaderIOTask {
                    final /* synthetic */ AnonymousClass6 this$1;

                    AnonymousClass1(AnonymousClass6 anonymousClass6) {
                        this.this$1 = anonymousClass6;
                    }

                    public void run() {
                        super.run();
                        this.this$1.a.h();
                        this.this$1.a.h.sendEmptyMessage(1002);
                    }
                }

                {
                    this.a = r1;
                }

                public boolean b(int i, Bundle bundle) {
                    if (this.a.D.h() == i) {
                        return false;
                    }
                    this.a.z = i;
                    this.a.o.setText(SearchLoaclBookActivity.y[this.a.z]);
                    this.a.r = 0;
                    this.a.j.clear();
                    this.a.e.clear();
                    this.a.n.setText(this.a.a(this.a.j.size()));
                    Iterator it = this.a.f.iterator();
                    while (it.hasNext()) {
                        a aVar = (a) it.next();
                        if (aVar != null && aVar.e() == 1) {
                            aVar.b(0);
                        }
                        if (aVar != null && ao.a(aVar.a(), this.a.a.getApplicationContext(), this.a.z)) {
                            this.a.e.add(aVar);
                            if (aVar.e() == 0) {
                                this.a.r = this.a.r + 1;
                            }
                        }
                    }
                    this.a.D.b(this.a.z);
                    this.a.d.setText(this.a.b());
                    Collections.sort(this.a.e);
                    this.a.g.notifyDataSetChanged();
                    this.a.getWindow().closeAllPanels();
                    return true;
                }
            });
        }
        return this.D;
    }

    @TargetApi(8)
    private void k() {
        if (this.D != null) {
            this.D.a(new OnShowListener(this) {
                final /* synthetic */ SearchLoaclBookActivity a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    if (!d.n) {
                        if (this.a.E == null) {
                            this.a.E = new GuideShadowView(this.a);
                        }
                        this.a.E.setHighLightRect(this.a.c());
                        ((ViewGroup) this.a.getWindow().getDecorView()).addView(this.a.E);
                    }
                }
            });
        }
    }

    public k c() {
        if (this.F == null) {
            View findViewById = findViewById(R.id.common_titler);
            r1 = new int[4];
            findViewById.getLocationOnScreen(r1);
            r1[2] = r1[0] + findViewById.getWidth();
            r1[3] = findViewById.getHeight() + r1[1];
            this.F = new k();
            this.F.a = new Rect(r1[0], r1[1], r1[2], r1[3]);
            this.F.b = 1;
        }
        return this.F;
    }
}
