package com.tencent.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

public class TypedArrayWarpper {
    private TypedArray a;

    public TypedArrayWarpper(TypedArray typedArray) {
        this.a = typedArray;
    }

    public int length() {
        return this.a.length();
    }

    public int getIndexCount() {
        return this.a.getIndexCount();
    }

    public int getIndex(int i) {
        return this.a.getIndex(i);
    }

    public Resources getResources() {
        return this.a.getResources();
    }

    public CharSequence getText(int i) {
        if (i >= 0) {
            return this.a.getText(i);
        }
        return null;
    }

    public String getString(int i) {
        if (i >= 0) {
            return this.a.getString(i);
        }
        return null;
    }

    public String getNonResourceString(int i) {
        if (i >= 0) {
            return this.a.getNonResourceString(i);
        }
        return null;
    }

    public boolean getBoolean(int i, boolean z) {
        if (i >= 0) {
            return this.a.getBoolean(i, z);
        }
        return z;
    }

    public int getInt(int i, int i2) {
        if (i >= 0) {
            return this.a.getInt(i, i2);
        }
        return i2;
    }

    public float getFloat(int i, float f) {
        if (i >= 0) {
            return this.a.getFloat(i, f);
        }
        return f;
    }

    public int getColor(int i, int i2) {
        if (i >= 0) {
            return this.a.getColor(i, i2);
        }
        return i2;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public ColorStateList getColorStateList(int i) {
        if (i >= 0) {
            return this.a.getColorStateList(i);
        }
        return null;
    }

    public int getInteger(int i, int i2) {
        if (i >= 0) {
            return this.a.getInteger(i, i2);
        }
        return i2;
    }

    public float getDimension(int i, float f) {
        if (i >= 0) {
            return this.a.getDimension(i, f);
        }
        return f;
    }

    public int getDimensionPixelOffset(int i, int i2) {
        if (i >= 0) {
            return this.a.getDimensionPixelOffset(i, i2);
        }
        return i2;
    }

    public int getDimensionPixelSize(int i, int i2) {
        if (i >= 0) {
            return this.a.getDimensionPixelSize(i, i2);
        }
        return i2;
    }

    public int getLayoutDimension(int i, String str) {
        return this.a.getLayoutDimension(i, str);
    }

    public int getLayoutDimension(int i, int i2) {
        if (i >= 0) {
            return this.a.getLayoutDimension(i, i2);
        }
        return i2;
    }

    public float getFraction(int i, int i2, int i3, float f) {
        if (i >= 0) {
            return this.a.getFraction(i, i2, i3, f);
        }
        return f;
    }

    public int getResourceId(int i, int i2) {
        if (i >= 0) {
            return this.a.getResourceId(i, i2);
        }
        return i2;
    }

    public Drawable getDrawable(int i) {
        if (i >= 0) {
            return this.a.getDrawable(i);
        }
        return null;
    }

    public CharSequence[] getTextArray(int i) {
        if (i >= 0) {
            return this.a.getTextArray(i);
        }
        return null;
    }

    public boolean getValue(int i, TypedValue typedValue) {
        if (i >= 0) {
            return this.a.getValue(i, typedValue);
        }
        return false;
    }

    public boolean hasValue(int i) {
        if (i >= 0) {
            return this.a.hasValue(i);
        }
        return false;
    }

    public TypedValue peekValue(int i) {
        if (i >= 0) {
            return this.a.peekValue(i);
        }
        return null;
    }

    public String getPositionDescription() {
        return this.a.getPositionDescription();
    }

    public void recycle() {
        this.a.recycle();
    }

    public String toString() {
        return this.a.toString();
    }
}
