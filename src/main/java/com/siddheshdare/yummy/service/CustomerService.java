package com.siddheshdare.yummy.service;

import com.siddheshdare.yummy.dto.CustomerRequest;
import com.siddheshdare.yummy.dto.CustomerResponse;
import com.siddheshdare.yummy.dto.LoginRequest;
import com.siddheshdare.yummy.entity.Customer;
import com.siddheshdare.yummy.exception.CustomerNotFoundException;
import com.siddheshdare.yummy.helper.EncryptionService;
import com.siddheshdare.yummy.helper.JWTHelper;
import com.siddheshdare.yummy.mapper.CustomerMapper;
import com.siddheshdare.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.siddheshdare.yummy.dto.CustomerUpdateRequest;

import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
               .orElseThrow(() -> new CustomerNotFoundException(
                       String.format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
               ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }

    public String deleteCustomer(String email) {
        Customer customer = getCustomer(email);
        customerRepo.delete(customer);
        return "Customer Deleted Successfully";
    }

    public String updateCustomerDetails(String email, CustomerUpdateRequest request) {
        Customer customer = getCustomer(email);
        customer.setFirstName(request.getName());
         customer.setLastName(request.getLastName());
         customer.setAddress(request.getAddress());
         customer.setCity(request.getCity());
         customer.setPincode(request.getPincode());
        customerRepo.save(customer);
        return "Customer Details Updated Successfully";
    }
}
