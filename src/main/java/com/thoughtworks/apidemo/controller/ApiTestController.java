package com.thoughtworks.apidemo.controller;

import com.thoughtworks.apidemo.dto.AddResponse;
import com.thoughtworks.apidemo.service.ApiTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ApiTestController {


    @Autowired
    private ApiTestService apiTestService;

    @GetMapping("/api/increase")
    public AddResponse getCheckAddResult(@RequestParam("number") Integer number) {
        return apiTestService.getCheckNumberResult(number);
    }

}
