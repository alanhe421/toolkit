package com.tencent.upload.task.data;

import com.tencent.upload.Const.FileType;
import java.util.HashMap;
import java.util.Map;

public class FileInfo {
    public Map<String, String> extendInfo = new HashMap();
    public String fileId = "";
    public FileType fileType;
    public String url = "";
}
