package tencent.tls.platform;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import tencent.tls.request.SigInfo;
import tencent.tls.request.Ticket;
import tencent.tls.request.TinyInfo.UserType;

public class TLSUserInfo implements Parcelable, Serializable {
    protected static final Creator<TLSUserInfo> CREATOR = new Creator<TLSUserInfo>() {
        public TLSUserInfo createFromParcel(Parcel parcel) {
            return new TLSUserInfo(parcel);
        }

        public TLSUserInfo[] newArray(int i) {
            return new TLSUserInfo[i];
        }
    };
    private static final long serialVersionUID = 1;
    protected List<Ticket> _tickets;
    public int accountType;
    public int adminFlag;
    protected long createTime;
    public String identifier;
    public boolean isGuest;
    protected long tinyid;
    public UserType userType;

    public TLSUserInfo(int i, String str, long j, long j2, UserType userType) {
        this.isGuest = false;
        this.userType = UserType.USER_TYPE_NORMAL;
        this.accountType = 0;
        this.adminFlag = 0;
        this._tickets = new ArrayList();
        this.accountType = i;
        this.identifier = str;
        this.tinyid = j;
        this.createTime = j2;
        this.userType = userType;
        if (userType == UserType.USER_TYPE_GUEST) {
            this.isGuest = true;
        }
    }

    public TLSUserInfo() {
        this.isGuest = false;
        this.userType = UserType.USER_TYPE_NORMAL;
        this.accountType = 0;
        this.adminFlag = 0;
        this._tickets = new ArrayList();
    }

    protected void get_clone(SigInfo sigInfo) {
        this._tickets.clear();
        this._tickets.add(new Ticket(64, sigInfo._TGT, sigInfo._TGTKey, sigInfo._A2_create_time, sigInfo._A2_expire_time));
        this._tickets.add(new Ticket(262144, sigInfo._D2, sigInfo._D2Key, sigInfo._A2_create_time, sigInfo._A2_expire_time));
        this._tickets.add(new Ticket(SigType.TLS, sigInfo._TLS, null, sigInfo._TLS_create_time, sigInfo._TLS_expire_time));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeLong(this.tinyid);
        parcel.writeTypedList(this._tickets);
    }

    private void readFromParcel(Parcel parcel) {
        this.identifier = parcel.readString();
        this.tinyid = parcel.readLong();
        parcel.readTypedList(this._tickets, Ticket.CREATOR);
    }

    private TLSUserInfo(Parcel parcel) {
        this.isGuest = false;
        this.userType = UserType.USER_TYPE_NORMAL;
        this.accountType = 0;
        this.adminFlag = 0;
        this._tickets = new ArrayList();
        readFromParcel(parcel);
    }
}
