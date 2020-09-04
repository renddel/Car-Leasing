package com.carLease.carLease.controller;

import com.carLease.carLease.model.CreditApplication;
import com.carLease.carLease.service.CreditApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreditApplicationController {

    @Autowired
    CreditApplicationService creditApplicationService;

    @GetMapping(value = "/credit-application/{id}")
    public CreditApplication getCreditApplication(@PathVariable("id") Long id) {
        return creditApplicationService.getOne(id);
    }

    @GetMapping(value = "/credit-application")
    public List<CreditApplication> getCreditApplications() {
        return creditApplicationService.getAll();
    }

    @PostMapping(
            value = "/credit-application",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public CreditApplication createCreditApplication(@RequestBody final CreditApplication creditApplication) {
        return creditApplicationService.save(creditApplication);
    }
}
