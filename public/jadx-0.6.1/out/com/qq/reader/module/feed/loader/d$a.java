package com.qq.reader.module.feed.loader;

import java.util.Comparator;

/* compiled from: FeedDataStyleBLoader */
public class d$a implements Comparator<String> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((String) obj, (String) obj2);
    }

    public int a(String str, String str2) {
        int parseInt = Integer.parseInt(str);
        int parseInt2 = Integer.parseInt(str2);
        if (parseInt > parseInt2) {
            return -1;
        }
        if (parseInt < parseInt2) {
            return 1;
        }
        return 0;
    }
}
