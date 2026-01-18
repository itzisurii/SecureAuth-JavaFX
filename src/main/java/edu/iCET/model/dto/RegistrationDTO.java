package edu.iCET.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String email;
    private String password;
}
