package com.tencent.tinker.lib.listener;

import android.content.Context;
import com.tencent.tinker.lib.service.TinkerPatchService;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class DefaultPatchListener implements PatchListener {
    protected final Context context;

    public DefaultPatchListener(Context context) {
        this.context = context;
    }

    public int onPatchReceived(String str) {
        int patchCheck = patchCheck(str);
        if (patchCheck == 0) {
            TinkerPatchService.runPatchService(this.context, str);
        } else {
            Tinker.with(this.context).getLoadReporter().onLoadPatchListenerReceiveFail(new File(str), patchCheck);
        }
        return patchCheck;
    }

    protected int patchCheck(String str) {
        Tinker with = Tinker.with(this.context);
        if (!with.isTinkerEnabled() || !ShareTinkerInternals.isTinkerEnableWithSharedPreferences(this.context)) {
            return -1;
        }
        if (!SharePatchFileUtil.isLegalFile(new File(str))) {
            return -2;
        }
        if (with.isPatchProcess()) {
            return -4;
        }
        if (TinkerServiceInternals.isTinkerPatchServiceRunning(this.context)) {
            return -3;
        }
        return 0;
    }
}
