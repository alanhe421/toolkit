package com.qq.reader.cservice.onlineread;

import com.qq.reader.common.protocol.ReadOnline.ReadOnlineFile;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import java.util.List;

/* compiled from: OnlineListener */
public interface c {
    void a();

    void a(OnlineTag onlineTag);

    void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult);

    void a(OnlineTag onlineTag, ReadOnlineResult readOnlineResult, OnlineChapterDownloadTask onlineChapterDownloadTask);

    void a(OnlineTag onlineTag, OnlineChapterDownloadTask onlineChapterDownloadTask);

    void a(List<ReadOnlineFile> list);

    void b(OnlineTag onlineTag, ReadOnlineResult readOnlineResult);
}
