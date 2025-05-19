package com.example.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public record CustomerDTO(
    String id,
    String firstname,
    String lastname,
    String email,
    String password,
    AddressDTO address,
    boolean admin
) {}
