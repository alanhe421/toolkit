package com.tencent;

import com.dynamicload.Lib.DLConstants;
import com.tencent.imcore.ComStatus;
import com.tencent.imcore.GroupDetailInfo;
import com.tencent.imcore.MapKeyFetcher;
import com.tencent.imsdk.QLog;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TIMGroupDetailInfo {
    private static final String tag = "TIMGroupDetailInfo";
    private long createTime;
    private Map<String, byte[]> custom = new HashMap();
    TIMGroupAddOpt groupAddOpt = TIMGroupAddOpt.TIM_GROUP_ADD_AUTH;
    private String groupFaceUrl = "";
    private String groupId = "";
    private String groupIntroduction = "";
    private String groupName = "";
    private String groupNotice = "";
    private String groupOwner = "";
    private String groupType = "";
    private long lastInfoTime;
    private TIMMessage lastMsg = null;
    private long lastMsgTime;
    private long maxMemberNum;
    private long memberNum;
    private long onlineMemberNum;
    private ComStatus searchable = ComStatus.kNotSet;
    private ComStatus visible = ComStatus.kNotSet;

    TIMGroupDetailInfo(GroupDetailInfo groupDetailInfo) {
        if (groupDetailInfo != null) {
            String str;
            setGroupId(groupDetailInfo.getSGroupId());
            try {
                str = new String(groupDetailInfo.getSGroupName(), "UTF-8");
                String str2 = new String(groupDetailInfo.getSIntroduction(), "UTF-8");
                String str3 = new String(groupDetailInfo.getSNotification(), "UTF-8");
                setIntroduction(str2);
                setNotice(str3);
                setGroupName(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            setGroupOwner(groupDetailInfo.getSOwner());
            setFaceUrl(groupDetailInfo.getSFaceUrl());
            setGroupType(groupDetailInfo.getSGroupType());
            setAddOption(groupDetailInfo.getDwAddOption());
            setCreateTime(groupDetailInfo.getDwCreateTime());
            setLastInfoTime(groupDetailInfo.getDwLastInfoTime());
            setLastMsgTime(groupDetailInfo.getDwLastMsgTime());
            setMemberNum(groupDetailInfo.getDwMemberNum());
            setMaxMemberNum(groupDetailInfo.getDwMaxMemberNum());
            setOnlineMemberNum(groupDetailInfo.getDwOnlineMemberNum());
            setLastMsg(new TIMMessage(groupDetailInfo.getLastMsg()));
            setVisible(groupDetailInfo.getEVisible());
            setSearchable(groupDetailInfo.getESearchable());
            int size = (int) groupDetailInfo.getCustom_info().size();
            MapKeyFetcher mapKeyFetcher = new MapKeyFetcher();
            mapKeyFetcher.fetchMapKeys(groupDetailInfo.getCustom_info());
            getCustom().clear();
            for (int i = 0; i < size; i++) {
                try {
                    str = new String(mapKeyFetcher.getKey(i), "utf-8");
                    Object value = mapKeyFetcher.getValue(groupDetailInfo.getCustom_info(), i);
                    QLog.i(tag, 1, "custom|" + str + DLConstants.DEPENDENCY_PACKAGE_DIV + new String(value, "utf-8"));
                    getCustom().put(str, value);
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public Map<String, byte[]> getCustom() {
        return this.custom;
    }

    public String getFaceUrl() {
        return this.groupFaceUrl;
    }

    public TIMGroupAddOpt getGroupAddOpt() {
        return this.groupAddOpt;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getGroupIntroduction() {
        return this.groupIntroduction;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getGroupNotification() {
        return this.groupNotice;
    }

    public String getGroupOwner() {
        return this.groupOwner;
    }

    public String getGroupType() {
        return this.groupType;
    }

    public long getLastInfoTime() {
        return this.lastInfoTime;
    }

    public TIMMessage getLastMsg() {
        return this.lastMsg;
    }

    public long getLastMsgTime() {
        return this.lastMsgTime;
    }

    public long getMaxMemberNum() {
        return this.maxMemberNum;
    }

    public long getMemberNum() {
        return this.memberNum;
    }

    public long getOnlineMemberNum() {
        return this.onlineMemberNum;
    }

    public ComStatus getSearchable() {
        return this.searchable;
    }

    public ComStatus getVisible() {
        return this.visible;
    }

    void setAddOption(long j) {
        for (TIMGroupAddOpt tIMGroupAddOpt : TIMGroupAddOpt.values()) {
            if (tIMGroupAddOpt.getValue() == j) {
                this.groupAddOpt = tIMGroupAddOpt;
            }
        }
    }

    void setCreateTime(long j) {
        this.createTime = j;
    }

    void setFaceUrl(String str) {
        this.groupFaceUrl = str;
    }

    void setGroupId(String str) {
        this.groupId = str;
    }

    void setGroupName(String str) {
        this.groupName = str;
    }

    void setGroupOwner(String str) {
        this.groupOwner = str;
    }

    void setGroupType(String str) {
        this.groupType = str;
    }

    void setIntroduction(String str) {
        this.groupIntroduction = str;
    }

    void setLastInfoTime(long j) {
        this.lastInfoTime = j;
    }

    void setLastMsg(TIMMessage tIMMessage) {
        this.lastMsg = tIMMessage;
    }

    void setLastMsgTime(long j) {
        this.lastMsgTime = j;
    }

    void setMaxMemberNum(long j) {
        this.maxMemberNum = j;
    }

    void setMemberNum(long j) {
        this.memberNum = j;
    }

    void setNotice(String str) {
        this.groupNotice = str;
    }

    void setOnlineMemberNum(long j) {
        this.onlineMemberNum = j;
    }

    public void setSearchable(ComStatus comStatus) {
        this.searchable = comStatus;
    }

    protected void setVisible(ComStatus comStatus) {
        this.visible = comStatus;
    }
}
