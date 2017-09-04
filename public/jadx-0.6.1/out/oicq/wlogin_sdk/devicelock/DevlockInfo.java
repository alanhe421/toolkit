package oicq.wlogin_sdk.devicelock;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DevlockInfo implements Parcelable, Cloneable {
    public static final Creator<DevlockInfo> CREATOR = new c();
    public int AllowSet;
    public int AvailableMsgCount;
    public String BakCountryCode;
    public String BakMobile;
    public int BakMobileState;
    public String CountryCode;
    public int DevSetup;
    public String MbGuideInfo;
    public int MbGuideInfoType;
    public String MbGuideMsg;
    public int MbGuideType;
    public int MbItemSmsCodeStatus;
    public String Mobile;
    public String ProtectIntro;
    public int TimeLimit;
    public byte[] TransferInfo;
    public String UnionVerifyUrl;
    public String VerifyReason;
    public String WarningInfo;
    public int WarningInfoType;
    public String WarningMsg;
    public String WarningTitle;

    public Object clone() {
        return super.clone();
    }

    private DevlockInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.DevSetup);
        parcel.writeInt(this.AllowSet);
        parcel.writeString(this.ProtectIntro);
        parcel.writeString(this.WarningTitle);
        parcel.writeString(this.WarningMsg);
        parcel.writeString(this.WarningInfo);
        parcel.writeInt(this.WarningInfoType);
        parcel.writeString(this.CountryCode);
        parcel.writeString(this.Mobile);
        parcel.writeInt(this.MbItemSmsCodeStatus);
        parcel.writeInt(this.AvailableMsgCount);
        parcel.writeInt(this.TimeLimit);
        parcel.writeString(this.MbGuideMsg);
        parcel.writeString(this.MbGuideInfo);
        parcel.writeInt(this.MbGuideType);
        parcel.writeInt(this.MbGuideInfoType);
        parcel.writeString(this.UnionVerifyUrl);
        parcel.writeString(this.VerifyReason);
        parcel.writeInt(this.BakMobileState);
        parcel.writeString(this.BakCountryCode);
        parcel.writeString(this.BakMobile);
        if (this.TransferInfo == null || this.TransferInfo.length <= 0) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(this.TransferInfo.length);
        parcel.writeByteArray(this.TransferInfo);
    }

    public void readFromParcel(Parcel parcel) {
        this.DevSetup = parcel.readInt();
        this.AllowSet = parcel.readInt();
        this.ProtectIntro = parcel.readString();
        this.WarningTitle = parcel.readString();
        this.WarningMsg = parcel.readString();
        this.WarningInfo = parcel.readString();
        this.WarningInfoType = parcel.readInt();
        this.CountryCode = parcel.readString();
        this.Mobile = parcel.readString();
        this.MbItemSmsCodeStatus = parcel.readInt();
        this.AvailableMsgCount = parcel.readInt();
        this.TimeLimit = parcel.readInt();
        this.MbGuideMsg = parcel.readString();
        this.MbGuideInfo = parcel.readString();
        this.MbGuideType = parcel.readInt();
        this.MbGuideInfoType = parcel.readInt();
        this.UnionVerifyUrl = parcel.readString();
        this.VerifyReason = parcel.readString();
        this.BakMobileState = parcel.readInt();
        this.BakCountryCode = parcel.readString();
        this.BakMobile = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.TransferInfo = new byte[readInt];
            parcel.readByteArray(this.TransferInfo);
        }
    }
}
