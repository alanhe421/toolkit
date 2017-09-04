package com.tencent.sharp.jni;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.av.utils.QLog;
import com.tencent.sharp.jni.TraeMediaPlayer.OnCompletionListener;
import com.tencent.upload.log.trace.TracerConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

@SuppressLint({"NewApi"})
public class TraeAudioManager extends BroadcastReceiver {
    public static final String ACTION_TRAEAUDIOMANAGER_NOTIFY = "com.tencent.sharp.ACTION_TRAEAUDIOMANAGER_NOTIFY";
    public static final String ACTION_TRAEAUDIOMANAGER_REQUEST = "com.tencent.sharp.ACTION_TRAEAUDIOMANAGER_REQUEST";
    public static final String ACTION_TRAEAUDIOMANAGER_RES = "com.tencent.sharp.ACTION_TRAEAUDIOMANAGER_RES";
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP = 128;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES = 256;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER = 512;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO = 16;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO_CARKIT = 64;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO_HEADSET = 32;
    static final int AUDIO_DEVICE_OUT_EARPIECE = 1;
    static final int AUDIO_DEVICE_OUT_SPEAKER = 2;
    static final int AUDIO_DEVICE_OUT_WIRED_HEADPHONE = 8;
    static final int AUDIO_DEVICE_OUT_WIRED_HEADSET = 4;
    public static final int AUDIO_MANAGER_ACTIVE_NONE = 0;
    public static final int AUDIO_MANAGER_ACTIVE_RING = 2;
    public static final int AUDIO_MANAGER_ACTIVE_VOICECALL = 1;
    static final String AUDIO_PARAMETER_STREAM_ROUTING = "routing";
    public static final String CONNECTDEVICE_DEVICENAME = "CONNECTDEVICE_DEVICENAME";
    public static final String CONNECTDEVICE_RESULT_DEVICENAME = "CONNECTDEVICE_RESULT_DEVICENAME";
    public static final String DEVICE_BLUETOOTHHEADSET = "DEVICE_BLUETOOTHHEADSET";
    public static final String DEVICE_EARPHONE = "DEVICE_EARPHONE";
    public static final String DEVICE_NONE = "DEVICE_NONE";
    public static final String DEVICE_SPEAKERPHONE = "DEVICE_SPEAKERPHONE";
    public static final int DEVICE_STATUS_CONNECTED = 2;
    public static final int DEVICE_STATUS_CONNECTING = 1;
    public static final int DEVICE_STATUS_DISCONNECTED = 0;
    public static final int DEVICE_STATUS_DISCONNECTING = 3;
    public static final int DEVICE_STATUS_ERROR = -1;
    public static final int DEVICE_STATUS_UNCHANGEABLE = 4;
    public static final String DEVICE_WIREDHEADSET = "DEVICE_WIREDHEADSET";
    public static final int EARACTION_AWAY = 0;
    public static final int EARACTION_CLOSE = 1;
    public static final String EXTRA_DATA_AVAILABLEDEVICE_LIST = "EXTRA_DATA_AVAILABLEDEVICE_LIST";
    public static final String EXTRA_DATA_CONNECTEDDEVICE = "EXTRA_DATA_CONNECTEDDEVICE";
    public static final String EXTRA_DATA_DEVICECONFIG = "EXTRA_DATA_DEVICECONFIG";
    public static final String EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME = "EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME";
    public static final String EXTRA_DATA_PREV_CONNECTEDDEVICE = "EXTRA_DATA_PREV_CONNECTEDDEVICE";
    public static final String EXTRA_DATA_ROUTESWITCHEND_DEV = "EXTRA_DATA_ROUTESWITCHEND_DEV";
    public static final String EXTRA_DATA_ROUTESWITCHEND_TIME = "EXTRA_DATA_ROUTESWITCHEND_TIME";
    public static final String EXTRA_DATA_ROUTESWITCHSTART_FROM = "EXTRA_DATA_ROUTESWITCHSTART_FROM";
    public static final String EXTRA_DATA_ROUTESWITCHSTART_TO = "EXTRA_DATA_ROUTESWITCHSTART_TO";
    public static final String EXTRA_DATA_STREAMTYPE = "EXTRA_DATA_STREAMTYPE";
    public static final String EXTRA_EARACTION = "EXTRA_EARACTION";
    public static final int FORCE_ANALOG_DOCK = 8;
    public static final int FORCE_BT_A2DP = 4;
    public static final int FORCE_BT_CAR_DOCK = 6;
    public static final int FORCE_BT_DESK_DOCK = 7;
    public static final int FORCE_BT_SCO = 3;
    public static final int FORCE_DEFAULT = 0;
    public static final int FORCE_DIGITAL_DOCK = 9;
    public static final int FORCE_HEADPHONES = 2;
    public static final int FORCE_NONE = 0;
    public static final int FORCE_NO_BT_A2DP = 10;
    public static final int FORCE_SPEAKER = 1;
    public static final int FORCE_WIRED_ACCESSORY = 5;
    public static final int FOR_COMMUNICATION = 0;
    public static final int FOR_DOCK = 3;
    public static final int FOR_MEDIA = 1;
    public static final int FOR_RECORD = 2;
    public static final String GETCONNECTEDDEVICE_RESULT_LIST = "GETCONNECTEDDEVICE_REULT_LIST";
    public static final String GETCONNECTINGDEVICE_RESULT_LIST = "GETCONNECTINGDEVICE_REULT_LIST";
    public static final String ISDEVICECHANGABLED_RESULT_ISCHANGABLED = "ISDEVICECHANGABLED_REULT_ISCHANGABLED";
    public static boolean IsMusicScene = false;
    public static boolean IsUpdateSceneFlag = false;
    public static final int MODE_MUSIC_PLAYBACK = 2;
    public static final int MODE_MUSIC_PLAY_RECORD = 1;
    public static final int MODE_MUSIC_PLAY_RECORD_HIGH_QUALITY = 3;
    public static final int MODE_VOICE_CHAT = 0;
    public static final String MUSIC_CONFIG = "DEVICE_SPEAKERPHONE;DEVICE_WIREDHEADSET;DEVICE_BLUETOOTHHEADSET;";
    public static final String NOTIFY_DEVICECHANGABLE_UPDATE = "NOTIFY_DEVICECHANGABLE_UPDATE";
    public static final String NOTIFY_DEVICECHANGABLE_UPDATE_DATE = "NOTIFY_DEVICECHANGABLE_UPDATE_DATE";
    public static final String NOTIFY_DEVICELIST_UPDATE = "NOTIFY_DEVICELISTUPDATE";
    public static final String NOTIFY_RING_COMPLETION = "NOTIFY_RING_COMPLETION";
    public static final String NOTIFY_ROUTESWITCHEND = "NOTIFY_ROUTESWITCHEND";
    public static final String NOTIFY_ROUTESWITCHSTART = "NOTIFY_ROUTESWITCHSTART";
    public static final String NOTIFY_SERVICE_STATE = "NOTIFY_SERVICE_STATE";
    public static final String NOTIFY_SERVICE_STATE_DATE = "NOTIFY_SERVICE_STATE_DATE";
    public static final String NOTIFY_STREAMTYPE_UPDATE = "NOTIFY_STREAMTYPE_UPDATE";
    private static final int NUM_FORCE_CONFIG = 11;
    private static final int NUM_FORCE_USE = 4;
    public static final String OPERATION_CONNECTDEVICE = "OPERATION_CONNECTDEVICE";
    public static final String OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE = "OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE";
    public static final String OPERATION_EARACTION = "OPERATION_EARACTION";
    public static final String OPERATION_GETCONNECTEDDEVICE = "OPERATION_GETCONNECTEDDEVICE";
    public static final String OPERATION_GETCONNECTINGDEVICE = "OPERATION_GETCONNECTINGDEVICE";
    public static final String OPERATION_GETDEVICELIST = "OPERATION_GETDEVICELIST";
    public static final String OPERATION_GETSTREAMTYPE = "OPERATION_GETSTREAMTYPE";
    public static final String OPERATION_ISDEVICECHANGABLED = "OPERATION_ISDEVICECHANGABLED";
    public static final String OPERATION_RECOVER_AUDIO_FOCUS = "OPERATION_RECOVER_AUDIO_FOCUS";
    public static final String OPERATION_REGISTERAUDIOSESSION = "OPERATION_REGISTERAUDIOSESSION";
    public static final String OPERATION_REQUEST_RELEASE_AUDIO_FOCUS = "OPERATION_REQUEST_RELEASE_AUDIO_FOCUS";
    public static final String OPERATION_STARTRING = "OPERATION_STARTRING";
    public static final String OPERATION_STARTSERVICE = "OPERATION_STARTSERVICE";
    public static final String OPERATION_STOPRING = "OPERATION_STOPRING";
    public static final String OPERATION_STOPSERVICE = "OPERATION_STOPSERVICE";
    public static final String OPERATION_VOICECALL_AUDIOPARAM_CHANGED = "OPERATION_VOICECALL_AUDIOPARAM_CHANGED";
    public static final String OPERATION_VOICECALL_POSTPROCESS = "OPERATION_VOICECALL_POSTROCESS";
    public static final String OPERATION_VOICECALL_PREPROCESS = "OPERATION_VOICECALL_PREPROCESS";
    public static final String PARAM_DEVICE = "PARAM_DEVICE";
    public static final String PARAM_ERROR = "PARAM_ERROR";
    public static final String PARAM_ISHOSTSIDE = "PARAM_ISHOSTSIDE";
    public static final String PARAM_MODEPOLICY = "PARAM_MODEPOLICY";
    public static final String PARAM_OPERATION = "PARAM_OPERATION";
    public static final String PARAM_RES_ERRCODE = "PARAM_RES_ERRCODE";
    public static final String PARAM_RING_DATASOURCE = "PARAM_RING_DATASOURCE";
    public static final String PARAM_RING_FILEPATH = "PARAM_RING_FILEPATH";
    public static final String PARAM_RING_LOOP = "PARAM_RING_LOOP";
    public static final String PARAM_RING_LOOPCOUNT = "PARAM_RING_LOOPCOUNT";
    public static final String PARAM_RING_MODE = "PARAM_RING_MODE";
    public static final String PARAM_RING_RSID = "PARAM_RING_RSID";
    public static final String PARAM_RING_URI = "PARAM_RING_URI";
    public static final String PARAM_RING_USERDATA_STRING = "PARAM_RING_USERDATA_STRING";
    public static final String PARAM_SESSIONID = "PARAM_SESSIONID";
    public static final String PARAM_STATUS = "PARAM_STATUS";
    public static final String PARAM_STREAMTYPE = "PARAM_STREAMTYPE";
    public static final String REGISTERAUDIOSESSION_ISREGISTER = "REGISTERAUDIOSESSION_ISREGISTER";
    public static final int RES_ERRCODE_DEVICE_BTCONNCECTED_TIMEOUT = 10;
    public static final int RES_ERRCODE_DEVICE_NOT_VISIABLE = 8;
    public static final int RES_ERRCODE_DEVICE_UNCHANGEABLE = 9;
    public static final int RES_ERRCODE_DEVICE_UNKOWN = 7;
    public static final int RES_ERRCODE_NONE = 0;
    public static final int RES_ERRCODE_RING_NOT_EXIST = 5;
    public static final int RES_ERRCODE_SERVICE_OFF = 1;
    public static final int RES_ERRCODE_STOPRING_INTERRUPT = 4;
    public static final int RES_ERRCODE_VOICECALLPOST_INTERRUPT = 6;
    public static final int RES_ERRCODE_VOICECALL_EXIST = 2;
    public static final int RES_ERRCODE_VOICECALL_NOT_EXIST = 3;
    public static final String VIDEO_CONFIG = "DEVICE_EARPHONE;DEVICE_SPEAKERPHONE;DEVICE_BLUETOOTHHEADSET;DEVICE_WIREDHEADSET;";
    public static final String VOICECALL_CONFIG = "DEVICE_SPEAKERPHONE;DEVICE_EARPHONE;DEVICE_BLUETOOTHHEADSET;DEVICE_WIREDHEADSET;";
    static int _gHostProcessId = -1;
    static TraeAudioManager _ginstance = null;
    static ReentrantLock _glock = new ReentrantLock();
    static final String[] forceName = new String[]{"FORCE_NONE", "FORCE_SPEAKER", "FORCE_HEADPHONES", "FORCE_BT_SCO", "FORCE_BT_A2DP", "FORCE_WIRED_ACCESSORY", "FORCE_BT_CAR_DOCK", "FORCE_BT_DESK_DOCK", "FORCE_ANALOG_DOCK", "FORCE_NO_BT_A2DP", "FORCE_DIGITAL_DOCK"};
    boolean IsBluetoothA2dpExisted = true;
    int _activeMode = 0;
    AudioManager _am = null;
    TraeAudioSessionHost _audioSessionHost = null;
    BluetoohHeadsetCheckInterface _bluetoothCheck = null;
    Context _context = null;
    DeviceConfigManager _deviceConfigManager = null;
    ReentrantLock _lock = new ReentrantLock();
    int _modePolicy = -1;
    int _prevMode = 0;
    int _streamType = 0;
    switchThread _switchThread = null;
    TraeAudioManagerLooper mTraeAudioManagerLooper = null;
    String sessionConnectedDev = DEVICE_NONE;

    abstract class BluetoohHeadsetCheckInterface {
        abstract void _addAction(IntentFilter intentFilter);

        abstract void _onReceive(Context context, Intent intent);

        public abstract boolean init(Context context, DeviceConfigManager deviceConfigManager);

        public abstract String interfaceDesc();

        public abstract boolean isConnected();

        public abstract void release();

        BluetoohHeadsetCheckInterface() {
        }

        public void addAction(IntentFilter intentFilter) {
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            _addAction(intentFilter);
        }

        public void onReceive(Context context, Intent intent, DeviceConfigManager deviceConfigManager) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "BT ACTION_STATE_CHANGED|   EXTRA_STATE " + getBTActionStateChangedExtraString(intExtra));
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "BT ACTION_STATE_CHANGED|   EXTRA_PREVIOUS_STATE " + getBTActionStateChangedExtraString(intExtra2));
                }
                if (intExtra == 10) {
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 0, "    BT off");
                    }
                    deviceConfigManager.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                } else if (intExtra == 12 && QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "BT OFF-->ON,Visiable it...");
                }
            } else if ("android.bluetooth.device.action.ACL_CONNECTED".equals(intent.getAction()) && VERSION.SDK_INT < 11) {
            } else {
                if (!"android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) || VERSION.SDK_INT >= 11) {
                    _onReceive(context, intent);
                }
            }
        }

        String getBTActionStateChangedExtraString(int i) {
            String str;
            switch (i) {
                case 10:
                    str = "STATE_OFF";
                    break;
                case 11:
                    str = "STATE_TURNING_ON";
                    break;
                case 12:
                    str = "STATE_ON";
                    break;
                case 13:
                    str = "STATE_TURNING_OFF";
                    break;
                default:
                    str = "unknow";
                    break;
            }
            return str + ":" + i;
        }

        String getSCOAudioStateExtraString(int i) {
            String str;
            switch (i) {
                case -1:
                    str = "SCO_AUDIO_STATE_ERROR";
                    break;
                case 0:
                    str = "SCO_AUDIO_STATE_DISCONNECTED";
                    break;
                case 1:
                    str = "SCO_AUDIO_STATE_CONNECTED";
                    break;
                case 2:
                    str = "SCO_AUDIO_STATE_CONNECTING";
                    break;
                default:
                    str = "unknow";
                    break;
            }
            return str + ":" + i;
        }

        String getBTAdapterConnectionState(int i) {
            String str;
            switch (i) {
                case 0:
                    str = "STATE_DISCONNECTED";
                    break;
                case 1:
                    str = "STATE_CONNECTING";
                    break;
                case 2:
                    str = "STATE_CONNECTED";
                    break;
                case 3:
                    str = "STATE_DISCONNECTING";
                    break;
                default:
                    str = "unknow";
                    break;
            }
            return str + ":" + i;
        }

        String getBTHeadsetConnectionState(int i) {
            String str;
            switch (i) {
                case 0:
                    str = "STATE_DISCONNECTED";
                    break;
                case 1:
                    str = "STATE_CONNECTING";
                    break;
                case 2:
                    str = "STATE_CONNECTED";
                    break;
                case 3:
                    str = "STATE_DISCONNECTING";
                    break;
                default:
                    str = "unknow";
                    break;
            }
            return str + ":" + i;
        }

        String getBTHeadsetAudioState(int i) {
            String str;
            switch (i) {
                case 10:
                    str = "STATE_AUDIO_DISCONNECTED";
                    break;
                case 12:
                    str = "STATE_AUDIO_CONNECTED";
                    break;
                default:
                    str = "unknow:" + i;
                    break;
            }
            return str + ":" + i;
        }
    }

    @TargetApi(11)
    class BluetoohHeadsetCheck extends BluetoohHeadsetCheckInterface implements ServiceListener {
        BluetoothAdapter _adapter = null;
        Context _ctx = null;
        DeviceConfigManager _devCfg = null;
        BluetoothProfile _profile = null;

        BluetoohHeadsetCheck() {
            super();
        }

        @TargetApi(11)
        public boolean init(Context context, DeviceConfigManager deviceConfigManager) {
            AudioDeviceInterface.LogTraceEntry("");
            if (context != null && deviceConfigManager != null) {
                this._ctx = context;
                this._devCfg = deviceConfigManager;
                this._adapter = BluetoothAdapter.getDefaultAdapter();
                if (this._adapter == null) {
                    if (!QLog.isColorLevel()) {
                        return false;
                    }
                    QLog.e("TRAE", 0, " err getDefaultAdapter fail!");
                    return false;
                } else if (!this._adapter.isEnabled() || this._profile != null || this._adapter.getProfileProxy(this._ctx, this, 1)) {
                    AudioDeviceInterface.LogTraceExit();
                    return true;
                } else if (!QLog.isColorLevel()) {
                    return false;
                } else {
                    QLog.e("TRAE", 0, "BluetoohHeadsetCheck: getProfileProxy HEADSET fail!");
                    return false;
                }
            } else if (!QLog.isColorLevel()) {
                return false;
            } else {
                QLog.e("TRAE", 0, " err ctx==null||_devCfg==null");
                return false;
            }
        }

        public void release() {
            AudioDeviceInterface.LogTraceEntry("_profile:" + this._profile);
            try {
                if (this._adapter != null) {
                    if (this._profile != null) {
                        this._adapter.closeProfileProxy(1, this._profile);
                    }
                    this._profile = null;
                }
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, " closeProfileProxy:e:" + e.getMessage());
                }
            }
            AudioDeviceInterface.LogTraceExit();
        }

        public boolean isConnected() {
            if (this._profile == null) {
                return false;
            }
            List connectedDevices = this._profile.getConnectedDevices();
            if (connectedDevices != null && connectedDevices.size() > 0) {
                return true;
            }
            return false;
        }

        @TargetApi(11)
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            AudioDeviceInterface.LogTraceEntry("_profile:" + this._profile + " profile:" + i + " proxy:" + bluetoothProfile);
            if (i == 1) {
                if (!(this._profile == null || this._profile == bluetoothProfile)) {
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 0, "BluetoohHeadsetCheck: HEADSET Connected proxy:" + bluetoothProfile + " _profile:" + this._profile);
                    }
                    this._adapter.closeProfileProxy(1, this._profile);
                    this._profile = null;
                }
                this._profile = bluetoothProfile;
                List connectedDevices = this._profile.getConnectedDevices();
                if (connectedDevices != null) {
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 0, "TRAEBluetoohProxy: HEADSET Connected devs:" + connectedDevices.size() + " _profile:" + this._profile);
                    }
                    for (int i2 = 0; i2 < connectedDevices.size(); i2++) {
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) connectedDevices.get(i2);
                        int connectionState = this._profile.getConnectionState(bluetoothDevice);
                        if (connectionState == 2) {
                            this._devCfg.setBluetoothName(bluetoothDevice.getName());
                        }
                        if (QLog.isColorLevel()) {
                            QLog.w("TRAE", 0, "   " + i2 + " " + bluetoothDevice.getName() + " ConnectionState:" + connectionState);
                        }
                    }
                }
                if (this._devCfg != null) {
                    CharSequence bluetoothName;
                    if (TraeAudioManager.this._deviceConfigManager != null) {
                        bluetoothName = TraeAudioManager.this._deviceConfigManager.getBluetoothName();
                    } else {
                        bluetoothName = null;
                    }
                    if (TextUtils.isEmpty(bluetoothName)) {
                        this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                    } else if (isConnected()) {
                        this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                        TraeAudioManager.this.checkDevicePlug(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                    } else {
                        this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                    }
                }
            }
            AudioDeviceInterface.LogTraceExit();
        }

        @TargetApi(11)
        public void onServiceDisconnected(int i) {
            AudioDeviceInterface.LogTraceEntry("_profile:" + this._profile + " profile:" + i);
            if (i == 1) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "TRAEBluetoohProxy: HEADSET Disconnected");
                }
                if (isConnected()) {
                    TraeAudioManager.this.checkDevicePlug(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
                if (this._profile != null) {
                    this._adapter.closeProfileProxy(1, this._profile);
                    this._profile = null;
                }
            }
            AudioDeviceInterface.LogTraceExit();
        }

        void _addAction(IntentFilter intentFilter) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, " " + interfaceDesc() + " _addAction");
            }
            intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        }

        void _onReceive(Context context, Intent intent) {
            int intExtra;
            int intExtra2;
            BluetoothDevice bluetoothDevice;
            if ("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", -1);
                intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE", -1);
                bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "BT ACTION_CONNECTION_STATE_CHANGED|   EXTRA_CONNECTION_STATE " + getBTAdapterConnectionState(intExtra));
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "    EXTRA_PREVIOUS_CONNECTION_STATE " + getBTAdapterConnectionState(intExtra2));
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "    EXTRA_DEVICE " + bluetoothDevice + " " + (bluetoothDevice != null ? bluetoothDevice.getName() : " "));
                }
                if (intExtra == 2) {
                    String name;
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 0, "   dev:" + bluetoothDevice.getName() + " connected,start sco...");
                    }
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                    DeviceConfigManager deviceConfigManager = this._devCfg;
                    if (bluetoothDevice != null) {
                        name = bluetoothDevice.getName();
                    } else {
                        name = "unkown";
                    }
                    deviceConfigManager.setBluetoothName(name);
                } else if (intExtra == 0) {
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
            } else if ("android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(intent.getAction())) {
                intExtra2 = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_PREVIOUS_STATE", -1);
                bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "BT ACTION_SCO_AUDIO_STATE_UPDATED|   EXTRA_CONNECTION_STATE  dev:" + bluetoothDevice);
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "   EXTRA_SCO_AUDIO_STATE " + getSCOAudioStateExtraString(intExtra2));
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "   EXTRA_SCO_AUDIO_PREVIOUS_STATE " + getSCOAudioStateExtraString(intExtra));
                }
            } else if ("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                switch (defaultAdapter.getProfileConnectionState(2)) {
                    case 0:
                        QLog.w("TRAE", 0, "BluetoothA2dp STATE_DISCONNECTED");
                        TraeAudioManager.this.IsBluetoothA2dpExisted = false;
                        return;
                    case 2:
                        QLog.w("TRAE", 0, "BluetoothA2dp STATE_CONNECTED");
                        TraeAudioManager.this.IsBluetoothA2dpExisted = true;
                        return;
                    default:
                        QLog.w("TRAE", 0, "BluetoothA2dp" + defaultAdapter.getProfileConnectionState(2));
                        return;
                }
            }
        }

        public String interfaceDesc() {
            return "BluetoohHeadsetCheck";
        }
    }

    class BluetoohHeadsetCheckFake extends BluetoohHeadsetCheckInterface {
        BluetoohHeadsetCheckFake() {
            super();
        }

        public boolean init(Context context, DeviceConfigManager deviceConfigManager) {
            return true;
        }

        public void release() {
        }

        public boolean isConnected() {
            return false;
        }

        void _addAction(IntentFilter intentFilter) {
        }

        void _onReceive(Context context, Intent intent) {
        }

        public String interfaceDesc() {
            return "BluetoohHeadsetCheckFake";
        }
    }

    class BluetoohHeadsetCheckFor2x extends BluetoohHeadsetCheckInterface {
        public static final String ACTION_BLUETOOTHHEADSET_AUDIO_STATE_CHANGED = "android.bluetooth.headset.action.AUDIO_STATE_CHANGED";
        public static final String ACTION_BLUETOOTHHEADSET_STATE_CHANGED = "android.bluetooth.headset.action.STATE_CHANGED";
        public static final int AUDIO_STATE_CONNECTED = 1;
        public static final int AUDIO_STATE_DISCONNECTED = 0;
        static final int STATE_CONNECTED = 2;
        static final int STATE_DISCONNECTED = 0;
        Class<?> BluetoothHeadsetClass = null;
        Object BluetoothHeadsetObj = null;
        Class<?> ListenerClass = null;
        Context _ctx = null;
        DeviceConfigManager _devCfg = null;
        Method getCurrentHeadsetMethod = null;

        BluetoohHeadsetCheckFor2x() {
            super();
        }

        public boolean init(Context context, DeviceConfigManager deviceConfigManager) {
            AudioDeviceInterface.LogTraceEntry("");
            this._ctx = context;
            this._devCfg = deviceConfigManager;
            if (this._ctx == null || this._devCfg == null) {
                return false;
            }
            try {
                this.BluetoothHeadsetClass = Class.forName("android.bluetooth.BluetoothHeadset");
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "BTLooperThread BluetoothHeadset class not found");
                }
            }
            if (this.BluetoothHeadsetClass == null) {
                return false;
            }
            try {
                this.ListenerClass = Class.forName("android.bluetooth.BluetoothHeadset$ServiceListener");
            } catch (Exception e2) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "BTLooperThread BluetoothHeadset.ServiceListener class not found:" + e2);
                }
            }
            if (this.ListenerClass == null) {
                try {
                } catch (NoSuchMethodException e3) {
                    if (QLog.isColorLevel()) {
                        QLog.e("TRAE", 0, "BTLooperThread BluetoothHeadset method getCurrentHeadset NoSuchMethodException");
                    }
                }
            }
            this.getCurrentHeadsetMethod = this.BluetoothHeadsetClass.getDeclaredMethod("getCurrentHeadset", new Class[0]);
            if (this.getCurrentHeadsetMethod == null) {
                return false;
            }
            try {
                this.BluetoothHeadsetObj = this.BluetoothHeadsetClass.getConstructor(new Class[]{Context.class, this.ListenerClass}).newInstance(new Object[]{context, null});
            } catch (IllegalArgumentException e4) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "BTLooperThread BluetoothHeadset getConstructor IllegalArgumentException");
                }
            } catch (InstantiationException e5) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "BTLooperThread BluetoothHeadset getConstructor InstantiationException");
                }
            } catch (IllegalAccessException e6) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "BTLooperThread BluetoothHeadset getConstructor IllegalAccessException");
                }
            } catch (InvocationTargetException e7) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "BTLooperThread BluetoothHeadset getConstructor InvocationTargetException");
                }
            } catch (NoSuchMethodException e8) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "BTLooperThread BluetoothHeadset getConstructor NoSuchMethodException");
                }
            }
            if (this.BluetoothHeadsetObj == null) {
                return false;
            }
            this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, isConnected());
            if (isConnected()) {
                this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                TraeAudioManager.this.checkDevicePlug(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
            } else {
                this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
            }
            AudioDeviceInterface.LogTraceExit();
            return true;
        }

        public void release() {
            AudioDeviceInterface.LogTraceEntry("");
            if (this.BluetoothHeadsetObj != null) {
                Method declaredMethod;
                try {
                    declaredMethod = this.BluetoothHeadsetClass.getDeclaredMethod("close", new Class[0]);
                } catch (NoSuchMethodException e) {
                    if (QLog.isColorLevel()) {
                        QLog.e("TRAE", 0, "BTLooperThread _uninitHeadsetfor2x method close NoSuchMethodException");
                    }
                    declaredMethod = null;
                }
                if (declaredMethod != null) {
                    try {
                        declaredMethod.invoke(this.BluetoothHeadsetObj, new Object[0]);
                    } catch (IllegalArgumentException e2) {
                    } catch (IllegalAccessException e3) {
                    } catch (InvocationTargetException e4) {
                    }
                    this.BluetoothHeadsetClass = null;
                    this.ListenerClass = null;
                    this.BluetoothHeadsetObj = null;
                    this.getCurrentHeadsetMethod = null;
                    AudioDeviceInterface.LogTraceExit();
                }
            }
        }

        public boolean isConnected() {
            Object obj = null;
            if (this.getCurrentHeadsetMethod == null || this.getCurrentHeadsetMethod == null) {
                return false;
            }
            try {
                obj = this.getCurrentHeadsetMethod.invoke(this.BluetoothHeadsetObj, new Object[0]);
            } catch (IllegalArgumentException e) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "BTLooperThread BluetoothHeadset method getCurrentHeadset IllegalArgumentException");
                }
            } catch (IllegalAccessException e2) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "BTLooperThread BluetoothHeadset method getCurrentHeadset IllegalAccessException");
                }
            } catch (InvocationTargetException e3) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "BTLooperThread BluetoothHeadset method getCurrentHeadset InvocationTargetException");
                }
            }
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "BTLooperThread BluetoothHeadset method getCurrentHeadset res:" + (obj != null ? " Y" : "N"));
            }
            if (obj != null) {
                return true;
            }
            return false;
        }

        void _addAction(IntentFilter intentFilter) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, " " + interfaceDesc() + " _addAction");
            }
            intentFilter.addAction(ACTION_BLUETOOTHHEADSET_AUDIO_STATE_CHANGED);
            intentFilter.addAction(ACTION_BLUETOOTHHEADSET_STATE_CHANGED);
        }

        void _onReceive(Context context, Intent intent) {
            int intExtra;
            int intExtra2;
            int intExtra3;
            if (ACTION_BLUETOOTHHEADSET_AUDIO_STATE_CHANGED.equals(intent.getAction())) {
                intExtra = intent.getIntExtra("android.bluetooth.headset.extra.STATE", -2);
                intExtra2 = intent.getIntExtra("android.bluetooth.headset.extra.PREVIOUS_STATE", -2);
                intExtra3 = intent.getIntExtra("android.bluetooth.headset.extra.AUDIO_STATE", -2);
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "++ AUDIO_STATE_CHANGED|  STATE " + intExtra);
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "       PREVIOUS_STATE " + intExtra2);
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "       AUDIO_STATE " + intExtra3);
                }
                if (intExtra3 == 2) {
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                } else if (intExtra3 == 0) {
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
            } else if (ACTION_BLUETOOTHHEADSET_STATE_CHANGED.equals(intent.getAction())) {
                intExtra = intent.getIntExtra("android.bluetooth.headset.extra.STATE", -2);
                intExtra2 = intent.getIntExtra("android.bluetooth.headset.extra.PREVIOUS_STATE", -2);
                intExtra3 = intent.getIntExtra("android.bluetooth.headset.extra.AUDIO_STATE", -2);
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "++ STATE_CHANGED|  STATE " + intExtra);
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "       PREVIOUS_STATE " + intExtra2);
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "       AUDIO_STATE " + intExtra3);
                }
                if (intExtra3 == 2) {
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                } else if (intExtra3 == 0) {
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
            }
        }

        public String interfaceDesc() {
            return "BluetoohHeadsetCheckFor2x";
        }
    }

    class DeviceConfigManager {
        String _bluetoothDevName = "unknow";
        String connectedDevice = TraeAudioManager.DEVICE_NONE;
        String connectingDevice = TraeAudioManager.DEVICE_NONE;
        HashMap<String, DeviceConfig> deviceConfigs = new HashMap();
        ReentrantLock mLock = new ReentrantLock();
        String prevConnectedDevice = TraeAudioManager.DEVICE_NONE;
        boolean visiableUpdate = false;

        public class DeviceConfig {
            String deviceName = TraeAudioManager.DEVICE_NONE;
            int priority = 0;
            boolean visible = false;

            public boolean init(String str, int i) {
                if (str == null || str.length() <= 0 || !TraeAudioManager.checkDevName(str)) {
                    return false;
                }
                this.deviceName = str;
                this.priority = i;
                return true;
            }

            public String getDeviceName() {
                return this.deviceName;
            }

            public boolean getVisible() {
                return this.visible;
            }

            public int getPriority() {
                return this.priority;
            }

            public void setVisible(boolean z) {
                this.visible = z;
            }
        }

        public boolean init(String str) {
            AudioDeviceInterface.LogTraceEntry(" strConfigs:" + str);
            if (str == null || str.length() <= 0) {
                return false;
            }
            String replace = str.replace("\n", "").replace("\r", "");
            if (replace == null || replace.length() <= 0) {
                return false;
            }
            if (replace.indexOf(VoiceWakeuperAidl.PARAMS_SEPARATE) < 0) {
                replace = replace + VoiceWakeuperAidl.PARAMS_SEPARATE;
            }
            String[] split = replace.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
            if (split == null || 1 > split.length) {
                return false;
            }
            this.mLock.lock();
            for (int i = 0; i < split.length; i++) {
                _addConfig(split[i], i);
            }
            this.mLock.unlock();
            TraeAudioManager.this.printDevices();
            return true;
        }

        boolean _addConfig(String str, int i) {
            AudioDeviceInterface.LogTraceEntry(" devName:" + str + " priority:" + i);
            DeviceConfig deviceConfig = new DeviceConfig();
            if (deviceConfig.init(str, i)) {
                if (!this.deviceConfigs.containsKey(str)) {
                    this.deviceConfigs.put(str, deviceConfig);
                    this.visiableUpdate = true;
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 0, " n" + getDeviceNumber() + " 0:" + getDeviceName(0));
                    }
                    AudioDeviceInterface.LogTraceExit();
                    return true;
                } else if (!QLog.isColorLevel()) {
                    return false;
                } else {
                    QLog.e("TRAE", 0, "err dev exist!");
                    return false;
                }
            } else if (!QLog.isColorLevel()) {
                return false;
            } else {
                QLog.e("TRAE", 0, " err dev init!");
                return false;
            }
        }

        public void clearConfig() {
            this.mLock.lock();
            this.deviceConfigs.clear();
            this.prevConnectedDevice = TraeAudioManager.DEVICE_NONE;
            this.connectedDevice = TraeAudioManager.DEVICE_NONE;
            this.connectingDevice = TraeAudioManager.DEVICE_NONE;
            this.mLock.unlock();
        }

        public boolean getVisiableUpdateFlag() {
            this.mLock.lock();
            boolean z = this.visiableUpdate;
            this.mLock.unlock();
            return z;
        }

        public void resetVisiableUpdateFlag() {
            this.mLock.lock();
            this.visiableUpdate = false;
            this.mLock.unlock();
        }

        public boolean setVisible(String str, boolean z) {
            boolean z2;
            this.mLock.lock();
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(str);
            if (deviceConfig == null || deviceConfig.getVisible() == z) {
                z2 = false;
            } else {
                deviceConfig.setVisible(z);
                this.visiableUpdate = true;
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, " ++setVisible:" + str + (z ? " Y" : " N"));
                }
                z2 = true;
            }
            this.mLock.unlock();
            return z2;
        }

        public void setBluetoothName(String str) {
            if (str == null) {
                this._bluetoothDevName = "unknow";
            } else if (str.isEmpty()) {
                this._bluetoothDevName = "unknow";
            } else {
                this._bluetoothDevName = str;
            }
        }

        public String getBluetoothName() {
            return this._bluetoothDevName;
        }

        public boolean getVisible(String str) {
            boolean visible;
            this.mLock.lock();
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(str);
            if (deviceConfig != null) {
                visible = deviceConfig.getVisible();
            } else {
                visible = false;
            }
            this.mLock.unlock();
            return visible;
        }

        public int getPriority(String str) {
            int priority;
            this.mLock.lock();
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(str);
            if (deviceConfig != null) {
                priority = deviceConfig.getPriority();
            } else {
                priority = -1;
            }
            this.mLock.unlock();
            return priority;
        }

        public int getDeviceNumber() {
            this.mLock.lock();
            int size = this.deviceConfigs.size();
            this.mLock.unlock();
            return size;
        }

        public String getDeviceName(int i) {
            DeviceConfig deviceConfig;
            String deviceName;
            String str = TraeAudioManager.DEVICE_NONE;
            this.mLock.lock();
            int i2 = 0;
            for (Entry entry : this.deviceConfigs.entrySet()) {
                if (i2 == i) {
                    deviceConfig = (DeviceConfig) entry.getValue();
                    break;
                }
                i2++;
            }
            deviceConfig = null;
            if (deviceConfig != null) {
                deviceName = deviceConfig.getDeviceName();
            } else {
                deviceName = str;
            }
            this.mLock.unlock();
            return deviceName;
        }

        public String getAvailabledHighestPriorityDevice(String str) {
            DeviceConfig deviceConfig = null;
            this.mLock.lock();
            for (Entry entry : this.deviceConfigs.entrySet()) {
                entry.getKey();
                entry.getValue();
                DeviceConfig deviceConfig2 = (DeviceConfig) entry.getValue();
                if (!(deviceConfig2 == null || !deviceConfig2.getVisible() || deviceConfig2.getDeviceName().equals(str))) {
                    if (deviceConfig != null && deviceConfig2.getPriority() < deviceConfig.getPriority()) {
                        deviceConfig2 = deviceConfig;
                    }
                    deviceConfig = deviceConfig2;
                }
            }
            this.mLock.unlock();
            return deviceConfig != null ? deviceConfig.getDeviceName() : TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        public String getAvailabledHighestPriorityDevice() {
            DeviceConfig deviceConfig = null;
            this.mLock.lock();
            for (Entry entry : this.deviceConfigs.entrySet()) {
                entry.getKey();
                entry.getValue();
                DeviceConfig deviceConfig2 = (DeviceConfig) entry.getValue();
                if (deviceConfig2 != null && deviceConfig2.getVisible()) {
                    if (deviceConfig != null && deviceConfig2.getPriority() < deviceConfig.getPriority()) {
                        deviceConfig2 = deviceConfig;
                    }
                    deviceConfig = deviceConfig2;
                }
            }
            this.mLock.unlock();
            return deviceConfig != null ? deviceConfig.getDeviceName() : TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        public String getConnectingDevice() {
            String str = TraeAudioManager.DEVICE_NONE;
            this.mLock.lock();
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(this.connectingDevice);
            if (deviceConfig == null || !deviceConfig.getVisible()) {
                str = null;
            } else {
                str = this.connectingDevice;
            }
            this.mLock.unlock();
            return str;
        }

        public String getConnectedDevice() {
            String str = TraeAudioManager.DEVICE_NONE;
            this.mLock.lock();
            str = _getConnectedDevice();
            this.mLock.unlock();
            return str;
        }

        public String getPrevConnectedDevice() {
            String str = TraeAudioManager.DEVICE_NONE;
            this.mLock.lock();
            str = _getPrevConnectedDevice();
            this.mLock.unlock();
            return str;
        }

        public boolean setConnecting(String str) {
            boolean z;
            this.mLock.lock();
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(str);
            if (deviceConfig == null || !deviceConfig.getVisible()) {
                z = false;
            } else {
                this.connectingDevice = str;
                z = true;
            }
            this.mLock.unlock();
            return z;
        }

        public boolean setConnected(String str) {
            boolean z;
            this.mLock.lock();
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(str);
            if (deviceConfig == null || !deviceConfig.getVisible()) {
                z = false;
            } else {
                if (!(this.connectedDevice == null || this.connectedDevice.equals(str))) {
                    this.prevConnectedDevice = this.connectedDevice;
                }
                this.connectedDevice = str;
                this.connectingDevice = "";
                z = true;
            }
            this.mLock.unlock();
            return z;
        }

        public boolean isConnected(String str) {
            boolean z;
            this.mLock.lock();
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(str);
            if (deviceConfig == null || !deviceConfig.getVisible()) {
                z = false;
            } else {
                z = this.connectedDevice.equals(str);
            }
            this.mLock.unlock();
            return z;
        }

        public HashMap<String, Object> getSnapParams() {
            HashMap<String, Object> hashMap = new HashMap();
            this.mLock.lock();
            hashMap.put(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST, _getAvailableDeviceList());
            hashMap.put(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE, _getConnectedDevice());
            hashMap.put(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE, _getPrevConnectedDevice());
            this.mLock.unlock();
            return hashMap;
        }

        public ArrayList<String> getAvailableDeviceList() {
            ArrayList arrayList = new ArrayList();
            this.mLock.lock();
            ArrayList<String> _getAvailableDeviceList = _getAvailableDeviceList();
            this.mLock.unlock();
            return _getAvailableDeviceList;
        }

        ArrayList<String> _getAvailableDeviceList() {
            ArrayList<String> arrayList = new ArrayList();
            for (Entry value : this.deviceConfigs.entrySet()) {
                DeviceConfig deviceConfig = (DeviceConfig) value.getValue();
                if (deviceConfig != null && deviceConfig.getVisible()) {
                    arrayList.add(deviceConfig.getDeviceName());
                }
            }
            return arrayList;
        }

        String _getConnectedDevice() {
            String str = TraeAudioManager.DEVICE_NONE;
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(this.connectedDevice);
            if (deviceConfig == null || !deviceConfig.getVisible()) {
                return str;
            }
            return this.connectedDevice;
        }

        String _getPrevConnectedDevice() {
            String str = TraeAudioManager.DEVICE_NONE;
            DeviceConfig deviceConfig = (DeviceConfig) this.deviceConfigs.get(this.prevConnectedDevice);
            if (deviceConfig == null || !deviceConfig.getVisible()) {
                return str;
            }
            return this.prevConnectedDevice;
        }
    }

    public class Parameters {
        public static final String BLUETOOTHPOLICY = "com.tencent.sharp.TraeAudioManager.Parameters.BLUETOOTHPOLICY";
        public static final String CONTEXT = "com.tencent.sharp.TraeAudioManager.Parameters.CONTEXT";
        public static final String DEVICECONFIG = "com.tencent.sharp.TraeAudioManager.Parameters.DEVICECONFIG";
        public static final String MODEPOLICY = "com.tencent.sharp.TraeAudioManager.Parameters.MODEPOLICY";
    }

    class TraeAudioManagerLooper extends Thread {
        public static final int MESSAGE_AUTO_DEVICELIST_PLUGIN_UPDATE = 32786;
        public static final int MESSAGE_AUTO_DEVICELIST_PLUGOUT_UPDATE = 32787;
        public static final int MESSAGE_AUTO_DEVICELIST_UPDATE = 32785;
        public static final int MESSAGE_BEGIN = 32768;
        public static final int MESSAGE_CONNECTDEVICE = 32775;
        public static final int MESSAGE_CONNECT_HIGHEST_PRIORITY_DEVICE = 32789;
        public static final int MESSAGE_DISABLE = 32773;
        public static final int MESSAGE_EARACTION = 32776;
        public static final int MESSAGE_ENABLE = 32772;
        public static final int MESSAGE_GETCONNECTEDDEVICE = 32778;
        public static final int MESSAGE_GETCONNECTINGDEVICE = 32779;
        public static final int MESSAGE_GETDEVICELIST = 32774;
        public static final int MESSAGE_GETSTREAMTYPE = 32784;
        public static final int MESSAGE_ISDEVICECHANGABLED = 32777;
        public static final int MESSAGE_RECOVER_AUDIO_FOCUS = 32791;
        public static final int MESSAGE_REQUEST_RELEASE_AUDIO_FOCUS = 32790;
        public static final int MESSAGE_STARTRING = 32782;
        public static final int MESSAGE_STOPRING = 32783;
        public static final int MESSAGE_VOICECALLPOSTPROCESS = 32781;
        public static final int MESSAGE_VOICECALLPREPROCESS = 32780;
        public static final int MESSAGE_VOICECALL_AUIDOPARAM_CHANGED = 32788;
        boolean _enabled = false;
        int _focusSteamType = 0;
        String _lastCfg = "";
        TraeAudioManager _parent = null;
        int _preRingMode = 0;
        int _preServiceMode = 0;
        String _ringOperation = "";
        TraeMediaPlayer _ringPlayer = null;
        long _ringSessionID = -1;
        String _ringUserdata = "";
        final boolean[] _started = new boolean[]{false};
        String _voiceCallOperation = "";
        long _voiceCallSessionID = -1;
        OnAudioFocusChangeListener mAudioFocusChangeListener = null;
        Handler mMsgHandler = null;

        public TraeAudioManagerLooper(TraeAudioManager traeAudioManager) {
            this._parent = traeAudioManager;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 0, "TraeAudioManagerLooper start...");
            }
            start();
            synchronized (this._started) {
                if (!this._started[0]) {
                    try {
                        this._started.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 0, "  start used:" + (SystemClock.elapsedRealtime() - elapsedRealtime) + "ms");
            }
        }

        public void quit() {
            AudioDeviceInterface.LogTraceEntry("");
            if (this.mMsgHandler != null) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                this.mMsgHandler.getLooper().quit();
                synchronized (this._started) {
                    if (this._started[0]) {
                        try {
                            this._started.wait(TracerConfig.LOG_FLUSH_DURATION);
                        } catch (InterruptedException e) {
                        }
                    }
                }
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "  quit used:" + (SystemClock.elapsedRealtime() - elapsedRealtime) + "ms");
                }
                this.mMsgHandler = null;
                AudioDeviceInterface.LogTraceExit();
            }
        }

        public int sendMessage(int i, HashMap<String, Object> hashMap) {
            if (this.mMsgHandler == null) {
                String str;
                StringBuilder append = new StringBuilder().append(" fail mMsgHandler==null _enabled:");
                if (this._enabled) {
                    str = "Y";
                } else {
                    str = "N";
                }
                AudioDeviceInterface.LogTraceEntry(append.append(str).append(" activeMode:").append(TraeAudioManager.this._activeMode).append(" msg:").append(i).toString());
                return -1;
            }
            return this.mMsgHandler.sendMessage(Message.obtain(this.mMsgHandler, i, hashMap)) ? 0 : -1;
        }

        void startService(HashMap<String, Object> hashMap) {
            String str = (String) hashMap.get(TraeAudioManager.EXTRA_DATA_DEVICECONFIG);
            Log.w("TRAE", "startService cfg:" + str);
            AudioDeviceInterface.LogTraceEntry(" _enabled:" + (this._enabled ? "Y" : "N") + " activeMode:" + TraeAudioManager.this._activeMode + " cfg:" + str);
            if (TraeAudioManager.this._context != null) {
                QLog.w("TRAE", 0, "   startService:" + str);
                if (!(this._enabled && this._lastCfg.equals(str)) && TraeAudioManager.this._activeMode == 0) {
                    if (this._enabled) {
                        stopService();
                    }
                    _prev_startService();
                    AudioManager audioManager = (AudioManager) TraeAudioManager.this._context.getSystemService("audio");
                    TraeAudioManager.this._deviceConfigManager.clearConfig();
                    TraeAudioManager.this._deviceConfigManager.init(str);
                    this._lastCfg = str;
                    if (TraeAudioManager.this._am != null) {
                        this._preServiceMode = TraeAudioManager.this._am.getMode();
                    }
                    this._enabled = true;
                    if (this._ringPlayer == null) {
                        this._ringPlayer = new TraeMediaPlayer(TraeAudioManager.this._context, new OnCompletionListener() {
                            public void onCompletion() {
                                if (QLog.isColorLevel()) {
                                    QLog.w("TRAE", 0, "_ringPlayer onCompletion _activeMode:" + TraeAudioManager.this._activeMode + " _preRingMode:" + TraeAudioManagerLooper.this._preRingMode);
                                }
                                HashMap hashMap = new HashMap();
                                hashMap.put(TraeAudioManager.PARAM_ISHOSTSIDE, Boolean.valueOf(true));
                                TraeAudioManagerLooper.this.sendMessage(TraeAudioManagerLooper.MESSAGE_STOPRING, hashMap);
                                TraeAudioManagerLooper.this.notifyRingCompletion();
                            }
                        });
                    }
                    notifyServiceState(this._enabled);
                    TraeAudioManager.this.updateDeviceStatus();
                    AudioDeviceInterface.LogTraceExit();
                }
            }
        }

        void stopService() {
            AudioDeviceInterface.LogTraceEntry(" _enabled:" + (this._enabled ? "Y" : "N") + " activeMode:" + TraeAudioManager.this._activeMode);
            if (this._enabled) {
                if (TraeAudioManager.this._activeMode == 1) {
                    interruptVoicecallPostprocess();
                } else if (TraeAudioManager.this._activeMode == 2) {
                    interruptRing();
                }
                if (TraeAudioManager.this._switchThread != null) {
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 0, "_switchThread:" + TraeAudioManager.this._switchThread.getDeviceName());
                    }
                    TraeAudioManager.this._switchThread.quit();
                    TraeAudioManager.this._switchThread = null;
                }
                if (this._ringPlayer != null) {
                    this._ringPlayer.stopRing();
                }
                this._ringPlayer = null;
                this._enabled = false;
                notifyServiceState(this._enabled);
                if (!(TraeAudioManager.this._am == null || TraeAudioManager.this._context == null)) {
                    try {
                        TraeAudioManager.this.InternalSetMode(0);
                    } catch (Exception e) {
                    }
                }
                _post_stopService();
                AudioDeviceInterface.LogTraceExit();
            }
        }

        int notifyServiceState(boolean z) {
            if (TraeAudioManager.this._context == null) {
                return -1;
            }
            Intent intent = new Intent();
            intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
            intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_SERVICE_STATE);
            intent.putExtra(TraeAudioManager.NOTIFY_SERVICE_STATE_DATE, z);
            TraeAudioManager.this._context.sendBroadcast(intent);
            return 0;
        }

        public void run() {
            AudioDeviceInterface.LogTraceEntry("");
            Looper.prepare();
            this.mMsgHandler = new Handler() {
                public void handleMessage(Message message) {
                    HashMap hashMap;
                    try {
                        hashMap = (HashMap) message.obj;
                    } catch (Exception e) {
                        hashMap = null;
                    }
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 0, "TraeAudioManagerLooper msg:" + message.what + " _enabled:" + (TraeAudioManagerLooper.this._enabled ? "Y" : "N"));
                    }
                    if (message.what == TraeAudioManagerLooper.MESSAGE_ENABLE) {
                        TraeAudioManagerLooper.this.startService(hashMap);
                    } else if (TraeAudioManagerLooper.this._enabled) {
                        String availabledHighestPriorityDevice;
                        switch (message.what) {
                            case TraeAudioManagerLooper.MESSAGE_DISABLE /*32773*/:
                                TraeAudioManagerLooper.this.stopService();
                                return;
                            case TraeAudioManagerLooper.MESSAGE_GETDEVICELIST /*32774*/:
                                TraeAudioManagerLooper.this.InternalSessionGetDeviceList(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_CONNECTDEVICE /*32775*/:
                                TraeAudioManager.this.InternalSessionConnectDevice(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_EARACTION /*32776*/:
                                TraeAudioManager.this.InternalSessionEarAction(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_ISDEVICECHANGABLED /*32777*/:
                                TraeAudioManager.this.InternalSessionIsDeviceChangabled(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_GETCONNECTEDDEVICE /*32778*/:
                                TraeAudioManager.this.InternalSessionGetConnectedDevice(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_GETCONNECTINGDEVICE /*32779*/:
                                TraeAudioManager.this.InternalSessionGetConnectingDevice(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_VOICECALLPREPROCESS /*32780*/:
                                TraeAudioManagerLooper.this.InternalVoicecallPreprocess(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_VOICECALLPOSTPROCESS /*32781*/:
                                TraeAudioManagerLooper.this.InternalVoicecallPostprocess(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_STARTRING /*32782*/:
                                TraeAudioManagerLooper.this.InternalStartRing(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_STOPRING /*32783*/:
                                TraeAudioManagerLooper.this.InternalStopRing(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_GETSTREAMTYPE /*32784*/:
                                TraeAudioManagerLooper.this.InternalGetStreamType(hashMap);
                                return;
                            case TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_UPDATE /*32785*/:
                            case TraeAudioManagerLooper.MESSAGE_CONNECT_HIGHEST_PRIORITY_DEVICE /*32789*/:
                                availabledHighestPriorityDevice = TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice();
                                String connectedDevice = TraeAudioManager.this._deviceConfigManager.getConnectedDevice();
                                if (QLog.isColorLevel()) {
                                    QLog.w("TRAE", 0, "MESSAGE_AUTO_DEVICELIST_UPDATE  connectedDev:" + connectedDevice + " highestDev" + availabledHighestPriorityDevice);
                                }
                                if (TraeAudioManager.IsUpdateSceneFlag) {
                                    if (!TraeAudioManager.IsMusicScene || TraeAudioManager.this.IsBluetoothA2dpExisted) {
                                        TraeAudioManager.this.InternalConnectDevice(availabledHighestPriorityDevice, null, true);
                                        return;
                                    } else {
                                        TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice(TraeAudioManager.DEVICE_BLUETOOTHHEADSET), null, true);
                                        return;
                                    }
                                } else if (availabledHighestPriorityDevice.equals(connectedDevice)) {
                                    TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                                    return;
                                } else {
                                    TraeAudioManager.this.InternalConnectDevice(availabledHighestPriorityDevice, null, false);
                                    return;
                                }
                            case TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_PLUGIN_UPDATE /*32786*/:
                                availabledHighestPriorityDevice = (String) hashMap.get(TraeAudioManager.PARAM_DEVICE);
                                if (TraeAudioManager.this.InternalConnectDevice(availabledHighestPriorityDevice, null, false) != 0) {
                                    if (QLog.isColorLevel()) {
                                        QLog.w("TRAE", 0, " plugin dev:" + availabledHighestPriorityDevice + " sessionConnectedDev:" + TraeAudioManager.this.sessionConnectedDev + " connected fail,auto switch!");
                                    }
                                    TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice(), null, false);
                                    return;
                                }
                                return;
                            case TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_PLUGOUT_UPDATE /*32787*/:
                                if (TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this.sessionConnectedDev, null, false) != 0) {
                                    availabledHighestPriorityDevice = (String) hashMap.get(TraeAudioManager.PARAM_DEVICE);
                                    if (QLog.isColorLevel()) {
                                        QLog.w("TRAE", 0, " plugout dev:" + availabledHighestPriorityDevice + " sessionConnectedDev:" + TraeAudioManager.this.sessionConnectedDev + " connected fail,auto switch!");
                                    }
                                    TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice(), null, false);
                                    return;
                                }
                                return;
                            case TraeAudioManagerLooper.MESSAGE_VOICECALL_AUIDOPARAM_CHANGED /*32788*/:
                                Integer num = (Integer) hashMap.get(TraeAudioManager.PARAM_STREAMTYPE);
                                if (num != null) {
                                    TraeAudioManager.this._streamType = num.intValue();
                                    TraeAudioManagerLooper.this.InternalNotifyStreamTypeUpdate(num.intValue());
                                    return;
                                } else if (QLog.isColorLevel()) {
                                    QLog.e("TRAE", 0, " MESSAGE_VOICECALL_AUIDOPARAM_CHANGED params.get(PARAM_STREAMTYPE)==null!!");
                                    return;
                                } else {
                                    return;
                                }
                            case TraeAudioManagerLooper.MESSAGE_REQUEST_RELEASE_AUDIO_FOCUS /*32790*/:
                                TraeAudioManagerLooper.this.abandonAudioFocus();
                                return;
                            case TraeAudioManagerLooper.MESSAGE_RECOVER_AUDIO_FOCUS /*32791*/:
                                TraeAudioManagerLooper.this.requestAudioFocus(TraeAudioManager.this._streamType);
                                return;
                            default:
                                return;
                        }
                    } else {
                        if (QLog.isColorLevel()) {
                            QLog.w("TRAE", 0, "******* disabled ,skip msg******");
                        }
                        TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 1);
                    }
                }
            };
            _init();
            synchronized (this._started) {
                this._started[0] = true;
                this._started.notify();
            }
            Looper.loop();
            _uninit();
            synchronized (this._started) {
                this._started[0] = false;
                this._started.notify();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        void _init() {
            AudioDeviceInterface.LogTraceEntry("");
            try {
                TraeAudioManager.this._audioSessionHost = new TraeAudioSessionHost();
                TraeAudioManager.this._deviceConfigManager = new DeviceConfigManager();
                TraeAudioManager._gHostProcessId = Process.myPid();
                TraeAudioManager.this._am = (AudioManager) TraeAudioManager.this._context.getSystemService("audio");
                TraeAudioManager.this._bluetoothCheck = TraeAudioManager.this.CreateBluetoothCheck(TraeAudioManager.this._context, TraeAudioManager.this._deviceConfigManager);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
                TraeAudioManager.this._bluetoothCheck.addAction(intentFilter);
                intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                TraeAudioManager.this._context.registerReceiver(this._parent, intentFilter);
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "======7");
                }
            }
            AudioDeviceInterface.LogTraceExit();
        }

        void _prev_startService() {
            try {
                TraeAudioManager.this._am = (AudioManager) TraeAudioManager.this._context.getSystemService("audio");
                if (TraeAudioManager.this._bluetoothCheck == null) {
                    TraeAudioManager.this._bluetoothCheck = TraeAudioManager.this.CreateBluetoothCheck(TraeAudioManager.this._context, TraeAudioManager.this._deviceConfigManager);
                }
                TraeAudioManager.this._context.unregisterReceiver(this._parent);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
                TraeAudioManager.this._bluetoothCheck.addAction(intentFilter);
                intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                TraeAudioManager.this._context.registerReceiver(this._parent, intentFilter);
            } catch (Exception e) {
            }
        }

        void _post_stopService() {
            try {
                if (TraeAudioManager.this._bluetoothCheck != null) {
                    TraeAudioManager.this._bluetoothCheck.release();
                }
                TraeAudioManager.this._bluetoothCheck = null;
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.unregisterReceiver(this._parent);
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                    TraeAudioManager.this._context.registerReceiver(this._parent, intentFilter);
                }
            } catch (Exception e) {
            }
        }

        void _uninit() {
            AudioDeviceInterface.LogTraceEntry("");
            try {
                stopService();
                if (TraeAudioManager.this._bluetoothCheck != null) {
                    TraeAudioManager.this._bluetoothCheck.release();
                }
                TraeAudioManager.this._bluetoothCheck = null;
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.unregisterReceiver(this._parent);
                    TraeAudioManager.this._context = null;
                }
                if (TraeAudioManager.this._deviceConfigManager != null) {
                    TraeAudioManager.this._deviceConfigManager.clearConfig();
                }
                TraeAudioManager.this._deviceConfigManager = null;
            } catch (Exception e) {
            }
            AudioDeviceInterface.LogTraceExit();
        }

        int InternalSessionGetDeviceList(HashMap<String, Object> hashMap) {
            Intent intent = new Intent();
            HashMap snapParams = TraeAudioManager.this._deviceConfigManager.getSnapParams();
            ArrayList arrayList = (ArrayList) snapParams.get(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST);
            String str = (String) snapParams.get(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE);
            String str2 = (String) snapParams.get(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST, (String[]) arrayList.toArray(new String[0]));
            intent.putExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE, str);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE, str2);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME, TraeAudioManager.this._deviceConfigManager.getBluetoothName());
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            return 0;
        }

        @TargetApi(8)
        void requestAudioFocus(int i) {
            if (VERSION.SDK_INT > 8 && this.mAudioFocusChangeListener == null) {
                this.mAudioFocusChangeListener = new OnAudioFocusChangeListener() {
                    @TargetApi(8)
                    public void onAudioFocusChange(int i) {
                        if (QLog.isColorLevel()) {
                            QLog.w("TRAE", 0, "focusChange:" + i + " _focusSteamType:" + TraeAudioManagerLooper.this._focusSteamType + " currMode:" + TraeAudioManager.this._am.getMode() + " _activeMode:" + TraeAudioManager.this._activeMode);
                        }
                        if (i != -1 && i != -2 && i != -3 && i == 1) {
                        }
                    }
                };
                if (TraeAudioManager.this._am != null) {
                    int requestAudioFocus = TraeAudioManager.this._am.requestAudioFocus(this.mAudioFocusChangeListener, i, 1);
                    if (requestAudioFocus != 1 && QLog.isColorLevel()) {
                        QLog.e("TRAE", 0, "request audio focus fail. " + requestAudioFocus + " mode:" + TraeAudioManager.this._am.getMode());
                    }
                    this._focusSteamType = i;
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 0, "-------requestAudioFocus _focusSteamType:" + this._focusSteamType);
                    }
                }
            }
        }

        @TargetApi(8)
        void abandonAudioFocus() {
            if (VERSION.SDK_INT > 8 && TraeAudioManager.this._am != null && this.mAudioFocusChangeListener != null) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "-------abandonAudioFocus _focusSteamType:" + this._focusSteamType);
                }
                TraeAudioManager.this._am.abandonAudioFocus(this.mAudioFocusChangeListener);
                this.mAudioFocusChangeListener = null;
            }
        }

        int InternalVoicecallPreprocess(HashMap<String, Object> hashMap) {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode);
            if (hashMap == null) {
                return -1;
            }
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, " InternalVoicecallPreprocess am==null!!");
                }
                return -1;
            } else if (TraeAudioManager.this._activeMode == 1) {
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 2);
                return -1;
            } else {
                this._voiceCallSessionID = ((Long) hashMap.get(TraeAudioManager.PARAM_SESSIONID)).longValue();
                this._voiceCallOperation = (String) hashMap.get(TraeAudioManager.PARAM_OPERATION);
                TraeAudioManager.this._activeMode = 1;
                TraeAudioManager.this._prevMode = TraeAudioManager.this._am.getMode();
                Integer.valueOf(-1);
                Integer.valueOf(0);
                Integer num = (Integer) hashMap.get(TraeAudioManager.PARAM_MODEPOLICY);
                if (num == null) {
                    if (QLog.isColorLevel()) {
                        QLog.e("TRAE", 0, " params.get(PARAM_MODEPOLICY)==null!!");
                    }
                    TraeAudioManager.this._modePolicy = -1;
                } else {
                    TraeAudioManager.this._modePolicy = num.intValue();
                }
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "  _modePolicy:" + TraeAudioManager.this._modePolicy);
                }
                num = (Integer) hashMap.get(TraeAudioManager.PARAM_STREAMTYPE);
                if (num == null) {
                    if (QLog.isColorLevel()) {
                        QLog.e("TRAE", 0, " params.get(PARAM_STREAMTYPE)==null!!");
                    }
                    TraeAudioManager.this._streamType = 0;
                } else {
                    TraeAudioManager.this._streamType = num.intValue();
                }
                if (!TraeAudioManager.isCloseSystemAPM(TraeAudioManager.this._modePolicy) || TraeAudioManager.this._activeMode == 2 || TraeAudioManager.this._deviceConfigManager == null) {
                    TraeAudioManager.this.InternalSetMode(TraeAudioManager.getCallAudioMode(TraeAudioManager.this._modePolicy));
                    requestAudioFocus(TraeAudioManager.this._streamType);
                } else if (TraeAudioManager.this._deviceConfigManager.getConnectedDevice().equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                    TraeAudioManager.this.InternalSetMode(0);
                    requestAudioFocus(3);
                } else {
                    TraeAudioManager.this.InternalSetMode(3);
                    requestAudioFocus(0);
                }
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 0);
                AudioDeviceInterface.LogTraceExit();
                return 0;
            }
        }

        int InternalVoicecallPostprocess(HashMap<String, Object> hashMap) {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode);
            if (TraeAudioManager.this._am == null) {
                if (!QLog.isColorLevel()) {
                    return -1;
                }
                QLog.e("TRAE", 0, " InternalVoicecallPostprocess am==null!!");
                return -1;
            } else if (TraeAudioManager.this._activeMode != 1) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, " not ACTIVE_VOICECALL!!");
                }
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 3);
                return -1;
            } else {
                TraeAudioManager.this._activeMode = 0;
                abandonAudioFocus();
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 0);
                AudioDeviceInterface.LogTraceExit();
                return 0;
            }
        }

        int interruptVoicecallPostprocess() {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode);
            if (TraeAudioManager.this._am == null) {
                if (!QLog.isColorLevel()) {
                    return -1;
                }
                QLog.e("TRAE", 0, " am==null!!");
                return -1;
            } else if (TraeAudioManager.this._activeMode == 1) {
                TraeAudioManager.this._activeMode = 0;
                if (TraeAudioManager.this._prevMode != -1) {
                    TraeAudioManager.this.InternalSetMode(TraeAudioManager.this._prevMode);
                }
                HashMap hashMap = new HashMap();
                hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this._voiceCallSessionID));
                hashMap.put(TraeAudioManager.PARAM_OPERATION, this._voiceCallOperation);
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 6);
                AudioDeviceInterface.LogTraceExit();
                return 0;
            } else if (!QLog.isColorLevel()) {
                return -1;
            } else {
                QLog.e("TRAE", 0, " not ACTIVE_RING!!");
                return -1;
            }
        }

        int InternalStartRing(HashMap<String, Object> hashMap) {
            boolean z = true;
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode);
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, " InternalStartRing am==null!!");
                }
                return -1;
            }
            if (TraeAudioManager.this._activeMode == 2) {
                interruptRing();
            }
            try {
                this._ringSessionID = ((Long) hashMap.get(TraeAudioManager.PARAM_SESSIONID)).longValue();
                this._ringOperation = (String) hashMap.get(TraeAudioManager.PARAM_OPERATION);
                this._ringUserdata = (String) hashMap.get(TraeAudioManager.PARAM_RING_USERDATA_STRING);
                int intValue = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_DATASOURCE)).intValue();
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "  dataSource:" + intValue);
                }
                int intValue2 = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_RSID)).intValue();
                Uri uri = (Uri) hashMap.get(TraeAudioManager.PARAM_RING_URI);
                String str = (String) hashMap.get(TraeAudioManager.PARAM_RING_FILEPATH);
                boolean booleanValue = ((Boolean) hashMap.get(TraeAudioManager.PARAM_RING_LOOP)).booleanValue();
                int intValue3 = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_LOOPCOUNT)).intValue();
                boolean booleanValue2 = ((Boolean) hashMap.get(TraeAudioManager.PARAM_RING_MODE)).booleanValue();
                if (TraeAudioManager.this._activeMode != 1) {
                    TraeAudioManager.this._activeMode = 2;
                }
                Intent intent = new Intent();
                intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this._ringUserdata);
                TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
                this._preRingMode = TraeAudioManager.this._am.getMode();
                TraeMediaPlayer traeMediaPlayer = this._ringPlayer;
                if (TraeAudioManager.this._activeMode != 1) {
                    z = false;
                }
                traeMediaPlayer.playRing(intValue, intValue2, uri, str, booleanValue, intValue3, booleanValue2, z, TraeAudioManager.this._streamType);
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, " _ringUserdata:" + this._ringUserdata + " DurationMS:" + this._ringPlayer.getDuration());
                }
                if (!this._ringPlayer.hasCall()) {
                    requestAudioFocus(this._ringPlayer.getStreamType());
                }
                InternalNotifyStreamTypeUpdate(this._ringPlayer.getStreamType());
                AudioDeviceInterface.LogTraceExit();
                return 0;
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, " startRing err params");
                }
                return -1;
            }
        }

        int InternalStopRing(HashMap<String, Object> hashMap) {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode + " _preRingMode:" + this._preRingMode);
            if (TraeAudioManager.this._am == null || this._ringPlayer == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, " InternalStopRing am==null!!");
                }
                return -1;
            }
            this._ringPlayer.stopRing();
            if (!this._ringPlayer.hasCall() && TraeAudioManager.this._activeMode == 2) {
                abandonAudioFocus();
                TraeAudioManager.this._activeMode = 0;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this._ringUserdata);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        int InternalGetStreamType(HashMap<String, Object> hashMap) {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode + " _preRingMode:" + this._preRingMode);
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, " InternalStopRing am==null!!");
                }
                return -1;
            }
            int streamType;
            if (TraeAudioManager.this._activeMode == 2) {
                streamType = this._ringPlayer.getStreamType();
            } else {
                streamType = TraeAudioManager.this._streamType;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, streamType);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        int InternalNotifyStreamTypeUpdate(int i) {
            if (TraeAudioManager.this._context == null) {
                return -1;
            }
            Intent intent = new Intent();
            intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
            intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_STREAMTYPE_UPDATE);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, i);
            TraeAudioManager.this._context.sendBroadcast(intent);
            return 0;
        }

        int interruptRing() {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode + " _preRingMode:" + this._preRingMode);
            if (TraeAudioManager.this._am == null) {
                if (!QLog.isColorLevel()) {
                    return -1;
                }
                QLog.e("TRAE", 0, " interruptRing am==null!!");
                return -1;
            } else if (TraeAudioManager.this._activeMode == 2) {
                this._ringPlayer.stopRing();
                abandonAudioFocus();
                TraeAudioManager.this._activeMode = 0;
                HashMap hashMap = new HashMap();
                hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this._ringSessionID));
                hashMap.put(TraeAudioManager.PARAM_OPERATION, this._ringOperation);
                Intent intent = new Intent();
                intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this._ringUserdata);
                TraeAudioManager.this.sendResBroadcast(intent, hashMap, 4);
                AudioDeviceInterface.LogTraceExit();
                return 0;
            } else if (!QLog.isColorLevel()) {
                return -1;
            } else {
                QLog.e("TRAE", 0, " not ACTIVE_RING!!");
                return -1;
            }
        }

        void notifyRingCompletion() {
            HashMap hashMap = new HashMap();
            hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this._ringSessionID));
            hashMap.put(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_RING_COMPLETION);
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this._ringUserdata);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
        }
    }

    abstract class switchThread extends Thread {
        boolean[] _exited = new boolean[]{false};
        HashMap<String, Object> _params = null;
        boolean _running = true;
        long _usingtime = 0;

        public abstract void _quit();

        public abstract void _run();

        public abstract String getDeviceName();

        switchThread() {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, " ++switchThread:" + getDeviceName());
            }
        }

        public void setDeviceConnectParam(HashMap<String, Object> hashMap) {
            this._params = hashMap;
        }

        void updateStatus() {
            TraeAudioManager.this._deviceConfigManager.setConnected(getDeviceName());
            processDeviceConnectRes(0);
        }

        void processDeviceConnectRes(int i) {
            TraeAudioManager.this.InternalNotifyDeviceChangableUpdate();
            AudioDeviceInterface.LogTraceEntry(getDeviceName() + " err:" + i);
            if (this._params == null) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                return;
            }
            TraeAudioManager.this.sessionConnectedDev = TraeAudioManager.this._deviceConfigManager.getConnectedDevice();
            Long l = (Long) this._params.get(TraeAudioManager.PARAM_SESSIONID);
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, " sessonID:" + l);
            }
            if (l == null || l.longValue() == Long.MIN_VALUE) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "processDeviceConnectRes sid null,don't send res");
                    return;
                }
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.CONNECTDEVICE_RESULT_DEVICENAME, (String) this._params.get(TraeAudioManager.PARAM_DEVICE));
            if (TraeAudioManager.this.sendResBroadcast(intent, this._params, i) == 0) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        public void run() {
            AudioDeviceInterface.LogTraceEntry(getDeviceName());
            TraeAudioManager.this._deviceConfigManager.setConnecting(getDeviceName());
            TraeAudioManager.this.InternalNotifyDeviceChangableUpdate();
            _run();
            synchronized (this._exited) {
                this._exited[0] = true;
                this._exited.notify();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        public void quit() {
            AudioDeviceInterface.LogTraceEntry(getDeviceName());
            this._running = false;
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, " quit:" + getDeviceName() + " _running:" + this._running);
            }
            interrupt();
            _quit();
            synchronized (this._exited) {
                if (!this._exited[0]) {
                    try {
                        this._exited.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            AudioDeviceInterface.LogTraceExit();
        }
    }

    class bluetoothHeadsetSwitchThread extends switchThread {
        bluetoothHeadsetSwitchThread() {
            super();
        }

        public void _run() {
            if (TraeAudioManager.IsMusicScene || !TraeAudioManager.IsUpdateSceneFlag) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "connect bluetoothHeadset: do nothing, IsMusicScene:" + TraeAudioManager.IsMusicScene + " ,IsUpdateSceneFlag:" + TraeAudioManager.IsUpdateSceneFlag);
                }
                updateStatus();
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            _startBluetoothSco();
            int i = 0;
            while (this._running) {
                int i2 = i + 1;
                if (i >= 10) {
                    break;
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "bluetoothHeadsetSwitchThread i:" + i2 + " sco:" + (TraeAudioManager.this._am.isBluetoothScoOn() ? "Y" : "N") + " :" + TraeAudioManager.this._deviceConfigManager.getBluetoothName());
                }
                if (TraeAudioManager.this._am.isBluetoothScoOn()) {
                    updateStatus();
                    break;
                }
                try {
                    Thread.sleep(1000);
                    i = i2;
                } catch (InterruptedException e2) {
                    i = i2;
                }
            }
            if (!TraeAudioManager.this._am.isBluetoothScoOn()) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 0, "bluetoothHeadsetSwitchThread sco fail,remove btheadset");
                }
                TraeAudioManager.this._deviceConfigManager.setVisible(getDeviceName(), false);
                processDeviceConnectRes(10);
                TraeAudioManager.this.checkAutoDeviceListUpdate();
            }
        }

        public String getDeviceName() {
            return TraeAudioManager.DEVICE_BLUETOOTHHEADSET;
        }

        @TargetApi(8)
        public void _quit() {
            if (TraeAudioManager.this._am != null) {
                _stopBluetoothSco();
            }
        }

        @TargetApi(8)
        void _startBluetoothSco() {
            TraeAudioManager.this._am.setBluetoothScoOn(true);
            if (VERSION.SDK_INT > 8) {
                TraeAudioManager.this._am.startBluetoothSco();
            }
        }

        @TargetApi(8)
        void _stopBluetoothSco() {
            if (VERSION.SDK_INT > 8) {
                TraeAudioManager.this._am.stopBluetoothSco();
            }
            TraeAudioManager.this._am.setBluetoothScoOn(false);
        }
    }

    class earphoneSwitchThread extends switchThread {
        earphoneSwitchThread() {
            super();
        }

        public void _run() {
            if (TraeAudioManager.IsUpdateSceneFlag) {
                TraeAudioManager.this.InternalSetSpeaker(TraeAudioManager.this._context, false);
            }
            updateStatus();
            if (TraeAudioManager.IsUpdateSceneFlag) {
                int i = 0;
                while (this._running) {
                    if (TraeAudioManager.this._am.isSpeakerphoneOn()) {
                        TraeAudioManager.this.InternalSetSpeaker(TraeAudioManager.this._context, false);
                    }
                    try {
                        Thread.sleep(i < 5 ? 1000 : 4000);
                    } catch (InterruptedException e) {
                    }
                    i++;
                }
            } else if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "connect earphone: do nothing");
            }
        }

        public String getDeviceName() {
            return TraeAudioManager.DEVICE_EARPHONE;
        }

        public void _quit() {
        }
    }

    class headsetSwitchThread extends switchThread {
        headsetSwitchThread() {
            super();
        }

        public void _run() {
            if (!TraeAudioManager.IsMusicScene && TraeAudioManager.IsUpdateSceneFlag) {
                TraeAudioManager.this.InternalSetSpeaker(TraeAudioManager.this._context, false);
                TraeAudioManager.this._am.setWiredHeadsetOn(true);
            }
            updateStatus();
            if (!TraeAudioManager.IsMusicScene && TraeAudioManager.IsUpdateSceneFlag) {
                int i = 0;
                while (this._running) {
                    if (TraeAudioManager.this._am.isSpeakerphoneOn()) {
                        TraeAudioManager.this.InternalSetSpeaker(TraeAudioManager.this._context, false);
                    }
                    try {
                        Thread.sleep(i < 5 ? 1000 : 4000);
                    } catch (InterruptedException e) {
                    }
                    i++;
                }
            } else if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "connect headset: do nothing");
            }
        }

        public String getDeviceName() {
            return TraeAudioManager.DEVICE_WIREDHEADSET;
        }

        public void _quit() {
        }
    }

    class speakerSwitchThread extends switchThread {
        speakerSwitchThread() {
            super();
        }

        public void _run() {
            int i = 0;
            if (!TraeAudioManager.IsMusicScene && TraeAudioManager.IsUpdateSceneFlag) {
                TraeAudioManager.this.InternalSetSpeaker(TraeAudioManager.this._context, true);
            }
            updateStatus();
            if (!TraeAudioManager.IsMusicScene && TraeAudioManager.IsUpdateSceneFlag) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, " _run:" + getDeviceName() + " _running:" + this._running);
                }
                while (this._running) {
                    if (!TraeAudioManager.this._am.isSpeakerphoneOn()) {
                        TraeAudioManager.this.InternalSetSpeaker(TraeAudioManager.this._context, true);
                    }
                    try {
                        Thread.sleep(i < 5 ? 1000 : 4000);
                    } catch (InterruptedException e) {
                    }
                    i++;
                }
            } else if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "connect speakerPhone: do nothing");
            }
        }

        public String getDeviceName() {
            return TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        public void _quit() {
        }
    }

    public static boolean checkDevName(String str) {
        if (str == null) {
            return false;
        }
        if (DEVICE_SPEAKERPHONE.equals(str) || DEVICE_EARPHONE.equals(str) || DEVICE_WIREDHEADSET.equals(str) || DEVICE_BLUETOOTHHEADSET.equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isHandfree(String str) {
        if (checkDevName(str) && DEVICE_SPEAKERPHONE.equals(str)) {
            return true;
        }
        return false;
    }

    void printDevices() {
        AudioDeviceInterface.LogTraceEntry("");
        int deviceNumber = this._deviceConfigManager.getDeviceNumber();
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "   ConnectedDevice:" + this._deviceConfigManager.getConnectedDevice());
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "   ConnectingDevice:" + this._deviceConfigManager.getConnectingDevice());
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "   prevConnectedDevice:" + this._deviceConfigManager.getPrevConnectedDevice());
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "   AHPDevice:" + this._deviceConfigManager.getAvailabledHighestPriorityDevice());
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "   deviceNamber:" + deviceNumber);
        }
        for (int i = 0; i < deviceNumber; i++) {
            String deviceName = this._deviceConfigManager.getDeviceName(i);
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "      " + i + " devName:" + deviceName + " Visible:" + this._deviceConfigManager.getVisible(deviceName) + " Priority:" + this._deviceConfigManager.getPriority(deviceName));
            }
        }
        String[] strArr = (String[]) this._deviceConfigManager.getAvailableDeviceList().toArray(new String[0]);
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "   AvailableNamber:" + strArr.length);
        }
        for (deviceNumber = 0; deviceNumber < strArr.length; deviceNumber++) {
            deviceName = strArr[deviceNumber];
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "      " + deviceNumber + " devName:" + deviceName + " Visible:" + this._deviceConfigManager.getVisible(deviceName) + " Priority:" + this._deviceConfigManager.getPriority(deviceName));
            }
        }
        AudioDeviceInterface.LogTraceExit();
    }

    static boolean isCloseSystemAPM(int i) {
        if (i != -1) {
            return false;
        }
        if (Build.MANUFACTURER.equals("Xiaomi")) {
            if (Build.MODEL.equals("MI 2")) {
                return true;
            }
            if (Build.MODEL.equals("MI 2A")) {
                return true;
            }
            if (Build.MODEL.equals("MI 2S")) {
                return true;
            }
            if (Build.MODEL.equals("MI 2SC")) {
                return true;
            }
            return false;
        } else if (Build.MANUFACTURER.equals(DLConstants.BRAND_SAMSUNG) && Build.MODEL.equals("SCH-I959")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean IsEabiLowVersionByAbi(String str) {
        if (str == null) {
            return true;
        }
        if (str.contains(DLConstants.CPU_X86)) {
            return false;
        }
        if (str.contains(DLConstants.CPU_MIPS)) {
            return false;
        }
        if (str.equalsIgnoreCase(DLConstants.CPU_ARMEABI) || !str.equalsIgnoreCase("armeabi-v7a")) {
            return true;
        }
        return false;
    }

    static boolean IsEabiLowVersion() {
        String str = Build.CPU_ABI;
        String str2 = ConfigBaseParser.DEFAULT_VALUE;
        if (VERSION.SDK_INT >= 8) {
            try {
                str2 = (String) Build.class.getDeclaredField("CPU_ABI2").get(null);
            } catch (Exception e) {
                if (IsEabiLowVersionByAbi(str)) {
                    return true;
                }
                return false;
            }
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "IsEabiVersion CPU_ABI:" + str + " CPU_ABI2:" + str2);
        }
        if (IsEabiLowVersionByAbi(str) && IsEabiLowVersionByAbi(str2)) {
            return true;
        }
        return false;
    }

    static int getAudioSource(int i) {
        if (IsMusicScene) {
            return 0;
        }
        if (!IsEabiLowVersion()) {
            int i2 = VERSION.SDK_INT;
            if (i >= 0) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "[Config] getAudioSource _audioSourcePolicy:" + i + " source:" + i);
                }
                return i;
            }
            if (i2 >= 11) {
                i2 = 7;
            } else {
                i2 = 0;
            }
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "[Config] getAudioSource _audioSourcePolicy:" + i + " source:" + i2);
            }
            return i2;
        } else if (!QLog.isColorLevel()) {
            return 0;
        } else {
            QLog.w("TRAE", 0, "[Config] armeabi low Version, getAudioSource _audioSourcePolicy:" + i + " source:" + 0);
            return 0;
        }
    }

    static int getAudioStreamType(int i) {
        int i2 = 3;
        if (!IsMusicScene) {
            if (!IsEabiLowVersion()) {
                int i3 = VERSION.SDK_INT;
                if (i >= 0) {
                    i2 = i;
                } else if (i3 >= 9) {
                    i2 = 0;
                }
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "[Config] getAudioStreamType audioStreamTypePolicy:" + i + " streamType:" + i2);
                }
            } else if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "[Config] armeabi low Version, getAudioStreamType audioStreamTypePolicy:" + i + " streamType:" + 3);
            }
        }
        return i2;
    }

    static int getCallAudioMode(int i) {
        if (IsMusicScene) {
            return 0;
        }
        if (!IsEabiLowVersion()) {
            int i2 = VERSION.SDK_INT;
            if (i >= 0) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "[Config] getCallAudioMode modePolicy:" + i + " mode:" + i);
                }
                return i;
            }
            if (i2 >= 11) {
                i2 = 3;
            } else {
                i2 = 0;
            }
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "[Config] getCallAudioMode _modePolicy:" + i + " mode:" + i2 + "facturer:" + Build.MANUFACTURER + " model:" + Build.MODEL);
            }
            return i2;
        } else if (!QLog.isColorLevel()) {
            return 0;
        } else {
            QLog.w("TRAE", 0, "[Config] armeabi low Version, getCallAudioMode modePolicy:" + i + " mode:" + 0);
            return 0;
        }
    }

    void updateDeviceStatus() {
        int deviceNumber = this._deviceConfigManager.getDeviceNumber();
        for (int i = 0; i < deviceNumber; i++) {
            boolean visible;
            String deviceName = this._deviceConfigManager.getDeviceName(i);
            if (deviceName != null) {
                if (deviceName.equals(DEVICE_BLUETOOTHHEADSET)) {
                    if (this._bluetoothCheck == null) {
                        visible = this._deviceConfigManager.setVisible(deviceName, false);
                    } else {
                        visible = this._deviceConfigManager.setVisible(deviceName, this._bluetoothCheck.isConnected());
                    }
                } else if (deviceName.equals(DEVICE_WIREDHEADSET)) {
                    visible = this._deviceConfigManager.setVisible(deviceName, this._am.isWiredHeadsetOn());
                } else if (deviceName.equals(DEVICE_SPEAKERPHONE)) {
                    this._deviceConfigManager.setVisible(deviceName, true);
                }
                if (visible && QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "pollUpdateDevice dev:" + deviceName + " Visible:" + this._deviceConfigManager.getVisible(deviceName));
                }
            }
            visible = false;
            QLog.w("TRAE", 0, "pollUpdateDevice dev:" + deviceName + " Visible:" + this._deviceConfigManager.getVisible(deviceName));
        }
        checkAutoDeviceListUpdate();
    }

    void _updateEarphoneVisable() {
        if (this._deviceConfigManager.getVisible(DEVICE_WIREDHEADSET)) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, " detected headset plugin,so disable earphone");
            }
            this._deviceConfigManager.setVisible(DEVICE_EARPHONE, false);
            return;
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, " detected headset plugout,so enable earphone");
        }
        this._deviceConfigManager.setVisible(DEVICE_EARPHONE, true);
    }

    void checkAutoDeviceListUpdate() {
        if (this._deviceConfigManager.getVisiableUpdateFlag()) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "checkAutoDeviceListUpdate got update!");
            }
            _updateEarphoneVisable();
            this._deviceConfigManager.resetVisiableUpdateFlag();
            internalSendMessage(TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_UPDATE, new HashMap());
        }
    }

    void checkDevicePlug(String str, boolean z) {
        if (this._deviceConfigManager.getVisiableUpdateFlag()) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "checkDevicePlug got update dev:" + str + (z ? " piugin" : " plugout") + " connectedDev:" + this._deviceConfigManager.getConnectedDevice());
            }
            _updateEarphoneVisable();
            this._deviceConfigManager.resetVisiableUpdateFlag();
            HashMap hashMap;
            if (z) {
                hashMap = new HashMap();
                hashMap.put(PARAM_DEVICE, str);
                internalSendMessage(TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_PLUGIN_UPDATE, hashMap);
                return;
            }
            String connectedDevice = this._deviceConfigManager.getConnectedDevice();
            if (connectedDevice.equals(str) || connectedDevice.equals(DEVICE_NONE)) {
                hashMap = new HashMap();
                hashMap.put(PARAM_DEVICE, str);
                internalSendMessage(TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_PLUGOUT_UPDATE, hashMap);
                return;
            }
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, " ---No switch,plugout:" + str + " connectedDev:" + connectedDevice);
            }
            internalSendMessage(TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_UPDATE, new HashMap());
        }
    }

    public static int SetSpeakerForTest(Context context, boolean z) {
        int i = -1;
        _glock.lock();
        if (_ginstance != null) {
            i = _ginstance.InternalSetSpeaker(context, z);
        } else if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "TraeAudioManager|static SetSpeakerForTest|null == _ginstance");
        }
        _glock.unlock();
        return i;
    }

    int InternalSetSpeaker(Context context, boolean z) {
        if (context != null) {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (audioManager != null) {
                if (QLog.isColorLevel()) {
                    String str;
                    String str2 = "TRAE";
                    StringBuilder append = new StringBuilder().append("InternalSetSpeaker entry:speaker:");
                    if (audioManager.isSpeakerphoneOn()) {
                        str = "Y";
                    } else {
                        str = "N";
                    }
                    append = append.append(str).append("-->:");
                    if (z) {
                        str = "Y";
                    } else {
                        str = "N";
                    }
                    QLog.w(str2, 0, append.append(str).toString());
                }
                if (isCloseSystemAPM(this._modePolicy) && this._activeMode != 2) {
                    return InternalSetSpeakerSpe(audioManager, z);
                }
                if (audioManager.isSpeakerphoneOn() != z) {
                    audioManager.setSpeakerphoneOn(z);
                }
                int i = audioManager.isSpeakerphoneOn() == z ? 0 : -1;
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "InternalSetSpeaker exit:" + z + " res:" + i + " mode:" + audioManager.getMode());
                }
                return i;
            } else if (!QLog.isColorLevel()) {
                return -1;
            } else {
                QLog.e("TRAE", 0, "Could not InternalSetSpeaker - no audio manager");
                return -1;
            }
        } else if (!QLog.isColorLevel()) {
            return -1;
        } else {
            QLog.e("TRAE", 0, "Could not InternalSetSpeaker - no context");
            return -1;
        }
    }

    int InternalSetSpeakerSpe(AudioManager audioManager, boolean z) {
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "InternalSetSpeakerSpe fac:" + Build.MANUFACTURER + " model:" + Build.MODEL + " st:" + this._streamType + " media_force_use:" + getForceUse(1));
        }
        if (z) {
            InternalSetMode(0);
            audioManager.setSpeakerphoneOn(true);
            setForceUse(1, 1);
        } else {
            InternalSetMode(3);
            audioManager.setSpeakerphoneOn(false);
            setForceUse(1, 0);
        }
        int i = audioManager.isSpeakerphoneOn() == z ? 0 : -1;
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "InternalSetSpeakerSpe exit:" + z + " res:" + i + " mode:" + audioManager.getMode());
        }
        return i;
    }

    void InternalSetMode(int i) {
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "SetMode entry:" + i);
        }
        if (this._am != null) {
            this._am.setMode(i);
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "setMode:" + i + (this._am.getMode() != i ? "fail" : "success"));
            }
        } else if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "setMode:" + i + " fail am=null");
        }
    }

    public static int registerAudioSession(boolean z, long j, Context context) {
        int i = -1;
        _glock.lock();
        if (_ginstance != null) {
            if (z) {
                _ginstance._audioSessionHost.add(j, context);
            } else {
                _ginstance._audioSessionHost.remove(j);
            }
            i = 0;
        }
        _glock.unlock();
        return i;
    }

    public static int sendMessage(int i, HashMap<String, Object> hashMap) {
        int i2 = -1;
        _glock.lock();
        if (_ginstance != null) {
            i2 = _ginstance.internalSendMessage(i, hashMap);
        }
        _glock.unlock();
        return i2;
    }

    public static int init(Context context) {
        Log.w("TRAE", "TraeAudioManager init _ginstance:" + _ginstance);
        AudioDeviceInterface.LogTraceEntry(" _ginstance:" + _ginstance);
        _glock.lock();
        if (_ginstance == null) {
            _ginstance = new TraeAudioManager(context);
        }
        _glock.unlock();
        AudioDeviceInterface.LogTraceExit();
        return 0;
    }

    public static void uninit() {
        Log.w("TRAE", "TraeAudioManager uninit _ginstance:" + _ginstance);
        AudioDeviceInterface.LogTraceEntry(" _ginstance:" + _ginstance);
        _glock.lock();
        if (_ginstance != null) {
            _ginstance.release();
            _ginstance = null;
        }
        _glock.unlock();
        AudioDeviceInterface.LogTraceExit();
    }

    TraeAudioManager(Context context) {
        AudioDeviceInterface.LogTraceEntry(" context:" + context);
        if (context != null) {
            this._context = context;
            this.mTraeAudioManagerLooper = new TraeAudioManagerLooper(this);
            if (this.mTraeAudioManagerLooper != null) {
                AudioDeviceInterface.LogTraceExit();
            } else {
                AudioDeviceInterface.LogTraceExit();
            }
        }
    }

    public void release() {
        AudioDeviceInterface.LogTraceEntry("");
        if (this.mTraeAudioManagerLooper != null) {
            this.mTraeAudioManagerLooper.quit();
            this.mTraeAudioManagerLooper = null;
        }
        AudioDeviceInterface.LogTraceExit();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            try {
                String action = intent.getAction();
                String stringExtra = intent.getStringExtra(PARAM_OPERATION);
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 0, "TraeAudioManager|onReceive::Action:" + intent.getAction());
                }
                if (this._deviceConfigManager != null) {
                    boolean visible = this._deviceConfigManager.getVisible(DEVICE_WIREDHEADSET);
                    boolean visible2 = this._deviceConfigManager.getVisible(DEVICE_BLUETOOTHHEADSET);
                    if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                        onHeadsetPlug(context, intent);
                        if (!visible && this._deviceConfigManager.getVisible(DEVICE_WIREDHEADSET)) {
                            checkDevicePlug(DEVICE_WIREDHEADSET, true);
                        }
                        if (visible && !this._deviceConfigManager.getVisible(DEVICE_WIREDHEADSET)) {
                            checkDevicePlug(DEVICE_WIREDHEADSET, false);
                        }
                    } else if (!"android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                        if (ACTION_TRAEAUDIOMANAGER_REQUEST.equals(action)) {
                            if (QLog.isColorLevel()) {
                                QLog.w("TRAE", 0, "   OPERATION:" + stringExtra);
                            }
                            if (OPERATION_REGISTERAUDIOSESSION.equals(stringExtra)) {
                                registerAudioSession(intent.getBooleanExtra(REGISTERAUDIOSESSION_ISREGISTER, false), intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), context);
                            } else if (OPERATION_STARTSERVICE.equals(stringExtra)) {
                                startService(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getStringExtra(EXTRA_DATA_DEVICECONFIG));
                            } else if (OPERATION_STOPSERVICE.equals(stringExtra)) {
                                stopService(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            } else if (OPERATION_GETDEVICELIST.equals(stringExtra)) {
                                getDeviceList(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            } else if (OPERATION_GETSTREAMTYPE.equals(stringExtra)) {
                                getStreamType(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            } else if (OPERATION_CONNECTDEVICE.equals(stringExtra)) {
                                connectDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getStringExtra(CONNECTDEVICE_DEVICENAME));
                            } else if (OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE.equals(stringExtra)) {
                                connectHighestPriorityDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            } else if (OPERATION_EARACTION.equals(stringExtra)) {
                                earAction(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getIntExtra(EXTRA_EARACTION, -1));
                            } else if (OPERATION_ISDEVICECHANGABLED.equals(stringExtra)) {
                                isDeviceChangabled(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            } else if (OPERATION_GETCONNECTEDDEVICE.equals(stringExtra)) {
                                getConnectedDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            } else if (OPERATION_GETCONNECTINGDEVICE.equals(stringExtra)) {
                                getConnectingDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            } else if (OPERATION_VOICECALL_PREPROCESS.equals(stringExtra)) {
                                voicecallPreprocess(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getIntExtra(PARAM_MODEPOLICY, -1), intent.getIntExtra(PARAM_STREAMTYPE, -1));
                            } else if (OPERATION_VOICECALL_POSTPROCESS.equals(stringExtra)) {
                                voicecallPostprocess(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            } else if (OPERATION_VOICECALL_AUDIOPARAM_CHANGED.equals(stringExtra)) {
                                voiceCallAudioParamChanged(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getIntExtra(PARAM_MODEPOLICY, -1), intent.getIntExtra(PARAM_STREAMTYPE, -1));
                            } else if (OPERATION_STARTRING.equals(stringExtra)) {
                                int intExtra = intent.getIntExtra(PARAM_RING_DATASOURCE, -1);
                                int intExtra2 = intent.getIntExtra(PARAM_RING_RSID, -1);
                                Uri uri = (Uri) intent.getParcelableExtra(PARAM_RING_URI);
                                String stringExtra2 = intent.getStringExtra(PARAM_RING_FILEPATH);
                                boolean booleanExtra = intent.getBooleanExtra(PARAM_RING_LOOP, false);
                                String stringExtra3 = intent.getStringExtra(PARAM_RING_USERDATA_STRING);
                                startRing(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intExtra, intExtra2, uri, stringExtra2, booleanExtra, intent.getIntExtra(PARAM_RING_LOOPCOUNT, 1), stringExtra3, intent.getBooleanExtra(PARAM_RING_MODE, false));
                            } else if (OPERATION_STOPRING.equals(stringExtra)) {
                                stopRing(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                            }
                        } else if (this._deviceConfigManager != null) {
                            if (this._bluetoothCheck != null) {
                                this._bluetoothCheck.onReceive(context, intent, this._deviceConfigManager);
                            }
                            if (!visible2 && this._deviceConfigManager.getVisible(DEVICE_BLUETOOTHHEADSET)) {
                                checkDevicePlug(DEVICE_BLUETOOTHHEADSET, true);
                            }
                            if (visible2 && !this._deviceConfigManager.getVisible(DEVICE_BLUETOOTHHEADSET)) {
                                checkDevicePlug(DEVICE_BLUETOOTHHEADSET, false);
                            }
                        }
                    }
                } else if (QLog.isColorLevel()) {
                    QLog.d("TRAE", 0, "_deviceConfigManager null!");
                }
            } catch (Exception e) {
            }
        } else if (QLog.isColorLevel()) {
            QLog.d("TRAE", 0, "onReceive intent or context is null!");
        }
    }

    void onHeadsetPlug(Context context, Intent intent) {
        boolean z;
        String str = "";
        String stringExtra = intent.getStringExtra("name");
        if (stringExtra == null) {
            stringExtra = "unkonw";
        }
        stringExtra = str + " [" + stringExtra + "] ";
        int intExtra = intent.getIntExtra("state", -1);
        if (intExtra != -1) {
            stringExtra = stringExtra + (intExtra == 0 ? "unplugged" : "plugged");
        }
        stringExtra = stringExtra + " mic:";
        int intExtra2 = intent.getIntExtra("microphone", -1);
        if (intExtra2 != -1) {
            stringExtra = stringExtra + (intExtra2 == 1 ? "Y" : "unkown");
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "onHeadsetPlug:: " + stringExtra);
        }
        DeviceConfigManager deviceConfigManager = this._deviceConfigManager;
        String str2 = DEVICE_WIREDHEADSET;
        if (1 == intExtra) {
            z = true;
        } else {
            z = false;
        }
        deviceConfigManager.setVisible(str2, z);
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "onHeadsetPlug exit");
        }
    }

    int internalSendMessage(int i, HashMap<String, Object> hashMap) {
        if (this.mTraeAudioManagerLooper != null) {
            return this.mTraeAudioManagerLooper.sendMessage(i, hashMap);
        }
        return -1;
    }

    static int getDeviceList(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_GETDEVICELIST, hashMap);
    }

    static int getStreamType(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_GETSTREAMTYPE, hashMap);
    }

    static int startService(String str, long j, boolean z, String str2) {
        if (str2.length() <= 0) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(EXTRA_DATA_DEVICECONFIG, str2);
        return sendMessage(TraeAudioManagerLooper.MESSAGE_ENABLE, hashMap);
    }

    static int stopService(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_DISABLE, hashMap);
    }

    static int connectDevice(String str, long j, boolean z, String str2) {
        if (str2 == null) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(CONNECTDEVICE_DEVICENAME, str2);
        hashMap.put(PARAM_DEVICE, str2);
        return sendMessage(TraeAudioManagerLooper.MESSAGE_CONNECTDEVICE, hashMap);
    }

    static int connectHighestPriorityDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_CONNECT_HIGHEST_PRIORITY_DEVICE, hashMap);
    }

    static int earAction(String str, long j, boolean z, int i) {
        if (i != 0 && i != 1) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(EXTRA_EARACTION, Integer.valueOf(i));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_EARACTION, hashMap);
    }

    static int isDeviceChangabled(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_ISDEVICECHANGABLED, hashMap);
    }

    static int getConnectedDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_GETCONNECTEDDEVICE, hashMap);
    }

    static int getConnectingDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_GETCONNECTINGDEVICE, hashMap);
    }

    static int voicecallPreprocess(String str, long j, boolean z, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_MODEPOLICY, Integer.valueOf(i));
        hashMap.put(PARAM_STREAMTYPE, Integer.valueOf(i2));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_VOICECALLPREPROCESS, hashMap);
    }

    static int voicecallPostprocess(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_VOICECALLPOSTPROCESS, hashMap);
    }

    static int voiceCallAudioParamChanged(String str, long j, boolean z, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_MODEPOLICY, Integer.valueOf(i));
        hashMap.put(PARAM_STREAMTYPE, Integer.valueOf(i2));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_VOICECALL_AUIDOPARAM_CHANGED, hashMap);
    }

    static int startRing(String str, long j, boolean z, int i, int i2, Uri uri, String str2, boolean z2, int i3, String str3, boolean z3) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_RING_DATASOURCE, Integer.valueOf(i));
        hashMap.put(PARAM_RING_RSID, Integer.valueOf(i2));
        hashMap.put(PARAM_RING_URI, uri);
        hashMap.put(PARAM_RING_FILEPATH, str2);
        hashMap.put(PARAM_RING_LOOP, Boolean.valueOf(z2));
        hashMap.put(PARAM_RING_LOOPCOUNT, Integer.valueOf(i3));
        hashMap.put(PARAM_RING_MODE, Boolean.valueOf(z3));
        hashMap.put(PARAM_RING_USERDATA_STRING, str3);
        return sendMessage(TraeAudioManagerLooper.MESSAGE_STARTRING, hashMap);
    }

    static int stopRing(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_STOPRING, hashMap);
    }

    static int requestReleaseAudioFocus(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_REQUEST_RELEASE_AUDIO_FOCUS, hashMap);
    }

    static int recoverAudioFocus(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_RECOVER_AUDIO_FOCUS, hashMap);
    }

    int InternalSessionConnectDevice(HashMap<String, Object> hashMap) {
        AudioDeviceInterface.LogTraceEntry("");
        if (hashMap == null || this._context == null) {
            return -1;
        }
        if (IsMusicScene) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "MusicScene: InternalSessionConnectDevice failed");
            }
            return -1;
        }
        int i;
        String str = "unkown";
        str = (String) hashMap.get(PARAM_DEVICE);
        Log.w("TRAE", "ConnectDevice: " + str);
        boolean InternalIsDeviceChangeable = InternalIsDeviceChangeable();
        if (!checkDevName(str)) {
            i = 7;
        } else if (!this._deviceConfigManager.getVisible(str)) {
            i = 8;
        } else if (InternalIsDeviceChangeable) {
            i = 0;
        } else {
            i = 9;
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "sessonID:" + ((Long) hashMap.get(PARAM_SESSIONID)) + " devName:" + str + " bChangabled:" + (InternalIsDeviceChangeable ? "Y" : "N") + " err:" + i);
        }
        Intent intent;
        if (i != 0) {
            intent = new Intent();
            intent.putExtra(CONNECTDEVICE_RESULT_DEVICENAME, (String) hashMap.get(PARAM_DEVICE));
            sendResBroadcast(intent, hashMap, i);
            return -1;
        } else if (str.equals(this._deviceConfigManager.getConnectedDevice())) {
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 0, " --has connected!");
            }
            intent = new Intent();
            intent.putExtra(CONNECTDEVICE_RESULT_DEVICENAME, (String) hashMap.get(PARAM_DEVICE));
            sendResBroadcast(intent, hashMap, i);
            return 0;
        } else {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, " --connecting...");
            }
            InternalConnectDevice(str, hashMap, false);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }
    }

    int InternalSessionEarAction(HashMap<String, Object> hashMap) {
        return 0;
    }

    int InternalConnectDevice(String str, HashMap<String, Object> hashMap, boolean z) {
        AudioDeviceInterface.LogTraceEntry(" devName:" + str);
        if (str == null) {
            return -1;
        }
        if (IsMusicScene && str.equals(DEVICE_EARPHONE)) {
            if (!QLog.isColorLevel()) {
                return -1;
            }
            QLog.e("TRAE", 0, "MusicScene, Connect device:" + str + " failed");
            return -1;
        } else if (!z && !this._deviceConfigManager.getConnectedDevice().equals(DEVICE_NONE) && str.equals(this._deviceConfigManager.getConnectedDevice())) {
            return 0;
        } else {
            if (checkDevName(str) && this._deviceConfigManager.getVisible(str)) {
                if (InternalIsDeviceChangeable()) {
                    if (this._switchThread != null) {
                        if (QLog.isColorLevel()) {
                            QLog.w("TRAE", 0, "_switchThread:" + this._switchThread.getDeviceName());
                        }
                        this._switchThread.quit();
                        this._switchThread = null;
                    }
                    if (str.equals(DEVICE_EARPHONE)) {
                        this._switchThread = new earphoneSwitchThread();
                    } else if (str.equals(DEVICE_SPEAKERPHONE)) {
                        this._switchThread = new speakerSwitchThread();
                    } else if (str.equals(DEVICE_WIREDHEADSET)) {
                        this._switchThread = new headsetSwitchThread();
                    } else if (str.equals(DEVICE_BLUETOOTHHEADSET)) {
                        this._switchThread = new bluetoothHeadsetSwitchThread();
                    }
                    if (this._switchThread != null) {
                        this._switchThread.setDeviceConnectParam(hashMap);
                        this._switchThread.start();
                    }
                    AudioDeviceInterface.LogTraceExit();
                    return 0;
                } else if (!QLog.isColorLevel()) {
                    return -1;
                } else {
                    QLog.e("TRAE", 0, " InternalIsDeviceChangeable fail");
                    return -1;
                }
            } else if (!QLog.isColorLevel()) {
                return -1;
            } else {
                QLog.e("TRAE", 0, " checkDevName fail");
                return -1;
            }
        }
    }

    int InternalSessionIsDeviceChangabled(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(ISDEVICECHANGABLED_RESULT_ISCHANGABLED, InternalIsDeviceChangeable());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    boolean InternalIsDeviceChangeable() {
        String connectingDevice = this._deviceConfigManager.getConnectingDevice();
        if (connectingDevice == null || connectingDevice.equals(DEVICE_NONE) || connectingDevice.equals("")) {
            return true;
        }
        return false;
    }

    int InternalSessionGetConnectedDevice(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(GETCONNECTEDDEVICE_RESULT_LIST, this._deviceConfigManager.getConnectedDevice());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    int InternalSessionGetConnectingDevice(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(GETCONNECTINGDEVICE_RESULT_LIST, this._deviceConfigManager.getConnectingDevice());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    int sendResBroadcast(Intent intent, HashMap<String, Object> hashMap, int i) {
        if (this._context == null) {
            return -1;
        }
        Long l = (Long) hashMap.get(PARAM_SESSIONID);
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, " sessonID:" + l + " " + ((String) hashMap.get(PARAM_OPERATION)));
        }
        if (l == null || l.longValue() == Long.MIN_VALUE) {
            InternalNotifyDeviceListUpdate();
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 0, "sendResBroadcast sid null,don't send res");
            }
            return -1;
        }
        intent.setAction(ACTION_TRAEAUDIOMANAGER_RES);
        intent.putExtra(PARAM_SESSIONID, (Long) hashMap.get(PARAM_SESSIONID));
        intent.putExtra(PARAM_OPERATION, (String) hashMap.get(PARAM_OPERATION));
        intent.putExtra(PARAM_RES_ERRCODE, i);
        this._context.sendBroadcast(intent);
        return 0;
    }

    int InternalNotifyDeviceListUpdate() {
        AudioDeviceInterface.LogTraceEntry("");
        if (this._context == null) {
            return -1;
        }
        HashMap snapParams = this._deviceConfigManager.getSnapParams();
        ArrayList arrayList = (ArrayList) snapParams.get(EXTRA_DATA_AVAILABLEDEVICE_LIST);
        String str = (String) snapParams.get(EXTRA_DATA_CONNECTEDDEVICE);
        String str2 = (String) snapParams.get(EXTRA_DATA_PREV_CONNECTEDDEVICE);
        Intent intent = new Intent();
        intent.setAction(ACTION_TRAEAUDIOMANAGER_NOTIFY);
        intent.putExtra(PARAM_OPERATION, NOTIFY_DEVICELIST_UPDATE);
        intent.putExtra(EXTRA_DATA_AVAILABLEDEVICE_LIST, (String[]) arrayList.toArray(new String[0]));
        intent.putExtra(EXTRA_DATA_CONNECTEDDEVICE, str);
        intent.putExtra(EXTRA_DATA_PREV_CONNECTEDDEVICE, str2);
        intent.putExtra(EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME, this._deviceConfigManager.getBluetoothName());
        this._context.sendBroadcast(intent);
        AudioDeviceInterface.LogTraceExit();
        return 0;
    }

    int InternalNotifyDeviceChangableUpdate() {
        if (this._context == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(ACTION_TRAEAUDIOMANAGER_NOTIFY);
        intent.putExtra(PARAM_OPERATION, NOTIFY_DEVICECHANGABLE_UPDATE);
        intent.putExtra(NOTIFY_DEVICECHANGABLE_UPDATE_DATE, InternalIsDeviceChangeable());
        this._context.sendBroadcast(intent);
        return 0;
    }

    public BluetoohHeadsetCheckInterface CreateBluetoothCheck(Context context, DeviceConfigManager deviceConfigManager) {
        BluetoohHeadsetCheckInterface bluetoohHeadsetCheck;
        if (VERSION.SDK_INT >= 11) {
            bluetoohHeadsetCheck = new BluetoohHeadsetCheck();
        } else if (VERSION.SDK_INT != 18) {
            bluetoohHeadsetCheck = new BluetoohHeadsetCheckFor2x();
        } else {
            bluetoohHeadsetCheck = new BluetoohHeadsetCheckFake();
        }
        if (!bluetoohHeadsetCheck.init(context, deviceConfigManager)) {
            bluetoohHeadsetCheck = new BluetoohHeadsetCheckFake();
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "CreateBluetoothCheck:" + bluetoohHeadsetCheck.interfaceDesc() + " skip android4.3:" + (VERSION.SDK_INT == 18 ? "Y" : "N"));
        }
        return bluetoohHeadsetCheck;
    }

    static String getForceConfigName(int i) {
        if (i < 0 || i >= forceName.length) {
            return "unknow";
        }
        return forceName[i];
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr, Class[] clsArr) {
        Object obj2 = null;
        try {
            obj2 = obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "invokeMethod Exception:" + e.getMessage());
            }
        }
        return obj2;
    }

    public static Object invokeStaticMethod(String str, String str2, Object[] objArr, Class[] clsArr) {
        Object obj = null;
        try {
            obj = Class.forName(str).getMethod(str2, clsArr).invoke(null, objArr);
        } catch (ClassNotFoundException e) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "ClassNotFound:" + str);
            }
        } catch (NoSuchMethodException e2) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "NoSuchMethod:" + str2);
            }
        } catch (IllegalArgumentException e3) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "IllegalArgument:" + str2);
            }
        } catch (IllegalAccessException e4) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "IllegalAccess:" + str2);
            }
        } catch (InvocationTargetException e5) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "InvocationTarget:" + str2);
            }
        } catch (Exception e6) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 0, "invokeStaticMethod Exception:" + e6.getMessage());
            }
        }
        return obj;
    }

    static void setParameters(String str) {
        Object[] objArr = new Object[]{str};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = String.class;
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "setParameters  :" + str);
        }
        invokeStaticMethod("android.media.AudioSystem", "setParameters", objArr, clsArr);
    }

    static void setPhoneState(int i) {
        Object[] objArr = new Object[]{Integer.valueOf(i)};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = Integer.TYPE;
        invokeStaticMethod("android.media.AudioSystem", "setPhoneState", objArr, clsArr);
    }

    static void setForceUse(int i, int i2) {
        Object[] objArr = new Object[]{Integer.valueOf(i), Integer.valueOf(i2)};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = Integer.TYPE;
        clsArr[1] = Integer.TYPE;
        Object invokeStaticMethod = invokeStaticMethod("android.media.AudioSystem", "setForceUse", objArr, clsArr);
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "setForceUse  usage:" + i + " config:" + i2 + " ->" + getForceConfigName(i2) + " res:" + invokeStaticMethod);
        }
    }

    static int getForceUse(int i) {
        Integer num;
        Integer valueOf = Integer.valueOf(0);
        Object[] objArr = new Object[]{Integer.valueOf(i)};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = Integer.TYPE;
        Object invokeStaticMethod = invokeStaticMethod("android.media.AudioSystem", "getForceUse", objArr, clsArr);
        if (invokeStaticMethod != null) {
            num = (Integer) invokeStaticMethod;
        } else {
            num = valueOf;
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "getForceUse  usage:" + i + " config:" + num + " ->" + getForceConfigName(num.intValue()));
        }
        return num.intValue();
    }

    static void forceVolumeControlStream(AudioManager audioManager, int i) {
        Object[] objArr = new Object[]{Integer.valueOf(i)};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = Integer.TYPE;
        Object invokeMethod = invokeMethod(audioManager, "forceVolumeControlStream", objArr, clsArr);
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 0, "forceVolumeControlStream  streamType:" + i + " res:" + invokeMethod);
        }
    }
}
