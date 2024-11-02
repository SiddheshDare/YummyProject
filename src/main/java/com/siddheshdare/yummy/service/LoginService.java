package com.siddheshdare.yummy.service;

import com.siddheshdare.yummy.dto.LoginRequest;
import com.siddheshdare.yummy.entity.Customer;
import com.siddheshdare.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final CustomerRepo customerRepo;

    public String login(LoginRequest request) {
        Customer customer = customerRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));


        if (request.getPassword().equals(customer.getPassword())) {
            return "User logged in successfully.";
        } else {
            throw new IllegalArgumentException("Invalid email or password.");
        }
    }
}
