package ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.services.ClientService;
//
//@EnableWebSecurity
//@RequiredArgsConstructor
//@Slf4j
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final ClientService clientService;
//
//    @Override
//    protected  void configure(HttpSecurity http) throws  Exception{
//        log.info("Dao Authentication Provider");
//        http.authorizeRequests()
//                .antMatchers("/auth_page/**").authenticated()
//                .antMatchers("/user_info").authenticated()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID");
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(clientService);
//        return authenticationProvider;
//    }
//
//}


@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ClientService clientService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Dao Authentication Provider");
        http.authorizeRequests()
                .antMatchers("/user_info").authenticated()
                .antMatchers("/edit_product/**").hasAnyAuthority("EDIT_PRODUCT","ADMINISTRATE")
                .antMatchers("/delete_product/**").hasAnyAuthority("EDIT_PRODUCT","ADMINISTRATE")
                .antMatchers("/add_product/**").hasAnyAuthority("EDIT_PRODUCT","ADMINISTRATE")
                .antMatchers("/admin/**").hasAnyAuthority("ADMINISTRATE")
                .antMatchers("/order/**").hasAnyAuthority("EDIT_PRODUCT","COMPLETE_ORDER","ADMINISTRATE") // вообще для заказа достаточно просто идентификации, но для тестирования применения прав сделала так
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(clientService);
        return authenticationProvider;
    }
}

