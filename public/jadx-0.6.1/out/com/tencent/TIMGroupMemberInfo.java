package com.tencent;

import com.tencent.imcore.BytesMap;
import com.tencent.imcore.MapKeyFetcher;
import com.tencent.imcore.MemberInfo;
import com.tencent.imcore.NewGroupMemberInfo;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TIMGroupMemberInfo {
    private Map<String, byte[]> custom = new HashMap();
    private long joinTime;
    private String nameCard = "";
    private TIMGroupMemberRoleType role = TIMGroupMemberRoleType.NotMember;
    private long silenceSeconds;
    private String user = "";

    TIMGroupMemberInfo(MemberInfo memberInfo) {
        if (memberInfo != null) {
            setUser(memberInfo.getMember());
            setJoinTime(memberInfo.getJoin_time());
            setRole(memberInfo.getRole());
            try {
                setNameCard(new String(memberInfo.getName_card(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            setSilenceSeconds(memberInfo.getShutup_time());
            BytesMap custom_info = memberInfo.getCustom_info();
            MapKeyFetcher mapKeyFetcher = new MapKeyFetcher();
            mapKeyFetcher.fetchMapKeys(custom_info);
            for (int i = 0; ((long) i) < custom_info.size(); i++) {
                try {
                    getCustomInfo().put(new String(mapKeyFetcher.getKey(i), "utf-8"), mapKeyFetcher.getValue(custom_info, i));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    TIMGroupMemberInfo(NewGroupMemberInfo newGroupMemberInfo) {
        if (newGroupMemberInfo != null) {
            setUser(newGroupMemberInfo.getIdentifier());
            setRole((long) newGroupMemberInfo.getMember_role());
            try {
                setNameCard(new String(newGroupMemberInfo.getName_card(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            BytesMap custom_info = newGroupMemberInfo.getCustom_info();
            MapKeyFetcher mapKeyFetcher = new MapKeyFetcher();
            mapKeyFetcher.fetchMapKeys(custom_info);
            for (int i = 0; ((long) i) < custom_info.size(); i++) {
                try {
                    getCustomInfo().put(new String(mapKeyFetcher.getKey(i), "utf-8"), mapKeyFetcher.getValue(custom_info, i));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public Map<String, byte[]> getCustomInfo() {
        return this.custom;
    }

    public long getJoinTime() {
        return this.joinTime;
    }

    public String getNameCard() {
        return this.nameCard;
    }

    public TIMGroupMemberRoleType getRole() {
        return this.role;
    }

    public long getSilenceSeconds() {
        return this.silenceSeconds;
    }

    public String getUser() {
        return this.user;
    }

    public void setCustomInfo(String str, byte[] bArr) {
        this.custom.put(str, bArr);
    }

    void setJoinTime(long j) {
        this.joinTime = j;
    }

    void setNameCard(String str) {
        this.nameCard = str;
    }

    void setRole(long j) {
        for (TIMGroupMemberRoleType tIMGroupMemberRoleType : TIMGroupMemberRoleType.values()) {
            if (j == tIMGroupMemberRoleType.getValue()) {
                this.role = tIMGroupMemberRoleType;
                return;
            }
        }
        this.role = TIMGroupMemberRoleType.NotMember;
    }

    public void setRoleType(TIMGroupMemberRoleType tIMGroupMemberRoleType) {
        if (tIMGroupMemberRoleType == TIMGroupMemberRoleType.Admin) {
            this.role = tIMGroupMemberRoleType;
        } else {
            this.role = TIMGroupMemberRoleType.NotMember;
        }
    }

    void setSilenceSeconds(long j) {
        this.silenceSeconds = j;
    }

    public void setUser(String str) {
        this.user = str;
    }
}
