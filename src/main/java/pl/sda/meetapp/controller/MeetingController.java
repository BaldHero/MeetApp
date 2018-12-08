package pl.sda.meetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetapp.model.Employee;
import pl.sda.meetapp.model.dto.MeetingDto;
import pl.sda.meetapp.service.EmployeeAuthService;
import pl.sda.meetapp.service.MeetingService;

import java.util.Optional;


@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private EmployeeAuthService employeeAuthService;

    @PostMapping("/createMeeting")
    public String postMeeting(MeetingDto meetingDto){
        Optional<Employee> loggedInUser = employeeAuthService.getLoggedInUser();

        if (loggedInUser.isPresent()) {
            Employee employee = loggedInUser.get();
            meetingService.createMeeting(meetingDto, employee);
        }
        return "redirect:/";
    }
}
