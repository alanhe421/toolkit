package com.tencent;

import com.tencent.imcore.BytesMemberInfoParser;
import com.tencent.imcore.BytesProfileMapParser;
import com.tencent.imcore.GroupTipsElem;
import com.tencent.imcore.GroupTipsElem_GroupInfoVec;
import com.tencent.imcore.GroupTipsElem_MemberInfoVec;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TIMGroupTipsElem extends TIMElem {
    private Map<String, TIMGroupMemberInfo> changedGroupMemberInfo;
    private Map<String, TIMUserProfile> changedUserInfo;
    private String groupId;
    private List<TIMGroupTipsElemGroupInfo> groupInfoList;
    private String groupName;
    private List<TIMGroupTipsElemMemberInfo> memberInfoList;
    private long memberNum;
    private TIMGroupMemberInfo opGroupMemberInfo;
    private String opUser;
    private TIMUserProfile opUserInfo;
    private String platform;
    private TIMGroupTipsType tipsType;
    private List<String> userList;

    public TIMGroupTipsElem() {
        this.userList = new ArrayList();
        this.memberNum = 0;
        this.groupInfoList = new ArrayList();
        this.memberInfoList = new ArrayList();
        this.changedUserInfo = new HashMap();
        this.changedGroupMemberInfo = new HashMap();
        this.platform = "";
        this.type = TIMElemType.GroupTips;
    }

    TIMGroupTipsElem(GroupTipsElem groupTipsElem) {
        int i;
        UnsupportedEncodingException unsupportedEncodingException;
        String str;
        String str2;
        BytesProfileMapParser bytesProfileMapParser;
        TIMUserProfile tIMUserProfile;
        BytesMemberInfoParser bytesMemberInfoParser;
        TIMGroupMemberInfo tIMGroupMemberInfo;
        int i2 = 0;
        this.userList = new ArrayList();
        this.memberNum = 0;
        this.groupInfoList = new ArrayList();
        this.memberInfoList = new ArrayList();
        this.changedUserInfo = new HashMap();
        this.changedGroupMemberInfo = new HashMap();
        this.platform = "";
        this.type = TIMElemType.GroupTips;
        TIMGroupTipsType tIMGroupTipsType = TIMGroupTipsType.Join;
        TIMGroupTipsType tIMGroupTipsType2;
        switch (groupTipsElem.getType()) {
            case 1:
                tIMGroupTipsType = TIMGroupTipsType.Join;
                this.memberNum = groupTipsElem.getMember_num();
                break;
            case 2:
                tIMGroupTipsType = TIMGroupTipsType.Quit;
                this.memberNum = groupTipsElem.getMember_num();
                break;
            case 3:
                tIMGroupTipsType = TIMGroupTipsType.Kick;
                this.memberNum = groupTipsElem.getMember_num();
                break;
            case 4:
                tIMGroupTipsType = TIMGroupTipsType.SetAdmin;
                break;
            case 5:
                tIMGroupTipsType = TIMGroupTipsType.CancelAdmin;
                break;
            case 6:
                tIMGroupTipsType2 = TIMGroupTipsType.ModifyGroupInfo;
                GroupTipsElem_GroupInfoVec group_change_list = groupTipsElem.getGroup_change_list();
                for (i = 0; ((long) i) < group_change_list.size(); i++) {
                    TIMGroupTipsElemGroupInfo tIMGroupTipsElemGroupInfo = new TIMGroupTipsElemGroupInfo();
                    tIMGroupTipsElemGroupInfo.setType(group_change_list.get(i).getType().swigValue());
                    try {
                        tIMGroupTipsElemGroupInfo.setContent(new String(group_change_list.get(i).getValue(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    getGroupInfoList().add(tIMGroupTipsElemGroupInfo);
                }
                tIMGroupTipsType = tIMGroupTipsType2;
                break;
            case 7:
                tIMGroupTipsType2 = TIMGroupTipsType.ModifyMemberInfo;
                GroupTipsElem_MemberInfoVec member_change_list = groupTipsElem.getMember_change_list();
                for (i = 0; ((long) i) < member_change_list.size(); i++) {
                    TIMGroupTipsElemMemberInfo tIMGroupTipsElemMemberInfo = new TIMGroupTipsElemMemberInfo();
                    tIMGroupTipsElemMemberInfo.setIdentifier(member_change_list.get(i).getIdentifier());
                    tIMGroupTipsElemMemberInfo.setShutupTime(member_change_list.get(i).getShutup_time());
                    getMemberInfoList().add(tIMGroupTipsElemMemberInfo);
                }
                tIMGroupTipsType = tIMGroupTipsType2;
                break;
        }
        setOpUser(groupTipsElem.getOp_user());
        setTipsType(tIMGroupTipsType);
        String str3 = "";
        String str4 = "";
        String str5 = "";
        try {
            String str6 = new String(groupTipsElem.getGroup_name(), "utf-8");
            try {
                str3 = new String(groupTipsElem.getGroup_id(), "utf-8");
            } catch (UnsupportedEncodingException e2) {
                str3 = str6;
                unsupportedEncodingException = e2;
                str = str4;
                unsupportedEncodingException.printStackTrace();
                str2 = str5;
                str5 = str;
                str = str2;
                setGroupName(str3);
                setGroupId(str5);
                setPlatform(str);
                for (i = 0; ((long) i) < groupTipsElem.getUser_list().size(); i++) {
                    getUserList().add(groupTipsElem.getUser_list().get(i));
                }
                setOpUserInfo(new TIMUserProfile(groupTipsElem.getOp_user_info()));
                setOpGroupMemberInfo(new TIMGroupMemberInfo(groupTipsElem.getOp_group_member_info()));
                bytesProfileMapParser = new BytesProfileMapParser();
                bytesProfileMapParser.fetchMapKeys(groupTipsElem.getChanged_user_info());
                for (i = 0; ((long) i) < bytesProfileMapParser.getKeys().size(); i++) {
                    tIMUserProfile = new TIMUserProfile(bytesProfileMapParser.getValue(groupTipsElem.getChanged_user_info(), i));
                    getChangedUserInfo().put(tIMUserProfile.getIdentifier(), tIMUserProfile);
                }
                bytesMemberInfoParser = new BytesMemberInfoParser();
                bytesMemberInfoParser.fetchMapKeys(groupTipsElem.getChanged_group_member_info());
                while (((long) i2) < bytesMemberInfoParser.getKeys().size()) {
                    tIMGroupMemberInfo = new TIMGroupMemberInfo(bytesMemberInfoParser.getValue(groupTipsElem.getChanged_group_member_info(), i2));
                    getChangedGroupMemberInfo().put(tIMGroupMemberInfo.getUser(), tIMGroupMemberInfo);
                    i2++;
                }
            }
            try {
                str = new String(groupTipsElem.getPlatform(), "utf-8");
                str5 = str3;
                str3 = str6;
            } catch (UnsupportedEncodingException e22) {
                UnsupportedEncodingException unsupportedEncodingException2 = e22;
                str = str3;
                str3 = str6;
                unsupportedEncodingException = unsupportedEncodingException2;
                unsupportedEncodingException.printStackTrace();
                str2 = str5;
                str5 = str;
                str = str2;
                setGroupName(str3);
                setGroupId(str5);
                setPlatform(str);
                for (i = 0; ((long) i) < groupTipsElem.getUser_list().size(); i++) {
                    getUserList().add(groupTipsElem.getUser_list().get(i));
                }
                setOpUserInfo(new TIMUserProfile(groupTipsElem.getOp_user_info()));
                setOpGroupMemberInfo(new TIMGroupMemberInfo(groupTipsElem.getOp_group_member_info()));
                bytesProfileMapParser = new BytesProfileMapParser();
                bytesProfileMapParser.fetchMapKeys(groupTipsElem.getChanged_user_info());
                for (i = 0; ((long) i) < bytesProfileMapParser.getKeys().size(); i++) {
                    tIMUserProfile = new TIMUserProfile(bytesProfileMapParser.getValue(groupTipsElem.getChanged_user_info(), i));
                    getChangedUserInfo().put(tIMUserProfile.getIdentifier(), tIMUserProfile);
                }
                bytesMemberInfoParser = new BytesMemberInfoParser();
                bytesMemberInfoParser.fetchMapKeys(groupTipsElem.getChanged_group_member_info());
                while (((long) i2) < bytesMemberInfoParser.getKeys().size()) {
                    tIMGroupMemberInfo = new TIMGroupMemberInfo(bytesMemberInfoParser.getValue(groupTipsElem.getChanged_group_member_info(), i2));
                    getChangedGroupMemberInfo().put(tIMGroupMemberInfo.getUser(), tIMGroupMemberInfo);
                    i2++;
                }
            }
        } catch (UnsupportedEncodingException e222) {
            unsupportedEncodingException = e222;
            str = str4;
            unsupportedEncodingException.printStackTrace();
            str2 = str5;
            str5 = str;
            str = str2;
            setGroupName(str3);
            setGroupId(str5);
            setPlatform(str);
            for (i = 0; ((long) i) < groupTipsElem.getUser_list().size(); i++) {
                getUserList().add(groupTipsElem.getUser_list().get(i));
            }
            setOpUserInfo(new TIMUserProfile(groupTipsElem.getOp_user_info()));
            setOpGroupMemberInfo(new TIMGroupMemberInfo(groupTipsElem.getOp_group_member_info()));
            bytesProfileMapParser = new BytesProfileMapParser();
            bytesProfileMapParser.fetchMapKeys(groupTipsElem.getChanged_user_info());
            for (i = 0; ((long) i) < bytesProfileMapParser.getKeys().size(); i++) {
                tIMUserProfile = new TIMUserProfile(bytesProfileMapParser.getValue(groupTipsElem.getChanged_user_info(), i));
                getChangedUserInfo().put(tIMUserProfile.getIdentifier(), tIMUserProfile);
            }
            bytesMemberInfoParser = new BytesMemberInfoParser();
            bytesMemberInfoParser.fetchMapKeys(groupTipsElem.getChanged_group_member_info());
            while (((long) i2) < bytesMemberInfoParser.getKeys().size()) {
                tIMGroupMemberInfo = new TIMGroupMemberInfo(bytesMemberInfoParser.getValue(groupTipsElem.getChanged_group_member_info(), i2));
                getChangedGroupMemberInfo().put(tIMGroupMemberInfo.getUser(), tIMGroupMemberInfo);
                i2++;
            }
        }
        setGroupName(str3);
        setGroupId(str5);
        setPlatform(str);
        for (i = 0; ((long) i) < groupTipsElem.getUser_list().size(); i++) {
            getUserList().add(groupTipsElem.getUser_list().get(i));
        }
        setOpUserInfo(new TIMUserProfile(groupTipsElem.getOp_user_info()));
        setOpGroupMemberInfo(new TIMGroupMemberInfo(groupTipsElem.getOp_group_member_info()));
        bytesProfileMapParser = new BytesProfileMapParser();
        bytesProfileMapParser.fetchMapKeys(groupTipsElem.getChanged_user_info());
        for (i = 0; ((long) i) < bytesProfileMapParser.getKeys().size(); i++) {
            tIMUserProfile = new TIMUserProfile(bytesProfileMapParser.getValue(groupTipsElem.getChanged_user_info(), i));
            getChangedUserInfo().put(tIMUserProfile.getIdentifier(), tIMUserProfile);
        }
        bytesMemberInfoParser = new BytesMemberInfoParser();
        bytesMemberInfoParser.fetchMapKeys(groupTipsElem.getChanged_group_member_info());
        while (((long) i2) < bytesMemberInfoParser.getKeys().size()) {
            tIMGroupMemberInfo = new TIMGroupMemberInfo(bytesMemberInfoParser.getValue(groupTipsElem.getChanged_group_member_info(), i2));
            getChangedGroupMemberInfo().put(tIMGroupMemberInfo.getUser(), tIMGroupMemberInfo);
            i2++;
        }
    }

    public Map<String, TIMGroupMemberInfo> getChangedGroupMemberInfo() {
        return this.changedGroupMemberInfo;
    }

    public Map<String, TIMUserProfile> getChangedUserInfo() {
        return this.changedUserInfo;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public List<TIMGroupTipsElemGroupInfo> getGroupInfoList() {
        return this.groupInfoList;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public List<TIMGroupTipsElemMemberInfo> getMemberInfoList() {
        return this.memberInfoList;
    }

    public long getMemberNum() {
        return this.memberNum;
    }

    public TIMGroupMemberInfo getOpGroupMemberInfo() {
        return this.opGroupMemberInfo;
    }

    public String getOpUser() {
        return this.opUser;
    }

    public TIMUserProfile getOpUserInfo() {
        return this.opUserInfo;
    }

    public String getPlatform() {
        return this.platform;
    }

    public TIMGroupTipsType getTipsType() {
        return this.tipsType;
    }

    public List<String> getUserList() {
        return this.userList;
    }

    void setGroupId(String str) {
        this.groupId = str;
    }

    void setGroupName(String str) {
        this.groupName = str;
    }

    void setMemberNum(long j) {
        this.memberNum = j;
    }

    void setOpGroupMemberInfo(TIMGroupMemberInfo tIMGroupMemberInfo) {
        this.opGroupMemberInfo = tIMGroupMemberInfo;
    }

    void setOpUser(String str) {
        this.opUser = str;
    }

    void setOpUserInfo(TIMUserProfile tIMUserProfile) {
        this.opUserInfo = tIMUserProfile;
    }

    void setPlatform(String str) {
        this.platform = str;
    }

    void setTipsType(TIMGroupTipsType tIMGroupTipsType) {
        this.tipsType = tIMGroupTipsType;
    }
}
