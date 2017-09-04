package com.tencent.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.RemoteViews.RemoteView;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.WebView;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.util.VersionUtils;
import com.tencent.widget.AbsListView.AdapterDataSetObserver;
import com.tencent.widget.AbsListView.LayoutParams;
import com.tencent.widget.AbsListView.RecycleBin;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

@RemoteView
public class ListView extends AbsListView {
    private static final int ANIMATION_DURATION = 350;
    private static final int ANIMATION_TAG = 2131230829;
    private static final int LISTVIEW_DIVIDER = AdapterView.getStyleableValue("ListView_divider");
    private static final int LISTVIEW_DIVIDERHEIGHT = AdapterView.getStyleableValue("ListView_dividerHeight");
    private static final int LISTVIEW_ENTRIES = AdapterView.getStyleableValue("ListView_entries");
    private static final int LISTVIEW_FOOTERDIVIDERSENABLED = AdapterView.getStyleableValue("ListView_footerDividersEnabled");
    private static final int LISTVIEW_HEADERDIVIDERSENABLED = AdapterView.getStyleableValue("ListView_headerDividersEnabled");
    private static final int LISTVIEW_OVERSCROLLFOOTER = AdapterView.getStyleableValue("ListView_overScrollFooter");
    private static final int LISTVIEW_OVERSCROLLHEADER = AdapterView.getStyleableValue("ListView_overScrollHeader");
    private static final float MAX_SCROLL_FACTOR = 0.33f;
    private static final int MIN_SCROLL_PREVIEW_PIXELS = 2;
    static final int NO_POSITION = -1;
    public static final int OVERSCROLL_STATUS_COMPLETE_RELEASE = 3;
    public static final int OVERSCROLL_STATUS_COMPLETE_VISABLE = 2;
    public static final int OVERSCROLL_STATUS_IDLE = 0;
    public static final int OVERSCROLL_STATUS_VISABLE = 1;
    private long delAnimDuration;
    private boolean isTouchHolding;
    private int[] mAddingRows;
    private boolean mAnimation;
    private boolean mAreAllItemsSelectable;
    private final ArrowScrollFocusResult mArrowScrollFocusResult;
    private Drawable mContentBackgroundDrawable;
    Drawable mDivider;
    int mDividerHeight;
    private boolean mDividerIsOpaque;
    private Paint mDividerPaint;
    protected boolean mEnsureOverScrollStatusToIdleWhenRelease;
    private FocusSelector mFocusSelector;
    private boolean mFooterDividersEnabled;
    private ArrayList<FixedViewInfo> mFooterViewInfos;
    private boolean mHeaderDividersEnabled;
    private ArrayList<FixedViewInfo> mHeaderViewInfos;
    private Animation mInsertAnimation;
    private boolean mIsCacheColorOpaque;
    private boolean mItemsCanFocus;
    Drawable mOverScrollFooter;
    Drawable mOverScrollHeader;
    private Drawable mOverScrollHeaderShadow;
    protected int mOverScrollHeight;
    private OverScrollViewListener mOverScrollViewListener;
    private OverscrollViewContainer mOverscrollFooterView;
    protected int mOverscrollHeadState;
    private OverscrollViewContainer mOverscrollHeaderView;
    private final Rect mTempRect;

    public ListView(Context context) {
        this(context, null);
    }

    public ListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEnsureOverScrollStatusToIdleWhenRelease = false;
        this.mHeaderViewInfos = new ArrayList();
        this.mFooterViewInfos = new ArrayList();
        this.mAreAllItemsSelectable = true;
        this.mItemsCanFocus = false;
        this.mTempRect = new Rect();
        this.mArrowScrollFocusResult = new ArrowScrollFocusResult(null);
        this.mOverscrollHeadState = 0;
        this.mAddingRows = null;
        this.mOverScrollHeight = 0;
        this.isTouchHolding = false;
        this.mAnimation = true;
        TypedArrayWarpper typedArrayWarpper = new TypedArrayWarpper(context.obtainStyledAttributes(attributeSet, AdapterView.getStyleableValues("ListView"), i, 0));
        CharSequence[] textArray = typedArrayWarpper.getTextArray(LISTVIEW_ENTRIES);
        if (textArray != null) {
            setAdapter(new ArrayAdapter(context, 17367043, textArray));
        }
        Drawable drawable = typedArrayWarpper.getDrawable(LISTVIEW_DIVIDER);
        if (drawable != null) {
            setDivider(drawable);
        }
        drawable = typedArrayWarpper.getDrawable(LISTVIEW_OVERSCROLLHEADER);
        if (drawable != null) {
            setOverscrollHeader(drawable);
        }
        drawable = typedArrayWarpper.getDrawable(LISTVIEW_OVERSCROLLFOOTER);
        if (drawable != null) {
            setOverscrollFooter(drawable);
        }
        int dimensionPixelSize = typedArrayWarpper.getDimensionPixelSize(LISTVIEW_DIVIDERHEIGHT, 0);
        if (dimensionPixelSize != 0) {
            setDividerHeight(dimensionPixelSize);
        }
        this.mHeaderDividersEnabled = typedArrayWarpper.getBoolean(LISTVIEW_HEADERDIVIDERSENABLED, true);
        this.mFooterDividersEnabled = typedArrayWarpper.getBoolean(LISTVIEW_FOOTERDIVIDERSENABLED, true);
        typedArrayWarpper.recycle();
    }

    public void setOverScrollHeight(int i) {
        this.mOverScrollHeight = i;
    }

    public int getOverScrollHeight() {
        if (this.mOverScrollHeight != 0 || this.mOverscrollHeaderView == null) {
            return this.mOverScrollHeight;
        }
        return this.mOverscrollHeaderView.getHeight();
    }

    public int getOverScrollFooterHeight() {
        if (this.mOverscrollFooterView == null) {
            return 0;
        }
        return this.mOverscrollFooterView.getHeight();
    }

    public int getMaxScrollAmount() {
        return (int) (MAX_SCROLL_FACTOR * ((float) (getBottom() - this.mTop)));
    }

    private void stayOnTheTop() {
        if (this.mStackFromBottom && this.mFirstPosition == 0 && getChildCount() > 0) {
            int top = getChildAt(0).getTop() - this.mListPadding.top;
            if (top > 0) {
                offsetChildrenTopAndBottom(-top);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustViewsUpOrDown() {
        /*
        r5 = this;
        r0 = 0;
        r2 = r5.getChildCount();
        if (r2 <= 0) goto L_0x0027;
    L_0x0007:
        r1 = r5.mStackFromBottom;
        if (r1 != 0) goto L_0x0028;
    L_0x000b:
        r1 = r5.getChildAt(r0);
        r1 = r1.getTop();
        r2 = r5.mListPadding;
        r2 = r2.top;
        r1 = r1 - r2;
        r2 = r5.mFirstPosition;
        if (r2 == 0) goto L_0x001f;
    L_0x001c:
        r2 = r5.mDividerHeight;
        r1 = r1 - r2;
    L_0x001f:
        if (r1 >= 0) goto L_0x0048;
    L_0x0021:
        if (r0 == 0) goto L_0x0027;
    L_0x0023:
        r0 = -r0;
        r5.offsetChildrenTopAndBottom(r0);
    L_0x0027:
        return;
    L_0x0028:
        r1 = r2 + -1;
        r1 = r5.getChildAt(r1);
        r1 = r1.getBottom();
        r3 = r5.getHeight();
        r4 = r5.mListPadding;
        r4 = r4.bottom;
        r3 = r3 - r4;
        r1 = r1 - r3;
        r3 = r5.mFirstPosition;
        r2 = r2 + r3;
        r3 = r5.mItemCount;
        if (r2 >= r3) goto L_0x0046;
    L_0x0043:
        r2 = r5.mDividerHeight;
        r1 = r1 + r2;
    L_0x0046:
        if (r1 > 0) goto L_0x0021;
    L_0x0048:
        r0 = r1;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.widget.ListView.adjustViewsUpOrDown():void");
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        if (this.mAdapter == null || (this.mAdapter instanceof HeaderViewListAdapter)) {
            FixedViewInfo fixedViewInfo = new FixedViewInfo(this);
            fixedViewInfo.view = view;
            fixedViewInfo.data = obj;
            fixedViewInfo.isSelectable = z;
            this.mHeaderViewInfos.add(fixedViewInfo);
            if (this.mAdapter != null && this.mDataSetObserver != null) {
                this.mDataSetObserver.onChanged();
                return;
            }
            return;
        }
        throw new IllegalStateException("Cannot add header view to list -- setAdapter has already been called.");
    }

    public void addHeaderView(View view) {
        addHeaderView(view, null, true);
    }

    public int getHeaderViewsCount() {
        return this.mHeaderViewInfos.size();
    }

    public boolean removeHeaderView(View view) {
        if (this.mHeaderViewInfos.size() <= 0) {
            return false;
        }
        boolean z;
        if (this.mAdapter == null || !((HeaderViewListAdapter) this.mAdapter).removeHeader(view)) {
            z = false;
        } else {
            if (this.mDataSetObserver != null) {
                this.mDataSetObserver.onChanged();
            }
            z = true;
        }
        removeFixedViewInfo(view, this.mHeaderViewInfos);
        return z;
    }

    private void removeFixedViewInfo(View view, ArrayList<FixedViewInfo> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((FixedViewInfo) arrayList.get(i)).view == view) {
                arrayList.remove(i);
                return;
            }
        }
    }

    public void addFooterView(View view, Object obj, boolean z) {
        FixedViewInfo fixedViewInfo = new FixedViewInfo(this);
        fixedViewInfo.view = view;
        fixedViewInfo.data = obj;
        fixedViewInfo.isSelectable = z;
        this.mFooterViewInfos.add(fixedViewInfo);
        if (this.mAdapter != null && this.mDataSetObserver != null) {
            this.mDataSetObserver.onChanged();
        }
    }

    public void addFooterView(View view) {
        addFooterView(view, null, true);
    }

    public int getFooterViewsCount() {
        return this.mFooterViewInfos.size();
    }

    public boolean removeFooterView(View view) {
        if (this.mFooterViewInfos.size() <= 0) {
            return false;
        }
        boolean z;
        if (this.mAdapter == null || !((HeaderViewListAdapter) this.mAdapter).removeFooter(view)) {
            z = false;
        } else {
            if (this.mDataSetObserver != null) {
                this.mDataSetObserver.onChanged();
            }
            z = true;
        }
        removeFixedViewInfo(view, this.mFooterViewInfos);
        return z;
    }

    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!(this.mAdapter == null || this.mDataSetObserver == null)) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        }
        resetList();
        this.mRecycler.clear();
        if (this.mHeaderViewInfos.size() > 0 || this.mFooterViewInfos.size() > 0) {
            this.mAdapter = new HeaderViewListAdapter(this.mHeaderViewInfos, this.mFooterViewInfos, listAdapter);
        } else {
            this.mAdapter = listAdapter;
        }
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        super.setAdapter(listAdapter);
        if (this.mAdapter != null) {
            int lookForSelectablePosition;
            this.mAreAllItemsSelectable = this.mAdapter.areAllItemsEnabled();
            this.mOldItemCount = this.mItemCount;
            this.mItemCount = this.mAdapter.getCount();
            checkFocus();
            this.mDataSetObserver = new ListDataSetObserver(this);
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
            this.mRecycler.setViewTypeCount(this.mAdapter.getViewTypeCount());
            if (this.mStackFromBottom) {
                lookForSelectablePosition = lookForSelectablePosition(this.mItemCount - 1, false);
            } else {
                lookForSelectablePosition = lookForSelectablePosition(0, true);
            }
            setSelectedPositionInt(lookForSelectablePosition);
            setNextSelectedPositionInt(lookForSelectablePosition);
            if (this.mItemCount == 0) {
                checkSelectionChanged();
            }
        } else {
            this.mAreAllItemsSelectable = true;
            checkFocus();
            checkSelectionChanged();
        }
        requestLayout();
    }

    void resetList() {
        clearRecycledState(this.mHeaderViewInfos);
        clearRecycledState(this.mFooterViewInfos);
        super.resetList();
        this.mLayoutMode = 0;
    }

    private void clearRecycledState(ArrayList<FixedViewInfo> arrayList) {
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                LayoutParams layoutParams = (LayoutParams) ((FixedViewInfo) arrayList.get(i)).view.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.recycledHeaderFooter = false;
                }
            }
        }
    }

    private boolean showingTopFadingEdge() {
        int i = this.mScrollY + this.mListPadding.top;
        if (this.mFirstPosition > 0 || getChildAt(0).getTop() > i) {
            return true;
        }
        return false;
    }

    private boolean showingBottomFadingEdge() {
        int childCount = getChildCount();
        return (childCount + this.mFirstPosition) + -1 < this.mItemCount + -1 || getChildAt(childCount - 1).getBottom() < (getScrollY() + getHeight()) - this.mListPadding.bottom;
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        boolean z2;
        int i = rect.top;
        rect.offset(view.getLeft(), view.getTop());
        rect.offset(-view.getScrollX(), -view.getScrollY());
        int height = getHeight();
        int scrollY = getScrollY();
        int i2 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (showingTopFadingEdge() && (this.mSelectedPosition > 0 || i > verticalFadingEdgeLength)) {
            scrollY += verticalFadingEdgeLength;
        }
        i = getChildAt(getChildCount() - 1).getBottom();
        if (showingBottomFadingEdge() && (this.mSelectedPosition < this.mItemCount - 1 || rect.bottom < i - verticalFadingEdgeLength)) {
            i2 -= verticalFadingEdgeLength;
        }
        if (rect.bottom > i2 && rect.top > scrollY) {
            if (rect.height() > height) {
                scrollY = (rect.top - scrollY) + 0;
            } else {
                scrollY = (rect.bottom - i2) + 0;
            }
            i2 = Math.min(scrollY, i - i2);
        } else if (rect.top >= scrollY || rect.bottom >= i2) {
            i2 = 0;
        } else {
            if (rect.height() > height) {
                i2 = 0 - (i2 - rect.bottom);
            } else {
                i2 = 0 - (scrollY - rect.top);
            }
            i2 = Math.max(i2, getChildAt(0).getTop() - scrollY);
        }
        if (i2 != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            scrollListItemsBy(-i2);
            positionSelector(-1, view);
            this.mSelectedTop = view.getTop();
            invalidate();
        }
        return z2;
    }

    void fillGap(boolean z) {
        int childCount = getChildCount();
        int listPaddingTop;
        if (z) {
            if ((this.mGroupFlags & 34) == 34) {
                listPaddingTop = getListPaddingTop();
            } else {
                listPaddingTop = 0;
            }
            if (childCount > 0) {
                listPaddingTop = getChildAt(childCount - 1).getBottom() + this.mDividerHeight;
            }
            fillDown(this.mFirstPosition + childCount, listPaddingTop);
            correctTooHigh(getChildCount());
            return;
        }
        if ((this.mGroupFlags & 34) == 34) {
            listPaddingTop = getListPaddingBottom();
        } else {
            listPaddingTop = 0;
        }
        fillUp(this.mFirstPosition - 1, childCount > 0 ? getChildAt(0).getTop() - this.mDividerHeight : getHeight() - listPaddingTop);
        correctTooLow(getChildCount());
    }

    private View fillDown(int i, int i2) {
        int i3;
        int i4;
        int i5;
        View view = null;
        int i6 = this.mBottom - this.mTop;
        if ((this.mGroupFlags & 34) == 34) {
            i3 = i6 - this.mListPadding.bottom;
            i4 = i2;
            i5 = i;
        } else {
            i3 = i6;
            i4 = i2;
            i5 = i;
        }
        while (i4 < i3 && i5 < this.mItemCount) {
            boolean z = i5 == this.mSelectedPosition;
            View makeAndAddView = makeAndAddView(i5, i4, true, this.mListPadding.left, z);
            i2 = makeAndAddView.getBottom() + this.mDividerHeight;
            if (!z) {
                makeAndAddView = view;
            }
            view = makeAndAddView;
            i4 = i2;
            i5++;
        }
        return view;
    }

    private View fillUp(int i, int i2) {
        int i3;
        int i4;
        View view = null;
        if ((this.mGroupFlags & 34) == 34) {
            int i5 = this.mListPadding.top;
            i3 = i2;
            i4 = i;
        } else {
            boolean z = false;
            i3 = i2;
            i4 = i;
        }
        while (i3 > i5 && i4 >= 0) {
            boolean z2 = i4 == this.mSelectedPosition;
            View makeAndAddView = makeAndAddView(i4, i3, false, this.mListPadding.left, z2);
            i2 = makeAndAddView.getTop() - this.mDividerHeight;
            if (!z2) {
                makeAndAddView = view;
            }
            view = makeAndAddView;
            i3 = i2;
            i4--;
        }
        this.mFirstPosition = i4 + 1;
        return view;
    }

    private View fillFromTop(int i) {
        this.mFirstPosition = Math.min(this.mFirstPosition, this.mSelectedPosition);
        this.mFirstPosition = Math.min(this.mFirstPosition, this.mItemCount - 1);
        if (this.mFirstPosition < 0) {
            this.mFirstPosition = 0;
        }
        return fillDown(this.mFirstPosition, i);
    }

    private View fillFromMiddle(int i, int i2) {
        int i3 = i2 - i;
        int reconcileSelectedPosition = reconcileSelectedPosition();
        View makeAndAddView = makeAndAddView(reconcileSelectedPosition, i, true, this.mListPadding.left, true);
        this.mFirstPosition = reconcileSelectedPosition;
        int measuredHeight = makeAndAddView.getMeasuredHeight();
        if (measuredHeight <= i3) {
            makeAndAddView.offsetTopAndBottom((i3 - measuredHeight) / 2);
        }
        fillAboveAndBelow(makeAndAddView, reconcileSelectedPosition);
        if (this.mStackFromBottom) {
            correctTooLow(getChildCount());
        } else {
            correctTooHigh(getChildCount());
        }
        return makeAndAddView;
    }

    private void fillAboveAndBelow(View view, int i) {
        int i2 = this.mDividerHeight;
        if (this.mStackFromBottom) {
            fillDown(i + 1, view.getBottom() + i2);
            adjustViewsUpOrDown();
            fillUp(i - 1, view.getTop() - i2);
            return;
        }
        fillUp(i - 1, view.getTop() - i2);
        adjustViewsUpOrDown();
        fillDown(i + 1, i2 + view.getBottom());
    }

    private View fillFromSelection(int i, int i2, int i3) {
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int i4 = this.mSelectedPosition;
        int topSelectionPixel = getTopSelectionPixel(i2, verticalFadingEdgeLength, i4);
        int bottomSelectionPixel = getBottomSelectionPixel(i3, verticalFadingEdgeLength, i4);
        View makeAndAddView = makeAndAddView(i4, i, true, this.mListPadding.left, true);
        if (makeAndAddView.getBottom() > bottomSelectionPixel) {
            makeAndAddView.offsetTopAndBottom(-Math.min(makeAndAddView.getTop() - topSelectionPixel, makeAndAddView.getBottom() - bottomSelectionPixel));
        } else if (makeAndAddView.getTop() < topSelectionPixel) {
            makeAndAddView.offsetTopAndBottom(Math.min(topSelectionPixel - makeAndAddView.getTop(), bottomSelectionPixel - makeAndAddView.getBottom()));
        }
        fillAboveAndBelow(makeAndAddView, i4);
        if (this.mStackFromBottom) {
            correctTooLow(getChildCount());
        } else {
            correctTooHigh(getChildCount());
        }
        return makeAndAddView;
    }

    private int getBottomSelectionPixel(int i, int i2, int i3) {
        if (i3 != this.mItemCount - 1) {
            return i - i2;
        }
        return i;
    }

    private int getTopSelectionPixel(int i, int i2, int i3) {
        if (i3 > 0) {
            return i + i2;
        }
        return i;
    }

    public void smoothScrollToPosition(int i) {
        super.smoothScrollToPosition(i);
    }

    public void smoothScrollByOffset(int i) {
        super.smoothScrollByOffset(i);
    }

    private View moveSelection(View view, View view2, int i, int i2, int i3) {
        View makeAndAddView;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int i4 = this.mSelectedPosition;
        int topSelectionPixel = getTopSelectionPixel(i2, verticalFadingEdgeLength, i4);
        int bottomSelectionPixel = getBottomSelectionPixel(i2, verticalFadingEdgeLength, i4);
        if (i > 0) {
            View makeAndAddView2 = makeAndAddView(i4 - 1, view.getTop(), true, this.mListPadding.left, false);
            int i5 = this.mDividerHeight;
            makeAndAddView = makeAndAddView(i4, makeAndAddView2.getBottom() + i5, true, this.mListPadding.left, true);
            if (makeAndAddView.getBottom() > bottomSelectionPixel) {
                int min = Math.min(Math.min(makeAndAddView.getTop() - topSelectionPixel, makeAndAddView.getBottom() - bottomSelectionPixel), (i3 - i2) / 2);
                makeAndAddView2.offsetTopAndBottom(-min);
                makeAndAddView.offsetTopAndBottom(-min);
            }
            if (this.mStackFromBottom) {
                fillDown(this.mSelectedPosition + 1, makeAndAddView.getBottom() + i5);
                adjustViewsUpOrDown();
                fillUp(this.mSelectedPosition - 2, makeAndAddView.getTop() - i5);
            } else {
                fillUp(this.mSelectedPosition - 2, makeAndAddView.getTop() - i5);
                adjustViewsUpOrDown();
                fillDown(this.mSelectedPosition + 1, makeAndAddView.getBottom() + i5);
            }
        } else if (i < 0) {
            if (view2 != null) {
                makeAndAddView = makeAndAddView(i4, view2.getTop(), true, this.mListPadding.left, true);
            } else {
                makeAndAddView = makeAndAddView(i4, view.getTop(), false, this.mListPadding.left, true);
            }
            if (makeAndAddView.getTop() < topSelectionPixel) {
                makeAndAddView.offsetTopAndBottom(Math.min(Math.min(topSelectionPixel - makeAndAddView.getTop(), bottomSelectionPixel - makeAndAddView.getBottom()), (i3 - i2) / 2));
            }
            fillAboveAndBelow(makeAndAddView, i4);
        } else {
            int top = view.getTop();
            makeAndAddView = makeAndAddView(i4, top, true, this.mListPadding.left, true);
            if (top < i2 && makeAndAddView.getBottom() < i2 + 20) {
                makeAndAddView.offsetTopAndBottom(i2 - makeAndAddView.getTop());
            }
            fillAboveAndBelow(makeAndAddView, i4);
        }
        return makeAndAddView;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (getChildCount() > 0) {
            View focusedChild = getFocusedChild();
            if (focusedChild != null) {
                int indexOfChild = this.mFirstPosition + indexOfChild(focusedChild);
                int top = focusedChild.getTop() - Math.max(0, focusedChild.getBottom() - (i2 - this.mPaddingTop));
                if (this.mFocusSelector == null) {
                    this.mFocusSelector = new FocusSelector(this, null);
                }
                post(this.mFocusSelector.setup(indexOfChild, top));
            }
            clearDelAnim();
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    @TargetApi(11)
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int measuredHeight;
        super.onMeasure(i, i2);
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.mAdapter == null) {
            i3 = 0;
        } else {
            i3 = this.mAdapter.getCount();
        }
        this.mItemCount = i3;
        if (this.mItemCount <= 0 || !(mode == 0 || mode2 == 0)) {
            i4 = 0;
            i5 = 0;
            i3 = 0;
        } else {
            View obtainView = obtainView(0, this.mIsScrap);
            measureScrapChild(obtainView, 0, i);
            i5 = obtainView.getMeasuredWidth();
            measuredHeight = obtainView.getMeasuredHeight();
            if (VersionUtils.isHoneycomb()) {
                i4 = combineMeasuredStates(0, obtainView.getMeasuredState());
            } else {
                i4 = 0;
            }
            if (recycleOnMeasure() && this.mRecycler.shouldRecycleViewType(((LayoutParams) obtainView.getLayoutParams()).viewType)) {
                this.mRecycler.addScrapView(obtainView, -1);
            }
            i3 = i5;
            i5 = measuredHeight;
        }
        measuredHeight = mode == 0 ? (i3 + (this.mListPadding.left + this.mListPadding.right)) + getVerticalScrollbarWidth() : VersionUtils.isHoneycomb() ? (WebView.NIGHT_MODE_COLOR & i4) | size : size;
        if (mode2 == 0) {
            size2 = ((this.mListPadding.top + this.mListPadding.bottom) + i5) + (getVerticalFadingEdgeLength() * 2);
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = measureHeightOfChildren(i, 0, -1, size2, -1);
        }
        setMeasuredDimension(measuredHeight, size2);
        this.mWidthMeasureSpec = i;
        if (this.mOverscrollHeaderView != null) {
            this.mOverscrollHeaderView.measure(ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mListPadding.left + this.mListPadding.right, -1), MeasureSpec.makeMeasureSpec(0, 0));
        }
        if (this.mOverscrollFooterView != null) {
            this.mOverscrollFooterView.measure(ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mListPadding.left + this.mListPadding.right, -1), MeasureSpec.makeMeasureSpec(0, 0));
        }
    }

    private void measureScrapChild(View view, int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2, 0);
            view.setLayoutParams(layoutParams);
        }
        layoutParams.viewType = this.mAdapter.getItemViewType(i);
        layoutParams.forceAdd = true;
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, this.mListPadding.left + this.mListPadding.right, layoutParams.width);
        int i3 = layoutParams.height;
        if (i3 > 0) {
            i3 = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        } else {
            i3 = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i3);
    }

    @ExportedProperty(category = "list")
    protected boolean recycleOnMeasure() {
        return true;
    }

    final int measureHeightOfChildren(int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null) {
            return this.mListPadding.top + this.mListPadding.bottom;
        }
        int i7 = this.mListPadding.top + this.mListPadding.bottom;
        int i8 = (this.mDividerHeight <= 0 || this.mDivider == null) ? 0 : this.mDividerHeight;
        if (i3 == -1) {
            i3 = listAdapter.getCount() - 1;
        }
        RecycleBin recycleBin = this.mRecycler;
        boolean recycleOnMeasure = recycleOnMeasure();
        boolean[] zArr = this.mIsScrap;
        while (i2 <= i3) {
            int i9;
            View obtainView = obtainView(i2, zArr);
            measureScrapChild(obtainView, i2, i);
            if (i2 > 0) {
                i9 = i7 + i8;
            } else {
                i9 = i7;
            }
            if (recycleOnMeasure && recycleBin.shouldRecycleViewType(((LayoutParams) obtainView.getLayoutParams()).viewType)) {
                recycleBin.addScrapView(obtainView, -1);
            }
            i7 = obtainView.getMeasuredHeight() + i9;
            if (i7 < i4) {
                if (i5 >= 0 && i2 >= i5) {
                    i6 = i7;
                }
                i2++;
            } else if (i5 < 0 || i2 <= i5 || i6 <= 0 || i7 == i4) {
                return i4;
            } else {
                return i6;
            }
        }
        return i7;
    }

    int findMotionRow(int i) {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i2;
            if (this.mStackFromBottom) {
                for (i2 = childCount - 1; i2 >= 0; i2--) {
                    if (i >= getChildAt(i2).getTop()) {
                        return i2 + this.mFirstPosition;
                    }
                }
            } else {
                for (i2 = 0; i2 < childCount; i2++) {
                    if (i <= getChildAt(i2).getBottom()) {
                        return i2 + this.mFirstPosition;
                    }
                }
            }
        }
        return -1;
    }

    private View fillSpecificBottom(int i, int i2) {
        View fillDown;
        View fillUp;
        boolean z = i == this.mSelectedPosition;
        View makeAndAddView = makeAndAddView(i, i2, false, this.mListPadding.left, z);
        this.mFirstPosition = i;
        int i3 = this.mDividerHeight;
        if (this.mStackFromBottom) {
            fillDown = fillDown(i + 1, makeAndAddView.getBottom() + i3);
            adjustViewsUpOrDown();
            fillUp = fillUp(i - 1, makeAndAddView.getTop() - i3);
            i3 = getChildCount();
            if (i3 > 0) {
                correctTooLow(i3);
            }
        } else {
            fillUp = fillUp(i - 1, makeAndAddView.getTop() - i3);
            adjustViewsUpOrDown();
            fillDown = fillDown(i + 1, i3 + makeAndAddView.getBottom());
            i3 = getChildCount();
            if (i3 > 0) {
                correctTooHigh(i3);
            }
        }
        if (z) {
            return makeAndAddView;
        }
        if (fillUp != null) {
            return fillUp;
        }
        return fillDown;
    }

    private View fillSpecific(int i, int i2) {
        View fillDown;
        View fillUp;
        boolean z = i == this.mSelectedPosition;
        View makeAndAddView = makeAndAddView(i, i2, true, this.mListPadding.left, z);
        this.mFirstPosition = i;
        int i3 = this.mDividerHeight;
        if (this.mStackFromBottom) {
            fillDown = fillDown(i + 1, makeAndAddView.getBottom() + i3);
            adjustViewsUpOrDown();
            fillUp = fillUp(i - 1, makeAndAddView.getTop() - i3);
            i3 = getChildCount();
            if (i3 > 0) {
                correctTooLow(i3);
            }
        } else {
            fillUp = fillUp(i - 1, makeAndAddView.getTop() - i3);
            adjustViewsUpOrDown();
            fillDown = fillDown(i + 1, i3 + makeAndAddView.getBottom());
            i3 = getChildCount();
            if (i3 > 0) {
                correctTooHigh(i3);
            }
        }
        if (z) {
            return makeAndAddView;
        }
        if (fillUp != null) {
            return fillUp;
        }
        return fillDown;
    }

    private void correctTooHigh(int i) {
        if ((this.mFirstPosition + i) - 1 == this.mItemCount - 1 && i > 0) {
            int bottom = ((getBottom() - this.mTop) - this.mListPadding.bottom) - getChildAt(i - 1).getBottom();
            View childAt = getChildAt(0);
            int top = childAt.getTop();
            if (bottom <= 0) {
                return;
            }
            if (this.mFirstPosition > 0 || top < this.mListPadding.top) {
                if (this.mFirstPosition == 0) {
                    bottom = Math.min(bottom, this.mListPadding.top - top);
                }
                offsetChildrenTopAndBottom(bottom);
                if (this.mFirstPosition > 0) {
                    fillUp(this.mFirstPosition - 1, childAt.getTop() - this.mDividerHeight);
                    adjustViewsUpOrDown();
                }
            }
        }
    }

    private void correctTooLow(int i) {
        if (this.mFirstPosition == 0 && i > 0) {
            int bottom = (getBottom() - this.mTop) - this.mListPadding.bottom;
            int top = getChildAt(0).getTop() - this.mListPadding.top;
            View childAt = getChildAt(i - 1);
            int bottom2 = childAt.getBottom();
            int i2 = (this.mFirstPosition + i) - 1;
            if (top <= 0) {
                return;
            }
            if (i2 < this.mItemCount - 1 || bottom2 > bottom) {
                if (i2 == this.mItemCount - 1) {
                    top = Math.min(top, bottom2 - bottom);
                }
                offsetChildrenTopAndBottom(-top);
                if (i2 < this.mItemCount - 1) {
                    fillDown(i2 + 1, childAt.getBottom() + this.mDividerHeight);
                    adjustViewsUpOrDown();
                }
            } else if (i2 == this.mItemCount - 1) {
                adjustViewsUpOrDown();
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mOverscrollHeaderView != null) {
            this.mOverscrollHeaderView.layout(this.mListPadding.left, this.mListPadding.top, this.mListPadding.left + this.mOverscrollHeaderView.getMeasuredWidth(), this.mOverscrollHeaderView.getMeasuredHeight());
            this.mTopOverflingDistance = this.mOverscrollHeaderView.getHeight();
        }
        if (this.mOverscrollFooterView != null) {
            this.mOverscrollFooterView.layout(this.mListPadding.left, this.mListPadding.top, this.mListPadding.left + this.mOverscrollFooterView.getMeasuredWidth(), this.mOverscrollFooterView.getMeasuredHeight());
            this.mBottomOverflingDistance = this.mOverscrollFooterView.getHeight();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void layoutChildren() {
        /*
        r19 = this;
        r0 = r19;
        r11 = r0.mBlockLayoutRequests;
        if (r11 != 0) goto L_0x002d;
    L_0x0006:
        r1 = 1;
        r0 = r19;
        r0.mBlockLayoutRequests = r1;
        r1 = "ListView.layoutChildren";
        com.tencent.widget.AdapterView.traceBegin(r1);
        super.layoutChildren();	 Catch:{ all -> 0x0143 }
        r19.invalidate();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mAdapter;	 Catch:{ all -> 0x0143 }
        if (r1 != 0) goto L_0x002e;
    L_0x001d:
        r19.resetList();	 Catch:{ all -> 0x0143 }
        r19.invokeOnItemScrollListener();	 Catch:{ all -> 0x0143 }
        if (r11 != 0) goto L_0x002a;
    L_0x0025:
        r1 = 0;
        r0 = r19;
        r0.mBlockLayoutRequests = r1;
    L_0x002a:
        com.tencent.widget.AdapterView.traceEnd();
    L_0x002d:
        return;
    L_0x002e:
        r0 = r19;
        r1 = r0.mListPadding;	 Catch:{ all -> 0x0143 }
        r5 = r1.top;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mBottom;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mTop;	 Catch:{ all -> 0x0143 }
        r1 = r1 - r2;
        r0 = r19;
        r2 = r0.mListPadding;	 Catch:{ all -> 0x0143 }
        r2 = r2.bottom;	 Catch:{ all -> 0x0143 }
        r6 = r1 - r2;
        r12 = r19.getChildCount();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        r1 = r1 + r12;
        r13 = r1 + -1;
        r8 = 0;
        r4 = 0;
        r2 = 0;
        r1 = 0;
        r3 = 0;
        r7 = 0;
        r0 = r19;
        r9 = r0.mLayoutMode;	 Catch:{ all -> 0x0143 }
        switch(r9) {
            case 1: goto L_0x00d3;
            case 2: goto L_0x00bb;
            case 3: goto L_0x00d3;
            case 4: goto L_0x00d3;
            case 5: goto L_0x00d3;
            default: goto L_0x005d;
        };	 Catch:{ all -> 0x0143 }
    L_0x005d:
        r0 = r19;
        r1 = r0.mSelectedPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        r3 = r1 - r2;
        if (r3 < 0) goto L_0x0071;
    L_0x0069:
        if (r3 >= r12) goto L_0x0071;
    L_0x006b:
        r0 = r19;
        r4 = r0.getChildAt(r3);	 Catch:{ all -> 0x0143 }
    L_0x0071:
        r1 = 0;
        r0 = r19;
        r2 = r0.getChildAt(r1);	 Catch:{ all -> 0x0143 }
        r1 = r12 + -1;
        r0 = r19;
        r1 = r0.getChildAt(r1);	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r9 = r0.mNextSelectedPosition;	 Catch:{ all -> 0x0143 }
        if (r9 < 0) goto L_0x008f;
    L_0x0086:
        r0 = r19;
        r8 = r0.mNextSelectedPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r9 = r0.mSelectedPosition;	 Catch:{ all -> 0x0143 }
        r8 = r8 - r9;
    L_0x008f:
        r3 = r3 + r8;
        r0 = r19;
        r3 = r0.getChildAt(r3);	 Catch:{ all -> 0x0143 }
        r9 = r1;
        r10 = r2;
        r2 = r4;
        r4 = r8;
    L_0x009a:
        r0 = r19;
        r14 = r0.mDataChanged;	 Catch:{ all -> 0x0143 }
        if (r14 == 0) goto L_0x00a3;
    L_0x00a0:
        r19.handleDataChanged();	 Catch:{ all -> 0x0143 }
    L_0x00a3:
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        if (r1 != 0) goto L_0x00d8;
    L_0x00a9:
        r19.resetList();	 Catch:{ all -> 0x0143 }
        r19.invokeOnItemScrollListener();	 Catch:{ all -> 0x0143 }
        if (r11 != 0) goto L_0x00b6;
    L_0x00b1:
        r1 = 0;
        r0 = r19;
        r0.mBlockLayoutRequests = r1;
    L_0x00b6:
        com.tencent.widget.AdapterView.traceEnd();
        goto L_0x002d;
    L_0x00bb:
        r0 = r19;
        r9 = r0.mNextSelectedPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r10 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        r9 = r9 - r10;
        if (r9 < 0) goto L_0x0443;
    L_0x00c6:
        if (r9 >= r12) goto L_0x0443;
    L_0x00c8:
        r0 = r19;
        r3 = r0.getChildAt(r9);	 Catch:{ all -> 0x0143 }
        r9 = r1;
        r10 = r2;
        r2 = r4;
        r4 = r8;
        goto L_0x009a;
    L_0x00d3:
        r9 = r1;
        r10 = r2;
        r2 = r4;
        r4 = r8;
        goto L_0x009a;
    L_0x00d8:
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r8 = r0.mAdapter;	 Catch:{ all -> 0x0143 }
        r8 = r8.getCount();	 Catch:{ all -> 0x0143 }
        if (r1 == r8) goto L_0x014f;
    L_0x00e6:
        r0 = r19;
        r1 = r0.mAdapter;	 Catch:{ all -> 0x0143 }
        r1 = r1.getClass();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mAdapter;	 Catch:{ all -> 0x0143 }
        r2 = r2 instanceof com.tencent.widget.HeaderViewListAdapter;	 Catch:{ all -> 0x0143 }
        if (r2 == 0) goto L_0x0104;
    L_0x00f6:
        r0 = r19;
        r1 = r0.mAdapter;	 Catch:{ all -> 0x0143 }
        r1 = (com.tencent.widget.HeaderViewListAdapter) r1;	 Catch:{ all -> 0x0143 }
        r1 = r1.getWrappedAdapter();	 Catch:{ all -> 0x0143 }
        r1 = r1.getClass();	 Catch:{ all -> 0x0143 }
    L_0x0104:
        r2 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0143 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0143 }
        r3.<init>();	 Catch:{ all -> 0x0143 }
        r4 = "The content of the adapter has changed but ListView did not receive a notification. Make sure the content of your adapter is not modified from a background thread, but only from the UI thread. [in ListView(";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0143 }
        r4 = r19.getId();	 Catch:{ all -> 0x0143 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0143 }
        r4 = ", ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0143 }
        r4 = r19.getClass();	 Catch:{ all -> 0x0143 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0143 }
        r4 = ") with Adapter(";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0143 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x0143 }
        r3 = ")]";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0143 }
        r1 = r1.toString();	 Catch:{ all -> 0x0143 }
        r2.<init>(r1);	 Catch:{ all -> 0x0143 }
        throw r2;	 Catch:{ all -> 0x0143 }
    L_0x0143:
        r1 = move-exception;
        if (r11 != 0) goto L_0x014b;
    L_0x0146:
        r2 = 0;
        r0 = r19;
        r0.mBlockLayoutRequests = r2;
    L_0x014b:
        com.tencent.widget.AdapterView.traceEnd();
        throw r1;
    L_0x014f:
        r0 = r19;
        r1 = r0.mNextSelectedPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r0.setSelectedPositionInt(r1);	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r15 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r0 = r0.mRecycler;	 Catch:{ all -> 0x0143 }
        r16 = r0;
        r1 = 0;
        if (r14 == 0) goto L_0x0176;
    L_0x0165:
        r8 = 0;
    L_0x0166:
        if (r8 >= r12) goto L_0x017b;
    L_0x0168:
        r0 = r19;
        r17 = r0.getChildAt(r8);	 Catch:{ all -> 0x0143 }
        r18 = r15 + r8;
        r16.addScrapView(r17, r18);	 Catch:{ all -> 0x0143 }
        r8 = r8 + 1;
        goto L_0x0166;
    L_0x0176:
        r0 = r16;
        r0.fillActiveViews(r12, r15);	 Catch:{ all -> 0x0143 }
    L_0x017b:
        r8 = r19.getFocusedChild();	 Catch:{ all -> 0x0143 }
        if (r8 == 0) goto L_0x043f;
    L_0x0181:
        if (r14 == 0) goto L_0x018b;
    L_0x0183:
        r0 = r19;
        r15 = r0.isDirectChildHeaderOrFooter(r8);	 Catch:{ all -> 0x0143 }
        if (r15 == 0) goto L_0x0195;
    L_0x018b:
        r7 = r19.findFocus();	 Catch:{ all -> 0x0143 }
        if (r7 == 0) goto L_0x0194;
    L_0x0191:
        r7.onStartTemporaryDetach();	 Catch:{ all -> 0x0143 }
    L_0x0194:
        r1 = r8;
    L_0x0195:
        r19.requestFocus();	 Catch:{ all -> 0x0143 }
        r8 = r7;
        r7 = r1;
    L_0x019a:
        r19.detachAllViewsFromParent();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mLayoutMode;	 Catch:{ all -> 0x0143 }
        switch(r1) {
            case 1: goto L_0x02eb;
            case 2: goto L_0x0294;
            case 3: goto L_0x02d9;
            case 4: goto L_0x02fc;
            case 5: goto L_0x02ac;
            case 6: goto L_0x0323;
            case 100: goto L_0x030d;
            default: goto L_0x01a4;
        };	 Catch:{ all -> 0x0143 }
    L_0x01a4:
        if (r12 != 0) goto L_0x034d;
    L_0x01a6:
        r0 = r19;
        r1 = r0.mStackFromBottom;	 Catch:{ all -> 0x0143 }
        if (r1 != 0) goto L_0x032c;
    L_0x01ac:
        r1 = 0;
        r2 = 1;
        r0 = r19;
        r1 = r0.lookForSelectablePosition(r1, r2);	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r0.setSelectedPositionInt(r1);	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.fillFromTop(r5);	 Catch:{ all -> 0x0143 }
        r2 = r1;
    L_0x01c0:
        r19.stayOnTheTop();	 Catch:{ all -> 0x0143 }
        r16.scrapActiveViews();	 Catch:{ all -> 0x0143 }
        if (r2 == 0) goto L_0x03ed;
    L_0x01c8:
        r0 = r19;
        r1 = r0.mItemsCanFocus;	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x03e5;
    L_0x01ce:
        r1 = r19.hasFocus();	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x03e5;
    L_0x01d4:
        r1 = r2.hasFocus();	 Catch:{ all -> 0x0143 }
        if (r1 != 0) goto L_0x03e5;
    L_0x01da:
        if (r2 != r7) goto L_0x01e2;
    L_0x01dc:
        r1 = r8.requestFocus();	 Catch:{ all -> 0x0143 }
        if (r1 != 0) goto L_0x01e8;
    L_0x01e2:
        r1 = r2.requestFocus();	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x03d5;
    L_0x01e8:
        r1 = 1;
    L_0x01e9:
        if (r1 != 0) goto L_0x03d8;
    L_0x01eb:
        r1 = r19.getFocusedChild();	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x01f4;
    L_0x01f1:
        r1.clearFocus();	 Catch:{ all -> 0x0143 }
    L_0x01f4:
        r1 = -1;
        r0 = r19;
        r0.positionSelector(r1, r2);	 Catch:{ all -> 0x0143 }
    L_0x01fa:
        r1 = r2.getTop();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r0.mSelectedTop = r1;	 Catch:{ all -> 0x0143 }
    L_0x0202:
        if (r8 == 0) goto L_0x020d;
    L_0x0204:
        r1 = r8.getWindowToken();	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x020d;
    L_0x020a:
        r8.onFinishTemporaryDetach();	 Catch:{ all -> 0x0143 }
    L_0x020d:
        r1 = 0;
        r0 = r19;
        r0.mLayoutMode = r1;	 Catch:{ all -> 0x0143 }
        r1 = 0;
        r0 = r19;
        r0.mDataChanged = r1;	 Catch:{ all -> 0x0143 }
        r1 = 0;
        r0 = r19;
        r0.mNeedSync = r1;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mSelectedPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r0.setNextSelectedPositionInt(r1);	 Catch:{ all -> 0x0143 }
        r19.updateScrollIndicators();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        if (r1 <= 0) goto L_0x0231;
    L_0x022e:
        r19.checkSelectionChanged();	 Catch:{ all -> 0x0143 }
    L_0x0231:
        r19.invokeOnItemScrollListener();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        r2 = r19.getChildCount();	 Catch:{ all -> 0x0143 }
        r1 = r1 + r2;
        r2 = r1 + -1;
        r0 = r19;
        r1 = r0.mInsertAnimation;	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x042e;
    L_0x0245:
        r0 = r19;
        r1 = r0.mAddingRows;	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x042e;
    L_0x024b:
        if (r14 == 0) goto L_0x042e;
    L_0x024d:
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        if (r1 <= 0) goto L_0x042e;
    L_0x0253:
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        r1 = r1 + -1;
        if (r2 < r1) goto L_0x042e;
    L_0x025b:
        r1 = r19.getChildCount();	 Catch:{ all -> 0x0143 }
        r1 = r1 + -1;
        r0 = r19;
        r1 = r0.getChildAt(r1);	 Catch:{ all -> 0x0143 }
        r1 = r1.getBottom();	 Catch:{ all -> 0x0143 }
        if (r1 >= r6) goto L_0x042e;
    L_0x026d:
        r0 = r19;
        r3 = r0.mAddingRows;	 Catch:{ all -> 0x0143 }
        r4 = r3.length;	 Catch:{ all -> 0x0143 }
        r1 = 0;
    L_0x0273:
        if (r1 >= r4) goto L_0x042e;
    L_0x0275:
        r5 = r3[r1];	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r6 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        if (r5 < r6) goto L_0x0291;
    L_0x027d:
        if (r5 > r2) goto L_0x0291;
    L_0x027f:
        r0 = r19;
        r6 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        r5 = r5 - r6;
        r0 = r19;
        r5 = r0.getChildAt(r5);	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r6 = r0.mInsertAnimation;	 Catch:{ all -> 0x0143 }
        r5.startAnimation(r6);	 Catch:{ all -> 0x0143 }
    L_0x0291:
        r1 = r1 + 1;
        goto L_0x0273;
    L_0x0294:
        if (r3 == 0) goto L_0x02a3;
    L_0x0296:
        r1 = r3.getTop();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.fillFromSelection(r1, r5, r6);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x02a3:
        r0 = r19;
        r1 = r0.fillFromMiddle(r5, r6);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x02ac:
        r0 = r19;
        r1 = r0.mStackFromBottom;	 Catch:{ all -> 0x0143 }
        if (r1 != 0) goto L_0x02c3;
    L_0x02b2:
        r0 = r19;
        r1 = r0.mSyncPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mSpecificTop;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.fillSpecific(r1, r2);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x02c3:
        r0 = r19;
        r1 = r0.mSyncPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mLayoutHeight;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r3 = r0.mSpecificBottom;	 Catch:{ all -> 0x0143 }
        r2 = r2 - r3;
        r0 = r19;
        r1 = r0.fillSpecificBottom(r1, r2);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x02d9:
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        r1 = r1 + -1;
        r0 = r19;
        r1 = r0.fillUp(r1, r6);	 Catch:{ all -> 0x0143 }
        r19.adjustViewsUpOrDown();	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x02eb:
        r1 = 0;
        r0 = r19;
        r0.mFirstPosition = r1;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.fillFromTop(r5);	 Catch:{ all -> 0x0143 }
        r19.adjustViewsUpOrDown();	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x02fc:
        r1 = r19.reconcileSelectedPosition();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mSpecificTop;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.fillSpecific(r1, r2);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x030d:
        r1 = r19.reconcileSelectedPosition();	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mLayoutHeight;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r3 = r0.mSpecificBottom;	 Catch:{ all -> 0x0143 }
        r2 = r2 - r3;
        r0 = r19;
        r1 = r0.fillSpecificBottom(r1, r2);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x0323:
        r1 = r19;
        r1 = r1.moveSelection(r2, r3, r4, r5, r6);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x032c:
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        r1 = r1 + -1;
        r2 = 0;
        r0 = r19;
        r1 = r0.lookForSelectablePosition(r1, r2);	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r0.setSelectedPositionInt(r1);	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        r1 = r1 + -1;
        r0 = r19;
        r1 = r0.fillUp(r1, r6);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x034d:
        r0 = r19;
        r1 = r0.mSelectedPosition;	 Catch:{ all -> 0x0143 }
        if (r1 < 0) goto L_0x0371;
    L_0x0353:
        r0 = r19;
        r1 = r0.mSelectedPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r3 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        if (r1 >= r3) goto L_0x0371;
    L_0x035d:
        r0 = r19;
        r1 = r0.mSelectedPosition;	 Catch:{ all -> 0x0143 }
        if (r2 != 0) goto L_0x036c;
    L_0x0363:
        r0 = r19;
        r1 = r0.fillSpecific(r1, r5);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x036c:
        r5 = r2.getTop();	 Catch:{ all -> 0x0143 }
        goto L_0x0363;
    L_0x0371:
        r0 = r19;
        r1 = r0.mScrollY;	 Catch:{ all -> 0x0143 }
        if (r1 != 0) goto L_0x037d;
    L_0x0377:
        r0 = r19;
        r1 = r0.mStackFromBottom;	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x0383;
    L_0x037d:
        r0 = r19;
        r1 = r0.mScrollY;	 Catch:{ all -> 0x0143 }
        if (r1 >= 0) goto L_0x03ab;
    L_0x0383:
        r0 = r19;
        r1 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        if (r1 >= r2) goto L_0x03a1;
    L_0x038d:
        r0 = r19;
        r1 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        if (r10 != 0) goto L_0x039c;
    L_0x0393:
        r0 = r19;
        r1 = r0.fillSpecific(r1, r5);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x039c:
        r5 = r10.getTop();	 Catch:{ all -> 0x0143 }
        goto L_0x0393;
    L_0x03a1:
        r1 = 0;
        r0 = r19;
        r1 = r0.fillSpecific(r1, r5);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x03ab:
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        if (r13 >= r1) goto L_0x03c2;
    L_0x03b1:
        if (r9 != 0) goto L_0x03bd;
    L_0x03b3:
        r1 = r6;
    L_0x03b4:
        r0 = r19;
        r1 = r0.fillSpecificBottom(r13, r1);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x03bd:
        r1 = r9.getBottom();	 Catch:{ all -> 0x0143 }
        goto L_0x03b4;
    L_0x03c2:
        r0 = r19;
        r1 = r0.mItemCount;	 Catch:{ all -> 0x0143 }
        r1 = r1 + -1;
        r0 = r19;
        r2 = r0.mLayoutHeight;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.fillSpecificBottom(r1, r2);	 Catch:{ all -> 0x0143 }
        r2 = r1;
        goto L_0x01c0;
    L_0x03d5:
        r1 = 0;
        goto L_0x01e9;
    L_0x03d8:
        r1 = 0;
        r2.setSelected(r1);	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mSelectorRect;	 Catch:{ all -> 0x0143 }
        r1.setEmpty();	 Catch:{ all -> 0x0143 }
        goto L_0x01fa;
    L_0x03e5:
        r1 = -1;
        r0 = r19;
        r0.positionSelector(r1, r2);	 Catch:{ all -> 0x0143 }
        goto L_0x01fa;
    L_0x03ed:
        r0 = r19;
        r1 = r0.mTouchMode;	 Catch:{ all -> 0x0143 }
        if (r1 <= 0) goto L_0x0421;
    L_0x03f3:
        r0 = r19;
        r1 = r0.mTouchMode;	 Catch:{ all -> 0x0143 }
        r2 = 3;
        if (r1 >= r2) goto L_0x0421;
    L_0x03fa:
        r0 = r19;
        r1 = r0.mMotionPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r2 = r0.mFirstPosition;	 Catch:{ all -> 0x0143 }
        r1 = r1 - r2;
        r0 = r19;
        r1 = r0.getChildAt(r1);	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x0414;
    L_0x040b:
        r0 = r19;
        r2 = r0.mMotionPosition;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r0.positionSelector(r2, r1);	 Catch:{ all -> 0x0143 }
    L_0x0414:
        r1 = r19.hasFocus();	 Catch:{ all -> 0x0143 }
        if (r1 == 0) goto L_0x0202;
    L_0x041a:
        if (r8 == 0) goto L_0x0202;
    L_0x041c:
        r8.requestFocus();	 Catch:{ all -> 0x0143 }
        goto L_0x0202;
    L_0x0421:
        r1 = 0;
        r0 = r19;
        r0.mSelectedTop = r1;	 Catch:{ all -> 0x0143 }
        r0 = r19;
        r1 = r0.mSelectorRect;	 Catch:{ all -> 0x0143 }
        r1.setEmpty();	 Catch:{ all -> 0x0143 }
        goto L_0x0414;
    L_0x042e:
        r1 = 0;
        r0 = r19;
        r0.mAddingRows = r1;	 Catch:{ all -> 0x0143 }
        if (r11 != 0) goto L_0x043a;
    L_0x0435:
        r1 = 0;
        r0 = r19;
        r0.mBlockLayoutRequests = r1;
    L_0x043a:
        com.tencent.widget.AdapterView.traceEnd();
        goto L_0x002d;
    L_0x043f:
        r8 = r7;
        r7 = r1;
        goto L_0x019a;
    L_0x0443:
        r9 = r1;
        r10 = r2;
        r2 = r4;
        r4 = r8;
        goto L_0x009a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.widget.ListView.layoutChildren():void");
    }

    public void setInsertAnimation(Animation animation) {
        this.mInsertAnimation = animation;
    }

    private boolean isDirectChildHeaderOrFooter(View view) {
        int i;
        ArrayList arrayList = this.mHeaderViewInfos;
        int size = arrayList.size();
        for (i = 0; i < size; i++) {
            if (view == ((FixedViewInfo) arrayList.get(i)).view) {
                return true;
            }
        }
        arrayList = this.mFooterViewInfos;
        size = arrayList.size();
        for (i = 0; i < size; i++) {
            if (view == ((FixedViewInfo) arrayList.get(i)).view) {
                return true;
            }
        }
        return false;
    }

    private View makeAndAddView(int i, int i2, boolean z, int i3, boolean z2) {
        AdapterView.traceBegin("ListView.makeAndAddView");
        try {
            View activeView;
            if (!this.mDataChanged) {
                activeView = this.mRecycler.getActiveView(i);
                if (activeView != null) {
                    setupChild(activeView, i, i2, z, i3, z2, true);
                    return activeView;
                }
            }
            activeView = obtainView(i, this.mIsScrap);
            setupChild(activeView, i, i2, z, i3, z2, this.mIsScrap[0]);
            AdapterView.traceEnd();
            return activeView;
        } finally {
            AdapterView.traceEnd();
        }
    }

    @TargetApi(11)
    private void setupChild(View view, int i, int i2, boolean z, int i3, boolean z2, boolean z3) {
        setupChild(view, i, i2, z, i3, z2, z3, z ? -1 : 0);
    }

    @TargetApi(11)
    private void setupChild(View view, int i, int i2, boolean z, int i3, boolean z2, boolean z3, int i4) {
        Object obj;
        ViewGroup.LayoutParams layoutParams;
        int childMeasureSpec;
        AdapterView.traceBegin("ListView.setupChild");
        boolean z4 = z2 && shouldShowSelector();
        Object obj2 = z4 != view.isSelected() ? 1 : null;
        int i5 = this.mTouchMode;
        boolean z5 = i5 > 0 && i5 < 3 && this.mMotionPosition == i;
        Object obj3 = z5 != view.isPressed() ? 1 : null;
        if (z3 && obj2 == null && !view.isLayoutRequested()) {
            obj = null;
        } else {
            obj = 1;
        }
        ViewGroup.LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams = new LayoutParams(-1, -2, 0);
        } else {
            layoutParams = layoutParams2;
        }
        layoutParams.viewType = this.mAdapter.getItemViewType(i);
        if ((!z3 || layoutParams.forceAdd) && !(layoutParams.recycledHeaderFooter && layoutParams.viewType == -2)) {
            layoutParams.forceAdd = false;
            if (layoutParams.viewType == -2) {
                layoutParams.recycledHeaderFooter = true;
            }
            addViewInLayout(view, i4, layoutParams, true);
        } else {
            attachViewToParent(view, i4, layoutParams);
        }
        if (obj2 != null) {
            view.setSelected(z4);
        }
        if (obj3 != null) {
            view.setPressed(z5);
        }
        if (!(this.mChoiceMode == 0 || this.mCheckStates == null)) {
            if (view instanceof Checkable) {
                ((Checkable) view).setChecked(this.mCheckStates.get(i));
            } else if (getContext().getApplicationInfo().targetSdkVersion >= 11 && VersionUtils.isHoneycomb()) {
                view.setActivated(this.mCheckStates.get(i));
            }
        }
        if (obj != null) {
            childMeasureSpec = ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mListPadding.left + this.mListPadding.right, layoutParams.width);
            i5 = layoutParams.height;
            if (i5 > 0) {
                i5 = MeasureSpec.makeMeasureSpec(i5, 1073741824);
            } else {
                i5 = MeasureSpec.makeMeasureSpec(0, 0);
            }
            AdapterView.traceBegin("ListView.childMeasure");
            view.measure(childMeasureSpec, i5);
            AdapterView.traceEnd();
        } else {
            cleanupLayoutState(view);
        }
        i5 = view.getMeasuredWidth();
        childMeasureSpec = view.getMeasuredHeight();
        if (!z) {
            i2 -= childMeasureSpec;
        }
        if (obj != null) {
            i5 += i3;
            childMeasureSpec += i2;
            AdapterView.traceBegin("ListView.childLayout");
            view.layout(i3, i2, i5, childMeasureSpec);
            AdapterView.traceEnd();
        } else {
            view.offsetLeftAndRight(i3 - view.getLeft());
            view.offsetTopAndBottom(i2 - view.getTop());
        }
        if (this.mCachingStarted && !view.isDrawingCacheEnabled()) {
            view.setDrawingCacheEnabled(true);
        }
        if (VersionUtils.isHoneycomb() && z3 && ((LayoutParams) view.getLayoutParams()).scrappedFromPosition != i) {
            view.jumpDrawablesToCurrentState();
        }
        AdapterView.traceEnd();
    }

    @TargetApi(11)
    private int updateChild(View view, int i, int i2, boolean z, int i3, int i4) {
        View view2;
        ViewGroup.LayoutParams layoutParams;
        boolean isPressed;
        int height = view.getHeight();
        int i5 = ((LayoutParams) view.getLayoutParams()).viewType;
        int itemViewType = this.mAdapter.getItemViewType(i);
        if (i5 == itemViewType) {
            view2 = this.mAdapter.getView(i, view, this);
        } else {
            view2 = this.mAdapter.getView(i, this.mRecycler.getScrapView(i), this);
        }
        ViewGroup.LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams = new LayoutParams(-1, -2, 0);
        } else {
            layoutParams = layoutParams2;
        }
        layoutParams.viewType = itemViewType;
        if (view2 != view) {
            boolean isSelected = view.isSelected();
            isPressed = view.isPressed();
            this.mRecycler.addScrapView(view, i);
            if (this.mCacheColorHint != 0) {
                view2.setDrawingCacheBackgroundColor(this.mCacheColorHint);
            }
            detachViewFromParent(i4);
            addViewInLayout(view2, i4, layoutParams, true);
            if (view2.isSelected() != isSelected) {
                view2.setSelected(isSelected);
            }
            if (view2.isPressed() != isPressed) {
                view2.setPressed(isPressed);
            }
            if (!(this.mChoiceMode == 0 || this.mCheckStates == null)) {
                if (view2 instanceof Checkable) {
                    ((Checkable) view2).setChecked(this.mCheckStates.get(i));
                } else if (getContext().getApplicationInfo().targetSdkVersion >= 11 && VersionUtils.isHoneycomb()) {
                    view2.setActivated(this.mCheckStates.get(i));
                }
            }
        }
        isPressed = view2.isLayoutRequested();
        if (isPressed) {
            int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mListPadding.left + this.mListPadding.right, layoutParams.width);
            i5 = layoutParams.height;
            if (i5 > 0) {
                i5 = MeasureSpec.makeMeasureSpec(i5, 1073741824);
            } else {
                i5 = MeasureSpec.makeMeasureSpec(0, 0);
            }
            view2.measure(childMeasureSpec, i5);
        } else {
            cleanupLayoutState(view2);
        }
        i5 = view2.getMeasuredWidth();
        int measuredHeight = view2.getMeasuredHeight();
        if (!z) {
            i2 -= measuredHeight;
        }
        if (isPressed) {
            view2.layout(i3, i2, i5 + i3, measuredHeight + i2);
        } else {
            view2.offsetLeftAndRight(i3 - view2.getLeft());
            view2.offsetTopAndBottom(i2 - view2.getTop());
        }
        if (this.mCachingStarted && !view2.isDrawingCacheEnabled()) {
            view2.setDrawingCacheEnabled(true);
        }
        if (VersionUtils.isHoneycomb() && ((LayoutParams) view2.getLayoutParams()).scrappedFromPosition != i) {
            view2.jumpDrawablesToCurrentState();
        }
        return view2.getHeight() - height;
    }

    protected boolean canAnimate() {
        return super.canAnimate() && this.mItemCount > 0;
    }

    public void setSelection(int i) {
        setSelectionFromTop(i, 0);
    }

    public void setSelectionFromTop(int i, int i2) {
        if (this.mAdapter != null) {
            if (isInTouchMode()) {
                this.mResurrectToPosition = i;
            } else {
                i = lookForSelectablePosition(i, true);
                if (i >= 0) {
                    setNextSelectedPositionInt(i);
                }
            }
            if (i >= 0) {
                requestLayout();
                this.mLayoutMode = 4;
                if (this.mNeedSync) {
                    this.mSyncPosition = i;
                    this.mSyncRowId = this.mAdapter.getItemId(i);
                }
                this.mSpecificTop = this.mListPadding.top + i2;
            }
        }
    }

    public void setSelectionFromBottom(int i, int i2) {
        if (this.mAdapter != null) {
            if (isInTouchMode()) {
                this.mResurrectToPosition = i;
            } else {
                i = lookForSelectablePosition(i, true);
                if (i >= 0) {
                    setNextSelectedPositionInt(i);
                }
            }
            if (i >= 0) {
                requestLayout();
                this.mLayoutMode = 100;
                if (this.mNeedSync) {
                    this.mSyncPosition = i;
                    this.mSyncRowId = this.mAdapter.getItemId(i);
                }
                this.mSpecificBottom = this.mListPadding.bottom + i2;
            }
        }
    }

    void setSelectionInt(int i) {
        Object obj = 1;
        setNextSelectedPositionInt(i);
        int i2 = this.mSelectedPosition;
        if (i2 < 0 || !(i == i2 - 1 || i == i2 + 1)) {
            obj = null;
        }
        layoutChildren();
        if (obj != null) {
            awakenScrollBars();
        }
    }

    int lookForSelectablePosition(int i, boolean z) {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null || isInTouchMode()) {
            return -1;
        }
        int count = listAdapter.getCount();
        if (!this.mAreAllItemsSelectable) {
            if (z) {
                i = Math.max(0, i);
                while (i < count && !listAdapter.isEnabled(i)) {
                    i++;
                }
            } else {
                i = Math.min(i, count - 1);
                while (i >= 0 && !listAdapter.isEnabled(i)) {
                    i--;
                }
            }
            if (i < 0 || i >= count) {
                return -1;
            }
            return i;
        } else if (i < 0 || i >= count) {
            return -1;
        } else {
            return i;
        }
    }

    public void setSelectionAfterHeaderView() {
        int size = this.mHeaderViewInfos.size();
        if (size > 0) {
            this.mNextSelectedPosition = 0;
        } else if (this.mAdapter != null) {
            setSelection(size);
        } else {
            this.mNextSelectedPosition = size;
            this.mLayoutMode = 2;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean dispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        if (dispatchKeyEvent || getFocusedChild() == null || keyEvent.getAction() != 0) {
            return dispatchKeyEvent;
        }
        return onKeyDown(keyEvent.getKeyCode(), keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return commonKey(i, 1, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return commonKey(i, i2, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return commonKey(i, 1, keyEvent);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(11)
    private boolean commonKey(int r8, int r9, android.view.KeyEvent r10) {
        /*
        r7 = this;
        r3 = 2;
        r6 = 130; // 0x82 float:1.82E-43 double:6.4E-322;
        r5 = 33;
        r1 = 0;
        r2 = 1;
        r0 = r7.mAdapter;
        if (r0 == 0) goto L_0x000f;
    L_0x000b:
        r0 = r7.mIsAttached;
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        r2 = r1;
    L_0x0010:
        return r2;
    L_0x0011:
        r0 = r7.mDataChanged;
        if (r0 == 0) goto L_0x0018;
    L_0x0015:
        r7.layoutChildren();
    L_0x0018:
        r4 = r10.getAction();
        if (r4 == r2) goto L_0x0021;
    L_0x001e:
        switch(r8) {
            case 19: goto L_0x002f;
            case 20: goto L_0x006b;
            case 21: goto L_0x00a9;
            case 22: goto L_0x00bd;
            case 23: goto L_0x00d1;
            case 62: goto L_0x00f5;
            case 66: goto L_0x00d1;
            case 92: goto L_0x0135;
            case 93: goto L_0x0171;
            case 122: goto L_0x01ad;
            case 123: goto L_0x01cb;
            default: goto L_0x0021;
        };
    L_0x0021:
        r0 = r1;
    L_0x0022:
        if (r0 != 0) goto L_0x0010;
    L_0x0024:
        r0 = r7.sendToTextFilter(r8, r9, r10);
        if (r0 != 0) goto L_0x0010;
    L_0x002a:
        switch(r4) {
            case 0: goto L_0x01e9;
            case 1: goto L_0x01ef;
            case 2: goto L_0x01f5;
            default: goto L_0x002d;
        };
    L_0x002d:
        r2 = r1;
        goto L_0x0010;
    L_0x002f:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x004f;
    L_0x0035:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x004f;
    L_0x003b:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x0022;
    L_0x0041:
        r3 = r9;
    L_0x0042:
        r9 = r3 + -1;
        if (r3 <= 0) goto L_0x0022;
    L_0x0046:
        r3 = r7.arrowScroll(r5);
        if (r3 == 0) goto L_0x0022;
    L_0x004c:
        r0 = r2;
        r3 = r9;
        goto L_0x0042;
    L_0x004f:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x0055:
        r0 = r10.hasModifiers(r3);
        if (r0 == 0) goto L_0x0021;
    L_0x005b:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x0067;
    L_0x0061:
        r0 = r7.fullScroll(r5);
        if (r0 == 0) goto L_0x0069;
    L_0x0067:
        r0 = r2;
        goto L_0x0022;
    L_0x0069:
        r0 = r1;
        goto L_0x0022;
    L_0x006b:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x008b;
    L_0x0071:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x008b;
    L_0x0077:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x0022;
    L_0x007d:
        r3 = r9;
    L_0x007e:
        r9 = r3 + -1;
        if (r3 <= 0) goto L_0x0022;
    L_0x0082:
        r3 = r7.arrowScroll(r6);
        if (r3 == 0) goto L_0x0022;
    L_0x0088:
        r0 = r2;
        r3 = r9;
        goto L_0x007e;
    L_0x008b:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x0091:
        r0 = r10.hasModifiers(r3);
        if (r0 == 0) goto L_0x0021;
    L_0x0097:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x00a3;
    L_0x009d:
        r0 = r7.fullScroll(r6);
        if (r0 == 0) goto L_0x00a6;
    L_0x00a3:
        r0 = r2;
        goto L_0x0022;
    L_0x00a6:
        r0 = r1;
        goto L_0x0022;
    L_0x00a9:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x00af:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x0021;
    L_0x00b5:
        r0 = 17;
        r0 = r7.handleHorizontalFocusWithinListItem(r0);
        goto L_0x0022;
    L_0x00bd:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x00c3:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x0021;
    L_0x00c9:
        r0 = 66;
        r0 = r7.handleHorizontalFocusWithinListItem(r0);
        goto L_0x0022;
    L_0x00d1:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x00d7:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x0021;
    L_0x00dd:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x0022;
    L_0x00e3:
        r3 = r10.getRepeatCount();
        if (r3 != 0) goto L_0x0022;
    L_0x00e9:
        r3 = r7.getChildCount();
        if (r3 <= 0) goto L_0x0022;
    L_0x00ef:
        r7.keyPressed();
        r0 = r2;
        goto L_0x0022;
    L_0x00f5:
        r0 = r7.mPopup;
        if (r0 == 0) goto L_0x0101;
    L_0x00f9:
        r0 = r7.mPopup;
        r0 = r0.isShowing();
        if (r0 != 0) goto L_0x0021;
    L_0x0101:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x011c;
    L_0x0107:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x011c;
    L_0x010d:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x0119;
    L_0x0113:
        r0 = r7.pageScroll(r6);
        if (r0 == 0) goto L_0x0119;
    L_0x0119:
        r0 = r2;
        goto L_0x0022;
    L_0x011c:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0119;
    L_0x0122:
        r0 = r10.hasModifiers(r2);
        if (r0 == 0) goto L_0x0119;
    L_0x0128:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x0119;
    L_0x012e:
        r0 = r7.pageScroll(r5);
        if (r0 == 0) goto L_0x0119;
    L_0x0134:
        goto L_0x0119;
    L_0x0135:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0153;
    L_0x013b:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x0153;
    L_0x0141:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x014d;
    L_0x0147:
        r0 = r7.pageScroll(r5);
        if (r0 == 0) goto L_0x0150;
    L_0x014d:
        r0 = r2;
        goto L_0x0022;
    L_0x0150:
        r0 = r1;
        goto L_0x0022;
    L_0x0153:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x0159:
        r0 = r10.hasModifiers(r3);
        if (r0 == 0) goto L_0x0021;
    L_0x015f:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x016b;
    L_0x0165:
        r0 = r7.fullScroll(r5);
        if (r0 == 0) goto L_0x016e;
    L_0x016b:
        r0 = r2;
        goto L_0x0022;
    L_0x016e:
        r0 = r1;
        goto L_0x0022;
    L_0x0171:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x018f;
    L_0x0177:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x018f;
    L_0x017d:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x0189;
    L_0x0183:
        r0 = r7.pageScroll(r6);
        if (r0 == 0) goto L_0x018c;
    L_0x0189:
        r0 = r2;
        goto L_0x0022;
    L_0x018c:
        r0 = r1;
        goto L_0x0022;
    L_0x018f:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x0195:
        r0 = r10.hasModifiers(r3);
        if (r0 == 0) goto L_0x0021;
    L_0x019b:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x01a7;
    L_0x01a1:
        r0 = r7.fullScroll(r6);
        if (r0 == 0) goto L_0x01aa;
    L_0x01a7:
        r0 = r2;
        goto L_0x0022;
    L_0x01aa:
        r0 = r1;
        goto L_0x0022;
    L_0x01ad:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x01b3:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x0021;
    L_0x01b9:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x01c5;
    L_0x01bf:
        r0 = r7.fullScroll(r5);
        if (r0 == 0) goto L_0x01c8;
    L_0x01c5:
        r0 = r2;
        goto L_0x0022;
    L_0x01c8:
        r0 = r1;
        goto L_0x0022;
    L_0x01cb:
        r0 = com.tencent.util.VersionUtils.isHoneycomb();
        if (r0 == 0) goto L_0x0021;
    L_0x01d1:
        r0 = r10.hasNoModifiers();
        if (r0 == 0) goto L_0x0021;
    L_0x01d7:
        r0 = r7.resurrectSelectionIfNeeded();
        if (r0 != 0) goto L_0x01e3;
    L_0x01dd:
        r0 = r7.fullScroll(r6);
        if (r0 == 0) goto L_0x01e6;
    L_0x01e3:
        r0 = r2;
        goto L_0x0022;
    L_0x01e6:
        r0 = r1;
        goto L_0x0022;
    L_0x01e9:
        r2 = super.onKeyDown(r8, r10);
        goto L_0x0010;
    L_0x01ef:
        r2 = super.onKeyUp(r8, r10);
        goto L_0x0010;
    L_0x01f5:
        r2 = super.onKeyMultiple(r8, r9, r10);
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.widget.ListView.commonKey(int, int, android.view.KeyEvent):boolean");
    }

    boolean pageScroll(int i) {
        int max;
        boolean z;
        if (i == 33) {
            max = Math.max(0, (this.mSelectedPosition - getChildCount()) - 1);
            z = false;
        } else if (i == Opcodes.INT_TO_FLOAT) {
            max = Math.min(this.mItemCount - 1, (this.mSelectedPosition + getChildCount()) - 1);
            z = true;
        } else {
            max = -1;
            z = false;
        }
        if (max >= 0) {
            max = lookForSelectablePosition(max, z);
            if (max >= 0) {
                this.mLayoutMode = 4;
                this.mSpecificTop = this.mPaddingTop + getVerticalFadingEdgeLength();
                if (z && max > this.mItemCount - getChildCount()) {
                    this.mLayoutMode = 3;
                }
                if (!z && max < getChildCount()) {
                    this.mLayoutMode = 1;
                }
                setSelectionInt(max);
                invokeOnItemScrollListener();
                if (awakenScrollBars()) {
                    return true;
                }
                invalidate();
                return true;
            }
        }
        return false;
    }

    boolean fullScroll(int i) {
        boolean z = true;
        int lookForSelectablePosition;
        if (i == 33) {
            if (this.mSelectedPosition != 0) {
                lookForSelectablePosition = lookForSelectablePosition(0, true);
                if (lookForSelectablePosition >= 0) {
                    this.mLayoutMode = 1;
                    setSelectionInt(lookForSelectablePosition);
                    invokeOnItemScrollListener();
                }
            }
            z = false;
        } else {
            if (i == Opcodes.INT_TO_FLOAT && this.mSelectedPosition < this.mItemCount - 1) {
                lookForSelectablePosition = lookForSelectablePosition(this.mItemCount - 1, true);
                if (lookForSelectablePosition >= 0) {
                    this.mLayoutMode = 3;
                    setSelectionInt(lookForSelectablePosition);
                    invokeOnItemScrollListener();
                }
            }
            z = false;
        }
        if (z && !awakenScrollBars()) {
            awakenScrollBars();
            invalidate();
        }
        return z;
    }

    private boolean handleHorizontalFocusWithinListItem(int i) {
        if (i == 17 || i == 66) {
            int childCount = getChildCount();
            if (this.mItemsCanFocus && childCount > 0 && this.mSelectedPosition != -1) {
                View selectedView = getSelectedView();
                if (selectedView != null && selectedView.hasFocus() && (selectedView instanceof ViewGroup)) {
                    View findFocus = selectedView.findFocus();
                    selectedView = FocusFinder.getInstance().findNextFocus((ViewGroup) selectedView, findFocus, i);
                    if (selectedView != null) {
                        findFocus.getFocusedRect(this.mTempRect);
                        offsetDescendantRectToMyCoords(findFocus, this.mTempRect);
                        offsetRectIntoDescendantCoords(selectedView, this.mTempRect);
                        if (selectedView.requestFocus(i, this.mTempRect)) {
                            return true;
                        }
                    }
                    selectedView = FocusFinder.getInstance().findNextFocus((ViewGroup) getRootView(), findFocus, i);
                    if (selectedView != null) {
                        return isViewAncestorOf(selectedView, this);
                    }
                }
            }
            return false;
        }
        throw new IllegalArgumentException("direction must be one of {View.FOCUS_LEFT, View.FOCUS_RIGHT}");
    }

    boolean arrowScroll(int i) {
        try {
            this.mInLayout = true;
            boolean arrowScrollImpl = arrowScrollImpl(i);
            if (arrowScrollImpl) {
                playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
            }
            this.mInLayout = false;
            return arrowScrollImpl;
        } catch (Throwable th) {
            this.mInLayout = false;
        }
    }

    private boolean arrowScrollImpl(int i) {
        if (getChildCount() <= 0) {
            return false;
        }
        boolean z;
        View selectedView;
        int i2;
        View findFocus;
        View selectedView2 = getSelectedView();
        int i3 = this.mSelectedPosition;
        int lookForSelectablePositionOnScreen = lookForSelectablePositionOnScreen(i);
        int amountToScroll = amountToScroll(i, lookForSelectablePositionOnScreen);
        ArrowScrollFocusResult arrowScrollFocused = this.mItemsCanFocus ? arrowScrollFocused(i) : null;
        if (arrowScrollFocused != null) {
            lookForSelectablePositionOnScreen = arrowScrollFocused.getSelectedPosition();
            amountToScroll = arrowScrollFocused.getAmountToScroll();
        }
        if (arrowScrollFocused != null) {
            z = true;
        } else {
            z = false;
        }
        if (lookForSelectablePositionOnScreen != -1) {
            if (arrowScrollFocused != null) {
                z = true;
            } else {
                z = false;
            }
            handleNewSelectionChange(selectedView2, i, lookForSelectablePositionOnScreen, z);
            setSelectedPositionInt(lookForSelectablePositionOnScreen);
            setNextSelectedPositionInt(lookForSelectablePositionOnScreen);
            selectedView = getSelectedView();
            if (this.mItemsCanFocus && arrowScrollFocused == null) {
                View focusedChild = getFocusedChild();
                if (focusedChild != null) {
                    focusedChild.clearFocus();
                }
            }
            checkSelectionChanged();
            z = true;
            i2 = lookForSelectablePositionOnScreen;
        } else {
            int i4 = i3;
            selectedView = selectedView2;
            i2 = i4;
        }
        if (amountToScroll > 0) {
            scrollListItemsBy(i == 33 ? amountToScroll : -amountToScroll);
            z = true;
        }
        if (this.mItemsCanFocus && arrowScrollFocused == null && selectedView != null && selectedView.hasFocus()) {
            findFocus = selectedView.findFocus();
            if (!isViewAncestorOf(findFocus, this) || distanceToView(findFocus) > 0) {
                findFocus.clearFocus();
            }
        }
        if (lookForSelectablePositionOnScreen != -1 || selectedView == null || isViewAncestorOf(selectedView, this)) {
            findFocus = selectedView;
        } else {
            hideSelector();
            this.mResurrectToPosition = -1;
            findFocus = null;
        }
        if (!z) {
            return false;
        }
        if (findFocus != null) {
            positionSelector(i2, findFocus);
            this.mSelectedTop = findFocus.getTop();
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        invokeOnItemScrollListener();
        return true;
    }

    private void handleNewSelectionChange(View view, int i, int i2, boolean z) {
        boolean z2 = true;
        if (i2 == -1) {
            throw new IllegalArgumentException("newSelectedPosition needs to be valid");
        }
        View childAt;
        int i3;
        int i4 = this.mSelectedPosition - this.mFirstPosition;
        int i5 = i2 - this.mFirstPosition;
        boolean z3;
        if (i == 33) {
            childAt = getChildAt(i5);
            i3 = i5;
            i5 = i4;
            z3 = true;
        } else {
            childAt = view;
            view = getChildAt(i5);
            i3 = i4;
            z3 = false;
        }
        int childCount = getChildCount();
        if (childAt != null) {
            boolean z4;
            if (z || !r0) {
                z4 = false;
            } else {
                z4 = true;
            }
            childAt.setSelected(z4);
            measureAndAdjustDown(childAt, i3, childCount);
        }
        if (view != null) {
            if (z || r0) {
                z2 = false;
            }
            view.setSelected(z2);
            measureAndAdjustDown(view, i5, childCount);
        }
    }

    private void measureAndAdjustDown(View view, int i, int i2) {
        int height = view.getHeight();
        measureItem(view);
        if (view.getMeasuredHeight() != height) {
            relayoutMeasuredItem(view);
            int measuredHeight = view.getMeasuredHeight() - height;
            for (height = i + 1; height < i2; height++) {
                getChildAt(height).offsetTopAndBottom(measuredHeight);
            }
        }
    }

    private void measureItem(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mListPadding.left + this.mListPadding.right, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            i = MeasureSpec.makeMeasureSpec(i, 1073741824);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
    }

    private void relayoutMeasuredItem(View view) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i = this.mListPadding.left;
        measuredWidth += i;
        int top = view.getTop();
        view.layout(i, top, measuredWidth, measuredHeight + top);
    }

    private int getArrowScrollPreviewLength() {
        return Math.max(2, getVerticalFadingEdgeLength());
    }

    private int amountToScroll(int i, int i2) {
        int height = getHeight() - this.mListPadding.bottom;
        int i3 = this.mListPadding.top;
        int childCount = getChildCount();
        int i4;
        if (i == Opcodes.INT_TO_FLOAT) {
            i4 = childCount - 1;
            if (i2 != -1) {
                i4 = i2 - this.mFirstPosition;
            }
            i3 = this.mFirstPosition + i4;
            View childAt = getChildAt(i4);
            if (i3 < this.mItemCount - 1) {
                i4 = height - getArrowScrollPreviewLength();
            } else {
                i4 = height;
            }
            if (childAt.getBottom() <= i4) {
                return 0;
            }
            if (i2 != -1 && i4 - childAt.getTop() >= getMaxScrollAmount()) {
                return 0;
            }
            i4 = childAt.getBottom() - i4;
            if (this.mFirstPosition + childCount == this.mItemCount) {
                i4 = Math.min(i4, getChildAt(childCount - 1).getBottom() - height);
            }
            return Math.min(i4, getMaxScrollAmount());
        }
        if (i2 != -1) {
            i4 = i2 - this.mFirstPosition;
        } else {
            i4 = 0;
        }
        height = this.mFirstPosition + i4;
        View childAt2 = getChildAt(i4);
        if (height > 0) {
            i4 = getArrowScrollPreviewLength() + i3;
        } else {
            i4 = i3;
        }
        if (childAt2.getTop() >= i4) {
            return 0;
        }
        if (i2 != -1 && childAt2.getBottom() - i4 >= getMaxScrollAmount()) {
            return 0;
        }
        i4 -= childAt2.getTop();
        if (this.mFirstPosition == 0) {
            i4 = Math.min(i4, i3 - getChildAt(0).getTop());
        }
        return Math.min(i4, getMaxScrollAmount());
    }

    private int lookForSelectablePositionOnScreen(int i) {
        int i2 = this.mFirstPosition;
        int i3;
        int lastVisiblePosition;
        if (i == Opcodes.INT_TO_FLOAT) {
            i3 = this.mSelectedPosition != -1 ? this.mSelectedPosition + 1 : i2;
            if (i3 >= this.mAdapter.getCount()) {
                return -1;
            }
            if (i3 < i2) {
                i3 = i2;
            }
            lastVisiblePosition = getLastVisiblePosition();
            ListAdapter adapter = getAdapter();
            while (i3 <= lastVisiblePosition) {
                if (adapter.isEnabled(i3) && getChildAt(i3 - i2).getVisibility() == 0) {
                    return i3;
                }
                i3++;
            }
        } else {
            i3 = (getChildCount() + i2) - 1;
            lastVisiblePosition = this.mSelectedPosition != -1 ? this.mSelectedPosition - 1 : (getChildCount() + i2) - 1;
            if (lastVisiblePosition < 0 || lastVisiblePosition >= this.mAdapter.getCount()) {
                return -1;
            }
            if (lastVisiblePosition <= i3) {
                i3 = lastVisiblePosition;
            }
            ListAdapter adapter2 = getAdapter();
            while (i3 >= i2) {
                if (adapter2.isEnabled(i3) && getChildAt(i3 - i2).getVisibility() == 0) {
                    return i3;
                }
                i3--;
            }
        }
        return -1;
    }

    private ArrowScrollFocusResult arrowScrollFocused(int i) {
        int i2;
        View findNextFocusFromRect;
        int i3 = 1;
        View selectedView = getSelectedView();
        if (selectedView == null || !selectedView.hasFocus()) {
            if (i == Opcodes.INT_TO_FLOAT) {
                if (this.mFirstPosition <= 0) {
                    i3 = 0;
                }
                i2 = this.mListPadding.top;
                if (i3 != 0) {
                    i3 = getArrowScrollPreviewLength();
                } else {
                    i3 = 0;
                }
                i3 += i2;
                if (selectedView != null && selectedView.getTop() > i3) {
                    i3 = selectedView.getTop();
                }
                this.mTempRect.set(0, i3, 0, i3);
            } else {
                if ((this.mFirstPosition + getChildCount()) - 1 >= this.mItemCount) {
                    i3 = 0;
                }
                i2 = getHeight() - this.mListPadding.bottom;
                if (i3 != 0) {
                    i3 = getArrowScrollPreviewLength();
                } else {
                    i3 = 0;
                }
                i3 = i2 - i3;
                if (selectedView != null && selectedView.getBottom() < i3) {
                    i3 = selectedView.getBottom();
                }
                this.mTempRect.set(0, i3, 0, i3);
            }
            findNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(this, this.mTempRect, i);
        } else {
            findNextFocusFromRect = FocusFinder.getInstance().findNextFocus(this, selectedView.findFocus(), i);
        }
        if (findNextFocusFromRect != null) {
            int lookForSelectablePositionOnScreen;
            int positionOfNewFocus = positionOfNewFocus(findNextFocusFromRect);
            if (!(this.mSelectedPosition == -1 || positionOfNewFocus == this.mSelectedPosition)) {
                lookForSelectablePositionOnScreen = lookForSelectablePositionOnScreen(i);
                if (lookForSelectablePositionOnScreen != -1 && ((i == Opcodes.INT_TO_FLOAT && lookForSelectablePositionOnScreen < positionOfNewFocus) || (i == 33 && lookForSelectablePositionOnScreen > positionOfNewFocus))) {
                    return null;
                }
            }
            lookForSelectablePositionOnScreen = amountToScrollToNewFocus(i, findNextFocusFromRect, positionOfNewFocus);
            i2 = getMaxScrollAmount();
            if (lookForSelectablePositionOnScreen < i2) {
                findNextFocusFromRect.requestFocus(i);
                this.mArrowScrollFocusResult.populate(positionOfNewFocus, lookForSelectablePositionOnScreen);
                return this.mArrowScrollFocusResult;
            } else if (distanceToView(findNextFocusFromRect) < i2) {
                findNextFocusFromRect.requestFocus(i);
                this.mArrowScrollFocusResult.populate(positionOfNewFocus, i2);
                return this.mArrowScrollFocusResult;
            }
        }
        return null;
    }

    private int positionOfNewFocus(View view) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isViewAncestorOf(view, getChildAt(i))) {
                return i + this.mFirstPosition;
            }
        }
        throw new IllegalArgumentException("newFocus is not a child of any of the children of the list!");
    }

    private boolean isViewAncestorOf(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        boolean z = (parent instanceof ViewGroup) && isViewAncestorOf((View) parent, view2);
        return z;
    }

    private int amountToScrollToNewFocus(int i, View view, int i2) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        int i3;
        if (i != 33) {
            int height = getHeight() - this.mListPadding.bottom;
            if (this.mTempRect.bottom <= height) {
                return 0;
            }
            i3 = this.mTempRect.bottom - height;
            if (i2 < this.mItemCount - 1) {
                return i3 + getArrowScrollPreviewLength();
            }
            return i3;
        } else if (this.mTempRect.top >= this.mListPadding.top) {
            return 0;
        } else {
            i3 = this.mListPadding.top - this.mTempRect.top;
            return i2 > 0 ? i3 + getArrowScrollPreviewLength() : i3;
        }
    }

    private int distanceToView(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        int i = (this.mBottom - this.mTop) - this.mListPadding.bottom;
        if (this.mTempRect.bottom < this.mListPadding.top) {
            return this.mListPadding.top - this.mTempRect.bottom;
        }
        if (this.mTempRect.top > i) {
            return this.mTempRect.top - i;
        }
        return 0;
    }

    private void scrollListItemsBy(int i) {
        offsetChildrenTopAndBottom(i);
        int height = getHeight() - this.mListPadding.bottom;
        int i2 = this.mListPadding.top;
        RecycleBin recycleBin = this.mRecycler;
        int childCount;
        View childAt;
        View childAt2;
        if (i < 0) {
            childCount = getChildCount();
            childAt = getChildAt(childCount - 1);
            while (childAt.getBottom() < height) {
                int i3 = (this.mFirstPosition + childCount) - 1;
                if (i3 >= this.mItemCount - 1) {
                    break;
                }
                childAt = addViewBelow(childAt, i3);
                childCount++;
            }
            if (childAt.getBottom() < height) {
                offsetChildrenTopAndBottom(height - childAt.getBottom());
            }
            childAt2 = getChildAt(0);
            while (childAt2.getBottom() < i2) {
                if (recycleBin.shouldRecycleViewType(((LayoutParams) childAt2.getLayoutParams()).viewType)) {
                    detachViewFromParent(childAt2);
                    recycleBin.addScrapView(childAt2, this.mFirstPosition);
                } else {
                    removeViewInLayout(childAt2);
                }
                childAt = getChildAt(0);
                this.mFirstPosition++;
                childAt2 = childAt;
            }
            return;
        }
        childAt = getChildAt(0);
        while (childAt.getTop() > i2 && this.mFirstPosition > 0) {
            childAt = addViewAbove(childAt, this.mFirstPosition);
            this.mFirstPosition--;
        }
        if (childAt.getTop() > i2) {
            offsetChildrenTopAndBottom(i2 - childAt.getTop());
        }
        childCount = getChildCount() - 1;
        i2 = childCount;
        childAt2 = getChildAt(childCount);
        while (childAt2.getTop() > height) {
            if (recycleBin.shouldRecycleViewType(((LayoutParams) childAt2.getLayoutParams()).viewType)) {
                detachViewFromParent(childAt2);
                recycleBin.addScrapView(childAt2, this.mFirstPosition + i2);
            } else {
                removeViewInLayout(childAt2);
            }
            childCount = i2 - 1;
            i2 = childCount;
            childAt2 = getChildAt(childCount);
        }
    }

    private View addViewAbove(View view, int i) {
        int i2 = i - 1;
        View obtainView = obtainView(i2, this.mIsScrap);
        setupChild(obtainView, i2, view.getTop() - this.mDividerHeight, false, this.mListPadding.left, false, this.mIsScrap[0]);
        return obtainView;
    }

    private View addViewBelow(View view, int i) {
        int i2 = i + 1;
        View obtainView = obtainView(i2, this.mIsScrap);
        setupChild(obtainView, i2, this.mDividerHeight + view.getBottom(), true, this.mListPadding.left, false, this.mIsScrap[0]);
        return obtainView;
    }

    public void setItemsCanFocus(boolean z) {
        this.mItemsCanFocus = z;
        if (!z) {
            setDescendantFocusability(393216);
        }
    }

    public boolean getItemsCanFocus() {
        return this.mItemsCanFocus;
    }

    public boolean isOpaque() {
        return false;
    }

    public void setCacheColorHint(int i) {
        boolean z = (i >>> 24) == 255;
        this.mIsCacheColorOpaque = z;
        if (z) {
            if (this.mDividerPaint == null) {
                this.mDividerPaint = new Paint();
            }
            this.mDividerPaint.setColor(i);
        }
        super.setCacheColorHint(i);
    }

    void drawOverscrollHeader(Canvas canvas, View view, Drawable drawable, Drawable drawable2, Rect rect) {
        int intrinsicHeight;
        int save = canvas.save();
        if (drawable != null) {
            intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicHeight < rect.height()) {
                Rect rect2 = new Rect(rect);
                rect2.top = (rect2.top + rect.height()) - intrinsicHeight;
                drawable.setBounds(rect2);
            } else {
                drawable.setBounds(rect);
            }
            drawable.draw(canvas);
        }
        if (drawable2 != null) {
            intrinsicHeight = drawable2.getMinimumHeight();
            if (rect.bottom - rect.top < intrinsicHeight) {
                rect.top = rect.bottom - intrinsicHeight;
            }
            drawable2.setBounds(rect);
            drawable2.draw(canvas);
        }
        if (view != null) {
            checkOverscrollViewIsCompleteVisable(view);
            view.offsetTopAndBottom(rect.bottom - view.getBottom());
            drawChild(canvas, view, getDrawingTime());
        }
        canvas.restoreToCount(save);
    }

    void drawOverscrollFooter(Canvas canvas, View view, Drawable drawable, Rect rect) {
        int save = canvas.save();
        if (drawable != null) {
            int minimumHeight = drawable.getMinimumHeight();
            if (rect.bottom - rect.top < minimumHeight) {
                rect.top = rect.bottom - minimumHeight;
            }
            drawable.setBounds(rect);
            drawable.draw(canvas);
        }
        if (view != null) {
            checkOverscrollFooterViewIsCompleteVisable(view);
            view.offsetTopAndBottom(rect.top - view.getTop());
            drawChild(canvas, view, getDrawingTime());
        }
        canvas.restoreToCount(save);
    }

    void drawOverscrollFooter(Canvas canvas, Drawable drawable, Rect rect) {
        int minimumHeight = drawable.getMinimumHeight();
        canvas.save();
        canvas.clipRect(rect);
        if (rect.bottom - rect.top < minimumHeight) {
            rect.bottom = minimumHeight + rect.top;
        }
        drawable.setBounds(rect);
        drawable.draw(canvas);
        canvas.restore();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void dispatchDraw(android.graphics.Canvas r26) {
        /*
        r25 = this;
        r0 = r25;
        r2 = r0.mCachingStarted;
        if (r2 == 0) goto L_0x000b;
    L_0x0006:
        r2 = 1;
        r0 = r25;
        r0.mCachingActive = r2;
    L_0x000b:
        r0 = r25;
        r11 = r0.mDividerHeight;
        r0 = r25;
        r6 = r0.mOverScrollHeader;
        r0 = r25;
        r4 = r0.mOverscrollHeaderView;
        r0 = r25;
        r12 = r0.mOverscrollFooterView;
        r0 = r25;
        r13 = r0.mOverScrollFooter;
        r0 = r25;
        r5 = r0.mOverScrollHeaderShadow;
        if (r6 != 0) goto L_0x0029;
    L_0x0025:
        if (r4 != 0) goto L_0x0029;
    L_0x0027:
        if (r5 == 0) goto L_0x01ad;
    L_0x0029:
        r2 = 1;
        r3 = r2;
    L_0x002b:
        if (r13 != 0) goto L_0x002f;
    L_0x002d:
        if (r12 == 0) goto L_0x01b1;
    L_0x002f:
        r2 = 1;
        r10 = r2;
    L_0x0031:
        if (r11 <= 0) goto L_0x01b5;
    L_0x0033:
        r0 = r25;
        r2 = r0.mDivider;
        if (r2 == 0) goto L_0x01b5;
    L_0x0039:
        r2 = 1;
        r8 = r2;
    L_0x003b:
        r0 = r25;
        r2 = r0.mContentBackgroundDrawable;
        if (r2 == 0) goto L_0x006c;
    L_0x0041:
        r0 = r25;
        r2 = r0.mScrollY;
        if (r2 <= 0) goto L_0x01b9;
    L_0x0047:
        r0 = r25;
        r2 = r0.mContentBackgroundDrawable;
        r7 = 0;
        r0 = r25;
        r9 = r0.mScrollY;
        r14 = r25.getWidth();
        r15 = r25.getHeight();
        r0 = r25;
        r0 = r0.mScrollY;
        r16 = r0;
        r15 = r15 + r16;
        r2.setBounds(r7, r9, r14, r15);
    L_0x0063:
        r0 = r25;
        r2 = r0.mContentBackgroundDrawable;
        r0 = r26;
        r2.draw(r0);
    L_0x006c:
        if (r8 != 0) goto L_0x0072;
    L_0x006e:
        if (r3 != 0) goto L_0x0072;
    L_0x0070:
        if (r10 == 0) goto L_0x020a;
    L_0x0072:
        r0 = r25;
        r7 = r0.mTempRect;
        r0 = r25;
        r2 = r0.mPaddingLeft;
        r7.left = r2;
        r0 = r25;
        r2 = r0.mRight;
        r0 = r25;
        r9 = r0.mLeft;
        r2 = r2 - r9;
        r0 = r25;
        r9 = r0.mPaddingRight;
        r2 = r2 - r9;
        r7.right = r2;
        r14 = r25.getChildCount();
        r0 = r25;
        r2 = r0.mHeaderViewInfos;
        r15 = r2.size();
        r0 = r25;
        r2 = r0.mItemCount;
        r0 = r25;
        r9 = r0.mFooterViewInfos;
        r9 = r9.size();
        r2 = r2 - r9;
        r16 = r2 + -1;
        r0 = r25;
        r0 = r0.mHeaderDividersEnabled;
        r17 = r0;
        r0 = r25;
        r0 = r0.mFooterDividersEnabled;
        r18 = r0;
        r0 = r25;
        r0 = r0.mFirstPosition;
        r19 = r0;
        r0 = r25;
        r0 = r0.mAreAllItemsSelectable;
        r20 = r0;
        r0 = r25;
        r0 = r0.mAdapter;
        r21 = r0;
        r2 = com.tencent.util.VersionUtils.isECLAIR_MR1();
        if (r2 == 0) goto L_0x01d0;
    L_0x00cb:
        r2 = r25.isOpaque();
        if (r2 == 0) goto L_0x01cc;
    L_0x00d1:
        r2 = super.isOpaque();
        if (r2 != 0) goto L_0x01cc;
    L_0x00d7:
        r2 = 1;
        r9 = r2;
    L_0x00d9:
        if (r9 == 0) goto L_0x00fd;
    L_0x00db:
        r0 = r25;
        r2 = r0.mDividerPaint;
        if (r2 != 0) goto L_0x00fd;
    L_0x00e1:
        r0 = r25;
        r2 = r0.mIsCacheColorOpaque;
        if (r2 == 0) goto L_0x00fd;
    L_0x00e7:
        r2 = new android.graphics.Paint;
        r2.<init>();
        r0 = r25;
        r0.mDividerPaint = r2;
        r0 = r25;
        r2 = r0.mDividerPaint;
        r22 = r25.getCacheColorHint();
        r0 = r22;
        r2.setColor(r0);
    L_0x00fd:
        r0 = r25;
        r0 = r0.mDividerPaint;
        r22 = r0;
        r2 = 0;
        r0 = r25;
        r0 = r0.mGroupFlags;
        r23 = r0;
        r23 = r23 & 34;
        r24 = 34;
        r0 = r23;
        r1 = r24;
        if (r0 != r1) goto L_0x011a;
    L_0x0114:
        r0 = r25;
        r2 = r0.mListPadding;
        r2 = r2.bottom;
    L_0x011a:
        r0 = r25;
        r0 = r0.mBottom;
        r23 = r0;
        r0 = r25;
        r0 = r0.mTop;
        r24 = r0;
        r23 = r23 - r24;
        r2 = r23 - r2;
        r23 = r25.getScrollY();
        r23 = r23 + r2;
        r2 = r25.getScrollY();
        if (r14 <= 0) goto L_0x015b;
    L_0x0136:
        if (r2 >= 0) goto L_0x01d7;
    L_0x0138:
        if (r3 == 0) goto L_0x014b;
    L_0x013a:
        r0 = r25;
        r3 = r0.mDividerHeight;
        r3 = 0 - r3;
        r7.bottom = r3;
        r7.top = r2;
        r2 = r25;
        r3 = r26;
        r2.drawOverscrollHeader(r3, r4, r5, r6, r7);
    L_0x014b:
        if (r8 == 0) goto L_0x015b;
    L_0x014d:
        r2 = 0;
        r7.bottom = r2;
        r2 = -r11;
        r7.top = r2;
        r2 = -1;
        r0 = r25;
        r1 = r26;
        r0.drawDivider(r1, r7, r2);
    L_0x015b:
        if (r8 == 0) goto L_0x020a;
    L_0x015d:
        r2 = 0;
    L_0x015e:
        if (r2 >= r14) goto L_0x020a;
    L_0x0160:
        if (r17 != 0) goto L_0x0166;
    L_0x0162:
        r3 = r19 + r2;
        if (r3 < r15) goto L_0x01aa;
    L_0x0166:
        if (r18 != 0) goto L_0x016e;
    L_0x0168:
        r3 = r19 + r2;
        r0 = r16;
        if (r3 >= r0) goto L_0x01aa;
    L_0x016e:
        r0 = r25;
        r3 = r0.getChildAt(r2);
        r3 = r3.getBottom();
        r0 = r23;
        if (r3 >= r0) goto L_0x01aa;
    L_0x017c:
        if (r10 == 0) goto L_0x0182;
    L_0x017e:
        r4 = r14 + -1;
        if (r2 == r4) goto L_0x01aa;
    L_0x0182:
        if (r20 != 0) goto L_0x019e;
    L_0x0184:
        r4 = r19 + r2;
        r0 = r21;
        r4 = r0.isEnabled(r4);
        if (r4 == 0) goto L_0x01fb;
    L_0x018e:
        r4 = r14 + -1;
        if (r2 == r4) goto L_0x019e;
    L_0x0192:
        r4 = r19 + r2;
        r4 = r4 + 1;
        r0 = r21;
        r4 = r0.isEnabled(r4);
        if (r4 == 0) goto L_0x01fb;
    L_0x019e:
        r7.top = r3;
        r3 = r3 + r11;
        r7.bottom = r3;
        r0 = r25;
        r1 = r26;
        r0.drawDivider(r1, r7, r2);
    L_0x01aa:
        r2 = r2 + 1;
        goto L_0x015e;
    L_0x01ad:
        r2 = 0;
        r3 = r2;
        goto L_0x002b;
    L_0x01b1:
        r2 = 0;
        r10 = r2;
        goto L_0x0031;
    L_0x01b5:
        r2 = 0;
        r8 = r2;
        goto L_0x003b;
    L_0x01b9:
        r0 = r25;
        r2 = r0.mContentBackgroundDrawable;
        r7 = 0;
        r9 = 0;
        r14 = r25.getWidth();
        r15 = r25.getHeight();
        r2.setBounds(r7, r9, r14, r15);
        goto L_0x0063;
    L_0x01cc:
        r2 = 0;
        r9 = r2;
        goto L_0x00d9;
    L_0x01d0:
        r2 = r25.isOpaque();
        r9 = r2;
        goto L_0x00d9;
    L_0x01d7:
        if (r2 <= 0) goto L_0x014b;
    L_0x01d9:
        if (r10 == 0) goto L_0x014b;
    L_0x01db:
        r0 = r25;
        r3 = r0.mBottom;
        r2 = r2 + r3;
        r0 = r25;
        r3 = r0.mDividerHeight;
        r2 = r2 + r3;
        r0 = r25;
        r3 = r0.mBottom;
        r0 = r25;
        r4 = r0.mDividerHeight;
        r3 = r3 + r4;
        r7.top = r3;
        r7.bottom = r2;
        r0 = r25;
        r1 = r26;
        r0.drawOverscrollFooter(r1, r12, r13, r7);
        goto L_0x014b;
    L_0x01fb:
        if (r9 == 0) goto L_0x01aa;
    L_0x01fd:
        r7.top = r3;
        r3 = r3 + r11;
        r7.bottom = r3;
        r0 = r26;
        r1 = r22;
        r0.drawRect(r7, r1);
        goto L_0x01aa;
    L_0x020a:
        super.dispatchDraw(r26);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.widget.ListView.dispatchDraw(android.graphics.Canvas):void");
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild = super.drawChild(canvas, view, j);
        if (this.mCachingActive) {
            this.mCachingActive = false;
        }
        return drawChild;
    }

    void drawDivider(Canvas canvas, Rect rect, int i) {
        Drawable drawable = this.mDivider;
        drawable.setBounds(rect);
        drawable.draw(canvas);
    }

    public Drawable getDivider() {
        return this.mDivider;
    }

    public void setDivider(Drawable drawable) {
        boolean z = false;
        if (drawable != null) {
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerHeight = 0;
        }
        this.mDividerHeight = this.mDividerHeight < 0 ? 0 : this.mDividerHeight;
        this.mDivider = drawable;
        if (drawable == null || drawable.getOpacity() == -1) {
            z = true;
        }
        this.mDividerIsOpaque = z;
        requestLayout();
        invalidate();
    }

    public int getDividerHeight() {
        return this.mDividerHeight;
    }

    public void setDividerHeight(int i) {
        this.mDividerHeight = i;
        requestLayout();
        invalidate();
    }

    public void setHeaderDividersEnabled(boolean z) {
        this.mHeaderDividersEnabled = z;
        invalidate();
    }

    public void setFooterDividersEnabled(boolean z) {
        this.mFooterDividersEnabled = z;
        invalidate();
    }

    public void setOverscrollHeader(Drawable drawable) {
        this.mOverScrollHeader = drawable;
        if (getScrollY() < 0) {
            invalidate();
        }
    }

    public Drawable getOverscrollHeader() {
        return this.mOverScrollHeader;
    }

    public void setOverscrollFooter(Drawable drawable) {
        this.mOverScrollFooter = drawable;
        invalidate();
    }

    public Drawable getOverscrollFooter() {
        return this.mOverScrollFooter;
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        int i2 = 0;
        super.onFocusChanged(z, i, rect);
        ListAdapter listAdapter = this.mAdapter;
        int i3 = -1;
        if (!(listAdapter == null || !z || rect == null)) {
            rect.offset(getScrollX(), getScrollY());
            if (listAdapter.getCount() < getChildCount() + this.mFirstPosition) {
                this.mLayoutMode = 0;
                layoutChildren();
            }
            Rect rect2 = this.mTempRect;
            int i4 = Integer.MAX_VALUE;
            int childCount = getChildCount();
            int i5 = this.mFirstPosition;
            int i6 = 0;
            while (i6 < childCount) {
                int distance;
                if (listAdapter.isEnabled(i5 + i6)) {
                    View childAt = getChildAt(i6);
                    childAt.getDrawingRect(rect2);
                    offsetDescendantRectToMyCoords(childAt, rect2);
                    distance = AbsListView.getDistance(rect, rect2, i);
                    if (distance < i4) {
                        i2 = i6;
                        i4 = distance;
                        distance = childAt.getTop();
                    } else {
                        distance = i2;
                        i2 = i3;
                    }
                } else {
                    distance = i2;
                    i2 = i3;
                }
                i6++;
                i3 = i2;
                i2 = distance;
            }
        }
        if (i3 >= 0) {
            setSelectionFromTop(this.mFirstPosition + i3, i2);
        } else {
            requestLayout();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                addHeaderView(getChildAt(i));
            }
            removeAllViews();
        }
    }

    @Deprecated
    public long[] getCheckItemIds() {
        if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
            return getCheckedItemIds();
        }
        if (this.mChoiceMode == 0 || this.mCheckStates == null || this.mAdapter == null) {
            return new long[0];
        }
        SparseBooleanArray sparseBooleanArray = this.mCheckStates;
        int size = sparseBooleanArray.size();
        Object obj = new long[size];
        ListAdapter listAdapter = this.mAdapter;
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            if (sparseBooleanArray.valueAt(i)) {
                i3 = i2 + 1;
                obj[i2] = listAdapter.getItemId(sparseBooleanArray.keyAt(i));
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        if (i2 == size) {
            return obj;
        }
        long[] jArr = new long[i2];
        System.arraycopy(obj, 0, jArr, 0, i2);
        return jArr;
    }

    public void setOverScrollHeader(View view) {
        if (view != null) {
            if (this.mOverscrollHeaderView == null) {
                this.mOverscrollHeaderView = new OverscrollViewContainer(getContext());
                OverscrollViewContainer.access$200(this.mOverscrollHeaderView, this);
            }
            this.mOverscrollHeaderView.removeAllViewsInLayout();
            this.mOverscrollHeaderView.addView(view);
        } else if (this.mOverscrollHeaderView != null) {
            this.mOverscrollHeaderView.removeAllViewsInLayout();
            OverscrollViewContainer.access$200(this.mOverscrollHeaderView, null);
            this.mOverscrollHeaderView = null;
        }
        this.mOverscrollHeadState = 0;
        this.mScrollY = 0;
    }

    public void setOverScrollFooter(View view) {
        if (view != null) {
            if (this.mOverscrollFooterView == null) {
                this.mOverscrollFooterView = new OverscrollViewContainer(getContext());
                OverscrollViewContainer.access$200(this.mOverscrollFooterView, this);
            }
            this.mOverscrollFooterView.removeAllViewsInLayout();
            this.mOverscrollFooterView.addView(view);
        } else if (this.mOverscrollFooterView != null) {
            this.mOverscrollFooterView.removeAllViewsInLayout();
            OverscrollViewContainer.access$200(this.mOverscrollFooterView, null);
            this.mOverscrollFooterView = null;
        }
        this.mOverscrollHeadState = 0;
        this.mScrollY = 0;
    }

    public View getOverScrollHeaderView() {
        return this.mOverscrollHeaderView;
    }

    public View getOverScrollFooterView() {
        return this.mOverscrollFooterView;
    }

    public void setOverScrollListener(OverScrollViewListener overScrollViewListener) {
        this.mOverScrollViewListener = overScrollViewListener;
    }

    protected int getSpringbackOffset() {
        int scrollY = getScrollY();
        boolean onViewCompleteVisableAndReleased;
        if (this.mOverscrollHeaderView == null || scrollY >= 0) {
            if (this.mOverscrollFooterView == null || scrollY <= 0) {
                return 0;
            }
            if (scrollY > getOverScrollFooterHeight()) {
                if (this.mOverscrollHeadState == 2) {
                    if (this.mOverScrollViewListener != null) {
                        onViewCompleteVisableAndReleased = this.mOverScrollViewListener.onViewCompleteVisableAndReleased(1, this.mOverscrollFooterView.getChildAt(0), this);
                    } else {
                        onViewCompleteVisableAndReleased = false;
                    }
                    this.mOverscrollHeadState = 3;
                } else {
                    onViewCompleteVisableAndReleased = false;
                }
                if (!onViewCompleteVisableAndReleased) {
                    this.mOverscrollHeadState = 0;
                    return 0;
                } else if (this.mOverscrollFooterView != null) {
                    return getOverScrollFooterHeight();
                } else {
                    return 0;
                }
            } else if (this.mOverscrollHeadState >= 2 && !this.mEnsureOverScrollStatusToIdleWhenRelease) {
                return 0;
            } else {
                if (this.mOverScrollViewListener != null) {
                    this.mOverScrollViewListener.onViewNotCompleteVisableAndReleased(1, this.mOverscrollFooterView.getChildAt(0), this);
                }
                this.mOverscrollHeadState = 0;
                return 0;
            }
        } else if (scrollY <= (-getOverScrollHeight())) {
            if (this.mOverscrollHeadState == 2) {
                if (this.mOverScrollViewListener != null) {
                    onViewCompleteVisableAndReleased = this.mOverScrollViewListener.onViewCompleteVisableAndReleased(0, this.mOverscrollHeaderView.getChildAt(0), this);
                } else {
                    onViewCompleteVisableAndReleased = false;
                }
                this.mOverscrollHeadState = 3;
            } else {
                onViewCompleteVisableAndReleased = false;
            }
            if (!onViewCompleteVisableAndReleased) {
                this.mOverscrollHeadState = 0;
                return 0;
            } else if (this.mOverscrollHeaderView != null) {
                return -getOverScrollHeight();
            } else {
                return 0;
            }
        } else if (this.mOverscrollHeadState >= 2 && !this.mEnsureOverScrollStatusToIdleWhenRelease) {
            return 0;
        } else {
            if (this.mOverScrollViewListener != null) {
                this.mOverScrollViewListener.onViewNotCompleteVisableAndReleased(0, this.mOverscrollHeaderView.getChildAt(0), this);
            }
            this.mOverscrollHeadState = 0;
            return 0;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.isTouchHolding = true;
        } else if (action == 1 || action == 3) {
            this.isTouchHolding = false;
        }
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        checkOverScrollHeaderIsVisable();
        checkOverScrollFooterIsVisable();
        return onTouchEvent;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        checkOverScrollHeaderIsVisable();
        checkOverScrollFooterIsVisable();
        return onInterceptTouchEvent;
    }

    private void checkOverScrollHeaderIsVisable() {
        if (this.mTouchMode == 5 && getScrollY() != 0) {
            int scrollY = getScrollY();
            if (scrollY < 0 && this.mOverscrollHeaderView != null && scrollY > (-getOverScrollHeight())) {
                if (this.mOverscrollHeadState == 0 || this.mOverscrollHeadState == 2) {
                    if (this.mOverScrollViewListener != null) {
                        this.mOverScrollViewListener.onNotCompleteVisable(0, this.mOverscrollHeaderView.getChildAt(0), this);
                    }
                    this.mOverscrollHeadState = 1;
                }
            }
        }
    }

    private void checkOverscrollViewIsCompleteVisable(View view) {
        int scrollY = getScrollY();
        view.getHeight();
        if (view == this.mOverscrollHeaderView && this.mOverscrollHeadState == 1 && scrollY <= (-getOverScrollHeight())) {
            this.mOverscrollHeadState = 2;
            if (this.mOverScrollViewListener != null) {
                this.mOverScrollViewListener.onViewCompleteVisable(0, this.mOverscrollHeaderView.getChildAt(0), this);
            }
        }
    }

    private void checkOverScrollFooterIsVisable() {
        if (this.mTouchMode == 5 && getScrollY() != 0) {
            int scrollY = getScrollY();
            if (scrollY > 0 && this.mOverscrollFooterView != null && scrollY < getOverScrollFooterHeight()) {
                if (this.mOverscrollHeadState == 0 || this.mOverscrollHeadState == 2) {
                    if (this.mOverScrollViewListener != null) {
                        this.mOverScrollViewListener.onNotCompleteVisable(1, this.mOverscrollFooterView.getChildAt(0), this);
                    }
                    this.mOverscrollHeadState = 1;
                }
            }
        }
    }

    private void checkOverscrollFooterViewIsCompleteVisable(View view) {
        int scrollY = getScrollY();
        view.getHeight();
        if (view == this.mOverscrollFooterView && this.mOverscrollHeadState == 1 && scrollY > getOverScrollFooterHeight()) {
            this.mOverscrollHeadState = 2;
            if (this.mOverScrollViewListener != null) {
                this.mOverScrollViewListener.onViewCompleteVisable(1, this.mOverscrollFooterView.getChildAt(0), this);
            }
        }
    }

    public void springBackOverScrollHeaderView() {
        if (getScrollY() < 0 && !this.isTouchHolding) {
            doSpringBack();
        }
        this.mOverscrollHeadState = 0;
    }

    public void hideOverScrollHeaderView() {
        if (this.mScrollY < 0) {
            abordFling();
            if (this.mStackFromBottom) {
                this.mLayoutMode = 100;
                View childAt = getChildAt(0);
                setSelectionFromBottom(this.mFirstPosition, (childAt == null ? 0 : (this.mLayoutHeight - childAt.getBottom()) - this.mListPadding.bottom) + this.mScrollY);
            } else {
                setSelectionFromTop(this.mFirstPosition, this.mListPadding.top - this.mScrollY);
            }
            onScrollChanged(0, 0, 0, this.mScrollY);
            this.mScrollY = 0;
        }
        this.mOverscrollHeadState = 0;
    }

    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        ViewParent invalidateChildInParent = super.invalidateChildInParent(iArr, rect);
        if (rect.bottom > 0 && rect.top < getHeight()) {
            int scrollY = getScrollY();
            if (scrollY < 0 && rect.top + scrollY < 0) {
                invalidateWithoutNotiyParent(rect);
            } else if (scrollY > 0 && rect.bottom > getHeight() - scrollY) {
                invalidateWithoutNotiyParent(rect);
            }
        }
        return invalidateChildInParent;
    }

    private void invalidateWithoutNotiyParent(Rect rect) {
        try {
            Field declaredField = View.class.getDeclaredField("mPrivateFlags");
            declaredField.setAccessible(true);
            declaredField.set(this, Integer.valueOf(declaredField.getInt(this) | Integer.MIN_VALUE));
        } catch (Exception e) {
            postInvalidate(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    private void attachWindow(View view) {
        try {
            Field declaredField = View.class.getDeclaredField("mAttachInfo");
            declaredField.setAccessible(true);
            if (declaredField.get(this) != null) {
                Method declaredMethod = View.class.getDeclaredMethod("dispatchAttachedToWindow", new Class[]{declaredField.get(this).getClass(), Integer.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[]{r0, Integer.valueOf(getVisibility())});
            }
        } catch (Exception e) {
        }
    }

    private void detachedWindow(View view) {
        try {
            Field declaredField = View.class.getDeclaredField("mAttachInfo");
            declaredField.setAccessible(true);
            if (declaredField.get(view) != null) {
                Method declaredMethod = View.class.getDeclaredMethod("dispatchDetachedFromWindow", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[0]);
            }
        } catch (Exception e) {
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        if (i2 < 0 && i4 == 0 && this.mOverscrollHeaderView != null) {
            attachWindow(this.mOverscrollHeaderView);
        } else if (i2 == 0 && i4 < 0 && this.mOverscrollHeaderView != null) {
            detachedWindow(this.mOverscrollHeaderView);
        }
    }

    protected AdapterDataSetObserver newObserver() {
        return new ListDataSetObserver(this);
    }

    public void setDelAnimationDuration(long j) {
        if (j > 0) {
            this.delAnimDuration = j;
        }
    }

    public long getDelAnimationDuration() {
        if (this.delAnimDuration > 0) {
            return this.delAnimDuration;
        }
        return 350;
    }

    public void clearDelAnim() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getAnimation() != null) {
                childAt.clearAnimation();
            }
        }
    }

    public void setContentBackground(int i) {
        setContentBackground(getContext().getResources().getDrawable(i));
    }

    public void setContentBackground(Drawable drawable) {
        if (drawable == null) {
            this.mContentBackgroundDrawable = null;
            this.mOverScrollHeaderShadow = null;
            return;
        }
        this.mContentBackgroundDrawable = drawable;
        this.mOverScrollHeaderShadow = getResources().getDrawable(R.drawable.listview_header_shadow);
    }

    public void showOverScrollHeader() {
        scrollTo(0, (-getOverScrollHeight()) - 1);
        if (this.mOverScrollViewListener == null) {
            return;
        }
        if (this.mOverScrollViewListener.onViewCompleteVisableAndReleased((-getOverScrollHeight()) - 1, this.mOverscrollHeaderView.getChildAt(0), this)) {
            this.mOverscrollHeadState = 3;
        } else {
            springBackOverScrollHeaderView();
        }
    }

    public boolean isOverscrollHeadVisiable() {
        return this.mScrollY < 0 && this.mOverscrollHeaderView != null;
    }

    public int getOverScrollHeadState() {
        return this.mOverscrollHeadState;
    }
}
