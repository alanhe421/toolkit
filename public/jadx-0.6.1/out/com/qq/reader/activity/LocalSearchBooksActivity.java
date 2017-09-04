package com.qq.reader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.c.a;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class LocalSearchBooksActivity extends AbsBaseBookListActivity implements OnScrollListener, OnItemClickListener, OnItemLongClickListener {
    private InputMethodManager A = null;
    private ListView B = null;
    private List<Mark> C;
    private ArrayList<Mark> D = new ArrayList();
    private int E = 10101;
    private int F = i.a;
    private List<Mark> G = new ArrayList();
    private View H;
    private Button I;
    private Handler J = new Handler(this) {
        final /* synthetic */ LocalSearchBooksActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    try {
                        Mark[] markArr = (Mark[]) message.obj;
                        this.a.b.d();
                        this.a.b.a(markArr);
                        this.a.b.notifyDataSetInvalidated();
                        return;
                    } catch (Exception e) {
                        return;
                    }
                case 2:
                    this.a.v.dismissDropDown();
                    return;
                default:
                    return;
            }
        }
    };
    String s = "请输入书名或作者名";
    private final int t = 307;
    private ViewGroup u;
    private AutoCompleteTextView v;
    private ImageButton w;
    private ImageButton x;
    private String y = "";
    private View z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.local_search);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.E = extras.getInt("category_books_mode");
        }
        b();
        c();
        this.G = i.c().g();
        for (int i = 0; i < this.G.size(); i++) {
            ((Mark) this.G.get(i)).generatePinyin();
        }
    }

    private void b() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.F = extras.getInt("category_id");
        }
        this.A = (InputMethodManager) getSystemService("input_method");
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LocalSearchBooksActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.u = (ViewGroup) findViewById(R.id.websearchBar);
        this.z = findViewById(R.id.websearching_bg);
        this.z.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ LocalSearchBooksActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.z.setVisibility(8);
                if (this.a.A != null && this.a.A.isActive()) {
                    this.a.A.hideSoftInputFromWindow(this.a.v.getWindowToken(), 0);
                }
                return true;
            }
        });
        this.v = (AutoCompleteTextView) findViewById(R.id.searchBar);
        this.v.setHint(this.s);
        if (a.bU > 480) {
            this.v.setDropDownVerticalOffset(this.a.getResources().getDimensionPixelOffset(R.dimen.common_dp_7));
        } else {
            this.v.setDropDownVerticalOffset(this.a.getResources().getDimensionPixelOffset(R.dimen.common_dp_7_5));
        }
        this.v.setDropDownBackgroundResource(R.drawable.autocomplete_dropdown_bg);
        this.v.setText(this.y);
        if (this.v != null) {
            this.v.setSelection(this.y.length());
        }
        this.v.setThreshold(1);
        this.v.setOnTouchListener(new OnTouchListener(this) {
            int a = -1;
            long b = -1;
            final /* synthetic */ LocalSearchBooksActivity c;

            {
                this.c = r3;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.b = System.currentTimeMillis();
                        this.a = 1000;
                        break;
                    case 1:
                        long currentTimeMillis = System.currentTimeMillis() - this.b;
                        if (this.a == 1000 && currentTimeMillis < ((long) ViewConfiguration.getLongPressTimeout())) {
                            this.c.z.setVisibility(0);
                            break;
                        }
                    case 3:
                        break;
                    case 4:
                        this.a = -1;
                        break;
                }
                this.a = -1;
                if (motionEvent.getAction() == 3) {
                    this.c.A.hideSoftInputFromWindow(this.c.getCurrentFocus().getWindowToken(), 0);
                    this.c.A.showSoftInput(view, 0);
                }
                return false;
            }
        });
        this.v.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ LocalSearchBooksActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            }
        });
        this.v.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ LocalSearchBooksActivity a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.a.d();
                if (this.a.y == null || !this.a.y.equals(charSequence.toString())) {
                    this.a.y = charSequence.toString().trim();
                    if (this.a.y.length() < 1) {
                        this.a.b.d();
                        this.a.b.notifyDataSetInvalidated();
                        this.a.d();
                    } else if (charSequence != null && charSequence.toString().trim().length() > 0) {
                        this.a.a(charSequence.toString().trim());
                    }
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.v.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ LocalSearchBooksActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i) {
                    case 0:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        this.a.a(this.a.y);
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.w = (ImageButton) findViewById(R.id.searchBtn);
        this.w.setOnClickListener(new c(this) {
            final /* synthetic */ LocalSearchBooksActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.a(this.a.y);
            }
        });
        this.x = (ImageButton) findViewById(R.id.clearTextBtn);
        this.x.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ LocalSearchBooksActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                this.a.v.setText("");
                this.a.y = "";
                this.a.b.d();
                this.a.b.notifyDataSetInvalidated();
                this.a.d();
                return true;
            }
        });
        d();
        this.B = (ListView) findViewById(R.id.category_detail_list);
        this.B.setOnScrollListener(this);
        this.B.setOnItemClickListener(this);
        this.B.setOnItemLongClickListener(this);
        this.b = new com.qq.reader.module.bookshelf.a.a.a(getApplicationContext());
        ((com.qq.reader.module.bookshelf.a.a.a) this.b).b(this.E);
        this.B.setAdapter(this.b);
        a(true);
    }

    private void c() {
        getResources().getDimension(R.dimen.common_list_item_height);
        switch (this.E) {
            case 10101:
                this.B.setPadding(0, 0, 0, 0);
                return;
            default:
                return;
        }
    }

    private void d() {
        if (this.x != null) {
            if (this.v.getText().toString().length() > 0) {
                this.x.setVisibility(0);
            } else {
                this.x.setVisibility(4);
            }
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.B.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.b.getCount()) {
            a(headerViewsCount);
        }
    }

    public void c(int i) {
        if (this.b.e() > 0) {
            this.b.d();
        }
        this.F = i;
        if (i == 10001) {
            this.C = i.c().h();
        } else if (i == Constants.CODE_LOGIC_REGISTER_IN_PROCESS) {
            this.C = i.c().i();
        } else if (i == i.a) {
            this.C = i.c().g();
        } else {
            this.C = i.c().c(i);
            if (i == i.b) {
                List<Mark> c = i.c().c(i.c);
                if (c != null && c.size() > 0) {
                    for (Mark mark : c) {
                        if (mark != null) {
                            this.C.add(mark);
                        }
                    }
                }
            }
        }
        if (this.C != null && this.C.size() > 0) {
            this.b.a(this.C);
        }
    }

    public void onResume() {
        super.onResume();
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

    private void d(int i) {
        this.E = i;
        switch (i) {
            case 10101:
                if (this.D != null) {
                    this.D.clear();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(int i) {
        this.b.getItem(i);
        super.a(i);
    }

    public boolean b(int i) {
        if (this.E == 10102 || this.E == 10103) {
            return true;
        }
        return super.b(i);
    }

    public boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 20004:
                int i = message.arg1;
                String str = (String) message.obj;
                this.E = 10101;
                ((com.qq.reader.module.bookshelf.a.a.a) this.b).b(this.E);
                c(i);
                this.b.notifyDataSetInvalidated();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void a(Mark mark) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(mark);
        a(arrayList).f_();
    }

    protected void a(MetroItem metroItem) {
        d(10101);
        Message obtain = Message.obtain();
        obtain.arg1 = metroItem.getId();
        obtain.obj = metroItem.getName();
        obtain.what = 20004;
        this.mHandler.sendMessage(obtain);
    }

    private void a(boolean z) {
        if (z) {
            if (this.H == null) {
                this.H = getLayoutInflater().inflate(R.layout.local_search_button, null);
                this.I = (Button) this.H.findViewById(R.id.searchBookBtn);
                this.I.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ LocalSearchBooksActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.a();
                    }
                });
            }
            if (this.B.getFooterViewsCount() <= 1) {
                this.B.addFooterView(this.H);
            }
        } else if (this.H != null) {
            this.B.removeFooterView(this.H);
        }
    }

    private void a(String str) {
        if (this.G == null) {
            this.G = i.c().g();
        }
        Mark[] markArr = new Mark[this.G.size()];
        this.G.toArray(markArr);
        ArrayList arrayList = new ArrayList();
        for (Mark mark : markArr) {
            String pinyinBookName = mark.getPinyinBookName();
            String pinyinBookNameAll = mark.getPinyinBookNameAll();
            if (-1 != pinyinBookName.indexOf(str)) {
                arrayList.add(mark);
            } else if (-1 != pinyinBookNameAll.indexOf(str)) {
                arrayList.add(mark);
            } else if (-1 != mark.getBookName().indexOf(str)) {
                arrayList.add(mark);
            } else {
                pinyinBookName = mark.getAuthor();
                pinyinBookNameAll = mark.getPinyinAuthor();
                if (!(pinyinBookName == null || pinyinBookNameAll == null)) {
                    if (-1 != pinyinBookName.indexOf(str)) {
                        arrayList.add(mark);
                    } else if (-1 != pinyinBookNameAll.indexOf(str)) {
                        arrayList.add(mark);
                    } else if (-1 != mark.getPinyinAuthorAll().indexOf(str)) {
                        arrayList.add(mark);
                    }
                }
            }
        }
        Object obj = new Mark[arrayList.size()];
        arrayList.toArray(obj);
        Message message = new Message();
        message.what = 1;
        message.obj = obj;
        this.J.sendMessage(message);
    }

    protected void a() {
        String trim = this.v.getText().toString().trim();
        if (trim == null || trim.length() <= 0) {
            Toast.makeText(this.a, "搜索内容不能为空", 0).show();
            return;
        }
        if (this.A != null && this.A.isActive()) {
            this.A.hideSoftInputFromWindow(this.v.getWindowToken(), 0);
        }
        if (this.v.hasFocus()) {
            this.v.clearFocus();
        }
        trim = "/search.html?" + e.b(this.a) + "&" + "key=" + URLEncoder.encode(trim);
        Intent intent = new Intent();
        intent.setClass(this, WebBrowserForContents.class);
        intent.putExtra("com.qq.reader.WebContent", trim);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }
}
