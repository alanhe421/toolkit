package format.archive;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.utils.am;
import com.qq.reader.common.utils.ao;
import com.qq.reader.filebrowser.view.a;
import com.qq.reader.filebrowser.view.b;
import com.qq.reader.filebrowser.view.c;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import format.epub.common.utils.f;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArchiveFileBrowser extends ReaderBaseActivity implements c {
    OnItemClickListener a = new OnItemClickListener(this) {
        final /* synthetic */ ArchiveFileBrowser a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String d = ((a) this.a.d.get(i)).d();
            if (d.equals(this.a.getString(R.string.current_dir))) {
                this.a.a(this.a.h, true);
            } else if (d.equals(this.a.getString(R.string.up_one_level))) {
                this.a.b();
            } else {
                File b;
                a aVar = (a) this.a.d.get(i);
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
    private ListView b;
    private TextView c;
    private List<a> d = new ArrayList();
    private b e;
    private String f;
    private Handler g;
    private File h = null;
    private ArrayList<String> i = new ArrayList();
    private List<a> j = new ArrayList();
    private ImageView k;
    private View l;
    private TextView m;
    private TextView n;
    private int o;
    private TextView p;
    private RelativeLayout q;
    private TextView r;
    private StringBuffer s = new StringBuffer("放入书架");

    protected void onCreate(Bundle bundle) {
        String path;
        super.onCreate(bundle);
        setContentView(R.layout.localbook_layout);
        this.l = findViewById(R.id.local_book_bottom);
        this.k = (ImageView) findViewById(R.id.profile_header_left_back);
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ArchiveFileBrowser a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        ((Button) findViewById(R.id.profile_header_right_button)).setVisibility(8);
        ((ImageView) findViewById(R.id.profile_header_title_sort)).setVisibility(8);
        this.p = (TextView) findViewById(R.id.local_book_uplevel);
        this.q = (RelativeLayout) findViewById(R.id.localbook_book_uplevel_part);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ArchiveFileBrowser a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
                this.a.b();
            }
        });
        this.m = (TextView) findViewById(R.id.local_book_bottom_select_all);
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ArchiveFileBrowser a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = 0;
                int count;
                a aVar;
                if ("全选".equals(this.a.m.getText())) {
                    this.a.m.setText("取消");
                    count = this.a.e.getCount();
                    while (i < count) {
                        aVar = (a) this.a.e.getItem(i);
                        if (aVar != null && aVar.e() == 0) {
                            aVar.b(1);
                            this.a.j.add(aVar);
                        }
                        i++;
                    }
                    this.a.n.setText(this.a.a(this.a.j.size()));
                    this.a.e.notifyDataSetChanged();
                    return;
                }
                this.a.d();
                int count2 = this.a.e.getCount();
                for (count = 0; count < count2; count++) {
                    aVar = (a) this.a.e.getItem(count);
                    if (aVar != null && aVar.e() == 1) {
                        aVar.b(0);
                    }
                }
                this.a.e.notifyDataSetChanged();
            }
        });
        this.n = (TextView) findViewById(R.id.local_book_bottom_import);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ArchiveFileBrowser a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                f.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.c();
                        this.a.a.g.sendEmptyMessage(1002);
                    }
                }, this.a, "正在导入书籍到书架...");
            }
        });
        this.b = (ListView) findViewById(R.id.filelist);
        this.c = (TextView) findViewById(R.id.info);
        this.r = (TextView) findViewById(R.id.profile_header_title);
        this.b.setOnItemClickListener(this.a);
        this.e = new b(this, this.d);
        this.e.a(this);
        this.b.setAdapter(this.e);
        this.g = new Handler(this) {
            final /* synthetic */ ArchiveFileBrowser a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.handleMessageImp(message);
            }
        };
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            path = intent.getData().getPath();
            if (!(path == null || path.equals("") || path.startsWith("/mnt") || !com.qq.reader.common.c.a.l.startsWith("/mnt"))) {
                path = "/mnt" + path;
            }
            this.f = path;
        } else {
            this.f = getIntent().getExtras().getString("filepath");
        }
        CharSequence substring = this.f.substring(this.f.lastIndexOf(47) + 1, this.f.lastIndexOf(46));
        this.h = new File(this.f);
        this.c.setText(this.f);
        this.r.setText(substring);
        path = com.qq.reader.readengine.model.a.f(this.f);
        f.a(new Runnable(this) {
            final /* synthetic */ ArchiveFileBrowser b;

            public void run() {
                this.b.a(i.c().g());
                List a = a.a(this.b.f, "", false, path);
                Object obj = new FileItem[a.size()];
                a.toArray(obj);
                this.b.g.obtainMessage(1001, obj).sendToTarget();
            }
        }, this, "正在打开压缩文档...");
    }

    private void a(List<Mark> list) {
        if (this.i.size() == 0) {
            for (Mark mark : list) {
                if (mark != null) {
                    this.i.add(mark.getId());
                }
            }
        }
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

    public boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 1000:
                File file = (File) message.obj;
                a(file.getPath(), file.getName());
                break;
            case 1001:
                a((File[]) (FileItem[]) message.obj);
                break;
            case 1002:
                d();
                this.e.notifyDataSetChanged();
                break;
        }
        return true;
    }

    private void a(File[] fileArr) {
        if (fileArr != null) {
            this.o = 0;
            d();
            this.d.clear();
            if (a()) {
                this.p.setVisibility(4);
                this.q.setClickable(false);
            } else {
                this.p.setVisibility(0);
                this.q.setClickable(true);
            }
            Collection arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (File file : fileArr) {
                String a = com.qq.reader.readengine.model.a.a(file);
                if (file instanceof FileItem) {
                    ((FileItem) file).setParentFile(this.h);
                }
                if (file.isDirectory()) {
                    if (file.list() != null) {
                        a(2, file, arrayList, ao.a(file.list().length), "");
                    }
                } else if (ao.a(file.getName(), getApplicationContext())) {
                    if (com.qq.reader.readengine.model.a.b(a)) {
                        a(2, file, arrayList, ao.b(file.length()), com.qq.reader.readengine.model.a.a(file));
                    } else {
                        a a2 = a(arrayList2, file);
                        if (a2 != null && a2.e() == 0) {
                            this.o++;
                        }
                    }
                }
            }
            Collections.sort(arrayList2);
            Collections.sort(arrayList);
            this.d.addAll(arrayList2);
            this.d.addAll(arrayList);
            this.e.a(this.d);
            if (this.e.getCount() > 0) {
                this.b.setSelection(0);
            }
            this.e.notifyDataSetInvalidated();
        }
    }

    private int a(File file) {
        Object obj;
        String path = file.getPath();
        Iterator it = this.i.iterator();
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
        return com.qq.reader.readengine.model.a.b(com.qq.reader.readengine.model.a.a(file)) ? 2 : 0;
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

    private boolean a() {
        if (this.h.getParent() == null) {
            return true;
        }
        return this.h.getPath().equals(this.f);
    }

    private void a(String str, String str2) {
        if (!this.i.contains(str)) {
            this.i.add(str);
            this.e.a(str, true);
            d();
            if (this.o > 0) {
                this.o--;
            }
        }
        if (i.c().f(str) == null) {
            Mark localMark = new LocalMark(str2, str, 0, 1, false);
            localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
            localMark.setPercentStr("0.0%").setAuthor("匿名");
            i.c().a(localMark, true);
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("filepath", str);
        bundle.putString("filename", str2);
        intent.putExtras(bundle);
        com.qq.reader.b.a(intent, this);
    }

    private boolean b() {
        if (a()) {
            return false;
        }
        this.o = 0;
        this.j.clear();
        a(this.h.getParentFile(), false);
        return true;
    }

    private void a(File file, boolean z) {
        if (am.a() && file.exists()) {
            String a = com.qq.reader.readengine.model.a.a(file);
            if (file.isDirectory()) {
                this.h = file;
                this.c.setText(this.h.getAbsolutePath());
                a(file.listFiles());
                return;
            } else if (com.qq.reader.readengine.model.a.b(a)) {
                this.h = file;
                this.c.setText(this.h.getAbsolutePath());
                List a2 = a.a(file.getAbsolutePath(), "", false, a);
                File[] fileArr = new FileItem[a2.size()];
                a2.toArray(fileArr);
                a(fileArr);
                return;
            } else if (file.isFile()) {
                BookShelfFragment.resetScrollType = (byte) 1;
                a = file.getAbsolutePath();
                String name = file.getName();
                if (file instanceof FileItem) {
                    final FileItem fileItem = (FileItem) file;
                    if (new File(com.qq.reader.common.c.a.u + File.separator + name).exists()) {
                        a(fileItem.getPath(), name);
                        return;
                    } else {
                        f.a(new Runnable(this) {
                            final /* synthetic */ ArchiveFileBrowser b;

                            public void run() {
                                a.a(fileItem.mArchivePath, fileItem.getAbsolutePath(), fileItem.mCompressType);
                                this.b.g.obtainMessage(1000, fileItem).sendToTarget();
                            }
                        }, this, "文档加载中，请稍候");
                        return;
                    }
                }
                a(a, name);
                return;
            } else {
                return;
            }
        }
        af.a(getApplicationContext(), "SDCard无法访问", 0).a();
    }

    private void c() {
        for (a aVar : this.j) {
            if (aVar != null) {
                File b = aVar.b();
                String path = b.getPath();
                String name = b.getName();
                FileItem fileItem = (FileItem) b;
                if (!new File(com.qq.reader.common.c.a.u + File.separator + name).exists()) {
                    a.a(fileItem.mArchivePath, fileItem.getAbsolutePath(), fileItem.mCompressType);
                }
                Mark localMark = new LocalMark(name, path, 0, 1, false);
                localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
                localMark.setPercentStr("0.0%").setAuthor("匿名");
                if (i.c().a(localMark, true)) {
                    this.i.add(path);
                    aVar.b(3);
                }
            }
        }
    }

    private void d() {
        for (int i = 0; i < this.j.size(); i++) {
            a aVar = (a) this.j.get(i);
            if (aVar != null && aVar.e() == 1) {
                aVar.b(0);
            }
        }
        this.j.clear();
        this.l.setVisibility(8);
        this.m.setText("全选");
        this.n.setText("放入书架(1)");
    }

    private String a(int i) {
        this.s.setLength(4);
        this.s.append("(");
        this.s.append(i);
        this.s.append(")");
        return this.s.toString();
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
            this.j.add(aVar);
            if (this.j.size() == this.o) {
                this.m.setText("取消");
            }
            this.n.setText(a(this.j.size()));
            return;
        }
        this.j.remove(aVar);
        if (this.j.size() == 0) {
            d();
            return;
        }
        this.m.setText("全选");
        this.n.setText(a(this.j.size()));
    }
}
