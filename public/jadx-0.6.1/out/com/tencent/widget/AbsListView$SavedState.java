package com.tencent.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseBooleanArray;
import android.view.View.BaseSavedState;
import com.tencent.util.LongSparseArray;

public class AbsListView$SavedState extends BaseSavedState {
    public static final Creator<AbsListView$SavedState> CREATOR = new Creator<AbsListView$SavedState>() {
        public AbsListView$SavedState createFromParcel(Parcel parcel) {
            return new AbsListView$SavedState(parcel);
        }

        public AbsListView$SavedState[] newArray(int i) {
            return new AbsListView$SavedState[i];
        }
    };
    LongSparseArray<Integer> checkIdState;
    SparseBooleanArray checkState;
    int checkedItemCount;
    String filter;
    long firstId;
    int height;
    boolean inActionMode;
    int position;
    long selectedId;
    int viewTop;

    AbsListView$SavedState(Parcelable parcelable) {
        super(parcelable);
        this.selectedId = -1;
    }

    private AbsListView$SavedState(Parcel parcel) {
        int i = 0;
        super(parcel);
        this.selectedId = -1;
        this.selectedId = parcel.readLong();
        this.firstId = parcel.readLong();
        this.viewTop = parcel.readInt();
        this.position = parcel.readInt();
        this.height = parcel.readInt();
        this.filter = parcel.readString();
        this.inActionMode = parcel.readByte() != (byte) 0;
        this.checkedItemCount = parcel.readInt();
        this.checkState = parcel.readSparseBooleanArray();
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.checkIdState = new LongSparseArray();
            while (i < readInt) {
                this.checkIdState.put(parcel.readLong(), Integer.valueOf(parcel.readInt()));
                i++;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int size;
        int i2 = 0;
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.selectedId);
        parcel.writeLong(this.firstId);
        parcel.writeInt(this.viewTop);
        parcel.writeInt(this.position);
        parcel.writeInt(this.height);
        parcel.writeString(this.filter);
        parcel.writeByte((byte) (this.inActionMode ? 1 : 0));
        parcel.writeInt(this.checkedItemCount);
        parcel.writeSparseBooleanArray(this.checkState);
        if (this.checkIdState != null) {
            size = this.checkIdState.size();
        } else {
            size = 0;
        }
        parcel.writeInt(size);
        while (i2 < size) {
            parcel.writeLong(this.checkIdState.keyAt(i2));
            parcel.writeInt(((Integer) this.checkIdState.valueAt(i2)).intValue());
            i2++;
        }
    }

    public String toString() {
        return "AbsListView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.selectedId + " firstId=" + this.firstId + " viewTop=" + this.viewTop + " position=" + this.position + " height=" + this.height + " filter=" + this.filter + " checkState=" + this.checkState + "}";
    }
}
