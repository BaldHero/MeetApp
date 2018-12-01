package pl.sda.meetapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.meetapp.model.Employee;
import pl.sda.meetapp.model.dto.EmployeeDto;
import pl.sda.meetapp.repository.EmployeeRepository;

@Service
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        employeeDto.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);

        employeeRepository.save(employee);
        return employeeDto;
    }

    public boolean checkEmail(EmployeeDto employeeDto) {
        boolean check;
        check = employeeRepository.findByEmail(employeeDto.getEmail()) != null;
        log.info("Email check: " + check);
        return check;
    }
}
