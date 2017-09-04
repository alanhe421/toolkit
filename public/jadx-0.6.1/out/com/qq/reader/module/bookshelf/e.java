package com.qq.reader.module.bookshelf;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.a;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.plugin.audiobook.core.SongInfo;
import com.qq.reader.plugin.audiobook.core.l;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/* compiled from: BookShelfAdapater */
public class e extends i {
    public e(Context context, boolean z) {
        super(context, z);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Mark mark = (Mark) getItem(i);
        b c = a.a().c();
        if (mark != null) {
            String str;
            com.qq.reader.module.bookshelf.view.a aVar;
            boolean z;
            Object obj;
            long operateTime;
            long operateTime2;
            DownloadBookTask downloadTask;
            boolean l;
            com.qq.reader.common.login.b.a c2 = c.c();
            if (!this.n || c2 == null) {
                str = null;
            } else {
                str = c2.c();
            }
            if (view == null) {
                View inflate;
                com.qq.reader.module.bookshelf.view.a aVar2;
                if (c == null || c.b() != mark.getBookId()) {
                    inflate = LayoutInflater.from(this.a).inflate(R.layout.bookshelf_list_item, null);
                    aVar2 = new com.qq.reader.module.bookshelf.view.a(inflate, this.a);
                    aVar2.a(false);
                } else {
                    inflate = LayoutInflater.from(this.a).inflate(R.layout.bookshelf_list_item_for_activate, null);
                    aVar2 = new com.qq.reader.module.bookshelf.view.a(inflate, this.a);
                    aVar2.a(true);
                }
                inflate.setTag(aVar2);
                view = inflate;
                aVar = aVar2;
            } else {
                aVar = (com.qq.reader.module.bookshelf.view.a) view.getTag();
            }
            int type = mark.getType();
            aVar.e(type);
            aVar.a(type);
            aVar.b(mark.getPrivateProperty());
            String str2 = "";
            Object lastUpdateChapter = mark.getLastUpdateChapter();
            aVar.d(mark.getBookShortName());
            aVar.a(mark.getBookShortName());
            aVar.b(mark.getBookName());
            String lastReadChapterName = mark.getLastReadChapterName();
            Object obj2 = null;
            boolean z2 = false;
            if (mark.getReadTime() > 0 && !TextUtils.isEmpty(lastReadChapterName)) {
                if (mark.getType() == 8) {
                    aVar.e("听至：" + lastReadChapterName);
                } else {
                    aVar.e("读至：" + lastReadChapterName);
                }
                obj2 = 1;
            }
            if (mark.hasNewContent() && !TextUtils.isEmpty(lastUpdateChapter)) {
                aVar.e("更新至：" + lastUpdateChapter);
                obj2 = 1;
            }
            if (mark.getType() == 8 && l.a != null) {
                try {
                    int k = l.a.k();
                    if (k == 0 || k == 5 || k == 4) {
                        SongInfo o = l.a.o();
                        if (o != null && o.e() == mark.getBookId()) {
                            aVar.e("正在播放：" + o.h());
                            z = true;
                            obj = 1;
                            lastUpdateChapter = obj;
                            z2 = z;
                            if (lastUpdateChapter == null) {
                                if (mark.getBookId() > 0) {
                                    aVar.e(mark.getAuthor());
                                } else {
                                    aVar.e(mark.getPercentStr());
                                }
                            }
                            if (!(mark instanceof LocalMark)) {
                                if (mark.getSortIndex() > 0) {
                                    view.setBackgroundResource(R.drawable.list_item_top_bg);
                                } else {
                                    view.setBackgroundResource(R.drawable.list_item_trans_bg);
                                }
                                aVar.d(mark.getBookShortName());
                                aVar.d(mark.getReadTime() > 1);
                                aVar.c(mark.hasNewContent());
                                operateTime = mark.getOperateTime();
                                z = lastUpdateChapter == null && mark.hasNewContent();
                                aVar.a(operateTime, z);
                                aVar.d(8);
                                if (aVar.a()) {
                                    if (c == null && mark.getBookId() == c.b()) {
                                        aVar.e(c.g());
                                        com.qq.reader.common.imageloader.c.a(this.a).a(c.f(), aVar.a, com.qq.reader.common.imageloader.a.a().m(), new com.bumptech.glide.request.e<String, b>(this) {
                                            final /* synthetic */ e b;

                                            public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                                                aVar.a.setVisibility(8);
                                                return true;
                                            }

                                            public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                                                aVar.a.setVisibility(0);
                                                return false;
                                            }
                                        });
                                    } else {
                                        aVar.a.setVisibility(8);
                                    }
                                }
                            } else if (mark instanceof DownloadMark) {
                                aVar.d(false);
                                operateTime2 = mark.getOperateTime();
                                z = lastUpdateChapter == null && mark.hasNewContent();
                                aVar.a(operateTime2, z);
                                aVar.c(false);
                                downloadTask = ((DownloadMark) mark).getDownloadTask();
                                aVar.d(0);
                                if (downloadTask != null) {
                                    l = com.qq.reader.readengine.model.a.l(downloadTask.getFullName());
                                    aVar.a(true, downloadTask.getProgress(), downloadTask.getIsOnlyDownLoadIcon());
                                    aVar.a(downloadTask.getState(), downloadTask.getIsOnlyDownLoadIcon(), l);
                                } else {
                                    aVar.a(true, 0, true);
                                    aVar.a(TaskStateEnum.Paused, true, false);
                                }
                                view.setBackgroundResource(R.drawable.list_item_trans_bg);
                            }
                            if (str == null && d.a(this.a, str, mark.getBookId()) > 0) {
                                Date date = new Date(d.a(this.a, str, mark.getBookId()));
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
                                str2 = String.format(this.a.getResources().getString(R.string.sign_limited_book_more_info), new Object[]{simpleDateFormat.format(date)});
                                aVar.c((int) R.color.text_color_c601);
                                aVar.e(str2);
                            } else if (z2) {
                                aVar.c((int) R.color.text_color_c301);
                            } else {
                                aVar.c((int) R.color.text_color_c103);
                            }
                            aVar.b(z2);
                            aVar.b();
                            if (!aVar.a() && mark.isLimitFree()) {
                                aVar.c((int) R.color.text_color_c401);
                                str2 = this.a.getResources().getString(R.string.price_info_limitfree);
                                obj = ao.i(mark.getLimitFreeEndTime());
                                if (!TextUtils.isEmpty(obj)) {
                                    str2 = str2 + "，" + obj;
                                }
                                aVar.e(str2);
                                i.a("event_F313", null, this.a);
                            }
                            if (com.qq.reader.module.comic.e.e.a(this.a, String.valueOf(mark.getBookId()))) {
                                i.a("event_F292", null, this.a);
                            }
                            str2 = mark.getBookName();
                            com.qq.reader.common.imageloader.c.a(this.a).a(mark.getImageURI(), aVar.b, com.qq.reader.common.imageloader.a.a().j(), new com.bumptech.glide.request.e<String, b>(this) {
                                final /* synthetic */ e c;

                                public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                                    aVar.c(str2);
                                    return true;
                                }

                                public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                                    return false;
                                }
                            });
                        }
                    }
                    obj = obj2;
                    z = false;
                    lastUpdateChapter = obj;
                    z2 = z;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (lastUpdateChapter == null) {
                    if (mark.getBookId() > 0) {
                        aVar.e(mark.getPercentStr());
                    } else {
                        aVar.e(mark.getAuthor());
                    }
                }
                if (!(mark instanceof LocalMark)) {
                    if (mark.getSortIndex() > 0) {
                        view.setBackgroundResource(R.drawable.list_item_trans_bg);
                    } else {
                        view.setBackgroundResource(R.drawable.list_item_top_bg);
                    }
                    aVar.d(mark.getBookShortName());
                    if (mark.getReadTime() > 1) {
                    }
                    aVar.d(mark.getReadTime() > 1);
                    aVar.c(mark.hasNewContent());
                    operateTime = mark.getOperateTime();
                    if (lastUpdateChapter == null) {
                    }
                    aVar.a(operateTime, z);
                    aVar.d(8);
                    if (aVar.a()) {
                        if (c == null) {
                        }
                        aVar.a.setVisibility(8);
                    }
                } else if (mark instanceof DownloadMark) {
                    aVar.d(false);
                    operateTime2 = mark.getOperateTime();
                    if (lastUpdateChapter == null) {
                    }
                    aVar.a(operateTime2, z);
                    aVar.c(false);
                    downloadTask = ((DownloadMark) mark).getDownloadTask();
                    aVar.d(0);
                    if (downloadTask != null) {
                        aVar.a(true, 0, true);
                        aVar.a(TaskStateEnum.Paused, true, false);
                    } else {
                        l = com.qq.reader.readengine.model.a.l(downloadTask.getFullName());
                        aVar.a(true, downloadTask.getProgress(), downloadTask.getIsOnlyDownLoadIcon());
                        aVar.a(downloadTask.getState(), downloadTask.getIsOnlyDownLoadIcon(), l);
                    }
                    view.setBackgroundResource(R.drawable.list_item_trans_bg);
                }
                if (str == null) {
                }
                if (z2) {
                    aVar.c((int) R.color.text_color_c103);
                } else {
                    aVar.c((int) R.color.text_color_c301);
                }
                aVar.b(z2);
                aVar.b();
                aVar.c((int) R.color.text_color_c401);
                str2 = this.a.getResources().getString(R.string.price_info_limitfree);
                obj = ao.i(mark.getLimitFreeEndTime());
                if (TextUtils.isEmpty(obj)) {
                    str2 = str2 + "，" + obj;
                }
                aVar.e(str2);
                i.a("event_F313", null, this.a);
                if (com.qq.reader.module.comic.e.e.a(this.a, String.valueOf(mark.getBookId()))) {
                    i.a("event_F292", null, this.a);
                }
                str2 = mark.getBookName();
                com.qq.reader.common.imageloader.c.a(this.a).a(mark.getImageURI(), aVar.b, com.qq.reader.common.imageloader.a.a().j(), /* anonymous class already generated */);
            }
            lastUpdateChapter = obj2;
            if (lastUpdateChapter == null) {
                if (mark.getBookId() > 0) {
                    aVar.e(mark.getAuthor());
                } else {
                    aVar.e(mark.getPercentStr());
                }
            }
            if (!(mark instanceof LocalMark)) {
                if (mark.getSortIndex() > 0) {
                    view.setBackgroundResource(R.drawable.list_item_top_bg);
                } else {
                    view.setBackgroundResource(R.drawable.list_item_trans_bg);
                }
                aVar.d(mark.getBookShortName());
                if (mark.getReadTime() > 1) {
                }
                aVar.d(mark.getReadTime() > 1);
                aVar.c(mark.hasNewContent());
                operateTime = mark.getOperateTime();
                if (lastUpdateChapter == null) {
                }
                aVar.a(operateTime, z);
                aVar.d(8);
                if (aVar.a()) {
                    if (c == null) {
                    }
                    aVar.a.setVisibility(8);
                }
            } else if (mark instanceof DownloadMark) {
                aVar.d(false);
                operateTime2 = mark.getOperateTime();
                if (lastUpdateChapter == null) {
                }
                aVar.a(operateTime2, z);
                aVar.c(false);
                downloadTask = ((DownloadMark) mark).getDownloadTask();
                aVar.d(0);
                if (downloadTask != null) {
                    l = com.qq.reader.readengine.model.a.l(downloadTask.getFullName());
                    aVar.a(true, downloadTask.getProgress(), downloadTask.getIsOnlyDownLoadIcon());
                    aVar.a(downloadTask.getState(), downloadTask.getIsOnlyDownLoadIcon(), l);
                } else {
                    aVar.a(true, 0, true);
                    aVar.a(TaskStateEnum.Paused, true, false);
                }
                view.setBackgroundResource(R.drawable.list_item_trans_bg);
            }
            if (str == null) {
            }
            if (z2) {
                aVar.c((int) R.color.text_color_c301);
            } else {
                aVar.c((int) R.color.text_color_c103);
            }
            aVar.b(z2);
            aVar.b();
            aVar.c((int) R.color.text_color_c401);
            str2 = this.a.getResources().getString(R.string.price_info_limitfree);
            obj = ao.i(mark.getLimitFreeEndTime());
            if (TextUtils.isEmpty(obj)) {
                str2 = str2 + "，" + obj;
            }
            aVar.e(str2);
            i.a("event_F313", null, this.a);
            if (com.qq.reader.module.comic.e.e.a(this.a, String.valueOf(mark.getBookId()))) {
                i.a("event_F292", null, this.a);
            }
            str2 = mark.getBookName();
            com.qq.reader.common.imageloader.c.a(this.a).a(mark.getImageURI(), aVar.b, com.qq.reader.common.imageloader.a.a().j(), /* anonymous class already generated */);
        }
        return view;
    }

    public Mark[] a() {
        Mark[] markArr = new Mark[3];
        Iterator it = b().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            Mark mark = (Mark) it.next();
            if (mark.getBookId() <= 0) {
                markArr[i] = mark;
                i2 = i + 1;
            } else {
                i2 = i;
            }
            if (i2 > 2) {
                break;
            }
            i = i2;
        }
        return markArr;
    }
}
