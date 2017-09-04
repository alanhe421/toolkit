package com.tencent.widget;

import android.graphics.Point;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import java.util.HashSet;
import java.util.Iterator;

class ListView$ListDataSetObserver extends AbsListView$AdapterDataSetObserver implements ListDataSetListener {
    final /* synthetic */ ListView this$0;

    ListView$ListDataSetObserver(ListView listView) {
        this.this$0 = listView;
        super(listView);
    }

    public void onRowInserted(int i, int i2) {
        int i3;
        int i4;
        int headerViewsCount;
        int i5;
        if (i != 0 || this.this$0.mItemCount <= 0) {
            int[] iArr;
            if (ListView.access$300(this.this$0) == null) {
                iArr = new int[((i2 - i) + 1)];
                i3 = 0;
                while (i <= i2) {
                    i4 = i3 + 1;
                    iArr[i3] = i;
                    i++;
                    i3 = i4;
                }
                ListView.access$302(this.this$0, iArr);
            } else {
                HashSet hashSet = new HashSet();
                while (i <= i2) {
                    hashSet.add(Integer.valueOf(i));
                    i++;
                }
                for (int i52 : ListView.access$300(this.this$0)) {
                    hashSet.add(Integer.valueOf(i52));
                }
                iArr = new int[hashSet.size()];
                Iterator it = hashSet.iterator();
                i4 = 0;
                while (it.hasNext()) {
                    i3 = i4 + 1;
                    iArr[i4] = ((Integer) it.next()).intValue();
                    i4 = i3;
                }
                ListView.access$302(this.this$0, iArr);
            }
            onChanged();
            return;
        }
        System.nanoTime();
        if (i2 < i) {
            throw new IllegalArgumentException("lastRow must more than firstRow!");
        } else if (i < 0 || i > (this.this$0.mItemCount - this.this$0.getHeaderViewsCount()) - this.this$0.getFooterViewsCount()) {
            throw new IllegalArgumentException("row index out of bound");
        } else if (this.this$0.mNeedSync || this.this$0.mDataChanged) {
            onChanged();
        } else if (this.this$0.mItemCount != 0) {
            Object obj;
            int headerViewsCount2 = i + this.this$0.getHeaderViewsCount();
            headerViewsCount = i2 + this.this$0.getHeaderViewsCount();
            int i6 = (headerViewsCount - headerViewsCount2) + 1;
            int childCount = this.this$0.getChildCount();
            i52 = this.this$0.mFirstPosition;
            int i7 = (this.this$0.mFirstPosition + childCount) - 1;
            if (headerViewsCount2 == this.this$0.mItemCount) {
                obj = 1;
            } else {
                obj = null;
            }
            if (this.this$0.mFastScroller != null) {
                this.this$0.mFastScroller.onItemCountChanged(this.this$0.mItemCount, this.this$0.mItemCount - i6);
            }
            this.this$0.mItemCount += i6;
            if (this.this$0.mSelectedPosition >= 0) {
                i3 = calcNewPositionAdd(this.this$0.mSelectedPosition, headerViewsCount2, headerViewsCount, i52, i7);
                this.this$0.setSelectedPositionInt(i3);
                this.this$0.setNextSelectedPositionInt(i3);
            } else if (this.this$0.mSelectorPosition >= 0) {
                this.this$0.mSelectedPosition = calcNewPositionAdd(this.this$0.mSelectorPosition, headerViewsCount2, headerViewsCount, i52, i7);
            }
            if (this.this$0.mMotionPosition >= 0) {
                this.this$0.mMotionPosition = calcNewPositionAdd(this.this$0.mMotionPosition, headerViewsCount2, headerViewsCount, i52, i7);
            }
            if (this.this$0.mCheckStates == null) {
                i4 = 0;
            } else {
                i4 = this.this$0.mCheckStates.size();
            }
            if (i4 > 0) {
                for (i3 = 0; i3 < i4; i3++) {
                    int keyAt = this.this$0.mCheckStates.keyAt(i3);
                    boolean valueAt = this.this$0.mCheckStates.valueAt(i3);
                    if (keyAt >= headerViewsCount2) {
                        this.this$0.mCheckStates.delete(keyAt);
                        this.this$0.mCheckStates.put(keyAt + ((headerViewsCount - headerViewsCount2) + 1), valueAt);
                    }
                }
            }
            if (ListView.access$300(this.this$0) != null) {
                for (i3 = 0; i3 < ListView.access$300(this.this$0).length; i3++) {
                    i4 = ListView.access$300(this.this$0)[i3];
                    if (i4 >= headerViewsCount2) {
                        ListView.access$300(this.this$0)[i3] = i4 + ((headerViewsCount - headerViewsCount2) + 1);
                    }
                }
            }
            int access$400 = (ListView.access$400(this.this$0) - ListView.access$500(this.this$0)) - this.this$0.mListPadding.bottom;
            childCount = this.this$0.getChildAt(childCount - 1).getBottom();
            Object obj2 = (this.this$0.getTranscriptMode() != 1 || obj == null || childCount > access$400) ? null : 1;
            if (headerViewsCount2 <= i52 && obj2 == null && childCount >= access$400) {
                ListView listView = this.this$0;
                listView.mFirstPosition += i6;
            } else if (headerViewsCount2 <= i7 || obj2 != null || childCount < access$400) {
                int i8 = this.this$0.mListPadding.top;
                i3 = this.this$0.mSelectedPosition >= 0 ? this.this$0.mSelectedPosition : this.this$0.shouldShowSelector() ? this.this$0.mSelectorPosition : -1;
                int i9 = headerViewsCount2 - i52;
                int i10;
                int i11;
                View obtainView;
                if (obj2 != null) {
                    i7 = headerViewsCount;
                    i10 = 0;
                    i11 = access$400;
                    while (i7 >= headerViewsCount2) {
                        if (i11 < i8) {
                            i4 = i7 + 1;
                            break;
                        }
                        obtainView = this.this$0.obtainView(i7, this.this$0.mIsScrap);
                        ListView.access$600(this.this$0, obtainView, i7, i11, false, this.this$0.mListPadding.left, false, this.this$0.mIsScrap[0], i9);
                        i4 = obtainView.getHeight() + this.this$0.mDividerHeight;
                        i11 -= i4;
                        i7--;
                        i10 = i4 + i10;
                    }
                    i4 = -1;
                    if (i4 >= 0) {
                        for (i3 = 0; i3 <= i9 - 1; i3++) {
                            this.this$0.mRecycler.addScrapView(this.this$0.getChildAt(i3), this.this$0.mFirstPosition + i3);
                        }
                        ListView.access$700(this.this$0, 0, i9);
                        this.this$0.mFirstPosition = i4;
                    } else {
                        offsetTop(i8, i9 - 1, i10);
                    }
                } else if ((i3 < 0 || i3 >= headerViewsCount2) && (i3 >= 0 || this.this$0.mStackFromBottom)) {
                    if (access$400 > childCount) {
                        this.this$0.offsetChildrenTopAndBottom(access$400 - childCount);
                    }
                    i11 = this.this$0.getChildAt(i9).getTop() - this.this$0.mDividerHeight;
                    i7 = headerViewsCount;
                    i10 = 0;
                    while (i7 >= headerViewsCount2) {
                        if (i11 < i8) {
                            i4 = i7 + 1;
                            break;
                        }
                        obtainView = this.this$0.obtainView(i7, this.this$0.mIsScrap);
                        ListView.access$600(this.this$0, obtainView, i7, i11, false, this.this$0.mListPadding.left, false, this.this$0.mIsScrap[0], i9);
                        i4 = obtainView.getHeight() + this.this$0.mDividerHeight;
                        i11 -= i4;
                        i7--;
                        i10 = i4 + i10;
                    }
                    i4 = -1;
                    if (i4 >= 0) {
                        for (i3 = 0; i3 <= i9 - 1; i3++) {
                            this.this$0.mRecycler.addScrapView(this.this$0.getChildAt(i3), this.this$0.mFirstPosition + i3);
                        }
                        ListView.access$900(this.this$0, 0, i9);
                        this.this$0.mFirstPosition = i4;
                    } else {
                        offsetTop(i8, i9 - 1, i10);
                    }
                } else {
                    i4 = 0;
                    i3 = -1;
                    if (i9 > 0) {
                        i6 = this.this$0.getChildAt(i9 - 1).getBottom() + this.this$0.mDividerHeight;
                        for (i11 = headerViewsCount2; i11 <= headerViewsCount; i11++) {
                            if (i6 > access$400) {
                                i3 = i11 - this.this$0.mFirstPosition;
                                break;
                            }
                            View obtainView2 = this.this$0.obtainView(i11, this.this$0.mIsScrap);
                            ListView.access$600(this.this$0, obtainView2, i11, i6, true, this.this$0.mListPadding.left, false, this.this$0.mIsScrap[0], i11 - this.this$0.mFirstPosition);
                            headerViewsCount2 = obtainView2.getHeight() + this.this$0.mDividerHeight;
                            i6 += headerViewsCount2;
                            i4 += headerViewsCount2;
                        }
                        headerViewsCount2 = this.this$0.getChildCount();
                        if (i3 >= 0) {
                            for (i4 = i3; i4 < headerViewsCount2; i4++) {
                                this.this$0.mRecycler.addScrapView(this.this$0.getChildAt(i4), this.this$0.mFirstPosition + i4);
                            }
                            ListView.access$800(this.this$0, i3, headerViewsCount2 - i3);
                        } else {
                            offsetBottom(this.this$0.getChildCount(), access$400, (headerViewsCount - this.this$0.mFirstPosition) + 1, i4);
                        }
                    } else {
                        return;
                    }
                }
                ListView.access$1000(this.this$0);
            }
        }
    }

    public void onRowDeleted(int... iArr) {
        onChanged();
    }

    public void onRowDeleted(int i, int i2) {
        System.nanoTime();
        if (i2 < i) {
            throw new IllegalArgumentException("lastRow must more than firstRow!");
        } else if (i < 0 || i2 >= (this.this$0.mItemCount - this.this$0.getHeaderViewsCount()) - this.this$0.getFooterViewsCount()) {
            throw new IllegalArgumentException("row index out of bound");
        } else if (this.this$0.mNeedSync || this.this$0.mDataChanged) {
            onChanged();
        } else {
            int i3;
            int keyAt;
            int headerViewsCount = i + this.this$0.getHeaderViewsCount();
            int headerViewsCount2 = i2 + this.this$0.getHeaderViewsCount();
            int i4 = (headerViewsCount2 - headerViewsCount) + 1;
            int childCount = this.this$0.getChildCount();
            int i5 = this.this$0.mFirstPosition;
            int i6 = (this.this$0.mFirstPosition + childCount) - 1;
            if (this.this$0.mFastScroller != null) {
                this.this$0.mFastScroller.onItemCountChanged(this.this$0.mItemCount, this.this$0.mItemCount - i4);
            }
            this.this$0.mItemCount -= i4;
            if (this.this$0.mSelectedPosition >= 0) {
                i4 = calcNewPosition(this.this$0.mSelectedPosition, headerViewsCount, headerViewsCount2, i5, i6);
                this.this$0.setSelectedPositionInt(i4);
                this.this$0.setNextSelectedPositionInt(i4);
            } else if (this.this$0.mSelectorPosition >= 0) {
                this.this$0.mSelectedPosition = calcNewPosition(this.this$0.mSelectorPosition, headerViewsCount, headerViewsCount2, i5, i6);
            }
            if (this.this$0.mMotionPosition >= 0) {
                this.this$0.mMotionPosition = calcNewPosition(this.this$0.mMotionPosition, headerViewsCount, headerViewsCount2, i5, i6);
            }
            if (this.this$0.mCheckStates == null) {
                i3 = 0;
            } else {
                i3 = this.this$0.mCheckStates.size();
            }
            if (i3 > 0) {
                i4 = 0;
                while (i4 < i3) {
                    keyAt = this.this$0.mCheckStates.keyAt(i4);
                    boolean valueAt = this.this$0.mCheckStates.valueAt(i4);
                    if (keyAt >= headerViewsCount) {
                        this.this$0.mCheckStates.delete(keyAt);
                        if (keyAt <= headerViewsCount2) {
                            i4--;
                            i3--;
                        }
                        if (keyAt > headerViewsCount2) {
                            this.this$0.mCheckStates.put(keyAt - ((headerViewsCount2 - headerViewsCount) + 1), valueAt);
                        }
                    }
                    i4++;
                }
            }
            if (ListView.access$300(this.this$0) != null) {
                for (i4 = 0; i4 < ListView.access$300(this.this$0).length; i4++) {
                    i3 = ListView.access$300(this.this$0)[i4];
                    if (i3 >= headerViewsCount) {
                        if (i3 <= headerViewsCount2) {
                            i3 = -1;
                        } else {
                            i3 -= (headerViewsCount2 - headerViewsCount) + 1;
                        }
                        ListView.access$300(this.this$0)[i4] = i3;
                    }
                }
            }
            if (headerViewsCount2 >= i5 && headerViewsCount <= i6) {
                int i7 = this.this$0.mListPadding.top;
                int access$1100 = (ListView.access$1100(this.this$0) - ListView.access$1200(this.this$0)) - this.this$0.mListPadding.bottom;
                int i8 = headerViewsCount < i5 ? 0 : headerViewsCount - i5;
                i4 = headerViewsCount2 > i6 ? childCount - 1 : headerViewsCount2 - i5;
                i3 = 0;
                while (i3 < childCount) {
                    View childAt = this.this$0.getChildAt(i3);
                    if (i3 >= i8 && i3 <= i4) {
                        this.this$0.mRecycler.addScrapView(childAt, i3);
                        childAt.setTag(2131230829, null);
                    } else if (ListView.access$1300(this.this$0)) {
                        childAt.setTag(2131230829, new Point(childAt.getTop(), childAt.getBottom()));
                    }
                    i3++;
                }
                ListView.access$1400(this.this$0, i8, (i4 - i8) + 1);
                if (this.this$0.mItemCount != 0) {
                    boolean z;
                    int childCount2 = this.this$0.getChildCount();
                    boolean shouldShowSelector = this.this$0.shouldShowSelector();
                    int i9 = this.this$0.mSelectedPosition >= 0 ? this.this$0.mSelectedPosition : shouldShowSelector ? this.this$0.mSelectorPosition : -1;
                    if (headerViewsCount <= i5 && headerViewsCount2 >= i6) {
                        if (this.this$0.mStackFromBottom) {
                            ListView.access$1600(this.this$0, headerViewsCount + -1 < 0 ? 0 : headerViewsCount - 1, access$1100);
                        } else {
                            if (headerViewsCount > this.this$0.mItemCount - 1) {
                                headerViewsCount = this.this$0.mItemCount - 1;
                            }
                            ListView.access$1500(this.this$0, headerViewsCount, i7);
                        }
                        z = true;
                        headerViewsCount = -1;
                        i5 = 0;
                        headerViewsCount2 = 0;
                    } else if (headerViewsCount <= i5) {
                        if (this.this$0.mStackFromBottom || i9 >= 0) {
                            this.this$0.mFirstPosition = headerViewsCount;
                            r3 = this.this$0.getChildAt(i8);
                            i4 = r3.getTop();
                            this.this$0.fillGap(false);
                            ListView.access$1000(this.this$0);
                            headerViewsCount = r3.getTop();
                            i5 = i4 - headerViewsCount;
                            i4 = headerViewsCount - this.this$0.mDividerHeight > 0 ? (-headerViewsCount) + this.this$0.mDividerHeight : 0;
                            headerViewsCount = this.this$0.indexOfChild(r3) - 1;
                            headerViewsCount2 = i4;
                            z = false;
                        } else {
                            r3 = this.this$0.getChildAt(0);
                            headerViewsCount2 = r3.getTop();
                            this.this$0.offsetChildrenTopAndBottom(i7 - r3.getTop());
                            this.this$0.mFirstPosition = headerViewsCount;
                            this.this$0.fillGap(true);
                            ListView.access$1000(this.this$0);
                            i4 = r3.getTop() - this.this$0.mDividerHeight > 0 ? (-r3.getTop()) + this.this$0.mDividerHeight : 0;
                            i5 = headerViewsCount2 - r3.getTop();
                            headerViewsCount = this.this$0.indexOfChild(r3);
                            headerViewsCount2 = i4;
                            z = false;
                        }
                    } else if (headerViewsCount2 >= i6) {
                        if (!this.this$0.mStackFromBottom || i9 >= 0) {
                            r3 = this.this$0.getChildAt(childCount2 - 1);
                            i4 = r3.getBottom();
                            this.this$0.fillGap(true);
                            ListView.access$1000(this.this$0);
                            headerViewsCount = r3.getBottom();
                            headerViewsCount2 = i4 - headerViewsCount;
                            i4 = (access$1100 - headerViewsCount) - this.this$0.mDividerHeight > 0 ? (access$1100 - headerViewsCount) - this.this$0.mDividerHeight : 0;
                            headerViewsCount = this.this$0.indexOfChild(r3);
                            i5 = i4;
                            z = false;
                        } else {
                            r3 = this.this$0.getChildAt(childCount2 - 1);
                            headerViewsCount = r3.getTop();
                            this.this$0.offsetChildrenTopAndBottom(access$1100 - r3.getBottom());
                            this.this$0.fillGap(false);
                            ListView.access$1000(this.this$0);
                            i4 = (access$1100 - r3.getBottom()) - this.this$0.mDividerHeight > 0 ? (access$1100 - r3.getBottom()) - this.this$0.mDividerHeight : 0;
                            headerViewsCount2 = headerViewsCount - r3.getTop();
                            headerViewsCount = this.this$0.indexOfChild(r3);
                            i5 = i4;
                            z = false;
                        }
                    } else if ((i9 >= 0 && i9 < headerViewsCount) || (i9 < 0 && !this.this$0.mStackFromBottom)) {
                        r3 = this.this$0.getChildAt(i8 - 1);
                        headerViewsCount2 = r3.getTop();
                        r7 = this.this$0.getChildAt(i8);
                        childCount = r7.getTop();
                        keyAt = r7.getTop() - r3.getBottom();
                        for (i4 = headerViewsCount - i5; i4 < childCount2; i4++) {
                            this.this$0.getChildAt(i4).offsetTopAndBottom((-keyAt) + this.this$0.mDividerHeight);
                        }
                        this.this$0.fillGap(true);
                        ListView.access$1000(this.this$0);
                        i4 = childCount - r7.getTop();
                        headerViewsCount2 -= r3.getTop();
                        headerViewsCount = this.this$0.indexOfChild(r3);
                        i5 = i4;
                        z = false;
                    } else if (i9 >= headerViewsCount || (i9 < 0 && this.this$0.mStackFromBottom)) {
                        r3 = this.this$0.getChildAt(i8 - 1);
                        headerViewsCount2 = r3.getTop();
                        r7 = this.this$0.getChildAt(i8);
                        childCount = r7.getTop();
                        keyAt = r7.getTop() - this.this$0.getChildAt(i8 - 1).getBottom();
                        for (i4 = 0; i4 < headerViewsCount - i5; i4++) {
                            this.this$0.getChildAt(i4).offsetTopAndBottom(keyAt - this.this$0.mDividerHeight);
                        }
                        this.this$0.fillGap(false);
                        ListView.access$1000(this.this$0);
                        i4 = childCount - r7.getTop();
                        headerViewsCount2 -= r3.getTop();
                        headerViewsCount = this.this$0.indexOfChild(r3);
                        i5 = i4;
                        z = false;
                    } else {
                        headerViewsCount = -1;
                        i5 = 0;
                        headerViewsCount2 = 0;
                        z = false;
                    }
                    i3 = this.this$0.getChildCount();
                    if (i9 >= 0 && i9 < i3 && shouldShowSelector) {
                        this.this$0.positionSelector(i9, this.this$0.getChildAt(i9 - this.this$0.mFirstPosition));
                    }
                    if (ListView.access$1300(this.this$0)) {
                        calcAnimation(i3, headerViewsCount, headerViewsCount2, i5, z);
                    }
                    this.this$0.invalidate();
                }
            }
        }
    }

    public void onRowUpdated(int i, int i2) {
        if (i2 < i) {
            throw new IllegalArgumentException("lastRow must more than firstRow!");
        } else if (i < this.this$0.getHeaderViewsCount() || i2 >= (this.this$0.mItemCount - this.this$0.getHeaderViewsCount()) - this.this$0.getFooterViewsCount()) {
            throw new IllegalArgumentException("row index out of bound. insert range: " + i + "~" + i2 + ". valid range: " + this.this$0.getHeaderViewsCount() + "~" + ((this.this$0.mItemCount - this.this$0.getHeaderViewsCount()) - this.this$0.getFooterViewsCount()));
        } else if (this.this$0.mNeedSync || this.this$0.mDataChanged) {
            onChanged();
        } else if (this.this$0.mItemCount != 0) {
            int headerViewsCount = i + this.this$0.getHeaderViewsCount();
            int headerViewsCount2 = i2 + this.this$0.getHeaderViewsCount();
            int childCount = this.this$0.getChildCount();
            int i3 = this.this$0.mFirstPosition;
            int i4 = (this.this$0.mFirstPosition + childCount) - 1;
            if (headerViewsCount2 >= i3 && headerViewsCount <= i4) {
                int i5 = this.this$0.mListPadding.top;
                int access$1700 = (ListView.access$1700(this.this$0) - ListView.access$1800(this.this$0)) - this.this$0.mListPadding.bottom;
                int i6 = headerViewsCount < i3 ? 0 : headerViewsCount - i3;
                int i7 = headerViewsCount2 > i4 ? childCount - 1 : headerViewsCount2 - i3;
                i3 = this.this$0.mSelectedPosition >= 0 ? this.this$0.mSelectedPosition : this.this$0.shouldShowSelector() ? this.this$0.mSelectorPosition : -1;
                if ((i3 >= 0 && i3 <= headerViewsCount) || (i3 < 0 && !this.this$0.mStackFromBottom)) {
                    i3 = updateAfter(childCount, access$1700, i6, i7);
                    ListView.access$1900(this.this$0, this.this$0.getChildCount());
                    offsetBottom(childCount, access$1700, i7 + 1, i3);
                } else if (i3 >= headerViewsCount2 || (i3 < 0 && this.this$0.mStackFromBottom)) {
                    i7 = updateBefore(i5, i6, i7);
                    ListView.access$2000(this.this$0, this.this$0.getChildCount());
                    offsetTop(i5, i6 - 1, i7);
                } else {
                    i3 -= this.this$0.mFirstPosition;
                    offsetBottom(childCount, access$1700, i7 + 1, updateAfter(childCount, access$1700, i3, i7));
                    offsetTop(i5, i6 - 1, updateBefore(i5, i6, i3 - 1));
                }
                ListView.access$1000(this.this$0);
            }
        }
    }

    private int updateBefore(int i, int i2, int i3) {
        int i4 = i3;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        while (i4 >= i2) {
            if (i5 < i) {
                break;
            }
            View childAt = this.this$0.getChildAt(i4);
            if (i5 == Integer.MAX_VALUE) {
                i5 = childAt.getBottom();
            }
            int height = childAt.getHeight();
            int access$2100 = ListView.access$2100(this.this$0, childAt, this.this$0.mFirstPosition + i4, i5, false, this.this$0.mListPadding.left, i4);
            i6 += access$2100;
            i5 -= (access$2100 + height) + this.this$0.mDividerHeight;
            i4--;
        }
        i4 = -1;
        if (i4 < 0) {
            return i6;
        }
        for (access$2100 = 0; access$2100 <= i4; access$2100++) {
            this.this$0.mRecycler.addScrapView(this.this$0.getChildAt(access$2100), this.this$0.mFirstPosition + access$2100);
        }
        ListView.access$2200(this.this$0, 0, i4 + 1);
        ListView listView = this.this$0;
        listView.mFirstPosition += i4 + 1;
        return 0;
    }

    private void offsetTop(int i, int i2, int i3) {
        if (i2 >= 0) {
            if (i3 > 0) {
                int i4 = -1;
                for (int i5 = i2; i5 >= 0; i5--) {
                    View childAt = this.this$0.getChildAt(i5);
                    if (childAt.getBottom() - i3 < i) {
                        this.this$0.mRecycler.addScrapView(childAt, this.this$0.mFirstPosition + i5);
                        if (i4 < 0) {
                            i4 = i5;
                        }
                    } else {
                        childAt.offsetTopAndBottom(-i3);
                    }
                }
                if (i4 >= 0) {
                    ListView.access$2300(this.this$0, 0, i4 + 1);
                    ListView listView = this.this$0;
                    listView.mFirstPosition = (i4 + 1) + listView.mFirstPosition;
                }
            } else if (i3 < 0) {
                this.this$0.fillGap(false);
            }
        }
    }

    private int updateAfter(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = 0;
        int i7 = Integer.MIN_VALUE;
        for (int i8 = i3; i8 <= i4; i8++) {
            if (i7 > i2) {
                i5 = i8;
                break;
            }
            View childAt = this.this$0.getChildAt(i8);
            if (i7 == Integer.MIN_VALUE) {
                i7 = childAt.getTop();
            }
            int height = childAt.getHeight();
            int access$2100 = ListView.access$2100(this.this$0, childAt, this.this$0.mFirstPosition + i8, i7, true, this.this$0.mListPadding.left, i8);
            i6 += access$2100;
            i7 += (access$2100 + height) + this.this$0.mDividerHeight;
        }
        i5 = -1;
        if (i5 < 0) {
            return i6;
        }
        for (access$2100 = i5; access$2100 < i; access$2100++) {
            this.this$0.mRecycler.addScrapView(this.this$0.getChildAt(access$2100), this.this$0.mFirstPosition + access$2100);
        }
        ListView.access$2400(this.this$0, i5, i - i5);
        return 0;
    }

    private void offsetBottom(int i, int i2, int i3, int i4) {
        if (i4 > 0) {
            int i5 = -1;
            for (int i6 = i3; i6 < i; i6++) {
                View childAt = this.this$0.getChildAt(i6);
                if (childAt.getTop() + i4 > i2) {
                    this.this$0.mRecycler.addScrapView(childAt, this.this$0.mFirstPosition + i6);
                    if (i5 < 0) {
                        i5 = i6;
                    }
                } else {
                    childAt.offsetTopAndBottom(i4);
                }
            }
            if (i5 >= 0) {
                ListView.access$2500(this.this$0, i5, i - i5);
            }
        } else if (i4 < 0) {
            while (i3 < i) {
                this.this$0.getChildAt(i3).offsetTopAndBottom(i4);
                i3++;
            }
            this.this$0.fillGap(true);
        }
    }

    private void calcAnimation(int i, int i2, int i3, int i4, boolean z) {
        int i5 = 0;
        if (z) {
            while (i5 < i) {
                View childAt = this.this$0.getChildAt(i5);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(this.this$0.getDelAnimationDuration());
                childAt.setAnimation(alphaAnimation);
                i5++;
            }
            return;
        }
        while (i5 <= i2) {
            childAt = this.this$0.getChildAt(i5);
            alphaAnimation = new TranslateAnimation(0.0f, 0.0f, (float) i3, 0.0f);
            alphaAnimation.setDuration(this.this$0.getDelAnimationDuration());
            childAt.setAnimation(alphaAnimation);
            i5++;
        }
        for (i5 = i2 + 1; i5 < i; i5++) {
            childAt = this.this$0.getChildAt(i5);
            alphaAnimation = new TranslateAnimation(0.0f, 0.0f, (float) i4, 0.0f);
            alphaAnimation.setDuration(this.this$0.getDelAnimationDuration());
            childAt.setAnimation(alphaAnimation);
        }
    }

    private int calcNewPositionAdd(int i, int i2, int i3, int i4, int i5) {
        if (i > i5 || i < i4) {
            return -1;
        }
        if (i >= i2) {
            return i + ((i3 - i2) + 1);
        }
        return i;
    }

    private int calcNewPosition(int i, int i2, int i3, int i4, int i5) {
        if (i > i5 || i < i4) {
            return -1;
        }
        if (i < i2) {
            return i;
        }
        if (i <= i3) {
            return -1;
        }
        return i - ((i3 - i2) + 1);
    }
}
