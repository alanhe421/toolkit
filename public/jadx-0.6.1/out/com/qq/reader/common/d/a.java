package com.qq.reader.common.d;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Message;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.view.AlertController;
import com.qq.reader.view.AlertDialog;
import com.tencent.widget.ListView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ActivityLeakSolution */
public class a {
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
                                c.a("ActivityLeakSolution", "fixInputMethodManagerLeak break, context is not suitable, get_context=" + view.getContext() + " dest_context=" + context);
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

    public static void a(AlertDialog alertDialog) {
        if (alertDialog != null) {
            Field declaredField;
            Object obj;
            for (String declaredField2 : new String[]{"mDismissMessage", "mCancelMessage", "mShowMessage"}) {
                try {
                    declaredField = Dialog.class.getDeclaredField(declaredField2);
                    if (declaredField != null) {
                        if (!declaredField.isAccessible()) {
                            declaredField.setAccessible(true);
                        }
                        obj = declaredField.get(alertDialog);
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
            String[] strArr = new String[]{"mButtonPositiveMessage", "mButtonNegativeMessage"};
            try {
                declaredField = AlertDialog.class.getDeclaredField("mAlert");
                if (declaredField != null) {
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    obj = declaredField.get(alertDialog);
                    if (obj instanceof AlertController) {
                        AlertController alertController = (AlertController) obj;
                        for (String declaredField3 : strArr) {
                            try {
                                Field declaredField4 = AlertController.class.getDeclaredField(declaredField3);
                                if (declaredField4 != null) {
                                    if (!declaredField4.isAccessible()) {
                                        declaredField4.setAccessible(true);
                                    }
                                    Object obj2 = declaredField4.get(alertController);
                                    if (obj2 instanceof Message) {
                                        Message message2 = (Message) obj2;
                                        if (message2.obj != null) {
                                            message2.obj = null;
                                            message2.what = 0;
                                        }
                                    }
                                }
                            } catch (NoSuchFieldException e4) {
                                e4.printStackTrace();
                            } catch (IllegalArgumentException e5) {
                                e5.printStackTrace();
                            } catch (IllegalAccessException e6) {
                                e6.printStackTrace();
                            } catch (Throwable th2) {
                                th2.printStackTrace();
                            }
                        }
                    }
                }
            } catch (NoSuchFieldException e7) {
                e7.printStackTrace();
            } catch (IllegalArgumentException e22) {
                e22.printStackTrace();
            } catch (IllegalAccessException e32) {
                e32.printStackTrace();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
        }
    }

    private static void a(TextView textView) {
        if (textView != null) {
            textView.setHint("");
            try {
                Field declaredField = TextView.class.getDeclaredField("mListeners");
                if (declaredField != null) {
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    Object obj = declaredField.get(textView);
                    if (obj instanceof ArrayList) {
                        Iterator it = ((ArrayList) obj).iterator();
                        while (it.hasNext()) {
                            TextWatcher textWatcher = (TextWatcher) it.next();
                            it.remove();
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

    public static void a(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().peekDecorView() != null) {
            if (b.a) {
                a(activity, activity.getWindow().peekDecorView().getRootView());
                return;
            }
            try {
                a(activity, activity.getWindow().peekDecorView().getRootView());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void a(View view) {
        if (view != null) {
            try {
                view.setOnClickListener(null);
            } catch (Throwable e) {
                c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
                throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e));
            } catch (Throwable th) {
            }
            try {
                view.setOnCreateContextMenuListener(null);
            } catch (Throwable e2) {
                c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
                throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e2));
            } catch (Throwable th2) {
            }
            try {
                view.setOnFocusChangeListener(null);
            } catch (Throwable e22) {
                c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
                throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e22));
            } catch (Throwable th3) {
            }
            try {
                view.setOnKeyListener(null);
            } catch (Throwable e222) {
                c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
                throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e222));
            } catch (Throwable th4) {
            }
            try {
                view.setOnLongClickListener(null);
            } catch (Throwable e2222) {
                c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
                throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e2222));
            } catch (Throwable th5) {
            }
            try {
                view.setOnClickListener(null);
            } catch (Throwable e22222) {
                c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
                throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e22222));
            } catch (Throwable th6) {
            }
            try {
                view.setOnTouchListener(null);
            } catch (Throwable e222222) {
                c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
                throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e222222));
            } catch (Throwable th7) {
            }
            Drawable background = view.getBackground();
            if (background != null) {
                background.setCallback(null);
                view.setBackgroundDrawable(null);
            }
            view.destroyDrawingCache();
        }
    }

    private static void b(TextView textView) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setCallback(null);
            }
        }
        textView.setCompoundDrawables(null, null, null, null);
        if (textView instanceof EditText) {
            a(textView);
        }
    }

    private static void a(ProgressBar progressBar) {
        Drawable progressDrawable = progressBar.getProgressDrawable();
        if (progressDrawable != null) {
            progressBar.setProgressDrawable(null);
            progressDrawable.setCallback(null);
        }
        progressDrawable = progressBar.getIndeterminateDrawable();
        if (progressDrawable != null) {
            progressBar.setIndeterminateDrawable(null);
            progressDrawable.setCallback(null);
        }
    }

    private static void a(ListView listView) {
        Drawable selector = listView.getSelector();
        if (selector != null) {
            selector.setCallback(null);
        }
        try {
            if (listView.getAdapter() != null) {
                listView.setAdapter(null);
            }
        } catch (Throwable e) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e));
        } catch (Throwable th) {
        }
        try {
            listView.setOnScrollListener(null);
        } catch (Throwable e2) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e2));
        } catch (Throwable th2) {
        }
        try {
            listView.setOnItemClickListener(null);
        } catch (Throwable e22) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e22));
        } catch (Throwable th3) {
        }
        try {
            listView.setOnItemLongClickListener(null);
        } catch (Throwable e222) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e222));
        } catch (Throwable th4) {
        }
        try {
            listView.setOnItemSelectedListener(null);
        } catch (Throwable e2222) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e2222));
        } catch (Throwable th5) {
        }
    }

    private static void a(android.widget.ListView listView) {
        Drawable selector = listView.getSelector();
        if (selector != null) {
            selector.setCallback(null);
        }
        try {
            if (listView.getAdapter() != null) {
                listView.setAdapter(null);
            }
        } catch (Throwable e) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e));
        } catch (Throwable th) {
        }
        try {
            listView.setOnScrollListener(null);
        } catch (Throwable e2) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e2));
        } catch (Throwable th2) {
        }
        try {
            listView.setOnItemClickListener(null);
        } catch (Throwable e22) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e22));
        } catch (Throwable th3) {
        }
        try {
            listView.setOnItemLongClickListener(null);
        } catch (Throwable e222) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e222));
        } catch (Throwable th4) {
        }
        try {
            listView.setOnItemSelectedListener(null);
        } catch (Throwable e2222) {
            c.e("ActivityLeakSolution", "May cause dvmFindCatchBlock crash!");
            throw ((IncompatibleClassChangeError) new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(e2222));
        } catch (Throwable th5) {
        }
    }

    private static void a(FrameLayout frameLayout) {
        if (frameLayout != null) {
            Drawable foreground = frameLayout.getForeground();
            if (foreground != null) {
                foreground.setCallback(null);
                frameLayout.setForeground(null);
            }
        }
    }

    @TargetApi(16)
    private static void a(LinearLayout linearLayout) {
        if (linearLayout != null && 11 <= VERSION.SDK_INT) {
            Drawable dividerDrawable;
            if (16 <= VERSION.SDK_INT) {
                dividerDrawable = linearLayout.getDividerDrawable();
            } else {
                try {
                    Field declaredField = linearLayout.getClass().getDeclaredField("mDivider");
                    declaredField.setAccessible(true);
                    dividerDrawable = (Drawable) declaredField.get(linearLayout);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    dividerDrawable = null;
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                    dividerDrawable = null;
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                    dividerDrawable = null;
                }
            }
            if (dividerDrawable != null) {
                dividerDrawable.setCallback(null);
                linearLayout.setDividerDrawable(null);
            }
        }
    }

    private static void a(Activity activity, ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            a(activity, viewGroup.getChildAt(i));
        }
    }

    private static void a(Activity activity, View view) {
        if (view != null) {
            a(view);
            if (view instanceof ImageView) {
                a(activity, (ImageView) view);
            } else if (view instanceof TextView) {
                b((TextView) view);
            } else if (view instanceof ProgressBar) {
                a((ProgressBar) view);
            } else {
                if (view instanceof android.widget.ListView) {
                    a((android.widget.ListView) view);
                } else if (view instanceof ListView) {
                    a((ListView) view);
                } else if (view instanceof FrameLayout) {
                    a((FrameLayout) view);
                } else if (view instanceof LinearLayout) {
                    a((LinearLayout) view);
                }
                if (view instanceof ViewGroup) {
                    a(activity, (ViewGroup) view);
                }
            }
        }
    }

    private static void a(Activity activity, ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
            }
            imageView.setImageDrawable(null);
        }
    }

    public static void a(ImageView imageView) {
        if (imageView != null) {
            imageView.getDrawable().setCallback(null);
            imageView.setImageDrawable(null);
        }
    }

    public static void a(WebView webView) {
        if (webView != null) {
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }
            try {
                webView.stopLoading();
                webView.getSettings().setJavaScriptEnabled(false);
                webView.clearHistory();
                webView.clearView();
                webView.removeAllViews();
                webView.destroy();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
