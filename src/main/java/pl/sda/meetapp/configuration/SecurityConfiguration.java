package pl.sda.meetapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private UserDetailsService employeeAuthService;

    public SecurityConfiguration(PasswordEncoder passwordEncoder, UserDetailsService employeeAuthService) {
        this.passwordEncoder = passwordEncoder;
        this.employeeAuthService = employeeAuthService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/",
                        "/employee/login",
                        "/employee/register").permitAll()
                .antMatchers("/employee/profile").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/employee/login")
                .defaultSuccessUrl("/").permitAll()
                .and()
                .logout().logoutUrl("/employee/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/")
                .permitAll();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(employeeAuthService);

        return provider;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
}
