package com.xiaomi.d;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.xiaomi.smack.c;
import com.xiaomi.smack.l;
import java.net.UnknownHostException;

final class d {

    static class a {
        com.xiaomi.push.thrift.a a;
        String b;

        a() {
        }
    }

    static a a(Exception exception) {
        Throwable a;
        e(exception);
        if ((exception instanceof l) && ((l) exception).a() != null) {
            a = ((l) exception).a();
        }
        a aVar = new a();
        String message = a.getMessage();
        if (a.getCause() != null) {
            message = a.getCause().getMessage();
        }
        message = a.getClass().getSimpleName() + ":" + message;
        int a2 = c.a(a);
        if (a2 != 0) {
            aVar.a = com.xiaomi.push.thrift.a.a(a2 + com.xiaomi.push.thrift.a.i.a());
        }
        if (aVar.a == null) {
            aVar.a = com.xiaomi.push.thrift.a.q;
        }
        if (aVar.a == com.xiaomi.push.thrift.a.q) {
            aVar.b = message;
        }
        return aVar;
    }

    static a b(Exception exception) {
        Throwable a;
        e(exception);
        if ((exception instanceof l) && ((l) exception).a() != null) {
            a = ((l) exception).a();
        }
        a aVar = new a();
        String message = a.getMessage();
        if (a.getCause() != null) {
            message = a.getCause().getMessage();
        }
        int a2 = c.a(a);
        message = a.getClass().getSimpleName() + ":" + message;
        if (a2 != 0) {
            aVar.a = com.xiaomi.push.thrift.a.a(a2 + com.xiaomi.push.thrift.a.s.a());
            if (aVar.a == com.xiaomi.push.thrift.a.D) {
                Throwable cause = a.getCause();
                if (cause != null && (cause instanceof UnknownHostException)) {
                    aVar.a = com.xiaomi.push.thrift.a.C;
                }
            }
        } else {
            aVar.a = com.xiaomi.push.thrift.a.B;
        }
        if (aVar.a == com.xiaomi.push.thrift.a.A || aVar.a == com.xiaomi.push.thrift.a.B || aVar.a == com.xiaomi.push.thrift.a.D) {
            aVar.b = message;
        }
        return aVar;
    }

    static a c(Exception exception) {
        Throwable a;
        e(exception);
        if ((exception instanceof l) && ((l) exception).a() != null) {
            a = ((l) exception).a();
        }
        a aVar = new a();
        String message = a.getMessage();
        if (a.getCause() != null) {
            message = a.getCause().getMessage();
        }
        int a2 = c.a(a);
        String str = a.getClass().getSimpleName() + ":" + message;
        switch (a2) {
            case 105:
                aVar.a = com.xiaomi.push.thrift.a.I;
                break;
            case 109:
                aVar.a = com.xiaomi.push.thrift.a.J;
                break;
            case 110:
                aVar.a = com.xiaomi.push.thrift.a.K;
                break;
            case Opcodes.SUB_FLOAT_2ADDR /*199*/:
                aVar.a = com.xiaomi.push.thrift.a.L;
                break;
            case 499:
                aVar.a = com.xiaomi.push.thrift.a.O;
                if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                    aVar.a = com.xiaomi.push.thrift.a.N;
                    break;
                }
                break;
            default:
                aVar.a = com.xiaomi.push.thrift.a.M;
                break;
        }
        if (aVar.a == com.xiaomi.push.thrift.a.L || aVar.a == com.xiaomi.push.thrift.a.M || aVar.a == com.xiaomi.push.thrift.a.O) {
            aVar.b = str;
        }
        return aVar;
    }

    static a d(Exception exception) {
        Throwable a;
        e(exception);
        if ((exception instanceof l) && ((l) exception).a() != null) {
            a = ((l) exception).a();
        }
        a aVar = new a();
        String message = a.getMessage();
        int a2 = c.a(a);
        String str = a.getClass().getSimpleName() + ":" + message;
        switch (a2) {
            case 105:
                aVar.a = com.xiaomi.push.thrift.a.U;
                break;
            case 109:
                aVar.a = com.xiaomi.push.thrift.a.V;
                break;
            case 110:
                aVar.a = com.xiaomi.push.thrift.a.W;
                break;
            case Opcodes.SUB_FLOAT_2ADDR /*199*/:
                aVar.a = com.xiaomi.push.thrift.a.X;
                break;
            case 499:
                aVar.a = com.xiaomi.push.thrift.a.aa;
                if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                    aVar.a = com.xiaomi.push.thrift.a.Z;
                    break;
                }
                break;
            default:
                aVar.a = com.xiaomi.push.thrift.a.Y;
                break;
        }
        if (aVar.a == com.xiaomi.push.thrift.a.X || aVar.a == com.xiaomi.push.thrift.a.Y || aVar.a == com.xiaomi.push.thrift.a.aa) {
            aVar.b = str;
        }
        return aVar;
    }

    private static void e(Exception exception) {
        if (exception == null) {
            throw new NullPointerException();
        }
    }
}
