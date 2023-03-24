package edu.miu.eaproject.services;
import edu.miu.eaproject.entities.LoginRequest;
import edu.miu.eaproject.entities.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
}
