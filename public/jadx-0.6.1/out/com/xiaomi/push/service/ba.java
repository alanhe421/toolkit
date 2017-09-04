package com.xiaomi.push.service;

import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import android.text.TextUtils;

class ba implements OnAccountsUpdateListener {
    final /* synthetic */ az a;

    ba(az azVar) {
        this.a = azVar;
    }

    public void onAccountsUpdated(Account[] accountArr) {
        Object obj = null;
        if (accountArr != null && accountArr.length > 0) {
            for (Account account : accountArr) {
                if (account != null && TextUtils.equals("com.xiaomi", account.type)) {
                    break;
                }
            }
            Account account2 = null;
            if (!(account2 == null || TextUtils.isEmpty(account2.name))) {
                obj = 1;
            }
            boolean c = bb.a(az.a(this.a)).c();
            if (obj != null && !c) {
                bb.a(az.a(this.a)).a(account2.name);
                az.a(this.a, account2.name);
            } else if (obj == null && c) {
                bb.a(az.a(this.a)).a();
                az.a(this.a, "0");
            } else if (obj != null && c && !TextUtils.equals(bb.a(az.a(this.a)).b(), account2.name)) {
                bb.a(az.a(this.a)).a(account2.name);
                az.a(this.a, account2.name);
            }
        }
    }
}
