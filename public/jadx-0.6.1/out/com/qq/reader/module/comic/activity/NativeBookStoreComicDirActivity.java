package com.qq.reader.module.comic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.module.comic.a.a;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.comic.e.c;
import com.qq.reader.module.comic.entity.l;
import com.qq.reader.module.comic.entity.o;
import com.qrcomic.a.h;
import com.qrcomic.e.b.d;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.entity.n;
import com.qrcomic.manager.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

public class NativeBookStoreComicDirActivity extends ReaderBaseActivity {
    private static final String a = NativeBookStoreComicDirActivity.class.getSimpleName();
    private static String f = ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID;
    private static String g = "currentIndex";
    private View b;
    private String c;
    private int d;
    private boolean e = false;
    private View h;
    private ListView i;
    private a j;
    private c k;
    private l l;
    private h m;
    private com.qrcomic.e.c n;
    private TextView o;

    public static void a(Activity activity, String str, int i, int i2) {
        Intent intent = new Intent(activity, NativeBookStoreComicDirActivity.class);
        intent.putExtra(f, str);
        intent.putExtra(g, i);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        activity.startActivityForResult(intent, i2);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        d();
    }

    private void b() {
        setContentView(R.layout.online_directory);
        c();
    }

    private void c() {
        this.i = (ListView) findViewById(R.id.online_chapter_list);
        this.j = new a(this.d);
        this.i.setAdapter(this.j);
        this.i.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDirActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int count;
                if (i >= this.a.j.getCount()) {
                    count = this.a.j.getCount() - 1;
                } else {
                    count = i;
                }
                com.qq.reader.module.comic.a.a().a(this.a, this.a.c, ((o) this.a.j.getItem(count)).d(), count, "");
                this.a.finish();
            }
        });
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDirActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.b = findViewById(R.id.chapter_loading);
        this.b.setVisibility(0);
        this.o = (TextView) findViewById(R.id.profile_header_title);
        this.o.setText("目录");
        this.h = findViewById(R.id.online_chapter_empyt_layout);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDirActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.h.setVisibility(8);
                this.a.f();
            }
        });
        this.i.setVisibility(8);
        this.h.setVisibility(8);
    }

    private void d() {
        try {
            this.n = new com.qrcomic.e.c(this) {
                final /* synthetic */ NativeBookStoreComicDirActivity a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                    if (obj instanceof d) {
                        d dVar = (d) obj;
                        if (dVar.a == null || dVar.a.isEmpty()) {
                            com.qq.reader.common.monitor.debug.c.e(NativeBookStoreComicDirActivity.a, "onQueryUserBuyInfoSuccess but buyInfoList is null");
                            return;
                        }
                        com.qrcomic.entity.l lVar = (com.qrcomic.entity.l) dVar.a.get(0);
                        if (this.a.c.equals(lVar.a)) {
                            boolean z;
                            List arrayList = new ArrayList();
                            List<n> list = lVar.e;
                            if (list != null && list.size() > 0) {
                                for (n nVar : list) {
                                    if (nVar.b == 1 && nVar.a != null) {
                                        arrayList.add(nVar.a);
                                    }
                                }
                            }
                            this.a.j.b(arrayList);
                            a a = this.a.j;
                            if (lVar.c == 1) {
                                z = true;
                            } else {
                                z = false;
                            }
                            a.a(z);
                            this.a.j.notifyDataSetChanged();
                        }
                    }
                }

                public void b(Object obj) {
                }
            };
            this.c = getIntent().getStringExtra(f);
            this.d = getIntent().getIntExtra(g, 0);
            this.m = b.a().b();
            if (this.m == null && com.qq.reader.module.comic.a.a().a(this)) {
                this.m = b.a().b();
            }
            if (this.m != null) {
                this.m.a(this.n);
            }
            e();
            f();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void e() {
        g.a().a(new ReaderTask() {
            public String getTaskName() {
                return "queryBuyInfo";
            }

            public void run() {
                super.run();
                if (NativeBookStoreComicDirActivity.this.m != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new QRComicBuyReqInfo(NativeBookStoreComicDirActivity.this.c, null));
                    com.qrcomic.e.b bVar = (com.qrcomic.e.b) NativeBookStoreComicDirActivity.this.m.b(1);
                    bVar.a(arrayList, null, false);
                    bVar.a(arrayList, null, true);
                }
            }
        });
    }

    private void f() {
        this.k = new c(getApplicationContext());
        this.k.a(getHandler());
        this.k.a(this.c, true);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 21000:
                this.l = (l) message.obj;
                if (this.l != null) {
                    this.b.setVisibility(8);
                    this.o.setText(this.l.g());
                    List h = this.l.h();
                    int i = this.d;
                    if (this.e) {
                        if (h != null && h.size() > 0) {
                            this.j.a(h);
                            this.h.setVisibility(8);
                            this.j.a(i);
                            this.i.setSelection(i);
                            this.j.notifyDataSetChanged();
                            this.i.setVisibility(0);
                            break;
                        }
                    }
                    this.e = true;
                    if (h != null && h.size() != 0) {
                        this.i.setVisibility(0);
                        this.h.setVisibility(8);
                        this.j.a(h);
                        this.j.a(i);
                        this.i.setSelection(i);
                        this.j.notifyDataSetChanged();
                        break;
                    }
                    this.i.setVisibility(8);
                    this.h.setVisibility(0);
                    break;
                }
                break;
            case 21001:
                this.b.setVisibility(8);
                if (!this.e) {
                    this.i.setVisibility(8);
                    this.h.setVisibility(0);
                    break;
                }
                break;
            case 21011:
                ArrayList arrayList = (ArrayList) message.obj;
                if (this.j != null) {
                    this.j.b(arrayList);
                    this.j.notifyDataSetChanged();
                }
                return true;
            case 21101:
                if (this.j != null) {
                    this.j.a(true);
                    this.j.notifyDataSetChanged();
                }
                return true;
        }
        return false;
    }

    private void g() {
        if (this.k != null) {
            this.k.a();
            this.k = null;
        }
        if (this.n != null && this.m != null) {
            this.m.b(this.n);
        }
    }

    protected void onDestroy() {
        g();
        super.onDestroy();
    }
}
