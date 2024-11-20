package com.siddheshdare.yummy.controller;

import com.siddheshdare.yummy.dto.CustomerRequest;
import com.siddheshdare.yummy.dto.CustomerResponse;
import com.siddheshdare.yummy.dto.CustomerUpdateRequest;
import com.siddheshdare.yummy.helper.JWTHelper;
import com.siddheshdare.yummy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final JWTHelper jwtHelper;

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable String email) {
        if (!jwtHelper.validateJwtToken(token.substring(7))) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.ok(customerService.deleteCustomer(email));
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String token, @PathVariable String email, @RequestBody CustomerUpdateRequest request) {
        if (!jwtHelper.validateJwtToken(token.substring(7))) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.ok(customerService.updateCustomerDetails(email, request));
    }
}