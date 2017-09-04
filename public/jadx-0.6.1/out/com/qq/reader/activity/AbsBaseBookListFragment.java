package com.qq.reader.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.CheckBox;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.cservice.cloud.a;
import com.qq.reader.cservice.cloud.a.h;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookshelf.a.a.c;
import com.qq.reader.module.bookshelf.i;
import com.qq.reader.module.comic.e.e;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.plugin.audiobook.MusicActivity;
import com.qq.reader.plugin.audiobook.MusicBookGroup;
import com.qq.reader.qplugin.local.TingBookMark;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.b;
import com.qq.reader.view.linearmenu.d;
import com.qq.reader.view.metro.MetroItem;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import format.epub.common.utils.f;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsBaseBookListFragment extends ReaderBaseFragment implements OnCreateContextMenuListener {
    public static final int MENU_ID_GOTO_CLOUD = 4;
    public static final int MENU_ID_LOCAL_DISK = 0;
    public static final int MENU_ID_MANAGE = 2;
    public static final int MENU_ID_NET_DISK = 1;
    public static final int MENU_ID_NONE = -1;
    public static final int MENU_ID_SOFR_WITHTIME = 5;
    public static final int MENU_ID_UPDATE_ALARM = 3;
    protected static boolean isIntercept = false;
    protected final int DIALOG_DEL_BOOKMARK = 301;
    protected final int DIALOG_DEL_NOFILE_BOOKMARK = ErrorCode.ERROR_TBSCORE_DEXOPT_DIR;
    protected final int DIALOG_REMOVE_BOOKMARK = 302;
    protected final int MENU_BOOKMARK_COMMENT = 2;
    protected final int MENU_BOOKMARK_DEL = 0;
    protected final int MENU_BOOKMARK_REMOVE = 1;
    protected final int MENU_BOOK_SHARE_INTENT = 3;
    protected final int MENU_BOOK_SHARE_WX = 7;
    protected final int MENU_CATEGORY = 6;
    protected final int MENU_CLOSE_PRIVATE = 16;
    protected final int MENU_DOWNLOAD_GOON = 4;
    protected final int MENU_DOWNLOAD_PAUSE = 5;
    protected final int MENU_OPEN_PRIVATE = 15;
    protected final int MENU_SHARE = 14;
    private final int MESSAGE_REFRESH_BOOK = 0;
    protected d contextMenu;
    protected Mark currentSelectMark = null;
    protected i mAdapter;
    protected c mCategoryHandler = null;
    private a mCloudListener = null;
    protected Context mContext;
    private b mReaderMenu;

    protected abstract void categoryTo(Mark mark);

    protected abstract void doChooseCategory(MetroItem metroItem);

    protected abstract void setListViewDataByCateId(int i);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.qq.reader.common.c.a.a(false);
        this.mContext = getApplicationContext();
        if (getActivity() != null) {
            this.mCategoryHandler = new c(getActivity(), this.mHandler);
        }
    }

    public boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 0:
                final Mark mark = (Mark) message.obj;
                final int i = message.arg1;
                if (i == 0) {
                    af.a(getApplicationContext(), (CharSequence) "已开启私密阅读", 1).a();
                } else {
                    af.a(getApplicationContext(), (CharSequence) "已关闭私密阅读", 1).a();
                }
                this.mAdapter.a(mark, i);
                this.mAdapter.notifyDataSetChanged();
                g.a().a(new ReaderDBTask() {
                    public void run() {
                        com.qq.reader.common.db.handle.i.c().b(mark.getBookId() + "", i);
                    }
                });
                return true;
            case 70002:
                af.a(this.mContext.getApplicationContext(), (String) message.obj, 1).a();
                return true;
            case 300005:
                onClickBook(message.arg1);
                return true;
            case 300006:
                return onLongClickBook(message.arg1);
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void toReaderPage(Mark mark, Bundle bundle) {
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (new File(mark.getId()).exists() && 4 != mark.getType()) {
            bundle.putString("filepath", mark.getId());
            bundle.putString("filename", mark.getBookName());
            bundle.putString("fileauthor", mark.getAuthor());
            bundle.putInt("fileencode", mark.getEncoding());
            intent.putExtras(bundle);
            if (getActivity() != null) {
                com.qq.reader.b.a(intent, getActivity());
            }
            j.a(70, 0);
            com.qq.reader.common.monitor.i.a("event_A71", null, this.mContext);
            StatisticsManager.a().a("event_A71", null);
        } else if (4 == mark.getType()) {
            String id = mark.getId();
            com.qq.reader.common.c.a.dg = id;
            OnlineTag a = v.b().a(id);
            if (a != null && a.w() == 0 && mark.getIsFinish() == 1) {
                a.h(1);
                v.b().b(a);
            }
            bundle.putString("filepath", mark.getId());
            bundle.putString("filename", mark.getBookName());
            intent.putExtras(bundle);
            intent.putExtra("com.qq.reader.OnlineTag", a);
            intent.putExtra("com.qq.reader.fromonline", true);
            if (getActivity() != null) {
                com.qq.reader.b.a(intent, getActivity());
            }
            j.a(71, 0);
            com.qq.reader.common.monitor.i.a("event_A72", null, this.mContext);
            StatisticsManager.a().a("event_A72", null);
        } else {
            this.currentSelectMark = mark;
            showFragmentDialog(ErrorCode.ERROR_TBSCORE_DEXOPT_DIR);
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return onContextMenuSelected(menuItem.getItemId(), null);
    }

    public boolean onContextMenuSelected(int i, Bundle bundle) {
        if (this.currentSelectMark == null) {
            return false;
        }
        switch (i) {
            case 0:
                showFragmentDialog(301);
                return true;
            case 1:
                showFragmentDialog(302);
                j.a(9, 0);
                return true;
            case 2:
                return true;
            case 3:
                if (this.currentSelectMark == null || !(this.currentSelectMark instanceof LocalMark)) {
                    af.a(ReaderApplication.getApplicationImp(), (CharSequence) "该状态暂不支持。", 0).a();
                } else {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name) + "分享");
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("我正在用QQ阅读看一本书《");
                    stringBuffer.append(this.currentSelectMark.getBookShortName());
                    stringBuffer.append("》，觉得不错，与大家一起分享。");
                    intent.putExtra("android.intent.extra.TEXT", stringBuffer.toString());
                    startActivity(Intent.createChooser(intent, "分享到"));
                }
                return true;
            case 6:
                if (this.currentSelectMark != null) {
                    categoryTo(this.currentSelectMark);
                    j.a(11, 0);
                }
                return true;
            case 7:
                WXApiManager.getInstance(this.mContext).registerWX();
                if (this.currentSelectMark != null && (this.currentSelectMark instanceof LocalMark)) {
                    WXApiManager.getInstance(this.mContext).sendBookToWX(this.mContext, this.currentSelectMark, null);
                    j.a(32, 0);
                    break;
                }
            case 14:
                String bookName = this.currentSelectMark.getBookName();
                this.currentSelectMark.getAuthor();
                getShareDialog().a(String.valueOf(this.currentSelectMark.getBookId()), bookName);
                if (this.currentSelectMark.getType() == 8) {
                    getShareDialog().a(true);
                    com.qq.reader.common.monitor.i.a("event_C211", null, getApplicationContext());
                } else {
                    getShareDialog().a(false);
                }
                getShareDialog().f_();
                Map hashMap = new HashMap();
                hashMap.put("from", "0");
                com.qq.reader.common.monitor.i.a("event_M88", hashMap, ReaderApplication.getApplicationImp());
                j.a(111, 0);
                break;
            case 15:
                com.qq.reader.common.monitor.i.a("event_D135", null, ReaderApplication.getApplicationImp());
                com.qq.reader.module.bookshelf.d.b((ReaderBaseActivity) getActivity(), this.currentSelectMark.getBookId(), new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ AbsBaseBookListFragment a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        Message obtain = Message.obtain();
                        obtain.what = 0;
                        obtain.obj = this.a.currentSelectMark;
                        obtain.arg1 = 0;
                        this.a.mHandler.sendMessage(obtain);
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    }
                }, null);
                return true;
            case 16:
                com.qq.reader.common.monitor.i.a("event_D136", null, ReaderApplication.getApplicationImp());
                com.qq.reader.module.bookshelf.d.a((ReaderBaseActivity) getActivity(), this.currentSelectMark.getBookId(), new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ AbsBaseBookListFragment a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        Message obtain = Message.obtain();
                        obtain.what = 0;
                        obtain.obj = this.a.currentSelectMark;
                        obtain.arg1 = 1;
                        this.a.mHandler.sendMessage(obtain);
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    }
                }, null);
                return true;
        }
        return true;
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        Dialog dialog = null;
        if (getActivity() == null) {
            return null;
        }
        final CheckBox checkBox;
        switch (i) {
            case 301:
                dialog = new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_del).b((int) R.string.bookstand_dialog_del).a((int) R.string.alert_dialog_ok, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.a.currentSelectMark != null) {
                            this.a.delRecordFile(this.a.currentSelectMark, false);
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 302:
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.delete_file, null);
                checkBox = (CheckBox) inflate.findViewById(R.id.confirm_check);
                TextView textView = (TextView) inflate.findViewById(R.id.confirm_text);
                checkBox.setChecked(false);
                String string = this.mContext.getResources().getString(R.string.bookstand_dialog_remove);
                if (this.currentSelectMark != null) {
                    textView.setText(String.format(string, new Object[]{this.currentSelectMark.getBookShortName()}));
                }
                dialog = new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_remove).a(inflate).a((int) R.string.alert_dialog_ok, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListFragment b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.b.currentSelectMark != null) {
                            this.b.delRecordFile(this.b.currentSelectMark, checkBox.isChecked());
                            if (this.b.currentSelectMark.getType() == 8) {
                                com.qq.reader.module.bookstore.search.g.a(ReaderApplication.getApplicationImp()).a(this.b.currentSelectMark.getBookName(), 6);
                            } else {
                                com.qq.reader.module.bookstore.search.g.a(ReaderApplication.getApplicationImp()).a(this.b.currentSelectMark.getBookName(), 5);
                            }
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case ErrorCode.ERROR_TBSCORE_DEXOPT_DIR /*311*/:
                View inflate2 = LayoutInflater.from(getActivity()).inflate(R.layout.delete_nofile_mark, null);
                checkBox = (CheckBox) inflate2.findViewById(R.id.confirm_check);
                checkBox.setChecked(false);
                dialog = new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a((int) R.string.dialog_shortcut_title).a(inflate2).a((int) R.string.alert_dialog_ok, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListFragment b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (checkBox.isChecked()) {
                            f.a(new Runnable(this) {
                                final /* synthetic */ AnonymousClass18 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    for (Mark mark : com.qq.reader.common.db.handle.i.c().g()) {
                                        if (!(mark == null || new File(mark.getId()).exists() || 4 == mark.getType())) {
                                            this.a.b.delRecordFile(mark, false);
                                            try {
                                                Thread.sleep(100);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }, this.b.getActivity(), "正在清理,请稍候..");
                        } else if (this.b.currentSelectMark != null) {
                            this.b.delRecordFile(this.b.currentSelectMark, false);
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListFragment a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
        }
        return dialog == null ? super.createDialog(i, bundle) : dialog;
    }

    protected void onFragmentDialgoCancel(DialogInterface dialogInterface) {
    }

    public com.qq.reader.view.linearmenu.a getMenu() {
        if (getActivity() == null) {
            return null;
        }
        this.mReaderMenu = new b(getActivity());
        this.mReaderMenu.a(0, getString(R.string.text_menu_local_disk), null);
        this.mReaderMenu.a(1, getString(R.string.text_menu_net_disk), null);
        this.mReaderMenu.a(2, getString(R.string.text_menu_manage), null);
        this.mReaderMenu.a(3, getString(R.string.text_menu_updatet), null);
        this.mReaderMenu.a(4, getString(R.string.text_menu_cloud) + " ", null);
        this.mReaderMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ AbsBaseBookListFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                return this.a.menuSelected(i, bundle);
            }
        });
        this.mReaderMenu.a(new OnCancelListener(this) {
            final /* synthetic */ AbsBaseBookListFragment a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a.getActivity() != null) {
                    this.a.getActivity().getWindow().closeAllPanels();
                }
            }
        });
        return this.mReaderMenu;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return menuSelected(menuItem.getItemId(), null);
    }

    protected boolean menuSelected(int i, Bundle bundle) {
        switch (i) {
            case 1:
                gotoNetImportActivity();
                j.a(84, 0);
                return true;
            case 3:
                return true;
            case 4:
                gotoCloudActivity(-1);
                return true;
            default:
                return false;
        }
    }

    protected void delBookLocalFile(Mark mark) {
        if (mark instanceof LocalMark) {
            ao.a(new File(mark.getId()));
            ao.a(new File(com.qq.reader.common.drm.a.a(mark.getId())));
            ao.a(new File(com.qq.reader.common.drm.a.b(mark.getId())));
            if (mark.getType() == 4) {
                v.b().c(v.b().a(mark.getId()));
            }
        }
    }

    protected void delRecordFile(Mark mark, boolean z) {
        new a(new a.a(this) {
            final /* synthetic */ AbsBaseBookListFragment a;

            {
                this.a = r1;
            }

            public void a(int i, Object obj) {
                Message obtainMessage = this.a.mHandler.obtainMessage();
                obtainMessage.obj = obj;
                obtainMessage.what = i;
                this.a.mHandler.sendMessage(obtainMessage);
            }
        }).a(mark, z);
    }

    public void onClickBook(int i) {
        Object item = this.mAdapter.getItem(i);
        if (item != null && getActivity() != null) {
            if (item instanceof Mark) {
                Mark mark = (Mark) item;
                j.a(i);
                if (mark instanceof MusicBookGroup) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MusicActivity.class);
                    startActivity(intent);
                } else if (mark instanceof TingBookMark) {
                    o.e(getActivity(), mark.getId(), null);
                    com.qq.reader.common.monitor.i.a("event_C209", null, getApplicationContext());
                } else if (mark instanceof ComicBookMark) {
                    ComicBookMark comicBookMark = (ComicBookMark) mark;
                    OnlineTag a = v.b().a(String.valueOf(comicBookMark.getBookId()));
                    if (a != null && a.w() == 0 && mark.getIsFinish() == 1) {
                        a.h(1);
                        v.b().b(a);
                    }
                    if (e.a(getApplicationContext(), String.valueOf(comicBookMark.getBookId()))) {
                        com.qq.reader.common.monitor.i.a("event_F293", null, getApplicationContext());
                    }
                    com.qq.reader.module.comic.a.a().a(getActivity(), comicBookMark);
                } else if (mark instanceof LocalMark) {
                    Bundle bundle = new Bundle();
                    final com.qq.reader.module.bookshelf.b c = com.qq.reader.common.db.handle.a.a().c();
                    if (c != null && mark.getBookId() == c.b()) {
                        g.a().a(new ReaderDBTask() {
                            public void run() {
                                com.qq.reader.common.db.handle.a.a().b(c);
                            }
                        });
                        bundle.putString("book_activate", c.e());
                        com.qq.reader.common.monitor.i.a("event_A154", null, ReaderApplication.getApplicationImp());
                    }
                    if (getActivity() instanceof CategoryBooksActivity) {
                        bundle.putInt("readfrom", 11002);
                        toReaderPage(mark, bundle);
                    } else {
                        toReaderPage(mark, bundle);
                    }
                    com.qq.reader.appconfig.a.d.e(this.mContext.getApplicationContext(), i + 1);
                } else {
                    af.a(getApplicationContext(), (CharSequence) "该状态暂不支持。", 1).a();
                }
            }
            j.a(10, 0);
            com.qq.reader.common.monitor.i.a("event_A11", null, this.mContext);
            StatisticsManager.a().a("event_A11", null);
        }
    }

    public boolean onLongClickBook(int i) {
        if (getActivity() == null) {
            return false;
        }
        Mark mark = (Mark) this.mAdapter.getItem(i);
        if (mark == null) {
            return false;
        }
        if (mark instanceof MusicBookGroup) {
            return true;
        }
        this.currentSelectMark = mark;
        if (this.contextMenu == null) {
            this.contextMenu = new d(getActivity());
        }
        final int type = mark.getType();
        if (type == 8) {
            com.qq.reader.common.monitor.i.a("event_C210", null, getApplicationContext());
        }
        this.contextMenu.c(type);
        this.contextMenu.a(mark.getImageURI(), mark.getBookName());
        this.contextMenu.b(ao.i(mark.getReadTime()));
        if (mark.getBookId() > 0) {
            this.contextMenu.e(com.qq.reader.common.db.handle.e.a().b(String.valueOf(mark.getBookId())));
        } else {
            this.contextMenu.e(null);
        }
        if (mark instanceof LocalMark) {
            Mark mark2 = (LocalMark) mark;
            Object obj = null;
            if (4 == mark2.getType()) {
                OnlineTag a = v.b().a(mark2.getId());
                if (a != null && a.q() == 1) {
                    obj = 1;
                }
            }
            if (mark.getBookId() > 0) {
                if (mark.getPrivateProperty() == 1) {
                    this.contextMenu.a(15, "开启私密阅读", null);
                } else if (mark.getPrivateProperty() == 0) {
                    this.contextMenu.a(16, "关闭私密阅读", null);
                }
            }
            this.contextMenu.a(1, "删除本书", null);
            this.contextMenu.a(mark2.getBookShortName());
            this.contextMenu.c(mark2.getAuthor().trim());
            String percentStr = mark2.getPercentStr();
            if ((percentStr == null || percentStr.length() <= 0) && mark2.getFileLength() > mark2.getStartPoint()) {
                double startPoint = ((double) mark2.getStartPoint()) / ((double) mark2.getFileLength());
                if (startPoint > 1.0d) {
                    startPoint = 1.0d;
                }
                percentStr = ao.a(startPoint);
            }
            this.contextMenu.d(percentStr);
            this.contextMenu.a(6, "分组至", null);
            if (obj != null || !WXApiManager.getInstance(this.mContext.getApplicationContext()).getWXAPIInterface().isWXAppInstalled() || mark2.getType() == 3 || ((mark2.getBookName().endsWith(".teb") && !ao.a(mark2)) || mark2.getBookId() <= 0)) {
                this.contextMenu.a(false, mark.getBookShortName());
            } else {
                this.contextMenu.a(false, mark.getBookShortName());
            }
        } else if (mark instanceof DownloadMark) {
            DownloadBookTask downloadTask = ((DownloadMark) mark).getDownloadTask();
            if (downloadTask != null) {
                this.contextMenu.a(downloadTask.getName());
                this.contextMenu.c(downloadTask.getAuthor());
                this.contextMenu.d("0.0%");
                if (!com.qq.reader.readengine.model.a.l(downloadTask.getFullName())) {
                    TaskStateEnum state = downloadTask.getState();
                    if (state == TaskStateEnum.Paused || state == TaskStateEnum.Failed) {
                        this.contextMenu.a(4, "继续下载", null);
                    } else if (state == TaskStateEnum.Prepared || state == TaskStateEnum.Started) {
                        this.contextMenu.a(5, "暂停", null);
                    }
                }
            }
            if (mark.getBookId() > 0) {
                if (mark.getPrivateProperty() == 1) {
                    this.contextMenu.a(15, "开启私密阅读", null);
                } else if (mark.getPrivateProperty() == 0) {
                    this.contextMenu.a(16, "关闭私密阅读", null);
                }
            }
            this.contextMenu.a(0, "删除", null);
        }
        if (mark.getBookId() > 0 && type != 9) {
            this.contextMenu.a(14, "分享本书给好友", null);
        }
        this.contextMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ AbsBaseBookListFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                return this.a.onContextMenuSelected(i, bundle);
            }
        });
        this.contextMenu.a(new OnCancelListener(this) {
            final /* synthetic */ AbsBaseBookListFragment a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                AbsBaseBookListFragment.isIntercept = false;
            }
        });
        final long bookId = mark.getBookId();
        if (bookId > 0) {
            this.contextMenu.a(true);
            this.contextMenu.a(new View.OnClickListener(this) {
                final /* synthetic */ AbsBaseBookListFragment c;

                public void onClick(View view) {
                    this.c.contextMenu.dismiss();
                    if (type == 9) {
                        o.l(this.c.getActivity(), String.valueOf(bookId), null);
                    } else if (type == 8) {
                        o.b(this.c.getActivity(), String.valueOf(bookId), "", null, null);
                    } else {
                        this.c.toWebBookDetail(bookId);
                    }
                    j.a(128, 0);
                }
            });
        } else {
            this.contextMenu.a(false);
        }
        isIntercept = true;
        this.contextMenu.f_();
        j.a(8, 0);
        return true;
    }

    protected com.qq.reader.view.metro.b getCategoryOpDialog(final ArrayList<Mark> arrayList) {
        if (getActivity() == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        com.qq.reader.view.metro.b aVar = new com.qq.reader.view.metro.a(getActivity(), displayMetrics.widthPixels, "分组至", " ", this.mCategoryHandler.d());
        aVar.a(new com.qq.reader.view.metro.b.a(this) {
            final /* synthetic */ AbsBaseBookListFragment b;

            public void a(MetroItem metroItem) {
                if (arrayList != null && arrayList.size() > 0) {
                    Mark mark = null;
                    int i = 0;
                    while (i < arrayList.size()) {
                        Mark mark2;
                        if (arrayList.get(i) instanceof Mark) {
                            mark2 = (Mark) arrayList.get(i);
                        } else {
                            mark2 = mark;
                        }
                        if (mark2 != null && com.qq.reader.common.db.handle.i.c().d(mark2.getId(), metroItem.getId())) {
                            mark2.setCategoryID(metroItem.getId());
                        }
                        i++;
                        mark = mark2;
                    }
                }
                this.b.doChooseCategory(metroItem);
            }
        });
        return aVar;
    }

    public void startCloudService() {
        this.mCloudListener = new a(this) {
            final /* synthetic */ AbsBaseBookListFragment a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.cservice.cloud.f fVar, boolean z) {
                if (fVar.e() == 100) {
                    this.a.mHandler.sendEmptyMessage(Constants.CODE_SERVICE_DISABLED);
                } else if (2 == fVar.b()) {
                    if (z) {
                        this.a.mHandler.sendEmptyMessageDelayed(10006, 500);
                    } else {
                        this.a.mHandler.sendEmptyMessageDelayed(Constants.CODE_SERVICE_DISABLED, 500);
                    }
                } else if (1 != fVar.b() || !"batdel".equals(fVar.a())) {
                }
            }

            public void a(com.qq.reader.cservice.cloud.d dVar) {
                this.a.mHandler.sendMessageDelayed(this.a.mHandler.obtainMessage(10010, dVar), 500);
            }
        };
        com.qq.reader.cservice.cloud.b.a(this.mContext).a(hashCode(), this.mCloudListener);
        getBookListFromCloud();
    }

    protected void closeCloudService() {
        com.qq.reader.cservice.cloud.b.a(this.mContext).a(hashCode());
        this.mCloudListener = null;
    }

    private void getBookListFromCloud() {
        com.qq.reader.cservice.cloud.a.g hVar = new h();
        hVar.b(hashCode());
        com.qq.reader.cservice.cloud.b.a(this.mContext).a(hVar, false, this.mCloudListener);
    }
}
