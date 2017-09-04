package com.tencent;

import com.tencent.imcore.GroupCacheInfo;
import com.tencent.imcore.GroupMemberInfoVec;
import com.tencent.imcore.IGroupAssistantCallback;
import com.tencent.imcore.StrVec;
import com.tencent.imsdk.IMMsfCoreProxy;
import java.util.ArrayList;
import java.util.List;

public class IMCoreGroupAssistantCallback extends IGroupAssistantCallback {
    private String identifier;

    public IMCoreGroupAssistantCallback(String str) {
        this.identifier = str;
        swigReleaseOwnership();
    }

    public void onGroupAdd(GroupCacheInfo groupCacheInfo) {
        if (groupCacheInfo != null) {
            TIMGroupAssistantListener groupAssistantListener = TIMManager.getInstanceById(this.identifier).getGroupAssistantListener();
            if (groupAssistantListener != null) {
                IMMsfCoreProxy.mainHandler.post(new an(this, groupAssistantListener, new TIMGroupCacheInfo(groupCacheInfo)));
            }
        }
    }

    public void onGroupDelete(String str) {
        if (str != null) {
            TIMGroupAssistantListener groupAssistantListener = TIMManager.getInstanceById(this.identifier).getGroupAssistantListener();
            if (groupAssistantListener != null) {
                IMMsfCoreProxy.mainHandler.post(new ao(this, groupAssistantListener, str));
            }
        }
    }

    public void onGroupUpdate(GroupCacheInfo groupCacheInfo) {
        if (groupCacheInfo != null) {
            TIMGroupAssistantListener groupAssistantListener = TIMManager.getInstanceById(this.identifier).getGroupAssistantListener();
            if (groupAssistantListener != null) {
                IMMsfCoreProxy.mainHandler.post(new ap(this, groupAssistantListener, new TIMGroupCacheInfo(groupCacheInfo)));
            }
        }
    }

    public void onMemberJoin(String str, GroupMemberInfoVec groupMemberInfoVec) {
        if (str != null && groupMemberInfoVec != null) {
            long size = groupMemberInfoVec.size();
            if (size > 0) {
                TIMGroupAssistantListener groupAssistantListener = TIMManager.getInstanceById(this.identifier).getGroupAssistantListener();
                if (groupAssistantListener != null) {
                    List arrayList = new ArrayList();
                    for (int i = 0; ((long) i) < size; i++) {
                        arrayList.add(new TIMGroupMemberInfo(groupMemberInfoVec.get(i)));
                    }
                    IMMsfCoreProxy.mainHandler.post(new ak(this, groupAssistantListener, str, arrayList));
                }
            }
        }
    }

    public void onMemberQuit(String str, StrVec strVec) {
        if (str != null && strVec != null) {
            long size = strVec.size();
            if (size > 0) {
                TIMGroupAssistantListener groupAssistantListener = TIMManager.getInstanceById(this.identifier).getGroupAssistantListener();
                if (groupAssistantListener != null) {
                    List arrayList = new ArrayList();
                    for (int i = 0; ((long) i) < size; i++) {
                        arrayList.add(strVec.get(i));
                    }
                    IMMsfCoreProxy.mainHandler.post(new al(this, groupAssistantListener, str, arrayList));
                }
            }
        }
    }

    public void onMemberUpdate(String str, GroupMemberInfoVec groupMemberInfoVec) {
        if (str != null && groupMemberInfoVec != null) {
            long size = groupMemberInfoVec.size();
            if (size > 0) {
                TIMGroupAssistantListener groupAssistantListener = TIMManager.getInstanceById(this.identifier).getGroupAssistantListener();
                if (groupAssistantListener != null) {
                    List arrayList = new ArrayList();
                    for (int i = 0; ((long) i) < size; i++) {
                        arrayList.add(new TIMGroupMemberInfo(groupMemberInfoVec.get(i)));
                    }
                    IMMsfCoreProxy.mainHandler.post(new am(this, groupAssistantListener, str, arrayList));
                }
            }
        }
    }
}
