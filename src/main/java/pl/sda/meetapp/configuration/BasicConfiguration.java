package pl.sda.meetapp.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.sda.meetapp.model.Employee;

@Configuration
public class BasicConfiguration  {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public Employee getEmployee(){
//        return new Employee();
//    }
}
