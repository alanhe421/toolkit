package com.qq.reader.cservice.cloud.big;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.m;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.SwipeRefreshLayout.b;
import com.qq.reader.common.widget.viewpager.c;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.cservice.cloud.h;
import com.qq.reader.cservice.download.book.f;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookshelf.view.CloudListItem;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.view.CloudUpdateStateView;
import com.qq.reader.view.af;
import com.qq.reader.view.d;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class CloudBookListActivity extends ReaderBaseActivity implements OnItemClickListener, OnItemLongClickListener, f {
    SwipeRefreshLayout a;
    Dialog b = null;
    c c;
    private Context d;
    private a e;
    private ListView f;
    private View g;
    private ArrayList<g> h;
    private g i = null;
    private ProgressDialog j;
    private Thread k;
    private Handler l;
    private d m;
    private final int n = 0;
    private CloudUpdateStateView o;
    private int p;
    private StringBuffer q;
    private ViewGroup r;
    private int s = 0;
    private boolean t = false;
    private List<Long> u = new ArrayList();
    private volatile boolean v = false;
    private boolean w;

    private class a extends BaseAdapter {
        final /* synthetic */ CloudBookListActivity a;
        private ArrayList<g> b = new ArrayList();

        public a(CloudBookListActivity cloudBookListActivity) {
            this.a = cloudBookListActivity;
        }

        public ArrayList<g> a() {
            if (this.b == null) {
                return null;
            }
            return (ArrayList) this.b.clone();
        }

        public void a(ArrayList<g> arrayList) {
            if (arrayList != null && arrayList.size() > 0) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    this.b.add(arrayList.get(size));
                }
            }
        }

        public void b() {
            this.b.clear();
        }

        public int getCount() {
            if (this.b == null || this.b.size() <= 0) {
                return 0;
            }
            return this.b.size();
        }

        public Object getItem(int i) {
            if (this.b == null || this.b.size() <= 0) {
                return null;
            }
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                View view2 = (CloudListItem) LayoutInflater.from(this.a.d).inflate(R.layout.cloud_list_item, null);
                view2.a();
                view = view2;
            } else {
                CloudListItem cloudListItem = (CloudListItem) view;
            }
            g gVar = (g) this.b.get(i);
            boolean contains = this.a.u.contains(Long.valueOf(gVar.f()));
            view.setFileItemInfo(gVar, contains);
            view.setBookResType(gVar.w());
            view.setTag(R.id.cloudlist_tag_position, new Integer(i));
            view.setTag(R.id.cloudlist_tag_isontab, Boolean.valueOf(contains));
            com.qq.reader.common.imageloader.c.a(this.a.getContext()).a(gVar.m(), view.getIconImageView(), com.qq.reader.common.imageloader.a.a().j());
            return view;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = getApplicationContext();
        setContentView(R.layout.cloud_list);
        this.r = (ViewGroup) findViewById(R.id.cloud_list_rootview);
        this.l = getHandler();
        this.a = (SwipeRefreshLayout) findViewById(R.id.pull_down_list);
        this.g = findViewById(R.id.cloud_empty_content);
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CloudBookListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                j.a(63, 0);
                this.a.setResult(-1);
                this.a.finish();
            }
        });
        ((TextView) findViewById(R.id.profile_header_title)).setText("云书架");
        this.h = new ArrayList();
        this.f = (ListView) findViewById(R.id.cluodbooklist);
        this.e = new a(this);
        this.f.setAdapter(this.e);
        this.f.setOnItemClickListener(this);
        this.f.setOnItemLongClickListener(this);
        this.f.setOnCreateContextMenuListener(this);
        this.f.setOnScrollListener(new com.qq.reader.common.imageloader.core.a.a(com.qq.reader.common.imageloader.c.a((Context) this).a(), false, true));
        this.o = (CloudUpdateStateView) findViewById(R.id.state_text);
    }

    private void a(ArrayList<g> arrayList) {
        this.u.clear();
        List<Mark> h = i.c().h();
        if (h != null) {
            for (Mark bookId : h) {
                this.u.add(Long.valueOf(bookId.getBookId()));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!this.u.contains(Long.valueOf(((g) it.next()).f()))) {
                return;
            }
        }
    }

    protected void onResume() {
        super.onResume();
        this.a.setOnRefreshListener(new b(this) {
            final /* synthetic */ CloudBookListActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a();
            }
        });
        this.l.sendEmptyMessage(110);
    }

    protected void onPause() {
        super.onPause();
        this.a.setOnRefreshListener(null);
        com.qq.reader.cservice.cloud.b.a(getApplicationContext()).a(hashCode());
    }

    protected void onDestroy() {
        getHandler().removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    private void b() {
        this.f.setVisibility(0);
        ArrayList d = m.b().d();
        if (com.qq.reader.appconfig.a.d.ae(getApplicationContext()) || d.size() == 0) {
            this.a.setRefreshing(true);
            a();
            return;
        }
        this.h = d;
        if (this.h.size() > 0) {
            a(this.h);
        }
        this.l.sendEmptyMessage(100);
    }

    private void c() {
        if (this.e.getCount() != 0) {
            this.f.setVisibility(0);
            this.g.setVisibility(8);
        } else {
            this.f.setVisibility(8);
            this.g.setVisibility(0);
        }
        if (this.e != null) {
            this.e.notifyDataSetChanged();
            j.j = this.e.getCount();
        }
    }

    public void a(int i, int i2) {
        com.qq.reader.common.readertask.g.a().a(new CloudListUpdateTaskBig(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ CloudBookListActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                boolean z = true;
                try {
                    CloudListUpdateTaskBig cloudListUpdateTaskBig = (CloudListUpdateTaskBig) readerProtocolTask;
                    this.a.h.clear();
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt("code");
                    if (1 == i || i == 0) {
                        long j2 = jSONObject.getLong("latestversion");
                        JSONArray jSONArray = jSONObject.getJSONArray("books");
                        if (jSONObject.optBoolean("hasNext", true)) {
                            z = false;
                        }
                        cloudListUpdateTaskBig.setIsLastPackage(z);
                        a.a(this.a.d, j2, jSONArray);
                        if (cloudListUpdateTaskBig.getIsLastPackage()) {
                            this.a.h = a.a(this.a.d, j2);
                            this.a.a(this.a.h);
                            this.a.p = this.a.a(this.a.h, this.a.e.a());
                            this.a.l.sendEmptyMessage(99);
                        } else {
                            this.a.a(cloudListUpdateTaskBig.getParamPn() + 1, cloudListUpdateTaskBig.getParamPs());
                            this.a.h = a.a(this.a.d);
                            this.a.a(this.a.h);
                            this.a.l.sendEmptyMessage(98);
                        }
                        this.a.t = true;
                    } else if (100 == i) {
                        a.a();
                        this.a.s = 0;
                        this.a.l.sendEmptyMessage(111);
                        this.a.t = true;
                    } else {
                        a.a();
                        String string = jSONObject.getString("message");
                        Message obtain = Message.obtain();
                        obtain.obj = string;
                        obtain.what = 101;
                        this.a.l.sendMessage(obtain);
                        this.a.t = false;
                    }
                } catch (Exception e) {
                    this.a.p = 0;
                    a.a();
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.t = false;
                String string = this.a.getResources().getString(R.string.dialog_net_error);
                Message obtain = Message.obtain();
                obtain.obj = string;
                obtain.what = 101;
                this.a.l.sendMessage(obtain);
            }
        }, i, i2));
    }

    private boolean d() {
        if (this.j == null || !this.j.isShowing()) {
            return false;
        }
        this.j.cancel();
        return true;
    }

    public boolean a(int i) {
        switch (i) {
            case 0:
                e();
                break;
        }
        return true;
    }

    private void e() {
        j.a(62, 0);
        com.qq.reader.common.monitor.i.a("event_A63", null, ReaderApplication.getApplicationImp());
        com.qq.reader.cservice.cloud.a.d dVar = new com.qq.reader.cservice.cloud.a.d(this.i.f(), com.qq.reader.cservice.cloud.a.d.a(this.i.w()), this.i.w());
        dVar.b(hashCode());
        com.qq.reader.common.readertask.g.a().a(new CloudSynCommitDelBookTaskBig(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ CloudBookListActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                if (str != null && str.length() > 0) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("code");
                        if (100 == optInt) {
                            this.a.l.sendEmptyMessage(111);
                        } else if (optInt == 0) {
                            h.b(jSONObject.getLong("latestversion"));
                        }
                    } catch (Exception e) {
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }, dVar, h.b()));
        m.b().a(this.i.f());
        this.l.sendEmptyMessage(110);
    }

    private void a(g gVar) {
        if (gVar.a().a()) {
            long f = gVar.f();
            if (f > 0) {
                ao.a((Context) this, f);
                return;
            }
            return;
        }
        Mark comicBookMark;
        OnlineTag onlineTag = new OnlineTag(String.valueOf(gVar.f()), "", 0);
        onlineTag.a(gVar.v()).j(gVar.w()).e(gVar.k()).f(gVar.l()).c(1).b(gVar.q()).e(gVar.s()).d(gVar.r()).f(0).h(gVar.m()).k(gVar.n()).i(gVar.o()).h(gVar.u()).c((int) gVar.h());
        onlineTag.a((long) gVar.i());
        onlineTag.b(System.currentTimeMillis());
        v.b().b(onlineTag);
        if (gVar.w() == 3) {
            comicBookMark = new ComicBookMark(gVar.f(), gVar.j());
        } else {
            comicBookMark = new LocalMark(gVar.v(), onlineTag.f(), 0, 4, false);
        }
        comicBookMark.setPercentStr("0.0%").setAuthor(gVar.k()).setDescriptionStr("");
        comicBookMark.setFinished(gVar.u());
        comicBookMark.setHasNewContent(false);
        comicBookMark.setId(onlineTag.k());
        comicBookMark.setBookId(Long.valueOf(onlineTag.k()).longValue());
        comicBookMark.setCoverUrl(onlineTag.u());
        comicBookMark.setOperateTime(System.currentTimeMillis());
        i.c().a(comicBookMark, true);
        com.qq.reader.appconfig.a.d.h(this.d.getApplicationContext(), onlineTag.k());
        com.qq.reader.cservice.cloud.b.a(this.d.getApplicationContext()).a(new com.qq.reader.cservice.cloud.a.b(gVar.f(), 1, 0, 0, gVar.t(), gVar.w()), false, null);
        this.u.add(Long.valueOf(this.i.f()));
    }

    protected boolean handleMessageImp(Message message) {
        int i = message.what;
        switch (i) {
            case 98:
            case 99:
            case 100:
                d();
                if (com.qq.reader.appconfig.a.d.ae(getApplicationContext())) {
                    com.qq.reader.appconfig.a.d.l(getApplicationContext(), false);
                }
                if (this.h != null) {
                    this.e.b();
                    this.e.a(this.h);
                }
                c();
                if (i == 99) {
                    this.q = new StringBuffer();
                    Resources resources = getResources();
                    if (this.p == 0) {
                        this.q.append(resources.getString(R.string.cloud_alert_update_no));
                    } else {
                        this.q.append(resources.getString(R.string.cloud_alert_update_yes_0));
                        this.q.append(this.p);
                        this.q.append(resources.getString(R.string.cloud_alert_update_yes_1));
                    }
                    this.o.setText(this.q.toString());
                }
                if (i == 99) {
                    com.qq.reader.appconfig.a.d.d(this.d.getApplicationContext(), System.currentTimeMillis());
                }
                if (i == 100 || i == 99) {
                    this.a.setRefreshing(false);
                    this.v = false;
                    break;
                }
            case 101:
                d();
                af.a(getApplicationContext(), (String) message.obj, 0).a();
                c();
                this.a.setRefreshing(false);
                this.v = false;
                com.qq.reader.appconfig.a.d.d(this.d.getApplicationContext(), System.currentTimeMillis());
                break;
            case 102:
            case 103:
                if (this.e != null) {
                    this.e.notifyDataSetChanged();
                    j.j = this.e.getCount();
                    break;
                }
                break;
            case 110:
                b();
                break;
            case 111:
                d();
                com.qq.reader.common.login.c.a();
                af.a(getApplicationContext(), (CharSequence) "登录已失效,请重新登录", 0).a();
                c();
                this.a.setRefreshing(false);
                this.v = false;
                break;
            case 113:
                this.e.notifyDataSetChanged();
                j.j = this.e.getCount();
                break;
        }
        return super.handleMessageImp(message);
    }

    public void a() {
        if (!this.v) {
            this.v = true;
            com.qq.reader.common.monitor.i.a("event_A61", null, ReaderApplication.getApplicationImp());
            this.l.postDelayed(new Runnable(this) {
                final /* synthetic */ CloudBookListActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.k = new Thread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a(-1, -1);
                        }
                    });
                    this.a.k.start();
                }
            }, 200);
        }
    }

    public void a(com.qq.reader.cservice.download.book.g gVar) {
        Message obtain = Message.obtain();
        obtain.what = 1203;
        obtain.obj = gVar;
        this.l.sendMessage(obtain);
    }

    public void b(com.qq.reader.cservice.download.book.g gVar) {
        Message message = new Message();
        message.what = 1204;
        message.obj = gVar;
        this.l.sendMessage(message);
    }

    public void c(com.qq.reader.cservice.download.book.g gVar) {
        Message message = new Message();
        message.what = 1205;
        message.obj = gVar;
        this.l.sendMessage(message);
    }

    private int a(ArrayList<g> arrayList, ArrayList<g> arrayList2) {
        if (arrayList == null || arrayList2 == null) {
            return 0;
        }
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            g gVar = (g) it.next();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                if (gVar.f() == ((g) it2.next()).f()) {
                    i2 = 1;
                    break;
                }
            }
            i2 = 0;
            if (i2 == 0) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        it = arrayList2.iterator();
        while (it.hasNext()) {
            gVar = (g) it.next();
            it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (gVar.f() == ((g) it2.next()).f()) {
                    i2 = 1;
                    break;
                }
            }
            i2 = 0;
            if (i2 == 0) {
                i++;
            }
        }
        return i;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.c != null && this.c.a()) {
            this.c.a(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                int i2;
                if (this.w) {
                    i2 = -1;
                } else {
                    i2 = 0;
                }
                setResult(i2);
                com.qq.reader.common.monitor.i.a("event_A64", null, ReaderApplication.getApplicationImp());
                finish();
                break;
        }
        return false;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item = this.e.getItem(i);
        if (!(item instanceof g) || this.v) {
            return false;
        }
        this.i = (g) item;
        this.m = new d(this);
        this.m.a(0, "删除", null);
        this.m.a(this.i.v());
        this.m.b(this.i.k());
        this.m.c(this.i.q());
        this.m.d("在线");
        com.qq.reader.common.imageloader.c.a(getContext()).a(this.i.m(), this.m.g(), com.qq.reader.common.imageloader.a.a().j());
        this.m.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ CloudBookListActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                return this.a.a(i);
            }
        });
        this.m.f_();
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!((Boolean) view.getTag(R.id.cloudlist_tag_isontab)).booleanValue()) {
            this.w = true;
            this.i = (g) this.e.getItem(i);
            a(this.i);
            this.l.sendEmptyMessage(103);
            j.a(61, 0);
            com.qq.reader.common.monitor.i.a("event_A62", null, ReaderApplication.getApplicationImp());
        }
    }
}
