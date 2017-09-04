package com.qq.reader.cservice.download.chapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineFile;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.buy.chapter.ChapterPayResult;
import com.qq.reader.cservice.buy.chapter.b;
import com.qq.reader.cservice.onlineread.OnlineChapterDownloadTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.cservice.onlineread.c;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: ChapterBatHandle */
public class a implements b, c {
    protected volatile int a = 3;
    protected ExecutorService b;
    protected final Map<OnlineChapterDownloadTask, List<Integer>> c = Collections.synchronizedMap(new HashMap());
    private Context d;
    private com.qq.reader.cservice.buy.chapter.c e;
    private b f;
    private OnlineTag g;
    private List<List<Integer>> h = Collections.synchronizedList(new ArrayList());
    private List<OnlineChapter> i;
    private boolean j = false;
    private ArrayList<Integer> k = new ArrayList();

    public a(OnlineTag onlineTag, Context context) {
        this.d = context;
        this.g = onlineTag.z();
    }

    public void b() {
        this.j = false;
    }

    public void c() {
        this.j = true;
    }

    protected synchronized void d() {
        if (this.b != null) {
            this.b.shutdownNow();
            this.b = null;
        }
        if (this.a > 0) {
            this.b = Executors.newFixedThreadPool(this.a);
        } else {
            this.b = Executors.newFixedThreadPool(3);
        }
    }

    public synchronized void b(List<Integer> list) {
        this.h.clear();
        int size = list.size();
        Collections.sort(list);
        List arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(list.get(i2));
            i++;
            if (i >= 100) {
                this.h.add(arrayList);
                arrayList = new ArrayList();
                i = 0;
            }
        }
        if (arrayList.size() > 0) {
            this.h.add(arrayList);
        }
    }

    public void a(List<Integer> list, int i) {
        this.e = new com.qq.reader.cservice.buy.chapter.c(this.g, list, i, this.d);
        this.e.a(this);
        this.e.start();
    }

    public synchronized void e() {
        if (this.g.F() == 1) {
            d();
            int size = this.h.size();
            if (size > 0) {
                int min = Math.min(size, this.a);
                for (size = 0; size < min; size++) {
                    Runnable onlineChapterDownloadTask = new OnlineChapterDownloadTask(this.g, this);
                    List list = (List) this.h.remove(0);
                    onlineChapterDownloadTask.setToDownloadChapters(list);
                    onlineChapterDownloadTask.setBatDownload(true);
                    this.c.put(onlineChapterDownloadTask, list);
                    this.b.submit(onlineChapterDownloadTask);
                }
                if (this.f != null) {
                    this.f.d();
                    this.f = null;
                }
            }
        } else if (this.g.F() == 2) {
            if (ao.j(this.d) || com.qq.reader.plugin.audiobook.core.b.a == 1) {
                com.qq.reader.common.conn.a.b c = com.qq.reader.common.conn.a.c.a().c();
                String a = com.qq.reader.common.login.c.c().a(this.d);
                String a2 = this.g.a(c.a(), a);
                for (int i = 0; i < this.i.size(); i++) {
                    OnlineChapter onlineChapter = (OnlineChapter) this.i.get(i);
                    com.qq.reader.common.monitor.debug.c.e("ChapterBatHandle", onlineChapter.getChapterName());
                    com.qq.reader.cservice.download.audio.a.a().d(new com.qq.reader.cservice.download.audio.b(this.g, onlineChapter.getBookId(), onlineChapter.getChapterId(), onlineChapter.getChapterName(), onlineChapter.getFileDir(), onlineChapter.getFileName(), a2 + onlineChapter.getUUID(), 1), true);
                }
                if (this.f != null) {
                    this.f.d();
                    this.f = null;
                }
            } else if (this.f != null) {
                this.f.e();
            }
        }
    }

    public void a(ChapterPayResult chapterPayResult) {
        j.a(60, 1);
        i.a("event_B61", null, this.d);
        StatisticsManager.a().a("event_B61", null);
        if (this.f != null) {
            this.f.a(chapterPayResult);
        }
        d(chapterPayResult);
        e();
    }

    private void d(ChapterPayResult chapterPayResult) {
        List needBuyChapters = chapterPayResult.getNeedBuyChapters();
        Collection arrayList = new ArrayList();
        int i = 0;
        while (this.h != null && i < this.h.size()) {
            List list = (List) this.h.get(i);
            Collection arrayList2 = new ArrayList();
            int i2 = 0;
            while (list != null && i2 < list.size()) {
                Integer num = (Integer) list.get(i2);
                if (needBuyChapters != null && needBuyChapters.contains(num)) {
                    arrayList2.add(num);
                }
                i2++;
            }
            list.removeAll(arrayList2);
            if (list.size() == 0) {
                arrayList.add(list);
            }
            i++;
        }
        this.h.removeAll(arrayList);
    }

    public void c(ChapterPayResult chapterPayResult) {
        if (this.f != null) {
            this.f.b(chapterPayResult);
        }
    }

    public void b(ChapterPayResult chapterPayResult) {
        if (this.f != null) {
            this.f.c(chapterPayResult);
        }
    }

    public void a(OnlineTag onlineTag, OnlineChapterDownloadTask onlineChapterDownloadTask) {
        synchronized (this) {
            if (((List) this.c.remove(onlineChapterDownloadTask)) != null) {
                if (this.h.size() > 0) {
                    if (!(this.k == null || onlineChapterDownloadTask == null || onlineChapterDownloadTask.getDownloadChap() == null || onlineChapterDownloadTask.getDownloadChap().size() <= 0)) {
                        this.k.addAll(onlineChapterDownloadTask.getDownloadChap());
                    }
                    Runnable onlineChapterDownloadTask2 = new OnlineChapterDownloadTask(this.g, this);
                    List list = (List) this.h.remove(0);
                    onlineChapterDownloadTask2.setToDownloadChapters(list);
                    onlineChapterDownloadTask2.setBatDownload(true);
                    this.c.put(onlineChapterDownloadTask2, list);
                    this.b.submit(onlineChapterDownloadTask2);
                } else if (this.c.size() == 0) {
                    if (!(this.k == null || onlineChapterDownloadTask == null || onlineChapterDownloadTask.getDownloadChap() == null || onlineChapterDownloadTask.getDownloadChap().size() <= 0)) {
                        this.k.addAll(onlineChapterDownloadTask.getDownloadChap());
                    }
                    long longValue = Long.valueOf(onlineTag.k()).longValue();
                    if (longValue > 0) {
                        com.qq.reader.cservice.cloud.b.a(this.d.getApplicationContext()).a(new com.qq.reader.cservice.cloud.a.b(longValue, 1, 0, 0, this.g.F()), false, null);
                    }
                    if (this.j) {
                        ao.a(this.d, (byte) 25, onlineTag, null);
                    }
                    Intent intent = new Intent("com.qq.reader.chapter.DownloadSucess");
                    if (onlineChapterDownloadTask != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(this.k);
                        intent.putIntegerArrayListExtra("ONLINE_DOWNLOAD_CHAP_KEY", arrayList);
                        this.k.clear();
                    }
                    if (this.d != null) {
                        d.a(this.d).a(intent);
                    }
                    if (this.b != null) {
                        this.b.shutdownNow();
                        this.b = null;
                    }
                } else if (!(this.c.size() <= 0 || this.k == null || onlineChapterDownloadTask == null || onlineChapterDownloadTask.getDownloadChap() == null || onlineChapterDownloadTask.getDownloadChap().size() <= 0)) {
                    this.k.addAll(onlineChapterDownloadTask.getDownloadChap());
                }
            }
        }
    }

    public void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult, OnlineChapterDownloadTask onlineChapterDownloadTask) {
        synchronized (this) {
            if (((List) this.c.remove(onlineChapterDownloadTask)) != null) {
                List list;
                List arrayList = new ArrayList();
                this.h.add(0, readOnlineResult.y());
                for (Entry value : this.c.entrySet()) {
                    list = (List) value.getValue();
                    if (list != null) {
                        this.h.add(0, list);
                    }
                }
                for (List list2 : this.h) {
                    arrayList.addAll(list2);
                }
                if (this.j) {
                    ao.a(this.d, (byte) 26, onlineTag, arrayList);
                }
                Intent intent = new Intent("com.qq.reader.chapter.DownloadFailed");
                if (this.d != null) {
                    d.a(this.d).a(intent);
                }
                this.c.clear();
                if (this.b != null) {
                    this.b.shutdownNow();
                    this.b = null;
                }
            }
        }
    }

    public void b(OnlineTag onlineTag, ReadOnlineResult readOnlineResult) {
        if (this.j) {
            ao.a(this.d, (byte) 26, onlineTag, readOnlineResult.y());
        }
    }

    public void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult) {
    }

    public void a() {
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public void a(OnlineTag onlineTag) {
        Intent intent = new Intent("com.qq.reader.chapter.updatecount");
        intent.putExtra("book_id", onlineTag.k());
        intent.putExtra("book_max_chapter", onlineTag.n());
        d.a(this.d).a(intent);
    }

    public void a(List<ReadOnlineFile> list) {
        ArrayList arrayList = (ArrayList) list;
        Intent intent = new Intent("com.qq.reader.chapter.updatefilelist");
        intent.putExtra("book_id", this.g.k());
        intent.putExtra("chapter_file_list", arrayList);
        d.a(this.d).a(intent);
    }
}
