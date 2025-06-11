package com.vti.auth_service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponse {
    private int status;
    private String message;
    private Long userId;
    private String accessToken;
    private String refreshToken;
}
