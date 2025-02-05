package com.kaurikallaste.jdsa.mobileid;

import com.kaurikallaste.jdsa.mobileid.dtos.MobileIdSigningDTO;
import ee.sk.mid.MidInputUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mobileid")
public class MobileIdController {

    private MobileIdServiceInterface mobileIdService;

    public MobileIdController(
        MobileIdService mobileIdService
    ) {
        this.mobileIdService = mobileIdService;
    }

    @PostMapping("/startSigning")
    public String startSigning(@RequestBody MobileIdSigningDTO mobileIdSigningDTO) {
        // Validate on the first request so it fails faster
        String personalIdCode = MidInputUtil.getValidatedNationalIdentityNumber(mobileIdSigningDTO.getPersonalIdCode());
        String phoneNumber = MidInputUtil.getValidatedPhoneNumber(mobileIdSigningDTO.getMobileNumber());

        return mobileIdService.startSigning(mobileIdSigningDTO.getDataBase64());
    }

    @PostMapping("/finishSigning")
    public String finishSigning(@RequestBody MobileIdSigningDTO mobileIdSigningDTO) {
        return "dataInBase64";
    }
}
