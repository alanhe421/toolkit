package com.tencent.tinker.loader;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build.VERSION;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipFile;

public class SystemClassLoaderAdder {
    private static final String CHECK_DEX_CLASS = "com.tencent.tinker.loader.TinkerTestDexLoad";
    private static final String CHECK_DEX_FIELD = "isPatch";
    private static final String TAG = "Tinker.ClassLoaderAdder";
    private static int sPatchDexCount = 0;

    private static final class V14 {
        private V14() {
        }

        private static void install(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            Object obj = ShareReflectUtil.findField((Object) classLoader, "pathList").get(classLoader);
            ShareReflectUtil.expandFieldArray(obj, "dexElements", makeDexElements(obj, new ArrayList(list), file));
        }

        private static Object[] makeDexElements(Object obj, ArrayList<File> arrayList, File file) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            return (Object[]) ShareReflectUtil.findMethod(obj, "makeDexElements", ArrayList.class, File.class).invoke(obj, new Object[]{arrayList, file});
        }
    }

    private static final class V19 {
        private V19() {
        }

        private static void install(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
            Object obj = ShareReflectUtil.findField((Object) classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            ShareReflectUtil.expandFieldArray(obj, "dexElements", makeDexElements(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    IOException iOException = (IOException) it.next();
                    Log.w(SystemClassLoaderAdder.TAG, "Exception in makeDexElement", iOException);
                    throw iOException;
                }
            }
        }

        private static Object[] makeDexElements(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Method findMethod;
            try {
                findMethod = ShareReflectUtil.findMethod(obj, "makeDexElements", ArrayList.class, File.class, ArrayList.class);
            } catch (NoSuchMethodException e) {
                Log.e(SystemClassLoaderAdder.TAG, "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure");
                try {
                    findMethod = ShareReflectUtil.findMethod(obj, "makeDexElements", List.class, File.class, List.class);
                } catch (NoSuchMethodException e2) {
                    Log.e(SystemClassLoaderAdder.TAG, "NoSuchMethodException: makeDexElements(List,File,List) failure");
                    throw e2;
                }
            }
            return (Object[]) findMethod.invoke(obj, new Object[]{arrayList, file, arrayList2});
        }
    }

    private static final class V23 {
        private V23() {
        }

        private static void install(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
            Object obj = ShareReflectUtil.findField((Object) classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            ShareReflectUtil.expandFieldArray(obj, "dexElements", makePathElements(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    IOException iOException = (IOException) it.next();
                    Log.w(SystemClassLoaderAdder.TAG, "Exception in makePathElement", iOException);
                    throw iOException;
                }
            }
        }

        private static Object[] makePathElements(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Method findMethod;
            try {
                findMethod = ShareReflectUtil.findMethod(obj, "makePathElements", List.class, File.class, List.class);
            } catch (NoSuchMethodException e) {
                Log.e(SystemClassLoaderAdder.TAG, "NoSuchMethodException: makePathElements(List,File,List) failure");
                try {
                    findMethod = ShareReflectUtil.findMethod(obj, "makePathElements", ArrayList.class, File.class, ArrayList.class);
                } catch (NoSuchMethodException e2) {
                    Log.e(SystemClassLoaderAdder.TAG, "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure");
                    try {
                        Log.e(SystemClassLoaderAdder.TAG, "NoSuchMethodException: try use v19 instead");
                        return V19.makeDexElements(obj, arrayList, file, arrayList2);
                    } catch (NoSuchMethodException e3) {
                        Log.e(SystemClassLoaderAdder.TAG, "NoSuchMethodException: makeDexElements(List,File,List) failure");
                        throw e3;
                    }
                }
            }
            return (Object[]) findMethod.invoke(obj, new Object[]{arrayList, file, arrayList2});
        }
    }

    private static final class V4 {
        private V4() {
        }

        private static void install(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            int size = list.size();
            Field findField = ShareReflectUtil.findField((Object) classLoader, "path");
            StringBuilder stringBuilder = new StringBuilder((String) findField.get(classLoader));
            String[] strArr = new String[size];
            File[] fileArr = new File[size];
            ZipFile[] zipFileArr = new ZipFile[size];
            DexFile[] dexFileArr = new DexFile[size];
            ListIterator listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                File file2 = (File) listIterator.next();
                String absolutePath = file2.getAbsolutePath();
                stringBuilder.append(':').append(absolutePath);
                int previousIndex = listIterator.previousIndex();
                strArr[previousIndex] = absolutePath;
                fileArr[previousIndex] = file2;
                zipFileArr[previousIndex] = new ZipFile(file2);
                dexFileArr[previousIndex] = DexFile.loadDex(absolutePath, SharePatchFileUtil.optimizedPathFor(file2, file), 0);
            }
            findField.set(classLoader, stringBuilder.toString());
            ShareReflectUtil.expandFieldArray(classLoader, "mPaths", strArr);
            ShareReflectUtil.expandFieldArray(classLoader, "mFiles", fileArr);
            ShareReflectUtil.expandFieldArray(classLoader, "mZips", zipFileArr);
            try {
                ShareReflectUtil.expandFieldArray(classLoader, "mDexs", dexFileArr);
            } catch (Exception e) {
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void installDexes(Application application, PathClassLoader pathClassLoader, File file, List<File> list) throws Throwable {
        if (!list.isEmpty()) {
            if (VERSION.SDK_INT >= 24) {
                pathClassLoader = AndroidNClassLoader.inject(pathClassLoader, application);
            }
            if (VERSION.SDK_INT >= 23) {
                V23.install(pathClassLoader, list, file);
            } else if (VERSION.SDK_INT >= 19) {
                V19.install(pathClassLoader, list, file);
            } else if (VERSION.SDK_INT >= 14) {
                V14.install(pathClassLoader, list, file);
            } else {
                V4.install(pathClassLoader, list, file);
            }
            sPatchDexCount = list.size();
            Log.i(TAG, "after loaded classloader: " + pathClassLoader + ", dex size:" + sPatchDexCount);
            if (!checkDexInstall(pathClassLoader)) {
                uninstallPatchDex(pathClassLoader);
                throw new TinkerRuntimeException(ShareConstants.CHECK_DEX_INSTALL_FAIL);
            }
        }
    }

    public static void uninstallPatchDex(ClassLoader classLoader) throws Throwable {
        if (sPatchDexCount > 0) {
            if (VERSION.SDK_INT >= 14) {
                ShareReflectUtil.reduceFieldArray(ShareReflectUtil.findField((Object) classLoader, "pathList").get(classLoader), "dexElements", sPatchDexCount);
                return;
            }
            ShareReflectUtil.reduceFieldArray(classLoader, "mPaths", sPatchDexCount);
            ShareReflectUtil.reduceFieldArray(classLoader, "mFiles", sPatchDexCount);
            ShareReflectUtil.reduceFieldArray(classLoader, "mZips", sPatchDexCount);
            try {
                ShareReflectUtil.reduceFieldArray(classLoader, "mDexs", sPatchDexCount);
            } catch (Exception e) {
            }
        }
    }

    private static boolean checkDexInstall(ClassLoader classLoader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        boolean booleanValue = ((Boolean) ShareReflectUtil.findField(Class.forName(CHECK_DEX_CLASS, true, classLoader), CHECK_DEX_FIELD).get(null)).booleanValue();
        Log.w(TAG, "checkDexInstall result:" + booleanValue);
        return booleanValue;
    }
}
