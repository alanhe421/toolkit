package tencent.tls.platform;

public interface TLSExchangeTicketListener {
    void OnExchangeTicketFail(TLSErrInfo tLSErrInfo);

    void OnExchangeTicketSuccess(TLSUserInfo tLSUserInfo);

    void OnExchangeTicketTimeout(TLSErrInfo tLSErrInfo);
}
