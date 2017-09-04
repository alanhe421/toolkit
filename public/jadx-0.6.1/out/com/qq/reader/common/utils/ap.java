package com.qq.reader.common.utils;

import android.util.SparseArray;
import android.view.View;
import com.tencent.feedback.proguard.R;

/* compiled from: ViewHolder */
public class ap {
    public static <T extends View> T a(View view, int i) {
        if (view == null) {
            return null;
        }
        SparseArray sparseArray;
        SparseArray sparseArray2 = (SparseArray) view.getTag(R.string.view_holder_key);
        if (sparseArray2 == null) {
            sparseArray2 = new SparseArray();
            view.setTag(R.string.view_holder_key, sparseArray2);
            sparseArray = sparseArray2;
        } else {
            sparseArray = sparseArray2;
        }
        View view2 = (View) sparseArray.get(i);
        if (view2 != null) {
            return view2;
        }
        T findViewById = view.findViewById(i);
        sparseArray.put(i, findViewById);
        return findViewById;
    }

    public static boolean b(View view, int i) {
        SparseArray sparseArray = (SparseArray) view.getTag();
        if (sparseArray == null) {
            return false;
        }
        return ((View) sparseArray.get(i)) != null;
    }
}
