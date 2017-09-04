package com.qq.reader.common.utils;

import android.content.ComponentName;
import android.text.TextUtils;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.Lib.DLPluginPackage;

/* compiled from: PluginJumpUtils */
public class w {
    public static ComponentName a(String str) {
        if (TextUtils.isEmpty(str) || !"pdf".equals(str)) {
            return null;
        }
        return new ComponentName("qqreader.plugin", "qqreader.testpluginapplication.MuPDFActivity");
    }

    public static DLPluginPackage b(String str) {
        if (TextUtils.isEmpty(str) || !"1".equals(str)) {
            return null;
        }
        return DLPluginManager.getInstance().getPackage("qqreader.plugin");
    }
}
