package com.tencent.theme;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import com.qq.reader.plugin.j;
import com.tencent.smtt.sdk.WebView;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ResourcesFactory {
    public static final byte[] sBaseChunk = new byte[]{(byte) 1, (byte) 2, (byte) 2, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 94, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 94, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0};

    static BaseConstantState createImageFromResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, String str, Options options, Rect rect, boolean z) throws IOException {
        return createImageFromResourceStream(resources, typedValue, inputStream, str, options, rect, z, 0);
    }

    static BaseConstantState createImageFromResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, String str, Options options, Rect rect, boolean z, int i) throws IOException {
        byte[] bArr = null;
        boolean z2 = false;
        if (inputStream == null) {
            return bArr;
        }
        Bitmap decodeResourceStream;
        if (options == null) {
            options = new Options();
        }
        options.inScreenDensity = resources.getDisplayMetrics().densityDpi;
        if (inputStream instanceof FileInputStream) {
            options.inScaled = z;
            decodeResourceStream = decodeResourceStream(resources, typedValue, inputStream, rect, options);
        } else {
            try {
                decodeResourceStream = BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
            } catch (NullPointerException e) {
                decodeResourceStream = bArr;
            }
        }
        if (options.inJustDecodeBounds) {
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = options.inDensity;
            int[] iArr = new int[]{i2, i3, i4};
            if (str.endsWith(".9.png")) {
                NinePatchState ninePatchState = new NinePatchState(bArr, bArr, new Rect(0, 0, 0, 0));
                ninePatchState.mImageSizeWhenOOM = iArr;
                if (resources != null) {
                    ninePatchState.mTargetDensity = resources.getDisplayMetrics().densityDpi;
                }
                return ninePatchState;
            }
            BitmapState bitmapState = new BitmapState((Bitmap) bArr);
            bitmapState.mImageSizeWhenOOM = iArr;
            if (resources != null) {
                bitmapState.mTargetDensity = resources.getDisplayMetrics().densityDpi;
            }
            return bitmapState;
        } else if (decodeResourceStream == null) {
            return bArr;
        } else {
            if (!str.endsWith(".9.png")) {
                Object bitmapState2 = new BitmapState(decodeResourceStream);
                if (resources == null) {
                    return bitmapState2;
                }
                bitmapState2.mTargetDensity = resources.getDisplayMetrics().densityDpi;
                return bitmapState2;
            } else if (z) {
                bArr = decodeResourceStream.getNinePatchChunk();
                if (bArr == null || !NinePatch.isNinePatchChunk(bArr)) {
                    byte[] makeChunk;
                    if (i == 0 || resources == null) {
                        makeChunk = makeChunk(decodeResourceStream);
                        rect = new Rect();
                        bArr = makeChunk;
                        z2 = true;
                    } else {
                        Rect rect2;
                        boolean z3;
                        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i);
                        if (decodeResource == null || decodeResource.isRecycled()) {
                            bArr = makeChunk(decodeResourceStream);
                            rect2 = new Rect();
                            makeChunk = bArr;
                            z3 = true;
                        } else {
                            bArr = decodeResource.getNinePatchChunk();
                            rect2 = j.b(bArr);
                            bArr = j.a(bArr);
                            decodeResource.recycle();
                            makeChunk = bArr;
                            z3 = false;
                        }
                        rect = rect2;
                        byte[] bArr2 = makeChunk;
                        z2 = z3;
                        bArr = bArr2;
                    }
                }
                r3 = new NinePatchState(new NinePatch(decodeResourceStream, bArr, str), decodeResourceStream, rect);
                if (resources != null) {
                    r3.mTargetDensity = resources.getDisplayMetrics().densityDpi;
                }
                r3.hasProblem = z2;
                return r3;
            } else {
                try {
                    bArr = grabNinePatchChunk(decodeResourceStream, rect);
                } catch (Throwable e2) {
                    if (SkinEngine.DEBUG) {
                        Log.e(SkinEngine.TAG, "", e2);
                    }
                }
                if (bArr == null || !NinePatch.isNinePatchChunk(bArr)) {
                    bArr = makeChunk(decodeResourceStream);
                    rect = new Rect();
                    z2 = true;
                } else {
                    Bitmap createBitmap = Bitmap.createBitmap(decodeResourceStream, 1, 1, decodeResourceStream.getWidth() - 2, decodeResourceStream.getHeight() - 2);
                    decodeResourceStream.recycle();
                    decodeResourceStream = createBitmap;
                }
                r3 = new NinePatchState(new NinePatch(decodeResourceStream, bArr, str), decodeResourceStream, rect);
                r3.hasProblem = z2;
                if (resources != null) {
                    r3.mTargetDensity = resources.getDisplayMetrics().densityDpi;
                }
                return r3;
            }
        }
    }

    static byte[] grabNinePatchChunk(Bitmap bitmap, Rect rect) throws IllegalArgumentException, IOException {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 2 || height <= 2) {
            throw new IllegalArgumentException("not a nine-path bitmap");
        }
        int i;
        int i2;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(83);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (i = 0; i < 32; i++) {
            dataOutputStream.write(0);
        }
        int[] iArr = new int[(width - 2)];
        bitmap.getPixels(iArr, 0, width, 1, 0, width - 2, 1);
        Object obj = iArr[0] == WebView.NIGHT_MODE_COLOR ? 1 : null;
        Object obj2 = iArr[iArr.length + -1] == -16777216 ? 1 : null;
        int i3 = 0;
        int length = iArr.length;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            width = iArr[i4];
            if (width == -65536) {
                width = 0;
            }
            if (width == -16777216 || width == 0) {
                if (i3 != width) {
                    i3 = i5 + 1;
                    dataOutputStream.writeInt(Integer.reverseBytes(i4));
                } else {
                    width = i3;
                    i3 = i5;
                }
                i4++;
                i5 = i3;
                i3 = width;
            } else {
                throw new IllegalArgumentException("Ticks in transparent frame must be black or red. Fount at pixel #" + (i4 + 1) + " along top edge");
            }
        }
        if (obj2 != null) {
            i5++;
            dataOutputStream.writeInt(Integer.reverseBytes(iArr.length));
        }
        int i6 = i5;
        int i7 = i6 + 1;
        if (obj != null) {
            i = i7 - 1;
        } else {
            i = i7;
        }
        if (obj2 != null) {
            i2 = i - 1;
        } else {
            i2 = i;
        }
        iArr = new int[(height - 2)];
        bitmap.getPixels(iArr, 0, 1, 0, 1, 1, height - 2);
        obj = iArr[0] == WebView.NIGHT_MODE_COLOR ? 1 : null;
        obj2 = iArr[iArr.length + -1] == -16777216 ? 1 : null;
        length = iArr.length;
        i4 = 0;
        width = 0;
        i3 = 0;
        while (i4 < length) {
            i5 = iArr[i4];
            if (i5 == -65536) {
                i5 = 0;
            }
            if (i5 == -16777216 || i5 == 0) {
                if (width != iArr[i4]) {
                    i3++;
                    dataOutputStream.writeInt(Integer.reverseBytes(i4));
                    width = iArr[i4];
                }
                i4++;
            } else {
                throw new IllegalArgumentException("Ticks in transparent frame must be black or red. Fount at pixel #" + (i4 + 1) + " along left edge");
            }
        }
        if (obj2 != null) {
            i3++;
            dataOutputStream.writeInt(Integer.reverseBytes(iArr.length));
        }
        i7 = i3 + 1;
        if (obj != null) {
            i = i7 - 1;
        } else {
            i = i7;
        }
        if (obj2 != null) {
            i--;
        }
        for (i7 = 0; i7 < i2 * i; i7++) {
            dataOutputStream.writeInt(SigType.WLOGIN_PF);
        }
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        toByteArray[0] = (byte) 1;
        toByteArray[1] = (byte) i6;
        toByteArray[2] = (byte) i3;
        toByteArray[3] = (byte) (i * i2);
        int i8 = -1;
        i2 = -1;
        iArr = new int[(bitmap.getWidth() - 2)];
        bitmap.getPixels(iArr, 0, iArr.length, 1, bitmap.getHeight() - 1, iArr.length, 1);
        int i9 = 0;
        i = 0;
        while (i < iArr.length) {
            width = iArr[i];
            if (width == -65536) {
                width = 0;
            }
            if (width == -16777216 || width == 0) {
                if (-16777216 != width || width == r2) {
                    if (width == 0 && width != r2) {
                        if (i2 == -1) {
                            i2 = iArr.length - i;
                        } else {
                            throw new IllegalArgumentException("Can't have more than one marked region along edge.Found at pixel #" + (i + 1) + " along bottom edge.");
                        }
                    }
                } else if (i8 == -1) {
                    i8 = i;
                } else {
                    throw new IllegalArgumentException("Can't have more than one marked region along edge.Found at pixel #" + (i + 1) + " along bottom edge.");
                }
                i9 = width;
                i3 = i8;
                width = i2;
            } else {
                width = i2;
                i3 = i8;
            }
            i++;
            i2 = width;
            i8 = i3;
        }
        iArr = new int[(bitmap.getHeight() - 2)];
        bitmap.getPixels(iArr, 0, 1, bitmap.getWidth() - 1, 0, 1, iArr.length);
        i9 = 0;
        width = -1;
        i3 = -1;
        for (i = 0; i < iArr.length; i++) {
            i5 = iArr[i];
            if (i5 == -65536) {
                i5 = 0;
            }
            if (i5 == -16777216 || i5 == 0) {
                if (-16777216 != i5 || i5 == r2) {
                    if (i5 == 0 && i5 != r2) {
                        if (width == -1) {
                            width = iArr.length - i;
                        } else {
                            throw new IllegalArgumentException("Can't have more than one marked region along edge.Found at pixel #" + (i + 1) + " along bottom edge.");
                        }
                    }
                } else if (i3 == -1) {
                    i3 = i;
                } else {
                    throw new IllegalArgumentException("Can't have more than one marked region along edge.Found at pixel #" + (i + 1) + " along right edge.");
                }
                i9 = i5;
            }
        }
        ByteBuffer wrap = ByteBuffer.wrap(toByteArray);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(12);
        wrap.putInt(i8);
        wrap.putInt(i2);
        wrap.putInt(i3);
        wrap.putInt(width);
        rect.set(i8, i3, i2, width);
        return toByteArray;
    }

    static byte[] makeChunk(Bitmap bitmap) {
        Object obj = new byte[sBaseChunk.length];
        System.arraycopy(sBaseChunk, 0, obj, 0, sBaseChunk.length);
        ByteBuffer wrap = ByteBuffer.wrap(obj);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(36);
        wrap.putInt(bitmap.getWidth());
        wrap.position(44);
        wrap.putInt(bitmap.getHeight());
        return obj;
    }

    static Bitmap decodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, Options options) {
        if (options == null) {
            options = new Options();
        }
        if (options.inDensity == 0 && typedValue != null) {
            int i = typedValue.density;
            if (i == 0) {
                options.inDensity = 160;
            } else if (i != 65535) {
                options.inDensity = i;
            }
        }
        if (options.inTargetDensity == 0 && resources != null) {
            options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        return BitmapFactory.decodeStream(inputStream, rect, options);
    }

    static BitmapState createImageFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, AttributeSet attributeSet2, boolean z) throws XmlPullParserException, IOException {
        int i = 0;
        String name = xmlPullParser.getName();
        if (name.equals("bitmap")) {
            int i2;
            BitmapState inflateBitmapState = SkinnableBitmapDrawable.inflateBitmapState(resources, xmlPullParser, attributeSet, attributeSet2, z);
            if (attributeSet != null) {
                for (i2 = 0; i2 < attributeSet.getAttributeCount(); i2++) {
                    if ("isNeedScale".equals(attributeSet.getAttributeName(i2))) {
                        i2 = 1;
                        break;
                    }
                }
            }
            i2 = 0;
            if (i2 == 0 && attributeSet2 != null) {
                while (i < attributeSet2.getAttributeCount()) {
                    if ("isNeedScale".equals(attributeSet2.getAttributeName(i))) {
                        i2 = 1;
                        break;
                    }
                    i++;
                }
            }
            if (i2 != 0) {
                inflateBitmapState.bitmapType = 1;
            }
            return inflateBitmapState;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid drawable tag " + name);
    }

    static BaseConstantState createImageFromXml(Resources resources, XmlPullParser xmlPullParser, XmlPullParser xmlPullParser2, boolean z) throws XmlPullParserException, IOException {
        BaseConstantState createImageFromXmlInner;
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (xmlPullParser2 != null) {
            int next2;
            AttributeSet asAttributeSet2 = Xml.asAttributeSet(xmlPullParser2);
            do {
                next2 = xmlPullParser2.next();
                if (next2 == 2) {
                    break;
                }
            } while (next2 != 1);
            if (next2 != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            createImageFromXmlInner = createImageFromXmlInner(resources, xmlPullParser, asAttributeSet, asAttributeSet2, z);
        } else if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        } else {
            createImageFromXmlInner = createImageFromXmlInner(resources, xmlPullParser, asAttributeSet, null, z);
        }
        if (createImageFromXmlInner != null) {
            return createImageFromXmlInner;
        }
        throw new RuntimeException("Unknown initial tag: " + xmlPullParser.getName());
    }

    static SkinnableColorStateList createColorStateListFromXmlFile(SkinEngine skinEngine, Resources resources, File file, boolean z) throws XmlPullParserException, IOException {
        long uptimeMillis = SystemClock.uptimeMillis();
        XmlPullParser androidXmlResourceParser = z ? new AndroidXmlResourceParser() : Xml.newPullParser();
        InputStream fileInputStream = new FileInputStream(file);
        androidXmlResourceParser.setInput(fileInputStream, "UTF-8");
        SkinnableColorStateList createFromXml = SkinnableColorStateList.createFromXml(skinEngine, resources, androidXmlResourceParser, z);
        fileInputStream.close();
        if (SkinEngine.DEBUG) {
            Log.d(SkinEngine.TAG, "load colorList:" + file.toString() + " , cost:" + (SystemClock.uptimeMillis() - uptimeMillis));
        }
        return createFromXml;
    }
}
