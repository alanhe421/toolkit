package com.tencent.theme;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.os.Process;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.widget.AbsListView;
import java.lang.reflect.Field;

public class SkinnableActivityProcesser extends BroadcastReceiver {
    public static final String TAG = "[SkinnableActivityProcesser]";
    private final Activity mActivity;
    private final Callback mCallback;

    public interface Callback {
        void onPostThemeChanged();

        void onPreThemeChanged();
    }

    public SkinnableActivityProcesser(Activity activity, Callback callback) {
        this.mActivity = activity;
        this.mCallback = callback;
        try {
            this.mActivity.registerReceiver(this, new IntentFilter(SkinEngine.ACTION_THEME_INVALIDATE), "com.qq.reader.theme.permission", null);
        } catch (Exception e) {
        }
    }

    public void destory() {
        try {
            this.mActivity.unregisterReceiver(this);
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getIntExtra("pid", Process.myPid()) == Process.myPid()) {
                if (this.mCallback != null) {
                    this.mCallback.onPreThemeChanged();
                }
                View decorView = this.mActivity.getWindow().getDecorView();
                ViewGroup viewGroup = (ViewGroup) decorView;
                View[] viewArr = new View[viewGroup.getChildCount()];
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    viewArr[i] = viewGroup.getChildAt(i);
                    if (viewArr[i] instanceof AbsListView) {
                        ((AbsListView) viewArr[i]).tryRecycle();
                    } else if (viewArr[i] instanceof ViewGroup) {
                        View[] viewArr2 = new View[((ViewGroup) viewArr[i]).getChildCount()];
                        for (int i2 = 0; i2 < ((ViewGroup) viewArr[i]).getChildCount(); i2++) {
                            if (viewArr2[i2] instanceof AbsListView) {
                                ((AbsListView) viewArr2[i2]).tryRecycle();
                            }
                        }
                    }
                }
                SkinEngine.invalidateAll(decorView);
                if (this.mCallback != null) {
                    this.mCallback.onPostThemeChanged();
                }
            }
        } catch (Exception e) {
            c.a(TAG, e.toString());
        }
    }

    static boolean updateDrawableContainerPadding(Drawable drawable) {
        boolean z = false;
        if (!(drawable instanceof DrawableContainer)) {
            return false;
        }
        DrawableContainerState drawableContainerState = (DrawableContainerState) drawable.getConstantState();
        Drawable[] children = drawableContainerState.getChildren();
        int childCount = drawableContainerState.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Drawable drawable2 = children[i];
            if (drawable2 instanceof SkinnableNinePatchDrawable) {
                z = true;
            } else if (drawable2 instanceof DrawableContainer) {
                z |= updateDrawableContainerPadding(drawable2);
            }
        }
        Class cls = DrawableContainerState.class;
        try {
            Field declaredField = cls.getDeclaredField("mComputedConstantSize");
            declaredField.setAccessible(true);
            declaredField.setBoolean(drawableContainerState, false);
        } catch (Exception e) {
        }
        if (!z) {
            return z;
        }
        try {
            Field declaredField2 = cls.getDeclaredField("mPaddingChecked");
            declaredField2.setAccessible(true);
            declaredField2.setBoolean(drawableContainerState, false);
            declaredField2 = cls.getDeclaredField("mConstantPadding");
            declaredField2.setAccessible(true);
            declaredField2.set(drawableContainerState, null);
            return true;
        } catch (Exception e2) {
            return true;
        }
    }
}
