package com.tencent.theme;

import android.content.res.ColorStateList;
import android.os.Parcel;
import android.os.Parcelable.Creator;

class SkinnableColorStateList$1 implements Creator<ColorStateList> {
    SkinnableColorStateList$1() {
    }

    public ColorStateList[] newArray(int i) {
        return new ColorStateList[i];
    }

    public ColorStateList createFromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        int[][] iArr = new int[readInt][];
        for (int i = 0; i < readInt; i++) {
            iArr[i] = parcel.createIntArray();
        }
        return new ColorStateList(iArr, parcel.createIntArray());
    }
}
