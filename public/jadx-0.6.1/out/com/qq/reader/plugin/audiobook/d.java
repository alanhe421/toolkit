package com.qq.reader.plugin.audiobook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.framework.mark.Mark;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MusicBookAdapter */
public class d extends BaseAdapter {
    private Context a;
    private List<MusicBookGroup> b = new ArrayList();
    private List<List<Mark>> c = new ArrayList();
    private Map<Long, List<Mark>> d = new HashMap();
    private long e = -1;

    public d(Context context) {
        this.a = context;
    }

    public void a(List<Mark> list) {
        int i = 0;
        this.b = i.a().c();
        if (this.b != null && this.b.size() > 0) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                ArrayList arrayList = new ArrayList();
                this.c.add(arrayList);
                this.d.put(Long.valueOf(((MusicBookGroup) this.b.get(i2)).getBookId()), arrayList);
            }
            for (Mark mark : list) {
                if (mark != null) {
                    List list2 = (List) this.d.get(Long.valueOf(mark.getBookId()));
                    if (list2 != null) {
                        list2.add(mark);
                    }
                }
            }
            while (i < this.c.size()) {
                ((MusicBookGroup) this.b.get(i)).setCurDownloadChapterId(b((List) this.c.get(i)));
                i++;
            }
        }
    }

    public void a(long j, long j2, String str) {
        for (MusicBookGroup musicBookGroup : this.b) {
            if (j == musicBookGroup.getBookId()) {
                musicBookGroup.setLastSeekTime(j2);
                musicBookGroup.setLastSongId(str);
                return;
            }
        }
    }

    public void a(long j) {
        this.d.remove(Long.valueOf(j));
        int i = 0;
        while (i < this.b.size()) {
            MusicBookGroup musicBookGroup = (MusicBookGroup) this.b.get(i);
            if (musicBookGroup == null || musicBookGroup.getBookId() != j) {
                i++;
            } else {
                this.b.remove(i);
                this.c.remove(i);
                return;
            }
        }
    }

    public List<Mark> b(long j) {
        return (List) this.d.get(Long.valueOf(j));
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void a() {
        this.b.clear();
        this.c.clear();
        this.d.clear();
    }

    public MusicBookGroup c(long j) {
        for (MusicBookGroup musicBookGroup : this.b) {
            if (musicBookGroup.getBookId() == j) {
                return musicBookGroup;
            }
        }
        return null;
    }

    public long d(long j) {
        long chapterId;
        Object obj;
        Object obj2 = null;
        for (Mark mark : b(j)) {
            Object obj3;
            MusicDownloadTask downloadTask = ((MusicDownloadMark) mark).getDownloadTask();
            if (downloadTask == null || downloadTask.getState() != TaskStateEnum.Prepared) {
                obj3 = obj2;
            } else {
                obj3 = 1;
            }
            if (downloadTask != null && downloadTask.getState() == TaskStateEnum.Started) {
                Object obj4 = obj3;
                chapterId = downloadTask.getChapterId();
                obj = obj4;
                break;
            }
            obj2 = obj3;
        }
        chapterId = -1000;
        obj = obj2;
        if (chapterId >= 0 || r2 == null) {
            return chapterId;
        }
        return -1001;
    }

    public long b(List<Mark> list) {
        long chapterId;
        Object obj;
        Object obj2 = null;
        for (Mark mark : list) {
            Object obj3;
            MusicDownloadTask downloadTask = ((MusicDownloadMark) mark).getDownloadTask();
            if (downloadTask == null || downloadTask.getState() != TaskStateEnum.Prepared) {
                obj3 = obj2;
            } else {
                obj3 = 1;
            }
            if (downloadTask != null && downloadTask.getState() == TaskStateEnum.Started) {
                Object obj4 = obj3;
                chapterId = downloadTask.getChapterId();
                obj = obj4;
                break;
            }
            obj2 = obj3;
        }
        chapterId = -1000;
        obj = obj2;
        if (chapterId >= 0 || r2 == null) {
            return chapterId;
        }
        return -1001;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        boolean z;
        if (view == null) {
            View view2 = (MusicBookListGroupItem) LayoutInflater.from(this.a).inflate(R.layout.music_book_list_group_item, null, false);
            view2.a();
            view = view2;
        } else {
            MusicBookListGroupItem musicBookListGroupItem = (MusicBookListGroupItem) view;
        }
        MusicBookGroup musicBookGroup = (MusicBookGroup) getItem(i);
        view.setBookName(musicBookGroup.getBookName());
        view.setBookAuthor(musicBookGroup.getAuthor());
        view.setBookDownload(musicBookGroup.getCurDownloadChapterId());
        if (this.e == musicBookGroup.getBookId()) {
            z = true;
        } else {
            z = false;
        }
        view.setIsPlaying(z);
        return view;
    }

    public void e(long j) {
        this.e = j;
    }
}
