package edu.miu.eaproject.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
//    private String refreshToken;

    @Data
    @Builder
    public static class HttpResponse {
        private String message;
        private HttpStatus status;
    }
}
