package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.BytesVec;
import com.tencent.imcore.GroupSettings;
import com.tencent.imcore.UpdateInfoOpt;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TIMGroupSettings {
    Options groupInfoOptions = new Options(-32769);
    Options memberInfoOptions = new Options(-1);

    public class Options {
        List<String> customTags = new ArrayList();
        long flags = -2147483648L;

        protected Options(long j) {
            this.flags = j;
        }

        public void addCustomTag(String str) {
            if (!TextUtils.isEmpty(str) && this.customTags != null) {
                this.customTags.add(str);
            }
        }

        UpdateInfoOpt convertTo() {
            UpdateInfoOpt updateInfoOpt = new UpdateInfoOpt(this.flags);
            BytesVec bytesVec = new BytesVec();
            if (this.customTags != null) {
                for (String bytes : this.customTags) {
                    try {
                        bytesVec.pushBack(bytes.getBytes("utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            updateInfoOpt.setCustomInfoTags(bytesVec);
            return updateInfoOpt;
        }

        public void setCustomTags(List<String> list) {
            this.customTags = list;
        }

        public void setFlags(long j) {
            this.flags = j;
        }
    }

    protected GroupSettings convertTo(GroupSettings groupSettings) {
        if (this.groupInfoOptions.flags == -2147483648L) {
            this.groupInfoOptions.setFlags(-32769);
        }
        groupSettings.setGroupInfoOpt(this.groupInfoOptions.convertTo());
        if (this.memberInfoOptions.flags == -2147483648L) {
            this.memberInfoOptions.setFlags(-1);
        }
        groupSettings.setMemberInfoOpt(this.memberInfoOptions.convertTo());
        return groupSettings;
    }

    public void setGroupInfoOptions(Options options) {
        this.groupInfoOptions = options;
    }

    public void setMemberInfoOptions(Options options) {
        this.memberInfoOptions = options;
    }
}
