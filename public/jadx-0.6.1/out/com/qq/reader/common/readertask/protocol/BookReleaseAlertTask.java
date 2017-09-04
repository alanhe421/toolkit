package com.qq.reader.common.readertask.protocol;

import android.text.TextUtils;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import java.net.URLEncoder;

public class BookReleaseAlertTask extends ReaderProtocolJSONTask {
    public BookReleaseAlertTask(String str, String str2, c cVar) {
        super(cVar);
        StringBuilder stringBuilder = new StringBuilder(e.cr);
        stringBuilder.append("addbooksubscribe?").append("bookname=").append(URLEncoder.encode(str));
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append("&authorname=").append(URLEncoder.encode(str2));
        }
        this.mUrl = stringBuilder.toString();
    }
}
