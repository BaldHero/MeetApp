package pl.sda.meetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.meetapp.model.dto.RegisterEmployeeRequest;
import pl.sda.meetapp.service.EmployeeService;

@Controller
@RequestMapping("/employee/")
public class UserController {
    @Autowired
    private EmployeeService employeeService;

    // todo: rejestracja - która ma zapisać nowych użytkowników do bazy
    // todo: wylistowanie - metoda do pobierania z serwisu/bazy pracowników i wyświetlenie ich na stronie html
    // todo: ^^^^ th:each - pętla po kolekcji. - listę na oddzielnej stronie html
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user_register_form", new RegisterEmployeeRequest());

        return "forms/register";
    }

    @PostMapping("/register")
    public String postRegisterForm(RegisterEmployeeRequest registerEmployeeRequest) {
        System.out.println(registerEmployeeRequest);

        return "redirect:/employee/register";
    }
}
