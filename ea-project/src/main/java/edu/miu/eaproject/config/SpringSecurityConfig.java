package edu.miu.eaproject.config;
import edu.miu.eaproject.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //By default, AuthenticationManager(required to authenticate later) is not publicly accessible, and we need to explicitly expose it as a bean in our configuration class.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Enable CSRF and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();


        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();
        // Set permissions on endpoints
        http.authorizeHttpRequests()
                .requestMatchers("/api/login").permitAll()
              //  .requestMatchers("/api/authentication/**").permitAll()
          .anyRequest().authenticated();

        // Add JWT token filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
