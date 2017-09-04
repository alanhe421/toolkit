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
import com.qq.reader.b;
import com.qq.reader.common.db.handle.e;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.cservice.cloud.a;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookshelf.a.a.c;
import com.qq.reader.module.bookshelf.i;
import com.qq.reader.module.bookstore.qnative.activity.NativeAudioBookPlayerActivity;
import com.qq.reader.module.bookstore.search.g;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.plugin.audiobook.MusicActivity;
import com.qq.reader.plugin.audiobook.MusicBookGroup;
import com.qq.reader.qplugin.local.TingBookMark;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.d;
import com.qq.reader.view.metro.MetroItem;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import format.epub.common.utils.f;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsBaseBookListActivity extends ReaderBaseActivity implements OnCreateContextMenuListener {
    protected static boolean q = false;
    protected Context a;
    protected i b;
    protected d c;
    protected final int d = 0;
    protected final int e = 1;
    protected final int f = 2;
    protected final int g = 3;
    protected final int h = 7;
    protected final int i = 4;
    protected final int j = 5;
    protected final int k = 6;
    protected final int l = 14;
    protected final int m = 301;
    protected final int n = 302;
    protected final int o = ErrorCode.ERROR_TBSCORE_DEXOPT_DIR;
    protected Mark p = null;
    protected c r = null;
    private a s = null;

    protected abstract void a(Mark mark);

    protected abstract void a(MetroItem metroItem);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.qq.reader.common.c.a.a(false);
        this.a = getApplicationContext();
        this.r = new c(this, this.mHandler);
    }

    public boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 70002:
                af.a(this.a.getApplicationContext(), (String) message.obj, 1).a();
                return true;
            case 300005:
                a(message.arg1);
                return true;
            case 300006:
                return b(message.arg1);
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void a(Mark mark, Bundle bundle) {
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
            b.a(intent, this);
            j.a(70, 0);
            com.qq.reader.common.monitor.i.a("event_A71", null, this.a);
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
            b.a(intent, this);
            j.a(71, 0);
            com.qq.reader.common.monitor.i.a("event_A72", null, this.a);
            StatisticsManager.a().a("event_A72", null);
        } else {
            this.p = mark;
            showFragmentDialog(ErrorCode.ERROR_TBSCORE_DEXOPT_DIR);
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return a(menuItem.getItemId(), null);
    }

    public boolean a(int i, Bundle bundle) {
        if (this.p == null) {
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
                if (this.p == null || !(this.p instanceof LocalMark)) {
                    af.a((Context) this, (CharSequence) "该状态暂不支持。", 0).a();
                } else {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name) + "分享");
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("我正在用QQ阅读看一本书《");
                    stringBuffer.append(this.p.getBookShortName());
                    stringBuffer.append("》，觉得不错，与大家一起分享。");
                    intent.putExtra("android.intent.extra.TEXT", stringBuffer.toString());
                    startActivity(Intent.createChooser(intent, "分享到"));
                }
                return true;
            case 6:
                if (this.p != null) {
                    a(this.p);
                    j.a(11, 0);
                }
                return true;
            case 7:
                WXApiManager.getInstance(this.a).registerWX();
                if (this.p != null && (this.p instanceof LocalMark)) {
                    WXApiManager.getInstance(this.a).sendBookToWX(this.a, this.p, null);
                    j.a(32, 0);
                    break;
                }
            case 14:
                String bookName = this.p.getBookName();
                this.p.getAuthor();
                getShareDialog().a(String.valueOf(this.p.getBookId()), bookName);
                if (this.p.getType() == 8) {
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
        }
        return true;
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        Dialog dialog = null;
        final CheckBox checkBox;
        switch (i) {
            case 301:
                dialog = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_del).b((int) R.string.bookstand_dialog_del).a((int) R.string.alert_dialog_ok, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.a.p != null) {
                            this.a.a(this.a.p, false);
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 302:
                View inflate = LayoutInflater.from(this).inflate(R.layout.delete_file, null);
                checkBox = (CheckBox) inflate.findViewById(R.id.confirm_check);
                TextView textView = (TextView) inflate.findViewById(R.id.confirm_text);
                checkBox.setChecked(false);
                String string = this.a.getResources().getString(R.string.bookstand_dialog_remove);
                if (this.p != null) {
                    textView.setText(String.format(string, new Object[]{this.p.getBookShortName()}));
                }
                dialog = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_remove).a(inflate).a((int) R.string.alert_dialog_ok, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.b.p != null) {
                            this.b.a(this.b.p, checkBox.isChecked());
                            if (this.b.p.getType() == 8) {
                                g.a(ReaderApplication.getApplicationImp()).a(this.b.p.getBookName(), 6);
                            } else {
                                g.a(ReaderApplication.getApplicationImp()).a(this.b.p.getBookName(), 5);
                            }
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case ErrorCode.ERROR_TBSCORE_DEXOPT_DIR /*311*/:
                View inflate2 = LayoutInflater.from(this).inflate(R.layout.delete_nofile_mark, null);
                checkBox = (CheckBox) inflate2.findViewById(R.id.confirm_check);
                checkBox.setChecked(false);
                dialog = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.dialog_shortcut_title).a(inflate2).a((int) R.string.alert_dialog_ok, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (checkBox.isChecked()) {
                            f.a(new Runnable(this) {
                                final /* synthetic */ AnonymousClass11 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    for (Mark mark : com.qq.reader.common.db.handle.i.c().g()) {
                                        if (!(mark == null || new File(mark.getId()).exists() || 4 == mark.getType())) {
                                            this.a.b.a(mark, false);
                                            try {
                                                Thread.sleep(100);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }, this.b, "正在清理,请稍候..");
                        } else if (this.b.p != null) {
                            this.b.a(this.b.p, false);
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new OnClickListener(this) {
                    final /* synthetic */ AbsBaseBookListActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
        }
        return dialog != null ? dialog : super.createDialog(i, bundle);
    }

    protected void onFragmentDialgoCancel(DialogInterface dialogInterface) {
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.isReady2Show) {
            return true;
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return b(menuItem.getItemId(), null);
    }

    protected boolean b(int i, Bundle bundle) {
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

    protected void a(Mark mark, boolean z) {
        new a(new a.a(this) {
            final /* synthetic */ AbsBaseBookListActivity a;

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

    public void a(int i) {
        Object item = this.b.getItem(i);
        if (item != null) {
            if (item instanceof Mark) {
                Mark mark = (Mark) item;
                j.a(i);
                if (mark instanceof MusicBookGroup) {
                    Intent intent = new Intent();
                    intent.setClass(this, MusicActivity.class);
                    startActivity(intent);
                } else if (mark instanceof TingBookMark) {
                    Intent intent2 = new Intent();
                    intent2.putExtra("filepath", mark.getId());
                    intent2.setClass(this, NativeAudioBookPlayerActivity.class);
                    startActivity(intent2);
                    com.qq.reader.common.monitor.i.a("event_C209", null, getApplicationContext());
                } else if (mark instanceof ComicBookMark) {
                    ComicBookMark comicBookMark = (ComicBookMark) mark;
                    OnlineTag a = v.b().a(String.valueOf(comicBookMark.getBookId()));
                    if (a != null && a.w() == 0 && mark.getIsFinish() == 1) {
                        a.h(1);
                        v.b().b(a);
                    }
                    com.qq.reader.module.comic.a.a().a((Context) this, comicBookMark);
                } else if (mark instanceof LocalMark) {
                    Bundle bundle = new Bundle();
                    final com.qq.reader.module.bookshelf.b c = com.qq.reader.common.db.handle.a.a().c();
                    if (c != null && mark.getBookId() == c.b()) {
                        com.qq.reader.common.readertask.g.a().a(new ReaderDBTask() {
                            public void run() {
                                com.qq.reader.common.db.handle.a.a().b(c);
                            }
                        });
                        bundle.putString("book_activate", c.e());
                        com.qq.reader.common.monitor.i.a("event_A154", null, ReaderApplication.getApplicationImp());
                    }
                    if (this instanceof CategoryBooksActivity) {
                        bundle.putInt("readfrom", 11002);
                        a(mark, bundle);
                    } else {
                        a(mark, bundle);
                    }
                    com.qq.reader.appconfig.a.d.e(this.a.getApplicationContext(), i + 1);
                } else {
                    af.a(getApplicationContext(), (CharSequence) "该状态暂不支持。", 1).a();
                }
            }
            j.a(10, 0);
            com.qq.reader.common.monitor.i.a("event_A11", null, this.a);
            StatisticsManager.a().a("event_A11", null);
        }
    }

    public boolean b(int i) {
        Mark mark = (Mark) this.b.getItem(i);
        if (mark == null) {
            return false;
        }
        if (mark instanceof MusicBookGroup) {
            return true;
        }
        this.p = mark;
        if (this.c == null) {
            this.c = new d(this);
        }
        final int type = mark.getType();
        if (type == 8) {
            com.qq.reader.common.monitor.i.a("event_C210", null, getApplicationContext());
        }
        this.c.c(type);
        this.c.a(mark.getImageURI(), mark.getBookName());
        this.c.b(ao.i(mark.getReadTime()));
        if (mark.getBookId() > 0) {
            this.c.e(e.a().b(String.valueOf(mark.getBookId())));
        } else {
            this.c.e(null);
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
            this.c.a(1, "删除本书", null);
            this.c.a(mark2.getBookShortName());
            this.c.c(mark2.getAuthor().trim());
            String percentStr = mark2.getPercentStr();
            if ((percentStr == null || percentStr.length() <= 0) && mark2.getFileLength() > mark2.getStartPoint()) {
                double startPoint = ((double) mark2.getStartPoint()) / ((double) mark2.getFileLength());
                if (startPoint > 1.0d) {
                    startPoint = 1.0d;
                }
                percentStr = ao.a(startPoint);
            }
            this.c.d(percentStr);
            this.c.a(6, "分组至", null);
            if (obj != null || !WXApiManager.getInstance(this.a.getApplicationContext()).getWXAPIInterface().isWXAppInstalled() || mark2.getType() == 3 || ((mark2.getBookName().endsWith(".teb") && !ao.a(mark2)) || mark2.getBookId() <= 0)) {
                this.c.a(false, mark.getBookShortName());
            } else {
                this.c.a(false, mark.getBookShortName());
            }
        } else if (mark instanceof DownloadMark) {
            this.c.a(mark.getBookShortName());
            this.c.c(mark.getAuthor());
            this.c.d("0.0%");
            DownloadBookTask downloadTask = ((DownloadMark) mark).getDownloadTask();
            if (downloadTask != null) {
                this.c.a(downloadTask.getName());
                this.c.c(downloadTask.getAuthor());
                this.c.d("0.0%");
                if (!com.qq.reader.readengine.model.a.l(downloadTask.getFullName())) {
                    TaskStateEnum state = downloadTask.getState();
                    if (state == TaskStateEnum.Paused || state == TaskStateEnum.Failed) {
                        this.c.a(4, "继续下载", null);
                    } else if (state == TaskStateEnum.Prepared || state == TaskStateEnum.Started) {
                        this.c.a(5, "暂停", null);
                    }
                }
            }
            this.c.a(0, "删除", null);
        }
        if (mark.getBookId() > 0) {
            this.c.a(14, "分享本书给好友", null);
        }
        this.c.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ AbsBaseBookListActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                return this.a.a(i, bundle);
            }
        });
        this.c.a(new OnCancelListener(this) {
            final /* synthetic */ AbsBaseBookListActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                AbsBaseBookListActivity.q = false;
            }
        });
        final long bookId = mark.getBookId();
        if (bookId > 0) {
            this.c.a(true);
            this.c.a(new View.OnClickListener(this) {
                final /* synthetic */ AbsBaseBookListActivity c;

                public void onClick(View view) {
                    this.c.c.dismiss();
                    if (type == 9) {
                        o.l(this.c, String.valueOf(bookId), null);
                    } else if (type == 8) {
                        o.b(this.c, String.valueOf(bookId), "", null, null);
                    } else {
                        this.c.toWebBookDetail(bookId);
                    }
                    j.a(128, 0);
                }
            });
        } else {
            this.c.a(false);
        }
        q = true;
        this.c.f_();
        j.a(8, 0);
        return true;
    }

    protected com.qq.reader.view.metro.b a(final ArrayList<Mark> arrayList) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        com.qq.reader.view.metro.b aVar = new com.qq.reader.view.metro.a(this, displayMetrics.widthPixels, "分组至", " ", this.r.d());
        aVar.a(new com.qq.reader.view.metro.b.a(this) {
            final /* synthetic */ AbsBaseBookListActivity b;

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
                this.b.a(metroItem);
            }
        });
        return aVar;
    }
}
