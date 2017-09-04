package com.qq.reader.liveshow.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;

/* compiled from: ActivityLeakSolution */
public class b {
    public static void a(Context context) {
        if (context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (inputMethodManager != null) {
                String[] strArr = new String[]{"mCurRootView", "mServedView", "mNextServedView", "mLastSrvView"};
                for (String declaredField : strArr) {
                    try {
                        Field declaredField2 = inputMethodManager.getClass().getDeclaredField(declaredField);
                        if (!declaredField2.isAccessible()) {
                            declaredField2.setAccessible(true);
                        }
                        Object obj = declaredField2.get(inputMethodManager);
                        if (obj != null && (obj instanceof View)) {
                            View view = (View) obj;
                            if (view.getContext() == context) {
                                declaredField2.set(inputMethodManager, null);
                            } else {
                                SxbLog.c("ActivityLeakSolution", "fixInputMethodManagerLeak break, context is not suitable, get_context=" + view.getContext() + " dest_context=" + context);
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public static void a(Dialog dialog) {
        if (dialog != null) {
            for (String declaredField : new String[]{"mDismissMessage", "mCancelMessage", "mShowMessage"}) {
                try {
                    Field declaredField2 = Dialog.class.getDeclaredField(declaredField);
                    if (declaredField2 != null) {
                        if (!declaredField2.isAccessible()) {
                            declaredField2.setAccessible(true);
                        }
                        Object obj = declaredField2.get(dialog);
                        if (obj instanceof Message) {
                            Message message = (Message) obj;
                            if (message.obj != null) {
                                message.obj = null;
                                message.what = 0;
                            }
                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }
}
