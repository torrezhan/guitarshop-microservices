package com.example.customer.security;

public record AuthenticationRequest(
    String email,
    String password
) {} 