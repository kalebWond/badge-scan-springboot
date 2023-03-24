package edu.miu.eaproject.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberLoginResponseDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
