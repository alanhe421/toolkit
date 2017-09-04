package com.tencent;

import com.tencent.imcore.GroupBaseInfo;
import java.io.UnsupportedEncodingException;

public class TIMGroupBaseInfo {
    private String faceUrl = "";
    private String groupId = "";
    private String groupName = "";
    private String groupType = "";
    private TIMGroupBasicSelfInfo selfInfo = new TIMGroupBasicSelfInfo();

    TIMGroupBaseInfo() {
    }

    TIMGroupBaseInfo(GroupBaseInfo groupBaseInfo) {
        setGroupId(groupBaseInfo.getSGroupId());
        try {
            setGroupName(new String(groupBaseInfo.getSGroupName(), "utf-8"));
            setFaceUrl(new String(groupBaseInfo.getSFaceUrl(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        setGroupType(groupBaseInfo.getSGroupType());
        this.selfInfo = new TIMGroupBasicSelfInfo(groupBaseInfo.getStSelfInfo());
    }

    public String getFaceUrl() {
        return this.faceUrl;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getGroupType() {
        return this.groupType;
    }

    public TIMGroupBasicSelfInfo getSelfInfo() {
        return this.selfInfo;
    }

    void setFaceUrl(String str) {
        this.faceUrl = str;
    }

    void setGroupId(String str) {
        this.groupId = str;
    }

    void setGroupName(String str) {
        this.groupName = str;
    }

    void setGroupType(String str) {
        this.groupType = str;
    }
}
