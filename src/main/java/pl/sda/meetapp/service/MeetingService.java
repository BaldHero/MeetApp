package pl.sda.meetapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sda.meetapp.model.Employee;
import pl.sda.meetapp.model.Meeting;
import pl.sda.meetapp.model.dto.MeetingDto;
import pl.sda.meetapp.repository.MeetingRepository;

import java.util.List;

@Service
@Slf4j
public class MeetingService {

    private MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public MeetingDto createMeeting(MeetingDto meetingDto, Employee employee) {
        Meeting meeting = new Meeting();
        BeanUtils.copyProperties(meetingDto, meeting);
        List<Meeting> meetings = employee.getMeetings();
        meetings.add(meeting);
        meetingRepository.save(meeting);
        return meetingDto;
    }

    public List<Meeting> printAllMeetings() {
        List<Meeting> foundMeetings = meetingRepository.findAll();
        return foundMeetings;
    }

    public List<Meeting> printAllByEmail(String email){
//        return meetingRepository.findAllByEmployees_Email(email);
        return meetingRepository.findAllByEmployees_EmailOrderByDate(email);
    }
}
