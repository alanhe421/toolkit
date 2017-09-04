package com.qq.reader.module.feed.c;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;

/* compiled from: FeedOperationStatisticsUtil */
public class b {
    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            int aS = d.aS(ReaderApplication.getApplicationImp().getApplicationContext());
            if (str.equals("103176")) {
                if (aS == 1) {
                    i.a("event_F38", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F39", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F40", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (str.equals("103177")) {
                i.a("event_F63", null, ReaderApplication.getApplicationImp().getApplicationContext());
            } else if (str.equals("103178")) {
                i.a("event_F64", null, ReaderApplication.getApplicationImp().getApplicationContext());
            } else if (str.equals("103179")) {
                if (aS == 1) {
                    i.a("event_F59", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F60", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (str.equals("103181")) {
                if (aS == 1) {
                    i.a("event_F44", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F45", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F46", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (str.equals("103184")) {
                if (aS == 1) {
                    i.a("event_F41", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F42", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F43", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (str.equals("103470")) {
                if (aS == 1) {
                    i.a("event_F47", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F48", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F49", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (str.equals("103186")) {
                if (aS == 1) {
                    i.a("event_F53", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F54", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F55", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (str.equals("103189")) {
                if (aS == 1) {
                    i.a("event_F56", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F57", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F58", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (str.equals("103192")) {
                i.a("event_F62", null, ReaderApplication.getApplicationImp().getApplicationContext());
            } else if (str.equals("103471")) {
                if (aS == 1) {
                    i.a("event_F144", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F145", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F146", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (str.equals("103493")) {
                if (aS == 1) {
                    i.a("event_F319", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F320", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F321", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            } else if (!str.equals("103194")) {
            } else {
                if (aS == 1) {
                    i.a("event_F147", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else if (aS == 2) {
                    i.a("event_F148", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } else {
                    i.a("event_F149", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            }
        }
    }
}
