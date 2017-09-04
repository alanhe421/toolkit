package com.qq.reader.liveshow.utils;

import com.dynamicload.Lib.DLConstants;

public class LogConstants {
    public static String a = DLConstants.DEPENDENCY_PACKAGE_DIV;
    public static String b = "clogs.host.createRoom";
    public static String c = "clogs.viewer.enterRoom";
    public static String d = "clogs.viewer.quitRoom";
    public static String e = "clogs.viewer.upShow";
    public static String f = "clogs.viewer.unShow";
    public static String g = "clogs.host.quitRoom";
    public static String h = "clogs.host.kick";

    public enum STATUS {
        SUCCEED,
        FAILED
    }
}
