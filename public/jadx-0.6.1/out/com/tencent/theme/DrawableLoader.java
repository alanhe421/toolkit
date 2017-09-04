package com.tencent.theme;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.TypedValue;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@TargetApi(16)
class DrawableLoader {
    LongSparseArray<Integer> mKeyToResourcesId;
    LongSparseArray<ConstantState>[] mOldPreloadCache;
    SkinEngine mSkinEngine;

    public DrawableLoader(SkinEngine skinEngine, Resources resources, int[] iArr, File file, LongSparseArray<ConstantState>... longSparseArrayArr) {
        this.mSkinEngine = skinEngine;
        this.mOldPreloadCache = new LongSparseArray[longSparseArrayArr.length];
        System.arraycopy(longSparseArrayArr, 0, this.mOldPreloadCache, 0, longSparseArrayArr.length);
        this.mKeyToResourcesId = new LongSparseArray(iArr.length);
        long uptimeMillis = SystemClock.uptimeMillis();
        if (file != null) {
            try {
                if (file.exists()) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
                    while (objectInputStream.available() > 0) {
                        this.mKeyToResourcesId.put(objectInputStream.readLong(), Integer.valueOf(objectInputStream.readInt()));
                    }
                    objectInputStream.close();
                    if (SkinEngine.DEBUG) {
                        Log.d(SkinEngine.TAG, "int DrawablePreloadIntercepter cost: " + (SystemClock.uptimeMillis() - uptimeMillis));
                        return;
                    }
                    return;
                }
            } catch (Throwable e) {
                if (SkinEngine.DEBUG) {
                    Log.w(SkinEngine.TAG, "", e);
                }
            } catch (Throwable th) {
                if (SkinEngine.DEBUG) {
                    Log.d(SkinEngine.TAG, "int DrawablePreloadIntercepter cost: " + (SystemClock.uptimeMillis() - uptimeMillis));
                }
            }
        }
        TypedValue typedValue = new TypedValue();
        for (int i : iArr) {
            resources.getValue(i, typedValue, true);
            if (typedValue.string != null) {
                String charSequence = typedValue.string.toString();
                if (charSequence.endsWith(".9.png") || charSequence.endsWith(".png") || charSequence.endsWith(".jpg") || charSequence.endsWith(".gif")) {
                    this.mKeyToResourcesId.put((((long) typedValue.assetCookie) << 32) | ((long) typedValue.data), Integer.valueOf(i));
                }
            }
        }
        if (SkinEngine.DEBUG) {
            Log.d(SkinEngine.TAG, "int DrawablePreloadIntercepter cost: " + (SystemClock.uptimeMillis() - uptimeMillis));
        }
    }

    public DrawableLoader(SkinEngine skinEngine, Resources resources, Class cls, int i, File file, LongSparseArray<ConstantState>... longSparseArrayArr) {
        this.mSkinEngine = skinEngine;
        this.mOldPreloadCache = new LongSparseArray[longSparseArrayArr.length];
        System.arraycopy(longSparseArrayArr, 0, this.mOldPreloadCache, 0, longSparseArrayArr.length);
        this.mKeyToResourcesId = new LongSparseArray(cls.getDeclaredFields().length + 10);
        long uptimeMillis = SystemClock.uptimeMillis();
        if (file != null) {
            try {
                if (file.exists()) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
                    while (objectInputStream.available() > 0) {
                        this.mKeyToResourcesId.put(objectInputStream.readLong(), Integer.valueOf(objectInputStream.readInt()));
                    }
                    objectInputStream.close();
                    if (SkinEngine.DEBUG) {
                        Log.d(SkinEngine.TAG, "int DrawablePreloadIntercepter cost: " + (SystemClock.uptimeMillis() - uptimeMillis));
                        return;
                    }
                    return;
                }
            } catch (Throwable e) {
                if (SkinEngine.DEBUG) {
                    Log.w(SkinEngine.TAG, "", e);
                }
            } catch (Throwable th) {
                if (SkinEngine.DEBUG) {
                    Log.d(SkinEngine.TAG, "int DrawablePreloadIntercepter cost: " + (SystemClock.uptimeMillis() - uptimeMillis));
                }
            }
        }
        TypedValue typedValue = new TypedValue();
        while (true) {
            try {
                resources.getValue(i, typedValue, true);
                if (typedValue.string != null) {
                    String charSequence = typedValue.string.toString();
                    if (charSequence.endsWith(".9.png") || charSequence.endsWith(".png") || charSequence.endsWith(".jpg") || charSequence.endsWith(".gif")) {
                        this.mKeyToResourcesId.put((((long) typedValue.assetCookie) << 32) | ((long) typedValue.data), Integer.valueOf(i));
                    }
                }
                i++;
            } catch (NotFoundException e2) {
                if (SkinEngine.DEBUG) {
                    Log.d(SkinEngine.TAG, "int DrawablePreloadIntercepter cost: " + (SystemClock.uptimeMillis() - uptimeMillis));
                    return;
                }
                return;
            }
        }
    }

    void addResource(Resources resources, int i) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        if (typedValue.string != null && typedValue.string.toString().endsWith(".xml")) {
            this.mKeyToResourcesId.put((((long) typedValue.assetCookie) << 32) | ((long) typedValue.data), Integer.valueOf(i));
        }
    }

    void writeCacheFile(File file) throws IOException {
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (int i = 0; i < this.mKeyToResourcesId.size(); i++) {
                long keyAt = this.mKeyToResourcesId.keyAt(i);
                int intValue = ((Integer) this.mKeyToResourcesId.valueAt(i)).intValue();
                objectOutputStream.writeLong(keyAt);
                objectOutputStream.writeInt(intValue);
            }
            objectOutputStream.close();
        }
    }

    public ConstantState get(int i, long j) {
        Integer num = (Integer) this.mKeyToResourcesId.get(j);
        if (num == null) {
            return (ConstantState) this.mOldPreloadCache[i].get(j);
        }
        if (SkinEngine.mIconResourceID == null || !SkinEngine.mIconResourceID.equals(num)) {
            return this.mSkinEngine.loadConstantState(num.intValue());
        }
        if (SkinEngine.DEBUG) {
            Log.d(SkinEngine.TAG, "To support Samsung multi-window, return default icon resource");
        }
        return null;
    }
}
