package com.qq.reader.common.utils.EmulatorCheck;

import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.tinker.android.dex.DexFormat;

public class JniAnti {
    public static String getCpuinfo() {
        try {
            String b = c.b("cat /proc/cpuinfo");
            if (b != null) {
                for (String str : b.replace("\n", DexFormat.MAGIC_SUFFIX).replace("\r", DexFormat.MAGIC_SUFFIX).split(DexFormat.MAGIC_SUFFIX)) {
                    if (!TextUtils.isEmpty(str) && (str.contains("Hardware") || str.contains("model name"))) {
                        String[] split = str.split(":");
                        if (split != null && split.length == 2) {
                            Object obj = split[1];
                            if (!TextUtils.isEmpty(obj)) {
                                return obj.trim();
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            c.e("Error", th.getMessage());
        }
        return "";
    }

    public static String getKernelVersion() {
        try {
            String replace = c.b("cat /proc/version").replace("\r", DexFormat.MAGIC_SUFFIX).replace("\n", DexFormat.MAGIC_SUFFIX);
            if (TextUtils.isEmpty(replace)) {
                return replace;
            }
            return replace.trim();
        } catch (Throwable th) {
            c.e("Error", th.getMessage());
            return "";
        }
    }

    public static int checkAntiFile() {
        return (((((((((((((((((((((0 + c.c("/system/bin/qemu_props")) + c.c("/system/bin/androVM-prop")) + c.c("/system/bin/microvirt-prop")) + c.c("/system/lib/libdroid4x.so")) + c.c("/system/bin/windroyed")) + c.c("/system/bin/microvirtd")) + c.c("/system/bin/nox-prop")) + c.c("/system/bin/ttVM-prop")) + c.c("/system/bin/droid4x-prop")) + c.c("/data/.bluestacks.prop")) + c.d("init.svc.vbox86-setup")) + c.d("init.svc.droid4x")) + c.d("init.svc.qemud")) + c.d("init.svc.su_kpbs_daemon")) + c.d("init.svc.noxd")) + c.d("init.svc.ttVM_x86-setup")) + c.d("init.svc.xxkmsg")) + c.d("init.svc.microvirtd")) + c.d("ro.kernel.android.qemud")) + c.d("androVM.vbox_dpi")) + c.d("androVM.vbox_graph_mode")) + c.a("ro.product.manufacturer", "Genymotion");
    }
}
