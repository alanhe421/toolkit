package com.tencent.tinker.loader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class TinkerLoader extends AbstractTinkerLoader {
    private static final String TAG = "Tinker.TinkerLoader";
    private SharePatchInfo patchInfo;

    public Intent tryLoad(TinkerApplication tinkerApplication, int i, boolean z) {
        Intent intent = new Intent();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        tryLoadPatchFilesInternal(tinkerApplication, i, z, intent);
        ShareIntentUtil.setIntentPatchCostTime(intent, SystemClock.elapsedRealtime() - elapsedRealtime);
        return intent;
    }

    private void tryLoadPatchFilesInternal(TinkerApplication tinkerApplication, int i, boolean z, Intent intent) {
        if (ShareTinkerInternals.isTinkerEnabled(i)) {
            File patchDirectory = SharePatchFileUtil.getPatchDirectory(tinkerApplication);
            if (patchDirectory == null) {
                Log.w(TAG, "tryLoadPatchFiles:getPatchDirectory == null");
                ShareIntentUtil.setIntentReturnCode(intent, -2);
                return;
            }
            String absolutePath = patchDirectory.getAbsolutePath();
            if (patchDirectory.exists()) {
                File patchInfoFile = SharePatchFileUtil.getPatchInfoFile(absolutePath);
                if (patchInfoFile.exists()) {
                    File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(absolutePath);
                    this.patchInfo = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);
                    if (this.patchInfo == null) {
                        ShareIntentUtil.setIntentReturnCode(intent, -4);
                        return;
                    }
                    String str = this.patchInfo.oldVersion;
                    String str2 = this.patchInfo.newVersion;
                    if (str == null || str2 == null) {
                        Log.w(TAG, "tryLoadPatchFiles:onPatchInfoCorrupted");
                        ShareIntentUtil.setIntentReturnCode(intent, -4);
                        return;
                    }
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_OLD_VERSION, str);
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_NEW_VERSION, str2);
                    boolean isInMainProcess = ShareTinkerInternals.isInMainProcess(tinkerApplication);
                    Object obj = !str.equals(str2) ? 1 : null;
                    if (obj == null || !isInMainProcess) {
                        str2 = str;
                    }
                    if (ShareTinkerInternals.isNullOrNil(str2)) {
                        Log.w(TAG, "tryLoadPatchFiles:version is blank, wait main process to restart");
                        ShareIntentUtil.setIntentReturnCode(intent, -5);
                        return;
                    }
                    str = SharePatchFileUtil.getPatchVersionDirectory(str2);
                    if (str == null) {
                        Log.w(TAG, "tryLoadPatchFiles:patchName is null");
                        ShareIntentUtil.setIntentReturnCode(intent, -6);
                        return;
                    }
                    absolutePath = absolutePath + "/" + str;
                    File file = new File(absolutePath);
                    if (file.exists()) {
                        File file2 = new File(file.getAbsolutePath(), SharePatchFileUtil.getPatchVersionFile(str2));
                        if (SharePatchFileUtil.isLegalFile(file2)) {
                            ShareSecurityCheck shareSecurityCheck = new ShareSecurityCheck(tinkerApplication);
                            int checkTinkerPackage = ShareTinkerInternals.checkTinkerPackage(tinkerApplication, i, file2, shareSecurityCheck);
                            if (checkTinkerPackage != 0) {
                                Log.w(TAG, "tryLoadPatchFiles:checkTinkerPackage");
                                intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_PATCH_CHECK, checkTinkerPackage);
                                ShareIntentUtil.setIntentReturnCode(intent, -8);
                                return;
                            }
                            intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_CONFIG, shareSecurityCheck.getPackagePropertiesIfPresent());
                            boolean isTinkerEnabledForDex = ShareTinkerInternals.isTinkerEnabledForDex(i);
                            if (isTinkerEnabledForDex && !TinkerDexLoader.checkComplete(absolutePath, shareSecurityCheck, intent)) {
                                Log.w(TAG, "tryLoadPatchFiles:dex check fail");
                                return;
                            } else if (!ShareTinkerInternals.isTinkerEnabledForNativeLib(i) || TinkerSoLoader.checkComplete(absolutePath, shareSecurityCheck, intent)) {
                                boolean isTinkerEnabledForResource = ShareTinkerInternals.isTinkerEnabledForResource(i);
                                Log.w(TAG, "tryLoadPatchFiles:isEnabledForResource:" + isTinkerEnabledForResource);
                                if (!isTinkerEnabledForResource || TinkerResourceLoader.checkComplete(tinkerApplication, absolutePath, shareSecurityCheck, intent)) {
                                    boolean z2 = ShareTinkerInternals.isVmArt() && ShareTinkerInternals.isSystemOTA(this.patchInfo.fingerPrint);
                                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_SYSTEM_OTA, z2);
                                    if (z2 || (isInMainProcess && obj != null)) {
                                        this.patchInfo.oldVersion = str2;
                                        if (!SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, this.patchInfo, patchInfoLockFile)) {
                                            ShareIntentUtil.setIntentReturnCode(intent, -18);
                                            Log.w(TAG, "tryLoadPatchFiles:onReWritePatchInfoCorrupted");
                                            return;
                                        }
                                    }
                                    if (!checkSafeModeCount(tinkerApplication)) {
                                        intent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, new TinkerRuntimeException("checkSafeModeCount fail"));
                                        ShareIntentUtil.setIntentReturnCode(intent, -24);
                                        Log.w(TAG, "tryLoadPatchFiles:checkSafeModeCount fail");
                                        return;
                                    } else if (isTinkerEnabledForDex && !TinkerDexLoader.loadTinkerJars(tinkerApplication, z, absolutePath, intent, z2)) {
                                        Log.w(TAG, "tryLoadPatchFiles:onPatchLoadDexesFail");
                                        return;
                                    } else if (!isTinkerEnabledForResource || TinkerResourceLoader.loadTinkerResources(tinkerApplication, z, absolutePath, intent)) {
                                        ShareIntentUtil.setIntentReturnCode(intent, 0);
                                        Log.i(TAG, "tryLoadPatchFiles: load end, ok!");
                                        return;
                                    } else {
                                        Log.w(TAG, "tryLoadPatchFiles:onPatchLoadResourcesFail");
                                        return;
                                    }
                                }
                                Log.w(TAG, "tryLoadPatchFiles:resource check fail");
                                return;
                            } else {
                                Log.w(TAG, "tryLoadPatchFiles:native lib check fail");
                                return;
                            }
                        }
                        Log.w(TAG, "tryLoadPatchFiles:onPatchVersionFileNotFound");
                        ShareIntentUtil.setIntentReturnCode(intent, -7);
                        return;
                    }
                    Log.w(TAG, "tryLoadPatchFiles:onPatchVersionDirectoryNotFound");
                    ShareIntentUtil.setIntentReturnCode(intent, -6);
                    return;
                }
                Log.w(TAG, "tryLoadPatchFiles:patch info not exist:" + patchInfoFile.getAbsolutePath());
                ShareIntentUtil.setIntentReturnCode(intent, -3);
                return;
            }
            Log.w(TAG, "tryLoadPatchFiles:patch dir not exist:" + absolutePath);
            ShareIntentUtil.setIntentReturnCode(intent, -2);
            return;
        }
        ShareIntentUtil.setIntentReturnCode(intent, -1);
    }

    private boolean checkSafeModeCount(TinkerApplication tinkerApplication) {
        String str = ShareConstants.TINKER_OWN_PREFERENCE_CONFIG + ShareTinkerInternals.getProcessName(tinkerApplication);
        SharedPreferences sharedPreferences = tinkerApplication.getSharedPreferences(str, 0);
        int i = sharedPreferences.getInt(ShareConstants.TINKER_SAFE_MODE_COUNT, 0) + 1;
        Log.w(TAG, "tinker safe mode preferName:" + str + " count:" + i);
        if (i >= 3) {
            sharedPreferences.edit().putInt(ShareConstants.TINKER_SAFE_MODE_COUNT, 0).commit();
            return false;
        }
        tinkerApplication.setUseSafeMode(true);
        sharedPreferences.edit().putInt(ShareConstants.TINKER_SAFE_MODE_COUNT, i).commit();
        return true;
    }
}
