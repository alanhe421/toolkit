package com.qq.reader.module.bookshelf;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.login.a;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.BookSetPrivateTask;
import com.qq.reader.common.utils.networkUtil.e;
import com.qq.reader.module.bookstore.qnative.model.BookSercetModel;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

/* compiled from: BookReadPrivateHelper */
public class d {
    public static void a(ReaderBaseActivity readerBaseActivity, long j, c cVar, Runnable runnable) {
        if (!e.a(ReaderApplication.getApplicationImp())) {
            af.a(ReaderApplication.getApplicationImp(), (int) R.string.net_not_available, 0).a();
        } else if (com.qq.reader.common.login.c.b()) {
            b(1, cVar, j);
        } else {
            final String R = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
            final long j2 = j;
            final c cVar2 = cVar;
            final Runnable runnable2 = runnable;
            readerBaseActivity.setLoginNextTask(new a() {
                public void a(int i) {
                    switch (i) {
                        case 1:
                            if (i.c().b(j2 + "", true) != null) {
                                if (R.equals(com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp()))) {
                                    d.b(1, cVar2, j2);
                                }
                            }
                            if (runnable2 != null) {
                                runnable2.run();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            });
            readerBaseActivity.startLogin();
        }
    }

    public static void b(ReaderBaseActivity readerBaseActivity, long j, c cVar, Runnable runnable) {
        if (!e.a(ReaderApplication.getApplicationImp())) {
            af.a(ReaderApplication.getApplicationImp(), (int) R.string.net_not_available, 0).a();
        } else if (com.qq.reader.common.login.c.b()) {
            String R = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
            if (com.qq.reader.appconfig.a.d.s(ReaderApplication.getApplicationImp(), R)) {
                com.qq.reader.appconfig.a.d.a(ReaderApplication.getApplicationImp(), R, false);
                b((Activity) readerBaseActivity, cVar, j).show();
                return;
            }
            b(0, cVar, j);
        } else if (readerBaseActivity != null) {
            final long j2 = j;
            final ReaderBaseActivity readerBaseActivity2 = readerBaseActivity;
            final c cVar2 = cVar;
            final Runnable runnable2 = runnable;
            readerBaseActivity.setLoginNextTask(new a() {
                public void a(int i) {
                    switch (i) {
                        case 1:
                            if (i.c().b(j2 + "", true) != null) {
                                String R = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
                                if (com.qq.reader.appconfig.a.d.s(ReaderApplication.getApplicationImp(), R)) {
                                    com.qq.reader.appconfig.a.d.a(ReaderApplication.getApplicationImp(), R, false);
                                    d.b(readerBaseActivity2, cVar2, j2).show();
                                } else {
                                    d.b(0, cVar2, j2);
                                }
                            }
                            if (runnable2 != null) {
                                runnable2.run();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            });
            readerBaseActivity.startLogin();
        }
    }

    public static void a(QRComicReadingBaseActivity qRComicReadingBaseActivity, long j, c cVar, Runnable runnable) {
        if (!e.a(ReaderApplication.getApplicationImp())) {
            af.a(ReaderApplication.getApplicationImp(), (int) R.string.net_not_available, 0).a();
        } else if (com.qq.reader.common.login.c.b()) {
            b(1, cVar, j);
        } else {
            final String R = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
            final long j2 = j;
            final c cVar2 = cVar;
            final Runnable runnable2 = runnable;
            qRComicReadingBaseActivity.a(new QRComicReadingBaseActivity.a() {
                public void a(boolean z) {
                    if (z) {
                        if (i.c().b(j2 + "", true) != null) {
                            if (R.equals(com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp()))) {
                                d.b(1, cVar2, j2);
                            }
                        }
                        if (runnable2 != null) {
                            runnable2.run();
                        }
                    }
                }
            });
            qRComicReadingBaseActivity.S();
        }
    }

    public static void b(QRComicReadingBaseActivity qRComicReadingBaseActivity, long j, c cVar, Runnable runnable) {
        if (!e.a(ReaderApplication.getApplicationImp())) {
            af.a(ReaderApplication.getApplicationImp(), (int) R.string.net_not_available, 0).a();
        } else if (com.qq.reader.common.login.c.b()) {
            String R = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
            if (com.qq.reader.appconfig.a.d.s(ReaderApplication.getApplicationImp(), R)) {
                com.qq.reader.appconfig.a.d.a(ReaderApplication.getApplicationImp(), R, false);
                b((Activity) qRComicReadingBaseActivity, cVar, j).show();
                return;
            }
            b(0, cVar, j);
        } else if (qRComicReadingBaseActivity != null) {
            final long j2 = j;
            final QRComicReadingBaseActivity qRComicReadingBaseActivity2 = qRComicReadingBaseActivity;
            final c cVar2 = cVar;
            final Runnable runnable2 = runnable;
            qRComicReadingBaseActivity.a(new QRComicReadingBaseActivity.a() {
                public void a(boolean z) {
                    if (i.c().b(j2 + "", true) != null) {
                        String R = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
                        if (com.qq.reader.appconfig.a.d.s(ReaderApplication.getApplicationImp(), R)) {
                            com.qq.reader.appconfig.a.d.a(ReaderApplication.getApplicationImp(), R, false);
                            d.b(qRComicReadingBaseActivity2, cVar2, j2).show();
                        } else {
                            d.b(0, cVar2, j2);
                        }
                    }
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            });
            qRComicReadingBaseActivity.S();
        }
    }

    private static void b(int i, c cVar, long j) {
        ArrayList arrayList = new ArrayList();
        BookSercetModel bookSercetModel = new BookSercetModel();
        bookSercetModel.a(j + "");
        bookSercetModel.a(i);
        arrayList.add(bookSercetModel);
        g.a().a(new BookSetPrivateTask(cVar, arrayList));
    }

    private static AlertDialog b(Activity activity, final c cVar, final long j) {
        return new AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((int) R.string.open_read_private).b((int) R.string.open_read_private_dialog_message).a((int) R.string.notices_open, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                d.b(0, cVar, j);
            }
        }).b((int) R.string.alert_dialog_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).a(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        }).a();
    }
}
