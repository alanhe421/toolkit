package com.nhaarman.listviewanimations.itemmanipulation.b.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: Util */
class d {
    static Collection<Integer> a(Collection<Integer> collection, List<Integer> list) {
        Collection<Integer> arrayList = new ArrayList(collection);
        Collections.sort(list, Collections.reverseOrder());
        Collection arrayList2 = new ArrayList();
        for (Integer intValue : list) {
            int intValue2 = intValue.intValue();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                int intValue3 = ((Integer) it.next()).intValue();
                if (intValue3 > intValue2) {
                    it.remove();
                    arrayList2.add(Integer.valueOf(intValue3 - 1));
                } else if (intValue3 == intValue2) {
                    it.remove();
                } else {
                    arrayList2.add(Integer.valueOf(intValue3));
                }
            }
            arrayList.clear();
            arrayList.addAll(arrayList2);
            arrayList2.clear();
        }
        return arrayList;
    }
}
