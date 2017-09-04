package oicq.wlogin_sdk.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import oicq.wlogin_sdk.request.oicq_request.EncryptionMethod;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;

public class TransReqContext implements Parcelable {
    public static final Creator<TransReqContext> CREATOR = new 1();
    public byte[] _body;
    public int _subcmd;
    public int _type;
    public long _uin;
    public EncryptionMethod requestEm;
    public byte[] wtSessionTicket;
    public byte[] wtSessionTicketKey;

    public void set_body(byte[] bArr) {
        if (bArr == null) {
            this._body = new byte[0];
        } else {
            this._body = bArr;
        }
    }

    public byte[] get_body() {
        return this._body;
    }

    public boolean is_register_req() {
        return this._type == 1;
    }

    public void set_register_req() {
        this._type = 1;
    }

    public boolean is_name_func_req() {
        return this._type == 2;
    }

    public void set_name_func_req() {
        this._type = 2;
    }

    public boolean is_code2d_func_req() {
        return this._type == 3;
    }

    public void set_code2d_func_req() {
        this._type = 3;
    }

    public boolean is_oidb_func_req() {
        return this._type == 4;
    }

    public void set_oidb_func_req() {
        this._type = 4;
    }

    public boolean is_devlock_req() {
        return this._type == 5;
    }

    public void set_devlock_req() {
        this._type = 5;
    }

    public int get_subcmd() {
        return this._subcmd;
    }

    public void set_subcmd(int i) {
        this._subcmd = i;
    }

    public long get_uin() {
        return this._uin;
    }

    public void set_uin(long j) {
        this._uin = j;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this._body);
        parcel.writeInt(this._type);
        parcel.writeInt(this._subcmd);
        parcel.writeLong(this._uin);
    }

    public void readFromParcel(Parcel parcel) {
        this._body = parcel.createByteArray();
        this._type = parcel.readInt();
        this._subcmd = parcel.readInt();
        this._uin = parcel.readLong();
    }

    public TransReqContext() {
        this._type = 0;
        this._subcmd = 0;
        this._uin = 0;
        this.requestEm = EncryptionMethod.EM_ECDH;
        this.wtSessionTicket = new byte[0];
        this.wtSessionTicketKey = new byte[0];
    }

    private TransReqContext(Parcel parcel) {
        this._type = 0;
        this._subcmd = 0;
        this._uin = 0;
        this.requestEm = EncryptionMethod.EM_ECDH;
        this.wtSessionTicket = new byte[0];
        this.wtSessionTicketKey = new byte[0];
        readFromParcel(parcel);
    }

    public void setSTEncryptMethod() {
        this.requestEm = EncryptionMethod.EM_ST;
    }

    public void setWtST(WloginSigInfo wloginSigInfo) {
        if (true != wloginSigInfo.isWtSessionTicketExpired() && wloginSigInfo.wtSessionTicket != null && wloginSigInfo.wtSessionTicketKey != null) {
            this.wtSessionTicket = (byte[]) wloginSigInfo.wtSessionTicket.clone();
            this.wtSessionTicketKey = (byte[]) wloginSigInfo.wtSessionTicketKey.clone();
        }
    }
}
