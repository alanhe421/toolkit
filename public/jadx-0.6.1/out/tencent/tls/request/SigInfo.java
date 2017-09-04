package tencent.tls.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import tencent.tls.platform.SigType;
import tencent.tls.report.QLog;

public class SigInfo implements Parcelable, Serializable {
    protected static final Creator<SigInfo> CREATOR = new Creator<SigInfo>() {
        public SigInfo createFromParcel(Parcel parcel) {
            return new SigInfo(parcel);
        }

        public SigInfo[] newArray(int i) {
            return new SigInfo[i];
        }
    };
    private static final long serialVersionUID = 1;
    public long _A1_create_time;
    public long _A2_create_time;
    public long _A2_expire_time;
    public byte[] _D2;
    public byte[] _D2Key;
    public long _D2_create_time;
    public long _D2_expire_time;
    public byte[] _TGT;
    public byte[] _TGTKey;
    public byte[] _TLS;
    public long _TLS_create_time;
    public long _TLS_expire_time;
    public byte[] _access_token;
    public long _app_pri;
    public byte[] _en_A1;
    public int _login_bitmap;
    public byte[] _noPicSig;
    public int accountType;
    public String appidAt3rd;
    public byte[] openid;

    public SigInfo(long j, long j2, ArrayList<Ticket> arrayList, int i) {
        this._TGT = new byte[0];
        this._TGTKey = new byte[0];
        this._D2 = new byte[0];
        this._D2Key = new byte[0];
        this._TLS = new byte[0];
        this._en_A1 = new byte[0];
        this._noPicSig = new byte[0];
        this.openid = new byte[0];
        this._access_token = new byte[0];
        this.accountType = 0;
        this._app_pri = 0;
        this._login_bitmap = 0;
        this._A1_create_time = 0;
        this._A2_create_time = 0;
        this._D2_create_time = 0;
        this._TLS_create_time = 0;
        this._A2_expire_time = 0;
        this._D2_expire_time = 0;
        this._TLS_expire_time = 0;
        SetSigInfo(j, j2, arrayList, i);
    }

    public void SetSigInfo(long j, long j2, ArrayList<Ticket> arrayList, int i) {
        this._app_pri = j;
        this._login_bitmap |= i;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Ticket ticket = (Ticket) it.next();
            if (!(ticket.Sig == null || ticket.Sig.length == 0)) {
                switch (ticket.Type) {
                    case 64:
                        this._A2_create_time = ticket.CreateTime;
                        this._A2_expire_time = ticket.ExpireTime;
                        this._TGT = ticket.Sig;
                        this._TGTKey = ticket.SigKey;
                        break;
                    case 262144:
                        this._D2_create_time = ticket.CreateTime;
                        this._D2_expire_time = ticket.ExpireTime;
                        this._D2 = ticket.Sig;
                        this._D2Key = ticket.SigKey;
                        break;
                    case SigType.TLS /*268435456*/:
                        this._TLS_create_time = ticket.CreateTime;
                        this._TLS_expire_time = ticket.ExpireTime;
                        this._TLS = ticket.Sig;
                        break;
                    case 305419896:
                        this._access_token = ticket.Sig;
                        this.openid = ticket.SigKey;
                        this.accountType = ticket.accountType;
                        this.appidAt3rd = ticket.appidAt3rd;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public SigInfo Set(long j, long j2, ArrayList<Ticket> arrayList, int i) {
        SetSigInfo(j, j2, arrayList, i);
        return this;
    }

    public SigInfo Set(byte[][] bArr, long j) {
        try {
            this._en_A1 = (byte[]) bArr[0].clone();
            this._A1_create_time = j;
            this._noPicSig = (byte[]) bArr[1].clone();
        } catch (Throwable e) {
            QLog.e(e);
        }
        return this;
    }

    private SigInfo(Parcel parcel) {
        this._TGT = new byte[0];
        this._TGTKey = new byte[0];
        this._D2 = new byte[0];
        this._D2Key = new byte[0];
        this._TLS = new byte[0];
        this._en_A1 = new byte[0];
        this._noPicSig = new byte[0];
        this.openid = new byte[0];
        this._access_token = new byte[0];
        this.accountType = 0;
        this._app_pri = 0;
        this._login_bitmap = 0;
        this._A1_create_time = 0;
        this._A2_create_time = 0;
        this._D2_create_time = 0;
        this._TLS_create_time = 0;
        this._A2_expire_time = 0;
        this._D2_expire_time = 0;
        this._TLS_expire_time = 0;
        readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this._en_A1);
        parcel.writeByteArray(this._noPicSig);
        parcel.writeByteArray(this._TGT);
        parcel.writeByteArray(this._TGTKey);
        parcel.writeByteArray(this._D2);
        parcel.writeByteArray(this._D2Key);
        parcel.writeByteArray(this._TLS);
        parcel.writeByteArray(this.openid);
        parcel.writeByteArray(this._access_token);
        parcel.writeInt(this.accountType);
        parcel.writeString(this.appidAt3rd);
        parcel.writeLong(this._A1_create_time);
        parcel.writeLong(this._A2_create_time);
        parcel.writeLong(this._D2_create_time);
        parcel.writeLong(this._TLS_create_time);
        parcel.writeLong(this._A2_expire_time);
        parcel.writeLong(this._D2_expire_time);
        parcel.writeLong(this._TLS_expire_time);
        parcel.writeInt(this._login_bitmap);
        parcel.writeLong(this._app_pri);
    }

    public void readFromParcel(Parcel parcel) {
        this._en_A1 = parcel.createByteArray();
        this._noPicSig = parcel.createByteArray();
        this._TGT = parcel.createByteArray();
        this._TGTKey = parcel.createByteArray();
        this._D2 = parcel.createByteArray();
        this._D2Key = parcel.createByteArray();
        this._TLS = parcel.createByteArray();
        this.openid = parcel.createByteArray();
        this._access_token = parcel.createByteArray();
        this.accountType = parcel.readInt();
        this.appidAt3rd = parcel.readString();
        this._A1_create_time = parcel.readLong();
        this._A2_create_time = parcel.readLong();
        this._D2_create_time = parcel.readLong();
        this._TLS_create_time = parcel.readLong();
        this._A2_expire_time = parcel.readLong();
        this._D2_expire_time = parcel.readLong();
        this._TLS_expire_time = parcel.readLong();
        this._login_bitmap = parcel.readInt();
        this._app_pri = parcel.readLong();
    }
}
