package tencent.tls.tlvs;

import java.util.List;
import tencent.tls.tools.util;

public class tlv_t16b extends tlv_t {
    public tlv_t16b() {
        this._cmd = 363;
    }

    public byte[] get_tlv_16b(List<String> list) {
        int i;
        int i2;
        int i3;
        if (list != null) {
            int size;
            size = list.size();
            i = 0;
            for (i2 = 0; i2 < size; i2++) {
                i3 = i + 2;
                if (list.get(i2) != null) {
                    i = ((String) list.get(i2)).length() + i3;
                } else {
                    i = i3;
                }
            }
            i3 = size;
        } else {
            i = 0;
            i3 = 0;
        }
        Object obj = new byte[(i + 2)];
        util.int16_to_buf(obj, 0, i3);
        if (list != null) {
            size = 2;
            i2 = 0;
            while (i2 < i3) {
                if (list.get(i2) != null) {
                    Object bytes = ((String) list.get(i2)).getBytes();
                    util.int16_to_buf(obj, size, bytes.length);
                    size += 2;
                    System.arraycopy(bytes, 0, obj, size, bytes.length);
                    i = bytes.length + size;
                } else {
                    util.int16_to_buf(obj, size, 0);
                    i = size + 2;
                }
                i2++;
                size = i;
            }
        }
        set_data(obj, obj.length);
        return get_buf();
    }
}
