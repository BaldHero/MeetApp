package pl.sda.meetapp.model.dto;

import lombok.Data;
import lombok.ToString;
import pl.sda.meetapp.model.Department;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ToString(exclude = "password")
public class EmployeeDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @Email
    private String email;

    private Department department;

}
