package edu.miu.eaproject.controllers;
import edu.miu.eaproject.entities.LoginRequest;
import edu.miu.eaproject.entities.LoginResponse;
import edu.miu.eaproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginControler {
    @Autowired
  UserService userService;
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        var loginResponse = userService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}
