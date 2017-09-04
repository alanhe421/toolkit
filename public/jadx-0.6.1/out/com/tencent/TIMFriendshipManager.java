package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.BytesMap;
import com.tencent.imcore.BytesVec;
import com.tencent.imcore.FriendDeleteType;
import com.tencent.imcore.FriendFutureItemVec;
import com.tencent.imcore.FriendGenderType;
import com.tencent.imcore.FriendGroupVec;
import com.tencent.imcore.FriendMetaInfo;
import com.tencent.imcore.FriendPendencyItemVec;
import com.tencent.imcore.FriendPendencyMeta;
import com.tencent.imcore.FriendProfile;
import com.tencent.imcore.FriendProfileVec;
import com.tencent.imcore.FriendshipManager;
import com.tencent.imcore.FutureFriendMeta;
import com.tencent.imcore.GetProfileOption;
import com.tencent.imcore.IFriendGroupCallback;
import com.tencent.imcore.IFriendshipActionCallback;
import com.tencent.imcore.IFriendshipActionCallbackV2;
import com.tencent.imcore.IFriendshipCallback;
import com.tencent.imcore.IFriendshipGetFriendV2Callback;
import com.tencent.imcore.IFriendshipGetFutureCallback;
import com.tencent.imcore.IFriendshipPendencyCallback;
import com.tencent.imcore.SNSProfileItem;
import com.tencent.imcore.SNSProfileItemVec;
import com.tencent.imcore.SetProfileOption;
import com.tencent.imcore.StrVec;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TIMFriendshipManager {
    public static final int TIM_FUTURE_FRIEND_DECIDE_TYPE = 8;
    public static final int TIM_FUTURE_FRIEND_PENDENCY_IN_TYPE = 1;
    public static final int TIM_FUTURE_FRIEND_PENDENCY_OUT_TYPE = 2;
    public static final int TIM_FUTURE_FRIEND_RECOMMEND_TYPE = 4;
    public static final int TIM_PROFILE_FLAG_ALLOW_TYPE = 2;
    public static final int TIM_PROFILE_FLAG_BIRTHDAY = 128;
    public static final int TIM_PROFILE_FLAG_FACE_URL = 4;
    public static final int TIM_PROFILE_FLAG_GENDER = 64;
    public static final int TIM_PROFILE_FLAG_GROUP = 16;
    public static final int TIM_PROFILE_FLAG_LANGUAGE = 512;
    public static final int TIM_PROFILE_FLAG_LOCATION = 256;
    public static final int TIM_PROFILE_FLAG_NICK = 1;
    public static final int TIM_PROFILE_FLAG_REMARK = 8;
    public static final int TIM_PROFILE_FLAG_SELF_SIGNATURE = 32;
    private static final String tag = "TIMFriendshipManager";
    private String identifier = "";
    private TIMFriendshipProxy proxy;

    abstract class aa extends IFriendshipActionCallbackV2 {
        public TIMValueCallBack<TIMUserSearchSucc> a;

        public aa(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack<TIMUserSearchSucc> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(TIMUserSearchSucc tIMUserSearchSucc);

        public void done(long j, FriendProfileVec friendProfileVec) {
            QLog.d(TIMFriendshipManager.tag, 1, "totalNum:" + j + "|vecSize:" + friendProfileVec.size());
            TIMUserSearchSucc tIMUserSearchSucc = new TIMUserSearchSucc();
            tIMUserSearchSucc.totalNum = j;
            tIMUserSearchSucc.infoList = new ArrayList();
            for (int i = 0; ((long) i) < friendProfileVec.size(); i++) {
                tIMUserSearchSucc.infoList.add(new TIMUserProfile(friendProfileVec.get(i)));
            }
            IMMsfCoreProxy.mainHandler.post(new dv(this, tIMUserSearchSucc));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new dw(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ab extends IFriendshipCallback {
        public TIMCallBack a;

        public ab(TIMFriendshipManager tIMFriendshipManager, TIMCallBack tIMCallBack) {
            swigReleaseOwnership();
            this.a = tIMCallBack;
        }

        public abstract void a();

        public abstract void a(int i, String str);

        public void done() {
            IMMsfCoreProxy.mainHandler.post(new dx(this));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new dy(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ac extends IFriendshipGetFutureCallback {
        public TIMValueCallBack<TIMGetFriendFutureListSucc> a;

        public ac(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack<TIMGetFriendFutureListSucc> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(TIMGetFriendFutureListSucc tIMGetFriendFutureListSucc);

        public void done(FutureFriendMeta futureFriendMeta, FriendFutureItemVec friendFutureItemVec) {
            IMMsfCoreProxy.mainHandler.post(new dz(this, new TIMGetFriendFutureListSucc(futureFriendMeta, friendFutureItemVec)));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new ea(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ad extends IFriendGroupCallback {
        public TIMValueCallBack<List<TIMFriendGroup>> a;

        public ad(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack<List<TIMFriendGroup>> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(List<TIMFriendGroup> list);

        public void done(FriendGroupVec friendGroupVec) {
            List arrayList = new ArrayList();
            for (int i = 0; ((long) i) < friendGroupVec.size(); i++) {
                arrayList.add(new TIMFriendGroup(friendGroupVec.get(i)));
            }
            IMMsfCoreProxy.mainHandler.post(new eb(this, arrayList));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new ec(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ae extends IFriendshipPendencyCallback {
        public TIMValueCallBack<TIMGetFriendPendencyListSucc> a;

        public ae(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack<TIMGetFriendPendencyListSucc> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(TIMGetFriendPendencyListSucc tIMGetFriendPendencyListSucc);

        public void done(FriendPendencyMeta friendPendencyMeta, FriendPendencyItemVec friendPendencyItemVec) {
            IMMsfCoreProxy.mainHandler.post(new ed(this, new TIMGetFriendPendencyListSucc(friendPendencyMeta, friendPendencyItemVec)));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new ee(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class af<T> extends IFriendshipActionCallback {
        public TIMValueCallBack<T> a;

        public af(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack<T> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(List<TIMUserProfile> list);

        public void done(FriendProfileVec friendProfileVec) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; ((long) i) < friendProfileVec.size(); i++) {
                arrayList.add(new TIMUserProfile(friendProfileVec.get(i)));
            }
            IMMsfCoreProxy.mainHandler.post(new ef(this, arrayList));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new eg(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ag<T> extends IFriendshipGetFriendV2Callback {
        public TIMValueCallBack<T> a;

        public ag(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack<T> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(TIMGetFriendListV2Succ tIMGetFriendListV2Succ);

        public void done(FriendMetaInfo friendMetaInfo, FriendProfileVec friendProfileVec) {
            List arrayList = new ArrayList();
            for (int i = 0; ((long) i) < friendProfileVec.size(); i++) {
                arrayList.add(new TIMUserProfile(friendProfileVec.get(i)));
            }
            TIMFriendMetaInfo tIMFriendMetaInfo = new TIMFriendMetaInfo();
            tIMFriendMetaInfo.setNextSeq(friendMetaInfo.getDdwNextSeq());
            tIMFriendMetaInfo.setInfoSeq(friendMetaInfo.getDdwInfoSeq());
            tIMFriendMetaInfo.setRecover(friendMetaInfo.getRecover());
            tIMFriendMetaInfo.setTimestamp(friendMetaInfo.getDdwTimestamp());
            TIMGetFriendListV2Succ tIMGetFriendListV2Succ = new TIMGetFriendListV2Succ();
            tIMGetFriendListV2Succ.setFriends(arrayList);
            tIMGetFriendListV2Succ.setMetaInfo(tIMFriendMetaInfo);
            IMMsfCoreProxy.mainHandler.post(new eh(this, tIMGetFriendListV2Succ));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new ei(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ah<T> extends IFriendshipActionCallback {
        public TIMValueCallBack<T> a;

        public ah(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack<T> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(List<TIMFriendResult> list);

        public void done(FriendProfileVec friendProfileVec) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; ((long) i) < friendProfileVec.size(); i++) {
                FriendProfile friendProfile = friendProfileVec.get(i);
                arrayList.add(new TIMFriendResult(friendProfile));
                QLog.d(TIMFriendshipManager.tag, 1, "identifier: " + friendProfile.getSIdentifier() + " status: " + friendProfile.getResult());
            }
            IMMsfCoreProxy.mainHandler.post(new ej(this, arrayList));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new ek(this, i, str));
            swigTakeOwnership();
        }
    }

    private enum ai {
        ;

        static {
            b = 1;
            c = 2;
            a = 3;
            int[] iArr = new int[]{1, 2, 3};
        }
    }

    abstract class aj extends IFriendshipActionCallback {
        public TIMCallBack a;

        public aj(TIMFriendshipManager tIMFriendshipManager, TIMCallBack tIMCallBack) {
            swigReleaseOwnership();
            this.a = tIMCallBack;
        }

        public abstract void a();

        public abstract void a(int i, String str);

        public void done(FriendProfileVec friendProfileVec) {
            IMMsfCoreProxy.mainHandler.post(new el(this));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new em(this, i, str));
            swigTakeOwnership();
        }
    }

    private TIMFriendshipManager(String str) {
        this.identifier = str;
    }

    private FriendshipManager getFriendShipMgr() {
        if (!TextUtils.isEmpty(this.identifier)) {
            return TIMManager.getInstanceById(this.identifier).getCoreUser().getFriendShipMgr();
        }
        QLog.w(tag, 1, "TIMFriendshipManager|getFriendShipMgr id is empty");
        return TIMManager.getInstance().getCoreUser().getFriendShipMgr();
    }

    public static TIMFriendshipManager getInstance() {
        return getInstanceById(TIMManager.getInstance().getIdentification());
    }

    public static TIMFriendshipManager getInstanceById(String str) {
        return new TIMFriendshipManager(str);
    }

    public void addBlackList(List<String> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null || list.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, identifiers is empty");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback cwVar = new cw(this, tIMValueCallBack);
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!TextUtils.isEmpty(str)) {
                        strVec.pushBack(str);
                    }
                }
                getFriendShipMgr().addBlackList(strVec, cwVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void addFriend(List<TIMAddFriendRequest> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback cqVar = new cq(this, tIMValueCallBack);
                FriendProfileVec friendProfileVec = new FriendProfileVec();
                for (TIMAddFriendRequest tIMAddFriendRequest : list) {
                    FriendProfile friendProfile = new FriendProfile();
                    friendProfile.setSIdentifier(tIMAddFriendRequest.getIdentifier());
                    try {
                        friendProfile.setSRemark(tIMAddFriendRequest.getRemark().getBytes("utf-8"));
                        friendProfile.setSAddSource(tIMAddFriendRequest.getAddSource().getBytes("utf-8"));
                        friendProfile.setSAddWording(tIMAddFriendRequest.getAddWording().getBytes("utf-8"));
                        BytesVec bytesVec = new BytesVec();
                        bytesVec.pushBack(tIMAddFriendRequest.getFriendGroup().getBytes("utf-8"));
                        friendProfile.setSGroupNames(bytesVec);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    friendProfileVec.pushBack(friendProfile);
                }
                getFriendShipMgr().addFriend(friendProfileVec, cqVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void addFriendResponse(TIMFriendAddResponse tIMFriendAddResponse, TIMValueCallBack<TIMFriendResult> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (tIMFriendAddResponse == null) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback crVar = new cr(this, tIMValueCallBack);
                FriendProfileVec friendProfileVec = new FriendProfileVec();
                FriendProfile friendProfile = new FriendProfile();
                friendProfile.setSIdentifier(tIMFriendAddResponse.getIdentifier());
                try {
                    friendProfile.setSRemark(tIMFriendAddResponse.getRemark().getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                friendProfile.setSResponseAction(tIMFriendAddResponse.getType().getAction());
                friendProfileVec.pushBack(friendProfile);
                getFriendShipMgr().doResponse(friendProfileVec, crVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void addFriendsToFriendGroup(String str, List<String> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (str == null || list == null || list.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, groupName or users is null or empty");
            } else if (IMCoreWrapper.get().isReady()) {
                StrVec strVec = new StrVec();
                for (String str2 : list) {
                    if (!(str2 == null || str2.length() == 0)) {
                        strVec.pushBack(str2);
                    }
                }
                byte[] bArr = null;
                try {
                    bArr = str.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                getFriendShipMgr().addFriends2Group(bArr, strVec, new dj(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void checkFriends(TIMFriendCheckParam tIMFriendCheckParam, TIMValueCallBack<List<TIMFriendCheckResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (tIMFriendCheckParam == null || tIMFriendCheckParam.identifiers == null || tIMFriendCheckParam.identifiers.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, must specify users in param");
            } else if (IMCoreWrapper.get().isReady()) {
                StrVec strVec = new StrVec();
                for (String str : tIMFriendCheckParam.identifiers) {
                    if (!TextUtils.isEmpty(str)) {
                        strVec.pushBack(str);
                    }
                }
                getFriendShipMgr().checkFriend(strVec, tIMFriendCheckParam.isBidirection() ? "CheckResult_Type_Both" : "CheckResult_Type_Singal", new do(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void createFriendGroup(List<String> list, List<String> list2, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null || list2 == null || list.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, groupNames or users is null or empty");
            } else if (IMCoreWrapper.get().isReady()) {
                BytesVec bytesVec = new BytesVec();
                for (String str : list) {
                    if (!(str == null || str.length() == 0)) {
                        try {
                            bytesVec.pushBack(str.getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                StrVec strVec = new StrVec();
                for (String str2 : list2) {
                    if (!(str2 == null || str2.length() == 0)) {
                        strVec.pushBack(str2);
                    }
                }
                getFriendShipMgr().createFriendGroup(bytesVec, strVec, new dh(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void delBlackList(List<String> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null || list.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, identifiers is empty");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback cxVar = new cx(this, tIMValueCallBack);
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!TextUtils.isEmpty(str)) {
                        strVec.pushBack(str);
                    }
                }
                getFriendShipMgr().delBlackList(strVec, cxVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void delFriend(TIMDelFriendType tIMDelFriendType, List<TIMAddFriendRequest> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback csVar = new cs(this, tIMValueCallBack);
                FriendProfileVec friendProfileVec = new FriendProfileVec();
                for (TIMAddFriendRequest tIMAddFriendRequest : list) {
                    FriendProfile friendProfile = new FriendProfile();
                    friendProfile.setSIdentifier(tIMAddFriendRequest.getIdentifier());
                    friendProfileVec.pushBack(friendProfile);
                }
                getFriendShipMgr().delFriend(FriendDeleteType.swigToEnum(tIMDelFriendType.ordinal()), friendProfileVec, csVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void delFriendsFromFriendGroup(String str, List<String> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (str == null || list == null || list.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, groupName or users is null or empty");
            } else if (IMCoreWrapper.get().isReady()) {
                StrVec strVec = new StrVec();
                for (String str2 : list) {
                    if (!(str2 == null || str2.length() == 0)) {
                        strVec.pushBack(str2);
                    }
                }
                byte[] bArr = null;
                try {
                    bArr = str.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                getFriendShipMgr().delFriendsFromGroup(bArr, strVec, new dk(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void deleteDecide(List<String> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null || list.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, users is empty");
            } else if (IMCoreWrapper.get().isReady()) {
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!(str == null || str.length() == 0)) {
                        strVec.pushBack(str);
                    }
                }
                getFriendShipMgr().deleteDecide(strVec, new dg(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void deleteFriendGroup(List<String> list, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (list == null || list.isEmpty()) {
                tIMCallBack.onError(6017, "invalid parameters, groupNames is empty");
            } else if (IMCoreWrapper.get().isReady()) {
                BytesVec bytesVec = new BytesVec();
                for (String str : list) {
                    if (!(str == null || str.length() == 0)) {
                        try {
                            bytesVec.pushBack(str.getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                getFriendShipMgr().deleteFriendGroup(bytesVec, new di(this, tIMCallBack));
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void deletePendency(TIMPendencyGetType tIMPendencyGetType, List<String> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (tIMPendencyGetType == null || list == null || list.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, type or users is null or empty");
            } else if (IMCoreWrapper.get().isReady()) {
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!(str == null || str.length() == 0)) {
                        strVec.pushBack(str);
                    }
                }
                getFriendShipMgr().deletePendency(TIMPendencyGetType.getType(tIMPendencyGetType), strVec, new df(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void deleteRecommend(List<String> list, TIMValueCallBack<List<TIMFriendResult>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null || list.isEmpty()) {
                tIMValueCallBack.onError(6017, "invalid parameters, users is empty");
            } else if (IMCoreWrapper.get().isReady()) {
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!(str == null || str.length() == 0)) {
                        strVec.pushBack(str);
                    }
                }
                getFriendShipMgr().deleteRecommend(strVec, new dd(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void getBlackList(TIMValueCallBack<List<String>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                getFriendShipMgr().getBlackList(new cy(this, tIMValueCallBack));
                return;
            }
            tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void getFriendGroups(List<String> list, TIMValueCallBack<List<TIMFriendGroup>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                BytesVec bytesVec = new BytesVec();
                if (list != null) {
                    for (String str : list) {
                        if (!(str == null || str.length() == 0)) {
                            try {
                                bytesVec.pushBack(str.getBytes("utf-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                getFriendShipMgr().getFriendGroup(bytesVec, true, new dn(this, tIMValueCallBack));
                return;
            }
            tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void getFriendList(TIMValueCallBack<List<TIMUserProfile>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                getFriendShipMgr().getFriendList(new cu(this, tIMValueCallBack));
                return;
            }
            tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void getFriendListV2(long j, List<String> list, TIMFriendMetaInfo tIMFriendMetaInfo, TIMValueCallBack<TIMGetFriendListV2Succ> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                IFriendshipGetFriendV2Callback cvVar = new cv(this, tIMValueCallBack);
                StrVec strVec = new StrVec();
                if (list != null) {
                    for (String str : list) {
                        if (!TextUtils.isEmpty(str)) {
                            strVec.pushBack(str);
                        }
                    }
                }
                FriendMetaInfo friendMetaInfo = new FriendMetaInfo();
                friendMetaInfo.setRecover(tIMFriendMetaInfo.isRecover());
                friendMetaInfo.setDdwInfoSeq(tIMFriendMetaInfo.getInfoSeq());
                friendMetaInfo.setDdwNextSeq(tIMFriendMetaInfo.getNextSeq());
                friendMetaInfo.setDdwTimestamp(tIMFriendMetaInfo.getTimestamp());
                getFriendShipMgr().getFriendListV2(j, strVec, friendMetaInfo, cvVar);
                return;
            }
            tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void getFriendsProfile(List<String> list, TIMValueCallBack<List<TIMUserProfile>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback coVar = new co(this, tIMValueCallBack);
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!TextUtils.isEmpty(str)) {
                        strVec.pushBack(str);
                    }
                }
                GetProfileOption getProfileOption = new GetProfileOption();
                TIMFriendshipSettings friendshipSettings = TIMManager.getInstanceById(this.identifier).getFriendshipSettings();
                getProfileOption.setFlag(friendshipSettings.getFlags());
                BytesMap bytesMap = new BytesMap();
                List<String> customFields = friendshipSettings.getCustomFields();
                if (customFields != null) {
                    for (String str2 : customFields) {
                        try {
                            bytesMap.set(str2.getBytes("utf-8"), "".getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                getProfileOption.setCustom_info(bytesMap);
                getFriendShipMgr().getFriendProfile(strVec, getProfileOption, coVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void getFutureFriends(long j, long j2, List<String> list, TIMFriendFutureMeta tIMFriendFutureMeta, TIMValueCallBack<TIMGetFriendFutureListSucc> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (tIMFriendFutureMeta == null) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipGetFutureCallback dcVar = new dc(this, tIMValueCallBack);
                StrVec strVec = new StrVec();
                if (list != null) {
                    for (String str : list) {
                        if (!TextUtils.isEmpty(str)) {
                            strVec.pushBack(str);
                        }
                    }
                }
                getFriendShipMgr().getFutureFriends(j, j2, strVec, tIMFriendFutureMeta.getFutureFriendMeta(), dcVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void getPendencyFromServer(TIMFriendPendencyMeta tIMFriendPendencyMeta, TIMPendencyGetType tIMPendencyGetType, TIMValueCallBack<TIMGetFriendPendencyListSucc> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (tIMFriendPendencyMeta == null || tIMPendencyGetType == null) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                getFriendShipMgr().getPendencyFromServer(tIMFriendPendencyMeta.toFriendPendencyMeta(), TIMPendencyGetType.getType(tIMPendencyGetType), new db(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void getSelfProfile(TIMValueCallBack<TIMUserProfile> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback clVar = new cl(this, tIMValueCallBack);
                StrVec strVec = new StrVec();
                if (TextUtils.isEmpty(this.identifier)) {
                    strVec.pushBack(TIMManager.getInstance().getIdentification());
                } else {
                    strVec.pushBack(this.identifier);
                }
                GetProfileOption getProfileOption = new GetProfileOption();
                TIMFriendshipSettings friendshipSettings = TIMManager.getInstanceById(this.identifier).getFriendshipSettings();
                getProfileOption.setFlag(friendshipSettings.getFlags());
                BytesMap bytesMap = new BytesMap();
                List<String> customFields = friendshipSettings.getCustomFields();
                if (customFields != null) {
                    for (String bytes : customFields) {
                        try {
                            bytesMap.set(bytes.getBytes("utf-8"), "".getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                getProfileOption.setCustom_info(bytesMap);
                getFriendShipMgr().getProfile(strVec, getProfileOption, clVar);
                return;
            }
            tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void getUsersProfile(List<String> list, TIMValueCallBack<List<TIMUserProfile>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (list == null) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback cpVar = new cp(this, tIMValueCallBack);
                StrVec strVec = new StrVec();
                for (String str : list) {
                    if (!TextUtils.isEmpty(str)) {
                        strVec.pushBack(str);
                    }
                }
                GetProfileOption getProfileOption = new GetProfileOption();
                TIMFriendshipSettings friendshipSettings = TIMManager.getInstanceById(this.identifier).getFriendshipSettings();
                getProfileOption.setFlag(friendshipSettings.getFlags());
                BytesMap bytesMap = new BytesMap();
                List<String> customFields = friendshipSettings.getCustomFields();
                if (customFields != null) {
                    for (String str2 : customFields) {
                        try {
                            bytesMap.set(str2.getBytes("utf-8"), "".getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                getProfileOption.setCustom_info(bytesMap);
                getFriendShipMgr().getProfile(strVec, getProfileOption, cpVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void pendencyReport(long j, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                getFriendShipMgr().pendencyReport(j, new cz(this, tIMCallBack));
                return;
            }
            tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void recommendReport(long j, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                getFriendShipMgr().recommendReport(j, new da(this, tIMCallBack));
                return;
            }
            tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void renameFriendGroupName(String str, String str2, TIMCallBack tIMCallBack) {
        UnsupportedEncodingException e;
        byte[] bArr = null;
        if (tIMCallBack != null) {
            if (str == null || str2 == null) {
                tIMCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                byte[] bytes;
                IFriendshipActionCallback dmVar = new dm(this, new dl(this, tIMCallBack));
                try {
                    bytes = str.getBytes("utf-8");
                    try {
                        bArr = str2.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e2) {
                        e = e2;
                        e.printStackTrace();
                        getFriendShipMgr().modifyFriendGroupName(bytes, bArr, dmVar);
                    }
                } catch (UnsupportedEncodingException e3) {
                    e = e3;
                    bytes = bArr;
                    e.printStackTrace();
                    getFriendShipMgr().modifyFriendGroupName(bytes, bArr, dmVar);
                }
                getFriendShipMgr().modifyFriendGroupName(bytes, bArr, dmVar);
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void searchFriend(String str, TIMValueCallBack<TIMUserProfile> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (TextUtils.isEmpty(str)) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipActionCallback cmVar = new cm(this, tIMValueCallBack);
                StrVec strVec = new StrVec();
                strVec.pushBack(str);
                GetProfileOption getProfileOption = new GetProfileOption();
                TIMFriendshipSettings friendshipSettings = TIMManager.getInstanceById(str).getFriendshipSettings();
                getProfileOption.setFlag(friendshipSettings.getFlags());
                BytesMap bytesMap = new BytesMap();
                List<String> customFields = friendshipSettings.getCustomFields();
                if (customFields != null) {
                    for (String bytes : customFields) {
                        try {
                            bytesMap.set(bytes.getBytes("utf-8"), "".getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                getProfileOption.setCustom_info(bytesMap);
                getFriendShipMgr().getProfile(strVec, getProfileOption, cmVar);
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void searchUser(String str, long j, long j2, TIMValueCallBack<TIMUserSearchSucc> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (str == null) {
                tIMValueCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                getFriendShipMgr().searchFriendsUseNickName(str, j, j2, new cn(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void setAllowType(TIMFriendAllowType tIMFriendAllowType, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback ckVar = new ck(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                setProfileOption.setAdd_option(tIMFriendAllowType.getType());
                int i = ai.a;
                setProfileOption.setFlag(2);
                getFriendShipMgr().setProfile(setProfileOption, ckVar);
                return;
            }
            tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void setBirthday(long j, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback dqVar = new dq(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                setProfileOption.setBirthday(j);
                setProfileOption.setFlag(128);
                getFriendShipMgr().setProfile(setProfileOption, dqVar);
                return;
            }
            tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void setCustomInfo(String str, byte[] bArr, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (str == null || bArr == null) {
                tIMCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback dpVar = new dp(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                BytesMap bytesMap = new BytesMap();
                try {
                    bytesMap.set(str.getBytes("utf-8"), bArr);
                    setProfileOption.setCustom_info(bytesMap);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                getFriendShipMgr().setProfile(setProfileOption, dpVar);
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void setFaceUrl(String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (str == null) {
                tIMCallBack.onError(6017, "invalid parameters, faceUrl is null");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback ctVar = new ct(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                setProfileOption.setFlag(4);
                try {
                    setProfileOption.setFace_url(str.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                getFriendShipMgr().setProfile(setProfileOption, ctVar);
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void setFriendCustom(String str, Map<byte[], byte[]> map, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (TextUtils.isEmpty(str) || map == null) {
                tIMCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                SNSProfileItem sNSProfileItem = new SNSProfileItem();
                sNSProfileItem.setSIdentifier(str);
                BytesMap bytesMap = new BytesMap();
                for (Entry entry : map.entrySet()) {
                    bytesMap.set((byte[]) entry.getKey(), (byte[]) entry.getValue());
                }
                sNSProfileItem.setMpCustom(bytesMap);
                SNSProfileItemVec sNSProfileItemVec = new SNSProfileItemVec();
                sNSProfileItemVec.pushBack(sNSProfileItem);
                getFriendShipMgr().setSnsProfile(sNSProfileItemVec, new cj(this, tIMCallBack));
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void setFriendRemark(String str, String str2, TIMCallBack tIMCallBack) {
        byte[] bytes;
        UnsupportedEncodingException e;
        SNSProfileItemVec sNSProfileItemVec;
        byte[] bArr = null;
        if (tIMCallBack != null) {
            if (TextUtils.isEmpty(str) || str2 == null) {
                tIMCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                SNSProfileItem sNSProfileItem = new SNSProfileItem();
                sNSProfileItem.setSIdentifier(str);
                try {
                    bytes = "Tag_SNS_IM_Remark".getBytes("utf-8");
                    try {
                        bArr = str2.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e2) {
                        e = e2;
                        e.printStackTrace();
                        sNSProfileItem.getMpProfiles().set(bytes, bArr);
                        sNSProfileItemVec = new SNSProfileItemVec();
                        sNSProfileItemVec.pushBack(sNSProfileItem);
                        getFriendShipMgr().setSnsProfile(sNSProfileItemVec, new du(this, tIMCallBack));
                    }
                } catch (UnsupportedEncodingException e3) {
                    e = e3;
                    bytes = bArr;
                    e.printStackTrace();
                    sNSProfileItem.getMpProfiles().set(bytes, bArr);
                    sNSProfileItemVec = new SNSProfileItemVec();
                    sNSProfileItemVec.pushBack(sNSProfileItem);
                    getFriendShipMgr().setSnsProfile(sNSProfileItemVec, new du(this, tIMCallBack));
                }
                sNSProfileItem.getMpProfiles().set(bytes, bArr);
                sNSProfileItemVec = new SNSProfileItemVec();
                sNSProfileItemVec.pushBack(sNSProfileItem);
                getFriendShipMgr().setSnsProfile(sNSProfileItemVec, new du(this, tIMCallBack));
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void setGender(TIMFriendGenderType tIMFriendGenderType, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback dtVar = new dt(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                setProfileOption.setGender(FriendGenderType.swigToEnum((int) tIMFriendGenderType.getValue()));
                setProfileOption.setFlag(64);
                getFriendShipMgr().setProfile(setProfileOption, dtVar);
                return;
            }
            tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void setLanguage(long j, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback drVar = new dr(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                setProfileOption.setLanguage(j);
                setProfileOption.setFlag(512);
                getFriendShipMgr().setProfile(setProfileOption, drVar);
                return;
            }
            tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
        }
    }

    public void setLocation(String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (str == null) {
                tIMCallBack.onError(6017, "invalid parameters");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback dsVar = new ds(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                try {
                    setProfileOption.setLocation(str.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                setProfileOption.setFlag(256);
                getFriendShipMgr().setProfile(setProfileOption, dsVar);
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void setNickName(String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (str == null) {
                tIMCallBack.onError(6017, "invalid parameters, nickname is null");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback ciVar = new ci(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                setProfileOption.setFlag(1);
                try {
                    setProfileOption.setNick(str.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                getFriendShipMgr().setProfile(setProfileOption, ciVar);
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }

    public void setSelfSignature(String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (str == null) {
                tIMCallBack.onError(6017, "invalid parameters, signature is null");
            } else if (IMCoreWrapper.get().isReady()) {
                IFriendshipCallback deVar = new de(this, tIMCallBack);
                SetProfileOption setProfileOption = new SetProfileOption();
                setProfileOption.setFlag(32);
                try {
                    setProfileOption.setSelf_signature(str.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                getFriendShipMgr().setProfile(setProfileOption, deVar);
            } else {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            }
        }
    }
}
