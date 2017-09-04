package com.qq.reader.tinker;

import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tencent.upload.log.trace.TracerConfig;

/* compiled from: SampleTinkerReport */
public class h {
    private static a a = null;

    /* compiled from: SampleTinkerReport */
    interface a {
        void a(int i);

        void a(String str);
    }

    public static void a(a aVar) {
        a = aVar;
    }

    public static void a(boolean z) {
        if (a != null) {
            a.a(2);
            a.a(70);
            if (z) {
                a.a(3);
            }
        }
    }

    public static void a(int i) {
        if (a != null) {
            switch (i) {
                case -11:
                    a.a(80);
                    return;
                case -9:
                    a.a(79);
                    return;
                case -8:
                    a.a(77);
                    return;
                case -7:
                    a.a(78);
                    return;
                case -6:
                    a.a(76);
                    return;
                case -5:
                    a.a(75);
                    return;
                case -4:
                    a.a(73);
                    return;
                case -3:
                    a.a(72);
                    return;
                case -2:
                    a.a(74);
                    return;
                case -1:
                    a.a(71);
                    return;
                default:
                    return;
            }
        }
    }

    public static void b(int i) {
        if (a != null) {
            switch (i) {
                case -9:
                    a.a(358);
                    return;
                case -8:
                    a.a(357);
                    return;
                case -7:
                    a.a(355);
                    return;
                case -6:
                    a.a(354);
                    return;
                case -5:
                    a.a(353);
                    return;
                case -4:
                    a.a(352);
                    return;
                case -3:
                    a.a(351);
                    return;
                case -2:
                    a.a(356);
                    return;
                case -1:
                    a.a(350);
                    return;
                default:
                    return;
            }
        }
    }

    public static void a(long j) {
        if (a != null) {
            a.a(6);
            if (j < 0) {
                TinkerLog.e("Tinker.SampleTinkerReport", "hp_report report load cost failed, invalid cost", new Object[0]);
            } else if (j <= 500) {
                a.a(400);
            } else if (j <= 1000) {
                a.a(401);
            } else if (j <= 3000) {
                a.a(402);
            } else if (j <= 5000) {
                a.a(403);
            } else {
                a.a(404);
            }
        }
    }

    public static void a() {
        if (a != null) {
            a.a(309);
        }
    }

    public static void c(int i) {
        if (a != null) {
            switch (i) {
                case 1:
                    a.a(305);
                    return;
                case 2:
                    a.a(306);
                    return;
                case 3:
                    a.a(303);
                    return;
                case 4:
                    a.a(307);
                    return;
                case 5:
                    a.a(304);
                    return;
                case 6:
                    a.a(308);
                    return;
                default:
                    return;
            }
        }
    }

    public static void d(int i) {
        if (a != null) {
            switch (i) {
                case 3:
                    a.a(300);
                    return;
                case 5:
                    a.a(301);
                    return;
                case 6:
                    a.a(302);
                    return;
                default:
                    return;
            }
        }
    }

    public static void a(Throwable th, int i) {
        int i2 = 1;
        if (a != null) {
            switch (i) {
                case -4:
                    a.a(251);
                    i2 = 0;
                    break;
                case -3:
                    if (!th.getMessage().contains(ShareConstants.CHECK_RES_INSTALL_FAIL)) {
                        a.a(254);
                        TinkerLog.e("Tinker.SampleTinkerReport", "tinker res reflect fail:" + th.getMessage(), new Object[0]);
                        i2 = 0;
                        break;
                    }
                    a.a(255);
                    TinkerLog.e("Tinker.SampleTinkerReport", "tinker res check fail:" + th.getMessage(), new Object[0]);
                    break;
                case -2:
                    if (!th.getMessage().contains(ShareConstants.CHECK_DEX_INSTALL_FAIL)) {
                        a.a(252);
                        TinkerLog.e("Tinker.SampleTinkerReport", "tinker dex reflect fail:" + th.getMessage(), new Object[0]);
                        i2 = 0;
                        break;
                    }
                    a.a(253);
                    TinkerLog.e("Tinker.SampleTinkerReport", "tinker dex check fail:" + th.getMessage(), new Object[0]);
                    break;
                case -1:
                    a.a(250);
                    break;
            }
            i2 = 0;
            if (i2 == 0) {
                a.a("Tinker Exception:load tinker occur exception " + m.b(th));
            }
        }
    }

    public static void b() {
        if (a != null) {
            a.a(4);
        }
    }

    public static void a(Throwable th) {
        if (a != null) {
            a.a(121);
            a.a("Tinker Exception:apply tinker occur exception " + m.b(th));
        }
    }

    public static void c() {
        if (a != null) {
            a.a(122);
        }
    }

    public static void d() {
        if (a != null) {
            a.a(180);
        }
    }

    public static void e(int i) {
        if (a != null) {
            switch (i) {
                case 1:
                    a.a((int) Opcodes.AND_INT_2ADDR);
                    return;
                case 3:
                    a.a((int) Opcodes.OR_INT_2ADDR);
                    return;
                case 5:
                    a.a((int) Opcodes.XOR_INT_2ADDR);
                    return;
                case 6:
                    a.a((int) Opcodes.SHL_INT_2ADDR);
                    return;
                default:
                    return;
            }
        }
    }

    public static void a(long j, boolean z) {
        if (a != null) {
            if (z) {
                a.a(5);
            }
            if (z) {
                a.a(100);
            } else {
                a.a(101);
            }
            TinkerLog.i("Tinker.SampleTinkerReport", "hp_report report apply cost = %d", Long.valueOf(j));
            if (j < 0) {
                TinkerLog.e("Tinker.SampleTinkerReport", "hp_report report apply cost failed, invalid cost", new Object[0]);
            } else if (j <= 5000) {
                if (z) {
                    a.a(200);
                } else {
                    a.a(205);
                }
            } else if (j <= TracerConfig.LOG_FLUSH_DURATION) {
                if (z) {
                    a.a(201);
                } else {
                    a.a(206);
                }
            } else if (j <= 30000) {
                if (z) {
                    a.a(202);
                } else {
                    a.a(207);
                }
            } else if (j <= BuglyBroadcastRecevier.UPLOADLIMITED) {
                if (z) {
                    a.a(203);
                } else {
                    a.a(208);
                }
            } else if (z) {
                a.a(204);
            } else {
                a.a(209);
            }
        }
    }

    public static void f(int i) {
        if (a != null) {
            TinkerLog.i("Tinker.SampleTinkerReport", "hp_report package check failed, error = %d", Integer.valueOf(i));
            switch (i) {
                case -9:
                    a.a((int) Opcodes.DIV_LONG);
                    return;
                case -8:
                    a.a((int) Opcodes.MUL_LONG);
                    return;
                case -7:
                    a.a((int) Opcodes.SUB_LONG);
                    return;
                case -6:
                    a.a((int) Opcodes.USHR_INT);
                    return;
                case -5:
                    a.a((int) Opcodes.SHR_INT);
                    return;
                case -4:
                    a.a((int) Opcodes.SHL_INT);
                    return;
                case -3:
                    a.a((int) Opcodes.XOR_INT);
                    return;
                case -2:
                    a.a((int) Opcodes.ADD_LONG);
                    return;
                case -1:
                    a.a((int) Opcodes.OR_INT);
                    return;
                default:
                    return;
            }
        }
    }

    public static void b(Throwable th) {
        if (a != null) {
            a.a(120);
            a.a("Tinker Exception:apply tinker occur exception " + m.b(th));
        }
    }

    public static void e() {
        if (a != null) {
            a.a(7);
        }
    }

    public static void f() {
        if (a != null) {
            if (ShareTinkerInternals.isVmArt()) {
                a.a(9);
            } else {
                a.a(8);
            }
        }
    }

    public static void g() {
        if (a != null) {
            a.a(10);
        }
    }
}
