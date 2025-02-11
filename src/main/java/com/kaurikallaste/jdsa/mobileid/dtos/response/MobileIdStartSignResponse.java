package com.kaurikallaste.jdsa.mobileid.dtos.response;

public class MobileIdStartSignResponse {
    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public MobileIdStartSignResponse setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;

        return this;
    }
}
