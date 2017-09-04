package com.tencent;

import java.util.List;

public class TIMFriendCheckParam {
    boolean bidirection = true;
    List<String> identifiers;

    List<String> getIdentifiers() {
        return this.identifiers;
    }

    boolean isBidirection() {
        return this.bidirection;
    }

    public void setBidirection(boolean z) {
        this.bidirection = z;
    }

    public void setIdentifiers(List<String> list) {
        this.identifiers = list;
    }
}
