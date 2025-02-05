package com.kaurikallaste.jdsa.mobileid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "mid")
@Configuration("mobileIdConfigurationProperties")
public class MobileIdConfigurationProperties {
    private String hostUrl;
    private String relyingPartyUUID;
    private String relyingPartyName;
    private String trustStorePath;
    private String trustStorePassword;

    public String getHostUrl() {
        return hostUrl;
    }

    public String getRelyingPartyUUID() {
        return relyingPartyUUID;
    }

    public String getRelyingPartyName() {
        return relyingPartyName;
    }

    public String getTrustStorePath() {
        return trustStorePath;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }
}
