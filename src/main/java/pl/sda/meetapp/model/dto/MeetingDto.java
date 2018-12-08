package pl.sda.meetapp.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.meetapp.model.Employee;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
public class MeetingDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

//    @NotBlank
    private List<Employee> employees;
}
