package com.qq.reader.module.readpage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.qq.reader.module.readpage.voteview.VoteViewGroup;
import com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType;
import com.qq.reader.module.readpage.voteview.VoteViewGroup.a;
import com.qq.reader.readengine.kernel.c.d;

/* compiled from: ReaderPageLayerVote */
public class t extends s implements a {
    VoteViewGroup f;

    public t(Context context) {
        this.f = new VoteViewGroup(context);
        this.f.setInvalidateObserver(this);
        this.a = this.f;
        this.a.setVisibility(8);
    }

    protected boolean a(int i) {
        return i == 103 || i == 100 || i == 101;
    }

    protected void a(d dVar) {
        if (this.f != null) {
            b(dVar.h().a());
            this.f.setViewGroupPadding(0, (int) (dVar.h().f() + ((float) j.k())), 0, 0);
            Message obtainMessage = this.f.getVoteViewGroupHander().obtainMessage();
            obtainMessage.what = 101;
            obtainMessage.arg1 = dVar.c;
            this.f.getVoteViewGroupHander().sendMessage(obtainMessage);
        }
    }

    public void a(Handler handler) {
        super.a(handler);
        this.f.setBaseHander(handler);
    }

    public boolean a(Message message) {
        Message obtain = Message.obtain();
        obtain.copyFrom(message);
        return this.f.getVoteViewGroupHander().sendMessage(obtain);
    }

    private void b(int i) {
        switch (i) {
            case 100:
                this.f.setViewType(ViewType.RECOMMENT);
                return;
            case 101:
                this.f.setViewType(ViewType.REWARD);
                return;
            case 103:
                this.f.setViewType(ViewType.MONTHTICKET);
                return;
            default:
                return;
        }
    }

    public void a(View view) {
        if (this.e != null) {
            this.e.a(this);
        }
    }
}
