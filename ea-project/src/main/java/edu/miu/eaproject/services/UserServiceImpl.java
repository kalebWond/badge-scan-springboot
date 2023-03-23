package edu.miu.eaproject.services;
import edu.miu.eaproject.Util.JwtTokenUtil;
import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtTokenUtil jwtHelper;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication result;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
            throw e;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw ex;
        }
        final String accessToken = jwtHelper.generateToken(loginRequest.getUsername(),
                new HashMap<String, Object>() {
                    {
                        put("userId", ((UserDetailsSecurity) result.getPrincipal()).getId());
                        put("username", ((UserDetailsSecurity) result.getPrincipal()).getUsername());
                        put("password", ((UserDetailsSecurity) result.getPrincipal()).getPassword());

                    } });
        Member member = memberRepository.findMemberByEmailIgnoreCase(loginRequest.getUsername());
        MemberLoginResponseDTO memberDTO = modelMapper.map(member, MemberLoginResponseDTO.class);
        var loginResponse = new LoginResponse(accessToken,memberDTO);
        return loginResponse;
    }

}
