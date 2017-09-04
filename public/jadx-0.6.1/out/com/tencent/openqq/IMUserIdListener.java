package com.tencent.openqq;

import java.util.List;

public interface IMUserIdListener extends IMBaseListener {
    void onSucc(List<IMUserId> list);
}
