package pl.sda.meetapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.meetapp.model.dto.EmployeeDto;
import pl.sda.meetapp.repository.EmployeeRepository;
import pl.sda.meetapp.service.EmployeeAuthService;
import pl.sda.meetapp.service.EmployeeService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/employee/")
public class UserController {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private EmployeeAuthService employeeAuthService;

    public UserController(EmployeeService employeeService, EmployeeRepository employeeRepository,
                          EmployeeAuthService employeeAuthService) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.employeeAuthService = employeeAuthService;
    }

    // todo: wylistowanie - metoda do pobierania z serwisu/bazy pracowników i wyświetlenie ich na stronie html
    // todo: ^^^^ th:each - pętla po kolekcji. - listę na oddzielnej stronie html
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user_register_form", new EmployeeDto());

        return "forms/register";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "forms/login";
    }

    @PostMapping("/register")
    public String postRegisterForm(@Valid EmployeeDto employeeDto, Model model) {
        log.info("Request: " + employeeDto);
        if(employeeService.canIAddEmail(employeeDto)) {
            try {
                employeeAuthService.sendNotification(employeeDto);
            } catch (MailException e) {
                log.error("Email could not be sent.");
            }
            employeeService.createEmployee(employeeDto);
            return "redirect:/employee/login";
        }
        model.addAttribute("message", "Registration failed.");
        model.addAttribute("user_register_form", employeeDto);
        return "forms/register";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @ModelAttribute("loggedIn")
    public boolean getIsLoggedIn() {
        return employeeAuthService.getLoggedInUser().isPresent();
    }
}
