package com.tencent;

public enum TIMFriendGenderType {
    Unknow(0),
    Male(1),
    Female(2);
    
    private long gender;

    private TIMFriendGenderType(long j) {
        this.gender = 0;
        this.gender = j;
    }

    public final long getValue() {
        return this.gender;
    }
}
