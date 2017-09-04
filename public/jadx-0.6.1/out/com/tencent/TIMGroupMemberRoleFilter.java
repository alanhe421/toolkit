package com.tencent;

public enum TIMGroupMemberRoleFilter {
    All(0),
    Owner(1),
    Admin(2),
    Normal(4);
    
    private long filter;

    private TIMGroupMemberRoleFilter(long j) {
        this.filter = 0;
        this.filter = j;
    }

    final long getFilter() {
        return this.filter;
    }
}
