package eldenmessages.config;

import eldenmessages.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    // constructor injection -> avoids circular references
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationProvider wired to your UserService
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            // register the provider first
            http.authenticationProvider(authenticationProvider());

            http
                    .requestCache(cache -> cache.disable()) // required for H2 console (prevents redirect-to-login saved request)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/signup", "/register", "/login", "/h2-console/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/register").permitAll()
                            .anyRequest().authenticated()
                    )
                    .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // allow H2 console
                    .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // allow frames for H2 UI
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/home", true)
                            .permitAll()
                    )
                    .logout(logout -> logout.logoutSuccessUrl("/login"));

            return http.build();
        }
    }
