package com.qq.reader.plugin.tts;

import com.qq.reader.plugin.tts.model.a;
import com.qq.reader.plugin.tts.model.d;
import com.qq.reader.readengine.kernel.g;
import java.io.UnsupportedEncodingException;

/* compiled from: TxtOnlineSentenceParser */
public class s extends i {
    public s() {
        this.a = 4;
    }

    protected void a(a aVar, f fVar) {
        UnsupportedEncodingException unsupportedEncodingException;
        String a = aVar.a();
        String c = aVar.c();
        g b = aVar.b();
        StringBuffer stringBuffer = new StringBuffer();
        if (a != null && a.length() > 0) {
            StringBuffer stringBuffer2 = stringBuffer;
            boolean z = false;
            for (int i = 0; i < a.length(); i++) {
                try {
                    g gVar;
                    g gVar2;
                    String a2;
                    int length;
                    int length2;
                    d dVar;
                    if (a(a.substring(i, i + 1), z)) {
                        z = true;
                        stringBuffer2.append(a.charAt(i));
                        if (i == a.length() - 1) {
                            gVar = new g();
                            gVar2 = new g();
                            a2 = a(stringBuffer2.toString(), c);
                            length = a(a, c).getBytes(c).length;
                            length2 = a2.getBytes(c).length;
                            gVar.a(b.f(), ((long) (length - length2)) + b.g());
                            gVar2.a(b.f(), b.g() + ((long) length));
                            if (length2 > 0) {
                                dVar = new d();
                                dVar.a(a2, gVar, gVar2);
                                fVar.a(dVar);
                            }
                        }
                    } else {
                        if (z) {
                            gVar = new g();
                            gVar2 = new g();
                            int length3 = a(a.substring(0, i), c).getBytes(c).length;
                            String a3 = a(stringBuffer2.toString(), c);
                            length2 = a3.getBytes(c).length;
                            gVar.a(b.f(), ((long) (length3 - length2)) + b.g());
                            gVar2.a(b.f(), b.g() + ((long) length3));
                            if (length2 > 0) {
                                d dVar2 = new d();
                                dVar2.a(a3, gVar, gVar2);
                                fVar.a(dVar2);
                            }
                            StringBuffer stringBuffer3 = new StringBuffer();
                            try {
                                stringBuffer3.append(a.charAt(i));
                                stringBuffer2 = stringBuffer3;
                            } catch (UnsupportedEncodingException e) {
                                UnsupportedEncodingException unsupportedEncodingException2 = e;
                                stringBuffer2 = stringBuffer3;
                                unsupportedEncodingException = unsupportedEncodingException2;
                                unsupportedEncodingException.printStackTrace();
                            }
                        } else {
                            stringBuffer2.append(a.charAt(i));
                        }
                        z = false;
                        if (i == a.length() - 1) {
                            gVar = new g();
                            gVar2 = new g();
                            a2 = a(stringBuffer2.toString(), c);
                            length = a(a, c).getBytes(c).length;
                            length2 = a2.getBytes(c).length;
                            gVar.a(b.f(), ((long) (length - length2)) + b.g());
                            gVar2.a(b.f(), b.g() + ((long) length));
                            if (length2 > 0) {
                                dVar = new d();
                                dVar.a(a2, gVar, gVar2);
                                fVar.a(dVar);
                            }
                        }
                    }
                } catch (UnsupportedEncodingException e2) {
                    unsupportedEncodingException = e2;
                    unsupportedEncodingException.printStackTrace();
                }
            }
        }
    }
}
