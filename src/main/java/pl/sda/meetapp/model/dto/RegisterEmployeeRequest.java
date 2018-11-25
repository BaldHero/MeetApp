package pl.sda.meetapp.model.dto;

import lombok.Data;

@Data
public class RegisterEmployeeRequest {
    private String username;
    private String password;

}
