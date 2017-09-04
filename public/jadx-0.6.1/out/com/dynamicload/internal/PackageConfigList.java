package com.dynamicload.internal;

import java.io.Serializable;
import java.util.HashMap;

public class PackageConfigList implements Serializable {
    private static final long serialVersionUID = 8118699213660176470L;
    public HashMap<String, PackageConfig> configPlugins = new HashMap();
    public boolean forceUpdate = false;
    public int version;
}
