package ru.shvets.springshop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  23.10.2022 16:49
 */

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
//            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/**").permitAll()
            .and().formLogin()
//            .requestMatchers("/css/**").permitAll()
//            .requestMatchers("/user/**").hasAuthority("ROLE_USER")
//            .and().formLogin().loginPage("/log-in")
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val users: User.UserBuilder = User.withDefaultPasswordEncoder()
        val manager = InMemoryUserDetailsManager()
        manager.createUser(users.username("user").password("user").roles("USER").build())
        manager.createUser(users.username("admin").password("admin").roles("USER","ADMIN").build())
        return manager
    }

}
