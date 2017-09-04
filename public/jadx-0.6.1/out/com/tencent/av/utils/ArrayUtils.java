package com.tencent.av.utils;

public class ArrayUtils {
    public static int indexOf(Object[] objArr, Object obj) {
        return indexOf(objArr, obj, 0);
    }

    public static int indexOf(Object[] objArr, Object obj, int i) {
        if (objArr == null) {
            return -1;
        }
        int i2;
        if (i < 0) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (obj == null) {
            while (i2 < objArr.length) {
                if (objArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
        } else if (objArr.getClass().getComponentType().isInstance(obj)) {
            while (i2 < objArr.length) {
                if (obj.equals(objArr[i2])) {
                    return i2;
                }
                i2++;
            }
        }
        return -1;
    }

    public static int lastIndexOf(Object[] objArr, Object obj) {
        return lastIndexOf(objArr, obj, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(Object[] objArr, Object obj, int i) {
        if (objArr == null || i < 0) {
            return -1;
        }
        int length;
        if (i >= objArr.length) {
            length = objArr.length - 1;
        } else {
            length = i;
        }
        if (obj == null) {
            while (length >= 0) {
                if (objArr[length] == null) {
                    return length;
                }
                length--;
            }
            return -1;
        } else if (!objArr.getClass().getComponentType().isInstance(obj)) {
            return -1;
        } else {
            while (length >= 0) {
                if (obj.equals(objArr[length])) {
                    return length;
                }
                length--;
            }
            return -1;
        }
    }

    public static boolean contains(Object[] objArr, Object obj) {
        return indexOf(objArr, obj) != -1;
    }

    public static int indexOf(int[] iArr, int i) {
        return indexOf(iArr, i, 0);
    }

    public static int indexOf(int[] iArr, int i, int i2) {
        if (iArr == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < iArr.length) {
            if (i == iArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static boolean contains(int[] iArr, int i) {
        return indexOf(iArr, i) != -1;
    }
}
