package com.qq.reader.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.utils.am;
import com.qq.reader.common.utils.ao;
import com.qq.reader.filebrowser.view.c;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import format.archive.FileItem;
import format.epub.common.book.BookEPub;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class LocalBookActivity extends ReaderBaseActivity implements OnScrollListener, c {
    public static final String b = am.b();
    public static String c = am.b();
    static ArrayList<String> d = new ArrayList();
    private int A;
    private com.qq.reader.view.c B;
    private volatile boolean C;
    private boolean D;
    private int E = -1;
    private StringBuffer F = new StringBuffer("放入书架");
    Context a;
    boolean e = false;
    OnItemClickListener f = new OnItemClickListener(this) {
        final /* synthetic */ LocalBookActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.p.setVisibility(4);
            String d = ((com.qq.reader.filebrowser.view.a) this.a.g.get(i)).d();
            if (d.equals(this.a.a.getString(R.string.current_dir))) {
                this.a.a(this.a.i, true);
            } else if (d.equals(this.a.a.getString(R.string.up_one_level))) {
                this.a.j();
            } else {
                File b;
                com.qq.reader.filebrowser.view.a aVar = (com.qq.reader.filebrowser.view.a) this.a.g.get(i);
                if (aVar.c() == 1) {
                    b = aVar.b();
                } else {
                    b = new File(aVar.a());
                }
                if (b != null) {
                    this.a.a(b, true);
                }
            }
        }
    };
    private List<com.qq.reader.filebrowser.view.a> g = new ArrayList();
    private List<com.qq.reader.filebrowser.view.a> h = new ArrayList();
    private File i = null;
    private Stack<Integer> j = new Stack();
    private TextView k;
    private Button l;
    private ImageView m;
    private ListView n;
    private com.qq.reader.filebrowser.view.b o;
    private TextView p;
    private a q;
    private int r;
    private Handler s;
    private RelativeLayout t;
    private TextView u;
    private View v;
    private TextView w;
    private TextView x;
    private int y;
    private View z;

    private class a implements Runnable {
        final /* synthetic */ LocalBookActivity a;

        private a(LocalBookActivity localBookActivity) {
            this.a = localBookActivity;
        }

        public void run() {
            if (!this.a.C) {
                this.a.p.setVisibility(4);
            }
        }
    }

    public interface b {
        void a(int i);

        void a(List<File> list);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = false;
        this.a = this;
        this.A = (int) getResources().getDimension(R.dimen.common_list_item_height);
        this.s = new Handler(this) {
            final /* synthetic */ LocalBookActivity a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.handleMessageImp(message);
            }
        };
        setContentView(R.layout.localbook_layout);
        this.z = findViewById(R.id.nofile);
        this.k = (TextView) findViewById(R.id.info);
        this.m = (ImageView) findViewById(R.id.profile_header_left_back);
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
                this.a.b();
                j.a(18, 0);
            }
        });
        this.u = (TextView) findViewById(R.id.local_book_uplevel);
        this.t = (RelativeLayout) findViewById(R.id.localbook_book_uplevel_part);
        this.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                j.a(80, 0);
                this.a.l();
                this.a.j();
            }
        });
        this.l = (Button) findViewById(R.id.profile_header_right_button);
        this.l.setText("扫描");
        this.l.setVisibility(0);
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                j.a(79, 0);
                this.a.p.setVisibility(4);
                this.a.l();
                Intent intent = new Intent();
                intent.setClass(this.a.getApplicationContext(), SearchLoaclBookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("filepath", this.a.i.getAbsolutePath());
                intent.putExtras(bundle);
                this.a.startActivityForResult(intent, 0);
            }
        });
        ((TextView) findViewById(R.id.profile_header_title)).setText("导入本地图书");
        this.v = findViewById(R.id.local_book_bottom);
        this.w = (TextView) findViewById(R.id.local_book_bottom_select_all);
        this.w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int count;
                int i;
                com.qq.reader.filebrowser.view.a aVar;
                if ("全选".equals(this.a.w.getText())) {
                    this.a.w.setText("取消");
                    count = this.a.o.getCount();
                    for (i = 0; i < count; i++) {
                        aVar = (com.qq.reader.filebrowser.view.a) this.a.o.getItem(i);
                        if (aVar != null && aVar.e() == 0) {
                            aVar.b(1);
                            this.a.h.add(aVar);
                        }
                    }
                    this.a.x.setText(this.a.a(this.a.h.size()));
                    this.a.o.notifyDataSetChanged();
                    j.a(85, 0);
                    return;
                }
                this.a.l();
                count = this.a.o.getCount();
                for (i = 0; i < count; i++) {
                    aVar = (com.qq.reader.filebrowser.view.a) this.a.o.getItem(i);
                    if (aVar != null && aVar.e() == 1) {
                        aVar.b(0);
                    }
                }
                this.a.o.notifyDataSetChanged();
            }
        });
        this.x = (TextView) findViewById(R.id.local_book_bottom_import);
        this.x.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e = true;
                this.a.c();
                g.a().a(new ReaderIOTask() {
                    public void run() {
                        super.run();
                        AnonymousClass6.this.a.a.getApplicationContext().sendBroadcast(new Intent(com.qq.reader.common.c.a.ch));
                        AnonymousClass6.this.a.f();
                        AnonymousClass6.this.a.s.sendEmptyMessage(1002);
                    }
                });
                i.a("event_A82", null, ReaderApplication.getApplicationImp());
            }
        });
        this.n = (ListView) findViewById(R.id.filelist);
        this.p = (TextView) findViewById(R.id.pophint);
        this.n.setOnItemClickListener(this.f);
        this.o = new com.qq.reader.filebrowser.view.b(this.a, this.g);
        this.o.a((c) this);
        this.n.setAdapter(this.o);
        this.n.setOnScrollListener(this);
        this.n.postDelayed(new Runnable(this) {
            final /* synthetic */ LocalBookActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.i();
            }
        }, 30);
        this.q = new a();
    }

    private void c() {
        if (this.B == null) {
            this.B = new com.qq.reader.view.c(this);
            this.B.c(true);
            this.B.a("正在导入...");
        }
        if (!this.B.f()) {
            this.B.f_();
        }
    }

    private void d() {
        if (this.B != null) {
            this.B.cancel();
        }
    }

    protected void onResume() {
        super.onResume();
        e();
    }

    private void e() {
        d.clear();
        List<Mark> g = com.qq.reader.common.db.handle.i.c().g();
        if (g != null) {
            for (Mark id : g) {
                d.add(id.getId());
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != 0) {
            try {
                this.e = true;
                this.n.postDelayed(new Runnable(this) {
                    final /* synthetic */ LocalBookActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.h();
                    }
                }, 20);
            } catch (Exception e) {
            }
        }
    }

    public void a() {
        g();
        this.j.clear();
        af a = this.o.a();
        if (a != null) {
            a.b();
        }
    }

    private void f() {
        for (com.qq.reader.filebrowser.view.a aVar : this.h) {
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
                        e.printStackTrace();
                    }
                }
                if (com.qq.reader.common.db.handle.i.c().a(localMark, true)) {
                    d.add(a);
                    aVar.b(3);
                }
            }
        }
    }

    private void g() {
        if (this.i != null && am.a() && this.i.exists()) {
            d.k(this.a.getApplicationContext(), this.i.getAbsolutePath());
        }
    }

    private void h() {
        a(this.i, false);
    }

    private void i() {
        this.i = new File(d.A(this.a.getApplicationContext()));
        if (am.a()) {
            if (!this.i.exists()) {
                this.i = new File(c);
            }
            a(this.i, true);
            return;
        }
        af.a(this.a.getApplicationContext(), (CharSequence) "SDCard无法访问", 0).a();
    }

    private boolean j() {
        if (k()) {
            return false;
        }
        a(this.i.getParentFile(), false);
        if (!this.j.empty()) {
            int intValue = ((Integer) this.j.pop()).intValue();
            if (intValue >= 0 && intValue < this.o.getCount()) {
                this.n.setSelection(intValue);
            }
        }
        return true;
    }

    private boolean k() {
        if (this.i.getParent() == null) {
            return true;
        }
        File parentFile = this.i.getParentFile();
        if (parentFile.listFiles() == null || parentFile.listFiles().length == 0) {
            return true;
        }
        return this.i.getPath().equals("/mnt");
    }

    private void a(File file, boolean z) {
        if (am.a() && file != null && file.exists()) {
            String a = com.qq.reader.readengine.model.a.a(file);
            if (file.isDirectory()) {
                this.l.setVisibility(0);
                this.k.setText(file.getAbsolutePath());
                if (this.i != file && z) {
                    this.j.add(Integer.valueOf(this.n.getFirstVisiblePosition()));
                }
                this.i = file;
                a(file.listFiles());
                if (file instanceof FileItem) {
                    this.l.setVisibility(4);
                    return;
                }
                return;
            } else if (com.qq.reader.readengine.model.a.b(a)) {
                l();
                a = file.getAbsolutePath();
                String name = file.getName();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("filepath", a);
                bundle.putString("filename", name);
                intent.putExtras(bundle);
                com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                com.qq.reader.b.a(intent, this.a);
                return;
            } else if (file.isFile()) {
                BookShelfFragment.resetScrollType = (byte) 1;
                return;
            } else {
                return;
            }
        }
        af.a(this.a.getApplicationContext(), (CharSequence) "SDCard无法访问", 0).a();
    }

    private void a(File[] fileArr) {
        if (fileArr != null) {
            this.y = 0;
            l();
            this.g.clear();
            if (k()) {
                this.u.setVisibility(4);
                this.t.setClickable(false);
            } else {
                this.u.setVisibility(0);
                this.t.setClickable(true);
            }
            Collection arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (File file : fileArr) {
                String a = com.qq.reader.readengine.model.a.a(file);
                if (file instanceof FileItem) {
                    ((FileItem) file).setParentFile(this.i);
                }
                if (file.isDirectory()) {
                    if (com.qq.reader.common.c.a.B) {
                        a(2, file, arrayList, "", "");
                    } else if (file.list() != null) {
                        a(2, file, arrayList, ao.a(file.listFiles(), this.a), "");
                    }
                } else if (ao.a(file.getName(), this.a.getApplicationContext(), 0)) {
                    if (com.qq.reader.readengine.model.a.b(a)) {
                        a(4, file, arrayList, ao.b(file.length()), com.qq.reader.readengine.model.a.a(file));
                    } else {
                        com.qq.reader.filebrowser.view.a a2 = a(arrayList2, file);
                        if (a2 != null && a2.e() == 0) {
                            this.y++;
                        }
                    }
                }
            }
            Collections.sort(arrayList2);
            Collections.sort(arrayList);
            this.g.addAll(arrayList2);
            this.g.addAll(arrayList);
            this.o.a(this.g);
            if (this.o.getCount() > 0) {
                this.n.setSelection(0);
            }
            this.o.notifyDataSetInvalidated();
            if (this.g.size() == 0) {
                this.z.setVisibility(0);
            } else {
                this.z.setVisibility(8);
            }
        }
    }

    protected com.qq.reader.filebrowser.view.a a(List<com.qq.reader.filebrowser.view.a> list, File file) {
        int a = a(file);
        String a2 = com.qq.reader.readengine.model.a.a(file);
        return a(a, file, list, ao.b(file.length()), a2);
    }

    private int a(File file) {
        Object obj;
        String path = file.getPath();
        Iterator it = d.iterator();
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

    protected com.qq.reader.filebrowser.view.a a(int i, File file, List<com.qq.reader.filebrowser.view.a> list, String str, String str2) {
        String name = file.getName();
        if (name.startsWith(".")) {
            return null;
        }
        if (file.isFile()) {
            name = name.substring(0, name.lastIndexOf("."));
        }
        com.qq.reader.filebrowser.view.a aVar = new com.qq.reader.filebrowser.view.a(name, i, str, str2);
        if (file instanceof FileItem) {
            aVar.a(1);
            aVar.a(file);
        }
        aVar.a(file.getAbsolutePath());
        list.add(aVar);
        return aVar;
    }

    public boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 1002:
                l();
                this.o.notifyDataSetChanged();
                d();
                break;
        }
        return true;
    }

    public void setTitle(int i) {
        ((TextView) findViewById(R.id.info)).setText(i);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.D) {
            this.C = true;
        }
        int i4 = i + 1;
        if (i4 >= 0 && i4 < i3) {
            String b = com.qq.reader.filebrowser.b.b(((com.qq.reader.filebrowser.view.a) this.o.getItem(i4)).d());
            if (b != null && b.trim().length() > 0 && b.charAt(0) <= '') {
                this.p.setText(b.toUpperCase());
            }
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.r = i;
        this.D = true;
        this.s.removeCallbacks(this.q);
        if (this.r == 0) {
            this.D = false;
            this.C = false;
            this.s.postDelayed(this.q, 1500);
        } else if (this.r == 1) {
            if (this.o.getCount() >= 2) {
                this.p.setVisibility(0);
            }
        } else if (this.r == 2 && VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 8) {
            this.C = false;
            this.D = false;
            this.s.postDelayed(this.q, 1500);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                a();
                if (this.v == null || this.v.getVisibility() != 0) {
                    b();
                    break;
                }
                l();
                return true;
        }
        return false;
    }

    public void b() {
        setResult(this.e ? -1 : 0);
        finish();
    }

    private void l() {
        for (int i = 0; i < this.h.size(); i++) {
            com.qq.reader.filebrowser.view.a aVar = (com.qq.reader.filebrowser.view.a) this.h.get(i);
            if (aVar != null && aVar.e() == 1) {
                aVar.b(0);
            }
        }
        this.h.clear();
        this.v.setVisibility(8);
        this.n.setPadding(0, 0, 0, 0);
        this.w.setText("全选");
        this.x.setText("放入书架(1)");
    }

    private String a(int i) {
        this.F.setLength(4);
        this.F.append("(");
        this.F.append(i);
        this.F.append(")");
        return this.F.toString();
    }

    public void a(com.qq.reader.filebrowser.view.a aVar, boolean z) {
        if (z) {
            this.v.setVisibility(0);
            this.n.setPadding(0, 0, 0, this.A);
            this.h.add(aVar);
            if (this.h.size() == this.y) {
                this.w.setText("取消");
            }
            this.x.setText(a(this.h.size()));
            return;
        }
        this.h.remove(aVar);
        if (this.h.size() == 0) {
            l();
            return;
        }
        this.w.setText("全选");
        this.x.setText(a(this.h.size()));
    }
}
