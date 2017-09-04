package com.tencent.tinker.loader;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.TinkerParallelDexOptimizer.ResultCallback;
import com.tencent.tinker.loader.shareutil.ShareDexDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class TinkerDexLoader {
    private static final String DEX_MEAT_FILE = "assets/dex_meta.txt";
    private static final String DEX_OPTIMIZE_PATH = "odex";
    private static final String DEX_PATH = "dex";
    private static final String TAG = "Tinker.TinkerDexLoader";
    private static final ArrayList<ShareDexDiffPatchInfo> dexList = new ArrayList();
    private static boolean parallelOTAResult;
    private static Throwable parallelOTAThrowable;

    private TinkerDexLoader() {
    }

    @TargetApi(14)
    public static boolean loadTinkerJars(Application application, boolean z, String str, Intent intent, boolean z2) {
        if (dexList.isEmpty()) {
            Log.w(TAG, "there is no dex to load");
            return true;
        }
        PathClassLoader pathClassLoader = (PathClassLoader) TinkerDexLoader.class.getClassLoader();
        if (pathClassLoader != null) {
            Log.i(TAG, "classloader: " + pathClassLoader.toString());
            String str2 = str + "/" + "dex" + "/";
            File file = new File(str + "/" + "odex");
            Collection arrayList = new ArrayList();
            boolean isVmArt = ShareTinkerInternals.isVmArt();
            Iterator it = dexList.iterator();
            while (it.hasNext()) {
                ShareDexDiffPatchInfo shareDexDiffPatchInfo = (ShareDexDiffPatchInfo) it.next();
                if (!isJustArtSupportDex(shareDexDiffPatchInfo)) {
                    File file2 = new File(str2 + shareDexDiffPatchInfo.realName);
                    if (z) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (SharePatchFileUtil.verifyDexFileMd5(file2, isVmArt ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm)) {
                            Log.i(TAG, "verify dex file:" + file2.getPath() + " md5, use time: " + (System.currentTimeMillis() - currentTimeMillis));
                        } else {
                            ShareIntentUtil.setIntentReturnCode(intent, -13);
                            intent.putExtra(ShareIntentUtil.INTENT_PATCH_MISMATCH_DEX_PATH, file2.getAbsolutePath());
                            return false;
                        }
                    }
                    arrayList.add(file2);
                }
            }
            if (z2) {
                parallelOTAResult = true;
                parallelOTAThrowable = null;
                Log.w(TAG, "systemOTA, try parallel oat dexes!!!!!");
                TinkerParallelDexOptimizer.optimizeAll(arrayList, file, new ResultCallback() {
                    long start;

                    public void onStart(File file, File file2) {
                        this.start = System.currentTimeMillis();
                        Log.i(TinkerDexLoader.TAG, "start to optimize dex:" + file.getPath());
                    }

                    public void onSuccess(File file, File file2, File file3) {
                        Log.i(TinkerDexLoader.TAG, "success to optimize dex " + file.getPath() + "use time " + (System.currentTimeMillis() - this.start));
                    }

                    public void onFailed(File file, File file2, Throwable th) {
                        TinkerDexLoader.parallelOTAResult = false;
                        TinkerDexLoader.parallelOTAThrowable = th;
                        Log.i(TinkerDexLoader.TAG, "fail to optimize dex " + file.getPath() + "use time " + (System.currentTimeMillis() - this.start));
                    }
                });
                if (!parallelOTAResult) {
                    Log.e(TAG, "parallel oat dexes failed");
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, parallelOTAThrowable);
                    ShareIntentUtil.setIntentReturnCode(intent, -15);
                    return false;
                }
            }
            try {
                SystemClassLoaderAdder.installDexes(application, pathClassLoader, file, arrayList);
                return true;
            } catch (Serializable th) {
                Log.e(TAG, "install dexes failed");
                intent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, th);
                ShareIntentUtil.setIntentReturnCode(intent, -14);
                return false;
            }
        }
        Log.e(TAG, "classloader is null");
        ShareIntentUtil.setIntentReturnCode(intent, -12);
        return false;
    }

    public static boolean checkComplete(String str, ShareSecurityCheck shareSecurityCheck, Intent intent) {
        String str2 = (String) shareSecurityCheck.getMetaContentMap().get("assets/dex_meta.txt");
        if (str2 == null) {
            return true;
        }
        dexList.clear();
        ShareDexDiffPatchInfo.parseDexDiffPatchInfo(str2, dexList);
        if (dexList.isEmpty()) {
            return true;
        }
        Serializable hashMap = new HashMap();
        Iterator it = dexList.iterator();
        while (it.hasNext()) {
            ShareDexDiffPatchInfo shareDexDiffPatchInfo = (ShareDexDiffPatchInfo) it.next();
            if (!isJustArtSupportDex(shareDexDiffPatchInfo)) {
                if (ShareDexDiffPatchInfo.checkDexDiffPatchInfo(shareDexDiffPatchInfo)) {
                    hashMap.put(shareDexDiffPatchInfo.realName, shareDexDiffPatchInfo.destMd5InDvm);
                } else {
                    intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_PATCH_CHECK, -3);
                    ShareIntentUtil.setIntentReturnCode(intent, -8);
                    return false;
                }
            }
        }
        String str3 = str + "/" + "dex" + "/";
        File file = new File(str3);
        if (file.exists() && file.isDirectory()) {
            File file2 = new File(str + "/" + "odex" + "/");
            for (String str22 : hashMap.keySet()) {
                File file3 = new File(str3 + str22);
                if (SharePatchFileUtil.isLegalFile(file3)) {
                    file = new File(SharePatchFileUtil.optimizedPathFor(file3, file2));
                    if (!SharePatchFileUtil.isLegalFile(file)) {
                        intent.putExtra(ShareIntentUtil.INTENT_PATCH_MISSING_DEX_PATH, file.getAbsolutePath());
                        ShareIntentUtil.setIntentReturnCode(intent, -11);
                        return false;
                    }
                }
                intent.putExtra(ShareIntentUtil.INTENT_PATCH_MISSING_DEX_PATH, file3.getAbsolutePath());
                ShareIntentUtil.setIntentReturnCode(intent, -10);
                return false;
            }
            intent.putExtra(ShareIntentUtil.INTENT_PATCH_DEXES_PATH, hashMap);
            return true;
        }
        ShareIntentUtil.setIntentReturnCode(intent, -9);
        return false;
    }

    private static boolean isJustArtSupportDex(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        if (!ShareTinkerInternals.isVmArt() && shareDexDiffPatchInfo.destMd5InDvm.equals("0")) {
            return true;
        }
        return false;
    }
}
