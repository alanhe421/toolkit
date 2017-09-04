package com.qq.reader.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.am;
import com.qq.reader.framework.a.c;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.readengine.model.b;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.view.q;
import com.qq.reader.view.t;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneBookNoteActivity extends ReaderBaseActivity implements com.qq.reader.view.a.a {
    private static final String[] w = new String[]{"导出到书架", "导出到邮箱", "导出到微信", "导出到其它"};
    List<com.qq.reader.framework.a.a> a = null;
    List<b> b = null;
    Map<com.qq.reader.framework.a.a, Mark> c = new HashMap();
    Context d;
    q e = null;
    b f = null;
    private final String g = "OneBookNoteActivity";
    private volatile Handler h;
    private int i = -1;
    private final int j = 100;
    private final int k = 101;
    private a l;
    private c m = null;
    private ListView n;
    private ImageView o = null;
    private View p = null;
    private TextView q = null;
    private com.qq.reader.view.linearmenu.b r;
    private final int s = 0;
    private final int t = 1;
    private final int u = 2;
    private t v;
    private FileWriter x = null;
    private String y = "note";
    private Mark z = null;

    private class a extends BaseAdapter {
        final /* synthetic */ OneBookNoteActivity a;
        private OnClickListener b;
        private OnLongClickListener c;

        class a {
            TextView a;
            TextView b;
            TextView c;
            TextView d;
            ImageView e;
            com.qq.reader.framework.a.a f;
            final /* synthetic */ a g;

            a(a aVar) {
                this.g = aVar;
            }
        }

        class b {
            TextView a;
            TextView b;
            TextView c;
            final /* synthetic */ a d;

            b(a aVar) {
                this.d = aVar;
            }
        }

        private a(OneBookNoteActivity oneBookNoteActivity) {
            this.a = oneBookNoteActivity;
            this.b = new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    a aVar = (a) view.getTag();
                    this.a.a.n.setDivider(null);
                    this.a.a.i = 101;
                    this.a.a.b = (List) this.a.a.m.b().get(aVar.f);
                    this.a.a.o.setVisibility(0);
                    this.a.a.q.setText(aVar.f.c());
                    this.a.notifyDataSetInvalidated();
                }
            };
            this.c = new OnLongClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean onLongClick(View view) {
                    this.a.a.f = (com.qq.reader.readengine.model.b) view.getTag();
                    this.a.a.a().f_();
                    return true;
                }
            };
        }

        public int getCount() {
            int i = 0;
            switch (this.a.i) {
                case 100:
                    if (this.a.a != null) {
                        synchronized (this.a.a) {
                            i = this.a.a.size();
                        }
                        break;
                    }
                    break;
                case 101:
                    if (this.a.b != null) {
                        synchronized (this.a.b) {
                            i = this.a.b.size();
                        }
                        break;
                    }
                    break;
            }
            return i;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2 = 1;
            LayoutInflater layoutInflater = (LayoutInflater) this.a.d.getSystemService("layout_inflater");
            int i3;
            switch (this.a.i) {
                case 100:
                    a aVar;
                    i3 = view == null ? 1 : 0;
                    if (view == null) {
                        i2 = i3;
                    } else if (view.getTag() instanceof a) {
                        i2 = 0;
                    }
                    if (i2 != 0) {
                        view = layoutInflater.inflate(R.layout.note_bookinfo_item, null);
                        a aVar2 = new a(this);
                        aVar2.a = (TextView) view.findViewById(R.id.note_bookinfo_item_info_bookname);
                        aVar2.b = (TextView) view.findViewById(R.id.note_bookinfo_item_info_author);
                        aVar2.c = (TextView) view.findViewById(R.id.note_bookinfo_item_info_notes_num);
                        aVar2.e = (ImageView) view.findViewById(R.id.note_bookinfo_item_cover);
                        aVar2.d = (TextView) view.findViewById(R.id.note_bookinfo_item_name);
                        view.setTag(aVar2);
                        aVar = aVar2;
                    } else {
                        aVar = (a) view.getTag();
                    }
                    com.qq.reader.framework.a.a aVar3 = (com.qq.reader.framework.a.a) this.a.a.get(i);
                    aVar.a.setText(aVar3.c());
                    Mark mark = (Mark) this.a.c.get(aVar3);
                    if (mark != null) {
                        CharSequence charSequence;
                        com.qq.reader.common.imageloader.c.a(this.a.d).a(mark.getImageURI(), aVar.e, com.qq.reader.common.imageloader.a.a().j());
                        String trim = mark.getAuthor().trim();
                        if (trim == null || trim.length() <= 0) {
                            charSequence = "作者：本地书籍";
                        } else {
                            charSequence = "作者：" + trim;
                        }
                        aVar.b.setText(charSequence);
                    } else {
                        aVar.e.setImageResource(R.color.localstore_img_loading);
                        aVar.b.setText("作者：本地书籍");
                        aVar.d.setText(aVar3.c());
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("共");
                    stringBuffer.append(a(aVar3));
                    stringBuffer.append("条");
                    aVar.c.setText(stringBuffer.toString());
                    aVar.f = aVar3;
                    view.setOnClickListener(this.b);
                    if (com.qq.reader.appconfig.b.l && view != null) {
                        view.setBackgroundResource(R.drawable.common_card_background);
                        break;
                    }
                    break;
                case 101:
                    b bVar;
                    i3 = view == null ? 1 : 0;
                    if (view == null) {
                        i2 = i3;
                    } else if (view.getTag() instanceof b) {
                        i2 = 0;
                    }
                    if (i2 != 0) {
                        view = layoutInflater.inflate(R.layout.note_noteinfo_item, null);
                        bVar = new b(this);
                        bVar.a = (TextView) view.findViewById(R.id.noteinfo_content_percentTime);
                        bVar.b = (TextView) view.findViewById(R.id.noteinfo_highline);
                        bVar.c = (TextView) view.findViewById(R.id.noteinfo_note);
                        view.setTag(bVar);
                    } else {
                        bVar = (b) view.getTag();
                    }
                    com.qq.reader.readengine.model.b bVar2 = (com.qq.reader.readengine.model.b) this.a.b.get(i);
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(DateFormat.format("yyyy-MM-dd kk:mm:ss", bVar2.i()));
                    bVar.a.setText(stringBuffer2.toString());
                    bVar.b.setText("高亮：" + bVar2.d());
                    if (bVar2.e().length() > 0) {
                        bVar.c.setVisibility(0);
                        bVar.c.setText("笔记：" + bVar2.e());
                        bVar.c.setVisibility(0);
                    } else {
                        bVar.c.setVisibility(8);
                    }
                    view.setOnLongClickListener(this.c);
                    view.setTag(bVar2);
                    break;
            }
            return view;
        }

        private int a(com.qq.reader.framework.a.a aVar) {
            if (aVar == null) {
                return 0;
            }
            List list = (List) this.a.m.b().get(aVar);
            if (list == null) {
                return 0;
            }
            return list.size();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.onebooknote);
        this.d = getApplicationContext();
        this.h = getHandler();
        this.n = (ListView) findViewById(R.id.onenote_list);
        this.p = findViewById(R.id.onenote_list_empty);
        this.l = new a();
        this.n.setAdapter(this.l);
        this.o = (ImageView) findViewById(R.id.note_noteinfo_vertical_line);
        this.o.setVisibility(8);
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OneBookNoteActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
            }
        });
        this.q = (TextView) findViewById(R.id.profile_header_title);
        this.q.setText("我的笔记");
        Button button = (Button) findViewById(R.id.profile_header_right_button);
        button.setText("导出");
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OneBookNoteActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                d.u(this.a.d.getApplicationContext(), true);
                this.a.d().a(view);
            }
        });
        this.h.sendEmptyMessage(4);
        this.i = 100;
    }

    private void b() {
        if (this.i == 100) {
            setResult(0);
            finish();
        } else if (this.i == 101) {
            this.i = 100;
            this.o.setVisibility(8);
            this.q.setText("我的笔记");
            this.n.setDivider(new ColorDrawable(this.d.getResources().getColor(R.color.listview_divider)));
            this.n.setDividerHeight(1);
            this.l.notifyDataSetInvalidated();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        b();
        return true;
    }

    private void c() {
        this.m = s.a().b();
        if (this.m != null) {
            this.c.clear();
            this.a = this.m.a();
            for (Mark mark : i.c().g()) {
                long bookId = mark.getBookId();
                if (bookId <= 0) {
                    bookId = (long) Math.abs(mark.getId().hashCode());
                }
                for (com.qq.reader.framework.a.a aVar : this.a) {
                    if (aVar.a().contains(Long.valueOf(bookId))) {
                        this.c.put(aVar, mark);
                    }
                }
            }
        }
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 4:
                c();
                if (this.m == null) {
                    this.p.setVisibility(0);
                    this.n.setVisibility(8);
                } else if (this.a == null || this.a.size() <= 0) {
                    this.p.setVisibility(0);
                    this.n.setVisibility(8);
                } else {
                    this.n.setVisibility(0);
                    this.p.setVisibility(8);
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    public boolean a(int i) {
        i();
        switch (i) {
            case 0:
                j();
                break;
            case 1:
                break;
            case 2:
                m();
                break;
            default:
                l();
                break;
        }
        k();
        return true;
    }

    private t d() {
        if (this.v == null) {
            this.v = new t(this, 5);
            for (int i = 0; i < w.length; i++) {
                this.v.a(w[i], i);
            }
            this.v.a(this);
        }
        return this.v;
    }

    public void a(String str) {
        if (this.e == null) {
            this.e = new q(this);
            this.e.a(new com.qq.reader.view.q.a(this) {
                final /* synthetic */ OneBookNoteActivity a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                    this.a.b(str);
                }

                public void a() {
                }
            });
        }
        this.e.a(str);
        this.e.f_();
    }

    public void b(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (s.a().a(null, str, currentTimeMillis + "", this.f.f(), this.f.q()) > 0) {
                s.a().a(null, this.f.q(), 0, System.currentTimeMillis(), false);
                this.f.a(str);
                for (b bVar : IBook.mRemarksList) {
                    if (bVar.f() == this.f.f()) {
                        bVar.a(str);
                        bVar.b(currentTimeMillis);
                        break;
                    }
                }
                for (b bVar2 : this.b) {
                    if (bVar2.f() == this.f.f()) {
                        bVar2.a(str);
                        bVar2.b(currentTimeMillis);
                        break;
                    }
                }
                this.l.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
    }

    private void e() {
        if (this.f != null && s.a().a(null, this.f.f(), this.f.q())) {
            s.a().a(null, this.f.q(), 0, System.currentTimeMillis(), false);
            this.b.remove(this.f);
            this.m.a(this.f);
            this.a = this.m.a();
            if (this.b.size() == 0 || this.a.size() == 0) {
                b();
            }
            if (this.a.size() == 0) {
                this.n.setVisibility(8);
                this.p.setVisibility(0);
            }
            this.l.notifyDataSetInvalidated();
        }
    }

    private void f() {
        if (this.f != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("《");
            stringBuffer.append(this.f.c());
            stringBuffer.append("》笔记");
            long q = this.f.q();
            String str = "";
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(this.f.d());
            if (this.f.e().length() > 0) {
                stringBuffer2.append("--笔记：");
                stringBuffer2.append(this.f.e());
            }
            if (q > 0) {
                new aj(this, String.valueOf(q), stringBuffer.toString(), stringBuffer2.toString(), 12).f_();
            } else {
                new aj(this, str, stringBuffer.toString(), stringBuffer2.toString(), 12).f_();
            }
        }
    }

    public com.qq.reader.view.linearmenu.a a() {
        if (this.r != null) {
            return this.r;
        }
        this.r = new com.qq.reader.view.linearmenu.b(this);
        this.r.a(0, getResources().getString(R.string.note_text_edit), null);
        this.r.a(1, getResources().getString(R.string.note_text_share), null);
        this.r.a(2, getResources().getString(R.string.note_text_del), null);
        this.r.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ OneBookNoteActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                switch (i) {
                    case 0:
                        j.a(30, 3);
                        com.qq.reader.common.monitor.i.a("event_D31", null, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_D31", null);
                        this.a.a(this.a.f.e());
                        return true;
                    case 1:
                        j.a(31, 3);
                        com.qq.reader.common.monitor.i.a("event_D32", null, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_D32", null);
                        this.a.f();
                        return true;
                    case 2:
                        j.a(32, 3);
                        com.qq.reader.common.monitor.i.a("event_D33", null, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_D33", null);
                        this.a.e();
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.r.a(new OnCancelListener(this) {
            final /* synthetic */ OneBookNoteActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getWindow().closeAllPanels();
            }
        });
        return this.r;
    }

    private void g() {
        String str = (am.b() + "/QQReader") + File.separator + this.y + ".txt";
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            this.x = new FileWriter(str);
            this.x.write(new String(new byte[]{(byte) -17, (byte) -69, (byte) -65}));
        } catch (Exception e) {
            if (this.x != null) {
                try {
                    this.x.close();
                    this.x = null;
                } catch (IOException e2) {
                }
            }
        }
    }

    private void h() {
        if (this.x != null) {
            try {
                this.x.close();
            } catch (IOException e) {
            }
        }
        this.x = null;
    }

    private void c(String str) {
        try {
            if (this.x == null) {
                g();
            }
            if (str != null && str.length() > 0 && this.x != null) {
                this.x.write(str);
                this.x.flush();
            }
        } catch (IOException e) {
        }
    }

    private void i() {
        String str = null;
        int i = 0;
        g();
        c("  我的" + getResources().getString(R.string.app_name) + "笔记\r\n");
        StringBuffer stringBuffer = null;
        for (b bVar : this.b) {
            String str2;
            StringBuffer stringBuffer2;
            int i2;
            if (bVar.j() == 0) {
                if (!(i <= 0 || stringBuffer == null || str == bVar.c())) {
                    c(stringBuffer.toString());
                }
                i++;
                stringBuffer = new StringBuffer();
                if (str == null || str != bVar.c()) {
                    str = bVar.c();
                    stringBuffer.append("\r\n《" + bVar.c() + "》\r\n");
                    str2 = str;
                    stringBuffer2 = stringBuffer;
                    i2 = i;
                } else {
                    str2 = str;
                    stringBuffer2 = stringBuffer;
                    i2 = i;
                }
            } else {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer();
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(DateFormat.format("    yyyy-MM-dd kk:mm:ss\r\n", bVar.i()));
                stringBuffer.append(stringBuffer3);
                stringBuffer.append("    摘要：" + bVar.d());
                stringBuffer.append("\r\n");
                if (bVar.e().length() > 0) {
                    stringBuffer.append("    笔记：");
                    stringBuffer.append(bVar.e());
                    stringBuffer.append("\r\n\r\n");
                }
                str2 = str;
                stringBuffer2 = stringBuffer;
                i2 = i;
            }
            i = i2;
            stringBuffer = stringBuffer2;
            str = str2;
        }
        if (stringBuffer != null) {
            c(stringBuffer.toString());
        }
        h();
    }

    private void j() {
        this.z = new LocalMark(this.y, (am.b() + "/QQReader") + File.separator + "note.txt", 0, 1, false);
        this.z.setStarPointStr(Mark.HEADPAGE_FLAG);
        this.z.setPercentStr("0.0%").setAuthor("QQ:1000001");
        i.c().a(this.z, true);
        AlertDialog a = new com.qq.reader.view.AlertDialog.a(this).c(17301543).a((CharSequence) "提示").b((CharSequence) "笔记已导出到书架。").a((CharSequence) "立即阅读", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ OneBookNoteActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.n();
                this.a.finish();
            }
        }).b((CharSequence) "关闭", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ OneBookNoteActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.finish();
            }
        }).a();
        if (a != null && !isFinishing()) {
            a.show();
        }
    }

    private void k() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", "我的" + getResources().getString(R.string.app_name) + "笔记");
        File file = new File((am.b() + "/QQReader") + File.separator + this.y + ".txt");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        if (file.getName().endsWith(".gz")) {
            intent.setType("application/x-gzip");
        } else if (file.getName().endsWith(".txt")) {
            intent.setType("text/plain");
        } else {
            intent.setType("application/octet-stream");
        }
        intent.putExtra("android.intent.extra.EMAIL", "739968605@qq.com");
        intent.putExtra("android.intent.extra.SUBJECT", "我的" + getResources().getString(R.string.app_name) + "笔记");
        intent.putExtra("android.intent.extra.TEXT", "我的" + getResources().getString(R.string.app_name) + "笔记，见附件");
        startActivity(Intent.createChooser(intent, "选择邮箱"));
    }

    private void l() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", "我的" + getResources().getString(R.string.app_name) + "笔记");
        File file = new File((am.b() + "/QQReader") + File.separator + this.y + ".txt");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        if (file.getName().endsWith(".gz")) {
            intent.setType("application/x-gzip");
        } else if (file.getName().endsWith(".txt")) {
            intent.setType("text/plain");
        } else {
            intent.setType("application/octet-stream");
        }
        startActivity(Intent.createChooser(intent, "选择导出应用"));
    }

    private void m() {
        Mark localMark = new LocalMark(this.y, (am.b() + "/QQReader/") + File.separator + this.y + ".txt", 0, 1, false);
        WXApiManager.getInstance(this.d).registerWX();
        WXApiManager.getInstance(this.d).sendBookToWX(this.d, localMark, null);
    }

    private void n() {
        Mark mark = this.z;
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (new File(mark.getId()).exists() && 4 != mark.getType()) {
            bundle.putString("filepath", mark.getId());
            bundle.putString("filename", mark.getBookName());
            bundle.putString("fileauthor", mark.getAuthor());
            bundle.putInt("fileencode", mark.getEncoding());
            intent.putExtras(bundle);
            com.qq.reader.b.a(intent, this);
        } else if (4 == mark.getType()) {
            Parcelable a = v.b().a(mark.getId());
            bundle.putString("filepath", mark.getId());
            bundle.putString("filename", mark.getBookName());
            intent.putExtras(bundle);
            intent.putExtra("com.qq.reader.OnlineTag", a);
            intent.putExtra("com.qq.reader.fromonline", true);
            com.qq.reader.b.a(intent, this);
        } else {
            af.a(getApplicationContext(), (CharSequence) "没有找到本书，请检查SDCard", 0).a();
        }
    }
}
