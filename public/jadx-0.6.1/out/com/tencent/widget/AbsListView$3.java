package com.tencent.widget;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputMethodManager;

class AbsListView$3 extends InputConnectionWrapper {
    final /* synthetic */ AbsListView this$0;

    AbsListView$3(AbsListView absListView, InputConnection inputConnection, boolean z) {
        this.this$0 = absListView;
        super(inputConnection, z);
    }

    public boolean reportFullscreenMode(boolean z) {
        return AbsListView.access$3500(this.this$0).reportFullscreenMode(z);
    }

    public boolean performEditorAction(int i) {
        if (i != 6) {
            return false;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) this.this$0.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.this$0.getWindowToken(), 0);
        }
        return true;
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        return AbsListView.access$3500(this.this$0).sendKeyEvent(keyEvent);
    }
}
