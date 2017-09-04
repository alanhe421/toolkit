package com.dynamicload.internal;

import java.io.Serializable;

public class PackageConfig implements Serializable {
    private static final long serialVersionUID = 5056113439010761614L;
    public String apkName;
    public long apkSize;
    public int apkVersion;
    public String packageName;
    public boolean preLoad;
}
