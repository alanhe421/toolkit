package com.qq.reader.plugin.audiobook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.framework.mark.Mark;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MusicChapterAdatper */
public class f extends BaseAdapter {
    private Context a;
    private String b;
    private long c;
    private String d;
    private String e = null;
    private List<Mark> f = new ArrayList();

    public f(Context context) {
        this.a = context;
        this.e = this.a.getResources().getString(R.string.music_download_percent);
    }

    public void a(List<Mark> list) {
        if (list != null) {
            this.f = list;
        }
    }

    public void a(int i) {
        this.f.remove(i);
    }

    public int getCount() {
        return this.f.size();
    }

    public List<Mark> a() {
        return this.f;
    }

    public int a(String str) {
        for (int i = 0; i < this.f.size(); i++) {
            Mark mark = (Mark) this.f.get(i);
            if (str != null && str.equals(mark.getId())) {
                return i;
            }
        }
        return -1;
    }

    public Object getItem(int i) {
        return this.f.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void b() {
        this.f.clear();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            View view2 = (MusicBookListChapterItem) LayoutInflater.from(this.a).inflate(R.layout.music_book_list_chapter_item, null, false);
            view2.a();
            view = view2;
        } else {
            MusicBookListChapterItem musicBookListChapterItem = (MusicBookListChapterItem) view;
        }
        MusicDownloadMark musicDownloadMark = (MusicDownloadMark) this.f.get(i);
        view.setTitle(musicDownloadMark.getChapterName());
        view.setDuration(musicDownloadMark.getCtime());
        view.setFileLength(musicDownloadMark.getCsize());
        view.setIsPlaying(musicDownloadMark.getId().equals(this.b));
        com.qq.reader.common.download.task.f downloadTask = musicDownloadMark.getDownloadTask();
        if (downloadTask != null) {
            view.setStatus(downloadTask.getState());
            view.setProgress(String.format(this.e, new Object[]{Integer.valueOf(downloadTask.getProgress())}));
        }
        return view;
    }

    public void b(String str) {
        this.b = str;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }
}
