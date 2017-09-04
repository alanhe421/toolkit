package com.sijla.b.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class e extends File implements Parcelable {
    public static final Creator<e> CREATOR = new Creator<e>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public e a(Parcel parcel) {
            return new e(parcel);
        }

        public e[] a(int i) {
            return new e[i];
        }
    };
    public final String b;

    protected static String b(String str) {
        Throwable th;
        BufferedReader bufferedReader;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new FileReader(str));
            try {
                String str2 = "";
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    stringBuilder.append(str2).append(readLine);
                    str2 = "\n";
                }
                str2 = stringBuilder.toString();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    protected e(String str) {
        super(str);
        this.b = b(str);
    }

    protected e(Parcel parcel) {
        super(parcel.readString());
        this.b = parcel.readString();
    }

    public long length() {
        return (long) this.b.length();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getAbsolutePath());
        parcel.writeString(this.b);
    }
}
