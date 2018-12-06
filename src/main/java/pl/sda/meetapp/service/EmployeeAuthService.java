package pl.sda.meetapp.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.meetapp.model.Employee;
import pl.sda.meetapp.repository.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeAuthService implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    public EmployeeAuthService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);

        if(employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            return User.builder()
                    .username(employee.getEmail())
                    .password(employee.getPassword())
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found.");
    }

    public Optional<Employee> getLoggedInUser(){
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return Optional.empty();
        }

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return employeeRepository.findByEmail(user.getUsername());
        }

        return Optional.empty();
    }
}
