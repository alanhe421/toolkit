package com.qq.reader.plugin.audiobook;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.m;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.plugin.PlugInDefaultActivity;
import com.qq.reader.plugin.audiobook.core.SongInfo;
import com.qq.reader.plugin.audiobook.core.e;
import com.qq.reader.plugin.audiobook.core.l;
import com.qq.reader.plugin.audiobook.core.n;
import com.qq.reader.view.af;
import com.qq.reader.view.c;
import com.qq.reader.view.web.b;
import com.tencent.android.tpush.common.Constants;
import com.tencent.av.sdk.AVError;
import com.tencent.feedback.proguard.R;
import com.tencent.upload.log.trace.TracerConfig;
import format.epub.common.utils.f;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class MusicActivity extends ReaderBaseActivity implements ServiceConnection {
    private BroadcastReceiver A = new BroadcastReceiver(this) {
        final /* synthetic */ MusicActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equalsIgnoreCase(e.h)) {
                SongInfo songInfo = (SongInfo) intent.getParcelableExtra(e.v);
                if (songInfo != null) {
                    Message obtainMessage = this.a.t.obtainMessage();
                    obtainMessage.what = 5005;
                    obtainMessage.obj = songInfo.c();
                    this.a.t.sendMessage(obtainMessage);
                }
                this.a.h();
                this.a.j();
                this.a.a(1);
                this.a.b = 12346;
            } else if (action.equals(e.l)) {
                if (l.a != null) {
                    try {
                        int q = l.a.q();
                        if (q > 0) {
                            n.a().a(l.a.p()[q - 1], 0, -1);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                this.a.j();
            } else if (action.equals(e.k)) {
                this.a.j();
            } else if (action.equals(e.j)) {
                if (l.a != null) {
                    try {
                        if (l.a.k() == 2) {
                            this.a.h();
                            Message obtainMessage2 = this.a.t.obtainMessage();
                            obtainMessage2.what = 5005;
                            obtainMessage2.obj = null;
                            this.a.t.sendMessage(obtainMessage2);
                        }
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
                this.a.j();
            } else if (action.equals(com.qq.reader.common.c.a.cp) && l.a != null) {
                try {
                    if (l.a.a()) {
                        this.a.b = 12345;
                        l.a.c();
                    }
                } catch (RemoteException e22) {
                    e22.printStackTrace();
                }
            }
        }
    };
    private c B;
    private b C;
    private MusicAllTag D = null;
    private b E;
    i a;
    public int b = -1;
    e c = null;
    private String d = "MusicActivity";
    private ListView e;
    private RelativeLayout f;
    private RelativeLayout g;
    private d h;
    private f i;
    private TextView j;
    private TextView k;
    private SeekBar l;
    private TextView m;
    private TextView n;
    private ImageView o;
    private Button p;
    private boolean q = false;
    private boolean r;
    private long s;
    private Handler t;
    private long u = 0;
    private TaskStateEnum[] v = new TaskStateEnum[]{TaskStateEnum.Installing, TaskStateEnum.Removed};
    private com.qq.reader.view.linearmenu.b w;
    private volatile int x = 0;
    private m y = new m(this) {
        final /* synthetic */ MusicActivity a;

        {
            this.a = r1;
        }

        public void a(com.qq.reader.common.download.task.n nVar) {
            TaskStateEnum c = nVar.c();
            TaskStateEnum a = nVar.a();
            MusicDownloadTask musicDownloadTask = (MusicDownloadTask) nVar.d();
            Message obtainMessage = this.a.t.obtainMessage();
            if (c == TaskStateEnum.Installing && a != TaskStateEnum.Installing) {
                this.a.h.c(((MusicDownloadTask) nVar.d()).getBookId()).setCurDownloadChapterId(-1000);
                this.a.t.sendEmptyMessage(5012);
                if (l.a != null) {
                    try {
                        if (l.a.q() > 0) {
                            SongInfo songInfo = l.a.p()[0];
                            if (songInfo != null) {
                                MusicDownloadMark musicDownloadMark = (MusicDownloadMark) this.a.a.b(songInfo.c());
                                if (musicDownloadMark != null && musicDownloadMark.getBookId() == musicDownloadTask.getBookId()) {
                                    obtainMessage.obj = musicDownloadTask;
                                    obtainMessage.what = 5004;
                                    this.a.t.sendMessage(obtainMessage);
                                }
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else if (c == TaskStateEnum.Removed && a != TaskStateEnum.Removed) {
            }
        }
    };
    private m z = new m(this) {
        final /* synthetic */ MusicActivity a;

        {
            this.a = r1;
        }

        public void a(com.qq.reader.common.download.task.n nVar) {
            long bookId;
            TaskStateEnum c = nVar.c();
            TaskStateEnum a = nVar.a();
            if (c == TaskStateEnum.Started && a != TaskStateEnum.Started) {
                MusicDownloadTask musicDownloadTask = (MusicDownloadTask) nVar.d();
                bookId = musicDownloadTask.getBookId();
                this.a.h.c(bookId).setCurDownloadChapterId(musicDownloadTask.getChapterId());
                this.a.t.sendEmptyMessage(5012);
            }
            Message obtainMessage = this.a.t.obtainMessage();
            bookId = System.currentTimeMillis();
            if (bookId - this.a.u > 500 || c != a) {
                this.a.u = bookId;
                obtainMessage.what = 5002;
                this.a.t.sendMessage(obtainMessage);
            }
        }
    };

    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a = new int[TaskStateEnum.values().length];

        static {
            try {
                a[TaskStateEnum.Prepared.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[TaskStateEnum.Started.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[TaskStateEnum.DeactivePrepared.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[TaskStateEnum.DeactiveStarted.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[TaskStateEnum.Paused.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[TaskStateEnum.Failed.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[TaskStateEnum.InstallCompleted.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[TaskStateEnum.Installing.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    private class a implements com.qq.reader.view.linearmenu.a.b {
        final /* synthetic */ MusicActivity a;
        private MusicBookGroup b;
        private MusicDownloadMark c;
        private int d;

        public a(MusicActivity musicActivity, MusicBookGroup musicBookGroup, MusicDownloadMark musicDownloadMark, int i) {
            this.a = musicActivity;
            this.b = musicBookGroup;
            this.c = musicDownloadMark;
            this.d = i;
        }

        public boolean a(int i, Bundle bundle) {
            List b;
            switch (i) {
                case Constants.ERRORCODE_UNKNOWN /*10000*/:
                    final List b2 = this.a.h.b(this.b.getBookId());
                    f.a(new Runnable(this) {
                        final /* synthetic */ a b;

                        public void run() {
                            for (int i = 0; i < b2.size(); i++) {
                                MusicDownloadMark musicDownloadMark = (MusicDownloadMark) b2.get(i);
                                if (musicDownloadMark != null) {
                                    g downloadTask = musicDownloadMark.getDownloadTask();
                                    if (downloadTask != null) {
                                        switch (AnonymousClass8.a[downloadTask.getState().ordinal()]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                            case 4:
                                                this.b.a.c.c(downloadTask);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                            this.b.b.setCurDownloadChapterId(-1000);
                            Message obtainMessage = this.b.a.t.obtainMessage();
                            obtainMessage.what = 5007;
                            this.b.a.t.sendMessage(obtainMessage);
                        }
                    }, this.a, "正在暂停本书下载");
                    return true;
                case 10003:
                    b = this.a.h.b(this.c.getBookId());
                    if (this.a.a.a(this.c.getId())) {
                        if (this.a.e.getFooterViewsCount() == 1) {
                            this.a.i.a(this.d);
                            if (this.a.i.getCount() == 0) {
                                this.a.e.removeFooterView(this.a.g);
                                this.a.e.setAdapter(this.a.i);
                            } else {
                                this.a.i.notifyDataSetChanged();
                            }
                        } else {
                            this.a.i.a(this.d);
                            this.a.e.addFooterView(this.a.g);
                            this.a.e.setAdapter(this.a.i);
                        }
                        if (l.a != null) {
                            try {
                                l.a.a(this.c.changeToSong());
                                SongInfo o = l.a.o();
                                if (o != null && o.c().equals(this.c.getId())) {
                                    this.a.i.b(null);
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        ao.a(new File(this.c.getId()));
                        this.a.c.d(this.c.getDownloadTask());
                        if (b.size() == 0) {
                            this.a.h.a(this.c.getBookId());
                            this.a.a.a(this.c.getBookId());
                        }
                    }
                    return true;
                case 10004:
                    b = this.a.h.b(this.b.getBookId());
                    if (b.size() == 0) {
                        return false;
                    }
                    final MusicDownloadMark musicDownloadMark = (MusicDownloadMark) b.get(0);
                    new com.qq.reader.view.AlertDialog.a(this.a).c(17301543).a(R.string.bookstand_menu_del).b("确定删除图书\"" + this.b.getBookName() + "\"?").a(R.string.alert_dialog_ok, new OnClickListener(this) {
                        final /* synthetic */ a b;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.b.a.a(musicDownloadMark);
                        }
                    }).b(R.string.alert_dialog_cancel, new OnClickListener(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).a().show();
                    return true;
                case AVError.AV_ERR_SERVER_CONNECT_ROOM_FAIL /*10005*/:
                    Intent intent = new Intent();
                    intent.setClass(this.a, WebBrowserForContents.class);
                    intent.putExtra("fromType", "musicbook");
                    intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.f(this.a, this.b.getBookId()));
                    intent.setFlags(SigType.WLOGIN_QRPUSH);
                    this.a.startActivity(intent);
                    return true;
                default:
                    return false;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.music_book);
        this.a = i.a();
        this.c = (e) com.qq.reader.common.download.task.l.d(1004);
        this.c.a(getApplicationContext());
        this.c.a(this.v, this.y);
        this.f = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.music_book_list_header, null, false);
        ((TextView) this.f.findViewById(R.id.enter_musicbookcity)).setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(this.a, WebBrowserForContents.class);
                intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.ax + com.qq.reader.appconfig.e.b(this.a));
                intent.putExtra("fromType", "musicbook");
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                this.a.startActivity(intent);
            }
        });
        this.g = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.music_book_list_footer, null, false);
        this.g.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(new MusicAllTag(this.a.i.c()));
            }
        });
        this.e = (ListView) findViewById(R.id.music_expandablelist);
        this.e.addHeaderView(this.f);
        this.h = new d(this);
        this.e.setAdapter(this.h);
        this.x = 1;
        this.i = new f(this);
        this.e.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                return false;
            }
        });
        this.e.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                long j2;
                int q;
                if (this.a.x != 1) {
                    try {
                        Mark mark = (Mark) this.a.i.getItem(i);
                        g downloadTask = ((MusicDownloadMark) mark).getDownloadTask();
                        long c = this.a.i.c();
                        if (downloadTask != null) {
                            switch (AnonymousClass8.a[downloadTask.getState().ordinal()]) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    this.a.c.c(downloadTask);
                                    return;
                                case 5:
                                case 6:
                                    this.a.c.e(downloadTask);
                                    return;
                                case 7:
                                case 8:
                                    if (l.a != null) {
                                        int q2 = l.a.q();
                                        SongInfo[] songInfoArr = null;
                                        j2 = -1;
                                        List a = this.a.i.a();
                                        Object obj = null;
                                        if (q2 > 0) {
                                            songInfoArr = l.a.p();
                                            SongInfo o = l.a.o();
                                            if (o != null) {
                                                j2 = o.g();
                                            }
                                            if (o != null && o.c().equals(mark.getId())) {
                                                obj = 1;
                                            }
                                        }
                                        int i2;
                                        if (j2 != c) {
                                            if (j2 != -1) {
                                                this.a.a(songInfoArr, j2);
                                            }
                                            SongInfo[] a2 = this.a.b(a);
                                            if (a2 != null && a2.length > 0) {
                                                l.a.a(a2, null);
                                                int q3 = l.a.q();
                                                i2 = 0;
                                                while (i2 < q3) {
                                                    if (a2[i2] == null || !a2[i2].c().equals(mark.getId())) {
                                                        i2++;
                                                    } else {
                                                        this.a.h.e(mark.getBookId());
                                                        this.a.a(i2);
                                                        this.a.i.b(mark.getId());
                                                        this.a.i.notifyDataSetChanged();
                                                        this.a.h();
                                                        this.a.j();
                                                        this.a.a(1);
                                                        this.a.b = 12349;
                                                        return;
                                                    }
                                                }
                                                i2 = -1;
                                                this.a.h.e(mark.getBookId());
                                                this.a.a(i2);
                                                this.a.i.b(mark.getId());
                                                this.a.i.notifyDataSetChanged();
                                                this.a.h();
                                                this.a.j();
                                                this.a.a(1);
                                                this.a.b = 12349;
                                                return;
                                            }
                                            return;
                                        } else if (mark != null && obj == null) {
                                            q = l.a.q();
                                            i2 = 0;
                                            while (i2 < q) {
                                                if (songInfoArr[i2] == null || !songInfoArr[i2].c().equals(mark.getId())) {
                                                    i2++;
                                                } else {
                                                    this.a.a(i2);
                                                    this.a.i.b(mark.getId());
                                                    this.a.i.notifyDataSetChanged();
                                                    this.a.h();
                                                    this.a.j();
                                                    this.a.a(1);
                                                    this.a.b = 12349;
                                                    return;
                                                }
                                            }
                                            i2 = -1;
                                            this.a.a(i2);
                                            this.a.i.b(mark.getId());
                                            this.a.i.notifyDataSetChanged();
                                            this.a.h();
                                            this.a.j();
                                            this.a.a(1);
                                            this.a.b = 12349;
                                            return;
                                        } else if (obj != null) {
                                            if (l.a.a()) {
                                                this.a.b = 12345;
                                                l.a.c();
                                            } else if (l.a.k() == 1 || l.a.k() == 6) {
                                                l.a.g();
                                            } else {
                                                this.a.a(-1);
                                            }
                                            this.a.i();
                                            this.a.j();
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                    return;
                                default:
                                    return;
                            }
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (i != 0) {
                    boolean z;
                    this.a.x = 2;
                    MusicBookGroup musicBookGroup = (MusicBookGroup) this.a.h.getItem(i - 1);
                    long bookId = musicBookGroup.getBookId();
                    String bookName = musicBookGroup.getBookName();
                    List b = this.a.h.b(bookId);
                    this.a.i.a(bookId);
                    this.a.i.c(bookName);
                    this.a.i.a(b);
                    try {
                        if (l.a != null) {
                            SongInfo[] songInfoArr2 = null;
                            j2 = -1;
                            if (l.a.q() > 0) {
                                songInfoArr2 = l.a.p();
                                j2 = songInfoArr2[0].g();
                            }
                            if (j2 != bookId) {
                                if (b != null && b.size() > 0) {
                                    if (j2 != -1) {
                                        this.a.a(songInfoArr2, j2);
                                    }
                                    SongInfo[] a3 = this.a.b(b);
                                    if (a3 != null && a3.length > 0) {
                                        q = this.a.a(musicBookGroup.getLastSongId(), a3);
                                        SongInfo songInfo = q >= 0 ? a3[q] : null;
                                        l.a.a(a3, null, songInfo);
                                        this.a.h.e(bookId);
                                        this.a.a(-1);
                                        if (q >= 0) {
                                            l.a.a(musicBookGroup.getLastSeekTime());
                                        }
                                        if (songInfo != null) {
                                            this.a.i.b(songInfo.c());
                                        }
                                        this.a.h();
                                        this.a.j();
                                        this.a.a(1);
                                    }
                                }
                            } else if (this.a.b == 12351) {
                                if (l.a.a()) {
                                    this.a.b = 12345;
                                } else if (l.a.k() == 1 || l.a.k() == 6) {
                                    this.a.b = 12345;
                                } else {
                                    this.a.a(-1);
                                }
                                this.a.a(1);
                                this.a.j();
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (this.a.i.getCount() < musicBookGroup.getChapterCount() || this.a.i.getCount() == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.a.a(z);
                    if (l.a != null) {
                        try {
                            SongInfo o2 = l.a.o();
                            if (o2 != null) {
                                int a4 = this.a.i.a(o2.c());
                                if (a4 >= 0) {
                                    this.a.e.setSelection(a4);
                                }
                            }
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        });
        this.e.setOnCreateContextMenuListener(this);
        this.k = (TextView) findViewById(R.id.music_chapter_name);
        this.l = (SeekBar) findViewById(R.id.music_progress);
        this.m = (TextView) findViewById(R.id.music_curtime);
        this.n = (TextView) findViewById(R.id.music_duration);
        this.l.setMax(Constants.ERRORCODE_UNKNOWN);
        this.l.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (this.a.s > 0 && this.a.q) {
                    this.a.m.setText(ao.a(((this.a.s * ((long) i)) / TracerConfig.LOG_FLUSH_DURATION) / 1000));
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.q = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (l.a != null) {
                    try {
                        l.a.a((((long) seekBar.getProgress()) * this.a.s) / TracerConfig.LOG_FLUSH_DURATION);
                        this.a.i();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    this.a.q = false;
                }
            }
        });
        this.o = (ImageView) findViewById(R.id.music_play_controller);
        this.o.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    if (l.a != null) {
                        if (l.a.a()) {
                            this.a.b = 12345;
                            l.a.c();
                        } else if (l.a.k() == 1 || l.a.k() == 6) {
                            l.a.g();
                        } else {
                            this.a.a(-1);
                        }
                        this.a.a(1);
                        this.a.j();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.x == 1) {
                    this.a.a();
                } else if (this.a.x == 2) {
                    this.a.x = 1;
                    this.a.a(false);
                }
            }
        });
        this.j = (TextView) findViewById(R.id.profile_header_title);
        this.j.setText("QQ听书");
        this.p = (Button) findViewById(R.id.profile_header_right_button);
        this.p.setText("设置");
        this.p.setVisibility(0);
        this.p.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.x == 1) {
                    Intent intent = new Intent();
                    intent.setClass(this.a, PlugInDefaultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PLUGIN_TYPE", "4");
                    intent.putExtras(bundle);
                    this.a.startActivityForResult(intent, 1000);
                    return;
                }
                intent = new Intent();
                intent.setClass(this.a, WebBrowserForContents.class);
                intent.putExtra("fromType", "musicbook");
                intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.f(this.a, this.a.i.c()));
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                this.a.startActivity(intent);
            }
        });
        this.t = new Handler(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.a(message);
            }
        };
        l.a(this, this);
    }

    private void a(boolean z) {
        if (this.x == 1) {
            this.e.removeFooterView(this.g);
            this.e.setAdapter(null);
            this.e.addHeaderView(this.f);
            this.e.setAdapter(this.h);
            this.p.setText("设置");
            a("QQ听书");
        } else if (this.x == 2) {
            this.e.removeHeaderView(this.f);
            if (z && this.e.getFooterViewsCount() == 0) {
                this.e.addFooterView(this.g);
            }
            this.e.setAdapter(this.i);
            this.p.setText("详情");
            a(this.i.d());
        }
    }

    private void a(String str) {
        this.j.setText(str);
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        MusicBookGroup c;
        MusicDownloadMark musicDownloadMark;
        int i;
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) contextMenuInfo;
        int i2;
        int i3;
        if (this.x != 1) {
            i2 = adapterContextMenuInfo.position;
            if (this.i.getItem(i2) instanceof MusicDownloadMark) {
                MusicDownloadMark musicDownloadMark2 = (MusicDownloadMark) this.i.getItem(i2);
                c = this.h.c(musicDownloadMark2.getBookId());
                this.w = new com.qq.reader.view.linearmenu.b(this);
                this.w.a(10003, getResources().getString(R.string.music_menu_del_chapter), null);
                this.w.a(10004, getResources().getString(R.string.music_menu_del_book), null);
                if (this.h.d(c.getBookId()) != -1000) {
                    this.w.a(Constants.ERRORCODE_UNKNOWN, getResources().getString(R.string.music_menu_pause_book), null);
                }
                i3 = i2;
                musicDownloadMark = musicDownloadMark2;
                i = i3;
            } else {
                return;
            }
        } else if (adapterContextMenuInfo.position != 0) {
            i2 = adapterContextMenuInfo.position - 1;
            if (this.h.getItem(i2) instanceof MusicBookGroup) {
                MusicBookGroup musicBookGroup = (MusicBookGroup) this.h.getItem(i2);
                this.w = new com.qq.reader.view.linearmenu.b(this);
                this.w.a(10004, getResources().getString(R.string.music_menu_del_book), null);
                this.w.a(AVError.AV_ERR_SERVER_CONNECT_ROOM_FAIL, getResources().getString(R.string.music_menu_view_book_detail), null);
                if (this.h.d(musicBookGroup.getBookId()) != -1000) {
                    this.w.a(Constants.ERRORCODE_UNKNOWN, getResources().getString(R.string.music_menu_pause_book), null);
                }
                i3 = i2;
                musicDownloadMark = null;
                c = musicBookGroup;
                i = i3;
            } else {
                return;
            }
        } else {
            return;
        }
        this.w.a(new a(this, c, musicDownloadMark, i));
        if (this.w.j() > 0) {
            this.w.f_();
        }
    }

    private void a(MusicDownloadMark musicDownloadMark) {
        final MusicDownloadTask downloadTask = musicDownloadMark.getDownloadTask();
        if (l.a != null) {
            try {
                if (l.a.q() > 0) {
                    SongInfo songInfo = l.a.p()[0];
                    if (songInfo != null) {
                        MusicDownloadMark musicDownloadMark2 = (MusicDownloadMark) this.a.b(songInfo.c());
                        if (musicDownloadMark2 != null && musicDownloadMark2.getBookId() == musicDownloadMark.getBookId()) {
                            l.a.b();
                            l.a.a(null, null);
                            this.i.b(null);
                        }
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (this.a.a(musicDownloadMark.getBookId())) {
            this.h.a(musicDownloadMark.getBookId());
            if (this.x == 1) {
                this.h.notifyDataSetChanged();
            } else {
                this.i.b();
                this.e.removeFooterView(this.g);
                this.e.setAdapter(this.i);
            }
            f.a(new Runnable(this) {
                final /* synthetic */ MusicActivity b;

                public void run() {
                    this.b.c.a(downloadTask);
                }
            }, this, "正在删除本地文件,请稍候..");
        }
    }

    private void g() {
        if (l.a != null) {
            h();
        }
    }

    private void h() {
        if (l.a != null) {
            try {
                SongInfo o = l.a.o();
                if (o == null) {
                    this.m.setText(ao.a(0));
                    this.n.setText(ao.a(0));
                    this.l.setProgress(0);
                    this.k.setText("暂无听书文件");
                    return;
                }
                MusicDownloadMark musicDownloadMark = (MusicDownloadMark) this.a.b(o.c());
                if (musicDownloadMark == null || musicDownloadMark.getChapterName() == null) {
                    this.k.setText(o.a());
                } else {
                    this.k.setText(musicDownloadMark.getBookName() + "   " + musicDownloadMark.getChapterName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(int i) {
        try {
            int q = l.a.q();
            if (i < 0 || i >= q) {
                l.a.d();
                if (this.b == 12351) {
                    l.a.a(n.a().b());
                }
            } else {
                l.a.b(i);
            }
            this.b = 12346;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long i() {
        long j = 0;
        if (l.a == null) {
            return 500;
        }
        try {
            long j2 = l.a.j();
            this.s = l.a.i();
            if (this.s < 0) {
                this.s = 0;
            }
            this.n.setText(ao.a(this.s / 1000));
            long j3 = 1000 - (j2 % 1000);
            if (j2 < 0 || this.s <= 0) {
                this.m.setText("00:00");
                this.l.setProgress(0);
            } else {
                if (!this.q) {
                    long j4 = j2 / 1000;
                    if (j4 >= 0) {
                        j = j4;
                    }
                    if (j > this.s) {
                        j = this.s;
                    }
                    this.m.setText(ao.a(j2 / 1000));
                }
                if (!this.q) {
                    this.l.setProgress((int) ((TracerConfig.LOG_FLUSH_DURATION * j2) / this.s));
                }
            }
            return j3;
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }

    private void a(long j) {
        if (!this.r) {
            Message obtainMessage = this.t.obtainMessage(5000);
            this.t.removeMessages(5000);
            this.t.sendMessageDelayed(obtainMessage, j);
        }
    }

    public boolean a(Message message) {
        switch (message.what) {
            case 5000:
                a(i());
                return true;
            case APPluginErrorCode.ERROR_APP_REGETKEYERROR /*5001*/:
                return true;
            case 5002:
                if (this.x != 1) {
                    this.i.notifyDataSetChanged();
                }
                return true;
            case 5003:
                Bundle data = message.getData();
                if (data != null) {
                    MusicDownloadTask musicDownloadTask = (MusicDownloadTask) data.getSerializable("tag_dt");
                    if (musicDownloadTask != null) {
                        this.c.b(musicDownloadTask);
                    }
                }
                if (this.x == 1) {
                    this.h.notifyDataSetChanged();
                } else {
                    this.i.notifyDataSetChanged();
                }
                return true;
            case 5004:
                k();
                SongInfo[] b = b(this.h.b(((MusicDownloadTask) message.obj).getBookId()));
                if (!(b == null || l.a == null)) {
                    try {
                        l.a.a(b, null, l.a.o());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            case 5005:
                String str = (String) message.obj;
                if (str == null) {
                    this.h.e(-1);
                    if (this.x == 1) {
                        this.h.notifyDataSetChanged();
                    }
                }
                this.i.b(str);
                if (this.x == 2) {
                    this.i.notifyDataSetChanged();
                }
                return true;
            case 5006:
                long[] jArr = (long[]) message.obj;
                long j = jArr[0];
                long j2 = jArr[1];
                if (j2 < 0) {
                    j2 = 0;
                }
                this.n.setText(ao.a(j2 / 1000));
                if (j < 0 || j2 <= 0) {
                    this.m.setText("00:00");
                    this.l.setProgress(0);
                } else {
                    this.m.setText(ao.a(j / 1000));
                    this.l.setProgress((int) ((j * TracerConfig.LOG_FLUSH_DURATION) / j2));
                }
                return true;
            case 5007:
                if (this.x == 1) {
                    this.h.notifyDataSetChanged();
                    break;
                }
                break;
            case 5008:
                if (d()) {
                    k();
                    if (this.x == 2 && this.i.getCount() == this.h.c(this.i.c()).getChapterCount()) {
                        this.e.removeFooterView(this.g);
                    }
                    af a = af.a(getApplicationContext(), "", 0);
                    a.a("已经添加到下载列表中");
                    a.a();
                    break;
                }
                return true;
            case 5010:
                if (d()) {
                    MusicAllTag musicAllTag = (MusicAllTag) message.obj;
                    if (this.C == null) {
                        this.C = new b(this, musicAllTag.getBuyUrl(), "购买");
                    }
                    if (!(this.C == null || this.C.f())) {
                        this.C.a(musicAllTag.getBuyUrl(), 5);
                        break;
                    }
                }
                return true;
            case 5011:
                if (d()) {
                    af a2 = af.a(getApplicationContext(), "", 0);
                    a2.a((String) message.obj);
                    a2.a();
                    break;
                }
                return true;
            case 5012:
                if (this.x == 1) {
                    this.h.notifyDataSetChanged();
                }
                return true;
        }
        return super.handleMessageImp(message);
    }

    private void j() {
        try {
            if (l.a != null) {
                int k = l.a.k();
                if (k == 1 || k == 6 || k == 3 || k == 2) {
                    this.o.setImageResource(R.drawable.music_book_controller_play);
                    return;
                } else {
                    this.o.setImageResource(R.drawable.music_book_controller_pause);
                    return;
                }
            }
            this.o.setImageResource(R.drawable.music_book_controller_play);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onStart() {
        super.onStart();
        registerReceiver(this.A, new IntentFilter(e.h));
        registerReceiver(this.A, new IntentFilter(e.i));
        registerReceiver(this.A, new IntentFilter(e.j));
        registerReceiver(this.A, new IntentFilter(e.k));
        registerReceiver(this.A, new IntentFilter(e.l));
        registerReceiver(this.A, new IntentFilter(com.qq.reader.common.c.a.cp));
    }

    public void onStop() {
        super.onStop();
    }

    protected void onResume() {
        super.onResume();
        this.r = false;
        this.c.a(TaskStateEnum.values(), this.z);
        k();
        h();
        if (this.b == 12351 || this.b == -1) {
            SongInfo c = n.a().c();
            if (c != null) {
                this.h.e(c.g());
            }
        }
        if (!(this.b == 12351 || this.b == -1)) {
            a(i());
        }
        j();
    }

    protected void onPause() {
        super.onPause();
        this.r = true;
        this.t.removeMessages(5000);
        this.c.b(TaskStateEnum.values(), this.z);
        SongInfo c = n.a().c();
        if (c != null) {
            this.a.b(c.g());
        }
    }

    public void onDestroy() {
        try {
            unregisterReceiver(this.A);
            this.c.b(this.v, this.y);
            l.b(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void a() {
        try {
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.x == 1) {
            a();
            return true;
        } else if (this.x != 2) {
            return true;
        } else {
            this.x = 1;
            a(false);
            return true;
        }
    }

    private void k() {
        List b = this.a.b();
        if (b != null && b.size() > 0) {
            a(b);
            this.h.a();
            this.h.a(b);
            if (this.x == 1) {
                this.h.notifyDataSetChanged();
            }
            if (this.x == 2) {
                this.i.a(this.h.b(this.i.c()));
                this.i.notifyDataSetChanged();
            }
        }
    }

    private void a(List<Mark> list) {
        List a = this.c.a();
        if (a != null) {
            for (int i = 0; i < a.size(); i++) {
                MusicDownloadTask musicDownloadTask = (MusicDownloadTask) a.get(i);
                int i2 = 0;
                while (i2 < list.size()) {
                    MusicDownloadMark musicDownloadMark = (MusicDownloadMark) list.get(i2);
                    if (musicDownloadMark.getBookId() == musicDownloadTask.getBookId() && musicDownloadMark.getChapterId() == musicDownloadTask.getChapterId()) {
                        musicDownloadMark.setDownloadTask(musicDownloadTask);
                        if (musicDownloadTask.getState() == TaskStateEnum.Installing) {
                            Message obtainMessage = this.t.obtainMessage();
                            obtainMessage.what = 5003;
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("tag_dt", musicDownloadTask);
                            obtainMessage.setData(bundle);
                            this.t.sendMessage(obtainMessage);
                        }
                    } else {
                        i2++;
                    }
                }
            }
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        SongInfo c = n.a().c();
        if (c != null && c.c().trim().length() > 0) {
            Mark b = this.a.b(c.c());
            if (b != null) {
                List c2 = this.a.c(b.getBookId());
                if (c2 != null && c2.size() > 0) {
                    try {
                        l.a.a(b(c2), null, c);
                        l.a.a(n.a().b());
                        Message obtainMessage = this.t.obtainMessage();
                        obtainMessage.what = 5006;
                        obtainMessage.obj = new long[]{n.a().b(), c.b()};
                        this.t.sendMessage(obtainMessage);
                        this.b = 12351;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        g();
        try {
            l.a.a(12);
            if (l.a.r() >= 0 || l.a.a() || l.a.o() != null) {
                j();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    private int a(String str, SongInfo[] songInfoArr) {
        if (!(songInfoArr == null || str == null)) {
            for (int i = 0; i < songInfoArr.length; i++) {
                SongInfo songInfo = songInfoArr[i];
                if (songInfo != null && str.equals(songInfo.c())) {
                    return i;
                }
            }
        }
        return -1;
    }

    private SongInfo[] b(List<Mark> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            MusicDownloadMark musicDownloadMark = (MusicDownloadMark) list.get(i);
            File file = new File(musicDownloadMark.getId());
            if (file.exists() && file.length() > 0) {
                arrayList.add(musicDownloadMark.changeToSong());
            }
        }
        SongInfo[] songInfoArr = new SongInfo[arrayList.size()];
        arrayList.toArray(songInfoArr);
        return songInfoArr;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1000 && !getContext().getSharedPreferences("internalplugin", 0).getBoolean(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SET_AVATAR, true)) {
            finish();
        }
    }

    public void a(MusicAllTag musicAllTag) {
        if (this.C != null && this.C.f()) {
            this.C.cancel();
        }
        this.D = musicAllTag;
        c();
        c cVar = new c(this.D.clone());
        cVar.a(b());
        cVar.start();
    }

    public b b() {
        if (this.E == null) {
            this.E = new b(this) {
                final /* synthetic */ MusicActivity a;

                {
                    this.a = r1;
                }

                public void a(MusicAllTag musicAllTag) {
                    if (this.a.D != null && this.a.D.getBookId() == musicAllTag.getBookId()) {
                        List onlineTags = musicAllTag.getOnlineTags();
                        Object obj = null;
                        int i = 0;
                        while (i < onlineTags.size()) {
                            Object obj2;
                            MusicOnlineTag musicOnlineTag = (MusicOnlineTag) onlineTags.get(i);
                            String a = ao.a(musicOnlineTag);
                            MusicDownloadMark musicDownloadMark = new MusicDownloadMark(a, musicOnlineTag.getBname(), musicOnlineTag.getCname(), musicOnlineTag.getBid(), musicOnlineTag.getCid(), musicOnlineTag.getCtime(), musicOnlineTag.getCsize(), 0);
                            musicDownloadMark.setReadTime(System.currentTimeMillis());
                            musicDownloadMark.setAuthor(musicOnlineTag.getAuthor());
                            if (i.a().b(a) == null) {
                                e eVar = (e) com.qq.reader.common.download.task.l.b(1004);
                                Object musicDownloadTask = new MusicDownloadTask(musicOnlineTag.getDownloadUrl(), musicOnlineTag.getBid(), musicOnlineTag.getCid(), 0, musicOnlineTag.getFileFormat(), musicOnlineTag.getmDrmFlag());
                                if (eVar.a(musicDownloadTask)) {
                                    musicDownloadMark.setDownloadTask(musicDownloadTask);
                                    i.a().a(musicDownloadMark, musicOnlineTag.getChapterCount());
                                    obj2 = 1;
                                    i++;
                                    obj = obj2;
                                }
                            }
                            obj2 = obj;
                            i++;
                            obj = obj2;
                        }
                        if (obj != null) {
                            this.a.t.sendEmptyMessage(5008);
                        } else {
                            this.a.t.sendEmptyMessage(5009);
                        }
                        this.a.D = null;
                    }
                }

                public Context a() {
                    return this.a.getApplicationContext();
                }

                public void a(MusicAllTag musicAllTag, String str) {
                    if (this.a.D != null && this.a.D.getBookId() == musicAllTag.getBookId()) {
                        Message obtainMessage = this.a.t.obtainMessage();
                        obtainMessage.what = 5011;
                        obtainMessage.obj = str;
                        this.a.t.sendMessage(obtainMessage);
                        this.a.D = null;
                    }
                }

                public void b(MusicAllTag musicAllTag) {
                    if (this.a.D != null && this.a.D.getBookId() == musicAllTag.getBookId()) {
                        Message obtainMessage = this.a.t.obtainMessage();
                        obtainMessage.what = 5010;
                        obtainMessage.obj = musicAllTag;
                        this.a.t.sendMessage(obtainMessage);
                        this.a.D = null;
                    }
                }
            };
        }
        return this.E;
    }

    protected void c() {
        if (this.B == null) {
            this.B = new c(this);
            this.B.c(true);
            this.B.a(getResources().getString(R.string.get_book_music_feed_loading));
        }
        if (!this.B.f()) {
            this.B.f_();
        }
    }

    protected boolean d() {
        if (this.B == null || !this.B.f()) {
            return false;
        }
        this.B.cancel();
        return true;
    }

    public void e() {
        if (this.C != null && this.C.f()) {
            this.C.cancel();
        }
    }

    private void a(SongInfo[] songInfoArr, long j) throws RemoteException {
        long j2 = l.a.j();
        if (j2 >= 0 && this.b != 12351) {
            SongInfo o = l.a.o();
            if (o == null) {
                o = songInfoArr[0];
            }
            if (this.a.a(j, j2, o.c())) {
                this.h.a(j, j2, o.c());
            }
        }
    }

    public void f() {
        this.t.post(new Runnable(this) {
            final /* synthetic */ MusicActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.C != null && this.a.C.f()) {
                    this.a.C.cancel();
                }
            }
        });
    }
}
