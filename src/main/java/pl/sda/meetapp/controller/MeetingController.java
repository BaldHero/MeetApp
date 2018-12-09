package pl.sda.meetapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetapp.model.Employee;
import pl.sda.meetapp.model.dto.MeetingDto;
import pl.sda.meetapp.service.EmployeeAuthService;
import pl.sda.meetapp.service.MeetingService;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class MeetingController {

    private MeetingService meetingService;

    private EmployeeAuthService employeeAuthService;

    public MeetingController(MeetingService meetingService, EmployeeAuthService employeeAuthService) {
        this.meetingService = meetingService;
        this.employeeAuthService = employeeAuthService;
    }

    @PostMapping("/createMeeting")
    public String postMeeting(@Valid MeetingDto meetingDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/";
        }
        Optional<Employee> loggedInUser = employeeAuthService.getLoggedInUser();

        if (loggedInUser.isPresent()) {
            Employee employee = loggedInUser.get();
            meetingService.createMeeting(meetingDto, employee);
        }
        return "redirect:/";
    }
}
