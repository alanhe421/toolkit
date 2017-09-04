package com.xiaomi.channel.commonutils.android;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import com.xiaomi.channel.commonutils.b.c;

public class f {
    public static Account a(Context context) {
        Account account = null;
        try {
            if (b(context)) {
                Account[] accountsByType = AccountManager.get(context).getAccountsByType("com.xiaomi");
                if (accountsByType != null && accountsByType.length > 0) {
                    account = accountsByType[0];
                }
            }
        } catch (Exception e) {
            c.d(e.toString());
        }
        return account;
    }

    public static boolean b(Context context) {
        try {
            return context.getPackageManager().checkPermission("android.permission.GET_ACCOUNTS", context.getPackageName()) == 0;
        } catch (Throwable th) {
            return false;
        }
    }
}
