package com.tencent.tinker.lib.tinker;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.util.HashMap;

public class TinkerLoadResult {
    private static final String TAG = "Tinker.TinkerLoadResult";
    public long costTime;
    public String currentVersion;
    public File dexDirectory;
    public HashMap<String, String> dexes;
    public File libraryDirectory;
    public HashMap<String, String> libs;
    public int loadCode;
    public HashMap<String, String> packageConfig;
    public SharePatchInfo patchInfo;
    public File patchVersionDirectory;
    public File patchVersionFile;
    public File resourceDirectory;
    public File resourceFile;
    public boolean systemOTA;
    public boolean versionChanged;

    public boolean parseTinkerResult(Context context, Intent intent) {
        String patchVersionDirectory;
        Tinker with = Tinker.with(context);
        this.loadCode = ShareIntentUtil.getIntentReturnCode(intent);
        this.costTime = ShareIntentUtil.getIntentPatchCostTime(intent);
        this.systemOTA = ShareIntentUtil.getBooleanExtra(intent, ShareIntentUtil.INTENT_PATCH_SYSTEM_OTA, false);
        TinkerLog.i(TAG, "parseTinkerResult loadCode:%d, systemOTA:%b", Integer.valueOf(this.loadCode), Boolean.valueOf(this.systemOTA));
        String stringExtra = ShareIntentUtil.getStringExtra(intent, ShareIntentUtil.INTENT_PATCH_OLD_VERSION);
        String stringExtra2 = ShareIntentUtil.getStringExtra(intent, ShareIntentUtil.INTENT_PATCH_NEW_VERSION);
        File patchDirectory = with.getPatchDirectory();
        File patchInfoFile = with.getPatchInfoFile();
        boolean isMainProcess = with.isMainProcess();
        if (!(stringExtra == null || stringExtra2 == null)) {
            if (isMainProcess) {
                this.currentVersion = stringExtra2;
            } else {
                this.currentVersion = stringExtra;
            }
            TinkerLog.i(TAG, "parseTinkerResult oldVersion:%s, newVersion:%s, current:%s", stringExtra, stringExtra2, this.currentVersion);
            patchVersionDirectory = SharePatchFileUtil.getPatchVersionDirectory(this.currentVersion);
            if (!ShareTinkerInternals.isNullOrNil(patchVersionDirectory)) {
                this.patchVersionDirectory = new File(patchDirectory.getAbsolutePath() + "/" + patchVersionDirectory);
                this.patchVersionFile = new File(this.patchVersionDirectory.getAbsolutePath(), SharePatchFileUtil.getPatchVersionFile(this.currentVersion));
                this.dexDirectory = new File(this.patchVersionDirectory, ShareConstants.DEX_PATH);
                this.libraryDirectory = new File(this.patchVersionDirectory, ShareConstants.SO_PATH);
                this.resourceDirectory = new File(this.patchVersionDirectory, "res");
                this.resourceFile = new File(this.resourceDirectory, ShareConstants.RES_NAME);
            }
            this.patchInfo = new SharePatchInfo(stringExtra, stringExtra2, Build.FINGERPRINT);
            this.versionChanged = !stringExtra.equals(stringExtra2);
        }
        Throwable intentPatchException = ShareIntentUtil.getIntentPatchException(intent);
        if (intentPatchException != null) {
            TinkerLog.i(TAG, "Tinker load have exception loadCode:%d", Integer.valueOf(this.loadCode));
            int i = -1;
            switch (this.loadCode) {
                case -24:
                    i = -4;
                    break;
                case -22:
                    i = -3;
                    break;
                case ShareConstants.ERROR_LOAD_PATCH_UNKNOWN_EXCEPTION /*-19*/:
                    i = -1;
                    break;
                case -15:
                    i = -5;
                    break;
                case -14:
                    i = -2;
                    break;
            }
            with.getLoadReporter().onLoadException(intentPatchException, i);
            return false;
        }
        switch (this.loadCode) {
            case -10000:
                TinkerLog.e(TAG, "can't get the right intent return code", new Object[0]);
                throw new TinkerRuntimeException("can't get the right intent return code");
            case -23:
                if (this.resourceFile != null) {
                    TinkerLog.e(TAG, "patch resource file md5 is mismatch: %s", this.resourceFile.getAbsolutePath());
                    with.getLoadReporter().onLoadFileMd5Mismatch(this.resourceFile, 6);
                    break;
                }
                TinkerLog.e(TAG, "resource file md5 mismatch, but patch resource file not found!", new Object[0]);
                throw new TinkerRuntimeException("resource file md5 mismatch, but patch resource file not found!");
            case -21:
                if (this.patchVersionDirectory != null) {
                    TinkerLog.e(TAG, "patch resource file not found:%s", this.resourceFile.getAbsolutePath());
                    with.getLoadReporter().onLoadFileNotFound(this.resourceFile, 6, false);
                    break;
                }
                TinkerLog.e(TAG, "patch resource file not found, warning why the path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch resource file not found, warning why the path is null!!!!");
            case -20:
                if (this.patchVersionDirectory != null) {
                    TinkerLog.e(TAG, "patch resource file directory not found:%s", this.resourceDirectory.getAbsolutePath());
                    with.getLoadReporter().onLoadFileNotFound(this.resourceDirectory, 6, true);
                    break;
                }
                TinkerLog.e(TAG, "patch resource file directory not found, warning why the path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch resource file directory not found, warning why the path is null!!!!");
            case ShareConstants.ERROR_LOAD_PATCH_REWRITE_PATCH_INFO_FAIL /*-18*/:
                TinkerLog.i(TAG, "rewrite patch info file corrupted", new Object[0]);
                with.getLoadReporter().onLoadPatchInfoCorrupted(stringExtra, stringExtra2, patchInfoFile);
                break;
            case ShareConstants.ERROR_LOAD_PATCH_VERSION_LIB_FILE_NOT_EXIST /*-17*/:
                patchVersionDirectory = ShareIntentUtil.getStringExtra(intent, ShareIntentUtil.INTENT_PATCH_MISSING_LIB_PATH);
                if (patchVersionDirectory != null) {
                    TinkerLog.e(TAG, "patch lib file not found:%s", patchVersionDirectory);
                    with.getLoadReporter().onLoadFileNotFound(new File(patchVersionDirectory), 5, false);
                    break;
                }
                TinkerLog.e(TAG, "patch lib file not found, but path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch lib file not found, but path is null!!!!");
            case -16:
                if (this.patchVersionDirectory != null) {
                    TinkerLog.e(TAG, "patch lib file directory not found:%s", this.libraryDirectory.getAbsolutePath());
                    with.getLoadReporter().onLoadFileNotFound(this.libraryDirectory, 5, true);
                    break;
                }
                TinkerLog.e(TAG, "patch lib file directory not found, warning why the path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch lib file directory not found, warning why the path is null!!!!");
            case -13:
                patchVersionDirectory = ShareIntentUtil.getStringExtra(intent, ShareIntentUtil.INTENT_PATCH_MISMATCH_DEX_PATH);
                if (patchVersionDirectory != null) {
                    TinkerLog.e(TAG, "patch dex file md5 is mismatch: %s", patchVersionDirectory);
                    with.getLoadReporter().onLoadFileMd5Mismatch(new File(patchVersionDirectory), 3);
                    break;
                }
                TinkerLog.e(TAG, "patch dex file md5 is mismatch, but path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch dex file md5 is mismatch, but path is null!!!!");
            case -12:
                TinkerLog.e(TAG, "patch dex load fail, classloader is null", new Object[0]);
                break;
            case -11:
                patchVersionDirectory = ShareIntentUtil.getStringExtra(intent, ShareIntentUtil.INTENT_PATCH_MISSING_DEX_PATH);
                if (patchVersionDirectory != null) {
                    TinkerLog.e(TAG, "patch dex opt file not found:%s", patchVersionDirectory);
                    with.getLoadReporter().onLoadFileNotFound(new File(patchVersionDirectory), 4, false);
                    break;
                }
                TinkerLog.e(TAG, "patch dex opt file not found, but path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch dex opt file not found, but path is null!!!!");
            case -10:
                patchVersionDirectory = ShareIntentUtil.getStringExtra(intent, ShareIntentUtil.INTENT_PATCH_MISSING_DEX_PATH);
                if (patchVersionDirectory != null) {
                    TinkerLog.e(TAG, "patch dex file not found:%s", patchVersionDirectory);
                    with.getLoadReporter().onLoadFileNotFound(new File(patchVersionDirectory), 3, false);
                    break;
                }
                TinkerLog.e(TAG, "patch dex file not found, but path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch dex file not found, but path is null!!!!");
            case -9:
                if (this.dexDirectory != null) {
                    TinkerLog.e(TAG, "patch dex file directory not found:%s", this.dexDirectory.getAbsolutePath());
                    with.getLoadReporter().onLoadFileNotFound(this.dexDirectory, 3, true);
                    break;
                }
                TinkerLog.e(TAG, "patch dex file directory not found, warning why the path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch dex file directory not found, warning why the path is null!!!!");
            case -8:
                TinkerLog.i(TAG, "patch package check fail", new Object[0]);
                if (this.patchVersionFile != null) {
                    with.getLoadReporter().onLoadPackageCheckFail(this.patchVersionFile, intent.getIntExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_PATCH_CHECK, -10000));
                    break;
                }
                throw new TinkerRuntimeException("error patch package check fail , but file is null");
            case -7:
                TinkerLog.e(TAG, "patch version file not found, current version:%s", this.currentVersion);
                if (this.patchVersionFile != null) {
                    with.getLoadReporter().onLoadFileNotFound(this.patchVersionFile, 1, false);
                    break;
                }
                throw new TinkerRuntimeException("error load patch version file not exist, but file is null");
            case -6:
                TinkerLog.e(TAG, "patch version directory not found, current version:%s", this.currentVersion);
                with.getLoadReporter().onLoadFileNotFound(this.patchVersionDirectory, 1, true);
                break;
            case -5:
                TinkerLog.e(TAG, "path info blank, wait main process to restart", new Object[0]);
                break;
            case -4:
                TinkerLog.e(TAG, "path info corrupted", new Object[0]);
                with.getLoadReporter().onLoadPatchInfoCorrupted(stringExtra, stringExtra2, patchInfoFile);
                break;
            case -3:
            case -2:
                TinkerLog.w(TAG, "can't find patch file, is ok, just return", new Object[0]);
                break;
            case -1:
                TinkerLog.w(TAG, "tinker is disable, just return", new Object[0]);
                break;
            case 0:
                TinkerLog.i(TAG, "oh yeah, tinker load all success", new Object[0]);
                with.setTinkerLoaded(true);
                this.dexes = ShareIntentUtil.getIntentPatchDexPaths(intent);
                this.libs = ShareIntentUtil.getIntentPatchLibsPaths(intent);
                this.packageConfig = ShareIntentUtil.getIntentPackageConfig(intent);
                if (isMainProcess && this.versionChanged) {
                    with.getLoadReporter().onLoadPatchVersionChanged(stringExtra, stringExtra2, patchDirectory, this.patchVersionDirectory.getName());
                }
                return true;
        }
        return false;
    }

    public String getPackageConfigByName(String str) {
        if (this.packageConfig != null) {
            return (String) this.packageConfig.get(str);
        }
        return null;
    }
}
