package oicq.wlogin_sdk.sharemem;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class WloginSimpleInfo implements Parcelable, Serializable {
    public static final Creator<WloginSimpleInfo> CREATOR = new Creator<WloginSimpleInfo>() {
        public WloginSimpleInfo createFromParcel(Parcel parcel) {
            return new WloginSimpleInfo(parcel);
        }

        public WloginSimpleInfo[] newArray(int i) {
            return new WloginSimpleInfo[i];
        }
    };
    private static final long serialVersionUID = 1;
    public byte[] _age;
    public byte[] _face;
    public byte[] _gender;
    public byte[] _img_format;
    public byte[] _img_type;
    public byte[] _img_url;
    public byte[] _nick;
    public long _uin;

    public WloginSimpleInfo(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        this._uin = j;
        if (bArr != null) {
            this._face = (byte[]) bArr.clone();
        } else {
            this._face = new byte[0];
        }
        if (bArr2 != null) {
            this._age = (byte[]) bArr2.clone();
        } else {
            this._age = new byte[0];
        }
        if (bArr3 != null) {
            this._gender = (byte[]) bArr3.clone();
        } else {
            this._gender = new byte[0];
        }
        if (bArr4 != null) {
            this._nick = (byte[]) bArr4.clone();
        } else {
            this._nick = new byte[0];
        }
        if (bArr5 != null) {
            this._img_type = (byte[]) bArr5.clone();
        } else {
            this._img_type = new byte[0];
        }
        if (bArr6 != null) {
            this._img_format = (byte[]) bArr6.clone();
        } else {
            this._img_format = new byte[0];
        }
        if (bArr7 != null) {
            this._img_url = (byte[]) bArr7.clone();
        } else {
            this._img_url = new byte[0];
        }
    }

    public WloginSimpleInfo(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][] bArr5) {
        this._uin = j;
        if (bArr != null) {
            this._face = (byte[]) bArr.clone();
        } else {
            this._face = new byte[0];
        }
        if (bArr2 != null) {
            this._age = (byte[]) bArr2.clone();
        } else {
            this._age = new byte[0];
        }
        if (bArr3 != null) {
            this._gender = (byte[]) bArr3.clone();
        } else {
            this._gender = new byte[0];
        }
        if (bArr4 != null) {
            this._nick = (byte[]) bArr4.clone();
        } else {
            this._nick = new byte[0];
        }
        if (bArr5 == null || bArr5.length != 3) {
            this._img_type = new byte[0];
            this._img_format = new byte[0];
            this._img_url = new byte[0];
            return;
        }
        this._img_type = (byte[]) bArr5[0].clone();
        this._img_format = (byte[]) bArr5[1].clone();
        this._img_url = (byte[]) bArr5[2].clone();
    }

    public WloginSimpleInfo() {
        this._uin = 0;
        this._face = new byte[0];
        this._age = new byte[0];
        this._gender = new byte[0];
        this._nick = new byte[0];
        this._img_type = new byte[0];
        this._img_format = new byte[0];
        this._img_url = new byte[0];
    }

    public void set_info(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][] bArr5) {
        this._uin = j;
        if (bArr != null) {
            this._face = (byte[]) bArr.clone();
        } else {
            this._face = new byte[0];
        }
        if (bArr2 != null) {
            this._age = (byte[]) bArr2.clone();
        } else {
            this._age = new byte[0];
        }
        if (bArr3 != null) {
            this._gender = (byte[]) bArr3.clone();
        } else {
            this._gender = new byte[0];
        }
        if (bArr4 != null) {
            this._nick = (byte[]) bArr4.clone();
        } else {
            this._nick = new byte[0];
        }
        if (bArr5 != null && bArr5.length == 3 && bArr5[0].length > 0 && bArr5[1].length > 0 && bArr5[2].length > 0) {
            this._img_type = (byte[]) bArr5[0].clone();
            this._img_format = (byte[]) bArr5[1].clone();
            this._img_url = (byte[]) bArr5[2].clone();
        }
    }

    public void get_clone(WloginSimpleInfo wloginSimpleInfo) {
        this._uin = wloginSimpleInfo._uin;
        if (wloginSimpleInfo._face != null) {
            this._face = (byte[]) wloginSimpleInfo._face.clone();
        } else {
            this._face = new byte[0];
        }
        if (wloginSimpleInfo._age != null) {
            this._age = (byte[]) wloginSimpleInfo._age.clone();
        } else {
            this._age = new byte[0];
        }
        if (wloginSimpleInfo._gender != null) {
            this._gender = (byte[]) wloginSimpleInfo._gender.clone();
        } else {
            this._gender = new byte[0];
        }
        if (wloginSimpleInfo._nick != null) {
            this._nick = (byte[]) wloginSimpleInfo._nick.clone();
        } else {
            this._nick = new byte[0];
        }
        if (wloginSimpleInfo._img_type != null) {
            this._img_type = (byte[]) wloginSimpleInfo._img_type.clone();
        } else {
            this._img_type = new byte[0];
        }
        if (wloginSimpleInfo._img_format != null) {
            this._img_format = (byte[]) wloginSimpleInfo._img_format.clone();
        } else {
            this._img_format = new byte[0];
        }
        if (wloginSimpleInfo._img_url != null) {
            this._img_url = (byte[]) wloginSimpleInfo._img_url.clone();
        } else {
            this._img_url = new byte[0];
        }
    }

    public WloginSimpleInfo get_clone() {
        WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
        wloginSimpleInfo._uin = this._uin;
        if (this._face != null) {
            wloginSimpleInfo._face = (byte[]) this._face.clone();
        }
        if (this._age != null) {
            wloginSimpleInfo._age = (byte[]) this._age.clone();
        }
        if (this._gender != null) {
            wloginSimpleInfo._gender = (byte[]) this._gender.clone();
        }
        if (this._nick != null) {
            wloginSimpleInfo._nick = (byte[]) this._nick.clone();
        }
        if (this._img_type != null) {
            wloginSimpleInfo._img_type = (byte[]) this._img_type.clone();
        }
        if (this._img_format != null) {
            wloginSimpleInfo._img_format = (byte[]) this._img_format.clone();
        }
        if (this._img_url != null) {
            wloginSimpleInfo._img_url = (byte[]) this._img_url.clone();
        }
        return wloginSimpleInfo;
    }

    private WloginSimpleInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this._uin);
        parcel.writeByteArray(this._face);
        parcel.writeByteArray(this._age);
        parcel.writeByteArray(this._gender);
        parcel.writeByteArray(this._nick);
        parcel.writeByteArray(this._img_type);
        parcel.writeByteArray(this._img_format);
        parcel.writeByteArray(this._img_url);
    }

    public void readFromParcel(Parcel parcel) {
        this._uin = parcel.readLong();
        this._face = parcel.createByteArray();
        this._age = parcel.createByteArray();
        this._gender = parcel.createByteArray();
        this._nick = parcel.createByteArray();
        this._img_type = parcel.createByteArray();
        this._img_format = parcel.createByteArray();
        this._img_url = parcel.createByteArray();
    }
}
