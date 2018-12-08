package pl.sda.meetapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.sda.meetapp.model.Employee;
import pl.sda.meetapp.model.Meeting;
import pl.sda.meetapp.service.EmployeeAuthService;
import pl.sda.meetapp.service.MeetingService;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    private EmployeeAuthService employeeAuthService;
    private MeetingService meetingService;

    public IndexController(EmployeeAuthService employeeAuthService, MeetingService meetingService) {
        this.employeeAuthService = employeeAuthService;
        this.meetingService = meetingService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        Optional<Employee> loggedInUser = employeeAuthService.getLoggedInUser();
        Meeting meeting = new Meeting();
        if (loggedInUser.isPresent()) {
            Employee employee = loggedInUser.get();
            model.addAttribute("employee", employee.getFirstName() + " " + employee.getLastName());
            List<Meeting> meetings = meetingService.printAllByEmail(employee.getEmail());
            model.addAttribute("meetings", meetings);
            model.addAttribute("meeting", meeting);
        }
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
