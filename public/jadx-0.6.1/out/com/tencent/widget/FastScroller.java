package com.tencent.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.SectionIndexer;
import com.tencent.feedback.proguard.R;
import com.tencent.util.VersionUtils;

class FastScroller {
    private static final int[] ATTRS = new int[]{16843609, 16843574, 16843577, 16843575, 16843576, 16843578};
    private static final int[] DEFAULT_STATES = new int[0];
    private static final int FADE_TIMEOUT = 1500;
    private static int MIN_PAGES = 4;
    private static final int OVERLAY_AT_THUMB = 1;
    private static final int OVERLAY_FLOATING = 0;
    private static final int OVERLAY_POSITION = 5;
    private static final int PENDING_DRAG_DELAY = 180;
    private static final int[] PRESSED_STATES = new int[]{16842919};
    private static final int PREVIEW_BACKGROUND_LEFT = 3;
    private static final int PREVIEW_BACKGROUND_RIGHT = 4;
    private static final int STATE_DRAGGING = 3;
    private static final int STATE_ENTER = 1;
    private static final int STATE_EXIT = 4;
    private static final int STATE_NONE = 0;
    private static final int STATE_VISIBLE = 2;
    private static final String TAG = "FastScroller";
    private static final int TEXT_COLOR = 0;
    private static final int THUMB_DRAWABLE = 1;
    private static final int TRACK_DRAWABLE = 2;
    private boolean mAlwaysShow;
    private boolean mChangedBounds;
    private final Runnable mDeferStartDrag = new Runnable() {
        public void run() {
            if (FastScroller.this.mList.mIsAttached) {
                FastScroller.this.beginDrag();
                int height = FastScroller.this.mList.getHeight();
                int i = (((int) FastScroller.this.mInitialTouchY) - FastScroller.this.mThumbH) + 10;
                if (i < 0) {
                    i = 0;
                } else if (FastScroller.this.mThumbH + i > height) {
                    i = height - FastScroller.this.mThumbH;
                }
                FastScroller.this.mThumbY = i;
                FastScroller.this.scrollTo(((float) FastScroller.this.mThumbY) / ((float) (height - FastScroller.this.mThumbH)));
            }
            FastScroller.this.mPendingDrag = false;
        }
    };
    private boolean mDrawOverlay;
    private Handler mHandler = new Handler();
    float mInitialTouchY;
    private int mItemCount = -1;
    AbsListView mList;
    BaseAdapter mListAdapter;
    private int mListOffset;
    private boolean mLongList;
    private boolean mMatchDragPosition;
    private Drawable mOverlayDrawable;
    private Drawable mOverlayDrawableLeft;
    private Drawable mOverlayDrawableRight;
    private RectF mOverlayPos;
    private int mOverlayPosition;
    private int mOverlaySize;
    private Paint mPaint;
    boolean mPendingDrag;
    private int mPosition;
    private int mScaledTouchSlop;
    boolean mScrollCompleted;
    private ScrollFade mScrollFade;
    private SectionIndexer mSectionIndexer;
    private String mSectionText;
    private Object[] mSections;
    private int mState;
    private Drawable mThumbDrawable;
    int mThumbH;
    int mThumbW;
    int mThumbY;
    private final Rect mTmpRect = new Rect();
    private Drawable mTrackDrawable;
    private int mVisibleItem;

    public class ScrollFade implements Runnable {
        static final int ALPHA_MAX = 208;
        static final long FADE_DURATION = 200;
        long mFadeDuration;
        long mStartTime;

        void startFade() {
            this.mFadeDuration = 200;
            this.mStartTime = SystemClock.uptimeMillis();
            FastScroller.this.setState(4);
        }

        int getAlpha() {
            if (FastScroller.this.getState() != 4) {
                return 208;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis > this.mStartTime + this.mFadeDuration) {
                return 0;
            }
            return (int) (208 - (((uptimeMillis - this.mStartTime) * 208) / this.mFadeDuration));
        }

        public void run() {
            if (FastScroller.this.getState() != 4) {
                startFade();
            } else if (getAlpha() > 0) {
                FastScroller.this.mList.invalidate();
            } else {
                FastScroller.this.setState(0);
            }
        }
    }

    public FastScroller(Context context, AbsListView absListView) {
        this.mList = absListView;
        init(context);
    }

    public void setAlwaysShow(boolean z) {
        this.mAlwaysShow = z;
        if (z) {
            this.mHandler.removeCallbacks(this.mScrollFade);
            setState(2);
        } else if (this.mState == 2) {
            this.mHandler.postDelayed(this.mScrollFade, 1500);
        }
    }

    public boolean isAlwaysShowEnabled() {
        return this.mAlwaysShow;
    }

    private void refreshDrawableState() {
        int[] iArr = this.mState == 3 ? PRESSED_STATES : DEFAULT_STATES;
        if (this.mThumbDrawable != null && this.mThumbDrawable.isStateful()) {
            this.mThumbDrawable.setState(iArr);
        }
        if (this.mTrackDrawable != null && this.mTrackDrawable.isStateful()) {
            this.mTrackDrawable.setState(iArr);
        }
    }

    public void setScrollbarPosition(int i) {
        this.mPosition = i;
        switch (i) {
            case 1:
                this.mOverlayDrawable = this.mOverlayDrawableLeft;
                return;
            default:
                this.mOverlayDrawable = this.mOverlayDrawableRight;
                return;
        }
    }

    public int getWidth() {
        return this.mThumbW;
    }

    public void setState(int i) {
        switch (i) {
            case 0:
                this.mHandler.removeCallbacks(this.mScrollFade);
                this.mList.invalidate();
                break;
            case 2:
                if (this.mState != 2) {
                    resetThumbPos();
                    break;
                }
                break;
            case 3:
                break;
            case 4:
                int width = this.mList.getWidth();
                this.mList.invalidate(width - this.mThumbW, this.mThumbY, width, this.mThumbY + this.mThumbH);
                break;
        }
        this.mHandler.removeCallbacks(this.mScrollFade);
        this.mState = i;
        refreshDrawableState();
    }

    public int getState() {
        return this.mState;
    }

    private void resetThumbPos() {
        int width = this.mList.getWidth();
        switch (this.mPosition) {
            case 0:
            case 2:
                this.mThumbDrawable.setBounds(width - this.mThumbW, 0, width, this.mThumbH);
                break;
            case 1:
                this.mThumbDrawable.setBounds(0, 0, this.mThumbW, this.mThumbH);
                break;
        }
        this.mThumbDrawable.setAlpha(208);
    }

    private void useThumbDrawable(Context context, Drawable drawable) {
        this.mThumbDrawable = drawable;
        if (drawable instanceof NinePatchDrawable) {
            this.mThumbW = context.getResources().getDimensionPixelSize(R.dimen.fastscroll_thumb_width);
            this.mThumbH = context.getResources().getDimensionPixelSize(R.dimen.fastscroll_thumb_height);
        } else {
            this.mThumbW = drawable.getIntrinsicWidth();
            this.mThumbH = drawable.getIntrinsicHeight();
        }
        this.mChangedBounds = true;
    }

    @TargetApi(11)
    private void init(Context context) {
        boolean z = true;
        int i = 0;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(ATTRS);
        useThumbDrawable(context, obtainStyledAttributes.getDrawable(1));
        this.mTrackDrawable = obtainStyledAttributes.getDrawable(2);
        this.mOverlayDrawableLeft = obtainStyledAttributes.getDrawable(3);
        this.mOverlayDrawableRight = obtainStyledAttributes.getDrawable(4);
        this.mOverlayPosition = obtainStyledAttributes.getInt(5, 0);
        this.mScrollCompleted = true;
        getSectionsFromIndexer();
        this.mOverlaySize = context.getResources().getDimensionPixelSize(R.dimen.fastscroll_overlay_size);
        this.mOverlayPos = new RectF();
        this.mScrollFade = new ScrollFade();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Align.CENTER);
        this.mPaint.setTextSize((float) (this.mOverlaySize / 2));
        this.mPaint.setColor(obtainStyledAttributes.getColorStateList(0).getDefaultColor());
        this.mPaint.setStyle(Style.FILL_AND_STROKE);
        if (this.mList.getWidth() > 0 && this.mList.getHeight() > 0) {
            onSizeChanged(this.mList.getWidth(), this.mList.getHeight(), 0, 0);
        }
        this.mState = 0;
        refreshDrawableState();
        obtainStyledAttributes.recycle();
        this.mScaledTouchSlop = ViewConfiguration.get(context.getApplicationContext()).getScaledTouchSlop();
        if (context.getApplicationInfo().targetSdkVersion < 11) {
            z = false;
        }
        this.mMatchDragPosition = z;
        if (VersionUtils.isHoneycomb()) {
            i = this.mList.getVerticalScrollbarPosition();
        }
        setScrollbarPosition(i);
    }

    void stop() {
        setState(0);
    }

    boolean isVisible() {
        return this.mState != 0;
    }

    public void draw(Canvas canvas) {
        if (this.mState != 0) {
            int alpha;
            int i = this.mThumbY;
            int width = this.mList.getWidth();
            ScrollFade scrollFade = this.mScrollFade;
            int i2 = -1;
            if (this.mState == 4) {
                alpha = scrollFade.getAlpha();
                if (alpha < 104) {
                    this.mThumbDrawable.setAlpha(alpha * 2);
                }
                switch (this.mPosition) {
                    case 0:
                    case 2:
                        i2 = width - ((this.mThumbW * alpha) / 208);
                        break;
                    case 1:
                        i2 = (-this.mThumbW) + ((this.mThumbW * alpha) / 208);
                        break;
                    default:
                        i2 = 0;
                        break;
                }
                this.mThumbDrawable.setBounds(i2, 0, this.mThumbW + i2, this.mThumbH);
                this.mChangedBounds = true;
                i2 = alpha;
            }
            if (this.mTrackDrawable != null) {
                Rect bounds = this.mThumbDrawable.getBounds();
                int i3 = bounds.left;
                alpha = (bounds.bottom - bounds.top) / 2;
                int intrinsicWidth = this.mTrackDrawable.getIntrinsicWidth();
                i3 = (i3 + (this.mThumbW / 2)) - (intrinsicWidth / 2);
                this.mTrackDrawable.setBounds(i3, alpha, intrinsicWidth + i3, this.mList.getHeight() - alpha);
                this.mTrackDrawable.draw(canvas);
            }
            canvas.translate(0.0f, (float) i);
            this.mThumbDrawable.draw(canvas);
            canvas.translate(0.0f, (float) (-i));
            if (this.mState == 3 && this.mDrawOverlay) {
                RectF rectF;
                if (this.mOverlayPosition == 1) {
                    switch (this.mPosition) {
                        case 1:
                            i2 = Math.min(this.mThumbDrawable.getBounds().right + this.mThumbW, this.mList.getWidth() - this.mOverlaySize);
                            break;
                        default:
                            i2 = Math.max(0, (this.mThumbDrawable.getBounds().left - this.mThumbW) - this.mOverlaySize);
                            break;
                    }
                    int max = Math.max(0, Math.min(((this.mThumbH - this.mOverlaySize) / 2) + i, this.mList.getHeight() - this.mOverlaySize));
                    rectF = this.mOverlayPos;
                    rectF.left = (float) i2;
                    rectF.right = rectF.left + ((float) this.mOverlaySize);
                    rectF.top = (float) max;
                    rectF.bottom = rectF.top + ((float) this.mOverlaySize);
                    if (this.mOverlayDrawable != null) {
                        this.mOverlayDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                    }
                }
                this.mOverlayDrawable.draw(canvas);
                Paint paint = this.mPaint;
                float descent = paint.descent();
                rectF = this.mOverlayPos;
                Rect rect = this.mTmpRect;
                this.mOverlayDrawable.getPadding(rect);
                width = (rect.right - rect.left) / 2;
                i = (rect.bottom - rect.top) / 2;
                canvas.drawText(this.mSectionText, (float) ((((int) (rectF.left + rectF.right)) / 2) - width), (((float) ((((int) (rectF.top + rectF.bottom)) / 2) + (this.mOverlaySize / 4))) - descent) - ((float) i), paint);
            } else if (this.mState != 4) {
            } else {
                if (i2 == 0) {
                    setState(0);
                } else if (this.mTrackDrawable != null) {
                    this.mList.invalidate(width - this.mThumbW, 0, width, this.mList.getHeight());
                } else {
                    this.mList.invalidate(width - this.mThumbW, i, width, this.mThumbH + i);
                }
            }
        }
    }

    void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.mThumbDrawable != null) {
            switch (this.mPosition) {
                case 1:
                    this.mThumbDrawable.setBounds(0, 0, this.mThumbW, this.mThumbH);
                    break;
                default:
                    this.mThumbDrawable.setBounds(i - this.mThumbW, 0, i, this.mThumbH);
                    break;
            }
        }
        if (this.mOverlayPosition == 0) {
            RectF rectF = this.mOverlayPos;
            rectF.left = (float) ((i - this.mOverlaySize) / 2);
            rectF.right = rectF.left + ((float) this.mOverlaySize);
            rectF.top = (float) (i2 / 10);
            rectF.bottom = rectF.top + ((float) this.mOverlaySize);
            if (this.mOverlayDrawable != null) {
                this.mOverlayDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            }
        }
    }

    void onItemCountChanged(int i, int i2) {
        if (this.mAlwaysShow) {
            this.mLongList = true;
        }
    }

    void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.mItemCount != i3 && i2 > 0) {
            this.mItemCount = i3;
            this.mLongList = this.mItemCount / i2 >= MIN_PAGES;
        }
        if (this.mAlwaysShow) {
            this.mLongList = true;
        }
        if (this.mLongList) {
            if (i3 - i2 > 0 && this.mState != 3) {
                this.mThumbY = getThumbPositionForListPosition(i, i2, i3);
                if (this.mChangedBounds) {
                    resetThumbPos();
                    this.mChangedBounds = false;
                }
            }
            this.mScrollCompleted = true;
            if (i != this.mVisibleItem) {
                this.mVisibleItem = i;
                if (this.mState != 3) {
                    setState(2);
                    if (!this.mAlwaysShow) {
                        this.mHandler.postDelayed(this.mScrollFade, 1500);
                    }
                }
            }
        } else if (this.mState != 0) {
            setState(0);
        }
    }

    SectionIndexer getSectionIndexer() {
        return this.mSectionIndexer;
    }

    Object[] getSections() {
        if (this.mListAdapter == null && this.mList != null) {
            getSectionsFromIndexer();
        }
        return this.mSections;
    }

    void getSectionsFromIndexer() {
        Adapter adapter = this.mList.getAdapter();
        this.mSectionIndexer = null;
        if (adapter instanceof HeaderViewListAdapter) {
            this.mListOffset = ((HeaderViewListAdapter) adapter).getHeadersCount();
            ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        }
    }

    public void onSectionsChanged() {
        this.mListAdapter = null;
    }

    void scrollTo(float f) {
        int i;
        int count = this.mList.getCount();
        this.mScrollCompleted = false;
        float f2 = (1.0f / ((float) count)) / 8.0f;
        Object[] objArr = this.mSections;
        int i2;
        if (objArr == null || objArr.length <= 1) {
            if (((int) (((float) count) * f)) > count - 1) {
                i2 = count - 1;
            }
            i = -1;
        } else {
            int positionForSection;
            int i3;
            int length = objArr.length;
            i2 = (int) (((float) length) * f);
            if (i2 >= length) {
                i2 = length - 1;
            }
            int positionForSection2 = this.mSectionIndexer.getPositionForSection(i2);
            int i4 = i2 + 1;
            if (i2 < length - 1) {
                positionForSection = this.mSectionIndexer.getPositionForSection(i2 + 1);
            } else {
                positionForSection = count;
            }
            if (positionForSection == positionForSection2) {
                i3 = positionForSection2;
                i = i2;
                while (i > 0) {
                    i3 = i - 1;
                    i = this.mSectionIndexer.getPositionForSection(i3);
                    if (i != positionForSection2) {
                        positionForSection2 = i;
                        i = i3;
                        break;
                    } else if (i3 == 0) {
                        positionForSection2 = i;
                        i = 0;
                        i3 = i2;
                        break;
                    } else {
                        int i5 = i;
                        i = i3;
                        i3 = i5;
                    }
                }
                positionForSection2 = i3;
                i = i2;
                i3 = i2;
            } else {
                i3 = i2;
                i = i2;
            }
            int i6 = i4 + 1;
            while (i6 < length && this.mSectionIndexer.getPositionForSection(i6) == positionForSection) {
                i6++;
                i4++;
            }
            float f3 = ((float) i3) / ((float) length);
            float f4 = ((float) i4) / ((float) length);
            if (i3 != i2 || f - f3 >= f2) {
                positionForSection2 += (int) ((((float) (positionForSection - positionForSection2)) * (f - f3)) / (f4 - f3));
            }
            if (positionForSection2 > count - 1) {
                i2 = count - 1;
            }
        }
        if (i >= 0) {
            String obj = objArr[i].toString();
            this.mSectionText = obj;
            boolean z = !(obj.length() == 1 && obj.charAt(0) == ' ') && i < objArr.length;
            this.mDrawOverlay = z;
            return;
        }
        this.mDrawOverlay = false;
    }

    private int getThumbPositionForListPosition(int i, int i2, int i3) {
        if (this.mSectionIndexer == null || this.mListAdapter == null) {
            getSectionsFromIndexer();
        }
        if (this.mSectionIndexer == null || !this.mMatchDragPosition) {
            return ((this.mList.getHeight() - this.mThumbH) * i) / (i3 - i2);
        }
        int i4 = i - this.mListOffset;
        if (i4 < 0) {
            return 0;
        }
        float f;
        int i5 = i3 - this.mListOffset;
        int height = this.mList.getHeight() - this.mThumbH;
        int sectionForPosition = this.mSectionIndexer.getSectionForPosition(i4);
        int positionForSection = this.mSectionIndexer.getPositionForSection(sectionForPosition);
        int positionForSection2 = this.mSectionIndexer.getPositionForSection(sectionForPosition + 1);
        int length = this.mSections.length;
        positionForSection2 -= positionForSection;
        View childAt = this.mList.getChildAt(0);
        if (childAt == null) {
            f = 0.0f;
        } else {
            f = (((float) (this.mList.getPaddingTop() - childAt.getTop())) / ((float) childAt.getHeight())) + ((float) i4);
        }
        int i6 = (int) (((((f - ((float) positionForSection)) / ((float) positionForSection2)) + ((float) sectionForPosition)) / ((float) length)) * ((float) height));
        if (i4 <= 0 || i4 + i2 != i5) {
            return i6;
        }
        View childAt2 = this.mList.getChildAt(i2 - 1);
        return (int) ((((float) (height - i6)) * (((float) ((this.mList.getHeight() - this.mList.getPaddingBottom()) - childAt2.getTop())) / ((float) childAt2.getHeight()))) + ((float) i6));
    }

    private void cancelFling() {
        MotionEvent obtain = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
        this.mList.onTouchEvent(obtain);
        obtain.recycle();
    }

    void cancelPendingDrag() {
        this.mList.removeCallbacks(this.mDeferStartDrag);
        this.mPendingDrag = false;
    }

    void startPendingDrag() {
        this.mPendingDrag = true;
        this.mList.postDelayed(this.mDeferStartDrag, 180);
    }

    void beginDrag() {
        setState(3);
        if (this.mListAdapter == null && this.mList != null) {
            getSectionsFromIndexer();
        }
        if (this.mList != null) {
            this.mList.requestDisallowInterceptTouchEvent(true);
            this.mList.reportScrollStateChange(1);
        }
        cancelFling();
    }

    boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                if (this.mState > 0 && isPointInside(motionEvent.getX(), motionEvent.getY())) {
                    if (this.mList.isInScrollingContainer()) {
                        this.mInitialTouchY = motionEvent.getY();
                        startPendingDrag();
                        break;
                    }
                    beginDrag();
                    return true;
                }
            case 1:
            case 3:
                cancelPendingDrag();
                break;
        }
        return false;
    }

    boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (this.mState == 0) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (!isPointInside(motionEvent.getX(), motionEvent.getY())) {
                return false;
            }
            if (this.mList.isInScrollingContainer()) {
                this.mInitialTouchY = motionEvent.getY();
                startPendingDrag();
                return false;
            }
            beginDrag();
            return true;
        } else if (action == 1) {
            if (this.mPendingDrag) {
                beginDrag();
                r3 = this.mList.getHeight();
                action = (((int) motionEvent.getY()) - this.mThumbH) + 10;
                if (action < 0) {
                    action = 0;
                } else if (this.mThumbH + action > r3) {
                    action = r3 - this.mThumbH;
                }
                this.mThumbY = action;
                scrollTo(((float) this.mThumbY) / ((float) (r3 - this.mThumbH)));
                cancelPendingDrag();
            }
            if (this.mState != 3) {
                return false;
            }
            if (this.mList != null) {
                this.mList.requestDisallowInterceptTouchEvent(false);
                this.mList.reportScrollStateChange(0);
            }
            setState(2);
            Handler handler = this.mHandler;
            handler.removeCallbacks(this.mScrollFade);
            if (!this.mAlwaysShow) {
                handler.postDelayed(this.mScrollFade, 1000);
            }
            this.mList.invalidate();
            return true;
        } else if (action == 2) {
            if (this.mPendingDrag && Math.abs(motionEvent.getY() - this.mInitialTouchY) > ((float) this.mScaledTouchSlop)) {
                setState(3);
                if (this.mListAdapter == null && this.mList != null) {
                    getSectionsFromIndexer();
                }
                if (this.mList != null) {
                    this.mList.requestDisallowInterceptTouchEvent(true);
                    this.mList.reportScrollStateChange(1);
                }
                cancelFling();
                cancelPendingDrag();
            }
            if (this.mState != 3) {
                return false;
            }
            r3 = this.mList.getHeight();
            action = (((int) motionEvent.getY()) - this.mThumbH) + 10;
            if (action >= 0) {
                if (this.mThumbH + action > r3) {
                    i = r3 - this.mThumbH;
                } else {
                    i = action;
                }
            }
            if (Math.abs(this.mThumbY - i) < 2) {
                return true;
            }
            this.mThumbY = i;
            if (this.mScrollCompleted) {
                scrollTo(((float) this.mThumbY) / ((float) (r3 - this.mThumbH)));
            }
            return true;
        } else if (action != 3) {
            return false;
        } else {
            cancelPendingDrag();
            return false;
        }
    }

    boolean isPointInside(float f, float f2) {
        boolean z;
        switch (this.mPosition) {
            case 1:
                if (f >= ((float) this.mThumbW)) {
                    z = false;
                    break;
                }
                z = true;
                break;
            default:
                if (f <= ((float) (this.mList.getWidth() - this.mThumbW))) {
                    z = false;
                    break;
                }
                z = true;
                break;
        }
        if (z) {
            if (this.mTrackDrawable != null) {
                return true;
            }
            if (f2 >= ((float) this.mThumbY) && f2 <= ((float) (this.mThumbY + this.mThumbH))) {
                return true;
            }
        }
        return false;
    }
}
