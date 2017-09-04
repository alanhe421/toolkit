package com.qrcomic.a;

import com.qq.reader.common.readertask.protocol.H5GameGrantTicketTask;
import com.qrcomic.downloader.a.c;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.f.d;
import com.qrcomic.util.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: QRComicURLUtil */
public class i {
    private static final String a = d.b("comicCdn");
    private static final String b = i.class.getSimpleName();

    public static final String a(String str, String str2, int i, int i2, int i3, int i4, int i5) {
        StringBuilder a = c.a();
        a.append(d.b("comicDetail"));
        a.append("?comicId=").append(str).append("&begin=").append(str2).append(H5GameGrantTicketTask.COMMON_COUNT).append(i).append("&direction=").append(i2).append("&pageType=").append(i3).append("&detailMode=").append(i4).append("&wd=").append(i5);
        if (g.a()) {
            g.a(b, g.d, " 漫画信息 = " + a.toString());
        }
        return a.toString();
    }

    public static String a(String str, String str2, int i) {
        StringBuilder a = c.a();
        a.append(d.b("sectionPicList"));
        a.append("?comicId=").append(str).append("&sectionId=").append(str2).append("&wd=").append(i);
        if (g.a()) {
            g.a(b, g.d, " 图片列表 = " + a.toString());
        }
        return a.toString();
    }

    public static String a(ArrayList<QRComicBuyReqInfo> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return "";
        }
        String b = d.b("comicPayRead");
        StringBuilder a = c.a();
        a.append(b);
        QRComicBuyReqInfo qRComicBuyReqInfo = (QRComicBuyReqInfo) arrayList.get(0);
        a.append("?comicId=").append(qRComicBuyReqInfo.a);
        List list = qRComicBuyReqInfo.b;
        if (!(list == null || list.size() == 0)) {
            a.append("&sectionId=");
            for (int i = 0; i < qRComicBuyReqInfo.b.size(); i++) {
                a.append((String) qRComicBuyReqInfo.b.get(i)).append(",");
            }
            a.deleteCharAt(a.length() - 1);
        }
        if (g.a()) {
            g.a(b, g.d, " 付费信息 = " + a.toString());
        }
        return a.toString();
    }

    public static String a(String str, List<String> list, int i, int i2, int i3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d.b("sectionBuy"));
        stringBuilder.append("?comicId=").append(str);
        if (list != null && list.size() > 0) {
            stringBuilder.append("&sectionIds=");
            for (int i4 = 0; i4 < list.size(); i4++) {
                stringBuilder.append((String) list.get(i4));
                if (i4 != list.size() - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        if (i2 > 0) {
            stringBuilder.append("&buyType=").append(i2);
        }
        if (i3 > 0) {
            stringBuilder.append("&cost=").append(i3);
        }
        if (g.a()) {
            g.a(b, g.d, " 购买链接URL = " + stringBuilder.toString());
        }
        return stringBuilder.toString();
    }

    public static String a(String str, String str2) {
        return d.b("sectionPreview") + "?" + "comicId=" + str + "&sectionId=" + str2;
    }

    public static String a(String str, String str2, int i, int i2) {
        return d.b("comicPicPublic") + "preview/" + str + "/" + str2 + ".jpg" + "?imageView2/2/w/" + i + "/h/" + i2;
    }

    public static String a(long j, long j2, long j3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d.b("comicPicPublic"));
        stringBuilder.append("cover/");
        stringBuilder.append(j);
        stringBuilder.append("/cover");
        stringBuilder.append(".jpg");
        stringBuilder.append("?imageView2/2/w/").append(j2);
        stringBuilder.append("/h/").append(j3);
        return stringBuilder.toString();
    }
}
