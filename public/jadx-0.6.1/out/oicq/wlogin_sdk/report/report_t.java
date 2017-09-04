package oicq.wlogin_sdk.report;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import oicq.wlogin_sdk.tools.util;

public class report_t implements Serializable {
    public static String FILE_NAME = "report_data";
    private static final long serialVersionUID = 1;

    public static synchronized int write_tofile(report_t1 oicq_wlogin_sdk_report_report_t1, Context context) {
        int i = 0;
        synchronized (report_t.class) {
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(oicq_wlogin_sdk_report_report_t1);
                objectOutputStream.flush();
                objectOutputStream.close();
                OutputStream openFileOutput = context.openFileOutput(FILE_NAME, 0);
                byteArrayOutputStream.writeTo(openFileOutput);
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                openFileOutput.close();
            } catch (Exception e) {
                util.LOGI(e.getStackTrace().toString());
                i = -1;
            }
        }
        return i;
    }

    public static synchronized report_t1 read_fromfile(Context context) {
        report_t1 oicq_wlogin_sdk_report_report_t1;
        synchronized (report_t.class) {
            try {
                InputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
                InputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                oicq_wlogin_sdk_report_report_t1 = (report_t1) new ObjectInputStream(bufferedInputStream).readObject();
                bufferedInputStream.close();
                fileInputStream.close();
            } catch (Exception e) {
                util.LOGI("read " + FILE_NAME + "failed");
                oicq_wlogin_sdk_report_report_t1 = null;
            }
        }
        return oicq_wlogin_sdk_report_report_t1;
    }

    public static synchronized void delete_file(Context context) {
        synchronized (report_t.class) {
            context.deleteFile(FILE_NAME);
        }
    }
}
