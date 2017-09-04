package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.BytesMap;
import com.tencent.imcore.BytesVecFetcher;
import com.tencent.imcore.FriendProfile;
import com.tencent.imcore.MapKeyFetcher;
import com.tencent.imsdk.QLog;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TIMUserProfile {
    private String allowType = "";
    private long birthday = 0;
    private Map<String, byte[]> customInfo = new HashMap();
    private String faceUrl = "";
    private long gender = 0;
    private List<String> groupNames = new ArrayList();
    private String identifier = "";
    private long language = 0;
    private String location = "";
    private String nickName = "";
    private String remark = "";
    private String selfSignature = "";

    public TIMUserProfile(FriendProfile friendProfile) {
        if (friendProfile != null) {
            this.identifier = friendProfile.getSIdentifier();
            this.allowType = friendProfile.getSAllowType();
            try {
                int i;
                this.nickName = new String(friendProfile.getSNickname(), "utf-8");
                this.remark = new String(friendProfile.getSRemark(), "utf-8");
                this.faceUrl = new String(friendProfile.getSFaceURL(), "utf-8");
                this.selfSignature = new String(friendProfile.getSSelfSignature(), "utf-8");
                this.gender = (long) friendProfile.getEGender().swigValue();
                this.birthday = friendProfile.getUBirthDay();
                this.language = friendProfile.getULanguage();
                this.location = new String(friendProfile.getSLocation(), "utf-8");
                int size = (int) friendProfile.getSGroupNames().size();
                BytesVecFetcher bytesVecFetcher = new BytesVecFetcher(friendProfile.getSGroupNames());
                this.groupNames.clear();
                for (i = 0; i < size; i++) {
                    this.groupNames.add(new String(bytesVecFetcher.getBytesByIndex(i), "utf-8"));
                }
                BytesMap custom_info = friendProfile.getCustom_info();
                MapKeyFetcher mapKeyFetcher = new MapKeyFetcher();
                mapKeyFetcher.fetchMapKeys(custom_info);
                for (i = 0; ((long) i) < custom_info.size(); i++) {
                    try {
                        getCustomInfo().put(new String(mapKeyFetcher.getKey(i), "utf-8"), mapKeyFetcher.getValue(custom_info, i));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
    }

    public TIMFriendAllowType getAllowType() {
        QLog.e("TIMUserProfile", 1, "allowType " + this.allowType);
        return this.allowType.equals(TIMFriendAllowType.TIM_FRIEND_ALLOW_ANY.getType()) ? TIMFriendAllowType.TIM_FRIEND_ALLOW_ANY : this.allowType.equals(TIMFriendAllowType.TIM_FRIEND_DENY_ANY.getType()) ? TIMFriendAllowType.TIM_FRIEND_DENY_ANY : this.allowType.equals(TIMFriendAllowType.TIM_FRIEND_NEED_CONFIRM.getType()) ? TIMFriendAllowType.TIM_FRIEND_NEED_CONFIRM : TIMFriendAllowType.TIM_FRIEND_INVALID;
    }

    public long getBirthday() {
        return this.birthday;
    }

    public Map<String, byte[]> getCustomInfo() {
        return this.customInfo;
    }

    public String getFaceUrl() {
        return this.faceUrl;
    }

    public List<String> getFriendGroups() {
        return this.groupNames;
    }

    public TIMFriendGenderType getGender() {
        for (TIMFriendGenderType tIMFriendGenderType : TIMFriendGenderType.values()) {
            if (tIMFriendGenderType.getValue() == this.gender) {
                return tIMFriendGenderType;
            }
        }
        return TIMFriendGenderType.Unknow;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public long getLanguage() {
        return this.language;
    }

    public String getLocation() {
        return this.location;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getSelfSignature() {
        return this.selfSignature;
    }

    void setBirthday(long j) {
        this.birthday = j;
    }

    void setGender(long j) {
        this.gender = j;
    }

    void setLanguage(long j) {
        this.language = j;
    }

    void setLocation(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.location = str;
        }
    }
}
