package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.GroupAssistant;
import com.tencent.imcore.GroupCacheInfoVec;
import com.tencent.imcore.StrVec;
import com.tencent.imsdk.QLog;
import java.util.ArrayList;
import java.util.List;

public class TIMGroupAssistant {
    private static TIMGroupAssistant inst = new TIMGroupAssistant();
    private static final String tag = "MSF.C.TIMGroupAssistant";
    private String identifier = "";

    private TIMGroupAssistant() {
    }

    private TIMGroupAssistant(String str) {
        this.identifier = str;
    }

    private GroupAssistant getGroupAssistant() {
        return TextUtils.isEmpty(this.identifier) ? TIMManager.getInstance().getCoreUser().getGroupAssistant() : TIMManager.getInstanceById(this.identifier).getCoreUser().getGroupAssistant();
    }

    public static TIMGroupAssistant getInstance() {
        inst.setIdentifier(TIMManager.getInstance().getIdentification());
        return inst;
    }

    public static TIMGroupAssistant getInstanceById(String str) {
        if (TextUtils.isEmpty(str)) {
            return inst;
        }
        TIMGroupAssistant tIMGroupAssistant = new TIMGroupAssistant(str);
        tIMGroupAssistant.setIdentifier(str);
        return tIMGroupAssistant;
    }

    public List<TIMGroupCacheInfo> getGroups(List<String> list) {
        if (IMCoreWrapper.get().isReady()) {
            StrVec strVec = new StrVec();
            if (!(list == null || list.size() == 0)) {
                for (String str : list) {
                    if (TextUtils.isEmpty(str)) {
                        QLog.w(tag, 1, "groupIds contain null or empty object");
                    } else {
                        strVec.pushBack(str);
                    }
                }
            }
            GroupCacheInfoVec groupCacheInfoVec = new GroupCacheInfoVec();
            if (TIMManager.getInstanceById(this.identifier).getCoreUser().getGroupAssistant().getGroups(strVec, groupCacheInfoVec) != 0) {
                return null;
            }
            List<TIMGroupCacheInfo> arrayList = new ArrayList();
            long size = groupCacheInfoVec.size();
            if (size > 0) {
                for (int i = 0; ((long) i) < size; i++) {
                    arrayList.add(new TIMGroupCacheInfo(groupCacheInfoVec.get(i)));
                }
            }
            QLog.d(tag, 1, "getGroupList size: " + size);
            return arrayList;
        }
        QLog.e(tag, 1, "sdk not initialized or not logged in.");
        return null;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    void setIdentifier(String str) {
        this.identifier = str;
    }
}
