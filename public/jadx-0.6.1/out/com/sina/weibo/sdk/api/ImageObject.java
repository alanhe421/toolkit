package com.sina.weibo.sdk.api;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.b.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageObject extends BaseMediaObject {
    public static final Creator<ImageObject> CREATOR = new Creator<ImageObject>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ImageObject a(Parcel parcel) {
            return new ImageObject(parcel);
        }

        public ImageObject[] a(int i) {
            return new ImageObject[i];
        }
    };
    public byte[] g;
    public String h;

    public ImageObject(Parcel parcel) {
        this.g = parcel.createByteArray();
        this.h = parcel.readString();
    }

    public final void a(Bitmap bitmap) {
        Exception e;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
                this.g = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    d.c("Weibo.ImageObject", "put thumb failed");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            byteArrayOutputStream = null;
            e.printStackTrace();
            d.c("Weibo.ImageObject", "put thumb failed");
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.g);
        parcel.writeString(this.h);
    }

    protected BaseMediaObject a(String str) {
        return this;
    }

    protected String a() {
        return "";
    }
}
