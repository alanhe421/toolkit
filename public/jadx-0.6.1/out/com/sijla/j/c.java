package com.sijla.j;

import com.sijla.j.a.b;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c {
    public boolean a(String str, List<String> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        return a(b.j() + str, a(list));
    }

    public boolean b(String str, List<String> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        return a(str, a(list));
    }

    public void a(String str, ArrayList<ArrayList<String>> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuffer.append(a((ArrayList) it.next()));
            }
            a(b.j() + str, stringBuffer);
        }
    }

    private StringBuffer a(List<String> list) {
        String property = System.getProperty("line.separator", "\n");
        String str = "\t";
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            stringBuffer.append((String) list.get(i));
            if (i == list.size() - 1) {
                stringBuffer.append(property);
            } else {
                stringBuffer.append(str);
            }
        }
        return stringBuffer;
    }

    private boolean a(String str, StringBuffer stringBuffer) {
        File a = b.a(str, b.e(stringBuffer.toString()));
        return a != null && a.exists();
    }
}
