package com.tencent.av.opengl.ui;

import android.graphics.Rect;
import android.os.SystemClock;
import android.view.MotionEvent;
import com.tencent.av.opengl.glrenderer.GLCanvas;
import com.tencent.av.opengl.ui.GLView.OnZOrderChangedListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GLViewGroup extends GLView {
    private static final String TAG = "GLViewGroup";
    protected final Rect mBounds = new Rect();
    private ArrayList<GLView> mChildren;
    private GLView mMotionTarget;
    private OnZOrderChangedListener mOnZOrderChangedListener = new 1(this);
    protected final Rect mPaddings = new Rect();
    private Comparator<GLView> mSortComparator = new SortComparator(this);

    public int getChildCount() {
        return this.mChildren == null ? 0 : this.mChildren.size();
    }

    public GLView getChild(int i) throws ArrayIndexOutOfBoundsException {
        if (this.mChildren != null && i >= 0 && i < this.mChildren.size()) {
            return (GLView) this.mChildren.get(i);
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public void addView(GLView gLView) {
        if (gLView.mParent != null) {
            throw new IllegalStateException();
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList();
        }
        this.mChildren.add(gLView);
        gLView.mParent = this;
        gLView.setOnZOrderChangedListener(this.mOnZOrderChangedListener);
        Collections.sort(this.mChildren, this.mSortComparator);
        if (this.mRootView != null) {
            gLView.onAttachToRoot(this.mRootView);
        }
    }

    public boolean removeView(GLView gLView) {
        if (this.mChildren == null || !this.mChildren.remove(gLView)) {
            return false;
        }
        if (this.mMotionTarget == gLView) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            dispatchTouchEvent(obtain);
            obtain.recycle();
        }
        gLView.onDetachFromRoot();
        gLView.mParent = null;
        gLView.setOnZOrderChangedListener(null);
        Collections.sort(this.mChildren, this.mSortComparator);
        return true;
    }

    public void removeAllView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            GLView gLView = (GLView) this.mChildren.get(i);
            if (this.mMotionTarget == gLView) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                dispatchTouchEvent(obtain);
                obtain.recycle();
            }
            gLView.onDetachFromRoot();
            gLView.mParent = null;
            gLView.setOnZOrderChangedListener(null);
        }
        this.mChildren.clear();
    }

    protected void render(GLCanvas gLCanvas) {
        renderBackground(gLCanvas);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            try {
                renderChild(gLCanvas, getChild(i));
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    protected void renderChild(GLCanvas gLCanvas, GLView gLView) {
        if (gLView.getVisibility() == 0 || gLView.mAnimation != null) {
            int i = gLView.mBounds.left - this.mScrollX;
            int i2 = gLView.mBounds.top - this.mScrollY;
            gLCanvas.translate((float) i, (float) i2);
            gLView.render(gLCanvas);
            gLCanvas.translate((float) (-i), (float) (-i2));
        }
    }

    protected boolean dispatchTouchEvent(MotionEvent motionEvent, int i, int i2, GLView gLView, boolean z) {
        Rect rect = gLView.mBounds;
        if (!z || rect.contains(i, i2)) {
            if (gLView.dispatchTouchEvent(motionEvent)) {
                return true;
            }
            if (this.mOnTouchListener != null && this.mOnTouchListener.onTouch(gLView, motionEvent)) {
                return true;
            }
        }
        return false;
    }

    protected boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (this.mMotionTarget != null) {
            if (action == 0) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction(3);
                dispatchTouchEvent(obtain, x, y, this.mMotionTarget, false);
                this.mMotionTarget = null;
            } else {
                dispatchTouchEvent(motionEvent, x, y, this.mMotionTarget, false);
                if (action == 3 || action == 1) {
                    this.mMotionTarget = null;
                }
                return true;
            }
        }
        if (action == 0) {
            for (action = getChildCount() - 1; action >= 0; action--) {
                try {
                    GLView child = getChild(action);
                    if (child.getVisibility() != 0) {
                        continue;
                    } else if (dispatchTouchEvent(motionEvent, x, y, child, true)) {
                        this.mMotionTarget = child;
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    protected void onVisibilityChanged(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            try {
                GLView child = getChild(i2);
                if (child.getVisibility() == 0) {
                    child.onVisibilityChanged(i);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    protected void onAttachToRoot(GLRootView gLRootView) {
        this.mRootView = gLRootView;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            try {
                getChild(i).onAttachToRoot(gLRootView);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    protected void onDetachFromRoot() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            try {
                getChild(i).onDetachFromRoot();
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        this.mRootView = null;
    }
}
