package com.example.messageboard.config

import com.example.messageboard.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val userService: UserService
) {
    @Bean
    @Order(1)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize.anyRequest().permitAll()
            }
            .csrf { csrf ->
                csrf.disable()
            }

        return http.build()
//        http
//            .csrf().disable()
//            .authorizeHttpRequests { authz ->
//                authz
//                    .requestMatchers("/user/register").permitAll()
//                    .anyRequest().authenticated()
//            }
//            .formLogin { form ->
//                form
//                    .permitAll()
//            }
//            .logout { logout ->
//                logout
//                    .permitAll()
//            }
//        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
    @Bean
    fun userService(): UserService {
        return userService
    }
}