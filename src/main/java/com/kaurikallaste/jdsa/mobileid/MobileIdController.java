package com.kaurikallaste.jdsa.mobileid;

import com.kaurikallaste.jdsa.mobileid.dtos.MobileIdSigningDTO;
import com.kaurikallaste.jdsa.mobileid.dtos.response.MobileIdStartSignResponse;
import ee.sk.mid.MidInputUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mid")
public class MobileIdController {

    private MobileIdServiceInterface mobileIdService;

    public MobileIdController(
        MobileIdService mobileIdService
    ) {
        this.mobileIdService = mobileIdService;
    }

    @PostMapping("/startSigning")
    public MobileIdStartSignResponse startSigning(@RequestBody MobileIdSigningDTO mobileIdSigningDTO) {
        // Validate on the first request so it fails faster
        String personalIdCode = MidInputUtil.getValidatedNationalIdentityNumber(mobileIdSigningDTO.getPersonalIdCode());
        String phoneNumber = MidInputUtil.getValidatedPhoneNumber(mobileIdSigningDTO.getMobileNumber());

        return (new MobileIdStartSignResponse()).setVerificationCode(
            mobileIdService.startSigning(mobileIdSigningDTO.getDataBase64())
        );
    }

    @PostMapping("/finishSigning")
    public String finishSigning(@RequestBody MobileIdSigningDTO mobileIdSigningDTO) {
        return this.mobileIdService.finishSigning(
            mobileIdSigningDTO.getPersonalIdCode(),
            mobileIdSigningDTO.getMobileNumber(),
            mobileIdSigningDTO.getDataBase64()
        );
    }
}
