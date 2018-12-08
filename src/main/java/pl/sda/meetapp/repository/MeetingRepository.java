package pl.sda.meetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.meetapp.model.Meeting;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
//    List<Meeting> findAllByEmployees_Email(String email);

    @Query("select m from Meeting m join Employee e where e.email = :email")
    List<Meeting> findAllByEmail(@Param("email")String email);
}
