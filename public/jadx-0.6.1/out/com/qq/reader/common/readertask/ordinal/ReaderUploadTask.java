package com.qq.reader.common.readertask.ordinal;

import android.content.Context;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ab;
import java.io.File;
import java.lang.ref.WeakReference;

public class ReaderUploadTask extends ReaderProtocolTask {
    public static final String TASKNAME = "ReaderUploadTask";
    private Context mContext;
    private String mId;
    private WeakReference<a> mListenerWeakRef;
    private a mStrongRefListener;
    private String mUploadPath;

    public ReaderUploadTask(Context context, String str) {
        setPriority(3);
        this.mContext = context;
        this.mId = str;
    }

    public String getUploadPath() {
        return this.mUploadPath;
    }

    public void setListener(a aVar) {
        this.mListenerWeakRef = new WeakReference(aVar);
    }

    public void setStrongRefListener(a aVar) {
        this.mStrongRefListener = aVar;
    }

    public byte[] getRequest() {
        try {
            return ab.f(new File(this.mUploadPath));
        } catch (Exception e) {
            c.e(TASKNAME, "getRequestBytesContent : " + e.toString());
            return null;
        }
    }

    public boolean isRequestGzip() {
        return true;
    }
}
