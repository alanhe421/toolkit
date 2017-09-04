package com.qrcomic.downloader;

/* compiled from: QRComicStatus */
public class v {
    public String a;
    public int b;
    public long c = 0;
    public long d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public long j = 0;
    public long k = 0;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("comicId=" + this.a);
        stringBuilder.append("&comicNewTaskTime=" + this.d);
        stringBuilder.append("&statusCountWait=" + this.e);
        stringBuilder.append("&statusCountDownload=" + this.f);
        stringBuilder.append("&statusCountPause=" + this.g);
        stringBuilder.append("&statusCountFail=" + this.h);
        stringBuilder.append("&statusCountEnd=" + this.i);
        stringBuilder.append("&downloadSum=" + this.j);
        stringBuilder.append("&comicSpeed=" + this.k);
        return stringBuilder.toString();
    }
}
