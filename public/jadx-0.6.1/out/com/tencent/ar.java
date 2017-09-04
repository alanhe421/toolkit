package com.tencent;

import com.tencent.imcore.GroupTipsElem;
import com.tencent.imcore.GroupTipsElem_MemberInfoVec;
import com.tencent.imcore.IGroupUpdateCallback;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

final class ar extends IGroupUpdateCallback {
    private static String a = "IMCoreGroupUpdateCallback";
    private String b;

    public ar(String str) {
        this.b = str;
        swigReleaseOwnership();
    }

    public final void onMembersUpdate(GroupTipsElem groupTipsElem) {
        int i = 0;
        TIMGroupMemberUpdateListener groupMemberUpdateListener = TIMManager.getInstanceById(this.b).getGroupMemberUpdateListener();
        if (groupMemberUpdateListener == null) {
            QLog.d(a, 1, "no group member update listener registered");
            return;
        }
        String str;
        String str2 = "";
        try {
            str = new String(groupTipsElem.getGroup_id(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = str2;
        }
        if (groupTipsElem.getType() < 6) {
            TIMGroupTipsType tIMGroupTipsType = TIMGroupTipsType.Join;
            switch (groupTipsElem.getType()) {
                case 1:
                    tIMGroupTipsType = TIMGroupTipsType.Join;
                    break;
                case 2:
                    tIMGroupTipsType = TIMGroupTipsType.Quit;
                    break;
                case 3:
                    tIMGroupTipsType = TIMGroupTipsType.Kick;
                    break;
                case 4:
                    tIMGroupTipsType = TIMGroupTipsType.SetAdmin;
                    break;
                case 5:
                    tIMGroupTipsType = TIMGroupTipsType.CancelAdmin;
                    break;
            }
            List arrayList = new ArrayList();
            for (int i2 = 0; ((long) i2) < groupTipsElem.getUser_list().size(); i2++) {
                arrayList.add(groupTipsElem.getUser_list().get(i2));
            }
            QLog.d(a, 1, "member update, size " + arrayList.size());
            IMMsfCoreProxy.mainHandler.post(new as(this, groupMemberUpdateListener, str, tIMGroupTipsType, arrayList));
        } else if (groupTipsElem.getType() == 7) {
            List arrayList2 = new ArrayList();
            GroupTipsElem_MemberInfoVec member_change_list = groupTipsElem.getMember_change_list();
            while (((long) i) < member_change_list.size()) {
                TIMGroupTipsElemMemberInfo tIMGroupTipsElemMemberInfo = new TIMGroupTipsElemMemberInfo();
                tIMGroupTipsElemMemberInfo.setIdentifier(member_change_list.get(i).getIdentifier());
                tIMGroupTipsElemMemberInfo.setShutupTime(member_change_list.get(i).getShutup_time());
                arrayList2.add(tIMGroupTipsElemMemberInfo);
                i++;
            }
            QLog.d(a, 1, "member info update, size " + arrayList2.size());
            IMMsfCoreProxy.mainHandler.post(new at(this, groupMemberUpdateListener, str, arrayList2));
        }
    }
}
