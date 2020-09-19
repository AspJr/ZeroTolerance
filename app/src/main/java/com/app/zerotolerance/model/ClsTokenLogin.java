package com.app.zerotolerance.model;

public class ClsTokenLogin {
    private ClsDetailLogin detail;
    private String access_token;
    private String token_type;
    private String expires_at;

    public ClsDetailLogin getDetail() {
        return detail;
    }

    public void setDetail(ClsDetailLogin detail) {
        this.detail = detail;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
}
