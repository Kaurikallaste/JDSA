package com.kaurikallaste.jdsa.mobileid;

import ee.sk.mid.*;
import ee.sk.mid.rest.dao.MidSessionStatus;
import ee.sk.mid.rest.dao.request.MidSignatureRequest;
import ee.sk.mid.rest.dao.response.MidSignatureResponse;
import org.springframework.stereotype.Service;

@Service
public class MobileIdService implements MobileIdServiceInterface {

    private MobileIdConfigurationProperties mobileIdConfigurationProperties;

    public MobileIdService(
            MobileIdConfigurationProperties mobileIdConfigurationProperties
    ) {
        this.mobileIdConfigurationProperties = mobileIdConfigurationProperties;
    }

    @Override
    public String startSigning(String dataBase64) {
        return this.getHashToSign(dataBase64).calculateVerificationCode();
    }

    @Override
    public String finishSigning(String personalIdCode, String phoneNumber, String dataBase64) {
        MidClient client = this.getMidClient();
        MidHashToSign hashToSign = this.getHashToSign(dataBase64);

        MidSignatureRequest request = MidSignatureRequest
            .newBuilder()
            .withPhoneNumber(phoneNumber)
            .withNationalIdentityNumber(personalIdCode)
            .withHashToSign(hashToSign)
            .withLanguage(MidLanguage.ENG) //TODO make conf
            .withDisplayText("sign") //TODO make conf
            .withDisplayTextFormat(MidDisplayTextFormat.GSM7)
            .build();

        MidSignatureResponse response = client.getMobileIdConnector().sign(request);

        MidSessionStatus midSessionStatus = client
            .getSessionStatusPoller()
            .fetchFinalSessionStatus(response.getSessionID(), "/signature/session/{sessionId}");

        MidSignature signature = client.createMobileIdSignature(midSessionStatus);

        //todo, create container, sign return as base64

        return "";
    }

    private MidHashToSign getHashToSign(String dataBase64) {
        byte[] data = dataBase64.getBytes();

        return MidHashToSign
            .newBuilder()
            .withHashType(MidHashType.SHA256)
            .withDataToHash(data)
            .build();
    }

    private MidClient getMidClient() {

        return MidClient
                .newBuilder()
                .withHostUrl(mobileIdConfigurationProperties.getHostUrl())
                .withRelyingPartyUUID(mobileIdConfigurationProperties.getRelyingPartyUUID())
                .withRelyingPartyName(mobileIdConfigurationProperties.getRelyingPartyName())
                //.withTrustStore() TODO
                .withTrustedCertificates("")
                .withLongPollingTimeoutSeconds(60)
                .build();
    }
}
