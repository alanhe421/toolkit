package com.tencent.qalsdk.base.remote;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.tencent.qalsdk.sdk.MsfCommand;
import java.util.HashMap;

public class FromServiceMsg implements Parcelable, Cloneable {
    public static final Creator<FromServiceMsg> CREATOR = new a();
    private static final String tag = "FromServiceMsg";
    private static final String version = "version";
    private int appId;
    private int appSeq;
    public HashMap<String, Object> attributes;
    private String errorMsg;
    public Bundle extraData;
    private int flag;
    private byte fromVersion;
    private MsfCommand msfCommand;
    private byte[] msgCookie;
    private int resultCode;
    private String serviceCmd;
    private int ssoSeq;
    private String uin;
    private byte[] wupBuffer;

    public byte[] getMsgCookie() {
        return this.msgCookie;
    }

    public void setMsgCookie(byte[] bArr) {
        this.msgCookie = bArr;
    }

    public FromServiceMsg() {
        this.errorMsg = "";
        this.ssoSeq = -1;
        this.appSeq = -1;
        this.wupBuffer = new byte[0];
        this.attributes = new HashMap();
        this.fromVersion = (byte) 1;
        this.msfCommand = MsfCommand.unknown;
        this.msgCookie = new byte[0];
        this.extraData = new Bundle();
        this.extraData.putByte("version", this.fromVersion);
    }

    public FromServiceMsg(String str, String str2) {
        this(-1, -1, str, str2);
        this.extraData.putByte("version", this.fromVersion);
    }

    public FromServiceMsg(int i, int i2, String str, String str2) {
        this.errorMsg = "";
        this.ssoSeq = -1;
        this.appSeq = -1;
        this.wupBuffer = new byte[0];
        this.attributes = new HashMap();
        this.fromVersion = (byte) 1;
        this.msfCommand = MsfCommand.unknown;
        this.msgCookie = new byte[0];
        this.extraData = new Bundle();
        this.resultCode = 1001;
        this.appId = i;
        this.appSeq = i2;
        this.uin = str;
        this.serviceCmd = str2;
        this.extraData.putByte("version", this.fromVersion);
    }

    public FromServiceMsg(int i, int i2, String str, String str2, byte[] bArr) {
        this.errorMsg = "";
        this.ssoSeq = -1;
        this.appSeq = -1;
        this.wupBuffer = new byte[0];
        this.attributes = new HashMap();
        this.fromVersion = (byte) 1;
        this.msfCommand = MsfCommand.unknown;
        this.msgCookie = new byte[0];
        this.extraData = new Bundle();
        this.resultCode = 1001;
        this.appId = i;
        this.appSeq = i2;
        this.uin = str;
        this.serviceCmd = str2;
        this.msgCookie = bArr;
        this.extraData.putByte("version", this.fromVersion);
    }

    public FromServiceMsg(Parcel parcel) {
        this.errorMsg = "";
        this.ssoSeq = -1;
        this.appSeq = -1;
        this.wupBuffer = new byte[0];
        this.attributes = new HashMap();
        this.fromVersion = (byte) 1;
        this.msfCommand = MsfCommand.unknown;
        this.msgCookie = new byte[0];
        this.extraData = new Bundle();
        readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeInt(this.appId);
            parcel.writeInt(this.appSeq);
            parcel.writeInt(this.resultCode);
            parcel.writeString(this.uin);
            parcel.writeString(this.serviceCmd);
            parcel.writeBundle(this.extraData);
            parcel.writeMap(this.attributes);
            if (this.fromVersion > (byte) 0) {
                parcel.writeSerializable(this.msfCommand);
                parcel.writeInt(this.ssoSeq);
                parcel.writeString(this.errorMsg);
                parcel.writeInt(this.wupBuffer.length);
                parcel.writeByteArray(this.wupBuffer);
                parcel.writeInt(this.msgCookie.length);
                parcel.writeByteArray(this.msgCookie);
                parcel.writeInt(this.flag);
            }
        } catch (Throwable e) {
            Log.d(tag, "writeToParcel RuntimeException", e);
            throw e;
        }
    }

    public void readFromParcel(Parcel parcel) {
        try {
            this.appId = parcel.readInt();
            this.appSeq = parcel.readInt();
            this.resultCode = parcel.readInt();
            this.uin = parcel.readString();
            this.serviceCmd = parcel.readString();
            this.extraData.clear();
            this.extraData = parcel.readBundle();
            this.attributes.clear();
            parcel.readMap(this.attributes, getClass().getClassLoader());
            if (this.extraData.getByte("version") > (byte) 0) {
                this.msfCommand = (MsfCommand) parcel.readSerializable();
                this.ssoSeq = parcel.readInt();
                this.errorMsg = parcel.readString();
                this.wupBuffer = new byte[parcel.readInt()];
                parcel.readByteArray(this.wupBuffer);
                this.msgCookie = new byte[parcel.readInt()];
                parcel.readByteArray(this.msgCookie);
                this.flag = parcel.readInt();
            }
        } catch (Throwable e) {
            Log.d(tag, "readFromParcel RuntimeException", e);
            throw e;
        }
    }

    public String toString() {
        return "FromServiceMsg msName:" + this.msfCommand + " ssoSeq:" + getRequestSsoSeq() + " failCode:" + this.resultCode + " errorMsg:" + this.errorMsg + " uin:" + this.uin + " serviceCmd:" + this.serviceCmd + " appId:" + this.appId + " appSeq:" + this.appSeq;
    }

    public byte[] getWupBuffer() {
        return this.wupBuffer;
    }

    public void putWupBuffer(byte[] bArr) {
        this.wupBuffer = bArr;
    }

    public boolean isSuccess() {
        return this.resultCode == 1000;
    }

    public void setBusinessFail(int i) {
        this.resultCode = i;
    }

    public void setBusinessFail(int i, String str) {
        this.resultCode = i;
        this.errorMsg = str;
    }

    public void setBusinessFail(int i, int i2, String str) {
        this.resultCode = i2;
        this.errorMsg = str;
    }

    public int getBusinessFailCode(int i) {
        return this.resultCode == -1 ? i : this.resultCode;
    }

    public int getBusinessFailCode() {
        return this.resultCode;
    }

    public int getFlag() {
        return this.flag;
    }

    public String getBusinessFailMsg() {
        return this.errorMsg;
    }

    public void setMsgSuccess() {
        this.resultCode = 1000;
    }

    public void setMsgFail() {
        this.resultCode = 1001;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public String getUin() {
        return this.uin;
    }

    public String getServiceCmd() {
        return this.serviceCmd;
    }

    public void setServiceCmd(String str) {
        this.serviceCmd = str;
    }

    public void setUin(String str) {
        this.uin = str;
    }

    public Object addAttribute(String str, Object obj) {
        return this.attributes.put(str, obj);
    }

    public Object getAttribute(String str) {
        return this.attributes.get(str);
    }

    public <T> T getAttribute(String str, T t) {
        return !this.attributes.containsKey(str) ? t : this.attributes.get(str);
    }

    public HashMap<String, Object> getAttributes() {
        return this.attributes;
    }

    public int getAppSeq() {
        return this.appSeq;
    }

    public void setAppSeq(int i) {
        this.appSeq = i;
    }

    public int getAppId() {
        return this.appId;
    }

    public void setAppId(int i) {
        this.appId = i;
    }

    public int getRequestSsoSeq() {
        return this.ssoSeq;
    }

    public void setRequestSsoSeq(int i) {
        this.ssoSeq = i;
    }

    public byte getFromVersion() {
        return this.fromVersion;
    }

    public void setFromVersion(byte b) {
        this.fromVersion = b;
    }

    public MsfCommand getMsfCommand() {
        return this.msfCommand;
    }

    public void setMsfCommand(MsfCommand msfCommand) {
        this.msfCommand = msfCommand;
    }
}
