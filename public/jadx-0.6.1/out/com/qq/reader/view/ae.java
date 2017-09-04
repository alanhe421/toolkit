package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.aj;
import com.qq.reader.common.utils.ao;
import com.qq.reader.readengine.b.c;
import com.qq.reader.readengine.b.c.b;
import com.qq.reader.readengine.b.d;
import com.qq.reader.readengine.fileparse.e;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ReaderTextSearchDlg */
public class ae extends BaseDialog implements OnItemClickListener, b {
    private TextView a;
    private ImageButton b;
    private ImageButton c;
    private EditText d;
    private a e;
    private Context i;
    private ListView j;
    private e k;
    private String l;
    private View m;
    private View n;
    private TextView o;
    private ViewGroup p;
    private ViewGroup q;
    private View r;
    private View s;
    private Handler t = new Handler(this) {
        final /* synthetic */ ae a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 601) {
                d dVar = (d) message.obj;
                synchronized (this.a) {
                    this.a.e.a(dVar);
                }
            } else if (message.what == 602) {
                this.a.e.a(c.e());
                if (this.a.e.getCount() > 0) {
                    this.a.m.setVisibility(8);
                    this.a.n.setVisibility(8);
                    this.a.a.setVisibility(0);
                    this.a.j.setVisibility(0);
                    this.a.a.setText(String.format(this.a.i.getResources().getString(R.string.search_results), new Object[]{Integer.valueOf(this.a.e.getCount())}));
                    if (this.a.e.getCount() >= http.Internal_Server_Error) {
                        this.a.j.removeFooterView(this.a.p);
                        this.a.j.addFooterView(this.a.r);
                        this.a.o.setVisibility(8);
                        this.a.q.setVisibility(8);
                    } else {
                        this.a.l();
                    }
                    this.a.e.notifyDataSetChanged();
                    return;
                }
                this.a.l();
            } else if (message.what == ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE) {
                this.a.e.a(c.e());
                if (this.a.e.getCount() > 0) {
                    this.a.m.setVisibility(8);
                    this.a.n.setVisibility(8);
                    this.a.a.setVisibility(0);
                    this.a.j.setVisibility(0);
                    this.a.j.removeFooterView(this.a.p);
                    this.a.j.addFooterView(this.a.r);
                    this.a.a.setText(String.format(this.a.i.getResources().getString(R.string.search_results), new Object[]{Integer.valueOf(this.a.e.getCount())}));
                    this.a.e.notifyDataSetChanged();
                } else {
                    this.a.m.setVisibility(8);
                    this.a.n.setVisibility(0);
                    this.a.a.setVisibility(8);
                    this.a.j.setVisibility(8);
                }
                this.a.o.setVisibility(8);
                this.a.q.setVisibility(8);
            }
        }
    };
    private b u;

    /* compiled from: ReaderTextSearchDlg */
    private class a extends BaseAdapter {
        SpannableStringBuilder a = new SpannableStringBuilder();
        final /* synthetic */ ae b;
        private ArrayList<d> c = new ArrayList();

        public a(ae aeVar) {
            this.b = aeVar;
        }

        public void a(d dVar) {
            if (dVar != null) {
                this.c.add(dVar);
            }
        }

        public void a(List<d> list) {
            if (list != null) {
                this.c.addAll(list);
            }
        }

        public void a() {
            this.c.clear();
        }

        public int getCount() {
            if (this.c == null) {
                return 0;
            }
            int size;
            synchronized (this.b) {
                size = this.c.size();
            }
            return size;
        }

        public Object getItem(int i) {
            if (i >= getCount() || i < 0) {
                return null;
            }
            Object obj;
            synchronized (this.c) {
                obj = this.c.get(i);
            }
            return obj;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int indexOf;
            if (view == null) {
                view = (ViewGroup) LayoutInflater.from(this.b.i).inflate(R.layout.searchlistitem, viewGroup, false);
            } else {
                ViewGroup viewGroup2 = (ViewGroup) view;
            }
            d dVar = (d) getItem(i);
            ((TextView) view.findViewById(R.id.percent)).setText(ao.a(dVar.d()));
            TextView textView = (TextView) view.findViewById(R.id.searchContext);
            this.a = new SpannableStringBuilder(dVar.f());
            int g = dVar.g();
            int length = this.b.l.length() + g;
            if (g == -1) {
                indexOf = dVar.f().indexOf(this.b.l);
            } else {
                indexOf = g;
            }
            this.a.setSpan(new BackgroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_common_textcolor)), indexOf, length, 33);
            this.a.setSpan(new ForegroundColorSpan(-1), indexOf, length, 34);
            textView.setText(this.a);
            return view;
        }
    }

    public boolean g() {
        int height = this.s.getHeight();
        if (height <= (com.qq.reader.common.c.a.bT * 2) / 3) {
            this.d.setFocusableInTouchMode(true);
            this.d.setFocusable(true);
            this.d.requestFocus();
            com.qq.reader.common.monitor.debug.c.e("TAG", "show soft input");
            return ao.e.a(this.d, this.i);
        }
        com.qq.reader.common.monitor.debug.c.e("TAG", "lHeight = " + height);
        return false;
    }

    public ae(final Activity activity) {
        this.i = activity;
        if (this.f == null) {
            a(activity, null, R.layout.searchdialog, 0, true);
            this.f.getWindow().setSoftInputMode(16);
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    c.a().a(true);
                }
            });
            this.f.setOnKeyListener(new OnKeyListener(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 82) {
                        return true;
                    }
                    return false;
                }
            });
            this.a = (TextView) this.f.findViewById(R.id.search_header);
            this.d = (EditText) this.f.findViewById(R.id.reader_searchBar);
            this.d.setHint("搜索");
            this.d.addTextChangedListener(new TextWatcher(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    this.a.j();
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                }
            });
            this.d.setOnEditorActionListener(new OnEditorActionListener(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 3 && i != 0) {
                        return false;
                    }
                    this.a.k();
                    return true;
                }
            });
            this.b = (ImageButton) this.f.findViewById(R.id.reader_searchbtn);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.k();
                }
            });
            this.c = (ImageButton) this.f.findViewById(R.id.clear_text_btn);
            this.c.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        this.a.d.setText("");
                    }
                    return false;
                }
            });
            this.f.findViewById(R.id.reader_search_header_back).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.h();
                    new Handler().postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass8 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.cancel();
                        }
                    }, 500);
                }
            });
            j();
            this.j = (ListView) this.f.findViewById(R.id.searchlist);
            this.j.setOnItemClickListener(this);
            this.p = (ViewGroup) LayoutInflater.from(this.i).inflate(R.layout.searchlist_footer, null);
            this.o = (TextView) this.p.findViewById(R.id.search_more_tv);
            this.q = (ViewGroup) this.p.findViewById(R.id.search_more_progress);
            this.j.addFooterView(this.p);
            this.e = new a(this);
            this.j.setAdapter(this.e);
            this.r = LayoutInflater.from(this.i).inflate(R.layout.searchlist_footer_ok, null);
            this.m = this.f.findViewById(R.id.search_tip_input_keyword);
            this.n = this.f.findViewById(R.id.search_tip_no_result);
            this.j.setOnScrollListener(new OnScrollListener(this) {
                final /* synthetic */ ae a;

                {
                    this.a = r1;
                }

                public void onScrollStateChanged(AbsListView absListView, int i) {
                }

                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    if (i3 == i2 + i) {
                        this.a.m();
                    }
                }
            });
            this.s = this.f.findViewById(R.id.search_dialog_content);
            aj.a(this.f, false);
            a(new OnShowListener(this) {
                final /* synthetic */ ae b;

                public void onShow(DialogInterface dialogInterface) {
                    aj.b(activity, false);
                }
            });
        }
    }

    private void j() {
        if (this.c != null) {
            if (this.d.getText().toString().length() > 0) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(4);
            }
        }
    }

    public void a(e eVar) {
        this.k = eVar;
    }

    private void k() {
        h();
        this.l = this.d.getText().toString();
        if (this.l == null || this.l.trim().length() == 0) {
            af.a(this.i.getApplicationContext(), "搜索关键词不能为空", 0).a();
            return;
        }
        try {
            i();
            this.e.a();
            this.e.notifyDataSetChanged();
            c.a().a((b) this);
            c.a().a(this.k, this.l, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void h() {
        ((InputMethodManager) this.i.getSystemService("input_method")).hideSoftInputFromWindow(this.d.getWindowToken(), 2);
    }

    protected void i() {
        this.j.removeFooterView(this.r);
        if (this.j.getFooterViewsCount() == 0) {
            this.j.addFooterView(this.p);
            this.j.setAdapter(this.e);
        }
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.a.setVisibility(0);
        this.a.setText(String.format(this.i.getResources().getString(R.string.search_results), new Object[]{Integer.valueOf(0)}));
        this.j.setVisibility(0);
        this.p.setVisibility(0);
        this.o.setVisibility(8);
        this.q.setVisibility(0);
    }

    private void l() {
        this.o.setVisibility(0);
        this.q.setVisibility(8);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.u == null) {
            return;
        }
        if (i == this.e.getCount()) {
            m();
        } else if (this.q.getVisibility() != 0) {
            this.u.a((d) this.e.getItem(i));
        }
    }

    private void m() {
        if (this.o.getVisibility() == 0) {
            this.o.setVisibility(8);
            this.q.setVisibility(0);
            try {
                c.a().a(this.k, this.l, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(c cVar, int i, d dVar) {
        Message obtain = Message.obtain();
        if (i == 601) {
            obtain.what = 601;
            obtain.obj = dVar;
            this.t.sendMessage(obtain);
        } else if (i == 602) {
            obtain.what = 602;
            this.t.sendMessage(obtain);
        } else {
            obtain.what = ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE;
            this.t.sendMessage(obtain);
        }
    }

    public void a(b bVar) {
        this.u = bVar;
    }
}
