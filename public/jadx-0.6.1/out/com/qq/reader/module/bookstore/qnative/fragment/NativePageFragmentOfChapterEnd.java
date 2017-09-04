package com.qq.reader.module.bookstore.qnative.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.IllegalCommentReportTask;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.page.impl.al;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.a;
import com.qq.reader.view.linearmenu.b;
import com.qq.reader.view.linearmenu.c;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.List;
import org.json.JSONObject;

public class NativePageFragmentOfChapterEnd extends NativePageFragmentOfReply {
    protected int getLayoutResId() {
        return R.layout.localbookclub_chapterend_layout;
    }

    public void doFunction(Bundle bundle) {
        if (bundle.getInt("function_type") == 4) {
            switch (bundle.getInt(BookClubReplyCard.REPLY_STATUS)) {
                case 1:
                case 2:
                    getAuthorMenu(bundle).f_();
                    return;
                default:
                    return;
            }
        }
        super.doFunction(bundle);
    }

    protected b getAuthorMenu(Bundle bundle) {
        this.mBottomContextMenu = new b(getActivity());
        this.mBottomContextMenu.a(1, "回复", bundle);
        int i = bundle.getInt(BookClubReplyCard.REPLY_STATUS);
        this.enterBundle.getString("KEY_JUMP_PAGENAME");
        switch (i) {
            case 1:
                if (this.mHoldPage instanceof al) {
                    if (((al) this.mHoldPage).S == 0) {
                        this.mBottomContextMenu.a(6, "举报", bundle);
                        break;
                    }
                    if (bundle.containsKey(BookClubReplyCard.REPLY_USER_BLACK)) {
                        i = bundle.getInt(BookClubReplyCard.REPLY_USER_BLACK);
                        if (i == 1) {
                            this.mBottomContextMenu.a(5, "解禁", bundle);
                        } else if (i == 0) {
                            this.mBottomContextMenu.a(4, "禁言7天", bundle);
                        }
                    }
                    this.mBottomContextMenu.a(0, "删除", bundle);
                    break;
                }
                break;
            case 2:
                this.mBottomContextMenu.a(6, "举报", bundle);
                this.mBottomContextMenu.a(0, "删除", bundle);
                break;
        }
        this.mBottomContextMenu.a(new a.b(this) {
            final /* synthetic */ NativePageFragmentOfChapterEnd a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.mBottomContextMenu.cancel();
                switch (i) {
                    case 0:
                        this.a.showDialog(609, bundle);
                        return true;
                    case 1:
                        i.a("event_B365", null, ReaderApplication.getApplicationImp());
                        bundle.putBoolean("SHOWKEYBOARD", true);
                        this.a.showReplyLayout(bundle, 1);
                        return true;
                    case 4:
                        this.a.setBanComment(true, bundle);
                        return true;
                    case 5:
                        this.a.setBanComment(false, bundle);
                        return true;
                    case 6:
                        i.a("event_B364", null, ReaderApplication.getApplicationImp());
                        this.a.showReportDialog(bundle);
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.mBottomContextMenu.a(new OnCancelListener(this) {
            final /* synthetic */ NativePageFragmentOfChapterEnd a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getActivity().getWindow().closeAllPanels();
            }
        });
        return this.mBottomContextMenu;
    }

    protected void showReportDialog(final Bundle bundle) {
        final a cVar = new c(getFromActivity());
        cVar.a(new OnCancelListener(this) {
            final /* synthetic */ NativePageFragmentOfChapterEnd a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a.getFromActivity() != null) {
                    this.a.getFromActivity().getWindow().closeAllPanels();
                }
            }
        });
        cVar.i();
        cVar.a(2, "广告及垃圾信息", null);
        cVar.a(5, "灌水", null);
        cVar.a(3, "反动", null);
        cVar.a(new a.b(this) {
            final /* synthetic */ NativePageFragmentOfChapterEnd c;

            public boolean a(int i, Bundle bundle) {
                cVar.cancel();
                final Bundle bundle2 = new Bundle();
                bundle2.putInt("COMMENT_REPORT_CTYPE", bundle.getInt("CTYPE"));
                bundle2.putString("COMMENT_REPORT_BID", bundle.getString(BookClubReplyCard.BID));
                bundle2.putString("COMMENT_REPORT_COMMENTID", bundle.getString("COMMENT_ID"));
                String string = bundle.getString(BookClubReplyCard.REPLY_ID, "0");
                if (TextUtils.isEmpty(string)) {
                    string = "0";
                }
                bundle2.putString("COMMENT_REPORT_REPLYID", string);
                bundle2.putInt("COMMENT_REPORT_REPORTTYPE", i);
                bundle2.putString("COMMENT_REPORT_DESC", cVar.b(i));
                ReaderTask commentReportTask = this.c.getCommentReportTask(bundle2);
                if (com.qq.reader.common.login.c.b()) {
                    g.a().a(commentReportTask);
                } else if (this.c.getFromActivity() != null) {
                    ((ReaderBaseActivity) this.c.getFromActivity()).mLoginNextTask = new com.qq.reader.common.login.a(this) {
                        final /* synthetic */ AnonymousClass4 b;

                        public void a(int i) {
                            switch (i) {
                                case 1:
                                    g.a().a(this.b.c.getCommentReportTask(bundle2));
                                    return;
                                default:
                                    return;
                            }
                        }
                    };
                    ((ReaderBaseActivity) this.c.getFromActivity()).startLogin();
                }
                return false;
            }
        });
        cVar.f_();
    }

    public IllegalCommentReportTask getCommentReportTask(Bundle bundle) {
        return new IllegalCommentReportTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativePageFragmentOfChapterEnd a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    Message obtainMessage = this.a.mHandler.obtainMessage(6000016);
                    obtainMessage.obj = new JSONObject(str).optString(SocialConstants.PARAM_SEND_MSG);
                    this.a.mHandler.sendMessage(obtainMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                exception.printStackTrace();
            }
        }, bundle);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 6000016:
                af.a(getApplicationContext(), (String) message.obj, 0).a();
                reLoadData();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void initCommentDetailBottomBtns() {
        this.mComment_Detail_Bottom_Btns = this.root.findViewById(R.id.comment_detail_bottom_btns);
        this.mComment_Detail_Bottom_Btns.setVisibility(0);
        this.mComment_Detail_Bottom_Btns.findViewById(R.id.comment_detail_bottom_btns_reply).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativePageFragmentOfChapterEnd a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.initShowReplyLayout();
                this.a.showReplyView();
                this.a.mReplyLayout.setHint("");
                i.a("event_B138", null, ReaderApplication.getApplicationImp());
            }
        });
        View findViewById = this.root.findViewById(R.id.fl_main);
        LayoutParams layoutParams = (LayoutParams) findViewById.getLayoutParams();
        layoutParams.bottomMargin = (int) ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.common_list_item_height);
        findViewById.setLayoutParams(layoutParams);
        List m = this.mHoldPage.m();
        if (m.size() == 1 && (m.get(0) instanceof MyFavorEmptyCard)) {
            this.mComment_Detail_Bottom_Btns.setVisibility(8);
        }
    }

    protected void refreshReply() {
    }

    protected void refreshBottomPannel() {
        List m = this.mHoldPage.m();
        if (m.size() == 1 && (m.get(0) instanceof MyFavorEmptyCard)) {
            this.mComment_Detail_Bottom_Btns.setVisibility(8);
        } else {
            this.mComment_Detail_Bottom_Btns.setVisibility(0);
        }
    }
}
