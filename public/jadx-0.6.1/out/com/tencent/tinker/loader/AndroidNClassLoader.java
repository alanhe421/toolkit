package com.tencent.tinker.loader;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@TargetApi(14)
class AndroidNClassLoader extends PathClassLoader {
    static ArrayList<DexFile> oldDexFiles = new ArrayList();
    PathClassLoader originClassLoader;

    private AndroidNClassLoader(String str, PathClassLoader pathClassLoader) {
        super(str, pathClassLoader.getParent());
        this.originClassLoader = pathClassLoader;
    }

    private static AndroidNClassLoader createAndroidNClassLoader(PathClassLoader pathClassLoader) throws Exception {
        Object androidNClassLoader = new AndroidNClassLoader("", pathClassLoader);
        Object obj = ShareReflectUtil.findField((Object) pathClassLoader, "pathList").get(pathClassLoader);
        ShareReflectUtil.findField(obj, "definingContext").set(obj, androidNClassLoader);
        ShareReflectUtil.findField(androidNClassLoader, "pathList").set(androidNClassLoader, obj);
        List arrayList = new ArrayList();
        Field findField = ShareReflectUtil.findField(obj, "dexElements");
        for (Object obj2 : (Object[]) findField.get(obj)) {
            DexFile dexFile = (DexFile) ShareReflectUtil.findField(obj2, "dexFile").get(obj2);
            arrayList.add(new File(dexFile.getName()));
            oldDexFiles.add(dexFile);
        }
        Method findMethod = ShareReflectUtil.findMethod(obj, "makePathElements", List.class, File.class, List.class);
        ArrayList arrayList2 = new ArrayList();
        findField.set(obj, (Object[]) findMethod.invoke(obj, new Object[]{arrayList, null, arrayList2}));
        return androidNClassLoader;
    }

    private static void reflectPackageInfoClassloader(Application application, ClassLoader classLoader) throws Exception {
        Object obj = (Context) ShareReflectUtil.findField((Object) application, "mBase").get(application);
        obj = ShareReflectUtil.findField(obj, "mPackageInfo").get(obj);
        Field findField = ShareReflectUtil.findField(obj, "mClassLoader");
        Thread.currentThread().setContextClassLoader(classLoader);
        findField.set(obj, classLoader);
    }

    public static AndroidNClassLoader inject(PathClassLoader pathClassLoader, Application application) throws Exception {
        ClassLoader createAndroidNClassLoader = createAndroidNClassLoader(pathClassLoader);
        reflectPackageInfoClassloader(application, createAndroidNClassLoader);
        return createAndroidNClassLoader;
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        if (str == null || !str.startsWith("com.tencent.tinker.loader.") || str.equals("com.tencent.tinker.loader.TinkerTestDexLoad")) {
            return super.findClass(str);
        }
        return this.originClassLoader.loadClass(str);
    }

    public String findLibrary(String str) {
        return super.findLibrary(str);
    }
}
