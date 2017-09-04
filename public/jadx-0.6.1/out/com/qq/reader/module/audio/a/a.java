package com.qq.reader.module.audio.a;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.d;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineFile;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.buy.chapter.ChapterPayResult;
import com.qq.reader.cservice.buy.chapter.b;
import com.qq.reader.cservice.onlineread.OnlineChapterDownloadTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.cservice.onlineread.c;
import com.qq.reader.module.audio.loader.AudioAuthChaptersTask;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* compiled from: AudioChapterBatHandle */
public class a implements b, c {
    protected volatile int a = 3;
    protected ExecutorService b;
    protected final Map<OnlineChapterDownloadTask, List<Integer>> c = Collections.synchronizedMap(new HashMap());
    private Context d;
    private com.qq.reader.cservice.buy.chapter.a e;
    private com.qq.reader.cservice.download.chapter.b f;
    private OnlineTag g;
    private List<List<Integer>> h = Collections.synchronizedList(new ArrayList());
    private List<OnlineChapter> i;
    private JSONObject j;
    private boolean k = false;
    private ArrayList<Integer> l = new ArrayList();

    public a(OnlineTag onlineTag, Context context) {
        this.d = context;
        this.g = onlineTag.z();
    }

    public void b() {
        this.k = false;
    }

    public void c() {
        this.k = true;
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
        this.e = new com.qq.reader.cservice.buy.chapter.a(this.g, list, i, this.d);
        this.e.a(this);
        this.e.start();
    }

    public synchronized void c(List<Integer> list) {
        ReaderTask audioAuthChaptersTask = new AudioAuthChaptersTask(this.g.k(), ao.a((List) list));
        audioAuthChaptersTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int optInt = jSONObject.optInt("result");
                    if (optInt != 0) {
                        String string;
                        switch (optInt) {
                            case -100204:
                                string = ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_check_level_limit);
                                break;
                            case -100199:
                                string = ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_off_market_tip);
                                break;
                            case -100132:
                                string = ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_already_buy_tip);
                                break;
                            default:
                                string = ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_error_common_tip);
                                break;
                        }
                        this.a.f.a(optInt, string);
                        return;
                    }
                    this.a.j = jSONObject.optJSONObject("auth");
                    this.a.d();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        });
        g.a().a(audioAuthChaptersTask);
    }

    public synchronized void d() {
        if (this.g.F() == 2 && this.j != null) {
            if (ao.j(this.d) || com.qq.reader.plugin.audiobook.core.b.a == 1) {
                for (int i = 0; i < this.i.size(); i++) {
                    OnlineChapter onlineChapter = (OnlineChapter) this.i.get(i);
                    JSONObject optJSONObject = this.j.optJSONObject(onlineChapter.getUUID() + "");
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString(SocialConstants.PARAM_URL);
                        com.qq.reader.common.monitor.debug.c.e("AudioChapterBatHandle", "bookid: " + onlineChapter.getBookId() + "\nchaterid: " + onlineChapter.getChapterId() + "-" + onlineChapter.getUUID() + "\nname: " + onlineChapter.getChapterName() + "\ndir: " + onlineChapter.getFileDir() + "\nfirname: " + onlineChapter.getFileName() + "\nurl: " + optString);
                        com.qq.reader.cservice.download.audio.a.a().d(new com.qq.reader.cservice.download.audio.b(this.g, onlineChapter.getBookId(), onlineChapter.getChapterId(), onlineChapter.getChapterName(), onlineChapter.getFileDir(), onlineChapter.getFileName(), optString, 1), true);
                    }
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
        List arrayList = new ArrayList();
        int i = 0;
        while (this.h != null && i < this.h.size()) {
            arrayList.addAll((List) this.h.get(i));
            i++;
        }
        c(arrayList);
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
                    if (!(this.l == null || onlineChapterDownloadTask == null || onlineChapterDownloadTask.getDownloadChap() == null || onlineChapterDownloadTask.getDownloadChap().size() <= 0)) {
                        this.l.addAll(onlineChapterDownloadTask.getDownloadChap());
                    }
                    Runnable onlineChapterDownloadTask2 = new OnlineChapterDownloadTask(this.g, this);
                    List list = (List) this.h.remove(0);
                    onlineChapterDownloadTask2.setToDownloadChapters(list);
                    onlineChapterDownloadTask2.setBatDownload(true);
                    this.c.put(onlineChapterDownloadTask2, list);
                    this.b.submit(onlineChapterDownloadTask2);
                } else if (this.c.size() == 0) {
                    if (!(this.l == null || onlineChapterDownloadTask == null || onlineChapterDownloadTask.getDownloadChap() == null || onlineChapterDownloadTask.getDownloadChap().size() <= 0)) {
                        this.l.addAll(onlineChapterDownloadTask.getDownloadChap());
                    }
                    long longValue = Long.valueOf(onlineTag.k()).longValue();
                    if (longValue > 0) {
                        com.qq.reader.cservice.cloud.b.a(this.d.getApplicationContext()).a(new com.qq.reader.cservice.cloud.a.b(longValue, 1, 0, 0, this.g.F()), false, null);
                    }
                    if (this.k) {
                        ao.a(this.d, (byte) 25, onlineTag, null);
                    }
                    Intent intent = new Intent("com.qq.reader.chapter.DownloadSucess");
                    if (onlineChapterDownloadTask != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(this.l);
                        intent.putIntegerArrayListExtra("ONLINE_DOWNLOAD_CHAP_KEY", arrayList);
                        this.l.clear();
                    }
                    if (this.d != null) {
                        d.a(this.d).a(intent);
                    }
                    if (this.b != null) {
                        this.b.shutdownNow();
                        this.b = null;
                    }
                } else if (!(this.c.size() <= 0 || this.l == null || onlineChapterDownloadTask == null || onlineChapterDownloadTask.getDownloadChap() == null || onlineChapterDownloadTask.getDownloadChap().size() <= 0)) {
                    this.l.addAll(onlineChapterDownloadTask.getDownloadChap());
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
                if (this.k) {
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
        if (this.k) {
            ao.a(this.d, (byte) 26, onlineTag, readOnlineResult.y());
        }
    }

    public void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult) {
    }

    public void a() {
    }

    public void a(com.qq.reader.cservice.download.chapter.b bVar) {
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

    public void d(List<OnlineChapter> list) {
        this.i = list;
        Collections.sort(this.i, new Comparator<OnlineChapter>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((OnlineChapter) obj, (OnlineChapter) obj2);
            }

            public int a(OnlineChapter onlineChapter, OnlineChapter onlineChapter2) {
                if (onlineChapter.getChapterId() < onlineChapter2.getChapterId()) {
                    return -1;
                }
                return onlineChapter.getChapterId() == onlineChapter2.getChapterId() ? 0 : 1;
            }
        });
    }
}
