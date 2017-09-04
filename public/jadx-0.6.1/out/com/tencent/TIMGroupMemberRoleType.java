package com.tencent;

public enum TIMGroupMemberRoleType {
    Owner(400),
    Admin(300),
    Normal(200),
    NotMember(0);
    
    private long role;

    private TIMGroupMemberRoleType(long j) {
        this.role = j;
    }

    final long getValue() {
        return this.role;
    }
}
