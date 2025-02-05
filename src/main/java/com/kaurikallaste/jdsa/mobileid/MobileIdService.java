package com.kaurikallaste.jdsa.mobileid;

import ee.sk.mid.MidClient;
import ee.sk.mid.MidHashToSign;
import ee.sk.mid.MidHashType;
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
