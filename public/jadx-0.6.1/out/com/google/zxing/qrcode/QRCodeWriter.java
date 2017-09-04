package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

public final class QRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeFormat);
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            int intValue;
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            if (map != null) {
                ErrorCorrectionLevel errorCorrectionLevel2 = (ErrorCorrectionLevel) map.get(EncodeHintType.ERROR_CORRECTION);
                if (errorCorrectionLevel2 != null) {
                    errorCorrectionLevel = errorCorrectionLevel2;
                }
                Integer num = (Integer) map.get(EncodeHintType.MARGIN);
                if (num != null) {
                    intValue = num.intValue();
                    return renderResult(Encoder.encode(str, errorCorrectionLevel, map), i, i2, intValue);
                }
            }
            intValue = 4;
            return renderResult(Encoder.encode(str, errorCorrectionLevel, map), i, i2, intValue);
        }
    }

    private static BitMatrix renderResult(QRCode qRCode, int i, int i2, int i3) {
        ByteMatrix matrix = qRCode.getMatrix();
        if (matrix == null) {
            throw new IllegalStateException();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int i4 = (i3 * 2) + width;
        int i5 = (i3 * 2) + height;
        int max = Math.max(i, i4);
        int max2 = Math.max(i2, i5);
        int min = Math.min(max / i4, max2 / i5);
        i5 = (max - (width * min)) / 2;
        i4 = (max2 - (height * min)) / 2;
        BitMatrix bitMatrix = new BitMatrix(max, max2);
        max2 = i4;
        for (int i6 = 0; i6 < height; i6++) {
            max = 0;
            i4 = i5;
            while (max < width) {
                if (matrix.get(max, i6) == (byte) 1) {
                    bitMatrix.setRegion(i4, max2, min, min);
                }
                max++;
                i4 += min;
            }
            max2 += min;
        }
        return bitMatrix;
    }
}
