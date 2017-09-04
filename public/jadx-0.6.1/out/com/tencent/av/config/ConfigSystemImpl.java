package com.tencent.av.config;

import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.av.config.ConfigProtocol.AVSwitchTypeTLV;
import com.tencent.av.config.ConfigProtocol.C2SReqConfigProtocol;
import com.tencent.av.config.ConfigProtocol.ClientAppIDTLV;
import com.tencent.av.config.ConfigProtocol.ClientDeviceTypeTLV;
import com.tencent.av.config.ConfigProtocol.ClientRomInfoTLV;
import com.tencent.av.config.ConfigProtocol.ClientSharpInfoTLV;
import com.tencent.av.config.ConfigProtocol.EngineVersionTLV;
import com.tencent.av.config.ConfigProtocol.S2CConfigInfoProtocol;
import com.tencent.av.config.ConfigProtocol.TLVBase;
import com.tencent.av.utils.QLog;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public class ConfigSystemImpl {
    public static final byte DEFLECT_ANGLE_0 = (byte) 0;
    public static final byte DEFLECT_ANGLE_180 = (byte) 2;
    public static final byte DEFLECT_ANGLE_270 = (byte) 3;
    public static final byte DEFLECT_ANGLE_90 = (byte) 1;
    static final String TAG = "ConfigSystem";
    static byte angle_local_background = (byte) 0;
    static byte angle_local_front = (byte) 0;
    static byte angle_remote_background_0 = (byte) 0;
    static byte angle_remote_background_180 = (byte) 0;
    static byte angle_remote_background_270 = (byte) 0;
    static byte angle_remote_background_90 = (byte) 0;
    static byte angle_remote_front_0 = (byte) 0;
    static byte angle_remote_front_180 = (byte) 0;
    static byte angle_remote_front_270 = (byte) 0;
    static byte angle_remote_front_90 = (byte) 0;
    static byte bace_camera_format = (byte) 0;
    static S2CConfigInfoProtocol configProtocol = null;
    static byte dAudio_enable = (byte) 1;
    static byte front_camera_format = (byte) 0;
    static boolean isReadDone = false;
    byte[] configMsg = null;
    String m_Appid = null;
    Context m_Context = null;
    ConfigProtocol protocol = null;

    public static byte[] getConfigRequestPackage(String str, Context context) {
        return new ConfigSystemImpl(str, context).getConfigRequestPackage();
    }

    public static boolean isNeedStartVideoProcess(String str, Context context, byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        ConfigSystemImpl configSystemImpl = new ConfigSystemImpl(str, context);
        configSystemImpl.setConfigMsg(bArr);
        configSystemImpl.WriteConfigInfoToFile();
        return configSystemImpl.isNeedStartVideoProcess();
    }

    static void GetAngleInfo(Context context) {
        byte[] readFile = Common.readFile(context, Common.FILE_NAME);
        if (readFile != null) {
            ConfigProtocol configProtocol = new ConfigProtocol();
            configProtocol.getClass();
            S2CConfigInfoProtocol s2CConfigInfoProtocol = new S2CConfigInfoProtocol();
            if (s2CConfigInfoProtocol.UnPack(new ByteBuffer(readFile))) {
                short numOfTLV = s2CConfigInfoProtocol.getNumOfTLV();
                for (short s = (short) 0; s < numOfTLV; s++) {
                    TLVBase GetTLVByIndex = s2CConfigInfoProtocol.GetTLVByIndex(s);
                    if (GetTLVByIndex != null && GetTLVByIndex.getType() == (short) 8) {
                        CameraAngleInfoTLV cameraAngleInfoTLV = (CameraAngleInfoTLV) GetTLVByIndex;
                        angle_local_front = cameraAngleInfoTLV.GetFrontCameraAngleForLocalPreview();
                        angle_local_background = cameraAngleInfoTLV.GetBackCameraAngleForLocalPreview();
                        angle_remote_front_0 = cameraAngleInfoTLV.GetFrontCameraAngleForRemote_0();
                        angle_remote_front_90 = cameraAngleInfoTLV.GetFrontCameraAngleForRemote_90();
                        angle_remote_front_180 = cameraAngleInfoTLV.GetFrontCameraAngleForRemote_180();
                        angle_remote_front_270 = cameraAngleInfoTLV.GetFrontCameraAngleForRemote_270();
                        angle_remote_background_0 = cameraAngleInfoTLV.GetBackCameraAngleForRemote_0();
                        angle_remote_background_90 = cameraAngleInfoTLV.GetBackCameraAngleForRemote_90();
                        angle_remote_background_180 = cameraAngleInfoTLV.GetBackCameraAngleForRemote_180();
                        angle_remote_background_270 = cameraAngleInfoTLV.GetBackCameraAngleForRemote_270();
                        front_camera_format = cameraAngleInfoTLV.GetFrontCameraFormat();
                        bace_camera_format = cameraAngleInfoTLV.GetBackCameraFormat();
                        break;
                    }
                }
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "angle_local_front: " + angle_local_front + ", angle_local_background: " + angle_local_background + ", angle_remote_front_0: " + angle_remote_front_0 + ", angle_remote_front_90: " + angle_remote_front_90 + ", angle_remote_front_180: " + angle_remote_front_180 + ", angle_remote_front_270: " + angle_remote_front_270 + ", angle_remote_background_0: " + angle_remote_background_0 + ", angle_remote_background_90: " + angle_remote_background_90 + ", angle_remote_background_180: " + angle_remote_background_180 + ", angle_remote_background_270: " + angle_remote_background_270);
                }
            }
        }
    }

    public static byte GetAngleForCamera(Context context, boolean z, boolean z2, byte b) {
        if (!isReadDone) {
            GetAngleInfo(context);
            isReadDone = true;
        }
        if (z2) {
            if (z) {
                return angle_local_front;
            }
            return angle_local_background;
        } else if (z) {
            if (b == (byte) 0) {
                return angle_remote_front_0;
            }
            if (b == (byte) 1) {
                return angle_remote_front_90;
            }
            if (b == (byte) 2) {
                return angle_remote_front_180;
            }
            if (b == (byte) 3) {
                return angle_remote_front_270;
            }
            return (byte) 0;
        } else if (b == (byte) 0) {
            return angle_remote_background_0;
        } else {
            if (b == (byte) 1) {
                return angle_remote_background_90;
            }
            if (b == (byte) 2) {
                return angle_remote_background_180;
            }
            if (b == (byte) 3) {
                return angle_remote_background_270;
            }
            return (byte) 0;
        }
    }

    public ConfigSystemImpl(String str, Context context) {
        this.m_Appid = str;
        this.m_Context = context;
        this.protocol = new ConfigProtocol();
        ConfigProtocol configProtocol = this.protocol;
        configProtocol.getClass();
        configProtocol = new S2CConfigInfoProtocol();
    }

    public boolean isNeedStartVideoProcess() {
        if (this.configMsg != null) {
            if (configProtocol.UnPack(new ByteBuffer(this.configMsg))) {
                short numOfTLV = configProtocol.getNumOfTLV();
                if (numOfTLV > (short) 0) {
                    for (short s = (short) 0; s < numOfTLV; s++) {
                        TLVBase GetTLVByIndex = configProtocol.GetTLVByIndex(s);
                        if (GetTLVByIndex != null && (GetTLVByIndex.getType() == (short) 2 || GetTLVByIndex.getType() == (short) 3)) {
                            return true;
                        }
                    }
                    return false;
                } else if (!QLog.isColorLevel()) {
                    return false;
                } else {
                    QLog.d(TAG, 0, "TLV number less then 1");
                    return false;
                }
            } else if (!QLog.isColorLevel()) {
                return false;
            } else {
                QLog.d(TAG, 0, "UnpackConfigInfo Error");
                return false;
            }
        } else if (!QLog.isColorLevel()) {
            return false;
        } else {
            QLog.d(TAG, 0, "null == this.configMsg");
            return false;
        }
    }

    public void WriteConfigInfoToFile() {
        if (this.configMsg != null && this.configMsg.length > 0) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "WriteConfigInfoToFile commit!");
            }
            Common.writeFile(this.m_Context, Common.FILE_NAME, this.configMsg);
            dAudio_enable = (byte) 1;
            String sharpConfigPayload = getSharpConfigPayload(this.m_Context);
            if (sharpConfigPayload != null && sharpConfigPayload.length() > 0) {
                String substring = sharpConfigPayload.substring(0, 1);
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "WriteConfigInfoToFile SharpConfigPayload: " + sharpConfigPayload + " sharpConfigType: " + substring);
                }
            }
        }
    }

    public byte[] getConfigRequestPackage() {
        ConfigProtocol configProtocol = this.protocol;
        configProtocol.getClass();
        C2SReqConfigProtocol c2SReqConfigProtocol = new C2SReqConfigProtocol();
        c2SReqConfigProtocol.setTag((short) 1);
        ConfigProtocol configProtocol2 = this.protocol;
        configProtocol2.getClass();
        TLVBase clientDeviceTypeTLV = new ClientDeviceTypeTLV();
        clientDeviceTypeTLV.setClientType((short) 101);
        c2SReqConfigProtocol.AddTLV(clientDeviceTypeTLV);
        configProtocol2 = this.protocol;
        configProtocol2.getClass();
        clientDeviceTypeTLV = new ClientAppIDTLV((short) 0);
        clientDeviceTypeTLV.setAppID(this.m_Appid);
        c2SReqConfigProtocol.AddTLV(clientDeviceTypeTLV);
        configProtocol2 = this.protocol;
        configProtocol2.getClass();
        clientDeviceTypeTLV = new EngineVersionTLV((short) 0);
        clientDeviceTypeTLV.setEngienVersion(Common.getVersion(this.m_Context));
        c2SReqConfigProtocol.AddTLV(clientDeviceTypeTLV);
        configProtocol2 = this.protocol;
        configProtocol2.getClass();
        clientDeviceTypeTLV = new ClientRomInfoTLV();
        clientDeviceTypeTLV.SetRomInfo(VERSION.INCREMENTAL);
        c2SReqConfigProtocol.AddTLV(clientDeviceTypeTLV);
        configProtocol2 = this.protocol;
        configProtocol2.getClass();
        clientDeviceTypeTLV = new ClientSharpInfoTLV();
        clientDeviceTypeTLV.SetOpenslInfo(22);
        c2SReqConfigProtocol.AddTLV(clientDeviceTypeTLV);
        return c2SReqConfigProtocol.Pack().Data();
    }

    void setConfigMsg(byte[] bArr) {
        this.configMsg = bArr;
    }

    static void getDAudioConfig(Context context) {
        byte[] readFile = Common.readFile(context, Common.FILE_NAME);
        if (readFile != null) {
            ConfigProtocol configProtocol = new ConfigProtocol();
            configProtocol.getClass();
            S2CConfigInfoProtocol s2CConfigInfoProtocol = new S2CConfigInfoProtocol();
            if (s2CConfigInfoProtocol.UnPack(new ByteBuffer(readFile))) {
                short numOfTLV = s2CConfigInfoProtocol.getNumOfTLV();
                short s = (short) 0;
                while (s < numOfTLV) {
                    TLVBase GetTLVByIndex = s2CConfigInfoProtocol.GetTLVByIndex(s);
                    if (GetTLVByIndex == null || GetTLVByIndex.getType() != (short) 11) {
                        s++;
                    } else {
                        dAudio_enable = ((AVSwitchTypeTLV) GetTLVByIndex).getM_bIsAuidoEnable();
                        return;
                    }
                }
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "file has no DAudio Config item");
                }
            } else if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "unPack TLV video config err");
            }
        } else if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "not have config file");
        }
    }

    public static boolean isDAudioEnable(Context context) {
        getDAudioConfig(context);
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "isDAudioEnable:" + dAudio_enable);
        }
        if (dAudio_enable == (byte) 1) {
            return true;
        }
        return false;
    }

    public String getSharpConfigPayload(Context context) {
        byte[] readFile = Common.readFile(context, Common.FILE_NAME);
        if (readFile == null) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "not have config file");
            }
            return "";
        }
        ConfigProtocol configProtocol = new ConfigProtocol();
        configProtocol.getClass();
        if (new S2CConfigInfoProtocol().UnPack(new ByteBuffer(readFile))) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "file has no SharpConfigPayload config item");
            }
            return "";
        }
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "unPack TLV video config err");
        }
        return "";
    }

    public int getSharpConfigVersion(Context context) {
        byte[] readFile = Common.readFile(context, Common.SHARP_CONFIG_PAYLOAD_FILE_NAME);
        if (readFile == null) {
            return 0;
        }
        String str = new String(readFile);
        int indexOf = str.indexOf(Opcodes.NOT_INT);
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        if (QLog.isColorLevel()) {
            QLog.d("", 0, "GetSharpConfigPayloadFromFile get version: " + substring + ". payload: " + substring2);
        }
        return Integer.parseInt(substring);
    }
}
