package com.kaurikallaste.jdsa.mobileid;

import java.security.cert.X509Certificate;

public interface MobileIdServiceInterface {

    public String startSigning(String dataBase64);

    public String finishSigning(String personalIdCode, String phoneNumber, String dataBase64);
}
