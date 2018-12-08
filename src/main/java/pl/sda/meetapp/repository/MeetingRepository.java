package pl.sda.meetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.meetapp.model.Meeting;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findAllByEmployees_Email(String email);
    List<Meeting> findAllByEmployees_EmailOrderByDate(String email);
}
