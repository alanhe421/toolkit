package com.qq.reader.tinker;

import android.app.ActivityManager;
import android.content.Context;
import com.tencent.android.tpush.common.Constants;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.util.Properties;

/* compiled from: SamplePatchListener */
public class f extends DefaultPatchListener {
    private final int a;

    public f(Context context) {
        super(context);
        this.a = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getMemoryClass();
        TinkerLog.i("Tinker.SamplePatchListener", "application maxMemory:" + this.a, new Object[0]);
    }

    public int patchCheck(String str) {
        boolean z = false;
        File file = new File(str);
        TinkerLog.i("Tinker.SamplePatchListener", "receive a patch file: %s, file size:%d", str, Long.valueOf(SharePatchFileUtil.getFileOrDirectorySize(file)));
        int patchCheck = super.patchCheck(str);
        if (patchCheck == 0) {
            patchCheck = m.a(62914560, this.a);
        }
        if (patchCheck == 0) {
            String md5 = SharePatchFileUtil.getMD5(file);
            if (this.context.getSharedPreferences(ShareConstants.TINKER_SHARE_PREFERENCE_CONFIG, 4).getInt(md5, 0) >= 3) {
                patchCheck = -9;
            } else {
                Tinker with = Tinker.with(this.context);
                if (with.isTinkerLoaded()) {
                    TinkerLoadResult tinkerLoadResultIfPresent = with.getTinkerLoadResultIfPresent();
                    if (tinkerLoadResultIfPresent != null && md5.equals(tinkerLoadResultIfPresent.currentVersion)) {
                        patchCheck = -8;
                    }
                }
            }
            if (patchCheck == 0) {
                patchCheck = l.a(this.context).a(md5) ? 0 : -10;
            }
        }
        if (patchCheck == 0) {
            Properties fastGetPatchPackageMeta = ShareTinkerInternals.fastGetPatchPackageMeta(file);
            if (fastGetPatchPackageMeta == null) {
                patchCheck = -11;
            } else {
                String property = fastGetPatchPackageMeta.getProperty(com.tencent.connect.common.Constants.PARAM_PLATFORM);
                TinkerLog.i("Tinker.SamplePatchListener", "get platform:" + property, new Object[0]);
                if (property == null || !property.equals(a.e)) {
                    patchCheck = -11;
                }
            }
        }
        if (patchCheck == 0) {
            z = true;
        }
        h.a(z);
        return patchCheck;
    }
}
