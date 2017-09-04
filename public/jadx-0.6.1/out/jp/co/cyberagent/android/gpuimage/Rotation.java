package jp.co.cyberagent.android.gpuimage;

import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public enum Rotation {
    NORMAL,
    ROTATION_90,
    ROTATION_180,
    ROTATION_270;

    public int asInt() {
        switch (this) {
            case NORMAL:
                return 0;
            case ROTATION_90:
                return 90;
            case ROTATION_180:
                return 180;
            case ROTATION_270:
                return im_common.WPA_QZONE;
            default:
                throw new IllegalStateException("Unknown Rotation!");
        }
    }

    public static Rotation fromInt(int i) {
        switch (i) {
            case 0:
                return NORMAL;
            case Opcodes.IPUT_WIDE /*90*/:
                return ROTATION_90;
            case 180:
                return ROTATION_180;
            case im_common.WPA_QZONE /*270*/:
                return ROTATION_270;
            case 360:
                return NORMAL;
            default:
                throw new IllegalStateException(i + " is an unknown rotation. Needs to be either 0, 90, 180 or 270!");
        }
    }
}
