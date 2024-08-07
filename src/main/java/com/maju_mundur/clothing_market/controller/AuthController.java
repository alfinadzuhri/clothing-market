package com.maju_mundur.clothing_market.controller;

import com.maju_mundur.clothing_market.constant.APIUrl;
import com.maju_mundur.clothing_market.dto.request.AuthRequest;
import com.maju_mundur.clothing_market.dto.response.CommonResponse;
import com.maju_mundur.clothing_market.dto.response.LoginResponse;
import com.maju_mundur.clothing_market.dto.response.RegisterResponse;
import com.maju_mundur.clothing_market.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.AUTH_API)
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/customer-register")
    public ResponseEntity<CommonResponse<?>> customerRegister(@RequestBody AuthRequest request){
        RegisterResponse register = authService.customerRegister(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully save data")
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/merchant-register")
    public ResponseEntity<CommonResponse<?>> merchantRegister(@RequestBody AuthRequest request){
        RegisterResponse register = authService.merchantRegister(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully save data")
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<CommonResponse<?>> login(@RequestBody AuthRequest request){
        LoginResponse loginResponse = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("login successfully")
                .data(loginResponse)
                .build();

        return ResponseEntity.ok(response);
    }

}
