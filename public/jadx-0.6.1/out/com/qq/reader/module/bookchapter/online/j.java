package com.qq.reader.module.bookchapter.online;

import java.util.ArrayList;
import java.util.List;

/* compiled from: OnlinePackage */
public class j {
    private List<OnlineChapter> a = new ArrayList();

    public void a(OnlineChapter onlineChapter) {
        this.a.add(onlineChapter);
    }

    public int a() {
        return this.a.size();
    }

    public String b() {
        if (this.a == null || this.a.size() == 0) {
            return "";
        }
        if (this.a.size() == 1) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("第");
            stringBuffer.append(((OnlineChapter) this.a.get(0)).getChapterId());
            stringBuffer.append(((OnlineChapter) this.a.get(0)).getChapterTagName());
            return stringBuffer.toString();
        }
        stringBuffer = new StringBuffer();
        stringBuffer.append("第");
        stringBuffer.append(((OnlineChapter) this.a.get(0)).getChapterId());
        stringBuffer.append(((OnlineChapter) this.a.get(0)).getChapterTagName());
        stringBuffer.append("-第");
        stringBuffer.append(((OnlineChapter) this.a.get(this.a.size() - 1)).getChapterId());
        stringBuffer.append(((OnlineChapter) this.a.get(0)).getChapterTagName());
        return stringBuffer.toString();
    }

    public OnlineChapter a(int i) {
        if (this.a != null && this.a.size() > 0 && i < this.a.size()) {
            return (OnlineChapter) this.a.get(i);
        }
        return null;
    }
}
