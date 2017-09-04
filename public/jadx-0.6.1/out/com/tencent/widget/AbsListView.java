package com.tencent.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewDebug.IntToString;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filter.FilterListener;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.iflytek.cloud.SpeechError;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.util.LongSparseArray;
import com.tencent.util.VersionUtils;
import com.tencent.widget.AdapterView.AdapterContextMenuInfo;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import tencent.tls.platform.SigType;

public abstract class AbsListView extends AdapterView<ListAdapter> implements TextWatcher, OnGlobalLayoutListener, OnTouchModeChangeListener, FilterListener {
    private static final int[] ABSLISTVIEW = AdapterView.getStyleableValues("AbsListView");
    private static final int ABSLISTVIEW_CACHECOLORHINT = AdapterView.getStyleableValue("AbsListView_cacheColorHint");
    private static final int ABSLISTVIEW_CHOICEMODE = AdapterView.getStyleableValue("AbsListView_choiceMode");
    private static final int ABSLISTVIEW_DRAWSELECTORONTOP = AdapterView.getStyleableValue("AbsListView_drawSelectorOnTop");
    private static final int ABSLISTVIEW_FASTSCROLLALWAYSVISIBLE = AdapterView.getStyleableValue("AbsListView_fastScrollAlwaysVisible");
    private static final int ABSLISTVIEW_FASTSCROLLENABLED = AdapterView.getStyleableValue("AbsListView_fastScrollEnabled");
    private static final int ABSLISTVIEW_LIST_SELECTOR = AdapterView.getStyleableValue("AbsListView_listSelector");
    private static final int ABSLISTVIEW_SCROLLINGCACHE = AdapterView.getStyleableValue("AbsListView_scrollingCache");
    private static final int ABSLISTVIEW_SMOOTHSCROLLBAR = AdapterView.getStyleableValue("AbsListView_smoothScrollbar");
    private static final int ABSLISTVIEW_STACKFROMBOTTOM = AdapterView.getStyleableValue("AbsListView_stackFromBottom");
    private static final int ABSLISTVIEW_TEXTFILTERENABLED = AdapterView.getStyleableValue("AbsListView_textFilterEnabled");
    private static final int ABSLISTVIEW_TRANSCRIPTMODE = AdapterView.getStyleableValue("AbsListView_transcriptMode");
    private static final int CHECK_POSITION_SEARCH_DISTANCE = 20;
    public static final int CHOICE_MODE_MULTIPLE = 2;
    public static final int CHOICE_MODE_MULTIPLE_MODAL = 3;
    public static final int CHOICE_MODE_NONE = 0;
    public static final int CHOICE_MODE_SINGLE = 1;
    private static final int INVALID_POINTER = -1;
    static final int LAYOUT_FORCE_BOTTOM = 3;
    static final int LAYOUT_FORCE_TOP = 1;
    static final int LAYOUT_MOVE_SELECTION = 6;
    static final int LAYOUT_NORMAL = 0;
    static final int LAYOUT_SET_SELECTION = 2;
    static final int LAYOUT_SPECIFIC = 4;
    static final int LAYOUT_SPECIFIC_BOTTOM = 100;
    static final int LAYOUT_SYNC = 5;
    private static final int[] NOTHING = new int[]{0};
    static final int OVERSCROLL_LIMIT_DIVISOR = 3;
    private static final int OVER_FLING_DISTANCE = 30;
    private static final boolean PROFILE_FLINGING = false;
    private static final boolean PROFILE_SCROLLING = false;
    static final int TOUCH_MODE_DONE_WAITING = 2;
    static final int TOUCH_MODE_DOWN = 0;
    static final int TOUCH_MODE_FLING = 4;
    private static final int TOUCH_MODE_OFF = 1;
    private static final int TOUCH_MODE_ON = 0;
    static final int TOUCH_MODE_OVERFLING = 6;
    static final int TOUCH_MODE_OVERSCROLL = 5;
    static final int TOUCH_MODE_REST = -1;
    static final int TOUCH_MODE_SCROLL = 3;
    static final int TOUCH_MODE_TAP = 1;
    private static final int TOUCH_MODE_UNKNOWN = -1;
    public static final int TRANSCRIPT_MODE_ALWAYS_SCROLL = 2;
    public static final int TRANSCRIPT_MODE_DISABLED = 0;
    public static final int TRANSCRIPT_MODE_NORMAL = 1;
    private int mActivePointerId;
    ListAdapter mAdapter;
    int mBottomOverflingDistance;
    private MoveToBottomScroller mBottomScroller;
    int mCacheColorHint;
    boolean mCachingActive;
    boolean mCachingStarted;
    private boolean mCallbackOnUnClickItem;
    SparseBooleanArray mCheckStates;
    LongSparseArray<Integer> mCheckedIdStates;
    int mCheckedItemCount;
    ActionMode mChoiceActionMode;
    int mChoiceMode;
    private Runnable mClearScrollingCache;
    private ContextMenuInfo mContextMenuInfo;
    AdapterDataSetObserver mDataSetObserver;
    private InputConnection mDefInputConnection;
    private boolean mDeferNotifyDataSetChanged;
    private float mDensityScale;
    private int mDirection;
    boolean mDrawSelectorOnTop;
    private boolean mEdgeEffectEnabled;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    boolean mFastScrollEnabled;
    FastScroller mFastScroller;
    private boolean mFiltered;
    private int mFirstPositionDistanceGuess;
    private boolean mFlingProfilingStarted;
    private FlingRunnable mFlingRunnable;
    private Object mFlingStrictSpan;
    private boolean mForceTranscriptScroll;
    private boolean mGlobalLayoutListenerAddedFilter;
    private int mGlowPaddingLeft;
    private int mGlowPaddingRight;
    boolean mIsAttached;
    private boolean mIsChildViewEnabled;
    final boolean[] mIsScrap;
    private int mLastAccessibilityScrollEventFromIndex;
    private int mLastAccessibilityScrollEventToIndex;
    private int mLastHandledItemCount;
    private int mLastPositionDistanceGuess;
    private int mLastScrollState;
    private int mLastTouchMode;
    int mLastY;
    int mLayoutMode;
    Rect mListPadding;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    int mMotionCorrection;
    int mMotionPosition;
    int mMotionViewNewTop;
    int mMotionViewOriginalTop;
    int mMotionX;
    int mMotionY;
    MultiChoiceModeWrapper mMultiChoiceModeCallback;
    private OnScrollListener mOnScrollListener;
    private int mOverScrollMode;
    int mOverscrollDistance;
    int mOverscrollMax;
    private CheckForKeyLongPress mPendingCheckForKeyLongPress;
    private CheckForLongPress mPendingCheckForLongPress;
    private Runnable mPendingCheckForTap;
    private PerformClick mPerformClick;
    PopupWindow mPopup;
    private boolean mPopupHidden;
    PositionScroller mPositionScroller;
    private InputConnectionWrapper mPublicInputConnection;
    final RecycleBin mRecycler;
    int mResurrectToPosition;
    View mScrollDown;
    private boolean mScrollProfilingStarted;
    private Object mScrollStrictSpan;
    protected boolean mScrollToBottom;
    View mScrollUp;
    boolean mScrollingCacheEnabled;
    int mSelectedTop;
    int mSelectionBottomPadding;
    int mSelectionLeftPadding;
    int mSelectionRightPadding;
    int mSelectionTopPadding;
    Drawable mSelector;
    int mSelectorPosition;
    Rect mSelectorRect;
    private boolean mSmoothScrollbarEnabled;
    boolean mStackFromBottom;
    EditText mTextFilter;
    private boolean mTextFilterEnabled;
    int mTopOverflingDistance;
    private Rect mTouchFrame;
    int mTouchMode;
    private Runnable mTouchModeReset;
    private int mTouchSlop;
    private int mTranscriptMode;
    private float mVelocityScale;
    private VelocityTracker mVelocityTracker;
    int mWidthMeasureSpec;

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams {
        @ExportedProperty(category = "list")
        boolean forceAdd;
        @ExportedProperty(category = "list")
        boolean recycledHeaderFooter;
        int scrappedFromPosition;
        @ExportedProperty(category = "list", mapping = {@IntToString(from = -1, to = "ITEM_VIEW_TYPE_IGNORE"), @IntToString(from = -2, to = "ITEM_VIEW_TYPE_HEADER_OR_FOOTER")})
        int viewType;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2);
            this.viewType = i3;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    abstract void fillGap(boolean z);

    abstract int findMotionRow(int i);

    abstract void setSelectionInt(int i);

    public AbsListView(Context context) {
        super(context);
        this.mChoiceMode = 0;
        this.mLayoutMode = 0;
        this.mEdgeEffectEnabled = false;
        this.mDeferNotifyDataSetChanged = false;
        this.mDrawSelectorOnTop = false;
        this.mSelectorPosition = -1;
        this.mSelectorRect = new Rect();
        this.mRecycler = new RecycleBin(this);
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mListPadding = new Rect();
        this.mWidthMeasureSpec = 0;
        this.mTouchMode = -1;
        this.mSelectedTop = 0;
        this.mSmoothScrollbarEnabled = true;
        this.mResurrectToPosition = -1;
        this.mContextMenuInfo = null;
        this.mLastTouchMode = -1;
        this.mScrollProfilingStarted = false;
        this.mFlingProfilingStarted = false;
        this.mScrollStrictSpan = null;
        this.mFlingStrictSpan = null;
        this.mLastScrollState = 0;
        this.mVelocityScale = 1.0f;
        this.mIsScrap = new boolean[1];
        this.mActivePointerId = -1;
        this.mDirection = 0;
        this.mCallbackOnUnClickItem = false;
        initAbsListView();
        setVerticalScrollBarEnabled(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(AdapterView.getStyleableValues("View"));
        initializeScrollbars(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public AbsListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842858);
    }

    public AbsListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChoiceMode = 0;
        this.mLayoutMode = 0;
        this.mEdgeEffectEnabled = false;
        this.mDeferNotifyDataSetChanged = false;
        this.mDrawSelectorOnTop = false;
        this.mSelectorPosition = -1;
        this.mSelectorRect = new Rect();
        this.mRecycler = new RecycleBin(this);
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mListPadding = new Rect();
        this.mWidthMeasureSpec = 0;
        this.mTouchMode = -1;
        this.mSelectedTop = 0;
        this.mSmoothScrollbarEnabled = true;
        this.mResurrectToPosition = -1;
        this.mContextMenuInfo = null;
        this.mLastTouchMode = -1;
        this.mScrollProfilingStarted = false;
        this.mFlingProfilingStarted = false;
        this.mScrollStrictSpan = null;
        this.mFlingStrictSpan = null;
        this.mLastScrollState = 0;
        this.mVelocityScale = 1.0f;
        this.mIsScrap = new boolean[1];
        this.mActivePointerId = -1;
        this.mDirection = 0;
        this.mCallbackOnUnClickItem = false;
        initAbsListView();
        TypedArrayWarpper typedArrayWarpper = new TypedArrayWarpper(context.obtainStyledAttributes(attributeSet, ABSLISTVIEW, i, 0));
        Drawable drawable = typedArrayWarpper.getDrawable(ABSLISTVIEW_LIST_SELECTOR);
        if (drawable != null) {
            setSelector(drawable);
        }
        this.mDrawSelectorOnTop = typedArrayWarpper.getBoolean(ABSLISTVIEW_DRAWSELECTORONTOP, false);
        setStackFromBottom(typedArrayWarpper.getBoolean(ABSLISTVIEW_STACKFROMBOTTOM, false));
        setScrollingCacheEnabled(typedArrayWarpper.getBoolean(ABSLISTVIEW_SCROLLINGCACHE, true));
        setTextFilterEnabled(typedArrayWarpper.getBoolean(ABSLISTVIEW_TEXTFILTERENABLED, false));
        setTranscriptMode(typedArrayWarpper.getInt(ABSLISTVIEW_TRANSCRIPTMODE, 0));
        setCacheColorHint(typedArrayWarpper.getColor(ABSLISTVIEW_CACHECOLORHINT, 0));
        setFastScrollEnabled(typedArrayWarpper.getBoolean(ABSLISTVIEW_FASTSCROLLENABLED, false));
        setSmoothScrollbarEnabled(typedArrayWarpper.getBoolean(ABSLISTVIEW_SMOOTHSCROLLBAR, true));
        setChoiceMode(typedArrayWarpper.getInt(ABSLISTVIEW_CHOICEMODE, 0));
        setFastScrollAlwaysVisible(typedArrayWarpper.getBoolean(ABSLISTVIEW_FASTSCROLLALWAYSVISIBLE, false));
        typedArrayWarpper.recycle();
    }

    @TargetApi(9)
    private void initAbsListView() {
        setClickable(true);
        setFocusableInTouchMode(true);
        setWillNotDraw(false);
        setAlwaysDrawnWithCacheEnabled(false);
        setScrollingCacheEnabled(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext().getApplicationContext());
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        float f = getResources().getDisplayMetrics().density;
        this.mOverscrollDistance = (int) ((0.0f * f) + 0.5f);
        int i = (int) ((f * 30.0f) + 0.5f);
        this.mBottomOverflingDistance = i;
        this.mTopOverflingDistance = i;
        this.mDensityScale = getContext().getResources().getDisplayMetrics().density;
        setOverScrollMode(0);
        setVerticalFadingEdgeEnabled(false);
        setFriction(0.005f);
    }

    public void setOverScrollMode(int i) {
        if (i == 0 || i == 1 || i == 2) {
            if (i == 2) {
                this.mEdgeGlowTop = null;
                this.mEdgeGlowBottom = null;
            } else if (this.mEdgeGlowTop == null && this.mEdgeEffectEnabled) {
                Context context = getContext();
                this.mEdgeGlowTop = new EdgeEffect(context);
                this.mEdgeGlowBottom = new EdgeEffect(context);
            }
            this.mOverScrollMode = i;
            return;
        }
        throw new IllegalArgumentException("Invalid overscroll mode " + i);
    }

    public void setEdgeEffectEnabled(boolean z) {
        if (this.mEdgeEffectEnabled != z) {
            if (!z) {
                this.mEdgeGlowTop = null;
                this.mEdgeGlowBottom = null;
            } else if (this.mOverScrollMode != 2 && this.mEdgeGlowTop == null) {
                Context context = getContext();
                this.mEdgeGlowTop = new EdgeEffect(context);
                this.mEdgeGlowBottom = new EdgeEffect(context);
            }
            this.mEdgeEffectEnabled = z;
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null && this.mChoiceMode != 0 && this.mAdapter.hasStableIds() && this.mCheckedIdStates == null) {
            this.mCheckedIdStates = new LongSparseArray();
        }
        if (this.mCheckStates != null) {
            this.mCheckStates.clear();
        }
        if (this.mCheckedIdStates != null) {
            this.mCheckedIdStates.clear();
        }
    }

    public int getCheckedItemCount() {
        return this.mCheckedItemCount;
    }

    public boolean isItemChecked(int i) {
        if (this.mChoiceMode == 0 || this.mCheckStates == null) {
            return false;
        }
        return this.mCheckStates.get(i);
    }

    public int getCheckedItemPosition() {
        if (this.mChoiceMode == 1 && this.mCheckStates != null && this.mCheckStates.size() == 1) {
            return this.mCheckStates.keyAt(0);
        }
        return -1;
    }

    public SparseBooleanArray getCheckedItemPositions() {
        if (this.mChoiceMode != 0) {
            return this.mCheckStates;
        }
        return null;
    }

    public long[] getCheckedItemIds() {
        int i = 0;
        if (this.mChoiceMode == 0 || this.mCheckedIdStates == null || this.mAdapter == null) {
            return new long[0];
        }
        LongSparseArray longSparseArray = this.mCheckedIdStates;
        int size = longSparseArray.size();
        long[] jArr = new long[size];
        while (i < size) {
            jArr[i] = longSparseArray.keyAt(i);
            i++;
        }
        return jArr;
    }

    public void clearChoices() {
        if (this.mCheckStates != null) {
            this.mCheckStates.clear();
        }
        if (this.mCheckedIdStates != null) {
            this.mCheckedIdStates.clear();
        }
        this.mCheckedItemCount = 0;
    }

    @TargetApi(11)
    public void setItemChecked(int i, boolean z) {
        if (this.mChoiceMode != 0) {
            if (VersionUtils.isHoneycomb() && z && this.mChoiceMode == 3 && this.mChoiceActionMode == null) {
                this.mChoiceActionMode = startActionMode(this.mMultiChoiceModeCallback);
            }
            boolean z2;
            if (this.mChoiceMode == 2 || this.mChoiceMode == 3) {
                z2 = this.mCheckStates.get(i);
                this.mCheckStates.put(i, z);
                if (this.mCheckedIdStates != null && this.mAdapter.hasStableIds()) {
                    if (z) {
                        this.mCheckedIdStates.put(this.mAdapter.getItemId(i), Integer.valueOf(i));
                    } else {
                        this.mCheckedIdStates.delete(this.mAdapter.getItemId(i));
                    }
                }
                if (z2 != z) {
                    if (z) {
                        this.mCheckedItemCount++;
                    } else {
                        this.mCheckedItemCount--;
                    }
                }
                if (this.mChoiceActionMode != null) {
                    this.mMultiChoiceModeCallback.onItemCheckedStateChanged(this.mChoiceActionMode, i, this.mAdapter.getItemId(i), z);
                }
            } else {
                z2 = this.mCheckedIdStates != null && this.mAdapter.hasStableIds();
                if (z || isItemChecked(i)) {
                    this.mCheckStates.clear();
                    if (z2) {
                        this.mCheckedIdStates.clear();
                    }
                }
                if (z) {
                    this.mCheckStates.put(i, true);
                    if (z2) {
                        this.mCheckedIdStates.put(this.mAdapter.getItemId(i), Integer.valueOf(i));
                    }
                    this.mCheckedItemCount = 1;
                } else if (this.mCheckStates.size() == 0 || !this.mCheckStates.valueAt(0)) {
                    this.mCheckedItemCount = 0;
                }
            }
            if (!this.mInLayout && !this.mBlockLayoutRequests) {
                this.mDataChanged = true;
                rememberSyncState();
                requestLayout();
            }
        }
    }

    public boolean performItemClick(View view, int i, long j) {
        boolean z;
        boolean z2 = true;
        boolean z3 = false;
        if (!isValidPosition(i, ((ListAdapter) getAdapter()).getCount()) || this.mChoiceMode == 0) {
            z = false;
        } else {
            if (this.mChoiceMode == 2 || (this.mChoiceMode == 3 && this.mChoiceActionMode != null)) {
                boolean z4 = !this.mCheckStates.get(i, false);
                this.mCheckStates.put(i, z4);
                if (this.mCheckedIdStates != null && this.mAdapter.hasStableIds()) {
                    if (z4) {
                        this.mCheckedIdStates.put(this.mAdapter.getItemId(i), Integer.valueOf(i));
                    } else {
                        this.mCheckedIdStates.delete(this.mAdapter.getItemId(i));
                    }
                }
                if (z4) {
                    this.mCheckedItemCount++;
                } else {
                    this.mCheckedItemCount--;
                }
                if (this.mChoiceActionMode != null) {
                    this.mMultiChoiceModeCallback.onItemCheckedStateChanged(this.mChoiceActionMode, i, j, z4);
                }
                z3 = true;
            } else {
                if (this.mChoiceMode == 1) {
                    if (!this.mCheckStates.get(i, false)) {
                        this.mCheckStates.clear();
                        this.mCheckStates.put(i, true);
                        if (this.mCheckedIdStates != null && this.mAdapter.hasStableIds()) {
                            this.mCheckedIdStates.clear();
                            this.mCheckedIdStates.put(this.mAdapter.getItemId(i), Integer.valueOf(i));
                        }
                        this.mCheckedItemCount = 1;
                        z3 = true;
                    } else if (this.mCheckStates.size() == 0 || !this.mCheckStates.valueAt(0)) {
                        this.mCheckedItemCount = 0;
                    }
                }
                z3 = true;
            }
            this.mDataChanged = true;
            rememberSyncState();
            requestLayout();
            z = true;
            z2 = z3;
        }
        if (z2) {
            return z | super.performItemClick(view, i, j);
        }
        return z;
    }

    public void setCallbackOnUnClickItem(boolean z) {
        this.mCallbackOnUnClickItem = z;
    }

    public int getChoiceMode() {
        return this.mChoiceMode;
    }

    @TargetApi(11)
    public void setChoiceMode(int i) {
        this.mChoiceMode = i;
        if (this.mChoiceActionMode != null) {
            this.mChoiceActionMode.finish();
            this.mChoiceActionMode = null;
        }
        if (this.mChoiceMode != 0) {
            if (this.mCheckStates == null) {
                this.mCheckStates = new SparseBooleanArray();
            }
            if (this.mCheckedIdStates == null && this.mAdapter != null && this.mAdapter.hasStableIds()) {
                this.mCheckedIdStates = new LongSparseArray();
            }
            if (this.mChoiceMode == 3) {
                clearChoices();
                setLongClickable(true);
            }
        }
    }

    public void setMultiChoiceModeListener(MultiChoiceModeListener multiChoiceModeListener) {
        if (this.mMultiChoiceModeCallback == null) {
            this.mMultiChoiceModeCallback = new MultiChoiceModeWrapper(this);
        }
        this.mMultiChoiceModeCallback.setWrapped(multiChoiceModeListener);
    }

    private boolean contentFits() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return true;
        }
        if (childCount != this.mItemCount) {
            return false;
        }
        if (getChildAt(0).getTop() < this.mListPadding.top || getChildAt(childCount - 1).getBottom() > getHeight() - this.mListPadding.bottom) {
            return false;
        }
        return true;
    }

    public void setFastScrollEnabled(boolean z) {
        this.mFastScrollEnabled = z;
        if (z) {
            if (this.mFastScroller == null) {
                this.mFastScroller = new FastScroller(getContext(), this);
            }
        } else if (this.mFastScroller != null) {
            this.mFastScroller.stop();
            this.mFastScroller = null;
        }
    }

    public void setFastScrollAlwaysVisible(boolean z) {
        if (z && !this.mFastScrollEnabled) {
            setFastScrollEnabled(true);
        }
        if (this.mFastScroller != null) {
            this.mFastScroller.setAlwaysShow(z);
        }
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeOpaqueFlags", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[0]);
        } catch (Exception e) {
        }
        try {
            declaredMethod = View.class.getDeclaredMethod("recomputePadding", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[0]);
        } catch (Exception e2) {
        }
    }

    public boolean isFastScrollAlwaysVisible() {
        return this.mFastScrollEnabled && this.mFastScroller.isAlwaysShowEnabled();
    }

    public int getVerticalScrollbarWidth() {
        if (isFastScrollAlwaysVisible()) {
            return Math.max(super.getVerticalScrollbarWidth(), this.mFastScroller.getWidth());
        }
        return super.getVerticalScrollbarWidth();
    }

    @ExportedProperty
    public boolean isFastScrollEnabled() {
        return this.mFastScrollEnabled;
    }

    public void setVerticalScrollbarPosition(int i) {
        super.setVerticalScrollbarPosition(i);
        if (this.mFastScroller != null) {
            this.mFastScroller.setScrollbarPosition(i);
        }
    }

    protected boolean isVerticalScrollBarHidden() {
        return this.mFastScroller != null && this.mFastScroller.isVisible();
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.mSmoothScrollbarEnabled = z;
    }

    @ExportedProperty
    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
        invokeOnItemScrollListener();
    }

    void invokeOnItemScrollListener() {
        if (this.mFastScroller != null) {
            this.mFastScroller.onScroll(this, this.mFirstPosition, getChildCount(), this.mItemCount);
        }
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScroll(this, this.mFirstPosition, getChildCount(), this.mItemCount);
        }
        onScrollChanged(0, 0, 0, 0);
    }

    public void sendAccessibilityEvent(int i) {
        if (i == 4096) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int lastVisiblePosition = getLastVisiblePosition();
            if (this.mLastAccessibilityScrollEventFromIndex != firstVisiblePosition || this.mLastAccessibilityScrollEventToIndex != lastVisiblePosition) {
                this.mLastAccessibilityScrollEventFromIndex = firstVisiblePosition;
                this.mLastAccessibilityScrollEventToIndex = lastVisiblePosition;
            } else {
                return;
            }
        }
        super.sendAccessibilityEvent(i);
    }

    @ExportedProperty
    public boolean isScrollingCacheEnabled() {
        return this.mScrollingCacheEnabled;
    }

    public void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled && !z) {
            clearScrollingCache();
        }
        this.mScrollingCacheEnabled = z;
    }

    public void setTextFilterEnabled(boolean z) {
        this.mTextFilterEnabled = z;
    }

    @ExportedProperty
    public boolean isTextFilterEnabled() {
        return this.mTextFilterEnabled;
    }

    public void getFocusedRect(Rect rect) {
        View selectedView = getSelectedView();
        if (selectedView == null || selectedView.getParent() != this) {
            super.getFocusedRect(rect);
            return;
        }
        selectedView.getFocusedRect(rect);
        offsetDescendantRectToMyCoords(selectedView, rect);
    }

    private void useDefaultSelector() {
        setSelector(getResources().getDrawable(17301602));
    }

    @ExportedProperty
    public boolean isStackFromBottom() {
        return this.mStackFromBottom;
    }

    public void setStackFromBottom(boolean z) {
        if (this.mStackFromBottom != z) {
            this.mStackFromBottom = z;
            requestLayoutIfNecessary();
        }
    }

    void requestLayoutIfNecessary() {
        if (getChildCount() > 0) {
            resetList();
            requestLayout();
            invalidate();
        }
    }

    public Parcelable onSaveInstanceState() {
        boolean z = true;
        int i = 0;
        dismissPopup();
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        boolean z2 = getChildCount() > 0 && this.mItemCount > 0;
        savedState.height = this.mLayoutHeight;
        if (this.mSelectedPosition >= 0) {
            savedState.selectedId = this.mNextSelectedRowId;
            if (z2) {
                savedState.position = getSelectedItemPosition();
                View childAt = getChildAt(this.mSelectedPosition - this.mFirstPosition);
                if (childAt != null) {
                    savedState.viewTop = this.mStackFromBottom ? this.mLayoutHeight - childAt.getBottom() : childAt.getTop();
                }
                savedState.firstId = -1;
            }
        } else if (this.mStackFromBottom) {
            if (!z2 || this.mFirstPosition < 0) {
                savedState.viewTop = 0;
                savedState.firstId = -1;
                savedState.position = 0;
            } else {
                int childCount = getChildCount();
                if (this.mFirstPosition == -1) {
                    r0 = -1;
                } else {
                    r0 = (this.mFirstPosition + childCount) - 1;
                }
                View childAt2 = getChildAt(childCount - 1);
                savedState.viewTop = this.mLayoutHeight - childAt2.getBottom();
                if (r0 >= this.mItemCount) {
                    r0 = this.mItemCount - 1;
                }
                if (childAt2.getBottom() > this.mLayoutHeight - this.mListPadding.bottom || this.mTranscriptMode != 1) {
                    savedState.position = r0;
                } else {
                    savedState.position = Integer.MAX_VALUE;
                }
                savedState.firstId = this.mAdapter.getItemId(r0);
            }
        } else if (!z2 || this.mFirstPosition < 0) {
            savedState.viewTop = 0;
            savedState.firstId = -1;
            savedState.position = 0;
        } else {
            savedState.viewTop = getChildAt(0).getTop();
            r0 = this.mFirstPosition;
            if (r0 >= this.mItemCount) {
                r0 = this.mItemCount - 1;
            }
            savedState.position = r0;
            savedState.firstId = this.mAdapter.getItemId(r0);
        }
        savedState.filter = null;
        if (this.mFiltered) {
            EditText editText = this.mTextFilter;
            if (editText != null) {
                Editable text = editText.getText();
                if (text != null) {
                    savedState.filter = text.toString();
                }
            }
        }
        if (this.mChoiceMode != 3 || this.mChoiceActionMode == null) {
            z = false;
        }
        savedState.inActionMode = z;
        if (this.mCheckStates != null) {
            savedState.checkState = this.mCheckStates.clone();
        }
        if (this.mCheckedIdStates != null) {
            LongSparseArray longSparseArray = new LongSparseArray();
            int size = this.mCheckedIdStates.size();
            while (i < size) {
                longSparseArray.put(this.mCheckedIdStates.keyAt(i), this.mCheckedIdStates.valueAt(i));
                i++;
            }
            savedState.checkIdState = longSparseArray;
        }
        savedState.checkedItemCount = this.mCheckedItemCount;
        return savedState;
    }

    @TargetApi(11)
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mDataChanged = true;
        this.mSyncHeight = (long) savedState.height;
        if (savedState.selectedId >= 0) {
            this.mNeedSync = true;
            this.mSyncRowId = savedState.selectedId;
            this.mSyncPosition = savedState.position;
            this.mSpecificTop = savedState.viewTop;
            this.mSpecificBottom = savedState.viewTop;
            this.mSyncMode = 0;
        } else if (savedState.firstId >= 0) {
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
            this.mSelectorPosition = -1;
            if (savedState.position != Integer.MAX_VALUE) {
                this.mNeedSync = true;
                this.mSyncRowId = savedState.firstId;
                if (this.mStackFromBottom) {
                    this.mSyncPosition = savedState.position;
                    this.mSpecificBottom = savedState.viewTop;
                    this.mSyncMode = 2;
                } else {
                    this.mSyncPosition = savedState.position;
                    this.mSpecificTop = savedState.viewTop;
                    this.mSyncMode = 1;
                }
            }
        }
        setFilterText(savedState.filter);
        if (savedState.checkState != null) {
            this.mCheckStates = savedState.checkState;
        }
        if (savedState.checkIdState != null) {
            this.mCheckedIdStates = savedState.checkIdState;
        }
        this.mCheckedItemCount = savedState.checkedItemCount;
        if (VersionUtils.isHoneycomb() && savedState.inActionMode && this.mChoiceMode == 3 && this.mMultiChoiceModeCallback != null) {
            this.mChoiceActionMode = startActionMode(this.mMultiChoiceModeCallback);
        }
        requestLayout();
    }

    private boolean acceptFilter() {
        return this.mTextFilterEnabled && (getAdapter() instanceof Filterable) && ((Filterable) getAdapter()).getFilter() != null;
    }

    public void setFilterText(String str) {
        if (this.mTextFilterEnabled && !TextUtils.isEmpty(str)) {
            createTextFilter(false);
            this.mTextFilter.setText(str);
            this.mTextFilter.setSelection(str.length());
            if (this.mAdapter instanceof Filterable) {
                if (this.mPopup == null) {
                    ((Filterable) this.mAdapter).getFilter().filter(str);
                }
                this.mFiltered = true;
                this.mDataSetObserver.clearSavedState();
            }
        }
    }

    public CharSequence getTextFilter() {
        if (!this.mTextFilterEnabled || this.mTextFilter == null) {
            return null;
        }
        return this.mTextFilter.getText();
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z && this.mSelectedPosition < 0 && !isInTouchMode()) {
            if (!(this.mIsAttached || this.mAdapter == null)) {
                this.mDataChanged = true;
                this.mOldItemCount = this.mItemCount;
                this.mItemCount = this.mAdapter.getCount();
            }
            resurrectSelection();
        }
    }

    public void requestLayout() {
        if (!this.mBlockLayoutRequests && !this.mInLayout) {
            rememberSyncState();
            super.requestLayout();
        }
    }

    void resetList() {
        removeAllViewsInLayout();
        this.mFirstPosition = 0;
        this.mDataChanged = false;
        this.mNeedSync = false;
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        setSelectedPositionInt(-1);
        setNextSelectedPositionInt(-1);
        this.mSelectedTop = 0;
        this.mSelectorPosition = -1;
        this.mSelectorRect.setEmpty();
        invalidate();
    }

    protected int computeVerticalScrollExtent() {
        int childCount = getChildCount();
        if (childCount <= 0) {
            return 0;
        }
        if (!this.mSmoothScrollbarEnabled) {
            return 1;
        }
        int i = childCount * 100;
        View childAt = getChildAt(0);
        int top = childAt.getTop();
        int height = childAt.getHeight();
        if (height > 0) {
            i += (top * 100) / height;
        }
        childAt = getChildAt(childCount - 1);
        childCount = childAt.getBottom();
        height = childAt.getHeight();
        if (height > 0) {
            return i - (((childCount - getHeight()) * 100) / height);
        }
        return i;
    }

    protected int computeVerticalScrollOffset() {
        int i = 0;
        int i2 = this.mFirstPosition;
        int childCount = getChildCount();
        if (i2 < 0 || childCount <= 0) {
            return 0;
        }
        int height;
        if (this.mSmoothScrollbarEnabled) {
            View childAt = getChildAt(0);
            childCount = childAt.getTop();
            height = childAt.getHeight();
            if (height > 0) {
                return Math.max((i2 * 100) - ((childCount * 100) / height), 0);
            }
            return 0;
        }
        height = this.mItemCount;
        if (i2 != 0) {
            if (i2 + childCount == height) {
                i = height;
            } else {
                i = (childCount / 2) + i2;
            }
        }
        return (int) (((((float) i) / ((float) height)) * ((float) childCount)) + ((float) i2));
    }

    protected int computeVerticalScrollRange() {
        if (this.mSmoothScrollbarEnabled) {
            return Math.max(this.mItemCount * 100, 0);
        }
        return this.mItemCount;
    }

    protected void onDrawVerticalScrollBar(Canvas canvas, Drawable drawable, int i, int i2, int i3, int i4) {
        drawable.setBounds(i, this.mScrollY + i2, i3, this.mScrollY + i4);
        drawable.draw(canvas);
    }

    protected float getTopFadingEdgeStrength() {
        int childCount = getChildCount();
        float topFadingEdgeStrength = super.getTopFadingEdgeStrength();
        if (childCount == 0) {
            return topFadingEdgeStrength;
        }
        if (this.mFirstPosition > 0) {
            return 1.0f;
        }
        childCount = getChildAt(0).getTop();
        return childCount < this.mPaddingTop ? ((float) (-(childCount - this.mPaddingTop))) / ((float) getVerticalFadingEdgeLength()) : topFadingEdgeStrength;
    }

    protected float getBottomFadingEdgeStrength() {
        int childCount = getChildCount();
        float bottomFadingEdgeStrength = super.getBottomFadingEdgeStrength();
        if (childCount == 0) {
            return bottomFadingEdgeStrength;
        }
        if ((this.mFirstPosition + childCount) - 1 < this.mItemCount - 1) {
            return 1.0f;
        }
        childCount = getChildAt(childCount - 1).getBottom();
        int height = getHeight();
        return childCount > height - this.mPaddingBottom ? ((float) ((childCount - height) + this.mPaddingBottom)) / ((float) getVerticalFadingEdgeLength()) : bottomFadingEdgeStrength;
    }

    protected void onMeasure(int i, int i2) {
        if (this.mSelector == null) {
            useDefaultSelector();
        }
        Rect rect = this.mListPadding;
        rect.left = this.mSelectionLeftPadding + this.mPaddingLeft;
        rect.top = this.mSelectionTopPadding + this.mPaddingTop;
        rect.right = this.mSelectionRightPadding + this.mPaddingRight;
        rect.bottom = this.mSelectionBottomPadding + this.mPaddingBottom;
        if (this.mTranscriptMode == 1) {
            int childCount = getChildCount();
            if (childCount > 0) {
                int height = getHeight() - this.mPaddingBottom;
                View childAt = getChildAt(childCount - 1);
                boolean z = childCount + this.mFirstPosition >= this.mLastHandledItemCount && (childAt != null ? childAt.getBottom() : height) <= height;
                this.mForceTranscriptScroll = z;
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AdapterView.traceBegin("AbsListView.onLayout");
        try {
            super.onLayout(z, i, i2, i3, i4);
            this.mInLayout = true;
            if (z) {
                int childCount = getChildCount();
                for (int i5 = 0; i5 < childCount; i5++) {
                    getChildAt(i5).forceLayout();
                }
                this.mRecycler.markChildrenDirty();
            }
            if (!(this.mFastScroller == null || this.mItemCount == this.mOldItemCount)) {
                this.mFastScroller.onItemCountChanged(this.mOldItemCount, this.mItemCount);
            }
            layoutChildren();
            this.mInLayout = false;
            this.mOverscrollMax = (i4 - i2) / 3;
            if (this.mScrollToBottom) {
                if (this.mBottomScroller == null) {
                    this.mBottomScroller = new MoveToBottomScroller(this);
                }
                this.mBottomScroller.start();
            }
            AdapterView.traceEnd();
        } catch (Throwable th) {
            AdapterView.traceEnd();
        }
    }

    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (frame) {
            Object obj = getWindowVisibility() == 0 ? 1 : null;
            if (this.mFiltered && obj != null && this.mPopup != null && this.mPopup.isShowing()) {
                positionPopup();
            }
        }
        return frame;
    }

    protected void layoutChildren() {
    }

    void updateScrollIndicators() {
        int i;
        int i2 = 1;
        int i3 = 0;
        if (this.mScrollUp != null) {
            i = this.mFirstPosition > 0 ? 1 : 0;
            if (i == 0 && getChildCount() > 0) {
                i = getChildAt(0).getTop() < this.mListPadding.top ? 1 : 0;
            }
            View view = this.mScrollUp;
            if (i != 0) {
                i = 0;
            } else {
                i = 4;
            }
            view.setVisibility(i);
        }
        if (this.mScrollDown != null) {
            int childCount = getChildCount();
            if (this.mFirstPosition + childCount < this.mItemCount) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0 || childCount <= 0) {
                i2 = i;
            } else if (getChildAt(childCount - 1).getBottom() <= this.mBottom - this.mListPadding.bottom) {
                i2 = 0;
            }
            View view2 = this.mScrollDown;
            if (i2 == 0) {
                i3 = 4;
            }
            view2.setVisibility(i3);
        }
    }

    @ExportedProperty
    public View getSelectedView() {
        if (this.mItemCount <= 0 || this.mSelectedPosition < 0) {
            return null;
        }
        return getChildAt(this.mSelectedPosition - this.mFirstPosition);
    }

    public int getListPaddingTop() {
        return this.mListPadding.top;
    }

    public int getListPaddingBottom() {
        return this.mListPadding.bottom;
    }

    public int getListPaddingLeft() {
        return this.mListPadding.left;
    }

    public int getListPaddingRight() {
        return this.mListPadding.right;
    }

    View obtainView(int i, boolean[] zArr) {
        View view;
        zArr[0] = false;
        View scrapView = this.mRecycler.getScrapView(i);
        if (scrapView != null) {
            view = this.mAdapter.getView(i, scrapView, this);
            if (view != scrapView) {
                this.mRecycler.addScrapView(scrapView, i);
                if (this.mCacheColorHint != 0) {
                    view.setDrawingCacheBackgroundColor(this.mCacheColorHint);
                }
            } else {
                zArr[0] = true;
                dispatchFinishTemporaryDetachForView(view);
            }
        } else {
            view = this.mAdapter.getView(i, null, this);
            if (this.mCacheColorHint != 0) {
                view.setDrawingCacheBackgroundColor(this.mCacheColorHint);
            }
        }
        return view;
    }

    void positionSelector(int i, View view) {
        if (i != -1) {
            this.mSelectorPosition = i;
        }
        Rect rect = this.mSelectorRect;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (view instanceof SelectionBoundsAdjuster) {
            ((SelectionBoundsAdjuster) view).adjustListItemSelectionBounds(rect);
        }
        positionSelector(rect.left, rect.top, rect.right, rect.bottom);
        boolean z = this.mIsChildViewEnabled;
        if (view.isEnabled() != z) {
            this.mIsChildViewEnabled = !z;
            if (getSelectedItemPosition() != -1) {
                refreshDrawableState();
            }
        }
    }

    private void positionSelector(int i, int i2, int i3, int i4) {
        this.mSelectorRect.set(i - this.mSelectionLeftPadding, i2 - this.mSelectionTopPadding, this.mSelectionRightPadding + i3, this.mSelectionBottomPadding + i4);
    }

    protected void dispatchDraw(Canvas canvas) {
        int i = 0;
        int i2 = (this.mGroupFlags & 34) == 34 ? 1 : 0;
        if (i2 != 0) {
            i = canvas.save();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            canvas.clipRect(this.mPaddingLeft + scrollX, this.mPaddingTop + scrollY, ((scrollX + this.mRight) - this.mLeft) - this.mPaddingRight, ((scrollY + this.mBottom) - this.mTop) - this.mPaddingBottom);
            this.mGroupFlags &= -35;
        }
        boolean z = this.mDrawSelectorOnTop;
        if (!z) {
            drawSelector(canvas);
        }
        super.dispatchDraw(canvas);
        if (z) {
            drawSelector(canvas);
        }
        if (i2 != 0) {
            canvas.restoreToCount(i);
            this.mGroupFlags |= 34;
        }
    }

    protected boolean isPaddingOffsetRequired() {
        return (this.mGroupFlags & 34) != 34;
    }

    protected int getLeftPaddingOffset() {
        return (this.mGroupFlags & 34) == 34 ? 0 : -getPaddingLeft();
    }

    protected int getTopPaddingOffset() {
        return (this.mGroupFlags & 34) == 34 ? 0 : -getPaddingTop();
    }

    protected int getRightPaddingOffset() {
        return (this.mGroupFlags & 34) == 34 ? 0 : this.mPaddingRight;
    }

    protected int getBottomPaddingOffset() {
        return (this.mGroupFlags & 34) == 34 ? 0 : this.mPaddingBottom;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (getChildCount() > 0) {
            this.mDataChanged = true;
            rememberSyncState();
        }
        if (this.mFastScroller != null) {
            this.mFastScroller.onSizeChanged(i, i2, i3, i4);
        }
    }

    boolean touchModeDrawsInPressedState() {
        switch (this.mTouchMode) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    boolean shouldShowSelector() {
        return (hasFocus() && !isInTouchMode()) || touchModeDrawsInPressedState();
    }

    private void drawSelector(Canvas canvas) {
        if (!this.mSelectorRect.isEmpty()) {
            Drawable drawable = this.mSelector;
            drawable.setBounds(this.mSelectorRect);
            drawable.draw(canvas);
        }
    }

    public void setDrawSelectorOnTop(boolean z) {
        this.mDrawSelectorOnTop = z;
    }

    public void setSelector(int i) {
        setSelector(getResources().getDrawable(i));
    }

    public void setSelector(Drawable drawable) {
        if (this.mSelector != null) {
            this.mSelector.setCallback(null);
            unscheduleDrawable(this.mSelector);
        }
        this.mSelector = drawable;
        Rect rect = new Rect();
        drawable.getPadding(rect);
        this.mSelectionLeftPadding = rect.left;
        this.mSelectionTopPadding = rect.top;
        this.mSelectionRightPadding = rect.right;
        this.mSelectionBottomPadding = rect.bottom;
        drawable.setCallback(this);
        updateSelectorState();
    }

    public Drawable getSelector() {
        return this.mSelector;
    }

    void keyPressed() {
        if (isEnabled() && isClickable()) {
            Drawable drawable = this.mSelector;
            Rect rect = this.mSelectorRect;
            if (drawable == null) {
                return;
            }
            if ((isFocused() || touchModeDrawsInPressedState()) && !rect.isEmpty()) {
                View childAt = getChildAt(this.mSelectedPosition - this.mFirstPosition);
                if (childAt != null) {
                    if (!childAt.hasFocusable()) {
                        childAt.setPressed(true);
                    } else {
                        return;
                    }
                }
                setPressed(true);
                boolean isLongClickable = isLongClickable();
                drawable = drawable.getCurrent();
                if (drawable != null && (drawable instanceof TransitionDrawable)) {
                    if (isLongClickable) {
                        ((TransitionDrawable) drawable).startTransition(ViewConfiguration.getLongPressTimeout());
                    } else {
                        ((TransitionDrawable) drawable).resetTransition();
                    }
                }
                if (isLongClickable && !this.mDataChanged) {
                    if (this.mPendingCheckForKeyLongPress == null) {
                        this.mPendingCheckForKeyLongPress = new CheckForKeyLongPress(this, null);
                    }
                    this.mPendingCheckForKeyLongPress.rememberWindowAttachCount();
                    postDelayed(this.mPendingCheckForKeyLongPress, (long) ViewConfiguration.getLongPressTimeout());
                }
            }
        }
    }

    public void setScrollIndicators(View view, View view2) {
        this.mScrollUp = view;
        this.mScrollDown = view2;
    }

    void updateSelectorState() {
        if (this.mSelector == null) {
            return;
        }
        if (shouldShowSelector()) {
            this.mSelector.setState(getDrawableState());
        } else {
            this.mSelector.setState(NOTHING);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateSelectorState();
    }

    protected int[] onCreateDrawableState(int i) {
        if (this.mIsChildViewEnabled) {
            return super.onCreateDrawableState(i);
        }
        int i2 = ENABLED_STATE_SET[0];
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        int length = onCreateDrawableState.length - 1;
        while (length >= 0) {
            if (onCreateDrawableState[length] == i2) {
                break;
            }
            length--;
        }
        length = -1;
        if (length < 0) {
            return onCreateDrawableState;
        }
        System.arraycopy(onCreateDrawableState, length + 1, onCreateDrawableState, length, (onCreateDrawableState.length - length) - 1);
        return onCreateDrawableState;
    }

    public boolean verifyDrawable(Drawable drawable) {
        return this.mSelector == drawable || super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (verifyDrawable(drawable)) {
            invalidate(drawable.getBounds());
        }
    }

    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.mSelector != null) {
            this.mSelector.jumpToCurrentState();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnTouchModeChangeListener(this);
        if (!(!this.mTextFilterEnabled || this.mPopup == null || this.mGlobalLayoutListenerAddedFilter)) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.mAdapter != null && this.mDataSetObserver == null) {
            this.mDataSetObserver = newObserver();
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
            this.mDataChanged = true;
            this.mOldItemCount = this.mItemCount;
            this.mItemCount = this.mAdapter.getCount();
            requestLayout();
        }
        this.mIsAttached = true;
    }

    protected AdapterDataSetObserver newObserver() {
        return new AdapterDataSetObserver(this);
    }

    public void tryRecycle() {
        if (this.mRecycler != null) {
            this.mRecycler.clear();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopup();
        this.mRecycler.clear();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.removeOnTouchModeChangeListener(this);
        if (this.mTextFilterEnabled && this.mPopup != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this);
            this.mGlobalLayoutListenerAddedFilter = false;
        }
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
            this.mDataSetObserver = null;
        }
        if (this.mScrollStrictSpan != null) {
            this.mScrollStrictSpan = finishSpan(this.mScrollStrictSpan);
        }
        if (this.mFlingStrictSpan != null) {
            this.mFlingStrictSpan = finishSpan(this.mFlingStrictSpan);
        }
        if (this.mFlingRunnable != null) {
            removeCallbacks(this.mFlingRunnable);
        }
        if (this.mPositionScroller != null) {
            this.mPositionScroller.stop();
        }
        if (this.mBottomScroller != null) {
            this.mBottomScroller.stop();
        }
        if (this.mClearScrollingCache != null) {
            removeCallbacks(this.mClearScrollingCache);
        }
        if (this.mPerformClick != null) {
            removeCallbacks(this.mPerformClick);
        }
        if (this.mTouchModeReset != null) {
            removeCallbacks(this.mTouchModeReset);
            this.mTouchModeReset = null;
        }
        this.mIsAttached = false;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        int i = isInTouchMode() ? 0 : 1;
        if (z) {
            if (this.mFiltered && !this.mPopupHidden) {
                showPopup();
            }
            if (!(i == this.mLastTouchMode || this.mLastTouchMode == -1)) {
                if (i == 1) {
                    resurrectSelection();
                } else {
                    hideSelector();
                    this.mLayoutMode = 0;
                    layoutChildren();
                }
            }
        } else {
            setChildrenDrawingCacheEnabled(false);
            if (this.mFlingRunnable != null) {
                removeCallbacks(this.mFlingRunnable);
                this.mFlingRunnable.endFling();
                if (this.mPositionScroller != null) {
                    this.mPositionScroller.stop();
                }
                if (this.mBottomScroller != null) {
                    this.mBottomScroller.stop();
                }
                if (getScrollY() != 0) {
                    this.mScrollY = 0;
                    invalidateParentCaches();
                    finishGlows();
                    invalidate();
                }
            }
            dismissPopup();
            if (i == 1) {
                this.mResurrectToPosition = this.mSelectedPosition;
            }
        }
        this.mLastTouchMode = i;
    }

    ContextMenuInfo createContextMenuInfo(View view, int i, long j) {
        return new AdapterContextMenuInfo(view, i, j);
    }

    private boolean isValidPosition(int i, int i2) {
        return this.mItemCount > 0 && i != -1 && i < i2;
    }

    @TargetApi(11)
    boolean performLongPress(View view, int i, long j) {
        boolean z = true;
        if (this.mChoiceMode != 3) {
            if (this.mOnItemLongClickListener != null) {
                z = this.mOnItemLongClickListener.onItemLongClick(this, view, i, j);
            } else {
                z = false;
            }
            if (!z) {
                this.mContextMenuInfo = createContextMenuInfo(view, i, j);
                z = super.showContextMenuForChild(this);
            }
            if (z) {
                performHapticFeedback(0);
            }
        } else if (VersionUtils.isHoneycomb() && this.mChoiceActionMode == null) {
            ActionMode startActionMode = startActionMode(this.mMultiChoiceModeCallback);
            this.mChoiceActionMode = startActionMode;
            if (startActionMode != null) {
                setItemChecked(i, true);
                performHapticFeedback(0);
            }
        }
        return z;
    }

    protected ContextMenuInfo getContextMenuInfo() {
        return this.mContextMenuInfo;
    }

    public boolean showContextMenu(float f, float f2, int i) {
        int pointToPosition = pointToPosition((int) f, (int) f2);
        if (pointToPosition != -1) {
            long itemId = this.mAdapter.getItemId(pointToPosition);
            View childAt = getChildAt(pointToPosition - this.mFirstPosition);
            if (childAt != null) {
                this.mContextMenuInfo = createContextMenuInfo(childAt, pointToPosition, itemId);
                return super.showContextMenuForChild(this);
            }
        }
        return super.showContextMenu();
    }

    public boolean showContextMenuForChild(View view) {
        boolean z = false;
        int positionForView = getPositionForView(view);
        if (positionForView < 0) {
            return false;
        }
        long itemId = this.mAdapter.getItemId(positionForView);
        if (this.mOnItemLongClickListener != null) {
            z = this.mOnItemLongClickListener.onItemLongClick(this, view, positionForView, itemId);
        }
        if (z) {
            return z;
        }
        this.mContextMenuInfo = createContextMenuInfo(getChildAt(positionForView - this.mFirstPosition), positionForView, itemId);
        return super.showContextMenuForChild(view);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 23:
            case 66:
                if (!isEnabled()) {
                    return true;
                }
                if (isClickable() && isPressed() && this.mSelectedPosition >= 0 && this.mAdapter != null && this.mSelectedPosition < this.mAdapter.getCount()) {
                    View childAt = getChildAt(this.mSelectedPosition - this.mFirstPosition);
                    if (childAt != null) {
                        performItemClick(childAt, this.mSelectedPosition, this.mSelectedRowId);
                        childAt.setPressed(false);
                    }
                    setPressed(false);
                    return true;
                }
        }
        return super.onKeyUp(i, keyEvent);
    }

    protected void dispatchSetPressed(boolean z) {
    }

    public int pointToPosition(int i, int i2) {
        Rect rect = this.mTouchFrame;
        if (rect == null) {
            this.mTouchFrame = new Rect();
            rect = this.mTouchFrame;
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() == 0) {
                childAt.getHitRect(rect);
                if (rect.contains(i, i2)) {
                    return this.mFirstPosition + childCount;
                }
            }
        }
        return -1;
    }

    public long pointToRowId(int i, int i2) {
        int pointToPosition = pointToPosition(i, i2);
        if (pointToPosition >= 0) {
            return this.mAdapter.getItemId(pointToPosition);
        }
        return Long.MIN_VALUE;
    }

    private boolean startScrollIfNeeded(int i) {
        int i2 = i - this.mMotionY;
        int abs = Math.abs(i2);
        boolean z = this.mScrollY != 0;
        if (!z && abs <= this.mTouchSlop) {
            return false;
        }
        createScrollingCache();
        if (z) {
            this.mTouchMode = 5;
            this.mMotionCorrection = 0;
        } else {
            this.mTouchMode = 3;
            this.mMotionCorrection = i2 > 0 ? this.mTouchSlop : -this.mTouchSlop;
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.mPendingCheckForLongPress);
        }
        setPressed(false);
        View childAt = getChildAt(this.mMotionPosition - this.mFirstPosition);
        if (childAt != null) {
            childAt.setPressed(false);
        }
        reportScrollStateChange(1);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        scrollIfNeeded(i);
        return true;
    }

    @TargetApi(9)
    private void scrollIfNeeded(int i) {
        int i2 = i - this.mMotionY;
        int i3 = i2 - this.mMotionCorrection;
        int i4 = this.mLastY != Integer.MIN_VALUE ? i - this.mLastY : i3;
        int i5;
        int i6;
        if (this.mTouchMode == 3) {
            if (this.mScrollStrictSpan == null) {
                this.mScrollStrictSpan = enterCriticalSpan("AbsListView-scroll");
            }
            if (i != this.mLastY) {
                int top;
                boolean trackMotionScroll;
                if ((this.mGroupFlags & 524288) == 0 && Math.abs(i2) > this.mTouchSlop) {
                    ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (this.mMotionPosition >= 0) {
                    i5 = this.mMotionPosition - this.mFirstPosition;
                } else {
                    i5 = getChildCount() / 2;
                }
                View childAt = getChildAt(i5);
                if (childAt != null) {
                    top = childAt.getTop();
                } else {
                    top = 0;
                }
                if (i4 != 0) {
                    trackMotionScroll = trackMotionScroll(i3, i4);
                } else {
                    trackMotionScroll = false;
                }
                View childAt2 = getChildAt(i5);
                if (childAt2 != null) {
                    i5 = childAt2.getTop();
                    if (trackMotionScroll) {
                        i6 = (-i4) - (i5 - top);
                        overScrollBy(0, reviseOverScrollByTouch(i6), 0, getScrollY(), 0, 0, 0, this.mOverscrollDistance, true);
                        if (Math.abs(this.mOverscrollDistance) == Math.abs(getScrollY()) && this.mVelocityTracker != null) {
                            this.mVelocityTracker.clear();
                        }
                        i4 = getOverScrollMode();
                        if (i4 == 0 || (i4 == 1 && !contentFits())) {
                            this.mDirection = 0;
                            this.mTouchMode = 5;
                            if (this.mEdgeGlowTop != null) {
                                if (i2 > 0) {
                                    this.mEdgeGlowTop.onPull(((float) i6) / ((float) getHeight()));
                                    if (!this.mEdgeGlowBottom.isFinished()) {
                                        this.mEdgeGlowBottom.onRelease();
                                    }
                                } else if (i2 < 0) {
                                    this.mEdgeGlowBottom.onPull(((float) i6) / ((float) getHeight()));
                                    if (!this.mEdgeGlowTop.isFinished()) {
                                        this.mEdgeGlowTop.onRelease();
                                    }
                                }
                            }
                        }
                    }
                    this.mMotionY = i;
                    invalidate();
                }
                this.mLastY = i;
            }
        } else if (this.mTouchMode == 5 && i != this.mLastY) {
            int i7;
            int i8;
            i5 = getScrollY();
            int i9 = i5 - i4;
            if (i > this.mLastY) {
                i6 = 1;
            } else {
                i6 = -1;
            }
            if (this.mDirection == 0) {
                this.mDirection = i6;
            }
            i3 = -i4;
            if ((i9 >= 0 || i5 < 0) && (i9 <= 0 || i5 > 0)) {
                i7 = i3;
                i8 = 0;
            } else {
                i3 = -i5;
                i7 = i3;
                i8 = i4 + i3;
            }
            if (i7 != 0) {
                overScrollBy(0, reviseOverScrollByTouch(i7), 0, getScrollY(), 0, 0, 0, this.mOverscrollDistance, true);
                i4 = getOverScrollMode();
                if (i4 == 0 || (i4 == 1 && !contentFits())) {
                    if (this.mEdgeGlowTop != null) {
                        if (i2 > 0) {
                            this.mEdgeGlowTop.onPull(((float) i7) / ((float) getHeight()));
                            if (!this.mEdgeGlowBottom.isFinished()) {
                                this.mEdgeGlowBottom.onRelease();
                            }
                        } else if (i2 < 0) {
                            this.mEdgeGlowBottom.onPull(((float) i7) / ((float) getHeight()));
                            if (!this.mEdgeGlowTop.isFinished()) {
                                this.mEdgeGlowTop.onRelease();
                            }
                        }
                    }
                    invalidate();
                }
            }
            if (i8 != 0) {
                this.mScrollY = 0;
                invalidateParentIfNeeded();
                if (i8 != 0) {
                    trackMotionScroll(i8, i8);
                }
                this.mTouchMode = 3;
                i3 = findClosestMotionRow(i);
                this.mMotionCorrection = 0;
                View childAt3 = getChildAt(i3 - this.mFirstPosition);
                this.mMotionViewOriginalTop = childAt3 != null ? childAt3.getTop() : 0;
                this.mMotionY = i;
                this.mMotionPosition = i3;
            }
            this.mLastY = i;
            this.mDirection = i6;
        }
    }

    private int reviseOverScrollByTouch(int i) {
        if (this.mScrollY * i < 0) {
            return i;
        }
        return (((this.mLayoutHeight - Math.abs(this.mScrollY)) * i) / this.mLayoutHeight) / 2;
    }

    public void onTouchModeChanged(boolean z) {
        if (z) {
            hideSelector();
            if (getHeight() > 0 && getChildCount() > 0) {
                layoutChildren();
            }
            updateSelectorState();
            return;
        }
        int i = this.mTouchMode;
        if (i == 5 || i == 6) {
            if (this.mFlingRunnable != null && this.mScrollY == 0) {
                this.mFlingRunnable.endFling();
            }
            if (this.mPositionScroller != null) {
                this.mPositionScroller.stop();
            }
            if (this.mBottomScroller != null) {
                this.mBottomScroller.stop();
            }
            if (getScrollY() != 0) {
                this.mScrollY = 0;
                invalidateParentCaches();
                finishGlows();
                invalidate();
            }
        }
    }

    @TargetApi(8)
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        boolean z;
        if (!isEnabled()) {
            if (isClickable() || isLongClickable()) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } else if (this.mFastScroller != null && this.mFastScroller.onTouchEvent(motionEvent)) {
            return true;
        } else {
            int action = motionEvent.getAction();
            initVelocityTrackerIfNotExists();
            this.mVelocityTracker.addMovement(motionEvent);
            int x;
            int y;
            int pointToPosition;
            Handler handler;
            switch (action & 255) {
                case 0:
                    switch (this.mTouchMode) {
                        case 6:
                            this.mFlingRunnable.endFling();
                            if (this.mPositionScroller != null) {
                                this.mPositionScroller.stop();
                            }
                            if (this.mBottomScroller != null) {
                                this.mBottomScroller.stop();
                            }
                            this.mTouchMode = 5;
                            this.mMotionX = (int) motionEvent.getX();
                            action = (int) motionEvent.getY();
                            this.mLastY = action;
                            this.mMotionY = action;
                            this.mMotionCorrection = 0;
                            this.mActivePointerId = motionEvent.getPointerId(0);
                            this.mDirection = 0;
                            break;
                        default:
                            this.mActivePointerId = motionEvent.getPointerId(0);
                            x = (int) motionEvent.getX();
                            y = (int) motionEvent.getY();
                            pointToPosition = pointToPosition(x, getScrollY() + y);
                            if (!this.mDataChanged) {
                                if (this.mTouchMode == 4 || pointToPosition < 0 || !((ListAdapter) getAdapter()).isEnabled(pointToPosition)) {
                                    if (this.mTouchMode == 4) {
                                        createScrollingCache();
                                        this.mFlingRunnable.endFling();
                                        this.mTouchMode = 3;
                                        this.mMotionCorrection = 0;
                                        action = findMotionRow(y);
                                        this.mFlingRunnable.flywheelTouch();
                                        if (action >= 0) {
                                            this.mMotionViewOriginalTop = getChildAt(action - this.mFirstPosition).getTop();
                                        }
                                        this.mMotionX = x;
                                        this.mMotionY = y;
                                        this.mMotionPosition = action;
                                        this.mLastY = Integer.MIN_VALUE;
                                        break;
                                    }
                                }
                                this.mTouchMode = 0;
                                if (this.mPendingCheckForTap == null) {
                                    this.mPendingCheckForTap = new CheckForTap(this);
                                }
                                postDelayed(this.mPendingCheckForTap, (long) ViewConfiguration.getTapTimeout());
                                action = pointToPosition;
                                if (action >= 0) {
                                    this.mMotionViewOriginalTop = getChildAt(action - this.mFirstPosition).getTop();
                                }
                                this.mMotionX = x;
                                this.mMotionY = y;
                                this.mMotionPosition = action;
                                this.mLastY = Integer.MIN_VALUE;
                            }
                            action = pointToPosition;
                            if (action >= 0) {
                                this.mMotionViewOriginalTop = getChildAt(action - this.mFirstPosition).getTop();
                            }
                            this.mMotionX = x;
                            this.mMotionY = y;
                            this.mMotionPosition = action;
                            this.mLastY = Integer.MIN_VALUE;
                            break;
                    }
                    if (!performButtonActionOnTouchDown(motionEvent) || this.mTouchMode != 0) {
                        return true;
                    }
                    removeCallbacks(this.mPendingCheckForTap);
                    return true;
                case 1:
                    float x2;
                    switch (this.mTouchMode) {
                        case 0:
                        case 1:
                        case 2:
                            pointToPosition = this.mMotionPosition;
                            View childAt = getChildAt(pointToPosition - this.mFirstPosition);
                            x2 = motionEvent.getX();
                            z = x2 > ((float) this.mListPadding.left) && x2 < ((float) (getWidth() - this.mListPadding.right));
                            if (this.mPerformClick == null) {
                                this.mPerformClick = new PerformClick(this, null);
                            }
                            PerformClick performClick = this.mPerformClick;
                            performClick.mClickMotionPosition = pointToPosition;
                            performClick.rememberWindowAttachCount();
                            if (childAt == null || childAt.hasFocusable() || !z) {
                                performClick.run();
                            } else {
                                if (this.mTouchMode != 0) {
                                    childAt.setPressed(false);
                                }
                                this.mResurrectToPosition = pointToPosition;
                                if (this.mTouchMode == 0 || this.mTouchMode == 1) {
                                    Handler handler2 = getHandler();
                                    if (handler2 != null) {
                                        handler2.removeCallbacks(this.mTouchMode == 0 ? this.mPendingCheckForTap : this.mPendingCheckForLongPress);
                                    }
                                    this.mLayoutMode = 0;
                                    if (this.mDataChanged || !this.mAdapter.isEnabled(pointToPosition)) {
                                        this.mTouchMode = -1;
                                        updateSelectorState();
                                        return true;
                                    }
                                    this.mTouchMode = 1;
                                    setSelectedPositionInt(this.mMotionPosition);
                                    layoutChildren();
                                    childAt.setPressed(true);
                                    positionSelector(this.mMotionPosition, childAt);
                                    setPressed(true);
                                    if (this.mSelector != null) {
                                        Drawable current = this.mSelector.getCurrent();
                                        if (current != null && (current instanceof TransitionDrawable)) {
                                            ((TransitionDrawable) current).resetTransition();
                                        }
                                    }
                                    if (this.mTouchModeReset != null) {
                                        removeCallbacks(this.mTouchModeReset);
                                    }
                                    this.mTouchModeReset = new 1(this, childAt, performClick);
                                    postDelayed(this.mTouchModeReset, (long) ViewConfiguration.getPressedStateDuration());
                                    return true;
                                } else if (!this.mDataChanged && this.mAdapter.isEnabled(pointToPosition)) {
                                    performClick.run();
                                }
                            }
                            this.mTouchMode = -1;
                            updateSelectorState();
                            break;
                        case 3:
                            pointToPosition = getChildCount();
                            if (pointToPosition <= 0) {
                                this.mTouchMode = -1;
                                reportScrollStateChange(0);
                                break;
                            }
                            x = getChildAt(0).getTop();
                            y = getChildAt(pointToPosition - 1).getBottom();
                            int i2 = this.mListPadding.top;
                            int height = getHeight() - this.mListPadding.bottom;
                            if (this.mFirstPosition == 0 && x >= i2 && this.mFirstPosition + pointToPosition < this.mItemCount && y <= getHeight() - height) {
                                this.mTouchMode = -1;
                                reportScrollStateChange(0);
                                break;
                            }
                            VelocityTracker velocityTracker = this.mVelocityTracker;
                            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                            if (VersionUtils.isrFroyo()) {
                                x2 = velocityTracker.getYVelocity(this.mActivePointerId);
                            } else {
                                x2 = velocityTracker.getYVelocity();
                            }
                            action = (int) (x2 * this.mVelocityScale);
                            if (Math.abs(action) > this.mMinimumVelocity && ((this.mFirstPosition != 0 || x != i2 - this.mOverscrollDistance) && (pointToPosition + this.mFirstPosition != this.mItemCount || y != this.mOverscrollDistance + height))) {
                                if (this.mFlingRunnable == null) {
                                    this.mFlingRunnable = new FlingRunnable(this);
                                }
                                reportScrollStateChange(2);
                                this.mFlingRunnable.start(-action);
                                break;
                            }
                            this.mTouchMode = -1;
                            reportScrollStateChange(0);
                            if (this.mFlingRunnable != null) {
                                this.mFlingRunnable.endFling();
                            }
                            if (this.mPositionScroller != null) {
                                this.mPositionScroller.stop();
                            }
                            if (this.mBottomScroller != null) {
                                this.mBottomScroller.stop();
                                break;
                            }
                            break;
                        case 5:
                            if (this.mFlingRunnable == null) {
                                this.mFlingRunnable = new FlingRunnable(this);
                            }
                            reportScrollStateChange(2);
                            this.mFlingRunnable.startSpringback(getSpringbackOffset());
                            break;
                    }
                    setPressed(false);
                    if (this.mEdgeGlowTop != null) {
                        this.mEdgeGlowTop.onRelease();
                        this.mEdgeGlowBottom.onRelease();
                    }
                    invalidate();
                    handler = getHandler();
                    if (handler != null) {
                        handler.removeCallbacks(this.mPendingCheckForLongPress);
                    }
                    recycleVelocityTracker();
                    this.mActivePointerId = -1;
                    if (this.mScrollStrictSpan == null) {
                        return true;
                    }
                    this.mScrollStrictSpan = finishSpan(this.mScrollStrictSpan);
                    return true;
                case 2:
                    action = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (action == -1) {
                        this.mActivePointerId = motionEvent.getPointerId(0);
                    } else {
                        i = action;
                    }
                    if (this.mDataChanged) {
                        layoutChildren();
                    }
                    action = (int) motionEvent.getY(i);
                    switch (this.mTouchMode) {
                        case 0:
                        case 1:
                        case 2:
                            startScrollIfNeeded(action);
                            return true;
                        case 3:
                        case 5:
                            scrollIfNeeded(action);
                            return true;
                        default:
                            return true;
                    }
                case 3:
                    switch (this.mTouchMode) {
                        case 5:
                            if (this.mFlingRunnable == null) {
                                this.mFlingRunnable = new FlingRunnable(this);
                            }
                            this.mFlingRunnable.startSpringback(0);
                            break;
                        case 6:
                            break;
                        default:
                            this.mTouchMode = -1;
                            setPressed(false);
                            View childAt2 = getChildAt(this.mMotionPosition - this.mFirstPosition);
                            if (childAt2 != null) {
                                childAt2.setPressed(false);
                            }
                            clearScrollingCache();
                            handler = getHandler();
                            if (handler != null) {
                                handler.removeCallbacks(this.mPendingCheckForLongPress);
                            }
                            recycleVelocityTracker();
                            break;
                    }
                    if (this.mEdgeGlowTop != null) {
                        this.mEdgeGlowTop.onRelease();
                        this.mEdgeGlowBottom.onRelease();
                    }
                    this.mActivePointerId = -1;
                    return true;
                case 5:
                    if (!VersionUtils.isrFroyo()) {
                        return true;
                    }
                    action = motionEvent.getActionIndex();
                    pointToPosition = motionEvent.getPointerId(action);
                    x = (int) motionEvent.getX(action);
                    action = (int) motionEvent.getY(action);
                    this.mMotionCorrection = 0;
                    this.mActivePointerId = pointToPosition;
                    this.mMotionX = x;
                    this.mMotionY = action;
                    i = pointToPosition(x, action);
                    if (i >= 0) {
                        this.mMotionViewOriginalTop = getChildAt(i - this.mFirstPosition).getTop();
                        this.mMotionPosition = i;
                    }
                    this.mLastY = action;
                    return true;
                case 6:
                    if (!VersionUtils.isrFroyo()) {
                        return true;
                    }
                    onSecondaryPointerUp(motionEvent);
                    action = this.mMotionX;
                    i = this.mMotionY;
                    action = pointToPosition(action, i);
                    if (action >= 0) {
                        this.mMotionViewOriginalTop = getChildAt(action - this.mFirstPosition).getTop();
                        this.mMotionPosition = action;
                    }
                    this.mLastY = i;
                    return true;
                default:
                    return true;
            }
        }
    }

    protected int getSpringbackOffset() {
        return 0;
    }

    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (getScrollY() != i2) {
            onScrollChanged(getScrollX(), i2, getScrollX(), getScrollY());
            this.mScrollY = i2;
            invalidateParentIfNeeded();
            awakenScrollBars();
        }
    }

    @TargetApi(12)
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0) {
            switch (motionEvent.getAction()) {
                case 8:
                    if (this.mTouchMode == -1) {
                        float axisValue = motionEvent.getAxisValue(9);
                        if (axisValue != 0.0f) {
                            int verticalScrollFactor = (int) (axisValue * getVerticalScrollFactor());
                            if (!trackMotionScroll(verticalScrollFactor, verticalScrollFactor)) {
                                return true;
                            }
                        }
                    }
                    break;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void draw(Canvas canvas) {
        int i;
        int save;
        super.draw(canvas);
        if (this.mEdgeGlowTop != null) {
            int i2;
            int width;
            i = this.mScrollY;
            if (!this.mEdgeGlowTop.isFinished()) {
                save = canvas.save();
                i2 = this.mListPadding.left + this.mGlowPaddingLeft;
                width = (getWidth() - i2) - (this.mListPadding.right + this.mGlowPaddingRight);
                canvas.translate((float) i2, (float) Math.min(0, this.mFirstPositionDistanceGuess + i));
                this.mEdgeGlowTop.setSize(width, getHeight());
                if (this.mEdgeGlowTop.draw(canvas)) {
                    invalidate();
                }
                canvas.restoreToCount(save);
            }
            if (!this.mEdgeGlowBottom.isFinished()) {
                save = canvas.save();
                i2 = this.mListPadding.left + this.mGlowPaddingLeft;
                width = (getWidth() - i2) - (this.mListPadding.right + this.mGlowPaddingRight);
                int height = getHeight();
                canvas.translate((float) (i2 + (-width)), (float) Math.max(height, i + this.mLastPositionDistanceGuess));
                canvas.rotate(180.0f, (float) width, 0.0f);
                this.mEdgeGlowBottom.setSize(width, height);
                if (this.mEdgeGlowBottom.draw(canvas)) {
                    invalidate();
                }
                canvas.restoreToCount(save);
            }
        }
        if (this.mFastScroller != null) {
            i = this.mScrollY;
            if (i != 0) {
                save = canvas.save();
                canvas.translate(0.0f, (float) i);
                this.mFastScroller.draw(canvas);
                canvas.restoreToCount(save);
                return;
            }
            this.mFastScroller.draw(canvas);
        }
    }

    public void setOverScrollEffectPadding(int i, int i2) {
        this.mGlowPaddingLeft = i;
        this.mGlowPaddingRight = i2;
    }

    private void initOrResetVelocityTracker() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            this.mVelocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            recycleVelocityTracker();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.mFastScroller != null && this.mFastScroller.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        switch (action & 255) {
            case 0:
                action = this.mTouchMode;
                if (action == 6 || action == 5) {
                    this.mMotionCorrection = 0;
                    return true;
                }
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                this.mActivePointerId = motionEvent.getPointerId(0);
                int findClosestMotionRow = findClosestMotionRow(y);
                if (action != 4 && findClosestMotionRow >= 0) {
                    this.mMotionViewOriginalTop = getChildAt(findClosestMotionRow - this.mFirstPosition).getTop();
                    this.mMotionX = x;
                    this.mMotionY = y;
                    this.mMotionPosition = findClosestMotionRow;
                    this.mTouchMode = 0;
                    clearScrollingCache();
                }
                this.mLastY = Integer.MIN_VALUE;
                initOrResetVelocityTracker();
                this.mVelocityTracker.addMovement(motionEvent);
                if (action == 4) {
                    return true;
                }
                return false;
            case 1:
            case 3:
                this.mTouchMode = -1;
                this.mActivePointerId = -1;
                recycleVelocityTracker();
                reportScrollStateChange(0);
                return false;
            case 2:
                switch (this.mTouchMode) {
                    case 0:
                        action = motionEvent.findPointerIndex(this.mActivePointerId);
                        if (action == -1) {
                            this.mActivePointerId = motionEvent.getPointerId(0);
                            action = 0;
                        }
                        if (this.mDataChanged) {
                            layoutChildren();
                        }
                        action = (int) motionEvent.getY(action);
                        initVelocityTrackerIfNotExists();
                        this.mVelocityTracker.addMovement(motionEvent);
                        if (startScrollIfNeeded(action)) {
                            return true;
                        }
                        return false;
                    default:
                        return false;
                }
            case 6:
                onSecondaryPointerUp(motionEvent);
                return false;
            default:
                return false;
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & 65280) >> 8;
        if (motionEvent.getPointerId(action) == this.mActivePointerId) {
            action = action == 0 ? 1 : 0;
            this.mMotionX = (int) motionEvent.getX(action);
            this.mMotionY = (int) motionEvent.getY(action);
            this.mMotionCorrection = 0;
            this.mActivePointerId = motionEvent.getPointerId(action);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        int childCount = getChildCount();
        int i = this.mFirstPosition;
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (listAdapter.isEnabled(i + i2)) {
                    arrayList.add(childAt);
                }
                childAt.addTouchables(arrayList);
            }
        }
    }

    protected void reportScrollStateChange(int i) {
        if (i != this.mLastScrollState && this.mOnScrollListener != null) {
            this.mLastScrollState = i;
            this.mOnScrollListener.onScrollStateChanged(this, i);
        }
    }

    public void setFriction(float f) {
        if (this.mFlingRunnable == null) {
            this.mFlingRunnable = new FlingRunnable(this);
        }
        FlingRunnable.access$1300(this.mFlingRunnable).setFriction(f);
    }

    public void setVelocityScale(float f) {
        this.mVelocityScale = f;
    }

    public void smoothScrollToPosition(int i) {
        if (this.mPositionScroller == null) {
            this.mPositionScroller = new PositionScroller(this);
        }
        this.mPositionScroller.start(i);
    }

    public void smoothScrollToPositionFromTop(int i, int i2, int i3) {
        if (this.mPositionScroller == null) {
            this.mPositionScroller = new PositionScroller(this);
        }
        this.mPositionScroller.startWithOffset(i, i2, i3);
    }

    public void smoothScrollToPositionFromTop(int i, int i2) {
        if (this.mPositionScroller == null) {
            this.mPositionScroller = new PositionScroller(this);
        }
        this.mPositionScroller.startWithOffset(i, i2);
    }

    public void smoothScrollToPosition(int i, int i2) {
        if (this.mPositionScroller == null) {
            this.mPositionScroller = new PositionScroller(this);
        }
        this.mPositionScroller.start(i, i2);
    }

    public void smoothScrollBy(int i, int i2) {
        if (this.mFlingRunnable == null) {
            this.mFlingRunnable = new FlingRunnable(this);
        }
        int i3 = this.mFirstPosition;
        int childCount = getChildCount();
        int i4 = (i3 + childCount) - 1;
        int i5 = this.mPaddingTop;
        int height = getHeight() - this.mPaddingBottom;
        if (i == 0 || this.mItemCount == 0 || childCount == 0 || ((i3 == 0 && getChildAt(0).getTop() == i5 && i < 0) || (i4 == this.mItemCount - 1 && getChildAt(childCount - 1).getBottom() == height && i > 0))) {
            this.mFlingRunnable.endFling();
            if (this.mPositionScroller != null) {
                this.mPositionScroller.stop();
            }
            if (this.mBottomScroller != null) {
                this.mBottomScroller.stop();
                return;
            }
            return;
        }
        reportScrollStateChange(2);
        this.mFlingRunnable.startScroll(i, i2);
    }

    void smoothScrollByOffset(int i) {
        int firstVisiblePosition;
        if (i < 0) {
            firstVisiblePosition = getFirstVisiblePosition();
        } else if (i > 0) {
            firstVisiblePosition = getLastVisiblePosition();
        } else {
            firstVisiblePosition = -1;
        }
        if (firstVisiblePosition > -1) {
            View childAt = getChildAt(firstVisiblePosition - getFirstVisiblePosition());
            if (childAt != null) {
                Rect rect = new Rect();
                if (childAt.getGlobalVisibleRect(rect)) {
                    int height = childAt.getHeight() * childAt.getWidth();
                    float height2 = ((float) (rect.height() * rect.width())) / ((float) height);
                    if (i < 0 && height2 < 0.75f) {
                        firstVisiblePosition++;
                    } else if (i > 0 && height2 < 0.75f) {
                        firstVisiblePosition--;
                    }
                }
                smoothScrollToPosition(Math.max(0, Math.min(getCount(), firstVisiblePosition + i)));
            }
        }
    }

    private void createScrollingCache() {
        if (this.mScrollingCacheEnabled && !this.mCachingStarted) {
            setChildrenDrawnWithCacheEnabled(true);
            setChildrenDrawingCacheEnabled(true);
            this.mCachingActive = true;
            this.mCachingStarted = true;
        }
    }

    private void clearScrollingCache() {
        if (this.mClearScrollingCache == null) {
            this.mClearScrollingCache = new 2(this);
        }
        post(this.mClearScrollingCache);
    }

    boolean trackMotionScroll(int i, int i2) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return true;
        }
        AdapterView.traceBegin("AbsListView.trackMotionScroll");
        try {
            int max;
            int max2;
            int top = getChildAt(0).getTop();
            int bottom = getChildAt(childCount - 1).getBottom();
            Rect rect = this.mListPadding;
            int i3 = 0;
            int i4 = 0;
            if ((this.mGroupFlags & 34) == 34) {
                i3 = rect.top;
                i4 = rect.bottom;
            }
            int i5 = i3 - top;
            int height = bottom - (getHeight() - i4);
            i3 = (getHeight() - this.mPaddingBottom) - this.mPaddingTop;
            if (i < 0) {
                max = Math.max(-(i3 - 1), i);
            } else {
                max = Math.min(i3 - 1, i);
            }
            if (i2 < 0) {
                max2 = Math.max(-(i3 - 1), i2);
            } else {
                max2 = Math.min(i3 - 1, i2);
            }
            int i6 = this.mFirstPosition;
            if (i6 == 0) {
                this.mFirstPositionDistanceGuess = top - rect.top;
            } else {
                this.mFirstPositionDistanceGuess += max2;
            }
            if (i6 + childCount == this.mItemCount) {
                this.mLastPositionDistanceGuess = rect.bottom + bottom;
            } else {
                this.mLastPositionDistanceGuess += max2;
            }
            Object obj = (i6 != 0 || top < rect.top || max2 < 0) ? null : 1;
            Object obj2 = (i6 + childCount != this.mItemCount || bottom > getHeight() - rect.bottom || max2 > 0) ? null : 1;
            if (obj == null && obj2 == null) {
                boolean z;
                if (max2 < 0) {
                    z = true;
                } else {
                    z = false;
                }
                boolean isInTouchMode = isInTouchMode();
                if (isInTouchMode) {
                    hideSelector();
                }
                int headerViewsCount = getHeaderViewsCount();
                int footerViewsCount = this.mItemCount - getFooterViewsCount();
                top = 0;
                if (z) {
                    i4 = -max2;
                    if ((this.mGroupFlags & 34) == 34) {
                        i4 += rect.top;
                    }
                    i3 = 0;
                    bottom = 0;
                    while (bottom < childCount) {
                        View childAt = getChildAt(bottom);
                        if (childAt.getBottom() >= i4) {
                            break;
                        }
                        int i7 = i3 + 1;
                        i3 = i6 + bottom;
                        if (i3 >= headerViewsCount && i3 < footerViewsCount) {
                            this.mRecycler.addScrapView(childAt, i3);
                        }
                        bottom++;
                        i3 = i7;
                    }
                    i4 = i3;
                    i3 = 0;
                } else {
                    i4 = getHeight() - max2;
                    if ((this.mGroupFlags & 34) == 34) {
                        i4 -= rect.bottom;
                    }
                    i3 = 0;
                    for (bottom = childCount - 1; bottom >= 0; bottom--) {
                        View childAt2 = getChildAt(bottom);
                        if (childAt2.getTop() <= i4) {
                            i4 = i3;
                            i3 = top;
                            break;
                        }
                        top = i3 + 1;
                        i3 = i6 + bottom;
                        if (i3 >= headerViewsCount && i3 < footerViewsCount) {
                            this.mRecycler.addScrapView(childAt2, i3);
                        }
                        i3 = top;
                        top = bottom;
                    }
                    i4 = i3;
                    i3 = top;
                }
                this.mMotionViewNewTop = this.mMotionViewOriginalTop + max;
                this.mBlockLayoutRequests = true;
                if (i4 > 0) {
                    detachViewsFromParent(i3, i4);
                }
                offsetChildrenTopAndBottom(max2);
                if (z) {
                    this.mFirstPosition = i4 + this.mFirstPosition;
                }
                invalidate();
                i4 = Math.abs(max2);
                if (i5 < i4 || height < i4) {
                    fillGap(z);
                }
                if (!isInTouchMode && this.mSelectedPosition != -1) {
                    i4 = this.mSelectedPosition - this.mFirstPosition;
                    if (i4 >= 0 && i4 < getChildCount()) {
                        positionSelector(this.mSelectedPosition, getChildAt(i4));
                    }
                } else if (this.mSelectorPosition != -1) {
                    i4 = this.mSelectorPosition - this.mFirstPosition;
                    if (i4 >= 0 && i4 < getChildCount()) {
                        positionSelector(-1, getChildAt(i4));
                    }
                } else {
                    this.mSelectorRect.setEmpty();
                }
                this.mBlockLayoutRequests = false;
                invokeOnItemScrollListener();
                awakenScrollBars();
                AdapterView.traceEnd();
                return false;
            }
            boolean z2;
            if (max2 != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            AdapterView.traceEnd();
            return z2;
        } catch (Throwable th) {
            AdapterView.traceEnd();
        }
    }

    int getHeaderViewsCount() {
        return 0;
    }

    int getFooterViewsCount() {
        return 0;
    }

    void hideSelector() {
        if (this.mSelectedPosition != -1) {
            if (this.mLayoutMode != 4) {
                this.mResurrectToPosition = this.mSelectedPosition;
            }
            if (this.mNextSelectedPosition >= 0 && this.mNextSelectedPosition != this.mSelectedPosition) {
                this.mResurrectToPosition = this.mNextSelectedPosition;
            }
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
            this.mSelectedTop = 0;
        }
    }

    int reconcileSelectedPosition() {
        int i = this.mSelectedPosition;
        if (i < 0) {
            i = this.mResurrectToPosition;
        }
        return Math.min(Math.max(0, i), this.mItemCount - 1);
    }

    int findClosestMotionRow(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return -1;
        }
        int findMotionRow = findMotionRow(i);
        return findMotionRow == -1 ? (this.mFirstPosition + childCount) - 1 : findMotionRow;
    }

    public void invalidateViews() {
        this.mDataChanged = true;
        rememberSyncState();
        requestLayout();
        invalidate();
    }

    boolean resurrectSelectionIfNeeded() {
        if (this.mSelectedPosition >= 0 || !resurrectSelection()) {
            return false;
        }
        updateSelectorState();
        return true;
    }

    boolean resurrectSelection() {
        boolean z = true;
        int childCount = getChildCount();
        if (childCount <= 0) {
            return false;
        }
        int top;
        int i;
        boolean z2;
        boolean z3 = this.mListPadding.top;
        int i2 = (this.mBottom - this.mTop) - this.mListPadding.bottom;
        int i3 = this.mFirstPosition;
        int i4 = this.mResurrectToPosition;
        if (i4 >= i3 && i4 < i3 + childCount) {
            View childAt = getChildAt(i4 - this.mFirstPosition);
            top = childAt.getTop();
            childCount = childAt.getBottom();
            if (top < z3) {
                top = getVerticalFadingEdgeLength() + z3;
            } else if (childCount > i2) {
                top = (i2 - childAt.getMeasuredHeight()) - getVerticalFadingEdgeLength();
            }
            i = top;
            z2 = true;
        } else if (i4 < i3) {
            boolean top2;
            i2 = 0;
            z2 = false;
            while (i2 < childCount) {
                boolean z4;
                top2 = getChildAt(i2).getTop();
                if (i2 != 0) {
                    z4 = z3;
                    z3 = z2;
                    z2 = z4;
                } else if (i3 > 0 || top2 < z3) {
                    z2 = getVerticalFadingEdgeLength() + z3;
                    z3 = top2;
                } else {
                    z2 = z3;
                    z3 = top2;
                }
                if (top2 >= z2) {
                    top = i3 + i2;
                    break;
                }
                i2++;
                z4 = z2;
                z2 = z3;
                z3 = z4;
            }
            top2 = z2;
            top = i3;
            i = i4;
            i4 = top;
            z2 = true;
        } else {
            int i5 = this.mItemCount;
            top = (i3 + childCount) - 1;
            int i6 = childCount - 1;
            i = 0;
            while (i6 >= 0) {
                int i7;
                View childAt2 = getChildAt(i6);
                i4 = childAt2.getTop();
                int bottom = childAt2.getBottom();
                if (i6 != childCount - 1) {
                    i7 = i2;
                    i2 = i;
                    i = i7;
                } else if (i3 + childCount < i5 || bottom > i2) {
                    i = i2 - getVerticalFadingEdgeLength();
                    i2 = i4;
                } else {
                    i = i2;
                    i2 = i4;
                }
                if (bottom <= i) {
                    i = i4;
                    i4 = i3 + i6;
                    z2 = false;
                    break;
                }
                i6--;
                i7 = i;
                i = i2;
                i2 = i7;
            }
            i4 = top;
            z2 = false;
        }
        this.mResurrectToPosition = -1;
        removeCallbacks(this.mFlingRunnable);
        if (this.mPositionScroller != null) {
            this.mPositionScroller.stop();
        }
        if (this.mBottomScroller != null) {
            this.mBottomScroller.stop();
        }
        this.mTouchMode = -1;
        clearScrollingCache();
        this.mSpecificTop = i;
        top = lookForSelectablePosition(i4, z2);
        if (top < i3 || top > getLastVisiblePosition()) {
            top = -1;
        } else {
            this.mLayoutMode = 4;
            updateSelectorState();
            setSelectionInt(top);
            invokeOnItemScrollListener();
        }
        reportScrollStateChange(0);
        if (top < 0) {
            z = false;
        }
        return z;
    }

    @TargetApi(11)
    void confirmCheckedPositionsById() {
        this.mCheckStates.clear();
        int i = 0;
        boolean z = false;
        while (i < this.mCheckedIdStates.size()) {
            int max;
            boolean z2;
            long keyAt = this.mCheckedIdStates.keyAt(i);
            int intValue = ((Integer) this.mCheckedIdStates.valueAt(i)).intValue();
            if (keyAt != this.mAdapter.getItemId(intValue)) {
                boolean z3;
                int min = Math.min(intValue + 20, this.mItemCount);
                for (max = Math.max(0, intValue - 20); max < min; max++) {
                    if (keyAt == this.mAdapter.getItemId(max)) {
                        this.mCheckStates.put(max, true);
                        this.mCheckedIdStates.setValueAt(i, Integer.valueOf(max));
                        z3 = true;
                        break;
                    }
                }
                z3 = false;
                if (!z3) {
                    this.mCheckedIdStates.delete(keyAt);
                    max = i - 1;
                    this.mCheckedItemCount--;
                    if (!(this.mChoiceActionMode == null || this.mMultiChoiceModeCallback == null)) {
                        this.mMultiChoiceModeCallback.onItemCheckedStateChanged(this.mChoiceActionMode, intValue, keyAt, false);
                    }
                    i = max;
                    z = true;
                }
                max = i;
                z2 = z;
            } else {
                this.mCheckStates.put(intValue, true);
                max = i;
                z2 = z;
            }
            z = z2;
            i = max + 1;
        }
        if (z && this.mChoiceActionMode != null) {
            this.mChoiceActionMode.invalidate();
        }
    }

    protected void handleDataChanged() {
        int i = this.mItemCount;
        int i2 = this.mLastHandledItemCount;
        this.mLastHandledItemCount = this.mItemCount;
        if (!(this.mChoiceMode == 0 || this.mAdapter == null || !this.mAdapter.hasStableIds())) {
            confirmCheckedPositionsById();
        }
        if (i > 0) {
            int height;
            int bottom;
            if (this.mNeedSync) {
                this.mNeedSync = false;
                if (this.mTranscriptMode == 2) {
                    this.mScrollToBottom = true;
                } else if (this.mTranscriptMode == 1) {
                    if (this.mForceTranscriptScroll) {
                        this.mForceTranscriptScroll = false;
                        this.mScrollToBottom = true;
                    } else if (getChildCount() > 0) {
                        int childCount = getChildCount();
                        height = getHeight() - this.mPaddingBottom;
                        View childAt = getChildAt(childCount - 1);
                        if (childAt != null) {
                            bottom = childAt.getBottom();
                        } else {
                            bottom = height;
                        }
                        if (childCount + this.mFirstPosition >= i2 && r0 <= height) {
                            this.mLayoutMode = 3;
                            return;
                        }
                    }
                }
                switch (this.mSyncMode) {
                    case 0:
                        if (isInTouchMode()) {
                            this.mLayoutMode = 5;
                            this.mSyncPosition = Math.min(Math.max(0, this.mSyncPosition), i - 1);
                            return;
                        }
                        bottom = findSyncPosition();
                        if (bottom >= 0 && lookForSelectablePosition(bottom, true) == bottom) {
                            this.mSyncPosition = bottom;
                            if (this.mSyncHeight == ((long) getHeight())) {
                                this.mLayoutMode = 5;
                            } else {
                                this.mLayoutMode = 2;
                            }
                            setNextSelectedPositionInt(bottom);
                            return;
                        }
                    case 1:
                        this.mLayoutMode = 5;
                        this.mSyncPosition = Math.min(Math.max(0, this.mSyncPosition), i - 1);
                        return;
                    case 2:
                        this.mLayoutMode = 5;
                        this.mSyncPosition = Math.max(Math.min(this.mSyncPosition, i - 1), 0);
                        return;
                }
            }
            if (!isInTouchMode()) {
                bottom = getSelectedItemPosition();
                if (bottom >= i) {
                    bottom = i - 1;
                }
                if (bottom < 0) {
                    bottom = 0;
                }
                height = lookForSelectablePosition(bottom, true);
                if (height >= 0) {
                    setNextSelectedPositionInt(height);
                    return;
                }
                bottom = lookForSelectablePosition(bottom, false);
                if (bottom >= 0) {
                    setNextSelectedPositionInt(bottom);
                    return;
                }
            } else if (this.mResurrectToPosition >= 0) {
                return;
            }
        }
        this.mLayoutMode = this.mStackFromBottom ? 3 : 1;
        this.mSelectedPosition = -1;
        this.mSelectedRowId = Long.MIN_VALUE;
        this.mNextSelectedPosition = -1;
        this.mNextSelectedRowId = Long.MIN_VALUE;
        this.mNeedSync = false;
        this.mSelectorPosition = -1;
        checkSelectionChanged();
    }

    void rememberSyncState() {
        int i = -1;
        if (getChildCount() > 0) {
            this.mNeedSync = true;
            this.mSyncHeight = (long) this.mLayoutHeight;
            if (this.mSelectedPosition >= 0) {
                View childAt = getChildAt(this.mSelectedPosition - this.mFirstPosition);
                this.mSyncRowId = this.mNextSelectedRowId;
                this.mSyncPosition = this.mNextSelectedPosition;
                if (childAt != null) {
                    this.mSpecificTop = childAt.getTop();
                    this.mSpecificBottom = this.mLayoutHeight - childAt.getBottom();
                }
                this.mSyncMode = 0;
            } else if ((this.mScrollY != 0 || this.mStackFromBottom) && this.mScrollY >= 0) {
                r0 = (ListAdapter) getAdapter();
                int childCount = getChildCount();
                if (this.mFirstPosition != -1) {
                    i = (this.mFirstPosition + childCount) - 1;
                }
                View childAt2 = getChildAt(childCount - 1);
                if (i < 0 || i >= r0.getCount()) {
                    this.mSyncRowId = -1;
                } else {
                    this.mSyncRowId = r0.getItemId(i);
                }
                this.mSyncPosition = i;
                if (childAt2 != null) {
                    this.mSpecificTop = childAt2.getTop();
                    this.mSpecificBottom = this.mLayoutHeight - childAt2.getBottom();
                }
                this.mSyncMode = 2;
            } else {
                View childAt3 = getChildAt(0);
                r0 = (ListAdapter) getAdapter();
                if (this.mFirstPosition < 0 || this.mFirstPosition >= r0.getCount()) {
                    this.mSyncRowId = -1;
                } else {
                    this.mSyncRowId = r0.getItemId(this.mFirstPosition);
                }
                this.mSyncPosition = this.mFirstPosition;
                if (childAt3 != null) {
                    this.mSpecificTop = childAt3.getTop();
                    this.mSpecificBottom = this.mLayoutHeight - childAt3.getBottom();
                }
                this.mSyncMode = 1;
            }
        }
    }

    protected void onDisplayHint(int i) {
        super.onDisplayHint(i);
        switch (i) {
            case 0:
                if (!(!this.mFiltered || this.mPopup == null || this.mPopup.isShowing())) {
                    showPopup();
                    break;
                }
            case 4:
                if (this.mPopup != null && this.mPopup.isShowing()) {
                    dismissPopup();
                    break;
                }
        }
        this.mPopupHidden = i == 4;
    }

    private void dismissPopup() {
        if (this.mPopup != null) {
            this.mPopup.dismiss();
        }
    }

    private void showPopup() {
        if (getWindowVisibility() == 0) {
            createTextFilter(true);
            positionPopup();
            checkFocus();
        }
    }

    private void positionPopup() {
        int i = getResources().getDisplayMetrics().heightPixels;
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        i = ((i - iArr[1]) - getHeight()) + ((int) (this.mDensityScale * 20.0f));
        if (this.mPopup.isShowing()) {
            this.mPopup.update(iArr[0], i, -1, -1);
        } else {
            this.mPopup.showAtLocation(this, 81, iArr[0], i);
        }
    }

    static int getDistance(Rect rect, Rect rect2, int i) {
        int width;
        int height;
        int width2;
        int height2;
        switch (i) {
            case 1:
            case 2:
                width = rect.right + (rect.width() / 2);
                height = rect.top + (rect.height() / 2);
                width2 = (rect2.width() / 2) + rect2.left;
                height2 = rect2.top + (rect2.height() / 2);
                break;
            case 17:
                width = rect.left;
                height = rect.top + (rect.height() / 2);
                width2 = rect2.right;
                height2 = rect2.top + (rect2.height() / 2);
                break;
            case 33:
                width = rect.left + (rect.width() / 2);
                height = rect.top;
                width2 = (rect2.width() / 2) + rect2.left;
                height2 = rect2.bottom;
                break;
            case 66:
                width = rect.right;
                height = rect.top + (rect.height() / 2);
                width2 = rect2.left;
                height2 = rect2.top + (rect2.height() / 2);
                break;
            case Opcodes.INT_TO_FLOAT /*130*/:
                width = rect.left + (rect.width() / 2);
                height = rect.bottom;
                width2 = (rect2.width() / 2) + rect2.left;
                height2 = rect2.top;
                break;
            default:
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT, FOCUS_FORWARD, FOCUS_BACKWARD}.");
        }
        width2 -= width;
        height2 -= height;
        return (height2 * height2) + (width2 * width2);
    }

    protected boolean isInFilterMode() {
        return this.mFiltered;
    }

    boolean sendToTextFilter(int i, int i2, KeyEvent keyEvent) {
        if (!acceptFilter()) {
            return false;
        }
        boolean z;
        boolean z2;
        switch (i) {
            case 4:
                if (this.mFiltered && this.mPopup != null && this.mPopup.isShowing()) {
                    if (keyEvent.getAction() != 0 || keyEvent.getRepeatCount() != 0) {
                        if (keyEvent.getAction() == 1 && keyEvent.isTracking() && !keyEvent.isCanceled()) {
                            this.mTextFilter.setText("");
                            z = true;
                            z2 = z;
                            z = false;
                            break;
                        }
                    }
                    DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    z = true;
                    z2 = z;
                    z = false;
                }
                z = false;
                z2 = z;
                z = false;
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 66:
                z = false;
                z2 = false;
                break;
            case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                z = this.mFiltered;
                z2 = false;
                break;
            default:
                z = true;
                z2 = false;
                break;
        }
        if (z) {
            KeyEvent changeTimeRepeat;
            createTextFilter(true);
            if (keyEvent.getRepeatCount() > 0) {
                changeTimeRepeat = KeyEvent.changeTimeRepeat(keyEvent, keyEvent.getEventTime(), 0);
            } else {
                changeTimeRepeat = keyEvent;
            }
            switch (keyEvent.getAction()) {
                case 0:
                    z = this.mTextFilter.onKeyDown(i, changeTimeRepeat);
                    break;
                case 1:
                    z = this.mTextFilter.onKeyUp(i, changeTimeRepeat);
                    break;
                case 2:
                    z = this.mTextFilter.onKeyMultiple(i, i2, keyEvent);
                    break;
            }
        }
        z = z2;
        return z;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (!isTextFilterEnabled()) {
            return null;
        }
        createTextFilter(false);
        if (this.mPublicInputConnection == null) {
            this.mDefInputConnection = new BaseInputConnection(this, false);
            this.mPublicInputConnection = new 3(this, this.mTextFilter.onCreateInputConnection(editorInfo), true);
        }
        editorInfo.inputType = Opcodes.SUB_INT_2ADDR;
        editorInfo.imeOptions = 6;
        return this.mPublicInputConnection;
    }

    public boolean checkInputConnectionProxy(View view) {
        return view == this.mTextFilter;
    }

    private void createTextFilter(boolean z) {
        if (this.mPopup == null) {
            Context context = getContext();
            PopupWindow popupWindow = new PopupWindow(context);
            this.mTextFilter = (EditText) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.typing_filter, null);
            this.mTextFilter.setRawInputType(Opcodes.SUB_INT_2ADDR);
            this.mTextFilter.setImeOptions(SigType.TLS);
            this.mTextFilter.addTextChangedListener(this);
            popupWindow.setFocusable(false);
            popupWindow.setTouchable(false);
            popupWindow.setInputMethodMode(2);
            popupWindow.setContentView(this.mTextFilter);
            popupWindow.setWidth(-2);
            popupWindow.setHeight(-2);
            popupWindow.setBackgroundDrawable(null);
            this.mPopup = popupWindow;
            getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.mGlobalLayoutListenerAddedFilter = true;
        }
    }

    public void clearTextFilter() {
        if (this.mFiltered) {
            this.mTextFilter.setText("");
            this.mFiltered = false;
            if (this.mPopup != null && this.mPopup.isShowing()) {
                dismissPopup();
            }
        }
    }

    public boolean hasTextFilter() {
        return this.mFiltered;
    }

    public void onGlobalLayout() {
        if (isShown()) {
            if (this.mFiltered && this.mPopup != null && !this.mPopup.isShowing() && !this.mPopupHidden) {
                showPopup();
            }
        } else if (this.mPopup != null && this.mPopup.isShowing()) {
            dismissPopup();
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mPopup != null && isTextFilterEnabled()) {
            int length = charSequence.length();
            boolean isShowing = this.mPopup.isShowing();
            if (!isShowing && length > 0) {
                showPopup();
                this.mFiltered = true;
            } else if (isShowing && length == 0) {
                dismissPopup();
                this.mFiltered = false;
            }
            if (this.mAdapter instanceof Filterable) {
                Filter filter = ((Filterable) this.mAdapter).getFilter();
                if (filter != null) {
                    filter.filter(charSequence, this);
                    return;
                }
                throw new IllegalStateException("You cannot call onTextChanged with a non filterable adapter");
            }
        }
    }

    public void afterTextChanged(Editable editable) {
    }

    public void onFilterComplete(int i) {
        if (this.mSelectedPosition < 0 && i > 0) {
            this.mResurrectToPosition = -1;
            resurrectSelection();
        }
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setTranscriptMode(int i) {
        this.mTranscriptMode = i;
    }

    public int getTranscriptMode() {
        return this.mTranscriptMode;
    }

    public int getSolidColor() {
        return this.mCacheColorHint;
    }

    public void setCacheColorHint(int i) {
        if (i != this.mCacheColorHint) {
            this.mCacheColorHint = i;
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                getChildAt(i2).setDrawingCacheBackgroundColor(i);
            }
            this.mRecycler.setCacheColorHint(i);
        }
    }

    @ExportedProperty(category = "drawing")
    public int getCacheColorHint() {
        return this.mCacheColorHint;
    }

    public void reclaimViews(List<View> list) {
        int childCount = getChildCount();
        RecyclerListener access$3600 = RecycleBin.access$3600(this.mRecycler);
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams != null && this.mRecycler.shouldRecycleViewType(layoutParams.viewType)) {
                list.add(childAt);
                if (access$3600 != null) {
                    access$3600.onMovedToScrapHeap(childAt);
                }
            }
        }
        this.mRecycler.reclaimScrapViews(list);
        removeAllViewsInLayout();
    }

    protected boolean onConsistencyCheck(int i) {
        boolean onConsistencyCheck = super.onConsistencyCheck(i);
        if (((i & 1) != 0 ? 1 : null) != null) {
            for (View view : RecycleBin.access$3700(this.mRecycler)) {
                if (view != null) {
                    onConsistencyCheck = false;
                }
            }
            if (!checkScrap(RecycleBin.access$3800(this.mRecycler))) {
                onConsistencyCheck = false;
            }
            for (ArrayList checkScrap : RecycleBin.access$3900(this.mRecycler)) {
                if (!checkScrap(checkScrap)) {
                    onConsistencyCheck = false;
                }
            }
        }
        return onConsistencyCheck;
    }

    private boolean checkScrap(ArrayList<View> arrayList) {
        boolean z = true;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                View view = (View) arrayList.get(i);
                if (view.getParent() != null) {
                    z = false;
                }
                if (indexOfChild(view) >= 0) {
                    z = false;
                }
            }
        }
        return z;
    }

    private void finishGlows() {
        if (this.mEdgeGlowTop != null) {
            this.mEdgeGlowTop.finish();
            this.mEdgeGlowBottom.finish();
        }
    }

    public void deferNotifyDataSetChanged() {
        this.mDeferNotifyDataSetChanged = true;
    }

    public void onRemoteAdapterDisconnected() {
    }

    public void setRecyclerListener(RecyclerListener recyclerListener) {
        RecycleBin.access$3602(this.mRecycler, recyclerListener);
    }

    static View retrieveFromScrap(ArrayList<View> arrayList, int i) {
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        for (int i2 = 0; i2 < size; i2++) {
            View view = (View) arrayList.get(i2);
            if (((LayoutParams) view.getLayoutParams()).scrappedFromPosition == i) {
                arrayList.remove(i2);
                return view;
            }
        }
        return (View) arrayList.remove(size - 1);
    }

    @TargetApi(9)
    private Object enterCriticalSpan(String str) {
        return null;
    }

    @TargetApi(9)
    private Object finishSpan(Object obj) {
        return null;
    }

    @TargetApi(14)
    protected boolean performButtonActionOnTouchDown(MotionEvent motionEvent) {
        if (VersionUtils.isIceScreamSandwich() && (motionEvent.getButtonState() & 2) != 0 && showContextMenu(motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState())) {
            return true;
        }
        return false;
    }

    private void dispatchStartTemporaryDetachForView(View view) {
        try {
            View.class.getMethod("dispatchStartTemporaryDetach", new Class[0]).invoke(view, new Object[0]);
        } catch (Exception e) {
            view.onStartTemporaryDetach();
        }
    }

    private void dispatchFinishTemporaryDetachForView(View view) {
        try {
            View.class.getMethod("dispatchFinishTemporaryDetach", new Class[0]).invoke(view, new Object[0]);
        } catch (Exception e) {
            view.onFinishTemporaryDetach();
        }
    }

    @TargetApi(9)
    protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        boolean z2;
        int i9 = this.mOverScrollMode;
        Object obj = computeHorizontalScrollRange() > computeHorizontalScrollExtent() ? 1 : null;
        Object obj2 = computeVerticalScrollRange() > computeVerticalScrollExtent() ? 1 : null;
        Object obj3 = (i9 == 0 || (i9 == 1 && obj != null)) ? 1 : null;
        obj = (i9 == 0 || (i9 == 1 && obj2 != null)) ? 1 : null;
        int i10 = i3 + i;
        if (obj3 == null) {
            i7 = 0;
        }
        i9 = i4 + i2;
        if (obj == null) {
            i8 = 0;
        }
        int i11 = -i7;
        int i12 = i7 + i5;
        int i13 = -i8;
        int i14 = i8 + i6;
        if (i10 > i12) {
            i11 = i12;
            z2 = true;
        } else if (i10 < i11) {
            z2 = true;
        } else {
            z2 = false;
            i11 = i10;
        }
        boolean z3 = false;
        if (i9 > i14) {
            z3 = true;
        } else if (i9 < i13) {
            z3 = true;
            i14 = i13;
        } else {
            i14 = i9;
        }
        onOverScrolled(i11, i14, z2, z3);
        if (z2 || z3) {
            return true;
        }
        return false;
    }

    public int getOverScrollMode() {
        return this.mOverScrollMode;
    }

    protected void doSpringBack() {
        if (this.mFlingRunnable == null) {
            this.mFlingRunnable = new FlingRunnable(this);
        }
        this.mFlingRunnable.startSpringback(0);
    }

    protected void abordFling() {
        if (this.mFlingRunnable != null) {
            this.mFlingRunnable.endFling();
        }
    }

    public void setMaximumVelocity(int i) {
        this.mMaximumVelocity = i;
    }
}
