package com.example.vivaha_v100.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "matrimony-backend", path = "/api")
public interface LookupServiceClient {

    @GetMapping("/castes/{id}/name")
    String getCasteName(@RequestParam int id);

    @GetMapping("/rashis/{id}/name")
    String getRashiName(@RequestParam int id);

    @GetMapping("/nakshatras/{id}/name")
    String getNakshatraName(@RequestParam int id);

    @GetMapping("/religions/{id}/name")
    String getReligionName(@RequestParam int id);

    @GetMapping("/gotras/{id}/name")
    String getGotraName(@RequestParam int id);

}
