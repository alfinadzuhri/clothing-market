package com.maju_mundur.clothing_market.service;


import com.maju_mundur.clothing_market.dto.request.AuthRequest;
import com.maju_mundur.clothing_market.dto.response.LoginResponse;
import com.maju_mundur.clothing_market.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse customerRegister(AuthRequest request);
    RegisterResponse merchantRegister(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
