package pl.sda.meetapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.meetapp.model.dto.EmployeeDto;
import pl.sda.meetapp.repository.EmployeeRepository;
import pl.sda.meetapp.service.EmployeeService;

@Controller
@Slf4j
@RequestMapping("/employee/")
public class UserController {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    public UserController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    // todo: rejestracja - która ma zapisać nowych użytkowników do bazy
    // todo: wylistowanie - metoda do pobierania z serwisu/bazy pracowników i wyświetlenie ich na stronie html
    // todo: ^^^^ th:each - pętla po kolekcji. - listę na oddzielnej stronie html
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user_register_form", new EmployeeDto());

        return "forms/register";
    }

    @PostMapping("/register")
    public String postRegisterForm(EmployeeDto employeeDto) {
//        System.out.println(registerEmployeeRequest);
        log.info("Request: " + employeeDto);
        if(!employeeService.checkEmail(employeeDto)) {
            employeeService.createEmployee(employeeDto);
            return "redirect:/employee/register";
        }
        return "redirect:/employee/register?error";
    }
}
