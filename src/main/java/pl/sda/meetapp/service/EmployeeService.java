package pl.sda.meetapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.meetapp.model.Employee;
import pl.sda.meetapp.model.dto.EmployeeDto;
import pl.sda.meetapp.repository.EmployeeRepository;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);

        employeeRepository.save(employee);
        return employeeDto;
    }

    public boolean canIAddEmail(EmployeeDto employeeDto) {
        boolean check;
        check = !employeeRepository.findByEmail(employeeDto.getEmail()).isPresent();
        log.info("Email check (can add): " + check);
        return check;
    }

    public List<Employee> printAll() {
        return employeeRepository.findAll();
    }
}
