package com.kaurikallaste.jdsa.mobileid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobileid")
public class MobileIdController {

    @GetMapping("")
    public String hello() {
        return "it lives";
    }
}
