package com.qq.reader.plugin;

import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PluginLauncher */
public class n {
    public static final List<String> a = new ArrayList();
    public static final List<String> b = new ArrayList();

    static {
        a.add(Constants.VIA_REPORT_TYPE_SET_AVATAR);
        b.add(Constants.VIA_REPORT_TYPE_MAKE_FRIEND);
    }

    public static boolean a(String str) {
        return b.contains(str);
    }
}
