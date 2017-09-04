package com.tencent;

import com.tencent.imcore.FriendFutureItemVec;
import com.tencent.imcore.FutureFriendMeta;
import java.util.ArrayList;
import java.util.List;

public class TIMGetFriendFutureListSucc {
    private List<TIMFriendFutureItem> items;
    private TIMFriendFutureMeta meta;

    TIMGetFriendFutureListSucc(FutureFriendMeta futureFriendMeta, FriendFutureItemVec friendFutureItemVec) {
        setMeta(new TIMFriendFutureMeta(futureFriendMeta));
        List arrayList = new ArrayList();
        int size = (int) friendFutureItemVec.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new TIMFriendFutureItem(friendFutureItemVec.get(i)));
        }
        setItems(arrayList);
    }

    public List<TIMFriendFutureItem> getItems() {
        return this.items;
    }

    public TIMFriendFutureMeta getMeta() {
        return this.meta;
    }

    void setItems(List<TIMFriendFutureItem> list) {
        this.items = list;
    }

    void setMeta(TIMFriendFutureMeta tIMFriendFutureMeta) {
        this.meta = tIMFriendFutureMeta;
    }
}
