package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.BytesMap;
import com.tencent.imcore.BytesVec;
import com.tencent.imcore.ComStatus;
import com.tencent.imcore.GetGroupBaseInfoOption;
import com.tencent.imcore.GroupBaseInfoVec;
import com.tencent.imcore.GroupDetailInfoVec;
import com.tencent.imcore.GroupManager;
import com.tencent.imcore.GroupMemRoleFilter;
import com.tencent.imcore.GroupMemberInfoVec;
import com.tencent.imcore.GroupMemberResultVec;
import com.tencent.imcore.GroupPendencyItemVec;
import com.tencent.imcore.GroupPendencyMeta;
import com.tencent.imcore.ICreateGroupCallback;
import com.tencent.imcore.IGroupGetPendencyCallback;
import com.tencent.imcore.IGroupInfoListCallback;
import com.tencent.imcore.IGroupInfoListCallbackV2;
import com.tencent.imcore.IGroupListCallback;
import com.tencent.imcore.IGroupMemberCallback;
import com.tencent.imcore.IGroupMemberCallbackV2;
import com.tencent.imcore.IGroupMemberResultCallback;
import com.tencent.imcore.IGroupNotifyCallback;
import com.tencent.imcore.MemberResult;
import com.tencent.imcore.ModifyGroupBaseInfoOption;
import com.tencent.imcore.ModifyGroupFlag;
import com.tencent.imcore.ModifyGroupMemberInfoOption;
import com.tencent.imcore.NewGroupInfo;
import com.tencent.imcore.NewGroupMemVec;
import com.tencent.imcore.NewGroupMemberInfo;
import com.tencent.imcore.QrEventType;
import com.tencent.imcore.StrVec;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.util.QualityReportHelper;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class TIMGroupManager {
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_ADD_OPTION = 8192;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_APP_ID = 128;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_CREATE_TIME = 2;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_FACE_URL = 4096;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_GROUP_TYPE = 16384;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_INTRODUCTION = 2048;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_LAST_MSG = 32768;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_LAST_MSG_TIME = 64;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_MAX_MEMBER_NUM = 512;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_MEMBER_NUM = 256;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_NAME = 1;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_NEXT_MSG_SEQ = 32;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_NOTIFICATION = 1024;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_OWNER_UIN = 4;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_SEQ = 8;
    public static final int TIM_GET_GROUP_BASE_INFO_FLAG_TIME = 16;
    public static final int TIM_GET_GROUP_MEM_INFO_FLAG_JOIN_TIME = 1;
    public static final int TIM_GET_GROUP_MEM_INFO_FLAG_MSG_FLAG = 2;
    public static final int TIM_GET_GROUP_MEM_INFO_FLAG_NAME_CARD = 32;
    public static final int TIM_GET_GROUP_MEM_INFO_FLAG_ROLE_INFO = 8;
    public static final int TIM_GET_GROUP_MEM_INFO_FLAG_SHUTUP_TIME = 16;
    static String defaultId = "";
    static ConcurrentHashMap<String, TIMGroupManager> mutiMap = new ConcurrentHashMap();
    private static final String tag = "MSF.C.TIMGroupManager";
    private String identifier = "";

    public abstract class CreateGroupCallBack extends ICreateGroupCallback {
        public TIMValueCallBack<String> cb;

        public CreateGroupCallBack(TIMValueCallBack<String> tIMValueCallBack) {
            swigReleaseOwnership();
            this.cb = tIMValueCallBack;
        }

        public void done(String str) {
            IMMsfCoreProxy.mainHandler.post(new fy(this, str));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new fz(this, i, str));
            swigTakeOwnership();
        }

        public abstract void onDone(String str);

        public abstract void onFail(int i, String str);
    }

    public class CreateGroupParam {
        TIMGroupAddOpt addOption;
        Map<String, byte[]> customInfo = new HashMap();
        String faceUrl;
        String groupId;
        String groupName;
        String groupType;
        String introduction;
        long maxMemberNum = 0;
        List<TIMGroupMemberInfo> members;
        String notification;

        public void setAddOption(TIMGroupAddOpt tIMGroupAddOpt) {
            this.addOption = tIMGroupAddOpt;
        }

        public void setCustomInfo(String str, byte[] bArr) {
            this.customInfo.put(str, bArr);
        }

        public void setFaceUrl(String str) {
            this.faceUrl = str;
        }

        public void setGroupId(String str) {
            this.groupId = str;
        }

        public void setGroupName(String str) {
            this.groupName = str;
        }

        public void setGroupType(String str) {
            this.groupType = str;
        }

        public void setIntroduction(String str) {
            this.introduction = str;
        }

        public void setMaxMemberNum(long j) {
            this.maxMemberNum = j;
        }

        public void setMembers(List<TIMGroupMemberInfo> list) {
            this.members = list;
        }

        public void setNotification(String str) {
            this.notification = str;
        }
    }

    abstract class aa extends IGroupInfoListCallback {
        public TIMValueCallBack<List<TIMGroupDetailInfo>> a;

        public aa(TIMGroupManager tIMGroupManager, TIMValueCallBack<List<TIMGroupDetailInfo>> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(List<TIMGroupDetailInfo> list);

        public void done(GroupDetailInfoVec groupDetailInfoVec) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; ((long) i) < groupDetailInfoVec.size(); i++) {
                arrayList.add(new TIMGroupDetailInfo(groupDetailInfoVec.get(i)));
            }
            IMMsfCoreProxy.mainHandler.post(new ga(this, arrayList));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gb(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ab extends IGroupInfoListCallbackV2 {
        public TIMValueCallBack<TIMGroupSearchSucc> a;

        public ab(TIMGroupManager tIMGroupManager, TIMValueCallBack<TIMGroupSearchSucc> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(TIMGroupSearchSucc tIMGroupSearchSucc);

        public void done(long j, GroupDetailInfoVec groupDetailInfoVec) {
            TIMGroupSearchSucc tIMGroupSearchSucc = new TIMGroupSearchSucc();
            tIMGroupSearchSucc.totalNum = j;
            tIMGroupSearchSucc.infoList = new ArrayList();
            for (int i = 0; ((long) i) < groupDetailInfoVec.size(); i++) {
                tIMGroupSearchSucc.infoList.add(new TIMGroupDetailInfo(groupDetailInfoVec.get(i)));
            }
            IMMsfCoreProxy.mainHandler.post(new gc(this, tIMGroupSearchSucc));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gd(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ac extends IGroupListCallback {
        public TIMValueCallBack<List<TIMGroupBaseInfo>> a;

        public ac(TIMGroupManager tIMGroupManager, TIMValueCallBack<List<TIMGroupBaseInfo>> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(List<TIMGroupBaseInfo> list);

        public final void done(GroupBaseInfoVec groupBaseInfoVec) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; ((long) i) < groupBaseInfoVec.size(); i++) {
                arrayList.add(new TIMGroupBaseInfo(groupBaseInfoVec.get(i)));
            }
            IMMsfCoreProxy.mainHandler.post(new ge(this, arrayList));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gf(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ad extends IGroupMemberCallback {
        public TIMValueCallBack<List<TIMGroupMemberInfo>> a;

        public ad(TIMGroupManager tIMGroupManager, TIMValueCallBack<List<TIMGroupMemberInfo>> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(List<TIMGroupMemberInfo> list);

        public void done(GroupMemberInfoVec groupMemberInfoVec) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; ((long) i) < groupMemberInfoVec.size(); i++) {
                arrayList.add(new TIMGroupMemberInfo(groupMemberInfoVec.get(i)));
            }
            IMMsfCoreProxy.mainHandler.post(new gg(this, arrayList));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gh(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ae extends IGroupMemberCallbackV2 {
        public TIMValueCallBack<TIMGroupMemberSuccV2> a;

        public ae(TIMGroupManager tIMGroupManager, TIMValueCallBack<TIMGroupMemberSuccV2> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(TIMGroupMemberSuccV2 tIMGroupMemberSuccV2);

        public void done(long j, GroupMemberInfoVec groupMemberInfoVec) {
            List arrayList = new ArrayList();
            for (int i = 0; ((long) i) < groupMemberInfoVec.size(); i++) {
                arrayList.add(new TIMGroupMemberInfo(groupMemberInfoVec.get(i)));
            }
            TIMGroupMemberSuccV2 tIMGroupMemberSuccV2 = new TIMGroupMemberSuccV2();
            tIMGroupMemberSuccV2.setNextSeq(j);
            tIMGroupMemberSuccV2.setMemberInfoList(arrayList);
            IMMsfCoreProxy.mainHandler.post(new gi(this, tIMGroupMemberSuccV2));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gj(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class af extends IGroupMemberResultCallback {
        public TIMValueCallBack<List<TIMGroupMemberResult>> a;

        public af(TIMGroupManager tIMGroupManager, TIMValueCallBack<List<TIMGroupMemberResult>> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(List<TIMGroupMemberResult> list);

        public void done(GroupMemberResultVec groupMemberResultVec) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; ((long) i) < groupMemberResultVec.size(); i++) {
                MemberResult memberResult = groupMemberResultVec.get(i);
                TIMGroupMemberResult tIMGroupMemberResult = new TIMGroupMemberResult();
                tIMGroupMemberResult.setUser(memberResult.getUser());
                tIMGroupMemberResult.setResult(memberResult.getStatus());
                arrayList.add(tIMGroupMemberResult);
            }
            IMMsfCoreProxy.mainHandler.post(new gk(this, arrayList));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gl(this, i, str));
            swigTakeOwnership();
        }
    }

    private enum ag {
        Invalid(0),
        Name(1),
        Brief(2),
        Option(3),
        Notice(4),
        Icon(5);
        
        int f;

        static {
            Invalid = new ag("Invalid", 0, 0);
            Name = new ag("Name", 1, 1);
            Brief = new ag("Brief", 2, 2);
            Option = new ag("Option", 3, 3);
            Notice = new ag("Notice", 4, 4);
            Icon = new ag("Icon", 5, 5);
            ag[] agVarArr = new ag[]{Invalid, Name, Brief, Option, Notice, Icon};
        }

        private ag(int i) {
            this.f = 0;
            this.f = i;
        }
    }

    abstract class ah extends IGroupNotifyCallback {
        public TIMCallBack a;

        public ah(TIMGroupManager tIMGroupManager, TIMCallBack tIMCallBack) {
            swigReleaseOwnership();
            this.a = tIMCallBack;
        }

        public abstract void a();

        public abstract void a(int i, String str);

        public void done() {
            IMMsfCoreProxy.mainHandler.post(new gm(this));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gn(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ai extends IGroupGetPendencyCallback {
        public TIMValueCallBack<TIMGroupPendencyListGetSucc> a;
        private /* synthetic */ TIMGroupManager b;

        public ai(TIMGroupManager tIMGroupManager, TIMValueCallBack<TIMGroupPendencyListGetSucc> tIMValueCallBack) {
            this.b = tIMGroupManager;
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(TIMGroupPendencyListGetSucc tIMGroupPendencyListGetSucc);

        public void done(GroupPendencyMeta groupPendencyMeta, GroupPendencyItemVec groupPendencyItemVec) {
            IMMsfCoreProxy.mainHandler.post(new go(this, new TIMGroupPendencyListGetSucc(this.b.identifier, groupPendencyMeta, groupPendencyItemVec)));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gp(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class aj<T> extends IGroupMemberCallback {
        public TIMValueCallBack<T> a;

        public aj(TIMGroupManager tIMGroupManager, TIMValueCallBack<T> tIMValueCallBack) {
            swigReleaseOwnership();
            this.a = tIMValueCallBack;
        }

        public abstract void a(int i, String str);

        public abstract void a(List<TIMGroupSelfInfo> list);

        public void done(GroupMemberInfoVec groupMemberInfoVec) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; ((long) i) < groupMemberInfoVec.size(); i++) {
                arrayList.add(new TIMGroupSelfInfo(groupMemberInfoVec.get(i)));
            }
            IMMsfCoreProxy.mainHandler.post(new gq(this, arrayList));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new gr(this, i, str));
            swigTakeOwnership();
        }
    }

    private enum ak {
        RecvMsgOpt(1),
        Role(2),
        Silence(3),
        NameCard(4);
        
        private long e;

        static {
            RecvMsgOpt = new ak("RecvMsgOpt", 0, 1);
            Role = new ak("Role", 1, 2);
            Silence = new ak("Silence", 2, 3);
            NameCard = new ak("NameCard", 3, 4);
            ak[] akVarArr = new ak[]{RecvMsgOpt, Role, Silence, NameCard};
        }

        private ak(long j) {
            this.e = j;
        }

        final long a() {
            return this.e;
        }

        final long b() {
            return (long) (1 << ((int) (this.e - 1)));
        }
    }

    private TIMGroupManager(String str) {
        this.identifier = str;
    }

    private GroupManager getGroupManager() {
        return TextUtils.isEmpty(this.identifier) ? TIMManager.getInstance().getCoreUser().getGroupMgr() : TIMManager.getInstanceById(this.identifier).getCoreUser().getGroupMgr();
    }

    public static TIMGroupManager getInstance() {
        return getInstanceById(TIMManager.getInstance().getIdentification());
    }

    public static TIMGroupManager getInstanceById(String str) {
        return new TIMGroupManager(str);
    }

    public void applyJoinGroup(String str, String str2, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        if (preCheck(TextUtils.isEmpty(str), tIMCallBack, QrEventType.kEventJoinGroup, qualityReportHelper)) {
            if (str2 == null) {
                str2 = "";
            }
            IGroupNotifyCallback fxVar = new fx(this, tIMCallBack, qualityReportHelper);
            QLog.i(tag, 1, "JoinGroup|1-Begin|Succ|group id=" + str);
            getGroupManager().applyJoinGroup(str, str2, fxVar);
        }
    }

    public void createAVChatroomGroup(String str, TIMValueCallBack<String> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        if (preCheck(TextUtils.isEmpty(str), tIMValueCallBack, QrEventType.kEventCreateGroup, qualityReportHelper)) {
            byte[] bArr = null;
            try {
                bArr = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getGroupManager().createGroup("AVChatRoom", new StrVec(), bArr, new ey(this, tIMValueCallBack, qualityReportHelper));
        }
    }

    public void createGroup(CreateGroupParam createGroupParam, TIMValueCallBack<String> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = createGroupParam == null || TextUtils.isEmpty(createGroupParam.groupType) || TextUtils.isEmpty(createGroupParam.groupName);
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventCreateGroup, qualityReportHelper)) {
            NewGroupInfo newGroupInfo = new NewGroupInfo();
            newGroupInfo.setGroup_type(createGroupParam.groupType);
            try {
                newGroupInfo.setGroup_name(createGroupParam.groupName.getBytes("utf-8"));
                if (createGroupParam.groupId != null) {
                    newGroupInfo.setGroup_id(createGroupParam.groupId.getBytes("utf-8"));
                }
                if (createGroupParam.notification != null) {
                    newGroupInfo.setNotification(createGroupParam.notification.getBytes("utf-8"));
                }
                if (createGroupParam.introduction != null) {
                    newGroupInfo.setIntroduction(createGroupParam.introduction.getBytes("utf-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (createGroupParam.faceUrl != null) {
                newGroupInfo.setFace_url(createGroupParam.faceUrl);
            }
            if (createGroupParam.addOption != null) {
                newGroupInfo.setSet_add_option(true);
                newGroupInfo.setAdd_option((long) createGroupParam.addOption.ordinal());
            }
            if (createGroupParam.maxMemberNum != 0) {
                newGroupInfo.setMax_member_num(createGroupParam.maxMemberNum);
            }
            if (createGroupParam.customInfo.size() > 0) {
                BytesMap bytesMap = new BytesMap();
                for (Entry entry : createGroupParam.customInfo.entrySet()) {
                    try {
                        bytesMap.set(((String) entry.getKey()).getBytes("utf-8"), (byte[]) entry.getValue());
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                }
                newGroupInfo.setBase_custom_info(bytesMap);
            }
            if (createGroupParam.members != null) {
                NewGroupMemVec newGroupMemVec = new NewGroupMemVec();
                for (TIMGroupMemberInfo tIMGroupMemberInfo : createGroupParam.members) {
                    NewGroupMemberInfo newGroupMemberInfo = new NewGroupMemberInfo();
                    newGroupMemberInfo.setIdentifier(tIMGroupMemberInfo.getUser());
                    newGroupMemberInfo.setMember_role((int) tIMGroupMemberInfo.getRole().getValue());
                    if (tIMGroupMemberInfo.getCustomInfo().size() > 0) {
                        BytesMap bytesMap2 = new BytesMap();
                        for (Entry entry2 : tIMGroupMemberInfo.getCustomInfo().entrySet()) {
                            try {
                                bytesMap2.set(((String) entry2.getKey()).getBytes("utf-8"), (byte[]) entry2.getValue());
                            } catch (UnsupportedEncodingException e22) {
                                e22.printStackTrace();
                            }
                        }
                        newGroupMemberInfo.setCustom_info(bytesMap2);
                    }
                    newGroupMemVec.pushBack(newGroupMemberInfo);
                }
                newGroupInfo.setGroup_members(newGroupMemVec);
            }
            TIMManager.getInstanceById(this.identifier).getCoreUser().getGroupMgr().createGroup(newGroupInfo, new en(this, tIMValueCallBack, qualityReportHelper));
        }
    }

    public void createGroup(String str, List<String> list, String str2, TIMValueCallBack<String> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = list == null || list.isEmpty() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventCreateGroup, qualityReportHelper)) {
            StrVec strVec = new StrVec();
            for (String str3 : list) {
                if (!TextUtils.isEmpty(str3)) {
                    strVec.pushBack(str3);
                }
            }
            ICreateGroupCallback fjVar = new fj(this, tIMValueCallBack, qualityReportHelper);
            byte[] bArr = null;
            try {
                bArr = str2.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getGroupManager().createGroup(str, strVec, bArr, fjVar);
        }
    }

    public void createGroup(String str, List<String> list, String str2, String str3, TIMValueCallBack<String> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3);
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventCreateGroup, qualityReportHelper)) {
            NewGroupMemVec newGroupMemVec = new NewGroupMemVec();
            for (String str4 : list) {
                NewGroupMemberInfo newGroupMemberInfo = new NewGroupMemberInfo();
                newGroupMemberInfo.setIdentifier(str4);
                newGroupMemVec.pushBack(newGroupMemberInfo);
            }
            ICreateGroupCallback fsVar = new fs(this, tIMValueCallBack, qualityReportHelper);
            NewGroupInfo newGroupInfo = new NewGroupInfo();
            try {
                newGroupInfo.setGroup_name(str2.getBytes("utf-8"));
                newGroupInfo.setGroup_id(str3.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            newGroupInfo.setGroup_type(str);
            newGroupInfo.setGroup_members(newGroupMemVec);
            getGroupManager().createGroup(newGroupInfo, fsVar);
        }
    }

    public void deleteGroup(String str, TIMCallBack tIMCallBack) {
        if (preCheck(TextUtils.isEmpty(str), tIMCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            getGroupManager().deleteGroup(str, new ft(this, tIMCallBack));
        }
    }

    public void deleteGroupMember(String str, List<String> list, TIMValueCallBack<List<TIMGroupMemberResult>> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || list == null || list.isEmpty();
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupMemberResultCallback fuVar = new fu(this, tIMValueCallBack);
            StrVec strVec = new StrVec();
            for (String str2 : list) {
                if (!TextUtils.isEmpty(str2)) {
                    strVec.pushBack(str2);
                }
            }
            getGroupManager().deleteGroupMember(str, strVec, fuVar);
        }
    }

    public void deleteGroupMemberWithReason(String str, String str2, List<String> list, TIMValueCallBack<List<TIMGroupMemberResult>> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || list == null || list.isEmpty();
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupMemberResultCallback fvVar = new fv(this, tIMValueCallBack);
            StrVec strVec = new StrVec();
            for (String str3 : list) {
                if (!TextUtils.isEmpty(str3)) {
                    strVec.pushBack(str3);
                }
            }
            byte[] bArr = null;
            try {
                bArr = str2.getBytes("utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            getGroupManager().deleteGroupMember(str, strVec, fvVar, bArr);
        }
    }

    public void getGroupDetailInfo(List<String> list, TIMValueCallBack<List<TIMGroupDetailInfo>> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = list == null || list.isEmpty();
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupInfoListCallback faVar = new fa(this, tIMValueCallBack);
            StrVec strVec = new StrVec();
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    strVec.pushBack(str);
                }
            }
            getGroupManager().getGroupBaseInfo(strVec, faVar);
        }
    }

    public void getGroupFTDetailInfo(List<String> list, TIMValueCallBack<List<TIMGroupDetailInfo>> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = list == null || list.isEmpty();
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupInfoListCallback fbVar = new fb(this, tIMValueCallBack);
            GetGroupBaseInfoOption getGroupBaseInfoOption = new GetGroupBaseInfoOption();
            getGroupBaseInfoOption.setFlag(-1);
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    getGroupBaseInfoOption.getGroups().pushBack(str);
                }
            }
            getGroupManager().getGroupBaseInfo(getGroupBaseInfoOption, fbVar);
        }
    }

    public void getGroupList(TIMValueCallBack<List<TIMGroupBaseInfo>> tIMValueCallBack) {
        if (preCheck(false, tIMValueCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            getGroupManager().getGroupList(false, new ep(this, tIMValueCallBack));
        }
    }

    public void getGroupMembers(String str, TIMValueCallBack<List<TIMGroupMemberInfo>> tIMValueCallBack) {
        if (preCheck(TextUtils.isEmpty(str), tIMValueCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            getGroupManager().getGroupMembers(str, new fl(this, tIMValueCallBack));
        }
    }

    public void getGroupMembersByFilter(String str, long j, TIMGroupMemberRoleFilter tIMGroupMemberRoleFilter, List<String> list, long j2, TIMValueCallBack<TIMGroupMemberSuccV2> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || tIMGroupMemberRoleFilter == null;
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupMemberCallbackV2 foVar = new fo(this, tIMValueCallBack);
            BytesVec bytesVec = new BytesVec();
            if (list != null) {
                for (String bytes : list) {
                    try {
                        bytesVec.pushBack(bytes.getBytes("utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            GroupMemRoleFilter groupMemRoleFilter = GroupMemRoleFilter.kGroupMemberAll;
            if (tIMGroupMemberRoleFilter == TIMGroupMemberRoleFilter.Owner) {
                groupMemRoleFilter = GroupMemRoleFilter.kGroupMemRoleOwner;
            } else if (tIMGroupMemberRoleFilter == TIMGroupMemberRoleFilter.Admin) {
                groupMemRoleFilter = GroupMemRoleFilter.kGroupMemRoleAdmin;
            } else if (tIMGroupMemberRoleFilter == TIMGroupMemberRoleFilter.Normal) {
                groupMemRoleFilter = GroupMemRoleFilter.kGroupMemRoleCommon_member;
            }
            getGroupManager().getGroupMembersByFilter(str, j, groupMemRoleFilter, bytesVec, j2, foVar);
        }
    }

    public void getGroupMembersInfo(String str, List<String> list, TIMValueCallBack<List<TIMGroupMemberInfo>> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || list == null || list.isEmpty();
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupMemberCallback fmVar = new fm(this, tIMValueCallBack);
            StrVec strVec = new StrVec();
            for (String str2 : list) {
                if (!TextUtils.isEmpty(str2)) {
                    strVec.pushBack(str2);
                }
            }
            getGroupManager().getGroupMembersInfo(str, strVec, fmVar);
        }
    }

    public void getGroupMembersV2(String str, long j, List<String> list, long j2, TIMValueCallBack<TIMGroupMemberSuccV2> tIMValueCallBack) {
        if (preCheck(TextUtils.isEmpty(str), tIMValueCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            IGroupMemberCallbackV2 fnVar = new fn(this, tIMValueCallBack);
            BytesVec bytesVec = new BytesVec();
            if (list != null) {
                for (String bytes : list) {
                    try {
                        bytesVec.pushBack(bytes.getBytes("utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            getGroupManager().getGroupMembersV2(str, j, bytesVec, j2, fnVar);
        }
    }

    public void getGroupPendencyList(TIMGroupPendencyGetParam tIMGroupPendencyGetParam, TIMValueCallBack<TIMGroupPendencyListGetSucc> tIMValueCallBack) {
        if (preCheck(tIMGroupPendencyGetParam == null, tIMValueCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            getGroupManager().getPendency(tIMGroupPendencyGetParam.toOption(), new fq(this, tIMValueCallBack));
        }
    }

    public void getGroupPublicInfo(List<String> list, TIMValueCallBack<List<TIMGroupDetailInfo>> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = list == null || list.isEmpty();
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupInfoListCallback fdVar = new fd(this, tIMValueCallBack);
            StrVec strVec = new StrVec();
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    strVec.pushBack(str);
                }
            }
            getGroupManager().getGroupPublicInfo(strVec, fdVar);
        }
    }

    public void getGroupPublicInfoV2(List<String> list, long j, List<String> list2, TIMValueCallBack<List<TIMGroupDetailInfo>> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = list == null || list.isEmpty();
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupInfoListCallback feVar = new fe(this, tIMValueCallBack);
            StrVec strVec = new StrVec();
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    strVec.pushBack(str);
                }
            }
            BytesVec bytesVec = new BytesVec();
            if (list2 != null) {
                for (String str2 : list2) {
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            bytesVec.pushBack(str2.getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            getGroupManager().getGroupPublicInfoV2(strVec, j, bytesVec, feVar);
        }
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void getSelfInfo(String str, TIMValueCallBack<TIMGroupSelfInfo> tIMValueCallBack) {
        if (preCheck(TextUtils.isEmpty(str), tIMValueCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            getGroupManager().getSelfInfo(str, new fp(this, tIMValueCallBack));
        }
    }

    public void inviteGroupMember(String str, List<String> list, TIMValueCallBack<List<TIMGroupMemberResult>> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || list == null || list.isEmpty();
        if (preCheck(z, tIMValueCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupMemberResultCallback fwVar = new fw(this, tIMValueCallBack);
            StrVec strVec = new StrVec();
            for (String str2 : list) {
                if (!TextUtils.isEmpty(str2)) {
                    strVec.pushBack(str2);
                }
            }
            getGroupManager().inviteGroupMember(str, strVec, fwVar);
        }
    }

    public void modifyGroupAddOpt(String str, TIMGroupAddOpt tIMGroupAddOpt, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || tIMGroupAddOpt == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            getGroupManager().modifyGroupBaseInfo(str, (long) ag.Option.ordinal(), "".getBytes(), (long) tIMGroupAddOpt.ordinal(), new ev(this, tIMCallBack));
        }
    }

    public void modifyGroupCustomInfo(String str, String str2, byte[] bArr, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || bArr == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            ModifyGroupBaseInfoOption modifyGroupBaseInfoOption = new ModifyGroupBaseInfoOption();
            modifyGroupBaseInfoOption.setGroup_id(str);
            BytesMap bytesMap = new BytesMap();
            try {
                bytesMap.set(str2.getBytes("utf-8"), bArr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            modifyGroupBaseInfoOption.setCustom_info(bytesMap);
            getGroupManager().modifyGroupBaseInfo(modifyGroupBaseInfoOption, new ew(this, tIMCallBack));
        }
    }

    public void modifyGroupFaceUrl(String str, String str2, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || str2 == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupNotifyCallback etVar = new et(this, tIMCallBack);
            byte[] bArr = null;
            try {
                bArr = str2.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getGroupManager().modifyGroupBaseInfo(str, (long) ag.Icon.f, bArr, 0, etVar);
        }
    }

    public void modifyGroupIntroduction(String str, String str2, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || str2 == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupNotifyCallback erVar = new er(this, tIMCallBack);
            byte[] bArr = null;
            try {
                bArr = str2.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getGroupManager().modifyGroupBaseInfo(str, (long) ag.Brief.f, bArr, 0, erVar);
        }
    }

    public void modifyGroupMemberInfoSetCustomInfo(String str, String str2, String str3, byte[] bArr, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || bArr == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            ModifyGroupMemberInfoOption modifyGroupMemberInfoOption = new ModifyGroupMemberInfoOption();
            modifyGroupMemberInfoOption.setGroup_id(str);
            modifyGroupMemberInfoOption.setMember(str2);
            try {
                BytesMap bytesMap = new BytesMap();
                bytesMap.set(str3.getBytes("utf-8"), bArr);
                modifyGroupMemberInfoOption.setCustom_info(bytesMap);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getGroupManager().modifyGroupMemberInfo(modifyGroupMemberInfoOption, new fi(this, tIMCallBack));
        }
    }

    public void modifyGroupMemberInfoSetNameCard(String str, String str2, String str3, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str3 == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            ModifyGroupMemberInfoOption modifyGroupMemberInfoOption = new ModifyGroupMemberInfoOption();
            modifyGroupMemberInfoOption.setGroup_id(str);
            modifyGroupMemberInfoOption.setMember(str2);
            modifyGroupMemberInfoOption.setFlag(ak.NameCard.b());
            byte[] bArr = null;
            try {
                bArr = str3.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            modifyGroupMemberInfoOption.setName_card(bArr);
            getGroupManager().modifyGroupMemberInfo(modifyGroupMemberInfoOption, new fh(this, tIMCallBack));
        }
    }

    public void modifyGroupMemberInfoSetRole(String str, String str2, TIMGroupMemberRoleType tIMGroupMemberRoleType, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || tIMGroupMemberRoleType == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            getGroupManager().modifyGroupMemberInfo(str2, str, ak.Role.a(), tIMGroupMemberRoleType.getValue(), new ff(this, tIMCallBack));
        }
    }

    public void modifyGroupMemberInfoSetSilence(String str, String str2, long j, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            getGroupManager().modifyGroupMemberInfo(str2, str, ak.Silence.a(), j, new fg(this, tIMCallBack));
        }
    }

    public void modifyGroupName(String str, String str2, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupNotifyCallback eqVar = new eq(this, tIMCallBack);
            byte[] bArr = null;
            try {
                bArr = str2.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getGroupManager().modifyGroupBaseInfo(str, (long) ag.Name.f, bArr, 0, eqVar);
        }
    }

    public void modifyGroupNotification(String str, String str2, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || str2 == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupNotifyCallback esVar = new es(this, tIMCallBack);
            byte[] bArr = null;
            try {
                bArr = str2.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getGroupManager().modifyGroupBaseInfo(str, (long) ag.Notice.f, bArr, 0, esVar);
        }
    }

    public void modifyGroupOwner(String str, String str2, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            getGroupManager().modifyGroupOwner(str, str2, new eu(this, tIMCallBack));
        }
    }

    public void modifyGroupSearchable(String str, boolean z, TIMCallBack tIMCallBack) {
        if (preCheck(TextUtils.isEmpty(str), tIMCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            ModifyGroupBaseInfoOption modifyGroupBaseInfoOption = new ModifyGroupBaseInfoOption();
            modifyGroupBaseInfoOption.setGroup_id(str);
            modifyGroupBaseInfoOption.setFlag((long) ModifyGroupFlag.kModifyGroupSearchable.swigValue());
            modifyGroupBaseInfoOption.setSearchable(z ? ComStatus.kOpen : ComStatus.kClose);
            getGroupManager().modifyGroupBaseInfo(modifyGroupBaseInfoOption, new ez(this, tIMCallBack));
        }
    }

    public void modifyGroupVisible(String str, boolean z, TIMCallBack tIMCallBack) {
        if (preCheck(TextUtils.isEmpty(str), tIMCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            ModifyGroupBaseInfoOption modifyGroupBaseInfoOption = new ModifyGroupBaseInfoOption();
            modifyGroupBaseInfoOption.setGroup_id(str);
            modifyGroupBaseInfoOption.setFlag((long) ModifyGroupFlag.kModifyGroupVisible.swigValue());
            modifyGroupBaseInfoOption.setVisible(z ? ComStatus.kOpen : ComStatus.kClose);
            getGroupManager().modifyGroupBaseInfo(modifyGroupBaseInfoOption, new ex(this, tIMCallBack));
        }
    }

    public void modifyReceiveMessageOpt(String str, TIMGroupReceiveMessageOpt tIMGroupReceiveMessageOpt, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        boolean z = TextUtils.isEmpty(str) || tIMGroupReceiveMessageOpt == null;
        if (preCheck(z, tIMCallBack, QrEventType.kEventMax, qualityReportHelper)) {
            IGroupNotifyCallback fkVar = new fk(this, tIMCallBack);
            String str2 = this.identifier;
            if (TextUtils.isEmpty(this.identifier)) {
                str2 = TIMManager.getInstance().getIdentification();
            }
            getGroupManager().modifyGroupMemberInfo(str2, str, ak.RecvMsgOpt.a(), tIMGroupReceiveMessageOpt.getValue(), fkVar);
        }
    }

    protected boolean preCheck(boolean z, Object obj, QrEventType qrEventType, QualityReportHelper qualityReportHelper) {
        if (obj == null) {
            return false;
        }
        IMErrInfo iMErrInfo;
        if (z) {
            iMErrInfo = new IMErrInfo(6017, "invalid param.");
            if (obj instanceof TIMValueCallBack) {
                ((TIMValueCallBack) obj).onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            } else if (obj instanceof TIMCallBack) {
                ((TIMCallBack) obj).onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            }
            if (qrEventType == QrEventType.kEventMax) {
                return false;
            }
            qualityReportHelper.init(qrEventType.swigValue(), iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
            return false;
        } else if (IMCoreWrapper.get().isReady()) {
            return true;
        } else {
            iMErrInfo = new IMErrInfo(6013, "sdk not initialized or not logged in.");
            if (obj instanceof TIMValueCallBack) {
                ((TIMValueCallBack) obj).onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            } else if (obj instanceof TIMCallBack) {
                ((TIMCallBack) obj).onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            }
            if (qrEventType == QrEventType.kEventMax) {
                return false;
            }
            qualityReportHelper.init(qrEventType.swigValue(), iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
            return false;
        }
    }

    public void quitGroup(String str, TIMCallBack tIMCallBack) {
        if (preCheck(TextUtils.isEmpty(str), tIMCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            getGroupManager().quitGroup(str, new eo(this, tIMCallBack));
        }
    }

    public void reportGroupPendency(long j, TIMCallBack tIMCallBack) {
        if (preCheck(false, tIMCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            getGroupManager().pendencyReport(j, new fr(this, tIMCallBack));
        }
    }

    public void searchGroup(String str, long j, List<String> list, int i, int i2, TIMValueCallBack<TIMGroupSearchSucc> tIMValueCallBack) {
        if (preCheck(TextUtils.isEmpty(str), tIMValueCallBack, QrEventType.kEventMax, new QualityReportHelper())) {
            IGroupInfoListCallbackV2 fcVar = new fc(this, tIMValueCallBack);
            BytesVec bytesVec = new BytesVec();
            if (list != null) {
                for (String bytes : list) {
                    try {
                        bytesVec.pushBack(bytes.getBytes("utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            getGroupManager().searchGroupByName(str, j, bytesVec, (long) i, (long) i2, fcVar);
        }
    }
}
