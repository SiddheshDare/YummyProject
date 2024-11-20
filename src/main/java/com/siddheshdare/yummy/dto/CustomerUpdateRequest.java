package com.siddheshdare.yummy.dto;

import lombok.Data;

@Data
public class CustomerUpdateRequest {
    private String name;
    private String lastName;
    private String address;
    private String city;
    private String pincode;

   //Add other fields except email and password
}