package pl.sda.meetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetapp.model.dto.MeetingDto;
import pl.sda.meetapp.service.MeetingService;

@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/createMeeting")
    public String postMeeting(MeetingDto meetingDto){
        meetingService.createMeeting(meetingDto);

        return "redirect:/";
    }
}
