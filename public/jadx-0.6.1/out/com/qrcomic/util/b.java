package com.qrcomic.util;

import android.app.Dialog;
import android.os.Message;
import java.lang.reflect.Field;

/* compiled from: ActivityLeakSolution */
public class b {
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
