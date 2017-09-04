package com.qq.reader.common.web.js;

import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.cservice.download.app.ReaderDownloadAppTask;

public class JSUpdate extends b {
    private static int b = 1;
    private static int c = 2;
    private static int d = 3;
    private ReaderBaseActivity a;

    public JSUpdate(ReaderBaseActivity readerBaseActivity) {
        this.a = readerBaseActivity;
    }

    public void update() {
        this.a.checkUpdate(false, false);
    }

    public void showhelp() {
        if (this.a instanceof WebBrowserForContents) {
            ((WebBrowserForContents) this.a).b();
        }
    }

    public boolean isLatestApkReady() {
        return new com.qq.reader.cservice.download.app.b().a();
    }

    public void startDownloadApk() {
        this.a.checkUpdate(true, false);
    }

    public int getDownloadStatus() {
        if (ReaderDownloadAppTask.isDownloading) {
            return b;
        }
        if (ReaderDownloadAppTask.isDownloadSuccess) {
            return c;
        }
        return d;
    }

    public void startInstall() {
        this.a.checkUpdate(true, false);
    }
}
