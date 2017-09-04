package com.tencent.av.camera;

import android.hardware.Camera.Size;
import java.util.Comparator;

public class CameraSizeComparator implements Comparator {
    public int compare(Object obj, Object obj2) {
        Size size = (Size) obj;
        Size size2 = (Size) obj2;
        if (size.width < size2.width) {
            return -1;
        }
        if (size.width > size2.width) {
            return 1;
        }
        if (size.height < size2.height) {
            return -1;
        }
        if (size.height > size2.height) {
            return 1;
        }
        return 0;
    }
}
