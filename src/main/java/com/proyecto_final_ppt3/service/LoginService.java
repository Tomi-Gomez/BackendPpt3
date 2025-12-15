package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.request.LoginRequest;
import com.proyecto_final_ppt3.controller.response.LoginResponse;

import java.util.List;

public interface LoginService {
    List<LoginResponse> login(LoginRequest loginRequest);
}
