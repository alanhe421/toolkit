package com.qq.reader.module.findhome.base;

import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;

public abstract class FindHomeBaseCard extends a {
    private View mView;

    public FindHomeBaseCard(b bVar, String str) {
        super(bVar, str);
    }

    public void refreshData() {
    }

    public View getView() {
        return this.mView;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public View getRootView() {
        return getView();
    }

    protected void handleExposedStatistics(final int i) {
        g.a().a(new ReaderShortTask() {
            public String getTaskName() {
                return "FindHomeBaseCard";
            }

            public void run() {
                super.run();
                switch (i) {
                    case 0:
                        i.a("event_B388", null, ReaderApplication.getApplicationImp());
                        return;
                    case 1:
                        i.a("event_B385", null, ReaderApplication.getApplicationImp());
                        return;
                    case 2:
                        i.a("event_B386", null, ReaderApplication.getApplicationImp());
                        return;
                    case 3:
                        i.a("event_B387", null, ReaderApplication.getApplicationImp());
                        return;
                    case 4:
                        i.a("event_B384", null, ReaderApplication.getApplicationImp());
                        return;
                    case 5:
                        i.a("event_B382", null, ReaderApplication.getApplicationImp());
                        return;
                    case 6:
                        i.a("event_B380", null, ReaderApplication.getApplicationImp());
                        return;
                    case 7:
                        i.a("event_B381", null, ReaderApplication.getApplicationImp());
                        return;
                    case 8:
                        i.a("event_B383", null, ReaderApplication.getApplicationImp());
                        return;
                    default:
                        return;
                }
            }
        });
    }

    protected void handleClickStatistics(final int i) {
        g.a().a(new ReaderShortTask() {
            public String getTaskName() {
                return "FindHomeBaseCard";
            }

            public void run() {
                super.run();
                switch (i) {
                    case 0:
                        i.a("event_C85", null, ReaderApplication.getApplicationImp());
                        return;
                    case 1:
                        i.a("event_F318", null, ReaderApplication.getApplicationImp());
                        return;
                    case 2:
                        i.a("event_C213", null, ReaderApplication.getApplicationImp());
                        return;
                    case 3:
                        i.a("event_B377", null, ReaderApplication.getApplicationImp());
                        return;
                    case 4:
                        i.a("event_C145", null, ReaderApplication.getApplicationImp());
                        return;
                    case 5:
                        i.a("event_D209", null, ReaderApplication.getApplicationImp());
                        return;
                    case 6:
                        i.a("event_F2", null, ReaderApplication.getApplicationImp());
                        return;
                    case 7:
                        i.a("event_C102", null, ReaderApplication.getApplicationImp());
                        return;
                    case 8:
                        i.a("event_C84", null, ReaderApplication.getApplicationImp());
                        return;
                    default:
                        return;
                }
            }
        });
    }
}
