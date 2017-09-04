package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class ApList extends JceStruct {
    static ArrayList cache_portList;
    static Map cache_primary;
    static Map cache_secondary;
    static ArrayList cache_speedTestIpList;
    public long backup = 0;
    public String domain = "";
    public ArrayList portList = null;
    public Map primary = null;
    public Map secondary = null;
    public ArrayList speedTestIpList = null;

    public ApList(Map map, Map map2, long j, String str, ArrayList arrayList, ArrayList arrayList2) {
        this.primary = map;
        this.secondary = map2;
        this.backup = j;
        this.domain = str;
        this.portList = arrayList;
        this.speedTestIpList = arrayList2;
    }

    public void writeTo(d dVar) {
        dVar.a(this.primary, 0);
        dVar.a(this.secondary, 1);
        dVar.a(this.backup, 2);
        dVar.a(this.domain, 3);
        dVar.a(this.portList, 4);
        dVar.a(this.speedTestIpList, 5);
    }

    public void readFrom(c cVar) {
        if (cache_primary == null) {
            cache_primary = new HashMap();
            cache_primary.put(Byte.valueOf((byte) 0), Long.valueOf(0));
        }
        this.primary = (Map) cVar.a(cache_primary, 0, true);
        if (cache_secondary == null) {
            cache_secondary = new HashMap();
            cache_secondary.put(Byte.valueOf((byte) 0), Long.valueOf(0));
        }
        this.secondary = (Map) cVar.a(cache_secondary, 1, true);
        this.backup = cVar.a(this.backup, 2, true);
        this.domain = cVar.a(3, true);
        if (cache_portList == null) {
            cache_portList = new ArrayList();
            cache_portList.add(Integer.valueOf(0));
        }
        this.portList = (ArrayList) cVar.a(cache_portList, 4, true);
        if (cache_speedTestIpList == null) {
            cache_speedTestIpList = new ArrayList();
            cache_speedTestIpList.add(Long.valueOf(0));
        }
        this.speedTestIpList = (ArrayList) cVar.a(cache_speedTestIpList, 5, true);
    }
}
