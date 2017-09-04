package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;
import com.tencent.tinker.commons.dexpatcher.DexPatchApplier;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerParallelDexOptimizer;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareDexDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import dalvik.system.DexFile;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DexDiffPatchInternal extends BasePatchInternal {
    protected static final int MAX_WAIT_COUNT = 30;
    protected static final String TAG = "Tinker.DexDiffPatchInternal";
    protected static final int WAIT_ASYN_OAT_TIME = 8000;
    private static List<File> failOptDexFile = new Vector();
    private static ArrayList<File> optFiles = new ArrayList();

    protected static boolean tryRecoverDexFiles(Tinker tinker, ShareSecurityCheck shareSecurityCheck, Context context, String str, File file) {
        if (tinker.isEnabledForDex()) {
            String str2 = (String) shareSecurityCheck.getMetaContentMap().get(ShareConstants.DEX_META_FILE);
            if (str2 == null) {
                TinkerLog.w(TAG, "patch recover, dex is not contained", new Object[0]);
                return true;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
            TinkerLog.i(TAG, "recover dex result:%b, cost:%d", Boolean.valueOf(patchDexExtractViaDexDiff(context, str, str2, file)), Long.valueOf(elapsedRealtime));
            return patchDexExtractViaDexDiff(context, str, str2, file);
        }
        TinkerLog.w(TAG, "patch recover, dex is not enabled", new Object[0]);
        return true;
    }

    protected static boolean waitDexOptFile() {
        int i = 30;
        if (optFiles.isEmpty()) {
            return true;
        }
        int size = optFiles.size() * 6;
        if (size <= 30) {
            i = size;
        }
        TinkerLog.i(TAG, "dex count: %d, final wait time: %d", Integer.valueOf(optFiles.size()), Integer.valueOf(i));
        for (size = 0; size < i; size++) {
            if (!checkAllDexOptFile(optFiles, size + 1)) {
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    TinkerLog.e(TAG, "thread sleep InterruptedException e:" + e, new Object[0]);
                }
            }
        }
        Iterator it = optFiles.iterator();
        while (it.hasNext()) {
            TinkerLog.i(TAG, "check dex optimizer file %s, size %d", r0.getName(), Long.valueOf(((File) it.next()).length()));
            if (!SharePatchFileUtil.isLegalFile((File) it.next())) {
                TinkerLog.e(TAG, "final parallel dex optimizer file %s is not exist, return false", r0.getName());
                return false;
            }
        }
        return true;
    }

    private static boolean patchDexExtractViaDexDiff(Context context, String str, String str2, File file) {
        String str3 = str + "/" + ShareConstants.DEX_PATH + "/";
        if (extractDexDiffInternals(context, str3, str2, file, 3)) {
            Tinker with = Tinker.with(context);
            File[] listFiles = new File(str3).listFiles();
            optFiles.clear();
            if (listFiles != null) {
                String str4 = str + "/" + ShareConstants.DEX_OPTIMIZE_PATH + "/";
                File file2 = new File(str4);
                if (file2.exists() || file2.mkdirs()) {
                    int i;
                    for (File optimizedPathFor : listFiles) {
                        optFiles.add(new File(SharePatchFileUtil.optimizedPathFor(optimizedPathFor, file2)));
                    }
                    TinkerLog.w(TAG, "patch recover, try to optimize dex file count:%d", Integer.valueOf(listFiles.length));
                    File file3;
                    long currentTimeMillis;
                    if (ShareTinkerInternals.isVmArt()) {
                        failOptDexFile.clear();
                        TinkerParallelDexOptimizer.optimizeAll(listFiles, file2, new 1());
                        for (File file32 : failOptDexFile) {
                            try {
                                String optimizedPathFor2 = SharePatchFileUtil.optimizedPathFor(file32, file2);
                                if (SharePatchFileUtil.isLegalFile(file32)) {
                                    TinkerLog.i(TAG, "try to retry dex optimize file, path: %s, size: %d", file32.getPath(), Long.valueOf(file32.length()));
                                    currentTimeMillis = System.currentTimeMillis();
                                    DexFile.loadDex(file32.getAbsolutePath(), optimizedPathFor2, 0);
                                    TinkerLog.i(TAG, "success retry dex optimize file, path: %s, opt file size: %d, use time: %d", file32.getPath(), Long.valueOf(new File(optimizedPathFor2).length()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                                } else {
                                    with.getPatchReporter().onPatchDexOptFail(file, file32, str4, file32.getName(), new TinkerRuntimeException("retry dex optimize file is not exist, name: " + file32.getName()));
                                    return false;
                                }
                            } catch (Throwable th) {
                                TinkerLog.e(TAG, "retry dex optimize or load failed, path:" + file32.getPath(), new Object[0]);
                                with.getPatchReporter().onPatchDexOptFail(file, file32, str4, file32.getName(), th);
                                return false;
                            }
                        }
                    }
                    int length = listFiles.length;
                    i = 0;
                    while (i < length) {
                        file32 = listFiles[i];
                        try {
                            String optimizedPathFor3 = SharePatchFileUtil.optimizedPathFor(file32, file2);
                            currentTimeMillis = System.currentTimeMillis();
                            DexFile.loadDex(file32.getAbsolutePath(), optimizedPathFor3, 0);
                            TinkerLog.i(TAG, "success single dex optimize file, path: %s, opt file size: %d, use time: %d", file32.getPath(), Long.valueOf(new File(optimizedPathFor3).length()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                            i++;
                        } catch (Throwable th2) {
                            TinkerLog.e(TAG, "single dex optimize or load failed, path:" + file32.getPath(), new Object[0]);
                            with.getPatchReporter().onPatchDexOptFail(file, file32, str4, file32.getName(), th2);
                            return false;
                        }
                    }
                }
                TinkerLog.w(TAG, "patch recover, make optimizeDexDirectoryFile fail", new Object[0]);
                return false;
            }
            return true;
        }
        TinkerLog.w(TAG, "patch recover, extractDiffInternals fail", new Object[0]);
        return false;
    }

    private static boolean checkAllDexOptFile(ArrayList<File> arrayList, int i) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!SharePatchFileUtil.isLegalFile((File) it.next())) {
                TinkerLog.e(TAG, "parallel dex optimizer file %s is not exist, just wait %d times", ((File) it.next()).getName(), Integer.valueOf(i));
                return false;
            }
        }
        return true;
    }

    private static boolean extractDexDiffInternals(Context context, String str, String str2, File file, int i) {
        Throwable th;
        ZipFile zipFile;
        Throwable th2;
        ArrayList arrayList = new ArrayList();
        ShareDexDiffPatchInfo.parseDexDiffPatchInfo(str2, arrayList);
        if (arrayList.isEmpty()) {
            TinkerLog.w(TAG, "extract patch list is empty! type:%s:", ShareTinkerInternals.getTypeString(i));
            return true;
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        Tinker with = Tinker.with(context);
        ZipFile zipFile2;
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                TinkerLog.w(TAG, "applicationInfo == null!!!!", new Object[0]);
                SharePatchFileUtil.closeZip(null);
                SharePatchFileUtil.closeZip(null);
                return false;
            }
            ZipFile zipFile3 = new ZipFile(applicationInfo.sourceDir);
            try {
                zipFile2 = new ZipFile(file);
                try {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str3;
                        ShareDexDiffPatchInfo shareDexDiffPatchInfo = (ShareDexDiffPatchInfo) it.next();
                        long currentTimeMillis = System.currentTimeMillis();
                        if (shareDexDiffPatchInfo.path.equals("")) {
                            str3 = shareDexDiffPatchInfo.rawName;
                        } else {
                            str3 = shareDexDiffPatchInfo.path + "/" + shareDexDiffPatchInfo.rawName;
                        }
                        String str4 = shareDexDiffPatchInfo.dexDiffMd5;
                        String str5 = shareDexDiffPatchInfo.oldDexCrC;
                        if (ShareTinkerInternals.isVmArt() || !shareDexDiffPatchInfo.destMd5InDvm.equals("0")) {
                            String str6 = ShareTinkerInternals.isVmArt() ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm;
                            if (SharePatchFileUtil.checkIfMd5Valid(str6)) {
                                File file3 = new File(str + shareDexDiffPatchInfo.realName);
                                if (!file3.exists()) {
                                    file3.getParentFile().mkdirs();
                                } else if (SharePatchFileUtil.verifyDexFileMd5(file3, str6)) {
                                    TinkerLog.w(TAG, "dex file %s is already exist, and md5 match, just continue", file3.getPath());
                                } else {
                                    TinkerLog.w(TAG, "have a mismatch corrupted dex " + file3.getPath(), new Object[0]);
                                    file3.delete();
                                }
                                ZipEntry entry = zipFile2.getEntry(str3);
                                ZipEntry entry2 = zipFile3.getEntry(str3);
                                if (str5.equals("0")) {
                                    if (entry == null) {
                                        TinkerLog.w(TAG, "patch entry is null. path:" + str3, new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                        SharePatchFileUtil.closeZip(zipFile3);
                                        SharePatchFileUtil.closeZip(zipFile2);
                                        return false;
                                    } else if (!extractDexFile(zipFile2, entry, file3, shareDexDiffPatchInfo)) {
                                        TinkerLog.w(TAG, "Failed to extract raw patch file " + file3.getPath(), new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                        SharePatchFileUtil.closeZip(zipFile3);
                                        SharePatchFileUtil.closeZip(zipFile2);
                                        return false;
                                    }
                                } else if (str4.equals("0")) {
                                    if (!ShareTinkerInternals.isVmArt()) {
                                        continue;
                                    } else if (entry2 == null) {
                                        TinkerLog.w(TAG, "apk entry is null. path:" + str3, new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                        SharePatchFileUtil.closeZip(zipFile3);
                                        SharePatchFileUtil.closeZip(zipFile2);
                                        return false;
                                    } else {
                                        if (String.valueOf(entry2.getCrc()).equals(str5)) {
                                            extractDexFile(zipFile3, entry2, file3, shareDexDiffPatchInfo);
                                            if (!SharePatchFileUtil.verifyDexFileMd5(file3, str6)) {
                                                TinkerLog.w(TAG, "Failed to recover dex file when verify patched dex: " + file3.getPath(), new Object[0]);
                                                with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                                SharePatchFileUtil.safeDeleteFile(file3);
                                                SharePatchFileUtil.closeZip(zipFile3);
                                                SharePatchFileUtil.closeZip(zipFile2);
                                                return false;
                                            }
                                        } else {
                                            TinkerLog.e(TAG, "apk entry %s crc is not equal, expect crc: %s, got crc: %s", str3, str5, String.valueOf(entry2.getCrc()));
                                            with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                            SharePatchFileUtil.closeZip(zipFile3);
                                            SharePatchFileUtil.closeZip(zipFile2);
                                            return false;
                                        }
                                    }
                                } else if (entry == null) {
                                    TinkerLog.w(TAG, "patch entry is null. path:" + str3, new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                    SharePatchFileUtil.closeZip(zipFile3);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                } else if (!SharePatchFileUtil.checkIfMd5Valid(str4)) {
                                    TinkerLog.w(TAG, "meta file md5 invalid, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), shareDexDiffPatchInfo.rawName, str4);
                                    with.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                                    SharePatchFileUtil.closeZip(zipFile3);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                } else if (entry2 == null) {
                                    TinkerLog.w(TAG, "apk entry is null. path:" + str3, new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                    SharePatchFileUtil.closeZip(zipFile3);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                } else {
                                    if (String.valueOf(entry2.getCrc()).equals(str5)) {
                                        patchDexFile(zipFile3, zipFile2, entry2, entry, shareDexDiffPatchInfo, file3);
                                        if (SharePatchFileUtil.verifyDexFileMd5(file3, str6)) {
                                            TinkerLog.w(TAG, "success recover dex file: %s, size: %d, use time: %d", file3.getPath(), Long.valueOf(file3.length()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                                        } else {
                                            TinkerLog.w(TAG, "Failed to recover dex file when verify patched dex: " + file3.getPath(), new Object[0]);
                                            with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                            SharePatchFileUtil.safeDeleteFile(file3);
                                            SharePatchFileUtil.closeZip(zipFile3);
                                            SharePatchFileUtil.closeZip(zipFile2);
                                            return false;
                                        }
                                    }
                                    TinkerLog.e(TAG, "apk entry %s crc is not equal, expect crc: %s, got crc: %s", str3, str5, String.valueOf(entry2.getCrc()));
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, shareDexDiffPatchInfo.rawName, i);
                                    SharePatchFileUtil.closeZip(zipFile3);
                                    SharePatchFileUtil.closeZip(zipFile2);
                                    return false;
                                }
                            }
                            TinkerLog.w(TAG, "meta file md5 invalid, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), shareDexDiffPatchInfo.rawName, str6);
                            with.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
                            SharePatchFileUtil.closeZip(zipFile3);
                            SharePatchFileUtil.closeZip(zipFile2);
                            return false;
                        }
                        TinkerLog.w(TAG, "patch dex %s is only for art, just continue", str3);
                    }
                    SharePatchFileUtil.closeZip(zipFile3);
                    SharePatchFileUtil.closeZip(zipFile2);
                    return true;
                } catch (Throwable th3) {
                    th = th3;
                    zipFile = zipFile3;
                    th2 = th;
                }
            } catch (Throwable th4) {
                th = th4;
                zipFile2 = null;
                zipFile = zipFile3;
                th2 = th;
                SharePatchFileUtil.closeZip(zipFile);
                SharePatchFileUtil.closeZip(zipFile2);
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            zipFile = null;
            zipFile2 = null;
            SharePatchFileUtil.closeZip(zipFile);
            SharePatchFileUtil.closeZip(zipFile2);
            throw th2;
        }
    }

    private static boolean extractDexToJar(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException {
        Throwable th;
        Closeable closeable = null;
        boolean z = false;
        int i = 0;
        while (i < 2 && !z) {
            Closeable bufferedInputStream;
            int i2 = i + 1;
            OutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            TinkerLog.i(TAG, "try Extracting " + file.getPath(), new Object[0]);
            try {
                Closeable zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
                try {
                    bufferedInputStream = new BufferedInputStream(inputStream);
                    try {
                        byte[] bArr = new byte[16384];
                        zipOutputStream.putNextEntry(new ZipEntry("classes.dex"));
                        for (int read = bufferedInputStream.read(bArr); read != -1; read = bufferedInputStream.read(bArr)) {
                            zipOutputStream.write(bArr, 0, read);
                        }
                        zipOutputStream.closeEntry();
                        SharePatchFileUtil.closeQuietly(bufferedInputStream);
                        SharePatchFileUtil.closeQuietly(zipOutputStream);
                        z = SharePatchFileUtil.verifyDexFileMd5(file, str);
                        TinkerLog.i(TAG, "isExtractionSuccessful: %b", Boolean.valueOf(z));
                        if (!z) {
                            file.delete();
                            if (file.exists()) {
                                TinkerLog.e(TAG, "Failed to delete corrupted dex " + file.getPath(), new Object[0]);
                            }
                        }
                        i = i2;
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = zipOutputStream;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedInputStream = null;
                    closeable = zipOutputStream;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream = null;
            }
        }
        return z;
        SharePatchFileUtil.closeQuietly(bufferedInputStream);
        SharePatchFileUtil.closeQuietly(closeable);
        throw th;
    }

    private static boolean extractDexFile(ZipFile zipFile, ZipEntry zipEntry, File file, ShareDexDiffPatchInfo shareDexDiffPatchInfo) throws IOException {
        String str = ShareTinkerInternals.isVmArt() ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm;
        String str2 = shareDexDiffPatchInfo.rawName;
        boolean z = shareDexDiffPatchInfo.isJarMode;
        if (SharePatchFileUtil.isRawDexFile(str2) && z) {
            return extractDexToJar(zipFile, zipEntry, file, str);
        }
        return BasePatchInternal.extract(zipFile, zipEntry, file, str, true);
    }

    private static void patchDexFile(ZipFile zipFile, ZipFile zipFile2, ZipEntry zipEntry, ZipEntry zipEntry2, ShareDexDiffPatchInfo shareDexDiffPatchInfo, File file) throws IOException {
        Throwable th;
        Closeable closeable = null;
        Closeable closeable2;
        try {
            Closeable bufferedInputStream;
            Closeable bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            if (zipEntry2 != null) {
                try {
                    bufferedInputStream = new BufferedInputStream(zipFile2.getInputStream(zipEntry2));
                } catch (Throwable th2) {
                    th = th2;
                    closeable2 = bufferedInputStream2;
                    SharePatchFileUtil.closeQuietly(closeable2);
                    SharePatchFileUtil.closeQuietly(closeable);
                    throw th;
                }
            }
            bufferedInputStream = null;
            try {
                boolean isRawDexFile = SharePatchFileUtil.isRawDexFile(shareDexDiffPatchInfo.rawName);
                if (!isRawDexFile || shareDexDiffPatchInfo.isJarMode) {
                    try {
                        Closeable zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                        try {
                            zipOutputStream.putNextEntry(new ZipEntry("classes.dex"));
                            if (isRawDexFile) {
                                new DexPatchApplier(bufferedInputStream2, bufferedInputStream).executeAndSaveTo(zipOutputStream);
                            } else {
                                try {
                                    ZipEntry nextEntry;
                                    closeable2 = new ZipInputStream(bufferedInputStream2);
                                    do {
                                        try {
                                            nextEntry = closeable2.getNextEntry();
                                            if (nextEntry == null) {
                                                break;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                        }
                                    } while (!"classes.dex".equals(nextEntry.getName()));
                                    if (nextEntry == null) {
                                        throw new TinkerRuntimeException("can't recognize zip dex format file:" + file.getAbsolutePath());
                                    }
                                    new DexPatchApplier(closeable2, bufferedInputStream).executeAndSaveTo(zipOutputStream);
                                    SharePatchFileUtil.closeQuietly(closeable2);
                                } catch (Throwable th4) {
                                    th = th4;
                                    closeable2 = null;
                                    SharePatchFileUtil.closeQuietly(closeable2);
                                    throw th;
                                }
                            }
                            zipOutputStream.closeEntry();
                            SharePatchFileUtil.closeQuietly(zipOutputStream);
                        } catch (Throwable th5) {
                            th = th5;
                            closeable = zipOutputStream;
                            SharePatchFileUtil.closeQuietly(closeable);
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        SharePatchFileUtil.closeQuietly(closeable);
                        throw th;
                    }
                }
                new DexPatchApplier(bufferedInputStream2, bufferedInputStream).executeAndSaveTo(file);
                SharePatchFileUtil.closeQuietly(bufferedInputStream2);
                SharePatchFileUtil.closeQuietly(bufferedInputStream);
            } catch (Throwable th7) {
                th = th7;
                closeable = bufferedInputStream;
                closeable2 = bufferedInputStream2;
                SharePatchFileUtil.closeQuietly(closeable2);
                SharePatchFileUtil.closeQuietly(closeable);
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            closeable2 = null;
            SharePatchFileUtil.closeQuietly(closeable2);
            SharePatchFileUtil.closeQuietly(closeable);
            throw th;
        }
    }
}
