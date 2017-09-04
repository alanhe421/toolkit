package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.qq.reader.view.as;

public class VoteTask extends ReaderProtocolJSONTask {
    public static final int VOTE_TYPE_MONTH = 2;
    public static final int VOTE_TYPE_RECOMMEND = 1;
    public static final int VOTE_TYPE_REWARD = 0;

    public VoteTask(int i, long j, int i2, int i3, c cVar) {
        super(cVar);
        switch (i) {
            case 0:
                if (i2 == -1) {
                    this.mUrl = e.h + "reward/pay?bid=" + j + H5GameChargeTask.MONEY + i3 + "&rewardOrigin=" + as.E;
                    return;
                } else {
                    this.mUrl = e.h + "reward/pay?bid=" + j + GetVoteUserIconsTask.CID + i2 + H5GameChargeTask.MONEY + i3 + "&rewardOrigin=" + as.E;
                    return;
                }
            case 1:
                if (i2 == -1) {
                    this.mUrl = e.h + "rticket/send?bid=" + j + H5GameGrantTicketTask.COMMON_COUNT + i3;
                    return;
                } else {
                    this.mUrl = e.h + "rticket/send?bid=" + j + GetVoteUserIconsTask.CID + i2 + H5GameGrantTicketTask.COMMON_COUNT + i3;
                    return;
                }
            case 2:
                if (i2 == -1) {
                    this.mUrl = e.h + "mticket/send?bid=" + j + H5GameGrantTicketTask.COMMON_COUNT + i3;
                    return;
                } else {
                    this.mUrl = e.h + "mticket/send?bid=" + j + GetVoteUserIconsTask.CID + i2 + H5GameGrantTicketTask.COMMON_COUNT + i3;
                    return;
                }
            default:
                return;
        }
    }
}
