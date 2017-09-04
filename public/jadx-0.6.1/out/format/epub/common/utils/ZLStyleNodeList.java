package format.epub.common.utils;

import com.qq.reader.readengine.kernel.h;
import format.epub.view.e;
import format.epub.view.z;
import java.util.ArrayList;

public class ZLStyleNodeList extends ArrayList<e> {
    public e searchNode(z zVar) {
        e eVar = null;
        int size = size() - 1;
        while (size >= 0) {
            e eVar2 = (e) get(size);
            do {
                h d = eVar2.d();
                h e = eVar2.e();
                if (d != null && zVar.b(d) > 0 && (e == null || e.j() == null || zVar.b(e) < 0)) {
                    break;
                }
                eVar2 = eVar2.f;
            } while (eVar2 != null);
            eVar2 = eVar;
            if (eVar2 != null) {
                return eVar2;
            }
            size--;
            eVar = eVar2;
        }
        return eVar;
    }

    public e getRightNode() {
        e eVar = null;
        int size = size();
        if (size > 0) {
            eVar = (e) get(size - 1);
            while (eVar.f != null) {
                eVar = eVar.f;
            }
        }
        return eVar;
    }

    public e getRightNode(int i) {
        int size = size() - 1;
        while (size >= 0) {
            e eVar = (e) get(size);
            if (eVar == null || eVar.a.a != i) {
                size--;
            } else {
                while (eVar.f != null) {
                    eVar = eVar.f;
                }
                return eVar;
            }
        }
        return null;
    }
}
