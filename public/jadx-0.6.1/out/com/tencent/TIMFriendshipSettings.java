package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.FriendshipProxyConfig;
import com.tencent.imcore.StrVec;
import java.util.List;

public class TIMFriendshipSettings {
    private List<String> customFields;
    private long flags = 255;

    protected void convertTo(FriendshipProxyConfig friendshipProxyConfig) {
        StrVec strVec = new StrVec();
        if (this.customFields != null) {
            for (String str : this.customFields) {
                if (!TextUtils.isEmpty(str)) {
                    strVec.pushBack(str);
                }
            }
        }
        friendshipProxyConfig.setCustom(strVec);
        friendshipProxyConfig.setFlags(this.flags);
    }

    public List<String> getCustomFields() {
        return this.customFields;
    }

    public long getFlags() {
        return this.flags;
    }

    public void setSettings(long j, List<String> list) {
        this.flags = j;
        this.customFields = list;
    }
}
