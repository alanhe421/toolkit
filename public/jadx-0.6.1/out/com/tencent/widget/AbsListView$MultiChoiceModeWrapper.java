package com.tencent.widget;

import android.annotation.SuppressLint;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint({"NewApi"})
class AbsListView$MultiChoiceModeWrapper implements AbsListView$MultiChoiceModeListener {
    private AbsListView$MultiChoiceModeListener mWrapped;
    final /* synthetic */ AbsListView this$0;

    AbsListView$MultiChoiceModeWrapper(AbsListView absListView) {
        this.this$0 = absListView;
    }

    public void setWrapped(AbsListView$MultiChoiceModeListener absListView$MultiChoiceModeListener) {
        this.mWrapped = absListView$MultiChoiceModeListener;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (!this.mWrapped.onCreateActionMode(actionMode, menu)) {
            return false;
        }
        this.this$0.setLongClickable(false);
        return true;
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return this.mWrapped.onPrepareActionMode(actionMode, menu);
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.mWrapped.onActionItemClicked(actionMode, menuItem);
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        this.mWrapped.onDestroyActionMode(actionMode);
        this.this$0.mChoiceActionMode = null;
        this.this$0.clearChoices();
        this.this$0.mDataChanged = true;
        this.this$0.rememberSyncState();
        this.this$0.requestLayout();
        this.this$0.setLongClickable(true);
    }

    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
        this.mWrapped.onItemCheckedStateChanged(actionMode, i, j, z);
        if (this.this$0.getCheckedItemCount() == 0) {
            actionMode.finish();
        }
    }
}
