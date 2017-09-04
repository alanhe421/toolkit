package com.qq.reader.plugin.audiobook.core;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.readpage.c;

/* compiled from: QQPlayerPreferences */
public class n {
    private static n a;
    private SharedPreferences b;

    private n() {
        if (this.b == null) {
            this.b = ReaderApplication.getApplicationImp().getSharedPreferences("qqmusic4zmini4readerprefer", 3);
        }
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            if (a == null) {
                a = new n();
            }
            nVar = a;
        }
        return nVar;
    }

    public int a(int i) {
        if (this.b != null) {
            return this.b.getInt("playmode", i);
        }
        return i;
    }

    public void b(int i) {
        if (this.b != null) {
            Editor edit = this.b.edit();
            edit.putInt("playmode", i);
            edit.commit();
        }
    }

    public void a(SongInfo songInfo, long j, long j2) {
        a(songInfo, j);
    }

    public long b() {
        if (this.b != null) {
            return this.b.getLong("lastPlayingSongTime", 0);
        }
        return 0;
    }

    public SongInfo c() {
        if (this.b == null) {
            return null;
        }
        SongInfo songInfo = new SongInfo(this.b.getString("lastPlayingSongInfoFilePath", ""), this.b.getLong("lastPlayingSongInfoFileId", -1));
        songInfo.a(this.b.getInt("lastPlayingSongInfoErr", 0));
        songInfo.a(this.b.getLong("lastPlayingSongInfoDuration", 0));
        songInfo.b(this.b.getLong("lastPlayingSongInfoFileSize", 0));
        return songInfo;
    }

    public static void a(SongInfo songInfo, long j) {
        if (songInfo != null) {
            long e = songInfo.e();
            Mark e2 = i.c().e(String.valueOf(e));
            if (e2 != null) {
                e2.setPercentStr("第" + songInfo.f() + "集");
                e2.setLastReadChapterName(songInfo.h());
                i.c().a(e2, true);
            }
            OnlineTag a = v.b().a(String.valueOf(e));
            if (a != null) {
                a.c(songInfo.f());
                a.a(j);
                v.b().b(a);
                if (e2 != null) {
                    c cVar = new c(ReaderApplication.getApplicationImp(), null, 2);
                    cVar.b(null, null, a);
                    cVar.c(null, null, a);
                }
            }
        }
    }
}
