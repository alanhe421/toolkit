package com.qq.reader.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.login.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.BookSetPrivateTask;
import com.qq.reader.common.utils.networkUtil.e;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.model.BookSercetModel;
import com.qq.reader.plugin.audiobook.MusicBookGroup;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryBooksActivity extends AbsBaseBookListActivity implements OnScrollListener, OnItemClickListener, OnItemLongClickListener {
    private TextView A;
    private TextView B;
    private Button C;
    private View D = null;
    private View E = null;
    private List<Mark> F;
    private ArrayList<Mark> G = new ArrayList();
    private int H = 10101;
    private int I = i.a;
    private boolean J = true;
    private boolean K = false;
    private final int L = 0;
    private final int s = 306;
    private TextView t = null;
    private ListView u = null;
    private LinearLayout v;
    private LinearLayout w;
    private TextView x;
    private TextView y;
    private TextView z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.category_detail_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.H = extras.getInt("category_books_mode");
        }
        g();
        b();
    }

    private void b() {
        c(this.I);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.common_list_item_height);
        switch (this.H) {
            case 10101:
                this.u.setPadding(0, 0, 0, 0);
                return;
            case 10102:
                c();
                e(this.H);
                j();
                this.u.setPadding(0, 0, 0, dimensionPixelOffset);
                return;
            case 10103:
                e();
                e(this.H);
                i();
                this.u.setPadding(0, 0, 0, dimensionPixelOffset);
                return;
            default:
                return;
        }
    }

    private void c() {
        this.w = (LinearLayout) findViewById(R.id.booklist_bottom_add_next);
        this.z = (TextView) findViewById(R.id.manage_next_add);
        this.A = (TextView) findViewById(R.id.manage_next_select_all);
        this.A.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CategoryBooksActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.F == null) {
                    return;
                }
                if (this.a.k() == this.a.G.size()) {
                    this.a.G.clear();
                    this.a.b.notifyDataSetChanged();
                    this.a.j();
                    return;
                }
                this.a.a();
                this.a.b.notifyDataSetChanged();
                this.a.j();
            }
        });
        this.z.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CategoryBooksActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                MetroItem k = i.c().k();
                if (k != null) {
                    if (this.a.G != null && this.a.G.size() > 0) {
                        Mark mark = null;
                        int i = 0;
                        while (i < this.a.G.size()) {
                            Mark mark2;
                            if (this.a.G.get(i) instanceof Mark) {
                                mark2 = (Mark) this.a.G.get(i);
                            } else {
                                mark2 = mark;
                            }
                            if (mark2 != null && i.c().d(mark2.getId(), k.getId())) {
                                mark2.setCategoryID(k.getId());
                            }
                            i++;
                            mark = mark2;
                        }
                    }
                    this.a.e(10101);
                    Message obtain = Message.obtain();
                    obtain.arg1 = k.getId();
                    obtain.obj = k.getName();
                    obtain.what = 20004;
                    this.a.mHandler.sendMessage(obtain);
                    return;
                }
                f.b("mButtonNextAdd", "mButtonNextAdd ERROR lastMetroItem == null");
            }
        });
    }

    private Activity d() {
        return this;
    }

    private void e() {
        this.v = (LinearLayout) findViewById(R.id.booklist_bottom_manage);
        this.C = (Button) findViewById(R.id.profile_header_right_button);
        this.C.setVisibility(0);
        this.x = (TextView) findViewById(R.id.manage_move_to);
        this.y = (TextView) findViewById(R.id.manage_del);
        this.B = (TextView) findViewById(R.id.manage_private);
        this.C.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CategoryBooksActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.F != null) {
                    this.a.k();
                    this.a.G.size();
                    if (this.a.k() == this.a.G.size()) {
                        this.a.G.clear();
                        this.a.b.notifyDataSetChanged();
                        this.a.i();
                        return;
                    }
                    this.a.a();
                    this.a.b.notifyDataSetChanged();
                    this.a.i();
                }
            }
        });
        this.x.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CategoryBooksActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(this.a.G).f_();
                j.a(2, 0);
            }
        });
        this.y.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CategoryBooksActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.showFragmentDialog(306);
                j.a(3, 0);
            }
        });
        this.B.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CategoryBooksActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.J) {
                    com.qq.reader.common.monitor.i.a("event_D137", null, ReaderApplication.getApplicationImp());
                } else {
                    com.qq.reader.common.monitor.i.a("event_D138", null, ReaderApplication.getApplicationImp());
                }
                if (this.a.G == null || this.a.G.size() < 1) {
                    af.a(this.a.getApplicationContext(), (CharSequence) "请选择一本书", 1).a();
                } else if (!e.a(this.a.getApplicationContext())) {
                    af.a(this.a.getApplicationContext(), (CharSequence) "网络异常，请稍后重试", 1).a();
                } else if (c.b()) {
                    this.a.mHandler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass8 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.f();
                        }
                    });
                } else {
                    final String R = d.R(this.a.getApplicationContext());
                    ((ReaderBaseActivity) this.a.d()).setLoginNextTask(new a(this) {
                        final /* synthetic */ AnonymousClass8 b;

                        public void a(int i) {
                            switch (i) {
                                case 1:
                                    String R = d.R(this.b.a.getApplicationContext());
                                    if (R != null && R.equals(R)) {
                                        this.b.a.mHandler.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                this.a.b.a.f();
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
                    ((ReaderBaseActivity) this.a.d()).startLogin();
                }
            }
        });
    }

    private void f() {
        int i = 0;
        final ArrayList arrayList = new ArrayList();
        if (this.J) {
            for (int i2 = 0; i2 < this.G.size(); i2++) {
                BookSercetModel bookSercetModel = new BookSercetModel();
                bookSercetModel.a(((Mark) this.G.get(i2)).getBookId() + "");
                bookSercetModel.a(0);
                arrayList.add(bookSercetModel);
            }
        } else {
            while (i < this.G.size()) {
                BookSercetModel bookSercetModel2 = new BookSercetModel();
                bookSercetModel2.a(((Mark) this.G.get(i)).getBookId() + "");
                bookSercetModel2.a(1);
                arrayList.add(bookSercetModel2);
                i++;
            }
        }
        g.a().a(new BookSetPrivateTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ CategoryBooksActivity b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = arrayList;
                this.b.mHandler.sendMessage(obtain);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }, arrayList));
    }

    private void g() {
        Bundle extras = getIntent().getExtras();
        this.I = extras.getInt("category_id");
        CharSequence string = extras.getString("category_name");
        this.t = (TextView) findViewById(R.id.profile_header_title);
        this.t.setText(string);
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CategoryBooksActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.u = (ListView) findViewById(R.id.category_detail_list);
        this.u.setOnScrollListener(this);
        this.u.setOnItemClickListener(this);
        this.u.setOnItemLongClickListener(this);
        this.C = (Button) findViewById(R.id.profile_header_right_button);
        h();
        this.b = new com.qq.reader.module.bookshelf.a.a.a(getApplicationContext());
        ((com.qq.reader.module.bookshelf.a.a.a) this.b).b(this.H);
        this.u.setAdapter(this.b);
    }

    private void h() {
        this.E = findViewById(R.id.no_book_layout);
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.u.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.b.getCount()) {
            this.c = new com.qq.reader.view.linearmenu.d(this);
            b(headerViewsCount);
        }
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.u.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.b.getCount()) {
            a(headerViewsCount);
        }
    }

    public void c(int i) {
        if (this.b.e() > 0) {
            this.b.d();
        }
        this.I = i;
        if (i == 10001) {
            this.F = i.c().h();
        } else if (i == Constants.CODE_LOGIC_REGISTER_IN_PROCESS) {
            this.F = i.c().i();
        } else if (i == i.a) {
            this.F = i.c().g();
        } else {
            this.F = i.c().c(i);
            if (i == i.b) {
                List<Mark> c = i.c().c(i.c);
                if (c != null && c.size() > 0) {
                    for (Mark mark : c) {
                        if (mark != null) {
                            this.F.add(mark);
                        }
                    }
                }
            }
        }
        if (this.F == null) {
            return;
        }
        if (this.F.size() > 0) {
            this.E.setVisibility(8);
            this.b.a(this.F);
            return;
        }
        this.E.setVisibility(0);
    }

    public void onResume() {
        super.onResume();
        c(this.I);
        this.b.notifyDataSetInvalidated();
    }

    public void finish() {
        super.finish();
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                finish();
                return true;
            default:
                return false;
        }
    }

    private void i() {
        if (this.v != null && this.v.getVisibility() == 0) {
            if (this.G.size() > 0) {
                this.y.setText("删除(" + this.G.size() + ")");
                this.y.setTextColor(getResources().getColor(R.color.text_color_c401));
                this.y.setEnabled(true);
                this.x.setText("分组至(" + this.G.size() + ")");
                this.x.setTextColor(getResources().getColor(R.color.text_color_c101));
                this.x.setEnabled(true);
            } else {
                this.y.setText("删除");
                this.y.setTextColor(getResources().getColor(R.color.text_color_c103));
                this.y.setEnabled(false);
                this.x.setText("分组至");
                this.x.setTextColor(getResources().getColor(R.color.text_color_c103));
                this.x.setEnabled(false);
            }
            if (this.F != null) {
                if (this.F.size() == 0) {
                    this.C.setText("全选");
                    this.C.setVisibility(8);
                } else {
                    if (k() == this.G.size()) {
                        this.C.setText("取消全选");
                    } else {
                        this.C.setText("全选");
                    }
                    this.C.setVisibility(0);
                }
                if (this.F.size() > 0) {
                    this.C.setTextColor(getResources().getColor(R.color.text_color_c104));
                    this.C.setEnabled(true);
                } else {
                    this.C.setTextColor(getResources().getColor(R.color.text_color_c103));
                    this.C.setEnabled(false);
                }
            }
            if (this.G == null) {
                return;
            }
            if (this.G.size() == 0) {
                this.B.setText("开启私密设置");
                this.B.setTextColor(getResources().getColor(R.color.text_color_c103));
                this.B.setEnabled(false);
                return;
            }
            this.J = false;
            Iterator it = this.G.iterator();
            while (it.hasNext()) {
                if (((Mark) it.next()).getPrivateProperty() == 1) {
                    this.J = true;
                    break;
                }
            }
            if (this.J) {
                this.B.setText("开启私密设置");
            } else {
                this.B.setText("关闭私密设置");
            }
            this.K = true;
            it = this.G.iterator();
            while (it.hasNext()) {
                if (((Mark) it.next()).getBookId() > 0) {
                    this.K = false;
                    break;
                }
            }
            if (this.K) {
                this.B.setTextColor(getResources().getColor(R.color.text_color_c103));
                this.B.setEnabled(false);
                return;
            }
            this.B.setTextColor(getResources().getColor(R.color.text_color_c101));
            this.B.setEnabled(true);
        }
    }

    private void j() {
        if (this.w != null && this.w.getVisibility() == 0) {
            if (this.G.size() == 0) {
                this.z.setEnabled(false);
                this.z.setText(d(-1));
                this.z.setTextColor(getResources().getColor(R.color.text_color_c103));
            } else {
                this.z.setEnabled(true);
                this.z.setText(d(this.G.size()));
                this.z.setTextColor(getResources().getColor(R.color.text_color_c101));
            }
            if (this.F == null) {
                return;
            }
            if (k() == this.G.size()) {
                this.A.setText("取消全选");
            } else {
                this.A.setText("全选");
            }
        }
    }

    private String d(int i) {
        StringBuffer stringBuffer = new StringBuffer("添加");
        if (i >= 0) {
            stringBuffer.append("(");
            stringBuffer.append(i);
            stringBuffer.append(")");
        }
        return stringBuffer.toString();
    }

    private int k() {
        if (this.F == null) {
            return 0;
        }
        return this.F.size();
    }

    private void e(int i) {
        this.H = i;
        switch (i) {
            case 10101:
                if (this.G != null) {
                    this.G.clear();
                }
                if (this.v != null) {
                    this.v.setVisibility(8);
                }
                if (this.w != null) {
                    this.w.setVisibility(8);
                    return;
                }
                return;
            case 10102:
                if (this.w != null) {
                    this.w.setVisibility(0);
                    return;
                }
                return;
            case 10103:
                if (this.v != null) {
                    this.v.setVisibility(0);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a() {
        this.G.clear();
        for (Mark add : this.F) {
            this.G.add(add);
        }
        ((com.qq.reader.module.bookshelf.a.a.a) this.b).a(this.G);
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        Dialog dialog = null;
        switch (i) {
            case 306:
                View inflate = LayoutInflater.from(this).inflate(R.layout.delete_file, null);
                final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.confirm_check);
                ((TextView) inflate.findViewById(R.id.confirm_text)).setText(R.string.bookstand_dialog_select_del);
                checkBox.setChecked(false);
                dialog = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_remove).a(inflate).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ CategoryBooksActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        new a(new a.a(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i, Object obj) {
                                Message obtainMessage = this.a.b.mHandler.obtainMessage();
                                obtainMessage.obj = obj;
                                obtainMessage.what = i;
                                this.a.b.mHandler.sendMessage(obtainMessage);
                            }
                        }).a(this.b.G, checkBox.isChecked());
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ CategoryBooksActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
        }
        return dialog != null ? dialog : super.createDialog(i, bundle);
    }

    public void a(int i) {
        Object item = this.b.getItem(i);
        if (item != null && (item instanceof Mark)) {
            Mark mark = (Mark) item;
            if (mark != null && (this.H == 10102 || this.H == 10103)) {
                if (!(mark instanceof MusicBookGroup)) {
                    if (this.G.contains(mark)) {
                        this.G.remove(mark);
                    } else {
                        this.G.add(mark);
                    }
                    ((com.qq.reader.module.bookshelf.a.a.a) this.b).a(this.G);
                    this.b.notifyDataSetChanged();
                    j();
                    i();
                    return;
                }
                return;
            }
        }
        super.a(i);
    }

    public boolean b(int i) {
        if (this.H == 10102 || this.H == 10103) {
            return true;
        }
        return super.b(i);
    }

    public boolean handleMessageImp(Message message) {
        int i;
        switch (message.what) {
            case 0:
                final ArrayList arrayList = (ArrayList) message.obj;
                if (arrayList != null && arrayList.size() > 0) {
                    for (i = 0; i < arrayList.size(); i++) {
                        BookSercetModel bookSercetModel = (BookSercetModel) arrayList.get(i);
                        this.b.a(bookSercetModel.b(), bookSercetModel.a());
                    }
                    g.a().a(new ReaderDBTask() {
                        public void run() {
                            for (int i = 0; i < arrayList.size(); i++) {
                                BookSercetModel bookSercetModel = (BookSercetModel) arrayList.get(i);
                                i.c().b(bookSercetModel.b(), bookSercetModel.a());
                            }
                        }
                    });
                    this.G.clear();
                    i();
                    this.b.notifyDataSetChanged();
                }
                if (this.J) {
                    af.a(getApplicationContext(), (CharSequence) "已开启私密阅读", 0).a();
                } else {
                    af.a(getApplicationContext(), (CharSequence) "已关闭私密阅读", 0).a();
                }
                return true;
            case 20004:
                i = message.arg1;
                String str = (String) message.obj;
                this.H = 10101;
                ((com.qq.reader.module.bookshelf.a.a.a) this.b).b(this.H);
                c(i);
                this.b.notifyDataSetInvalidated();
                this.t.setText(str);
                this.C.setVisibility(8);
                this.u.setPadding(0, 0, 0, 0);
                return true;
            case 70001:
                c(this.I);
                this.G.clear();
                i();
                this.b.notifyDataSetChanged();
                break;
            case 70002:
                c(this.I);
                break;
        }
        return super.handleMessageImp(message);
    }

    protected void a(Mark mark) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(mark);
        a(arrayList).f_();
    }

    protected void a(MetroItem metroItem) {
        e(10101);
        Message obtain = Message.obtain();
        obtain.arg1 = metroItem.getId();
        obtain.obj = metroItem.getName();
        obtain.what = 20004;
        this.mHandler.sendMessage(obtain);
    }
}
