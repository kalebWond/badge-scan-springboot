//package edu.miu.eaproject.services;
//import edu.miu.eaproject.Util.JwtTokenUtil;
//import edu.miu.eaproject.entities.LoginRequest;
//import edu.miu.eaproject.entities.LoginResponse;
//import edu.miu.eaproject.repositories.MemberRepository;
//import edu.miu.eaproject.repositories.RoleRepo;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final MemberRepository memberRepository;
//    private final RoleRepo roleRepo;
//    private final JwtTokenUtil jwtUtil;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//
//        try {
//
//            Authentication result = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//            );
//            final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());
//
//            final String accessToken = jwtUtil.generateToken(userDetails);
//
////            final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
//            var loginResponse = new LoginResponse(accessToken);
//            return loginResponse;
//        } catch (BadCredentialsException e) {
//            System.out.println("ISSUE" + e.getMessage());
//            throw new BadCredentialsException(e.getMessage());
//        }
//    }
//
//}
