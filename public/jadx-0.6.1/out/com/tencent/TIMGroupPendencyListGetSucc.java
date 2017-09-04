package com.tencent;

import com.tencent.imcore.GroupPendencyItemVec;
import com.tencent.imcore.GroupPendencyMeta;
import java.util.ArrayList;
import java.util.List;

public class TIMGroupPendencyListGetSucc {
    private String identifer;
    private TIMGroupPendencyMeta meta;
    private List<TIMGroupPendencyItem> pendencies = new ArrayList();

    TIMGroupPendencyListGetSucc(String str, GroupPendencyMeta groupPendencyMeta, GroupPendencyItemVec groupPendencyItemVec) {
        this.identifer = str;
        this.meta = new TIMGroupPendencyMeta(groupPendencyMeta);
        for (int i = 0; ((long) i) < groupPendencyItemVec.size(); i++) {
            this.pendencies.add(new TIMGroupPendencyItem(str, groupPendencyItemVec.get(i)));
        }
    }

    public List<TIMGroupPendencyItem> getPendencies() {
        return this.pendencies;
    }

    public TIMGroupPendencyMeta getPendencyMeta() {
        return this.meta;
    }
}
