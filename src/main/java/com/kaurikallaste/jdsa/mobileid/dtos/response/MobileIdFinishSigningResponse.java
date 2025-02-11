package com.kaurikallaste.jdsa.mobileid.dtos.response;

public class MobileIdFinishSigningResponse {
    private String signedContainerBase64;

    public String getSignedContainerBase64() {
        return signedContainerBase64;
    }

    public MobileIdFinishSigningResponse setSignedContainerBase64(String signedContainerBase64) {
        this.signedContainerBase64 = signedContainerBase64;

        return this;
    }
}
