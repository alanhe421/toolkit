package com.tencent.av.config;

import com.tencent.av.utils.QLog;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ConfigProtocol {
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_ANDROID_ROM_INFO = (short) 8;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_AVENGINE_INFO = (short) 7;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_AVENGINE_VERSION = (short) 4;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_CLIENT_APPID = (short) 3;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_CLIENT_DEVNAME = (short) 5;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_CLIENT_OS = (short) 2;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_CLIENT_TYPE = (short) 1;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_HARDWARE_INFO = (short) 6;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_SHARP_CONFIG_VERSION = (short) 10;
    public static final short AV_ENGINE_C2S_CONFIG_TLV_TYPE_SHARP_REPORT_INFO = (short) 9;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_AUDIOENGINE_NEED_INFO = (short) 7;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_AUDIO_SWITCH = (short) 11;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_CAMERAANGLE_INFO = (short) 8;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_CONN_PRIORITY_INFO = (short) 9;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_CONN_TYPE = (short) 5;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_LOG_UPLOAD = (short) 2;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_LOG_WRITE = (short) 1;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_SHARP_CONFIG_PAYLOAD = (short) 14;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_SHARP_INFO = (short) 10;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_STUN_SERVER_ADDR = (short) 4;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_UDP_CHECK = (short) 3;
    public static final short AV_ENGINE_S2C_CONFIG_TLV_TYPE_VIDEOENGINE_NEED_INFO = (short) 6;
    public static final byte C2S_CONFIG_REQ_TAG = (byte) 1;
    public static final short S2C_CONFIG_INFO_MD5_SIZE = (short) 33;
    public static final short TLV_IP_ADDRESS_LENGTH = (short) 6;

    public abstract class TLVBase {
        private short length;
        private short type;

        public abstract byte[] Pack();

        public abstract boolean Unpack(ByteBuffer byteBuffer);

        public TLVBase(short s, short s2) {
            this.type = s;
            this.length = s2;
        }

        public void setType(short s) {
            this.type = s;
        }

        public void setLength(short s) {
            this.length = s;
        }

        public short getType() {
            return this.type;
        }

        public short getLength() {
            return this.length;
        }
    }

    public class AVSwitchTypeTLV extends TLVBase {
        private byte m_bIsAuidoEnable = (byte) 1;
        private byte m_bIsOpenMaxEnable = (byte) 0;

        public AVSwitchTypeTLV() {
            super((short) 11, (short) 1);
        }

        public byte getM_bIsAuidoEnable() {
            return this.m_bIsAuidoEnable;
        }

        public byte getM_bIsOpenMaxEnable() {
            return this.m_bIsOpenMaxEnable;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (byteBuffer == null || byteBuffer.length() <= 0) {
                return false;
            }
            byte ReadUInt8 = byteBuffer.ReadUInt8();
            if (ReadUInt8 < (byte) 0) {
                return false;
            }
            this.m_bIsAuidoEnable = (byte) (ReadUInt8 & 1);
            this.m_bIsOpenMaxEnable = (byte) ((ReadUInt8 >> 1) & 1);
            if (QLog.isColorLevel()) {
                QLog.d("simonchwang", 0, "m_bIsAuidoEnable:" + this.m_bIsAuidoEnable + "m_bIsOpenMaxEnable:" + this.m_bIsOpenMaxEnable);
            }
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class AudioEngineNeedInfoTLV extends TLVBase {
        private byte m_OutputVolumeGain = (byte) 0;
        private byte m_WorkModel = (byte) 0;
        private int m_length;
        private byte m_param1 = (byte) 0;
        private byte m_preProcessModel = (byte) 0;

        public AudioEngineNeedInfoTLV(short s) {
            super((short) 7, (short) 4);
            this.m_length = s;
        }

        public byte GetWorkModel() {
            return this.m_WorkModel;
        }

        public byte GetPreProcessModel() {
            return this.m_preProcessModel;
        }

        public byte GetOutputVolumeGain() {
            return this.m_OutputVolumeGain;
        }

        public byte GetParam1() {
            return this.m_param1;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (this.m_length != getLength() || byteBuffer.length() < this.m_length) {
                return false;
            }
            this.m_WorkModel = byteBuffer.ReadUInt8();
            this.m_preProcessModel = byteBuffer.ReadUInt8();
            this.m_OutputVolumeGain = byteBuffer.ReadUInt8();
            this.m_param1 = byteBuffer.ReadUInt8();
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class C2SReqConfigProtocol {
        private ArrayList<TLVBase> attrs = new ArrayList();
        private short lengthOfTLV = (short) 0;
        private short numOfTLV = (short) 0;
        private short tag = (short) 1;

        public short getTag() {
            return this.tag;
        }

        public void setTag(short s) {
            this.tag = s;
        }

        public short getNumOfTLV() {
            return this.numOfTLV;
        }

        public void setNumOfTLV(short s) {
            this.numOfTLV = s;
        }

        public short getLengthOfTLV() {
            return this.lengthOfTLV;
        }

        public void setLengthOfTLV(short s) {
            this.lengthOfTLV = s;
        }

        public void AddTLV(TLVBase tLVBase) {
            if (this.attrs != null) {
                this.attrs.add(tLVBase);
                this.lengthOfTLV = (short) (this.lengthOfTLV + 4);
                this.lengthOfTLV = (short) (this.lengthOfTLV + tLVBase.getLength());
                this.numOfTLV = (short) (this.numOfTLV + 1);
            }
        }

        public TLVBase GetTLVByIndex(int i) {
            if (i >= this.attrs.size()) {
                return null;
            }
            return (TLVBase) this.attrs.get(i);
        }

        public boolean UnPack(ByteBuffer byteBuffer) {
            this.tag = byteBuffer.ReadUInt16();
            this.numOfTLV = byteBuffer.ReadUInt16();
            this.lengthOfTLV = byteBuffer.ReadUInt16();
            if (this.lengthOfTLV != byteBuffer.length()) {
                return false;
            }
            return true;
        }

        public ByteBuffer Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteUInt16(this.tag);
            byteBuffer.WriteUInt16(this.numOfTLV);
            byteBuffer.WriteUInt16(this.lengthOfTLV);
            for (int i = 0; i < this.attrs.size(); i++) {
                byteBuffer.WriteUInt16(((TLVBase) this.attrs.get(i)).getType());
                byteBuffer.WriteUInt16(((TLVBase) this.attrs.get(i)).getLength());
                byteBuffer.WriteByteBuffer(((TLVBase) this.attrs.get(i)).Pack());
            }
            return byteBuffer;
        }
    }

    class CameraAngleInfoTLV extends TLVBase {
        private byte m_BackCameraAngleForLocalPreview = (byte) 0;
        private byte m_BackCameraAngleForRemote_0 = (byte) 0;
        private byte m_BackCameraAngleForRemote_180 = (byte) 0;
        private byte m_BackCameraAngleForRemote_270 = (byte) 0;
        private byte m_BackCameraAngleForRemote_90 = (byte) 0;
        private byte m_BackCameraFormat = (byte) 0;
        private byte m_FrontCameraAngleForLocalPreview = (byte) 0;
        private byte m_FrontCameraAngleForRemote_0 = (byte) 0;
        private byte m_FrontCameraAngleForRemote_180 = (byte) 0;
        private byte m_FrontCameraAngleForRemote_270 = (byte) 0;
        private byte m_FrontCameraAngleForRemote_90 = (byte) 0;
        private byte m_FrontCameraFormat = (byte) 0;
        private short m_length;
        private byte m_param3 = (byte) 0;
        private byte m_param4 = (byte) 0;
        private byte m_param5 = (byte) 0;
        private byte m_param6 = (byte) 0;

        public CameraAngleInfoTLV(short s) {
            super((short) 8, (short) 16);
            this.m_length = s;
        }

        public byte GetFrontCameraAngleForLocalPreview() {
            return this.m_FrontCameraAngleForLocalPreview;
        }

        public byte GetBackCameraAngleForLocalPreview() {
            return this.m_BackCameraAngleForLocalPreview;
        }

        public byte GetFrontCameraAngleForRemote_0() {
            return this.m_FrontCameraAngleForRemote_0;
        }

        public byte GetBackCameraAngleForRemote_0() {
            return this.m_BackCameraAngleForRemote_0;
        }

        public byte GetFrontCameraAngleForRemote_90() {
            return this.m_FrontCameraAngleForRemote_90;
        }

        public byte GetBackCameraAngleForRemote_90() {
            return this.m_BackCameraAngleForRemote_90;
        }

        public byte GetFrontCameraAngleForRemote_180() {
            return this.m_FrontCameraAngleForRemote_180;
        }

        public byte GetBackCameraAngleForRemote_180() {
            return this.m_BackCameraAngleForRemote_180;
        }

        public byte GetFrontCameraAngleForRemote_270() {
            return this.m_FrontCameraAngleForRemote_270;
        }

        public byte GetBackCameraAngleForRemote_270() {
            return this.m_BackCameraAngleForRemote_270;
        }

        public byte GetFrontCameraFormat() {
            return this.m_FrontCameraFormat;
        }

        public byte GetBackCameraFormat() {
            return this.m_BackCameraFormat;
        }

        public byte GetParam3() {
            return this.m_param3;
        }

        public byte GetParam4() {
            return this.m_param4;
        }

        public byte GetParam5() {
            return this.m_param5;
        }

        public byte GetParam6() {
            return this.m_param6;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (this.m_length != getLength() || byteBuffer.length() < this.m_length) {
                return false;
            }
            this.m_FrontCameraAngleForLocalPreview = byteBuffer.ReadUInt8();
            this.m_BackCameraAngleForLocalPreview = byteBuffer.ReadUInt8();
            this.m_FrontCameraAngleForRemote_0 = byteBuffer.ReadUInt8();
            this.m_BackCameraAngleForRemote_0 = byteBuffer.ReadUInt8();
            this.m_FrontCameraAngleForRemote_90 = byteBuffer.ReadUInt8();
            this.m_BackCameraAngleForRemote_90 = byteBuffer.ReadUInt8();
            this.m_FrontCameraAngleForRemote_180 = byteBuffer.ReadUInt8();
            this.m_BackCameraAngleForRemote_180 = byteBuffer.ReadUInt8();
            this.m_FrontCameraAngleForRemote_270 = byteBuffer.ReadUInt8();
            this.m_BackCameraAngleForRemote_270 = byteBuffer.ReadUInt8();
            this.m_FrontCameraFormat = byteBuffer.ReadUInt8();
            this.m_BackCameraFormat = byteBuffer.ReadUInt8();
            this.m_param3 = byteBuffer.ReadUInt8();
            this.m_param4 = byteBuffer.ReadUInt8();
            this.m_param5 = byteBuffer.ReadUInt8();
            this.m_param6 = byteBuffer.ReadUInt8();
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class ClientAVEngineInfoTLV extends TLVBase {
        private short m_DispHeight;
        private short m_DispWidth;
        private byte m_maxDecFPS;
        private byte m_maxEncFPS;

        public ClientAVEngineInfoTLV() {
            super((short) 7, (short) 6);
        }

        void setAVEngineInfo(byte b, byte b2, short s, short s2) {
            this.m_maxEncFPS = b;
            this.m_maxDecFPS = b2;
            this.m_DispWidth = s;
            this.m_DispHeight = s2;
            setLength((short) 6);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteUInt8(this.m_maxEncFPS);
            byteBuffer.WriteUInt8(this.m_maxDecFPS);
            byteBuffer.WriteUInt16(this.m_DispWidth);
            byteBuffer.WriteUInt16(this.m_DispHeight);
            return byteBuffer.Data();
        }
    }

    public class ClientAppIDTLV extends TLVBase {
        private String appID;

        public ClientAppIDTLV(short s) {
            super((short) 3, s);
        }

        public void setAppID(String str) {
            this.appID = str;
            short length = (short) str.length();
            try {
                length = (short) str.getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            setLength(length);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteString(this.appID);
            return byteBuffer.Data();
        }
    }

    public class ClientDevNameTLV extends TLVBase {
        private String devNameInfo;

        public ClientDevNameTLV() {
            super((short) 5, (short) 0);
        }

        public void setDevNameInfo(String str) {
            this.devNameInfo = str;
            short length = (short) str.length();
            try {
                length = (short) this.devNameInfo.getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            setLength(length);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteString(this.devNameInfo);
            return byteBuffer.Data();
        }
    }

    public class ClientDeviceTypeTLV extends TLVBase {
        private short clientType = (short) 0;

        public ClientDeviceTypeTLV() {
            super((short) 1, (short) 2);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteUInt16(this.clientType);
            return byteBuffer.Data();
        }

        public void setClientType(short s) {
            this.clientType = s;
        }
    }

    public class ClientHardWareInfoTLV extends TLVBase {
        private short angleForCamera;
        private short cpuChipArch;
        private int cpuFreq;
        private short numOfCore;

        public ClientHardWareInfoTLV() {
            super((short) 6, (short) 10);
        }

        public void setHardWareInfo(short s, short s2, int i, short s3) {
            this.cpuChipArch = s;
            this.numOfCore = s2;
            this.cpuFreq = i;
            this.angleForCamera = s3;
            setLength((short) 10);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteUInt16(this.cpuChipArch);
            byteBuffer.WriteUInt16(this.numOfCore);
            byteBuffer.WriteUInt32(this.cpuFreq);
            byteBuffer.WriteUInt16(this.angleForCamera);
            return byteBuffer.Data();
        }
    }

    public class ClientOSTypeTLV extends TLVBase {
        private short clientOSType;

        public ClientOSTypeTLV() {
            super((short) 2, (short) 2);
            setClientOSType((short) 0);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteUInt16(this.clientOSType);
            return byteBuffer.Data();
        }

        public void setClientOSType(short s) {
            this.clientOSType = s;
        }
    }

    public class ClientRomInfoTLV extends TLVBase {
        private String m_RomInfo;

        public ClientRomInfoTLV() {
            super((short) 8, (short) 0);
        }

        public void SetRomInfo(String str) {
            this.m_RomInfo = str;
            short length = (short) str.length();
            try {
                length = (short) this.m_RomInfo.getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            setLength(length);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteString(this.m_RomInfo);
            return byteBuffer.Data();
        }
    }

    public class ClientSharpInfoTLV extends TLVBase {
        private int m_opensl = 0;

        public ClientSharpInfoTLV() {
            super((short) 9, (short) 4);
        }

        void SetOpenslInfo(int i) {
            this.m_opensl = i;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return false;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteUInt32(this.m_opensl);
            return byteBuffer.Data();
        }
    }

    public class ConnForbidTypeTLV extends TLVBase {
        private short m_ConnType = (short) 0;

        public ConnForbidTypeTLV() {
            super((short) 5, (short) 2);
        }

        public short getConnForbidType() {
            return this.m_ConnType;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (byteBuffer == null || byteBuffer.length() < 2) {
                return false;
            }
            this.m_ConnType = byteBuffer.ReadUInt16();
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class ConnPriorityInfoTLV extends TLVBase {
        private byte m_LocalDirectConnPri = (byte) 0;
        private byte m_RelayConnPri = (byte) 0;
        private byte m_StunDirectConnPri = (byte) 0;
        private short m_length;
        private byte m_param1 = (byte) 0;

        public ConnPriorityInfoTLV(short s) {
            super((short) 9, (short) 4);
            this.m_length = s;
        }

        public byte GetLocalDirectConnPri() {
            return this.m_LocalDirectConnPri;
        }

        public byte GetRelayConnPri() {
            return this.m_RelayConnPri;
        }

        public byte GetStunDirectConnPri() {
            return this.m_StunDirectConnPri;
        }

        public byte GetParam1() {
            return this.m_param1;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (this.m_length != getLength() || byteBuffer.length() < this.m_length) {
                return false;
            }
            this.m_LocalDirectConnPri = byteBuffer.ReadUInt8();
            this.m_RelayConnPri = byteBuffer.ReadUInt8();
            this.m_StunDirectConnPri = byteBuffer.ReadUInt8();
            this.m_param1 = byteBuffer.ReadUInt8();
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class EngineVersionTLV extends TLVBase {
        private String engienVersion;

        public EngineVersionTLV(short s) {
            super((short) 4, s);
        }

        public void setEngienVersion(String str) {
            this.engienVersion = str;
            short length = (short) str.length();
            try {
                length = (short) this.engienVersion.getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            setLength(length);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteString(this.engienVersion);
            return byteBuffer.Data();
        }
    }

    public class LocalLogUploadTLV extends TLVBase {
        private String m_allStr;
        private String m_endTimeStr;
        private String m_startTimeStr;

        public LocalLogUploadTLV(short s) {
            super((short) 2, s);
        }

        public boolean getLogUploadTimeScope() {
            if (this.m_allStr.length() < "2012/03/05/08,2012/03/05/20".length()) {
                return false;
            }
            this.m_startTimeStr = this.m_allStr.substring(0, 13);
            this.m_endTimeStr = this.m_allStr.substring(14, 13);
            return true;
        }

        public String getM_startTimeStr() {
            return this.m_startTimeStr;
        }

        public String getM_endTimeStr() {
            return this.m_endTimeStr;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (byteBuffer == null || byteBuffer.length() < getLength()) {
                return false;
            }
            this.m_allStr = byteBuffer.ReadString(getLength());
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class RelaySvrUDPCheckTLV extends TLVBase {
        private ArrayList<stNetAddress> addrList = new ArrayList();

        public RelaySvrUDPCheckTLV(short s) {
            super((short) 3, s);
        }

        public int getRelaySvrAddrCount() {
            return this.addrList.size();
        }

        public stNetAddress getRelaySvrAddrByIndex(int i) {
            if (i >= this.addrList.size() || i < 0) {
                return null;
            }
            return (stNetAddress) this.addrList.get(i);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            short s = (short) 0;
            if (byteBuffer == null) {
                return false;
            }
            short length = getLength();
            if (length < (short) 2) {
                return false;
            }
            short ReadUInt16 = byteBuffer.ReadUInt16();
            if (ReadUInt16 != (length - 2) / 6) {
                return false;
            }
            while (s < ReadUInt16) {
                int ReadUInt32 = byteBuffer.ReadUInt32();
                short ReadUInt162 = byteBuffer.ReadUInt16();
                stNetAddress com_tencent_av_config_ConfigProtocol_stNetAddress = new stNetAddress();
                com_tencent_av_config_ConfigProtocol_stNetAddress.m_ip = ReadUInt32;
                com_tencent_av_config_ConfigProtocol_stNetAddress.m_port = ReadUInt162;
                this.addrList.add(com_tencent_av_config_ConfigProtocol_stNetAddress);
                s++;
            }
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class S2CConfigInfoProtocol {
        private ArrayList<TLVBase> attrs = new ArrayList();
        private short lengthOfTLV = (short) 0;
        private String md5;
        private short numOfTLV = (short) 0;
        private short tag = (short) 0;

        public String getMd5() {
            return this.md5;
        }

        public short getTag() {
            return this.tag;
        }

        public short getNumOfTLV() {
            return this.numOfTLV;
        }

        public short getLengthOfTLV() {
            return this.lengthOfTLV;
        }

        public void setTag(short s) {
            this.tag = s;
        }

        public void setNumOfTLV(short s) {
            this.numOfTLV = s;
        }

        public void setLengthOfTLV(short s) {
            this.lengthOfTLV = s;
        }

        public void AddTLV(TLVBase tLVBase) {
        }

        public TLVBase GetTLVByIndex(int i) {
            if (i >= this.attrs.size()) {
                return null;
            }
            return (TLVBase) this.attrs.get(i);
        }

        public boolean UnPack(ByteBuffer byteBuffer) {
            if (byteBuffer == null || byteBuffer.length() < 39) {
                return false;
            }
            this.md5 = byteBuffer.ReadString(33);
            this.tag = byteBuffer.ReadUInt16();
            this.numOfTLV = byteBuffer.ReadUInt16();
            this.lengthOfTLV = byteBuffer.ReadUInt16Length();
            this.attrs.clear();
            int i = 0;
            while (byteBuffer.length() > 0) {
                short ReadUInt16 = byteBuffer.ReadUInt16();
                short ReadUInt16Length = byteBuffer.ReadUInt16Length();
                TLVBase CreateS2CTLV = ConfigProtocol.this.CreateS2CTLV(ReadUInt16, ReadUInt16Length);
                if (CreateS2CTLV == null) {
                    if (!byteBuffer.Consume(ReadUInt16Length)) {
                        if (!QLog.isColorLevel()) {
                            return false;
                        }
                        QLog.d("simonchwang", 0, "[S2CConfigInfoProtocol::UnPack] Consume failed" + i);
                        return false;
                    }
                } else if (CreateS2CTLV.Unpack(byteBuffer)) {
                    this.attrs.add(CreateS2CTLV);
                } else if (!QLog.isColorLevel()) {
                    return false;
                } else {
                    QLog.d("simonchwang", 0, "[S2CConfigInfoProtocol::UnPack] Unpack failed" + i);
                    return false;
                }
                i++;
            }
            if (this.attrs != null) {
                this.numOfTLV = (short) this.attrs.size();
            }
            return true;
        }

        public boolean Pack(ByteBuffer byteBuffer) {
            return true;
        }
    }

    public class SharpConfigPayloadTLV extends TLVBase {
        private String m_sharpConfigPayload;

        public SharpConfigPayloadTLV(short s) {
            super((short) 14, s);
        }

        public String getSharpConfigPayload() {
            return this.m_sharpConfigPayload;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (byteBuffer == null || byteBuffer.length() < getLength()) {
                return false;
            }
            this.m_sharpConfigPayload = byteBuffer.ReadString(getLength());
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class SharpConfigVersionTLV extends TLVBase {
        private int m_sharpConfigVersion;

        public SharpConfigVersionTLV() {
            super((short) 10, (short) 4);
        }

        public void SetSharpConfigVersion(int i) {
            this.m_sharpConfigVersion = i;
            setLength((short) 4);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            return true;
        }

        public byte[] Pack() {
            ByteBuffer byteBuffer = new ByteBuffer();
            byteBuffer.WriteUInt32(this.m_sharpConfigVersion);
            return byteBuffer.Data();
        }
    }

    public class SharpInfoTLV extends TLVBase {
        private byte m_ARM_Version = (byte) 0;
        private short m_CPU_Frequence = (short) 0;
        private byte m_TRAE_Interface = (byte) 0;
        private byte m_TRAE_Mode = (byte) 0;
        private byte m_TRAE_Source = (byte) 0;
        private byte m_TRAE_Stream_Type = (byte) 0;
        private byte m_TRAE_Volume = (byte) 0;
        private short m_length;

        public SharpInfoTLV(short s) {
            super((short) 10, (short) 16);
            this.m_length = s;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (this.m_length != getLength() || byteBuffer.length() < this.m_length) {
                return false;
            }
            this.m_TRAE_Source = byteBuffer.ReadUInt8();
            this.m_TRAE_Interface = byteBuffer.ReadUInt8();
            this.m_TRAE_Stream_Type = byteBuffer.ReadUInt8();
            this.m_TRAE_Volume = byteBuffer.ReadUInt8();
            this.m_TRAE_Mode = byteBuffer.ReadUInt8();
            this.m_ARM_Version = byteBuffer.ReadUInt8();
            this.m_CPU_Frequence = byteBuffer.ReadUInt16();
            byteBuffer.ReadUInt32();
            byteBuffer.ReadUInt32();
            if (QLog.isColorLevel()) {
                QLog.d("sevenzhu", 0, "SharpInfo " + this.m_TRAE_Source + " " + this.m_TRAE_Interface + " " + this.m_TRAE_Stream_Type + " " + this.m_TRAE_Volume + " " + this.m_TRAE_Mode + " " + this.m_ARM_Version + " " + this.m_CPU_Frequence);
            }
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class StunServerAddrTLV extends TLVBase {
        private ArrayList<stNetAddress> addrList = new ArrayList();

        public StunServerAddrTLV(short s) {
            super((short) 4, s);
        }

        public int GetStunSvrAddrCount() {
            return this.addrList.size();
        }

        public stNetAddress GetStunSvrAddrByIndex(int i) {
            if (i >= this.addrList.size() || i < 0) {
                return null;
            }
            return (stNetAddress) this.addrList.get(i);
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            short s = (short) 0;
            if (byteBuffer == null || getLength() < (short) 2) {
                return false;
            }
            short ReadUInt16 = byteBuffer.ReadUInt16();
            if (ReadUInt16 != (getLength() - 2) / 6) {
                return false;
            }
            while (s < ReadUInt16) {
                int ReadUInt32 = byteBuffer.ReadUInt32();
                short ReadUInt162 = byteBuffer.ReadUInt16();
                stNetAddress com_tencent_av_config_ConfigProtocol_stNetAddress = new stNetAddress();
                com_tencent_av_config_ConfigProtocol_stNetAddress.m_ip = ReadUInt32;
                com_tencent_av_config_ConfigProtocol_stNetAddress.m_port = ReadUInt162;
                this.addrList.add(com_tencent_av_config_ConfigProtocol_stNetAddress);
                s++;
            }
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class VideoEngineNeedInfoTLV extends TLVBase {
        private short m_BackAngleForCamera = (short) 0;
        private short m_CPUArch = (short) 0;
        private short m_FrontAngleForCamera = (short) 0;
        private short m_dispHeight = (short) 0;
        private short m_dispWidth = (short) 0;
        private short m_length;
        private byte m_maxDecFPS = (byte) 0;
        private byte m_maxEncFPS = (byte) 0;
        private short m_param2;

        public VideoEngineNeedInfoTLV(short s) {
            super((short) 6, (short) 14);
            this.m_length = s;
        }

        public short GetCPUArch() {
            return this.m_CPUArch;
        }

        public byte GetMaxEncFPS() {
            return this.m_maxEncFPS;
        }

        public byte GetMaxDecFPS() {
            return this.m_maxDecFPS;
        }

        public short GetFrontAngleForCamera() {
            return this.m_FrontAngleForCamera;
        }

        public short GetDispWidth() {
            return this.m_dispWidth;
        }

        public short GetDispHeight() {
            return this.m_dispHeight;
        }

        public short GetParam2() {
            return this.m_param2;
        }

        public short GetBackAngleForCamera() {
            return this.m_BackAngleForCamera;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (this.m_length != getLength() || byteBuffer.length() < this.m_length) {
                return false;
            }
            this.m_CPUArch = byteBuffer.ReadUInt16();
            this.m_FrontAngleForCamera = byteBuffer.ReadUInt16();
            this.m_maxEncFPS = byteBuffer.ReadUInt8();
            this.m_maxDecFPS = byteBuffer.ReadUInt8();
            this.m_dispWidth = byteBuffer.ReadUInt16();
            this.m_dispHeight = byteBuffer.ReadUInt16();
            this.m_BackAngleForCamera = byteBuffer.ReadUInt16();
            this.m_param2 = byteBuffer.ReadUInt16();
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class WriteLocalLogTLV extends TLVBase {
        private short m_bIsWrite = (short) 0;

        public WriteLocalLogTLV() {
            super((short) 1, (short) 2);
        }

        public short getIsWriteLog() {
            return this.m_bIsWrite;
        }

        public boolean Unpack(ByteBuffer byteBuffer) {
            if (byteBuffer == null || byteBuffer.length() < 2) {
                return false;
            }
            this.m_bIsWrite = byteBuffer.ReadUInt16();
            return true;
        }

        public byte[] Pack() {
            return null;
        }
    }

    public class stNetAddress {
        int m_ip = 0;
        short m_port = (short) 0;
    }

    public TLVBase CreateS2CTLV(short s, short s2) {
        if (s2 > (short) 0) {
            switch (s) {
                case (short) 1:
                    return new WriteLocalLogTLV();
                case (short) 2:
                    return new LocalLogUploadTLV(s2);
                case (short) 3:
                    return new RelaySvrUDPCheckTLV(s2);
                case (short) 4:
                    return new StunServerAddrTLV(s2);
                case (short) 5:
                    return new ConnForbidTypeTLV();
                case (short) 6:
                    return new VideoEngineNeedInfoTLV(s2);
                case (short) 7:
                    return new AudioEngineNeedInfoTLV(s2);
                case (short) 8:
                    return new CameraAngleInfoTLV(s2);
                case (short) 9:
                    return new ConnPriorityInfoTLV(s2);
                case (short) 10:
                    return new SharpInfoTLV(s2);
                case (short) 11:
                    return new AVSwitchTypeTLV();
                case (short) 14:
                    return new SharpConfigPayloadTLV(s2);
                default:
                    if (!QLog.isColorLevel()) {
                        return null;
                    }
                    QLog.d("simonchwang", 0, "[TLVBase::CreateS2CTLV] Error ,Unknow type:" + s);
                    return null;
            }
        } else if (!QLog.isColorLevel()) {
            return null;
        } else {
            QLog.d("simonchwang", 0, "[TLVBase::CreateS2CTLV] length <= 0, type=" + s + " length=" + s2);
            return null;
        }
    }
}
