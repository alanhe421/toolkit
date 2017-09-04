package com.qq.reader.module.bookchapter.online;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.k;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.QueryChapterBuyInfoTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.cservice.download.audio.AudioAuthCheckTask;
import com.qq.reader.cservice.download.audio.AuthCheckTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.audio.loader.QueryAudioChapterBuyInfoTask;
import com.qq.reader.module.bookchapter.ChapterAdapterItem;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONObject;

public class OnlineChapterActivity extends ReaderBaseActivity {
    private ListView a;
    private h b;
    private ImageView c;
    private View d;
    private EmptyView e;
    private f f;
    private OnlineTag g;
    private int h = 1;
    private g i;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private String m;
    private View n;
    private OnItemClickListener o = new OnItemClickListener(this) {
        final /* synthetic */ OnlineChapterActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ChapterAdapterItem chapterAdapterItem = (ChapterAdapterItem) view;
            if (view instanceof ChapterAdapterItem) {
                OnlineChapter onlineChapter = (OnlineChapter) this.a.b.getItem(i);
                this.a.g.c(onlineChapter.getChapterId()).b(onlineChapter.getChapterName()).a(0);
                i.a("event_C280", null, ReaderApplication.getApplicationImp());
                Intent intent;
                if (this.a.j) {
                    intent = new Intent();
                    intent.setClass(this.a, ReaderPageActivity.class);
                    intent.setFlags(SigType.WLOGIN_QRPUSH);
                    intent.putExtra("com.qq.reader.OnlineTag", this.a.g);
                    intent.putExtra("com.qq.reader.OnlineTag.web.chapter", true);
                    intent.putExtra("com.qq.reader.fromonline", true);
                    this.a.startActivity(intent);
                    StatisticsManager.a().a("event_Dir", null);
                    i.a("event_Bookonline", null, this.a);
                } else if (this.a.g == null || this.a.g.F() != 2) {
                    intent = new Intent();
                    intent.putExtra("com.qq.reader.OnlineTag", this.a.g);
                    this.a.setResult(-1, intent);
                    this.a.finish();
                } else {
                    o.b(this.a, this.a.g.k(), this.a.g.g(), null);
                    this.a.finish();
                }
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            this.g = (OnlineTag) extras.getParcelable("com.qq.reader.OnlineTag");
            this.j = extras.getBoolean("onlineChapterActivityFromWeb");
            if (this.g == null) {
                finish();
            }
            if (this.j) {
                a(1);
            } else {
                a(d.Y(getApplicationContext()));
            }
            setContentView(R.layout.online_directory);
            this.h = this.g.g();
            this.e = (EmptyView) findViewById(R.id.online_chapter_empyt_layout);
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ OnlineChapterActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.e.setVisibility(8);
                    this.a.d.setVisibility(0);
                    this.a.a();
                }
            });
            this.n = findViewById(R.id.layout_off_market);
            this.a = (ListView) findViewById(R.id.online_chapter_list);
            this.b = new h();
            this.a.setAdapter(this.b);
            this.a.setVisibility(8);
            this.a.setOnItemClickListener(this.o);
            this.c = (ImageView) findViewById(R.id.profile_header_left_back);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ OnlineChapterActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.finish();
                }
            });
            ((TextView) findViewById(R.id.profile_header_title)).setText("目录");
            this.d = findViewById(R.id.chapter_loading);
            this.d.setVisibility(0);
            a();
            if (this.g != null) {
                g.a().a(new ReaderIOTask() {
                    public void run() {
                        super.run();
                        OnlineChapterActivity.this.b(OnlineChapterActivity.this.g.k(), OnlineChapterActivity.this.g.F());
                    }
                });
            }
        } catch (Exception e) {
            finish();
        }
    }

    private void a(String str, int i) {
        int i2 = -1;
        if (this.i != null) {
            i2 = this.i.b();
        }
        String str2 = this.m;
        if ((i == 1 && r0 == 2) || (i == 2 && r0 == 3)) {
            if (!TextUtils.isEmpty(str2)) {
                a(str2, str, true);
            }
        } else if (((i == 1 && r0 == 1) || (i == 2 && r0 == 2)) && !TextUtils.isEmpty(str2)) {
            a(str2, str, false);
        }
    }

    private void a(String str, String str2, boolean z) {
        Message obtain;
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("retCode") == 0) {
                    List a = ao.a(jSONObject.optString("cids"));
                    if (a != null) {
                        k.a(getApplicationContext()).a(str2, a);
                        a = k.a(getApplicationContext()).a(str2);
                        obtain = Message.obtain();
                        obtain.what = 21011;
                        obtain.obj = a;
                        this.mHandler.sendMessage(obtain);
                    }
                }
            } catch (Exception e) {
                c.e("Err", e.getMessage());
            }
        } else if (new JSONObject(str).optInt("code") == 1) {
            ArrayList arrayList = new ArrayList();
            obtain = Message.obtain();
            obtain.what = 21101;
            obtain.obj = arrayList;
            this.mHandler.sendMessage(obtain);
        }
    }

    private void b(final String str, int i) {
        int i2 = -1;
        if (this.i != null) {
            i2 = this.i.b();
        }
        ReaderTask queryChapterBuyInfoTask;
        if (i == 1 && r0 == 2) {
            queryChapterBuyInfoTask = new QueryChapterBuyInfoTask(str);
            queryChapterBuyInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ OnlineChapterActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, str, true);
                    this.b.m = str;
                    this.b.l = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            });
            g.a().a(queryChapterBuyInfoTask);
        } else if (i == 1 && r0 == 1) {
            g.a().a(new AuthCheckTask(Long.parseLong(this.g.k()), new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ OnlineChapterActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, str, false);
                    this.b.m = str;
                    this.b.l = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.b.mHandler.sendEmptyMessage(10000505);
                }
            }));
        } else if (i == 2 && r0 == 3) {
            queryChapterBuyInfoTask = new QueryAudioChapterBuyInfoTask(str);
            queryChapterBuyInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ OnlineChapterActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, str, true);
                    this.b.m = str;
                    this.b.l = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            });
            g.a().a(queryChapterBuyInfoTask);
        } else if (i == 2 && r0 == 2) {
            g.a().a(new AudioAuthCheckTask(Long.parseLong(this.g.k()), new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ OnlineChapterActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, str, false);
                    this.b.m = str;
                    this.b.l = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.b.mHandler.sendEmptyMessage(10000505);
                }
            }));
        }
    }

    private void a(int i) {
        setRequestedOrientation(i);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 21000:
                this.f = (f) message.obj;
                if (!(this.f == null || this.f.y() == null || (this.f.y().U() != -1 && this.f.y().V() != -1))) {
                    this.n.setVisibility(0);
                }
                this.d.setVisibility(8);
                Collection e = this.f.e();
                if (this.k) {
                    if (e != null && e.size() > 0 && message.arg2 == 2) {
                        this.b.a(e);
                        this.b.notifyDataSetChanged();
                        break;
                    }
                }
                this.k = true;
                if (e == null || e.size() == 0) {
                    this.a.setVisibility(8);
                    this.e.setVisibility(0);
                } else {
                    int g = this.g.g() - 1;
                    this.a.setVisibility(0);
                    this.e.setVisibility(8);
                    this.b.a(this.f.e());
                    this.b.a(g);
                    this.a.setSelection(g);
                    this.b.notifyDataSetChanged();
                }
                if (this.l && this.g != null) {
                    a(this.g.k(), this.g.F());
                    break;
                }
                break;
            case 21001:
                this.d.setVisibility(8);
                if (!this.k) {
                    this.a.setVisibility(8);
                    this.e.setVisibility(0);
                    break;
                }
                break;
            case 21011:
                ArrayList arrayList = (ArrayList) message.obj;
                if (this.b != null) {
                    this.b.a(arrayList);
                    this.b.notifyDataSetChanged();
                }
                return true;
            case 21101:
                if (this.b != null) {
                    this.b.a(true);
                    this.b.notifyDataSetChanged();
                }
                return true;
        }
        b();
        return false;
    }

    private void a() {
        this.i = new g(getApplicationContext(), this.g);
        this.i.a(getHandler());
        this.i.a(true);
    }

    protected void onDestroy() {
        b();
        super.onDestroy();
    }

    private void b() {
        if (this.i != null) {
            this.i.d();
            this.i = null;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.i != null) {
            this.i = null;
        }
        finish();
        return true;
    }
}
