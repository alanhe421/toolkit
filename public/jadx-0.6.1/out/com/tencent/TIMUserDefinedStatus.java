package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.ClientType;
import com.tencent.imcore.InstStatus;
import com.tencent.imcore.InstStatusVec;
import com.tencent.imcore.UserStatus;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TIMUserDefinedStatus {
    private String identifier = "";
    private List<Instance> instances = new ArrayList();

    public class Instance {
        private ClientType clientType;
        private String userdefined = "";

        protected Instance(InstStatus instStatus) {
            setClientType(ClientType.swigToEnum((int) instStatus.getDdwClientType()));
            try {
                setUserdefined(new String(instStatus.getSUserDefine(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        public ClientType getClientType() {
            return this.clientType;
        }

        public String getUserdefined() {
            return this.userdefined;
        }

        protected void setClientType(ClientType clientType) {
            this.clientType = clientType;
        }

        protected void setUserdefined(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.userdefined = str;
            }
        }
    }

    protected TIMUserDefinedStatus(UserStatus userStatus) {
        if (userStatus != null) {
            setIdentifier(userStatus.getSUserId());
            InstStatusVec stInstStatus = userStatus.getStInstStatus();
            if (stInstStatus.size() > 0) {
                for (int i = 0; ((long) i) < stInstStatus.size(); i++) {
                    this.instances.add(new Instance(stInstStatus.get(i)));
                }
            }
        }
    }

    protected void addInstace(Instance instance) {
        this.instances.add(instance);
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public List<Instance> getInstances() {
        return this.instances;
    }

    protected void setIdentifier(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.identifier = str;
        }
    }
}
