package com.tencent.av.config;

import android.content.Context;
import com.tencent.av.utils.QLog;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public class ConfigInfo {
    private static ConfigInfo instance = null;
    private Context m_context = null;

    private static native void cacheMethodIds();

    private native void init();

    public static ConfigInfo instance() {
        if (instance == null) {
            instance = new ConfigInfo();
        }
        return instance;
    }

    public void init(Context context) {
        this.m_context = context;
    }

    public void WriteConfigInfoToFile(byte[] bArr) {
    }

    public byte[] GetConfigInfoFromFile() {
        return Common.readFile(this.m_context, Common.FILE_NAME);
    }

    public String GetSharpConfigPayloadFromFile() {
        byte[] readFile = Common.readFile(this.m_context, Common.SHARP_CONFIG_PAYLOAD_FILE_NAME);
        if (readFile == null) {
            return "";
        }
        String str = new String(readFile);
        if (QLog.isColorLevel()) {
            QLog.d("", 0, "GetSharpConfigPayloadFromFile payloadBufTmp: " + str);
        }
        int indexOf = str.indexOf(Opcodes.NOT_INT);
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        if (!QLog.isColorLevel()) {
            return substring2;
        }
        QLog.d("", 0, "GetSharpConfigPayloadFromFile version: " + substring + ". payload: " + substring2);
        return substring2;
    }

    public int GetSharpConfigVersionFromFile() {
        byte[] readFile = Common.readFile(this.m_context, Common.SHARP_CONFIG_PAYLOAD_FILE_NAME);
        if (readFile == null) {
            return 0;
        }
        String str = new String(readFile);
        if (QLog.isColorLevel()) {
            QLog.d("", 0, "GetSharpConfigPayloadFromFile payloadBufTmp: " + str);
        }
        int indexOf = str.indexOf(Opcodes.NOT_INT);
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        if (QLog.isColorLevel()) {
            QLog.d("", 0, "GetSharpConfigPayloadFromFile version: " + substring + ". payload: " + substring2);
        }
        return Integer.parseInt(substring);
    }

    public ConfigInfo() {
        try {
            init();
            cacheMethodIds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
