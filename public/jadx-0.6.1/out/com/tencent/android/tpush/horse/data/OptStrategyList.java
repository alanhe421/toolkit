package com.tencent.android.tpush.horse.data;

import android.text.format.Time;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public class OptStrategyList implements Serializable {
    private static final long serialVersionUID = 4532907276158395575L;
    private StrategyItem httpItem;
    private StrategyItem httpRedirectItem;
    private StrategyItem tcpItem;
    private StrategyItem tcpRedirectItem;
    private long timestamp;

    public StrategyItem a() {
        return this.tcpRedirectItem;
    }

    public void a(StrategyItem strategyItem) {
        this.tcpRedirectItem = strategyItem;
    }

    public StrategyItem b() {
        return this.tcpItem;
    }

    public void b(StrategyItem strategyItem) {
        this.tcpItem = strategyItem;
    }

    public StrategyItem c() {
        return this.httpRedirectItem;
    }

    public void c(StrategyItem strategyItem) {
        this.httpRedirectItem = strategyItem;
    }

    public StrategyItem d() {
        return this.httpItem;
    }

    public void d(StrategyItem strategyItem) {
        this.httpItem = strategyItem;
    }

    public StrategyItem e() {
        if (this.tcpRedirectItem != null) {
            return this.tcpRedirectItem;
        }
        if (this.tcpItem != null) {
            return this.tcpItem;
        }
        if (this.httpRedirectItem != null) {
            return this.httpRedirectItem;
        }
        if (this.httpItem != null) {
            return this.httpItem;
        }
        throw new NullReturnException("getOptStrategyItem return null,because the optstragegylist is empty");
    }

    public List f() {
        List arrayList = new ArrayList();
        if (this.tcpRedirectItem != null) {
            arrayList.add(this.tcpRedirectItem);
        }
        if (this.tcpItem != null) {
            arrayList.add(this.tcpItem);
        }
        if (this.httpRedirectItem != null) {
            arrayList.add(this.httpRedirectItem);
        }
        if (this.httpItem != null) {
            arrayList.add(this.httpItem);
        }
        return arrayList;
    }

    public List a(short s) {
        List arrayList = new ArrayList();
        if (s == (short) 1) {
            if (this.httpRedirectItem != null) {
                arrayList.add(this.httpRedirectItem);
            }
            if (this.httpItem != null) {
                arrayList.add(this.httpItem);
            }
        } else {
            if (this.tcpRedirectItem != null) {
                arrayList.add(this.tcpRedirectItem);
            }
            if (this.tcpItem != null) {
                arrayList.add(this.tcpItem);
            }
        }
        return arrayList;
    }

    public long g() {
        return this.timestamp;
    }

    public void a(long j) {
        this.timestamp = j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n------list start-----\n");
        stringBuilder.append("[TcpRedirectStrategyItem]:" + (this.tcpRedirectItem == null ? " tcpRedirect item is null" : this.tcpRedirectItem.toString()) + "\n");
        stringBuilder.append("[TCPStrategyItem]:" + (this.tcpItem == null ? " tcp item is null" : this.tcpItem.toString()) + "\n");
        stringBuilder.append("[HttpRedirectStrategyItem]:" + (this.httpRedirectItem == null ? " httpRedirect item is null" : this.httpRedirectItem.toString()) + "\n");
        stringBuilder.append("[HttpStrategyItem]:" + (this.httpItem == null ? " http item is null" : this.httpItem.toString()) + "\n");
        Time time = new Time();
        time.set(this.timestamp);
        stringBuilder.append(">>>saveTime=" + time.format2445() + ">>>\n");
        stringBuilder.append("------list end-----\n");
        return stringBuilder.toString();
    }
}
