package com.siddheshdare.yummy.service;

import com.siddheshdare.yummy.dto.CustomerRequest;
import com.siddheshdare.yummy.dto.CustomerResponse;
import com.siddheshdare.yummy.entity.Customer;
import com.siddheshdare.yummy.mapper.CustomerMapper;
import com.siddheshdare.yummy.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

    public String login(String email, String password) {
        Customer customer = repo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email"));
        if(!customer.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return "User logged in successfully";
    }
}
