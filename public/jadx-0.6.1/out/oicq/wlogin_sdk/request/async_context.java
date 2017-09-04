package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.b.ae;
import oicq.wlogin_sdk.b.az;
import oicq.wlogin_sdk.b.bj;
import oicq.wlogin_sdk.b.bo;
import oicq.wlogin_sdk.b.cn;
import oicq.wlogin_sdk.b.co;
import oicq.wlogin_sdk.b.g;
import oicq.wlogin_sdk.b.h;
import oicq.wlogin_sdk.devicelock.DevlockInfo;
import oicq.wlogin_sdk.tools.ErrMsg;

public class async_context {
    public byte[] _G = new byte[16];
    public long _appid = 0;
    DevlockInfo _devlock_info = new DevlockInfo();
    public byte[] _dpwd = new byte[16];
    boolean _isSmslogin = false;
    ErrMsg _last_err_msg = new ErrMsg();
    public int _last_flowid = 0;
    public int _login_bitmap = 0;
    public int _main_sigmap = 0;
    String _mpasswd = "";
    long _msalt = 0;
    public long _sappid = 0;
    public boolean _sec_guid_flag = false;
    String _smslogin_msg = "";
    int _smslogin_msgcnt = 0;
    int _smslogin_timelimit = 0;
    public long _sub_appid = 0;
    public long[] _sub_appid_list = new long[0];
    public g _t104 = new g();
    public h _t105 = new h();
    public ae _t126 = new ae();
    public az _t165 = new az();
    public bj _t174 = new bj();
    public bo _t17b = new bo();
    public cn _t402 = new cn();
    public co _t403 = new co();
    public byte[] _tgtgt_key = new byte[16];
    public byte[] _tmp_no_pic_sig = new byte[0];
    public byte[] _tmp_pwd = new byte[16];
    public int _tmp_pwd_type = 0;
}
