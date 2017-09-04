package com.qq.reader.module.question.loader;

import android.content.Context;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.storage.task.LoadNativePageDataTask;

public class AuthorSayLoadNativePageDataTask extends LoadNativePageDataTask {
    public AuthorSayLoadNativePageDataTask(Context context, b bVar) {
        super(context, bVar);
    }

    protected void tryDownloadPage() {
        ReaderTask authorSayNativeDataProtocolTask = new AuthorSayNativeDataProtocolTask();
        authorSayNativeDataProtocolTask.registerNetTaskListener(this);
        authorSayNativeDataProtocolTask.setUrl(this.mPage.g());
        this.mPageRequestStartTimeList.put(authorSayNativeDataProtocolTask.getUrl(), Long.valueOf(System.currentTimeMillis()));
        g.a().a(authorSayNativeDataProtocolTask);
    }
}
