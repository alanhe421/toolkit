package com.qq.reader.module.bookstore.qnative.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class BookSercetModel implements Parcelable {
    public static final Creator<BookSercetModel> CREATOR = new Creator<BookSercetModel>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public BookSercetModel[] a(int i) {
            return new BookSercetModel[i];
        }

        public BookSercetModel a(Parcel parcel) {
            return new BookSercetModel(parcel);
        }
    };
    private String a;
    private int b;

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public String b() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
    }

    public BookSercetModel(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
    }
}
