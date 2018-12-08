package pl.sda.meetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.meetapp.service.EmployeeAuthService;

@Controller
public class IndexController {

    private EmployeeAuthService employeeAuthService;

    public IndexController(EmployeeAuthService employeeAuthService) {
        this.employeeAuthService = employeeAuthService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
        }

    @ModelAttribute("loggedIn")
    public boolean getIsLoggedIn() {
        return employeeAuthService.getLoggedInUser().isPresent();
    }
//
//        RequestParam -> localhost:8080/parametr?nazwa=wartosc
//    PathVariable
//    @GetMapping("/parametr/{nejm}")
//    public String sendParam(
//            Model model,
//            @RequestParam(name = "nazwa") String nazwa,
//            @PathVariable(name = "nejm", required = true) String nejm) {
//        System.out.println(nazwa);
//
//        model.addAttribute("nazwa_atr", nazwa);
//        model.addAttribute("nejm_param", nejm);
//
//        return "param";
//    }
}
