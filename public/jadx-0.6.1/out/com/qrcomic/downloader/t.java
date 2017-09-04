package com.qrcomic.downloader;

/* compiled from: QRComicSectionStatus */
public class t {
    public String a;
    public int b = 0;
    public long c = 0;
    public long d = 0;
    public long e = 0;
    public long f = 0;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sectionId=" + this.a);
        stringBuilder.append("&sectionStatus=" + this.b);
        stringBuilder.append("&downloadSize=" + this.c);
        stringBuilder.append("&size=" + this.d);
        stringBuilder.append("&speed=" + this.e);
        stringBuilder.append("&addTime=" + this.f);
        return stringBuilder.toString();
    }
}
