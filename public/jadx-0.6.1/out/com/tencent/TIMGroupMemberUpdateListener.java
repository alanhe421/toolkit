package com.tencent;

import java.util.List;

@Deprecated
public interface TIMGroupMemberUpdateListener {
    void onMemberInfoUpdate(String str, List<TIMGroupTipsElemMemberInfo> list);

    void onMemberUpdate(String str, TIMGroupTipsType tIMGroupTipsType, List<String> list);
}
