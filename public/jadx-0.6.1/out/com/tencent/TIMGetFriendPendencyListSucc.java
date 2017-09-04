package com.tencent;

import com.tencent.imcore.FriendPendencyItemVec;
import com.tencent.imcore.FriendPendencyMeta;
import java.util.ArrayList;
import java.util.List;

public class TIMGetFriendPendencyListSucc {
    private TIMFriendPendencyMeta meta;
    private List<TIMFriendPendencyItem> pendencies;

    TIMGetFriendPendencyListSucc(FriendPendencyMeta friendPendencyMeta, FriendPendencyItemVec friendPendencyItemVec) {
        setMeta(new TIMFriendPendencyMeta(friendPendencyMeta));
        List arrayList = new ArrayList();
        int size = (int) friendPendencyItemVec.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new TIMFriendPendencyItem(friendPendencyItemVec.get(i)));
        }
        setPendencies(arrayList);
    }

    public TIMFriendPendencyMeta getMeta() {
        return this.meta;
    }

    public List<TIMFriendPendencyItem> getPendencies() {
        return this.pendencies;
    }

    void setMeta(TIMFriendPendencyMeta tIMFriendPendencyMeta) {
        this.meta = tIMFriendPendencyMeta;
    }

    void setPendencies(List<TIMFriendPendencyItem> list) {
        this.pendencies = list;
    }
}
