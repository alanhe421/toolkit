package com.tencent;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.imcore.IMCore;
import com.tencent.imcore.IMCoreUser;
import com.tencent.imcore.PairSession;
import com.tencent.imcore.PairVectorSession;
import com.tencent.imcore.Session;
import com.tencent.imcore.StrVec;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.statistics.BeaconUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TIMManager {
    public static final int DEBUG = 4;
    public static final int ERROR = 1;
    public static final int INFO = 3;
    public static final int WARN = 2;
    static String defaultId = "";
    static ConcurrentHashMap<String, TIMManager> mutiMap = new ConcurrentHashMap();
    private static final String tag = "imsdk.TIMManager";
    private TIMConnListener connListener = null;
    private IMCoreUser coreUser;
    private TIMConversation defaultConversation = new TIMConversation("");
    private TIMExceptionListener exceptionListener;
    private TIMFriendshipProxyListener friendshipProxyListener;
    private TIMGroupAssistantListener groupAssistantListener;
    private TIMGroupEventListener groupEventListener;
    private String identification = "";
    private TIMGroupMemberUpdateListener memberUpdateListener;
    private TIMMessageListener msgListener = null;
    private HashSet<TIMMessageListener> msgListeners = new HashSet();
    private HashSet<TIMMessageUpdateListener> msgUpListeners = new HashSet();
    private TIMOfflinePushListener offlinePushListener;
    private TIMMessageReceiptListener receiptListener;
    private TIMRefreshListener refreshListener;
    private TIMUploadProgressListener uploadProgressListener = null;
    private IMCoreUserConfig userConfig = new IMCoreUserConfig();
    private TIMUserDefinedStatusListener userDefinedStatusListener;
    private TIMUserStatusListener userStatusListener = null;

    private TIMManager(String str) {
        this.identification = str;
    }

    private void ensureId(String str) {
        if (str != null) {
            if (!(TextUtils.isEmpty(this.identification) || str.equals(this.identification))) {
                IMMsfCoreProxy.get().logout(this.identification);
            }
            setIdentification(str, true);
            this.coreUser = null;
            if (TextUtils.isEmpty(this.defaultConversation.getIdentifer())) {
                this.defaultConversation.setIdentifer(str);
            }
        }
    }

    public static TIMManager getInstance() {
        return getInstanceById(defaultId);
    }

    public static TIMManager getInstanceById(String str) {
        if (TextUtils.isEmpty(str)) {
            str = defaultId;
        } else if (TextUtils.isEmpty(defaultId)) {
            if (mutiMap.containsKey(defaultId)) {
                TIMManager tIMManager = (TIMManager) mutiMap.get(defaultId);
                defaultId = str;
                tIMManager.setIdentification(str, false);
                return tIMManager;
            }
            defaultId = str;
        }
        if (mutiMap.containsKey(str)) {
            return (TIMManager) mutiMap.get(str);
        }
        if (str.equals(defaultId) && mutiMap.containsKey("")) {
            tIMManager = (TIMManager) mutiMap.get("");
            mutiMap.put(defaultId, tIMManager);
            return tIMManager;
        }
        tIMManager = new TIMManager(str);
        mutiMap.put(str, tIMManager);
        return tIMManager;
    }

    public static ConcurrentHashMap<String, TIMManager> getMutiTIMMangerMap() {
        return mutiMap;
    }

    public void addMessageListener(TIMMessageListener tIMMessageListener) {
        this.msgListeners.add(tIMMessageListener);
    }

    public void addMessageUpdateListener(TIMMessageUpdateListener tIMMessageUpdateListener) {
        this.msgUpListeners.add(tIMMessageUpdateListener);
    }

    public void configOfflinePushSettings(TIMOfflinePushSettings tIMOfflinePushSettings) {
        IMMsfCoreProxy.get().initOfflinePushSettings(this.identification, tIMOfflinePushSettings);
    }

    public boolean deleteConversation(TIMConversationType tIMConversationType, String str) {
        int i = 0;
        if (!IMCoreWrapper.get().isReady()) {
            return false;
        }
        if (str == null) {
            QLog.e(tag, 1, "delete conversation with null peer");
            return false;
        }
        QLog.i(tag, 1, "before deleteConversation");
        int conversationCount = (int) getConversationCount();
        for (int i2 = 0; i2 < conversationCount; i2++) {
            getConversationByIndex((long) i2);
        }
        boolean deleteSession = getCoreUser().deleteSession(TIMConversationType.getType(tIMConversationType), str);
        QLog.i(tag, 1, "after deleteConversation");
        conversationCount = (int) getConversationCount();
        while (i < conversationCount) {
            getConversationByIndex((long) i);
            i++;
        }
        return deleteSession;
    }

    public boolean deleteConversationAndLocalMsgs(TIMConversationType tIMConversationType, String str) {
        int i = 0;
        if (!IMCoreWrapper.get().isReady()) {
            return false;
        }
        if (str == null) {
            QLog.e(tag, 1, "delete conversation with null peer");
            return false;
        }
        QLog.i(tag, 1, "before deleteConversation");
        int conversationCount = (int) getConversationCount();
        for (int i2 = 0; i2 < conversationCount; i2++) {
            getConversationByIndex((long) i2);
        }
        boolean deleteSessionAndMsgs = getCoreUser().deleteSessionAndMsgs(TIMConversationType.getType(tIMConversationType), str);
        QLog.i(tag, 1, "after deleteConversation");
        conversationCount = (int) getConversationCount();
        while (i < conversationCount) {
            getConversationByIndex((long) i);
            i++;
        }
        return deleteSessionAndMsgs;
    }

    public void disableAutoReport() {
        this.userConfig.setAutoReportEnabled(false);
    }

    public void disableBeaconReport() {
        BeaconUtil.setEnable(false);
    }

    public void disableCrashReport() {
        IMMsfCoreProxy.get().disableCrashReport();
    }

    public void disableRecentContact() {
        this.userConfig.setRecentContactEnabled(false);
    }

    public void disableRecentContactNotify() {
        this.userConfig.setRecentContactNotifyEnabled(false);
    }

    public void disableStorage() {
        this.userConfig.setStorageEnabled(false);
    }

    public void enableFriendshipStorage(boolean z) {
        this.userConfig.setFriendshipStorageEnabled(z);
    }

    public void enableGroupInfoStorage(boolean z) {
        this.userConfig.setGroupStorageEnabled(z);
    }

    public void enableReadReceipt() {
        this.userConfig.setReadReceiptEnabled(true);
    }

    public String getAccountType() {
        return IMMsfCoreProxy.get().getUidType();
    }

    public TIMConnListener getConnectionListener() {
        return this.connListener;
    }

    public TIMConversation getConversation(TIMConversationType tIMConversationType, String str) {
        if (!IMCoreWrapper.get().isReady()) {
            return this.defaultConversation;
        }
        if (str == null) {
            QLog.e(tag, 1, "get conversation with null peer");
            return this.defaultConversation;
        }
        TIMConversation tIMConversation = new TIMConversation(this.identification);
        tIMConversation.setPeer(str);
        tIMConversation.setType(tIMConversationType);
        return tIMConversation;
    }

    public TIMConversation getConversationByIndex(long j) {
        if (!IMCoreWrapper.get().isReady()) {
            return this.defaultConversation;
        }
        Session session = getCoreUser().getSession(j);
        TIMConversation tIMConversation = new TIMConversation(this.identification);
        tIMConversation.fromSession(session);
        QLog.i(tag, 1, "getConversationByIndex: " + j + " type: " + tIMConversation.getType() + " peer: " + tIMConversation.getPeer());
        return tIMConversation;
    }

    public long getConversationCount() {
        if (!IMCoreWrapper.get().isReady()) {
            return 0;
        }
        long sessionCount = getCoreUser().sessionCount();
        QLog.i(tag, 1, "getConversationCount: " + sessionCount);
        return sessionCount;
    }

    public List<TIMConversation> getConversionList() {
        List<TIMConversation> arrayList = new ArrayList();
        if (!IMCoreWrapper.get().isReady()) {
            return arrayList;
        }
        PairVectorSession sessionList = getCoreUser().getSessionList();
        for (int i = 0; ((long) i) < sessionList.size(); i++) {
            PairSession pairSession = sessionList.get(i);
            TIMConversation tIMConversation = new TIMConversation(this.identification);
            tIMConversation.setPeer(new String(pairSession.getFirst()));
            tIMConversation.setType(TIMConversationType.getType(pairSession.getSecond()));
            arrayList.add(tIMConversation);
        }
        return arrayList;
    }

    public IMCoreUser getCoreUser() {
        if (this.coreUser == null && IMMsfCoreProxy.get().getMode() == 1) {
            QLog.w(tag, 1, "TIMManager|getCoreUser reload user from: " + this.identification);
            this.coreUser = IMCore.get().getUser(this.identification);
        }
        return this.coreUser;
    }

    @Deprecated
    public int getEnv() {
        return IMMsfCoreProxy.get().getEnv();
    }

    public TIMExceptionListener getExceptionListener() {
        return this.exceptionListener;
    }

    String getFileCachePath() {
        return IMCoreWrapper.get().getFileCachePath();
    }

    TIMFriendshipProxyListener getFriendshipProxyListener() {
        return this.friendshipProxyListener;
    }

    TIMFriendshipSettings getFriendshipSettings() {
        return this.userConfig.getFriendshipSettings();
    }

    TIMGroupAssistantListener getGroupAssistantListener() {
        return this.groupAssistantListener;
    }

    TIMGroupEventListener getGroupEventListener() {
        return this.groupEventListener;
    }

    TIMGroupMemberUpdateListener getGroupMemberUpdateListener() {
        return this.memberUpdateListener;
    }

    TIMGroupSettings getGroupSettings() {
        return this.userConfig.getGroupSettings();
    }

    public String getIdentification() {
        return this.identification;
    }

    public boolean getIsLogPrintEnabled() {
        return IMCoreWrapper.get().getIsLogPrintEnabled();
    }

    public TIMLogLevel getLogLevel() {
        return IMCoreWrapper.get().getLogLevel();
    }

    public String getLogPath() {
        return IMCoreWrapper.get().getLogPath();
    }

    public String getLoginUser() {
        IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(this.identification);
        if (msfUserInfo != null && msfUserInfo.isLoggedIn()) {
            return this.identification;
        }
        msfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        return (msfUserInfo == null || !msfUserInfo.isLoggedIn()) ? "" : msfUserInfo.getUserId();
    }

    Set<TIMMessageListener> getMessageListeners() {
        return this.msgListeners;
    }

    public TIMMessageReceiptListener getMessageReceiptListener() {
        return this.receiptListener;
    }

    Set<TIMMessageUpdateListener> getMessageUpdateListeners() {
        return this.msgUpListeners;
    }

    public int getMode() {
        return IMMsfCoreProxy.get().getMode();
    }

    public TIMNetworkStatus getNetworkStatus() {
        return IMMsfCoreProxy.get().getNetworkStatus();
    }

    public TIMOfflinePushListener getOfflinePushListener() {
        return this.offlinePushListener;
    }

    public void getOfflinePushSettings(TIMValueCallBack<TIMOfflinePushSettings> tIMValueCallBack) {
        IMMsfCoreProxy.get().getOfflinePushSettings(this.identification, tIMValueCallBack);
    }

    TIMRefreshListener getRefreshListener() {
        return this.refreshListener;
    }

    public int getSdkAppId() {
        return IMMsfCoreProxy.get().getSdkAppId();
    }

    public long getServerTimeDiff() {
        return QALSDKManager.getInstance().getServetTimeSecondInterv();
    }

    public TIMUploadProgressListener getUploadProgressListener() {
        return this.uploadProgressListener;
    }

    protected IMCoreUserConfig getUserConfig() {
        return this.userConfig;
    }

    public void getUserDefinedStatus(List<String> list, TIMValueCallBack<List<TIMUserDefinedStatus>> tIMValueCallBack) {
        if (list == null || list.isEmpty()) {
            tIMValueCallBack.onError(6017, "invalid param. users is empty");
        } else if (IMCoreWrapper.get().isReady()) {
            IMCoreUser user = IMCore.get().getUser(this.identification);
            if (user.getStatusMgr() != null) {
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!TextUtils.isEmpty(str)) {
                        strVec.pushBack(str);
                    }
                }
                user.getStatusMgr().getUserStatus(strVec, new gy(this, tIMValueCallBack));
            }
        } else {
            QLog.e(tag, 1, "sdk not initialized or not logged in.");
            tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public TIMUserDefinedStatusListener getUserDefinedStatusListener() {
        return this.userDefinedStatusListener;
    }

    public TIMUserStatusListener getUserStatusListener() {
        return this.userStatusListener;
    }

    public String getVersion() {
        return "2.5.0" + ".8551.8552";
    }

    String getVideoCachePath() {
        return IMCoreWrapper.get().getVideoCachePath();
    }

    public void init(Context context) {
        if (IMMsfCoreProxy.get().init(context) && IMMsfCoreProxy.get().getMode() == 1) {
            IMCore.get().initOpenIM(IMCoreWrapper.get().getGateWayIp(), context.getFilesDir().toString(), aa.a(), "2.5.0", "8551.8552");
            QLog.d(tag, 1, "init finished with imcore");
            return;
        }
        QLog.d(tag, 1, "init finished without imcore");
    }

    public void init(Context context, int i) {
        if (IMMsfCoreProxy.get().init(context, i) && IMMsfCoreProxy.get().getMode() == 1) {
            IMCore.get().initOpenIM("", context.getFilesDir().toString(), aa.a(), "2.5.0", "8551.8552");
            QLog.d(tag, 1, "init finished with imcore");
            return;
        }
        QLog.d(tag, 1, "init finished without imcore");
    }

    public void init(Context context, int i, String str) {
        if (IMMsfCoreProxy.get().init(context, i, str) && IMMsfCoreProxy.get().getMode() == 1) {
            IMCore.get().initOpenIM("", context.getFilesDir().toString(), aa.a(), "2.5.0", "8551.8552");
            QLog.d(tag, 1, "init finished with imcore");
            return;
        }
        QLog.d(tag, 1, "init finished without imcore");
    }

    public void initFriendshipSettings(long j, List<String> list) {
        this.userConfig.getFriendshipSettings().setSettings(j, list);
    }

    public void initGroupSettings(TIMGroupSettings tIMGroupSettings) {
        if (tIMGroupSettings != null) {
            this.userConfig.setGroupSettings(tIMGroupSettings);
        }
    }

    public void initLogSettings(boolean z, String str) {
        if (!IMMsfCoreProxy.get().inited) {
            IMCoreWrapper.get().initLogSettings(z, str);
        }
    }

    public void initStorage(int i, TIMUser tIMUser, String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null && IMMsfCoreProxy.get().inited && IMMsfCoreProxy.get().getMode() == 1) {
            this.identification = tIMUser.getIdentifier();
            this.userConfig.setNotifyCallback(new IMCoreNotify(this.identification));
            IMCore.get().initUser(i, tIMUser.getAccountType(), tIMUser.getAppIdAt3rd(), tIMUser.getIdentifier(), "0", "0".getBytes(), IMCoreWrapper.get().getGateWayIp(), IMMsfCoreProxy.get().getContext().getFilesDir().toString(), this.userConfig.convertTo(this.identification), new gx(this, tIMCallBack));
        }
    }

    public boolean isFriendshipStorageEnabled() {
        return this.userConfig.isFriendshipStorageEnabled();
    }

    public boolean isGrpStorageEnabled() {
        return this.userConfig.isGroupStorageEnabled();
    }

    public void log(int i, String str, String str2) {
        switch (i) {
            case 1:
                QLog.e(str, 1, str2);
                return;
            case 2:
                QLog.w(str, 1, str2);
                return;
            case 3:
                QLog.i(str, 1, str2);
                return;
            case 4:
                QLog.d(str, 1, str2);
                return;
            default:
                return;
        }
    }

    public void login(int i, TIMUser tIMUser, String str, TIMCallBack tIMCallBack) {
        ensureId(tIMUser.getIdentifier());
        IMMsfCoreProxy.get().login(i, tIMUser, str, tIMCallBack);
    }

    public void login(String str, String str2, TIMCallBack tIMCallBack) {
        ensureId(str);
        IMMsfCoreProxy.get().login(str, str2, tIMCallBack);
    }

    public void logout() {
        IMMsfCoreProxy.get().logout(this.identification);
        if (!this.identification.equals("")) {
            mutiMap.remove(this.identification);
            if (defaultId.equals(this.identification)) {
                QLog.d(tag, 1, "reset default id");
                defaultId = "";
            }
            this.identification = "";
        }
    }

    public void logout(TIMCallBack tIMCallBack) {
        IMMsfCoreProxy.get().logout(this.identification, tIMCallBack);
        if (!this.identification.equals("")) {
            mutiMap.remove(this.identification);
            if (defaultId.equals(this.identification)) {
                QLog.d(tag, 1, "reset default id");
                defaultId = "";
            }
            this.identification = "";
        }
    }

    public void removeMessageListener(TIMMessageListener tIMMessageListener) {
        this.msgListeners.remove(tIMMessageListener);
    }

    public void removeMessageUpdateListener(TIMMessageUpdateListener tIMMessageUpdateListener) {
        this.msgUpListeners.remove(tIMMessageUpdateListener);
    }

    public void sendMessageToMultiUsers(List<String> list, TIMMessage tIMMessage, TIMSendMsgToMultiUserCallback tIMSendMsgToMultiUserCallback) {
        if (tIMSendMsgToMultiUserCallback != null) {
            if (list == null || list.isEmpty() || tIMMessage == null) {
                tIMSendMsgToMultiUserCallback.onError(6017, "invalid params, users or msg is null or empty", new TIMBatchOprDetailInfo());
            } else if (IMCoreWrapper.get().isReady()) {
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!TextUtils.isEmpty(str)) {
                        strVec.pushBack(str);
                    }
                }
                getCoreUser().sendMsgToMultiUsers(strVec, tIMMessage.getMsg(), new hf(this, tIMSendMsgToMultiUserCallback));
            } else {
                tIMSendMsgToMultiUserCallback.onError(6013, "sdk not initialized or not logged in.", new TIMBatchOprDetailInfo());
            }
        }
    }

    public void setConnectionListener(TIMConnListener tIMConnListener) {
        this.connListener = tIMConnListener;
    }

    public void setCoreUser(IMCoreUser iMCoreUser) {
        this.coreUser = iMCoreUser;
    }

    @Deprecated
    public void setEnv(int i) {
        QLog.w(tag, 1, "setEnv " + i);
        IMMsfCoreProxy.get().setEnv(i);
    }

    public void setExceptionListener(TIMExceptionListener tIMExceptionListener) {
        this.exceptionListener = tIMExceptionListener;
    }

    public void setFriendshipProxyListener(TIMFriendshipProxyListener tIMFriendshipProxyListener) {
        this.friendshipProxyListener = tIMFriendshipProxyListener;
    }

    public void setGroupAssistantListener(TIMGroupAssistantListener tIMGroupAssistantListener) {
        this.groupAssistantListener = tIMGroupAssistantListener;
    }

    public void setGroupEventListener(TIMGroupEventListener tIMGroupEventListener) {
        this.groupEventListener = tIMGroupEventListener;
    }

    @Deprecated
    public void setGroupMemberUpdateListener(TIMGroupMemberUpdateListener tIMGroupMemberUpdateListener) {
        this.memberUpdateListener = tIMGroupMemberUpdateListener;
    }

    public void setIdentification(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            QLog.w(tag, 1, "setIdentification->enter with empty id");
        } else if (TextUtils.isEmpty(this.identification) || !str.equals(this.identification)) {
            QLog.d(tag, 1, "setIdentification->update id:" + this.identification + "=>" + str + ", " + z);
            if (!TextUtils.isEmpty(this.identification)) {
                if (TextUtils.isEmpty(defaultId) || this.identification.equals(defaultId)) {
                    defaultId = str;
                }
                if (z && !TextUtils.isEmpty(this.identification) && mutiMap.containsKey(this.identification) && mutiMap.get(this.identification) == this) {
                    mutiMap.remove(this.identification);
                }
            }
            this.identification = str;
            mutiMap.put(str, this);
        }
    }

    public void setLogLevel(TIMLogLevel tIMLogLevel) {
        if (!IMMsfCoreProxy.get().inited) {
            IMCoreWrapper.get().setLogLevel(tIMLogLevel);
        }
    }

    public void setLogListenCallBackLevel(TIMLogLevel tIMLogLevel) {
        IMMsfCoreProxy.get().setTIMLogCbLevel(tIMLogLevel);
    }

    public void setLogListener(TIMLogListener tIMLogListener) {
        IMMsfCoreProxy.get().setTIMLogListener(tIMLogListener);
    }

    @Deprecated
    public void setLogPrintEanble(boolean z) {
        IMCoreWrapper.get().setIsLogPrintEnabled(z);
    }

    public void setLogPrintEnable(boolean z) {
        IMCoreWrapper.get().setIsLogPrintEnabled(z);
    }

    public void setMessageReceiptListener(TIMMessageReceiptListener tIMMessageReceiptListener) {
        this.receiptListener = tIMMessageReceiptListener;
    }

    public void setMode(int i) {
        IMMsfCoreProxy.get().setMode(i);
    }

    public void setOfflinePushListener(TIMOfflinePushListener tIMOfflinePushListener) {
        QLog.d(tag, 1, "registering offline push listener");
        this.offlinePushListener = tIMOfflinePushListener;
        QALSDKManager.getInstance().setOffLinePushListener("im_open_push.msg_push", new he(this, tIMOfflinePushListener));
    }

    @Deprecated
    public void setOfflinePushToken(TIMOfflinePushToken tIMOfflinePushToken) {
        IMMsfCoreProxy.get().setOfflinePushToken(this.identification, tIMOfflinePushToken, null);
    }

    public void setOfflinePushToken(TIMOfflinePushToken tIMOfflinePushToken, TIMCallBack tIMCallBack) {
        IMMsfCoreProxy.get().setOfflinePushToken(this.identification, tIMOfflinePushToken, tIMCallBack);
    }

    @Deprecated
    public void setRefreshLiistener(TIMRefreshListener tIMRefreshListener) {
        this.refreshListener = tIMRefreshListener;
    }

    public void setRefreshListener(TIMRefreshListener tIMRefreshListener) {
        this.refreshListener = tIMRefreshListener;
    }

    public void setSoLibPath(String str) {
        if (!IMMsfCoreProxy.get().inited) {
            IMCoreWrapper.get().setSoLibPath(str);
        }
    }

    public void setUploadProgressListener(TIMUploadProgressListener tIMUploadProgressListener) {
        this.uploadProgressListener = tIMUploadProgressListener;
    }

    public void setUserDefinedStatus(String str, TIMCallBack tIMCallBack) {
        if (IMCoreWrapper.get().isReady()) {
            if (str == null) {
                str = "";
            }
            IMCoreUser user = IMCore.get().getUser(this.identification);
            if (user.getStatusMgr() != null) {
                try {
                    user.getStatusMgr().setUsedefinedData(str.getBytes("utf-8"), new hb(this, tIMCallBack));
                    return;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        QLog.e(tag, 1, "sdk not initialized or not logged in.");
        tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
    }

    public void setUserDefinedStatusListener(TIMUserDefinedStatusListener tIMUserDefinedStatusListener) {
        this.userDefinedStatusListener = tIMUserDefinedStatusListener;
    }

    public void setUserStatusListener(TIMUserStatusListener tIMUserStatusListener) {
        this.userStatusListener = tIMUserStatusListener;
    }

    public void stopQALService() {
        IMMsfCoreProxy.get().stopQALService();
    }

    public boolean uploadLogFile(IMCoreUploadLogFileOpt iMCoreUploadLogFileOpt) {
        return IMMsfCoreProxy.get().uploadLogFile(this.identification, iMCoreUploadLogFileOpt);
    }
}
