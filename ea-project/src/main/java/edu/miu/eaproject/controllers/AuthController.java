package edu.miu.eaproject.controllers;
import edu.miu.eaproject.entities.LoginRequest;
import edu.miu.eaproject.entities.LoginResponse;
import edu.miu.eaproject.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authenticate")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }


}
