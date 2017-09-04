package com.tencent.imsdk;

import com.tencent.openqq.IMCmdListener;
import com.tencent.openqq.IMUserId;
import java.util.ArrayList;
import java.util.List;

public abstract class WrapCmdListener<T> implements IMCmdListener {
    public int called_cnt = 0;
    public T listener;
    public List<String> openIdList = new ArrayList();
    public int req_num = 20;
    public int seq = 0;
    public int start_index = 0;
    public List<IMUserId> userIdList;

    public WrapCmdListener(T t) {
        this.listener = t;
    }

    public List<IMUserId> getUserIdList() {
        return this.userIdList;
    }

    public void setUserIdList(List<IMUserId> list) {
        this.userIdList = list;
    }
}
