package com.tencent.theme;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SkinnableColorStateList extends ColorStateList {
    public static final Creator<ColorStateList> CREATOR = new 1();
    static Constructor C_FACTORY;
    private static final int[][] EMPTY = new int[][]{new int[0]};
    static Field F_FACTORY;
    private static final int[][] TMP = new int[0][];
    private int[] mColors;
    private int mDefaultColor = -65536;
    private int[][] mStateSpecs;
    Object mmFactory;
    public SkinData skinData;

    static {
        if (SkinEngine.IS_MNC_PREVIEW) {
            try {
                C_FACTORY = Class.forName("android.content.res.ColorStateList$ColorStateListFactory").getConstructor(new Class[]{ColorStateList.class});
                C_FACTORY.setAccessible(true);
                F_FACTORY = ColorStateList.class.getDeclaredField("mFactory");
                F_FACTORY.setAccessible(true);
            } catch (Exception e) {
                C_FACTORY = null;
                F_FACTORY = null;
            }
        }
    }

    public SkinnableColorStateList(int[][] iArr, int[] iArr2) {
        int i = 0;
        super(TMP, null);
        this.mStateSpecs = iArr;
        this.mColors = iArr2;
        if (iArr != null && iArr.length > 0) {
            this.mDefaultColor = iArr2[0];
            while (i < iArr.length) {
                if (iArr[i].length == 0) {
                    this.mDefaultColor = iArr2[i];
                }
                i++;
            }
        }
        if (SkinEngine.IS_MNC_PREVIEW) {
            workroundForMNC();
        }
    }

    void update(SkinnableColorStateList skinnableColorStateList) {
        this.mStateSpecs = skinnableColorStateList.mStateSpecs;
        this.mColors = skinnableColorStateList.mColors;
        this.mDefaultColor = skinnableColorStateList.mDefaultColor;
    }

    public static SkinnableColorStateList createFromXml(SkinEngine skinEngine, Resources resources, XmlPullParser xmlPullParser, boolean z) throws XmlPullParserException, IOException {
        try {
            int next;
            AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
            do {
                next = xmlPullParser.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return createFromXmlInner(skinEngine, resources, xmlPullParser, asAttributeSet, z);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (Throwable e) {
            if (SkinEngine.DEBUG) {
                Log.e(SkinEngine.TAG, "", e);
            }
            return new SkinnableColorStateList(EMPTY, new int[]{-65281});
        }
    }

    private static SkinnableColorStateList createFromXmlInner(SkinEngine skinEngine, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, boolean z) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (name.equals("selector")) {
            SkinnableColorStateList skinnableColorStateList = new SkinnableColorStateList((int[][]) null, null);
            skinnableColorStateList.inflate(skinEngine, resources, xmlPullParser, attributeSet, z);
            return skinnableColorStateList;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid drawable tag " + name);
    }

    public ColorStateList withAlpha(int i) {
        int[] iArr = new int[this.mColors.length];
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = (this.mColors[i2] & SkinEngine.TYPE_FILE) | (i << 24);
        }
        return new ColorStateList(this.mStateSpecs, iArr);
    }

    private void inflate(SkinEngine skinEngine, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, boolean z) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth() + 1;
        int i = 20;
        int i2 = 0;
        Object obj = new int[20];
        Object obj2 = new int[20][];
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                break;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                break;
            } else if (next == 2 && depth2 <= depth && xmlPullParser.getName().equals("item")) {
                int i3 = 0;
                int i4 = -65536;
                Object obj3 = null;
                depth2 = 0;
                int attributeCount = attributeSet.getAttributeCount();
                int[] iArr = new int[attributeCount];
                int i5 = 0;
                while (i5 < attributeCount) {
                    int i6;
                    if (!z) {
                        String attributeName = attributeSet.getAttributeName(i5);
                        if (!"color".equals(attributeName)) {
                            if (!"state_window_focused".equals(attributeName)) {
                                if (!"state_selected".equals(attributeName)) {
                                    if (!"state_focused".equals(attributeName)) {
                                        if (!"state_enabled".equals(attributeName)) {
                                            if (!"state_pressed".equals(attributeName)) {
                                                if (!"state_checked".equals(attributeName)) {
                                                    if (!"state_activated".equals(attributeName)) {
                                                        if (!"state_accelerated".equals(attributeName)) {
                                                            if (!"state_hovered".equals(attributeName)) {
                                                                if (!"state_drag_can_accept".equals(attributeName)) {
                                                                    if (!"state_drag_hovered".equals(attributeName)) {
                                                                        break;
                                                                    }
                                                                    i6 = depth2 + 1;
                                                                    iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16843625 : -16843625;
                                                                    next = i6;
                                                                } else {
                                                                    i6 = depth2 + 1;
                                                                    iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16843624 : -16843624;
                                                                    next = i6;
                                                                }
                                                            } else {
                                                                i6 = depth2 + 1;
                                                                iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16843623 : -16843623;
                                                                next = i6;
                                                            }
                                                        } else {
                                                            i6 = depth2 + 1;
                                                            iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16843547 : -16843547;
                                                            next = i6;
                                                        }
                                                    } else {
                                                        i6 = depth2 + 1;
                                                        iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16843518 : -16843518;
                                                        next = i6;
                                                    }
                                                } else {
                                                    i6 = depth2 + 1;
                                                    iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16842912 : -16842912;
                                                    next = i6;
                                                }
                                            } else {
                                                i6 = depth2 + 1;
                                                iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16842919 : -16842919;
                                                next = i6;
                                            }
                                        } else {
                                            i6 = depth2 + 1;
                                            iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16842910 : -16842910;
                                            next = i6;
                                        }
                                    } else {
                                        i6 = depth2 + 1;
                                        iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16842908 : -16842908;
                                        next = i6;
                                    }
                                } else {
                                    i6 = depth2 + 1;
                                    iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16842913 : -16842913;
                                    next = i6;
                                }
                            } else {
                                i6 = depth2 + 1;
                                iArr[depth2] = attributeSet.getAttributeBooleanValue(i5, false) ? 16842909 : -16842909;
                                next = i6;
                            }
                        } else {
                            i4 = Color.parseColor(attributeSet.getAttributeValue(i5));
                            obj3 = 1;
                            next = depth2;
                        }
                    } else {
                        next = attributeSet.getAttributeNameResource(i5);
                        if (next == 0) {
                            break;
                        }
                        int i7;
                        Object obj4;
                        if (next == 16843173) {
                            i3 = attributeSet.getAttributeResourceValue(i5, 0);
                            if (i3 == 0) {
                                i6 = attributeSet.getAttributeIntValue(i5, i4);
                                i7 = i3;
                                next = depth2;
                                obj4 = 1;
                            } else {
                                next = depth2;
                                i6 = i4;
                                obj4 = obj3;
                                i7 = i3;
                            }
                        } else {
                            i6 = depth2 + 1;
                            if (!attributeSet.getAttributeBooleanValue(i5, false)) {
                                next = -next;
                            }
                            iArr[depth2] = next;
                            next = i6;
                            obj4 = obj3;
                            i6 = i4;
                            i7 = i3;
                        }
                        i4 = i6;
                        i3 = i7;
                        obj3 = obj4;
                    }
                    i5++;
                    depth2 = next;
                }
                int[] trimStateSet = StateSet.trimStateSet(iArr, depth2);
                if (i3 != 0) {
                    i4 = skinEngine.getColor(i3);
                } else if (obj3 == null) {
                    throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'android:color' attribute.");
                }
                if (i2 == 0 || trimStateSet.length == 0) {
                    this.mDefaultColor = i4;
                }
                if (i2 + 1 >= i) {
                    next = idealIntArraySize(i2 + 1);
                    Object obj5 = new int[next];
                    System.arraycopy(obj, 0, obj5, 0, i2);
                    obj = new int[next][];
                    System.arraycopy(obj2, 0, obj, 0, i2);
                    obj2 = obj;
                    obj = obj5;
                    i = next;
                }
                obj[i2] = i4;
                obj2[i2] = trimStateSet;
                i2++;
            }
        }
        this.mColors = new int[i2];
        this.mStateSpecs = new int[i2][];
        System.arraycopy(obj, 0, this.mColors, 0, i2);
        System.arraycopy(obj2, 0, this.mStateSpecs, 0, i2);
    }

    private int idealIntArraySize(int i) {
        return idealByteArraySize(i * 4) / 4;
    }

    public static int idealByteArraySize(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    public boolean isStateful() {
        return true;
    }

    public int getColorForState(int[] iArr, int i) {
        int[][] iArr2 = (int[][]) this.mStateSpecs.clone();
        int length = iArr2.length;
        int length2 = this.mColors.length;
        int i2 = 0;
        while (i2 < length) {
            if (StateSet.stateSetMatches(iArr2[i2], iArr) && length2 > i2) {
                return this.mColors[i2];
            }
            if (length2 <= i2) {
                return i;
            }
            i2++;
        }
        return i;
    }

    public int getDefaultColor() {
        return this.mDefaultColor;
    }

    public String toString() {
        return "ColorStateList{mStateSpecs=" + Arrays.deepToString(this.mStateSpecs) + "mColors=" + Arrays.toString(this.mColors) + "mDefaultColor=" + this.mDefaultColor + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(r1);
        for (int[] writeIntArray : this.mStateSpecs) {
            parcel.writeIntArray(writeIntArray);
        }
        parcel.writeIntArray(this.mColors);
    }

    private void workroundForMNC() {
        try {
            Object newInstance = C_FACTORY.newInstance(new Object[]{this});
            F_FACTORY.set(this, newInstance);
            this.mmFactory = newInstance;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
