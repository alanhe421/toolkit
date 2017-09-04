package com.tencent.tinker.loader;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.ArrayMap;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class TinkerResourcePatcher {
    private static final String TAG = "Tinker.ResourcePatcher";
    private static final String TEST_ASSETS_VALUE = "only_use_to_test_tinker_resource.txt";
    private static Method addAssetPathMethod = null;
    private static Field assetsFiled = null;
    private static Object currentActivityThread = null;
    private static Method ensureStringBlocksMethod = null;
    private static AssetManager newAssetManager = null;
    private static Field packagesFiled = null;
    private static Collection<WeakReference<Resources>> references = null;
    private static Field resDir = null;
    private static Field resourcePackagesFiled = null;
    private static Field resourcesImplFiled = null;

    TinkerResourcePatcher() {
    }

    public static void isResourceCanPatch(Context context) throws Throwable {
        Class cls;
        Class cls2 = Class.forName("android.app.ActivityThread");
        currentActivityThread = ShareReflectUtil.getActivityThread(context, cls2);
        try {
            cls = Class.forName("android.app.LoadedApk");
        } catch (ClassNotFoundException e) {
            cls = Class.forName("android.app.ActivityThread$PackageInfo");
        }
        resDir = cls.getDeclaredField("mResDir");
        resDir.setAccessible(true);
        packagesFiled = cls2.getDeclaredField("mPackages");
        packagesFiled.setAccessible(true);
        resourcePackagesFiled = cls2.getDeclaredField("mResourcePackages");
        resourcePackagesFiled.setAccessible(true);
        if (context.getAssets().getClass().getName().equals("android.content.res.BaiduAssetManager")) {
            newAssetManager = (AssetManager) Class.forName("android.content.res.BaiduAssetManager").getConstructor(new Class[0]).newInstance(new Object[0]);
        } else {
            newAssetManager = (AssetManager) AssetManager.class.getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        addAssetPathMethod = AssetManager.class.getDeclaredMethod("addAssetPath", new Class[]{String.class});
        addAssetPathMethod.setAccessible(true);
        ensureStringBlocksMethod = AssetManager.class.getDeclaredMethod("ensureStringBlocks", new Class[0]);
        ensureStringBlocksMethod.setAccessible(true);
        Field declaredField;
        if (VERSION.SDK_INT >= 19) {
            cls2 = Class.forName("android.app.ResourcesManager");
            Method declaredMethod = cls2.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            try {
                declaredField = cls2.getDeclaredField("mActiveResources");
                declaredField.setAccessible(true);
                references = ((ArrayMap) declaredField.get(invoke)).values();
            } catch (NoSuchFieldException e2) {
                declaredField = cls2.getDeclaredField("mResourceReferences");
                declaredField.setAccessible(true);
                references = (Collection) declaredField.get(invoke);
            }
        } else {
            declaredField = cls2.getDeclaredField("mActiveResources");
            declaredField.setAccessible(true);
            references = ((HashMap) declaredField.get(currentActivityThread)).values();
        }
        if (references == null) {
            throw new IllegalStateException("resource references is null");
        }
        try {
            assetsFiled = Resources.class.getDeclaredField("mAssets");
            assetsFiled.setAccessible(true);
        } catch (Throwable th) {
            resourcesImplFiled = Resources.class.getDeclaredField("mResourcesImpl");
            resourcesImplFiled.setAccessible(true);
        }
    }

    public static void monkeyPatchExistingResources(Context context, String str) throws Throwable {
        if (str != null) {
            for (Field field : new Field[]{packagesFiled, resourcePackagesFiled}) {
                for (Entry value : ((Map) field.get(currentActivityThread)).entrySet()) {
                    Object obj = ((WeakReference) value.getValue()).get();
                    if (!(obj == null || str == null)) {
                        resDir.set(obj, str);
                    }
                }
            }
            if (((Integer) addAssetPathMethod.invoke(newAssetManager, new Object[]{str})).intValue() == 0) {
                throw new IllegalStateException("Could not create new AssetManager");
            }
            ensureStringBlocksMethod.invoke(newAssetManager, new Object[0]);
            for (WeakReference weakReference : references) {
                Resources resources = (Resources) weakReference.get();
                if (resources != null) {
                    try {
                        assetsFiled.set(resources, newAssetManager);
                    } catch (Throwable th) {
                        Object obj2 = resourcesImplFiled.get(resources);
                        Field findField = ShareReflectUtil.findField(obj2, "mAssets");
                        findField.setAccessible(true);
                        findField.set(obj2, newAssetManager);
                    }
                    clearPreloadTypedArrayIssue(resources);
                    resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
                }
            }
            if (!checkResUpdate(context)) {
                throw new TinkerRuntimeException(ShareConstants.CHECK_RES_INSTALL_FAIL);
            }
        }
    }

    private static void clearPreloadTypedArrayIssue(Resources resources) {
        Log.w(TAG, "try to clear typedArray cache!");
        try {
            Field findField = ShareReflectUtil.findField(Resources.class, "mTypedArrayPool");
            Object obj = findField.get(resources);
            Field findField2 = ShareReflectUtil.findField(obj, "mPool");
            Constructor constructor = obj.getClass().getConstructor(new Class[]{Integer.TYPE});
            constructor.setAccessible(true);
            findField.set(resources, constructor.newInstance(new Object[]{Integer.valueOf(((Object[]) findField2.get(obj)).length)}));
        } catch (Throwable th) {
            Log.e(TAG, "clearPreloadTypedArrayIssue failed, ignore error: " + th);
        }
    }

    private static boolean checkResUpdate(Context context) {
        try {
            Log.e(TAG, "checkResUpdate success, found test resource assets file only_use_to_test_tinker_resource.txt");
            context.getAssets().open(TEST_ASSETS_VALUE);
            return true;
        } catch (Throwable th) {
            Log.e(TAG, "checkResUpdate failed, can't find test resource assets file only_use_to_test_tinker_resource.txt e:" + th.getMessage());
            return false;
        }
    }
}
