package com.tencent.qalsdk.base.remote;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.IBaseActionListener.Stub;
import com.tencent.qalsdk.sdk.MsfCommand;
import java.util.HashMap;

public class ToServiceMsg implements Parcelable {
    public static final Creator<ToServiceMsg> CREATOR = new c();
    private static final String tag = "ToServiceMsg";
    private static final String version = "version";
    private IBaseActionListener actionListener;
    private int appId;
    private int appSeq;
    private HashMap<String, Object> attributes;
    public Bundle extraData;
    private boolean httpReq;
    private MsfCommand msfCommand;
    private boolean needResp;
    private long sendTimeout;
    private String serviceCmd;
    private String serviceName;
    private int ssoSeq;
    private long timeout;
    private byte toVersion;
    private String uin;
    private byte uinType;
    private byte[] wupBuffer;

    public ToServiceMsg(String str, String str2, String str3) {
        this.serviceCmd = "";
        this.sendTimeout = -1;
        this.timeout = -1;
        this.appSeq = -1;
        this.wupBuffer = new byte[0];
        this.needResp = true;
        this.httpReq = false;
        this.ssoSeq = -1;
        this.attributes = new HashMap();
        this.extraData = new Bundle();
        this.toVersion = (byte) 1;
        this.msfCommand = MsfCommand.unknown;
        this.uinType = a.z;
        this.serviceName = str;
        this.uin = str2;
        this.serviceCmd = str3;
        this.extraData.putByte("version", this.toVersion);
    }

    public ToServiceMsg(Parcel parcel) {
        this.serviceCmd = "";
        this.sendTimeout = -1;
        this.timeout = -1;
        this.appSeq = -1;
        this.wupBuffer = new byte[0];
        this.needResp = true;
        this.httpReq = false;
        this.ssoSeq = -1;
        this.attributes = new HashMap();
        this.extraData = new Bundle();
        this.toVersion = (byte) 1;
        this.msfCommand = MsfCommand.unknown;
        this.uinType = a.z;
        readFromParcel(parcel);
    }

    public String toString() {
        return "ToServiceMsg msName:" + this.msfCommand + " ssoSeq:" + getRequestSsoSeq() + " appId:" + this.appId + " appSeq:" + this.appSeq + " sName:" + this.serviceName + " uin:" + this.uin + " sCmd:" + this.serviceCmd + " t:" + this.timeout + " needResp:" + this.needResp + "httpReq:" + this.httpReq;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b = (byte) 1;
        try {
            parcel.writeInt(this.appId);
            parcel.writeInt(this.appSeq);
            parcel.writeString(this.serviceName);
            parcel.writeString(this.uin);
            parcel.writeByte(this.uinType);
            parcel.writeString(this.serviceCmd);
            parcel.writeLong(this.timeout);
            parcel.writeBundle(this.extraData);
            parcel.writeStrongInterface(this.actionListener);
            if (this.toVersion > (byte) 0) {
                parcel.writeSerializable(this.msfCommand);
                parcel.writeLong(this.sendTimeout);
                parcel.writeByte(this.needResp ? (byte) 1 : (byte) 0);
                if (!this.httpReq) {
                    b = (byte) 0;
                }
                parcel.writeByte(b);
                parcel.writeInt(this.wupBuffer.length);
                parcel.writeByteArray(this.wupBuffer);
                parcel.writeInt(this.ssoSeq);
                parcel.writeMap(this.attributes);
            }
        } catch (Throwable e) {
            Log.d(tag, "writeToParcel RuntimeException", e);
            throw e;
        }
    }

    private void readFromParcel(Parcel parcel) {
        try {
            this.appId = parcel.readInt();
            this.appSeq = parcel.readInt();
            this.serviceName = parcel.readString();
            this.uin = parcel.readString();
            this.uinType = parcel.readByte();
            this.serviceCmd = parcel.readString();
            this.timeout = parcel.readLong();
            this.extraData.clear();
            this.extraData.putAll(parcel.readBundle(Thread.currentThread().getContextClassLoader()));
            this.actionListener = Stub.asInterface(parcel.readStrongBinder());
            this.toVersion = this.extraData.getByte("version");
            if (this.toVersion > (byte) 0) {
                boolean z;
                this.msfCommand = (MsfCommand) parcel.readSerializable();
                this.sendTimeout = parcel.readLong();
                this.needResp = parcel.readByte() != (byte) 0;
                if (parcel.readByte() == (byte) 0) {
                    z = false;
                } else {
                    z = true;
                }
                this.httpReq = z;
                this.wupBuffer = new byte[parcel.readInt()];
                parcel.readByteArray(this.wupBuffer);
                this.ssoSeq = parcel.readInt();
                this.attributes.clear();
                parcel.readMap(this.attributes, ToServiceMsg.class.getClassLoader());
            }
        } catch (Throwable e) {
            Log.d(tag, "readFromParcel RuntimeException", e);
            throw e;
        }
    }

    public byte[] getWupBuffer() {
        return this.wupBuffer;
    }

    public void putWupBuffer(byte[] bArr) {
        this.wupBuffer = bArr;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public void setTimeout(long j) {
        this.timeout = j;
    }

    public void setActionListener(IBaseActionListener iBaseActionListener) {
        this.actionListener = iBaseActionListener;
    }

    public String getDestServiceId() {
        return this.serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getUin() {
        return this.uin;
    }

    public String getServiceCmd() {
        return this.serviceCmd;
    }

    public IBaseActionListener getActionListener() {
        return this.actionListener;
    }

    public boolean isNeedCallback() {
        return this.needResp;
    }

    public void setNeedCallback(boolean z) {
        this.needResp = z;
    }

    public boolean isHttpReq() {
        return this.httpReq;
    }

    public void setIsHttpReq() {
        this.httpReq = true;
    }

    public int getAppId() {
        return this.appId;
    }

    public void setAppId(int i) {
        this.appId = i;
    }

    public int getAppSeq() {
        return this.appSeq;
    }

    public void setAppSeq(int i) {
        this.appSeq = i;
    }

    public int getRequestSsoSeq() {
        return this.ssoSeq;
    }

    public void setRequestSsoSeq(int i) {
        this.ssoSeq = i;
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

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public void setUin(String str) {
        this.uin = str;
    }

    public void setServiceCmd(String str) {
        this.serviceCmd = str;
    }

    public MsfCommand getMsfCommand() {
        return this.msfCommand;
    }

    public void setMsfCommand(MsfCommand msfCommand) {
        this.msfCommand = msfCommand;
    }

    public void setAttributes(HashMap<String, Object> hashMap) {
        this.attributes = hashMap;
    }

    public void setEnableFastResend(boolean z) {
        addAttribute("fastresend", Boolean.valueOf(z));
    }

    public boolean isFastResendEnabled() {
        return ((Boolean) getAttribute("fastresend", Boolean.valueOf(false))).booleanValue();
    }

    public void setNeedRemindSlowNetwork(boolean z) {
        addAttribute(a.bb, Boolean.valueOf(z));
    }

    public boolean isNeedRemindSlowNetwork() {
        return ((Boolean) getAttribute(a.bb, Boolean.valueOf(false))).booleanValue();
    }

    public void setUinType(int i) {
        this.uinType = (byte) i;
    }

    public byte getUinType() {
        return this.uinType;
    }
}
